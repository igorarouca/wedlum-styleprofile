package com.wedlum.styleprofile.business.model;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.wedlum.styleprofile.util.observer.GenericSubject;
import com.wedlum.styleprofile.util.observer.Observer;

public class PhotoSourceUsingFileSystem implements PhotoSource {

    public static File STORAGE = new File("photo-storage");

    private GenericSubject<File> delegate = new GenericSubject<File>();

    public void addPhoto(File photo) {
        File storedPhoto = store(photo);
        delegate.setChanged();
		delegate.notifyObservers(storedPhoto);
	}

    public void addObserver(Observer<File> observer) {
        delegate.registerObserver(observer);
        redo(observer);
    }

    private File store(File photo) {
        File storedPhoto = new File(STORAGE, photo.getName());
        try {
            FileUtils.copyFile(photo, storedPhoto);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return storedPhoto;
    }

    private void redo(Observer<File> observer) {
        if(!STORAGE.exists()) STORAGE.mkdir();

    	for(File file: STORAGE.listFiles())
            observer.update(file);
    }

}
