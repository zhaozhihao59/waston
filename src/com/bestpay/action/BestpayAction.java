package com.bestpay.action;


import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.bestpay.util.BestpayUtil;
import com.tocersoft.base.action.BaseAction;

/**
 *<pre>
 *project:heros
 *path:com.bestpay.action.BestpayAction.java
 *description : 
 *
 *</pre>
 * @author J.殷嘉健
 * @date 2015年3月12日下午2:34:06
 */

@ParentPackage("front")
@Namespace("/pay")
@Controller
public class BestpayAction extends BaseAction {	
	@Action(value = "pay")
	public void pay() {
		try {
			BestpayUtil.pay(getRequest(), 5.1,2.2, "123", "12");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
