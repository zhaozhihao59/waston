package com.tocersoft.email.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.email.dao.SysEmailServerConfigDao;
import com.tocersoft.email.entity.EmailServerConfig;
import com.tocersoft.email.service.SysEmailServerConfigService;

@Transactional
@Service
public class SysEmailServerConfigServiceImpl implements SysEmailServerConfigService{

	@Autowired
	private SysEmailServerConfigDao configDao;
	
	@Override
	public EmailServerConfig getEmailServerConfig() {
		return configDao.getEmailServerConfig();
	}

	@Override
	public void update(EmailServerConfig config) {
		EmailServerConfig serverConfig = configDao.getEmailServerConfig();
		if(null==serverConfig){
			configDao.add(config);
		}else{
			serverConfig.setAccount(config.getAccount());
			serverConfig.setHostName(config.getHostName());
			serverConfig.setPassword(config.getPassword());
			configDao.update(serverConfig);
		}
	}

}
