package com.tocersoft.cms.dto;

import com.tocersoft.base.dto.BaseCondition;

public class CmsAdvCondition extends BaseCondition {
	
	/** 广告位名称 */
	private String name;
	/** 广告位说明 */
	private String note;
	/** 尺寸（像素） - 宽度 */
	private Integer photoWidth;
	/** 尺寸（像素） - 高度 */
	private Integer photoHeight;
	/** 最多图片数量 */
	private Integer maxPhotoNum;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getPhotoWidth() {
		return photoWidth;
	}
	public void setPhotoWidth(Integer photoWidth) {
		this.photoWidth = photoWidth;
	}
	public Integer getPhotoHeight() {
		return photoHeight;
	}
	public void setPhotoHeight(Integer photoHeight) {
		this.photoHeight = photoHeight;
	}
	public Integer getMaxPhotoNum() {
		return maxPhotoNum;
	}
	public void setMaxPhotoNum(Integer maxPhotoNum) {
		this.maxPhotoNum = maxPhotoNum;
	}

}
