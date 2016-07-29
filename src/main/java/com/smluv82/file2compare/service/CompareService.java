package com.smluv82.file2compare.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.smluv82.file2compare.base.BaseService;
import com.smluv82.file2compare.domain.Compare;
import com.smluv82.file2compare.util.FileEncryptUtil;
import com.smluv82.file2compare.util.UsefulUtil;

@Service
public class CompareService extends BaseService {

	@Value("${file.ext}")
	private String fileExt;

	public List<Compare> compareFileRun(String filePath, String fileListTxt, String encryptAlgorithm) {
		List<Compare> originalFileList = getOriginalFileList(fileListTxt);
		List<Compare> targetFileList = getTargetFileList(filePath, encryptAlgorithm);
//		List<Compare> finalFileList = new ArrayList<Compare>();

		if(originalFileList != null && targetFileList == null) {
			for(Compare file : originalFileList) {
				file.setCompareResult("false");
//				finalFileList.add(file);
			}
		}else if(originalFileList != null && targetFileList != null) {
			for(Compare originalFile : originalFileList) {
				for(Compare targetFile : targetFileList) {
					if(originalFile.getFileName().equals(targetFile.getFileName())) {
						if(originalFile.getFileHash().equals(targetFile.getFileHash())) {
							originalFile.setCompareResult("true");
//							finalFileList.add(originalFile);
						}else {
							originalFile.setCompareResult("false");
//							finalFileList.add(originalFile);
						}
						break;
					}
				}
			}
		}

		return originalFileList;
	}

	public List<Compare> makeFileRun(String filePath, String encryptAlgorithm) {
		List<Compare> makeFileList = getTargetFileList(filePath, encryptAlgorithm);

		return makeFileList;
	}

	private List<Compare> getOriginalFileList(String fileListTxt) {
		logger.info("originalFileList start");

		int cnt = 0;
		Compare originalFile = null;
		List<Compare> originalFileList = new ArrayList<Compare>();
		List<String> listData = null;

		try {
			listData = FileUtils.readLines(new File(fileListTxt), "UTF-8");
		} catch (IOException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}

		for(String data : UsefulUtil.saveNullCheckList(listData)) {
			logger.debug("data : {}", data);
			cnt++;
			String[] datas = data.split(",");

			originalFile = new Compare();
			originalFile.setFileNo(cnt);
			originalFile.setFileName(datas[0]);
			originalFile.setFileHash(datas[1]);
			originalFile.setCompareResult("false");

			logger.debug("originalFile compare object : {}", originalFile.toString());

			originalFileList.add(originalFile);
		}

		logger.info("originalFileList size : {}", originalFileList.size());

		return originalFileList;
	}

	private List<Compare> getTargetFileList(String filePath, String encryptAlgorithm) {
		logger.info("targetFileList start");

		int cnt = 0;

		List<Compare> fileCompareList = new ArrayList<Compare>();

		if(filePath != null && !"".equals(filePath)) {
			FileEncryptUtil.getFilePathAndName(filePath, fileExt, fileCompareList);

			if(fileCompareList.size() > 0) {
				for(Compare targetCompare : fileCompareList) {
					cnt++;
					targetCompare.setFileNo(cnt);
					targetCompare.setFileHash(FileEncryptUtil.getFileHash(targetCompare.getFullPathFileName(), encryptAlgorithm));

					logger.debug("target File compare object : {}", targetCompare.toString());

//					targetFileList.add(targetFile);
				}
			}
		}

		logger.debug("fileCompareList size : {}", fileCompareList.size());

		return fileCompareList;
	}
}
