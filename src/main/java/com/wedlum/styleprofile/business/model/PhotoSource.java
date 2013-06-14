package com.wedlum.styleprofile.business.model;

import java.io.File;

public class PhotoSource implements Subject<File> {

	private Observer<File> observer;

	public void addPhoto(File photo) {
		observer.notify(photo);
	}

	public void register(Observer<File> receiver) {
		this.observer = receiver;
	}

}
