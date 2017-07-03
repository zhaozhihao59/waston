package com.tocersoft.game.action.front;

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

import com.bestpay.model.CommModel;
import com.bestpay.util.BestpayUtil;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.page.PageResult;
import com.tocersoft.base.utils.Constant;
import com.tocersoft.base.utils.DoubleUtil;
import com.tocersoft.base.utils.UUIDUtil;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.game.dto.GameCondition;
import com.tocersoft.game.entity.Game;
import com.tocersoft.game.entity.GameEnroll;
import com.tocersoft.game.entity.GameEnrollTeam;
import com.tocersoft.game.entity.GameItem;
import com.tocersoft.game.model.GameModel;
import com.tocersoft.game.service.IGameEnrollService;
import com.tocersoft.game.service.IGameItemService;
import com.tocersoft.game.service.IGameService;
import com.tocersoft.member.entity.Member;
import com.tocersoft.member.service.IMemTeamMemberService;
import com.tocersoft.member.service.IMemTeamService;
import com.tocersoft.member.service.IMemberService;
import com.tocersoft.system.dto.SysUploadFileCondition;
import com.tocersoft.system.entity.SysDictItem;
import com.tocersoft.system.entity.SysUploadFile;
import com.tocersoft.system.service.ISysDictItemService;
import com.tocersoft.system.service.ISysUploadFileService;


