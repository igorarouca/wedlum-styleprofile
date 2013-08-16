package com.wedlum.styleprofile.controller;

import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.wedlum.styleprofile.util.web.ParseUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wedlum.styleprofile.domain.survey.Profile;
import com.wedlum.styleprofile.domain.survey.Result;
import com.wedlum.styleprofile.domain.survey.Step;
import com.wedlum.styleprofile.domain.survey.Survey;

@Controller
@RequestMapping(value = "survey")
@Scope(value = "request")
public class SurveyController {

	@Inject Survey survey;

	@RequestMapping(value = "nextstep", method = RequestMethod.POST)
	@ResponseBody
	public String nextStep(HttpServletRequest request) throws Exception {
		Step nextStep = survey.nextStepFor(readProfileFrom(request));
		return ParseUtils.toJson(nextStep);
	}

	@RequestMapping(value = "style", method = RequestMethod.GET)
	@ResponseBody
	public Result result(HttpServletRequest request) throws Exception {
		return survey.resultFor(readProfileFrom(request));
	}

    private Profile readProfileFrom(HttpServletRequest request) {
        String jsonProfile = request.getParameter("profile");
        if (jsonProfile != null)
            return new Profile(ParseUtils.fromJson(jsonProfile, HashMap.class));

        return new Profile();
    }

}
