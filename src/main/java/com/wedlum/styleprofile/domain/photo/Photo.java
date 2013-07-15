package com.wedlum.styleprofile.domain.photo;

import com.wedlum.styleprofile.util.json.JsonUtils;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;

public class Photo implements Serializable {

    private static final long serialVersionUID = 1L;

	private String id;
	private String metadata;

    public Photo(){
    }

	public Photo(String id, String metadata) {
		this.id = id;
		this.metadata = metadata;
    }

    public String getId() {
		return id;
	}

	public String getMetadata() {
        if (this.metadata == null || this.metadata.isEmpty()){
            return
                    "Photo:\n"+
                    "   Description:\n"+
                    "   Photographer:\n"+
                    "       Drue Carr\n"+
                    "   Tags:\n"+
                    "       Color:\n"+
                    "           Hue:\n"+
                    "           Saturation:\n"+
                    "           Value:\n" +
                    "       Palette:\n";
        }
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
        Photo subject = this;
        return JsonUtils.toJson(subject);
    }

    public static <T> T fromJson(String source, Class<T> c){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(source, c);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Photo fromJson(String body) {
        return fromJson(body, Photo.class);
    }
}
