package com.tocersoft.auth.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tocersoft.auth.entity.Right;

@Repository("rightDaoImpl")
public interface IRightDao {

	/**
	 * 获取所有权限数据
	 * @return
	 */
	List<Right> getAllEnableRights();
	
	/**
	 * 根据用户ID获取权限集合
	 */
	List<Right> getAllEnableRightsByUserId(String userId);
	/**
	 * 根据角色ID获取权限集合
	 * @param roleId
	 * @return
	 */
	List<Right> getRoleByRoleId(String roleId);
	
	/**
	 * 根据父级ID查询下级操作权限
	 * @param 	pid		父级ID
	 * @return
	 */
	List<Right> listRightByPid(String pid);
	
	/**
	 * 根据权限ID 查找权限信息
	 * @param 	id
	 * @return
	 */
	Right getRightById(String id);
	
	/**
	 * 获取一级部门最大codeNum
	 * 
	 * @param parentCatId
	 * @return
	 */
	String getMaxCodeNum(String parentId);
	
	
	/**
	 * 添加类别
	 * 
	 * @param map
	 */
	void doAddRightNew(Map<String, Object> map);
	void add(Right right);
	/**
	 * 获取上级部门的codeNum
	 * @param parentId
	 * @return
	 */
	String getCodeNum(String parentId);
	
	/**
	 * 修改 部门
	 * @param map
	 */
	void doUpdateRight(Map<String, Object> map);
	
	/**
	 * 根据id删除
	 * @param nodeId
	 */
	int delRightById(String nodeId);
	
	/**
	 * 根据部门ID更新部门是否为父节点
	 * @param id		部门ID
	 * @param isParent	0-非父节点 1-是父节点
	 * @param isDelete	0-不可删除 1-可删除
	 */
	void updateIsParentDeleteById(@Param("id")String id, @Param("isParent")Integer isParent, @Param("isDelete")Integer isDelete);
	
	/**
	 * 修改
	 * @param right
	 */
	public void doUpdateRight(Right right);
}
