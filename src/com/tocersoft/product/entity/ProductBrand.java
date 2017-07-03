package com.tocersoft.product.entity;
import java.util.Date;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 产品品牌信息
 * 
 * @creator
 * @create-time 2014-08-03 21:17:54
 */
public class ProductBrand extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 品牌名称 - 中文 */
	private String name;
	/** 品牌名称 - 英文 */
	private String nameEn;
	/** 品牌介绍 - 中文 */
	private String introduce;
	/** 品牌介绍 - 英文 */
	private String introduceEn;
	/** 品牌图片路径 */
	private String imagePath;
	/** 品牌类型：数据字典项ID */
	private String brandTypeId;
	/** 排序参数：数字越小越靠前 */
	private Integer sort;
	/** 是否为明星品牌 */
	private Integer isStarBrand;
	
	/** ======== 不持久化数据库 ========= */
	
	/** 品牌类型名称 */
	private String brandTypeName;

	public String getBrandTypeName() {
		return brandTypeName;
	}

	public void setBrandTypeName(String brandTypeName) {
		this.brandTypeName = brandTypeName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getBrandTypeId() {
		return brandTypeId;
	}

	public void setBrandTypeId(String brandTypeId) {
		this.brandTypeId = brandTypeId;
	}

	public Integer getIsStarBrand() {
		return isStarBrand;
	}

	public void setIsStarBrand(Integer isStarBrand) {
		this.isStarBrand = isStarBrand;
	}
	
}