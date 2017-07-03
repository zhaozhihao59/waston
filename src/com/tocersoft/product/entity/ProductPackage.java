package com.tocersoft.product.entity;
import java.util.Date;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 产品包装信息
 * 
 * @creator
 * @create-time 2014-06-25 20:15:54
 */
public class ProductPackage extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 对应的产品ID */
	private String productId;
	/** 单位重量 */
	private Double grossWeight;
	/** 高度 */
	private Double height;
	/** 长度 */
	private Double length;
	/** 数量 */
	private Long lots;
	/** 单位ID */
	private String measureId;
	/** 每包销售数量 */
	private Long packingQuantity;
	/** 宽度 */
	private Double width;
	/** 产品阶梯重量 */
	private ProductWeightRange prodWeightRange;

	public ProductWeightRange getProdWeightRange() {
		return prodWeightRange;
	}

	public void setProdWeightRange(ProductWeightRange prodWeightRange) {
		this.prodWeightRange = prodWeightRange;
	}

	/** 对应的产品ID */
	public String getProductId(){
		return this.productId;
	}

	/** 对应的产品ID */
	public void setProductId(String productId){
		this.productId = productId;
	}
	/** 单位重量 */
	public Double getGrossWeight(){
		return this.grossWeight;
	}

	/** 单位重量 */
	public void setGrossWeight(Double grossWeight){
		this.grossWeight = grossWeight;
	}
	/** 高度 */
	public Double getHeight(){
		return this.height;
	}

	/** 高度 */
	public void setHeight(Double height){
		this.height = height;
	}
	/** 长度 */
	public Double getLength(){
		return this.length;
	}

	/** 长度 */
	public void setLength(Double length){
		this.length = length;
	}
	/** 数量 */
	public Long getLots(){
		return this.lots;
	}

	/** 数量 */
	public void setLots(Long lots){
		this.lots = lots;
	}
	/** 单位ID */
	public String getMeasureId(){
		return this.measureId;
	}

	/** 单位ID */
	public void setMeasureId(String measureId){
		this.measureId = measureId;
	}
	/** 每包销售数量 */
	public Long getPackingQuantity(){
		return this.packingQuantity;
	}

	/** 每包销售数量 */
	public void setPackingQuantity(Long packingQuantity){
		this.packingQuantity = packingQuantity;
	}
	/** 宽度 */
	public Double getWidth(){
		return this.width;
	}

	/** 宽度 */
	public void setWidth(Double width){
		this.width = width;
	}
}