package com.tocersoft.system.entity;

import java.io.Serializable;

/**
 * 原地址代码
 * @creator 方泉
 * 
 */
public class CodeAddress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8546972351475437594L;

	/**
	 * 主键
	 */
	private Integer idx;
	
	/**
	 * 省
	 */
	private String sido;
	
	/**
	 * 市
	 */
	private String gugun;
	
	/**
	 * 区
	 */
	private String dong;
	
	private Integer sidoIdx;
	
	private Integer gugunIdx;

	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getGugun() {
		return gugun;
	}

	public void setGugun(String gugun) {
		this.gugun = gugun;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public Integer getSidoIdx() {
		return sidoIdx;
	}

	public void setSidoIdx(Integer sidoIdx) {
		this.sidoIdx = sidoIdx;
	}

	public Integer getGugunIdx() {
		return gugunIdx;
	}

	public void setGugunIdx(Integer gugunIdx) {
		this.gugunIdx = gugunIdx;
	}

}
