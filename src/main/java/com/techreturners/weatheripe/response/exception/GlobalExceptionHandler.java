package com.techreturners.weatheripe.response.exception;


import com.techreturners.weatheripe.response.ErrorMessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {EmailAlreadyExistsException.class, UsernameAlreadyExistsException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessageResponse handleResourceAlreadyExistsException(LoginResourceAlreadyExistsException ex, WebRequest request) {
        return new ErrorMessageResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessageResponse handleResourceAlreadyExistsException(BadCredentialsException ex, WebRequest request) {
        return new ErrorMessageResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }
}
