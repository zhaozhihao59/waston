package com.tocersoft.product.service.impl;

import java.io.FileInputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.base.utils.CommonUtil;
import com.tocersoft.base.utils.UUIDUtil;
import com.tocersoft.product.dao.IProductCategoryDao;
import com.tocersoft.product.entity.Product;
import com.tocersoft.product.entity.ProductCategory;
import com.tocersoft.product.service.IProductCategoryService;

import com.tocersoft.product.dto.ProductCategoryCondition;

@Service("productCategoryServiceImpl")
@Transactional(value = "transactionManager")
public class ProductCategoryServiceImpl implements IProductCategoryService{

	@Resource(name = "productCategoryDaoImpl")
	private IProductCategoryDao productCategoryDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listProductCategoryByPage(PageResult<ProductCategory> pageResult,ProductCategoryCondition condition){
		int rows = productCategoryDao.listProductCategoryByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<ProductCategory> list = productCategoryDao.listProductCategoryByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 查询所有数据
	 */
	public List<ProductCategory> listProductCategory(){
		return productCategoryDao.listProductCategory();
	}
	
	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品类别信息
	 */
	public ProductCategory getProductCategoryById(String id){
		return productCategoryDao.getProductCategoryById(id);
	}

	/**
	 * 新增
	 * @param item 产品类别信息
	 */
	public void add(ProductCategory item){
		String id = UUIDUtil.uuid();
		item.setId(id);
		productCategoryDao.add(item);
	}

	/**
	 * 修改
	 * @param item 产品类别信息
	 */
	public void update(ProductCategory item){
		productCategoryDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		productCategoryDao.delByIds(ids);
	}

	/**
	 * 根据父级ID查询下级类别
	 * @param pid 父级ID
	 */
	@Override
	public List<ProductCategory> listProductCatgoryByParentId(String pid) {
		return productCategoryDao.listProductCatgoryByParentId(pid);
	}

	/** 导入产品类别 */
	@Override
	public String importXls(String path) {
		String message  = "";
		
		POIFSFileSystem fs = null;
		HSSFWorkbook wb = null;
		try {
			fs = new POIFSFileSystem(new FileInputStream(path));
			wb = new HSSFWorkbook(fs);
		} catch (Exception e) {
		 
		}
		
		if (null == wb) {
			return "上传错误";
		}
		
		HSSFSheet sheet = wb.getSheetAt(0);
		// 获得总行数
		int rows = sheet.getLastRowNum();
		// 遍历所有行
		for (int i = 1; i <= rows; i++) {
			HSSFRow row = sheet.getRow(i);
			if(row == null){
				continue;
			}
			
			String id = CommonUtil.getCellValue(row.getCell(0));
			String name  = CommonUtil.getCellValue(row.getCell(1));
			String enName  = CommonUtil.getCellValue(row.getCell(2));
			String desc  = CommonUtil.getCellValue(row.getCell(3));
			String isParent  = CommonUtil.getCellValue(row.getCell(4));
			
			String pId = "0";
			if(id.length() > 3 ){
				pId = id.substring(0 , id.length() -3);
			}
			
			ProductCategory item = new ProductCategory();
			item.setId(id);
			item.setName(name);
			item.setTip(enName);
//			item.setLocation(0);
			item.setParentId(pId);
			item.setSort(i);
			item.setRemark(desc == null ? "" : desc);
			item.setIsParent(isParent.equals("0") ? 1:0);
			
			productCategoryDao.add(item);
		}
		
		
		return message;
	}
	
	 /**
	 * 查询所有父类别
	 */
	public List<ProductCategory> listTopProductCatgory(){
		return productCategoryDao.listTopProductCatgory();
	}
}

