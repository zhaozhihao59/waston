package com.tocersoft.member.entity;
import java.util.Date;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 会员信息
 * 
 * @creator
 * @create-time 2014-03-22 22:44:07
 */
public class Member extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 创建人 */
	private String createBy;
	/** 创建时间 */
	private Date createDate;
	/** 更新人 */
	private String updateBy;
	/** 更新时间 */
	private Date updateDate;
	/** 删除标记：0-未删除，1-已删除 */
	private Integer deleteFlag;
	/** 姓名 */
	private String name;
	/** 手机号码 */
	private String mobile;
	/** 证件类型 */
	private String cardType;
	/** 证件号码 */
	private String idCardNo;
	/** 电子邮箱 */
	private String email;
	/** 邮箱状态是否激活（1未激活2已激活） */
	private Integer emailactive;
	/** 性别：0-女；1-男 */
	private Integer sex;

	/** 参赛运动员国籍（数据字典项） */
	private String nation;
	/** 紧急联系人 */
	private String linkName;
	/** 紧急联系人联系电话 */
	private String linkPhone;
	/** 紧急联系人关系（数据字典项） */
	private String linkRelation;
	/** 参赛运动员健康注意事项 */
	private String health;
	/** 参赛运动员血型（数据字典项） */
	private String bloodType;
	
	
	/** 会员帐号 */
	private String account;
	/** 登录密码 */
	private String password;
	/** 登录新密码 */
	private String newPassword;
	/** QQ */
	private String qq;
	/** 微信号 */
	private String wechat;
	/** 地址 */
	private String address;
	/** 行业 */
	private String industry;
	/** 个人简介 */
	private String brief;
	/** 描述 */
	private String desc;
	/** 团队标签ID字符串，以逗号,隔开，不持久化 */
	private String tagIds;
	/** 会员等级 */
	private MemLevel memLevel;
	/** 省 */
	private String province;
	/** 省ID */
	private String provinceId;
	/** 市 */
	private String city;
	/** 市ID */
	private String cityId;
	/** 省市*/
	private String provinceCity;
	/** 区ID */
	private String districtId;
	/** 区 */
	private String district;
	/** 邮编 */
	private String postCode;
	/** 团队中的身份（是否为队长） 不持久化*/
	private Integer teamCaptain;
	public MemLevel getMemLevel() {
		return memLevel;
	}

	public void setMemLevel(MemLevel memLevel) {
		this.memLevel = memLevel;
	}

	public String getTagIds() {
		return tagIds;
	}

	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}

	/** 创建人 */
	public String getCreateBy(){
		return this.createBy;
	}

	/** 创建人 */
	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}
	/** 创建时间 */
	public Date getCreateDate(){
		return this.createDate;
	}

	/** 创建时间 */
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	/** 更新人 */
	public String getUpdateBy(){
		return this.updateBy;
	}

	/** 更新人 */
	public void setUpdateBy(String updateBy){
		this.updateBy = updateBy;
	}
	/** 更新时间 */
	public Date getUpdateDate(){
		return this.updateDate;
	}

	/** 更新时间 */
	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}
	/** 删除标记：0-未删除，1-已删除 */
	public Integer getDeleteFlag(){
		return this.deleteFlag;
	}

	/** 删除标记：0-未删除，1-已删除 */
	public void setDeleteFlag(Integer deleteFlag){
		this.deleteFlag = deleteFlag;
	}
	/** 姓名 */
	public String getName(){
		return this.name;
	}

	/** 姓名 */
	public void setName(String name){
		this.name = name;
	}
	/** 手机号码 */
	public String getMobile(){
		return this.mobile;
	}

	/** 手机号码 */
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	/** 电子邮箱 */
	public String getEmail(){
		return this.email;
	}

	/** 电子邮箱 */
	public void setEmail(String email){
		this.email = email;
	}
	/** 邮箱状态是否激活（1未激活2已激活） */
	public Integer getEmailactive(){
		return this.emailactive;
	}

	/** 邮箱状态是否激活（1未激活2已激活） */
	public void setEmailactive(Integer emailactive){
		this.emailactive = emailactive;
	}
	/** 会员帐号 */
	public String getAccount(){
		return this.account;
	}

	/** 会员帐号 */
	public void setAccount(String account){
		this.account = account;
	}
	/** 登录密码 */
	public String getPassword(){
		return this.password;
	}

	/** 登录密码 */
	public void setPassword(String password){
		this.password = password;
	}
	/** QQ */
	public String getQq(){
		return this.qq;
	}

	/** QQ */
	public void setQq(String qq){
		this.qq = qq;
	}
	/** 微信号 */
	public String getWechat(){
		return this.wechat;
	}

	/** 微信号 */
	public void setWechat(String wechat){
		this.wechat = wechat;
	}
	/** 地址 */
	public String getAddress(){
		return this.address;
	}

	/** 地址 */
	public void setAddress(String address){
		this.address = address;
	}
	/** 行业 */
	public String getIndustry(){
		return this.industry;
	}

	/** 行业 */
	public void setIndustry(String industry){
		this.industry = industry;
	}
	/** 性别：0-女；1-男 */
	public Integer getSex(){
		return this.sex;
	}

	/** 性别：0-女；1-男 */
	public void setSex(Integer sex){
		this.sex = sex;
	}
	/** 个人简介 */
	public String getBrief(){
		return this.brief;
	}

	/** 个人简介 */
	public void setBrief(String brief){
		this.brief = brief;
	}
	/** 描述 */
	public String getDesc(){
		return this.desc;
	}

	/** 描述 */
	public void setDesc(String desc){
		this.desc = desc;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getProvinceCity() {
		return provinceCity;
	}

	public void setProvinceCity(String provinceCity) {
		this.provinceCity = provinceCity;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public Integer getTeamCaptain() {
		return teamCaptain;
	}

	public void setTeamCaptain(Integer teamCaptain) {
		this.teamCaptain = teamCaptain;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public String getLinkRelation() {
		return linkRelation;
	}

	public void setLinkRelation(String linkRelation) {
		this.linkRelation = linkRelation;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	
}