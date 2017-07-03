package com.tocersoft.base.interceptors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tocersoft.base.utils.Constant;
import com.tocersoft.base.utils.WebUtils;

/**
 * 摘要：基础路径拦截器
 * 
 * @creator 方泉
 * @create-time Dec 26, 2011 10:45:30 AM
 */
@Component("baseInterceptor")
public class BaseInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3316470408259781621L;
	private Logger logger = Logger.getLogger(BaseInterceptor.class);

	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		logger.info("====== 进入基础拦截器 ======");
		
		// 获取基准路径
	   	HttpSession session = WebUtils.getSession();
		String basePath = (String) session.getAttribute(Constant.BASE_PATH);
		if (basePath == null) {
			basePath = WebUtils.getBasePath();
			session.setAttribute(Constant.BASE_PATH, basePath);
		}
		// 获取网站基本配置
		HttpServletRequest request = WebUtils.getRequest();
		@SuppressWarnings("unused")
		String requestType = request.getHeader("X-Requested-With");
//		logger.info("此次请求方式：" + requestType);
//		logger.info("网站基准路径：" + basePath);
		String result = "";
		// 判断会员是否可用
		try {
			result = invocation.invoke();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return "error";
		}
		return result;
	}

	protected void setCookie(String name, String value, Integer maxAge) {
		Cookie cookie = new Cookie(name, value);
		if (maxAge != null) {
			cookie.setMaxAge(maxAge);
		}
		cookie
				.setPath(ServletActionContext.getRequest().getContextPath()
						+ "/");
		ServletActionContext.getResponse().addCookie(cookie);
	}
}
