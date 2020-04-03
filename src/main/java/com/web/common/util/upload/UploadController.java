package com.web.common.util.upload;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.shara.common.initialize.Configuration;


@Controller
public class UploadController {

	/** 日志对象*/
	private Log logger = LogFactory.getLog(this.getClass());

	/** 允许上传的扩展名*/
	private static final String [] extensionPermit = {"doc", "docx","jpg","png"};

	@RequestMapping(value = "common/upload", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> fileUpload(@RequestParam("file") CommonsMultipartFile file, 
			HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.info("UploadController#fileUpload() start");

		//清除上次上传进度信息
		String saveDirectoryPath =Configuration.getReourcesV("upload"); //Constants.uploadServerPath;
		File saveDirectory = new File(saveDirectoryPath);
		logger.debug("Project real path [" + saveDirectory.getAbsolutePath() + "]");
		// 自定义文件名
		String createFilname = String.valueOf(System.currentTimeMillis());
		String name = new String();
		String common = new String();
		// 判断文件是否存在
		if (!file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			String fileExtension = FilenameUtils.getExtension(fileName);
			name = createFilname;
			common = fileName;
			createFilname = "/"+ createFilname + "."+fileExtension;
			if(!ArrayUtils.contains(extensionPermit, fileExtension)) {
				throw new NoSupportExtensionException("No Support extension.");
			}
			file.transferTo(new File(saveDirectory, name+"."+fileExtension));
		}

		logger.info("UploadController#fileUpload() end");
		Map<String, Object> map = State.OK.toMap();
		map.put("name", name);
		map.put("common", common);
		map.put("url", createFilname);
		return map;
	}

}
