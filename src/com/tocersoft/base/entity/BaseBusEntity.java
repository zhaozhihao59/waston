package com.tocersoft.base.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务基类
 * @creator     zhangqiang
 * @version 1.0
 * @create-time May 5, 2011   10:48:19 AM
 */
public abstract class BaseBusEntity implements Serializable{
	private static final long serialVersionUID = 7904053207325003853L;
	
	public static final String CREATE_BY_PROPERTY_NAME = "createBy";	//"创建人"属性名称
	public static final String UPDATE_BY_PROPERTY_NAME = "updateBy";	//"修改人"属性名称
	public static final String CREATE_DATE_PROPERTY_NAME = "createDate";// "创建日期"属性名称
	public static final String UPDATE_DATE_PROPERTY_NAME = "updateDate";// "修改日期"属性名称
	public static final String DELETE_FLAG_PROPERTY_NAME = "deleteFlag"; //"删除标志位"属性名称
	
	//更新实体时，拷贝属性忽略的属性集合
	public static final String[] IGNORE_PROPERTIES_ON_UPDATE = new String[]{"id","createBy","createDate","deleteFlag"};
	
	private String id;
	
	/** 创建人 */
	private String createBy;
	
	/** 创建时间 */
	private Date createDate;
	
	/** 修改人 */
	private String updateBy;
	
	/** 修改日期 */
	private Date updateDate;
	
	/** 删除标志位 0：未删除 1：已删除 */
	private Integer deleteFlag;
	
	public BaseBusEntity() {
		super();
	}
	
	public String getId() {
		return id;
	}
	
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
