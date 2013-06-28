package com.wedlum.styleprofile.business.model;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;

public class PhotoDetail implements Serializable {

    private static final long serialVersionUID = 1L;

	private final String id;
	private final String metadata;

	public PhotoDetail(String id, String metadata) {
		this.id = id;
		this.metadata = metadata;
	}

	public String getId() {
		return id;
	}

	public String getMetadata() {
		return metadata;
	}

	@Override
	public String toString() {
		return id;
	}

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            StringWriter result = new StringWriter();
            mapper.writeValue(result, this);
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

    public static PhotoDetail fromJson(String body) {
        return fromJson(body, PhotoDetail.class);
    }
}
