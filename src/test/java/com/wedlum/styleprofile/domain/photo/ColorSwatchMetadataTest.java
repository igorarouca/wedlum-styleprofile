package com.wedlum.styleprofile.domain.photo;

import junit.framework.Assert;
import org.junit.Test;

public class ColorSwatchMetadataTest {

    @Test
    public void _null(){
        try {
            new ColorSwatchMetadata(null);
            Assert.fail();
        } catch (IllegalStateException ex){
            Assert.assertEquals("Invalid Metadata: Root tag 'Photo:' not found.", ex.getMessage());
        }
    }

    @Test
    public void empty(){
        try {
            new ColorSwatchMetadata("");
            Assert.fail();
        } catch (IllegalStateException ex){
            Assert.assertEquals("Invalid Metadata: Root tag 'Photo:' not found.", ex.getMessage());
        }
    }
}
