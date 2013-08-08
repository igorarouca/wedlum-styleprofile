package com.wedlum.styleprofile.domain.photo;

import java.io.File;
import java.util.LinkedHashMap;
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
	public String[] getPhotos() {
		return storage.keySet().toArray(new String[0]);
	}

    @Override
    public void addObserver(Observer<String> observer) {
        this.observer = observer;
    }

    @Override
    public String getMetadata(String id) {
        return storage.get(id);
    }

    public void setMetadata(String photoId, String metadata) {
    	setMetadata(photoId, new ColorSwatchMetadata(metadata));
    }

    @Override
    public void setMetadata(String photoId, ColorSwatchMetadata metadata) {
    	storage.put(photoId, metadata.toString());
    	if (observer != null) observer.update(photoId);    	
    }

    @Override
    public void delete(String id) {}

}
