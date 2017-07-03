package com.tocersoft.member.model;

import java.util.ArrayList;
import java.util.List;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.base.page.PageResult;
import com.tocersoft.game.dto.GameEnrollCondition;
import com.tocersoft.game.entity.GameEnroll;
import com.tocersoft.member.dto.MemberCondition;
import com.tocersoft.member.entity.Member;
import com.tocersoft.order.dto.OrderSellCondition;
import com.tocersoft.order.entity.OrderSell;
import com.tocersoft.order.entity.OrderSellItem;

public class MemberModel extends BaseModel<Member>{

	/** */
	private static final long serialVersionUID = 1L;

	private Member item = new Member();

	private MemberCondition condition = new MemberCondition();
	
	private String matchJSON;
	
	//报名条件类
	private GameEnrollCondition gameEnrollCondition = new GameEnrollCondition();
	//报名分页
	private PageResult<GameEnroll> gameEnrollpageResult = new PageResult<GameEnroll>();
	
	// 当前页
	private int page;
	/** 邀请码 */
	private String code;
	
	/** 会员帐号 - 用于验证唯一性 */
	private String account;
	
	/** 会员邮箱 - 用于验证唯一性 */
	private String email;
	
	/** 会员手机- 用于验证唯一性 */
	private String mobile;
	/** 验证码 */
	private String verCode;
	
	/** 是否记住密码 1-记住密码 0或null-不记住密码 */
	private Integer isRmbPwd;

	private String passworded;
	/** 我的订单集合 */
	private List<OrderSell> orderSells = new ArrayList<OrderSell>();
	/** 订单搜索条件 */
	private OrderSellCondition orderSellCondition = new OrderSellCondition();
	/** 订单ID */
	private String orderId;
	/** 订单详情 */
	private OrderSell orderSell = new OrderSell();
	
	PageResult<OrderSell> pageResult1 = new PageResult<OrderSell>();

	/** 保存前一个地址*/
	private String preUrl;
	public Integer getIsRmbPwd() {
		return isRmbPwd;
	}

	public void setIsRmbPwd(Integer isRmbPwd) {
		this.isRmbPwd = isRmbPwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVerCode() {
		return verCode;
	}

	public void setVerCode(String verCode) {
		this.verCode = verCode;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMatchJSON() {
		return matchJSON;
	}

	public void setMatchJSON(String matchJSON) {
		this.matchJSON = matchJSON;
	}

	public MemberModel() {
		super();
	}

	public Member getItem() {
		return item;
	}

	public void setItem(Member item) {
		this.item = item;
	}

	public MemberCondition getCondition() {
		return condition;
	}

	public void setCondition(MemberCondition condition) {
		this.condition = condition;
	}

	public String getPassworded() {
		return passworded;
	}

	public void setPassworded(String passworded) {
		this.passworded = passworded;
	}

	public String getPreUrl() {
		return preUrl;
	}

	public void setPreUrl(String preUrl) {
		this.preUrl = preUrl;
	}
 
	public List<OrderSell> getOrderSells() {
		return orderSells;
	}

	public void setOrderSells(List<OrderSell> orderSells) {
		this.orderSells = orderSells;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public OrderSell getOrderSell() {
		return orderSell;
	}

	public void setOrderSell(OrderSell orderSell) {
		this.orderSell = orderSell;
	}

	public OrderSellCondition getOrderSellCondition() {
		return orderSellCondition;
	}

	public void setOrderSellCondition(OrderSellCondition orderSellCondition) {
		this.orderSellCondition = orderSellCondition;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public PageResult<OrderSell> getPageResult1() {
		return pageResult1;
	}

	public void setPageResult1(PageResult<OrderSell> pageResult1) {
		this.pageResult1 = pageResult1;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public GameEnrollCondition getGameEnrollCondition() {
		return gameEnrollCondition;
	}

	public void setGameEnrollCondition(GameEnrollCondition gameEnrollCondition) {
		this.gameEnrollCondition = gameEnrollCondition;
	}

	public PageResult<GameEnroll> getGameEnrollpageResult() {
		return gameEnrollpageResult;
	}

	public void setGameEnrollpageResult(PageResult<GameEnroll> gameEnrollpageResult) {
		this.gameEnrollpageResult = gameEnrollpageResult;
	}
	
}
