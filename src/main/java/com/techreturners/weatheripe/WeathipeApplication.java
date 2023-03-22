package com.techreturners.weatheripe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class WeathipeApplication {
	private final int MAX_MEMORY_SIZE = 16*1024*1024;

	public static void main(String[] args) {
		SpringApplication.run(WeathipeApplication.class, args);
	}

	@Bean
	public WebClient getWebClient(){
		final ExchangeStrategies strategies = ExchangeStrategies.builder()
				.codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(MAX_MEMORY_SIZE))
				.build();
		return WebClient.builder()
				.exchangeStrategies(strategies)
				.build();
	}
}