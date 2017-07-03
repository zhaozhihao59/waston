package com.tocersoft.system.entity;
import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 数据库字典表
 * @author      miaoshuai
 * @email       miaoshuai@tocersfot.com
 * @company		www.tocersoft.com
 * @create-time 2013-11-19 上午11:25:10
 * @version     1.0
 */
public class SysDict extends BaseBusEntity{
	private static final long serialVersionUID = -50432915758635405L;
	
	/** 字典名称 */
	private String name;
	/** 排序 */
	private Integer sort;

	/** 字典名称 */
	public String getName(){
		return this.name;
	}

	/** 字典名称 */
	public void setName(String name){
		this.name = name;
	}
	/** 排序 */
	public Integer getSort(){
		return this.sort;
	}

	/** 排序 */
	public void setSort(Integer sort){
		this.sort = sort;
	}
}