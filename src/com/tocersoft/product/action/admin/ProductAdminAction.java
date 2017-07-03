package com.tocersoft.product.action.admin;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.simple.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.tocersoft.auth.entity.User;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.utils.Excel2007Util;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.order.entity.FreightTemplate;
import com.tocersoft.order.service.IFreightTemplateService;
import com.tocersoft.product.dto.ProductBrandCondition;
import com.tocersoft.product.dto.ProductCategoryAttributeCondition;
import com.tocersoft.product.dto.ProductCondition;
import com.tocersoft.product.dto.ProductPackageCondition;
import com.tocersoft.product.entity.Product;
import com.tocersoft.product.entity.ProductBrand;
import com.tocersoft.product.entity.ProductCategory;
import com.tocersoft.product.entity.ProductCategoryAttribute;
import com.tocersoft.product.entity.ProductLogistics;
import com.tocersoft.product.entity.ProductPackage;
import com.tocersoft.product.entity.ProductWholesaleRange;
import com.tocersoft.product.model.ProductModel;
import com.tocersoft.product.service.IProductBrandService;
import com.tocersoft.product.service.IProductCategoryAttributeService;
import com.tocersoft.product.service.IProductCategoryAttributeValueService;
import com.tocersoft.product.service.IProductCategoryService;
import com.tocersoft.product.service.IProductLogisticsService;
import com.tocersoft.product.service.IProductPackageService;
import com.tocersoft.product.service.IProductService;
import com.tocersoft.product.service.IProductSkuAttrvalService;
import com.tocersoft.product.service.IProductSkuService;
import com.tocersoft.product.service.IProductWholesaleRangeService;

/**
 * 产品商城-类别
 * @author fangquan
 * @createDate 2013-10-28 上午10:10:17
 */
@ParentPackage("admin")
@Namespace("/admin/product")
@Controller
public class ProductAdminAction extends BaseAction implements ModelDriven<ProductModel> {
	
	private static final long serialVersionUID = -8337375042837415260L;
	
	private Log logger = LogFactory.getLog(ProductAdminAction.class);
	
	private ProductModel model = new ProductModel();
	
	@Resource
	private IProductService productService;
	@Resource
	private IProductCategoryService categoryService;
	@Resource
	private IProductCategoryAttributeService categoryAttributeService;
	@Resource
	private IProductCategoryAttributeValueService categoryAttributeValueService;
	@Resource(name = "productWholesaleRangeServiceImpl")
	private IProductWholesaleRangeService productWholesaleRangeService;
	@Resource(name = "productSkuAttrvalServiceImpl")
	private IProductSkuAttrvalService productSkuAttrvalService;
	@Resource(name = "productSkuServiceImpl")
	private IProductSkuService productSkuService;
	@Resource(name = "productBrandServiceImpl")
	private IProductBrandService productBrandService;
	@Resource(name = "productLogisticsServiceImpl")
	private IProductLogisticsService productLogisticsService;
	@Resource(name = "freightTemplateServiceImpl")
	private IFreightTemplateService freightTemplateService;
	@Resource(name = "productPackageServiceImpl")
	private IProductPackageService packageService;
	
	/**
	 * 打开添加页面
	 * @return
	 */
	@Action(value = "toAdd", results={@Result(name="index",location="/WEB-INF/pages/admin/product/product_add.jsp")})
	public String toAdd(){
		Product item = new Product();
		// 产品类别
		String categoryId = model.getCid();
		ProductCategory category = categoryService.getProductCategoryById(categoryId);
		item.setCategoryId(category.getId());
		item.setCategoryName(category.getName());
		ProductBrandCondition condition = new ProductBrandCondition();
		List<ProductBrand> listProductBrand = productBrandService.listProductBrand(condition);
		model.setListProductBrand(listProductBrand);
		model.setProduct(item);
		model.setCategory(category);
		return INDEX;
	}
	
	/** 导入 */
	@Action(value="doImport")
	public String doImport(){
		String message = "";
		try {
			String path = getRequest().getParameter("path");
			message = productService.doImport(path);
		} catch (Exception e) {
			message = e.getMessage();
		}
		
		return ajax(Status.success,message);
	}
	
