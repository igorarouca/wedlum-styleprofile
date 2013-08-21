package com.wedlum.styleprofile.domain.photo;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.wedlum.styleprofile.util.observer.Observer;

public class PhotoSourceMock implements PhotoSource {

	private Observer<String> observer;
    private Map<String, String> storage = new LinkedHashMap<String, String>();

    @Override
    public void addPhoto(File photo) {
        throw new RuntimeException("Not Implemented Yet!");
    }

	@Override
	public List<Photo> getPhotos() {
		List<Photo> result = new ArrayList<Photo>();
		for (String photoId : storage.keySet())
			result.add(getPhoto(photoId));

		return result;
	}

    @Override
    public void addObserver(Observer<String> observer) {
        this.observer = observer;
    }

    @Override
    public String getMetadata(String id) {
        return storage.get(id);
    }

    public void setMetadataWithoutValidation(String photoId, String metadata) {
        storage.put(photoId, metadata);
        if (observer != null) observer.update(photoId);
    }

    @Override
    public void setMetadata(String photoId, ColorSwatchMetadata metadata) {
    	setMetadataWithoutValidation(photoId, metadata.toString());
    }

    @Override
    public void delete(String id) {}

    @Override
    public Photo getPhoto(String id) {
        return new Photo(id, getMetadata(id));
    }

}
