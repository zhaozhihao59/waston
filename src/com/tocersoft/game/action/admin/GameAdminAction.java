package com.tocersoft.game.action.admin;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.tocersoft.game.model.GameModel;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.tocersoft.game.entity.Game;
import com.tocersoft.game.service.IGameService;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.base.action.BaseAction;


@ParentPackage("admin")
@Namespace("/admin/game")
@Controller
public class GameAdminAction extends BaseAction implements ModelDriven<GameModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(GameAdminAction.class);

	private GameModel model = new GameModel();

	@Resource(name = "gameServiceImpl")
	private IGameService gameService;

	/**
	 * 首页
	 * @return 
	 */
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/game/game_list.jsp") })
	public String index(){
		return SUCCESS;
	}

	/**
	 * 分页查询
	 * @return 
	 */
	@Action(value = "listGameByPage")
	public String listGameByPage(){
		gameService.listGameByPage(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{
			"id","gameNo","gameName","state","enrollBeginDate","enrollEndDate","gameDateStr","gameAddress"
		});
		return ajax(root);
	}

	/**
	 * 新增
	 * @return 
	 */
	@Action(value = "add", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/game/game_add.jsp") })
	public String toAdd(){
		return SUCCESS;
	}

	/**
	 * 修改
	 * @return 
	 */
	@Action(value = "update", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/game/game_detail.jsp") })
	public String toUpdate(){
		Game item = gameService.getGameById(model.getItem().getId());
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
			gameService.delByIds(model.getSelIds().split(","));
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
				gameService.add(model.getItem());
			}else{
				gameService.update(model.getItem());
			}
			return ajax(Status.success,"保存成功!");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}
	/**
	 * 保存信息图片
	 * @return 
	 */
	@Action(value="doSaveGamePhoto")
	public String doSaveGamePhoto(){
		try {
			String gameId = model.getItem().getId();
			String gamePhoto = model.getItem().getGamePhoto();
			String gamePhotoTo = model.getItem().getGamePhotoTo();
			if(StringUtils.isNotBlank(gamePhoto)){
				gameService.updatePhoto(gamePhoto, gameId);
			}
			if(StringUtils.isNotBlank(gamePhotoTo)){
				gameService.updatePhotoTo(gamePhotoTo, gameId);
			}
			return ajax(Status.success,"保存成功!");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}

	@Override
	public GameModel getModel() {
		return model;
	}}

