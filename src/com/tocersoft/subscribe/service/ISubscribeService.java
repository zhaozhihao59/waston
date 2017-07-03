package com.tocersoft.subscribe.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.game.dto.GameEnrollCondition;
import com.tocersoft.subscribe.dto.SubscribeCondition;
import com.tocersoft.subscribe.entity.Subscribe;

public interface ISubscribeService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listSubscribeByPage(PageResult<Subscribe> pageResult,SubscribeCondition condition);
	/**
	 * 选择发送人员分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listSubscribeSelectByPage(PageResult<Subscribe> pageResult,SubscribeCondition condition);
	
	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 订阅表
	 */
	Subscribe getSubscribeById(String id);
	/**
	 * 根据邮箱、期刊类型查询
	 * @param id
	 * @return
	 */
	Subscribe getSubscribeItemByemail(String email,String channelid);

	/**
	 * 新增
	 * @param item 订阅表
	 */
	void add(Subscribe item);

	/**
	 * 修改
	 * @param item 订阅表
	 */
	void update(Subscribe item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);
	/**
	 * 根据email和期刊ID查询当前邮箱是否订阅过当前期刊
	 * @param email
	 * @param channelid
	 * @return
	 */
	int getSubscribeByemail(String email,String channelid);
	
	/**
	 * 导出
	 * @author 刘鸿博
	 */
	String doExport(SubscribeCondition condition);

	/**
	 * 取消订阅
	 */
	void cancleSubscribe(String channelID,String email);
	
	/**
	 * 
	 */
}

