package com.example.thymeleaf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
	@GetMapping("/m_insert")
	public String m_insert(Model model) {
		log.info("/m_insert...");
		
		model.addAttribute("content","thymeleaf/member/th_insert");
		model.addAttribute("title","회원가입 ....");
		return "thymeleaf/member/th_layout_main";
	}
	

	@PostMapping("/m_insertOK")
	public String m_insertOK(MemberVO vo, Model model) {
		log.info("/m_selectOne...");
		log.info("vo: {}", vo);

		//입력처리 완료시 분기 리다이렉
		
		return "redirect:m_selectAll";
	}
	
	@GetMapping("/m_selectAll")
	public String m_selectAll(Model model) {
		log.info("/m_selectAll...");
		
		List<MemberVO> vos = new ArrayList<>();
		vos.add(new MemberVO(1, "admin1", "hi12", "kim1", "011"));
		vos.add(new MemberVO(2, "admin2", "hi12", "kim1", "011"));
		vos.add(new MemberVO(3, "admin3", "hi12", "kim1", "011"));
		
		model.addAttribute("vos", vos);
		
		model.addAttribute("content","thymeleaf/member/th_selectAll");
		model.addAttribute("title","회원목록....");
		return "thymeleaf/member/th_layout_main";
	}
	
	@GetMapping("/m_selectOne")
	public String m_selectOne(MemberVO vo, Model model) {
		log.info("/m_selectOne...");
		log.info("vo: {}", vo);

		MemberVO vo2 = new MemberVO(1, "admin1", "hi12", "kim1", "011");
		
		model.addAttribute("vo2", vo2);
		
		model.addAttribute("content","thymeleaf/member/th_selectOne");
		model.addAttribute("title","회원정보....");
		return "thymeleaf/member/th_layout_main";
	}
	
	@PostMapping("/m_updateOK")
	public String m_updateOK(MemberVO vo) {
		log.info("/m_updateOK...");
		
		return "redirect:m_selectOne?num=" + vo.getNum();
	}
	
	
	@GetMapping("/m_delete")
	public String m_delete(Model model) {
		log.info("/m_insert...");
		
		model.addAttribute("content","thymeleaf/member/th_delete");
		model.addAttribute("title","회원삭제 ....");
		return "thymeleaf/member/th_layout_main";
	}
	
	@PostMapping("/m_deleteOK")
	public String m_deleteOK(MemberVO vo) {
		log.info("/m_deleteOK...");
		
		return "redirect:m_selectAll";
	}
}
