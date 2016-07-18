package com.smluv82.file2compare.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.smluv82.file2compare.constants.File2CompareConstants;
import com.smluv82.file2compare.security.File2CompareAuthenticationProvider;
import com.smluv82.file2compare.util.UsefulUtil;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static Logger logger = LoggerFactory.getLogger(File2CompareConstants.LOG_FILE2COMPARE);

	@Autowired
	private File2CompareAuthenticationProvider file2AuthenticationProvider;

	@Override
	protected void configure(HttpSecurity httpSecurity) {
		try {
			httpSecurity.csrf().disable();

			httpSecurity.authorizeRequests().antMatchers("/bower_components/**").permitAll();

			httpSecurity.authorizeRequests()
					.antMatchers("/authenticate/login", "/authenticate/login/**").anonymous()
					.antMatchers("/jquery/**", "/angularjs/**").hasAuthority("ROLE_ADMIN")
					.and()
					.formLogin()
					.loginPage("/authenticate/login")
					.loginProcessingUrl("/authenticate/login/process")
					.failureUrl("/authenticate/login?fail")
					.usernameParameter("id")
					.passwordParameter("pwd")
					.defaultSuccessUrl("/authenticate/login/success", true)
					.and()
					.logout()
					.logoutSuccessUrl("/authenticate/logout");

			httpSecurity.sessionManagement().invalidSessionUrl("/authenticate/login");

		} catch (Exception e) {
			logger.error(UsefulUtil.getPrintStacTraceString(e));
		}
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(this.file2AuthenticationProvider);
	}

//	@Override
//	protected AuthenticationManager authenticationManager() throws Exception {
//		return new ProviderManager(Arrays.asList((AuthenticationProvider) new File2CompareAuthenticationProvider()));
//	}

}
