package com.tocersoft.member.action.front;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.utils.Constant;
import com.tocersoft.base.utils.Utils;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.member.entity.Member;
import com.tocersoft.member.model.MemberModel;
import com.tocersoft.member.service.IMemberService;

@ParentPackage("front")
@Namespace("/register")
@Controller
public class RegisterAction extends BaseAction implements ModelDriven<MemberModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(RegisterAction.class);

	private MemberModel model = new MemberModel();

	@Resource(name = "memberServiceImpl")
	private IMemberService memberService;
	
	/**
	 * 临时链接 - 用于修改注册申请成功的页面
	 * @return 
	 */
	@Action(value = "to_apply_success", results = { @Result(name = "success", location = "/WEB-INF/pages/member/register/reg_apply_success.jsp") })
	public String toApplySuccess(){
		return SUCCESS;
	}
	
	/**
	 * 临时链接 - 用于修改注册申请成功的页面
	 * @return 
	 */
	@Action(value = "to_register", results = { @Result(name = "success", location = "/WEB-INF/pages/member/register/register_index.jsp") })
	public String toRegister(){
		
		return SUCCESS;
	}
	
	/**
	 * 跳转到注册页
	 * @return 
	 */
	@Action(value = "index", results = { 
			@Result(name = "success", location = "/WEB-INF/pages/member/register/register_index.jsp"),
			@Result(name = "code_error", location = "/WEB-INF/pages/member/register/code_error.jsp")
	})
	public String index(){
		return SUCCESS;
	}
	
	/**
	 * 注册
	 * @return 
	 */
	@Action(value = "register", results = { @Result(name = "success", location = "/WEB-INF/pages/member/register/register_index.jsp") })
	public String register(){
		String str = "{\"token_type\":\"bearer\",\"expires_in\":2593566,\"refresh_token\":\"268852|0.OdvB28P2d01et3JvnSFehTcZKPnXtdBZ.451161880.1402023050599\",\"user\":{\"id\":451161880,\"name\":\"12\"},\"access_token\":\"268852|6.861dc872fff49eb29432df1971f5deb4.2592000.1404651600-451161880\"}";
		
		JSONObject tokenJson = (JSONObject) JSONValue.parse(str);
		return SUCCESS;
	}
	
	/**
	 * 注册成功
	 * @return 
	 */
	@Action(value = "registerSuccess", results = { @Result(name = "success", location = "/WEB-INF/pages/member/register/register_success.jsp") })
	public String registerSuccess(){
		try {
			model.getItem().setEmailactive(1);
			memberService.add(model.getItem());
			// 发送邮件去激活邮箱
			memberService.sendUrlEmail(model.getItem());
		} catch (Exception e) {
			String msg = "注册时发生异常：" + e.getMessage();
			logger.error(msg,e);
		}
		return SUCCESS;
	}
	
	/**
	 * 激活邮箱
	 * @return
	 */
	@Action(value = "activeEmail", results = { @Result(name = "success", location = "/WEB-INF/pages/member/login/login_index.jsp") })
	public String activeEmail(){
		try {
			memberService.updateEmailState(model.getItem().getId(),2);
			return SUCCESS;
		} catch (Exception e) {
			String msg = "激活时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ERROR;
		}
	}
	
	/**
	 * 验证帐号是否存在
	 * 参数： model.account
	 * 处理： 验证帐号是否存在
	 * 输出： json字符串 true - 存在 false - 不存在
	 */
	@Action(value = "checkAccount")
	@Validations(requiredStrings = {@RequiredStringValidator(fieldName = "account", message = "帐号不能为空")})
	@InputConfig(resultName = "ajaxError")
	public void checkAccount(){
		String account = model.getAccount();
		Member member = memberService.getMemberByAccount(account);
		if(null == member){
			this.ajax(Status.success, "200");
		}else{
			this.ajax(Status.success, "100");
		}
	}
	
	/**
	 * 验证手机是否存在
	 * 参数： model.account
	 * 处理： 验证手机是否存在
	 * 输出： json字符串 true - 存在 false - 不存在
	 */
	@Action(value = "checkMobile")
	@Validations(requiredStrings = {@RequiredStringValidator(fieldName = "mobile", message = "手机不能为空")})
	@InputConfig(resultName = "ajaxError")
	public void checkMobile(){
		String mobile = model.getMobile();
		Member member = memberService.getMemberByAccount(mobile);
		if(null == member){
			this.ajax(Status.success, "200");
		}else{
			this.ajax(Status.success, "100");
		}
	}
	
	/**
	 * 验证邮箱是否存在
	 * 参数： model.email
	 * 处理： 验证邮箱是否存在
	 * 输出： json字符串 true - 存在 false - 不存在
	 */
	@Action(value = "checkEmail")
	@Validations(requiredStrings = {@RequiredStringValidator(fieldName = "email", message = "邮箱不能为空")})
	@InputConfig(resultName = "ajaxError")
	public void checkEmail(){
		String email = model.getEmail();
		Member member = memberService.getMemberByEmail(email);
		if(null == member){
			this.ajax(Status.success, "200");
		}else{
			this.ajax(Status.success, "100");
		}
	}
	
	/**
	 * 验证验证码是否输入正确
	 * 参数：verCode 验证码
	 * 处理：与Session中的verCode匹配，验证验证码是否输入正确 
	 * 输出：	json字符串 success - 正确 error - 错误
	 */
	@Action(value = "checkVerCode")
	public String checkVerCode() {
		// 获取客户端输入的验证码
		String verCode = model.getVerCode();
		// 获取服务器端正确的验证码
		String verCodeSession = (String) getSession().getAttribute(Constant.JAPTCHACODE);
		if (!StringUtils.isNotBlank(verCode) || !verCode.toLowerCase().equals(verCodeSession.toLowerCase())) {
			// 如果验证码为空，或者验证码不正确
			return ajax(Status.error, "验证码输入不正确");
		}
		return ajax(Status.success);
	}

	/**
	 * 会员注册
	 * @return 
	 * @throws UnsupportedEncodingException 
	 */
	@Action(value = "regist", 
			interceptorRefs = { 
			@InterceptorRef("token"),@InterceptorRef("baseStack"), @InterceptorRef("trimInterceptor") 
		}, 
		results = { 
			@Result(name = "success", location = "/WEB-INF/pages/member/register/register_success.jsp"),
			@Result(name = "invalid.token", location = "/WEB-INF/pages/common/error_token.jsp")
		})
	@Validations(requiredStrings = {
				@RequiredStringValidator(fieldName = "item.password", message = "注册密码不能为空"),
				@RequiredStringValidator(fieldName = "item.email", message = "注册邮箱不能为空"),
			})
	@InputConfig(resultName = "error")
	public String regist() throws UnsupportedEncodingException{
		Member item = model.getItem();
		memberService.regist(item,null);
		// 注册成功后即为登录状态 ( 还未邮件激活 非登录状态 )
		getSession().setAttribute(Constant.FRONT_USER, item);
		
		Object proUrl = getSession().getAttribute("preUrl");
		if(proUrl == null){
			return ajax(Status.success,"");
		}else{
			getSession().removeAttribute("preUrl");
			return ajax(Status.success,proUrl.toString());
		}
		
	}
	
	/**
	 * 临时链接 - 用于修改注册申请成功的页面
	 * @return 
	 */
	@Action(value = "to_regist_success", results = { @Result(name = "success", location = "/WEB-INF/pages/member/register/register_success.jsp") })
	public String toRegistSuccess(){
		return SUCCESS;
	}

	/**
	 * 修改
	 * @return 
	 */
	@Action(value = "update", results = { @Result(name = "success", location = "/WEB-INF/pages/") })
	public String toUpdate(){
		Member item = memberService.getMemberById(model.getItem().getId());
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
			memberService.delByIds(model.getSelIds().split(","));
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
				memberService.add(model.getItem());
			}else{
				memberService.update(model.getItem());
			}
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}


	@Override
	public MemberModel getModel() {
		return model;
	}}

