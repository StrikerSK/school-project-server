package com.charts.general.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(value = InvalidParameterException.class)
    public ResponseEntity<Map<String, String>> handleException(InvalidParameterException ex) {
        Map<String, String> response = createResponse("Parameter error", ex);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> response = createResponse("Internal Server Error", ex);
        return ResponseEntity.internalServerError().body(response);
    }

    public static Map<String, String> createResponse(String message, Exception ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        response.put("error", ex.getMessage());
        return response;
    }

}
