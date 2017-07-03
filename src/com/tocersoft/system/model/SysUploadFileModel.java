package com.tocersoft.system.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.system.entity.SysUploadFile;

import com.tocersoft.system.dto.SysUploadFileCondition;

public class SysUploadFileModel extends BaseModel<SysUploadFile>{

	private SysUploadFile item = new SysUploadFile();

	private SysUploadFileCondition condition = new SysUploadFileCondition();
	
	private String productId;
	
	/** 产品首图 */
	private String imageUrl;
	/** 赛事首图 */
	private String gamePhoto;
	/** 赛事列表图 */
	private String gamePhotoTo;
	
	/** 图片BASE-64编码字符串 */
	private String imageBase64;
	
	/** 产品接口 - 授权帐号 */
	private String authAccount;
	
	/** 产品接口 - 授权密码 */
	private String authPassword;

	public String getAuthAccount() {
		return authAccount;
	}

	public void setAuthAccount(String authAccount) {
		this.authAccount = authAccount;
	}

	public String getAuthPassword() {
		return authPassword;
	}

	public void setAuthPassword(String authPassword) {
		this.authPassword = authPassword;
	}

	public String getImageBase64() {
		return imageBase64;
	}

	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}

	public SysUploadFileModel() {
		super();
	}

	public SysUploadFile getItem() {
		return item;
	}

	public void setItem(SysUploadFile item) {
		this.item = item;
	}

	public SysUploadFileCondition getCondition() {
		return condition;
	}

	public void setCondition(SysUploadFileCondition condition) {
		this.condition = condition;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getGamePhoto() {
		return gamePhoto;
	}

	public void setGamePhoto(String gamePhoto) {
		this.gamePhoto = gamePhoto;
	}

	public String getGamePhotoTo() {
		return gamePhotoTo;
	}

	public void setGamePhotoTo(String gamePhotoTo) {
		this.gamePhotoTo = gamePhotoTo;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	


}
