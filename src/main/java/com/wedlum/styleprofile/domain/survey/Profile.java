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
    private final Map<String, Map<String, Object>> sessionDataByName;

    public Profile() {
        this(new LinkedHashMap<Object, Object>(), new LinkedHashMap<Object, Object>());
	}

	@SuppressWarnings("unchecked")
	public Profile(Map<?, ?> $sessionsByName, Map<?, ?> $sessionDataByName) {
        this.photoIds = new ArrayList<String>();
        if ($sessionsByName.containsKey("photoIds"))
            this.photoIds = (List<String>) $sessionsByName.get("photoIds");
        this.sessionDataByName = (Map<String, Map<String, Object>>) $sessionDataByName;
	}

	public void addSession(String name, List<String> session) {
		Map<String, Object> sessionData = new LinkedHashMap<String, Object>();
		sessionData.put("likedPhotos", session);
		sessionDataByName.put(name + "Data", sessionData);
	}

	@SuppressWarnings("unchecked")
	public List<String> getSession(String name) {
		return (List<String>) sessionDataByName.get(name + "Data").get("likedPhotos");
	}

	public boolean hasSession(String name) {
		return sessionDataByName.containsKey(name + "Data");
	}

    public Iterable<String> allSessions() {
    	List<String> result = new ArrayList<String>();
        for (String sessionDataName: sessionDataByName.keySet())
        	result.add(sessionDataName.replaceAll("Data$", ""));

        return result;
    }

    public List<Photo> getLikedPhotos() {
    	List<String> likedPhotoIds = new ArrayList<String>();
        for (String sessionName : allSessions())
        	likedPhotoIds.addAll(getSession(sessionName));

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
