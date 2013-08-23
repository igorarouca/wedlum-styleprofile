package com.wedlum.styleprofile.domain.survey;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;

public class Session {

    private final String name;
	private final List<String> likedPhotos;
    private final List<String> allPhotos;

    public Session(String name, Map<String, List<String>> sessioData) {
    	this(name, sessioData.get("likedPhotos"), sessioData.get("allPhotos"));
    }

	public Session(String name, List<String> likedPhotos, List<String> allPhotos) {
		Validate.notNull(name, "name is required");
        Validate.notNull(likedPhotos, "likedPhotos is required");
		Validate.notNull(allPhotos, "allColors is required");

        this.name = name;
		this.likedPhotos = likedPhotos;
		this.allPhotos = allPhotos;
	}

	public String getName() {
		return name;
	}

	public List<String> getAllPhotos() {
		return allPhotos;
	}

	public List<String> getLikedPhotos() {
		return likedPhotos;
	}

}
