package com.smluv82.file2compare.service;

import org.springframework.stereotype.Service;

import com.smluv82.file2compare.base.BaseService;
import com.smluv82.file2compare.domain.UserInfo;

@Service
public class SecurityService extends BaseService {
	public UserInfo authenticate(String adminId, String adminPwd) {

		UserInfo userInfo = userInfoRepository.findByUserIdAndUserPass(adminId, adminPwd);

		if(userInfo != null) {
			logger.debug("userInfo object : {}", userInfo.toString());
		}

		return userInfo;
	}
}
