package com.wedlum.styleprofile.domain.photo;

import junit.framework.Assert;
import org.junit.Test;

public class PhotoTest {

    @Test
    public void invalidMetadata(){
        Photo subject = new Photo("id", "garbage");
        try {
            subject.getColors();
            Assert.fail();
        } catch (IllegalStateException ex){
            Assert.assertEquals("Invalid metadata for: id", ex.getMessage());
        }

    }
}
