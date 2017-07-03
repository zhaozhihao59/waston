package com.tocersoft.game.entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tocersoft.base.entity.BaseBusEntity;
import com.tocersoft.member.entity.Member;

/**
 * 赛事报名表
 * 
 * @creator
 * @create-time 2015-01-10 11:38:41
 */
public class GameEnroll extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 赛事ID */
	private String gameItemId;
	/** 报名类型：0-个人；1-团队 */
	private Integer enrollType;
	/** 团队报名ID */
	private String enrollTeamId;
	/** 会员ID */
	private String memberId;
	/** 所属车队 */
	private String teamId;
	/** 参赛号 */
	private String enrollNo;
	/** 参赛运动员姓名 */
	private String memberName;
	/** 参赛运动员手机号码 */
	private String memberPhone;
	/** 参赛运动员邮箱 */
	private String memberEmail;
	/** 参赛运动员国籍（数据字典项） */
	private String memberNation;
	/** 紧急联系人 */
	private String memberLinkName;
	/** 紧急联系人联系电话 */
	private String memberLinkPhone;
	/** 紧急联系人关系（数据字典项） */
	private String memberLinkRelation;
	/** 参赛运动员健康注意事项 */
	private String memberHealth;
	/** 参赛运动员血型（数据字典项） */
	private String memberBloodType;
	/** 是否当地人：0-否，1-是*/
	private Integer isLocal;
	/** 选择套餐*/
	private Integer setMeal;
	/** 选择交通 */
	private Integer vehicle;
	/** 其它交通名称*/
	private String 	elseVehicleName;
	/** 选择住宿*/
	private Integer lodge;
	/** 选择保险*/
	private Integer insure;
	/** 身份证号码 */
	private String idCardNo;
	/** 报名费 */
	private Double enrollFee;
	/** 报名费 - 已付款 */
	private Double enrollFeePaid;
	/** 报名状态：0-未付款，1-已付款，2-已成功，3-已取消 */
	private Integer state;
	/** 备注 */
	private String remark;
	/** 比赛成绩 */
	private String score;
	/** 比赛名次 */
	private Integer rank;
	
	/************* 不持久化 ****************/
	/** 对应会员 */
	private Member member = new Member();
	/** 赛事名称 */
	private String gameName;
	/** 项目名称 */
	private String gameItemName;
	/** 所属车队名称 */
	private String teamName;
	/** 所属车队经理 */
	private Integer teamCaptain;
	/** 参赛运动员性别 0-女 1-男 */
	private Integer sex;
	/** 选择套餐名称*/
	private String setMealName;
	/** 选择交通 名称*/
	private String vehicleName;
	/** 选择住宿名称*/
	private String lodgeName;
	/** 选择保险名称*/
	private String insureName;
	
	
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/** 赛事ID */
	public String getGameItemId(){
		return this.gameItemId;
	}

	/** 赛事ID */
	public void setGameItemId(String gameItemId){
		this.gameItemId = gameItemId;
	}
	/** 报名类型：0-个人；1-团队 */
	public Integer getEnrollType(){
		return this.enrollType;
	}

	/** 报名类型：0-个人；1-团队 */
	public void setEnrollType(Integer enrollType){
		this.enrollType = enrollType;
	}
	/** 会员ID */
	public String getMemberId(){
		return this.memberId;
	}

	/** 会员ID */
	public void setMemberId(String memberId){
		this.memberId = memberId;
	}
	/** 所属车队 */
	public String getTeamId(){
		return this.teamId;
	}

	/** 所属车队 */
	public void setTeamId(String teamId){
		this.teamId = teamId;
	}
	/** 参赛号 */
	public String getEnrollNo(){
		return this.enrollNo;
	}

	/** 参赛号 */
	public void setEnrollNo(String enrollNo){
		this.enrollNo = enrollNo;
	}
	/** 参赛运动员姓名 */
	public String getMemberName(){
		return this.memberName;
	}

	/** 参赛运动员姓名 */
	public void setMemberName(String memberName){
		this.memberName = memberName;
	}
	/** 参赛运动员手机号码 */
	public String getMemberPhone(){
		return this.memberPhone;
	}

	/** 参赛运动员手机号码 */
	public void setMemberPhone(String memberPhone){
		this.memberPhone = memberPhone;
	}
	/** 报名费 */
	public Double getEnrollFee(){
		return this.enrollFee;
	}

	/** 报名费 */
	public void setEnrollFee(Double enrollFee){
		this.enrollFee = enrollFee;
	}
	/** 报名费 - 已付款 */
	public Double getEnrollFeePaid(){
		return this.enrollFeePaid;
	}

	/** 报名费 - 已付款 */
	public void setEnrollFeePaid(Double enrollFeePaid){
		this.enrollFeePaid = enrollFeePaid;
	}
	/** 报名状态：0-未付款，1-已付款，2-已成功，3-已取消 */
	public Integer getState(){
		return this.state;
	}

	/** 报名状态：0-未付款，1-已付款，2-已成功，3-已取消 */
	public void setState(Integer state){
		this.state = state;
	}
	/** 备注 */
	public String getRemark(){
		return this.remark;
	}

	/** 备注 */
	public void setRemark(String remark){
		this.remark = remark;
	}
	/** 比赛成绩 */
	public String getScore(){
		return this.score;
	}

	/** 比赛成绩 */
	public void setScore(String score){
		this.score = score;
	}
	/** 比赛名次 */
	public Integer getRank(){
		return this.rank;
	}

	/** 比赛名次 */
	public void setRank(Integer rank){
		this.rank = rank;
	}


	public String getGameItemName() {
		return gameItemName;
	}

	public void setGameItemName(String gameItemName) {
		this.gameItemName = gameItemName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public Integer getSetMeal() {
		return setMeal;
	}

	public void setSetMeal(Integer setMeal) {
		this.setMeal = setMeal;
	}

	public Integer getVehicle() {
		return vehicle;
	}

	public void setVehicle(Integer vehicle) {
		this.vehicle = vehicle;
	}

	public Integer getLodge() {
		return lodge;
	}

	public void setLodge(Integer lodge) {
		this.lodge = lodge;
	}

	public Integer getInsure() {
		return insure;
	}

	public void setInsure(Integer insure) {
		this.insure = insure;
	}

	public String getSetMealName() {
		return setMealName;
	}

	public void setSetMealName(String setMealName) {
		this.setMealName = setMealName;
	}


	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public String getLodgeName() {
		return lodgeName;
	}

	public void setLodgeName(String lodgeName) {
		this.lodgeName = lodgeName;
	}

	public String getInsureName() {
		return insureName;
	}

	public void setInsureName(String insureName) {
		this.insureName = insureName;
	}

	public String getElseVehicleName() {
		return elseVehicleName;
	}

	public void setElseVehicleName(String elseVehicleName) {
		this.elseVehicleName = elseVehicleName;
	}

	public Integer getTeamCaptain() {
		return teamCaptain;
	}

	public void setTeamCaptain(Integer teamCaptain) {
		this.teamCaptain = teamCaptain;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberNation() {
		return memberNation;
	}

	public void setMemberNation(String memberNation) {
		this.memberNation = memberNation;
	}

	public String getMemberLinkName() {
		return memberLinkName;
	}

	public void setMemberLinkName(String memberLinkName) {
		this.memberLinkName = memberLinkName;
	}

	public String getMemberLinkPhone() {
		return memberLinkPhone;
	}

	public void setMemberLinkPhone(String memberLinkPhone) {
		this.memberLinkPhone = memberLinkPhone;
	}

	public String getMemberLinkRelation() {
		return memberLinkRelation;
	}

	public void setMemberLinkRelation(String memberLinkRelation) {
		this.memberLinkRelation = memberLinkRelation;
	}

	public String getMemberHealth() {
		return memberHealth;
	}

	public void setMemberHealth(String memberHealth) {
		this.memberHealth = memberHealth;
	}

	public String getMemberBloodType() {
		return memberBloodType;
	}

	public void setMemberBloodType(String memberBloodType) {
		this.memberBloodType = memberBloodType;
	}

	public Integer getIsLocal() {
		return isLocal;
	}

	public void setIsLocal(Integer isLocal) {
		this.isLocal = isLocal;
	}

	public String getEnrollTeamId() {
		return enrollTeamId;
	}

	public void setEnrollTeamId(String enrollTeamId) {
		this.enrollTeamId = enrollTeamId;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	
	
}