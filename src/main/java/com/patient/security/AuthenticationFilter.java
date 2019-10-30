package com.patient.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	PatientUserDetailsServices myUserService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String principal = request.getHeader("uniqueid");
		String rolesHeader = request.getHeader("role");
		String providerCode = request.getHeader("providerCode");
		String sessionId = "dummySessionId";
		StringBuilder csaRole = new StringBuilder();


		logger.debug("uniqueid value from header: " + request.getHeader("uniqueid"));
		logger.debug("uid value from header: " + request.getHeader("uid"));
		logger.debug("role value from header" + request.getHeader("role"));
		logger.debug("providerCode value from header" + request.getHeader("providerCode"));

		sessionId = getCookies(request, sessionId);

		// Take the second part of cookie value as session id
		if (sessionId != null) {
			String[] ids = sessionId.split("\\*");
			if (ids.length > 1) {
				sessionId = ids[1];
			}
		}

		if (rolesHeader != null && !rolesHeader.equals("")) {
			logger.debug("Header user_roles is present. Value is " + rolesHeader);
			String[] roleHeaderArray = rolesHeader.split("\\|");
			logger.debug("roleHeaderArray is " + Arrays.toString(roleHeaderArray));
			
			for (String roleInfo : roleHeaderArray) {
				logger.debug("Processing roleInfo " + roleInfo);
				String[] roleArray = roleInfo.split(",");
                String roleName = roleArray[0].split("=")[1];
				// This is just for comparing  Open AM Role vs Csa Role
                logger.debug("Processing roleName " + roleName);
                String csaRoleFound=Role.roleKeys.get(roleName);
                if(csaRoleFound==null) {
                	csaRoleFound="";
                } else {
                  logger.debug("CSA Mapping found "+csaRoleFound);
                  csaRole.append(csaRoleFound+":");
                }
			}

		}
		
		if (providerCode == null) {
			providerCode = "DHS";
		}

		if (principal == null) {
			principal = "secureduser";
		}

		if (principal != null && !csaRole.toString().isEmpty()) {
			List<GrantedAuthority> authorities = new ArrayList<>();
			Authentication authentication;
			String commaRoles = csaRole.toString().replace(':', ',');
			if(commaRoles.contains(",")) {
			  commaRoles = commaRoles.substring(0,commaRoles.lastIndexOf(','));
			}
			authorities = org.springframework.security.core.authority.AuthorityUtils
					.commaSeparatedStringToAuthorityList(commaRoles);

			// Create our Authentication and let Spring know about it
			UserDetails userDetails = myUserService.loadUserByUsername(principal, providerCode, sessionId, authorities,commaRoles);// new
			
			authentication = new PreAuthenticatedAuthenticationToken(userDetails, "", authorities);
			authentication.setAuthenticated(true);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		logger.debug("Response before going to doFiler: " + request.getHeader("response"));
		filterChain.doFilter(request, response);
	}

	private String getCookies(HttpServletRequest request, String sessionId) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if ("iPlanetDirectoryPro".equals(c.getName())) {
					sessionId = c.getValue();
				}
			}
		}
		return sessionId;
	}

}
