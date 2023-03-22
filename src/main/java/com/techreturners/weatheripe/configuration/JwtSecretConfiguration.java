package com.techreturners.weatheripe.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("secretkey")
public record JwtSecretConfiguration(String jwtKey, String weatherApiKey) {
}
