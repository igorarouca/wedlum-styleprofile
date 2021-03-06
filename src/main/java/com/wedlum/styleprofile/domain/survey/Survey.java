package com.wedlum.styleprofile.domain.survey;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.wedlum.styleprofile.domain.photo.PhotoSource;

@Component(value="survey")
@Scope(value = "request")
public class Survey {

	private final SurveyScript script;

	@Inject
	private PhotoSource photoSource;

	@Inject
	public Survey(SurveyScript script) {
		this.script = script;
	}

	public Step nextStepFor(Profile profile) {
		for (Step step : script) {	    	   
			if (!profile.hasSession(step.getName())) {
	           populate(step.getData(), profile);
	           return step;
			}
	   	}

		throw new IllegalStateException("end of test not yet implemented");
	}

	public Result resultFor(Profile profile) {
		String style = new StyleProfiler(profile, photoSource).style(); 
		return new Result(style);
	}

	private void populate(String[] data, Profile profile) {
        Map<String, String> all = new StyleProfiler(profile, photoSource).resolveAll();
		for (int i = 0; i < data.length; ++i) {
			String resolved = resolve(data[i], profile, all);
			data[i] = resolved;
		}
	}

	public String resolve(String item, Profile profile, Map<String, String> all) {
		if (item.startsWith("{"))
			return getValue(item.replace("{", "").replace("}", ""), profile, all);

		return item;
	}
		 
	private String getValue(String itemName, Profile profile, Map<String, String> all) {

		if (!all.containsKey(itemName)) return itemName + " NOT FOUND";
		return all.get(itemName);
	}

}
