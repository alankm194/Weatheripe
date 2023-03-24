package com.techreturners.weatheripe.exception.userauthentication;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserSessionNotFoundException extends RuntimeException {

    public UserSessionNotFoundException(String message) {
        super(message);
    }
}
