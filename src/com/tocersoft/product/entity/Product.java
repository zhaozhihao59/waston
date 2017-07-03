package com.tocersoft.product.entity;
import java.util.ArrayList;
import java.util.List;

import com.tocersoft.base.entity.BaseBusEntity;
import com.tocersoft.product.dto.ProductDownloadVO;
import com.tocersoft.system.entity.SysUploadFile;

/**
 * 产品表 - V1.5
 * 
 * 描述：
 * 1、加入了品牌ID
 * 
 * @creator 方泉
 */
public class Product extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 敦煌网提供产品的ID */
	private String productNo;
	/** 类别ID */
	private String categoryId;
	/** 类别名称 */
	private String categoryName;
	/** 品牌ID */
	private String brandId;
	/** 产品名称 */
	private String name;
	/** 默认市场价 */
	private String marketPrice;
	/** 默认平台价 */
	private Double unitPrice;
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
	private String unitPriceEn;
	/** 搜索关键字(英文) */
	private String keyWordsEn;
	/** 产品简短描述(英文) */
	private String shortDescEn;
	/** 产品详情内容 (英文)*/
	private String htmlContentEn;
	/** 品牌名称 */
	private String brandName;
	/** 是否促销 0-不促销，1-促销 */
	private Integer isPromotion;
	/** 折扣 */
	private Double discount;
	/** 是否推荐 0-不推荐，1-推荐 */
	private Integer isRecommend;
	/** 是否为明星产品 0-不是，1-是 */
	private Integer isStarProduct;
	
	
	// ------------临时字段
	private String attr;
	private String attrVal;
	private String brandImg;
	private int sales;
	private String origin;
	
	private List<ProductDownloadVO> voList = new ArrayList<ProductDownloadVO>();
	
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	/** 产品图片列表 */
	private List<SysUploadFile> prodAttachList;
	/** 产品属性列表 */
	private List<ProductAttr> prodAttrList;
	/** 产品备货信息 */
	private ProductInventory prodInventory;
	/** 产品包装信息 */
	private ProductPackage prodPackage;
	/** 产品销售属性设置 */
	private ProductSaleSetting prodSaleSetting;
	/** 产品SKU列表 */
	private List<ProductSku> prodSkuList;
	/** 产品自定义属性列表,最多10条 */
	private List<ProductSpecSelfDef> prodSpecSelfDefList;
	/** 产品折扣区间 */
	private List<ProductWholesaleRange> prodWholesaleRangeList;

	
	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}


	public String getUnitPriceEn() {
		return unitPriceEn;
	}

	public void setUnitPriceEn(String unitPriceEn) {
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

	public List<SysUploadFile> getProdAttachList() {
		return prodAttachList;
	}

	public void setProdAttachList(List<SysUploadFile> prodAttachList) {
		this.prodAttachList = prodAttachList;
	}

	public List<ProductAttr> getProdAttrList() {
		return prodAttrList;
	}

	public void setProdAttrList(List<ProductAttr> prodAttrList) {
		this.prodAttrList = prodAttrList;
	}

	public ProductInventory getProdInventory() {
		return prodInventory;
	}

	public void setProdInventory(ProductInventory prodInventory) {
		this.prodInventory = prodInventory;
	}

	public ProductPackage getProdPackage() {
		return prodPackage;
	}

	public void setProdPackage(ProductPackage prodPackage) {
		this.prodPackage = prodPackage;
	}

	public ProductSaleSetting getProdSaleSetting() {
		return prodSaleSetting;
	}

	public void setProdSaleSetting(ProductSaleSetting prodSaleSetting) {
		this.prodSaleSetting = prodSaleSetting;
	}

	public List<ProductSku> getProdSkuList() {
		return prodSkuList;
	}

	public void setProdSkuList(List<ProductSku> prodSkuList) {
		this.prodSkuList = prodSkuList;
	}

	public List<ProductSpecSelfDef> getProdSpecSelfDefList() {
		return prodSpecSelfDefList;
	}

	public void setProdSpecSelfDefList(List<ProductSpecSelfDef> prodSpecSelfDefList) {
		this.prodSpecSelfDefList = prodSpecSelfDefList;
	}

	public List<ProductWholesaleRange> getProdWholesaleRangeList() {
		return prodWholesaleRangeList;
	}

	public void setProdWholesaleRangeList(
			List<ProductWholesaleRange> prodWholesaleRangeList) {
		this.prodWholesaleRangeList = prodWholesaleRangeList;
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
	
	/** 默认市场价 */
	public String getMarketPrice() {
		return marketPrice;
	}
	/** 默认市场价 */
	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
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

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getAttrVal() {
		return attrVal;
	}

	public void setAttrVal(String attrVal) {
		this.attrVal = attrVal;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getIsPromotion() {
		return isPromotion;
	}

	public void setIsPromotion(Integer isPromotion) {
		this.isPromotion = isPromotion;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
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

	public String getBrandImg() {
		return brandImg;
	}

	public void setBrandImg(String brandImg) {
		this.brandImg = brandImg;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public List<ProductDownloadVO> getVoList() {
		return voList;
	}

	public void setVoList(List<ProductDownloadVO> voList) {
		this.voList = voList;
	}
}