package com.wedlum.styleprofile.domain.survey;

import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Named;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.collections4.Transformer;

import com.wedlum.styleprofile.util.web.ParseUtils;

@Named(value = "surveyScriptParser")
public class SurveyScriptJsParser implements Transformer<Reader, List<Step>> {

	@Override
	public List<Step> transform(Reader scriptReader) {
		List<Step> steps = new ArrayList<Step>();

		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("JavaScript");

		try {
			engine.eval(scriptReader);
			String jsonScript = engine.eval("JSON.stringify(wedlum.styleprofile.survey.script)").toString();

			List<?> $steps = ParseUtils.fromJson(jsonScript, List.class);
			for (Object $step : $steps)
				steps.add(new Step((LinkedHashMap<?, ?>) $step));

		} catch (ScriptException se) {
			throw new RuntimeException(se);
		}

		return steps;
	}

}
