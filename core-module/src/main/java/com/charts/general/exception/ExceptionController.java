package com.charts.general.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = InvalidParameterException.class)
    public ResponseEntity<Map<String, String>> handleException(InvalidParameterException ex) {
        Map<String, String> response = ExceptionUtil.createResponse("Parameter error", ex);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = CsvContentException.class)
    public ResponseEntity<Map<String, String>> handleException(CsvContentException ex) {
        Map<String, String> response = ExceptionUtil.createResponse("CSV content error", ex);
        return ResponseEntity.badRequest().body(response);
    }

}
