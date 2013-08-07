package com.wedlum.styleprofile.domain.survey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

class ColorRank {

	static List<String> rankColors(final List<String> likes, List<String> allColors) {
		// (Liked / Times Shown) * (Magic_Factorö(Likedö2)) * 10
		List<String> result = unique(likes);

		Collections.sort(result, new Comparator<String>() {

			int score(String color) {
				count(color, likes);
				return 0;
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

		});

		return result;
    }

	private static List<String> unique(List<String> likes) {
		return new ArrayList<String>(new HashSet<String>(likes));
	}

}
