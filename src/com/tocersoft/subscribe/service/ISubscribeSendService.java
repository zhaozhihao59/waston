package com.tocersoft.subscribe.service;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.subscribe.dto.SubscribeSendCondition;
import com.tocersoft.subscribe.entity.SubscribeSend;

public interface ISubscribeSendService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listSubscribeSendByPage(PageResult<SubscribeSend> pageResult,SubscribeSendCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 邮件订阅发送表
	 */
	SubscribeSend getSubscribeSendById(String id);

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
	
	/**
	 * 发送邮件
	 */
	void sendEmail(String[] subscribe,SubscribeSend subscribeSend);

}

