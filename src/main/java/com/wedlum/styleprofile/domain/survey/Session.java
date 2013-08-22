package com.wedlum.styleprofile.domain.survey;

import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.Map;

public class Session {

	@SuppressWarnings("unchecked")
	public static Session fromMap(Map<String, Object> $session) {
		return new Session(
				(List<String>) $session.get("likedPhotos"),
				(List<String>) $session.get("allPhotos")
		);
	}

	private final List<String> likes;
	private final List<String> allPhotos;

	public Session(List<String> likes, List<String> allColors) {
		Validate.notNull(likes, "likes is required");
		Validate.notNull(allColors, "allColors is required");

		this.likes = likes;
		this.allPhotos = allColors;
	
	}


    public List<String> getLikes() {
		return likes;
	}

	public List<String> getAllPhotos() {
		return allPhotos;
	}

}
