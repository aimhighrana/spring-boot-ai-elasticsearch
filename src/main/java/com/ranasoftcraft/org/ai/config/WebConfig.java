package com.ranasoftcraft.org.ai.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("PUT", "GET", "DELETE", "OPTIONS", "PATCH", "POST");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/ui/");

        registry
                .addResourceHandler("/_next/static/**")
                .addResourceLocations("classpath:/static/ui/static/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry ) {
        registry.addRedirectViewController("/ui", "/ui/");
        registry.addViewController("/ui/")
                .setViewName("forward:/ui/index.html");
    }
}
