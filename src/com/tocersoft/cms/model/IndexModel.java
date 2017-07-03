package com.tocersoft.cms.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tocersoft.activity.entity.Activity;
import com.tocersoft.base.model.BaseModel;
import com.tocersoft.base.page.PageResult;
import com.tocersoft.cms.entity.CmsAdvPhoto;
import com.tocersoft.cms.entity.CmsArticle;
import com.tocersoft.cms.entity.CmsChannel;
import com.tocersoft.game.entity.Game;
import com.tocersoft.product.entity.Product;
import com.tocersoft.product.entity.ProductBrand;
import com.tocersoft.product.entity.ProductCategory;
import com.tocersoft.professional.entity.Professional;
import com.tocersoft.system.entity.SysDictItem;
import com.tocersoft.system.entity.SysUploadFile;

public class IndexModel extends BaseModel<CmsArticle> {

	private static final long serialVersionUID = 4346507314203660761L;
	/**
	 * 文章实体
	 */
	private CmsArticle item = new CmsArticle();
	/**
	 * 栏目实体
	 */
	private CmsChannel cmsChannel = new CmsChannel();
	
	/**
	 * 文章实体list
	 */
	private List<CmsArticle> listCmsArticles = new ArrayList<CmsArticle>();
	/**
	 * 栏目集合
	 */
	private List<CmsChannel> cmsChannels = new ArrayList<CmsChannel>();
	
	/**
	 * 下载中心 集合
	 */
	private List<CmsArticle> cmsList1 = new ArrayList<CmsArticle>();
	
	/**
	 * 下载中心 集合
	 */
	private List<CmsArticle> cmsList2 = new ArrayList<CmsArticle>();
	
	/**
	 * 下载中心 集合
	 */
	private List<CmsArticle> cmsList3 = new ArrayList<CmsArticle>();
	
	/**
	 * 下载中心 集合
	 */
	private List<CmsArticle> cmsList4 = new ArrayList<CmsArticle>();
	
	/**
	 * 下载中心 集合
	 */
	private List<CmsArticle> cmsList5 = new ArrayList<CmsArticle>();
	
	
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
	 * 栏目ID
	 * @return
	 */
	private String channelId;

	/**
	 * 页码
	 * @return
	 */
	private int page;
	/** 轮转首图 */
	private List<CmsAdvPhoto> cmsAdvPhotos1 = new ArrayList<CmsAdvPhoto>();
	/** 小图广告 */
	private List<CmsAdvPhoto> cmsAdvPhotos2 = new ArrayList<CmsAdvPhoto>();
	/** 中间图片 */
	private List<CmsAdvPhoto> cmsAdvPhotos3 = new ArrayList<CmsAdvPhoto>();
	/** 父类别集合 */
	private List<ProductCategory> listParentCategory;
	/** 产品类别 */
	private ProductCategory productCategory;
	/** 产品品牌 */
	private List<ProductBrand> productBrandList = new ArrayList<ProductBrand>();
	private List<SysDictItem> sysDictItemList = new ArrayList<SysDictItem>();
	/** 折扣产品集合 */
	private List<Product> discountProducts = new ArrayList<Product>();
	/** 产品顶级类别 */
	List<ProductCategory> topProductCatgory = new ArrayList<ProductCategory>();
	/** 明星品牌集合 */
	List<ProductBrand> productBrands = new ArrayList<ProductBrand>();
	/** 明星产品集合 */
	List<Product> starProducts = new ArrayList<Product>();
	/** 不同类别的推荐产品 */
	Map<String,List<Product>> map = new HashMap<String,List<Product>>();
	/** 赛事照片分页器 */
	private PageResult<SysUploadFile> photoPageResult = new PageResult<SysUploadFile>();
	//正在报名的赛事
	List<Game> gameList=new ArrayList<Game>();
	/** 首页新闻跑马灯 */
	private List<CmsArticle> ListCmsArticle = new ArrayList<CmsArticle>();
	/** 首页华诚动态 */
	private List<CmsArticle> ListCmsArticle1 = new ArrayList<CmsArticle>();
	/** ArticleByComplex 华城动态*/
	private List<CmsArticle> ListArticleByComplex1 = new ArrayList<CmsArticle>();
	/** ArticleByComplex 案例分析*/
	private List<CmsArticle> ListArticleByComplex2 = new ArrayList<CmsArticle>();
	/** ArticleByComplex 出版物*/
	private List<CmsArticle> ListArticleByComplex3 = new ArrayList<CmsArticle>();
	/**视频*/
	private List<CmsArticle> videoArticleList = new ArrayList<CmsArticle>();
	private String en;
	private String businessEn;
	
