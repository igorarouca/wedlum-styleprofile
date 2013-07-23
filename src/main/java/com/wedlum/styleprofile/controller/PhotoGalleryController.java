package com.wedlum.styleprofile.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wedlum.styleprofile.domain.photo.PhotoGallery;
import com.wedlum.styleprofile.domain.photo.PhotoSummary;
import com.wedlum.styleprofile.domain.photo.TagAutocomplete;
import com.wedlum.styleprofile.util.web.JsonUtils;

@Controller
@RequestMapping(value = "photoGallery")
public class PhotoGalleryController {

    @Inject private PhotoGallery gallery;
    @Inject private TagAutocomplete tagAutocomplete;

    @RequestMapping(value = "untagged", method = RequestMethod.GET)
    @ResponseBody
    public String untagged(HttpServletRequest request) throws FileNotFoundException, IOException {
        Set<PhotoSummary> result = new LinkedHashSet<PhotoSummary>();
        for(String photo: gallery.untagged())
            result.add(new PhotoSummary(photo));
        return JsonUtils.toJson(result);
    }

    @RequestMapping(value = "autocomplete", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Map<String, Set<String>> autocomplete(HttpServletRequest request) throws FileNotFoundException, IOException {
        return tagAutocomplete.getSuggestions();
    }
}
