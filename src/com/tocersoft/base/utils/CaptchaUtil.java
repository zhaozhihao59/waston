package com.tocersoft.base.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;


public class CaptchaUtil {
	
	public static final String CAPTCHA_PARAMETER_NAME = "j_captcha";// 验证码输入表单名称
	
	/**
	 * 根据HttpServletRequest校验验证码
	 * 
	 * @param parameterName
	 *         参数名称
	 * 
	 * @return 是否验证通过
	 */
	public static boolean validateCaptchaByRequest(HttpServletRequest request, String parameterName) {
		String captchaID = request.getSession().getId();
		String challengeResponse = StringUtils.upperCase(request.getParameter(parameterName));
		
		if (StringUtils.isEmpty(captchaID) || StringUtils.isEmpty(challengeResponse)) {
			return false;
		}
		String sessionCaptcha= (String) request.getSession().getAttribute(Constant.JAPTCHACODE);
		if (sessionCaptcha.equals(challengeResponse)) {
				return true;
			}
		
		return false;
	}
	
	/**
	 * 根据HttpServletRequest校验验证码,使用默认参数名称
	 * 
	 * @return 是否验证通过
	 */
	public static boolean validateCaptchaByRequest(HttpServletRequest request) {
		return validateCaptchaByRequest(request,CAPTCHA_PARAMETER_NAME);
	}

}