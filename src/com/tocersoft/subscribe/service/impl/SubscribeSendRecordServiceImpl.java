package com.tocersoft.subscribe.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.subscribe.dao.ISubscribeSendRecordDao;
import com.tocersoft.subscribe.dto.SubscribeSendRecordCondition;
import com.tocersoft.subscribe.entity.SubscribeSendRecord;
import com.tocersoft.subscribe.service.ISubscribeSendRecordService;

@Service("subscribeSendRecordServiceImpl")
@Transactional(value = "transactionManager")
public class SubscribeSendRecordServiceImpl implements ISubscribeSendRecordService{

	@Resource(name = "subscribeSendRecordDaoImpl")
	private ISubscribeSendRecordDao subscribeSendRecordDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listSubscribeSendRecordByPage(PageResult<SubscribeSendRecord> pageResult,SubscribeSendRecordCondition condition){
		int rows = subscribeSendRecordDao.listSubscribeSendRecordByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<SubscribeSendRecord> list = subscribeSendRecordDao.listSubscribeSendRecordByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 邮件订阅发送表
	 */
	public SubscribeSendRecord getSubscribeSendRecordById(String id){
		return subscribeSendRecordDao.getSubscribeSendRecordById(id);
	}

	/**
	 * 新增
	 * @param item 邮件订阅发送表
	 */
	public void add(SubscribeSendRecord item){
		subscribeSendRecordDao.add(item);
	}

	/**
	 * 修改
	 * @param item 邮件订阅发送表
	 */
	public void update(SubscribeSendRecord item){
		subscribeSendRecordDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		subscribeSendRecordDao.delByIds(ids);
	}
	
	/**
	 *邮件发送记录列表
	 *@author lhb
	 */
	@Override
	public void subscribeSendList(PageResult<SubscribeSendRecord> pageResult,SubscribeSendRecordCondition condition) {
		int rows = subscribeSendRecordDao.subscribeSendListByCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<SubscribeSendRecord> list = subscribeSendRecordDao.subscribeSendList(rowBounds,condition);
		pageResult.setResult(list);
	}
	
	
	

}

