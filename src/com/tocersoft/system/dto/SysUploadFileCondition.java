package com.tocersoft.system.dto;

import com.tocersoft.base.dto.BaseCondition;

public class SysUploadFileCondition extends BaseCondition {

	/** 文件名称-用户自定义 */
	private String name;
	/** 上传时的文件名 */
	private String fileName;
	/** 物理存放路径 */
	private String path;
	/** 文件下载URL */
	private String url;
	/** 文件扩展名 */
	private String extend;
	/** 文件说明 */
	private String desc;
	/** 超链接 */
	private String link;
	/** 相关联的对象ID */
	private String objectId;
	/** 相关联的对象类型 */
	private String objectType;

	/** 文件名称 */
	public String getFileName(){
		return this.fileName;
	}

	/** 文件名称 */
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	/** 物理存放路径 */
	public String getPath(){
		return this.path;
	}

	/** 物理存放路径 */
	public void setPath(String path){
		this.path = path;
	}
	/** 文件下载URL */
	public String getUrl(){
		return this.url;
	}

	/** 文件下载URL */
	public void setUrl(String url){
		this.url = url;
	}
	/** 文件扩展名 */
	public String getExtend(){
		return this.extend;
	}

	/** 文件扩展名 */
	public void setExtend(String extend){
		this.extend = extend;
	}
	/** 文件说明 */
	public String getDesc(){
		return this.desc;
	}

	/** 文件说明 */
	public void setDesc(String desc){
		this.desc = desc;
	}
	/** 超链接 */
	public String getLink(){
		return this.link;
	}

	/** 超链接 */
	public void setLink(String link){
		this.link = link;
	}
	/** 相关联的对象ID */
	public String getObjectId(){
		return this.objectId;
	}

	/** 相关联的对象ID */
	public void setObjectId(String objectId){
		this.objectId = objectId;
	}
	/** 相关联的对象类型 */
	public String getObjectType(){
		return this.objectType;
	}

	/** 相关联的对象类型 */
	public void setObjectType(String objectType){
		this.objectType = objectType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
