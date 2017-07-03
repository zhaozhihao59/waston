package com.tocersoft.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.order.dto.FreightTemplateItemCondition;
import com.tocersoft.order.entity.FreightTemplateItem;

@Repository("freightTemplateItemDaoImpl")
public interface IFreightTemplateItemDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<FreightTemplateItem> listFreightTemplateItemByPage(RowBounds bounds,@Param("condition") FreightTemplateItemCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listFreightTemplateItemByPageCount(@Param("condition") FreightTemplateItemCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 运费模板表-明细
	 */
	FreightTemplateItem getFreightTemplateItemById(@Param("id") String id);

	/**
	 * 查询集合通过条件
	 * @param condition
	 * @return
	 */
	List<FreightTemplateItem> listFreightTemplateItemByCondition(@Param("condition") FreightTemplateItemCondition condition);
	
	/**
	 * 新增
	 * @param item 运费模板表-明细
	 */
	void add(FreightTemplateItem item);

	/**
	 * 修改
	 * @param item 运费模板表-明细
	 */
	void update(FreightTemplateItem item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

	/**
	 * 根据模板ID和达到城市查找运费
	 * @param freightTemplateId
	 * @param arrivalCity
	 * @return
	 */
	FreightTemplateItem getFreightTemplateItemByTemplateIdAndItemName(@Param("freightTemplateId") String freightTemplateId,@Param("arrivalCity") String arrivalCity);

}

