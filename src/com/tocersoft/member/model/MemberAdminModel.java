package com.tocersoft.member.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.member.dto.MemberCondition;
import com.tocersoft.member.entity.Member;

public class MemberAdminModel extends BaseModel<Member> {
	
	/** */
	private static final long serialVersionUID = 1L;
	
	/** 会员实体 */
	private Member item = new Member();
	/** 会员查询条件 */
	private MemberCondition condition = new MemberCondition();

	/** 注册时验证帐号是否唯一 */
	private String account;
	
	/** 用于存放会员信息 */
	private Member member;
	
	/** 验证码 */
	private String verCode;
	
	/** 请求带的随机数 */
	private String m;
	
	/** 表明是从登录过来的 0-从登录过来 */
	private Integer fromLogin;
	
	/** 记住密码 */
	private Integer isRmbPwd;
	
	/** 找回密码时输入的手机号码 */
	private String phone;

	public Integer getIsRmbPwd() {
		return isRmbPwd;
	}

	public void setIsRmbPwd(Integer isRmbPwd) {
		this.isRmbPwd = isRmbPwd;
	}

	public String getM() {
		return m;
	}

	public void setM(String m) {
		this.m = m;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getVerCode() {
		return verCode;
	}

	public void setVerCode(String verCode) {
		this.verCode = verCode;
	}

	public Integer getFromLogin() {
		return fromLogin;
	}

	public void setFromLogin(Integer fromLogin) {
		this.fromLogin = fromLogin;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Member getItem() {
		return item;
	}

	public void setItem(Member item) {
		this.item = item;
	}

	public MemberCondition getCondition() {
		return condition;
	}

	public void setCondition(MemberCondition condition) {
		this.condition = condition;
	}

}
