package com.wedlum.styleprofile.domain.photo;

import java.util.Set;

public interface PhotoGallery {

	Set<String> untagged();

	Photo photo(String id);

	String loadDetail(String id);

	void storeDetail(String id, String metadata);

}