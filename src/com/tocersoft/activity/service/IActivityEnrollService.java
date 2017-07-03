package com.tocersoft.activity.service;

import com.tocersoft.activity.dto.ActivityEnrollCondition;
import com.tocersoft.activity.entity.ActivityEnroll;
import com.tocersoft.base.page.PageResult;
import com.tocersoft.subscribe.dto.SubscribeCondition;

public interface IActivityEnrollService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listActivityEnrollByPage(PageResult<ActivityEnroll> pageResult,ActivityEnrollCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 活动报名表
	 */
	ActivityEnroll getActivityEnrollById(String id);

	/**
	 * 新增
	 * @param item 活动报名表
	 */
	void add(ActivityEnroll item);

	/**
	 * 修改
	 * @param item 活动报名表
	 */
	void update(ActivityEnroll item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);
	/**
	 * 立即报名注册
	 * @author lhb
	 */
	void insertRegeister(ActivityEnroll item);
	
	/**
	 * 检验邮箱是否已经报名过
	 * @author LHB
	 */
	int checkEnrollEmail(String activityId,String email);
	
	/**
	 * 检验手机号是否已经报名过
	 * @author LHB
	 */
	int checkEnrollMobile(String activityId,String mobile);
	/**
	 * 导出
	 * @author 刘鸿博
	 */
	String doExport(ActivityEnrollCondition condition);

}

