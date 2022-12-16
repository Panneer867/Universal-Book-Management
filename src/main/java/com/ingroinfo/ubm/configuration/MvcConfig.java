package com.ingroinfo.ubm.configuration;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		Path uploadDir = Paths.get("C:/Company");
		String uploadPath = uploadDir.toFile().getAbsolutePath();
		registry.addResourceHandler("/Company/**").addResourceLocations("file:/" + uploadPath + "/");
	}

}
