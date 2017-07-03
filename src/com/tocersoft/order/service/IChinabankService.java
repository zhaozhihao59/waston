package com.tocersoft.order.service;

import java.util.List;

import com.tocersoft.member.entity.Member;
import com.tocersoft.order.entity.OrderSell;
import com.tocersoft.order.entity.OrderSellItem;

/**
 * 网银在线支付service
 * @creator     zhangqiang
 * @email zhangqiang@tocersoft.com
 * @company www.tocersoft.com
 * @create-time Sep 29, 2014   3:05:21 PM
 * @version 1.0
 */
public interface IChinabankService {

	/**
	 * 获取支付xml格式字符串
	 * @param member
	 * @param orderSell
	 * @param orderSellItemList
	 * @return
	 */
	String getPayInfoXmlStr(Member member, OrderSell orderSell,List<OrderSellItem> orderSellItemList);

}
