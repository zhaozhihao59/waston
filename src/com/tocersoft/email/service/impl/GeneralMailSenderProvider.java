package com.tocersoft.email.service.impl;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.tocersoft.email.entity.EmailServerConfig;
import com.tocersoft.email.service.MailSenderProvider;
import com.tocersoft.email.service.SysEmailServerConfigService;

/**
 * 
 * 邮箱发送提供类
 * 
 * 该类在126,163,gmail,yahoo账户测试下通过
 * 
 * @author taiqichao
 * 
 */
@Component
public class GeneralMailSenderProvider implements MailSenderProvider {

	Log log = LogFactory.getLog(GeneralMailSenderProvider.class);

	private JavaMailSenderImpl sender;

	@Autowired
	private SysEmailServerConfigService service;

	public GeneralMailSenderProvider() {
		sender = new JavaMailSenderImpl();
	}

	public JavaMailSenderImpl getSender() {
		EmailServerConfig config = service.getEmailServerConfig();
		// 校验邮箱服务器配置
		if (StringUtils.isBlank(config.getHostName())
				|| StringUtils.isBlank(config.getAccount())
				|| StringUtils.isBlank(config.getPassword())) {
			log.error("邮箱服务器配置信息不完整");
			return null;
		}
		sender.setHost(config.getHostName());
		sender.setUsername(config.getAccount());
		sender.setPassword(config.getPassword());
		sender.setDefaultEncoding("UTF-8");

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.debug", "true");
		javaMailProperties.put("mail.smtp.auth", "true");

		// Gmail SSL设置
		if (config.getHostName().indexOf("smtp.gmail.com") >= 0) {
			log.info("gmail邮箱服务,SSL设置");
			javaMailProperties.put("mail.smtp.socketFactory.port", "465");
			javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			javaMailProperties.put("mail.smtp.socketFactory.fallbacks", "true");
		}
		sender.setJavaMailProperties(javaMailProperties);

		return this.sender;
	}

}
