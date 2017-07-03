package com.tocersoft.subscribe.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.subscribe.dto.SubscribeCondition;
import com.tocersoft.subscribe.entity.Subscribe;

@Repository("subscribeDaoImpl")
public interface ISubscribeDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<Subscribe> listSubscribeByPage(RowBounds bounds,@Param("condition") SubscribeCondition condition);
	/**
	 * 选择人员分页查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<Subscribe> listSubscribeSelectByPage(RowBounds bounds,@Param("condition") SubscribeCondition condition);
	
	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listSubscribeByPageCount(@Param("condition") SubscribeCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 订阅表
	 */
	Subscribe getSubscribeById(@Param("id") String id);
	/**
	 * 根据邮箱、期刊类型查询
	 * @param id
	 * @return
	 */
	Subscribe getSubscribeItemByemail(@Param("email") String email,@Param("channelid") String channelid);

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
	int getSubscribeByemail(@Param("email") String email,@Param("channelid") String channelid);

	/**
	 * 取消订阅
	 */
	void cancleSuscribe(Subscribe item);
}

