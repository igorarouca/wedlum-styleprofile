package com.wedlum.styleprofile.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wedlum.styleprofile.domain.photo.Photo;
import com.wedlum.styleprofile.domain.photo.PhotoGallery;
import com.wedlum.styleprofile.util.web.JsonUtils;

@Controller
@RequestMapping(value = "photoDetail")
public class PhotoDetailController {

    @Inject
    private PhotoGallery gallery;

    @RequestMapping(value="{id:.+}", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public String get(@PathVariable String id) throws FileNotFoundException, IOException {
        return gallery.loadDetail(id);
    }

    @RequestMapping(value="{id:.+}", method = RequestMethod.PUT, consumes="application/json")
    @ResponseBody
    public void put(@RequestBody String body) throws FileNotFoundException, IOException {
        Photo detail = JsonUtils.fromJson(body, Photo.class);
        gallery.storeDetail(detail.getId(), body);
    }

}
