package com.tocersoft.order.model;

import java.util.SortedMap;
import java.util.TreeMap;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.base.page.PageResult;
import com.tocersoft.member.dto.MemAddressCondition;
import com.tocersoft.member.entity.MemAddress;
import com.tocersoft.order.dto.OrderSellCondition;
import com.tocersoft.order.entity.OrderSell;
import com.tocersoft.product.entity.ExchangeRate;

public class OrderSellModel extends BaseModel<OrderSell>{

	private static final long serialVersionUID = 2694513264571314669L;

	private OrderSell item = new OrderSell();
	private OrderSellCondition condition = new OrderSellCondition();
	
	/** 从购物车进行下单 */
	private String cartItemStrs;
	
	/** 直接从产品列表中的产品下单 */
	private String productId;
	/** 收货地址  */
	private MemAddress memaddress;
	private PageResult<MemAddress> memAddressPageResult = new PageResult<MemAddress>();
	private MemAddressCondition memaddresscondition = new MemAddressCondition();
	/** 汇率 */
	private ExchangeRate exchangeRate = new ExchangeRate();
	
	private String payInfoEncodeStr;
	private String payInfoSignStr;
	/** 支付宝支付签名 */
	private String alipaySign;
	/** 立即购购数量 */
	private double num;
	
	/** 支付宝支付参数map */
	private SortedMap<String,String> alipayParamsMap = new TreeMap<String, String>();

	public OrderSellModel() {
		super();
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public OrderSell getItem() {
		return item;
	}

	public void setItem(OrderSell item) {
		this.item = item;
	}

	public OrderSellCondition getCondition() {
		return condition;
	}

	public void setCondition(OrderSellCondition condition) {
		this.condition = condition;
	}

	public String getCartItemStrs() {
		return cartItemStrs;
	}

	public void setCartItemStrs(String cartItemStrs) {
		this.cartItemStrs = cartItemStrs;
	}

	public MemAddress getMemaddress() {
		return memaddress;
	}

	public void setMemaddress(MemAddress memaddress) {
		this.memaddress = memaddress;
	}

	public PageResult<MemAddress> getMemAddressPageResult() {
		return memAddressPageResult;
	}

	public void setMemAddressPageResult(PageResult<MemAddress> memAddressPageResult) {
		this.memAddressPageResult = memAddressPageResult;
	}

	public MemAddressCondition getMemaddresscondition() {
		return memaddresscondition;
	}

	public void setMemaddresscondition(MemAddressCondition memaddresscondition) {
		this.memaddresscondition = memaddresscondition;
	}

	public ExchangeRate getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(ExchangeRate exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public double getNum() {
		return num;
	}

	public void setNum(double num) {
		this.num = num;
	}

	public String getPayInfoEncodeStr() {
		return payInfoEncodeStr;
	}

	public void setPayInfoEncodeStr(String payInfoEncodeStr) {
		this.payInfoEncodeStr = payInfoEncodeStr;
	}

	public String getPayInfoSignStr() {
		return payInfoSignStr;
	}

	public void setPayInfoSignStr(String payInfoSignStr) {
		this.payInfoSignStr = payInfoSignStr;
	}

	public String getAlipaySign() {
		return alipaySign;
	}

	public void setAlipaySign(String alipaySign) {
		this.alipaySign = alipaySign;
	}

	public SortedMap<String, String> getAlipayParamsMap() {
		return alipayParamsMap;
	}

	public void setAlipayParamsMap(SortedMap<String, String> alipayParamsMap) {
		this.alipayParamsMap = alipayParamsMap;
	}
}
