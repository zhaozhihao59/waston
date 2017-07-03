package com.tocersoft.cms.action.admin;

import java.util.ArrayList;
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
import com.tocersoft.base.action.BaseAction.Status;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.cms.entity.CmsChannel;
import com.tocersoft.cms.model.CmsChannelModel;
import com.tocersoft.cms.service.ICmsArticleService;
import com.tocersoft.cms.service.ICmsChannelService;

/**
 * 
 * 栏目管理
 * @author 欧阳明航
 *
 */
@ParentPackage("admin")
@Namespace("/admin/cms/channel")
@Controller
public class ArticleChannelAdminAction extends BaseAction implements ModelDriven<CmsChannelModel>{

	private static final long serialVersionUID = -1376510225646245175L;
	
	private CmsChannelModel model = new CmsChannelModel();
	
	private Log logger = LogFactory.getLog(CmsChannel.class);
	
	@Resource
	private ICmsChannelService channelService;
	
	@Resource
	private ICmsArticleService cmsArticleService;
	
	@Action(value = "index", results = { @Result(name = "index", location = "/WEB-INF/pages/admin/cms/channel_list.jsp") })
	public String home() {
		return INDEX;
	}
	
	/**
	 * 分页查询栏目
	 */
	@Action(value = "searchCmsChannelList")
	public String getMaxLevelChannelAll(){
		channelService.getChannelListByPage(model.getPageResult());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String []{"id","name","remark","state","isRight","sort"});
		return ajax(root);

//			// 当前类别ID
//			String parent = model.getNodeid();
//			List<CmsChannel> cmsChannelList = new ArrayList<CmsChannel>();
//			if (StringUtils.isBlank(parent)) {
//				// 根据类别和上级类别编号查询
//				parent="0";
//			} 
//			cmsChannelList = channelService.listChannelByPid(parent);
//			// 根据类别和上级类别编号查询
//			JSONArray rows = new JSONArray();
//			JSONObject jsonObj = new JSONObject();
//			jsonObj.put("page", 1);
//			jsonObj.put("total", 1);
//			jsonObj.put("records", cmsChannelList.size());
//			// int i = 0 ;
//			for (CmsChannel cmsChannel : cmsChannelList) {
//				JSONObject list = new JSONObject();
//				list.put("id", cmsChannel.getId());
//				list.put("name", cmsChannel.getName());
//				list.put("remark", cmsChannel.getRemark());
//				list.put("state", cmsChannel.getState());
//				// 是否显示添加子类按钮
//				int curLevel = cmsChannel.getLevel();
//				list.put("level", curLevel);
//				list.put("showAdd", true);
//				if(curLevel>2){
//					list.put("showAdd", false);
//				}
//				list.put("codeNum", cmsChannel.getCodeNum());
//				list.put("sort", cmsChannel.getSort());
//				list.put("parent", cmsChannel.getParent());
//				rows.add(list);
//			}
//			jsonObj.put("rows", rows);
//			this.ajax(jsonObj.toString(), "text/json");
	}
	
	/**
	 * 转到添加大类页面
	 * 
	 * @return
	 */
	@Action(value="toAddCmsChannel",results={@Result(name="toNewWin",location="/WEB-INF/pages/admin/cms/channel_add.jsp")})
	public String toAddCategory() {
		return "toNewWin";
	}
	
	/**
	 * 新增商品类别
	 */
	@Action(value="doAddCmsChannel")
	public void doAddCmsChannel() throws Exception {
		// 上级类别ID
		String parentId = model.getParentId();
		// 类别名称
		String name = model.getName();
		if (StringUtils.isBlank(name)) {
			ajax(Status.error, "类别名称不能为空。");
			return;
		}
		// 排序
		Integer sort = model.getSortNum() == null ? 0 : model.getSortNum();
		channelService.doAddCmsChannel(parentId, name, sort);
		ajax(Status.success, "添加成功。");
	}
	
	/**
	 * 跳转到修改类别页面
	 */
	@Action(value="toUpdateCmsChannel",results={@Result(name="toNewWin",location="/WEB-INF/pages/admin/cms/channel_add.jsp")})
	public String toUpdateCategory() {
		// 获得类别的ID
		String nodeId = model.getNodeid();
		// 获得类别信息
		CmsChannel item = channelService.getById(nodeId);
		model.setItem(item);
		return "toNewWin";
	}
	
	/**
	 * 修改商品类型
	 */
	@Action(value="doUpdateCmsChannel")
	public void doUpdateGoodsCategory() throws Exception {
		String nodeId = model.getNodeid();
		if (StringUtils.isBlank(nodeId)) {
			ajax(Status.error, "修改失败");
			return;
		}
		model.setNodeid(nodeId);
		// 类别名称
		String name = model.getName();
		if (StringUtils.isBlank(name)) {
			ajax(Status.error, "类别名称不能为空。");
			return;
		}
		// 排序
		Integer sort = model.getSortNum() == null ? 0 : model.getSortNum();
		channelService.doUpdateCmsChannel(nodeId, name, sort);
		ajax(Status.success, "修改成功。");
	}
	
	/**
	 * 删除商品类别
	 */
	@Action(value="delCmsChannelById")
	public void delCmsChannelById() throws Exception {
		String id = model.getNodeid();
		CmsChannel channel = channelService.getById(id);
		
		// 删除标记位
		if(null != channel && channel.getIsDelete() != null && channel.getIsDelete().intValue() == 1){
			ajax(Status.error,"该类别不可删除");
			return;
		}
		
		// 系统与用户
		if(null != channel && channel.getState() != null && channel.getState().intValue() == 1){
			ajax(Status.error,"该类别不可删除");
			return;
		}
		
		channelService.delCmsChannel(channel);
		ajax(Status.success);
	}
	
	
	/**
	 * 保存栏目
	 */
	@Action(value = "doSave")
	@Validations(
			requiredStrings = {
				@RequiredStringValidator(fieldName = "item.name" , message = "栏目标题不能为空")
			}
	)
	@InputConfig(resultName = "ajaxError")
	public String doSave(){
		try {
			channelService.doSave(model.getItem());
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "操作时发生异常："+ e.getMessage();
			logger.error(msg);
			e.printStackTrace();
			return ajax(Status.error,msg);
		}
	}
	
	/**
	 * 显示栏目树，默认展开第一级
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "list_channel")
	public void listChannelFirst(){
		JSONArray root = new JSONArray();
		List<CmsChannel> list=new ArrayList<CmsChannel>();
		// 初次加载
		if(StringUtils.isBlank(model.getParentId())){
			// 构造“全部”项
			JSONObject topJson = new JSONObject();
			topJson.put("id", "0");
			topJson.put("name", "全部");
			topJson.put("sort", 0);
			topJson.put("parentId", "-1");
			topJson.put("isParent", true);
			topJson.put("isDelete", 1);
			topJson.put("state", 1);
			topJson.put("open", true);
			
			list = channelService.listChannelByPid("0");
			JSONArray children = new JSONArray();
			for (int i = 0; i < list.size(); i++){
				JSONObject json = makeJsonObject(list.get(i));
				children.add(json);
			}
			topJson.put("children", children);
			root.add(topJson);
		
		// 点击后加载
		}else{
			list = channelService.listChannelByPid(model.getParentId());
			for (int i = 0; i < list.size(); i++){
				JSONObject json = makeJsonObject(list.get(i));
				root.add(json);
			}
		}
		ajax(root);
	}
	
	/**
	 * 删除产品栏目
	 */
	@Action(value = "delChannel")
	@Validations(
			requiredStrings = {
				@RequiredStringValidator(fieldName = "model.channelId" , message = "栏目ID不能为空"),
			}
	)
	@InputConfig(resultName = "ajaxError")
	public String delChannel(){
		try {
			int count =cmsArticleService.getArticleCount(model.getChannelId());
			if(count > 0){
				return ajax(Status.error,"该栏目下有文章，无法删除！");
			}else{
				channelService.delChannel(model.getChannelId());
				return ajax(Status.success,"删除成功");
			}
		} catch (Exception e) {
			String msg = "操作时发生异常："+ e.getMessage();
			logger.error(msg);
			e.printStackTrace();
			return ajax(Status.error,msg);
		}
	}
	
	@Action(value = "showRight")
	@Validations(
			requiredStrings = {
				@RequiredStringValidator(fieldName = "model.channelId" , message = "栏目ID不能为空"),
			}
	)
	@InputConfig(resultName = "ajaxError")
	public String showRight(){
		try {
			channelService.showRight(model.getChannelId());
			return ajax(Status.success,"操作成功");
		} catch (Exception e) {
			String msg = "操作时发生异常："+ e.getMessage();
			logger.error(msg);
			e.printStackTrace();
			return ajax(Status.error,msg);
		}
	}
	
	@Action(value = "unShowRight")
	@Validations(
			requiredStrings = {
				@RequiredStringValidator(fieldName = "model.channelId" , message = "栏目ID不能为空"),
			}
	)
	@InputConfig(resultName = "ajaxError")
	public String unShowRight(){
		try {
			channelService.unShowRight(model.getChannelId());
			return ajax(Status.success,"操作成功");
		} catch (Exception e) {
			String msg = "操作时发生异常："+ e.getMessage();
			logger.error(msg);
			e.printStackTrace();
			return ajax(Status.error,msg);
		}
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject makeJsonObject(CmsChannel channel){
		JSONObject data = new JSONObject();
		data.put("id",channel.getId());
		data.put("name",channel.getName());
		if(channel.getIsParent() != null && channel.getIsParent().intValue() == 1){
			data.put("isParent", true);
		}else{
			data.put("isParent", false);
		}
		data.put("parentId",channel.getParent());
		data.put("isDelete", channel.getIsDelete());
		data.put("state", channel.getState());
		data.put("open", true);
		return data;
	}
	
	@Override
	public CmsChannelModel getModel() {
		return model;
	}
	
}
