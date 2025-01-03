package com.charts.general.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = InvalidParameterException.class)
    public ResponseEntity<Map<String, String>> handleException(InvalidParameterException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Parameter error");
        response.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = CsvContentException.class)
    public ResponseEntity<Map<String, String>> handleException(CsvContentException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "CSV content error");
        response.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

}
