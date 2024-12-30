package com.charts.files.utils;

import com.charts.general.exception.CsvContentException;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CsvProcessor {

    public static <T> void writeEntries(Writer writer, List<T> data) {
        try (CSVWriter csvWriter = new CSVWriter(writer)) {
            StatefulBeanToCsvBuilder<T> builder = new StatefulBeanToCsvBuilder<>(csvWriter);
            builder.build().write(data);
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            throw new CsvContentException(e.getMessage(), e);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> readEntries(MultipartFile payload, Class<T> clazz) throws IOException {
        InputStreamReader inputStream = new InputStreamReader(payload.getInputStream(), StandardCharsets.UTF_8);
        BufferedReader fileReader = new BufferedReader(inputStream);
        String stringReader = IOUtils.toString(fileReader);
        CSVReader csvReader = new CSVReader(new StringReader(stringReader));
        CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(csvReader)
                .withType(clazz)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        return csvToBean.parse();
    }

}
