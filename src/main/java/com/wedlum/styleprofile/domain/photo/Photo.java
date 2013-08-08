package com.wedlum.styleprofile.domain.photo;

import java.util.List;

import com.wedlum.styleprofile.domain.DomainObject;

public class Photo implements DomainObject {

	private static final long serialVersionUID = 1L;

	private String id;
	private String metadata;

	public Photo() {
	}

	public Photo(String id, String metadata) {
		this.id = id;
		this.metadata = metadata;
	}

	public String getId() {
		return id;
	}

	public String getMetadata() {
		if (this.metadata == null || this.metadata.isEmpty()) {
			return	"Photo:\n" +
					"   Description:\n" +
					"   Photographer:\n" +
					"       Drue Carr\n" +
					"   Tags:\n" +
					"       Colors:\n" +
					"           - code";
		}

		return metadata;
	}

	public List<String> getColors() {
		return new ColorSwatchMetadata(getMetadata()).getValue("Colors");
	}

	public List<String> getFeaturedColors() {
		return new ColorSwatchMetadata(getMetadata()).getValue("FeaturedColor");
	}

	@Override
	public String toString() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
