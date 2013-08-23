package com.wedlum.styleprofile.domain.survey;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import com.wedlum.styleprofile.domain.photo.Photo;
import com.wedlum.styleprofile.domain.photo.PhotoSource;

class ColorScoreComparatorNG implements Comparator<String> {

	private final PhotoSource photoSource;
	private final Set<Session> sessions;

	ColorScoreComparatorNG(PhotoSource photoSource, Set<Session> sessions) {
		this.photoSource = photoSource;
		this.sessions = sessions;
	}

	@Override
	public int compare(String color1, String color2) {
		return getScore(color2).compareTo(getScore(color1));
	}

    private Integer getScore(String color) {
        int sumOfScores = 0;
        int relevantSessionsCount = 0;
        for (Session s : sessions){
            int scoreOfSession = getScoreOfColorInSession(color, s);
            sumOfScores += scoreOfSession;
            if (scoreOfSession >0) relevantSessionsCount++;
        }

        if (relevantSessionsCount == 0) return 0;
        return sumOfScores/relevantSessionsCount;
    }

    private int getScoreOfColorInSession(String color, Session s) {
        int totalPhotos = s.getAllPhotos().size();
        int photosContainingColor = countPhotosContainingColor(s.getAllPhotos(), color);
        int timesLiked = countPhotosContainingColor(s.getLikes(), color);

        return timesLiked * (totalPhotos - photosContainingColor);
    }

    private int countPhotosContainingColor(List<String> photos, String color) {
        int result = 0;
        for (String $photo : photos){
            Photo photo = photoSource.getPhoto($photo);
            if (photo.getColors().contains(color))
                result++;
        }
        return result;
    }

}
