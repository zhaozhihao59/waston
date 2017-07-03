package com.tocersoft.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.system.dao.ICodeAddressDao;
import com.tocersoft.system.dao.ICodeCityDao;
import com.tocersoft.system.dao.ICodeProvinceDao;
import com.tocersoft.system.entity.CodeAddress;
import com.tocersoft.system.entity.CodeCity;
import com.tocersoft.system.entity.CodeProvince;
import com.tocersoft.system.service.ICodeAddressService;


@Service
@Transactional(value = "transactionManager")
public class CodeAddressServiceImpl implements ICodeAddressService{
	
	@Resource(name="codeAddressDaoImpl")
	private ICodeAddressDao codeAddressDao;
	
	@Resource(name="codeCityDaoImpl")
	private ICodeCityDao codeCityDao;
	
	@Resource(name="codeProvinceDaoImpl")
	private ICodeProvinceDao codeProvinceDao;

	@Override
	public List<CodeProvince> ListAllProvince(){
		return codeProvinceDao.ListAllProvince();
	}

	@Override
	public List<CodeAddress> ListAddrByCid(Integer cid) {
		return codeAddressDao.ListAddrByCid(cid);
	}

	@Override
	public List<CodeCity> ListCitiesByPid(Integer pid) {
		return codeCityDao.ListCitiesByPid(pid);
	}

	@Override
	public CodeCity getCityByCode(Integer cid) {
		return codeCityDao.getCityByCode(cid);
	}

	@Override
	public CodeProvince getProvinceByCode(Integer pid) {
		return codeProvinceDao.getProvinceByCode(pid);
	}

	@Override
	public List<CodeAddress> selProvinceCityDistrict(String province, String city,
			String district) {
		List<CodeAddress> list = codeAddressDao.selProvinceCityDistrict(province,city,district);
		return list;
	}

}
