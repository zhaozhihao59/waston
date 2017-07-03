package com.tocersoft.member.action.front;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
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
import com.tocersoft.base.utils.Constant;
import com.tocersoft.base.utils.DataUtil;
import com.tocersoft.base.utils.UUIDUtil;
import com.tocersoft.base.utils.Utils;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.email.service.SysMailService;
import com.tocersoft.member.entity.Member;
import com.tocersoft.member.model.MemberModel;
import com.tocersoft.member.service.IMemberService;
import com.tocersoft.order.entity.OrderCartItem;
import com.tocersoft.order.service.IOrderCartItemService;

@ParentPackage("front")
@Namespace("/login")
@Controller
public class LoginAction extends BaseAction implements ModelDriven<MemberModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(LoginAction.class);

	private MemberModel model = new MemberModel();

	@Resource(name = "memberServiceImpl")
	private IMemberService memberService;
	@Resource
	private SysMailService emailService;
	@Resource(name = "orderCartItemServiceImpl")
	private IOrderCartItemService orderCartItemService;
	
	/**
	 * 正常登录
	 * @return
	 */
	@Action(value = "index", results = { 
			@Result(name = "index", location = "/WEB-INF/pages/member/login/login_index.jsp")
		})
	public String index(){
		getSession().removeAttribute("preUrl");
		return INDEX;
	}
	
	/**
	 * 拦截登录
	 * 描述：通过此方法进入登录页后，可回跳至原页面
	 * @return
	 */
	@Action(value = "index_back", results = {
			@Result(name = "index", location = "/WEB-INF/pages/member/login/login_index.jsp")
		})
	public String indexBack(){
		HttpServletRequest request = getRequest();
		getSession().setAttribute("preUrl",request.getHeader("Referer"));
		return INDEX;
	}
	
/*	*//**
	 * 拦截登录
	 * 描述：通过此方法进入登录页后，可回跳至原页面
	 * @return
	 *//*
	@Action(value = "index_back", results = {
			@Result(name = "index", location = "/WEB-INF/pages/front/comm/login-box.jsp")
		})
	public String indexBack(){
		return INDEX;
	}*/
	
	/**
	 * 登录方法：	验证会员名和密码是否正确 
	 * 参数：  
	 * 处理： 	验证会员名和密码是否正确 
	 * 输出：		json字符串 
	 * 			success - 正确，设置Session中的会员信息 
	 * 			error - 错误
	 */
//	@Action(value = "login",
//			results={ 
//			@Result(name="home",type="redirectAction",location="home"),
//			@Result(name="login",type="redirectAction",params={"namespace","/","actionName","index"})
//		})
	@Action(value = "login")
