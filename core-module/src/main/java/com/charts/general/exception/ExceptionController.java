package com.charts.general.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = InvalidParameterException.class)
    public ResponseEntity<Map<String, String>> handleParameterException(InvalidParameterException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Invalid parameters provided");
        response.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

}
