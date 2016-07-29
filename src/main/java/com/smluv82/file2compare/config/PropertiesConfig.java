package com.smluv82.file2compare.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesConfig {
	@Value("${file.ext}")
	private String fileExt;

	@Value("${pass.key}")
	private String passKey;

	/** properties의 키,값들을 저장하는 Map */
	private static Map<String, String> propertiesMap = new HashMap<String, String>();

	@PostConstruct
	public void init() {
//		propertiesMap.put("rootPath", rootPath);
//		propertiesMap.put("encryptAlgorithm", encryptAlgorithm);

		propertiesMap.put("passKey", passKey);
		propertiesMap.put("fileExt", fileExt);
	}

	/**
	 * 1. 처리내용 : 저장되어있는 Properties의 정보를 읽어온다.
	 *
	 * @param key property key 값
	 * @return property 값
	 */
	public static String getProperty(final String key) {
		return propertiesMap.get(key);
	}

//
//
//	public static String getRootPath() {
//		return rootPath;
//	}
//
//
//	public static void setRootPath(String rootPath) {
//		PropertiesConfig.rootPath = rootPath;
//	}
//
//
//	public PropertiesConfig() {
//		System.out.println("smoh : " + rootPath);
//	}
}
