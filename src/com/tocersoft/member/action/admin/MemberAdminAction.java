package com.tocersoft.member.action.admin;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.utils.DataUtil;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.member.entity.Member;
import com.tocersoft.member.model.MemberModel;
import com.tocersoft.member.service.IMemberService;


@ParentPackage("admin")
@Namespace("/admin/member")
@Controller
public class MemberAdminAction extends BaseAction implements ModelDriven<MemberModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(MemberAdminAction.class);

	private MemberModel model = new MemberModel();

	@Resource(name = "memberServiceImpl")
	private IMemberService memberService;

	/**
	 * 首页
	 * @return 
	 */
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/member/member_list.jsp") })
	public String index(){
		return SUCCESS;
	}
	
	/**
	 * 选择标签
	 * @return 
	 */
	@Action(value = "choose_tag", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/member/member_add_choose_tag.jsp") })
	public String toChooseTag(){
		
		return SUCCESS;
	}

	/**
	 * 分页查询
	 * @return 
	 */
	@Action(value = "listMemberByPage")
	public String listMemberByPage(){
		memberService.listMemberByPage(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{
			"id","createDate","name","mobile","email","emailactive","account","qq","wechat","address","industry","sex","brief","provinceCity","postCode"
		});
		return ajax(root);
	}
	
	/**
	 * 用于自动补全控件
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "autoListAllMember")
	public String autoListAllMember(){
		String key = getRequest().getParameter("key");
		List<Member> memebers = memberService.listMemberByKey(key);
		JSONArray root = new JSONArray();
		for(Member item : memebers){
			JSONObject json = new JSONObject();
			json.put("id", item.getId());
			json.put("name",item.getName());
			json.put("account",item.getAccount());
			json.put("email",item.getEmail());
			json.put("mobile",item.getMobile());
			json.put("wechat",item.getWechat());
			root.add(json);
		}
		return ajax(root);
	}

	/**
	 * 新增
	 * @return 
	 */
	@Action(value = "add", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/member/member_add.jsp") })
	public String toAdd(){
		return SUCCESS;
	}
	
	/**
	 * 详细
	 */
	@Action(value="detail", results = { @Result(name = "success", location= "/WEB-INF/pages/admin/member/member_detail.jsp")})
	public String doDetail(){
		Member item = memberService.getMemberById(model.getItem().getId());
		model.setItem(item);
		return SUCCESS;
	}
	
	/**
	 * 跳转到修改
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "update", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/member/member_add.jsp") })
	public String toUpdate(){
		// 查询会员实体
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


	@Override
	public MemberModel getModel() {
		return model;
	}}


