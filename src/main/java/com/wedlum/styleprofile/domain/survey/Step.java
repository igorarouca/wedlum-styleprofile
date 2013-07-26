package com.wedlum.styleprofile.domain.survey;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Step implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String type;
	private String[] data;

	public Step(String name, String type, String[] data) {
		super();
		this.name = name;
		this.type = type;
		this.data = data;
	}

	@SuppressWarnings("unchecked")
	public Step(Map<?, ?> map) {
		this(
			map.get("name").toString(),
			map.get("type").toString(),
			((List<String>) map.get("data")).toArray(new String[0])
		);
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String[] getData() {
		return data;
	}

	@Override
	public String toString() {
		return "Step [name=" + name + ", data=" + Arrays.toString(data) + "]";
	}

}
