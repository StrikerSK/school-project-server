package com.javapid.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javapid.entity.PidData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FileParser {

    private static String getJsonData(MultipartFile originalFile) throws IOException {
        return new String(originalFile.getBytes());
    }

    public static PidData getEmployeeFromJson(MultipartFile originalFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = getJsonData(originalFile);
        return mapper.readValue(jsonString, PidData.class);
    }
}
