package com.tocersoft.auth.service;

import java.util.List;

import com.tocersoft.auth.dto.RoleVo;
import com.tocersoft.auth.entity.Right;
import com.tocersoft.auth.entity.Role;
import com.tocersoft.base.page.PageResult;

public interface IRoleService {

	List<Role> getAllEnableRolesByIds(String roleIds);

	List<Role> getAllEnableRoles(String roleIds);
	/**
	 * 根据用户编号获得用户的角色集合
	 * 
	 * @param userId
	 * @return
	 */
	List<Role> getRoleByUserId(String id);
	/**
	 * 根据用户的ID 与 角色ID 判断该角色是否具有该权限
	 * @param userId
	 * @return
	 */
	boolean getbooleanByUserIdAndRoleId(String userId , String roleId);
	/**
	 * 分页查询角色
	 * @param pageResult
	 */
	void getRoleListByPage(PageResult<RoleVo> pageResult);


	boolean judgeAnotherNameExist(String name, String id);

	boolean judgeNameExist(String name);
	/**
	 * 保存
	 * @param item
	 */
	void saveRole(Role item);
	/**
	 * 删除用户角色
	 * @param id
	 */
	void delRoleById(String id);
	/**
	 * 根据角色编号获取角色权限数据
	 * @param roleId
	 * @return
	 */
	List<Right> getRolePermissionsById(String roleId);
	/**
	 * 保存角色权限数据
	 * @param roleId
	 * @param permissionIds
	 */
	void saveRolePermissions(String roleId, String permissionIds);
	/**
	 * 根据ID获得角色信息
	 * @param id
	 * @return
	 */
	Role getById(String id);
}
