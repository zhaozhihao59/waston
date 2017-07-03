package com.tocersoft.member.entity;
import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 
 * 
 * @creator
 * @create-time 2014-03-18 11:06:59
 */
public class MemAddress extends BaseBusEntity {
	private static final long serialVersionUID = 1L;
	
	/** 收货联系人 */
	private String linkman;
	/** 收货联系方式 */
	private String contact;
	 
	/** 收货地址 */
	private String address;
	/** 地址状态值:0-正常 1-默认收货地址 */ 
	private Integer addressStatus;
	/** 所属会员 */
	private String memberId;
	
	/** 省 */
	private String province;
	/** 省ID */
	private String provinceId;
	/** 市 */
	private String city;
	/** 市ID */
	private String cityId;
	/** 省市*/
	private String provinceCity;
	/** 区ID */
	private String districtId;
	/** 区 */
	private String district;
	/** 邮编 */
	private String postCode;
	
	/** 手机号码 */
	private String mobile;
	
	public MemAddress() {
		super();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Integer getAddressStatus() {
		return addressStatus;
	}

	public void setAddressStatus(Integer addressStatus) {
		this.addressStatus = addressStatus;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getProvinceCity() {
		return provinceCity;
	}

	public void setProvinceCity(String provinceCity) {
		this.provinceCity = provinceCity;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}