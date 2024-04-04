package com.example.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Component
@ConfigurationProperties("user") //application.properties 파일의 user.xx
public class UserComponent {
	
	//값 초기화 하는방법 2가지, 하드코딩, 생성자 생성해서 초기화
	private String user_name;
	private int user_age;
	
//	@Autowired
//	TestComponent testcom;
	
	public UserComponent() {
		log.info("UserComponent()...");
		
//		testcom.start();
	}
	
	public void start() {
		log.info("start()....");
	}

	@Override
	public String toString() {
		return "UserComponent [user_name=" + user_name + ", user_age=" + user_age + "]";
	}
	
	
	
}
