package com.wedlum.styleprofile.domain.survey;

import java.util.Comparator;
import java.util.List;

final class ColorScoreComparator implements Comparator<String> {

	private final List<String> likes;
	private final List<String> allColors;

	ColorScoreComparator(List<String> likes, List<String> allColors) {
		this.likes = likes;
		this.allColors = allColors;
	}

	int score(String color) {
		int likesCount = count(color, likes);
	    int timesShown = count(color, allColors);
	    float magicFactor = 1.05f;

		return (int)
	            ((likesCount / timesShown)
	                    * (Math.pow(magicFactor, (likesCount * likesCount))) * 10);
	}

	private int count(String item, List<String> list) {
		int count = 0;
		for (String each : list)
			if (each.equals(item)) ++count;

		return count;
	}

	@Override public int compare(String color1, String color2) {
		return score(color1) - score(color2);
	}

}