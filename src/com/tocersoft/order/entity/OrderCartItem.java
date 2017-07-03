package com.tocersoft.order.entity;
import com.tocersoft.base.entity.BaseBusEntity;
import com.tocersoft.base.utils.DoubleUtil;
import com.tocersoft.product.entity.Product;

/**
 * 购物车明细项
 * 
 * @creator
 * @create-time 2014-08-09 21:28:52
 */
public class OrderCartItem extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 会员ID */
	private String memberId;
	/** 产品ID */
	private String productId;
	/** 产品SKU的ID */
	private String productSkuId;
	/** 订购数量 */
	private double num;
	/** 备注 */
	private String remark;
	
	/** ====== 不持久化数据库 ======= */
	/** 购物车相关产品实体 */
	private Product product;
	/** 单价 */
	private Double unitPrice;
	/** 价格小计 */
	@SuppressWarnings("unused")
	private Double sumPrice;
	
	public Double getSumPrice() {
		if(null == unitPrice){
			unitPrice = product.getUnitPrice();
		}
		return DoubleUtil.mul(unitPrice, num);
	}

	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	/** 会员ID */
	public String getMemberId(){
		return this.memberId;
	}

	/** 会员ID */
	public void setMemberId(String memberId){
		this.memberId = memberId;
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
	/** 订购数量 */
	public double getNum(){
		return this.num;
	}

	/** 订购数量 */
	public void setNum(double num){
		this.num = num;
	}
	/** 备注 */
	public String getRemark(){
		return this.remark;
	}

	/** 备注 */
	public void setRemark(String remark){
		this.remark = remark;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
}