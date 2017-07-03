package com.tocersoft.email.service;

import java.util.List;

import com.tocersoft.email.entity.SysEmailTemplate;

/**
 * 邮件模板服务
 * 
 * @author taiqichao
 * 
 */
public interface SysEmailTemplateService {

	/**
	 * 获取所有邮件模板
	 * 
	 * @return
	 */
	List<SysEmailTemplate> getAllEmailTemplates();

	/**
	 * 获取指定编号模板
	 * 
	 * @param id
	 * @return
	 */
	SysEmailTemplate getEmailTemplateById(String id);

	/**
	 * 更新邮件模板
	 * 
	 * @param template
	 */
	void updateEmailTemplate(SysEmailTemplate template) throws RuntimeException;

}
