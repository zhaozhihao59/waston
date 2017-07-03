package com.tocersoft.activity.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.activity.dto.ActivityCondition;
import com.tocersoft.activity.entity.Activity;

@Repository("activityDaoImpl")
public interface IActivityDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<Activity> listActivityByPage(RowBounds bounds,@Param("condition") ActivityCondition condition);

	/**
	 * 查询总数 分页查询  历届
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listActivityByPageCount(@Param("condition") ActivityCondition condition);
	
	/**
	 * 查询区间数据 分页查询  历届
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<Activity> listActivityByPageByOld(RowBounds bounds,@Param("condition") ActivityCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listActivityByPageCountByOld(@Param("condition") ActivityCondition condition);
	

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 活动表
	 */
	Activity getActivityById(@Param("id") String id);

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
	List<Activity> listActivity(@Param("state") Integer state);
	

}

