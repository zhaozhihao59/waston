package com.tocersoft.system.model;

import java.util.ArrayList;
import java.util.List;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.system.entity.SysDict;
import com.tocersoft.system.entity.SysDictItem;

public class SysDictModel extends BaseModel<SysDict> {
	private static final long serialVersionUID = -9124092182467064555L;
	
	/** 数据库字典ID */
	private String id;
	/** 数据库字典 */
	private SysDict item = new SysDict();
	/** 数据库字典明细 */
	private SysDictItem sysDictItem = new SysDictItem();
	/** 数据库字典列表 */
	private List<SysDict> sysDictList = new ArrayList<SysDict>();
	/** 数据库字典明细项列表 */
	private List<SysDictItem> sysDictItemList = new ArrayList<SysDictItem>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SysDict getItem() {
		return item;
	}

	public void setItem(SysDict item) {
		this.item = item;
	}

	public SysDictItem getSysDictItem() {
		return sysDictItem;
	}

	public void setSysDictItem(SysDictItem sysDictItem) {
		this.sysDictItem = sysDictItem;
	}

	public List<SysDict> getSysDictList() {
		return sysDictList;
	}

	public void setSysDictList(List<SysDict> sysDictList) {
		this.sysDictList = sysDictList;
	}

	public List<SysDictItem> getSysDictItemList() {
		return sysDictItemList;
	}

	public void setSysDictItemList(List<SysDictItem> sysDictItemList) {
		this.sysDictItemList = sysDictItemList;
	}
	
}
