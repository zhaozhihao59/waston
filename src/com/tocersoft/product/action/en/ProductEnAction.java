package com.tocersoft.product.action.en;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.page.PageResult;
import com.tocersoft.member.entity.Member;
import com.tocersoft.member.service.IMemberService;
import com.tocersoft.order.entity.OrderSellItem;
import com.tocersoft.order.service.IOrderSellItemService;
import com.tocersoft.product.dto.ProductCommentCondition;
import com.tocersoft.product.dto.ProductCondition;
import com.tocersoft.product.entity.Product;
import com.tocersoft.product.entity.ProductAttr;
import com.tocersoft.product.entity.ProductAttrValue;
import com.tocersoft.product.entity.ProductBrand;
import com.tocersoft.product.entity.ProductCategory;
import com.tocersoft.product.entity.ProductCategoryAttribute;
import com.tocersoft.product.entity.ProductCategoryAttributeValue;
import com.tocersoft.product.entity.ProductComment;
import com.tocersoft.product.model.ProductModel;
import com.tocersoft.product.service.IProductAttrService;
import com.tocersoft.product.service.IProductAttrValueService;
import com.tocersoft.product.service.IProductBrandService;
import com.tocersoft.product.service.IProductCategoryAttributeService;
import com.tocersoft.product.service.IProductCategoryAttributeValueService;
import com.tocersoft.product.service.IProductCategoryService;
import com.tocersoft.product.service.IProductCommentService;
import com.tocersoft.product.service.IProductService;
import com.tocersoft.system.entity.SysUploadFile;
import com.tocersoft.system.service.ISysUploadFileService;

/**
 * 
 */
@ParentPackage("front")
@Namespace("/en/product")
@Controller
public class ProductEnAction extends BaseAction implements ModelDriven<ProductModel> {
	
	private static final long serialVersionUID = -8337375042837415260L;
	
	private Log logger = LogFactory.getLog(ProductEnAction.class);
	
	private ProductModel model = new ProductModel();
	
	@Resource
	private IProductService productService;
	@Resource
	private IProductBrandService productBrandService;
	@Resource
	private IProductCategoryService productCategoryService;
	@Resource
	private IProductCategoryAttributeService productCategoryAttributeService;
	@Resource
	private IProductCategoryAttributeValueService productCategoryAttributeValueService;
	@Resource
	private IProductAttrService productAttrService;
	@Resource
	private IProductAttrValueService productAttrValueService;
//	@Resource(name = "cartItemServiceImpl")
//	private ICartItemService cartItemService;
	@Resource(name="memberServiceImpl")
	private IMemberService memberService;
	@Resource(name="sysUploadFileServiceImpl")
	private ISysUploadFileService sysUploadFileService;
	@Resource(name="orderSellItemServiceImpl")
	private IOrderSellItemService orderSellItemService;
	@Resource
	private IProductCommentService productCommentService;
	
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/pages/en/front/zh/product/index.jsp") })
	public String index(){
		
		return SUCCESS;
	}
	
