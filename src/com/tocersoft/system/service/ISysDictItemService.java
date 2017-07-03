package com.tocersoft.system.service;

import java.util.List;

import com.tocersoft.auth.entity.User;
import com.tocersoft.system.entity.SysDictItem;

public interface ISysDictItemService {

	/**
	 * 根据数据库字典ID查询明细
	 * @param dictId
	 * @return
	 */
	List<SysDictItem> listSysDictItemByDictId(String dictId);
	
	/**
	 * 根据ID查询数据库字典明细
	 * @param id
	 * @return
	 */
	SysDictItem getSysDictItemById(String id);
	
	/**
	 * 添加数据库字典
	 * @param item
	 */
	void editSysDictItem(SysDictItem item,User currentOperatorUser);
	
	/**
	 * 根据ID删除数据库字典
	 * @param id
	 */
	void deleteSysDictItemById(String id);
	
	/**
	 * 批量删除数据库字典项
	 * @param ids
	 */
	void batchDelete(String [] ids);
}
