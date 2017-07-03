package com.tocersoft.cms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.cms.entity.CmsArticle;
import com.tocersoft.cms.entity.CmsChannel;

/**
 * 栏目DAO
 * @author lenovo
 *
 */
@Repository("cmsChannelDaoImpl")
public interface ICmsChannelDao {
	/**
	 * 分页查询栏目
	 * @param bounds
	 * @return
	 */
	List<CmsChannel> getChannelListByPage(RowBounds bounds);
	/**
	 * 查询栏目总行数
	 * @return
	 */
	int getChannelListByPageCount();
	/**
	 * 添加
	 * @param item
	 */
	void add(CmsChannel item);
	/**
	 * 修改
	 * @param item
	 */
	void update(CmsChannel item);
	/**
	 *  查询所有栏目
	 * @return
	 */
	List<CmsChannel> getCmsChannelAll();
	/**
	 *  查询网站右侧栏目
	 * @return
	 */
	List<CmsChannel> getCmsChannelRight();
	
	/**
	 * 根据语言查询
	 * @return
	 */
	List<CmsChannel> listChannelByIsEn(@Param("isEn")Integer isEn);
	
	/**
	 * 根据栏目ID查询栏目信息
	 * @param channelId
	 * @return
	 */
	CmsChannel getCmsChannelById(String channelId);
	/**
	 * 根据栏目ID查询栏目下是否有文章
	 * @param channelId
	 * @return
	 */
	int getChannelById(String channelId);
	/**
	 * 删除
	 * @param channelId
	 */
	void delChannel(String channelId);
	
	void showRight(@Param("channelId")String[] channelId, @Param("isRight") Integer isRight);
	/**
	 * 根据上级类别ID获得类别列表
	 * 
	 * @param parentId
	 * @return
	 */
	List<CmsChannel> listChannelByPid(String parent);
	
	
	/**
	 * 获取一级栏目最大codeNum
	 * 
	 * @param parentCatId
	 * @return
	 */
	String getMaxCodeNum(String parentId);
	/**
	 * 获取上级栏目的codeNum
	 * @param parentId
	 * @return
	 */
	String getCodeNum(String parentId);
	
	
	/**
	 * 添加类别
	 * 
	 * @param map
	 */
	void doAddCmsChannel(Map<String, Object> map);
	
	CmsChannel getById(String parentId);
	
	void doUpdateCmsChannel(Map<String, Object> map);
	/**
	 * 
	 * @param nodeId
	 * @return
	 */
	List<CmsArticle> getArticleByChannelId(@Param("channelId")String channelId);
	
	/**
	 * 根据id删除栏目
	 * @param nodeId
	 */
	void delCmsChannelById(String nodeId);
	
	/**
	 * 根据栏目ID更新栏目是否为父节点
	 * @param id		栏目ID
	 * @param isParent	0-非父节点 1-是父节点
	 */
	void updateChannelIsParentById(@Param("id")String id, @Param("isParent")Integer isParent);
	
	/**
	 * 根据栏目ID更新栏目是否为父节点
	 * @param id		栏目ID
	 * @param isParent	0-非父节点 1-是父节点
	 * @param isDelete	0-不可删除 1-可删除
	 */
	void updateIsParentDeleteById(@Param("id")String id, @Param("isParent")Integer isParent, @Param("isDelete")Integer isDelete);
	
}
