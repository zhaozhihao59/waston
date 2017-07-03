package com.tocersoft.activity.service;

import java.util.List;

import com.tocersoft.activity.dto.ActivityCondition;
import com.tocersoft.activity.entity.Activity;
import com.tocersoft.base.page.PageResult;

public interface IActivityService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listActivityByPage(PageResult<Activity> pageResult,ActivityCondition condition);
	
	/**
	 * 分页查询  历届
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listActivityByPageByOld(PageResult<Activity> pageResult,ActivityCondition condition);
	

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 活动表
	 */
	Activity getActivityById(String id);

	/**
	 * 新增
	 * @param item 活动表
	 */
	void add(Activity item);

	/**
	 * 修改
	 * @param item 活动表
	 */
	void update(Activity item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);
	
	/**
	 * 根据活动时间分页查询
	 * @author lhb
	 */
	List<Activity> listActivity(Integer state);
	

}

