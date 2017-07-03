package com.tocersoft.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.system.dao.ISysDictDao;
import com.tocersoft.system.entity.SysDict;
import com.tocersoft.system.service.ISysDictService;
@Service("sysDictServiceImpl")
@Transactional("transactionManager")
public class ISysDictServiceImpl implements ISysDictService {
	
	@Resource(name = "sysDictDaoImpl")
	private ISysDictDao sysDictDao;
	
	@Override
	public List<SysDict> listAll(){
		return sysDictDao.listAll();
	}

	@Override
	public SysDict getSysDictById(String id) {
		return sysDictDao.getSysDictById(id);
	}

	/**
	 * 保存数据名称
	 * @param item
	 */
	public void doSave(SysDict item){
		sysDictDao.addSysDict(item);
	}

	/**
	 * 修改数据名称
	 * @param item
	 */
	public void doUpdate(SysDict item) {
		sysDictDao.doUpdate(item);
	}

	/**
	 * 批量删除数据库字典项
	 * @param ids
	 */
	public void delBatch(String[] ids) {
		sysDictDao.delBatch(ids);
	}

}
