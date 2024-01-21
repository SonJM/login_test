package com.encore.projecttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ProjecttestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjecttestApplication.class, args);
	}

}
