package com.wedlum.styleprofile.domain.survey;

import java.util.List;

public class ColorSession {

	private final List<String> likes;
	private final List<String> allColors;

	public ColorSession(List<String> likes, List<String> allColors) {
		this.likes = likes;
		this.allColors = allColors;
	
	}

	public List<String> getLikes() {
		return likes;
	}

	public List<String> getAllColors() {
		return allColors;
	}

}
