package com.application.products.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UtilsTest {

	@Test
	@DisplayName("GIVEN an object of type Person"
			+ "  WHEN the object is converted to a JSON string using Utils.mapToJsonString"
			+ "THEN the resulting JSON string should match the expected value"

	)
	void testMapToJsonString() {
		String expectedJson = "{\"name\":\"Andrea\",\"age\":30,\"city\":\"New York\"}";
		Person person = new Person("Andrea", 30, "New York");

		String json = Utils.mapToJsonString(person);

		Assertions.assertEquals(expectedJson, json);
	}

	@Test
	void testMapToJsonStringWithInvalidObject() {
		Assertions.assertThrows(RuntimeException.class, () -> Utils.mapToJsonString(new InvalidObject()));
	}

	private static class Person {
		private String name;
		private int age;
		private String city;

		public Person(String name, int age, String city) {
			this.name = name;
			this.age = age;
			this.city = city;
		}

		public String getName() {
			return name;
		}

		public int getAge() {
			return age;
		}

		public String getCity() {
			return city;
		}
	}

	private static class InvalidObject {
		// Invalid object with no getters or setters
	}
}
