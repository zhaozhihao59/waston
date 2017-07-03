package com.tocersoft.auth.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.auth.dto.UserCondition;
import com.tocersoft.auth.entity.User;
import com.tocersoft.base.dao.BaseDao;

@Repository("userDaoImpl")
public interface IUserDao extends BaseDao<User, UserCondition>{

	/**
	 * 根据账号获取用户
	 * @param account
	 * @return
	 */
	public User getUserByAccount(String account);
	
	/**
	 * 根据名字用于模糊查询
	 * @param name
	 * @return
	 */
	public List<User> listUserNo(@Param("name") String name);
	
	/**
	 * 根据名字部门用于模糊查询
	 * @param name,departId
	 * @return
	 */
	public List<User> listUserByNameAndProductId(@Param("name")String name,@Param("productId")String productId);
	
	/**
	 *跟据部门ID查询用户
	 * @param account
	 * @return
	 */
	public List<User> getUserByDepartId(String DepartId);
	
	/**
	 * 新增用户
	 * @param item
	 */
	@Override
	public void add(User item);
	/**
	 * 根据ID查询用户详细信息
	 * @param id
	 * @return
	 */
	public User getUserDetailById(String id);
	/**
	 * 根据部门ID查询该部门主管用户详细
	 * @param id
	 * @return
	 */
	public User getUserDetailByDepartId(@Param("departId")String departId);
	
	
	public User getUserAnotherByAccount(String account, String id);
	/**
	 * 新增UserRole
	 * @param map
	 */
	public void addUserRole(Map<String, String> map);
	
	/**
	 * 根据帐号获得用户
	 * @param item
	 * @param roleIds
	 */
	public User getUserDetailByAccount(String account );
	
	/**
	 * 获取员工个数
	 * @return
	 */
	public int getUserCount();
	
	/**
	 * 分页查询
	 * @param bounds
	 * @param map
	 * @return
	 */
	public List<User> getUserListByPage(RowBounds bounds, @Param("condition") UserCondition condition);
	
	/**
	 * 获取总行数
	 * @param map
	 * @return
	 */
	public int getUserByListPageCount(@Param("condition") UserCondition condition);
	/**
	 * 修改用户启动/禁用
	 * @param map
	 */
	public void updateState(User item);
	/**
	 * 修改密码
	 * @param item
	 * @return
	 */
	public void resetPwd(User item);
	
	/**
	 * 修改银行帐号
	 * @param item
	 */
	void updateBankAccount(@Param("bankAccount")String bankAccount,@Param("id")String id);
	
	/**
	 * 确认银行账号状态
	 * @param item
	 */
	void updateBankStatus(String id);
	/**
	 * 删除用户
	 * @param id
	 */
	public void updateUser(String id);
	/**
	 * 批量删除用户
	 * @param selIds
	 */
	public void doBatchDelUser(String[] sel);

	/**
	 * 删除用户角色
	 * @param sel
	 */
	public void doBatchDelUserRole(String[] sel);
	/**
	 * 修改用户
	 * @param old
	 */
	@Override
	public void update(User old);
	/**
	 * 根据角色编号获得对应的用户
	 * @param id
	 * @return
	 */
	public List<User> getUserByRoleId(String id);
	/**
	 */
	public List<User> getUserListByPage(RowBounds bounds, String id);
	/**
	 * 先删除
	 * @param id
	 * @return
	 */
	public void doBatchDelUserRoles(String id);
	
	/**
	 * 根据ID删除用户
	 * @param id
	 */
	void deleteById(@Param("id")String id);
	
	/**
	 * 获得全部的员工
	 */
	public List<User> getAllUser(@Param("condition") UserCondition condition);
	/**
	 * 获得全部的员工包括管理员
	 */
	public List<User> getAllUserAndAdmin();
	
	/**
	 * 批量设置用户主管设置
	 * @param selIds
	 */
	public void doManagerContr(@Param("sel")String selIds);
	
	/**
	 * 批量设置用户取消主管设置
	 * @param selIds
	 */
	public void doManagerNoContr(@Param("sel")String selIds);
}
