package com.patient.common;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig   {
	@Value("${swagger.info.title}")
	private String title;
	
	@Value("${swagger.info.description}")
	private String description;
	
	@Value("${swagger.info.version}")
	private String version;
	
	@Value("${swagger.info.contact.name}")
	private String name;
	
	@Value("${swagger.info.contact.url}")
	private String url;
	
	@Value("${swagger.info.contact.email}")
	private String email;
	
	@Value("${swagger.info.termsofservice}")
	private String termsofService;
	
	@Value("${swagger.info.license.name}")
	private String licensceName;
	
	@Value("${swagger.info.license.url}")
	private String licensceUrl;
	
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("gov.mdthink.dhs"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }
	/**
	 * This method returns swagger Api basic information.
	 * @return ApiInfo
	 */
    private ApiInfo metaData() {
        return new ApiInfo(
        	       getTitle(), 
        	       getDescription(), 
        	       "API TOS", 
        	       getTermsofService(), 
        	       new Contact(getName(), getUrl(), getEmail()), 
        	       getLicensceName(), getLicensceUrl(), Collections.emptyList());
        
    }
    
    //Getters and setters
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTermsofService() {
		return termsofService;
	}
	public void setTermsofService(String termsofService) {
		this.termsofService = termsofService;
	}
	public String getLicensceName() {
		return licensceName;
	}
	public void setLicensceName(String licensceName) {
		this.licensceName = licensceName;
	}
	public String getLicensceUrl() {
		return licensceUrl;
	}
	public void setLicensceUrl(String licensceUrl) {
		this.licensceUrl = licensceUrl;
	}
	
}
