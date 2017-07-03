package com.tocersoft.cms.model;

import java.util.ArrayList;
import java.util.List;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.cms.entity.CmsAdvPhoto;

public class CmsAdvPhotoModel extends BaseModel<CmsAdvPhoto> {

	private static final long serialVersionUID = 4346507314203660761L;
	
	/** 广告位实体 */
	private CmsAdvPhoto item = new CmsAdvPhoto();
	
	/** 广告位ID */
	private String advId;
	
	private List<CmsAdvPhoto> photoList = new ArrayList<CmsAdvPhoto>();
	/**
	 * 图片位置
	 */
	private int index;
	
	/** 当前页码 */
	private Integer page;

	public CmsAdvPhoto getItem() {
		return item;
	}

	public void setItem(CmsAdvPhoto item) {
		this.item = item;
	}

	public String getAdvId() {
		return advId;
	}

	public void setAdvId(String advId) {
		this.advId = advId;
	}


	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<CmsAdvPhoto> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(List<CmsAdvPhoto> photoList) {
		this.photoList = photoList;
	}

}
