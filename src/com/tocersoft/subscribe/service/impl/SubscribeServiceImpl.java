package com.tocersoft.subscribe.service.impl;

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

import com.tocersoft.base.page.PageResult;
import com.tocersoft.base.utils.ExcelUtil;
import com.tocersoft.cms.dao.ICmsChannelDao;
import com.tocersoft.cms.entity.CmsChannel;
import com.tocersoft.game.dto.GameEnrollCondition;
import com.tocersoft.game.entity.GameEnroll;
import com.tocersoft.subscribe.dao.ISubscribeDao;
import com.tocersoft.subscribe.dto.SubscribeCondition;
import com.tocersoft.subscribe.entity.Subscribe;
import com.tocersoft.subscribe.service.ISubscribeService;

@Service("subscribeServiceImpl")
@Transactional(value = "transactionManager")
public class SubscribeServiceImpl implements ISubscribeService{

	@Resource(name = "subscribeDaoImpl")
	private ISubscribeDao subscribeDao;
	@Resource(name="cmsChannelDaoImpl")
	private ICmsChannelDao cmschanneldao;
	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listSubscribeByPage(PageResult<Subscribe> pageResult,SubscribeCondition condition){
		int rows = subscribeDao.listSubscribeByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<Subscribe> list = subscribeDao.listSubscribeByPage(rowBounds,condition);
		pageResult.setResult(list);
	}
	
	/**
	 * 选择已订阅的人员分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */

	@Override
	public void listSubscribeSelectByPage(PageResult<Subscribe> pageResult,
			SubscribeCondition condition) {
		condition.setState("0");
		int rows = subscribeDao.listSubscribeByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<Subscribe> list = subscribeDao.listSubscribeSelectByPage(rowBounds,condition);
		pageResult.setResult(list);
		
	}



	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 订阅表
	 */
	public Subscribe getSubscribeById(String id){
		return subscribeDao.getSubscribeById(id);
	}

	/**
	 * 新增
	 * @param item 订阅表
	 */
	public void add(Subscribe item){
		//正常订阅
		item.setState(0);
		//创建时间
		item.setCreateDate(new Date());
		//根据栏目ID查找栏目名称
		CmsChannel channel = cmschanneldao.getCmsChannelById(item.getChannelId());
		item.setChannelName(channel.getName());	
		subscribeDao.add(item);
	}

	/**
	 * 根据邮箱、期刊类型查询
	 * @param id
	 * @return
	 */
	public Subscribe getSubscribeItemByemail(String email,String channelid){
		return subscribeDao.getSubscribeItemByemail(email, channelid);
	}
	/**
	 * 修改
	 * @param item 订阅表
	 */
	public void update(Subscribe item){
		Subscribe subscribe = subscribeDao.getSubscribeById(item.getId());
		subscribe.setName(item.getName());
		subscribe.setMobile(item.getMobile());
		subscribe.setEmail(item.getEmail());
		subscribe.setState(item.getState());
		subscribe.setCompanyName(item.getCompanyName());
		subscribe.setPosition(item.getPosition());
		subscribe.setChannelId(item.getChannelId());
		//根据栏目ID查找栏目名称
		CmsChannel channel = cmschanneldao.getCmsChannelById(item.getChannelId());
		subscribe.setChannelName(channel.getName());
		subscribe.setCreateDate(new Date());
		
		
		subscribeDao.update(subscribe);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		subscribeDao.delByIds(ids);
	}
	@Override
	public int getSubscribeByemail(String email, String channelid) {
		// TODO Auto-generated method stub
		return subscribeDao.getSubscribeByemail(email, channelid);
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
	public String doExport(SubscribeCondition condition){
		int count = subscribeDao.listSubscribeByPageCount(condition);
		RowBounds rowBound = new RowBounds(0,count);
		List<Subscribe> list  =  subscribeDao.listSubscribeByPage(rowBound, condition);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("订阅名单");
        String [] titles = new String[]{"订阅者姓名","手机号码","邮箱","公司名称","职务",
        		"期刊类型名称","订阅状态","订阅时间"}; 
        //列宽
        Integer[] titleWidths = new Integer[]{20,20,20,20,20,20,20,20};
        // 列头
        HSSFRow row = ExcelUtil.createHSSFRow(titles, titleWidths, wb, sheet, 0);
		HSSFCellStyle cellStyles= ExcelUtil.createHSSFCellStyle(wb);
        for(int i = 0; i < list.size(); i++){
        	row = sheet.createRow(i+1);
        	Subscribe item = list.get(i);
        	ExcelUtil.createCell(cellStyles, row,0).setCellValue(item.getName());
        	ExcelUtil.createCell(cellStyles, row,1).setCellValue(item.getMobile());
        	ExcelUtil.createCell(cellStyles, row,2).setCellValue(item.getEmail());
        	ExcelUtil.createCell(cellStyles, row,3).setCellValue(item.getCompanyName());
        	ExcelUtil.createCell(cellStyles, row,4).setCellValue(item.getPosition());
        	ExcelUtil.createCell(cellStyles, row,5).setCellValue(item.getChannelName());
        	
        	String  stateStr="--";
        	if((item.getState()+"") != null){
        		if(item.getState() ==0){
            		stateStr ="正常订阅";
            	}else if(item.getState() ==1){
            		stateStr ="退订";
            	}
        	}
        	ExcelUtil.createCell(cellStyles, row,6).setCellValue(sdf.format(item.getCreateDate()));
        	ExcelUtil.createCell(cellStyles, row,7).setCellValue(stateStr);
        }
       return ExcelUtil.exportAjaxExcelData("报名信息", wb);
	}

	/**
	 * 取消订阅
	 */
	@Override
	public void cancleSubscribe(String channelID, String email) {
		Subscribe item = new Subscribe();
		item.setChannelId(channelID);
		item.setEmail(email);
		subscribeDao.cancleSuscribe(item);
	}
	
	
	
}

