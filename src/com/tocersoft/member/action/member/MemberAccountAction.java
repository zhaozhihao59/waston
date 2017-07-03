package com.tocersoft.member.action.member;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.page.PageResult;
import com.tocersoft.base.utils.Constant;
import com.tocersoft.base.utils.DataUtil;
import com.tocersoft.game.service.IGameEnrollService;
import com.tocersoft.member.dto.MemTeamMemberCondition;
import com.tocersoft.member.entity.MemTeamMember;
import com.tocersoft.member.entity.Member;
import com.tocersoft.member.model.MemberModel;
import com.tocersoft.member.service.IMemAddressService;
import com.tocersoft.member.service.IMemTeamMemberService;
import com.tocersoft.member.service.IMemberService;
import com.tocersoft.order.dto.OrderSellCondition;
import com.tocersoft.order.entity.OrderSell;
import com.tocersoft.order.entity.OrderSellItem;
import com.tocersoft.order.service.IOrderSellItemService;
import com.tocersoft.order.service.IOrderSellService;
import com.tocersoft.product.entity.Product;
import com.tocersoft.product.service.IProductService;


@ParentPackage("member")
@Namespace("/member/account")
@Controller
public class MemberAccountAction extends BaseAction implements ModelDriven<MemberModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(MemberAccountAction.class);

	private MemberModel model = new MemberModel();
	
	//个人中心-我的车队 中使用的车队成员list
	public List<Member> memberList = new ArrayList<Member>();

	@Resource(name = "memberServiceImpl")
	private IMemberService memberService;
	@Resource 
	private IMemAddressService memAddressService;
	@Resource
	private IOrderSellService orderSellService; 
	@Resource
	private IOrderSellItemService orderSellItemService; 
	@Resource
	private IProductService productService;
	@Resource(name = "memTeamMemberServiceImpl")
	private IMemTeamMemberService memTeamMemberService;
	@Resource(name = "gameEnrollServiceImpl")
	private IGameEnrollService gameEnrollService;
	
	/**
	 * 首页 个人资料
	 * @return 
	 */
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/pages/member/account/index.jsp") })
	public String index(){
		Member member = (Member)getSession().getAttribute(Constant.FRONT_USER);
		model.setItem(member);
//		model.getGameEnrollCondition().setMemberId(member.getId());
//		gameEnrollService.listGameEnrollByPage(model.getGameEnrollpageResult(), model.getGameEnrollCondition());

		
		return SUCCESS;
	}
	
	/**
	 * 会员中心我的报名分页
	 * @return 
	 */
	@Action(value = "gameEnrollPage", results = { @Result(name = "success", location = "/WEB-INF/pages/member/account/index.jsp") })
	public String gameEnrollPage(){
		Member member = (Member)getSession().getAttribute(Constant.FRONT_USER);
		model.setItem(member);
//		model.getGameEnrollCondition().setMemberId(member.getId());
//		gameEnrollService.listGameEnrollByPage(model.getGameEnrollpageResult(), model.getGameEnrollCondition());
		
		
		return SUCCESS;
	}
	
	/**
	 * 会员中心我的团队
	 * @return 
	 * shao
	 */
	@Action(value = "team", results = { @Result(name = "success", location = "/WEB-INF/pages/member/account/team.jsp") })
	public String team(){
		Member member = (Member)getSession().getAttribute(Constant.FRONT_USER);
		model.setItem(member);
		memberList = memberService.listMemberByTeamMember(member.getId());
		return SUCCESS;
	}
	/**
	 * 会员中心我的资料
	 * @return 
	 * shao
	 */
	@Action(value = "my_data", results = { @Result(name = "success", location = "/WEB-INF/pages/member/account/my_data.jsp") })
	public String my_data(){
		return SUCCESS;
	}
	/**
	 * 会员中心修改密码
	 * @return 
	 * shao
	 */
	@Action(value = "update_pwd", results = { @Result(name = "success", location = "/WEB-INF/pages/member/account/update_pwd.jsp") })
	public String update_pwd(){
		return SUCCESS;
	}
	/**
	 * 首页 收货地址
	 * @return 
	 */
	@Action(value = "address_list", results = { 
			/*@Result(name = "success", location = "/WEB-INF/pages/member/account/address_list.jsp") */
			@Result(name="success",type="redirectAction",params={"namespace","/member/address","actionName","address_list"})
			})
	public String address_list(){
		
		return SUCCESS;
	}
	
	/**
	 * 新增 收货地址
	 * @return 
	 */
	@Action(value = "add_address", results = { @Result(name = "success", location = "/WEB-INF/pages/") })
	public String add_address(){
		return SUCCESS;
	}
	
	
	/**
	 * 选择 收货地址
	 * @return 
	 */
	@Action(value = "select_address", results = { @Result(name = "success", location = "/WEB-INF/pages/") })
	public String select_address(){
		return SUCCESS;
	}
	
	/** 
	 * 我的收藏
	 * @return
	 */
	@Action(value="my_collect", results = {@Result(name = "success", location = "/WEB-INF/pages/member/account/my_collect.jsp")})
	public String my_collect(){
		return SUCCESS;
	}

	/**
	 * 我的订单
	 * @return 
	 */
	@Action(value = "my_order", results = { @Result(name = "success", location = "/WEB-INF/pages/member/account/my_order.jsp") })
	public String my_order(){
		Member member = (Member)getSession().getAttribute(Constant.FRONT_USER);
		// 查询会员全部有效订单
		PageResult<OrderSell> pageResult = new PageResult<OrderSell>();
		pageResult.setPageSize(10);
		pageResult.setCurrentPage(model.getPage());
		OrderSellCondition orderSellCondition = model.getOrderSellCondition();
		orderSellCondition.setMemberId(member.getId());
		orderSellCondition.setDeleteFlag(0);
		orderSellService.listOrderSellByPage(pageResult, orderSellCondition);
		List<OrderSell> orderSells = pageResult.getResult();
		// 格式化订单状态
		for(OrderSell orderSell : orderSells){
			if(orderSell.getOrderState() != null){
				String instantOrderState = orderSell.formatOrderState(orderSell.getOrderState());
				orderSell.setInstantOrderState(instantOrderState);
			}
		}
		model.setPageResult1(pageResult);
		model.setOrderSells(orderSells);
		return SUCCESS;
	}

	/**
	 * 我的订单-详情
	 * @return 
	 */
	@Action(value = "my_order_detail", results = { @Result(name = "success", location = "/WEB-INF/pages/member/account/order_detail.jsp") })
	public String my_order_detail(){
		// 查询指定订单详情
		OrderSell orderSell = orderSellService.getOrderSellById(model.getOrderId());
		List<OrderSellItem> orderSellItems = orderSellItemService.listOrderSellItemByOrderId(model.getOrderId());
		if(null != orderSell.getOrderState()){
			String instantOrderState = orderSell.formatOrderState(orderSell.getOrderState());
			orderSell.setInstantOrderState(instantOrderState);
		}
		orderSell.setOrderSellItems(orderSellItems);
		String sendCountry = orderSell.formatCountry(orderSell.getSendCountryCode()); 
		orderSell.setSendCountry(sendCountry);
		model.setOrderSell(orderSell);
		return SUCCESS;
	}

	/**
	 * 取消订单
	 * @return 
	 */
	@Action(value="cancelOrder")
	public String cancelOrder(){
		orderSellService.changeOrderState(model.getOrderId(), 4);
		return ajax(Status.success);
	}
	
	/**
	 * 确认收货
	 * @return 
	 */
	@Action(value="comfirmBusiness")
	public String comfirmBusiness(){
		orderSellService.changeOrderState(model.getOrderId(), 3);
		return ajax(Status.success);
	}
	
	/**
	 * 移除订单
	 * @return 
	 */
	@Action(value="clearOrder")
	public String clearOrder(){
		OrderSell orderSell = orderSellService.getOrderSellById(model.getOrderId());
		orderSell.setDeleteFlag(1);
		orderSellService.update(orderSell);
		return ajax(Status.success);
	}
	
	/**
	 * 重置密码
	 * @return 
	 */
	@Action(value = "change_pwd", results = { @Result(name = "success", location = "/WEB-INF/pages/member/account/change_pwd.jsp") })
	public String change_pwd(){
		return SUCCESS;
	}

	/**
	 * 确认订单
	 * @return 
	 */
	@Action(value = "confirm_order", results = { @Result(name = "success", location = "/WEB-INF/pages/") })
	public String confirm_order(){
		return SUCCESS;
	}
	
	/**
	 * 保存
	 * @return 
	 */
	@Action(value="doSave")
	public String doSave(){
		try {
			if(StringUtils.isBlank(model.getItem().getId())){
				memberService.add(model.getItem());
			}else{
				String encPwd ="";
				if(!StringUtils.isBlank(model.getItem().getPassword())){
					 encPwd  = DataUtil.encryptionPw(model.getItem().getPassword());
					 model.getItem().setPassword(encPwd);
				}else{
					 model.getItem().setPassword(model.getItem().getNewPassword());
				}
				memberService.update(model.getItem());
			}
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}
	
	/**
	 * 修改密码
	 * @return 
	 */
	@Action(value = "doUpdatePwd")
	@Validations(requiredStrings = {
	  @RequiredStringValidator(fieldName = "item.password", message = "新密码不能为空"),
	  @RequiredStringValidator(fieldName = "passworded", message = "旧密码不能为空")
	})
	@InputConfig(resultName = "error")
	public String doUpdatePwd(){
		Member saMember = (Member)getSession().getAttribute(Constant.FRONT_USER);
		model.getPassworded();
		Member serviceMember = memberService.getMemberByAccountPwd(saMember.getEmail(), model.getPassworded());
		if(serviceMember == null ){
			return ajax(Status.error , "旧密码输入不正确");
		}
		
		String encPwd = DataUtil.encryptionPw(model.getItem().getPassword());
		serviceMember.setPassword(encPwd);
		memberService.update(serviceMember);
		return ajax(Status.success);
	}

	/**
	 * 修改密码
	 * @return 
	 */
	@Action(value = "doUpdateAccount")
	@InputConfig(resultName = "error")
	public String doUpdateAccount(){
		Member saMember = (Member)getSession().getAttribute(Constant.FRONT_USER);
		
		saMember.setName(model.getItem().getName());
		saMember.setPostCode(model.getItem().getPostCode());
		saMember.setMobile(model.getItem().getMobile());
		saMember.setProvince(model.getItem().getProvince());
		saMember.setProvinceCity(model.getItem().getProvinceCity());
		saMember.setProvinceId(model.getItem().getProvinceId());
		saMember.setCity(model.getItem().getCity());
		saMember.setCityId(model.getItem().getCityId());
		saMember.setDistrict(model.getItem().getProvince());
		saMember.setDistrictId(model.getItem().getDistrictId());
		saMember.setAddress(model.getItem().getAddress());
		memberService.update(saMember);
		return ajax(Status.success);
	}
	
 
	@Override
	public MemberModel getModel() {
		return model;
	}
	
}