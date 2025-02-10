package com.charts.general.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

import static com.charts.general.exception.ExceptionHandlers.createResponse;

@Slf4j
@Order
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        log.debug("{}", ex.getMessage(), ex);
        Map<String, String> response = createResponse("Internal Server Error", ex);
        return ResponseEntity.internalServerError().body(response);
    }

}
