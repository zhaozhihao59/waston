package com.tocersoft.system.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.page.PageResult;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.system.dao.ISysUploadFileDao;
import com.tocersoft.system.dao.ISysUploadObjectDao;
import com.tocersoft.system.dto.SysUploadFileCondition;
import com.tocersoft.system.entity.SysUploadFile;
import com.tocersoft.system.entity.SysUploadObject;
import com.tocersoft.system.service.ISysUploadFileService;

@Service("sysUploadFileServiceImpl")
@Transactional(value = "transactionManager")
public class SysUploadFileServiceImpl implements ISysUploadFileService{

	@Resource(name = "sysUploadFileDaoImpl")
	private ISysUploadFileDao sysUploadFileDao;
	@Resource(name = "sysUploadObjectDaoImpl")
	private ISysUploadObjectDao sysUploadObjectDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listSysUploadFileByPage(PageResult<SysUploadFile> pageResult,SysUploadFileCondition condition){
		int rows = sysUploadFileDao.listSysUploadFileByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<SysUploadFile> list = sysUploadFileDao.listSysUploadFileByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 文件图片表
	 */
	public SysUploadFile getSysUploadFileById(String id){
		return sysUploadFileDao.getSysUploadFileById(id);
	}
	
	/**
	 * 根据ID查询
	 * @param 相关联的对象ID
	 * @param 相关联的对象类型ID
	 */
	public List<SysUploadFile> listSysUploadFileByObjectIdAndTypeId(String objectId,String typeId){
		List<SysUploadFile> list = sysUploadFileDao.listSysUploadFileByObjectIdAndTypeId(objectId,typeId);
		return list;
	}

	/**
	 * 新增
	 * @param item 文件图片表
	 */
	public void add(SysUploadFile item){
		// 获取文件扩展名
		int lastIndex = item.getFileName().lastIndexOf(".");
		item.setExtend(item.getFileName().substring(lastIndex+1,item.getFileName().length()));
		item.setType(1);
		
		// 相关联的对象ID和对象类型
//		SysUploadObject object = sysUploadObjectDao.getSysUploadObjectByName("产品");
//		item.setObjectType(object.getId());
		sysUploadFileDao.add(item);
	}

	/**
	 * 修改
	 * @param item 文件图片表
	 */
	public void update(SysUploadFile item){
		sysUploadFileDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		for(String id : ids){
			SysUploadFile file = sysUploadFileDao.getSysUploadFileById(id);
			String realPath = WebUtils.getRealPath("/" + file.getPath());
			FileUtils.deleteQuietly(new File(realPath));
		}
		sysUploadFileDao.delByIds(ids);
	}

}

