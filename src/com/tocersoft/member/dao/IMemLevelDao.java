package com.tocersoft.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.member.entity.MemLevel;
import com.tocersoft.member.dto.MemLevelCondition;

@Repository("memLevelDaoImpl")
public interface IMemLevelDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<MemLevel> listMemLevelByPage(RowBounds bounds,@Param("condition") MemLevelCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listMemLevelByPageCount(@Param("condition") MemLevelCondition condition);
	
	/**
	 * 列出所有会员等级
	 */
	List<MemLevel> listAllMemLevel();

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 
	 */
	MemLevel getMemLevelById(@Param("id") String id);

	/**
	 * 新增
	 * @param item 
	 */
	void add(MemLevel item);

	/**
	 * 修改
	 * @param item 
	 */
	void update(MemLevel item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);
	/**
	 * 删除会员
	 * @param id
	 */
	public void delMemberById(String id);
	/**
	 * 批量删除会员
	 * @param selIds
	 */
	public void doBatchDelMember(String[] sel);

	/**
	 * 根据等级序号和ID（如果存在）查找会员等级对象数量
	 * @param item
	 * @return
	 */
	int getCountByLevelNumAndId(MemLevel item);

	/**
	 * 获取最小会员等级
	 * @return
	 */
	MemLevel getMinMemLevel();

}

