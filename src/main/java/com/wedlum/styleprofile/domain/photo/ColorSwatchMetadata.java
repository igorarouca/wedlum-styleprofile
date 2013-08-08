package com.wedlum.styleprofile.domain.photo;

import com.wedlum.styleprofile.util.web.ParseUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ColorSwatchMetadata {

	private String yaml;

	public ColorSwatchMetadata(String yaml) {
		this.yaml = "" + yaml;
		getTags();
	}

	@SuppressWarnings("unchecked")
	List<String> getValue(String tag) {
		Map<String, Map<String, Object>> tags = getTags();

		List<String> values = (List<String>) tags.get(tag);
		if (values == null)
			return Collections.emptyList();

		return values;
	}

	private Map<String, Map<String, Object>> getTags() {
		Map<String, Map<String, Map<String, Object>>> model = parseModel();

		Map<String, Map<String, Object>> tags = model.get("Tags");
		if (tags == null)
			throw new IllegalStateException("Invalid metadata: 'Tags:' not found.");

		return tags;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Map<String, Map<String, Object>>> parseModel() {
		Map<String, Object> modelOrNull = ParseUtils.fromYaml(yaml);
		if (modelOrNull == null)
			throw new IllegalStateException("Invalid metadata: Root tag 'Photo:' not found.");

		Object photo = modelOrNull.get("Photo");
		if (photo == null)
			throw new IllegalStateException("Invalid metadata: Root tag 'Photo:' not found.");

		return (Map<String, Map<String, Map<String, Object>>>) photo;
	}

	@Override
	public String toString() {
		return yaml;
	}

}
