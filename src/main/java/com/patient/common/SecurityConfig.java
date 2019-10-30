package com.patient.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.patient.security.AuthenticationFilter;
import com.patient.security.PatientUserDetailsServices;



@Configuration
@EnableWebSecurity
@EnableJpaRepositories
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled=true,jsr250Enabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	PatientUserDetailsServices userDetailsService;

	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(this.userDetailsService);
	}
	
//	@Bean
//	EvaluationContextExtension securityExtension() {
//	        return new SecurityEvaluationContextExtension();
//	}

	@Bean
	public AuthenticationFilter authenticationFilterBean() throws Exception {
		return new AuthenticationFilter();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		        // we don't need CSRF because our token is invulnerable
		        .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // don't create session
		        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		        .authorizeRequests()
		        // allow anonymous resource requests
		        .antMatchers(HttpMethod.GET,AUTH_WHITELIST)
		        .permitAll().anyRequest().authenticated();
				// Custom JWT based security filter
				httpSecurity.addFilterBefore(authenticationFilterBean(), BasicAuthenticationFilter.class);
				// disable page caching
				httpSecurity.headers().cacheControl();
		        httpSecurity.cors();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	private static final String[] AUTH_WHITELIST = {
	            // -- swagger ui
	            "/v2/api-docs",
	            "/swagger-resources",
	            "/swagger-resources/**",
	            "/configuration/ui",
	            "/configuration/security",
	            "/swagger-ui.html",
	            "/webjars/**",
	            "/", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js","/**/*.json","/actuator/**","/**/headers/"
	            ,"/**/appDocument/**"
	            ,"/**/iva-referral/**"
                // other public endpoints of your API may be appended to this array
	 };

}