	public String getBusinessEn() {
		return businessEn;
	}

	public void setBusinessEn(String businessEn) {
		this.businessEn = businessEn;
	}

	public String getEn() {
		return en;
	}

	public void setEn(String en) {
		this.en = en;
	}

	public List<CmsArticle> getVideoArticleList() {
		return videoArticleList;
	}

	public void setVideoArticleList(List<CmsArticle> videoArticleList) {
		this.videoArticleList = videoArticleList;
	}

	private String searchName;
	
	private List<Activity> listActivity = new ArrayList<Activity>();
	private Activity activity;
	
	private PageResult<Professional> professionalPageResult = new PageResult<Professional>();
	private Professional professional;
	
	private String qualification;
	private Integer type;
	
	
	List<Activity> listActivityPrediction = new ArrayList<Activity>();
	private PageResult<Activity> activityPageResult = new PageResult<Activity>();
 
	private Integer status;
	
	private PageResult<CmsArticle> cmsArticlePageResult = new PageResult<CmsArticle>();

 
	
	public PageResult<CmsArticle> getCmsArticlePageResult() {
		return cmsArticlePageResult;
	}

	public void setCmsArticlePageResult(PageResult<CmsArticle> cmsArticlePageResult) {
		this.cmsArticlePageResult = cmsArticlePageResult;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public PageResult<Activity> getActivityPageResult() {
		return activityPageResult;
	}

	public void setActivityPageResult(PageResult<Activity> activityPageResult) {
		this.activityPageResult = activityPageResult;
	}

	public List<Activity> getListActivityPrediction() {
		return listActivityPrediction;
	}

	public void setListActivityPrediction(List<Activity> listActivityPrediction) {
		this.listActivityPrediction = listActivityPrediction;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public PageResult<Professional> getProfessionalPageResult() {
		return professionalPageResult;
	}

	public void setProfessionalPageResult(
			PageResult<Professional> professionalPageResult) {
		this.professionalPageResult = professionalPageResult;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public List<CmsArticle> getListArticleByComplex1() {
		return ListArticleByComplex1;
	}

	public List<CmsArticle> getListArticleByComplex2() {
		return ListArticleByComplex2;
	}

	public void setListArticleByComplex2(List<CmsArticle> listArticleByComplex2) {
		ListArticleByComplex2 = listArticleByComplex2;
	}

	public List<CmsArticle> getListArticleByComplex3() {
		return ListArticleByComplex3;
	}

	public void setListArticleByComplex3(List<CmsArticle> listArticleByComplex3) {
		ListArticleByComplex3 = listArticleByComplex3;
	}

	public void setListArticleByComplex1(List<CmsArticle> listArticleByComplex1) {
		ListArticleByComplex1 = listArticleByComplex1;
	}

	public List<CmsArticle> getListCmsArticle1() {
		return ListCmsArticle1;
	}

	public void setListCmsArticle1(List<CmsArticle> listCmsArticle1) {
		ListCmsArticle1 = listCmsArticle1;
	}

	public List<CmsArticle> getListCmsArticle() {
		return ListCmsArticle;
	}

	public void setListCmsArticle(List<CmsArticle> listCmsArticle) {
		ListCmsArticle = listCmsArticle;
	}

	public List<SysDictItem> getSysDictItemList() {
		return sysDictItemList;
	}

	public void setSysDictItemList(List<SysDictItem> sysDictItemList) {
		this.sysDictItemList = sysDictItemList;
	}

	public List<ProductBrand> getProductBrandList() {
		return productBrandList;
	}

	public void setProductBrandList(List<ProductBrand> productBrandList) {
		this.productBrandList = productBrandList;
	}

	public List<ProductCategory> getListParentCategory() {
		return listParentCategory;
	}

	public void setListParentCategory(List<ProductCategory> listParentCategory) {
		this.listParentCategory = listParentCategory;
	}



	public ProductCategory getProductCategory() {
		return productCategory;
	}



	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
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



	public List<CmsAdvPhoto> getPhotosNo4() {
		return photosNo4;
	}



	public void setPhotosNo4(List<CmsAdvPhoto> photosNo4) {
		this.photosNo4 = photosNo4;
	}



	public int getPage() {
		return page;
	}



	public void setPage(int page) {
		this.page = page;
	}



	public String getChannelId() {
		return channelId;
	}



	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}



	public List<CmsArticle> getCmsList1() {
		return cmsList1;
	}



	public void setCmsList1(List<CmsArticle> cmsList1) {
		this.cmsList1 = cmsList1;
	}



	public List<CmsArticle> getCmsList2() {
		return cmsList2;
	}



	public void setCmsList2(List<CmsArticle> cmsList2) {
		this.cmsList2 = cmsList2;
	}



	public List<CmsArticle> getCmsList3() {
		return cmsList3;
	}



	public void setCmsList3(List<CmsArticle> cmsList3) {
		this.cmsList3 = cmsList3;
	}



	public List<CmsArticle> getCmsList4() {
		return cmsList4;
	}



	public void setCmsList4(List<CmsArticle> cmsList4) {
		this.cmsList4 = cmsList4;
	}



	public List<CmsAdvPhoto> getCmsAdvPhotos1() {
		return cmsAdvPhotos1;
	}

	public void setCmsAdvPhotos1(List<CmsAdvPhoto> cmsAdvPhotos1) {
		this.cmsAdvPhotos1 = cmsAdvPhotos1;
	}

	public List<CmsAdvPhoto> getCmsAdvPhotos2() {
		return cmsAdvPhotos2;
	}

	public void setCmsAdvPhotos2(List<CmsAdvPhoto> cmsAdvPhotos2) {
		this.cmsAdvPhotos2 = cmsAdvPhotos2;
	}

	public List<CmsArticle> getCmsList5() {
		return cmsList5;
	}



	public void setCmsList5(List<CmsArticle> cmsList5) {
		this.cmsList5 = cmsList5;
	}

	public List<Product> getDiscountProducts() {
		return discountProducts;
	}

	public void setDiscountProducts(List<Product> discountProducts) {
		this.discountProducts = discountProducts;
	}

	public List<ProductCategory> getTopProductCatgory() {
		return topProductCatgory;
	}

	public void setTopProductCatgory(List<ProductCategory> topProductCatgory) {
		this.topProductCatgory = topProductCatgory;
	}

	public List<ProductBrand> getProductBrands() {
		return productBrands;
	}

	public void setProductBrands(List<ProductBrand> productBrands) {
		this.productBrands = productBrands;
	}

	public Map<String, List<Product>> getMap() {
		return map;
	}

	public void setMap(Map<String, List<Product>> map) {
		this.map = map;
	}

	public List<Product> getStarProducts() {
		return starProducts;
	}

	public void setStarProducts(List<Product> starProducts) {
		this.starProducts = starProducts;
	}

	public List<CmsAdvPhoto> getCmsAdvPhotos3() {
		return cmsAdvPhotos3;
	}

	public void setCmsAdvPhotos3(List<CmsAdvPhoto> cmsAdvPhotos3) {
		this.cmsAdvPhotos3 = cmsAdvPhotos3;
	}

	public List<Game> getGameList() {
		return gameList;
	}

	public void setGameList(List<Game> gameList) {
		this.gameList = gameList;
	}

	public PageResult<SysUploadFile> getPhotoPageResult() {
		return photoPageResult;
	}

	public void setPhotoPageResult(PageResult<SysUploadFile> photoPageResult) {
		this.photoPageResult = photoPageResult;
	}

	public List<Activity> getListActivity() {
		return listActivity;
	}

	public void setListActivity(List<Activity> listActivity) {
		this.listActivity = listActivity;
	}

	public Professional getProfessional() {
		return professional;
	}

	public void setProfessional(Professional professional) {
		this.professional = professional;
	}

	public List<CmsArticle> getListCmsArticles() {
		return listCmsArticles;
	}

	public void setListCmsArticles(List<CmsArticle> listCmsArticles) {
		this.listCmsArticles = listCmsArticles;
	}

	public CmsChannel getCmsChannel() {
		return cmsChannel;
	}

	public void setCmsChannel(CmsChannel cmsChannel) {
		this.cmsChannel = cmsChannel;
	}
	
	
}
