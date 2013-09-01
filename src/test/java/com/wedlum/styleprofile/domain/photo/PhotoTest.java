package com.wedlum.styleprofile.domain.photo;

import static org.junit.Assert.*;

import org.junit.Test;

public class PhotoTest {

    @Test
    public void invalidMetadata(){
        try {
            new Photo("myPhoto.png", "garbage");
            fail();
        } catch (IllegalStateException ex){
            assertEquals("Invalid: invalid metadata: Root tag 'Photo:' not found.", ex.getMessage());
        }

    }
}
