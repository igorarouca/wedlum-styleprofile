package com.wedlum.styleprofile.domain.survey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

class ColorRank {

	static List<String> rankColors(ColorSession colorSession) {
		List<String> likes = colorSession.getLikes();
		List<String> allColors = colorSession.getAllColors();

		List<String> result = unique(likes);
		Collections.sort(result, new ColorScoreComparator(likes, allColors));

		return result;

	}

	private static List<String> unique(List<String> likes) {
		return new ArrayList<String>(new HashSet<String>(likes));
	}

}
