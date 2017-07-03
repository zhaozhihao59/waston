package com.tocersoft.base.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;

import com.tocersoft.auth.entity.Right;
import com.tocersoft.auth.entity.User;
import com.tocersoft.auth.service.IRightService;
import com.tocersoft.base.utils.Constant;
import com.tocersoft.base.utils.SpringUtil;

/**
 * 将简历脱离程序 拦截简历请求,读取物理路径文件输出到浏览器
 * @author      miaoshuai
 * @email       miaoshuai@tocersfot.com
 * @company		www.tocersoft.com
 * @create-time 2014-2-20 上午11:33:29
 * @version     1.0
 */
public class HtmlServlet extends HttpServlet {
	private static final long serialVersionUID = -5828180766981597305L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IRightService permissionService = (IRightService) SpringUtil.getBean("permissionServiceImpl");
		try {
			response.setContentType("text/html; charset=utf-8");
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(user != null){
				// 查看用户是否拥有员工管理权限
				boolean isHave = false;
				if(StringUtils.equals(user.getAccount(),Constant.ADMIN_USER)){
					isHave = true;
				}else{
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
					String uri = request.getRequestURI();
					String suffix = StringUtils.substring(uri, uri.indexOf("resume/")+6);
					Properties props = PropertiesLoaderUtils.loadAllProperties("appconfig.properties");
					String resumePath = props.getProperty("resume_path");
					String htmlPath = resumePath+suffix;
					outputStream(htmlPath,response);
				}else{
					response.sendRedirect(request.getContextPath() +"/admin/index.htm");
				}
			}else{
				response.sendRedirect(request.getContextPath() +"/admin/login.htm");
			}
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/admin/login.htm");
		}
	}
	
	public void outputStream(String path,HttpServletResponse response) throws IOException{
		FileInputStream is = null;
		ServletOutputStream sos = null;

		try {
			is = new FileInputStream(path);
			response.reset();
			sos = response.getOutputStream();
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

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
