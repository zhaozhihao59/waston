package com.tocersoft.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.cms.dao.ICmsAdvPhotoDao;
import com.tocersoft.cms.entity.CmsAdvPhoto;
import com.tocersoft.cms.service.ICmsAdvPhotoService;

/**
 * 文章管理业务层
 * @author lenovo
 *
 */
@Service
@Transactional(value = "transactionManager")
public class CmsAdvPhotoServiceImpl implements ICmsAdvPhotoService{
	
	@Resource
	private ICmsAdvPhotoDao cmsAdvPhotoDao;

	@Override
	public void listAdvPhotoByPage(PageResult<CmsAdvPhoto> pageResult, String advId) {
		
		RowBounds row = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		
		//获取结果集
		List<CmsAdvPhoto> list = cmsAdvPhotoDao.listAdvPhotoByPage(row, advId);
		pageResult.setResult(list);
		
		//获取总行数
		int count = cmsAdvPhotoDao.listAdvPhotoByPageCount(advId);
		pageResult.setRows(count);
		
	}

	@Override
	public CmsAdvPhoto getAdvPhotoById(String id) {
		return cmsAdvPhotoDao.getAdvPhotoById(id);
	}

	@Override
	public void addAdvPhoto(CmsAdvPhoto photo) {
		cmsAdvPhotoDao.addAdvPhoto(photo);
	}

	@Override
	public void updateAdvPhoto(CmsAdvPhoto photo) {
		cmsAdvPhotoDao.updateAdvPhoto(photo);
	}

	@Override
	public void removeAdvPhotoById(String id) {
		cmsAdvPhotoDao.removeAdvPhotoById(id);
	}

	@Override
	public List<CmsAdvPhoto> listAdvPhotoByAdvId(String advId) {
		return cmsAdvPhotoDao.listAdvPhotoByAdvId(advId);
	}
}