	@Action(value = "list", results = { @Result(name = "success", location = "/WEB-INF/pages/en/front/product/list.jsp") })
	public String list() throws UnsupportedEncodingException{
			// 类别Id
			String categoryId = model.getCategoryId();
			// 品牌Id
			String brandId = model.getBrandId();
			
			// 按条件查询
			ProductCondition condition = model.getCondition();
			if(StringUtils.isNotBlank(categoryId)){
				// 查询指定类别产品
				ProductCategory category = productCategoryService.getProductCategoryById(categoryId);
				model.setCategoryAndBrandName(category.getNameEn());
				condition.setCategoryId(categoryId);
			}else if(StringUtils.isNotBlank(brandId)){
				if(new String(brandId.getBytes("ISO-8859-1"),"UTF-8").equals("BrandZone")){
					// 查询所有品牌产品
					model.setCategoryAndBrandName("Brand zone");
				}else{
					// 查询指定品牌产品
					ProductBrand brand = productBrandService.getProductBrandById(brandId);
					model.setCategoryAndBrandName(brand.getName());
					condition.setBrandId(brandId);
				}
			}else if(null != model.getIsStarProduct() && model.getIsStarProduct() == 1){
				// 查询明星产品
				model.setCategoryAndBrandName("Star products");
				condition.setIsStarProduct(1);
			}
			PageResult<Product> pageResult = model.getPageResult();
			pageResult.setPageSize(20);
			pageResult.setCurrentPage(model.getPage());
			productService.listProductByPage(pageResult, condition);
			
			List<Product> listBrand = model.getPageResult().getResult();
			
			for(Product proBrand : listBrand){
				//查询所有类别属性
				List<ProductCategoryAttribute> proCateAttrlist = productCategoryAttributeService.listProductCategoryAttributeByCategoryId(proBrand.getCategoryId());
				for(ProductCategoryAttribute proCateAttr : proCateAttrlist){
					//对应类别属性的属性值
					List<ProductCategoryAttributeValue> proCateAttrValueList = productCategoryAttributeValueService.listProductCategoryAttributeValueByCatePubAttrId(proCateAttr.getId());
					proCateAttr.setProCatAttrValList(proCateAttrValueList);
				}
				model.setProductCategoryAttrList(proCateAttrlist);
			}
			model.setProductList(listBrand);
			
			// 查询热销产品
			List<Product> sellWellProducts = model.getSellWellProducts();
			List<OrderSellItem> orderSellItems = orderSellItemService.listBySales();
			for(OrderSellItem orderSellItem : orderSellItems){
				Product pro = productService.getProductById(orderSellItem.getProductId());
				sellWellProducts.add(pro);
			}
			
			return SUCCESS;
	}
	
	// 跳转商品类别
	@Action(value = "toProduct_category", results = { @Result(name = "success", location = "/WEB-INF/pages/front/product/product_category.jsp") })
	public String toProductCategory(){
		return SUCCESS;
	}
	
	
	@Action(value = "detail", results = { @Result(name = "success", location = "/WEB-INF/pages/en/front/product/detail.jsp") })
	public String detail() throws UnsupportedEncodingException{
		String id = model.getId();
		//当前具体产品
		Product product = productService.getProductById(id);
		ProductBrand brand = productBrandService.getProductBrandById(product.getBrandId());
		model.setProductBrand(brand);
		String categoryAndBrandName = model.getCategoryAndBrandName();
		if(categoryAndBrandName != null){
			model.setCategoryAndBrandName(new String(categoryAndBrandName.getBytes("ISO-8859-1"),"UTF-8"));
		}
		//根据产品Id取到相应的产品属性
		List<ProductAttr> proAttrList = productAttrService.listProductAttrByPid(product.getId());
		if(null != proAttrList){
			for(ProductAttr proAttr : proAttrList){
				List<ProductAttrValue> proAttrValueList = productAttrValueService.listProductAttrValueByProductAttrId(proAttr.getId());
				for(ProductAttrValue proAttrval : proAttrValueList){
					proAttr.setProAttrName(proAttrval.getAttrName());
				}
				proAttr.setProdAttrValueList(proAttrValueList);
			}
		}
		// 查询产品的图片
		List<SysUploadFile> sysUploadFiles = sysUploadFileService.listSysUploadFileByObjectIdAndTypeId(product.getId(),"1");
		model.setSysUploadFiles(sysUploadFiles);
		
		// 查询同类同品牌产品
		PageResult<Product> pageResult = new PageResult<Product>();
		ProductCondition condition = new ProductCondition();
		condition.setCategoryId(product.getCategoryId());
		productService.listProductByPage(pageResult, condition);
		List<Product> similarProduct = pageResult.getResult();
		model.setSimilarProduct(similarProduct);
		
		// 查询热销产品
		List<Product> sellWellProducts = model.getSellWellProducts();
		List<OrderSellItem> orderSellItems = orderSellItemService.listBySales();
		for(OrderSellItem orderSellItem : orderSellItems){
			Product pro = productService.getProductById(orderSellItem.getProductId());
			sellWellProducts.add(pro);
		}
		
		// 查询产品评论
		PageResult<ProductComment> pcPageResult = new PageResult<ProductComment>();
		pcPageResult.setCurrentPage(model.getPage());
		pcPageResult.setPageSize(10);
		ProductCommentCondition pcCondition = new ProductCommentCondition();
		pcCondition.setProductId(id);
		productCommentService.listProductCommentByPage(pcPageResult, pcCondition);
		List<ProductComment> productComments = pcPageResult.getResult();
		int markSum = 0;
		if(productComments != null && productComments.size()>0){
			for(ProductComment productComment : productComments){
				Member member = memberService.getMemberById(productComment.getMemberId());
				productComment.setMemberName(member.getName());
				markSum += productComment.getMark();
			}
		}
		model.setMarkSum(markSum);
		model.setPcPageResult(pcPageResult);
		model.setProductComments(productComments);
		
		product.setProdAttrList(proAttrList);
		model.setProduct(product);
		return SUCCESS;
	}
	
