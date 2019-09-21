package com.seeyu.normal.utils;

import com.seeyu.lang.utils.FileUtils;
import com.seeyu.lang.utils.IOUtils;
import com.seeyu.mvc.common.web.context.ServletContext;
import com.seeyu.normal.config.GlobalConfig;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * WEB文件下载
 * @author seeyu
 */
@Slf4j
public class WebFileDownLoadUtils {


	/**
	 * 基于文件流下载文件
	 * @param filePath
	 */
	public static void fileDownLoadStreamToResponse(String filePath, String displayFileName) throws Exception{
		fileDownLoadStreamToResponse(GlobalConfig.loadResourceAsStream(filePath), displayFileName);
	}

	/**
	 * 基于文件流下载文件
	 * @param filePath
	 */
	@Deprecated
	public static void fileDownLoadStreamToResponse(String filePath) throws Exception{
		fileDownLoadStreamToResponse(GlobalConfig.loadResourceAsStream(filePath), FileUtils.extractFileName(filePath));
	}

	/**
	 * 基于文件流下载文件
	 * @param in
	 * @param displayFileName
	 */
	public static void fileDownLoadStreamToResponse(InputStream in, String displayFileName) throws Exception {
		OutputStream os = null;
		try {
			HttpServletRequest request = ServletContext.getRequest();
			HttpServletResponse response = ServletContext.getResponse();
			String displayName = new String(URLEncoder.encode(displayFileName, "UTF-8"));
			String fileMimeType = request.getSession().getServletContext().getMimeType(displayFileName);
			fileMimeType = fileMimeType == null ? "APPLICATION/OCTET-STREAM" : fileMimeType;
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;filename=" + displayName);
			os = response.getOutputStream();
			IOUtils.copyBytes(in, os, 10 * 1024 * 1024, false);
		} finally {
			IOUtils.closeStream(in);
			IOUtils.closeStream(os);
		}
	}

	/**
	 * 基于文件流下载文件
	 * @param response
	 * @param request
	 * @param filePath
	 */
	@Deprecated
	public static void fileDownLoadStreamToResponse(HttpServletRequest request, HttpServletResponse response, String filePath) throws Exception{
		fileDownLoadStreamToResponse(request, response, GlobalConfig.loadResourceAsStream(filePath), FileUtils.extractFileName(filePath));
	}

	/**
	 * 基于文件流下载文件
	 * @param response
	 * @param request
	 * @param in
	 * @param displayFileName
	 */
	@Deprecated
	public static void fileDownLoadStreamToResponse(HttpServletRequest request, HttpServletResponse response, InputStream in, String displayFileName) throws Exception {
		fileDownLoadStreamToResponse(in, displayFileName);
	}
}