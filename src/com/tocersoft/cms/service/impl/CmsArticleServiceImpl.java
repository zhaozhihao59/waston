package com.tocersoft.cms.service.impl;

import java.io.File;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.nntp.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.base.utils.CommonUtil;
import com.tocersoft.cms.dao.ICmsArticleDao;
import com.tocersoft.cms.dao.ICmsChannelDao;
import com.tocersoft.cms.dto.CmsArticleCondition;
import com.tocersoft.cms.entity.CmsAdv;
import com.tocersoft.cms.entity.CmsArticle;
import com.tocersoft.cms.entity.CmsChannel;
import com.tocersoft.cms.service.ICmsArticleService;

/**
 * 文章管理业务层
 * @author lenovo
 *
 */
@Service
@Transactional(value = "transactionManager")
public class CmsArticleServiceImpl implements ICmsArticleService{
	
	@Resource
	private ICmsArticleDao cmsArticleDao;
	
	@Resource
	private ICmsChannelDao cmsChannelDao;

	@Override
	public void getArticleListByPage(PageResult<CmsArticle> pageResult,
			CmsArticleCondition condition) {
		//进行like检查
		CommonUtil.checkEscapeValue(condition);
		RowBounds row = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		if(StringUtils.isNotBlank(condition.getChannelId()) && condition.getChannelId().equals("0")){
			condition.setChannelId(null);
		}
		//获取结果集
		List<CmsArticle> list = cmsArticleDao.getArticleListByPage(row,condition);
		pageResult.setResult(list);
		//获取总行数
		int count = cmsArticleDao.getArticleListByPageCount(condition);
		pageResult.setRows(count);
	}
	
	@Override
	public void getArticleListByPageOrderByReady(PageResult<CmsArticle> pageResult,
			CmsArticleCondition condition) {
		//进行like检查
		CommonUtil.checkEscapeValue(condition);
		RowBounds row = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		//获取结果集
		List<CmsArticle> list = cmsArticleDao.getArticleListByPageOrderByReady(row,condition);
		pageResult.setResult(list);
		//获取总行数
		int count = cmsArticleDao.getArticleListByPageCount(condition);
		pageResult.setRows(count);
	}
	@Override
	public void doSave(CmsArticle item) {
		cmsArticleDao.doSave(item);
	}
	@Override
	public void doUpdate(CmsArticle item) {
		cmsArticleDao.doUpdate(item);
	}
	@Override
	public void delArticle(String articleId) {
		if(StringUtils.isNotBlank(articleId)){
			String [] selIds = articleId.split(",");
			for (int i = 0; i < selIds.length; i++) {
				CmsArticle article = cmsArticleDao.getArticleByItemId(selIds[i]);
				//【中文附件】地址
				String annexPath = article.getAnnexPath();
				//【英文附件】地址
				String enAnnexPath = article.getEnAnnexPath();
				//【日文附件】地址
				String jpAnnexPath = article.getJpAnnexPath();
				//【标题图片】地址
				String titlePath = article.getPath();
				//判断【中文附件】是否为空，如果不为空则删除本地文件
				if(StringUtils.isNotBlank(annexPath)){
					FileUtils.deleteQuietly(new File(annexPath));	
				}
				//判断【英文附件】是否为空，如果不为空则删除本地文件
				if(StringUtils.isNotBlank(enAnnexPath)){
					FileUtils.deleteQuietly(new File(enAnnexPath));
				}
				//判断【日文附件】是否为空，如果不为空则删除本地文件
				if(StringUtils.isNotBlank(jpAnnexPath)){
					FileUtils.deleteQuietly(new File(jpAnnexPath));
				}
				//判断【标题图片】附件是否为空，如果不为空则删除本地文件
				if(StringUtils.isNotBlank(titlePath)){
					FileUtils.deleteQuietly(new File(titlePath));
				}		
			}
			cmsArticleDao.delArticle(selIds);
		}
	}
	
