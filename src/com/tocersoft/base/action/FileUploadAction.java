package com.tocersoft.base.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.tocersoft.base.service.IUploadService;
import com.tocersoft.base.utils.FtpUtils;
import com.tocersoft.cms.entity.CmsArticle;
import com.tocersoft.cms.service.ICmsArticleService;

/**
 * 文件上传action(支持多文件上传)
 * @creator     zhangqiang
 * @create-time Feb 7, 2012   3:50:14 PM
 */
@ParentPackage("admin")
@Namespace("/")
@Controller
public class FileUploadAction extends BaseAction{
	private static final long serialVersionUID = 7543347785637731321L;
	
	private static final Log logger = LogFactory.getLog(FileUploadAction.class); 
	
	private List<File> file;
	private List<String> fileFileName;
	
	/** 富文本编辑器中的图片 */
	private String picPath;
	private File imgFile;
	private String imgFileFileName;
	
	private String directory;
	private  String selIds;
	private String url;
	
	@Value("${attachment_path}")
	private String attachmentPath;
	
	
	@Value("${ftp.server}")
	private String ftpServer;
	@Value("${ftp.port}")
	private String ftpPort;
	@Value("${ftp.username}")
	private String ftpUsername;
	@Value("${ftp.password}")
	private String ftpPassword;
	@Value("${ftp.upload.directory}")
	private String ftpUploadDirectory;
	@Value("${image.server}")
	private String imageServer;	//图片的服务器IP或者是域名
	@Value("${image.port}")
	private String imagePort;
	@Value("${image.baseDir}")
	private String imageBaseDir;
	
	@Resource(name="uploadServiceImpl")
	private IUploadService uploadService;
	@Resource
	private ICmsArticleService cmsArticleService;
	@SuppressWarnings("unchecked")
	@Action(value = "upload")
	public void doUpload() throws IOException{
		if(logger.isDebugEnabled()){
			logger.debug("上传的目录为："+directory);
		}
		JSONArray root = new JSONArray();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		if(null != file && file.size() > 0){
			for(int i=0;i<file.size();i++){
				File f = file.get(i);
				int start = fileFileName.get(i).lastIndexOf(".");
				
				// 新的文件名 = 日期字符串 + ".jpg(图片扩展名)"
				String fileName = df.format(new Date())+fileFileName.get(i).substring(start,fileFileName.get(i).length());
				
				String url = "";
				if(StringUtils.isNotBlank(directory)){
					url = this.doUploadFile(f, fileName, attachmentPath+directory);
				}else{
					url = this.doUploadFile(f, fileName, attachmentPath);
				}
				
				JSONObject json = new JSONObject();
				json.put("path", url);
				json.put("fileName", fileFileName.get(i));
				
				root.add(json);
			}
		}
		ajax(root);
	}
	
	
	@SuppressWarnings("unchecked")
	@Action(value = "uploadImg")
	public void uploadImg() throws IOException{
		if(logger.isDebugEnabled()){
			logger.debug("上传的目录为："+directory);
		}
		
		JSONArray root = new JSONArray();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssS");
		if(null != file && file.size() > 0){
			for(int i=0;i<file.size();i++){
				File f = file.get(i);
				int start = fileFileName.get(i).lastIndexOf(".");
				String fileName = df.format(new Date())+fileFileName.get(i).substring(start,fileFileName.get(i).length());
				
				String relPath = attachmentPath + fileName;
				String destPath = this.getRealyPath(relPath);
				
				File tmpFile = new File(destPath);
				this.upload(f, tmpFile);	//先暂存本地
				
				//通过FTP上传到图片服务器上
				/*
				 * 文件上传目录，如果图片数量比较多 可使用 年/月/日/时/产品图片名 这样的目录结构
				 */
				//String subfixPath = "/product/";
				// 构建上传路径	使用:/基础路径/年月日/图片名
				SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMdd");
				String midPath = df2.format(new Date());
				InputStream is = new FileInputStream(tmpFile);
				FtpUtils.uploadFile(ftpUploadDirectory,ftpServer, Integer.parseInt(ftpPort), ftpUsername, ftpPassword, midPath, fileName, is);
				//删除临时文件
				tmpFile.delete();
				
				//获取图片的url地址
				String url = "http://"+imageServer+":"+imagePort+imageBaseDir+midPath+"/"+fileName;
				System.out.println("图片的url:"+url);
				JSONObject json = new JSONObject();
				json.put("fileName", fileFileName.get(i));
				json.put("path", url);
				root.add(json);
			}
		}
		
		ajax(root);
		
	}
	
