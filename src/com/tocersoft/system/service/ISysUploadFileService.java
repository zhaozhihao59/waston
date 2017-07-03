package com.tocersoft.system.service;

import java.util.List;

import com.tocersoft.system.entity.SysUploadFile;
import com.tocersoft.base.page.PageResult;

import com.tocersoft.system.dto.SysUploadFileCondition;

public interface ISysUploadFileService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listSysUploadFileByPage(PageResult<SysUploadFile> pageResult,SysUploadFileCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 文件图片表
	 */
	SysUploadFile getSysUploadFileById(String id);
	
	/**
	 * 根据ID查询
	 * @param 相关联的对象ID
	 * @param 相关联的对象类型ID
	 */
	List<SysUploadFile> listSysUploadFileByObjectIdAndTypeId(String objectId,String typeId);

	/**
	 * 新增
	 * @param item 文件图片表
	 */
	void add(SysUploadFile item);

	/**
	 * 修改
	 * @param item 文件图片表
	 */
	void update(SysUploadFile item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

}

