package com.techreturners.weatheripe;

import com.techreturners.weatheripe.configuration.JwtSecretConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtSecretConfiguration.class)
public class WeathipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeathipeApplication.class, args);
	}

}
