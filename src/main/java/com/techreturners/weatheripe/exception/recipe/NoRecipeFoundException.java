package com.techreturners.weatheripe.exception.recipe;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoRecipeFoundException extends RuntimeException {
    public NoRecipeFoundException(String message) {
        super(message);
    }
}
