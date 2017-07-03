package com.tocersoft.cms.entity;

import java.util.List;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 广告管理
 * 
 * @author 方泉
 * 
 */
public class CmsAdv extends BaseBusEntity {

	private static final long serialVersionUID = 9143225122750279955L;
	
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
	/** 排序 */
	private Integer sort;
	
	/** 该广告位下面的广告图 -不存DB */
	private List<CmsAdvPhoto> photos;
	
	public List<CmsAdvPhoto> getPhotos() {
		return photos;
	}
	public void setPhotos(List<CmsAdvPhoto> photos) {
		this.photos = photos;
	}
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
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}
