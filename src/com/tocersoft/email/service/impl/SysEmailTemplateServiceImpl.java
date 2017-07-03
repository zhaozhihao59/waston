package com.tocersoft.email.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.email.dao.SysEmailTemplateDao;
import com.tocersoft.email.entity.SysEmailTemplate;
import com.tocersoft.email.service.SysEmailTemplateService;

@Service
@Transactional
public class SysEmailTemplateServiceImpl implements SysEmailTemplateService {

	private Log log = LogFactory.getLog(SysEmailTemplateServiceImpl.class);

	@Autowired
	private SysEmailTemplateDao templateDao;

	public List<SysEmailTemplate> getAllEmailTemplates() {
		return templateDao.getTemplates();
	}

	public void updateEmailTemplate(SysEmailTemplate template)throws RuntimeException {
		SysEmailTemplate dbTemplate = templateDao.getTemplateById(template.getId());
		if (null != dbTemplate) {
			dbTemplate.setContent(template.getContent());
			dbTemplate.setUpdateDate(new Date());
			templateDao.updateTemplate(dbTemplate);
		} else {
			log.error("要更新的邮件模板不存在");
			throw new RuntimeException("要更新的邮件模板不存在，type:" + template.getType());
		}
	}

	public SysEmailTemplate getEmailTemplateById(String id) {
		return templateDao.getTemplateById(id);
	}

}
