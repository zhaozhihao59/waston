package com.tocersoft.cms.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.cms.entity.CmsArticle;
import com.tocersoft.cms.entity.CmsChannel;

/**
 * 栏目管理接口
 * @author 欧阳明航
 *
 */
public interface ICmsChannelService {
	/**
	 *  栏目分页查询
	 * @param pageResult
	 */
	void getChannelListByPage(PageResult<CmsChannel> pageResult);
	/**
	 * 保存栏目
	 * @param item
	 */
	void doSave(CmsChannel item);
	/**
	 * 获取所有栏目
	 * @return
	 */
	List<CmsChannel> getCmsChannelAll();
	/**
	 * 获取网站右侧栏目
	 * @return
	 */
	List<CmsChannel> getCmsChannelRight();
	/**
	 * 删除
	 * @param channelId
	 */
	void delChannel(String channelId);
	
	/**
	 * 批量显示在右侧
	 * @param channelId
	 */
	void showRight(String channelId);
	
	/**
	 * 批量不显示在右侧
	 * @param channelId
	 */
	void unShowRight(String channelId);
	
	/**
	 * 根据ID获取栏目
	 * @return
	 */
	CmsChannel getCmsChannelById(String channelId);
	
	/**
	 * 根据语言查询
	 * @return
	 */
	List<CmsChannel> listChannelByIsEn(Integer isEn);
	
	/**
	 * 根据上级类别ID获得类别列表
	 * 
	 * @param parentId
	 * @return
	 */
	List<CmsChannel> listChannelByPid(String parent);
	
	
	/**
	 * 添加栏目
	 * @param parentId
	 * @param name
	 * @param sort
	 */
	void doAddCmsChannel(String parentId, String name, Integer sort);
	
	void doUpdateCmsChannel(String nodeId, String name, Integer sort);
	CmsChannel getById(String nodeId);
	
	/**
	 * 删除栏目
	 * @param channel	需要删除的实体，主要用了id和parentId的属性
	 * @return
	 */
	void delCmsChannel(CmsChannel channel);
	
	/**
	 * 检查栏目是否能被删除
	 * 1、该栏目下是否有子栏目，若有，则不能删除；
	 * 2、该栏目下是否有文章，若有，则不能删除；
	 * @param 	id	栏目ID
	 * @return	0 - 可删除 1 - 不可删除
	 */
	Integer checkChannelIsDeleteById(String id);

}
