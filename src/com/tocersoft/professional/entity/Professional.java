package com.tocersoft.professional.entity;
import java.util.Date;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 专业人员表
 * 
 * @creator
 * @create-time 2015-04-23 16:57:45
 */
public class Professional extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;
	/** 专业人员名称 */
	private String name;
	private String nameEn;
	private String nameJp;
	/** 专业 人员简介 */
	private String desc;
	private String descEn;
	private String descJp;
	/** 专业人员 头像 */
	private String photo;
	/** 专业人员 邮箱 */
	private String email;
	/** 资格类型(数据库字典项多选) */
	private String qualification;
	private String qualificationEn;
	private String qualificationJp;
	/**资格类型名称*/
	private String qualificationName;
	/** 工作语言(数据库字典项多选) */
	private String language;
	private String languageEn;
	private String languageJp;
	/** 工作语言名称 */
	private String languageName;
	/** 专业人员类型0合伙人11高级顾问 */
	private String type;
	/** 专业人员类型名称*/
	private String typeName;
	/**业务*/
	private String business;
	private String businessEn;
	private String businessJp;
	/** 排序 数值越小越靠前*/
	private Integer sort;
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getNameJp() {
		return nameJp;
	}

	public void setNameJp(String nameJp) {
		this.nameJp = nameJp;
	}

	public String getDescEn() {
		return descEn;
	}

	public void setDescEn(String descEn) {
		this.descEn = descEn;
	}

	public String getDescJp() {
		return descJp;
	}

	public void setDescJp(String descJp) {
		this.descJp = descJp;
	}

	public String getQualificationEn() {
		return qualificationEn;
	}

	public void setQualificationEn(String qualificationEn) {
		this.qualificationEn = qualificationEn;
	}

	public String getQualificationJp() {
		return qualificationJp;
	}

	public void setQualificationJp(String qualificationJp) {
		this.qualificationJp = qualificationJp;
	}

	public String getLanguageEn() {
		return languageEn;
	}

	public void setLanguageEn(String languageEn) {
		this.languageEn = languageEn;
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
	/** 专业人员类型0合伙人11高级顾问 */
	public String getType(){
		return this.type;
	}

	/** 专业人员类型0合伙人11高级顾问 */
	public void setType(String type){
		this.type = type;
	}

	public String getQualificationName() {
		return qualificationName;
	}

	public void setQualificationName(String qualificationName) {
		this.qualificationName = qualificationName;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
}