package com.tocersoft.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import com.tocersoft.auth.entity.Role;

/**
 * 角色DAO
 * @author lenovo
 *
 */
@Repository("roleDaoImpl")
public interface IRoleDao {
	/**
	 * 查询所有角色
	 * @param roleIds
	 * @return
	 */
	List<Role> getAllEnableRoles();
	/**
	 * 查询所有不包含的角色
	 * @param list
	 * @return
	 */
	List<Role> queryForList(List<String> list);
	/**
	 * 查询所有包含的角色
	 * @param list
	 * @return
	 */
	List<Role> queryForInList(List<String> list);
	/**
	 * 根据用户ID查询所有角色
	 * @param id
	 * @return
	 */
	List<Role> getRoleByUserId(String id);
	/**
	 * 根据用户的ID 与 角色ID 获得该记录
	 * @param id
	 * @return
	 */
	List<Role> getbooleanByUserIdAndRoleId(@Param("userId")String userId,@Param("roleId")String roleId);
	/**
	 * 分页查询角色
	 * @param row
	 * @return
	 */
	List<Role> getRoleListByPage(RowBounds row);
	/**
	 * 查询总行数
	 * @return
	 */
	int getRoleListByPageCount();
	
	Role getRoleAnotherByName(@Param("name")String name, @Param("id")String id);
	
	Role getRoleByName(String name);
	/**
	 * 保存
	 * @param item
	 */
	void addRole(Role item);
	/**
	 * 修改
	 * @param item
	 */
	void updateRole(Role item);
	/**
	 * 删除
	 * @param strings
	 */
	void delRoleById(String id);
	/**
	 * 添加权限
	 * @param roleId
	 * @param rightIds
	 */
	void addRoleRights(@Param("roleId") String roleId,@Param("rightIds") String[] rightIds);
	/**
	 * 根据角色ID删除角色权限
	 * @param roleId
	 * @return
	 */
	void delRoleRightByRoleId(String roleId);
	/**
	 * 根据ID获得角色信息
	 * @param id
	 * @return
	 */
	Role getById(String id);
}
