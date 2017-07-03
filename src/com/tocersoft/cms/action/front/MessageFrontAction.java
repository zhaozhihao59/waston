package com.tocersoft.cms.action.front;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.action.BaseAction.Status;
import com.tocersoft.cms.model.MessageModel;
import com.tocersoft.cms.service.IMessageService;

/**
 * 留言
 * @author Tao
 * @createDate 2013-11-1 下午3:43:16
 */
@ParentPackage("front")
@Namespace("/message")
@Controller
public class MessageFrontAction extends BaseAction implements ModelDriven<MessageModel> {
	private static final long serialVersionUID = 5621271417787880258L;
	private MessageModel model=new MessageModel();
	
	@Resource
	private IMessageService massageService;
	
	/**
	 * 保存留言
	 */
	@Action(value="save")
	public String save(){
		try {
			model.getItem().setCreateDate(new Date());
			massageService.doSave(model.getItem());
		} catch (Exception e) {
			e.printStackTrace();
			return ajax(Status.error);
		}
		return ajax(Status.success);
	}

	@Override
	public MessageModel getModel() {
		return model;
	}

}
