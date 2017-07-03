package com.tocersoft.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.cms.dto.CmsAdvCondition;
import com.tocersoft.cms.entity.CmsAdv;

/**
 * 广告管理DAO
 * @author 方泉
 *
 */
@Repository("cmsAdvDaoImpl")
public interface ICmsAdvDao {
	
	/**
	 * 分页查询文章
	 * @param row
	 * @param condition
	 * @return
	 */
	List<CmsAdv> listAdvByPage(RowBounds row, @Param("condition")CmsAdvCondition condition);
	
	/**
	 * 获取总行数
	 * @param condition
	 * @return
	 */
	int listAdvByPageCount(@Param("condition")CmsAdvCondition condition);
	
	/**
	 * 根据ID查询广告位信息
	 * @param id
	 * @return
	 */
	CmsAdv getAdvById(String id);
	
	
}
