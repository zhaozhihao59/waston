package com.tocersoft.order.service;

import java.util.List;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.member.entity.MemAddress;
import com.tocersoft.order.dto.FreightTemplateCondition;
import com.tocersoft.order.entity.OrderCartItem;
import com.tocersoft.order.entity.FreightTemplate;

public interface IFreightTemplateService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listFreightTemplateByPage(PageResult<FreightTemplate> pageResult,FreightTemplateCondition condition);
	
	/**
	 * 加载所有模板
	 * @return
	 */
	List<FreightTemplate> listFreightTemplateAll(FreightTemplateCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 运费模板表
	 */
	FreightTemplate getFreightTemplateById(String id);

	/**
	 * 新增
	 * @param item 运费模板表
	 */
	void add(FreightTemplate item);

	/**
	 * 修改
	 * @param item 运费模板表
	 */
	void update(FreightTemplate item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

//	/**
//	 * 根据微信用户所在地理位置，加载运费价格
//	 * @param wxCustomer
//	 * @return
//	 */
//	JSONObject loadFreightPriceByWXCustomer(WXCustomer wxCustomer,String proWeinId);

	/**
	 * 计算运费价格
	 * @param memAddress
	 * @param cartItems
	 * @return
	 */
	Double calFreightAmountByMemAddress(MemAddress memAddress,List<OrderCartItem> cartItems);
}

