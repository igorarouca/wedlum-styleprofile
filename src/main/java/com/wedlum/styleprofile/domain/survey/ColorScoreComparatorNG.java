package com.wedlum.styleprofile.domain.survey;

import java.util.Comparator;
import java.util.Set;

import com.wedlum.styleprofile.domain.photo.PhotoSource;

class ColorScoreComparatorNG implements Comparator<String> {

	private final PhotoSource photoSource;
	private final Set<Session> sessions;

	ColorScoreComparatorNG(PhotoSource photoSource, Set<Session> sessions) {
		this.photoSource = photoSource;
		this.sessions = sessions;
	}

	@Override
	public int compare(String color1, String color2) {
		
		return 0;
	}

}
