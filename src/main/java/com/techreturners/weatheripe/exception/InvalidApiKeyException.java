package com.techreturners.weatheripe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NETWORK_AUTHENTICATION_REQUIRED)
public class InvalidApiKeyException extends RuntimeException{
    public InvalidApiKeyException(String message) {
        super(message);
    }
}

