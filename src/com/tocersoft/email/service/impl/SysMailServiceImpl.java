package com.tocersoft.email.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.tocersoft.email.dao.ISysEmailDao;
import com.tocersoft.email.entity.SysEmail;
import com.tocersoft.email.entity.SysEmailTemplate;
import com.tocersoft.email.service.SysEmailTemplateService;
import com.tocersoft.email.service.SysMailService;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class SysMailServiceImpl implements SysMailService {

	Log log = LogFactory.getLog(SysMailServiceImpl.class);

	@Resource(name = "freeMarkerConfigurer")
	FreeMarkerConfigurer freeMarkerConfigurer;

	@Autowired
	SysEmailTemplateService emailTemplateService;
	
	@Autowired
	private ISysEmailDao sysEmailDao;

	public void sendEmail(String templateId, String emailTitle, String emailSendTo, Map<String, Object> data) {
		this.batchSendEmail(templateId, emailTitle, new String[]{emailSendTo}, data);
	}

	/**
	 * 模板类型 email_invite 邀请 title 定义的好友邀请 email 注册邮箱 数组接受 data 例 海风教育平台 ， 和 路径
	 * 用集合 jieshou
	 */
	@Async
	public void batchSendEmail(String templateId, String emailTitle, String[] emailSendTo, Map<String, Object> data) {
		SysEmailTemplate template = emailTemplateService.getEmailTemplateById(templateId);
		if(null==template){
			log.error("该邮件模板不存在..." + templateId);
			return;
		}
		StringTemplateLoader stringLoader = new StringTemplateLoader();
		stringLoader.putTemplate(templateId, template.getContent());
		Configuration cfg = freeMarkerConfigurer.getConfiguration();
		cfg.setTemplateLoader(stringLoader);
		try {
			Template markTemplate = cfg.getTemplate(templateId);
			String content = FreeMarkerTemplateUtils.processTemplateIntoString(markTemplate, data);
			SysEmail email = new SysEmail();
			email.setContent(content);
			email.setCreateBy("system");
			if(emailSendTo!=null){
				StringBuffer sb = new StringBuffer();
				for (String string : emailSendTo) {
					sb.append(string);
					sb.append(";");
				}
				email.setEmail(sb.toString());
			}
			
			email.setFlag(0);
			email.setTitle(emailTitle);
			email.setType(templateId);
			sysEmailDao.add(email);
		} catch (Exception e) {
			log.error("邮件发送失败:" + e);
			e.printStackTrace();
		}
	}
}
