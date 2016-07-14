package com.smluv82.file2compare.service;

import org.springframework.stereotype.Service;

import com.smluv82.file2compare.domain.UserInfo;

@Service
public class SecurityService {
	public UserInfo authenticate(String adminId, String adminPwd) {
		return new UserInfo();
	}
}
