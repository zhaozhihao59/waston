package com.tocersoft.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.system.entity.SysUploadFile;
import com.tocersoft.system.dto.SysUploadFileCondition;

@Repository("sysUploadFileDaoImpl")
public interface ISysUploadFileDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<SysUploadFile> listSysUploadFileByPage(RowBounds bounds,@Param("condition") SysUploadFileCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listSysUploadFileByPageCount(@Param("condition") SysUploadFileCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 文件图片表
	 */
	SysUploadFile getSysUploadFileById(@Param("id") String id);

	/**
	 * 根据ID查询
	 * @param 相关联的对象ID
	 * @param 相关联的对象类型ID
	 */
	List<SysUploadFile> listSysUploadFileByObjectIdAndTypeId(@Param("objectId") String objectId,@Param("objectType") String typeId);
	
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

