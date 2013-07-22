package com.wedlum.styleprofile.domain.profile;

import java.io.InputStreamReader;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.wedlum.styleprofile.controller.StyleProfileController;
import com.wedlum.styleprofile.util.web.JsonUtils;

public class TestScript {


    public static List TestScript() throws ScriptException {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        engine.eval(new InputStreamReader(StyleProfileController.class.getResourceAsStream("/test-script.js")));
        String testScriptJson =engine.eval("JSON.stringify(wedlum.testScript)").toString();
        return JsonUtils.fromJson(testScriptJson, List.class);
    }
}
