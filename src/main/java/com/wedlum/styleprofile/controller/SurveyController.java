package com.wedlum.styleprofile.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wedlum.styleprofile.domain.survey.Step;
import com.wedlum.styleprofile.domain.survey.Survey;
import com.wedlum.styleprofile.util.web.JsonUtils;

@Controller
@RequestMapping(value = "survey")
public class SurveyController {

	@RequestMapping(value = "nextstep", method = RequestMethod.GET)
	@ResponseBody
	public String nextStep(HttpServletRequest request) throws Exception{

        List<?> surveyScript = Survey.script();

        Map<?, ?> profile = getProfile(request);
        for (Object $step : surveyScript) {
        	LinkedHashMap<?, ?> stepAttributes = (LinkedHashMap<?, ?>) $step;

        	Step step = parse(stepAttributes);
        	if (!profile.containsKey(step.name)) {
                populate(step.data, profile);
        		return JsonUtils.toJson(step);
        	}
        }

        throw new IllegalStateException("end of test not yet implemented");
	}

	@SuppressWarnings("unchecked")
	private Step parse(LinkedHashMap<?, ?> stepAttributes) {
		Step step = new Step();
		step.name = stepAttributes.get("name").toString();
		step.type = stepAttributes.get("type").toString();
		step.data = ((ArrayList<String>) stepAttributes.get("data")).toArray(new String[0]);

		return step;
	}

	private void populate(String[] data, Map<?, ?> profile) {
		for (int i = 0; i < data.length; ++i) {
			String resolved = Survey.resolve(data[i], profile);
			data[i] = resolved;
		}
	}

    private Map<?, ?> getProfile(HttpServletRequest request) {
        String profileJson = request.getParameter("profile");
        if (profileJson != null){
            return JsonUtils.fromJson(profileJson, HashMap.class);
        }
        return new LinkedHashMap<Object, Object>();
    }

}
