package com.charts.general.exception;

import java.util.HashMap;
import java.util.Map;

public class ExceptionUtil {

    public static Map<String, String> createResponse(String message, Exception ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        response.put("error", ex.getMessage());
        return response;
    }

}
