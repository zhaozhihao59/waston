package com.tocersoft.base.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.tocersoft.base.page.Page;
import com.tocersoft.base.page.PageResult;


/**
 * web帮助类
 * 
 * @author tqc
 * 
 */
public class WebUtils {

	/**
	 * 获得session
	 * 
	 * @return
	 */
	public static HttpSession getSession() {
		return getRequest().getSession(true);
	}

	/**
	 * 获得当前请求
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ServletActionContext.getRequest();
		return request;
	}

	/**
	 * 获得绝对路径
	 * 
	 * @param path
	 * @return
	 */
	public static String getRealPath(String path) {
		return getSession().getServletContext().getRealPath(path);
	}


	
	/**
	 * 获得IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr() {
		HttpServletRequest request = getRequest();
		String ip = request.getHeader("X-Real-IP");
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		} else {
			return ip;
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		} else {
			int index = ip.indexOf(',');
			if (index != -1) {
				ip = ip.substring(0, index);
			}
		}
		return ip;
	}
	
	public static String getBasePath() {
		String serverPort = "";
		if (getRequest().getServerPort() != 80) {
			serverPort = ":" + getRequest().getServerPort();
		}
		String serverName = getRequest().getServerName();
		String basePath = getRequest().getScheme() + "://" + serverName + serverPort + getRequest().getContextPath() + "/";
		return basePath;
	}

	/**
	 * 去除html代码
	 * 
	 * @param inputString
	 * @return
	 */
	public static String htmlToText(String htmlStr) {
		if(htmlStr==null){
			return null;
		}
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		java.util.regex.Pattern p_ba;
		java.util.regex.Matcher m_ba;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
			// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
			// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			String patternStr = "\\s+";
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签
			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签
			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签
			p_ba = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
			m_ba = p_ba.matcher(htmlStr);
			htmlStr = m_ba.replaceAll(""); // 过滤空格
			textStr = htmlStr;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(WebUtils.class.getName()).log(Level.SEVERE, null,
					e);
		}
		return textStr;// 返回文本字符串
	}
	/**
	 * 生成JSONObject对象
	 * @param itemList
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject toJsonResultList(List<?> itemList,String[] includeProperties) {
		JSONObject root = new JSONObject();
		JSONArray array = new JSONArray();
		root.put("resultList", array);
		
		for(int i=0;i<itemList.size();i++){
			JSONObject json = new JSONObject();
			Object obj = itemList.get(i);
			for(String includeProperty : includeProperties){
				json.put(includeProperty,ReflectionUtil.invokeGetterMethod(obj, includeProperty));
			}
			array.add(json);
		}
		return root;
	}
	/**
	 * 分页json构建
	 * 
	 * @param page
	 *            分页信息
	 * @param resultList
	 *            结果集json数组
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String toPageJson(Page<?> page, JSONArray resultList) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("page", page.getPage());
			jsonObject.put("totalCount", page.getTotalCount());
			jsonObject.put("totalPages", page.getTotalPages());
			jsonObject.put("resultList", resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
	/**
	 * 分页json构建
	 * 
	 * @param page
	 *            分页信息
	 * @param resultList
	 *            结果集json数组
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject toPageJson(PageResult pageResult,String[] includeProperties) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("pageResult.currentPage", pageResult.getCurrentPage());
			jsonObject.put("pageResult.rows", pageResult.getRows());
			jsonObject.put("pageResult.allPages", pageResult.getAllPages());
			
			JSONArray array = new JSONArray();
			jsonObject.put("resultList", array);
			
			for(int i=0;i<pageResult.getResult().size();i++){
				JSONObject json = new JSONObject();
				Object obj = pageResult.getResult().get(i);
				for(String includeProperty : includeProperties){
					json.put(includeProperty,ReflectionUtil.invokeGetterMethod(obj, includeProperty));
				}
				array.add(json);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	/**
	 * 获取当前访问的url路径，带参数
	 * 
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	public static String getPrevUrl(HttpServletRequest request)
			throws UnsupportedEncodingException {
		StringBuffer prevUrl = request.getRequestURL();
		Map<String, String[]> map = request.getParameterMap();
		Iterator<String> iter = map.keySet().iterator();
		int i = 0;
		while (iter.hasNext()) {
			String key = iter.next();
			String[] value = map.get(key);
			if (i == 0) {
				prevUrl.append("?");
			} else {
				prevUrl.append("&");
			}
			for (int j = 0; j < value.length; j++) {
				prevUrl.append(key).append("=").append(value[j]);
				if (j != value.length - 1) {
					prevUrl.append("&");
				}
			}

			i++;
		}
		//String url = URLEncoder.encode(prevUrl.toString(), "utf-8");
		return prevUrl.toString();
	}
}
