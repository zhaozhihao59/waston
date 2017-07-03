package com.tocersoft.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface BaseDao<T,C> {
	
	/**
	 * 根据条件查询
	 * @param c
	 * @return
	 */
	List<T> listByCondition(@Param("condition")C c);
	
	/**
	 * 查询单个对象
	 * @param id
	 * @return
	 */
	T get(String id);
	
	/**
	 * 添加
	 * @param t
	 */
	void add(T t);
	
	/**
	 * 修改
	 * @param t
	 */
	void update(T t);
	
	/**
	 * 删除
	 * @param id
	 */
	void delByIds(String [] ids);
}
