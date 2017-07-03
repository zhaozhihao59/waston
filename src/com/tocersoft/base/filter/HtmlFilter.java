package com.tocersoft.base.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;

import com.tocersoft.auth.entity.Right;
import com.tocersoft.auth.entity.User;
import com.tocersoft.auth.service.IRightService;
import com.tocersoft.base.utils.Constant;
import com.tocersoft.base.utils.SpringUtil;


/**
 * 过滤器 - 过滤HTML请求
 * @author      miaoshuai
 * @email       miaoshuai@tocersfot.com
 * @company		www.tocersoft.com
 * @create-time 2014-2-19 上午10:13:31
 * @version     1.0
 */
public class HtmlFilter implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		try {
			// 1.查看用户是否登录
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(user != null){
				// 查看用户是否拥有员工管理权限
				boolean isHave = false;
				if(StringUtils.equals(user.getAccount(),Constant.ADMIN_USER)){
					isHave = true;
				}else{
					IRightService permissionService = (IRightService) SpringUtil.getBean("permissionServiceImpl");
					List<Right> permissions = new ArrayList<Right>();
					permissions = permissionService.getAllEnablePermissionsByUserId(user.getId());
					if(!CollectionUtils.isEmpty(permissions)){
						for (Right right : permissions) {
							if(StringUtils.equals(right.getId(),"63cd83f8518d11e3b53400e04dbb1c03")){
								isHave = true;
								break;
							}
						}
					}
				}
				// 如果存在,则继续访问,否则跳转到主页
				if(isHave){
					filterChain.doFilter(request, response);
				}else{
					response.sendRedirect(request.getContextPath() +"/admin/index.htm");
				}
			}else{
				response.sendRedirect(request.getContextPath() +"/admin/login.htm?error=captcha");
			}
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() +"/admin/login.htm?error=captcha");
		}
	}
	
	public void destroy() {}

}