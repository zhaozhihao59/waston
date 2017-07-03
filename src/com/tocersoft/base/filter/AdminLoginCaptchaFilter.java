package com.tocersoft.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.tocersoft.base.utils.CaptchaUtil;


/**
 * 过滤器 - 后台登录验证码
 * @creator     zhangqiang
 * @create-time Oct 17, 2012   1:06:50 PM
 * @version 0.1
 */
@Component("adminLoginCaptchaFilter")
public class AdminLoginCaptchaFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		if (CaptchaUtil.validateCaptchaByRequest(request)) {
			filterChain.doFilter(request, response);
		} else {
			response.sendRedirect(request.getContextPath() +"/admin/login.htm?error=captcha");
		}
	}
	
	public void destroy() {}

}