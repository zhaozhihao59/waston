package com.tocersoft.product.model;

import java.util.ArrayList;
import java.util.List;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.base.page.PageResult;
import com.tocersoft.product.dto.ProductCondition;
import com.tocersoft.product.entity.Product;
import com.tocersoft.product.entity.ProductBrand;
import com.tocersoft.product.entity.ProductCategory;
import com.tocersoft.product.entity.ProductCategoryAttribute;
import com.tocersoft.product.entity.ProductCategoryAttributeValue;
import com.tocersoft.product.entity.ProductComment;
import com.tocersoft.product.entity.ProductLogistics;
import com.tocersoft.product.entity.ProductPackage;
import com.tocersoft.product.entity.ProductWholesaleRange;
import com.tocersoft.system.entity.SysUploadFile;

public class ProductModel extends BaseModel<Product>{
	private Product product=new Product();
	
	private ProductWholesaleRange productRange = new ProductWholesaleRange();
	
	private String item;
	
	private String id;
	
	private String cid;
	
	private ProductCategory category;
	
	private String ids;
	
	private Integer flag;
	
	// 当前页
	private int page;
	
	//当前产品以关联的属性值
	private List<ProductCategoryAttributeValue> productCategoryAttrValueList;
	
	private List<ProductCategoryAttribute> productCategoryAttrList;
	
	private ProductCondition condition = new ProductCondition();
	// 产品的图片
	private List<SysUploadFile> sysUploadFiles = new ArrayList<SysUploadFile>();
	
	/** 产品接口 - 授权帐号 */
	private String authAccount;
	
	/** 产品接口 - 授权密码 */
	private String authPassword;
	
	private ProductBrand productBrand = new ProductBrand();
	
	private List<ProductBrand> listProductBrand;
	
	private String brandId;
	
	private String categoryId;
	
	private Integer isStarProduct;
	
	private int markSum;
	
	/** 判断是类别查询还是品牌查询 */
	private Integer chosess;
	
	/** 导航条的类别名称或者品牌名称 */
	private String categoryAndBrandName;
	private PageResult<ProductComment> pcPageResult = new PageResult<ProductComment>();
//	private List<CartItem> cartItemList = new ArrayList<CartItem>();
	/** 产品评论集合 */
	private List<ProductComment> productComments = new ArrayList<ProductComment>();
	
	/** 子类别集合 */
	private List<ProductCategory> productCategories = new ArrayList<ProductCategory>();
	
	private List<Product> productList;
	
	/** 同类同品牌产品 */
	private List<Product> similarProduct = new ArrayList<Product>();
	/** 热销产品 */
	private List<Product> sellWellProducts = new ArrayList<Product>();
	/** 所有产品消费金额 */
	private double shopMoney;
	
	private ProductLogistics productLogistics = new ProductLogistics();
	private ProductPackage productPackage = new ProductPackage();
	
	private Integer count;
	
	private String productId;
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public double getShopMoney() {
		return shopMoney;
	}

	public void setShopMoney(double shopMoney) {
		this.shopMoney = shopMoney;
	}

//	public List<CartItem> getCartItemList() {
//		return cartItemList;
//	}
//
//	public void setCartItemList(List<CartItem> cartItemList) {
//		this.cartItemList = cartItemList;
//	}

	public String getCategoryAndBrandName() {
		return categoryAndBrandName;
	}

	public void setCategoryAndBrandName(String categoryAndBrandName) {
		this.categoryAndBrandName = categoryAndBrandName;
	}

	
	
	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public Integer getChosess() {
		return chosess;
	}

	public void setChosess(Integer chosess) {
		this.chosess = chosess;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public List<ProductBrand> getListProductBrand() {
		return listProductBrand;
	}

	public void setListProductBrand(List<ProductBrand> listProductBrand) {
		this.listProductBrand = listProductBrand;
	}

	public String getAuthAccount() {
		return authAccount;
	}

	public void setAuthAccount(String authAccount) {
		this.authAccount = authAccount;
	}

	public String getAuthPassword() {
		return authPassword;
	}

	public void setAuthPassword(String authPassword) {
		this.authPassword = authPassword;
	}

	public ProductModel() {
		super();
	}

	public String getItem() {
		return item;
	}

	public ProductCondition getCondition() {
		return condition;
	}

	public void setCondition(ProductCondition condition) {
		this.condition = condition;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public List<ProductCategoryAttribute> getProductCategoryAttrList() {
		return productCategoryAttrList;
	}

	public void setProductCategoryAttrList(
			List<ProductCategoryAttribute> productCategoryAttrList) {
		this.productCategoryAttrList = productCategoryAttrList;
	}

	public ProductWholesaleRange getProductRange() {
		return productRange;
	}

	public void setProductRange(ProductWholesaleRange productRange) {
		this.productRange = productRange;
	}

	public List<ProductCategoryAttributeValue> getProductCategoryAttrValueList() {
		return productCategoryAttrValueList;
	}

	public void setProductCategoryAttrValueList(List<ProductCategoryAttributeValue> productCategoryAttrValueList) {
		this.productCategoryAttrValueList = productCategoryAttrValueList;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<Product> getSimilarProduct() {
		return similarProduct;
	}

	public void setSimilarProduct(List<Product> similarProduct) {
		this.similarProduct = similarProduct;
	}

	public List<SysUploadFile> getSysUploadFiles() {
		return sysUploadFiles;
	}

	public void setSysUploadFiles(List<SysUploadFile> sysUploadFiles) {
		this.sysUploadFiles = sysUploadFiles;
	}

	public List<Product> getSellWellProducts() {
		return sellWellProducts;
	}

	public void setSellWellProducts(List<Product> sellWellProducts) {
		this.sellWellProducts = sellWellProducts;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List<ProductCategory> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(List<ProductCategory> productCategories) {
		this.productCategories = productCategories;
	}

	public ProductLogistics getProductLogistics() {
		return productLogistics;
	}

	public void setProductLogistics(ProductLogistics productLogistics) {
		this.productLogistics = productLogistics;
	}

	public ProductPackage getProductPackage() {
		return productPackage;
	}

	public void setProductPackage(ProductPackage productPackage) {
		this.productPackage = productPackage;
	}

	public ProductBrand getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(ProductBrand productBrand) {
		this.productBrand = productBrand;
	}

	public Integer getIsStarProduct() {
		return isStarProduct;
	}

	public void setIsStarProduct(Integer isStarProduct) {
		this.isStarProduct = isStarProduct;
	}

	public List<ProductComment> getProductComments() {
		return productComments;
	}

	public void setProductComments(List<ProductComment> productComments) {
		this.productComments = productComments;
	}

	public PageResult<ProductComment> getPcPageResult() {
		return pcPageResult;
	}

	public void setPcPageResult(PageResult<ProductComment> pcPageResult) {
		this.pcPageResult = pcPageResult;
	}

	public int getMarkSum() {
		return markSum;
	}

	public void setMarkSum(int markSum) {
		this.markSum = markSum;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
}
