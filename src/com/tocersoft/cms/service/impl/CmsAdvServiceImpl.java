package com.tocersoft.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.base.utils.CommonUtil;
import com.tocersoft.cms.dao.ICmsAdvDao;
import com.tocersoft.cms.dto.CmsAdvCondition;
import com.tocersoft.cms.entity.CmsAdv;
import com.tocersoft.cms.service.ICmsAdvService;

/**
 * 文章管理业务层
 * @author lenovo
 *
 */
@Service
@Transactional(value = "transactionManager")
public class CmsAdvServiceImpl implements ICmsAdvService{
	
	@Resource
	private ICmsAdvDao cmsAdvDao;

	@Override
	public void listAdvByPage(PageResult<CmsAdv> pageResult, CmsAdvCondition condition) {
		
		//进行like检查
		CommonUtil.checkEscapeValue(condition);
		RowBounds row = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		
		//获取结果集
		List<CmsAdv> list = cmsAdvDao.listAdvByPage(row, condition);
		pageResult.setResult(list);
		
		//获取总行数
		int count = cmsAdvDao.listAdvByPageCount(condition);
		pageResult.setRows(count);
		
	}

	@Override
	public CmsAdv getAdvById(String id) {
		return cmsAdvDao.getAdvById(id);
	}
}
