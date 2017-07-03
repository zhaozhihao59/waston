package com.tocersoft.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tocersoft.system.entity.CodeAddress;
/**
 * 会员登录DAO
 * @creator     zhangqiang
 * @create-time Sep 13, 2012   12:48:31 PM
 * @version 0.1
 */
@Repository("codeAddressDaoImpl")
public interface ICodeAddressDao {
	
	/**
	 * 根据城市ID查询得到该城市所有区县
	 * 
	 * @return List
	 * 
	 */
	List<CodeAddress> ListAddrByCid(@Param("cid")Integer cid);
	
	/**
	 * 根据省 市 区查询详情
	 * @param procince
	 * @param city
	 * @param district
	 * @return
	 */
	List<CodeAddress> selProvinceCityDistrict(@Param("procince")String procince,@Param("city")String city,@Param("district")String district);
}
