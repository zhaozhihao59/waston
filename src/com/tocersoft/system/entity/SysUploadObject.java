package com.tocersoft.system.entity;
import java.util.Date;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 文件对应的关联对象类型：比如与哪张表关联，用于关联表
 * 
 * @creator
 * @create-time 2014-05-28 23:07:44
 */
public class SysUploadObject extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 关联对象类型 */
	private String name;

	/** 关联对象类型 */
	public String getName(){
		return this.name;
	}

	/** 关联对象类型 */
	public void setName(String name){
		this.name = name;
	}
}