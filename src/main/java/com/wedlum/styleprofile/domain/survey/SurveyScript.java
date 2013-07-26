package com.wedlum.styleprofile.domain.survey;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.Transformer;

public abstract class SurveyScript implements Iterable<Step> {

	private List<Step> steps;

	public SurveyScript(List<Step> steps) {
		this.steps = steps;
	}

	public SurveyScript(Reader scriptSource, Transformer<Reader, List<Step>> scriptParser) {
		this(scriptParser.transform(scriptSource));
	}

	@Override
	public Iterator<Step> iterator() {
		return steps.iterator();
	}

	public List<Step> getSteps() {
		return steps;
	}

	@Override
	public String toString() {
		return "SurveyScript [steps=" + steps + "]";
	}

}
