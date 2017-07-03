package com.tocersoft.auth.entity;

import java.util.ArrayList;
import java.util.List;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 权限
 * 
 * @creator zhangqiang
 * @create-time May 25, 2011 9:21:37 AM
 */
public class Right extends BaseBusEntity {
	private static final long serialVersionUID = 1L;
	/** 名称 */
	private String name;
	/** 上级编号 */
	private String parentId;
	/** 位置 1：主菜单 2：一级子菜单 3：二级子菜单 4：工具栏 */
	private Integer location;
	/** 提示信息 */
	private String tip;
	/** 地址 */
	private String url;
	/** 状态 1：正常 2：禁用 */
	private Integer state;
	/** 图标路径 */
	private String iconPath;
	/** 关联多个角色 */
	private List<Role> roles = new ArrayList<Role>();
	/** 权限码 */
	private String pcode;
	/** 排序码 */
	private Integer sort;
	/** 下级子权限 */
	private List<Right> childRights = new ArrayList<Right>();

	public Right() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getLocation() {
		return location;
	}

	public void setLocation(Integer location) {
		this.location = location;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public List<Right> getChildRights() {
		return childRights;
	}

	public void setChildRights(List<Right> childRights) {
		this.childRights = childRights;
	}

}
