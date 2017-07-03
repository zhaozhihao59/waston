package com.tocersoft.cms.model;

import java.util.ArrayList;
import java.util.List;

import com.tocersoft.auth.entity.User;
import com.tocersoft.base.model.BaseModel;
import com.tocersoft.cms.dto.MessageCondition;
import com.tocersoft.cms.entity.CmsArticle;
import com.tocersoft.cms.entity.Message;

public class MessageModel extends BaseModel<Message>{
	
	private static final long serialVersionUID = -3579744296285032578L;
	private Message item = new Message();
	private MessageCondition condition = new MessageCondition();
	private User user = new User();
	private List<User> userList = new ArrayList<User>();
	private String ids;
	private String roleJSON;
	private String userJSON;
	private String selIds;
	/** 会员独享活动的预约，将对应的活动传到页面 */
	private CmsArticle article;
	
	public CmsArticle getArticle() {
		return article;
	}
	public void setArticle(CmsArticle article) {
		this.article = article;
	}
	public Message getItem() {
		return item;
	}
	public void setItem(Message item) {
		this.item = item;
	}
	public MessageCondition getCondition() {
		return condition;
	}
	public void setCondition(MessageCondition condition) {
		this.condition = condition;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getRoleJSON() {
		return roleJSON;
	}
	public void setRoleJSON(String roleJSON) {
		this.roleJSON = roleJSON;
	}
	public String getUserJSON() {
		return userJSON;
	}
	public void setUserJSON(String userJSON) {
		this.userJSON = userJSON;
	}
	public String getSelIds() {
		return selIds;
	}
	public void setSelIds(String selIds) {
		this.selIds = selIds;
	}
}
