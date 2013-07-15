package com.wedlum.styleprofile.domain.photo;

import java.io.Serializable;

public class PhotoSummary implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String id;
    private final String name;

    public PhotoSummary(String name) {
        this.id = name;
        this.name = name;
    }

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhotoSummary other = (PhotoSummary) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PhotoSummary [name=" + name + "]";
	}

}
