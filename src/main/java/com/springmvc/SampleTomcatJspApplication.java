package com.springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

import com.springmvc.util.SpringContextHolder;

@SpringBootApplication
public class SampleTomcatJspApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SampleTomcatJspApplication.class);
	}

	public static void main(String[] args) throws Exception {
		final ApplicationContext applicationContext = SpringApplication.run(SampleTomcatJspApplication.class, args);
		
		// set spring context holder for basic service
		new SpringContextHolder().setApplicationContext(applicationContext);
	}

}