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
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.tocersoft.game.model.GameEnrollModel;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.tocersoft.game.entity.GameEnroll;
import com.tocersoft.game.entity.GameItem;
import com.tocersoft.game.service.IGameEnrollService;
import com.tocersoft.game.service.IGameItemService;
import com.tocersoft.member.entity.MemTeam;
import com.tocersoft.member.service.IMemTeamService;
import com.tocersoft.member.service.IMemberService;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.base.action.BaseAction;


@ParentPackage("admin")
@Namespace("/admin/game_enroll")
@Controller
public class GameEnrollAdminAction extends BaseAction implements ModelDriven<GameEnrollModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(GameEnrollAdminAction.class);

	private GameEnrollModel model = new GameEnrollModel();

	@Resource(name = "gameEnrollServiceImpl")
	private IGameEnrollService gameEnrollService;
	
	@Resource(name = "memberServiceImpl")
	private IMemberService memberService;
	
	@Resource(name = "memTeamServiceImpl")
	private IMemTeamService memTeamService;
	
	@Resource(name = "gameItemServiceImpl")
	private IGameItemService gameItemService;
	


	/**
	 * 首页
	 * @return 
	 */
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/game/game_enroll_list.jsp") })
	public String index(){
		List<GameItem> listGameItem=gameItemService.listGameItem("");
		model.setListGameItem(listGameItem);
		return SUCCESS;
	}
	
	/**
	 * 分页查询
	 * @return 
	 */
	@Action(value = "listGameEnrollByPage")
	public String listGameEnrollByPage(){
		gameEnrollService.listGameEnrollByPage(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{
			"id","enrollNo","memberId","memberName","teamId","teamName","gameItemId","gameItemName","memberPhone","enrollFee",
			"enrollFeePaid","state","score","rank"
		});
		return ajax(root);
	}

	/**
	 * 新增
	 * @return 
	 */
	@Action(value = "add", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/game/game_enroll_add.jsp") })
	public String toAdd(){
		List<MemTeam> listMemTeam= memTeamService.listMemTeamByKey("");
		model.setListMemTeam(listMemTeam);
		return SUCCESS;
	}

	/**
	 * 修改
	 * @return 
	 */
	@Action(value = "update", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/game/game_enroll_add.jsp") })
	public String toUpdate(){
		GameEnroll item = gameEnrollService.getGameEnrollById(model.getItem().getId());
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
			gameEnrollService.delByIds(model.getSelIds().split(","));
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
				gameEnrollService.add(model.getItem());
			}else{
				gameEnrollService.update(model.getItem());
			}
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}


	@Override
	public GameEnrollModel getModel() {
		return model;
	}}

