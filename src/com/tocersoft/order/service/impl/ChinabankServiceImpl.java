package com.tocersoft.order.service.impl;

import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.utils.CommonUtil;
import com.tocersoft.member.entity.Member;
import com.tocersoft.order.entity.OrderSell;
import com.tocersoft.order.entity.OrderSellItem;
import com.tocersoft.order.service.IChinabankService;

@Service("chinabankServiceImpl")
@Transactional(value = "transactionManager")
public class ChinabankServiceImpl implements IChinabankService{
	
	private Log logger = LogFactory.getLog(ChinabankServiceImpl.class);
	
	@Value("${chinabank.pay.sucess.url}")
	private String paySuccessUrl;
	@Value("${chinabank.pay.receive.result.url}")
	private String payReceiveResultUrl;
	
	@Override
	public String getPayInfoXmlStr(Member member, OrderSell orderSell,List<OrderSellItem> orderSellItemList) {
		DecimalFormat df = new DecimalFormat("#.00");
		
		Document document = DocumentHelper.createDocument();
		// 第一级节点
		Element root = document.addElement("chinabank");
		//订单
		Element orderEl = root.addElement("order").addAttribute("version", "6.0").addAttribute("orderID", orderSell.getOrderNo());
		//商户号
		orderEl.addElement("attr").addAttribute("id", "merchantID").addAttribute("value", "23047025");	
		//订单总额
		orderEl.addElement("attr").addAttribute("id", "amount").addAttribute("value", df.format(orderSell.getAmountTotal()));	
		//货币单位
		orderEl.addElement("attr").addAttribute("id", "currency").addAttribute("value", "CNY");	
		//前台返回URL。支付结果返回URL，请传递全地址。若不需要则可不加入XML格式的明文串中。
		orderEl.addElement("attr").addAttribute("id", "backurl").addAttribute("value", paySuccessUrl);
		//服务编码。默认为0，进入网银在线收银台页面；若直连银行，填写银行对应服务编码，具体数值参见1.1.2.5
		orderEl.addElement("attr").addAttribute("id", "servicecode").addAttribute("value", "0");	
		//服务类型。网关支付填写0即可。
		orderEl.addElement("attr").addAttribute("id", "serviceType").addAttribute("value", "0");	
		//订单的授权码
		//orderEl.addElement("attr").addAttribute("id", "authcode").addAttribute("value", "23047025");
		//订单名称，字符集：GBK。若不需要则可不加入XML格式的明文串中。
		orderEl.addElement("attr").addAttribute("id", "ordername").addAttribute("value", orderSellItemList.get(0).getProductName());
		//订单时间。格式为：yyyymmddhh24miss。若不需要则可不加入XML格式的明文串中。
		orderEl.addElement("attr").addAttribute("id", "orderdate").addAttribute("value", CommonUtil.formatDate(orderSell.getCreateDate(), "yyyyMMddHHmmss"));
		//商户账户。若不需要则可不加入XML格式的明文串中。
		orderEl.addElement("attr").addAttribute("id", "merchantaccount").addAttribute("value", "NZUE");
		
		//备注信息
		Element remarkEl = orderEl.addElement("remark");	
		remarkEl.addElement("attr").addAttribute("id", "remark1").addAttribute("value", "{'order_id':'"+orderSell.getOrderNo()+"','user_id':'" + member.getId() +"','order_subtype':'4','isreport':'N','order_currency':'USD'}");
		//商户后台接收结果地址
		remarkEl.addElement("attr").addAttribute("id", "remark2").addAttribute("value", "url:=" + payReceiveResultUrl);
		
		//附加信息
		Element featuresEl = orderEl.addElement("features");
		//网关订单提交返回结果。填写0即可
		featuresEl.addElement("attr").addAttribute("id", "resultmode").addAttribute("value", "0");
		
		//生成字符串
		String xmlStr = document.asXML().replaceAll("\r\n", "").replaceAll("\n", "").replace("UTF-8", "GB2312");
		
		logger.warn("生成的订单支付字符串为：" + xmlStr);
		
		return xmlStr;
	}

}
