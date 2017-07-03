package com.tocersoft.system.entity;
import java.util.Date;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 文件图片表
 * 
 * @creator
 * @create-time 2014-05-28 23:07:44
 */
public class SysUploadFile extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

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
	/** 文件类型：0-图片，1-文件，2-音频，3-视频，4-Flash */
	private Integer type;
	/** 文件说明 */
	private String desc;
	/** 超链接 */
	private String link;
	/** 相关联的对象ID */
	private String objectId;
	/** 相关联的对象类型 */
	private String objectType;
	/** 位置参数：由不同的业务对象类型进行自定义 */
	private Integer location;
	/** 排序字段，数字越小排序越靠前 */
	private Integer sort;

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
	/** 文件类型：0-图片，1-文件，2-音频，3-视频，4-Flash */
	public Integer getType(){
		return this.type;
	}

	/** 文件类型：0-图片，1-文件，2-音频，3-视频，4-Flash */
	public void setType(Integer type){
		this.type = type;
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
	/** 排序字段，数字越小排序越靠前 */
	public Integer getSort(){
		return this.sort;
	}

	/** 排序字段，数字越小排序越靠前 */
	public void setSort(Integer sort){
		this.sort = sort;
	}

	public Integer getLocation() {
		return location;
	}

	public void setLocation(Integer location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}