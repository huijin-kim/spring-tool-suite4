package com.example.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Boot02RestControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot02RestControllerApplication.class, args);
		
		//application.properties
		//server.port=8802
		
		//@RestController 부트에서 제공한다.(@ResponseBody없이 사용가능한 컨트롤러)
		//TestRestController 생성
		
		//@RequestMapping대신 @GetMapping , @PostMapping을 사용할 수있다.
		//예: hello메소드를 생성시 선언하면됨.
	}

}
