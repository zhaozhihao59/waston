package com.tocersoft.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.auth.entity.User;
import com.tocersoft.system.dao.ISysDictItemDao;
import com.tocersoft.system.entity.SysDictItem;
import com.tocersoft.system.service.ISysDictItemService;

@Service("sysDictItemServiceImpl")
@Transactional("transactionManager")
public class SysDictItemServiceImpl implements ISysDictItemService {
	
	@Resource(name = "sysDictItemDaoImpl")
	private ISysDictItemDao sysDictItemDao;
	
	@Override
	public List<SysDictItem> listSysDictItemByDictId(String dictId) {
		return sysDictItemDao.listSysDictItemByDictId(dictId);
	}

	@Override
	public void deleteSysDictItemById(String id) {
		sysDictItemDao.deleteSysDictItemById(id);
	}

	@Override
	public void editSysDictItem(SysDictItem item, User currentOperatorUser) {
		item.setUpdateBy(currentOperatorUser.getAccount());
		if(StringUtils.isNotBlank(item.getId())){
			sysDictItemDao.updateSysDictItem(item);
		}else{
			item.setCreateBy(currentOperatorUser.getAccount());
			sysDictItemDao.addSysDictItem(item);
		}
	}

	@Override
	public SysDictItem getSysDictItemById(String id) {
		return sysDictItemDao.getSysDictItemById(id);
	}

	@Override
	public void batchDelete(String[] ids) {
		sysDictItemDao.batchDelete(ids);
	}
}
