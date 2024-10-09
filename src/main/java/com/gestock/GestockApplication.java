package com.gestock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class GestockApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestockApplication.class, args);
	}

}
