package com.tocersoft.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.system.dao.ISysUploadObjectDao;
import com.tocersoft.system.entity.SysUploadObject;
import com.tocersoft.system.service.ISysUploadObjectService;

import com.tocersoft.system.dto.SysUploadObjectCondition;

@Service("sysUploadObjectServiceImpl")
@Transactional(value = "transactionManager")
public class SysUploadObjectServiceImpl implements ISysUploadObjectService{

	@Resource(name = "sysUploadObjectDaoImpl")
	private ISysUploadObjectDao sysUploadObjectDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listSysUploadObjectByPage(PageResult<SysUploadObject> pageResult,SysUploadObjectCondition condition){
		int rows = sysUploadObjectDao.listSysUploadObjectByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<SysUploadObject> list = sysUploadObjectDao.listSysUploadObjectByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 文件对应的关联对象类型：比如与哪张表关联，用于关联表
	 */
	public SysUploadObject getSysUploadObjectById(String id){
		return sysUploadObjectDao.getSysUploadObjectById(id);
	}

	/**
	 * 新增
	 * @param item 文件对应的关联对象类型：比如与哪张表关联，用于关联表
	 */
	public void add(SysUploadObject item){
		sysUploadObjectDao.add(item);
	}

	/**
	 * 修改
	 * @param item 文件对应的关联对象类型：比如与哪张表关联，用于关联表
	 */
	public void update(SysUploadObject item){
		sysUploadObjectDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		sysUploadObjectDao.delByIds(ids);
	}

}

