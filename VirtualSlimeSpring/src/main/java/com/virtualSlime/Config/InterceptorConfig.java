package com.virtualSlime.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Resource
    GlobalInterceptor globalInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(globalInterceptor)
                .addPathPatterns("/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .allowedMethods("POST")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
