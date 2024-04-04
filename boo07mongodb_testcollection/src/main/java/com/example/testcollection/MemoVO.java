package com.example.testcollection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection="testcollection")
@Data
public class MemoVO {
	
	@Id
	private String _id;
	private String mid;
	private int age; 
	private String name;
	private String office;
	private String phone;
}
