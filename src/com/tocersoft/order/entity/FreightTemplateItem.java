package com.tocersoft.order.entity;
import java.util.Date;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 运费模板表-明细
 * 
 * @creator
 * @create-time 2014-04-22 14:37:46
 */
public class FreightTemplateItem extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 运费模板ID */
	private String templateId;
	/** 到达城市列表：上海市，浙江省，江苏省，安徽省 */
	private String arrivalCityList;
	/** 首重价格 */
	private double firstWeightFee;
	/** 首重重量 */
	private double firstWeight;
	/** 续重价格 */
	private double continuedWeightFee;
	/** 续重重量 */
	private double continuedWeight;

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
	/** 首重价格 */
	public double getFirstWeightFee(){
		return this.firstWeightFee;
	}

	/** 首重价格 */
	public void setFirstWeightFee(double firstWeightFee){
		this.firstWeightFee = firstWeightFee;
	}
	/** 首重重量 */
	public double getFirstWeight(){
		return this.firstWeight;
	}

	/** 首重重量 */
	public void setFirstWeight(double firstWeight){
		this.firstWeight = firstWeight;
	}
	/** 续重价格 */
	public double getContinuedWeightFee(){
		return this.continuedWeightFee;
	}

	/** 续重价格 */
	public void setContinuedWeightFee(double continuedWeightFee){
		this.continuedWeightFee = continuedWeightFee;
	}
	/** 续重重量 */
	public double getContinuedWeight(){
		return this.continuedWeight;
	}

	/** 续重重量 */
	public void setContinuedWeight(double continuedWeight){
		this.continuedWeight = continuedWeight;
	}
}