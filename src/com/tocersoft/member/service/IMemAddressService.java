package com.tocersoft.member.service;

import java.util.List;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.member.dto.MemAddressCondition;
import com.tocersoft.member.entity.MemAddress;

public interface IMemAddressService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listMemAddressByPage(PageResult<MemAddress> pageResult,MemAddressCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 
	 */
	MemAddress getMemAddressById(String id);

	/**
	 * 新增
	 * @param item 
	 */
	void add(MemAddress item);

	/**
	 * 修改
	 * @param item 
	 */
	void update(MemAddress item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids,String memberId);

	/**
	 * 根据会员ID查询收货地址
	 * @param memberId
	 * @return
	 */
	List<MemAddress> listMemAddressByMemberId(String memberId);

	/**
	 * 更新默认收货地址
	 * @param memAddressId
	 * @param memberId
	 */
	void doUpdateDefaultAddress(String memAddressId, String memberId);

	/**
	 * 根据会员ID取默认收货地址
	 * @param memAddressId
	 * @return
	 */
	MemAddress getDefaultMemAddress(String memberId);

}

