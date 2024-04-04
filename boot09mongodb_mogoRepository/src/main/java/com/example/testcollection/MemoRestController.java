package com.example.testcollection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MemoRestController {

	@Autowired
	MemoService service;

	// http://localhost:8991/findAll
	@GetMapping("findAll")
	public List<MemoVO> findAll(@RequestParam(value = "sort", defaultValue = "1") int sort,
			@RequestParam(value = "limit", defaultValue = "3") int limit) {
		log.info("/findAll...");
		log.info("sort:{}, limit:{}", sort, limit);

//		List<MemoVO> vos = service.findAll();//모두검색

		// http://localhost:8991/findAll?sort=-1&limit=10
		List<MemoVO> vos = service.findAll(sort, limit);// 정렬과 리미트

		return vos;
	}

	// http://localhost:8991/findAllCount
	@GetMapping("findAllCount")
	public Map<String, Long> findAllCount() {
		log.info("/findAllCount...");

		long totalRowCount = service.findAllCount();// 모든 행카운트얻기

		Map<String, Long> map = new HashMap<>();
		map.put("totalRowCount", totalRowCount);

		return map;
	}

	// findAllPageLimit?page=1&limit=5
	@GetMapping("findAllPageLimit")
	public List<MemoVO> findAllPageLimit(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "3") int limit) {
		log.info("/findAllPageLimit...");
		log.info("page:{}, limit:{}", page, limit);

		// Page타입으로 받아서 리스트만 뽑아서 json처리.
		Page<MemoVO> result = service.findAllPageLimit(page, limit);
		List<MemoVO> vos = result.getContent();
		return vos;
	}

	// http://localhost:8991/searchList
	@GetMapping("searchList")
	public List<MemoVO> searchList(String searchKey, String searchWord) {
		log.info("/searchList...");
		log.info("searchKey:{}, searchWord:{}", searchKey, searchWord);

		List<MemoVO> vos = service.searchList(searchKey, searchWord);

		return vos;
	}

	// http://localhost:8991/searchListPageLimit?searchKey=name&searchWord=nn&page=1&limit=5
	@GetMapping("searchListPageLimit")
	public List<MemoVO> searchListPageLimit(String searchKey, String searchWord,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "3") int limit) {
		log.info("/searchList...");
		log.info("searchKey:{}, searchWord:{}", searchKey, searchWord);
		log.info("page:{}, limit:{}", page, limit);

		// Page타입으로 받아서 리스트만 뽑아서 json처리.
		Page<MemoVO> result = service.searchListPageLimit(searchKey, searchWord,page, limit);
		List<MemoVO> vos = result.getContent();
		
		return vos;
	}

	// http://localhost:8991/searchListAndOrIn?filter=gtlt&age1=3&age2=6
	@GetMapping("searchListAndOrIn")
	public List<MemoVO> searchListAndOrIn(String filter,
			@RequestParam(name = "age1", defaultValue = "0") int age1,
			@RequestParam(name = "age2", defaultValue = "0") int age2) {
		log.info("/searchListAndOrIn...");
		log.info("filter:{},age1:{}, age2:{}",filter, age1, age2);

		List<MemoVO> vos = service.searchListAndOrIn(filter,age1, age2);

		return vos;
	}


	// http://localhost:8991/findOne?age=1
	@GetMapping("findOne")
	public MemoVO findOne(MemoVO vo) {
		log.info("/findOne...");
		log.info("{}", vo);

		MemoVO vo2 = service.findOne(vo);

		return vo2;
	}

	// http://localhost:8991/insertOneOK?age=110&name=lee&office=multi&phone=02
	@GetMapping("insertOneOK")
	public MemoVO insertOneOK(MemoVO vo) {
		log.info("/insertOneOK...");
		log.info("{}", vo);

		MemoVO vo2 = service.insertOne(vo);//insertOne >> save

		return vo2;
	}

	// http://localhost:8999/insertManyOK
	@GetMapping("insertManyOK")
	public Map<String, Long> insertManyOK() {
		log.info("/insertManyOK...");

		long result = service.insertMany();//insertMany..saveAll

		Map<String, Long> map = new HashMap<>();
		map.put("result", result);

		return map;
	}

	// http://localhost:8999/updateOneOK?age=110&name=lee11&office=multi11&phone=0211
	@GetMapping("updateOneOK")
	public MemoVO updateOneOK(MemoVO vo) {
		log.info("/updateOneOK...");
		log.info("{}", vo);

		// updateOne >>> save
		MemoVO vo2 = service.updateOne(vo);

		return vo2;
	}

	// http://localhost:8999/updateManyOK?age=101&name=aaa&office=bbb&phone=ccc
	@GetMapping("updateManyOK")
	public Map<String, Long> updateManyOK(MemoVO vo) {
		log.info("/updateManyOK...");
		log.info("{}", vo);

		// updateMany >>> updateMulti
		long result = service.updateMany(vo);

		Map<String, Long> map = new HashMap<>();
		map.put("result", result);

		return map;
	}

	// http://localhost:8999/deleteOneOK?mid=65f92faa7b77b83ff86dcd68
	// http://localhost:8999/deleteOneOK?age=110
	@GetMapping("deleteOneOK")
	public Map<String, Long> deleteOneOK(MemoVO vo) {
		log.info("/deleteOneOK...");
		log.info("{}", vo);

		// deleteOne >>> remove(차이점:같은 조건의 대상이 여러개인 경우은 여러개 지워짐)
		long result = service.deleteOne(vo);

		Map<String, Long> map = new HashMap<>();
		map.put("result", result);

		return map;
	}

	// http://localhost:8999/deleteManyOK?age=101
	@GetMapping("deleteManyOK")
	public Map<String, Long> deleteManyOK(MemoVO vo) {
		log.info("/deleteManyOK...");
		log.info("{}", vo);

		long result = service.deleteMany(vo);//101이상 모두 삭제

		Map<String, Long> map = new HashMap<>();
		map.put("result", result);

		return map;
	}

}
