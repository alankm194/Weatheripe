package com.techreturners.weatheripe.exception.weather;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
public class NoMatchingWeatherException extends RuntimeException{
    public NoMatchingWeatherException(String message) {
        super(message);
    }
}
