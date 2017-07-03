package com.tocersoft.order.entity;
import com.tocersoft.base.entity.BaseBusEntity;
import com.tocersoft.base.utils.DoubleUtil;
import com.tocersoft.product.entity.Product;

/**
 * 销售订单明细
 * 
 * @creator
 * @create-time 2014-08-09 21:28:52
 */
public class OrderSellItem extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 订单ID */
	private String orderId;
	/** 产品ID */
	private String productId;
	/** 产品SKU的ID */
	private String productSkuId;
	/** 产品名称 */
	private String productName;
	/** 产品名称(英文) */
	private String productNameEn;
	/** 产品图片 */
	private String productPhoto;
	/** 产品单价 */
	private double unitPrice;
	/** 汇率转换后的 产品单价 */
	private double exchangeRateUnitPrice;
	/** 订购数量 */
	private double num;

	/** ====== 不持久化数据库 ======= */
	
	/** 产品销量（不持久化至数据库） */
	private int sales;
	/** 购物车相关产品实体 */
	private Product product;
	/** 价格小计 */
	@SuppressWarnings("unused")
	private Double sumPrice;

	
	public Double getSumPrice(){
		return DoubleUtil.mul(unitPrice, num);
	}

	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}
	
	/** 订单ID */
	public String getOrderId(){
		return this.orderId;
	}

	/** 订单ID */
	public void setOrderId(String orderId){
		this.orderId = orderId;
	}
	/** 产品ID */
	public String getProductId(){
		return this.productId;
	}

	/** 产品ID */
	public void setProductId(String productId){
		this.productId = productId;
	}
	/** 产品SKU的ID */
	public String getProductSkuId(){
		return this.productSkuId;
	}

	/** 产品SKU的ID */
	public void setProductSkuId(String productSkuId){
		this.productSkuId = productSkuId;
	}
	/** 产品名称 */
	public String getProductName(){
		return this.productName;
	}

	/** 产品名称 */
	public void setProductName(String productName){
		this.productName = productName;
	}
	/** 产品图片 */
	public String getProductPhoto(){
		return this.productPhoto;
	}

	/** 产品图片 */
	public void setProductPhoto(String productPhoto){
		this.productPhoto = productPhoto;
	}
	/** 产品单价 */
	public double getUnitPrice(){
		return this.unitPrice;
	}

	/** 产品单价 */
	public void setUnitPrice(double unitPrice){
		this.unitPrice = unitPrice;
	}
	
	/** 汇率转换后的 产品单价 */
	public double getExchangeRateUnitPrice() {
		return exchangeRateUnitPrice;
	}

	/** 汇率转换后的 产品单价 */
	public void setExchangeRateUnitPrice(double exchangeRateUnitPrice) {
		this.exchangeRateUnitPrice = exchangeRateUnitPrice;
	}

	/** 订购数量 */
	public double getNum(){
		return this.num;
	}

	/** 订购数量 */
	public void setNum(double num){
		this.num = num;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getProductNameEn() {
		return productNameEn;
	}

	public void setProductNameEn(String productNameEn) {
		this.productNameEn = productNameEn;
	}	
}