package com.charts.general.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Order(1)
@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(value = InvalidParameterException.class)
    public ResponseEntity<Map<String, String>> handleException(InvalidParameterException ex) {
        return createResponse(HttpStatus.BAD_REQUEST.value(), "Parameter error", ex);
    }

    public static ResponseEntity<Map<String, String>> createResponse(Integer statusCode, String message, Exception ex) {
        log.debug(ex.getMessage(), ex);
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        response.put("error", ex.getMessage());
        return ResponseEntity.status(statusCode).body(response);
    }

}
