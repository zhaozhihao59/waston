package com.tocersoft.product.entity;
import java.util.Date;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 汇率表
 * 
 * @creator
 * @create-time 2014-08-19 11:40:43
 */
public class ExchangeRate extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 人民币 */
	private double rmb;
	/** 对新西兰币汇率 */
	private double nzdRate;

	/** 人民币 */
	public double getRmb(){
		return this.rmb;
	}

	/** 人民币 */
	public void setRmb(double rmb){
		this.rmb = rmb;
	}
	/** 对新西兰币汇率 */
	public double getNzdRate(){
		return this.nzdRate;
	}

	/** 对新西兰币汇率 */
	public void setNzdRate(double nzdRate){
		this.nzdRate = nzdRate;
	}
}