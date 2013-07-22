package com.wedlum.styleprofile.domain.photo;

import java.util.LinkedHashSet;
import java.util.Set;

import com.wedlum.styleprofile.util.observer.Observer;
import com.wedlum.styleprofile.util.web.JsonUtils;

public class PhotoGalleryImpl implements PhotoGallery {

	private Set<String> untagged = new LinkedHashSet<String>();
    private PhotoSource source;

    public PhotoGalleryImpl(PhotoSource source) {
        setPhotoSource(source);
	}

	private void setPhotoSource(PhotoSource source) {
		this.source = source;
        source.addObserver(new Observer<String>() {
            public void update(String photoId) {
                untagged.add(photoId);
            }
        });
	}

	@Override
	public Set<String> untagged() {
		return untagged;
	}

	@Override
	public Photo photo(String id) {
		for (String photoId : untagged)
			if (photoId.equals(id))
				return new Photo(id, "");
		
		throw new IllegalStateException("Photo not found: " + id);
				
	}

    @Override
	public String loadDetail(String id) {
        String stored = source.getMetadata(id);
        if (stored == "")
            return JsonUtils.toJson(new Photo(id, ""));
        return stored;
    }

    @Override
	public void storeDetail(String id, String metadata) {
        source.setMetadata(id, metadata);
    }

}
