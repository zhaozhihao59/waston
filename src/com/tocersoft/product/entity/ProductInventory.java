package com.tocersoft.product.entity;
import java.util.Date;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 产品库存表
 * 
 * @creator
 * @create-time 2014-06-25 20:15:54
 */
public class ProductInventory extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 对应的产品ID */
	private String productId;
	/** 备货地 */
	private String inventoryLocation;
	/** 原备货量 */
	private Long inventoryOriQty;
	/** 备货数量 */
	private Long inventoryQty;
	/** 是否有备货：0否，1是 */
	private Long inventoryStatus;

	/** 对应的产品ID */
	public String getProductId(){
		return this.productId;
	}

	/** 对应的产品ID */
	public void setProductId(String productId){
		this.productId = productId;
	}
	/** 备货地 */
	public String getInventoryLocation(){
		return this.inventoryLocation;
	}

	/** 备货地 */
	public void setInventoryLocation(String inventoryLocation){
		this.inventoryLocation = inventoryLocation;
	}
	/** 原备货量 */
	public Long getInventoryOriQty(){
		return this.inventoryOriQty;
	}

	/** 原备货量 */
	public void setInventoryOriQty(Long inventoryOriQty){
		this.inventoryOriQty = inventoryOriQty;
	}
	/** 备货数量 */
	public Long getInventoryQty(){
		return this.inventoryQty;
	}

	/** 备货数量 */
	public void setInventoryQty(Long inventoryQty){
		this.inventoryQty = inventoryQty;
	}
	/** 是否有备货：0否，1是 */
	public Long getInventoryStatus(){
		return this.inventoryStatus;
	}

	/** 是否有备货：0否，1是 */
	public void setInventoryStatus(Long inventoryStatus){
		this.inventoryStatus = inventoryStatus;
	}
}