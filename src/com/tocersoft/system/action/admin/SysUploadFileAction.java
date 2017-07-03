package com.tocersoft.system.action.admin;

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
import com.tocersoft.base.utils.UUIDUtil;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.game.entity.Game;
import com.tocersoft.game.service.IGameService;
import com.tocersoft.product.entity.Product;
import com.tocersoft.product.service.IProductService;
import com.tocersoft.system.dto.SysUploadFileCondition;
import com.tocersoft.system.entity.SysUploadFile;
import com.tocersoft.system.model.SysUploadFileModel;
import com.tocersoft.system.service.ISysUploadFileService;


@ParentPackage("admin")
@Namespace("/admin/upload")
@Controller
public class SysUploadFileAction extends BaseAction implements ModelDriven<SysUploadFileModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(SysUploadFileAction.class);

	private SysUploadFileModel model = new SysUploadFileModel();

	@Resource(name = "sysUploadFileServiceImpl")
	private ISysUploadFileService sysUploadFileService;
	
	@Resource(name = "productServiceImpl")
	private IProductService productService;
	
	@Resource(name = "gameServiceImpl")
	private IGameService gameService;


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
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{"id","name","type","url"});
		return ajax(root);
	}
	
	/**
	 * 分页查询
	 * @return 
	 */
	@Action(value = "list",results = { @Result(name = "success", location = "/WEB-INF/pages/admin/game/product_photo_list.jsp") })
	public String list(){
		// 设置当前页
//		if(model.getPage() != null){
//			model.getPageResult().setCurrentPage(model.getPage());
//		}
		
		SysUploadFileCondition condition = model.getCondition();
		condition.setObjectType("1");
		
		// 查询产品首图照片
		Game item = gameService.getGameById(condition.getObjectId());
		if(item!=null){
			model.setGamePhoto(item.getGamePhoto());
			model.setGamePhotoTo(item.getGamePhotoTo());
		}
		
		// 根据广告位ID分页查询图片
		sysUploadFileService.listSysUploadFileByPage(model.getPageResult(),condition);
		
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
	@Action(value="del",results = {@Result(name = "success", location = "/WEB-INF/pages/admin/game/product_photo_list.jsp")})
	@Validations(requiredStrings = {
				@RequiredStringValidator(fieldName = "selIds", message = "ID不能为空")
			}
	)
	@InputConfig(resultName = "ajaxError")
	public String del(){
		try {
			sysUploadFileService.delByIds(model.getSelIds().split(","));
			// 根据广告位ID分页查询图片
			sysUploadFileService.listSysUploadFileByPage(model.getPageResult(), model.getCondition());
			
			return SUCCESS;
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ERROR;
		}
	}

	/**
	 * 保存
	 * @return 
	 */
	@Action(value="doSave",results = {@Result(name = "success", location = "/WEB-INF/pages/admin/game/product_photo_list.jsp")})
	public String doSave(){
		SysUploadFile photo=model.getItem();
		try {
			// 设立图片的ID
			photo.setId(UUIDUtil.uuid());
			// 如果图片名称为空时，将文件名默认为图片名称
			if(!StringUtils.isNotBlank(photo.getName())){
				photo.setName(photo.getFileName());
			}
			// 如果排序为空时，默认排序为1
			if(null == photo.getSort()){
				photo.setSort(1);
			}
			sysUploadFileService.add(photo);
			
			SysUploadFileCondition condition = model.getCondition();
			condition.setObjectId(photo.getObjectId());
			
			// 查询产品首图照片
			Product product = productService.getProductById(condition.getObjectId());
			if(product != null){
				model.setImageUrl(product.getImageUrl());
			}
			
			// 根据广告位ID分页查询图片
			sysUploadFileService.listSysUploadFileByPage(model.getPageResult(),condition);
			return SUCCESS;
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ERROR;
		}
	}
	
	/**
	 * 保存
	 * @return 
	 */
	@Action(value="doSaveFile")
	public String doSaveFile(){
		SysUploadFile photo=model.getItem();
		try {
			// 设立图片的ID
			photo.setId(UUIDUtil.uuid());
			// 如果图片名称为空时，将文件名默认为图片名称
			if(!StringUtils.isNotBlank(photo.getName())){
				photo.setName(photo.getFileName());
			}
			// 如果排序为空时，默认排序为1
			if(null == photo.getSort()){
				photo.setSort(1);
			}
			sysUploadFileService.add(photo);
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
	@Action(value="doUpdate")
	public String doUpdate(){
		SysUploadFile photo=model.getItem();
		try {
			SysUploadFile item = sysUploadFileService.getSysUploadFileById(photo.getId());
			// 如果图片名称为空时，将文件名默认为图片名称
			if(!StringUtils.isNotBlank(photo.getName())){
				item.setName(photo.getFileName());
			}else {
				item.setName(photo.getName());
			}
			// 如果排序为空时，默认排序为1
			if(null == photo.getSort()){
				item.setSort(1);
			}
			
			item.setDesc(photo.getDesc());
			item.setUrl(photo.getUrl());
			
			sysUploadFileService.update(item);;
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}


	@Override
	public SysUploadFileModel getModel() {
		return model;
	}}

