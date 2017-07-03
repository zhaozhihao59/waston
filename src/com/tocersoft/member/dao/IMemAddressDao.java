package com.tocersoft.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.member.dto.MemAddressCondition;
import com.tocersoft.member.entity.MemAddress;

@Repository("memAddressDaoImpl")
public interface IMemAddressDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<MemAddress> listMemAddressByPage(RowBounds bounds,@Param("condition") MemAddressCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listMemAddressByPageCount(@Param("condition") MemAddressCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 
	 */
	MemAddress getMemAddressById(@Param("id") String id);

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
	void delByIds(@Param("ids") String[] ids,@Param("memberId") String memberId);

	/**
	 * 加载会员收货地址
	 * @param memberId
	 * @return
	 */
	List<MemAddress> listMemAddressByMemberId(@Param("memberId") String memberId);
	/**
	 * 更新地址状态
	 * @param memberId
	 * @param addressStatus
	 */
	void updateAllAddressStatusByMemberId(@Param("memberId") String memberId,@Param("addressStatus")  int addressStatus);

	/**
	 * 更新默认收货地址
	 * @param memAddressId
	 * @param memberId
	 */
	void updateDefaultAddress(@Param("memAddressId") String memAddressId,@Param("memberId") String memberId);

	/**
	 * 获取默认地址
	 * @param memAddressId
	 * @return
	 */
	MemAddress getDefaultMemAddress(@Param("memberId") String memberId);

	/**
	 * 根据会员ID集合删除
	 * @param memberIds
	 */
	void delByMemberIds(@Param("memberIds") String[] memberIds);


}

