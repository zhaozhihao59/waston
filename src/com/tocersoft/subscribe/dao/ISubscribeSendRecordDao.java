package com.tocersoft.subscribe.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.subscribe.dto.SubscribeSendRecordCondition;
import com.tocersoft.subscribe.entity.SubscribeSendRecord;

@Repository("subscribeSendRecordDaoImpl")
public interface ISubscribeSendRecordDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<SubscribeSendRecord> listSubscribeSendRecordByPage(RowBounds bounds,@Param("condition") SubscribeSendRecordCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listSubscribeSendRecordByPageCount(@Param("condition") SubscribeSendRecordCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 邮件订阅发送表
	 */
	SubscribeSendRecord getSubscribeSendRecordById(@Param("id") String id);

	/**
	 * 新增
	 * @param item 邮件订阅发送表
	 */
	void add(SubscribeSendRecord item);

	/**
	 * 修改
	 * @param item 邮件订阅发送表
	 */
	void update(SubscribeSendRecord item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);
	
	/**
	 * 关联查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int subscribeSendListByCount(@Param("condition") SubscribeSendRecordCondition condition);
	
	/**
	 *关联邮件发送记录列表
	 *@author lhb
	 */
	List<SubscribeSendRecord> subscribeSendList(RowBounds bounds,@Param("condition") SubscribeSendRecordCondition condition);

}

