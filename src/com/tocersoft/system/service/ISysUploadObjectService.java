package com.tocersoft.system.service;

import java.util.List;

import com.tocersoft.system.entity.SysUploadObject;
import com.tocersoft.base.page.PageResult;

import com.tocersoft.system.dto.SysUploadObjectCondition;

public interface ISysUploadObjectService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listSysUploadObjectByPage(PageResult<SysUploadObject> pageResult,SysUploadObjectCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 文件对应的关联对象类型：比如与哪张表关联，用于关联表
	 */
	SysUploadObject getSysUploadObjectById(String id);

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

