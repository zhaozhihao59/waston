package com.tocersoft.auth.dto;

import java.util.Date;

import com.tocersoft.base.dto.BaseCondition;
import com.tocersoft.base.utils.CommonUtil;


public class UserCondition extends BaseCondition {

	private static final long serialVersionUID = 1L;
	/** 角色编号 */
	private String roleId;
	/** 管理员 */
	private String admin;
	/** 员工工号 */
	private String staffId;
	/** 真实姓名 */
	private String name;
	/** 账号 */
	private String account;
	/** 手机 */
	private String mobile;
	/** 邮箱 */
	private String email;
	/** 是否启用 1:启用 2：禁用 */
	private Integer state;
	/** 过滤部分会员 1.过滤*/
	private Integer filt;
	/** 禁用计时 （禁用一个月后不加入分页查看） */
	private Date stateDate;
	/** 部门ID*/
	private String departId;
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public UserCondition() {
		super();
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = CommonUtil.escapeSpecialSign(staffId);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public Integer getFilt() {
		return filt;
	}

	public void setFilt(Integer filt) {
		this.filt = filt;
	}

	public Date getStateDate() {
		return stateDate;
	}

	public void setStateDate(Date stateDate) {
		this.stateDate = stateDate;
	}

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}


}
