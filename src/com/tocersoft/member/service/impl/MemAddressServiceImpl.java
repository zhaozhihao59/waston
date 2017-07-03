package com.tocersoft.member.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.member.dao.IMemAddressDao;
import com.tocersoft.member.entity.MemAddress;
import com.tocersoft.member.service.IMemAddressService;

import com.tocersoft.member.dto.MemAddressCondition;

@Service("memAddressServiceImpl")
@Transactional(value = "transactionManager")
public class MemAddressServiceImpl implements IMemAddressService{

	@Resource(name = "memAddressDaoImpl")
	private IMemAddressDao memAddressDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listMemAddressByPage(PageResult<MemAddress> pageResult,MemAddressCondition condition){
		int rows = memAddressDao.listMemAddressByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<MemAddress> list = memAddressDao.listMemAddressByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 
	 */
	public MemAddress getMemAddressById(String id){
		return memAddressDao.getMemAddressById(id);
	}

	/**
	 * 新增
	 * @param item 
	 */
	public void add(MemAddress item){
		//判断会员下的收货地址数量
		List<MemAddress> memAddressList = memAddressDao.listMemAddressByMemberId(item.getMemberId());
		if(CollectionUtils.isEmpty(memAddressList) || memAddressList.size() < 1){
			item.setAddressStatus(1);	//默认地址
		}else{
			item.setAddressStatus(0);	//非默认
		}
		
		memAddressDao.add(item);
	}

	/**
	 * 修改
	 * @param item 
	 */
	public void update(MemAddress item){
		memAddressDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids,String memberId){
		memAddressDao.delByIds(ids,memberId);
	}

	@Override
	public List<MemAddress> listMemAddressByMemberId(String memberId) {
		return memAddressDao.listMemAddressByMemberId(memberId);
	}

	@Override
	public void doUpdateDefaultAddress(String memAddressId, String memberId) {
		memAddressDao.updateAllAddressStatusByMemberId(memberId,0);
		memAddressDao.updateDefaultAddress(memAddressId,memberId);
	}

	@Override
	public MemAddress getDefaultMemAddress(String memberId) {
		return memAddressDao.getDefaultMemAddress(memberId);
	}

}

