package com.techreturners.weatheripe.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("secretkey")
public record SecretConfiguration(String jwtKey, String weatherApiKey, String recipeAppKey, String recipeAppId, String recipeApiType) {
}