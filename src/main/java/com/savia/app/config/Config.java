package com.savia.app.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.savia.app.constants.PathFileUpload;

@Configuration
public class Config {

    @Value("${allowed.origin}")
    private String allowedOrigin;

    @Autowired
    ServletContext context;

    @Bean /* Bean para configurar los CORS */
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/v1/**")
                        .allowedOrigins(allowedOrigin)
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .maxAge(3600)
                        .allowedHeaders("*");
            }
        };
    }

    @Bean(name = "multipartResolver") /* Bean para configurar los Multipart que vienen del front */
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        return multipartResolver;
    }

    @Bean(name = "createDirectoryTempForFiles")
    public void createDirectoryTempForFiles() {
        File directory = null;
        try {
            String directoryPath = PathFileUpload.PATH_FILE_UPLOAD;
            directory = new File(directoryPath);
            if (!directory.isDirectory()) {
                directory.mkdirs();
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        } finally {
            directory = null;
        }
    }
}
