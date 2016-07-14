package com.smluv82.file2compare.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.smluv82.file2compare.constants.File2CompareConstants;
import com.smluv82.file2compare.service.SecurityService;

public class File2CompareAuthenticationProvider implements AuthenticationProvider {
	private static Logger logger = LoggerFactory.getLogger(File2CompareConstants.LOG_FILE2COMPARE);

	@Autowired
	private transient SecurityService securityService;

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		final String adminId = (String) authentication.getPrincipal();
		final String adminPwd = (String) authentication.getCredentials();

		//it's don't product logging
		logger.info("ID : {}, PWD : {}", adminId, adminPwd);



		logger.info("authentication start");



		securityService.authenticate(adminId, adminPwd);

		final List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
//		return false;
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
