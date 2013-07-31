package com.wedlum.styleprofile.domain.survey;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StyleProfiler {

	private final Profile profile;

	public StyleProfiler(Profile profile) {
		this.profile = profile;
	}

    public Map<String, String> resolveAll() {
        LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();
        addMiniPalettes(result);
        return result;
    }

    private void addMiniPalettes(LinkedHashMap<String, String> result) {
        int miniPaletteIndex = 0;

        List<String> allMiniPalettes = new ArrayList<String>();
        allMiniPalettes.addAll(miniPalettesFor("singleColorSession1", "_A"));
        allMiniPalettes.addAll(miniPalettesFor("singleColorSession2", "_A"));
        allMiniPalettes.addAll(miniPalettesFor("singleColorSession3", "_A"));

        allMiniPalettes.addAll(miniPalettesFor("singleColorSession1", "_C"));
        allMiniPalettes.addAll(miniPalettesFor("singleColorSession2", "_C"));
        allMiniPalettes.addAll(miniPalettesFor("singleColorSession3", "_C"));

        for (String miniPalette : allMiniPalettes)
            result.put("miniPalette" + ++miniPaletteIndex, miniPalette);
    }

    private List<String> miniPalettesFor(String sessionName, String suffix) {
        if (!profile.hasSession(sessionName))
            return new ArrayList<String>();

        List<String> result = new ArrayList<String>();
        for (String item : getSession(sessionName))
            result.add(item.replace(".png", suffix + ".png"));

        return result;
    }

	private List<String> getSession(String sessionName) {
        return profile.getSession(sessionName);
    }
}