package com.application.products.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Utils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private Utils() {
        // Prevent instantiation
    }

    public static String mapToJsonString(Object object) {
        try {
            return objectMapper.writeValueAsString( object );
        } catch (JsonProcessingException e) {
            String errorMessage = String.format( "Failed to convert object '%s' to JSON string: %s", object, e.getMessage() );
            throw new RuntimeException( errorMessage, e );
        }
    }
}

