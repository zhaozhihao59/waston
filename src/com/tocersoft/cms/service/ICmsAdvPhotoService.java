package com.tocersoft.cms.service;

import java.util.List;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.cms.entity.CmsAdvPhoto;

/**
 * 广告图片接口
 * @author 方泉
 *
 */
public interface ICmsAdvPhotoService {
	
	/**
	 * 分页查询广告位图片
	 * @param pageResult
	 */
	void listAdvPhotoByPage(PageResult<CmsAdvPhoto> pageResult, String advId);
	
	/**
	 * 根据ID查询广告位信息
	 * @param id
	 * @return
	 */
	CmsAdvPhoto getAdvPhotoById(String id);
	
	/**
	 * 保存广告图片
	 * @param photo	广告图实体
	 */
	void addAdvPhoto(CmsAdvPhoto photo);
	
	/**
	 * 修改广告图片
	 * @param photo	广告图实体
	 */
	void updateAdvPhoto(CmsAdvPhoto photo);
	
	/**
	 * 删除广告图片
	 * @param id 广告图实体ID
	 */
	void removeAdvPhotoById(String id);
	
	/**
	 * 根据广告位ID查询广告位图片
	 * @param advId
	 */
	List<CmsAdvPhoto> listAdvPhotoByAdvId(String advId);
}
