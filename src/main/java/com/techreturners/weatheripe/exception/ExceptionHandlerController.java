package com.techreturners.weatheripe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class ExceptionHandlerController {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorType> handleResourceNotFoundException(
            RuntimeException ex) {
        return new ResponseEntity<>(
                new ErrorType(ex.getMessage(), "RECORD_NOT_FOUND", "EXTERNAL DATA NOT FOUND", "DATA"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({WeatherNotFoundException.class})
    public ResponseEntity<ErrorType> handleWeatherNotFoundException(
            RuntimeException ex) {
        return new ResponseEntity<>(
                new ErrorType(ex.getMessage(), "W0001", "RECORD_NOT_FOUND", "EXT"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NoMatchingWeatherException.class})
    public ResponseEntity<ErrorType> handleNoMatchingWeatherException(
            RuntimeException ex) {
        return new ResponseEntity<>(
                new ErrorType(ex.getMessage(), "W0002", "RECORD_NOT_FOUND", "DB"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NoMatchingFoodException.class})
    public ResponseEntity<ErrorType> handleNoMatchingFoodException(
            RuntimeException ex) {
        return new ResponseEntity<>(
                new ErrorType(ex.getMessage(), "W0003", "RECORD_NOT_FOUND", "DB"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UserSessionNotFoundException.class})
    public ResponseEntity<ErrorType> handleUserSessionNotFoundException(
            RuntimeException ex) {
        return new ResponseEntity<>(
                new ErrorType(ex.getMessage(), "W0004", "USER_SESSION_NOT_FOUND", "SESSION"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NoRecipeFoundException.class})
    public ResponseEntity<ErrorType> handleNoRecipeFoundException(
            RuntimeException ex) {
        return new ResponseEntity<>(
                new ErrorType(ex.getMessage(), "W0005", "RECORD_NOT_FOUND", "EXT"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({RecipeNotFoundException.class})
    public ResponseEntity<ErrorType> handleRecipeNotFoundException(
            RuntimeException ex) {
        return new ResponseEntity<>(
                new ErrorType(ex.getMessage(), "W0006", "RECORD_NOT_FOUND", "EXT"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NoMatchingCriteriaException.class})
    public ResponseEntity<ErrorType> handleNoMatchingCriteriaException(
            RuntimeException ex) {
        return new ResponseEntity<>(
                new ErrorType(ex.getMessage(), "W0007", "RECORD_NOT_FOUND", "EXT"),
                HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({NoRecipeBookFoundException.class})
    public ResponseEntity<ErrorType> handleNoRecipeBookFoundException(
            RuntimeException ex) {
        return new ResponseEntity<>(
                new ErrorType(ex.getMessage(), "W0008", "RECORD_NOT_FOUND", "DB"),
                HttpStatus.NOT_FOUND);
    }

}
