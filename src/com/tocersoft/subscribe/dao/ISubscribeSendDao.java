package com.tocersoft.subscribe.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.subscribe.dto.SubscribeSendCondition;
import com.tocersoft.subscribe.entity.SubscribeSend;

@Repository("subscribeSendDaoImpl")
public interface ISubscribeSendDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<SubscribeSend> listSubscribeSendByPage(RowBounds bounds,@Param("condition") SubscribeSendCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listSubscribeSendByPageCount(@Param("condition") SubscribeSendCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 邮件订阅发送表
	 */
	SubscribeSend getSubscribeSendById(@Param("id") String id);

	/**
	 * 新增
	 * @param item 邮件订阅发送表
	 */
	void add(SubscribeSend item);

	/**
	 * 修改
	 * @param item 邮件订阅发送表
	 */
	void update(SubscribeSend item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);
	

}