	/**
	 * 批量导出产品
	 */
	@Action(value = "batchExport")
	public void batchExport(){
		try {
			// 取得需要导出的数据
			Workbook wb = productService.batchExport();
			// 导出excel数据
			Excel2007Util.exportExcelData("产品信息", getResponse(), wb);
		} catch (Exception e) {
			String message = e.getMessage();
			logger.error("导出产品信息异常" + message);
		}
	}
	
	/**
	 * 新增/修改
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Action(value="doAdd")
	public String doAdd(){
		Product item = model.getProduct();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		item.setCreateBy(user.getName());
		item.setUpdateBy(user.getName());
		item.setIsPromotion(0);
		item.setIsRecommend(0);
		item.setIsStarProduct(0);
		productService.add(item);
		String message = item.getId();
		return ajax(Status.success,message);
		
//		JSONObject result = new JSONObject();
//		
//		// 接口授权验证
//		String authAccount = model.getAuthAccount();
//		String authPassword = model.getAuthPassword();
//		if(StringUtils.isBlank(authAccount) || StringUtils.isBlank(authPassword)){
//			result.put("status", "100");
//			result.put("message", "验证不通过-接口授权帐号或密码为空");
//			return ajax(result);
//		}else{
//			if(authAccount.equals("dunhuang") && authPassword.equals("DunHuang2014!@#")){
//				
//			}else{
//				result.put("status", "200");
//				result.put("message", "验证不通过-接口授权帐号或密码错误");
//				return ajax(result);
//			}
//		}
//		
//		String itemString = model.getItem();
//		JSONParser parser = new JSONParser();
//		try {
//			JSONObject obj = (JSONObject)parser.parse(itemString);
//			productService.addApi(obj);
//			
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			
//			result.put("status", "300");
//			result.put("message", "接口调用出现异常，请检查数据类型是否匹配，或者请求管理员查看系统日志");
//			return ajax(result);
//		}
//		
//		result.put("status", "000");
//		result.put("message", "保存成功");
//		return ajax(result);
	}
	
	
	@Action(value = "index", results = { @Result(name = "index", location = "/WEB-INF/pages/admin/product/product_list.jsp") })
	public String index(){
		ProductBrandCondition condition = new ProductBrandCondition();
		List<ProductBrand> listProductBrand = productBrandService.listProductBrand(condition);
		model.setListProductBrand(listProductBrand);
		return INDEX;
	}
	
	/**
	 * 分页查询
	 * @return
	 */
	@Action(value = "searchProductList")
	public String searchProductList(){
		ProductCondition condition = model.getCondition();
		if(StringUtils.isNotBlank(condition.getCategoryId()) && (condition.getCategoryId().equals("1") || "0".equals(condition.getCategoryId()))){
			condition.setCategoryId(null);
		}
		productService.listProductByPage(model.getPageResult(), condition);
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{
			"id","name","imageUrl","keyWords","shortDesc","htmlContent","videoUrl","categoryId",
			"isPromotion","isRecommend","isStarProduct","discount"
		});
		return ajax(root);
	}
	
	/**
	 * 跳转到促销产品
	 * @return
	 */
	@Action(value = "toPromotion", results = {@Result(name="success" ,location="/WEB-INF/pages/admin/product/product_promotion_list.jsp")})
	public String toPromotion(){
		return SUCCESS;
	}
	
	/**
	 * 加入促销产品
	 * @return
	 */
	@Action(value = "addPromotion")
	public String addPromotion(){
		String ids = model.getIds();
		String[] productIds = ids.split(",");
		double discount = model.getProduct().getDiscount();
		productService.updateIsPromotion(productIds, 1, discount);
		return ajax(Status.success);
	}
	
	/**
	 * 取消促销产品
	 * @return
	 */
	@Action(value = "cancelPromotion")
	public String cancelPromotion(){
		String ids = model.getIds();
		String[] productIds = ids.split(",");
		productService.updateIsPromotion(productIds, 0, 0.0);
		return ajax(Status.success);
	}
	
	/**
	 * 查询促销产品(分页查询)
	 * @return
	 */
	@Action(value = "searchPromotionProductList")
	public String searchPromotionProductList(){
		ProductCondition condition = model.getCondition();
		productService.listProductByPage(model.getPageResult(), condition);
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{
			"id","name","imageUrl","keyWords","shortDesc","discount"
		});
		return ajax(root);
	}
	
	/**
	 * 跳转到推荐产品
	 * @return
	 */
	@Action(value = "toRecommend", results = {@Result(name="success" ,location="/WEB-INF/pages/admin/product/product_recommend_list.jsp")})
	public String toRecommend(){
		return SUCCESS;
	}
	
	/**
	 * 加入推荐产品
	 * @return
	 */
	@Action(value = "addRecommend")
	public String addRecommend(){
		String ids = model.getIds();
		String[] productIds = ids.split(",");
		productService.updateIsRecommend(productIds, 1);
		return ajax(Status.success);
	}
	
	/**
	 * 取消推荐产品
	 * @return
	 */
	@Action(value = "cancelRecommend")
	public String cancelRecommend(){
		String ids = model.getIds();
		String[] productIds = ids.split(",");
		productService.updateIsRecommend(productIds, 0);
		return ajax(Status.success);
	}
	

	/**
	 * 查询推荐产品(分页查询)
	 * @return
	 */
	@Action(value = "searchRecommendProductList")
	public String searchRecommendProductList(){
		ProductCondition condition = model.getCondition();
		productService.listProductByPage(model.getPageResult(), condition);
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{
			"id","name","imageUrl","keyWords","shortDesc"
		});
		return ajax(root);
	}
	
	/**
	 * 跳转到明星产品
	 * @return
	 */
	@Action(value = "toStar", results = {@Result(name="success" ,location="/WEB-INF/pages/admin/product/product_star_list.jsp")})
	public String toStar(){
		return SUCCESS;
	}
	
	/**
	 * 加入明星产品
	 * @return
	 */
	@Action(value = "addStar")
	public String addStar(){
		String ids = model.getIds();
		String[] productIds = ids.split(",");
		productService.updateIsStar(productIds, 1);
		return ajax(Status.success);
	}
	
	/**
	 * 取消明星产品
	 * @return
	 */
	@Action(value = "cancelStar")
	public String cancelStar(){
		String ids = model.getIds();
		String[] productIds = ids.split(",");
		productService.updateIsStar(productIds, 0);
		return ajax(Status.success);
	}
	

	/**
	 * 查询明星产品(分页查询)
	 * @return
	 */
	@Action(value = "searchStarProductList")
	public String searchStarProductList(){
		ProductCondition condition = model.getCondition();
		productService.listProductByPage(model.getPageResult(), condition);
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{
			"id","name","imageUrl","keyWords","shortDesc"
		});
		return ajax(root);
	}
	
	/**
	 * 批量更改产品类别
	 * @return
	 */
	@Action(value = "changeCategory")
	public String changeCategory(){
		String ids = model.getIds();
		String categoryId = model.getCategoryId();
		// 判断选择的类别是否为子类别
		ProductCategory category = categoryService.getProductCategoryById(categoryId);
		if(category != null && (null == category.getIsParent() || category.getIsParent() == 0)){
			productService.changeCategory(ids.split(","),categoryId);
			return ajax(Status.success);
		}else{
			return ajax(Status.error,"更改失败,只能选择三级子类");
		}
	}
	
	/**
	 * 打开修改页面
	 * @return
	 */
	@Action(value = "toUpdate", results={@Result(name="index",location="/WEB-INF/pages/admin/product/product_add.jsp")} )
	public String toUpdate(){
		String id = model.getId();
		Product product = productService.getProductById(id);
		
		// 考虑一下产品没有品牌ID的情况下
		if(StringUtils.isNotBlank(product.getBrandId())){
			ProductBrand productbrand = productBrandService.getProductBrandById(product.getBrandId());
			if(null != productbrand){
				product.setBrandName(productbrand.getName());
			}
		}
		
		// 根据产品ID得到其物流信息
		List<ProductLogistics> list = productLogisticsService.getProductLogisticsByProductId(id);
		if(list != null && list.size()>0){
			ProductLogistics productLogistics = list.get(0);
			FreightTemplate freightTemplate = freightTemplateService.getFreightTemplateById(productLogistics.getTemplateId());
			if(freightTemplate != null){
				productLogistics.setTemplateName(freightTemplate.getTemplateName());
			}
			model.setProductLogistics(productLogistics);
		}
		
		// 根据产品ID得到其包装信息
		ProductPackageCondition packageCondition = new ProductPackageCondition();
		packageCondition.setProductId(id);
		List<ProductPackage> packages = packageService.listProductPackageByCondition(packageCondition);
		if(packages != null && packages.size()>0){
			model.setProductPackage(packages.get(0));
		}
		
		// 根据产品的类别ID得到对应的产品类别
		ProductCategory category = categoryService.getProductCategoryById(product.getCategoryId());
		if(null != category){
			product.setCategoryName(category.getName());
		}
		
		// 加载sku页签下的属性值复选框数据
		// 根据类别ID与销售属性两个条件得到产品类别属性集合
		ProductCategoryAttributeCondition condition=new ProductCategoryAttributeCondition();
		condition.setCategoryId(product.getCategoryId());
		condition.setSaleAttr("1");
		List<ProductCategoryAttribute> productCategoryAttrList=categoryAttributeService.listProductCategoryAttributeByCondition(condition);
		// 根据属性ID得到该属性下所有的属性值
		for (ProductCategoryAttribute proCatAttr : productCategoryAttrList) {
			proCatAttr.setProCatAttrValList(categoryAttributeValueService.listProductCategoryAttributeValueByCatePubAttrId(proCatAttr.getId()));
		}
		
		// 得到当前产品以关联的属性值
//		List<ProductSku> skuList=productSkuService.listProductSkuByProductId(product.getId());
//		if (skuList.size()!=0) {
//			List<ProductSkuAttrval> productSkuAttrvals = productSkuAttrvalService.listProductSkuAttrvalBySkuIds(skuList);
//			List<ProductCategoryAttributeValue> catAttrValList=categoryAttributeValueService.listProductCategoryAttributeValueByIds(productSkuAttrvals);
//			model.setProductCategoryAttrValueList(catAttrValList);
//		}
		ProductBrandCondition brandCondition = new ProductBrandCondition();
		List<ProductBrand> listProductBrand = productBrandService.listProductBrand(brandCondition);
		model.setListProductBrand(listProductBrand);
		model.setProduct(product);
		model.setCategory(category);
		model.setProductCategoryAttrList(productCategoryAttrList);
		return INDEX;
	}
	
	/**
	 * 修改记录
	 * @return
	 */
	@Action(value = "doUpdate")
	public String doUpdate(){
		Product item = model.getProduct();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		item.setUpdateBy(user.getName());
		productService.update(item);
		String message = item.getId();
		return ajax(Status.success,message);
	}
	
	/**
	 * 保存产品首图url
	 * @return
	 */
	@Action(value = "doSaveOneImgUrl")
	public String doSaveOneImgUrl(){
		Product product = model.getProduct();
		productService.saveOneImgUrl(product);
		String message = product.getImageUrl();
		return ajax(Status.success,message);
	}
	
	/**
	 * 保存默认价格
	 * @return
	 */
	@Action(value = "doSaveDefaultPrice")
	public String doSaveDefaultPrice(){
		Product product = model.getProduct();
		productService.saveDefaultPrice(product);
		String message = product.getId();
		return ajax(Status.success,message);
	}
	
	/**
	 * 打开新增起批区间价格页面
	 * @return 
	 */
	@Action(value = "addPriceRange", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/product/product_addPriceRange.jsp") })
	public String toAddPriceRange(){
		ProductWholesaleRange productRange = productWholesaleRangeService.getProductWholesaleRangeById(model.getId());
		model.setProductRange(productRange);
		if(productRange!=null){
			model.getProduct().setId(productRange.getProductId());
		}
		return SUCCESS;
	}
	
	/**
	 * 批量删除
	 * @return
	 */
	@Action(value = "doBatchDel")	
	public String doBatchDel(){	
		String strIds = model.getSelIds();
		String[] ids = strIds.split(",");
		productService.delByIds(ids);
		return ajax(Status.success);
	}
	
	/**
	 * 跳转到接口测试页面
	 * @return
	 */
	@Action(value = "test", results = { @Result(name = "index", location = "/WEB-INF/pages/api/product/test.jsp") })
	public String test() {
		return INDEX;
	}
	
	@Override
	public ProductModel getModel() {
		return model;
	}
}
