package com.eleserv.log_consumer_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LogConsumerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogConsumerServiceApplication.class, args);
	}

}
