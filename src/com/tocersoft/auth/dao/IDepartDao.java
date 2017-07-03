package com.tocersoft.auth.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.auth.entity.Depart;
import com.tocersoft.auth.dto.DepartCondition;

@Repository("departDaoImpl")
public interface IDepartDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<Depart> listDepartByPage(RowBounds bounds,@Param("condition") DepartCondition condition);

	/**
	 * 根据上级ID查询部门列表
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<Depart> listDepartByPid(@Param("value") String value);
	
	/**
	 * 查询全部部门
	 * @param value
	 * @return
	 */
	List<Depart> listAllDepart();
	

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listDepartByPageCount(@Param("condition") DepartCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 部门表
	 */
	Depart getDepartById(@Param("id") String id);
	
	/**
	 * 根据name查询
	 * @param id 主键
	 * @return 部门表
	 */
	List<Depart> getDepartByName(@Param("dname") String dname);

	/**
	 * 新增
	 * @param item 部门表
	 */
	void add(Depart item);

	/**
	 * 修改
	 * @param item 部门表
	 */
	void update(Depart item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);
	
	/**
	 * 添加类别
	 * 
	 * @param map
	 */
	void doAddDepartNew(Map<String, Object> map);
	
	/**
	 * 获取一级部门最大codeNum
	 * 
	 * @param parentCatId
	 * @return
	 */
	String getMaxCodeNum(String parentId);
	/**
	 * 获取上级部门的codeNum
	 * @param parentId
	 * @return
	 */
	String getCodeNum(String parentId);
	
	/**
	 * 根据部门ID更新部门是否为父节点
	 * @param id		部门ID
	 * @param isParent	0-非父节点 1-是父节点
	 * @param isDelete	0-不可删除 1-可删除
	 */
	void updateIsParentDeleteById(@Param("id")String id, @Param("isParent")Integer isParent, @Param("isDelete")Integer isDelete);
	
	/**
	 * 修改 部门
	 * @param map
	 */
	void doUpdateDepart(Map<String, Object> map);
	
	/**
	 * 根据id删除部门
	 * @param nodeId
	 */
	int delDepartById(String nodeId);
	
	/**
	 * 批量设置用户主管设置
	 * @param selIds
	 */
	public void doManagerContr(@Param("manageId")String manageId,@Param("sel")String sel);
	
	/**
	 * 批量取消用户主管设置
	 * @param selIds
	 */
	public void doManagerNoContr(@Param("manageId")String manageId,@Param("sel")String sel);
 
}

