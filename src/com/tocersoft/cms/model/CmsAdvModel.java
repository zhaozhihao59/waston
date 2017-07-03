package com.tocersoft.cms.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.cms.dto.CmsAdvCondition;
import com.tocersoft.cms.entity.CmsAdv;

public class CmsAdvModel extends BaseModel<CmsAdv> {

	private static final long serialVersionUID = 4346507314203660761L;
	
	/**
	 * 广告位实体
	 * 
	 * @return
	 */
	private CmsAdv item = new CmsAdv();

	/**
	 * 广告位条件类
	 * 
	 * @return
	 */
	private CmsAdvCondition condition = new CmsAdvCondition();
	
	/**
	 * 页码
	 * @return
	 */
	private int page;

	public CmsAdvCondition getCondition() {
		return condition;
	}

	public void setCondition(CmsAdvCondition condition) {
		this.condition = condition;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public CmsAdv getItem() {
		return item;
	}

	public void setItem(CmsAdv item) {
		this.item = item;
	}

}
