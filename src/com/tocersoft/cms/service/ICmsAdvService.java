package com.tocersoft.cms.service;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.cms.dto.CmsAdvCondition;
import com.tocersoft.cms.entity.CmsAdv;

/**
 * 广告位管理接口
 * @author 方泉
 *
 */
public interface ICmsAdvService {
	
	/**
	 * 分页查询广告位
	 * @param pageResult
	 * @param condition
	 */
	void listAdvByPage(PageResult<CmsAdv> pageResult, CmsAdvCondition condition);
	
	/**
	 * 根据ID查询广告位信息
	 * @param id
	 * @return CmsAdv
	 */
	CmsAdv getAdvById(String id);
}
