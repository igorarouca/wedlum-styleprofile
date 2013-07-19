package com.wedlum.styleprofile.util.json;

import com.wedlum.styleprofile.domain.photo.Photo;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;


public class JsonUtils {
    public static String toJson(Object subject) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            StringWriter result = new StringWriter();
            mapper.writeValue(result, subject);
            return result.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static <T> T fromJson(String source, Class<T> c){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(source, c);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
