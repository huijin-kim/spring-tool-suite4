package com.example.rest;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class TestResult {
	//상수필드는 JSON컨버팅시 속성에서 자동배제
	public static final String CODE_ERROR = "500";
	public static final String CODE_SUCCESS = "200";
	
	public static final String MESSAGE_ERROR = "서버오류 입니다.";
	public static final String MESSAGE_SUCCESS = "정상처리 되었습니다.";

	private String code;
	private String message;
	private TestVO data;
}
