package com.smluv82.file2compare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
public class TilesConfig {
//	@Bean
//	public UrlBasedViewResolver urlViewResolver() {
//		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
//		resolver.setViewClass(TilesView.class);
//		return resolver;
//	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[] {"WEB-INF/tiles/f2c-tiles.xml"});
		configurer.setCheckRefresh(true);
		return configurer;
	}

	@Bean
	public TilesViewResolver tilesViewResolver() {
		final TilesViewResolver resolver = new TilesViewResolver();
		resolver.setViewClass(TilesView.class);
		return resolver;
	}
}
