package com.wedlum.styleprofile.domain.photo;

import com.wedlum.styleprofile.util.web.ParseUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ColorSwatchMetadata {
    private String yaml;

    public ColorSwatchMetadata(String yaml) {
        this.yaml = "" + yaml;
        parseModel();
    }

    @SuppressWarnings("unchecked")
    List<String> getValue(String tag) {
        Map<String, Map<String, Map<String, Object>>> model = parseModel();

        if (model == null)
            throw new IllegalStateException("Invalid metadata");

        Map<String, Map<String, Object>> tags = model.get("Tags");
        if (tags == null)
            throw new IllegalStateException("Invalid metadata");

        List<String> values = (List<String>) tags.get(tag);
        if (values == null)
            return Collections.emptyList();

        return values;
    }

    private Map<String, Map<String, Map<String, Object>>> parseModel() {
        Map<String, Object> modelOrNull = ParseUtils.fromYaml(yaml);
        if (modelOrNull == null)
            throw new IllegalStateException("Invalid Metadata: Root tag 'Photo:' not found.");
        return (Map<String, Map<String, Map<String, Object>>>) modelOrNull.get("Photo");
    }
}
