package com.tocersoft.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tocersoft.system.entity.CodeCity;

/**
 * 会员登录DAO
 * @creator     zhangqiang
 * @create-time Sep 13, 2012   12:48:31 PM
 * @version 0.1
 */
@Repository("codeCityDaoImpl")
public interface ICodeCityDao {
	
	/**
	 * 根据省份ID查询得到该省份所有城市
	 * 
	 * @return List
	 * 
	 */
	List<CodeCity> ListCitiesByPid(@Param("pid")Integer pid);
	
	/**
	 * 根据cid查询城市信息
	 * @param cid
	 * @return
	 */
	CodeCity getCityByCode(@Param("cid")Integer cid);
	
}
