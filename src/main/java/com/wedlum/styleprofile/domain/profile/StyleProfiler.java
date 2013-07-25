package com.wedlum.styleprofile.domain.profile;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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

    public Map<String, String> resolveAll() {
        LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();
        addMiniPalettes(result);
        return result;
    }

	private String getValue(String itemName) {

        Map<String, String> all = resolveAll();
        if (!all.containsKey(itemName)) return itemName + " not found!";
        return all.get(itemName);
	}

    private void addMiniPalettes(LinkedHashMap<String, String> result) {
        int miniPaletteIndex = 0;

        List<String> allMiniPalettes = new ArrayList<String>();
        allMiniPalettes.addAll(miniPalettesFor("singleColorSession1"));
        allMiniPalettes.addAll(miniPalettesFor("singleColorSession2"));
        allMiniPalettes.addAll(miniPalettesFor("singleColorSession3"));
        for (String miniPalette : allMiniPalettes)
            result.put("miniPalette" + ++miniPaletteIndex, miniPalette);
    }

    private List<String> miniPalettesFor(String session) {
        if (!profile.containsKey(session))
            return new ArrayList<String>();
        List<String> result = new ArrayList<String>();
        for (String item : getSession(session)){
            result.add(item.replace(".png", "_bold.png"));
        }
        return result;
    }

    private List<String> getSession(String sessionName) {
        return (List<String>)profile.get(sessionName);
    }
}
