package com.patient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * 
 * @author Debashis Patra
 *
 */
@Configuration
public class AppCorsConfig implements WebMvcConfigurer {
	/*
	 * CORS configuration currently all origins are accepted need to change while
	 * deploying in PROD environment
	 */
	@Value("${spring.endpoints.app.cors.allowed-origins}")
	private String allowedOrigins;
	@Value("${spring.endpoints.app.cors.max-age}")
	private String maxAge;
	@Value("${spring.endpoints.app.cors.alllow-credentials}")
	private String allowedCredentials;
	@Value("${spring.endpoints.app.cors.allowed-methods}")
	private String allowedMethods;

	public static final String COMMA_SEPERATOR = ",";

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins(getValuesFromString(allowedOrigins)).allowedMethods(getValuesFromString(allowedMethods));
	}

	private String[] getValuesFromString(String value) {
		return Collections.list(new StringTokenizer(value, ",")).stream().map(token -> (String) token)
				.collect(Collectors.toList()).toArray(new String[0]);
	}
}


