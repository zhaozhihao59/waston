package com.tocersoft.order.model;

import java.util.ArrayList;
import java.util.List;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.order.dto.OrderCartItemCondition;
import com.tocersoft.order.entity.OrderCartItem;
import com.tocersoft.product.entity.Product;

public class OrderCartItemModel extends BaseModel<OrderCartItem>{

	private OrderCartItem item = new OrderCartItem();

	private OrderCartItemCondition condition = new OrderCartItemCondition();
	
	/** 产品ID */
	private String productId;
	
	/** 购物车明细项ID */
	private String itemId;
	
	/** 加入购物车数量 */
	private Double cartNum;
	
	/** 购物车内的明细集合 */
	private List<OrderCartItem> cartItemList = new ArrayList<OrderCartItem>();

	/** 购物车内商品的总金额 */
	private Double totalPrice;
	
	/** 选中的购物车明细 */
	private String cartItemChoose;
	
	/** 明星产品集合 */
	private List<Product> starProducts = new ArrayList<Product>();
	
	public List<OrderCartItem> getCartItemList() {
		return cartItemList;
	}

	public void setCartItemList(List<OrderCartItem> cartItemList) {
		this.cartItemList = cartItemList;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Double getCartNum() {
		return cartNum;
	}

	public void setCartNum(Double cartNum) {
		this.cartNum = cartNum;
	}

	public OrderCartItemModel() {
		super();
	}

	public OrderCartItem getItem() {
		return item;
	}

	public void setItem(OrderCartItem item) {
		this.item = item;
	}

	public OrderCartItemCondition getCondition() {
		return condition;
	}

	public void setCondition(OrderCartItemCondition condition) {
		this.condition = condition;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getCartItemChoose() {
		return cartItemChoose;
	}

	public void setCartItemChoose(String cartItemChoose) {
		this.cartItemChoose = cartItemChoose;
	}

	public List<Product> getStarProducts() {
		return starProducts;
	}

	public void setStarProducts(List<Product> starProducts) {
		this.starProducts = starProducts;
	}

}
