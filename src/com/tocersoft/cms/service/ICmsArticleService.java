package com.tocersoft.cms.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.cms.dto.CmsArticleCondition;
import com.tocersoft.cms.entity.CmsAdv;
import com.tocersoft.cms.entity.CmsArticle;

/**
 * 
 * 文章管理接口
 * @author 欧阳明航
 *
 */
public interface ICmsArticleService {
	/**
	 * 添加文章
	 * @param item
	 */
	void doSave(CmsArticle item);
	/**
	 *  修改文章
	 * @param item
	 */
	void doUpdate(CmsArticle item);
	/**
	 * 分页查询文章
	 * @param pageResult
	 * @param condition
	 */
	void getArticleListByPage(PageResult<CmsArticle> pageResult,CmsArticleCondition condition);

	/**
	 * 分页查询文章根据父栏目的Id
	 * @param pageResult
	 * @param condition
	 */
	void getArticleListByPageByParentId(PageResult<CmsArticle> pageResult);
	void getArticleListByPageByParentIdEn(PageResult<CmsArticle> pageResult);
	void getArticleListByPageByParentIdJp(PageResult<CmsArticle> pageResult);
	
	/**
	 * 分页查询文章
	 * @param pageResult
	 * @param condition
	 */
	void getArticleListByPageOrderByReady(PageResult<CmsArticle> pageResult,CmsArticleCondition condition);
	/**
	 * 批量删除文章
	 * @param articleId
	 */
	void delArticle(String articleId);
	void publish(String articleId);
	void unPublish(String articleId);
	
	/**
	 * 为当前文章加上一次阅读数量
	 * @param articleId
	 */
	void readOnce(String articleId);
	
	/**
	 * 为当前文章加上一次点赞数量
	 * @param articleId
	 */
	void likeOnce(String articleId);
	
	/**
	 * 根据栏目ID查询栏目下是否有文章
	 * @param channelId
	 * @return
	 */
	int getArticleCount(String channelId);
	/**
	 * 根据栏目ID查询所有文章
	 * @param channleId
	 * @return
	 */
	List<CmsArticle> getArticleByChannelId(String channleId);
	/**
	 * 根据ID查询详细信息
	 * @param id
	 * @return
	 */
	CmsArticle getArticleById(String id);
	/**
	 * 查询所有文章
	 * @return
	 */
	List<CmsArticle> getArticleAll();
	
	/**
	 * 查询类别下的某几篇文章
	 * @param cid	类别ID
	 * @param limit	限制几篇文章 null或者0表示不限数量
	 */
	List<CmsArticle> listArticleByCidLimit(String cid, Integer limit);
	
	/**
	 * 查询英文版
	 * @param cid
	 * @return
	 */
	List<CmsArticle> listArticleByCidEn(String cid);
	/**
	 * 查询日文版
	 * @param cid
	 * @return
	 */
	List<CmsArticle> listArticleByCidJp(String cid);
	
	/**
	 * 查询类别下的最新发布的前几篇文章
	 * @param cid	类别ID
	 * @param limit	限制几篇文章 null或者0表示不限数量
	 */
	List<CmsArticle> listNewArticleByCidLimit(String cid, Integer limit);
	
	/**
	 * 根据新闻ID查找下属所以类别的文章，显示五条
	 * @return
	 */
	List<CmsArticle> getAdvByTopFive(String en,String jp);
	/**
	 * 多参数查找新闻：ID、查询条数
	 * @param name
	 * @param Level
	 * @param count
	 */
	List<CmsArticle> getArticleByComplex(String id,int count,String en,String jp);
	/**
	 * 多参数查找新闻：ID、查询条数
	 * @param name
	 * @param Level
	 * @param count
	 */
	List<CmsArticle> getArticleByComplex2(String id,int count,String en,String jp);
	/**
	 * 多参数查找新闻：ID、查询条数
	 * @param name
	 * @param Level
	 * @param count
	 */
	List<CmsArticle> getArticleByComplexs(String id,String id1,int count);
	
	/**
	 * 根据channelID 发布状态 条数
	 * @param row
	 * @param condition
	 * @return
	 */
	List<CmsArticle> getArticleListByChannelId(String id,int count,String en,String jp);
	
	/**
	 * 分页查询文章
	 * @param row
	 * @param condition
	 * @return
	 */
	void listArticleListByChannelId(PageResult<CmsArticle> pageResult, CmsArticleCondition condition);
	
	/**
	 * 获取总行数
	 * @param condition
	 * @return
	 */
	int listArticleListByChannelIdCount(CmsArticleCondition condition);
	
	/**
	 * 根据栏目ID查询所有文章
	 * @param id
	 * @return
	 */
	List<CmsArticle> getArticleAll(String id);
	List<CmsArticle> getArticleAllEn(String id);
	List<CmsArticle> getArticleAllJp(String id);
	/**
	 * 重载分页查询文章根据父栏目的Id
	 * */
	List<CmsArticle> getArticleListByPageByParentIdAll(PageResult<CmsArticle> pageResult);
	
}
