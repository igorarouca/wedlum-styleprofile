package com.wedlum.styleprofile.domain.profile;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.wedlum.styleprofile.util.web.JsonUtils;

public class Survey {

	@SuppressWarnings("unused")
	private final Map<?, ?> profile;

	public Survey(Map<?, ?> profile) {
		this.profile = profile;
	}

	public static String resolve(String item, Map<?, ?> profile) {
		Survey profiler = new Survey(profile);

		if (item.startsWith("{")) {
			return profiler.getValue(item.replace("{", "").replace("}", ""));
		}

		return item;
	}

	private String getValue(String itemName) {
		return "ToDo";
	}

    public static List<?> script() throws ScriptException {
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("JavaScript");

		engine.eval(new InputStreamReader(Survey.class.getResourceAsStream("/survey-script.js")));
		String surveyScriptJson = engine.eval("JSON.stringify(wedlum.styleprofile.survey.script)").toString();

		return JsonUtils.fromJson(surveyScriptJson, List.class);
    }

}
