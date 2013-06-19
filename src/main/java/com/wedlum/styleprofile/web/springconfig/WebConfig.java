
package com.wedlum.styleprofile.web.springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.wedlum.styleprofile.business.model.PhotoGallery;
import com.wedlum.styleprofile.business.model.PhotoSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.wedlum.styleprofile.web.controller")
public class WebConfig {

//    @Bean
//	public InternalResourceViewResolver viewResolver() {
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setPrefix("/WEB-INF/view/");
//		resolver.setSuffix(".jsp");
//		return resolver;
//	}

    private PhotoSource photoSource = new PhotoSource();

    @Bean
    public PhotoGallery  photoGallery(){
        return PhotoGallery.on(photoSource);
    }

    @Bean
    public PhotoSource  photoSource(){
        return photoSource;
    }

}
