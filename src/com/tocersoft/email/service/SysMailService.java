package com.tocersoft.email.service;

import java.util.Map;

/**
 * 邮件服务
 * 
 * @author fangquan
 */
public interface SysMailService {

	/**
	 * 发送邮件
	 * 
	 * @param templateId
	 *            邮件模板ID
	 * @param emailTitle
	 *            邮件标题
	 * @param emailSendTo
	 *            邮件发送至
	 * @param data
	 *            渲染模板数据key-value
	 */
	public void sendEmail(String templateId, String emailTitle, String emailSendTo, Map<String, Object> data);

	/**
	 * 批量发送邮件
	 * 
	 * @param templateId
	 *            邮件模板ID
	 * @param emailTitle
	 *            邮件标题
	 * @param emailSendTo
	 *            邮件发送至
	 * @param data
	 *            渲染模板数据key-value
	 */
	public void batchSendEmail(String templateId, String emailTitle, String[] emailSendTo, Map<String, Object> data);

}
