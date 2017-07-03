package com.tocersoft.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tocersoft.system.entity.CodeProvince;


/**
 * 会员登录DAO
 * @creator     zhangqiang
 * @create-time Sep 13, 2012   12:48:31 PM
 * @version 0.1
 */
@Repository("codeProvinceDaoImpl")
public interface ICodeProvinceDao {
	
	/**
	 * 根据CodeAddress表查询得到所有省
	 * 
	 * @return List
	 * 
	 */
	List<CodeProvince> ListAllProvince();
	
	/**
	 * 根据pid查询省份信息
	 * @param pid
	 * @return
	 */
	CodeProvince getProvinceByCode(@Param("pid")Integer pid);
	
}
