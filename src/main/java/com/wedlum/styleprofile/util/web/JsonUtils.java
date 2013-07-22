package com.wedlum.styleprofile.util.web;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtils {

	public static String toJson(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			StringWriter result = new StringWriter();
			mapper.writeValue(result, object);

			return result.toString();

		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	public static <T> T fromJson(String jsonString, Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(jsonString, clazz);

		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

}
