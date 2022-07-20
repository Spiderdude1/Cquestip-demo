package com.cquestip.cquestip.Domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.Map;

public class HashMapConverter implements AttributeConverter<Map<String, Object>, String> {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Object> studentInfo) {

        String studentInfoJson = null;

        try {
            studentInfoJson = objectMapper.writeValueAsString(studentInfo);
        } catch (final JsonProcessingException e) {
            System.out.println("Json error");
        }



        return studentInfoJson;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String studentInfoJson) {

        Map<String, Object> studentInfo = null;

        try {
            studentInfo = objectMapper.readValue(studentInfoJson, Map.class);

        } catch (final IOException e)
        {
            System.out.println("JSON Reading error");
        }


        return studentInfo;
    }
}
