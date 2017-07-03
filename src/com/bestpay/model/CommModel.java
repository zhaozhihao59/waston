package com.bestpay.model;

import java.io.Serializable;

import com.bestpay.util.CryptTool;


public class CommModel implements Serializable {

	private final String merchantId = "02310109012159000"; // 商户代码

	private final String commKey = "052C58F11CB2DFB693DD8C80D50239188DE93AE24E4403C2"; // 商户key

	private final String commPwd = "02310109"; // 商户密码

	private String merchantUrl = "http://www.heros.org.cn/game/pay_result.htm"; // 前台通知地址
	//private String merchantUrl = "http://heros.tocersoft.net/game/pay_result.htm"; // 前台通知地址

	private String backMerchantUrl = "http://www.heros.org.cn/game/pay_result.htm"; // 后台通知地址

	private String orderID = CryptTool.getCurrentDate(); // 订单号(当前时间)(格式如：yyyymmddhhmmss)

	private String orderReqTranSeq = CryptTool.getCurrentDate() + "000001"; // 订单流水号(当前时间+000001)(格式如：yyyymmddhhmmss000001)

	private String orderDate = CryptTool.getTodayDate2(); // 订单日期

//	private String ActionUrlSelectBank = "https://webpaywg.bestpay.com.cn/payWeb.do"; // 请求网关平台地址(选择银行再进行交易)
//
//	private String ActionUrlBank = "https://webpaywg.bestpay.com.cn/payWebDirect.do"; // 请求网关平台地址(直接进行交易)

	private String reFundUrl = "http://telepay.bestpay.com.cn/services"; // 退款地址

	private String reqTime = CryptTool.getCurrentDate(); // 退款请求时间，格式yyyyMMddHHmmss

	private String orderRefundID = "REFUNDID" + System.currentTimeMillis(); // 退款请求流水号

	private String productAmount;
	private String attachAmount;
	private String transAmount;
	private String curType = "RMB";
	private final String encodeType = "1";
	private String attach = "比赛报名";
	private final String busiCode = "0001";
	private final String productId = "99";
	private final String tmNum = "99";
	private String productDesc = "比赛报名";
	private String ipRemote;
	private String mac;
	private String customerId;
	public String getMerchantUrl() {
		return merchantUrl;
	}
	public void setMerchantUrl(String merchantUrl) {
		this.merchantUrl = merchantUrl;
	}
	public String getBackMerchantUrl() {
		return backMerchantUrl;
	}
	public void setBackMerchantUrl(String backMerchantUrl) {
		this.backMerchantUrl = backMerchantUrl;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getOrderReqTranSeq() {
		return orderReqTranSeq;
	}
	public void setOrderReqTranSeq(String orderReqTranSeq) {
		this.orderReqTranSeq = orderReqTranSeq;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getReFundUrl() {
		return reFundUrl;
	}
	public void setReFundUrl(String reFundUrl) {
		this.reFundUrl = reFundUrl;
	}
	public String getReqTime() {
		return reqTime;
	}
	public void setReqTime(String reqTime) {
		this.reqTime = reqTime;
	}
	public String getOrderRefundID() {
		return orderRefundID;
	}
	public void setOrderRefundID(String orderRefundID) {
		this.orderRefundID = orderRefundID;
	}
	public String getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(String productAmount) {
		this.productAmount = productAmount;
	}
	public String getAttachAmount() {
		return attachAmount;
	}
	public void setAttachAmount(String attachAmount) {
		this.attachAmount = attachAmount;
	}
	public String getTransAmount() {
		return transAmount;
	}
	public void setTransAmount(String transAmount) {
		this.transAmount = transAmount;
	}
	public String getCurType() {
		return curType;
	}
	public void setCurType(String curType) {
		this.curType = curType;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getIpRemote() {
		return ipRemote;
	}
	public void setIpRemote(String ipRemote) {
		this.ipRemote = ipRemote;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public String getCommKey() {
		return commKey;
	}
	public String getCommPwd() {
		return commPwd;
	}
	public String getEncodeType() {
		return encodeType;
	}
	public String getBusiCode() {
		return busiCode;
	}
	public String getProductId() {
		return productId;
	}
	public String getTmNum() {
		return tmNum;
	}

}
