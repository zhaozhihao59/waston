package com.tocersoft.base.listener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import com.tocersoft.base.utils.CommonUtil;
import com.tocersoft.base.utils.Constant;
import com.tocersoft.system.entity.CodeAddress;
import com.tocersoft.system.entity.CodeCity;
import com.tocersoft.system.entity.CodeProvince;
import com.tocersoft.system.service.ICodeAddressService;

/**
 * 系统初始化操作
 * @creator     zhangqiang
 * @create-time Jun 21, 2012   4:44:43 PM
 * @version 0.1
 */
@Component
public class InitDataListener implements
		ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired(required = false)
	private ServletContext servletContent;
	
	@Value("${sys.version}")
	private String sysVersion;
	
	@Autowired
	private ICodeAddressService codeAddressService;

	private Log log = LogFactory.getLog(InitDataListener.class);

	
	@SuppressWarnings("unchecked")
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		log.info("====== 系统数据初始化 ======");
		
		// 设置系统启动版本号
		if (StringUtils.isBlank(sysVersion)) {
			sysVersion = CommonUtil.formatDate(new Date(), "yyyyMMddHHmm");
		}
		
		//初始化时间区域
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
		
		if (servletContent != null) {
			// 初始化网站基本信息
			servletContent.setAttribute(Constant.SYS_VERSION, sysVersion);
		}
		
		// 查询全部市
		List<CodeProvince> provinces = codeAddressService.ListAllProvince();
		List<CodeProvince> CodeProvinces = new ArrayList<CodeProvince>();
		// 创建jsonArray数组
		JSONArray jsonArrayProvince = new JSONArray();
		for (int i = 0; i < provinces.size(); i++) {
			CodeProvince province = provinces.get(i);
			// 创建jsonprovice
			JSONObject jsonProvince = new JSONObject();
			jsonProvince.put("idx", province.getIdx());
			jsonProvince.put("province", province.getProvince());
			List<CodeCity> citys = codeAddressService.ListCitiesByPid(province.getIdx());
			JSONArray jsonArrayCity = new JSONArray();
			for (int j = 0; j < citys.size(); j++) {
				CodeCity city = citys.get(j);
				JSONObject jsonCity = new JSONObject();
				jsonCity.put("idx", city.getIdx());
				jsonCity.put("city", city.getCity());
				List<CodeAddress> addrs = codeAddressService.ListAddrByCid(city.getIdx());
				JSONArray jsonArrayAddrs = new JSONArray();
				for(CodeAddress addr:addrs){
					JSONObject jsonAddress = new JSONObject();
					jsonAddress.put("idx", addr.getIdx());
					jsonAddress.put("sido", addr.getSido());
					jsonAddress.put("gugun", addr.getGugun());
					jsonAddress.put("dong", addr.getDong());
					jsonAddress.put("sidoIdx", addr.getSidoIdx());
					jsonAddress.put("gugunIdx", addr.getGugunIdx());
					jsonArrayAddrs.add(jsonAddress);
				}
				city.setAddrs(addrs);
				jsonCity.put("addrList", jsonArrayAddrs);
				jsonArrayCity.add(jsonCity);
			}
			province.setCities(citys);
			CodeProvinces.add(province);
			jsonProvince.put("cityList", jsonArrayCity);
			jsonArrayProvince.add(jsonProvince);
		}
		servletContent.setAttribute("provinceList", jsonArrayProvince.toString());
		servletContent.setAttribute("provinces", CodeProvinces);
		
	}

}
