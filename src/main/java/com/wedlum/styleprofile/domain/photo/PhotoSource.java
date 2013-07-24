package com.wedlum.styleprofile.domain.photo;

import com.wedlum.styleprofile.util.observer.Observer;

import java.io.File;

public interface PhotoSource {

	String[] VALID_IMAGE_FILE_FORMATS = { ".png", ".jpg" };

	void addPhoto(File photo);

	void addObserver(Observer<String> observer);

    String getMetadata(String photoId);
    void setMetadata(String photoId, String metadata);

    void delete(String id);
}
