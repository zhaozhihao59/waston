package com.tocersoft.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.system.dto.SysDictCondition;
import com.tocersoft.system.entity.SysDict;

@Repository("sysDictDaoImpl")
public interface ISysDictDao {
	
	/**
	 * 数据库字典分页
	 * @param rowBounds
	 * @param condition
	 * @return
	 */
	List<SysDict> listSysDictByPage(@Param("rowBounds")RowBounds rowBounds,@Param("condition")SysDictCondition condition);
	
	/**
	 * 数据库字典数量
	 * @param condition
	 * @return
	 */
	int listSysDictByPageCount(@Param("condition")SysDictCondition condition);
	
	/**
	 * 根据ID查询数据库字典
	 * @param id
	 * @return
	 */
	SysDict getSysDictById(@Param("id")String id);
	
	/**
	 * 获取所有数据库字典
	 * @return
	 */
	List<SysDict> listAll();

	/**
	 * 添加数据库字典项
	 * @param sysDict
	 */
	void addSysDict(SysDict sysDict);
	
	/**
	 * 修改数据名称
	 * @param item
	 */
	void doUpdate(SysDict item);
	
	/**
	 * 批量删除数据库字典项
	 * @param ids
	 */
	void delBatch(@Param("ids")String[] ids);
}