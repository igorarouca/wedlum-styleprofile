package com.wedlum.styleprofile.web.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.wedlum.styleprofile.business.model.PhotoDetail;
import com.wedlum.styleprofile.business.model.PhotoGallery;

@Controller
@RequestMapping(value = "photoDetail")
public class PhotoDetailController {

    @Autowired
    private PhotoGallery gallery;

    @RequestMapping(value="{id:.+}", method = RequestMethod.GET)
    @ResponseBody
    public String get(@PathVariable String id) throws FileNotFoundException, IOException {
    	return new PhotoDetail(id, "").toJson();
    }

    @RequestMapping(value="{id:.+}", method = RequestMethod.PUT)
    @ResponseBody
    public void put(@RequestBody String body) throws FileNotFoundException, IOException {
        PhotoDetail detail = PhotoDetail.fromJson(body);

    }

}
