package com.wedlum.styleprofile.domain.photo;

import junit.framework.Assert;

import org.junit.Test;

public class ColorSwatchMetadataTest {

	private String photoId = "myPhoto.png";

    @Test
    public void _null(){
        try {
            ColorSwatchMetadata.fromYaml(photoId, null);
            Assert.fail();
        } catch (IllegalStateException ex){
            Assert.assertEquals("myPhoto.png: invalid metadata: Root tag 'Photo:' not found.", ex.getMessage());
        }
    }

    @Test
    public void empty(){
        try {
        	ColorSwatchMetadata.fromYaml(photoId, "");
            Assert.fail();
        } catch (IllegalStateException ex){
            Assert.assertEquals("myPhoto.png: invalid metadata: Root tag 'Photo:' not found.", ex.getMessage());
        }
    }

    @Test
    public void withoutPhoto(){
        try {
        	ColorSwatchMetadata.fromYaml(photoId, "Custom: one");
            Assert.fail();
        } catch (IllegalStateException ex){
            Assert.assertEquals("myPhoto.png: invalid metadata: Root tag 'Photo:' not found.", ex.getMessage());
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
            Assert.fail();
        } catch (IllegalStateException ex){
            Assert.assertEquals("myPhoto.png: invalid metadata: 'Tags:' not found.", ex.getMessage());
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
/*            "Photo:\n" + 
            "   \t  Tags:\n" +
            "			Colors:\n" +
            "				- MyFavoriteColor"
    	);
*/
    }

}
