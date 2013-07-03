package com.wedlum.styleprofile.business.model;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TagAutocompleteTest {

    @Test
    public void testAutocomplete(){
        PhotoSourceMock photoSourceMock = new PhotoSourceMock();
        TagAutocomplete subject = new TagAutocomplete(photoSourceMock);

        photoSourceMock.setMetadata("42.png",
                "Tag:\n" +
                "   Sub-Tag: \n" +
                "      - Tag Value 1\n" +
                "      - Tag Value 2");

        Map<String, List<String>> suggestMap = subject.getSuggestions();
        Assert.assertEquals(
                "Root [Tag]\n" +
                "Root/Tag [Sub-Tag]\n" +
                "Root/Tag/Sub-Tag [Tag Value 1, Tag Value 2]",
                toString(suggestMap)
        );
    }

    private String toString(Map<String,List<String>> suggestMap) {
        String result = "";
        for(Map.Entry<String, List<String>> entry : suggestMap.entrySet())
            result += entry.getKey() + " " + Arrays.toString(entry.getValue().toArray()) + "\n" ;
        return result.trim();
    }
}
