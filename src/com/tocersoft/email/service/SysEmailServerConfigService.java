package com.tocersoft.email.service;

import com.tocersoft.email.entity.EmailServerConfig;

public interface SysEmailServerConfigService {

	EmailServerConfig getEmailServerConfig();
	
	public void update(EmailServerConfig config);
}
