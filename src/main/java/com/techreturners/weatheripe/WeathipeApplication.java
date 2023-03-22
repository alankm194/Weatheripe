package com.techreturners.weatheripe;

import com.techreturners.weatheripe.configuration.SecretConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SecretConfiguration.class)
public class WeathipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeathipeApplication.class, args);
	}

}
