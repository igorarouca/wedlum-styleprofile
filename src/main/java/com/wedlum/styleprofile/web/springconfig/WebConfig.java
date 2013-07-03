
package com.wedlum.styleprofile.web.springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.wedlum.styleprofile.business.model.PhotoGallery;
import com.wedlum.styleprofile.business.model.PhotoSource;
import com.wedlum.styleprofile.business.model.PhotoSourceUsingFileSystem;

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

}
