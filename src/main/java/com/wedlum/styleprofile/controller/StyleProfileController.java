package com.wedlum.styleprofile.controller;

import com.wedlum.styleprofile.domain.profile.Step;
import com.wedlum.styleprofile.util.json.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "style-profile")
public class StyleProfileController {

    private static Step COLOR_SESSION1 = new Step("colorsession1", "session",
            "1.png",
            "2.png",
            "3.png",
            "4.png",
            "5.png",
            "6.png",
            "7.png",
            "8.png",
            "9.png",
            "10.png",
            "11.png",
            "12.png",
            "13.png",
            "14.png",
            "15.png",
            "16.png",
            "17.png",
            "18.png");

	@RequestMapping(value = "color/next-step", method = RequestMethod.GET)
	@ResponseBody
	public String nextStep() {
        return JsonUtils.toJson(COLOR_SESSION1);
	}

}
