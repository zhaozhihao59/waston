package com.tocersoft.order.dto;

import com.tocersoft.base.dto.BaseCondition;

public class FreightTemplateItemCondition extends BaseCondition {

	/** 运费模板ID */
	private String templateId;
	/** 到达城市列表：上海市，浙江省，江苏省，安徽省 */
	private String arrivalCityList;

	/** 运费模板ID */
	public String getTemplateId(){
		return this.templateId;
	}

	/** 运费模板ID */
	public void setTemplateId(String templateId){
		this.templateId = templateId;
	}
	/** 到达城市列表：上海市，浙江省，江苏省，安徽省 */
	public String getArrivalCityList(){
		return this.arrivalCityList;
	}

	/** 到达城市列表：上海市，浙江省，江苏省，安徽省 */
	public void setArrivalCityList(String arrivalCityList){
		this.arrivalCityList = arrivalCityList;
	}
}
