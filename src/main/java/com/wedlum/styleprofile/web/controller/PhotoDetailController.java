package com.wedlum.styleprofile.web.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wedlum.styleprofile.business.model.PhotoDetail;
import com.wedlum.styleprofile.business.model.PhotoGallery;

@Controller
@RequestMapping(value = "photoDetail")
public class PhotoDetailController {

    @Autowired
    private PhotoGallery gallery;

    @RequestMapping(value="{id:.+}", method = RequestMethod.GET)
    public PhotoDetail untagged(@PathVariable String id) throws FileNotFoundException, IOException {
    	return gallery.photoDetail(id);
    }

}
