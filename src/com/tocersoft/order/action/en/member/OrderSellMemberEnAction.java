package com.tocersoft.order.action.en.member;

import java.text.DecimalFormat;
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
import org.springframework.util.CollectionUtils;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.utils.Constant;
import com.tocersoft.base.utils.DoubleUtil;
import com.tocersoft.base.utils.UUIDUtil;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.member.entity.MemAddress;
import com.tocersoft.member.entity.Member;
import com.tocersoft.member.service.IMemAddressService;
import com.tocersoft.order.dto.FreightTemplateCondition;
import com.tocersoft.order.dto.FreightTemplateItemCondition;
import com.tocersoft.order.entity.FreightTemplate;
import com.tocersoft.order.entity.FreightTemplateItem;
import com.tocersoft.order.entity.OrderCartItem;
import com.tocersoft.order.entity.OrderSell;
import com.tocersoft.order.entity.OrderSellItem;
import com.tocersoft.order.model.OrderSellModel;
import com.tocersoft.order.service.IFreightTemplateItemService;
import com.tocersoft.order.service.IFreightTemplateService;
import com.tocersoft.order.service.IOrderSellItemService;
import com.tocersoft.order.service.IOrderSellService;
import com.tocersoft.product.dto.ProductPackageCondition;
import com.tocersoft.product.entity.ExchangeRate;
import com.tocersoft.product.entity.Product;
import com.tocersoft.product.entity.ProductLogistics;
import com.tocersoft.product.entity.ProductPackage;
import com.tocersoft.product.service.IExchangeRateService;
import com.tocersoft.product.service.IProductCategoryAttributeService;
import com.tocersoft.product.service.IProductCategoryAttributeValueService;
import com.tocersoft.product.service.IProductLogisticsService;
import com.tocersoft.product.service.IProductPackageService;
import com.tocersoft.product.service.IProductService;


