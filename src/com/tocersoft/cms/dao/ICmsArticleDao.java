package com.tocersoft.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.cms.dto.CmsArticleCondition;
import com.tocersoft.cms.entity.CmsAdv;
import com.tocersoft.cms.entity.CmsArticle;

/**
 * 文章管理DAO
 * @author 欧阳明航
 *
 */
@Repository("cmsArticleDaoImpl")
public interface ICmsArticleDao {
	/**
	 * 根据栏目ID查询文章数量
	 * @param id
	 * @return
	 */
	int getArticleCount(String id);
	/**
	 * 添加文章
	 * @param item
	 */
	void doSave(CmsArticle item);
	/**
	 * 修改文章
	 * @param item
	 */
	void doUpdate(CmsArticle item);
	/**
	 * 分页查询文章
	 * @param row
	 * @param condition
	 * @return
	 */
	List<CmsArticle> getArticleListByPage(RowBounds row,@Param("condition") CmsArticleCondition condition);
	/**
	 * 根据父类Id分页查询所有文章
	 * 
	 */
	List<CmsArticle> getArticleListByPageByParentId(RowBounds row);
	/**
	 * 根据父类Id分页查询所有文章
	 * 
	 */
	List<CmsArticle> getArticleListByPageByParentId();
	/**
	 * 根据父类Id分页查询所有文章(Jp)
	 * 
	 */
	List<CmsArticle> getArticleListByPageByParentIdJp(RowBounds row);	
	/**
	 * 根据父类Id分页查询所有文章(En)
	 * 
	 */
	List<CmsArticle> getArticleListByPageByParentIdEn(RowBounds row);
	/**
	 * 根据父类Id分页查询总条数
	 */
	int getArticleListByPageByCount();
	int getArticleListByPageByCountEn();
	int getArticleListByPageByCountJp();
	
	/**
	 * 分页查询文章
	 * @param row
	 * @param condition
	 * @return
	 */
	List<CmsArticle> listArticleListByChannelId(RowBounds row,@Param("condition") CmsArticleCondition condition);
	
	CmsArticle getArticleByItemId(@Param("id") String id);
	
	
	/**
	 * 获取总行数
	 * @param condition
	 * @return
	 */
	int listArticleListByChannelIdCount(@Param("condition") CmsArticleCondition condition);
	
	
	/**
	 * 根据channelID 发布状态 条数
	 * @param row
	 * @param condition
	 * @return
	 */
	List<CmsArticle> getArticleListByChannelId(String id,int count,@Param("en")String en,@Param("jp")String jp);
	
	
	
	
	/**
	 * 获取总行数
	 * @param condition
	 * @return
	 */
	int getArticleListByPageCount(@Param("condition") CmsArticleCondition condition);
	/**
	 * 分页按点击次数排列
	 * @param row
	 * @param condition
	 * @return
	 */
	List<CmsArticle> getArticleListByPageOrderByReady(RowBounds row,@Param("condition") CmsArticleCondition condition);
	
	/**
	 * 批量删除
	 * @param articleId
	 */
	void delArticle(String[] articleId);
	
	/**
	 * 批量发布
	 * @param articleId
	 */
	void publish(@Param("articleId")String[] articleId, @Param("isPublish") Integer isPublish);
	
	/**
	 * 根据栏目ID查询文章集合
	 * @param channleId
	 * @return
	 */
	List<CmsArticle> getArticleByChannelId(String channleId);
	
	/**
	 * 查询类别下的某几篇文章
	 * @param cid	类别ID
	 * @param limit	限制几篇文章 null或者0表示不限数量
	 */
	List<CmsArticle> listArticleByCidLimit(@Param("cid")String cid, @Param("limit")Integer limit);
	/**
	 * 查询英文版
	 * @param cid
	 * @return
	 */
	List<CmsArticle> listArticleByCidEn(@Param("cid")String cid);
	/**
	 * 查询日文版
	 * @param cid
	 * @return
	 */
	List<CmsArticle> listArticleByCidJp(@Param("cid")String cid);
	
	/**
	 * 查询类别下的最新发布的前几篇文章
	 * @param cid	类别ID
	 * @param limit	限制几篇文章 null或者0表示不限数量
	 */
	List<CmsArticle> listNewArticleByCidLimit(@Param("cid")String cid, @Param("limit")Integer limit);
	
	/**
	 * 根据ID查询详细信息
	 * @param id
	 * @return
	 */
	List<CmsArticle> getArticleById(String id);
	
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
	 * 根据新闻ID查找下属所以类别的文章，显示五条
	 * @return
	 */
	List<CmsArticle> getAdvByTopFive(@Param("en")String en,@Param("jp")String jp);
	/**
	 * 多参数查找新闻：ID、查询条数
	 * @param name
	 * @param Level
	 * @param count
	 */
	List<CmsArticle> getArticleByComplex(String id,int count,@Param("en")String en,@Param("jp")String jp);
	/**
	 * 多参数查找新闻：ID、查询条数
	 * @param name
	 * @param Level
	 * @param count
	 */
	List<CmsArticle> getArticleByComplex2(String id,int count,@Param("en")String en,@Param("jp")String jp);
	
	/**
	 * 多参数查找新闻：ID、查询条数
	 * @param name
	 * @param Level
	 * @param count
	 */
	List<CmsArticle> getArticleByComplexs(String id,String id1,int count);
	/**
	 * 根据栏目ID查询所有文章
	 * @param id
	 * @return
	 */
	List<CmsArticle> getArticleAll(String id);
	List<CmsArticle> getArticleAllJp(String id);
	List<CmsArticle> getArticleAllEn(String id);
	
	/**
	 * 根据案例ID获得相应的子栏目下所有相对应 的文章
	 * @author 刘鸿博
	 * 
	 */
	List<CmsArticle> listArticleById(@Param("parentId") String parentId);
}
