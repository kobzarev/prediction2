package com.prediction2.config;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/table").setViewName("redirect:/table/");
        registry.addViewController("/table/").setViewName("forward:/table.html");
    }
}
