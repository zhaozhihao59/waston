package com.tocersoft.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.base.utils.CommonUtil;
import com.tocersoft.cms.dao.IMessageDao;
import com.tocersoft.cms.dto.MessageCondition;
import com.tocersoft.cms.entity.Message;
import com.tocersoft.cms.service.IMessageService;

/**
 * 文章管理业务层
 * @author lenovo
 *
 */
@Service
@Transactional(value = "transactionManager")
public class MessageServiceImpl implements IMessageService{
	
	@Resource
	private IMessageDao massageDao;

	/**
	 * 分页查询留言
	 * @param pageResult
	 * @param condition
	 */
	@Override
	public void listMessageByPage(PageResult<Message> pageResult,MessageCondition condition) {
		//进行like检查
		CommonUtil.checkEscapeValue(condition);
		RowBounds row = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		//获取结果集
		List<Message> list = massageDao.getMessageByPage(row, condition);
		pageResult.setResult(list);
		//获取总行数
		int count = massageDao.getMessageByPageCount(condition);
		pageResult.setRows(count);
	}
	
	/**
	 * 添加留言
	 * @param item
	 */
	@Override
	public void doSave(Message item) {
		massageDao.addMessage(item);
	}
	
	/**
	 * 修改留言
	 * @param item
	 */
	@Override
	public void doUpdate(Message item) {
		massageDao.updataMessage(item);
	}
	
	/**
	 * 回复留言
	 * @param messageId	留言ID
	 */
	@Override
	public void replyMessage(Message item){
		massageDao.replyMessage(item);
	}
	
	/**
	 * 批量删除留言
	 * @param messageIds
	 */
	@Override
	public void delMessage(String messageIds) {
		if(StringUtils.isNotBlank(messageIds)){
			String [] selIds = messageIds.split(",");
			for(String id : selIds){
				massageDao.delMessage(id);
			}
			
		}
	}
	
	/**
	 * 根据ID查询详细信息
	 * @param id
	 * @return
	 */
	@Override
	public Message getMessageById(String id) {
		Message item =  massageDao.getMessageById(id);
		return item;
	}
	
	/**
	 * 根据对应的实体ID查询消息属性
	 * @param 	memberId		会员ID
	 * @param 	productId		产品ID
	 * @return
	 */
	@Override
	public Message getMassgaeByMemberProductId(String memberId, String productId){
		List<Message> messages = massageDao.listMessageByMemberProductId(memberId,productId);
		if(null != messages && messages.size() > 0){
			return messages.get(0);
		}else{
			return null;
		}
	}

}
