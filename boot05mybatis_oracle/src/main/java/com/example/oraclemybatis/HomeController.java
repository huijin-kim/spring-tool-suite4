package com.example.oraclemybatis;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

	@GetMapping({"/","/hello"})
	public String hello(Model model) {
		log.info("/hello...");

		Date to_day = new Date();//Wed Feb 21 16:35:13 KST 2024
		model.addAttribute("to_day",to_day);

		MemberVO vo = new MemberVO(1, "admin", "hi1234", "kim", "02");
		model.addAttribute("vo",vo);

		return "thymeleaf/th_hello";
	}

	@GetMapping("/layout_main")
	public String layout_main() {
		log.info("/layout_main...");


		return "thymeleaf/th_layout_main";
	}

	@GetMapping("/layout_main02")
	public String layout_main02(Model model) {
		log.info("/layout_main02...");

		//thymeleaf/th_content
		model.addAttribute("content","thymeleaf/th_content");
		return "thymeleaf/th_layout_main02";
	}

	@GetMapping("/layout_main03")
	public String layout_main03(Model model) {
		log.info("/layout_main03...");

		//thymeleaf/th_content
		model.addAttribute("content","thymeleaf/th_content");
		model.addAttribute("title","new title....");
		return "thymeleaf/th_layout_main03";
	}


}
