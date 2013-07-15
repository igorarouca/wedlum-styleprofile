package com.wedlum.styleprofile.domain.photo;

import java.util.Map;
import java.util.Set;

public interface TagAutocomplete {

	Map<String, Set<String>> getSuggestions();

}