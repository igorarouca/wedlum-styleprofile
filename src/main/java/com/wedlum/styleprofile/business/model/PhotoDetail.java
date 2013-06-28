package com.wedlum.styleprofile.business.model;

public class PhotoDetail {

	private String id;
	private String metadata;

	public PhotoDetail(String id, String metadata) {
		this.id = id;
		this.metadata = metadata;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	@Override
	public String toString() {
		return id;
	}

}
