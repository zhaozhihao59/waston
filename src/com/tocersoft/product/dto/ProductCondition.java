package com.tocersoft.product.dto;

import com.tocersoft.base.dto.BaseCondition;

public class ProductCondition extends BaseCondition {

	/** 类别id */
	private String categoryId;
	/** 品牌id */
	private String brandId;
	/** 产品名称 */
	private String name;
	/** 产品首图url */
	private String imageUrl;
	/** 搜索关键字 */
	private String keyWords;
	/** 产品简短描述 */
	private String shortDesc;
	/** 产品详情内容 */
	private String htmlContent;
	/** 产品视频地址 */
	private String videoUrl;
	/** 产品名称(英文) */
	private String nameEn;
	/** 默认平台价(英文) */
	private double unitPriceEn;
	/** 搜索关键字(英文) */
	private String keyWordsEn;
	/** 产品简短描述(英文) */
	private String shortDescEn;
	/** 产品详情内容 (英文)*/
	private String htmlContentEn;
	/** 是否为促销产品 0-不是促销产品，1-是促销产品 */
	private Integer isPromotion;
	/** 是否为推荐产品 0-不是推荐产品，1-是推荐产品 */
	private Integer isRecommend;
	/** 是否为明星产品 0-不是，1-是 */
	private Integer isStarProduct;
	
	/** 搜索产品时的价格区间 */
	private double minPrice;
	private double maxPrice;
	
	/** 排序 */
	private String sort;

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public double getUnitPriceEn() {
		return unitPriceEn;
	}

	public void setUnitPriceEn(double unitPriceEn) {
		this.unitPriceEn = unitPriceEn;
	}

	public String getKeyWordsEn() {
		return keyWordsEn;
	}

	public void setKeyWordsEn(String keyWordsEn) {
		this.keyWordsEn = keyWordsEn;
	}

	public String getShortDescEn() {
		return shortDescEn;
	}

	public void setShortDescEn(String shortDescEn) {
		this.shortDescEn = shortDescEn;
	}

	public String getHtmlContentEn() {
		return htmlContentEn;
	}

	public void setHtmlContentEn(String htmlContentEn) {
		this.htmlContentEn = htmlContentEn;
	}

	/** 类别id */
	public String getCategoryId(){
		return this.categoryId;
	}

	/** 类别id */
	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}
	/** 产品名称 */
	public String getName(){
		return this.name;
	}

	/** 产品名称 */
	public void setName(String name){
		this.name = name;
	}
	/** 产品首图url */
	public String getImageUrl(){
		return this.imageUrl;
	}

	/** 产品首图url */
	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}
	/** 搜索关键字 */
	public String getKeyWords(){
		return this.keyWords;
	}

	/** 搜索关键字 */
	public void setKeyWords(String keyWords){
		this.keyWords = keyWords;
	}
	/** 产品简短描述 */
	public String getShortDesc(){
		return this.shortDesc;
	}

	/** 产品简短描述 */
	public void setShortDesc(String shortDesc){
		this.shortDesc = shortDesc;
	}
	/** 产品详情内容 */
	public String getHtmlContent(){
		return this.htmlContent;
	}

	/** 产品详情内容 */
	public void setHtmlContent(String htmlContent){
		this.htmlContent = htmlContent;
	}
	/** 产品视频地址 */
	public String getVideoUrl(){
		return this.videoUrl;
	}

	/** 产品视频地址 */
	public void setVideoUrl(String videoUrl){
		this.videoUrl = videoUrl;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getIsPromotion() {
		return isPromotion;
	}

	public void setIsPromotion(Integer isPromotion) {
		this.isPromotion = isPromotion;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	public Integer getIsStarProduct() {
		return isStarProduct;
	}

	public void setIsStarProduct(Integer isStarProduct) {
		this.isStarProduct = isStarProduct;
	}
	
}
