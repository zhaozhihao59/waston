package com.tocersoft.base.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.context.SecurityContextHolder;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tocersoft.auth.entity.User;
import com.tocersoft.base.utils.DateUtil;
import com.tocersoft.base.utils.JsonUtil;

public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = -2059402104812661307L;
	
	private static final int BUFFER_SIZE = 2048000;

	private static final String HEADER_ENCODING = "UTF-8";
	private static final boolean HEADER_NO_CACHE = true;
	private static final String HEADER_TEXT_CONTENT_TYPE = "text/plain";
	private static final String HEADER_JSON_CONTENT_TYPE = "text/json";
	
	protected static final String TRUE = "true";
	protected static final String FALSE = "false";
	protected static final String INDEX = "index";
	protected static final String WELCOME = "welcome";
	protected static final String LIST = "list";
	protected static final String ADD = "add";

	public static final String STATUS_PARAMETER_NAME = "status";// 操作状态参数名称
	public static final String MESSAGE_PARAMETER_NAME = "message";// 操作消息参数名称
	
	//当前系统语言
	public enum SystemLanguage{
		Chinese,English,Japanse
	}
	
	// 操作状态（警告、错误、成功）
	public enum Status {
		warn, error, success
	}
	
	public String input() {
		return NONE;
	}
	
	
	// 获取Request
	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	// 获取Response
	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	// 获取ServletContext
	protected ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	// 获取Attribute
	protected Object getAttribute(String name) {
		return ServletActionContext.getRequest().getAttribute(name);
	}

	// 设置Attribute
	protected void setAttribute(String name, Object value) {
		ServletActionContext.getRequest().setAttribute(name, value);
	}

	// 获取Parameter
	protected String getParameter(String name) {
		return ServletActionContext.getRequest().getParameter(name);
	}

	// 获取Parameter数组
	protected String[] getParameterValues(String name) {
		return ServletActionContext.getRequest().getParameterValues(name);
	}

	// 获取Session
	protected Object getSession(String name) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		return session.get(name);
	}
	
	/**
	 * 获取session对象
	 * 
	 * @return
	 */
	protected HttpSession getSession() {
		return getRequest().getSession();
	}
	
	// 设置Session
	protected void setSession(String name, Object value) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		session.put(name, value);
	}

	// 移除Session
	protected void removeSession(String name) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		session.remove(name);
	}
	
	// 获取Cookie
	protected String getCookie(String name) {
		Cookie cookies[] = ServletActionContext.getRequest().getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	
	// 设置Cookie
	protected void setCookie(String name, String value) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath(ServletActionContext.getRequest().getContextPath() + "/");
 		ServletActionContext.getResponse().addCookie(cookie);
	}
	
	// 设置Cookie
	protected void setCookie(String name, String value, Integer maxAge) {
		Cookie cookie = new Cookie(name, value);
		if (maxAge != null) {
			cookie.setMaxAge(maxAge);
		}
		cookie.setPath(ServletActionContext.getRequest().getContextPath() + "/");
 		ServletActionContext.getResponse().addCookie(cookie);
	}

	// 移除Cookie
	protected void removeCookie(String name) {
		Cookie cookie = new Cookie(name, null);
		cookie.setMaxAge(0);
		cookie.setPath(ServletActionContext.getRequest().getContextPath() + "/");
		ServletActionContext.getResponse().addCookie(cookie);
	}

	// 获取真实路径
	protected String getRealPath(String path) {
		return ServletActionContext.getServletContext().getRealPath(path);
	}
	
	// 获取ContextPath
	protected String getContextPath() {
		return ServletActionContext.getRequest().getContextPath();
	}

	// AJAX输出
	protected String ajax(String content, String contentType) {
		try {
			HttpServletResponse response = initResponse(contentType);
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}

	// 根据文本内容输出AJAX
	protected String ajax(String text) {
		return ajax(text, HEADER_TEXT_CONTENT_TYPE);
	}
	
	// 根据操作状态输出AJAX
	protected String ajax(Status status) {
		HttpServletResponse response = initResponse(HEADER_JSON_CONTENT_TYPE);
		try {
			Map<String, String> jsonMap = new HashMap<String, String>();
			jsonMap.put(STATUS_PARAMETER_NAME, status.toString());
			JsonUtil.getMapper().writeValue(response.getWriter(), jsonMap);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		return NONE;
	}
	
	// 根据操作状态、消息内容输出AJAX
	protected String ajax(Status status, String message) {
		HttpServletResponse response = initResponse(HEADER_JSON_CONTENT_TYPE);
		try {
			Map<String, String> jsonMap = new HashMap<String, String>();
			jsonMap.put(STATUS_PARAMETER_NAME, status.toString());
			jsonMap.put(MESSAGE_PARAMETER_NAME, message);
			JsonUtil.getMapper().writeValue(response.getWriter(), jsonMap);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		return NONE;
	}
	
	// 根据Object输出AJAX
	protected String ajax(Object object) {
		HttpServletResponse response = initResponse(HEADER_JSON_CONTENT_TYPE);
		try {
			JsonUtil.getMapper().writeValue(response.getWriter(), object);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		return NONE;
	}
	
	// 根据Object输出AJAX(格式化日期)
	protected String ajax(Object object,String dateFormat) {
		HttpServletResponse response = initResponse(HEADER_JSON_CONTENT_TYPE);
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setDateFormat(new SimpleDateFormat(dateFormat));
			mapper.writeValue(response.getWriter(), object);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
		return NONE;
	}
	
	// 根据boolean状态输出AJAX
	protected String ajax(boolean booleanStatus) {
		HttpServletResponse response = initResponse(HEADER_JSON_CONTENT_TYPE);
		try {
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put(STATUS_PARAMETER_NAME, booleanStatus);
			JsonUtil.getMapper().writeValue(response.getWriter(), jsonMap);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		return NONE;
	}
	
	protected HttpServletResponse initResponse(String contentType) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType(contentType + ";charset=" + HEADER_ENCODING);
		if (HEADER_NO_CACHE) {
			response.setDateHeader("Expires", 1L);
			response.addHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
		}
		return response;
	}
	
	/**
	 * 获取当前用户
	 * @return
	 */
	protected User getCurrentUser(){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
	
	/**
	 * <pre>
	 * 上传文件，此方法将在目标路径下按照月份进行文件夹建立，
	 * 例如传递的目标路径为 upload/photo 则 存储的路径将为：upload/photo/20xx-xx-xx
	 * </pre>
	 * 
	 * @param file
	 *            要上传的文件
	 * @param fileName
	 *            文件名称
	 * @param filePath
	 *            文件存储的目标路径
	 * @return 返回文件存储的相对路径，包含文件名 例如：upload/yourfile/datafiledir/fileName
	 */
	protected String doUploadFile(File file, String fileName, String filePath) {
		String fileType = "";
		int point = fileName.lastIndexOf(".");
		if (point != -1) {
			fileType = fileName.substring(point);
		}
		if (file.length() <= 20971520) {
			String time = String.valueOf(new Date().getTime());
			String saveFileName = time + (int) (Math.random() * 100) + fileType;
			String realPath = this.getRealyPath(filePath);
			Date now = new Date();
			String subDir = DateUtil.format(now, "yyyy-MM-dd");
			realPath = realPath + File.separator + subDir;
			File dir = new File(realPath);
			boolean createSuccess = false;
			if (!dir.exists()) {
				createSuccess = dir.mkdirs();
			} else {
				createSuccess = true;
			}
			if (createSuccess) {
				String destPath = realPath + File.separator + saveFileName;
				File destFile = new File(destPath);
				this.upload(file, destFile);
			} else {
				saveFileName = null;
			}
			return filePath + "/" + subDir + "/" + saveFileName;
		} else {
			return null;
		}
	}
	
	/**
	 * 获取物理路径
	 * 
	 * @param path
	 * @return 返回路径
	 */
	protected String getRealyPath(String path) {
		if (path == null) {
			return "";
		}
		return getServletContext().getRealPath(path).replace("\\", "/");
	}
	
	/**
	 * 上传文件
	 * 
	 * @param src
	 *            源文件
	 * @param dst
	 *            目标文件
	 */
	protected void upload(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				int length = 0;
				while ((length = in.read(buffer)) != -1) {
					out.write(buffer, 0, length);
				}
				out.flush();
			} finally {
				IOUtils.closeQuietly(in);
				IOUtils.closeQuietly(out);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *图片输出流
	 */	
	public void outputStream(String path) throws IOException{
		FileInputStream is = null;
		ServletOutputStream sos = null;

		try {
			is = new FileInputStream(path);
			getResponse().reset();
			sos = getResponse().getOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) > 0) {
				sos.write(buffer, 0, len);
			}
			sos.flush();
		} finally {
			if (null != is) {
				is.close();
			}
			if(null != sos){
				sos.close();
			}
		}
	}
}
