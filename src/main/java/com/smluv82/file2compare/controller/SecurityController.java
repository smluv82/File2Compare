package com.smluv82.file2compare.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smluv82.file2compare.base.BaseController;
import com.smluv82.file2compare.domain.UserInfo;

@Controller
@RequestMapping(value="/authenticate")
public class SecurityController extends BaseController {
	@Value("${page.javascript.framework}")
	private String pageFramework;

	@RequestMapping(value="/login")
	public void login(final HttpSession session) {
		logger.info("Welcome fil2compare login! : {}", session.getId());
	}

	@RequestMapping(value="/login/success")
	public String success(final HttpSession session) {
		logger.info("spring security login success");

		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if(authentication == null) {
			logger.error("authentication is null");
			return "reditect:/authenticate/login";
		}else {
			final UserInfo userInfo = (UserInfo) authentication.getDetails();
			session.setAttribute("userInfo", userInfo);

			if(pageFramework == null || "".equals(pageFramework) || "jquery".equalsIgnoreCase(pageFramework)) {
				return "redirect:/jquery/compare/main";
			}else {
				return "redirect:/angularjs/compare/main";
			}
		}
	}

	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if(authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}

		request.getSession().invalidate();

		logger.debug("file2compare logout sucess");
		return "redirect:/authenticate/login?logout";
	}
}
