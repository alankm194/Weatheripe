package com.techreturners.weatheripe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class EmptyInputParameterException extends RuntimeException {

    public EmptyInputParameterException(String message) {
        super(message);
    }
}

