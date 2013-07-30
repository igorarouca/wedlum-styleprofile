package com.wedlum.styleprofile.domain.survey;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections4.Transformer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "surveyScript")
@Scope(value = "request")
public class SurveyScriptFromFile extends SurveyScript {

	private static final String PATH = "/wedlum/styleprofile/survey/survey-script.js";

	@Inject
	public SurveyScriptFromFile(Transformer<Reader, List<Step>> fileParser) {
		super(fileReader(PATH), fileParser);
	}

	private static Reader fileReader(String filePath) {
		return new InputStreamReader(SurveyScriptFromFile.class.getResourceAsStream(filePath));
	}

}
