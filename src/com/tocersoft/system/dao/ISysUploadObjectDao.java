package com.tocersoft.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.system.entity.SysUploadObject;
import com.tocersoft.system.dto.SysUploadObjectCondition;

@Repository("sysUploadObjectDaoImpl")
public interface ISysUploadObjectDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<SysUploadObject> listSysUploadObjectByPage(RowBounds bounds,@Param("condition") SysUploadObjectCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listSysUploadObjectByPageCount(@Param("condition") SysUploadObjectCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 文件对应的关联对象类型：比如与哪张表关联，用于关联表
	 */
	SysUploadObject getSysUploadObjectById(@Param("id") String id);
	
	/**
	 * 根据类型查询
	 * @param id 主键
	 * @return 文件对应的关联对象类型：比如与哪张表关联，用于关联表
	 */
	SysUploadObject getSysUploadObjectByName(@Param("name") String name);

	/**
	 * 新增
	 * @param item 文件对应的关联对象类型：比如与哪张表关联，用于关联表
	 */
	void add(SysUploadObject item);

	/**
	 * 修改
	 * @param item 文件对应的关联对象类型：比如与哪张表关联，用于关联表
	 */
	void update(SysUploadObject item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

}

