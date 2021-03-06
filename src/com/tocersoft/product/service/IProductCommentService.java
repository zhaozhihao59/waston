package com.tocersoft.product.service;

import java.util.List;

import com.tocersoft.product.entity.ProductComment;
import com.tocersoft.base.page.PageResult;

import com.tocersoft.product.dto.ProductCommentCondition;

public interface IProductCommentService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listProductCommentByPage(PageResult<ProductComment> pageResult,ProductCommentCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品评论表
	 */
	ProductComment getProductCommentById(String id);

	/**
	 * 新增
	 * @param item 产品评论表
	 */
	void add(ProductComment item);

	/**
	 * 修改
	 * @param item 产品评论表
	 */
	void update(ProductComment item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

	/**
	 * 根据条件查询
	 * @param condition
	 * @return
	 */
	List<ProductComment> listProductCommentByCondition(ProductCommentCondition condition);
}

