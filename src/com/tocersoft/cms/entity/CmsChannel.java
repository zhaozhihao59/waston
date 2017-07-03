package com.tocersoft.cms.entity;

import java.util.ArrayList;
import java.util.List;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 栏目管理
 * 
 * @author 欧阳明航
 * 
 */
public class CmsChannel extends BaseBusEntity {

	private static final long serialVersionUID = 1L;
	
	/** 栏目名称 */
	private String name;
	
	/** 状态 0-用户 1-系统 null-用户 */
	private Integer state;
	/** 备注 */
	private String remark;
	/** 是否放置在网站右侧 1-是 0-否*/
	private Integer isRight;
	/** 是否英文 1-英文 0-中*/
	private Integer isEn;
	/** 排序 */
	private Integer sort;
	/**
	 * 类别编号唯一 编号规则
	 * 
	 * <pre>
	 * 一级：起始 0000 最大值9999
	 * 二级：起始 0000-0000 0000-9999 9999-9999
	 * 三级：起始 0000-0000-0000 9999-9999-9999
	 * ....
	 * </pre>
	 */
	private String codeNum;
	/** 上级类别id 默认为0 */
	private String parent;
	/** 是否父级 0-非父级 1-父级 null-非父级 */
	private Integer isParent;
	/** 类别级别 共三级类别 */
	private Integer level;
	/** 是否可删除 0-可删除 1-不可删除 null-可删除 */
	private Integer isDelete;
	/** 文章集合 */
	private List<CmsArticle> cmsArticles = new ArrayList<CmsArticle>();
	
	private List<CmsArticle> articles;

	public List<CmsArticle> getArticles() {
		return articles;
	}

	public void setArticles(List<CmsArticle> articles) {
		this.articles = articles;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getIsRight() {
		return isRight;
	}

	public void setIsRight(Integer isRight) {
		this.isRight = isRight;
	}

	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getCodeNum() {
		return codeNum;
	}

	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}

	public Integer getIsParent() {
		return isParent;
	}

	public void setIsParent(Integer isParent) {
		this.isParent = isParent;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public List<CmsArticle> getCmsArticles() {
		return cmsArticles;
	}

	public void setCmsArticles(List<CmsArticle> cmsArticles) {
		this.cmsArticles = cmsArticles;
	}

	public Integer getIsEn() {
		return isEn;
	}

	public void setIsEn(Integer isEn) {
		this.isEn = isEn;
	}

	
}
