package com.tocersoft.system.service;

import java.util.List;

import com.tocersoft.system.entity.SysDict;

public interface ISysDictService {

	/**
	 * 获取所有数据库字典
	 * @return
	 */
	List<SysDict> listAll();
	
	/**
	 * 根据ID查询数据库字典
	 * @param id
	 * @return
	 */
	SysDict getSysDictById(String id);
	
	/**
	 * 保存数据名称
	 * @param item
	 */
	void doSave(SysDict item);
	
	/**
	 * 修改数据名称
	 * @param item
	 */
	void doUpdate(SysDict item);
	
	/**
	 * 批量删除数据库字典项
	 * @param ids
	 */
	void delBatch(String[] ids);
}
