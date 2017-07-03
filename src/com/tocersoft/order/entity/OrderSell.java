package com.tocersoft.order.entity;
import java.util.ArrayList;
import java.util.List;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 销售订单
 * 
 * @creator
 * @create-time 2014-08-09 21:28:52
 */
public class OrderSell extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 订单编号 */
	private String orderNo;
	/** 会员ID */
	private String memberId;
	/** 订单备注 */
	private String remark;
	/** 订单总金额 */
	private double amountTotal;
	/** 汇率转换后的 订单总金额 */
	private double exchangeRateAmountTotal;
	/** 运费总额 */
	private double amountFreight;
	/** 汇率转换后的 运费总额 */
	private double exchangeRateAmountFreight;
	/** 产品总额 */
	private double amountProduct;
	/** 汇率转换后的 产品总额 */
	private double exchangeRateAmountProduct;
	/** 送货国家代码 */
	private String sendCountryCode;
	/** 送货省份 */
	private String sendProvince;
	/** 送货城市 */
	private String sendCity;
	/** 送货地址 */
	private String sendAddress;
	/** 送货邮箱 */
	private String sendPostCode;
	/** 送货联系人 */
	private String sendLinkman;
	/** 送货联系电话 */
	private String sendContact;
	/** 订单状态：0-未付款 1-待发货 2-已发货 3-交易成功 4-订单作废 */
	private Integer orderState;
	/** 物流公司 */
	private String freightCompany;
	/** 运单号码 */
	private String freightBillNo;
	/** 发货时间 */
	private String freightDate;
	/** 省市区  */
	private String provinceCity;
	/** 订单状态(不持久化至数据库) */
	private String instantOrderState;
	/** 我的订单详细集合 */
	private  List<OrderSellItem> orderSellItems = new ArrayList<OrderSellItem>();
	
	/** 会员名称 不持久化至数据库 */
	private String memberName;
	
	private String sendCountry;
	
	/** 折扣代码 0:无折扣 1：已折扣 */
	private Integer discountCode;
	/** 免运费代码 0:未免运费 1：已免运费 */
	private Integer freeShippingCode;
	/** 买家留言 */
	private String words;
	
	/** 订单编号 */
	public String getOrderNo(){
		return this.orderNo;
	}

	/** 订单编号 */
	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}
	/** 会员ID */
	public String getMemberId(){
		return this.memberId;
	}

	/** 会员ID */
	public void setMemberId(String memberId){
		this.memberId = memberId;
	}
	/** 订单备注 */
	public String getRemark(){
		return this.remark;
	}

	/** 订单备注 */
	public void setRemark(String remark){
		this.remark = remark;
	}
	/** 订单总金额 */
	public double getAmountTotal(){
		return this.amountTotal;
	}

	/** 订单总金额 */
	public void setAmountTotal(double amountTotal){
		this.amountTotal = amountTotal;
	}
	/** 运费总额 */
	public double getAmountFreight(){
		return this.amountFreight;
	}

	/** 运费总额 */
	public void setAmountFreight(double amountFreight){
		this.amountFreight = amountFreight;
	}
	/** 产品总额 */
	public double getAmountProduct(){
		return this.amountProduct;
	}

	/** 产品总额 */
	public void setAmountProduct(double amountProduct){
		this.amountProduct = amountProduct;
	}
	
	/** 汇率转换后的 订单总金额 */
	public double getExchangeRateAmountTotal() {
		return exchangeRateAmountTotal;
	}

	/** 汇率转换后的 订单总金额 */
	public void setExchangeRateAmountTotal(double exchangeRateAmountTotal) {
		this.exchangeRateAmountTotal = exchangeRateAmountTotal;
	}

	/** 汇率转换后的 运费总额 */
	public double getExchangeRateAmountFreight() {
		return exchangeRateAmountFreight;
	}

	/** 汇率转换后的 运费总额 */
	public void setExchangeRateAmountFreight(double exchangeRateAmountFreight) {
		this.exchangeRateAmountFreight = exchangeRateAmountFreight;
	}

	/** 汇率转换后的 产品总额 */
	public double getExchangeRateAmountProduct() {
		return exchangeRateAmountProduct;
	}

	/** 汇率转换后的 产品总额 */
	public void setExchangeRateAmountProduct(double exchangeRateAmountProduct) {
		this.exchangeRateAmountProduct = exchangeRateAmountProduct;
	}

	/** 送货国家 */
	public String getSendCountryCode() {
		return sendCountryCode;
	}

	/** 送货国家 */
	public void setSendCountryCode(String sendCountryCode) {
		this.sendCountryCode = sendCountryCode;
	}

	/** 送货省份 */
	public String getSendProvince() {
		return sendProvince;
	}

	/** 送货省份 */
	public void setSendProvince(String sendProvince) {
		this.sendProvince = sendProvince;
	}

	/** 送货城市 */
	public String getSendCity() {
		return sendCity;
	}

	/** 送货城市 */
	public void setSendCity(String sendCity) {
		this.sendCity = sendCity;
	}

	/** 送货地址 */
	public String getSendAddress(){
		return this.sendAddress;
	}

	/** 送货地址 */
	public void setSendAddress(String sendAddress){
		this.sendAddress = sendAddress;
	}
	/** 送货联系人 */
	public String getSendLinkman(){
		return this.sendLinkman;
	}

	/** 送货联系人 */
	public void setSendLinkman(String sendLinkman){
		this.sendLinkman = sendLinkman;
	}
	/** 送货联系电话 */
	public String getSendContact(){
		return this.sendContact;
	}

	/** 送货联系电话 */
	public void setSendContact(String sendContact){
		this.sendContact = sendContact;
	}
	/** 订单状态：0-未付款 1-待发货 2-已发货 3-交易成功 4-订单作废 */
	public Integer getOrderState(){
		return this.orderState;
	}

	/** 订单状态：0-未付款 1-等待卖家发货 2-已发货 3-交易成功 4-订单作废 */
	public void setOrderState(Integer orderState){
		this.orderState = orderState;
	}
	
	// 格式化订单状态显示
	public String formatOrderState(Integer orderState){
		String instantOrderState = "";
		switch(orderState){
			case 0:
				instantOrderState = "未付款";
			break;
			case 1:
				instantOrderState = "待发货";
			break;
			case 2:
				instantOrderState = "已发货";
			break;
			case 3:
				instantOrderState = "交易成功";
			break;
			case 4:
				instantOrderState = "订单作废";
			break;
			default:
				instantOrderState = "未付款";
			break;
		}
		return instantOrderState;
	}
	
	// 格式化订单状态显示(英文)
	public String formatOrderStateEn(Integer orderState){
		String instantOrderState = "";
		switch(orderState){
			case 0:
				instantOrderState = "Non-payment";
			break;
			case 1:
				instantOrderState = "Wait for the delivery";
			break;
			case 2:
				instantOrderState = "Delivered";
			break;
			case 3:
				instantOrderState = "Success Deal";
			break;
			case 4:
				instantOrderState = "Order Cancellation";
			break;
			default:
				instantOrderState = "Non-payment";
			break;
		}
		return instantOrderState;
	}
	
	public String formatCountry(String sendCountryCode){
		if(sendCountryCode.equals("CN")){
			return "中国";
		}
		if(sendCountryCode.equals("EN")){
			return "美国";
		}else{
			return "";
		}
	}
	
	/** 物流公司 */
	public String getFreightCompany(){
		return this.freightCompany;
	}

	/** 物流公司 */
	public void setFreightCompany(String freightCompany){
		this.freightCompany = freightCompany;
	}
	/** 运单号码 */
	public String getFreightBillNo(){
		return this.freightBillNo;
	}

	/** 运单号码 */
	public void setFreightBillNo(String freightBillNo){
		this.freightBillNo = freightBillNo;
	}
	/** 发货时间 */
	public String getFreightDate(){
		return this.freightDate;
	}

	/** 发货时间 */
	public void setFreightDate(String freightDate){
		this.freightDate = freightDate;
	}

	public String getProvinceCity() {
		return provinceCity;
	}

	public void setProvinceCity(String provinceCity) {
		this.provinceCity = provinceCity;
	}

	public List<OrderSellItem> getOrderSellItems() {
		return orderSellItems;
	}

	public void setOrderSellItems(List<OrderSellItem> orderSellItems) {
		this.orderSellItems = orderSellItems;
	}

	public String getInstantOrderState() {
		return instantOrderState;
	}

	public void setInstantOrderState(String instantOrderState) {
		this.instantOrderState = instantOrderState;
	}

	public String getSendPostCode() {
		return sendPostCode;
	}

	public void setSendPostCode(String sendPostCode) {
		this.sendPostCode = sendPostCode;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getSendCountry() {
		return sendCountry;
	}

	public void setSendCountry(String sendCountry) {
		this.sendCountry = sendCountry;
	}

	public Integer getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(Integer discountCode) {
		this.discountCode = discountCode;
	}

	public Integer getFreeShippingCode() {
		return freeShippingCode;
	}

	public void setFreeShippingCode(Integer freeShippingCode) {
		this.freeShippingCode = freeShippingCode;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}
	
}