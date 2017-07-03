package com.tocersoft.activity.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.net.nntp.Article;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.activity.dao.IActivityDao;
import com.tocersoft.activity.dto.ActivityCondition;
import com.tocersoft.activity.entity.Activity;
import com.tocersoft.activity.service.IActivityService;
import com.tocersoft.base.page.PageResult;

@Service("activityServiceImpl")
@Transactional(value = "transactionManager")
public class ActivityServiceImpl implements IActivityService{

	@Resource(name = "activityDaoImpl")
	private IActivityDao activityDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listActivityByPage(PageResult<Activity> pageResult,ActivityCondition condition){
		int rows = activityDao.listActivityByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<Activity> list = activityDao.listActivityByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 活动表
	 */
	public Activity getActivityById(String id){
		return activityDao.getActivityById(id);
	}

	/**
	 * 新增
	 * @param item 活动表
	 */
	public void add(Activity item){
		activityDao.add(item);
	}

	/**
	 * 修改
	 * @param item 活动表
	 */
	public void update(Activity item){
		activityDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		activityDao.delByIds(ids);
	}
	/**
	 * 根据活动时间分页查询H
	 * @author lhb
	 */
	@Override
	public List<Activity> listActivity(Integer state) {
		List<Activity> list = activityDao.listActivity(state);
		return list;
	}

	@Override
	public void listActivityByPageByOld(PageResult<Activity> pageResult,
			ActivityCondition condition) {
		int rows = activityDao.listActivityByPageCountByOld(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<Activity> list = activityDao.listActivityByPageByOld(rowBounds,condition);
		pageResult.setResult(list);
		
	}
	
	



}

