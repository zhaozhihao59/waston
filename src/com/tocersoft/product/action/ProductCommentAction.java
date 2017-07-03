package com.tocersoft.product.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

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
import com.tocersoft.base.page.PageResult;
import com.tocersoft.base.utils.Constant;
import com.tocersoft.base.utils.Utils;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.member.entity.Member;
import com.tocersoft.product.dto.ProductCommentCondition;
import com.tocersoft.product.entity.ProductComment;
import com.tocersoft.product.model.ProductCommentModel;
import com.tocersoft.product.service.IProductCommentService;


@ParentPackage("front")
@Namespace("/productComment")
@Controller
public class ProductCommentAction extends BaseAction implements ModelDriven<ProductCommentModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(ProductCommentAction.class);

	private ProductCommentModel model = new ProductCommentModel();

	@Resource(name = "productCommentServiceImpl")
	private IProductCommentService productCommentService;



	/**
	 * 首页
	 * @return 
	 */
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/product/product_comment_list.jsp") })
	public String index(){
		return SUCCESS;
	}

	/**
	 * 分页查询
	 * @return 
	 */
	@Action(value = "listProductCommentByPage")
	public String listProductCommentByPage(){
		productCommentService.listProductCommentByPage(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{
			"id","memberName","productId","productName","content","mark","createDate"
		});
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
		ProductComment item = productCommentService.getProductCommentById(model.getItem().getId());
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
			productCommentService.delByIds(model.getSelIds().split(","));
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
	 * @throws UnsupportedEncodingException 
	 */
	@Action(value="doSave")
	public String doSave() throws UnsupportedEncodingException{
		Member member = (Member)getSession().getAttribute(Constant.FRONT_USER);
		if(null == member){
			// 获取请求的url
			String preUrl = getRequest().getParameter("preUrl");
			getSession().setAttribute("preUrl", preUrl);
			return ajax(Status.error,"请先登录");
		}
		ProductComment comment = model.getItem();
		comment.setMemberId(member.getId());
		try {
			if(StringUtils.isBlank(comment.getId())){
				productCommentService.add(model.getItem());
			}else{
				productCommentService.update(comment);
			}
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}

	@Override
	public ProductCommentModel getModel() {
		return model;
	}
}

