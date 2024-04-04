package com.example.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class Boot01helloApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot01helloApplication.class, args);
		
		log.trace("trace...");
		log.debug("debug..");
		log.info("info...");
		log.warn("warn..");
		log.error("error...");
		
		//컴포넌트를 생성해보자. 
		//TestComponent, UserComponent 
		//리로드시 컴포넌트 인식되어지는 순서는 알파벳순서. 
		//따라서 컴포넌트간의 DI를 사용하기 위해서는 
		//implements ApplicationRunner을 상속받고 run메소드를 재정의해야한다. (오버라이딩)
		//@Controller, @Service, @Repository등은 이것이 내장되어 있다. 
		
	}//end main
	
	

}//end class
