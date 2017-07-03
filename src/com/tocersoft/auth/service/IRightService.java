package com.tocersoft.auth.service;

import java.util.List;
import java.util.Map;

import com.tocersoft.auth.entity.Right;

/**
 * 后台菜单service类
 * @creator     zhangqiang
 * @email       zhangqiang@tocersoft.com
 * @company     www.tocersoft.com
 * @create-time Jul 24, 2012   9:20:03 AM
 * @version 0.1
 */
public interface IRightService {

	/**
	 * 获取全部权限集合
	 * @return
	 */
	List<Right> getAllEnablePermissions();
	
	/**
	 * 根据父级ID查询下级操作权限
	 * @param 	pid		父级ID
	 * @return
	 */
	List<Right> listRightByPid(String pid, Integer level);

	/**
	 * 根据用户ID获取权限集合
	 * @param userId
	 * @return
	 */
	List<Right> getAllEnablePermissionsByUserId(String userId);
	/**
	 * 获取所有可用权限并组装
	 * @return
	 */
	Map<Right, List<Map<Right, List<Map<Right, List<Right>>>>>> loadAllEnablePermissions(String id);

	/**
	 * 添加 权限
	 * @param parentId
	 * @param name
	 * @param sort
	 */
	void doAddRight(String parentId, String name, Integer sort,String tip,String fuUrl,Integer location);
	
	/**
	 * 根据给定的code值生成下一个code，规则是
	 * 
	 * <pre>
	 * 0001 生成 0002
	 * 0000-2222 生成 0000-2223
	 * 0001-9999-9998 生成 0001-9999-9999
	 * 第一段取值：0-999
	 * 其他段取值：0-9999
	 * </pre>
	 * 
	 * @param code
	 * @return
	 */
	String createCodeNum(String code);
	
	/**
	 * 新增生成编号
	 * 
	 * @return
	 */
	String createCodeNumByAdd(String parentId) ;
	
	/**
	 * 添加类别属性
	 */
	void doAddRightNew(String parentId, String name, int sort,String codeNum, String tip,String fuUrl,Integer location);
	
	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 部门表
	 */
	Right getRightById(String id);
	
	/**
	 * 修改  
	 * @param map
	 */
	void doUpdateRight(String nodeId, String name, Integer sort,String tip,String url,Integer location );
	
	/**
	 * 修改  
	 * @param map
	 */
	void doUpdateRight(Right right);
	
	/**
	 * 删除
	 * @param channel	需要删除的实体，主要用了id和parentId的属性
	 * @return
	 */
	int delRight(Right right);
	
	/**
	 * 检查栏目是否能被删除
	 * 1、该栏目下是否有子栏目，若有，则不能删除；
	 * 2、该栏目下是否有文章，若有，则不能删除；
	 * @param 	id	部门ID  pid 父节点ID
	 * @return	0 - 可删除 1 - 不可删除
	 */
	Integer checkDepartIsDeleteById(String id) ;
	
	/**
	 * 添加类别
	 * @param right
	 */
	void add(Right right);
}
