package com.tocersoft.auth.service;

import java.util.List;

import com.tocersoft.auth.dto.UserCondition;
import com.tocersoft.auth.dto.UserVo;
import com.tocersoft.auth.entity.User;
import com.tocersoft.base.page.PageResult;


public interface IUserService {
	/**
	 * 保存用户
	 * @param item
	 * @param roleIds
	 */
	public void saveUser(User item, String roleIds);
	
	/**
	 * 更新用户（一起更新角色）
	 * @param item
	 * @param roleIds
	 */
	public void updateUserDetail(User item, String roleIds);
	
	/**
	 * 更新银行帐号
	 * @param item
	 * @param roleIds
	 */
	public void updateBankAccount(String bankAccount,String id);
	
	/**
	 * 确认银行帐号状态
	 * @param item
	 * @param roleIds
	 */
	public void updateBankStatus(String id);
	
	
	/**
	 * 验证用户名是否存在
	 * @param item
	 * @param roleIds
	 */
	public boolean alreadyExists(String account );
	
	/**
	 * 获得所有的员工
	 * @param item
	 * @param roleIds
	 */
	public List<User> getAllUser(UserCondition condition);
	
	/**
	 * 获取员工个数
	 * @return
	 */
	public int getUserCount();
	
	/**
	 * 获得所有的员工包括管理员
	 * @param item
	 * @param roleIds
	 */
	public List<User> getAllUserAndAdmin();
	
	/**
	 * 更新用户
	 * @param item
	 * 
	 */
	public void updateUser(User item);
	/**
	 * 判断账号是否存在
	 * @param account
	 * @return
	 */
	public boolean judgeAccountExist(String account);
	
	public boolean judgeAnotherAccountExist(String account, String id);
	/**
	 * 分页查询用户
	 * @param pageResult
	 * @param condition
	 */
	public void getUserByPage(PageResult<UserVo> pageResult,UserCondition condition);
	/**
	 * 修改用户状态
	 * @param item
	 */
	public void updateState(User item);
	/**
	 * 根据ID查询用户详细
	 * @param id
	 * @return
	 */
	public User getUserDetailById(String id);
	
	
	/**
	 * 根据部门ID查询该部门主管用户详细
	 * @param id
	 * @return
	 */
	public User getUserDetailByDepartId(String departId);
	
	/**
	 * 修改密码
	 * @param item
	 */
	public void resetPwd(User item);
	/**
	 * 删除用户
	 * @param id
	 */
	public void delUserById(String id);
	/**
	 * 批量删除用户
	 * @param selIds
	 */
	public void doBatchDelUser(String selIds);
	/**
	 * 批量 设置用户主管
	 * @param selIds
	 */
	public void doManagerContr(String manageId,String selIds);
	/**
	 * 批量 取消用户主管
	 * @param selIds
	 */
	public void doManagerNoContr(String manageId,String selIds);
	
	/**
	 * 根据名字用于模糊查询
	 * @param account
	 * @return
	 */
	public List<User> listUserNo(String name);

	/**
	 * 根据名字部门模糊查询
	 * @param name,departId
	 * @return
	 */
	public List<User> listUserByNameAndProductId(String name,String productId);
	
	
	/**
	 * 批量导入用户
	 */
	public void doImport(String tempPath);
}
