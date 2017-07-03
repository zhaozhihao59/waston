package com.tocersoft.email.dao;

import org.springframework.stereotype.Repository;

import com.tocersoft.email.entity.EmailServerConfig;
@Repository
public interface SysEmailServerConfigDao {

	EmailServerConfig getEmailServerConfig();

	void add(EmailServerConfig config);

	void update(EmailServerConfig serverConfig);

}
