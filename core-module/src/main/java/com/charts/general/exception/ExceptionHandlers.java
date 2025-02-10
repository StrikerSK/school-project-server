package com.charts.general.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
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
        log.debug("{}", ex.getMessage(), ex);
        Map<String, String> response = createResponse("Parameter error", ex);
        return ResponseEntity.badRequest().body(response);
    }

    public static Map<String, String> createResponse(String message, Exception ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        response.put("error", ex.getMessage());
        return response;
    }

}
