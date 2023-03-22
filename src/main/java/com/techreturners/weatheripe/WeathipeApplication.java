package com.techreturners.weatheripe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableConfigurationProperties(SecretConfiguration.class)
public class WeathipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeathipeApplication.class, args);
	}

}
