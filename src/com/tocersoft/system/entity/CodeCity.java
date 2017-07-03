package com.tocersoft.system.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 原地址代码
 * @creator 方泉
 * 
 */
public class CodeCity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6373513379760444798L;

	/**
	 * 主键
	 */
	private Integer idx;
	
	/**
	 * 城市
	 */
	private String city;
	
	/**
	 * 城市下的区县
	 */
	private List<CodeAddress> addrs;

	
	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<CodeAddress> getAddrs() {
		return addrs;
	}

	public void setAddrs(List<CodeAddress> addrs) {
		this.addrs = addrs;
	}

}
