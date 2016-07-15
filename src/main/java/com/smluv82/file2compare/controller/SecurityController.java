package com.smluv82.file2compare.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smluv82.file2compare.base.BaseController;

@Controller
@RequestMapping("/authenticate")
public class SecurityController extends BaseController {

	@RequestMapping("/login")
	public void login(final HttpSession session) {
		logger.info("Welcome login! : {}", session.getId());
	}
}
