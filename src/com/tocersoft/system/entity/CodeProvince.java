package com.tocersoft.system.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 原城市代码
 * @creator 方泉
 * 
 */

public class CodeProvince implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2985825590786686146L;

	/**
	 * 主键
	 */
	private Integer idx;
	
	/**
	 * 省
	 */
	private String province;
	
	/**
	 * 省份下的城市
	 */
	private List<CodeCity> cities;
	

	public List<CodeCity> getCities() {
		return cities;
	}

	public void setCities(List<CodeCity> cities) {
		this.cities = cities;
	}

	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
}
