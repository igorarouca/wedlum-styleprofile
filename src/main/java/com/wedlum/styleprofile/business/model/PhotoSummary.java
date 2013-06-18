package com.wedlum.styleprofile.business.model;

import java.io.Serializable;

public class PhotoSummary implements Serializable {

	private static final long serialVersionUID = 1L;

	private final int id;
    private final String path;

    public PhotoSummary(String path) {
    	this.path = path;
        this.id = path.hashCode();
    }

	public int getId() {
		return id;
	}

	public String getPath() {
		return path;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		return "PhotoSummary [path=" + path + "]";
	}

}
