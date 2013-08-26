package com.wedlum.styleprofile.domain.survey;

import java.util.List;
import java.util.Set;

import com.wedlum.styleprofile.domain.photo.Photo;
import com.wedlum.styleprofile.domain.photo.PhotoSource;

class ColorScorer {

	private final Set<Session> sessions;
	private final PhotoSource photoSource;

	ColorScorer(PhotoSource photoSource, Set<Session> sessions) {
		this.photoSource = photoSource;
		this.sessions = sessions;
	}

	Integer score(String color) {
        int sumOfScores = 0;
        int relevantSessionsCount = 0;
        for (Session session : sessions) {
            int scoreOfSession = getScoreOfColorInSession(color, session);
            sumOfScores += scoreOfSession;
            if (scoreOfSession > 0) relevantSessionsCount++;
        }

        if (relevantSessionsCount == 0) return 0;
        return sumOfScores/relevantSessionsCount;
    }

	int getScoreOfColorInSession(String color, Session session) {
		List<String> allPhotos = session.getAllPhotos();
		int totalPhotos = allPhotos.size();
		int photosContainingColor = numberOfPhotosContainingColor(color, allPhotos);
		int timesLiked = numberOfPhotosContainingColor(color, session.getLikedPhotos());
        int likesAvailable = session.getLikedPhotos().size();

        if (timesLiked == 0) return 0;

        int proportionOfSwatchesContainingColor = (photosContainingColor * 100) / totalPhotos;
        int chanceOfPickingAtRandom = Math.min(proportionOfSwatchesContainingColor * likesAvailable, 100);
        int chanceLikeAtRandom = chanceOfPickingAtRandom / timesLiked;
        int score = 100 - chanceLikeAtRandom;

        return score;
    }

	private int numberOfPhotosContainingColor(String color, List<String> photos) {
        int result = 0;
        for (String $photo : photos) {
            Photo photo = photoSource.getPhoto($photo);
            if (photo.getColors().contains(color))
                result++;
        }

        return result;
    }

}
