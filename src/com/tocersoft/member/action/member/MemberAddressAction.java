package com.tocersoft.member.action.member;

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
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.utils.Constant;
import com.tocersoft.member.entity.MemAddress;
import com.tocersoft.member.entity.Member;
import com.tocersoft.member.model.MemAddressModel;
import com.tocersoft.member.service.IMemAddressService;
import com.tocersoft.member.service.IMemberService;
import com.tocersoft.order.entity.OrderSellItem;


@ParentPackage("front")
@Namespace("/member/address")
@Controller
public class MemberAddressAction extends BaseAction implements ModelDriven<MemAddressModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(MemberAddressAction.class);

	private MemAddressModel model = new MemAddressModel();

	@Resource(name = "memberServiceImpl")
	private IMemberService memberService;
	@Resource 
	private IMemAddressService memAddressService;
	
	/**
	 * 首页 收货地址
	 * @return 
	 */
	@Action(value = "address_list", results = { @Result(name = "success", location = "/WEB-INF/pages/member/account/address_list.jsp") })
	public String address_list(){
		Member saMember = (Member)getSession().getAttribute(Constant.FRONT_USER);
		model.getPageResult().setPageSize(5);
		model.getCondition().setMemberId(saMember.getId());
		memAddressService.listMemAddressByPage(model.getPageResult(),model.getCondition());
		
		MemAddress item = memAddressService.getMemAddressById(model.getItem().getId());
		model.setItem(item);
		return SUCCESS;
	}
	/**
	 * 保存
	 * @return 
	 */
	@Action(value="doSave")
	public String doSave(){
		try {
			Member saMember = (Member)getSession().getAttribute(Constant.FRONT_USER);
			model.getItem().setMemberId(saMember.getId());
			if(StringUtils.isBlank(model.getItem().getId())){
				memAddressService.add(model.getItem());
			}else{
				memAddressService.update(model.getItem());
			}
			memAddressService.doUpdateDefaultAddress(model.getItem().getId(), saMember.getId());
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}
	/**
	 * 保存
	 * @return 
	 */
	@Action(value="updaAddressDefault")
	public String updaAddressDefault(){
		try {
			Member saMember = (Member)getSession().getAttribute(Constant.FRONT_USER);
			
			model.getItem().setMemberId(saMember.getId());
			
	/*		model.getPageResult().setPageSize(10);
			model.getCondition().setMemberId(saMember.getId());
			// 查询已经设置为常用地址的数据
			model.getCondition().setAddressStatus(1);
			memAddressService.listMemAddressByPage(model.getPageResult(),model.getCondition());
			if(model.getPageResult().getResult().size()>0){
				if(model.getItem().getAddressStatus() == 1){
					return ajax(Status.error,"默认地址只能有一个");
				}
			}*/
			if(!StringUtils.isBlank(model.getItem().getId())){
				memAddressService.doUpdateDefaultAddress(model.getItem().getId(), saMember.getId());
			}
			return ajax(Status.success,"设置成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}
	
	/**
	 * 临时编辑收货地址
	 * @return 
	 */
	@Action(value = "addressEdit", results = { @Result(name = "success", location = "/WEB-INF/pages/member/account/add_address.jsp") })
	public String addressEdit(){
		MemAddress item = memAddressService.getMemAddressById(model.getItem().getId());
		model.setItem(item);
		return SUCCESS;
	}
	 
	/**
	 * 会员中中心临时编辑收货地址
	 * @return 
	 */
	@Action(value = "accoutAddressEdit", results = { 
/*			@Result(name = "success", location = "/WEB-INF/pages/member/account/add_address.jsp"),
*/			@Result(name="success",type="redirectAction",params={"namespace","/member/address","actionName","address_list"})
})
	public String accoutAddressEdit(){
		return SUCCESS;
	}
	
	/**
	 * 删除
	 * @return 
	 */
	@Action(value="del",
			results = {
			@Result(name="success",type="redirectAction",params={"namespace","/member/address","actionName","address_list"}),
			@Result(name="error",type="redirectAction",params={"namespace","/member/address","actionName","address_list"})
			 }
	)
	@Validations(requiredStrings = {
				@RequiredStringValidator(fieldName = "selIds", message = "ID不能为空")
			}
	)
	@InputConfig(resultName = "ajaxError")
	public String del(){
		try {
			Member saMember = (Member)getSession().getAttribute(Constant.FRONT_USER);
			memAddressService.delByIds(model.getSelIds().split(","),saMember.getId());
			return SUCCESS;
		} catch (Exception e) {
			 return ERROR;
		}
	}

	 
	@Override
	public MemAddressModel getModel() {
		return model;
	}
	
	 
  
	
}