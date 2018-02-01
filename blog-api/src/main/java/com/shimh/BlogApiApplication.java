package com.shimh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogApiApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BlogApiApplication.class);
		app.run(args);
	}
}
