package com.tocersoft.auth.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 用户 实体
 * 
 * @creator zhangqiang
 * @create-time Nov 7, 2011 4:04:45 PM
 */
public class User extends BaseBusEntity implements UserDetails{
	private static final long serialVersionUID = -8346921787927336274L;

	/** 员工工号 */
	private String staffId;
	/** 真实姓名 */
	private String name;
	/** 英文名*/
	private String enName;
	/** 性别 1:男，0女 */
	private Integer sex;
	/** 生日 */
	private Date birthday;
	/** 固定电话 */
	private String tel;
	/** 分机*/
	private String ext;
	/** 手机 */
	private String mobile;
	/** 邮箱 */
	private String email;
	/** 是否启用 1:启用 2：禁用 */
	private Integer state = 1;
	/** 其他联系方式 */
	private String contact;
	/** 在线状态 1:离线 2：在线 */
	private Integer onlineState;
	/** 账号 */
	private String account;
	/** 密码 */
	private String password;
	/** 是否修改过初始密码 0-初始密码 1-已修改 */
	private Integer isChangePwd;
	/** 是否主管 */
	private Integer isManager;
	/** 所属角色 */
	private List<Role> roles = new ArrayList<Role>();
	
	private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();// 授权角色信息
	/** 禁用计时 （禁用一个月后不加入分页查看） */
	private Date stateDate;
	/** 部门ID */
	private String departId;
	/** 部门名称 不做持久化 */
	private String departName;
	/** 关联部门*/
	private Depart depart = new Depart();
	
	public User() {
		super();
	}
	
	public Date getStateDate() {
		return stateDate;
	}

	public void setStateDate(Date stateDate) {
		this.stateDate = stateDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Integer getOnlineState() {
		return onlineState;
	}

	public void setOnlineState(Integer onlineState) {
		this.onlineState = onlineState;
	}
	@Override
	public List<GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public String getUsername() {
		return account;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return state == 1;
	}

	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public Integer getIsChangePwd() {
		return isChangePwd;
	}

	public void setIsChangePwd(Integer isChangePwd) {
		this.isChangePwd = isChangePwd;
	}

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	public Integer getIsManager() {
		return isManager;
	}

	public void setIsManager(Integer isManager) {
		this.isManager = isManager;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public Depart getDepart() {
		return depart;
	}

	public void setDepart(Depart depart) {
		this.depart = depart;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}
	
}
