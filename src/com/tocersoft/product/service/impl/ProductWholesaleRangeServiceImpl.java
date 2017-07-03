package com.tocersoft.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.product.dao.IProductCategoryAttributeDao;
import com.tocersoft.product.dao.IProductCategoryAttributeValueDao;
import com.tocersoft.product.dao.IProductWholesaleRangeDao;
import com.tocersoft.product.dto.ProductWholesaleRangeCondition;
import com.tocersoft.product.entity.ProductWholesaleRange;
import com.tocersoft.product.service.IProductWholesaleRangeService;

@Service("productWholesaleRangeServiceImpl")
@Transactional(value = "transactionManager")
public class ProductWholesaleRangeServiceImpl implements IProductWholesaleRangeService{

	@Resource(name = "productWholesaleRangeDaoImpl")
	private IProductWholesaleRangeDao productWholesaleRangeDao;
	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listProductWholesaleRangeByPage(PageResult<ProductWholesaleRange> pageResult,
			ProductWholesaleRangeCondition condition) {
		int rows = productWholesaleRangeDao.listProductWholesaleRangeByPageCount(condition);
		pageResult.setRows(rows);
		
		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<ProductWholesaleRange> list = productWholesaleRangeDao.listProductWholesaleRangeByPage(rowBounds, condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品批发折扣区间
	 */
	public ProductWholesaleRange getProductWholesaleRangeById(String id){
		return productWholesaleRangeDao.getProductWholesaleRangeById(id);
	}

	/**
	 * 新增
	 * @param item 产品批发折扣区间
	 */
	public void add(ProductWholesaleRange item){
		productWholesaleRangeDao.add(item);
	}

	/**
	 * 修改
	 * @param item 产品批发折扣区间
	 */
	public void update(ProductWholesaleRange item){
		productWholesaleRangeDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		productWholesaleRangeDao.delByIds(ids);
	}
}

