package com.tocersoft.product.dto;

import com.tocersoft.base.dto.BaseCondition;

public class ProductCategoryAttributeCondition extends BaseCondition {

	/** 类别ID */
	private String categoryId;
	/** 是否购买属性（1：是购买属性，0：非购买属性） */
	private String buyAttr;
	/** 叶子发布类目id */
	private String catePubId;
	/** 属性的属性值是否可以自定义修改 1：是 0：否 （只对多选、单选有效） */
	private String defined;
	/** 是否是品牌属性（1：是，0：不是） */
	private String isbrand;
	/** 是否有other属性（1：是，0：否） */
	private String isother;
	/** 属性英文名称 */
	private String lineAttrName;
	/** 属性中文名称 */
	private String lineAttrNameCn;
	/** 是否定位属性（1：定位属性，0：非定位属性）定位属性不可能是销售或购买属性且卖家不可更改 */
	private String located;
	/** 是否必填 （1：必填项， 0：非必填项） */
	private String required;
	/** 是否销售属性（1：是销售属性，0：非销售属性） */
	private String saleAttr;
	/** 样式 1：文本 2：图片 3：文本 */
	private String style;
	/** 形式 1：多选框 2：下拉框 4：字符型输入框 5：数值型输入框 */
	private String type;
	/** 是否有效（1：有效，0：无效） */
	private String valid;

	/** 是否购买属性（1：是购买属性，0：非购买属性） */
	public String getBuyAttr(){
		return this.buyAttr;
	}

	/** 是否购买属性（1：是购买属性，0：非购买属性） */
	public void setBuyAttr(String buyAttr){
		this.buyAttr = buyAttr;
	}
	/** 叶子发布类目id */
	public String getCatePubId(){
		return this.catePubId;
	}

	/** 叶子发布类目id */
	public void setCatePubId(String catePubId){
		this.catePubId = catePubId;
	}
	/** 属性的属性值是否可以自定义修改 1：是 0：否 （只对多选、单选有效） */
	public String getDefined(){
		return this.defined;
	}

	/** 属性的属性值是否可以自定义修改 1：是 0：否 （只对多选、单选有效） */
	public void setDefined(String defined){
		this.defined = defined;
	}
	/** 是否是品牌属性（1：是，0：不是） */
	public String getIsbrand(){
		return this.isbrand;
	}

	/** 是否是品牌属性（1：是，0：不是） */
	public void setIsbrand(String isbrand){
		this.isbrand = isbrand;
	}
	/** 是否有other属性（1：是，0：否） */
	public String getIsother(){
		return this.isother;
	}

	/** 是否有other属性（1：是，0：否） */
	public void setIsother(String isother){
		this.isother = isother;
	}
	/** 属性英文名称 */
	public String getLineAttrName(){
		return this.lineAttrName;
	}

	/** 属性英文名称 */
	public void setLineAttrName(String lineAttrName){
		this.lineAttrName = lineAttrName;
	}
	/** 属性中文名称 */
	public String getLineAttrNameCn(){
		return this.lineAttrNameCn;
	}

	/** 属性中文名称 */
	public void setLineAttrNameCn(String lineAttrNameCn){
		this.lineAttrNameCn = lineAttrNameCn;
	}
	/** 是否定位属性（1：定位属性，0：非定位属性）定位属性不可能是销售或购买属性且卖家不可更改 */
	public String getLocated(){
		return this.located;
	}

	/** 是否定位属性（1：定位属性，0：非定位属性）定位属性不可能是销售或购买属性且卖家不可更改 */
	public void setLocated(String located){
		this.located = located;
	}
	/** 是否必填 （1：必填项， 0：非必填项） */
	public String getRequired(){
		return this.required;
	}

	/** 是否必填 （1：必填项， 0：非必填项） */
	public void setRequired(String required){
		this.required = required;
	}
	/** 是否销售属性（1：是销售属性，0：非销售属性） */
	public String getSaleAttr(){
		return this.saleAttr;
	}

	/** 是否销售属性（1：是销售属性，0：非销售属性） */
	public void setSaleAttr(String saleAttr){
		this.saleAttr = saleAttr;
	}
	/** 样式 1：文本 2：图片 3：文本 */
	public String getStyle(){
		return this.style;
	}

	/** 样式 1：文本 2：图片 3：文本 */
	public void setStyle(String style){
		this.style = style;
	}
	/** 形式 1：多选框 2：下拉框 4：字符型输入框 5：数值型输入框 */
	public String getType(){
		return this.type;
	}

	/** 形式 1：多选框 2：下拉框 4：字符型输入框 5：数值型输入框 */
	public void setType(String type){
		this.type = type;
	}
	/** 是否有效（1：有效，0：无效） */
	public String getValid(){
		return this.valid;
	}

	/** 是否有效（1：有效，0：无效） */
	public void setValid(String valid){
		this.valid = valid;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
}
