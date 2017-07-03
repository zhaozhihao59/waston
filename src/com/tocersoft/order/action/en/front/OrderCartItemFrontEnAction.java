package com.tocersoft.order.action.en.front;

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
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.page.PageResult;
import com.tocersoft.base.utils.Constant;
import com.tocersoft.base.utils.DoubleUtil;
import com.tocersoft.base.utils.UUIDUtil;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.member.entity.Member;
import com.tocersoft.order.entity.OrderCartItem;
import com.tocersoft.order.model.OrderCartItemModel;
import com.tocersoft.order.service.IOrderCartItemService;
import com.tocersoft.product.dto.ProductCondition;
import com.tocersoft.product.entity.Product;
import com.tocersoft.product.service.IProductService;


@ParentPackage("front")
@Namespace("/en/cart")
@Controller
public class OrderCartItemFrontEnAction extends BaseAction implements ModelDriven<OrderCartItemModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(OrderCartItemFrontEnAction.class);

	private OrderCartItemModel model = new OrderCartItemModel();

	@Resource(name = "orderCartItemServiceImpl")
	private IOrderCartItemService orderCartItemService;
	@Resource(name = "productServiceImpl")
	private IProductService productService;


	/**
	 * 显示购物车页面
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/pages/en/front/order/cart_list.jsp") })
	public String index(){
		
		// 先获取到Cookie中的购物车数据
		String orderCartItemListCookie = this.getCookie("orderCartItemListCookie");
		List<OrderCartItem> cartItemList = (List<OrderCartItem>)this.getSession().getAttribute(orderCartItemListCookie);
		if(cartItemList != null && cartItemList.size()>0){
			// 购物车内的明细总金额
			Double totalPrice = 0D;
			
			if(null != cartItemList){
				for(OrderCartItem item : cartItemList){
					String productId = item.getProductId();
					Product product = productService.getProductById(productId);
					item.setProduct(product);
					item.setUnitPrice(product.getUnitPrice());
					
					// 遍历累加总金额
					totalPrice += item.getSumPrice();
				}
			}
			
			model.setCartItemList(cartItemList);
			model.setTotalPrice(totalPrice);
		}else{
			// 查询明星产品
			PageResult<Product> pageResult = new PageResult<Product>();
			ProductCondition condition = new ProductCondition();
			condition = new ProductCondition();
			condition.setIsStarProduct(1);
			productService.listProductByPage(pageResult, condition);
			List<Product> starProducts = pageResult.getResult();
			model.setStarProducts(starProducts);
		}
		return SUCCESS;
	}

	/**
	 * 分页查询
	 * @return 
	 */
	@Action(value = "listOrderCartItemByPage")
	public String listOrderCartItemByPage(){
		orderCartItemService.listOrderCartItemByPage(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{});
		return ajax(root);
	}

	/**
	 * 添加到购物车
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "add")
	public String toAdd(){
		String productId = model.getProductId();
		Double cartNum = model.getCartNum();
		// 构造购物车明细项
		OrderCartItem item = new OrderCartItem();
		item.setId(UUIDUtil.uuid());
		item.setProductId(productId);
		item.setNum(cartNum);
		Product product = productService.getProductById(productId);
		item.setProduct(product);
		
		// 先获取到Cookie中的购物车数据
		String orderCartItemListCookie = this.getCookie("orderCartItemListCookie");
		List<OrderCartItem> orderCartItemList = (List<OrderCartItem>)this.getSession().getAttribute(orderCartItemListCookie);
		
		// 判断是否登录，如果登录，则存入购物车数据库；如果未登录，则存入客户端Cookie
		Member member = (Member)this.getSession().getAttribute(Constant.FRONT_USER);
		
		// 不论是否登录，都先存入客户端Cookie
		// 当Cookie对应的Session中的购物车明细集合不为空时，直接加入
		if(null != orderCartItemList){
			
			// 是否新增购物车明细项
			boolean isAdd = true;
			
			// 如果之前Cookie中的购物车产品已经存在，则增加数量
			for(OrderCartItem cookieItem : orderCartItemList){
				if(cookieItem.getProductId().equals(productId)){
					Double itemNum = cookieItem.getNum();
					Double newItemNum = DoubleUtil.sum(itemNum,cartNum);
					cookieItem.setNum(newItemNum);
					
					// 此时不再新增购物车明细项
					isAdd = false;
				}
			}
			
			if(isAdd){
				orderCartItemList.add(item);
			}
			
		// 当Cookie对应的Session中的购物车明细集合为空时，则存入Session，并存入Cookie
		}else{
			orderCartItemList = new ArrayList<OrderCartItem>();
			orderCartItemList.add(item);
			orderCartItemListCookie = UUIDUtil.uuid();
			this.setCookie("orderCartItemListCookie", orderCartItemListCookie);
		}
		
		// 重新放入Session与Cookie
		this.getSession().setAttribute(orderCartItemListCookie, orderCartItemList);
		
		// 当前会话中存入购物车内产品数量
		Double cartItemNum = 0D;
		for(OrderCartItem cookieItem : orderCartItemList){
			cartItemNum = DoubleUtil.sum(cartItemNum, cookieItem.getNum());
		}
		this.getSession().setAttribute("cartItemNum", cartItemNum);
		
		// 如果已登录，则存入购物车数据库，同时同步一下当前的Cookie数据
		if(null != member){
			orderCartItemService.addOrUpdate(orderCartItemList, member.getId());
		}
		
		JSONObject result = new JSONObject();
		result.put("status", "success");
		result.put("imageUrl", product.getImageUrl());
		result.put("productName", product.getName());
		result.put("productNameEn", product.getNameEn());
		result.put("unitPrice", product.getUnitPrice());
		result.put("num", item.getNum());
		result.put("sumPrice", item.getSumPrice());
		result.put("cartItemNum", cartItemNum);
		
		return ajax(result);
	}
	
	/**
	 * 重新计算购物车
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "change_num")
	public String changeNum(){
		
		String itemId = model.getItemId();
		Double cartNum = model.getCartNum();
		
		// 先获取到Cookie中的购物车数据
		String orderCartItemListCookie = this.getCookie("orderCartItemListCookie");
		List<OrderCartItem> orderCartItemList = (List<OrderCartItem>)this.getSession().getAttribute(orderCartItemListCookie);
		
		// 判断是否登录，如果登录，则存入购物车数据库；如果未登录，则存入客户端Cookie
		Member member = (Member)this.getSession().getAttribute(Constant.FRONT_USER);
		
		// 需要更新的购物车明细项
		OrderCartItem itemNeedUpdate = new OrderCartItem();
		
		// 更改数量
		for(OrderCartItem cookieItem : orderCartItemList){
			if(cookieItem.getId().equals(itemId)){
				itemNeedUpdate = cookieItem;
				cookieItem.setNum(cartNum);
				itemNeedUpdate.setNum(cartNum);
			}
		}
		// 重新放入Session与Cookie
		this.getSession().setAttribute(orderCartItemListCookie, orderCartItemList);
		
		// 如果已登录，则存入购物车数据库，同时同步一下当前的Cookie数据
		if(null != member){
			orderCartItemService.updateNum(cartNum, itemId);
		}
		
		// 当前会话中存入购物车内产品数量
		Double cartItemNum = 0D;
		for(OrderCartItem cookieItem : orderCartItemList){
			cartItemNum = DoubleUtil.sum(cartItemNum, cookieItem.getNum());
		}
		this.getSession().setAttribute("cartItemNum", cartItemNum);
		
		Product product = productService.getProductById(itemNeedUpdate.getProductId());
		itemNeedUpdate.setProduct(product);
		itemNeedUpdate.setUnitPrice(product.getUnitPrice());
		
		JSONObject result = new JSONObject();
		result.put("status", "success");
		result.put("sumPrice", itemNeedUpdate.getSumPrice());
		result.put("cartItemNum", cartItemNum);
		
		return ajax(result);
	}

	/**
	 * 移除购物车中选中的产品
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "remove_items")
	public String removeItems(){
		
		String cartItemChoose = model.getCartItemChoose();
		
		// 先获取到Cookie中的购物车数据
		String orderCartItemListCookie = this.getCookie("orderCartItemListCookie");
		List<OrderCartItem> orderCartItemList = (List<OrderCartItem>)this.getSession().getAttribute(orderCartItemListCookie);
		
		List<OrderCartItem> newOrderCartItemList = new ArrayList<OrderCartItem>();
		// 更改数量
		for(OrderCartItem cookieItem : orderCartItemList){
			// 表明没有匹配，继续保留
			if(cartItemChoose.indexOf(cookieItem.getId()) < 0){
				newOrderCartItemList.add(cookieItem);
			}
		}
		// 重新放入Session与Cookie
		this.getSession().setAttribute(orderCartItemListCookie, newOrderCartItemList);
		this.getSession().setAttribute("cartItemList", newOrderCartItemList);
		
		// 判断是否登录，如果登录，则存入购物车数据库；如果未登录，则存入客户端Cookie
		Member member = (Member)this.getSession().getAttribute(Constant.FRONT_USER);
		
		// 如果已登录，则删除购物车中的明细项
		if(null != member){
			orderCartItemService.delByIds(cartItemChoose.split(","));
		}
		
		JSONObject result = new JSONObject();
		result.put("status", "success");
		
		return ajax(result);
	}
	
	/**
	 * 修改
	 * @return 
	 */
	@Action(value = "update", results = { @Result(name = "success", location = "/WEB-INF/pages/en/") })
	public String toUpdate(){
		OrderCartItem item = orderCartItemService.getOrderCartItemById(model.getItem().getId());
		model.setItem(item);
		return SUCCESS;
	}

	/**
	 * 删除
	 * @return 
	 */
	@Action(value="del")
	@Validations(requiredStrings = {
				@RequiredStringValidator(fieldName = "selIds", message = "ID不能为空")
			}
	)
	@InputConfig(resultName = "ajaxError")
	public String del(){
		try {
			orderCartItemService.delByIds(model.getSelIds().split(","));
			return ajax(Status.success,"删除成功");
		} catch (Exception e) {
			String msg = "删除时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}

	/**
	 * 保存
	 * @return 
	 */
	@Action(value="doSave")
	public String doSave(){
		try {
			if(StringUtils.isBlank(model.getItem().getId())){
				orderCartItemService.add(model.getItem());
			}else{
				orderCartItemService.update(model.getItem());
			}
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}


	@Override
	public OrderCartItemModel getModel() {
		return model;
	}}

