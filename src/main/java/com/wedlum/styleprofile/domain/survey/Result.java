package com.wedlum.styleprofile.domain.survey;

import java.io.Serializable;

public class Result implements Serializable {

	private static final long serialVersionUID = 1L;

	private String style;

	Result() {}

	public Result(String style) {
		this.style = style;
	}

	public String getStyle() {
		return style;
	}

	@Override
	public String toString() {
		return "Result [style=" + style + "]";
	}

}
