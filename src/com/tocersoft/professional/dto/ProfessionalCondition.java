package com.tocersoft.professional.dto;

import com.tocersoft.base.dto.BaseCondition;

public class ProfessionalCondition extends BaseCondition {

	/** 专业人员名称 */
	private String name;
	/** 专业 人员简介 */
	private String desc;
	/** 专业人员 头像 */
	private String photo;
	/** 专业人员 邮箱 */
	private String email;
	/** 资格类型(数据库字典项多选) */
	private String qualification;
	/** 工作语言(数据库字典项多选) */
	private String language;
	/** 工作语言(数据库字典项多选) */
	private Integer type;
	/** 专业人员名称【英文】 */
	private String nameEn;
	/** 专业 人员简介【英文】 */
	private String descEn;
	/** 资格类型【英文】(数据库字典项多选) */
	private String qualificationEn;
	/** 工作语言【英文】(数据库字典项多选) */
	private String languageEn;
	/** 专业人员名称【日文】 */
	private String nameJp;
	/** 专业 人员简介【日文】 */
	private String descJp;
	/** 资格类型【日文】(数据库字典项多选) */
	private String qualificationJp;
	/** 工作语言【日文】(数据库字典项多选) */
	private String languageJp;
	/**业务*/
	private String business;
	private String businessEn;
	private String businessJp;
	/**判断是不是律师*/
	private String[] keyname;
	/***/
	private int count = 0;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String[] getKeyname() {
		return keyname;
	}

	public void setKeyname(String[] keyname) {
		this.keyname = keyname;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getDescEn() {
		return descEn;
	}

	public void setDescEn(String descEn) {
		this.descEn = descEn;
	}

	public String getQualificationEn() {
		return qualificationEn;
	}

	public void setQualificationEn(String qualificationEn) {
		this.qualificationEn = qualificationEn;
	}

	public String getLanguageEn() {
		return languageEn;
	}

	public void setLanguageEn(String languageEn) {
		this.languageEn = languageEn;
	}


	public String getDescJp() {
		return descJp;
	}

	public void setDescJp(String descJp) {
		this.descJp = descJp;
	}

	public String getNameJp() {
		return nameJp;
	}

	public void setNameJp(String nameJp) {
		this.nameJp = nameJp;
	}

	public String getQualificationJp() {
		return qualificationJp;
	}

	public void setQualificationJp(String qualificationJp) {
		this.qualificationJp = qualificationJp;
	}

	public String getLanguageJp() {
		return languageJp;
	}

	public void setLanguageJp(String languageJp) {
		this.languageJp = languageJp;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getBusinessEn() {
		return businessEn;
	}

	public void setBusinessEn(String businessEn) {
		this.businessEn = businessEn;
	}

	public String getBusinessJp() {
		return businessJp;
	}

	public void setBusinessJp(String businessJp) {
		this.businessJp = businessJp;
	}

	/**查询key*/
	private String key;
	
	
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	/** 专业人员名称 */
	public String getName(){
		return this.name;
	}

	/** 专业人员名称 */
	public void setName(String name){
		this.name = name;
	}
	/** 专业 人员简介 */
	public String getDesc(){
		return this.desc;
	}

	/** 专业 人员简介 */
	public void setDesc(String desc){
		this.desc = desc;
	}
	/** 专业人员 头像 */
	public String getPhoto(){
		return this.photo;
	}

	/** 专业人员 头像 */
	public void setPhoto(String photo){
		this.photo = photo;
	}
	/** 专业人员 邮箱 */
	public String getEmail(){
		return this.email;
	}

	/** 专业人员 邮箱 */
	public void setEmail(String email){
		this.email = email;
	}
	/** 资格类型(数据库字典项多选) */
	public String getQualification(){
		return this.qualification;
	}

	/** 资格类型(数据库字典项多选) */
	public void setQualification(String qualification){
		this.qualification = qualification;
	}
	/** 工作语言(数据库字典项多选) */
	public String getLanguage(){
		return this.language;
	}

	/** 工作语言(数据库字典项多选) */
	public void setLanguage(String language){
		this.language = language;
	}
}