@ParentPackage("enMember")
@Namespace("/en/member/order/sell")
@Controller
public class OrderSellMemberEnAction extends BaseAction implements ModelDriven<OrderSellModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(OrderSellMemberEnAction.class);

	private OrderSellModel model = new OrderSellModel();

	@Resource(name = "orderSellServiceImpl")
	private IOrderSellService orderSellService;
	@Resource(name = "productServiceImpl")
	private IProductService productService;
	@Resource(name = "orderSellItemServiceImpl")
	private IOrderSellItemService orderSellItemService;
	@Resource 
	private IMemAddressService memAddressService;
	@Resource(name = "exchangeRateServiceImpl")
	private IExchangeRateService exchangeRateService;
	@Resource(name = "productLogisticsServiceImpl")
	private IProductLogisticsService productLogisticsService;
	@Resource(name = "freightTemplateServiceImpl")
	private IFreightTemplateService freightTemplateService;
	@Resource(name = "freightTemplateItemServiceImpl")
	private IFreightTemplateItemService freightTemplateItemService;
	@Resource(name = "productCategoryAttributeServiceImpl")
	private IProductCategoryAttributeService productCategoryAttributeService;
	@Resource(name = "productCategoryAttributeValueServiceImpl")
	private IProductCategoryAttributeValueService productCategoryAttributeValueService;
	@Resource(name = "productPackageServiceImpl")
	private IProductPackageService packageService;
	
	/**
	 * 首页
	 * @return 
	 */
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/pages/en/") })
	public String index(){
		return SUCCESS;
	}
	
	/**
	 * 立即购买
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "buy", results = { 
			@Result(name = "success", location = "/WEB-INF/pages/en/front/order/order_confirm.jsp"),
			@Result(name = "cart_list",type="redirectAction",params={"namespace","/en/cart","actionName","index"})
			}
	)
	public String buy(){
		// 查询用户 收货地址
		// 获得当前会员信息
		// 分页查询 收货地址
		Member saMember = (Member)getSession().getAttribute(Constant.FRONT_USER);
		model.getMemAddressPageResult().setPageSize(20);
		model.getMemaddresscondition().setMemberId(saMember.getId());
		memAddressService.listMemAddressByPage(model.getMemAddressPageResult(),model.getMemaddresscondition());
		
		Member member = (Member)getSession().getAttribute(Constant.FRONT_USER);
		MemAddress memaddress =  memAddressService.getDefaultMemAddress(member.getId());
		model.setMemaddress(memaddress);
		
		String cartItemStrs = model.getCartItemStrs();
		String productId = model.getProductId();
		
		// 构造订单明细集合，用于将选中的购物车中的明细加入至订单明细集合中
		List<OrderSellItem> orderSellItemList = new ArrayList<OrderSellItem>();
		
		//查找汇率
		ExchangeRate exchangeRate = exchangeRateService.getExchangeRate();
		model.setExchangeRate(exchangeRate);
		List<String> productIds = new ArrayList<String>();
		// 如果从购物车中进行下单
		if(StringUtils.isNotBlank(cartItemStrs)){
			
			// 先获取到Cookie中的购物车数据
			String orderCartItemListCookie = this.getCookie("orderCartItemListCookie");
			
			if(StringUtils.isBlank(orderCartItemListCookie)){
				//购物车内没有商品
				return "cart_list";
			}
			
			List<OrderCartItem> orderCartItemList = (List<OrderCartItem>)this.getSession().getAttribute(orderCartItemListCookie);
			
			if(CollectionUtils.isEmpty(orderCartItemList)){
				//没有商品
				return "cart_list";
			}
			
			for(OrderCartItem cartItem : orderCartItemList){
				// 如果匹配到购物车明细，则将购物车明细加入之订单明细集合中
				if(cartItemStrs.indexOf(cartItem.getId()) >= 0){
					OrderSellItem sellItem = new OrderSellItem();
					sellItem.setId(UUIDUtil.uuid());
					sellItem.setProductId(cartItem.getProductId());
					sellItem.setProductSkuId(cartItem.getProductSkuId());
					sellItem.setUnitPrice(cartItem.getProduct().getUnitPrice());
					sellItem.setNum(cartItem.getNum());
					Product product = new Product();
					product.setId(cartItem.getProductId());
					product.setName(cartItem.getProduct().getName());
					product.setNameEn(cartItem.getProduct().getNameEn());
					product.setUnitPrice(cartItem.getProduct().getUnitPrice());
					product.setBrandName(cartItem.getProduct().getBrandName());
					product.setImageUrl(cartItem.getProduct().getImageUrl());
					sellItem.setProduct(product);
					orderSellItemList.add(sellItem);
					
					productIds.add(cartItem.getProductId());
				}
			}
			
		// 如果从订单列表中直接下单
		}else if(StringUtils.isNotBlank(productId)){
			
			Product product = productService.getProductById(productId);
			OrderSellItem sellItem = new OrderSellItem();
			sellItem.setId(UUIDUtil.uuid());
			sellItem.setProductId(productId);
			sellItem.setUnitPrice(product.getUnitPrice());
			sellItem.setNum(model.getNum());
			sellItem.setProduct(product);
			orderSellItemList.add(sellItem);
			
			productIds.add(productId);
		}
		// 计算运费
		double postage = 0;  // 运费
		if(productIds != null && productIds.size()>0){
			for(String pId : productIds){
				// 默认收货地址不为空时才计算邮费
				if(memaddress != null){
					// 通过产品ID查询该产品的邮费模板
					List<ProductLogistics> pList = productLogisticsService.getProductLogisticsByProductId(pId);
					// 运费模板规则
					List<FreightTemplateItem> freightTemplateItems = new ArrayList<FreightTemplateItem>();
					// 如果该产品设置了邮费模板，就使用自己的邮费模板得到相应的模板规则
					if(pList != null && pList.size()>0){
						ProductLogistics productLogistics = pList.get(0); // 一个产品只有一个运费模板
						// 如果不为1就是买家承担运费，才计算运费。卖家承担运费就不用计算运费了
						if(!productLogistics.getFreightType().equals("1")){
							FreightTemplateItemCondition ftCondition = new FreightTemplateItemCondition();
							ftCondition.setTemplateId(productLogistics.getTemplateId());
							freightTemplateItems = freightTemplateItemService.listFreightTemplateItemByCondition(ftCondition);
						}
					}
					// 否则使用默认的运费模板，得到模板规则
					else{
						FreightTemplateCondition condition = new FreightTemplateCondition();
						condition.setIsDefault(1);
						List<FreightTemplate> fList = freightTemplateService.listFreightTemplateAll(condition);
						if(fList != null && fList.size()>0){
							// 查询运费规则
							FreightTemplateItemCondition ftCondition = new FreightTemplateItemCondition();
							ftCondition.setTemplateId(fList.get(0).getId()); // 只能有一个默认的运费模板
							freightTemplateItems = freightTemplateItemService.listFreightTemplateItemByCondition(ftCondition);
						}
					}
					// 如果模板规则不为空了就去计算运费
					if(freightTemplateItems != null && freightTemplateItems.size()>0){
						//Product product = productService.getProductById(pId);
						double productWeight = 0; // 产品重量
						ProductPackageCondition ppCondition = new ProductPackageCondition();
						ppCondition.setProductId(pId);
						List<ProductPackage> packages = packageService.listProductPackageByCondition(ppCondition);
						if(packages != null && packages.size()>0){
							productWeight = packages.get(0).getGrossWeight();
						}
						
//						// 先根据产品类别ID和"产品重量"属性查询产品属性
//						ProductCategoryAttributeCondition pcaCondition = new ProductCategoryAttributeCondition();
//						pcaCondition.setCategoryId(product.getCategoryId());
//						pcaCondition.setLineAttrNameCn("产品重量");
//						List<ProductCategoryAttribute> productCategoryAttributes = productCategoryAttributeService.listProductCategoryAttributeByCondition(pcaCondition);
//						if(productCategoryAttributes != null && productCategoryAttributes.size()>0){
//							// 根据属性ID查询属性值
//							List<ProductCategoryAttributeValue> productCategoryAttributeValues = productCategoryAttributeValueService
//									.listProductCategoryAttributeValueByCatePubAttrId(productCategoryAttributes.get(0).getId());
//							// 最终得到产品的重量(格式：300g)
//							String pWeight = productCategoryAttributeValues.get(0).getLineAttrvalNameCn();
//							// 转换为300(同时转换为kg)
//							productWeight = Double.parseDouble(pWeight.substring(0, pWeight.length()-1))/1000;
//						}
						
						FreightTemplateItem freightTemplateItem = null; //模板规则
						for(FreightTemplateItem ftItem : freightTemplateItems){
							// 将模板规则里的到达城市切成数组
							String[] arrivalCityList = ftItem.getArrivalCityList().split(",");
							for (String arrivalCity : arrivalCityList) {
								// 用会员选择的送货地址去匹配出一个模板规则
								if((arrivalCity.equals("全国")) || (memaddress.getProvince().equals(arrivalCity)) || (memaddress.getCity().equals(arrivalCity))){
									freightTemplateItem = ftItem;
									break;
								}
							}
						}
						if(freightTemplateItem != null){
							// 计算运费
							double firstWeight = freightTemplateItem.getFirstWeight(); // 产品首重
							double firstWeightFee = freightTemplateItem.getFirstWeightFee(); // 首重价格
							// 如果产品重量小于等于首重，邮费就等于首重价格
							if(productWeight <= firstWeight){
								postage += firstWeightFee;
							}
							// 否则邮费 = 首重价格 + (产品的重量-首重)/模板续重重量*续重价格
							else{
								double productContinuedWeight = productWeight - firstWeight; // 产品续重
								double continuedWeight = freightTemplateItem.getContinuedWeight(); // 模板续重
								postage += firstWeightFee + (productContinuedWeight%continuedWeight==0?productContinuedWeight/continuedWeight:productContinuedWeight/continuedWeight+1) * freightTemplateItem.getContinuedWeightFee();
							}
						}
					}
				}
			}
		}
		
		// 先将订单明细构造好存入Session中
		getSession().setAttribute("orderSellItemList", orderSellItemList);
		
		// 邮费总额
		getSession().setAttribute("postage", postage);
		
		// 产品金额
		Double productTotalPrice = 0D;
		for(OrderSellItem sellItem : orderSellItemList){
			productTotalPrice = DoubleUtil.sum(productTotalPrice, sellItem.getSumPrice());
		}
		
		// 订单总额
		Double totalPrice = 0D;
		totalPrice = productTotalPrice + postage;
		getSession().setAttribute("orderSellTotalPrice", totalPrice);
		//汇率转换
		//将人民币转换成新西兰币
		double exchangeRateVal = exchangeRate.getNzdRate() * 1 / exchangeRate.getRmb();
		DecimalFormat df = new DecimalFormat("#.00");	//格式化2位小数
		getSession().setAttribute("orderSellTotalPriceNzd", df.format(totalPrice * exchangeRateVal));
		return SUCCESS;
	}
	
	/**
	 * 验证折扣代码
	 * @return
	 */
	@Action(value = "checkDiscountCode")
	public String checkDiscountCode(){
		String discountCode = getRequest().getParameter("discountCode");	//折扣代码
		String freeShippingCode = getRequest().getParameter("freeShippingCode");	//免运费折扣
		
		if(StringUtils.isNotBlank(discountCode)){
			if(!discountCode.equals(Constant.VISA_DISCOUNT_CODE)){
				return ajax(Status.error,"折扣代码输入错误");
			}
		}
		
		if(StringUtils.isNotBlank(freeShippingCode)){
			if(!freeShippingCode.equals(Constant.VISA_DISCOUNT_CODE)){
				return ajax(Status.error,"免邮费代码输入错误");
			}
		}
		
		return ajax(Status.success);
	}

	/**
	 * 确认订单，并在线支付
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "confirm", results = { 
			@Result(name = "success",type="redirectAction",params={"namespace","/en/member/order/pay","actionName","detail","item.id","${item.id}"}),
			@Result(name = "order_list",type="redirectAction",params={"namespace","/en/member/account","actionName","my_order"})
			}
	)
	public String confirm(){
		String discountCode = getRequest().getParameter("discountCode");	//折扣代码
		String freeShippingCode = getRequest().getParameter("freeShippingCode");	//免运费折扣
		
		// 获得当前会员信息
		Member member = (Member)getSession().getAttribute(Constant.FRONT_USER);
		//订单项
		List<OrderSellItem> orderSellItemList = (List<OrderSellItem>)getSession().getAttribute("orderSellItemList");
		
		if(CollectionUtils.isEmpty(orderSellItemList)){
			//没有商品
			return "order_list";
		}
		
		//收货地址
		MemAddress memaddress =  memAddressService.getDefaultMemAddress(member.getId());
		//查找汇率
		ExchangeRate exchangeRate = exchangeRateService.getExchangeRate();
		double exchangeRateVal = exchangeRate.getNzdRate() * 1 / exchangeRate.getRmb();
		
		DecimalFormat df = new DecimalFormat("#.00");	//格式化2位小数
		
		// 先保存orderSell信息
		OrderSell orderSell = model.getItem();
		orderSell.setId(UUIDUtil.uuid());
		orderSell.setMemberId(member.getId());
		orderSell.setOrderState(0);	//未付款
		orderSell.setSendAddress(memaddress.getAddress());
		orderSell.setSendCountryCode("CN");	//默认给中国
		orderSell.setSendProvince(memaddress.getProvince());	//省份
		orderSell.setSendCity(memaddress.getCity());	//城市
		// 还需要保存送货信息与运费金额
		if(StringUtils.isNotBlank(freeShippingCode) && Constant.VISA_DISCOUNT_CODE.equals(freeShippingCode)){
			//免邮费
			orderSell.setAmountFreight(0);
			orderSell.setFreeShippingCode(1);	//已免运费
		}else{
			orderSell.setFreeShippingCode(0);	//未免运费
		}
		
		//邮费换换后的价格
		double exchangeRateAmountFreightVal = Double.parseDouble(df.format(orderSell.getAmountFreight() * exchangeRateVal));
		orderSell.setExchangeRateAmountFreight(exchangeRateAmountFreightVal);
		
		double itemTotal = 0;	//产品总额
		double exchangeRateItemTotal = 0;	//汇率转换后的产品总额
		for(OrderSellItem curOrderSellItem : orderSellItemList){
			//10%折扣
			if(StringUtils.isNotBlank(discountCode) && Constant.VISA_DISCOUNT_CODE.equals(discountCode)){
				//折扣计算
				double discountUnitPrice = DoubleUtil.mul(curOrderSellItem.getUnitPrice(), 0.9);
				curOrderSellItem.setUnitPrice(discountUnitPrice);	//按照9折来走
				orderSell.setDiscountCode(1);	//已折扣
			}else{
				orderSell.setDiscountCode(0);	//无折扣
			}
			itemTotal += curOrderSellItem.getSumPrice();	//产品总额
			
			
			curOrderSellItem.setProductName(curOrderSellItem.getProduct().getName());	//产品名称 
			curOrderSellItem.setProductNameEn(curOrderSellItem.getProduct().getNameEn());	//产品名称 (英文)
			curOrderSellItem.setProductPhoto(curOrderSellItem.getProduct().getImageUrl()); //产品首图
			
			//计算汇率转换后的价格
			double exchangeRateUnitPrice = Double.parseDouble(df.format(curOrderSellItem.getUnitPrice() * exchangeRateVal));
			curOrderSellItem.setExchangeRateUnitPrice(exchangeRateUnitPrice);
			exchangeRateItemTotal += DoubleUtil.mul(curOrderSellItem.getExchangeRateUnitPrice(), curOrderSellItem.getNum());
		}
		
		orderSell.setAmountProduct(itemTotal);	//产品总额
		orderSell.setExchangeRateAmountProduct(exchangeRateItemTotal);	//汇率转换后的产品总额
		
		orderSell.setAmountTotal(orderSell.getAmountProduct() + orderSell.getAmountFreight());	//订单总额
		orderSell.setExchangeRateAmountTotal(orderSell.getExchangeRateAmountProduct() + orderSell.getExchangeRateAmountFreight()); //汇率转换后的订单总额
		//保存
		orderSellService.add(orderSell);
		model.setItem(orderSell);
		
		// 将Session中的订单信息保存到数据库
		orderSellItemService.addBatch(orderSellItemList, orderSell.getId());
		
		//移除session数据
		getSession().removeAttribute("orderSellItemList");
		getSession().removeAttribute("cartItemNum");
		//移除cookie数据
		this.removeCookie("orderCartItemListCookie");
		
		return SUCCESS;
	}
	
	/**
	 * 分页查询
	 * @return 
	 */
	@Action(value = "listOrderSellByPage")
	public String listOrderSellByPage(){
		orderSellService.listOrderSellByPage(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{});
		return ajax(root);
	}

	/**
	 * 新增
	 * @return 
	 */
	@Action(value = "add", results = { @Result(name = "success", location = "/WEB-INF/pages/") })
	public String toAdd(){
		return SUCCESS;
	}

	/**
	 * 修改
	 * @return 
	 */
	@Action(value = "update", results = { @Result(name = "success", location = "/WEB-INF/pages/") })
	public String toUpdate(){
		OrderSell item = orderSellService.getOrderSellById(model.getItem().getId());
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
			orderSellService.delByIds(model.getSelIds().split(","));
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
				orderSellService.add(model.getItem());
			}else{
				orderSellService.update(model.getItem());
			}
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}


	@Override
	public OrderSellModel getModel() {
		return model;
	}}

