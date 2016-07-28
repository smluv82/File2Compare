package com.smluv82.file2compare.controller.jquery;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smluv82.file2compare.base.BaseController;

@Controller
@RequestMapping(value="/jquery/compare")
public class CompareController extends BaseController {
//	@Value("${root.path}")
//	private String rootPath;

//	@RequestMapping(value="/test")
//	public String testView(HttpServletRequest request) {
//		logger.info("rootPath : {}", rootPath);
//
//		if(rootPath != null && !"".equals(rootPath)) {
//			List<String> fileList = new ArrayList<String>();
//
//			FileEncryptUtil.getFilePathAndName(rootPath, fileList);
//
//			if(fileList.size() > 0) {
//				for(String file : fileList) {
//					String hash = FileEncryptUtil.getFileHash(file);
//
//					if(hash != null && !"".equals(hash)) {
//						logger.info("test hash : {}", hash);
//					}
//				}
//			}
//		}
//
//		return "jquery/compare/test";
//	}

	@RequestMapping(value="/main")
	public String mainView() {
		return "jquery/compare/main";
	}

	@RequestMapping(value="/main/run")
	public void mainRun(HttpServletRequest request) {
		String filePath = ServletRequestUtils.getStringParameter(request, "filePath", "");
		String fileListTxt = ServletRequestUtils.getStringParameter(request, "fileListTxt", "");
		String encryptAlgorithm = ServletRequestUtils.getStringParameter(request, "encryptAlgorithm", "");

		logger.info("filePath : {}", filePath);
		logger.info("fileListTxt : {}", fileListTxt);
		logger.info("encryptAlgorithm : {}", encryptAlgorithm);

		compareService.compareRun(filePath, fileListTxt, encryptAlgorithm);
	}
}
