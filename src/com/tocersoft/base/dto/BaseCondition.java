package com.tocersoft.base.dto;

/**
 * 查询条件类 基类 所有查询类都应该继承该类
 * @author lenovo
 *
 */
public class BaseCondition {

	/**
	 * 是否包含转移符
	 * 如果查询条件中 包含like，这需要将此值设为true，否则false
	 */
	private boolean escapeSymbol = false;
	
	public boolean isEscapeSymbol() {
		return escapeSymbol;
	}

	public void setEscapeSymbol(boolean escapeSymbol) {
		this.escapeSymbol = escapeSymbol;
	}
}
