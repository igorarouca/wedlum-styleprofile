package com.wedlum.styleprofile.business.model;

import com.wedlum.styleprofile.util.observer.Observer;

import java.io.File;

public interface PhotoSource {
    void addPhoto(File photo);
    void addObserver(Observer<File> observer);

    String getMetadata(String id);
    void setMetadata(String id, String metadata);
}
