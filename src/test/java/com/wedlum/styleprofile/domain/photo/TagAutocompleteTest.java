package com.wedlum.styleprofile.domain.photo;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.wedlum.styleprofile.domain.photo.PhotoSource;
import com.wedlum.styleprofile.domain.photo.TagAutocomplete;
import com.wedlum.styleprofile.domain.photo.TagAutocompleteImpl;

public class TagAutocompleteTest {

	private PhotoSource photoSourceMock;
	private TagAutocomplete subject;

	@Before
	public void init() {
		photoSourceMock = new PhotoSourceMock();
		subject = new TagAutocompleteImpl(photoSourceMock);		
	}

    @Test
    public void testAutocomplete(){
        photoSourceMock.setMetadata(
                "42.png",
                "metadata: \"Tag:\\n" +
                "   Sub-Tag: \\n" +
                "      - Tag Value 1\\n" +
                "      - Tag Value 2\"");

        Map<String, Set<String>> suggestMap = subject.getSuggestions();

        Assert.assertEquals(
                "Root/Tag/Sub-Tag [Tag Value 1, Tag Value 2]\n" +
                "Root/Tag [Sub-Tag]\n" +
                "Root [Tag]",
                toString(suggestMap)
        );
    }

    @Test
    public void testEmptyInput(){
        photoSourceMock.setMetadata("42.png","");

        Map<String, Set<String>> suggestMap = subject.getSuggestions();
        Assert.assertEquals("", toString(suggestMap)
        );
    }

    @Test
    public void testDuplicateOutuputEntries() {
         photoSourceMock.setMetadata("42.png", "metadata: \"Tag:\"");
         photoSourceMock.setMetadata("43.png", "metadata: \"Tag:\"");

         Map<String, Set<String>> suggestMap = subject.getSuggestions();
         Assert.assertEquals("Root [Tag]", toString(suggestMap));
	}

    private String toString(Map<String, Set<String>> suggestMap) {
        String result = "";
        for(Map.Entry<String, Set<String>> entry : suggestMap.entrySet())
            result += entry.getKey() + " " + Arrays.toString(entry.getValue().toArray()) + "\n" ;
        return result.trim();
    }
}