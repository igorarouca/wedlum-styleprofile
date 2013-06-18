package com.wedlum.styleprofile.web.controller;

import com.wedlum.styleprofile.business.model.PhotoGallery;
import com.wedlum.styleprofile.business.model.PhotoSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

@Controller
@RequestMapping(value = "photoGallery")
public class PhotoGalleryController {


    @Autowired
    private PhotoGallery gallery;

    @RequestMapping(value = "untagged", method = RequestMethod.GET)
    @ResponseBody
    public Set<PhotoSummary> untagged(HttpServletRequest request) throws FileNotFoundException, IOException {
        Set<PhotoSummary> result = new LinkedHashSet<PhotoSummary>();
        for(File photo: gallery.untagged())
            result.add(new PhotoSummary(photo.getPath()));
        return result;
    }
}
