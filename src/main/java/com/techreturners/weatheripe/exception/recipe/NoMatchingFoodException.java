package com.techreturners.weatheripe.exception.recipe;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
public class NoMatchingFoodException extends RuntimeException{
    public NoMatchingFoodException(String message) {
        super(message);
    }
}
