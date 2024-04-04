package com.example.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TestRestController {

	@Autowired
	private TestService service;

	public TestRestController() {
		log.info("TestRestController()...");
	}

	// http://localhost:8802/,http://localhost:8802/hello
	@GetMapping({ "/hello", "/" })
	public String hello() {
		log.info("/hello");

		// local > boot02RestController > 실행후> 더블클릭(오픈웹브라우저)> 브라우져 열림

		return "hello 스프링부트 good";
	}

	// http://localhost:8802/json_m_insertOK?num=1&name=lee&age=11
//	@GetMapping("/json_m_insertOK")
	@PostMapping("/json_m_insertOK") // 포스트맨에서 post전송테스트할것
	public Map<String, Integer> m_insertOK(TestVO vo) {
		log.info("/json_m_insertOK");
		log.info("{}", vo);

		// {"result":1}
		Map<String, Integer> map = new HashMap<>();
		map.put("result", service.insert(vo));

//		return "{\"result\":1}";
		return map;// 부트에서는 jackson라이브러리 없이 자동 컨버팅 가능
	}

	// 동일한 방식으로 m_updateOK,m_deleteOK 처리가능

	// http://localhost:8802/json_m_selectOne?num=1
	@GetMapping("/json_m_selectOne")
	public TestVO m_selectOne(TestVO vo) {
		log.info("/json_m_selectOne");
		log.info("{}", vo);

		TestVO vo2 = service.selectOne(vo);
		log.info("vo2:{}", vo2);

		return vo2;// 부트에서는 jackson라이브러리 없이 자동 컨버팅 가능
	}
	// http://localhost:8802/json_m_loginOK?id=admin&pw=hi1234
	@GetMapping("/json_m_loginOK")
//	@PostMapping("/json_m_loginOK")//포스트맨에서 post테스트
	public Map<String,String> json_m_loginOK(TestVO vo) {
		log.info("/json_m_loginOK");
		log.info("{}", vo);
		
		TestVO vo2 = service.login(vo);
		log.info("vo2:{}", vo2);
		
		Map<String,String> map = new HashMap<>();
		if(vo2 == null) {
			map.put("result", "login failed");
		}else {
			map.put("result", "login successed");
		}
		
		return map;
	}
	
	// http://localhost:8802/json_m_idCheck?id=admin
	@GetMapping("/json_m_idCheck")
//	@PostMapping("/json_m_idCheck")//포스트맨에서 post테스트
	public Map<String,String>  json_m_idCheck(TestVO vo) {
		log.info("/json_m_idCheck");
		log.info("{}", vo);
		
		TestVO vo2 = service.selectOne(vo);
		log.info("vo2:{}", vo2);
		
		Map<String,String> map = new HashMap<>();
		if(vo2 == null) {
			map.put("result", "OK");
		}else {
			map.put("result", "Not OK");
		}
		return map;
	}

	// http://localhost:8802/json_m_selectAll
	@GetMapping("/json_m_selectAll")
	public List<TestVO> json_m_selectAll() {
		log.info("/json_m_selectOne");

		List<TestVO> vos = service.selectAll();
		log.info("vos:{}", vos);

		return vos;// 부트에서는 jackson라이브러리 없이 자동 컨버팅 가능
	}
	
	
	//컴포넌트와 상수를 이용해서 메세지 처리가능.
	@Autowired
	private TestResult result;
	
	// http://localhost:8802/json_m_result?test=1
	@GetMapping("/json_m_result")
	public TestResult json_m_result(int test) {
		log.info("/json_m_result");
		log.info("test:{}",test);
		
		if(test==1) {
			result.setCode(TestResult.CODE_SUCCESS);
			result.setMessage(TestResult.MESSAGE_SUCCESS);
			TestVO vo = new TestVO();
			vo.setName("kim");
			result.setData(vo);
		}else {
			result.setCode(TestResult.CODE_ERROR);
			result.setMessage(TestResult.MESSAGE_ERROR);
		}
		
		return result;
	}
	
	

}// end class
