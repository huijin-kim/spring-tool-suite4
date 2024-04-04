package com.example.thymeleaf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {

	private int num;
	private String id;
	private String pw;
	private String name;
	private String tel;
	
}
