package com.wedlum.styleprofile.domain.photo;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import com.wedlum.styleprofile.domain.photo.PhotoSource;
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

    @Override
    public void setMetadata(String id, String metadata) {
        storage.put(id, metadata);
        if (observer != null) observer.update(id);
    }

    @Override
    public void delete(String id) {

    }

}
