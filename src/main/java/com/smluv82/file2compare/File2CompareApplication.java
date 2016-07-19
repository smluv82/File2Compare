package com.smluv82.file2compare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan("com.smluv82.file2compare")
@PropertySource("f2c-config.properties")
public class File2CompareApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(File2CompareApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(File2CompareApplication.class, args);
	}
}
