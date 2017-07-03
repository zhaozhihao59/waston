package com.tocersoft.product.entity;
import java.util.Date;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 产品包装重量信息
 * 
 * @creator
 * @create-time 2014-06-25 20:15:54
 */
public class ProductWeightRange extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 对应的产品包装ID */
	private String packageId;
	/** 基本体积 */
	private Long baseQt;
	/** 是否只以重量计算运费 */
	private String isOnlyWeight;
	/** 单步体积 */
	private Long stepQt;
	/** 单步重量 */
	private Double stepWeight;

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	/** 基本体积 */
	public Long getBaseQt(){
		return this.baseQt;
	}

	/** 基本体积 */
	public void setBaseQt(Long baseQt){
		this.baseQt = baseQt;
	}
	/** 是否只以重量计算运费 */
	public String getIsOnlyWeight(){
		return this.isOnlyWeight;
	}

	/** 是否只以重量计算运费 */
	public void setIsOnlyWeight(String isOnlyWeight){
		this.isOnlyWeight = isOnlyWeight;
	}
	/** 单步体积 */
	public Long getStepQt(){
		return this.stepQt;
	}

	/** 单步体积 */
	public void setStepQt(Long stepQt){
		this.stepQt = stepQt;
	}
	/** 单步重量 */
	public Double getStepWeight(){
		return this.stepWeight;
	}

	/** 单步重量 */
	public void setStepWeight(Double stepWeight){
		this.stepWeight = stepWeight;
	}
}