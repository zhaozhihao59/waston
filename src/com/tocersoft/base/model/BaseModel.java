package com.tocersoft.base.model;

import java.io.Serializable;
import com.tocersoft.base.page.PageResult;

@SuppressWarnings("serial")
public abstract class BaseModel<T> implements Serializable{

	private PageResult<T> pageResult = new PageResult<T>();
	
	private String selIds;	//选中的编号集合

	public BaseModel() {
		super();
	}

	public PageResult<T> getPageResult() {
		return pageResult;
	}

	public void setPageResult(PageResult<T> pageResult) {
		this.pageResult = pageResult;
	}
	
	public String getSelIds() {
		return selIds;
	}

	public void setSelIds(String selIds) {
		this.selIds = selIds;
	}
}
