package com.tocersoft.professional.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.professional.dto.ProfessionalCondition;
import com.tocersoft.professional.entity.Professional;

@Repository("professionalDaoImpl")
public interface IProfessionalDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<Professional> listProfessionalByPage(RowBounds bounds,@Param("condition") ProfessionalCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listProfessionalByPageCount(@Param("condition") ProfessionalCondition condition,String ids[]);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 专业人员表
	 */
	Professional getProfessionalById(@Param("id") String id);

	/**
	 * 新增
	 * @param item 专业人员表
	 */
	void add(Professional item);

	/**
	 * 修改
	 * @param item 专业人员表
	 */
	void update(Professional item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);
	
	/**
	 * 点击上一页显示专业人员详情
	 * 根据创建时间查询
	 */
	Professional getProProfessionalByTime(@Param("selIds") String selIds);
	
	/**
	 * 点击上一页显示专业人员详情
	 * 根据创建时间查询
	 */
	Professional getNextProProfessionalByTime(@Param("selIds") String selIds);
	/**
	 * 查询所有的律师ID
	 * @return
	 */
	List<Professional> listProfessionalLvShiId();
	/**
	 * 根据sort查询（防止设置错误可查询多条记录）
	 * @param sort
	 * @return
	 */
	List<Professional> listProfessionalSort(@Param("sort") String sort);
}

