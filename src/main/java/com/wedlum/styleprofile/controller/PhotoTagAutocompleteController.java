package com.wedlum.styleprofile.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wedlum.styleprofile.domain.photo.TagAutocomplete;
import com.wedlum.styleprofile.util.web.ParseUtils;

@Controller
@RequestMapping(value = "photo/tagautocomplete")
public class PhotoTagAutocompleteController {

    @Inject private TagAutocomplete tagAutocomplete;
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String autocomplete(HttpServletRequest request) throws FileNotFoundException, IOException {
        return ParseUtils.toJson(tagAutocomplete.getSuggestions());
    }

}
