package com.wedlum.styleprofile.domain.survey;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

@Named(value="survey")
public class Survey {

	private final SurveyScript script;

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

	private void populate(String[] data, Profile profile) {
		for (int i = 0; i < data.length; ++i) {
			String resolved = resolve(data[i], profile);
			data[i] = resolved;
		}
	}

	public String resolve(String item, Profile profile) {
		if (item.startsWith("{"))
			return getValue(item.replace("{", "").replace("}", ""), profile);

		return item;
	}
		 
	private String getValue(String itemName, Profile profile) {
		Map<String, String> all = new StyleProfiler(profile).resolveAll();
		if (!all.containsKey(itemName)) return itemName + " NOT FOUND";
		return all.get(itemName);
	}

}
