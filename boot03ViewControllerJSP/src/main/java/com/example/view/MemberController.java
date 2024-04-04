package com.example.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {

	@GetMapping({"/","/index"})
	public String index() {
		log.info("/index...");
		return "index";
	}
	
	
}
