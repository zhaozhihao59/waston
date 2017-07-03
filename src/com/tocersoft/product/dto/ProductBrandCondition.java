package com.tocersoft.product.dto;

import com.tocersoft.base.dto.BaseCondition;

public class ProductBrandCondition extends BaseCondition {

	/** 品牌名称 */
	private String name;
	/** 品牌名称 - 英文 */
	private String nameEn;
	/** 品牌介绍 - 中文 */
	private String introduce;
	/** 品牌介绍 - 英文 */
	private String introduceEn;
	private Integer sort;
	
	/** 是否为明星品牌 */
	private Integer isStarBrand;
	

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	/** 品牌名称 */
	public String getName(){
		return this.name;
	}

	/** 品牌名称 */
	public void setName(String name){
		this.name = name;
	}
	/** 品牌介绍 - 中文 */
	public String getIntroduce(){
		return this.introduce;
	}

	/** 品牌介绍 - 中文 */
	public void setIntroduce(String introduce){
		this.introduce = introduce;
	}
	/** 品牌介绍 - 英文 */
	public String getIntroduceEn(){
		return this.introduceEn;
	}

	/** 品牌介绍 - 英文 */
	public void setIntroduceEn(String introduceEn){
		this.introduceEn = introduceEn;
	}

	public Integer getIsStarBrand() {
		return isStarBrand;
	}

	public void setIsStarBrand(Integer isStarBrand) {
		this.isStarBrand = isStarBrand;
	}
	
}
