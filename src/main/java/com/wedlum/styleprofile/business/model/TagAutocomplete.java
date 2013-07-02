package com.wedlum.styleprofile.business.model;

import com.wedlum.styleprofile.util.observer.Observer;
import org.yaml.snakeyaml.Yaml;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TagAutocomplete {

    private LinkedHashMap<String,String> storage = new LinkedHashMap<String, String>();

    public TagAutocomplete(final PhotoSource photoSource) {
        photoSource.addObserver(new Observer<String>() {
            public void update(String id) {
                storage.put(id, photoSource.getMetadata(id));
            }
        });
    }

    public Map<String,List<String>> getSuggestions() {
        LinkedHashMap<String, List<String>> result = new LinkedHashMap<String, List<String>>();
        Yaml yaml = new Yaml();
        for(String source : storage.values()){
            Map<String, Object> model = (Map<String, Object>) yaml.load(source);
            traverse("Root", model, result);
        }
        return result;
    }

    private void traverse(String parent, Map<String, Object> model, LinkedHashMap<String, List<String>> result) {
        List<String> children = new ArrayList<String>();
        if (!result.containsKey(parent)) result.put(parent, new ArrayList<String>());
        for(String key : model.keySet()){
            String child = parent + "/" + key;
            if (!result.containsKey(child)) result.put(child, new ArrayList<String>());
            children.add(key);
            Object value = model.get(key);
            if (value instanceof LinkedHashMap){
                traverse(child, (Map<String, Object>) value,result);
            } else if (value instanceof String){
                result.get(child).add((String) value);
            } else if (value instanceof ArrayList){
                ArrayList list = (ArrayList) value;
                for(Object each : list){
                    result.get(child).add((String) each);
                }
            }
            else {
                throw new IllegalStateException("Unexpected value " + value + " of class " + value.getClass() );
            }
        }
        result.get(parent).addAll(children);
    }
}
