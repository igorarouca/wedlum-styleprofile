package com.wedlum.styleprofile.business.model;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.Set;

import com.wedlum.styleprofile.util.observer.Observer;

public class PhotoGallery {

	private Set<File> untagged = new LinkedHashSet<File>();
    private PhotoSource source;

    private PhotoGallery(PhotoSource source) {
        this.source = source;
        source.addObserver(new Observer<File>() {
            public void update(File photo) {
                untagged.add(photo);
            }
        });
	}

	public static PhotoGallery on(PhotoSource photoSource) {
		return new PhotoGallery(photoSource);
	}

	public Set<File> untagged() {
		return untagged;
	}

	public PhotoDetail photoDetail(String id) {
		for (File photo : untagged)
			if (photo.getName().equals(id))
				return new PhotoDetail(id, "");
		
		throw new IllegalStateException("Photo not found: " + id);
				
	}

    public String loadDetail(String id) {
        String stored = source.getMetadata(id);
        if (stored == "")
            return new PhotoDetail(id, "").toJson();
        return stored;
    }

    public void storeDetail(String id, String metadata) {
        source.setMetadata(id, metadata);
    }
}
