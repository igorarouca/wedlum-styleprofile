package com.wedlum.styleprofile.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wedlum.styleprofile.domain.profile.TestScript;
import com.wedlum.styleprofile.util.web.JsonUtils;

@Controller
@RequestMapping(value = "style-profile")
public class StyleProfileController {

	@RequestMapping(value = "color/next-step", method = RequestMethod.GET)
	@ResponseBody
	public String nextStep(HttpServletRequest request) throws Exception{

        List testScript = TestScript.TestScript();

        Map profile = getProfile(request);
        for (Object $test : testScript){
            LinkedHashMap test = (LinkedHashMap) $test;
            if (!profile.containsKey(test.get("name")))
                return JsonUtils.toJson(test);
        }

        throw new IllegalStateException("end of test not yet implemented");
	}

    private Map getProfile(HttpServletRequest request) {
        String profileJson = request.getParameter("profile");
        if (profileJson != null){
            return JsonUtils.fromJson(profileJson, HashMap.class);
        }
        return new LinkedHashMap();
    }

}
