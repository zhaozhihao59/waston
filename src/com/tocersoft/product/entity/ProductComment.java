package com.tocersoft.product.entity;
import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 产品评论表
 * 
 * @creator
 * @create-time 2014-08-26 10:24:55
 */
public class ProductComment extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;
	/** 会员ID */
	private String memberId;
	/** 评论内容 */
	private String content;
	/** 评分 */
	private Integer mark;
	/** 产品ID */
	private String productId;
	
	/** 会员名称 不持久至数据库*/
	private String memberName;
	private String productName;
	
	/** 会员ID */
	public String getMemberId(){
		return this.memberId;
	}

	/** 会员ID */
	public void setMemberId(String memberId){
		this.memberId = memberId;
	}
	/** 评论内容 */
	public String getContent(){
		return this.content;
	}

	/** 评论内容 */
	public void setContent(String content){
		this.content = content;
	}
	/** 评分 */
	public Integer getMark(){
		return this.mark;
	}

	/** 评分 */
	public void setMark(Integer mark){
		this.mark = mark;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
}