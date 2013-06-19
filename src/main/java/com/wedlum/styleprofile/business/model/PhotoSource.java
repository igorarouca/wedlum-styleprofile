package com.wedlum.styleprofile.business.model;

import java.io.File;

import com.wedlum.styleprofile.util.observer.GenericSubject;
import com.wedlum.styleprofile.util.observer.Observer;

public class PhotoSource {

	private GenericSubject<File> delegate = new GenericSubject<File>();

	public void addPhoto(File photo) {
		delegate.setChanged();
		delegate.notifyObservers(photo);
	}

	public void addObserver(Observer<File> observer) {
		delegate.registerObserver(observer);
	}

}
