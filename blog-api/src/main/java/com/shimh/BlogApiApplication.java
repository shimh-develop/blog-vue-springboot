package com.shimh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class BlogApiApplication {

	public static void main(String[] args) {
		//SpringApplication.run(BlogApiApplication.class, args);
		SpringApplication app = new SpringApplication(BlogApiApplication.class);
	    app.run(args);
	}
}
