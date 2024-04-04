package com.example.testcollection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MemoRestController {
	
	@Autowired
	MemoService service;
	
	
	@GetMapping("findAll")
	public List<MemoVO> findAll() {
		log.info("/findAll..");
		
		List<MemoVO> vos = service.findAll();
		
		return vos;
	}
	
	@GetMapping("findAll2")
	public String findAll2(int page, int limit) {
		log.info("/findAll2..");
		
		
		return "findAll2";
	}
	
	
	@GetMapping("searchList")
	public String searchList(String searchKey, String Word) {
		log.info("/searchList..");
		
		return "searchList";
	}
	
	@GetMapping("searchList2")
	public String searchList2(int age, int age) {
		log.info("/searchList2..");
		
		return "searchList2";
	}
	
	@GetMapping("searchList3")
	public String searchList3(int age, int age) {
		log.info("/searchList3..");
		
		return "searchList3";
	}
	
	
	@GetMapping("findOne")
	public MemoVO findOne(MemoVO vo) {
		log.info("/findOne..");
		log.info("{}", vo);
		
		return "findOne";
	}
	
	
	//위에 부터 다시 해라. 
	@GetMapping("insertOneOK")
	public Map<String, Integer> insertOneOK(MemoVO vo) {
		log.info("/insertOneOK");
		log.info("{}", vo);
		
		MemoVO vo2 = service.insertOne(vo);
		
		Map<String, Integer> map = new HashMap<>();
		map.put("result", vo2.getAge());
		
		return map;
	}
	
	@GetMapping("insertManyOK")
	public Map<String, Long> insertManyOK() {
		log.info("/insertManyOK");
		
		long result = service.insertMany();
		
		Map<String, Long> map = new HashMap<>();
		map.put("result", result);
		
		return map;
	}
	
	@GetMapping("updateOneOK")
	public Map<String, Integer> updateOneOK(MemoVO vo) {
		log.info("/insertOneOK");
		log.info("{}", vo);
		
		//updateOne >> updateFirst
		MemoVO vo2 = service.updateOne(vo);
		
		Map<String, Integer> map = new HashMap<>();
		map.put("result", vo2.getAge());
		
		return map;
	}
	
	
	
	
}
