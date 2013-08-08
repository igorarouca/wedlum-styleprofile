package com.wedlum.styleprofile.domain.survey;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.wedlum.styleprofile.domain.photo.Photo;
import com.wedlum.styleprofile.domain.photo.PhotoSource;

// Profile is a DTO used to store the interaction of the user with the survey.
public class Profile {

	// List of photos the user has been exposed
	public List<String> photoIds;

	PhotoSource photoSource;

	// List of photos the user liked
	private Map<String, List<String>> sessionsByName;

    public Profile() {
        this(new LinkedHashMap<Object, Object>());
	}

	@SuppressWarnings("unchecked")
	public Profile(Map<?, ?> $sessionsByName) {
        this.photoIds = new ArrayList<String>();
        this.sessionsByName = (Map<String, List<String>>) $sessionsByName;
	}

	public void addSession(String name, List<String> session) {
		sessionsByName.put(name, session);
	}

	public List<String> getSession(String name) {
		return sessionsByName.get(name);
	}

	public boolean hasSession(String name) {
		return sessionsByName.containsKey(name);
	}

	public boolean isEmpty() {
		return sessionsByName.isEmpty();
	}

    public Iterable<String> allSessions() {
        return sessionsByName.keySet();
    }

    public List<Photo> getLikedPhotos() {
    	List<String> likedPhotoIds = new ArrayList<String>();
        for (List<String> likes : sessionsByName.values())
			likedPhotoIds.addAll(likes);

        return asPhotos(likedPhotoIds);
    }

	public List<Photo> getPhotos() {
		return asPhotos(photoIds);
	}

    private List<Photo> asPhotos(List<String> ids) {
        List<Photo> result = new ArrayList<Photo>();
        for (String id : ids)
			result.add(new Photo(id, photoSource.getMetadata(id)));

        return result;
    }
}
