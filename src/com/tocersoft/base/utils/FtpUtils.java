package com.tocersoft.base.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * 
 * FTP上传工具类
 * 
 * @author taiqichao
 * @version
 * @Date Jul 15, 2011
 */
public class FtpUtils {

	private static Log log = LogFactory.getLog(FtpUtils.class);

	/**
	 * 向FTP服务器上传文件
	 * @param homeDir ftp根目录
	 * @param url FTP服务器hostname
	 * @param port FTP服务器端口
	 * @param username FTP登录账号
	 * @param password FTP登录密码
	 * @param subDir FTP服务器保存目录 子目录名称，如果存储在根目录设置为 null
	 * @param filename 上传到FTP服务器上的文件名
	 * @param input 输入流
	 * @return 成功返回true，否则返回false
	 */
	public static boolean uploadFile(String homeDir,String url, int port, String username,
			String password, String subDir, String filename, InputStream input) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		ftp.setControlKeepAliveTimeout(60*2); // set timeout to 2 minutes
		ftp.setControlEncoding("UTF-8");

		try {
			int reply;
			ftp.connect(url, port);
			log.info("Connect the server " + url + ":" + port + " success.");
			ftp.login(username, password);
			log.info("Login in,use username:" + username + ",password:" + password);

			reply = ftp.getReplyCode();
			log.info("Server reply code:" + reply);
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				log.info("Login failed,server reply code:" + reply);
				return success;
			}
			
			/*
			 * 使用二进制模式上传，防止图片等类型的文件数据字节丢失
			 */
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.setFileTransferMode(FTP.BINARY_FILE_TYPE);
			
			log.info("current working  directory:" + homeDir);
			if(StringUtils.isNotBlank(subDir)){
				ftp.makeDirectory(subDir);
				log.info("Make working  directory:" + subDir);
				ftp.changeWorkingDirectory(subDir);
				log.info("Change working  directory:" + subDir);
			}
			
			//服务器环境(win2003,jdk1.5,tomcat6.0 执行这里就不向下执行了。)
			// 使用 pasv mode 解决卡住问题(防火墙原因，使用被动连接模式，具体原因请查看ftp协议相关内容) 
			// edit by zhangqiang at 2011-08-30
			ftp.enterLocalPassiveMode();
			
			ftp.storeFile(filename, input);
			log.info("StoreFile the filename:" + filename);
			log.info("StoreFile success,filename:" + filename + "");
			ftp.logout();
			log.info("Upload file success.");
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
					log.info("Disconnect.");
				} catch (IOException ex) {
					log.error(ex);
				}
			}
			IOUtils.closeQuietly(input);
		}
		return success;
	}
	
	/**
	 * 下载文件
	 * @param ftpFilePath FTP服务器文件路径
	 * @param localFile 本地文件
	 * @return
	 */
	public static boolean downloadFile(String homeDir,String url, int port, String username,
			String password,String ftpFilePath,File localFile){
		boolean success = false;
		FTPClient ftp = new FTPClient();
		ftp.setControlKeepAliveTimeout(60*2); // set timeout to 2 minutes
		ftp.setControlEncoding("UTF-8");
		FileOutputStream fos = null;
		try {
			int reply;
			ftp.connect(url, port);
			log.info("Connect the server " + url + ":" + port + " success.");
			ftp.login(username, password);
			log.info("Login in,use username:" + username + ",password:" + password);

			reply = ftp.getReplyCode();
			log.info("Server reply code:" + reply);
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				log.info("Login failed,server reply code:" + reply);
				return success;
			}
			
			/*
			 * 使用二进制模式上传，防止图片等类型的文件数据字节丢失
			 */
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.setFileTransferMode(FTP.BINARY_FILE_TYPE);
			
			//服务器环境(win2003,jdk1.5,tomcat6.0 执行这里就不向下执行了。)
			// 使用 pasv mode 解决卡住问题(防火墙原因，使用被动连接模式，具体原因请查看ftp协议相关内容) 
			// edit by zhangqiang at 2011-08-30
			ftp.enterLocalPassiveMode();
			
			//通过FTP方式下载远程文件
			fos = new FileOutputStream(localFile);
			ftp.retrieveFile(ftpFilePath,fos);
			ftp.logout();
			log.info("Download file success.");
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
					log.info("Disconnect.");
				} catch (IOException ex) {
					log.error(ex);
				}
			}
			IOUtils.closeQuietly(fos);
		}
		return success;
	}
	
	
	public static void main(String[] args) {
		
	}

}
