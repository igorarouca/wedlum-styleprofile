
package com.wedlum.styleprofile.web.springconfig;


import com.wedlum.styleprofile.business.model.PhotoGallery;
import com.wedlum.styleprofile.business.model.PhotoSource;
import com.wedlum.styleprofile.business.model.PhotoSourceUsingFileSystem;
import com.wedlum.styleprofile.business.model.TagAutocomplete;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.wedlum.styleprofile.web.controller")
public class WebConfig {

    private PhotoSource photoSource = new PhotoSourceUsingFileSystem();

    @Bean
    public PhotoGallery  photoGallery(){
        return PhotoGallery.on(photoSource);
    }

    @Bean
    public PhotoSource photoSource(){
        return photoSource;
    }

    @Bean
    public TagAutocomplete tagAutocomplete(){
        return TagAutocomplete.on(photoSource);
    }
}
