package com.wedlum.styleprofile.business.model;

import java.io.File;
import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

public class PhotoGalleryTest {

	@Test
	public void newPhotosListedAsUntagged() {
		PhotoSource source = new PhotoSource();
		PhotoGallery subject = PhotoGallery.on(source);

		source.addPhoto(new File("photo.png"));
		Assert.assertEquals("[photo.png]", Arrays.toString(subject.untagged().toArray()));
	}

	@Test
	public void separateTaggedPhotos() {
		PhotoSource source = new PhotoSource();
		PhotoGallery subject = PhotoGallery.on(source);

		File photo = new File("photo.png");
		source.addPhoto(photo);
		subject.tag(photo);
		Assert.assertEquals("[]", Arrays.toString(subject.untagged().toArray()));
		Assert.assertEquals("[photo.png]", Arrays.toString(subject.tagged().toArray()));
	}
}
