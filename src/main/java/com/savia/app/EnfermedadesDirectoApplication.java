package com.savia.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

@SpringBootApplication(exclude = { MultipartAutoConfiguration.class })
public class EnfermedadesDirectoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnfermedadesDirectoApplication.class, args);
	}
}
