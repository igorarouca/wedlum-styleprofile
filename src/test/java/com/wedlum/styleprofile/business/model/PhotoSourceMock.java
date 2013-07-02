package com.wedlum.styleprofile.business.model;

import com.wedlum.styleprofile.util.observer.Observer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class PhotoSourceMock implements PhotoSource {
    private Observer<String> observer;
    private Map<String, String> storage = new LinkedHashMap<String, String>();

    @Override
    public void addPhoto(File photo) {
        throw new NotImplementedException();
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
