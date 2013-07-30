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
        allMiniPalettes.addAll(miniPalettesFor("singleColorSession1"));
        allMiniPalettes.addAll(miniPalettesFor("singleColorSession2"));
        allMiniPalettes.addAll(miniPalettesFor("singleColorSession3"));
        for (String miniPalette : allMiniPalettes)
            result.put("miniPalette" + ++miniPaletteIndex, miniPalette);
    }

    private List<String> miniPalettesFor(String session) {
        if (!profile.hasSession(session))
            return new ArrayList<String>();
        List<String> result = new ArrayList<String>();
        for (String item : getSession(session)){
            result.add(item.replace(".png", "_A.png"));
        }
        return result;
    }

	private List<String> getSession(String sessionName) {
        return profile.getSession(sessionName);
    }
}
