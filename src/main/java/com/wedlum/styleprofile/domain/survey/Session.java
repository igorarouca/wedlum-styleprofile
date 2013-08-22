package com.wedlum.styleprofile.domain.survey;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;

public class Session {

    private final String name;
	private final List<String> likes;
    private final List<String> allPhotos;

    public Session(String name, Map<String, List<String>> sessioData) {
    	this(name, sessioData.get("likedPhotos"), sessioData.get("allPhotos"));
    }

	public Session(String name, List<String> likes, List<String> allColors) {
		Validate.notNull(name, "name is required");
        Validate.notNull(likes, "likes is required");
		Validate.notNull(allColors, "allColors is required");

        this.name = name;
		this.likes = likes;
		this.allPhotos = allColors;
	}

	public String getName() {
		return name;
	}

	public List<String> getAllPhotos() {
		return allPhotos;
	}

	public List<String> getLikes() {
		return likes;
	}

	public int getLikesCount() {
		return likes.size();
	}

}
