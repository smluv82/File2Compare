package com.smluv82.file2compare.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.smluv82.file2compare.constants.File2CompareConstants;
import com.smluv82.file2compare.repository.UserInfoRepository;

public class BaseService {
	protected static Logger logger = LoggerFactory.getLogger(File2CompareConstants.LOG_FILE2COMPARE);

	@Autowired
	protected UserInfoRepository userInfoRepository;
}
