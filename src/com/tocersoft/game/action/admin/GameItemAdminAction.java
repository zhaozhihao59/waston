package com.tocersoft.game.action.admin;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;


import com.opensymphony.xwork2.ModelDriven;
import com.tocersoft.game.model.GameItemModel;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.tocersoft.game.entity.GameItem;
import com.tocersoft.game.service.IGameItemService;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.base.action.BaseAction;


@ParentPackage("admin")
@Namespace("/admin/game_item")
@Controller
public class GameItemAdminAction extends BaseAction implements ModelDriven<GameItemModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(GameItemAdminAction.class);

	private GameItemModel model = new GameItemModel();

	@Resource(name = "gameItemServiceImpl")
	private IGameItemService gameItemService;



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
	@Action(value = "listGameItemByPage")
	public String listGameItemByPage(){
		gameItemService.listGameItemByPage(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{
			"id","gameId","itemName","state","enrollFee","enrollFeeTeam","enrollFeeLocal"
		});
		return ajax(root);
	}

	/**
	 * 新增
	 * @return 
	 */
	@Action(value = "add", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/game/game_item_add.jsp") })
	public String toAdd(){
		return SUCCESS;
	}

	/**
	 * 修改
	 * @return 
	 */
	@Action(value = "update", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/game/game_item_add.jsp") })
	public String toUpdate(){
		GameItem item = gameItemService.getGameItemById(model.getItem().getId());
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
			gameItemService.delByIds(model.getSelIds().split(","));
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
				gameItemService.add(model.getItem());
			}else{
				gameItemService.update(model.getItem());
			}
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}
	/**
	 * 赛事项目列表
	 * @return 
	 */
	@Action(value="listGameItem")
	public String listGameItem(){
		List<GameItem> listGameItem= gameItemService.listGameItem(model.getItem().getGameId());
		JSONArray array =new JSONArray();
		for (GameItem gameItem : listGameItem) {
			JSONObject json = new JSONObject();
			json.put("id",gameItem.getId());
			json.put("name",gameItem.getItemName());
			array.add(json);
		}
		JSONObject object=new JSONObject();
		object.put("status", "success");
		object.put("listGameItem", array);
		return ajax(object.toJSONString());
		
	}
	
	public static void main(String[] args) throws JSONException {
		GameItem gameItem = new GameItem();
		gameItem.setCreateBy("a");
		gameItem.setId("sadsafd");
		gameItem.setGameId("12323");
		System.err.println(JSONUtil.serialize(gameItem));
	}

	@Override
	public GameItemModel getModel() {
		return model;
	}}