	@Override
	public int getArticleCount(String channelId) {
		return cmsArticleDao.getArticleCount(channelId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CmsArticle> getArticleByChannelId(String channleId) {
		List<CmsArticle> list = cmsArticleDao.getArticleByChannelId(channleId);
		if(null != list && list.size() > 0 ){
			return list;
		}
		return Collections.EMPTY_LIST;
	}
	
	@Override
	public CmsArticle getArticleById(String id) {
		List<CmsArticle> list =  cmsArticleDao.getArticleById(id);
		CmsArticle art = null;
		if(null != list && list.size() > 0){
			art = list.get(0);
			// 阅读次数+1
			int readyNum = art.getReadNum().intValue()+1;
			art.setReadNum(readyNum);
			this.doUpdate(art);
		}
		return art;
	}
	
	@Override
	public List<CmsArticle> getArticleAll() {
		return null;
	}
	
	/**
	 * 查询类别下的某几篇文章
	 * @param cid	类别ID
	 * @param limit	限制几篇文章 null或者0表示不限数量
	 */
	@Override
	public List<CmsArticle> listArticleByCidLimit(String cid, Integer limit) {
		List<CmsArticle> list = cmsArticleDao.listArticleByCidLimit(cid, limit);
		if(null != list && list.size() > 0 ){
			return list;
		}
		return Collections.EMPTY_LIST;
	}
	
	/**
	 * 查询类别下的最新发布的前几篇文章
	 * @param cid	类别ID
	 * @param limit	限制几篇文章 null或者0表示不限数量
	 */
	@Override
	public List<CmsArticle> listNewArticleByCidLimit(String cid, Integer limit) {
		List<CmsArticle> list = cmsArticleDao.listNewArticleByCidLimit(cid, limit);
		if(null != list && list.size() > 0 ){
			return list;
		}
		return Collections.EMPTY_LIST;
	}
	/**
	 * 查询英文版
	 * @param cid
	 * @return
	 */
	public List<CmsArticle> listArticleByCidEn(String cid){
		return cmsArticleDao.listArticleByCidEn(cid);
	}
	/**
	 * 查询日文版
	 * @param cid
	 * @return
	 */
	public List<CmsArticle> listArticleByCidJp(String cid){
		return cmsArticleDao.listArticleByCidJp(cid);
	}
	@Override
	public void publish(String articleId) {
		if(StringUtils.isNotBlank(articleId)){
			String [] selIds = articleId.split(",");
			cmsArticleDao.publish(selIds, 1);
		}
	}
	
	@Override
	public void unPublish(String articleId) {
		if(StringUtils.isNotBlank(articleId)){
			String [] selIds = articleId.split(",");
			cmsArticleDao.publish(selIds, 0);
		}
	}

	/**
	 * 为当前文章加上一次阅读数量
	 * @param articleId
	 */
	@Override
	public void readOnce(String articleId) {
		cmsArticleDao.readOnce(articleId);
	}
	
	/**
	 * 为当前文章加上一次点赞数量
	 * @param articleId
	 */
	@Override
	public void likeOnce(String articleId) {
		cmsArticleDao.likeOnce(articleId);
	}
	/**
	 * 根据新闻ID查找下属所以类别的文章，显示五条
	 * @return
	 */
	@Override
	public List<CmsArticle> getAdvByTopFive(String en,String jp) {
		return cmsArticleDao.getAdvByTopFive(en, jp);
	}
	@Override
	public List<CmsArticle> getArticleByComplex(String id, int count,String en,String jp) {
		// TODO Auto-generated method stub
		return cmsArticleDao.getArticleByComplex(id, count,en,jp);
	}
	@Override
	public List<CmsArticle> getArticleByComplex2(String id, int count,String en,String jp) {
		// TODO Auto-generated method stub
		return cmsArticleDao.getArticleByComplex2(id, count,en,jp);
	}

	@Override
	public List<CmsArticle> getArticleByComplexs(String id, String id1,
			int count) {
		return cmsArticleDao.getArticleByComplexs(id, id1, count);
	}

	@Override
	public List<CmsArticle> getArticleListByChannelId(String id, int count,String en,String jp) {
		return cmsArticleDao.getArticleListByChannelId(id,count,en,jp);
	}

	@Override
	public void listArticleListByChannelId(PageResult<CmsArticle> pageResult,CmsArticleCondition condition) {
		//进行like检查
		CommonUtil.checkEscapeValue(condition);
		RowBounds row = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		if(StringUtils.isNotBlank(condition.getChannelId()) && condition.getChannelId().equals("0")){
			condition.setChannelId(null);
		}
		//获取结果集
		List<CmsArticle> list = cmsArticleDao.listArticleListByChannelId(row, condition);
		for (CmsArticle cmsArticle : list) {
			CmsChannel cmsChannel = cmsChannelDao.getCmsChannelById(cmsArticle.getChannelId());
			cmsArticle.setType(cmsChannel.getName());
		}
		pageResult.setResult(list);
		//获取总行数
		int count = cmsArticleDao.listArticleListByChannelIdCount(condition);
		pageResult.setRows(count);
	}
	
	
	@Override
	public int listArticleListByChannelIdCount(CmsArticleCondition condition) {
		return cmsArticleDao.listArticleListByChannelIdCount(condition);
	}

	@Override
	public List<CmsArticle> getArticleAll(String id) {
		return cmsArticleDao.getArticleAll(id);
	}

	@Override
	public void getArticleListByPageByParentId(
			PageResult<CmsArticle> pageResult) {
		
				RowBounds row = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
				//获取结果集
				List<CmsArticle> list = cmsArticleDao.getArticleListByPageByParentId(row);
				pageResult.setResult(list);
				//获取总行数
				int count = cmsArticleDao.getArticleListByPageByCount();
				pageResult.setRows(count);
	}
	@Override
	public List<CmsArticle> getArticleListByPageByParentIdAll(PageResult<CmsArticle> pageResult){
		RowBounds row = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		return cmsArticleDao.getArticleListByPageByParentId(row);
	}

	@Override
	public void getArticleListByPageByParentIdEn(
			PageResult<CmsArticle> pageResult) {
		RowBounds row = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		//获取结果集
		List<CmsArticle> list = cmsArticleDao.getArticleListByPageByParentIdEn(row);
		pageResult.setResult(list);
		//获取总行数
		int count = cmsArticleDao.getArticleListByPageByCountEn();
		pageResult.setRows(count);
	}

	@Override
	public void getArticleListByPageByParentIdJp(
			PageResult<CmsArticle> pageResult) {
		RowBounds row = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		//获取结果集
		List<CmsArticle> list = cmsArticleDao.getArticleListByPageByParentIdJp(row);
		pageResult.setResult(list);
		//获取总行数
		int count = cmsArticleDao.getArticleListByPageByCountJp();
		pageResult.setRows(count);
	}

	@Override
	public List<CmsArticle> getArticleAllEn(String id) {
		return cmsArticleDao.getArticleAllEn(id);
	}

	@Override
	public List<CmsArticle> getArticleAllJp(String id) {
		return cmsArticleDao.getArticleAllJp(id);
	}

	
	
}
