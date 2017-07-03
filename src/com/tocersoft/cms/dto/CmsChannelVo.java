package com.tocersoft.cms.dto;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 栏目管理VO
 * 
 * @author 欧阳明航
 * 
 */
public class CmsChannelVo extends BaseBusEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8842175004797659860L;
	/** 栏目名称 */
	private String name;
	/** 备注 */
	private String remark;
	/** 文章数量 */
	private Integer articleNum;
	/** 状态 1:系统 2：用户 */
	private Integer state;
	private Integer isRight;
	private Integer sort;
	public Integer getIsRight() {
		return isRight;
	}
	public void setIsRight(Integer isRight) {
		this.isRight = isRight;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(Integer articleNum) {
		this.articleNum = articleNum;
	}

	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
}
