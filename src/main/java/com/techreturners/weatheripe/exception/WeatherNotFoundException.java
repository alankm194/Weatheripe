package com.techreturners.weatheripe.exception;

public class WeatherNotFoundException extends RuntimeException{
    public WeatherNotFoundException(String message) {
        super(message);
    }
}
