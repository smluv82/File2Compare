package com.smluv82.file2compare.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.smluv82.file2compare.constants.File2CompareConstants;
import com.smluv82.file2compare.util.UsefulUtil;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static Logger logger = LoggerFactory.getLogger(File2CompareConstants.LOG_FILE2COMPARE);

//	@Autowired
//	private UserDetailsService f2cUserDetailsService;

	@Override
	protected void configure(HttpSecurity httpSecurity) {
		try {
			httpSecurity.csrf().disable();

			httpSecurity.authorizeRequests()
			.antMatchers("/bower_components/**").permitAll();

			httpSecurity.authorizeRequests()
			.antMatchers("/login").anonymous()
			.anyRequest().hasAuthority("ROLE_ADMIN")
			.and()
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/login/process")
			.failureUrl("/login?fail")
			.usernameParameter("username")
			.passwordParameter("password")
			.defaultSuccessUrl("/admin/main", true)
			.and()
			.logout()
			.logoutSuccessUrl("/logout");
		}catch(Exception e) {
			logger.error(UsefulUtil.getPrintStacTraceString(e));
		}
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(new File2CompareAuthenticationProvider());
	}

}
