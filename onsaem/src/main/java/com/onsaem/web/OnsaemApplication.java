package com.onsaem.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.onsaem.web.**.mapper")
public class OnsaemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnsaemApplication.class, args);
	}

}
