package com.wedlum.styleprofile.domain.survey;

import com.wedlum.styleprofile.domain.photo.Photo;
import com.wedlum.styleprofile.domain.photo.PhotoSource;

import java.util.*;

class ColorRank {

    private PhotoSource source;

    public ColorRank(PhotoSource source){
        this.source = source;
    }

	List<String> rankColors(LinkedHashSet<Session> allSessions) {
        List<String> allColors = getAllColors(allSessions);
        List<String> allLikes = getAllLikes(allSessions);

        List<String> result = allColors;

        Collections.sort(result, new ColorScoreComparator(allColors, allLikes));

		return new ArrayList<String>(new LinkedHashSet<String>(result));
	}

    private List<String> getAllLikes(LinkedHashSet<Session> allSessions) {
        List<String> result = new ArrayList<String>();
        for (Session session : allSessions)
            result.addAll(session.getLikes());
        return getColors(result);
    }

    private List<String> getAllColors(LinkedHashSet<Session> allSessions) {
        List<String> result = new ArrayList<String>();
        for (Session session : allSessions)
            result.addAll(getColors(session.getAllPhotos()));
        return result;
    }

    private List<String> getColors(List<String> photos) {
        List<String> result = new ArrayList<String>();
        for(String $photo : photos){
            Photo photo = new Photo($photo, source.getMetadata($photo));
            result.addAll(photo.getColors());
        }
        return result;
    }

}