@ParentPackage("front")
@Namespace("/game")
@Controller
public class GameFrontAction extends BaseAction implements ModelDriven<GameModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(GameFrontAction.class);

	private GameModel model = new GameModel();

	@Resource(name = "gameServiceImpl")
	private IGameService gameService;
	@Resource(name = "gameEnrollServiceImpl")
	private IGameEnrollService gameEnrollService;
	@Resource(name = "memberServiceImpl")
	private IMemberService memberService;
	@Resource(name = "gameItemServiceImpl")
	private IGameItemService gameItemService;
	@Resource(name = "memTeamMemberServiceImpl")
	private IMemTeamMemberService memTeamMemberService;
	@Resource(name = "memTeamServiceImpl")
	private IMemTeamService memTeamService;
	@Resource(name = "sysUploadFileServiceImpl")
	private ISysUploadFileService uploadService;
	@Resource(name = "sysDictItemServiceImpl")
	private ISysDictItemService dictItemService;
	@Resource(name = "sysUploadFileServiceImpl")
	private ISysUploadFileService fileService;
	//支付使用的model
	public CommModel commModel = new CommModel();
	
	/**
	 * 首页
	 * @return 
	 */
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/pages/front/game/game_list.jsp") })
	public String index(){
		//正在报名的赛事
		GameCondition condition=new GameCondition();
		condition.setState(1);
		List<Game> gameList=gameService.listGameByCondition(condition);
		model.setGameList(gameList);
		
		model.getCondition().setState(4);
		gameService.listGameByPage(model.getPageResult(),model.getCondition());
		return SUCCESS;
	}
	
	/**
	 * 赛事详情
	 * @return 
	 */
	@Action(value = "detail", results = { @Result(name = "success", location = "/WEB-INF/pages/front/game/game_detail.jsp") })
	public String detail(){
		try{
			String id = model.getId();
			if(StringUtils.isNotBlank(id)){
				Game game = gameService.getGameById(id);
				model.setItem(game);
				// 根据赛事ID查询相关文档
				List<SysUploadFile> uploadList = uploadService.listSysUploadFileByObjectIdAndTypeId(id, "2");
				model.setUploadList(uploadList);
			}
			return SUCCESS;
		}catch(Exception e){
			return ERROR;
		}
	}
	
	/**
	 * 赛事照片
	 * @return 
	 */
	@Action(value = "photo_list", results = { @Result(name = "success", location = "/WEB-INF/pages/front/game/game_photo_list.jsp") })
	public String photoList(){
		
		PageResult<SysUploadFile> photoPageResult = model.getPhotoPageResult();
		photoPageResult.setPageSize(20);
		SysUploadFileCondition condition = new SysUploadFileCondition();
		condition.setObjectType("1");
		fileService.listSysUploadFileByPage(photoPageResult, condition);
		
		return SUCCESS;
	}
	
	/**
	 * 成绩查询
	 * @return 
	 */
	@Action(value = "result_search", results = { @Result(name = "success", location = "/WEB-INF/pages/front/game/game_result_search.jsp") })
	public String resultSearch(){
		return SUCCESS;
	}
	
	/**
	 * 赛事报名
	 * @return 
	 */
	@Action(value = "enroll", results = { @Result(name = "success", location = "/WEB-INF/pages/front/game/game_enroll.jsp") })
	public String enroll(){
		// 加载赛事与比赛项目
		List<Game> gameList = gameService.listGameByCondition(model.getCondition());
		model.setGameList(gameList);
		
		// 加载国籍 - 数据字典
		List<SysDictItem> nationList = dictItemService.listSysDictItemByDictId("3390c9e8c58a11e48d5a00fffd437687");
		model.setNationList(nationList);
		
		// 加载紧急联系人关系 - 数据字典
		List<SysDictItem> relationList = dictItemService.listSysDictItemByDictId("3d85de77c58a11e48d5a00fffd437687");
		model.setRelationList(relationList);
		
		// 加载血型 - 数据字典
		List<SysDictItem> bloodTypeList = dictItemService.listSysDictItemByDictId("5d562d06c58a11e48d5a00fffd437687");
		model.setBloodTypeList(bloodTypeList);
		
		//加载证件类型 - 数据字典
		List<SysDictItem> cardTypeList = dictItemService.listSysDictItemByDictId("1a5da4a319cf1033a08fb067f28adba9");
		model.setCardTypeList(cardTypeList);
		
		return SUCCESS;
	}
	
	/**
	 * 赛事报名确认
	 * @return 
	 * shao
	 */
	@Action(value = "confirm_enroll_debug", results = { @Result(name = "success", location = "/WEB-INF/pages/front/game/confirm_enroll.jsp") })
	public String confirm_enroll_debug(){
		String amount = "123";
		String customerId = "123";
		//amount填写总价，customerId填写电话号码   （金额以分为单位）
		commModel = BestpayUtil.createCommModel(getRequest(), amount, customerId);
		model.setCommModel(commModel);
		try{
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	
	/**
	 * 赛事报名确认
	 * @return 
	 * shao
	 */
	@Action(value = "confirm_enroll", results = { @Result(name = "success", location = "/WEB-INF/pages/front/game/confirm_enroll.jsp") })
	public String confirm_enroll(){
		// 加载国籍 - 数据字典
		List<SysDictItem> nationList = dictItemService.listSysDictItemByDictId("3390c9e8c58a11e48d5a00fffd437687");
		model.setNationList(nationList);
		
		// 加载紧急联系人关系 - 数据字典
		List<SysDictItem> relationList = dictItemService.listSysDictItemByDictId("3d85de77c58a11e48d5a00fffd437687");
		model.setRelationList(relationList);
		
		// 加载血型 - 数据字典
		List<SysDictItem> bloodTypeList = dictItemService.listSysDictItemByDictId("5d562d06c58a11e48d5a00fffd437687");
		model.setBloodTypeList(bloodTypeList);
		
		//加载证件类型 - 数据字典
		List<SysDictItem> cardTypeList = dictItemService.listSysDictItemByDictId("1a5da4a319cf1033a08fb067f28adba9");
		model.setCardTypeList(cardTypeList);
		/**
		String amount = null;
		String customerId = null;
		//amount填写总价，customerId填写电话号码    （金额以分为单位）
		commModel = BestpayUtil.createCommModel(getRequest(), amount, customerId);
		*/
		try{
			GameEnroll gameEnroll = model.getGameEnroll();
			// 如果是团队，则按照团队报名形式保存数据库
			if(null != gameEnroll.getEnrollType() && gameEnroll.getEnrollType().intValue() == 1){
				GameEnrollTeam enrollTeam = model.getEnrollTeam();
				enrollTeam.setGameItemId(gameEnroll.getGameItemId());
				gameEnrollService.addEnrollTeam(enrollTeam);
				this.getSession().setAttribute("gameEnrollItem",enrollTeam);
				
				String amount = String.valueOf((int) (enrollTeam.getEnrollFee()*100));
				String customerId = enrollTeam.getTeamLeaderPhone();
				//amount填写总价，customerId填写电话号码    （金额以分为单位）
				commModel = BestpayUtil.createCommModel(getRequest(), amount, customerId);
				model.setCommModel(commModel);
			} else if (gameEnroll.getEnrollType() == 0) {
				// 查询获得项目团队单价
				GameItem gameItem = gameItemService.getGameItemById(gameEnroll.getGameItemId());
				// 先获取普通 价格
				Double fee = gameItem.getEnrollFee();
				//是本地人
				if(gameEnroll.getIsLocal().intValue() ==1 ){
					// 如果不填写当地人单价，则取普通单价
					if(null != gameItem.getEnrollFeeLocal()){
						fee = gameItem.getEnrollFeeLocal();
					}else{
						fee = gameItem.getEnrollFee();
					}
				}
				
				//设置费用
				gameEnroll.setEnrollFee(fee);
				gameEnrollService.add(gameEnroll);
				String amount = String.valueOf((int) (gameEnroll.getEnrollFee()*100));
				String customerId = gameEnroll.getMemberPhone();
				//amount填写总价，customerId填写电话号码    （金额以分为单位）
				commModel = BestpayUtil.createCommModel(getRequest(), amount, customerId);
				model.setCommModel(commModel);
				this.getSession().setAttribute("gameEnrollItem",gameEnroll);
			}
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	
	/**
	 * 赛事报名成功
	 * @return 
	 * shao
	 */
	@Action(value = "success_enroll", results = { @Result(name = "success", location = "/WEB-INF/pages/front/game/success_enroll.jsp") })
	public String success_enroll(){
		GameEnroll gameEnroll=(GameEnroll)this.getSession().getAttribute("gameEnrollItem");
		// 判断会员是否存在
		Member meUser=(Member)getSession().getAttribute(Constant.FRONT_USER);
		// 报名类型为个人
		if(gameEnroll.getEnrollType()==0){
			if(meUser==null){
				// 新增个人报名会员
				Member member=new Member();
				member.setId(UUIDUtil.uuid());
				member.setName(gameEnroll.getMemberName());
				member.setIdCardNo(gameEnroll.getIdCardNo());
				member.setMobile(gameEnroll.getMemberPhone());
				member.setSex(gameEnroll.getSex());
				member.setDeleteFlag(0);
				member.setAccount(gameEnroll.getMemberPhone());
				member.setPassword("111111");
				member.setDeleteFlag(1);
				memberService.addMemberByEnroll(member);
				//新增个人报名
				gameEnroll.setMemberId(member.getId());
				gameEnroll.setDeleteFlag(0);
			
			}
				gameEnroll.setTeamId("");
				gameEnrollService.add(gameEnroll);
		
		// 报名类型为车队
		}else{
//			//车队成员集合
//			List<Member> memberList=gameEnroll.getMemberList();
//			//会员未登录
//			if(meUser==null){
//				//新增车队
//				MemTeam team=new MemTeam();
//				team.setId(UUIDUtil.uuid());
//				team.setName(gameEnroll.getTeamName());
//				team.setType(0);
//				team.setDeleteFlag(0);
//				memTeamService.addMemTeamByEnroll(team);
//				
//				//车队成员生成会员 
//				for (Member item : memberList) {
//					item.setId(UUIDUtil.uuid());
//					item.setDeleteFlag(0);
//					item.setAccount(item.getMobile());
//					item.setPassword("111111");
//					memberService.addMemberByEnroll(item);
//				
//					//车队成员加入车队
//					MemTeamMember teamMember=new MemTeamMember();
//					teamMember.setTeamId(team.getId());
//					teamMember.setMemberId(item.getId());
//					if(item.getTeamCaptain()!=null &&item.getTeamCaptain()==1){
//						teamMember.setType(1);
//					}else{
//						teamMember.setType(0);
//					}
//					memTeamMemberService.add(teamMember);
//					
//					//新增车队成员报名
//					GameEnroll enrollItem=new GameEnroll();
//					enrollItem.setMemberId(item.getId());
//					enrollItem.setMemberName(item.getName());
//					enrollItem.setMemberPhone(item.getMobile());
//					enrollItem.setTeamId(team.getId());
//					enrollItem.setGameItemId(gameEnroll.getGameItemId());
//					enrollItem.setEnrollType(gameEnroll.getEnrollType());
//					enrollItem.setEnrollFee(gameEnroll.getEnrollFee());  
//					enrollItem.setSetMeal(gameEnroll.getSetMeal());
//					enrollItem.setVehicle(gameEnroll.getVehicle());
//					enrollItem.setElseVehicleName(gameEnroll.getElseVehicleName());
//					enrollItem.setLodge(gameEnroll.getLodge());
//					enrollItem.setInsure(gameEnroll.getInsure());
//					enrollItem.setDeleteFlag(0);
//					gameEnrollService.add(enrollItem);
//				}
//			}else{
//				for (Member item : memberList) {
//					//新增车队成员报名
//					GameEnroll enrollItem=new GameEnroll();
//					enrollItem.setMemberId(item.getId());
//					enrollItem.setMemberName(item.getName());
//					enrollItem.setMemberPhone(item.getMobile());
//					enrollItem.setTeamId(gameEnroll.getTeamId());
//					enrollItem.setGameItemId(gameEnroll.getGameItemId());
//					enrollItem.setEnrollType(gameEnroll.getEnrollType());
//					enrollItem.setEnrollFee(gameEnroll.getEnrollFee());  
//					enrollItem.setSetMeal(gameEnroll.getSetMeal());
//					enrollItem.setVehicle(gameEnroll.getVehicle());
//					enrollItem.setElseVehicleName(gameEnroll.getElseVehicleName());
//					enrollItem.setLodge(gameEnroll.getLodge());
//					enrollItem.setInsure(gameEnroll.getInsure());
//					enrollItem.setDeleteFlag(0);
//					gameEnrollService.add(enrollItem);
//				}
//			}
		}
		this.getSession().removeAttribute("gameEnrollItem");
		/*Member member=(Member)getSession().getAttribute(Constant.FRONT_USER);
		member.setIdCardNo(gameEnroll.getIdCardNo());
		member.setMobile(gameEnroll.getMemberPhone());
		member.setSex(gameEnroll.getSex());
		memberService.update(member);
		gameEnrollService.add(gameEnroll);*/
		return SUCCESS;
	}
	/**
	 * 过往赛事分页查询
	 * @return 
	 */
	@Action(value = "listOverGameByPage", results = { @Result(name = "success", location = "/WEB-INF/pages/front/game/game_list_inc.jsp") })
	public String listOverGameByPage(){
		model.getCondition().setState(4);
		gameService.listGameByPage(model.getPageResult(),model.getCondition());
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
	 * 赛事项目列表
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	@Action(value="listGameItems")
	public String listGameItem(){
		List<GameItem> listGameItem= gameItemService.listGameItem(model.getItem().getId());
		JSONArray array =new JSONArray();
		for (GameItem gameItem : listGameItem) {
			JSONObject json = new JSONObject();
			json.put("id",gameItem.getId());
			json.put("name",gameItem.getItemName());
			json.put("fee",gameItem.getEnrollFee());
			json.put("feeTeam",gameItem.getEnrollFeeTeam());
			json.put("feeLocal",gameItem.getEnrollFeeLocal());
			array.add(json);
		}
		JSONObject object=new JSONObject();
		object.put("status", "success");
		object.put("listGameItem", array);
		return ajax(object.toJSONString());
		
	}
	
	@Action(value = "pay_result", results = {
			@Result(name = "success", location = "/WEB-INF/pages/front/game/pay_success.jsp"),
			@Result(name = "fail", location = "/WEB-INF/pages/front/game/pay_fail.jsp")
	})
	public String pay_result(){
		try{
			String retncode = getRequest().getParameter("RETNCODE");
			String orderamount = getRequest().getParameter("ORDERAMOUNT");
			
			if(StringUtils.isNotBlank(retncode) && (retncode.equals("0") || retncode.equals("00") || retncode.equals("000") || retncode.equals("0000"))){
				
				logger.info("======= 【天翼支付返回成功】 返回代码： " + retncode + " =======");
				
				// 天翼支付接口的金额已“毛”为单位，因而需要除上100
				Double payMoney = DoubleUtil.div(Double.parseDouble(orderamount), 100, 2);
				model.setPayAmount(payMoney);
				
				return SUCCESS;
			}else{
				logger.info("======= 【天翼支付返回失败】 返回代码： " + retncode + " =======");
				model.setRetncode(retncode);
				return "fail";
			}
			
		}catch(Exception e){
			logger.info("======= 【天翼支付返回异常】  =======");
			e.printStackTrace();
			return "fail";
		}
	}
	
	public CommModel getCommModel() {
		return commModel;
	}

	public void setCommModel(CommModel commModel) {
		this.commModel = commModel;
	}

	@Override
	public GameModel getModel() {
		return model;
	}}

