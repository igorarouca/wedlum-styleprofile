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
            Assert.assertEquals("Invalid metadata: Root tag 'Photo:' not found.", ex.getMessage());
        }
    }

    @Test
    public void empty(){
        try {
            new ColorSwatchMetadata("");
            Assert.fail();
        } catch (IllegalStateException ex){
            Assert.assertEquals("Invalid metadata: Root tag 'Photo:' not found.", ex.getMessage());
        }
    }

    @Test
    public void withoutPhoto(){
        try {
            new ColorSwatchMetadata("Custom: one");
            Assert.fail();
        } catch (IllegalStateException ex){
            Assert.assertEquals("Invalid metadata: Root tag 'Photo:' not found.", ex.getMessage());
        }
    }


    @Test
    public void withoutTags(){
        try {
            new ColorSwatchMetadata(
                    "Photo:\n" +
                    "   name: 'foobar'");
            Assert.fail();
        } catch (IllegalStateException ex){
            Assert.assertEquals("Invalid metadata: 'Tags:' not found.", ex.getMessage());
        }
    }
}
