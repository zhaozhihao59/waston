package com.tocersoft.cms.entity;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 广告图管理
 * 
 * @author 方泉
 * 
 */
public class CmsAdvPhoto extends BaseBusEntity {

	private static final long serialVersionUID = 9143225122750279955L;
	
	/** 广告图片名称 */
	private String name;
	/** 广告图片文件名称 */
	private String fileName;
	/** 广告图片路径 */
	private String path;
	/** 广告图片超链接 */
	private String linkUrl;
	/** 广告图片说明 */
	private String note;
	/** 广告位ID */
	private String advId;
	/** 排序 */
	private Integer sort;
	
	public String getAdvId() {
		return advId;
	}
	public void setAdvId(String advId) {
		this.advId = advId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
