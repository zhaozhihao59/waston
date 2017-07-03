package com.tocersoft.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.cms.dto.MessageCondition;
import com.tocersoft.cms.entity.Message;

@Repository("messageDao")
public interface IMessageDao {
	
	/**
	 * 分页查询
	 * @param bounds
	 * @param map
	 * @return
	 */
	public List<Message> getMessageByPage(RowBounds bounds, @Param("condition") MessageCondition condition);
	
	/**
	 * 获取总行数
	 * @param map
	 * @return
	 */
	public int getMessageByPageCount(@Param("condition")MessageCondition condition);
	
	/**
	 * 根据ID 获取
	 * @param map
	 * @return
	 */
	public Message getMessageById(String id);
	
	/**
	 * 添加信息
	 * @param item 实体
	 */
	public void addMessage(Message item);
	/**
	 * 更新信息
	 * @param item 考勤实体
	 */
	public void updataMessage(Message item);
	
	/**
	 * 回复留言
	 * @param item	留言实体
	 */
	public void replyMessage(Message item);
	
	/**
	 * 删除信息
	 * @param item 考勤实体
	 */
	public void delMessage(String id);
	
	/**
	 * 根据会员与对应的实体ID查询未回复的预约消息
	 * @param 	memberId		会员ID
	 * @param 	productId		产品ID
	 * @return
	 */
	List<Message> listMessageByMemberProductId(@Param("memberId")String memberId, @Param("productId")String productId);
}
