package com.tocersoft.activity.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.activity.dto.ActivityEnrollCondition;
import com.tocersoft.activity.entity.ActivityEnroll;

@Repository("activityEnrollDaoImpl")
public interface IActivityEnrollDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<ActivityEnroll> listActivityEnrollByPage(RowBounds bounds,@Param("condition") ActivityEnrollCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listActivityEnrollByPageCount(@Param("condition") ActivityEnrollCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 活动报名表
	 */
	ActivityEnroll getActivityEnrollById(@Param("id") String id);

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
	 * /**
	 * 立即报名注册
	 * @author lhb
	 */
	 void insertRegeister(ActivityEnroll item);
	 
	 /**
     * 检验邮箱是否已经报名过
	 * @author LHB
	 */
	 
	 int checkEnrollEmail(@Param("activityId") String activityId,@Param("email") String email);
	 
	 /**
	 *检验手机号是否已经报名过
	 * @author LHB
		 */
		 
	int checkEnrollMobile(@Param("activityId") String activityId,@Param("mobile") String mobile);
		 
	 /**
	  * 批量导出分页查询
	  */
	 List<ActivityEnroll> listActivityEnrollByExport(RowBounds bounds,@Param("condition") ActivityEnrollCondition condition);
	 
	 /**
	  * 批量导出数量
	  */
	 int listActivityEnrollByExportCount(@Param("condition") ActivityEnrollCondition condition);
}

