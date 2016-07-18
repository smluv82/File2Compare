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
import org.springframework.stereotype.Component;

import com.smluv82.file2compare.config.PropertiesConfig;
import com.smluv82.file2compare.constants.File2CompareConstants;
import com.smluv82.file2compare.domain.UserInfo;
import com.smluv82.file2compare.repository.UserInfoRepository;
import com.smluv82.file2compare.service.SecurityService;
import com.smluv82.file2compare.util.AES;

@Component
public class File2CompareAuthenticationProvider implements AuthenticationProvider {
	private static Logger logger = LoggerFactory.getLogger(File2CompareConstants.LOG_FILE2COMPARE);

	@Autowired
	private transient SecurityService securityService;

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		final String adminId = (String) authentication.getPrincipal();
		final String adminPwd = (String) authentication.getCredentials();

		//it's don't product logging
		logger.info("ID : {}, PWD : {}", adminId, adminPwd);
		logger.info("passkey : {}", PropertiesConfig.getProperty("passKey"));

		String encAdminPwd = AES.encrypt(PropertiesConfig.getProperty("passKey"), adminPwd);

		logger.debug("encAdminPwd : {}", encAdminPwd);
		logger.info("authenticate start");
		UserInfo userInfo = securityService.authenticate(adminId, encAdminPwd);

		System.out.println("ssmoisdfsdfsdfsdf");

		if(userInfo != null) {
			final List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
			roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

			final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(adminId, adminPwd, roles);
			token.setDetails(userInfo);
			return token;
		}else {
			logger.error("{} user not exist", adminId);
			return null;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
