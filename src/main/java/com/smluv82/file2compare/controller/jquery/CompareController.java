package com.smluv82.file2compare.controller.jquery;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smluv82.file2compare.base.BaseController;
import com.smluv82.file2compare.util.FileEncryptUtil;

@Controller
@RequestMapping(value="/jquery/compare")
public class CompareController extends BaseController {
	@Value("${root.path}")
	private String rootPath;

	@RequestMapping(value="/test")
	public String testView(HttpServletRequest request) {
		logger.info("test in````");

//		logger.debug("getRealPath : {}",request.getSession().getServletContext().getRealPath("/"));
		logger.info("rootPath : {}", rootPath);

		if(rootPath != null && !"".equals(rootPath)) {
			List<String> fileList = new ArrayList<String>();

			FileEncryptUtil.getFilePathAndName(rootPath, fileList);

			if(fileList.size() > 0) {
				for(String file : fileList) {
					String hash = FileEncryptUtil.getFileHash(file);

					if(hash != null && !"".equals(hash)) {
						logger.info("test hash : {}", hash);
					}
				}
			}

		}

		return "jquery/compare/test";
	}

	@RequestMapping(value="/main")
	@ResponseBody
	public String mainView() {


		return "구현중";
	}
}
