package com.tocersoft.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.product.entity.ProductComment;
import com.tocersoft.product.dto.ProductCommentCondition;

@Repository("productCommentDaoImpl")
public interface IProductCommentDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<ProductComment> listProductCommentByPage(RowBounds bounds,@Param("condition") ProductCommentCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listProductCommentByPageCount(@Param("condition") ProductCommentCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品评论表
	 */
	ProductComment getProductCommentById(@Param("id") String id);

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
	List<ProductComment> listProductCommentByCondition(@Param("condition") ProductCommentCondition condition);
}

