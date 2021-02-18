package com.example.m3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example*")
public class M3Application {

	public static void main(String[] args) {
		SpringApplication.run(M3Application.class, args);
	}

}
