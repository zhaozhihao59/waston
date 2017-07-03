package com.tocersoft.member.entity;
import java.util.Date;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 团队下会员信息
 * 
 * @creator
 * @create-time 2015-01-10 11:36:05
 */
public class MemTeamMember extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 会员ID */
	private String memberId;
	/** 团队ID */
	private String teamId;
	/** 0-普通队员；1-领队；2-教练；3-联系人 */
	private Integer type;
	
	/** 不持久化 */
	/** 姓名 */
	private String name;
	/** 手机号码 */
	private String mobile;
	/** 电子邮箱 */
	private String email;
	/** 性别：0-女；1-男 */
	private Integer sex;
	/** 身份证*/
	private String idCardNo;
	
	/** 会员 */
	private Member member;
	

	/** 会员ID */
	public String getMemberId(){
		return this.memberId;
	}

	/** 会员ID */
	public void setMemberId(String memberId){
		this.memberId = memberId;
	}
	/** 团队ID */
	public String getTeamId(){
		return this.teamId;
	}

	/** 团队ID */
	public void setTeamId(String teamId){
		this.teamId = teamId;
	}
	/** 0-普通队员；1-领队；2-教练；3-联系人 */
	public Integer getType(){
		return this.type;
	}

	/** 0-普通队员；1-领队；2-教练；3-联系人 */
	public void setType(Integer type){
		this.type = type;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	
	
}