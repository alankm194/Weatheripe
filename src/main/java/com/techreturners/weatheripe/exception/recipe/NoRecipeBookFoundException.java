package com.techreturners.weatheripe.exception.recipe;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoRecipeBookFoundException extends RuntimeException {
    public NoRecipeBookFoundException(String message) {
        super(message);
    }
}
