package com.wedlum.styleprofile.business.model;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.wedlum.styleprofile.util.observer.GenericSubject;
import com.wedlum.styleprofile.util.observer.Observer;

public class PhotoSourceUsingFileSystem implements PhotoSource {

    public static File STORAGE = new File("photo-storage");

    private GenericSubject<String> delegate = new GenericSubject<String>();

    public void addPhoto(File photo) {
        String photoId = store(photo);
    	delegate.setChanged();
		delegate.notifyObservers(photoId);
	}

    public void addObserver(Observer<String> observer) {
        delegate.registerObserver(observer);
        redo(observer);
    }

    public String getMetadata(String id) {
        try {
            File file = getMetadataFile(id);
            if (!file.exists()) return "";
            return FileUtils.readFileToString(file);
        } catch (IOException e) {
            throw new IllegalStateException("Error reading metadata for " + id, e);
        }
    }

    public void setMetadata(String id, String metadata) {
        try {
            FileUtils.writeStringToFile(getMetadataFile(id), metadata);
        } catch (IOException e) {
            throw new IllegalStateException("Error storing metadata for " + id, e);
        }

        delegate.setChanged();
        delegate.notifyObservers(id);
    }

    private File getMetadataFile(String id) {
        return new File(STORAGE, id + ".metadata");
    }

    private String store(File photo) {
        File storedPhoto = new File(STORAGE, photo.getName());
        try {
            FileUtils.copyFile(photo, storedPhoto);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return storedPhoto.getName();
    }

    private void redo(Observer<String> observer) {
        if(!STORAGE.exists()) STORAGE.mkdir();

    	for(File file: STORAGE.listFiles()){
            if (file.getName().toLowerCase().endsWith(".png") || file.getName().toLowerCase().endsWith(".jpg"))
                observer.update(file.getName());
        }

    }

}
