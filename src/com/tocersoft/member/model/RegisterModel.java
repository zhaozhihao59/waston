package com.tocersoft.member.model;

import com.tocersoft.member.entity.Member;

public class RegisterModel {
	
	/** 注册时验证帐号是否唯一 */
	private String account;
	
	/** 用于存放会员信息 */
	private Member member;
	
	/** 验证码 */
	private String verCode;
	
	/** ID */
	private Integer id;
	
	/** 当前年份 */
	private Integer nowYear;
	
//	/** 数据字典集合 - 币种 */
//	private List<SysDataset> currency;
//	
//	/** 数据字典集合 - 服务行业 */
//	private List<SysDataset> industry;
//	
//	/** 数据字典集合 - 机构业务类型 */
//	private List<SysDataset> serviceType;
//	
//	/** 数据字典集合 - 第三方服务 */
//	private List<SysDataset> serviceContent;
//	
//	/** 获取国家 */
//	private List<SysRegion> countries;
//	
//	/** 获取城市 - 中国 */
//	private Map<String, List<SysRegion>> citiesCN;
//	
//	/** 获取城市 - 其他 */
//	private Map<String, List<SysRegion>> citiesOther;
	
	/** 字母序号 */
	private String[] letterStr = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
			"L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W",
			"X", "Y", "Z" };
	
	/** 注册第二步 - 详细信息 */
//	private Enterprise enterprise;
	
	/** 请求带的随机数 */
	private String m;
	
	/** 外包类型 */
//	private List<SysDataset> sourcingList;
	
	/** 选中项 - 发包商 外包类型 */
	private String sourcingListStr1;
	/** 选中项 - 发包商 服务行业 */
	private String industryStr1;
	/** 选中项 - 收包商 外包类型 */
	private String sourcingListStr2;
	/** 选中项 - 收包商 服务行业 */
	private String industryStr2;
	/** 选中项 - 机构业务类型 */
	private String serviceTypeStr;
	/** 选中项 - 机构业务内容 */
	private String serviceContentStr;
	
	/** 其他项 - 服务行业 */
	private String industryOther1;
	/** 其他项 - 服务行业 */
	private String industryOther2;
	/** 其他项 - 服务内容 */
	private String serviceContentOther;
	
	/** 图片路径 */
	private String path;
	
	/** 企业地点分布 */
//	private List<EnterpriseComplace> placeList;
//	/** 企业人员 */
//	private List<EnterpriseEdu> eduList;
//	/** 企业简介 */
//	private EnterpriseIntro intro;
//	/** 企业证书 */
//	private EnterpriseCert cert;
//	/** 企业证书 */
//	private List<EnterpriseCert> certList;
//	/** 项目类型 */
//	private List<ProjectCat> projectCatList;
	/** 项目类型 */
	private String projectCatStr;
	
	/** 表明是从登录过来的 1-从登录过来 */
	private Integer fromLogin;
	
	private Integer cpNum;
	private Integer cpType;
	/** 证书类型 JSON */
	private String certTypeJSON;
	/** 组织机构代码证第一段*/
	private String cardNum1;
	/** 组织机构代码证第二段*/
	private String cardNum2;
	
	public Integer getCpNum() {
		return cpNum;
	}

	public void setCpNum(Integer cpNum) {
		this.cpNum = cpNum;
	}

	public Integer getCpType() {
		return cpType;
	}

	public void setCpType(Integer cpType) {
		this.cpType = cpType;
	}

	public Integer getFromLogin() {
		return fromLogin;
	}

	public void setFromLogin(Integer fromLogin) {
		this.fromLogin = fromLogin;
	}

//	public EnterpriseCert getCert() {
//		return cert;
//	}
//
//	public void setCert(EnterpriseCert cert) {
//		this.cert = cert;
//	}
//
//	public List<EnterpriseEdu> getEduList() {
//		return eduList;
//	}
//
//	public void setEduList(List<EnterpriseEdu> eduList) {
//		this.eduList = eduList;
//	}

	public String getM() {
		return m;
	}

	public void setM(String m) {
		this.m = m;
	}

//	public Enterprise getEnterprise() {
//		return enterprise;
//	}
//
//	public void setEnterprise(Enterprise enterprise) {
//		this.enterprise = enterprise;
//	}

	public String[] getLetterStr() {
		return letterStr;
	}

	public void setLetterStr(String[] letterStr) {
		this.letterStr = letterStr;
	}

