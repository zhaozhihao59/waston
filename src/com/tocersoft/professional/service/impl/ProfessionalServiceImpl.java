package com.tocersoft.professional.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.professional.dao.IProfessionalDao;
import com.tocersoft.professional.dto.ProfessionalCondition;
import com.tocersoft.professional.entity.Professional;
import com.tocersoft.professional.service.IProfessionalService;
import com.tocersoft.system.dao.ISysDictDao;
import com.tocersoft.system.entity.SysDict;
import com.tocersoft.system.entity.SysDictItem;
import com.tocersoft.system.service.ISysDictItemService;
import com.tocersoft.system.service.impl.ISysDictServiceImpl;


@Service("professionalServiceImpl")
@Transactional(value = "transactionManager")
public class ProfessionalServiceImpl implements IProfessionalService{

	@Resource(name = "professionalDaoImpl")
	private IProfessionalDao professionalDao;
	@Resource(name = "sysDictItemServiceImpl")
	private ISysDictItemService sysdictitemservice;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listProfessionalByPage(PageResult<Professional> pageResult,ProfessionalCondition condition){
		try {
		int rows = professionalDao.listProfessionalByPageCount(condition,condition.getKeyname());
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<Professional> list = professionalDao.listProfessionalByPage(rowBounds,condition);
		//工作语言
		for (int i = 0; i < list.size(); i++) {
			Professional pro = list.get(i);
			if(pro.getLanguage() == null){
				continue;
			}
			 String[] languageArray = pro.getLanguage().split(",");
			 String languageStr = "";
			 for (int j = 0; j < languageArray.length; j++) {
				 SysDictItem sysdictitem = sysdictitemservice.getSysDictItemById(languageArray[j].trim());
				 if(sysdictitem!=null){
					 languageStr += sysdictitem.getName()+",";
				 }
			 }
			 if(languageStr != ""){
				 pro.setLanguageName(languageStr.substring(0,languageStr.length()-1));
			 }
		}
		//资格类型
		for (int i = 0; i < list.size(); i++) {
			Professional pro = list.get(i);
			if(pro.getQualification() == null){
				continue;
			}
			 String[] qualificationArray = pro.getQualification().split(",");
			 String qualificationStr = "";
			 for (int j = 0; j < qualificationArray.length; j++) {
				 SysDictItem sysdictitem = sysdictitemservice.getSysDictItemById(qualificationArray[j].trim());
				 if(sysdictitem != null){
					 qualificationStr += sysdictitem.getName()+",";
				 }
			 }
			 if(qualificationStr != ""){
				 pro.setQualificationName(qualificationStr.substring(0,qualificationStr.length()-1));
			 }
		}
		pageResult.setResult(list);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 专业人员表
	 */
	public Professional getProfessionalById(String id){
		return professionalDao.getProfessionalById(id);
	}

	/**
	 * 新增
	 * @param item 专业人员表
	 */
	public void add(Professional item){
		professionalDao.add(item);
	}

	/**
	 * 修改
	 * @param item 专业人员表
	 */
	public void update(Professional item){
		professionalDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		professionalDao.delByIds(ids);
	}
	
	/**
	 * 点击上一页显示专业人员详情
	 * 根据创建时间查询
	 */
	public Professional getProProfessionalByTime(String selIds){
		Professional professional = professionalDao.getProProfessionalByTime(selIds);
		return professional;
	}
	/**
	 * 点击下一页显示专业人员详情
	 * 根据创建时间查询
	 */
	public Professional getNextProfessionalByTime(String selIds){
		Professional professional = professionalDao.getNextProProfessionalByTime(selIds);
		return professional;
	}

	@Override
	public List<Professional> listProfessionalLvShiId() {
		return professionalDao.listProfessionalLvShiId();
	}

	@Override
	public List<Professional> listProfessionalSort(String sort) {
		// TODO Auto-generated method stub
		return professionalDao.listProfessionalSort(sort);
	}
	

}

