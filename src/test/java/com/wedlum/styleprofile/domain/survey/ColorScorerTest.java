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
        Assert.assertEquals(new Integer(50), subject.score("000S"));
	}


    @Test
    public void testScoreIs90IfOneOfTen() {
        photoSource = createPhotoSource();
        Session session = new Session("My Session", Arrays.asList("10b.png"),
                Arrays.asList(  "1a.png", "2b.png", "3a.png", "4b.png",
                                "5a.png", "6b.png", "7a.png", "8b.png",
                                "9a.png", "10b.png"));

        ColorScorer subject = new ColorScorer(photoSource, StyleProfilerTest.asSet(session));
        Assert.assertEquals(new Integer(90), subject.score("042S"));
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

        source.setMetadataWithoutValidation(
                "3a.png",
                "Photo:\n" +
                        "   Tags:\n" +
                        "       Colors:\n" +
                        "           - 000S\n" +
                        "           - 100S"
        );

        source.setMetadataWithoutValidation(
                "4b.png",
                "Photo:\n" +
                        "   Tags:\n" +
                        "       Colors:\n" +
                        "           - 220S\n" +
                        "           - 100S"
        );

        source.setMetadataWithoutValidation(
                "5a.png",
                "Photo:\n" +
                        "   Tags:\n" +
                        "       Colors:\n" +
                        "           - 000S\n" +
                        "           - 100S"
        );

        source.setMetadataWithoutValidation(
                "6b.png",
                "Photo:\n" +
                        "   Tags:\n" +
                        "       Colors:\n" +
                        "           - 220S\n" +
                        "           - 100S"
        );

        source.setMetadataWithoutValidation(
                "7a.png",
                "Photo:\n" +
                        "   Tags:\n" +
                        "       Colors:\n" +
                        "           - 000S\n" +
                        "           - 100S"
        );

        source.setMetadataWithoutValidation(
                "8b.png",
                "Photo:\n" +
                        "   Tags:\n" +
                        "       Colors:\n" +
                        "           - 220S\n" +
                        "           - 100S"
        );

        source.setMetadataWithoutValidation(
                "9a.png",
                "Photo:\n" +
                        "   Tags:\n" +
                        "       Colors:\n" +
                        "           - 000S\n" +
                        "           - 100S"
        );

        source.setMetadataWithoutValidation(
                "10b.png",
                "Photo:\n" +
                        "   Tags:\n" +
                        "       Colors:\n" +
                        "           - 220S\n" +
                        "           - 100S\n" +
                        "           - 042S"
        );

        return source;
    }

}
