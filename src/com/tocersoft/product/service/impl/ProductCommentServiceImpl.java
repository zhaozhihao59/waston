package com.tocersoft.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.member.dao.IMemberDao;
import com.tocersoft.member.entity.Member;
import com.tocersoft.product.dao.IProductCommentDao;
import com.tocersoft.product.dao.IProductDao;
import com.tocersoft.product.dto.ProductCommentCondition;
import com.tocersoft.product.entity.Product;
import com.tocersoft.product.entity.ProductComment;
import com.tocersoft.product.service.IProductCommentService;

@Service("productCommentServiceImpl")
@Transactional(value = "transactionManager")
public class ProductCommentServiceImpl implements IProductCommentService{

	@Resource(name = "productCommentDaoImpl")
	private IProductCommentDao productCommentDao;
	@Resource(name = "memberDaoImpl")
	private IMemberDao memberDao;
	@Resource(name = "productDaoImpl")
	private IProductDao productDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listProductCommentByPage(PageResult<ProductComment> pageResult,ProductCommentCondition condition){
		int rows = productCommentDao.listProductCommentByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<ProductComment> list = productCommentDao.listProductCommentByPage(rowBounds,condition);
		for(ProductComment comment : list){
			Member member = memberDao.getMemberById(comment.getMemberId());
			comment.setMemberName(member.getName());
			Product product = productDao.getProductById(comment.getProductId());
			comment.setProductName(product.getName());
		}
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品评论表
	 */
	public ProductComment getProductCommentById(String id){
		return productCommentDao.getProductCommentById(id);
	}

	/**
	 * 新增
	 * @param item 产品评论表
	 */
	public void add(ProductComment item){
		productCommentDao.add(item);
	}

	/**
	 * 修改
	 * @param item 产品评论表
	 */
	public void update(ProductComment item){
		productCommentDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		productCommentDao.delByIds(ids);
	}
	
	/**
	 * 根据条件查询
	 * @param condition
	 * @return
	 */
	public List<ProductComment> listProductCommentByCondition(ProductCommentCondition condition){
		return productCommentDao.listProductCommentByCondition(condition);
	}
}

