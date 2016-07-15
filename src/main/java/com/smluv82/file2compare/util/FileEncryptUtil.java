package com.smluv82.file2compare.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.smluv82.file2compare.constants.File2CompareConstants;

public class FileEncryptUtil {
	@Value("${encrypt.algorithm}")
	private static String encryptAlgoritm;

	private static Logger logger = LoggerFactory.getLogger(File2CompareConstants.LOG_FILE2COMPARE);

	public static void getFilePathAndName(String path, List<String> fileList) {
		File dir = new File(path);
		File[] files = dir.listFiles();
		try {
			for(int i=0;i<files.length;i++) {
				File file = files[i];

				//파일일 경우
				if(file.isFile()) {
					logger.info("파일 경로 및 이름 : {}", file.getAbsolutePath());
					logger.info("파일 이름 : {}", file.getName());
					fileList.add(file.getAbsolutePath());
				}else if(file.isDirectory()) {
					logger.info("디렉토리 명 : {}", file.getAbsolutePath());
					getFilePathAndName(file.getCanonicalPath(), fileList);
				}
			}
		}catch(IOException e) {
			logger.error(UsefulUtil.getPrintStacTraceString(e));
		}
	}

	public static String getFileHash(String fullPathFileName) {
		String base64 = null;
		byte[] binary = null;
		byte[] hash = null;

		try {
			binary = IOUtils.toByteArray(new FileInputStream(fullPathFileName));

			MessageDigest mDigest;
			if(encryptAlgoritm != null && !"".equals(encryptAlgoritm)) {
				mDigest = MessageDigest.getInstance(encryptAlgoritm);
			}else {
				mDigest = MessageDigest.getInstance("SHA-256");
			}
			mDigest.update(binary);
			hash = mDigest.digest();

			base64 = Base64.encodeBase64String(hash);
		} catch (FileNotFoundException e) {
			logger.error(UsefulUtil.getPrintStacTraceString(e));
			return null;
		} catch (IOException e) {
			logger.error(UsefulUtil.getPrintStacTraceString(e));
			return null;
		} catch (NoSuchAlgorithmException e) {
			logger.error(UsefulUtil.getPrintStacTraceString(e));
			return null;
		}

		return base64;
	}

//	public static void main(String[] args) {
//		String rootPath = "D:/test";
//		List<String> tests = new ArrayList<String>();
//
//		getFilePathAndName(rootPath, tests);
//
//		for(String test : tests) {
//			String hashVal = getFileHash(test);
//			logger.info("파일명 : {}, 해쉬값 : {}", test, hashVal);
//		}
//	}
}
