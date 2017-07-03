package com.tocersoft.system.entity;
import java.util.List;

import com.tocersoft.base.entity.BaseBusEntity;
import com.tocersoft.product.entity.ProductBrand;

/**
 * 数据库字典明细表
 * @author      miaoshuai
 * @email       miaoshuai@tocersfot.com
 * @company		www.tocersoft.com
 * @create-time 2013-11-19 上午11:25:24
 * @version     1.0
 */
public class SysDictItem extends BaseBusEntity{
	private static final long serialVersionUID = -8732081628764473457L;
	
	/** 名称 */
	private String name;
	/** 名称 (英文版)*/
	private String nameEn;
	/**名称(日文版)*/
	private String nameJp;
	/** 排序 */
	private Integer sort;
	/** 数据库字典ID */
	private String dictId;
	
	public String getNameJp() {
		return nameJp;
	}
	public void setNameJp(String nameJp) {
		this.nameJp = nameJp;
	}
	private List<ProductBrand> productBrandList;
	
	public List<ProductBrand> getProductBrandList() {
		return productBrandList;
	}
	public void setProductBrandList(List<ProductBrand> productBrandList) {
		this.productBrandList = productBrandList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getDictId() {
		return dictId;
	}
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
}