	@Action(value = "listCart", results = { @Result(name = "success", location = "/WEB-INF/pages/front/product/shoppingCart.jsp") })
	public String listCart(){
		//消费总金额
		double money=0.0;
		double moneyOne=0.0;
//		Member member = (Member) getSession().getAttribute(Constant.FRONT_USER);
//		List<CartItem> cartItemList = cartItemService.listCartItemByMemberId(member.getId());
//		List<CartItem> cartItemList = cartItemService.listCartByMemberId("d9cdad6bcf9a11e3bcff00fffd437687");
//		for(CartItem cartItem : cartItemList){
//			//拿到一个产品
//			Product product = productService.getProductById(cartItem.getProductWeixinId());
//			cartItem.setProductSellName(product.getName());
//			cartItem.setImgPath(product.getImageUrl());
//			cartItem.setPrice( Double.parseDouble(product.getUnitPrice()));
//			cartItem.setShortDescEn(product.getShortDescEn());
//			money += cartItem.getAmount()*Integer.parseInt(product.getUnitPrice());
//			moneyOne = cartItem.getAmount()*Integer.parseInt(product.getUnitPrice());
//			cartItem.setProductMoney(moneyOne);
//		}
//			model.setShopMoney(money);
//			model.setCartItemList(cartItemList);
			return SUCCESS;
	}
	
	@Action(value = "shoppingCart", results = { @Result(name = "success", location = "/WEB-INF/pages/front/product/shoppingCart.jsp") })
	public String shoppingCart(){
		try {
			//会员Id
			String memberId = "d9cdad6bcf9a11e3bcff00fffd437687";
			Member member = memberService.getMemberById(memberId);
			
			//产品Id
			String productId = model.getProductId();
			//根据当前产品Id查询出购物车是否含有该产品
//			CartItem cartitem= cartItemService.getCartItemByProductId(memberId,productId);
//			//根据当前产品Id拿到整个产品
//			Product product = productService.getProductById(productId);
//			if(null == cartitem){
//				Integer count = model.getCount();
//				CartItem cartItemOne = new CartItem();
//				if(count != null && count != 0){
//					cartItemOne.setAmount(count);
//				}
//				cartItemOne.setAmount(1);
//				cartItemOne.setProductSellName(product.getName());
//				cartItemOne.setPrice(Double.parseDouble(product.getUnitPrice()));
//				cartItemOne.setImgPath(product.getImageUrl());
//				cartItemOne.setProductWeixinId(productId);
//				cartItemOne.setMemberId(memberId);
//				cartItemOne.setCreateBy(member.getName());
//				cartItemOne.setUpdateBy(member.getName());
//				cartItemOne.setIsOrderSell(0);
//				cartItemService.add(cartItemOne);
//			}
//			else{
//				cartitem.setMemberId(memberId);
//				cartitem.setAmount(cartitem.getAmount()+1);
//				cartitem.setUpdateBy(member.getName());
//				cartItemService.updateAmount(cartitem);
//			}
			return ajax(Status.success);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			String msg = "加入购物车发送异常："+e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error,msg);
		}
	}
	
	
	@Action(value = "confirmOrder", results = { @Result(name = "success", location = "/WEB-INF/pages/front/product/confirmOrder.jsp") })
	public String confirmOrder(){
		
		return SUCCESS;
	}
	@Action(value = "toPay", results = { @Result(name = "success", location = "/WEB-INF/pages/front/product/toPay.jsp") })
	public String toPay(){
		
		return SUCCESS;
	}
	
	@Override
	public ProductModel getModel() {
		return model;
	}
}
