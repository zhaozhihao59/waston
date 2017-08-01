package com.tocersoft.base.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.tocersoft.auth.service.IRightService;
import com.tocersoft.base.action.BaseAction;

@ParentPackage("admin")
@Namespace("/download")
@Controller
public class DownloadAction extends BaseAction {
	private static final long serialVersionUID = 3351785033210042535L;

	// 地址
	private String filePath;
	// 文件名
	private String fileName;
	// 下载后是否删除
	private Boolean needToDelete;
	@Value("${attachment_path}")
	private String attachmentPath;	
	@Resource
	private IRightService permissionService;

	@Action(value = "downloadWebFileByPath")
	public String downloadWebFileByPath() {
		// 获得地址
		String realPath = this.getRealPath(filePath);
		try {
			String name = fileName;
			// 添加下载文件的头信息。此信息在下载时会在下载面板上显示，比如：
			// 迅雷下载显示的文件名称，就是此处filiname
			name = new String(name.getBytes(), "ISO-8859-1"); // 各浏览器基本都支持ISO编码
			
			// path是指欲下载的文件的路径。
            File file = new File(realPath);
            // 取得文件的后缀名。
//           String ext = name.substring(name.lastIndexOf(".") + 1).toUpperCase();
            // 以流的形式下载文件。
            this.writeToClient(name, file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return NONE;
	}
	
	@Action(value = "downloadLocalFileByPath")
	@Validations(requiredStrings = { 
			@RequiredStringValidator(fieldName = "filePath", message = "文件路径不能为空"),
			@RequiredStringValidator(fieldName = "fileName", message = "文件名称不能为空")
			}
	)
	@InputConfig(resultName = "error")
	public String downloadLocalFileByPath() {
		// 获得地址
		try {
			// 添加下载文件的头信息。此信息在下载时会在下载面板上显示，比如：
			// 迅雷下载显示的文件名称，就是此处filiname
			fileName = URLDecoder.decode(fileName, "UTF-8");; // 前台两次转码
			fileName = new String(fileName.getBytes(), "ISO-8859-1"); // 各浏览器基本都支持ISO编码
			filePath = URLDecoder.decode(filePath,"UTF-8");
			// path是指欲下载的文件的路径。
            File file = new File(filePath);
            // 取得文件的后缀名。
//           String ext = name.substring(name.lastIndexOf(".") + 1).toUpperCase();
            // 以流的形式下载文件。
            this.writeToClient(fileName, file);
            
            //判断是否需要删除
            if(null != needToDelete && needToDelete){
            	//删除文件
        		FileUtils.deleteQuietly(file);
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 输出到客户端
	 * @param fileName
	 * @param localFile
	 */
	private void writeToClient(String fileName, File localFile) {
		//输出到客户端
		HttpServletResponse response = ServletActionContext.getResponse();
		response.reset();
		response.setContentType("application/x-msdownload");
		response.setCharacterEncoding("UTF-8");
		try {
			response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			int fileLength = (int) localFile.length();
			response.setContentLength(fileLength);
			/* 创建输入流 */
			InputStream inStream = new FileInputStream(localFile);
			byte[] buf = new byte[4096];
			/* 创建输出流 */
			ServletOutputStream servletOS = response.getOutputStream();
			int readLength;
			while (((readLength = inStream.read(buf)) != -1)) {
				servletOS.write(buf, 0, readLength);
			}
			
			inStream.close();
			servletOS.flush();
			servletOS.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	public IRightService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(IRightService permissionService) {
		this.permissionService = permissionService;
	}
	
	
	
}
