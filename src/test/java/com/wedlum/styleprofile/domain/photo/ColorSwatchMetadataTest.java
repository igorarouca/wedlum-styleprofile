package com.wedlum.styleprofile.domain.photo;

import junit.framework.Assert;

import org.junit.Test;

public class ColorSwatchMetadataTest {

	private String photoId = "myPhoto.png";

    @Test
    public void _null(){
        try {
            new ColorSwatchMetadata(photoId, null);
            Assert.fail();
        } catch (IllegalStateException ex){
            Assert.assertEquals("myPhoto.png: invalid metadata: Root tag 'Photo:' not found.", ex.getMessage());
        }
    }

    @Test
    public void empty(){
        try {
            new ColorSwatchMetadata(photoId, "");
            Assert.fail();
        } catch (IllegalStateException ex){
            Assert.assertEquals("myPhoto.png: invalid metadata: Root tag 'Photo:' not found.", ex.getMessage());
        }
    }

    @Test
    public void withoutPhoto(){
        try {
            new ColorSwatchMetadata(photoId, "Custom: one");
            Assert.fail();
        } catch (IllegalStateException ex){
            Assert.assertEquals("myPhoto.png: invalid metadata: Root tag 'Photo:' not found.", ex.getMessage());
        }
    }


    @Test
    public void withoutTags(){
        try {
            new ColorSwatchMetadata(
            		photoId,
                    "Photo:\n" +
                    "   name: 'foobar'");
            Assert.fail();
        } catch (IllegalStateException ex){
            Assert.assertEquals("myPhoto.png: invalid metadata: 'Tags:' not found.", ex.getMessage());
        }
    }
}
