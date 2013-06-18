package com.wedlum.styleprofile.business.model;

import java.io.Serializable;

public class PhotoSummary implements Serializable {
    public final int id;
    public final String path;

    public PhotoSummary(String path) {
        this.id = path.hashCode();
        this.path = path;
    }
}
