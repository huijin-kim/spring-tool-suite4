package com.example.oraclemybatis;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberDAO_JPA extends JpaRepository<MemberVO_JPA, Object>{

	//***jpa에 내장된 함수 또는 약속된 규칙에 맞게 정의한 함수.*** 
	public List<MemberVO_JPA> findAll();
	public List<MemberVO_JPA> findByOrderByNumDesc();
	public List<MemberVO_JPA> findByOrderByNumAsc();
	
	
	//***JPQL : @Query(객체를 테이블로 하는 쿼리)***
	@Query("select vo from MemberVO_JPA vo order by num desc") //객체명은 대소분자 구분함.
	public List<MemberVO_JPA> selectAll_JPQL();
	
	//***네이티브쿼리(SQL) : @Query(nativeQuery=true,value="일반쿼리문") //복잡한쿼리:서브쿼리,조인
	@Query(nativeQuery=true,
			value="select rnum,num,user_id ,user_pw,user_name,user_tel,regdate  "
			+ "		from (select ROW_NUMBER() OVER(ORDER BY num desc) AS rnum,"
			+ "		num,user_id ,user_pw,user_name,user_tel,regdate from member_jpa)  "
			+ "		where rnum between ?1 and ?2")
	public List<MemberVO_JPA> selectAllPageBlock(Integer startRow,Integer endRow);
	
	//대소분자구분하는 검색
	public List<MemberVO_JPA> findByIdLike(String id);
	public List<MemberVO_JPA> findByNameLike(String name);
	public List<MemberVO_JPA> findByTelLike(String tel);
	
	//대소분자구분 없는 검색
	public List<MemberVO_JPA> findByIdIgnoreCaseLike(String id);
	public List<MemberVO_JPA> findByNameIgnoreCaseLike(String name);
	public List<MemberVO_JPA> findByTelIgnoreCaseLike(String tel);
	
	//대소분자구분 없는 검색 + 정렬
	public List<MemberVO_JPA> findByIdIgnoreCaseLikeOrderByNumDesc(String id);
	public List<MemberVO_JPA> findByNameIgnoreCaseLikeOrderByNumDesc(String name);
	public List<MemberVO_JPA> findByTelIgnoreCaseLikeOrderByNumDesc(String tel);
	
	//네이티브쿼리-searchListPageBlock
	@Query(nativeQuery=true,
			value="select rnum,num,user_id ,user_pw,user_name,user_tel,regdate  "
			+ "		from (select ROW_NUMBER() OVER(ORDER BY num desc) AS rnum,"
			+ "		num,user_id ,user_pw,user_name,user_tel,regdate from member_jpa"
			+ "		where upper(user_id) like upper(?1))  "
			+ "		where rnum between ?2 and ?3")
	public List<MemberVO_JPA> searchListID_PAGE(String searchWord, int startRow, int endRow);
	
	@Query(nativeQuery=true,
			value="select rnum,num,user_id ,user_pw,user_name,user_tel,regdate  "
			+ "		from (select ROW_NUMBER() OVER(ORDER BY num desc) AS rnum,"
			+ "		num,user_id ,user_pw,user_name,user_tel,regdate from member_jpa"
			+ "		where upper(user_name) like upper(?1))  "
			+ "		where rnum between ?2 and ?3")
	public List<MemberVO_JPA> searchListNAME_PAGE(String searchWord, int startRow, int endRow);
	
	@Query(nativeQuery=true,
			value="select count(*) total_rows from member_jpa  where upper(user_id) like upper(?1)")
	public long count_id(String searchWord);
	
	@Query(nativeQuery=true,
			value="select count(*) total_rows from member_jpa  where upper(user_name) like upper(?1)")
	public long count_name(String searchWord);
	
	//내장함수 커스텀
	public MemberVO_JPA findByNum(int num);
	
	//내장함수 커스텀 : 삭제시는 컨트롤러에 반드시 @Transactional
	public int deleteByNum(int num);
	
	
	
}//end interface
