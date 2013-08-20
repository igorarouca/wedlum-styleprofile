package com.wedlum.styleprofile.domain.survey;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.wedlum.styleprofile.domain.photo.Photo;
import com.wedlum.styleprofile.domain.photo.PhotoSource;

// Profile is a DTO used to store the interaction of the user with the survey.
public class Profile {

	PhotoSource photoSource;

	// List of photos the user liked
    private final Map<String, Map<String, Object>> sessionDataByName;

    public Profile() {
        this(new LinkedHashMap<Object, Object>());
	}

	@SuppressWarnings("unchecked")
	public Profile(Map<?, ?> $sessionDataByName) {
        this.sessionDataByName = (Map<String, Map<String, Object>>) $sessionDataByName;
	}

    public void addSession(String name, List<String> liked) {
        this.addSession(name, liked, Collections.EMPTY_LIST);
    }

	public void addSession(String name, List<String> liked, List<String> all) {
		Map<String, Object> sessionData = new LinkedHashMap<String, Object>();
		sessionData.put("likedPhotos", liked);
        sessionData.put("allPhotos", all);
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
        List<String> result = new ArrayList<String>();
        for (String sessionName : allSessions()) {
            Collection<? extends String> allPhotos = (Collection<? extends String>) sessionDataByName.get(sessionName + "Data").get("allPhotos");
            if (allPhotos != null)
                result.addAll(allPhotos);
        }

        return asPhotos(result);
	}

    private List<Photo> asPhotos(List<String> ids) {
        List<Photo> result = new ArrayList<Photo>();
        for (String id : ids)
			result.add(new Photo(id, photoSource.getMetadata(id)));

        return result;
    }
}
