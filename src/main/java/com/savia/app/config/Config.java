package com.savia.app.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.savia.app.constants.PathFileUpload;

@Configuration
public class Config {

    private final Logger logger = LoggerFactory.getLogger(Config.class);

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
        List<String> listDirectory =new ArrayList<>();
        try {
            listDirectory.add(PathFileUpload.PATH_FILE_UPLOAD+"upload\\");
            listDirectory.add(PathFileUpload.PATH_FILE_UPLOAD+"excel\\");
            for (String directoryPath: listDirectory) {
                directory = new File(directoryPath);

                if (!directory.isDirectory()) {
                    directory.mkdirs();
                    directory.setReadable(true, false);
                    directory.setWritable(true, false);
                    directory.setExecutable(true, false);
                    logger.info("Directorio de archivos temporales creado");
                }
            }

        } catch (Exception e) {
            logger.error("Error en creacion de directorio termporal : " + e.getLocalizedMessage());
        } finally {
            directory = null;
            logger.info("Proceso de creacion de directorio temporal terminado");
        }
    }
}
