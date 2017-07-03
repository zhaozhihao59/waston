package com.tocersoft.auth.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.auth.dao.IRightDao;
import com.tocersoft.auth.dao.IRoleDao;
import com.tocersoft.auth.dao.IUserDao;
import com.tocersoft.auth.dto.RoleVo;
import com.tocersoft.auth.entity.Right;
import com.tocersoft.auth.entity.Role;
import com.tocersoft.auth.entity.User;
import com.tocersoft.auth.service.IRoleService;
import com.tocersoft.base.page.PageResult;
@Service
@Transactional
public class RoleServiceImpl implements IRoleService{
	
	@Resource
	private IRoleDao roleDao;
	@Resource
	private IUserDao userDao;
	@Resource
	private IRightDao permissionDao;
	@Override
	public List<Role> getAllEnableRolesByIds(String roleIds) {
		if(StringUtils.isBlank(roleIds)){
			return roleDao.getAllEnableRoles();
		}
		String[] roles = roleIds.split(",");
		List<String> list = Arrays.asList(roles);
		List<Role> result = roleDao.queryForInList(list);
		return result;
	}

	@Override
	public List<Role> getAllEnableRoles(String roleIds) {
		if(StringUtils.isBlank(roleIds)){
			return roleDao.getAllEnableRoles();
		}
		String[] roles = roleIds.split(",");
		List<String> list = Arrays.asList(roles);
		List<Role> result = roleDao.queryForList(list);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoleByUserId(String id) {
		List<Role> list = roleDao.getRoleByUserId(id);
		if(null != list && list.size()>0){
			 return list;
		}
		return Collections.EMPTY_LIST;
	}
	
	/**
	 * 根据用户的ID 与 角色ID 判断该角色是否具有该权限
	 * @param userId
	 * @return
	 */
	@Override
	public boolean getbooleanByUserIdAndRoleId(String userId , String roleId) {
		boolean fol = false;
		List<Role> list = roleDao.getbooleanByUserIdAndRoleId(userId, roleId);
		if(list!=null&& list.size()>0){
			fol = true;
		}
		return fol;
	}
	
	@Override
	public void getRoleListByPage(PageResult<RoleVo> pageResult) {
		RowBounds row = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		//查询结果集
		List<Role> roleList = roleDao.getRoleListByPage(row);
		List<RoleVo> roleVoList = new ArrayList<RoleVo>(roleList.size());
		pageResult.setResult(roleVoList);
		
		//查询总行数
		int rows  = roleDao.getRoleListByPageCount();
		pageResult.setRows(rows);
		//封装
		for (int i = 0; i < roleList.size(); i++) {
			Role role = roleList.get(i);
			RoleVo vo = new RoleVo();
			StringBuffer users = new StringBuffer();
			//拷贝属性
			BeanUtils.copyProperties(role, vo,new String []{"permissions","users"});
			List<User> userList = userDao.getUserByRoleId(role.getId());
			for(int j=0; j < userList.size();j++){
				if(j>0){
					users.append(",");
				}
				User user = userList.get(j);
				users.append(user.getName());
			}
			vo.setUsers(users.toString());
			roleVoList.add(vo);
		}
	}
	@Override
	public boolean judgeNameExist(String name) {
		Role role = roleDao.getRoleByName(name);
		return null != role;
	}
	@Override
	public boolean judgeAnotherNameExist(String name, String id) {
		Role role = roleDao.getRoleAnotherByName(name,id);
		return null != role;
	}
	@Override
	public void saveRole(Role item) {
		if(StringUtils.isBlank(item.getId())){
			item.setRoleType(2);	//用户类型
			roleDao.addRole(item);
		}else{
			Role old = new Role();
			old.setId(item.getId());
			old.setName(item.getName());
			old.setRemark(item.getRemark());
			roleDao.updateRole(old);
		}
	}
	@Override
	public void delRoleById(String id) {
		//删除角色，假删
		if(StringUtils.isNotBlank(id)){
			roleDao.delRoleById(id);
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Right> getRolePermissionsById(String roleId) {
		List<Right> permissionList = permissionDao.getRoleByRoleId(roleId);
		if(null != permissionList && permissionList.size() > 0){
			return permissionList;
		}
		return Collections.EMPTY_LIST;
	}
	@Override
	public void saveRolePermissions(String roleId, String rightIds) {
		String[] ids = rightIds.split(",");
		roleDao.delRoleRightByRoleId(roleId);  //删除该角色下的权限
		roleDao.addRoleRights(roleId, ids);  //添加权限
	}

	@Override
	public Role getById(String id) {
		if(StringUtils.isBlank(id)){
			return null;
		}
		return roleDao.getById(id);
	}
}
