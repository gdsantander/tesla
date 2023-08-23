package com.gsantander.tesla.controllers;

import com.gsantander.tesla.exceptions.TslFoundException;
import com.gsantander.tesla.exceptions.TslNotFoundException;
import com.gsantander.tesla.exceptions.TslPasswordChangeException;
import com.gsantander.tesla.exceptions.TslTokenExpiredException;
import com.gsantander.tesla.tools.TslFunctions;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
public class AdviceController {

    // Bad Request

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for(ConstraintViolation<?> violation:violations) {
            String attribute = violation.getRootBeanClass().getSimpleName() + " > " + violation.getPropertyPath().toString();
            String message = violation.getMessage();
            message = StringUtils.substring(message,1,violation.getMessage().length()-1);
            errors.put(attribute,TslFunctions.getValidation(message));
        }
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ex.getMessage();
    }

    // Found

    @ResponseStatus(HttpStatus.FOUND)
    @ExceptionHandler(TslFoundException.class)
    public void handleFoundExceptions(TslFoundException ex) {
    }

    // Not Found

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TslNotFoundException.class)
    public void handleNotFoundExceptions(TslNotFoundException ex) {
    }

    // Unauthorized

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public String handleAuthenticationException(AuthenticationException ex) {
        return TslFunctions.getMessage("authenticationFailed") + " (" + ex.getMessage() + ")";
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(TslTokenExpiredException.class)
    public String handleTslTokenExpiredException(TslTokenExpiredException ex) {
        return TslFunctions.getMessage("tokenExpired");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ExpiredJwtException.class)
    public String handleExpiredJwtExceptionException(ExpiredJwtException ex) {
        return TslFunctions.getMessage("tokenExpired");
    }

    // Forbidden

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(TslPasswordChangeException.class)
    public String handleTslForbiddenException(Exception ex) {
        return ex.getMessage();
    }

}
