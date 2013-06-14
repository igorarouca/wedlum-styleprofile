package com.wedlum.styleprofile.business.model;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.Set;

public class PhotoGallery {

	private Set<File> untagged = new LinkedHashSet<File>();
	private Set<File> tagged = new LinkedHashSet<File>();

	private PhotoGallery(PhotoSource source) {
		source.register(new Observer<File>()  { public void notify(File photo) {
			untagged.add(photo);
		}});
	}

	public static PhotoGallery on(PhotoSource photoSource) {
		return new PhotoGallery(photoSource);
	}

	public Set<File> untagged() {
		return untagged;
	}

	public void tag(File photo) {
		tagged.add(photo);
		untagged.remove(photo);
	}

	public Set<File> tagged() {
		return tagged;
	}

}