//	public Map<String, List<SysRegion>> getCitiesCN() {
//		return citiesCN;
//	}
//
//	public void setCitiesCN(Map<String, List<SysRegion>> citiesCN) {
//		this.citiesCN = citiesCN;
//	}
//
//	public Map<String, List<SysRegion>> getCitiesOther() {
//		return citiesOther;
//	}
//
//	public void setCitiesOther(Map<String, List<SysRegion>> citiesOther) {
//		this.citiesOther = citiesOther;
//	}
//
//	public List<SysRegion> getCountries() {
//		return countries;
//	}
//
//	public void setCountries(List<SysRegion> countries) {
//		this.countries = countries;
//	}
//
//	public List<SysDataset> getCurrency() {
//		return currency;
//	}
//
//	public void setCurrency(List<SysDataset> currency) {
//		this.currency = currency;
//	}

	public Integer getNowYear() {
		return nowYear;
	}

	public void setNowYear(Integer nowYear) {
		this.nowYear = nowYear;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

//	public Member getMember() {
//		return member;
//	}
//
//	public void setMember(Member member) {
//		this.member = member;
//	}

	public String getVerCode() {
		return verCode;
	}

	public void setVerCode(String verCode) {
		this.verCode = verCode;
	}

//	public List<SysDataset> getSourcingList() {
//		return sourcingList;
//	}
//
//	public void setSourcingList(List<SysDataset> sourcingList) {
//		this.sourcingList = sourcingList;
//	}
//
//	public List<SysDataset> getIndustry() {
//		return industry;
//	}
//
//	public void setIndustry(List<SysDataset> industry) {
//		this.industry = industry;
//	}


//	public List<SysDataset> getServiceType() {
//		return serviceType;
//	}
//
//	public void setServiceType(List<SysDataset> serviceType) {
//		this.serviceType = serviceType;
//	}
//
//
//	public List<SysDataset> getServiceContent() {
//		return serviceContent;
//	}
//
//	public void setServiceContent(List<SysDataset> serviceContent) {
//		this.serviceContent = serviceContent;
//	}

	public String getServiceTypeStr() {
		return serviceTypeStr;
	}

	public void setServiceTypeStr(String serviceTypeStr) {
		this.serviceTypeStr = serviceTypeStr;
	}

	public String getServiceContentStr() {
		return serviceContentStr;
	}

	public void setServiceContentStr(String serviceContentStr) {
		this.serviceContentStr = serviceContentStr;
	}


	public String getSourcingListStr1() {
		return sourcingListStr1;
	}

	public void setSourcingListStr1(String sourcingListStr1) {
		this.sourcingListStr1 = sourcingListStr1;
	}

	public String getIndustryStr1() {
		return industryStr1;
	}

	public void setIndustryStr1(String industryStr1) {
		this.industryStr1 = industryStr1;
	}

	public String getSourcingListStr2() {
		return sourcingListStr2;
	}

	public void setSourcingListStr2(String sourcingListStr2) {
		this.sourcingListStr2 = sourcingListStr2;
	}

	public String getIndustryStr2() {
		return industryStr2;
	}

	public void setIndustryStr2(String industryStr2) {
		this.industryStr2 = industryStr2;
	}

	public String getIndustryOther1() {
		return industryOther1;
	}

	public void setIndustryOther1(String industryOther1) {
		this.industryOther1 = industryOther1;
	}

	public String getIndustryOther2() {
		return industryOther2;
	}

	public void setIndustryOther2(String industryOther2) {
		this.industryOther2 = industryOther2;
	}

	public String getServiceContentOther() {
		return serviceContentOther;
	}

	public void setServiceContentOther(String serviceContentOther) {
		this.serviceContentOther = serviceContentOther;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

//	public List<EnterpriseComplace> getPlaceList() {
//		return placeList;
//	}
//
//	public void setPlaceList(List<EnterpriseComplace> placeList) {
//		this.placeList = placeList;
//	}
//
//	public EnterpriseIntro getIntro() {
//		return intro;
//	}
//
//	public void setIntro(EnterpriseIntro intro) {
//		this.intro = intro;
//	}
//
//	public List<ProjectCat> getProjectCatList() {
//		return projectCatList;
//	}
//
//	public void setProjectCatList(List<ProjectCat> projectCatList) {
//		this.projectCatList = projectCatList;
//	}

	public String getProjectCatStr() {
		return projectCatStr;
	}

	public void setProjectCatStr(String projectCatStr) {
		this.projectCatStr = projectCatStr;
	}

	public String getCertTypeJSON() {
		return certTypeJSON;
	}

	public void setCertTypeJSON(String certTypeJSON) {
		this.certTypeJSON = certTypeJSON;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public List<EnterpriseCert> getCertList() {
//		return certList;
//	}
//
//	public void setCertList(List<EnterpriseCert> certList) {
//		this.certList = certList;
//	}

	public String getCardNum1() {
		return cardNum1;
	}

	public void setCardNum1(String cardNum1) {
		this.cardNum1 = cardNum1;
	}

	public String getCardNum2() {
		return cardNum2;
	}

	public void setCardNum2(String cardNum2) {
		this.cardNum2 = cardNum2;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
}
