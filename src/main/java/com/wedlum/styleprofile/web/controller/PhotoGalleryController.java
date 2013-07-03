package com.wedlum.styleprofile.web.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.wedlum.styleprofile.business.model.TagAutocomplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wedlum.styleprofile.business.model.PhotoGallery;
import com.wedlum.styleprofile.business.model.PhotoSummary;

@Controller
@RequestMapping(value = "photoGallery")
public class PhotoGalleryController {

    @Autowired private PhotoGallery gallery;
    @Autowired private TagAutocomplete tagAutocomplete;

    @RequestMapping(value = "untagged", method = RequestMethod.GET)
    @ResponseBody
    public Set<PhotoSummary> untagged(HttpServletRequest request) throws FileNotFoundException, IOException {
        Set<PhotoSummary> result = new LinkedHashSet<PhotoSummary>();
        for(String photo: gallery.untagged())
            result.add(new PhotoSummary(photo));
        return result;
    }

    @RequestMapping(value = "autocomplete", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List<String>> autocomplete(HttpServletRequest request) throws FileNotFoundException, IOException {
        return tagAutocomplete.getSuggestions();
    }
}
