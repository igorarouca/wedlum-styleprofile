package com.wedlum.styleprofile.domain.survey;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.wedlum.styleprofile.domain.photo.PhotoSource;
import com.wedlum.styleprofile.domain.photo.PhotoSourceMock;

public class ColorScoreComparatorNGTest {

	private ColorScoreComparatorNG subject;
	private PhotoSource photoSource;

	@Ignore
	@Test
	public void testScoreIsZeroWhenColorAppearsInAllSwatches() {
		photoSource = createPhotoSource();
		Session session = new Session("My Session", Arrays.asList("1a.png"), Arrays.asList("1a.png", "2b.png"));
		subject = new ColorScoreComparatorNG(photoSource, StyleProfilerTest.asSet(session));
		List<String> colors = Arrays.asList("220S", "100S", "000S");
		Collections.sort(colors, subject);
		Assert.assertEquals("000S, 220S, 100S", colors.toString());
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
