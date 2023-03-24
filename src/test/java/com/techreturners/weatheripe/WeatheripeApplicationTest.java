package com.techreturners.weatheripe;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
class WeatheripeApplicationTest {

	private final int MAX_MEMORY_SIZE = 16*1024*1024;

	@Test
	void contextLoads() {}

}