//	@InputConfig(resultName = "ajaxError")
	public String login() { 
		// 会员验证是否通过 true-通过 false-不通过
		boolean loginPass = false;
		
		// 验证用户名与密码是否正确
		Member memberDb = memberService.getMemberByAccountPwd(model.getItem().getEmail(), model.getItem().getPassword());
		// 不存在此用户
		if (null != memberDb){
			// 会员验证通过 - 通过会员帐号登录
			loginPass = true;
		}
		
		if(loginPass){
			if(memberDb.getEmailactive() == 1){
				return ajax(Status.error, "邮箱未激活");
			}
			
			// 判断是否记住密码
			if (model.getIsRmbPwd() != null && model.getIsRmbPwd().equals(1)) {
				setCookie("loginOutCode", memberDb.getId().toString(),
						30 * 24 * 60 * 60); // 存放30天
				setCookie(memberDb.getId().toString() + "account", model.getItem().getEmail(), 30 * 24 * 60 * 60);
				setCookie(memberDb.getId().toString() + "pwd", model.getItem().getPassword(), 30 * 24 * 60 * 60);
			} else {
				String memberId = getCookie("loginOutCode");
				if (memberId != null) {
					removeCookie("loginOutCode");
					removeCookie(memberId + "account");
					removeCookie(memberId + "pwd");
				}
			}
			// 将会员信息存在Session中
			getSession().setAttribute(Constant.FRONT_USER, memberDb);
			
			/** =============== 将数据库中的购物车明细项同步到Session与Cookie中 方泉 2014-08-14 ================= */
			/*	
			// 先获取到Cookie中的购物车数据
			String orderCartItemListCookie = this.getCookie("orderCartItemListCookie");
			List<OrderCartItem> orderCartItemList = (List<OrderCartItem>)this.getSession().getAttribute(orderCartItemListCookie);
			
			// 如果Cookie中购物车明细项为空，则将数据库中的明细项加载到Cookie中
			if(null == orderCartItemList){
				// 取得数据库中购物车明细项
				orderCartItemList = orderCartItemService.listOrderCartItemByMemberId(memberDb.getId());
				// 构建购物车Cookie
				orderCartItemListCookie = UUIDUtil.uuid();
				this.setCookie("orderCartItemListCookie", orderCartItemListCookie);
			}else{
				// 如果不为空时，则先将Cookie中的购物车明细存入数据库中，之后再同步到Session中
				orderCartItemService.addOrUpdate(orderCartItemList, memberDb.getId());
				// 之后再同步到Session中
				orderCartItemList = orderCartItemService.listOrderCartItemByMemberId(memberDb.getId());
			}
			// 重新放入Session与Cookie
			this.getSession().setAttribute(orderCartItemListCookie, orderCartItemList);
			*/
			/** =============== 将数据库中的购物车明细项同步到Session与Cookie中 方泉 2014-08-14 END ================= */
			
			String preUrl = (String)getSession().getAttribute("preUrl");
			JSONObject result = new JSONObject();
			result.put("preUrl", preUrl);
			result.put("status", "success");
			return ajax(result);
		}else{
			return ajax(Status.error, "用户名或密码不正确");
		}
	}
	
	/**
	 * 忘记密码 步骤一
	 * @return 
	 */
	@Action(value = "forget", results = { @Result(name = "success", location = "/WEB-INF/pages/member/login/login_forget.jsp") })
	public String forget(){
		return SUCCESS;
	}
	
	/** 发送密码找回邮件   忘记密码 步骤二*/
	@Action(value = "sendForgetEmail")
	public String sendForgetEmail(){
//		 // 获取客户端输入的验证码
//		String verCode = model.getVerCode();
//		// 获取服务器端正确的验证码
//		String verCodeSession = (String) getSession().getAttribute(Constant.JAPTCHACODE);
//		if (StringUtils.isBlank(verCode) || !verCode.toLowerCase().equals(verCodeSession.toLowerCase())) {
//			// 如果验证码为空，或者验证码不正确
//			return ajax(Status.error, "验证码输入不正确");
//		}
		
		// 发送时间
		// 验证发送间隔
		boolean checkSendTime = false;
		Date lastTime = (Date) getSession().getAttribute("sendTime");
		if (lastTime == null) {
			checkSendTime = true;
		}else{
			Long sendEmailTimeLong = lastTime.getTime();
			Long now = new Date().getTime();
			Long reduceResult = (now - sendEmailTimeLong) / 1000;
			getRequest().setAttribute("reduceResult", reduceResult);
			if (reduceResult >= 60) {
				getSession().setAttribute("sendTime", new Date());
				getSession().removeAttribute("sendEmailTime");
				checkSendTime = true;
			}
			
			if(!checkSendTime){
				return ajax(Status.error, "请于"+(60-reduceResult)+"秒后再次发送邮件");
			}
		}
		
		Member member = memberService.getMemberByEmail(model.getEmail());
		if(member == null){
			return ajax(Status.error, "该邮箱不存在，请核对后在再次发送。");
		}

		// 构造发送邮件的内容
		String serverPort = "";
		if (getRequest().getServerPort() != 80) {
			serverPort = ":" + getRequest().getServerPort();
		}
		String serverName = getRequest().getServerName();
		String basePath = getRequest().getScheme() + "://" + serverName
				+ serverPort + getRequest().getContextPath() + "/";
		StringBuffer activateUrl = new StringBuffer();
		activateUrl.append(basePath);

		// 构造验证码
		String authCode = DataUtil.encryptionPw(member.getEmail() + member.getPassword());
		getSession().setAttribute(member.getEmail(), authCode);

		Date sendDate = new Date();
		activateUrl.append("login/forget_edit.htm?id=" + member.getEmail()
				+ "&code=" + authCode + "&m=" + sendDate.getTime());
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("url", activateUrl.toString());
		
		emailService.sendEmail("2","找回密码 - 澳新邮易购", member.getEmail(), data);
		
		getSession().setAttribute("sendTime" , new Date());
		return ajax(Status.success);
	}
	
	
	/**
	 * 重置密码 步骤三
	 * @return 
	 */
	@Action(value = "forget_edit", results = { @Result(name = "success", location = "/WEB-INF/pages/member/login/login_forget_edit_password.jsp") })
	public String forget_edit(){
		List<Member> listLogin = memberService.listMemberByKey(model.getItem().getEmail());
		if (null != listLogin && listLogin.size() > 0) {
			model.setItem(listLogin.get(0));
		}

		// 获得用户帐号
		String id = getParameter("id");

		// 获得code
		String code = getParameter("code");

		// 获得发送时间m
		String m = getParameter("m");
		if (StringUtils.isBlank(id) || StringUtils.isBlank(code)
				|| StringUtils.isBlank(m)) {
			return ERROR;
		}

		// 验证30分钟内超时
		Long sendTime = Long.parseLong(m);
		Long reduceResult = new Date().getTime() - sendTime;
		if (((reduceResult / 1000) / 60) > 30 * 60) {
			return ERROR;
		}

		// 验证会员名是否存在
		Member member = memberService.getMemberByEmail(id);
		if (null == member || !member.getEmail().equals(id)) {
			return ERROR;
		}

		// 验证授权验证码是否正确
		String authCode = DataUtil.encryptionPw(member.getEmail()
				+ member.getPassword());
		if (!authCode.equals(code)) {
			return ERROR;
		}

		// 验证通过后，将参数返回至修改密码页面
		getRequest().setAttribute("id", id);
		getRequest().setAttribute("code", code);
		getRequest().setAttribute("m", m);
		model.setItem(member);

		return SUCCESS;
	}
	
	
	/**
	 * 重置成功 步骤四
	 * @return 
	 */
	@Action(value = "edit_forgeted_pwd",results={ 
			@Result(name="success",type="redirectAction",location="home")
		})
	public String edit_forget_pwd(){
		// 获得用户帐号
		String id = getParameter("id");

		// 获得code 
		String code = getParameter("code");
		
		
		// 验证会员名是否存在
		Member member = memberService.getMemberByEmail(id);
		if (null == member || !member.getEmail().equals(id)) {
			return ajax(Status.error , "非法提交。");
		}

		// 验证授权验证码是否正确
		String authCode = DataUtil.encryptionPw(member.getEmail()+ member.getPassword());
		if (!authCode.equals(code)) {
			return ajax(Status.error , "非法提交。");
		}

		// 验证通过后，修改密码
		member.setPassword(DataUtil.encryptionPw(model.getItem().getPassword()));
		memberService.update(member);
		
		ServletActionContext.getRequest().getSession().setAttribute(Constant.FRONT_USER, member);
		return ajax(Status.success);
	}
	
	/**
	 * 重置成功
	 * @return 
	 */
	@Action(value = "forget_success", results = { @Result(name = "success", location = "/WEB-INF/pages/member/login/login_forget_edit_success.jsp") })
	public String forget_success(){
		return SUCCESS;
	}
	
	/**
	 * 分页查询
	 * @return 
	 */
	@Action(value = "listMemberByPage")
	public String listMemberByPage(){
		memberService.listMemberByPage(model.getPageResult(),model.getCondition());
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
	
	/**
	 * 退出当前会员登录
	 * @return
	 */
	@Action(value = "logout",results = {
			@Result(name="success",type="redirectAction",params={"namespace","/","actionName","index"})
	})
	public String logout(){
//		Member member = (Member) getSession().getAttribute(Constant.FRONT_USER);
//		
//		// 清除COOKIE
//		removeCookie("loginOutCode");
//		removeCookie(member.getId().toString() + "account");
//		removeCookie(member.getId().toString() + "pwd");
		
		// 清除当前session里存的会员信息
		removeSession(Constant.FRONT_USER);
		return SUCCESS;
	}


	@Override
	public MemberModel getModel() {
		return model;
	}}

