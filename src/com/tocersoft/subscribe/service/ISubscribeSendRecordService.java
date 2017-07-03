package com.tocersoft.subscribe.service;

import java.util.List;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.subscribe.dto.SubscribeSendRecordCondition;
import com.tocersoft.subscribe.entity.SubscribeSendRecord;

public interface ISubscribeSendRecordService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listSubscribeSendRecordByPage(PageResult<SubscribeSendRecord> pageResult,SubscribeSendRecordCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 邮件订阅发送表
	 */
	SubscribeSendRecord getSubscribeSendRecordById(String id);

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
	 *邮件发送记录列表
	 *@author lhb
	 */
	void subscribeSendList(PageResult<SubscribeSendRecord> pageResult,SubscribeSendRecordCondition condition);
	

}

