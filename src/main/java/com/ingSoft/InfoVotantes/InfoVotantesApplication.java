package com.ingSoft.InfoVotantes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class InfoVotantesApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfoVotantesApplication.class, args);
	}

}
