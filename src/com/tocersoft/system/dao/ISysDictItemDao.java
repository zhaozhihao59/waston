package com.tocersoft.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tocersoft.system.entity.SysDictItem;

@Repository("sysDictItemDaoImpl")
public interface ISysDictItemDao {

	/**
	 * 根据数据库字典ID查询明细
	 * @param dictId
	 * @return
	 */
	List<SysDictItem> listSysDictItemByDictId(@Param("dictId")String dictId);
	
	/**
	 * 根据ID查询数据库字典明细
	 * @param id
	 * @return
	 */
	SysDictItem getSysDictItemById(@Param("id")String id);
	
	/**
	 * 添加数据库字典
	 * @param item
	 */
	void addSysDictItem(SysDictItem item);
	
	/**
	 * 修改数据库字典
	 * @param item
	 */
	void updateSysDictItem(SysDictItem item);
	
	/**
	 * 根据ID删除数据库字典
	 * @param id
	 */
	void deleteSysDictItemById(String id);
	
	/**
	 * 批量删除数据库字典项
	 * @param ids
	 */
	void batchDelete(@Param("ids")String [] ids);
}