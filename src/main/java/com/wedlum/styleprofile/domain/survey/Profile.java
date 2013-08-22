package com.wedlum.styleprofile.domain.survey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import com.wedlum.styleprofile.domain.photo.Photo;
import com.wedlum.styleprofile.domain.photo.PhotoSource;

// Profile is a DTO used to store the interaction of the user with the survey.
public class Profile {

	PhotoSource photoSource;

    private final Map<String, Session> sessionObjectByName;

    public Profile() {
        this(new LinkedHashMap<String, Map<String, Object>>());
	}

	public Profile(Map<String, Map<String, Object>> $sessionByName) {
        this.sessionObjectByName = parseSessions($sessionByName);
	}

    private static Map<String, Session> parseSessions(Map<String, Map<String, Object>> $sessionByName) {
        Map<String, Session> result = new LinkedHashMap<String, Session>();
        for (Map.Entry<String, Map<String, Object>> entry : $sessionByName.entrySet())
            result.put(entry.getKey(), Session.fromMap(entry.getValue()));

        return result;
    }

    @SuppressWarnings("unchecked")
	public void addSession(String name, List<String> liked) {
        this.addSession(name, liked, Collections.EMPTY_LIST);
    }

    // TODO: Remove
	public void addSession(String name, List<String> liked, List<String> all) {
		Map<String, Object> sessionData = new LinkedHashMap<String, Object>();
		sessionData.put("likedPhotos", liked);
        sessionData.put("allPhotos", all);
		sessionObjectByName.put(name, Session.fromMap(sessionData));
	}

	public List<String> getLikedPhotosFor(String session) {
		return sessionObjectByName.get(session).getLikes();
	}

	public boolean hasSession(String name) {
		return sessionObjectByName.containsKey(name);
	}

    public Iterable<String> allSessions() {
    	return sessionObjectByName.keySet();
    }

    public List<Photo> getLikedPhotos() {
    	List<String> likedPhotoIds = new ArrayList<String>();
        for (String sessionName : allSessions())
        	likedPhotoIds.addAll(getLikedPhotosFor(sessionName));

        return asPhotos(likedPhotoIds);
    }

	public List<Photo> getPhotos() {
        List<String> result = new ArrayList<String>();
        for (String sessionName : allSessions()) {
            List<String> allPhotos = sessionObjectByName.get(sessionName).getAllPhotos();
            if (allPhotos != null)
                result.addAll(allPhotos);
        }

        return asPhotos(result);
	}

    private List<Photo> asPhotos(List<String> ids) {
        List<Photo> result = new ArrayList<Photo>();
        for (String id : ids)
			result.add(photoSource.getPhoto(id));

        return result;
    }

    public LinkedHashSet<Session> allSessionsObject() {
        return new LinkedHashSet<Session>(sessionObjectByName.values());
    }

}
