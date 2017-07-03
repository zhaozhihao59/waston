package com.tocersoft.base.action;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.stereotype.Controller;

import com.tocersoft.base.utils.Constant;
import com.tocersoft.base.utils.VerifyCode;

@ParentPackage("front")
@Namespace("/")
@Controller
public class CommonAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6243836344067336946L;
	private Logger logger = Logger.getLogger(CommonAction.class);

	/**
	 * 生成验证码
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "japtcha")
	public String japtcha() throws Exception {
		getResponse().setDateHeader("Expires", 0);
		getResponse().setHeader("Cache-Control",
				"no-store, no-cache, must-revalidate");
		getResponse().addHeader("Cache-Control", "post-check=0, pre-check=0");
		getResponse().setHeader("Pragma", "no-cache");
		getResponse().setContentType("image/jpeg");

		// 生成验证码，写入用户session
		String verifyCode = VerifyCode.generateTextCode(
				VerifyCode.TYPE_UPPER_ONLY, 4, null);
		getRequest().getSession()
				.setAttribute(Constant.JAPTCHACODE, verifyCode);
		logger.debug("验证码:" + verifyCode);
		// 输出验证码给客户端
		ServletOutputStream out = getResponse().getOutputStream();
		BufferedImage bim = VerifyCode.generateImageCode(verifyCode, 90, 30, 3,
				true, Color.WHITE, Color.BLACK, null);
		ImageIO.write(bim, "JPEG", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
		return null;
	}

	@Action(value = "japtchaCheck")
	public String japtchaCheck() throws Exception {
		String yzm = (String) getRequest().getSession().getAttribute(
				Constant.JAPTCHACODE);
		if (StringUtils.isBlank(yzm)) {
			ajax("error");
			return null;
		}
		String code = getParameter("yzm");
		if (StringUtils.isBlank(code)) {
			ajax("error");
			return null;
		}
		if (yzm.equalsIgnoreCase(code)) {
			ajax("success");
			return null;
		} else {
			ajax("error");
			return null;
		}
	}
}
