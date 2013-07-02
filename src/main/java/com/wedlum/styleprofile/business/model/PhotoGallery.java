package com.wedlum.styleprofile.business.model;

import java.util.LinkedHashSet;
import java.util.Set;

import com.wedlum.styleprofile.util.observer.Observer;

public class PhotoGallery {

	private Set<String> untagged = new LinkedHashSet<String>();
    private PhotoSource source;

    private PhotoGallery(PhotoSource source) {
        this.source = source;
        source.addObserver(new Observer<String>() {
            public void update(String photoId) {
                untagged.add(photoId);
            }
        });
	}

	public static PhotoGallery on(PhotoSource photoSource) {
		return new PhotoGallery(photoSource);
	}

	public Set<String> untagged() {
		return untagged;
	}

	public PhotoDetail photoDetail(String id) {
		for (String photoId : untagged)
			if (photoId.equals(id))
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
