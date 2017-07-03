package com.tocersoft.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.cms.entity.CmsAdvPhoto;

/**
 * 广告管理DAO
 * @author 方泉
 *
 */
@Repository("cmsAdvPhotoDaoImpl")
public interface ICmsAdvPhotoDao {
	
	/**
	 * 分页查询文章
	 * @param row
	 * @param condition
	 * @return
	 */
	List<CmsAdvPhoto> listAdvPhotoByPage(RowBounds row, @Param("advId")String advId);
	
	/**
	 * 获取总行数
	 * @param condition
	 * @return
	 */
	int listAdvPhotoByPageCount(@Param("advId")String advId);
	
	/**
	 * 根据ID查询广告位图片信息
	 * @param id
	 * @return
	 */
	CmsAdvPhoto getAdvPhotoById(String id);
	
	/**
	 * 保存广告图片
	 * @param photo	广告图实体
	 * @return
	 */
	void addAdvPhoto(CmsAdvPhoto photo);
	
	/**
	 * 修改广告图片
	 * @param photo	广告图实体
	 * @return
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
