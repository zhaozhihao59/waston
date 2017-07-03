package com.tocersoft.cms.service;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.cms.dto.MessageCondition;
import com.tocersoft.cms.entity.Message;

/**
 * 留言管理接口
 * @author 方泉
 */
public interface IMessageService {
	
	/**
	 * 添加留言
	 * @param item
	 */
	void doSave(Message item);
	
	/**
	 * 修改留言
	 * @param item
	 */
	void doUpdate(Message item);
	
	/**
	 * 回复留言
	 * @param item	留言实体
	 */
	void replyMessage(Message item);
	
	/**
	 * 分页查询留言
	 * @param pageResult
	 * @param condition
	 */
	void listMessageByPage(PageResult<Message> pageResult,MessageCondition condition);
	
	/**
	 * 批量删除留言
	 * @param messageIds
	 */
	void delMessage(String messageIds);
	
	/**
	 * 根据ID查询详细信息
	 * @param id
	 * @return
	 */
	Message getMessageById(String id);
	
	/**
	 * 根据对应的实体ID查询消息属性
	 * @param 	memberId		会员ID
	 * @param 	productId		产品ID
	 * @return
	 */
	Message getMassgaeByMemberProductId(String memberId, String productId);
}
