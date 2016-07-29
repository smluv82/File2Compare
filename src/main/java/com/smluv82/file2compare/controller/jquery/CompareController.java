package com.smluv82.file2compare.controller.jquery;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smluv82.file2compare.base.BaseController;
import com.smluv82.file2compare.domain.Compare;

@Controller
@RequestMapping(value = "/jquery/compare")
public class CompareController extends BaseController {
	// @Value("${root.path}")
	// private String rootPath;

	// @RequestMapping(value="/test")
	// public String testView(HttpServletRequest request) {
	// logger.info("rootPath : {}", rootPath);
	//
	// if(rootPath != null && !"".equals(rootPath)) {
	// List<String> fileList = new ArrayList<String>();
	//
	// FileEncryptUtil.getFilePathAndName(rootPath, fileList);
	//
	// if(fileList.size() > 0) {
	// for(String file : fileList) {
	// String hash = FileEncryptUtil.getFileHash(file);
	//
	// if(hash != null && !"".equals(hash)) {
	// logger.info("test hash : {}", hash);
	// }
	// }
	// }
	// }
	//
	// return "jquery/compare/test";
	// }

	@RequestMapping(value = "/main")
	public String mainView() {
		return "jquery/compare/main";
	}

	@RequestMapping(value = "/main/run")
	public void mainRun(HttpServletRequest request) {
		String filePath = ServletRequestUtils.getStringParameter(request, "filePath", "");
		String fileListTxt = ServletRequestUtils.getStringParameter(request, "fileListTxt", "");
		String encryptAlgorithm = ServletRequestUtils.getStringParameter(request, "encryptAlgorithm", "");

		logger.info("filePath : {}", filePath);
		logger.info("fileListTxt : {}", fileListTxt);
		logger.info("encryptAlgorithm : {}", encryptAlgorithm);

		List<Compare> finalFileList = compareService.compareFileRun(filePath, fileListTxt, encryptAlgorithm);

		for (Compare finalFile : finalFileList) {
			logger.info("finalFile compare object : {}", finalFile.toString());
		}
	}

	@RequestMapping(value = "/makeFile")
	public String makeFile() {
		return "jquery/compare/makeFile";
	}

	@RequestMapping(value = "/makeFile/run")
	@ResponseBody
	public void makeFileRun(HttpServletRequest request) {
		String filePath = ServletRequestUtils.getStringParameter(request, "filePath", "");
		String encryptAlgorithm = ServletRequestUtils.getStringParameter(request, "encryptAlgorithm", "");
		String createFilePath = ServletRequestUtils.getStringParameter(request, "createFilePath", "");

		logger.info("filePath : {}", filePath);
		logger.info("encryptAlgorithm : {}", encryptAlgorithm);

		List<Compare> makeFileList = compareService.makeFileRun(filePath, encryptAlgorithm);

		for (Compare makeFile : makeFileList) {
			logger.info("makeFile compare object : {}", makeFile.toString());
		}

		String fileName = createFilePath;

		try {

			// 파일 객체 생성
			File file = new File(fileName);

			// 파일안에 문자열 쓰기
			StringBuilder stringBuilder = new StringBuilder();

			for(Compare compare : makeFileList) {
				stringBuilder.append(compare.getFileName());
				stringBuilder.append(",");
				stringBuilder.append(compare.getFileHash());
				stringBuilder.append("\r\n");

				FileUtils.writeStringToFile(file, stringBuilder.toString(), "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
