package com.tocersoft.cms.model;

import java.util.ArrayList;
import java.util.List;

import com.tocersoft.activity.entity.Activity;
import com.tocersoft.base.model.BaseModel;
import com.tocersoft.cms.dto.CmsArticleCondition;
import com.tocersoft.cms.entity.CmsAdvPhoto;
import com.tocersoft.cms.entity.CmsArticle;
import com.tocersoft.cms.entity.CmsChannel;

public class CmsArticleModel extends BaseModel<CmsArticle> {

	private static final long serialVersionUID = 4346507314203660761L;
	/**
	 * 文章实体
	 */
	private CmsArticle item = new CmsArticle();
	
	/**
	 * 文章实体
	 */
	private String id;

	/**
	 * 栏目集合
	 */
	private List<CmsChannel> cmsChannels = new ArrayList<CmsChannel>();
	/**
	 * 文章集合
	 */
	private List<CmsArticle> cmsArticles = new ArrayList<CmsArticle>();
	
	/**
	 * 文章集合
	 */
	private List<CmsArticle> orderNewsList = new ArrayList<CmsArticle>();
	
	/**
	 * 文章集合
	 */
	private List<CmsArticle> keyNewsList = new ArrayList<CmsArticle>();
	
	/**
	 * 首页轮转广告No1
	 */
	private List<CmsAdvPhoto> photosNo1 = new ArrayList<CmsAdvPhoto>();
	
	/**
	 * 首页轮转广告No2
	 */
	private List<CmsAdvPhoto> photosNo2 = new ArrayList<CmsAdvPhoto>();
	
	/**
	 * 首页轮转广告No3
	 */
	private List<CmsAdvPhoto> photosNo3 = new ArrayList<CmsAdvPhoto>();
	
	/**
	 * 首页轮转广告No4
	 */
	private List<CmsAdvPhoto> photosNo4 = new ArrayList<CmsAdvPhoto>();


	/**
	 * 文章条件类
	 * 
	 * @return
	 */
	private CmsArticleCondition condition = new CmsArticleCondition();
	
	/**
	 * 文章ID
	 * @return
	 */
	private String articleId;
	
	/**
	 * 类别ID
	 * @return
	 */
	private String cid;
	
	/**
	 * 大类别分类
	 * @return
	 */
	private Integer bcid;
	
	/**
	 * 类别实体
	 * @return
	 */
	private CmsChannel channel;
	private CmsChannel channel1;
	
	private List<String> visitCountList = new ArrayList<String>();
	
	private List<String> downloadCountList = new ArrayList<String>();	
	
	/**
	 * 页码
	 * @return
	 */
	private int page;
	
	private double count;
	
	/** 用于详细页左侧栏目菜单 - 展示文章 */
	private List<CmsArticle> menuArticles = new ArrayList<CmsArticle>();
	
	/** 文章详细页广告位 */
	private CmsAdvPhoto photo;
	
	/** 最新公告 */
	private List<CmsArticle> articlesAnnounce = new ArrayList<CmsArticle>();
	/** 全站搜索关键词，模糊匹配文章标题与摘要 */
	private String key;
	/**根据栏目ID查询该栏目下的所有文章*/
	private List<CmsArticle> cmsArticle1 = new ArrayList<CmsArticle>();
	private List<CmsArticle> cmsArticle2 = new ArrayList<CmsArticle>();
	private List<CmsArticle> cmsArticle3 = new ArrayList<CmsArticle>();
	private List<CmsArticle> cmsArticle_problem = new ArrayList<CmsArticle>();
	/**业务领域*/
	private List<CmsArticle> cmsArticleField1 = new ArrayList<CmsArticle>();
	private List<CmsArticle> cmsArticleField2 = new ArrayList<CmsArticle>();
	private List<CmsArticle> cmsArticleField3 = new ArrayList<CmsArticle>();
	private List<CmsArticle> cmsArticleField4 = new ArrayList<CmsArticle>();
	private List<Activity> listActivity = new ArrayList<Activity>();
	

	public List<Activity> getListActivity() {
		return listActivity;
	}

	public void setListActivity(List<Activity> listActivity) {
		this.listActivity = listActivity;
	}

	/**接受首页跳转条件*/
	private int i = 0;
	
	/**视频*/
	private List<CmsArticle> videoArticleList = new ArrayList<CmsArticle>();

	public List<CmsArticle> getVideoArticleList() {
		return videoArticleList;
	}

