package com.tocersoft.cms.action.admin;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.utils.UUIDUtil;
import com.tocersoft.cms.entity.CmsAdvPhoto;
import com.tocersoft.cms.model.CmsAdvPhotoModel;
import com.tocersoft.cms.service.ICmsAdvPhotoService;

/**
 * 
 * 广告管理
 * @author 方泉
 *
 */
@ParentPackage("admin")
@Namespace("/admin/cms/advPhoto")
@Controller
public class AdvPhotoAdminAction extends BaseAction implements ModelDriven<CmsAdvPhotoModel>{

	private static final long serialVersionUID = 5107024224886599818L;
	
	private CmsAdvPhotoModel model = new CmsAdvPhotoModel();
	
	@Resource
	private ICmsAdvPhotoService cmsAdvPhotoService;
	
	@Action(value = "list", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/cms/adv_photo_list.jsp") })
	public String list() {
		
		// 设置当前页
		if(model.getPage() != null){
			model.getPageResult().setCurrentPage(model.getPage());
		}
		
		// 根据广告位ID分页查询图片
		cmsAdvPhotoService.listAdvPhotoByPage(model.getPageResult(), model.getAdvId());
		
		return SUCCESS;
	}
	
	/**
	 * 跳转到新增广告图
	 * @return
	 */
	@Action(value = "to_add", results = { @Result(name = "index", location = "/WEB-INF/pages/admin/cms/adv_photo_add.jsp") })
	public String toAdd() {
		return INDEX;
	}
	
	/**
	 * 新增广告图
	 * @return
	 */
	@Action(value = "add")
	public String add() {
		CmsAdvPhoto photo = model.getItem();
		
		// 设立图片的ID
		photo.setId(UUIDUtil.uuid());
		
		// 如果图片名称为空时，将文件名默认为图片名称
		if(!StringUtils.isNotBlank(photo.getName())){
			photo.setName(model.getItem().getFileName());
		}
		
		// 如果排序为空时，默认排序为1
		if(null == photo.getSort()){
			photo.setSort(1);
		}
		
		cmsAdvPhotoService.addAdvPhoto(photo);
		
		return ajax(SUCCESS);
	}
	
	/**
	 * 跳转到修改广告图页面
	 * @return
	 */
	@Action(value = "to_update", results = { @Result(name = "index", location = "/WEB-INF/pages/admin/cms/adv_photo_add.jsp") })
	public String toUpdate() {
		String photoId = model.getItem().getId();
		CmsAdvPhoto photo = cmsAdvPhotoService.getAdvPhotoById(photoId);
		model.setItem(photo);
		return INDEX;
	}
	
	/**
	 * 修改广告图
	 * @return
	 */
	@Action(value = "update")
	public String update() {
		CmsAdvPhoto photo = model.getItem();
		
		// 如果图片名称为空时，将文件名默认为图片名称
		if(!StringUtils.isNotBlank(photo.getName())){
			photo.setName(model.getItem().getFileName());
		}
		
		// 如果排序为空时，默认排序为1
		if(null == photo.getSort()){
			photo.setSort(1);
		}
		
		cmsAdvPhotoService.updateAdvPhoto(photo);
		
		return ajax(SUCCESS);
	}
	
	/**
	 * 删除广告图
	 * @return
	 */
	@Action(value = "remove")
	public String remove() {
		String id = model.getItem().getId();
		cmsAdvPhotoService.removeAdvPhotoById(id);
		
		return ajax(SUCCESS);
	}
	
	@Override
	public CmsAdvPhotoModel getModel() {
		return model;
	}
}
