package com.application.products.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Utils {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	private Utils() {
		// Prevent instantiation
	}

	/**
	 * 
	 * Converts an object to its JSON string representation.
	 * 
	 * @param object the object to convert to a JSON string
	 * 
	 * @return the JSON string representation of the object, or an empty string if
	 *         the input object is null
	 * 
	 * @throws RuntimeException if there is an error converting the object to JSON
	 */
	public static String mapToJsonString(Object object)  {
		if (object == null) {
			return "";
		}

		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			String errorMessage = String.format("Failed to convert object '%s' to JSON string: %s", object,
					e.getMessage());
			throw new RuntimeException(errorMessage, e);
		}
	}
}
