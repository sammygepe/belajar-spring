package com.sammy.belajar_spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Error validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> validation(MethodArgumentNotValidException ex) {

        Map<String, Object> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(err ->
                        errors.put(
                                err.getField(),
                                err.getDefaultMessage()
                        ));

        return errors;
    }

    // Error user not found
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> notFound(UserNotFoundException ex) {

        Map<String, String> response = new HashMap<>();

        response.put("error", ex.getMessage());

        return response;
    }
}