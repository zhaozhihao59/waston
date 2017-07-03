package com.tocersoft.product.entity;
import java.util.List;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 产品SKU信息
 * 
 * @creator
 * @create-time 2014-06-25 20:15:54
 */
public class ProductSku extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 对应的产品ID */
	private String productId;
	/** 产品备货数量(有备货为必选，无备货为可选) */
	private Long inventory;
	/** 零售价 */
	private Double retailPrice;
	/** 销售状态：0 不可销售，1 可销售 */
	private Long saleStatus;
	/** sku编码 */
	private String skuCode;
	/** 产品SKU属性值列表 */
	private List<ProductSkuAttrval> prodSkuAttrvalList;
	
	/** 对应的产品ID */
	public String getProductId(){
		return this.productId;
	}

	/** 对应的产品ID */
	public void setProductId(String productId){
		this.productId = productId;
	}
	/** 产品备货数量(有备货为必选，无备货为可选) */
	public Long getInventory(){
		return this.inventory;
	}

	/** 产品备货数量(有备货为必选，无备货为可选) */
	public void setInventory(Long inventory){
		this.inventory = inventory;
	}
	/** 零售价 */
	public Double getRetailPrice(){
		return this.retailPrice;
	}

	/** 零售价 */
	public void setRetailPrice(Double retailPrice){
		this.retailPrice = retailPrice;
	}
	/** 销售状态：0 不可销售，1 可销售 */
	public Long getSaleStatus(){
		return this.saleStatus;
	}

	/** 销售状态：0 不可销售，1 可销售 */
	public void setSaleStatus(Long saleStatus){
		this.saleStatus = saleStatus;
	}
	/** sku编码 */
	public String getSkuCode(){
		return this.skuCode;
	}

	/** sku编码 */
	public void setSkuCode(String skuCode){
		this.skuCode = skuCode;
	}

	public void setProdSkuAttrvalList(List<ProductSkuAttrval> prodSkuAttrvalList) {
		this.prodSkuAttrvalList = prodSkuAttrvalList;
	}

	public List<ProductSkuAttrval> getProdSkuAttrvalList() {
		return prodSkuAttrvalList;
	}
}