package com.wedlum.styleprofile.domain.photo;

import static org.junit.Assert.*;

import org.junit.Test;

public class ColorSwatchMetadataTest {

	private String photoId = "myPhoto.png";

    @Test
    public void _null(){
        try {
            ColorSwatchMetadata.fromYaml(photoId, null);
            fail();
        } catch (IllegalStateException ex){
            assertEquals("myPhoto.png: invalid metadata: Root tag 'Photo:' not found.", ex.getMessage());
        }
    }

    @Test
    public void empty(){
        try {
        	ColorSwatchMetadata.fromYaml(photoId, "");
            fail();
        } catch (IllegalStateException ex){
            assertEquals("myPhoto.png: invalid metadata: Root tag 'Photo:' not found.", ex.getMessage());
        }
    }

    @Test
    public void withoutPhoto(){
        try {
        	ColorSwatchMetadata.fromYaml(photoId, "Custom: one");
            fail();
        } catch (IllegalStateException ex){
            assertEquals("myPhoto.png: invalid metadata: Root tag 'Photo:' not found.", ex.getMessage());
        }
    }

    @Test
    public void withoutTags(){
        try {
        	ColorSwatchMetadata.fromYaml(
        		photoId,
                "Photo:\n" +
                "   Anything:"
            );
            fail();
        } catch (IllegalStateException ex){
            assertEquals("myPhoto.png: invalid metadata: 'Tags:' not found.", ex.getMessage());
        }
    }

    @Test
    public void withTabs() {
    	ColorSwatchMetadata.fromYaml(
    		photoId,
    		"Photo:\n" + 
    		"   Description:\n" + 
    		"   Photographer:\n" + 
    		"       Drue Carr\n" + 
    		"   Tags:\n" + 
    		"       Colors:\n" + 
    		"           - 000M"
    	);
    }

}
