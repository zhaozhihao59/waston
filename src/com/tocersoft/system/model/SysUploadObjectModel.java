package com.tocersoft.system.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.system.entity.SysUploadObject;

import com.tocersoft.system.dto.SysUploadObjectCondition;

public class SysUploadObjectModel extends BaseModel<SysUploadObject>{

	private SysUploadObject item = new SysUploadObject();

	private SysUploadObjectCondition condition = new SysUploadObjectCondition();

	public SysUploadObjectModel() {
		super();
	}

	public SysUploadObject getItem() {
		return item;
	}

	public void setItem(SysUploadObject item) {
		this.item = item;
	}

	public SysUploadObjectCondition getCondition() {
		return condition;
	}

	public void setCondition(SysUploadObjectCondition condition) {
		this.condition = condition;
	}

}
