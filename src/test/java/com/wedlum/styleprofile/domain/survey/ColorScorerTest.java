package com.wedlum.styleprofile.domain.survey;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.wedlum.styleprofile.domain.photo.PhotoSource;
import com.wedlum.styleprofile.domain.photo.PhotoSourceMock;

public class ColorScorerTest {

	private ColorScoreComparatorNG subject;
	private PhotoSource photoSource;

	@Test
	public void testScoreIsZeroWhenColorAppearsInAllSwatches() {
		photoSource = createPhotoSource();
		Session session = new Session("My Session", Arrays.asList("1a.png"), Arrays.asList("1a.png", "2b.png"));

		ColorScorer subject = new ColorScorer(photoSource, StyleProfilerTest.asSet(session));
		Assert.assertEquals(new Integer(0), subject.score("100S"));
        Assert.assertEquals(new Integer(0), subject.score("220S"));
        Assert.assertEquals(new Integer(1), subject.score("000S"));
	}

	private static PhotoSourceMock createPhotoSource() {
        PhotoSourceMock source = new PhotoSourceMock();

        source.setMetadataWithoutValidation(
                "1a.png",
                "Photo:\n" +
                "   Tags:\n" +
                "       Colors:\n" +
                "           - 000S\n" +
                "           - 100S"
        );

        source.setMetadataWithoutValidation(
                "2b.png",
                "Photo:\n" +
                "   Tags:\n" +
                "       Colors:\n" +
                "           - 220S\n" +
                "           - 100S"
        );

        return source;
    }

}
