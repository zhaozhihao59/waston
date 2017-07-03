package com.tocersoft.email.model;

import com.tocersoft.email.entity.EmailServerConfig;
import com.tocersoft.email.entity.SysEmailTemplate;

public class AdminEmailModel {

	private EmailServerConfig serverConfig;
	
	private SysEmailTemplate emailTemplate;

	private String id;
	
	public EmailServerConfig getServerConfig() {
		return serverConfig;
	}

	public void setServerConfig(EmailServerConfig serverConfig) {
		this.serverConfig = serverConfig;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SysEmailTemplate getEmailTemplate() {
		return emailTemplate;
	}

	public void setEmailTemplate(SysEmailTemplate emailTemplate) {
		this.emailTemplate = emailTemplate;
	}
}
