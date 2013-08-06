package com.wedlum.styleprofile.domain.survey;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class ColorRank {

	static List<String> rankColors(List<String> likes, List<String> allColors) {
		// B3/A3*($E$2^(B3^2))*10
		// (Liked / Times Shown) * (Magic_Factorö(Likedö2)) * 10
		List<String> result = unique(likes);
		return result;
    }

	private static List<String> unique(List<String> likes) {
		return new ArrayList<String>(new HashSet<String>(likes));
	}

}
