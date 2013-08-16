package com.wedlum.styleprofile.util.web;

import java.io.IOException;

public class ParseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ParseException(IOException e) {
		super(e);
	}


}