	public void setVideoArticleList(List<CmsArticle> videoArticleList) {
		this.videoArticleList = videoArticleList;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public List<CmsArticle> getCmsArticleField1() {
		return cmsArticleField1;
	}

	public void setCmsArticleField1(List<CmsArticle> cmsArticleField1) {
		this.cmsArticleField1 = cmsArticleField1;
	}

	public List<CmsArticle> getCmsArticleField2() {
		return cmsArticleField2;
	}

	public void setCmsArticleField2(List<CmsArticle> cmsArticleField2) {
		this.cmsArticleField2 = cmsArticleField2;
	}

	public List<CmsArticle> getCmsArticleField3() {
		return cmsArticleField3;
	}

	public void setCmsArticleField3(List<CmsArticle> cmsArticleField3) {
		this.cmsArticleField3 = cmsArticleField3;
	}

	public List<CmsArticle> getCmsArticleField4() {
		return cmsArticleField4;
	}

	public void setCmsArticleField4(List<CmsArticle> cmsArticleField4) {
		this.cmsArticleField4 = cmsArticleField4;
	}

	public List<CmsArticle> getCmsArticle_problem() {
		return cmsArticle_problem;
	}

	public void setCmsArticle_problem(List<CmsArticle> cmsArticle_problem) {
		this.cmsArticle_problem = cmsArticle_problem;
	}

	public List<CmsArticle> getCmsArticle1() {
		return cmsArticle1;
	}

	public void setCmsArticle1(List<CmsArticle> cmsArticle1) {
		this.cmsArticle1 = cmsArticle1;
	}

	public List<CmsArticle> getCmsArticle2() {
		return cmsArticle2;
	}

	public void setCmsArticle2(List<CmsArticle> cmsArticle2) {
		this.cmsArticle2 = cmsArticle2;
	}

	public List<CmsArticle> getCmsArticle3() {
		return cmsArticle3;
	}

	public void setCmsArticle3(List<CmsArticle> cmsArticle3) {
		this.cmsArticle3 = cmsArticle3;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<CmsArticle> getArticlesAnnounce() {
		return articlesAnnounce;
	}

	public void setArticlesAnnounce(List<CmsArticle> articlesAnnounce) {
		this.articlesAnnounce = articlesAnnounce;
	}

	public List<CmsArticle> getMenuArticles() {
		return menuArticles;
	}

	public void setMenuArticles(List<CmsArticle> menuArticles) {
		this.menuArticles = menuArticles;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public CmsArticleCondition getCondition() {
		return condition;
	}

	public void setCondition(CmsArticleCondition condition) {
		this.condition = condition;
	}

	public CmsArticle getItem() {
		return item;
	}

	public void setItem(CmsArticle item) {
		this.item = item;
	}

	public List<CmsChannel> getCmsChannels() {
		return cmsChannels;
	}

	public void setCmsChannels(List<CmsChannel> cmsChannels) {
		this.cmsChannels = cmsChannels;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public CmsChannel getChannel() {
		return channel;
	}

	public void setChannel(CmsChannel channel) {
		this.channel = channel;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<CmsArticle> getCmsArticles() {
		return cmsArticles;
	}

	public void setCmsArticles(List<CmsArticle> cmsArticles) {
		this.cmsArticles = cmsArticles;
	}

	public List<CmsAdvPhoto> getPhotosNo1() {
		return photosNo1;
	}

	public void setPhotosNo1(List<CmsAdvPhoto> photosNo1) {
		this.photosNo1 = photosNo1;
	}

	public List<CmsAdvPhoto> getPhotosNo2() {
		return photosNo2;
	}

	public void setPhotosNo2(List<CmsAdvPhoto> photosNo2) {
		this.photosNo2 = photosNo2;
	}

	public List<CmsAdvPhoto> getPhotosNo3() {
		return photosNo3;
	}

	public void setPhotosNo3(List<CmsAdvPhoto> photosNo3) {
		this.photosNo3 = photosNo3;
	}

	public List<String> getVisitCountList() {
		return visitCountList;
	}

	public void setVisitCountList(List<String> visitCountList) {
		this.visitCountList = visitCountList;
	}

	public List<String> getDownloadCountList() {
		return downloadCountList;
	}

	public void setDownloadCountList(List<String> downloadCountList) {
		this.downloadCountList = downloadCountList;
	}

	public List<CmsAdvPhoto> getPhotosNo4() {
		return photosNo4;
	}

	public void setPhotosNo4(List<CmsAdvPhoto> photosNo4) {
		this.photosNo4 = photosNo4;
	}

	/**
	 * @return the count
	 */
	public double getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(double count) {
		this.count = count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getBcid() {
		return bcid;
	}

	public void setBcid(Integer bcid) {
		this.bcid = bcid;
	}

	public CmsChannel getChannel1() {
		return channel1;
	}

	public void setChannel1(CmsChannel channel1) {
		this.channel1 = channel1;
	}

	public List<CmsArticle> getKeyNewsList() {
		return keyNewsList;
	}

	public void setKeyNewsList(List<CmsArticle> keyNewsList) {
		this.keyNewsList = keyNewsList;
	}

	public List<CmsArticle> getOrderNewsList() {
		return orderNewsList;
	}

	public void setOrderNewsList(List<CmsArticle> orderNewsList) {
		this.orderNewsList = orderNewsList;
	}

	public CmsAdvPhoto getPhoto() {
		return photo;
	}

	public void setPhoto(CmsAdvPhoto photo) {
		this.photo = photo;
	}

}
