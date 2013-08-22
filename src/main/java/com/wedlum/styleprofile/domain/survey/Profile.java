package com.wedlum.styleprofile.domain.survey;

import java.util.*;

import com.wedlum.styleprofile.domain.photo.Photo;
import com.wedlum.styleprofile.domain.photo.PhotoSource;

// Profile is a DTO used to store the interaction of the user with the survey.
public class Profile {

	PhotoSource photoSource;

    private final Set<Session> sessions;
    private final Map<String, Session> sessionByName;

    public Profile() {
        this(new LinkedHashMap<String, Map<String, Object>>());
	}

	public Profile(Map<String, Map<String, Object>> $sessionByName) {
        this.sessions = parseSessions($sessionByName);
        this.sessionByName = new LinkedHashMap<String, Session>();
        for(Session s : sessions)
            sessionByName.put(s.getName(), s);
	}

    private static Set<Session> parseSessions(Map<String, Map<String, Object>> $sessionByName) {
        Set<Session> result = new LinkedHashSet<Session>();
        for (Map.Entry<String, Map<String, Object>> entry : $sessionByName.entrySet())
            result.add(Session.fromMap(entry.getKey(), entry.getValue()));

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
        Session session = Session.fromMap(name, sessionData);
        sessions.add(session);
        sessionByName.put(name, session);
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
