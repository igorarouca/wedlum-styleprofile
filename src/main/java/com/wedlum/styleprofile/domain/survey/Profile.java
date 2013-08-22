package com.wedlum.styleprofile.domain.survey;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wedlum.styleprofile.domain.photo.Photo;
import com.wedlum.styleprofile.domain.photo.PhotoSource;

// Profile is a DTO used to store the interaction of the user with the survey.
public class Profile {

	PhotoSource photoSource;

    private final Set<Session> sessions;
    private final Map<String, Session> sessionByName;

    public Profile() {
        this(new LinkedHashSet<Session>());
	}

    public Profile(Set<Session> sessions) {
    	this.sessions = sessions;
    	this.sessionByName = new LinkedHashMap<String, Session>();

    	for (Session session : sessions)
        	sessionByName.put(session.getName(), session);
    }

	public Profile(Map<String, Map<String, List<String>>> $sessionByName) {
		this(parse($sessionByName));
	}

	private static Set<Session> parse(Map<String, Map<String, List<String>>> $sessionByName) {
		Set<Session> sessions = new LinkedHashSet<Session>();

		for (Map.Entry<String, Map<String, List<String>>> nameDataPair : $sessionByName.entrySet()) {
			String name = nameDataPair.getKey();
			Map<String, List<String>> data = nameDataPair.getValue();
			Session session = new Session(name, data);
			sessions.add(session);
		}

		return sessions;
	}

	public List<String> getLikedPhotosFor(String session) {
		return sessionByName.get(session).getLikes();
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
        	likedPhotoIds.addAll(getLikedPhotosFor(sessionName));

        return asPhotos(likedPhotoIds);
    }

	public List<Photo> getPhotos() {
        List<String> result = new ArrayList<String>();
        for (String sessionName : allSessions()) {
            List<String> allPhotos = sessionByName.get(sessionName).getAllPhotos();
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
        return (LinkedHashSet<Session>) sessions;
    }

}
