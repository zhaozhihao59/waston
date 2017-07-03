package com.tocersoft.professional.model;

import java.util.ArrayList;
import java.util.List;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.professional.dto.ProfessionalCondition;
import com.tocersoft.professional.entity.Professional;
import com.tocersoft.system.entity.SysDictItem;

public class ProfessionalModel extends BaseModel<Professional>{

	private Professional item = new Professional();

	private ProfessionalCondition condition = new ProfessionalCondition();
	
	private List<Professional> professional = new ArrayList<Professional>();
	
	private List<SysDictItem> languageList = new ArrayList<SysDictItem>();
	//ok
	private List<SysDictItem> languageListEn = new ArrayList<SysDictItem>();    
	private List<SysDictItem> languageListJp = new ArrayList<SysDictItem>();    
	private List<SysDictItem> qualificationList = new ArrayList<SysDictItem>();
	//查询数据字典项
	private List<SysDictItem> sysDictItemList = new ArrayList<SysDictItem>();
	//专业
	private List<SysDictItem> businessList = new ArrayList<SysDictItem>();
	//ok
	private List<SysDictItem> qualificationListEn = new ArrayList<SysDictItem>();
	//查询数据字典项
	private List<SysDictItem> sysDictItemListEn = new ArrayList<SysDictItem>();
	//专业
	private List<SysDictItem> businessListEn = new ArrayList<SysDictItem>();
	//ok
	private List<SysDictItem> qualificationListJp = new ArrayList<SysDictItem>();
	//查询数据字典项
	private List<SysDictItem> sysDictItemListJp = new ArrayList<SysDictItem>();
	//专业
	private List<SysDictItem> businessListJp = new ArrayList<SysDictItem>();
	//排序
	private int page;
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<SysDictItem> getLanguageListEn() {
		return languageListEn;
	}

	public void setLanguageListEn(List<SysDictItem> languageListEn) {
		this.languageListEn = languageListEn;
	}

	public List<SysDictItem> getLanguageListJp() {
		return languageListJp;
	}

	public void setLanguageListJp(List<SysDictItem> languageListJp) {
		this.languageListJp = languageListJp;
	}

	public List<SysDictItem> getQualificationListEn() {
		return qualificationListEn;
	}

	public void setQualificationListEn(List<SysDictItem> qualificationListEn) {
		this.qualificationListEn = qualificationListEn;
	}

	public List<SysDictItem> getSysDictItemListEn() {
		return sysDictItemListEn;
	}

	public void setSysDictItemListEn(List<SysDictItem> sysDictItemListEn) {
		this.sysDictItemListEn = sysDictItemListEn;
	}

	public List<SysDictItem> getBusinessListEn() {
		return businessListEn;
	}

	public void setBusinessListEn(List<SysDictItem> businessListEn) {
		this.businessListEn = businessListEn;
	}

	public List<SysDictItem> getQualificationListJp() {
		return qualificationListJp;
	}

	public void setQualificationListJp(List<SysDictItem> qualificationListJp) {
		this.qualificationListJp = qualificationListJp;
	}

	public List<SysDictItem> getSysDictItemListJp() {
		return sysDictItemListJp;
	}

	public void setSysDictItemListJp(List<SysDictItem> sysDictItemListJp) {
		this.sysDictItemListJp = sysDictItemListJp;
	}

	public List<SysDictItem> getBusinessListJp() {
		return businessListJp;
	}

	public void setBusinessListJp(List<SysDictItem> businessListJp) {
		this.businessListJp = businessListJp;
	}

	public List<SysDictItem> getBusinessList() {
		return businessList;
	}

	public void setBusinessList(List<SysDictItem> businessList) {
		this.businessList = businessList;
	}

	public List<SysDictItem> getQualificationList() {
		return qualificationList;
	}

	public void setQualificationList(List<SysDictItem> qualificationList) {
		this.qualificationList = qualificationList;
	}

	public List<SysDictItem> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(List<SysDictItem> languageList) {
		this.languageList = languageList;
	}

	public ProfessionalModel() {
		super();
	}

	public Professional getItem() {
		return item;
	}

	public void setItem(Professional item) {
		this.item = item;
	}

	public ProfessionalCondition getCondition() {
		return condition;
	}

	public void setCondition(ProfessionalCondition condition) {
		this.condition = condition;
	}

	public List<SysDictItem> getSysDictItemList() {
		return sysDictItemList;
	}

	public void setSysDictItemList(List<SysDictItem> sysDictItemList) {
		this.sysDictItemList = sysDictItemList;
	}

	public List<Professional> getProfessional() {
		return professional;
	}

	public void setProfessional(List<Professional> professional) {
		this.professional = professional;
	}
	
	

}
