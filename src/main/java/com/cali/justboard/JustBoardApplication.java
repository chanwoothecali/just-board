package com.cali.justboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class JustBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(JustBoardApplication.class, args);
	}

}
