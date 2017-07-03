package com.tocersoft.system.action.api;

import java.io.File;
import java.util.Date;

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

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.utils.CommonUtil;
import com.tocersoft.base.utils.DateUtil;
import com.tocersoft.base.utils.UUIDUtil;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.system.entity.SysUploadFile;
import com.tocersoft.system.model.SysUploadFileModel;
import com.tocersoft.system.service.ISysUploadFileService;


@ParentPackage("api")
@Namespace("/api/file")
@Controller
public class SysUploadFileApiAction extends BaseAction implements ModelDriven<SysUploadFileModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(SysUploadFileApiAction.class);

	private SysUploadFileModel model = new SysUploadFileModel();

	@Resource(name = "sysUploadFileServiceImpl")
	private ISysUploadFileService sysUploadFileService;



	/**
	 * 首页
	 * @return 
	 */
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/pages/") })
	public String index(){
		return SUCCESS;
	}

	/**
	 * 分页查询
	 * @return 
	 */
	@Action(value = "listSysUploadFileByPage" )
	public String listSysUploadFileByPage(){
		sysUploadFileService.listSysUploadFileByPage(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{"id","url"});
		return ajax(root);
	}
	
	/**
	 * 分页查询
	 * @return 
	 */
	@Action(value = "list",results = { @Result(name = "success", location = "/WEB-INF/pages/admin/product/product_photo_list.jsp") })
	public String list(){
		// 设置当前页
//		if(model.getPage() != null){
//			model.getPageResult().setCurrentPage(model.getPage());
//		}
		
		// 根据广告位ID分页查询图片
		sysUploadFileService.listSysUploadFileByPage(model.getPageResult(),model.getCondition());
		
		return SUCCESS;
	}

	
	/**
	 * 转到新增图片
	 * @return 
	 */
	@Action(value = "to_add", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/product/product_photo_add.jsp") })
	public String toAdd(){
		return SUCCESS;
	}

	/**
	 * 转到修改图片页面
	 * @return 
	 */
	@Action(value = "to_update", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/product/product_photo_add.jsp") })
	public String toUpdate(){
		SysUploadFile item = sysUploadFileService.getSysUploadFileById(model.getItem().getId());
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
			sysUploadFileService.delByIds(model.getSelIds().split(","));
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
	@SuppressWarnings("unchecked")
	@Action(value="doAdd")
	public String doAdd(){
		
		JSONObject result = new JSONObject();
		
		// 接口授权验证
		String authAccount = model.getAuthAccount();
		String authPassword = model.getAuthPassword();
		if(StringUtils.isBlank(authAccount) || StringUtils.isBlank(authPassword)){
			result.put("status", "100");
			result.put("message", "验证不通过-接口授权帐号或密码为空");
			return ajax(result);
		}else{
			if(authAccount.equals("dunhuang") && authPassword.equals("DunHuang2014!@#")){
				
			}else{
				result.put("status", "200");
				result.put("message", "验证不通过-接口授权帐号或密码错误");
				return ajax(result);
			}
		}

		String imageBase64 = model.getImageBase64();
		logger.info("=========== 已获得BASE-64图片字符串： " + imageBase64);
		
		try {
			// 以UUID作为图片的文件名
			String fileName = UUIDUtil.uuid();
			
			/** 图片存放路径为：/doc/images/${年月日-小时}/UUID.png */
			// 获取服务器当前日期
			Date nowDate = new Date();
			String nowDateStr = DateUtil.format(nowDate, "yyyyMMdd-HH");
			
			String imageBasePath = "/doc/images/";
			
			//如果文件夹不存在则创建
			try {
				File file = new File(imageBasePath + nowDateStr + "/");
				if (!file.exists()) {
					boolean isSuccess = file.mkdirs();
					logger.info("=========== 创建目录完毕，是否创建成功：  " + isSuccess + "==========");
		        }
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				result.put("message", "创建存储图片文件夹异常");
				return ajax(result);
			}
			
			String imageUrl = nowDateStr + "/" + fileName + ".png";
			
			CommonUtil.saveBase64ImageToFile(imageBase64, imageBasePath + imageUrl);
			
			logger.info("=========== 已获得完成图片存储，路径为： " + imageBasePath + imageUrl);
			
			result.put("url", "http://image.trademonster.co.nz/" + imageUrl);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			
			result.put("status", "300");
			result.put("message", "接口调用出现异常，请检查数据类型是否匹配，或者请求管理员查看系统日志");
			return ajax(result);
		}
		
		result.put("status", "000");
		result.put("message", "保存成功");
		return ajax(result);
		
	}


	@Override
	public SysUploadFileModel getModel() {
		return model;
	}}

