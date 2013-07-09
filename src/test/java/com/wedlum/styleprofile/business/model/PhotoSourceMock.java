package com.wedlum.styleprofile.business.model;

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
        observer.update(id);
    }
}
