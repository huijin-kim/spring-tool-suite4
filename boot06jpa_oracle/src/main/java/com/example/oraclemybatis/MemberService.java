package com.example.oraclemybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	private MemberDAO_JPA jpa;

	public MemberVO_JPA insertOK(MemberVO_JPA vo) {
		return jpa.save(vo); //pk 즉 num값이 있으면 수정, 없으면 입력, dao재정의 필요없음
	}

	public MemberVO_JPA updateOK(MemberVO_JPA vo) {
		return jpa.save(vo); //pk 즉 num값이 있으면 수정, 없으면 입력, dao재정의 필요없음
	}

	public int deleteOK(MemberVO_JPA vo) {
		return jpa.deleteByNum(vo.getNum()); //함수커스텀
	}

	public List<MemberVO_JPA> selectAll() {
//		return jpa.findAll();
//		return jpa.findByOrderByNumDesc();//역정렬해주는 메소드를 jpa규칙에 따라 빌드 가능
//		return jpa.findByOrderByNumAsc();//순정렬해주는 메소드를 jpa규칙에 따라 빌드 가능
		return jpa.selectAll_JPQL();//JPQL
	}
	public List<MemberVO_JPA> selectAllPageBlock(int cpage,int pageBlock) {
		int startRow = (cpage - 1) * pageBlock + 1;
		int endRow = startRow + pageBlock - 1;

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		
		return jpa.selectAllPageBlock(startRow, endRow);//네이티브쿼리사용 함수
	}

	public MemberVO_JPA selectOne(MemberVO_JPA vo) {
		return jpa.findByNum(vo.getNum());
	}
	
	public List<MemberVO_JPA> searchList(String searchKey,String searchWord) {
		
		//대소문자 구분함.
//		if(searchKey.equals("id")) {
//			return jpa.findByIdLike("%"+searchWord+"%");
//		}else if(searchKey.equals("name")) {
//			return jpa.findByNameLike("%"+searchWord+"%");
//		}else {
//			return jpa.findByTelLike("%"+searchWord+"%");
//		}
		
//		//대소문자 구분안함.
//		if(searchKey.equals("id")) {
//			return jpa.findByIdIgnoreCaseLike("%"+searchWord+"%");
//		}else if(searchKey.equals("name")) {
//			return jpa.findByNameIgnoreCaseLike("%"+searchWord+"%");
//		}else {
//			return jpa.findByTelIgnoreCaseLike("%"+searchWord+"%");
//		}
		//대소문자 구분안함.+ 정렬
		if(searchKey.equals("id")) {
			return jpa.findByIdIgnoreCaseLikeOrderByNumDesc("%"+searchWord+"%");
		}else if(searchKey.equals("name")) {
			return jpa.findByNameIgnoreCaseLikeOrderByNumDesc("%"+searchWord+"%");
		}else {
			return jpa.findByTelIgnoreCaseLikeOrderByNumDesc("%"+searchWord+"%");
		}
	}

	public List<MemberVO_JPA> searchListPageBlock(String searchKey, String searchWord, int cpage, int pageBlock) {
		
		int startRow = (cpage - 1) * pageBlock + 1;
		int endRow = startRow + pageBlock - 1;
		
		if(searchKey.equals("id")) {
			return jpa.searchListID_PAGE("%"+searchWord+"%",startRow,endRow);//네이티브 쿼리
		}else {
			return jpa.searchListNAME_PAGE("%"+searchWord+"%",startRow,endRow);//네이티브 쿼리
		}
	}

	public long getTotalRows() {
		return jpa.count();//내장함수 : dao에 재정의 안해도됨.
	}

	public long getSearchTotalRows(String searchKey, String searchWord) {
		
		if(searchKey.equals("id")) {
			return jpa.count_id("%"+searchWord+"%");
		}else {
			return jpa.count_name("%"+searchWord+"%");
		}
	}

}
