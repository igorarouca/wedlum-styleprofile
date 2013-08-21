package com.wedlum.styleprofile.domain.photo;

import java.io.File;
import java.util.List;

import com.wedlum.styleprofile.util.observer.Observer;

public interface PhotoSource {

	String[] VALID_IMAGE_FILE_FORMATS = { ".png", ".jpg" };

	void addObserver(Observer<String> observer);

	void addPhoto(File photo);
	Photo getPhoto(String id);
	List<Photo> getPhotos();

    String getMetadata(String photoId);
    void setMetadata(String photoId, ColorSwatchMetadata metadata);

    void delete(String photoId);

}
