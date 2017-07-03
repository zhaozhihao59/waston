package com.tocersoft.email.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tocersoft.email.entity.SysEmailTemplate;

@Repository
public interface SysEmailTemplateDao {

	List<SysEmailTemplate> getTemplates();

	SysEmailTemplate getTemplate(String type);

	SysEmailTemplate getTemplateById(String id);
	
	void updateTemplate(SysEmailTemplate dbTemplate);


}
