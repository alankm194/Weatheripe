package com.techreturners.weatheripe.exception;

import com.techreturners.weatheripe.exception.recipe.*;
import com.techreturners.weatheripe.exception.userauthentication.*;
import com.techreturners.weatheripe.exception.weather.NoMatchingWeatherException;
import com.techreturners.weatheripe.exception.weather.WeatherNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;



@RestControllerAdvice(annotations = RestController.class)
public class ExceptionHandlerController {



    @ExceptionHandler({WeatherNotFoundException.class})
    public ResponseEntity<ErrorResponseDTO> handleWeatherNotFoundException(
            RuntimeException ex) {
        return new ResponseEntity<>(
                new ErrorResponseDTO(ex.getMessage(), "W0001", "RECORD_NOT_FOUND", "EXT"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NoMatchingWeatherException.class})
    public ResponseEntity<ErrorResponseDTO> handleNoMatchingWeatherException(
            RuntimeException ex) {
        return new ResponseEntity<>(
                new ErrorResponseDTO(ex.getMessage(), "W0002", "RECORD_NOT_FOUND", "DB"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NoMatchingFoodException.class})
    public ResponseEntity<ErrorResponseDTO> handleNoMatchingFoodException(
            RuntimeException ex) {
        return new ResponseEntity<>(
                new ErrorResponseDTO(ex.getMessage(), "W0003", "RECORD_NOT_FOUND", "DB"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UserSessionNotFoundException.class})
    public ResponseEntity<ErrorResponseDTO> handleUserSessionNotFoundException(
            RuntimeException ex) {
        return new ResponseEntity<>(
                new ErrorResponseDTO(ex.getMessage(), "W0004", "USER_SESSION_NOT_FOUND", "SESSION"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NoRecipeFoundException.class})
    public ResponseEntity<ErrorResponseDTO> handleNoRecipeFoundException(
            RuntimeException ex) {
        return new ResponseEntity<>(
                new ErrorResponseDTO(ex.getMessage(), "W0005", "RECORD_NOT_FOUND", "EXT"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({RecipeNotFoundException.class})
    public ResponseEntity<ErrorResponseDTO> handleRecipeNotFoundException(
            RuntimeException ex) {
        return new ResponseEntity<>(
                new ErrorResponseDTO(ex.getMessage(), "W0006", "RECORD_NOT_FOUND", "EXT"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NoMatchingCriteriaException.class})
    public ResponseEntity<ErrorResponseDTO> handleNoMatchingCriteriaException(
            RuntimeException ex) {
        return new ResponseEntity<>(
                new ErrorResponseDTO(ex.getMessage(), "W0007", "RECORD_NOT_FOUND", "EXT"),
                HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({NoRecipeBookFoundException.class})
    public ResponseEntity<ErrorResponseDTO> handleNoRecipeBookFoundException(
            RuntimeException ex) {
        return new ResponseEntity<>(
                new ErrorResponseDTO(ex.getMessage(), "W0008", "RECORD_NOT_FOUND", "DB"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ErrorResponseDTO> handleUserNotFoundException(
            RuntimeException ex) {
        return new ResponseEntity<>(
                new ErrorResponseDTO(ex.getMessage(), "W0009", "RECORD_NOT_FOUND", "DB"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({RecipeNotBelongToUserException.class})
    public ResponseEntity<ErrorResponseDTO> handleRecipeNotBelongToUserException(
            RuntimeException ex) {
        return new ResponseEntity<>(
                new ErrorResponseDTO(ex.getMessage(), "W0010", "RECORD_NOT_BELONG_TO_USER", "DB"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({AccessDeniedException.class, AuthenticationException.class})
    public ResponseEntity<ErrorResponseDTO> handleInvalidJWTBearerToken(
            RuntimeException  ex) {
        return new ResponseEntity<>(
                new ErrorResponseDTO(ex.getMessage(), "W0011", "INVALID_ACCESS_TOKEN", "SC"),
                HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(
            RuntimeException ex) {
        return new ResponseEntity<>(
                new ErrorResponseDTO(ex.getMessage(), "W0012", "EXTERNAL_DATA_NOT_FOUND", "DATA"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {EmailAlreadyExistsException.class, UsernameAlreadyExistsException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDTO> handleLoginResourceAlreadyExistsException(LoginResourceAlreadyExistsException ex, WebRequest request) {
        return new ResponseEntity<>(
               new ErrorResponseDTO(ex.getMessage(),"W0012", "REGISTER_DETAILS_INVALID", "REGISTER"),
        HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDTO> handleResourceAlreadyExistsException(BadCredentialsException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponseDTO(ex.getMessage(), "W0013", "BAD_CREDENTIALS", "LOGIN"),
        HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler({InvalidApiKeyException.class})
    public ResponseEntity<ErrorResponseDTO> handleInvalidApiKeyException(
            RuntimeException ex) {

        return new ResponseEntity<>(
                new ErrorResponseDTO(ex.getMessage(), "W0014", "INVALID_API_KEY", "EXT"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        String errorResp = "Invalid arguments used during registration";
        if (ex.getErrorCount() > 0) {
            errorResp = ex.getAllErrors().get(0).getDefaultMessage();
        }
        return new ResponseEntity<>(new ErrorResponseDTO(errorResp, "W0015", "DETAILS_INVALID", "AUTH"),
                HttpStatus.BAD_REQUEST);
    }
}
