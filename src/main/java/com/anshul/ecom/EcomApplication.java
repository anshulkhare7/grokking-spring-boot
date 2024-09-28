package com.anshul.ecom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcomApplication {

	private static final Logger log = LoggerFactory.getLogger(EcomApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EcomApplication.class, args);
	}

}
