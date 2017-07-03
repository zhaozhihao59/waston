package com.tocersoft.activity.entity;
import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 活动表
 * 
 * @creator
 * @create-time 2015-04-22 17:47:03
 */
public class Activity extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 会议标题 */
	private String title;
	/**会议标题【英文】*/
	private String titleEn;
	/**会议标题【日文】*/
	private String titleJp;
	/** 会议图片 */
	private String photo;
	/** 会议简介 */
	private String desc;
	/**会议简介【英文】*/
	private String descEn;
	/**会议简介【日文】*/
	private String descJp;
	/** 会议时间(字符串输入) */
	private String dateStr;
	private String dateStrEn;
	private String dateStrJp;
	/** 活动地址 */
	private String address;
	/**活动地址【英文】*/
	private String addressEn;
	/**活动地址【日文】*/
	private String addressJp;
	/** 活动状态1预告2正在报名3历届 */
	private Integer state;
	/** 活动推荐：0-未推荐，1-已推荐 */
	private Integer isRecommend;
	/** 排序 */
	private Integer sort;
	/** 摘要 */
	private String prom;
	/**摘要【英文】*/
	private String promEn;
	/**摘要【日文】*/
	private String promJp;

	public String getDateStrEn() {
		return dateStrEn;
	}

	public void setDateStrEn(String dateStrEn) {
		this.dateStrEn = dateStrEn;
	}

	public String getDateStrJp() {
		return dateStrJp;
	}

	public void setDateStrJp(String dateStrJp) {
		this.dateStrJp = dateStrJp;
	}

	public String getTitleEn() {
		return titleEn;
	}

	public void setTitleEn(String titleEn) {
		this.titleEn = titleEn;
	}

	public String getTitleJp() {
		return titleJp;
	}

	public void setTitleJp(String titleJp) {
		this.titleJp = titleJp;
	}

	public String getDescEn() {
		return descEn;
	}

	public void setDescEn(String descEn) {
		this.descEn = descEn;
	}

	public String getDescJp() {
		return descJp;
	}

	public void setDescJp(String descJp) {
		this.descJp = descJp;
	}

	public String getAddressEn() {
		return addressEn;
	}

	public void setAddressEn(String addressEn) {
		this.addressEn = addressEn;
	}

	public String getAddressJp() {
		return addressJp;
	}

	public void setAddressJp(String addressJp) {
		this.addressJp = addressJp;
	}

	public String getPromEn() {
		return promEn;
	}

	public void setPromEn(String promEn) {
		this.promEn = promEn;
	}

	public String getPromJp() {
		return promJp;
	}

	public void setPromJp(String promJp) {
		this.promJp = promJp;
	}

	/** 会议标题 */
	public String getTitle(){
		return this.title;
	}

	/** 会议标题 */
	public void setTitle(String title){
		this.title = title;
	}
	/** 会议图片 */
	public String getPhoto(){
		return this.photo;
	}

	/** 会议图片 */
	public void setPhoto(String photo){
		this.photo = photo;
	}
	/** 会议简介 */
	public String getDesc(){
		return this.desc;
	}

	/** 会议简介 */
	public void setDesc(String desc){
		this.desc = desc;
	}
	/** 会议时间(字符串输入) */
	public String getDateStr(){
		return this.dateStr;
	}

	/** 会议时间(字符串输入) */
	public void setDateStr(String dateStr){
		this.dateStr = dateStr;
	}
	/** 活动地址 */
	public String getAddress(){
		return this.address;
	}

	/** 活动地址 */
	public void setAddress(String address){
		this.address = address;
	}
	/** 活动状态0预告1正在报名2报名结束3报名结束等待开始4进行中5已过期 */
	public Integer getState(){
		return this.state;
	}

	/** 活动状态0预告1正在报名3历届 */
	public void setState(Integer state){
		this.state = state;
	}
	/** 活动推荐：0-未推荐，1-已推荐 */
	public Integer getIsRecommend(){
		return this.isRecommend;
	}

	/** 活动推荐：0-未推荐，1-已推荐 */
	public void setIsRecommend(Integer isRecommend){
		this.isRecommend = isRecommend;
	}
	/** 排序 */
	public Integer getSort(){
		return this.sort;
	}

	/** 排序 */
	public void setSort(Integer sort){
		this.sort = sort;
	}
	/** 摘要 */
	public String getProm() {
		return prom;
	}
	/** 摘要 */
	public void setProm(String prom) {
		this.prom = prom;
	}
	
	
	
	
}