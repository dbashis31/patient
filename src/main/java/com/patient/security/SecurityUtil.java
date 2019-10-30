package com.patient.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.patient.security.data.ApiUser;


public abstract class SecurityUtil {
	public static final String ROLE = "role";
	
	private SecurityUtil () {
		
	}

	public static Authentication getCurrentAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public static ApiUser getAuthenticatedUser() {
		return (ApiUser) getCurrentAuthentication().getPrincipal();
	}

	public static String getAuthenticatedUserName() {
		ApiUser mu = (ApiUser) getCurrentAuthentication().getPrincipal();
		return mu.getUsername();
	}

	

	public static boolean hasRole(String roleName) {
		SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleName);
		return getCurrentAuthentication().getAuthorities().contains(grantedAuthority);
	}

	public static String getUserRoles(HttpServletRequest request) {
		return request.getHeader(ROLE);
	}

}
