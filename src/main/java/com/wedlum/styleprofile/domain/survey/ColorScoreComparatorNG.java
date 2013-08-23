package com.wedlum.styleprofile.domain.survey;

import java.util.Comparator;

class ColorScoreComparatorNG implements Comparator<String> {

	private final ColorScorer colorScorer;

	public ColorScoreComparatorNG(ColorScorer scorer) {
		this.colorScorer = scorer;
	}

	@Override
	public int compare(String color1, String color2) {
		return colorScorer.getScore(color2).compareTo(colorScorer.getScore(color1));
	}

}
