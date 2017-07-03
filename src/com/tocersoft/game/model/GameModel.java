package com.tocersoft.game.model;

import java.util.ArrayList;
import java.util.List;

import com.bestpay.model.CommModel;
import com.tocersoft.base.model.BaseModel;
import com.tocersoft.base.page.PageResult;
import com.tocersoft.game.dto.GameCondition;
import com.tocersoft.game.entity.Game;
import com.tocersoft.game.entity.GameEnroll;
import com.tocersoft.game.entity.GameEnrollTeam;
import com.tocersoft.system.entity.SysDictItem;
import com.tocersoft.system.entity.SysUploadFile;

public class GameModel extends BaseModel<Game>{

	private Game item = new Game();

	private GameCondition condition = new GameCondition();
	private List<Game> gameList = new ArrayList<Game>();
	private GameEnroll gameEnroll = new GameEnroll();
	
	/** 团队报名表 */
	private GameEnrollTeam enrollTeam = new GameEnrollTeam();

	private List<GameEnroll> gameEnrollList = new ArrayList<GameEnroll>();
	
	private CommModel commModel = new CommModel();
	
	/** 赛事ID */
	private String id;
	/** 赛事文件 */
	private List<SysUploadFile> uploadList = new ArrayList<SysUploadFile>();
	/** 国籍 - 数据字典 */
	private List<SysDictItem> nationList = new ArrayList<SysDictItem>();
	/** 紧急联系人关系 - 数据字典 */
	private List<SysDictItem> relationList = new ArrayList<SysDictItem>();
	/** 血型 - 数据字典 */
	private List<SysDictItem> bloodTypeList = new ArrayList<SysDictItem>();
	/**证件类型 - 数据字典*/
	private List<SysDictItem> cardTypeList = new ArrayList<SysDictItem>();
	
	/** 赛事照片分页器 */
	private PageResult<SysUploadFile> photoPageResult = new PageResult<SysUploadFile>();
	
	/** 支付接口回调函数 - 已支付的金额 */
	private Double payAmount;
	/** 支付接口回调函数 - 返回代码 */
	private String retncode;
	/** 页面跳转标记 */
	private Integer i;
	public Integer getI() {
		return i;
	}

	public void setI(Integer i) {
		this.i = i;
	}
	public String getRetncode() {
		return retncode;
	}

	public void setRetncode(String retncode) {
		this.retncode = retncode;
	}

	public Double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}

	public List<SysDictItem> getNationList() {
		return nationList;
	}

	public void setNationList(List<SysDictItem> nationList) {
		this.nationList = nationList;
	}

	public List<SysDictItem> getRelationList() {
		return relationList;
	}

	public void setRelationList(List<SysDictItem> relationList) {
		this.relationList = relationList;
	}

	public List<SysDictItem> getBloodTypeList() {
		return bloodTypeList;
	}

	public void setBloodTypeList(List<SysDictItem> bloodTypeList) {
		this.bloodTypeList = bloodTypeList;
	}

	public GameModel() {
		super();
	}

	public CommModel getCommModel() {
		return commModel;
	}

	public void setCommModel(CommModel commModel) {
		this.commModel = commModel;
	}

	public Game getItem() {
		return item;
	}

	public void setItem(Game item) {
		this.item = item;
	}

	public GameCondition getCondition() {
		return condition;
	}

	public void setCondition(GameCondition condition) {
		this.condition = condition;
	}

	public List<Game> getGameList() {
		return gameList;
	}

	public void setGameList(List<Game> gameList) {
		this.gameList = gameList;
	}

	public GameEnroll getGameEnroll() {
		return gameEnroll;
	}

	public void setGameEnroll(GameEnroll gameEnroll) {
		this.gameEnroll = gameEnroll;
	}

	public List<GameEnroll> getGameEnrollList() {
		return gameEnrollList;
	}

	public void setGameEnrollList(List<GameEnroll> gameEnrollList) {
		this.gameEnrollList = gameEnrollList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<SysUploadFile> getUploadList() {
		return uploadList;
	}

	public void setUploadList(List<SysUploadFile> uploadList) {
		this.uploadList = uploadList;
	}

	public GameEnrollTeam getEnrollTeam() {
		return enrollTeam;
	}

	public void setEnrollTeam(GameEnrollTeam enrollTeam) {
		this.enrollTeam = enrollTeam;
	}

	public PageResult<SysUploadFile> getPhotoPageResult() {
		return photoPageResult;
	}

	public void setPhotoPageResult(PageResult<SysUploadFile> photoPageResult) {
		this.photoPageResult = photoPageResult;
	}

	public List<SysDictItem> getCardTypeList() {
		return cardTypeList;
	}

	public void setCardTypeList(List<SysDictItem> cardTypeList) {
		this.cardTypeList = cardTypeList;
	}

}
