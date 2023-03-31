package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
	public void addResourceHandlers(ResourceHandlerRegistry registry) { 
		//registry.addResourceHandler("/logos/**").addResourceLocations("file:/empleos/img-vacantes/"); // Linux 
		registry.addResourceHandler("/equipos/**").addResourceLocations("file:c:/agencia/img-equipos/"); // Windows
		}

}
