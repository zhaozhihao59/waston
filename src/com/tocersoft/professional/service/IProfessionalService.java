package com.tocersoft.professional.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.professional.dto.ProfessionalCondition;
import com.tocersoft.professional.entity.Professional;

public interface IProfessionalService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listProfessionalByPage(PageResult<Professional> pageResult,ProfessionalCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 专业人员表
	 */
	Professional getProfessionalById(String id);

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
	Professional getProProfessionalByTime(String selIds);
	
	/**
	 * 点击下一页显示专业人员详情
	 * 根据创建时间查询
	 */
	Professional getNextProfessionalByTime(String selIds);
	
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

