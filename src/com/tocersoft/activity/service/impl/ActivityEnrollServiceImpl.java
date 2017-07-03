package com.tocersoft.activity.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.activity.dao.IActivityDao;
import com.tocersoft.activity.dao.IActivityEnrollDao;
import com.tocersoft.activity.dto.ActivityEnrollCondition;
import com.tocersoft.activity.entity.Activity;
import com.tocersoft.activity.entity.ActivityEnroll;
import com.tocersoft.activity.service.IActivityEnrollService;
import com.tocersoft.base.page.PageResult;
import com.tocersoft.base.utils.ExcelUtil;
import com.tocersoft.subscribe.dto.SubscribeCondition;
import com.tocersoft.subscribe.entity.Subscribe;

@Service("activityEnrollServiceImpl")
@Transactional(value = "transactionManager")
public class ActivityEnrollServiceImpl implements IActivityEnrollService{

	@Resource(name = "activityEnrollDaoImpl")
	private IActivityEnrollDao activityEnrollDao;

	@Resource
	private IActivityDao activityDao;
	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listActivityEnrollByPage(PageResult<ActivityEnroll> pageResult,ActivityEnrollCondition condition){
		int rows = activityEnrollDao.listActivityEnrollByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<ActivityEnroll> list = activityEnrollDao.listActivityEnrollByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 活动报名表
	 */
	public ActivityEnroll getActivityEnrollById(String id){
		return activityEnrollDao.getActivityEnrollById(id);
	}

	/**
	 * 新增
	 * @param item 活动报名表
	 */
	public void add(ActivityEnroll item){
		activityEnrollDao.add(item);
	}

	/**
	 * 修改
	 * @param item 活动报名表
	 */
	public void update(ActivityEnroll item){
		activityEnrollDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		activityEnrollDao.delByIds(ids);
	}

	/**
	 * 立即报名注册
	 * @author lhb
	 */
	@Override
	public void insertRegeister(ActivityEnroll item) {
		Activity activityItem = activityDao.getActivityById(item.getActivityId());
		ActivityEnroll activityEnroll = new ActivityEnroll();
		activityEnroll.setActivityId(item.getActivityId());
		activityEnroll.setActivityName(activityItem.getTitle());
		activityEnroll.setName(item.getName());
		activityEnroll.setCompanyName(item.getCompanyName());
		activityEnroll.setCreateBy(item.getName());
		activityEnroll.setCreateDate(new Date());
		activityEnroll.setEmail(item.getEmail());
		activityEnroll.setTel(item.getTel());
		activityEnroll.setMobile(item.getMobile());
		activityEnroll.setDeleteFlag(0);
		activityEnroll.setPositionName(item.getPositionName());
		activityEnrollDao.insertRegeister(activityEnroll);
	}
	
	/**
	 * 检验邮箱是否已经报名过
	 * @author LHB
	 */
	@Override
	public int checkEnrollEmail(String activityId, String email) {
		
		return activityEnrollDao.checkEnrollEmail(activityId, email);
	}
	/**
	 * 检验手机号是否已经报名过
	 * @author LHB
	 */
	@Override
	public int checkEnrollMobile(String activityId, String mobile) {
		
		return activityEnrollDao.checkEnrollMobile(activityId, mobile);
	}
	/**
	 * 导出
	 * @author 刘鸿博
	 */
	/**
	 * 
	 * @param condition
	 * @return
	 */
	@Override
	public String doExport(ActivityEnrollCondition condition){
		int count = activityEnrollDao.listActivityEnrollByPageCount(condition);
		RowBounds rowBound = new RowBounds(0,count);
		List<ActivityEnroll> list  =  activityEnrollDao.listActivityEnrollByPage(rowBound, condition);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("活动报名名单");
        String [] titles = new String[]{"活动名称","姓名","手机号码","邮箱","公司名称",
        		"职位","创建时间"}; 
        //列宽
        Integer[] titleWidths = new Integer[]{20,20,20,20,20,20,20};
        // 列头
        HSSFRow row = ExcelUtil.createHSSFRow(titles, titleWidths, wb, sheet, 0);
		HSSFCellStyle cellStyles= ExcelUtil.createHSSFCellStyle(wb);
        for(int i = 0; i < list.size(); i++){
        	row = sheet.createRow(i+1);
        	ActivityEnroll item = list.get(i);
        	ExcelUtil.createCell(cellStyles, row,0).setCellValue(item.getActivityName());
        	ExcelUtil.createCell(cellStyles, row,1).setCellValue(item.getName());
        	ExcelUtil.createCell(cellStyles, row,2).setCellValue(item.getMobile());
        	ExcelUtil.createCell(cellStyles, row,3).setCellValue(item.getEmail());
        	ExcelUtil.createCell(cellStyles, row,4).setCellValue(item.getCompanyName());
        	ExcelUtil.createCell(cellStyles, row,5).setCellValue(item.getPositionName());
        	
        	
        	ExcelUtil.createCell(cellStyles, row,6).setCellValue(sdf.format(item.getCreateDate()));
        }
       return ExcelUtil.exportAjaxExcelData("活动报名名单", wb);
	}
	

}