	@SuppressWarnings("unchecked")
	@Action(value = "ajaxFileUpload" ,results = { @Result(name = "success", type = "json" , params = { "contentType", "text/html" })})
	public String ajaxFileUpload(){
		if(logger.isDebugEnabled()){
			logger.debug("上传的目录为："+directory);
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		if(null != file && file.size() > 0){
			int i = 0;
			File f = file.get(i);
			int start = fileFileName.get(i).lastIndexOf(".");
			
			// 新的文件名 = 日期字符串 + ".jpg(图片扩展名)"
			String fileName = df.format(new Date())+fileFileName.get(i).substring(start,fileFileName.get(i).length());
			
			String url = "";
			if(StringUtils.isNotBlank(directory)){
				url = this.doUploadFile(f, fileName, attachmentPath+directory);
			}else{
				url = this.doUploadFile(f, fileName, attachmentPath);
			}
//			String basePath = WebUtils.getBasePath();
			
			this.setUrl(url);
		}
		return SUCCESS;
	}
	
	
	/**
	 * 上传历史数据文件
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "uploadHistoryImportFile")
	public String uploadHistoryImportFile(){
		try {
			JSONObject json = new JSONObject();
			JSONArray root = new JSONArray();
			json.put("root", root);
			String fileName = fileFileName.get(0);
			String suffixName =  StringUtils.substring(fileName,fileName.lastIndexOf("."),fileName.length());
			JSONObject result = uploadService.uploadHistoryImportFile(file.get(0),suffixName);
			root.add(result);
			json.put("status", Status.success);
			json.put("message", "上传文件成功");
			return ajax(json);
		} catch (Exception e) {
			e.printStackTrace();
			String msg = "上传文件时发生异常："+e.getMessage();
			for(int i=0;i<file.size();i++){
				FileUtils.deleteQuietly(file.get(i));
			}
			return ajax(Status.error,msg);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Action(value = "editorUpload")
	public String editorUpload() throws IOException{
		if(logger.isDebugEnabled()){
			logger.debug("上传的目录为："+directory);
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssS");
		
		int start = imgFileFileName.lastIndexOf(".");
		String fileName = df.format(new Date())+imgFileFileName.substring(start,imgFileFileName.length());
		
		String relPath = attachmentPath + File.separatorChar + fileName;
		String destPath = this.getRealyPath(relPath);
		
		File tmpFile = new File(destPath);
		this.upload(imgFile, tmpFile);	//先暂存本地
		
		//通过FTP上传到图片服务器上
		/*
		 * 文件上传目录，如果图片数量比较多 可使用 年/月/日/时/产品图片名 这样的目录结构
		 */ 
		String subfixPath = "editor";
		InputStream is = new FileInputStream(tmpFile);
		FtpUtils.uploadFile(ftpUploadDirectory,ftpServer, Integer.parseInt(ftpPort), ftpUsername, ftpPassword, subfixPath, fileName, is);
		
		//删除临时文件
		tmpFile.delete();
		
		JSONObject object = new JSONObject();
		object.put("error", 0);
		String url = "http://"+imageServer+":"+imagePort+imageBaseDir+subfixPath+"/"+fileName;
		object.put("url", url);
		
		return ajax(object.toString());
		
	}

	/**
	 * 下载
	 * @return
	 */
	@Action(value = "dowloadFile")
	@InputConfig(resultName = "error")
	public String dowloadFile() throws Exception{
		CmsArticle cmsArticle = cmsArticleService.getArticleById(selIds);
		/* 读取文件 */
		File file = new File(getRealyPath(cmsArticle.getAnnexPath()));
		//File file = new File(task.getAccessoryUrl());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.reset();
		// 设置编码格式
		response.setContentType("application/x-msdownload;charser=utf-8");
		// 要下载的文件大小
		response.setContentLength((int)file.length());
		try {
			/* 如果文件存在 */
			if (file.exists()) {
				File downloadFile = new File("temp/" + cmsArticle.getAnnexFilename());
				FileUtils.copyFile(file, downloadFile);
				String filename = new String(downloadFile.getName().getBytes(), "ISO-8859-1");
				response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
				int fileLength = (int) file.length();
				response.setContentLength(fileLength);
				/* 如果文件长度大于0 */
			//	if (fileLength != 0) {
					
					/* 创建输入流 */
					InputStream inStream = new FileInputStream(file);
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
				//}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Action(value = "dowloadFileEn")
	@InputConfig(resultName = "error")
	public String dowloadFileEn() throws Exception{
		CmsArticle cmsArticle = cmsArticleService.getArticleById(selIds);
		/* 读取文件 */
		File file = new File(getRealyPath(cmsArticle.getEnAnnexPath()));
		//File file = new File(task.getAccessoryUrl());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.reset();
		// 设置编码格式
		response.setContentType("application/x-msdownload;charser=utf-8");
		// 要下载的文件大小
		response.setContentLength((int)file.length());
		try {
			/* 如果文件存在 */
			if (file.exists()) {
				File downloadFile = new File("temp/" + cmsArticle.getEnAnnexFilename());
				FileUtils.copyFile(file, downloadFile);
				String filename = new String(downloadFile.getName().getBytes(), "ISO-8859-1");
				response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
				int fileLength = (int) file.length();
				response.setContentLength(fileLength);
				/* 如果文件长度大于0 */
			//	if (fileLength != 0) {
					
					/* 创建输入流 */
					InputStream inStream = new FileInputStream(file);
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
				//}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 日文版下载
	 * @return
	 * @throws Exception
	 */
	@Action(value = "dowloadFileJp")
	@InputConfig(resultName = "error")
	public String dowloadFileJp() throws Exception{
		CmsArticle cmsArticle = cmsArticleService.getArticleById(selIds);
		/* 读取文件 */
		File file = new File(getRealyPath(cmsArticle.getJpAnnexPath()));
		//File file = new File(task.getAccessoryUrl());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.reset();
		// 设置编码格式
		response.setContentType("application/x-msdownload;charser=utf-8");
		// 要下载的文件大小
		response.setContentLength((int)file.length());
		try {
			/* 如果文件存在 */
			if (file.exists()) {
				File downloadFile = new File("temp/" + cmsArticle.getJpAnnexFilename());
				FileUtils.copyFile(file, downloadFile);
				String filename = new String(downloadFile.getName().getBytes(), "ISO-8859-1");
				response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
				int fileLength = (int) file.length();
				response.setContentLength(fileLength);
				/* 如果文件长度大于0 */
			//	if (fileLength != 0) {
					
					/* 创建输入流 */
					InputStream inStream = new FileInputStream(file);
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
				//}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public String getImgFileFileName() {
		return imgFileFileName;
	}

	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getSelIds() {
		return selIds;
	}


	public void setSelIds(String selIds) {
		this.selIds = selIds;
	}
	
	
}
