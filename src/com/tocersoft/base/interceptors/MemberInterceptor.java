package com.tocersoft.base.interceptors;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tocersoft.base.utils.Constant;
import com.tocersoft.base.utils.DoubleUtil;
import com.tocersoft.base.utils.Utils;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.member.entity.Member;
import com.tocersoft.order.entity.OrderCartItem;

/**
 * 会员后台拦截器，用于验证会员是否已经登录
 * 
 * @author vinartis
 * @createDate Apr 8, 2013
 */
@Component("memberInterceptor")
public class MemberInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 2365641900033439481L;
	
	Logger logger = Logger.getLogger(MemberInterceptor.class);

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		logger.info("==== 进入会员登录过滤器 ====");
		HttpServletRequest request = ServletActionContext.getRequest();
		
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute(Constant.FRONT_USER);
		
		// 未登录的用户，不能进入会员中心
		String requestType = request.getHeader("X-Requested-With");
		logger.debug("request type : " + requestType);
		
		String requestUri = request.getRequestURI();
		if(requestUri.indexOf("alipayNotify") != -1){
			return actionInvocation.invoke();
		}
		
		if(null == member){
			if("XMLHttpRequest".equalsIgnoreCase(requestType)){
				ActionSupport action =(ActionSupport)actionInvocation.getAction();
				action.addFieldError("", "登录超时，请重新登录");
				return "ajaxError";
			}
			
			// 获得请求URL
			String preUrl = WebUtils.getPrevUrl(request);
			
			// 将URL的参数表进行截取，对参数表字符串进行转码处理
			Integer beginIndex = preUrl.indexOf("?");
			
			// 如果有带参数的，需要对参数字符串进行UTF-8编码
			if(null != beginIndex && beginIndex >= 0){
				String urlStr = preUrl.substring(0, beginIndex);
				String paramStr = preUrl.substring(beginIndex);
				String paramStrUTF8 = Utils.stringFormat(paramStr);
				preUrl = urlStr + paramStrUTF8;
			}
			session.setAttribute("preUrl", preUrl);
			
			return "login";
		}else{
			
			// 获得购物车的客户端Cookie
			Cookie cookies[] = ServletActionContext.getRequest().getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if ("orderCartItemListCookie".equals(cookie.getName())) {
						// 获得购物车的Cookie
						List<OrderCartItem> orderCartItemList = (List<OrderCartItem>)session.getAttribute(cookie.getValue());
						if(null != orderCartItemList){
							Double cartItemNum = 0D;
							for(OrderCartItem cookieItem : orderCartItemList){
								cartItemNum = DoubleUtil.sum(cartItemNum, cookieItem.getNum());
							}
							session.setAttribute("cartItemNum",cartItemNum);
						}
					}
				}
			}
			
			return actionInvocation.invoke();
		}
		
	}

}