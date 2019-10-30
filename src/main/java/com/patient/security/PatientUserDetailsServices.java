package com.patient.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.patient.security.data.ApiUser;


@Service("myUserService")
public class PatientUserDetailsServices implements UserDetailsService {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		return new ApiUser(username, null);
	}

	public UserDetails loadUserByUsername(String username, String providerCode, String sessionId,
	        Collection<? extends GrantedAuthority> authorities,String roles) {
        ApiUser user = new ApiUser(username, authorities);
		ApiUser.builder().roles(roles);
		return user;
	}
}