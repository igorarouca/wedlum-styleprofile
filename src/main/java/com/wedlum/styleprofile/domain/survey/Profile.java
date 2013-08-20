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

    private final Map<String, Map<String, Object>> sessionByName;

    public Profile() {
        this(new LinkedHashMap<Object, Object>());
	}

	@SuppressWarnings("unchecked")
	public Profile(Map<?, ?> $sessionByName) {
        this.sessionByName = (Map<String, Map<String, Object>>) $sessionByName;
	}

    public void addSession(String name, List<String> liked) {
        this.addSession(name, liked, Collections.EMPTY_LIST);
    }

	public void addSession(String name, List<String> liked, List<String> all) {
		Map<String, Object> sessionData = new LinkedHashMap<String, Object>();
		sessionData.put("likedPhotos", liked);
        sessionData.put("allPhotos", all);
		sessionByName.put(name, sessionData);
	}

	@SuppressWarnings("unchecked")
	public List<String> getSession(String name) {
		return (List<String>) sessionByName.get(name).get("likedPhotos");
	}

	public boolean hasSession(String name) {
		return sessionByName.containsKey(name);
	}

    public Iterable<String> allSessions() {
    	return sessionByName.keySet();
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
            Collection<? extends String> allPhotos = (Collection<? extends String>) sessionByName.get(sessionName).get("allPhotos");
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
