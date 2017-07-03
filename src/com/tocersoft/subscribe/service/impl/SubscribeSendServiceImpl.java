package com.tocersoft.subscribe.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paypal.sdk.openidconnect.Session;
import com.tocersoft.auth.entity.User;
import com.tocersoft.base.page.PageResult;
import com.tocersoft.email.dao.ISysEmailDao;
import com.tocersoft.email.entity.SysEmail;
import com.tocersoft.subscribe.dao.ISubscribeSendDao;
import com.tocersoft.subscribe.dto.SubscribeSendCondition;
import com.tocersoft.subscribe.entity.Subscribe;
import com.tocersoft.subscribe.entity.SubscribeSend;
import com.tocersoft.subscribe.entity.SubscribeSendRecord;
import com.tocersoft.subscribe.service.ISubscribeSendRecordService;
import com.tocersoft.subscribe.service.ISubscribeSendService;
import com.tocersoft.subscribe.service.ISubscribeService;

@Service("subscribeSendServiceImpl")
@Transactional(value = "transactionManager")
public class SubscribeSendServiceImpl implements ISubscribeSendService{

	@Resource(name = "subscribeSendDaoImpl")
	private ISubscribeSendDao subscribeSendDao;
	@Resource(name = "subscribeServiceImpl")
	private ISubscribeService subscribeService;
	@Resource(name = "subscribeSendRecordServiceImpl")
	private ISubscribeSendRecordService subscribeSendRecordService;
	@Resource
	private ISysEmailDao  sysEmailDao;
	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listSubscribeSendByPage(PageResult<SubscribeSend> pageResult,SubscribeSendCondition condition){
		int rows = subscribeSendDao.listSubscribeSendByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<SubscribeSend> list = subscribeSendDao.listSubscribeSendByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 邮件订阅发送表
	 */
	public SubscribeSend getSubscribeSendById(String id){
		return subscribeSendDao.getSubscribeSendById(id);
	}

	/**
	 * 新增
	 * @param item 邮件订阅发送表
	 */
	public void add(SubscribeSend item){
		subscribeSendDao.add(item);
	}

	/**
	 * 修改
	 * @param item 邮件订阅发送表
	 */
	public void update(SubscribeSend item){
		subscribeSendDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		subscribeSendDao.delByIds(ids);
	}

	/**
	 * 发送邮件
	 */
	@Override
	public void sendEmail(String[] subscribe, SubscribeSend subscribeSend) {
//		String emailStr="";
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		subscribeSend.setCreateBy(user.getUsername());
		subscribeSend.setCreateDate(new Date());
		subscribeSend.setUpdateBy(user.getUsername());
		subscribeSend.setUpdateDate(new Date());
		subscribeSendDao.add(subscribeSend);
		Subscribe subscribeInfo =null;
		SubscribeSendRecord subscribeSendRecord = null;
		for(int i = 0;i<subscribe.length ;i++){
			subscribeInfo = subscribeService.getSubscribeById(StringUtils.trim(subscribe[i]));
//			emailStr=emailStr+subscribeInfo.getEmail()+",";
			subscribeSendRecord=new SubscribeSendRecord();
			subscribeSendRecord.setCreateBy(user.getUsername());
			subscribeSendRecord.setCreateDate(new Date());
			subscribeSendRecord.setSubscribeId(subscribeInfo.getId());
			subscribeSendRecord.setEmail(subscribeInfo.getEmail());
			subscribeSendRecord.setSendId(subscribeSend.getId());
			subscribeSendRecordService.add(subscribeSendRecord);
//			//创建发送邮件
			SysEmail sysEmail= new SysEmail();
			sysEmail.setTitle(subscribeSend.getSendTitle());
			String content = subscribeSend.getHtmlContent();
			String param = "http://watson.chunkea.com/front/subscribe/cancle_subscribe.htm?email="+subscribeInfo.getEmail()+"&channelId="+subscribeInfo.getChannelId();
			String preContent="<p style='font-size:12px;background-color:#daf0b2;padding:2px 5px;'>本邮件内容由华诚律师事务所提供，如果您不想继续收到该邮件，可<a href='"+param+"' style='color:#0066cc;font-size:12px;'>点此退订</a></p><br/><br/>";
			sysEmail.setContent(preContent+content);
			sysEmail.setEmail(subscribeInfo.getEmail());
			sysEmail.setType("1");
			//默认为未发送
			sysEmail.setFlag(0);
			sysEmailDao.add(sysEmail);
		}
//		emailStr = emailStr.substring(0,emailStr.length()-1);
	}
	

	

}

