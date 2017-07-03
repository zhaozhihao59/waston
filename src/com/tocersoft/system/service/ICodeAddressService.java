package com.tocersoft.system.service;

import java.util.List;

import com.tocersoft.system.entity.CodeAddress;
import com.tocersoft.system.entity.CodeCity;
import com.tocersoft.system.entity.CodeProvince;


public interface ICodeAddressService {

	/**
	 * 根据CodeAddress表查询得到所有省
	 * 
	 * @return List
	 * 
	 */
	List<CodeProvince> ListAllProvince();
	
	/**
	 * 根据省份ID查询得到该省份所有城市
	 * 
	 * @return List
	 * 
	 */
	List<CodeCity> ListCitiesByPid(Integer pid);
	
	/**
	 * 根据城市ID查询得到该城市所有区县
	 * 
	 * @return List
	 * 
	 */
	List<CodeAddress> ListAddrByCid(Integer cid);
	
	/**
	 * 根据productCode查找省份信息
	 * @return CodeProvince
	 * 
	 */
	CodeProvince getProvinceByCode(Integer pid);
	
	/**
	 * 根据cityCode查找城市信息
	 * @return CodeCity
	 * 
	 */
	CodeCity getCityByCode(Integer cid);
	
	/**
	 * 根据省 市 区 查询详细信息
	 * @param province
	 * @param city
	 * @param district
	 * @return
	 */
	List<CodeAddress> selProvinceCityDistrict(String province,String city,String district);
}
