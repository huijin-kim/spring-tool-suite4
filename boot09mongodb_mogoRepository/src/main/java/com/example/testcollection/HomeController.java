package com.example.testcollection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class HomeController {

	@GetMapping({"/","hello"})
	public String hello() {
		log.info("/hello...");
		
		return "thymeleaf/th_hello";
	}
	
}
