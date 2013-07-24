package com.wedlum.styleprofile.domain.profile;

import java.util.Map;

public class StyleProfiler {

	@SuppressWarnings("unused")
	private final Map<?, ?> profile;

	public StyleProfiler(Map<?, ?> profile) {
		this.profile = profile;
	}

	public static String resolve(String item, Map<?, ?> profile) {
		StyleProfiler profiler = new StyleProfiler(profile);

		if (item.startsWith("{")) {
			return profiler.getValue(item.replace("{", "").replace("}", ""));
		}

		return item;
	}

	private String getValue(String itemName) {
		return "ToDo";
	}

}
