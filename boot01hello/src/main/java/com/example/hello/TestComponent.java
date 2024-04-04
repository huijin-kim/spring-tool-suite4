package com.example.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestComponent implements ApplicationRunner{
	
	@Autowired
	UserComponent userCom;
	
	public TestComponent() {
		log.info("TestComponent()...");
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		log.info("run()....");
		userCom.start();
		
		log.info(userCom.toString());
	}
}
