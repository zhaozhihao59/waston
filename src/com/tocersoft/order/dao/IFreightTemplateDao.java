package com.tocersoft.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.order.dto.FreightTemplateCondition;
import com.tocersoft.order.entity.FreightTemplate;

@Repository("freightTemplateDaoImpl")
public interface IFreightTemplateDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<FreightTemplate> listFreightTemplateByPage(RowBounds bounds,@Param("condition") FreightTemplateCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listFreightTemplateByPageCount(@Param("condition") FreightTemplateCondition condition);
	

	/**
	 * 查询所有
	 * @return
	 */
	List<FreightTemplate> listFreightTemplateAll(@Param("condition") FreightTemplateCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 运费模板表
	 */
	FreightTemplate getFreightTemplateById(@Param("id") String id);

	/**
	 * 新增
	 * @param item 运费模板表
	 */
	void add(FreightTemplate item);

	/**
	 * 修改
	 * @param item 运费模板表
	 */
	void update(FreightTemplate item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);
}

