package com.wedlum.styleprofile.util.web;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ParseUtils {

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
			throw new ParseException(e);
		}
	}

    @SuppressWarnings("unchecked")
	public static Map<String, Object> fromYaml(String yamlString) {
        try {
            return (Map<String, Object>) new Yaml().load(yamlString);
        } catch (Throwable ignored){
            return Collections.emptyMap();
        }
    }

	@SuppressWarnings("unchecked")
	public static Map<String, Object> fromJson(String json) {
		return fromJson(json, Map.class);
	}
}
