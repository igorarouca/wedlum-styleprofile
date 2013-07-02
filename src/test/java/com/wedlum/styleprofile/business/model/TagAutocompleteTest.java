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
                "Leaf:\n" +
                "   SubLeaf: \n" +
                "      - it's a leaf\n" +
                "      - it's another leaf");

        Map<String, List<String>> suggestMap = subject.getSuggestions();
        Assert.assertEquals(
                "Root [Leaf]\n" +
                "Root/Leaf [SubLeaf]\n" +
                "Root/Leaf/SubLeaf [it's a leaf, it's another leaf]",
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
