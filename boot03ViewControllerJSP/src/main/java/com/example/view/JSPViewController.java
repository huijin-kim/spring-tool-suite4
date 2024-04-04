package com.example.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class JSPViewController {

	@GetMapping({"m_insert.do"})
	public String m_insert() {
		log.info("/m_insert...");
		return "member/m_insert";
	}
	
	
}
