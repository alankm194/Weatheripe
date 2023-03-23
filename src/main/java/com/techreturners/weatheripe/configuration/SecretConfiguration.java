package com.techreturners.weatheripe.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties("secretkey")
public record SecretConfiguration(String weatherApiKey,
                                  String recipeAppKey,
                                  String recipeAppId,
                                  RSAPublicKey jwtPublicKey,
                                  RSAPrivateKey jwtPrivateKey) {
}