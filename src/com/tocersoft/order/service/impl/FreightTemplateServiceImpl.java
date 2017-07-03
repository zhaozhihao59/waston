package com.tocersoft.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.member.entity.MemAddress;
import com.tocersoft.order.dao.IFreightTemplateDao;
import com.tocersoft.order.dao.IFreightTemplateItemDao;
import com.tocersoft.order.dto.FreightTemplateCondition;
import com.tocersoft.order.entity.OrderCartItem;
import com.tocersoft.order.entity.FreightTemplate;
import com.tocersoft.order.service.IFreightTemplateService;

@Service("freightTemplateServiceImpl")
@Transactional(value = "transactionManager")
public class FreightTemplateServiceImpl implements IFreightTemplateService{
	
	private Log logger = LogFactory.getLog(FreightTemplateServiceImpl.class);

	@Resource(name = "freightTemplateDaoImpl")
	private IFreightTemplateDao freightTemplateDao;
	@Resource(name = "freightTemplateItemDaoImpl")
	private IFreightTemplateItemDao freightTemplateItemDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listFreightTemplateByPage(PageResult<FreightTemplate> pageResult,FreightTemplateCondition condition){
		int rows = freightTemplateDao.listFreightTemplateByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<FreightTemplate> list = freightTemplateDao.listFreightTemplateByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 运费模板表
	 */
	public FreightTemplate getFreightTemplateById(String id){
		return freightTemplateDao.getFreightTemplateById(id);
	}
	

	@Override
	public List<FreightTemplate> listFreightTemplateAll(FreightTemplateCondition condition) {
		return freightTemplateDao.listFreightTemplateAll(condition);
	}

	/**
	 * 新增
	 * @param item 运费模板表
	 */
	public void add(FreightTemplate item){
		freightTemplateDao.add(item);
	}

	/**
	 * 修改
	 * @param item 运费模板表
	 */
	public void update(FreightTemplate item){
		freightTemplateDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		freightTemplateDao.delByIds(ids);
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public JSONObject loadFreightPriceByWXCustomer(WXCustomer wxCustomer,String proWeinId) {
//		WXCustomer current = wxCustomerDao.getWXCustomerById(wxCustomer.getId());
//		JSONObject json = new JSONObject();
//		json.put("city", "未知");
//		json.put("price", "0");
//		if(StringUtils.isBlank(current.getLatitude()) || StringUtils.isBlank(current.getLongitude())){
//			return json;
//		}
//		
//		//调用地图接口，获取城市和省份
//		StringBuffer invokeUrl = new StringBuffer();
//		invokeUrl.append("http://api.map.baidu.com/geocoder/v2/?");
//		invokeUrl.append("ak=").append(WeiXinProps.getProperty("baidu_map_ak"));
//		invokeUrl.append("&callback=renderReverse");
//		invokeUrl.append("&location=").append(current.getLatitude()).append(",").append(current.getLongitude());
//		invokeUrl.append("&output=json&pois=0");
//		
//		try{
//			JSONObject invokeMapResult = WXHttpsUtil.loadBaiduMapLocationByGetMethod(invokeUrl.toString());
//			JSONObject address = (JSONObject) ((JSONObject) invokeMapResult.get("result")).get("addressComponent");
//			String province = address.get("province").toString();
//			String city = address.get("city").toString();
//			
//			logger.info("查询到用户所在的地理位置，所在省份："+province+",所在城市："+city);
//			
//			json.put("city", city);
//			json.put("province", province);
//		}catch(Exception ex){
//			String msg = "调用地图API发生异常："+ex.getMessage();
//			logger.error(msg);
//			return json;
//		}
//		
//		
//		//获取价格
//		ProProductWeixin proWeiXin = proProductWeixinDao.getProProductWeixinById(proWeinId);
//		if(null == proWeiXin || StringUtils.isBlank(proWeiXin.getFreightTemplateId())){
//			return json;
//		}
//		FreightTemplate freightTemplate = freightTemplateDao.getFreightTemplateById(proWeiXin.getFreightTemplateId());
//		if(null == freightTemplate){
//			return json;
//		}
//		
//		//根据到达城市列表和模板ID查询
//		FreightTemplateItem firstMatchItem = freightTemplateItemDao.getFreightTemplateItemByTemplateIdAndItemName(freightTemplate.getId(),json.get("city").toString());
//		if(null != firstMatchItem){
//			json.put("price", firstMatchItem.getFirstWeightFee());
//			return json;
//		}
//		
//		//向上一级，根据省份名称找
//		firstMatchItem = freightTemplateItemDao.getFreightTemplateItemByTemplateIdAndItemName(freightTemplate.getId(),json.get("province").toString());
//		if(null != firstMatchItem){
//			json.put("price", firstMatchItem.getFirstWeightFee());
//		}
//		
//		
//		return json;
//	}

	@Override
	public Double calFreightAmountByMemAddress(MemAddress memAddress,List<OrderCartItem> cartItems) {
		Double freightAmount = 0d;
		if(null == memAddress || CollectionUtils.isEmpty(cartItems)){
			return freightAmount;
		}
		
//		for(CartItem cartItem : cartItems){
//			ProProductWeixin proWeiXin = proProductWeixinDao.getProProductWeixinById(cartItem.getProductWeixinId());
//			if(null == proWeiXin || StringUtils.isBlank(proWeiXin.getFreightTemplateId())){
//				continue;
//			}
//			
//			FreightTemplate freightTemplate = freightTemplateDao.getFreightTemplateById(proWeiXin.getFreightTemplateId());
//			if(null == freightTemplate){
//				continue;
//			}
//			
//			//根据到达城市列表和模板ID查询
//			FreightTemplateItem firstMatchItem = freightTemplateItemDao.getFreightTemplateItemByTemplateIdAndItemName(freightTemplate.getId(),memAddress.getCity());
//			if(null == firstMatchItem){
//				//向上一级，根据省份名称找
//				firstMatchItem = freightTemplateItemDao.getFreightTemplateItemByTemplateIdAndItemName(freightTemplate.getId(),memAddress.getProvince());
//			}
//			
//			if(null == firstMatchItem){
//				continue;
//			}
//			
//			//计算运费价格
//			Double proWeiXinWeight = proWeiXin.getWeight();
//			if(proWeiXinWeight == null || proWeiXinWeight.doubleValue() == 0){
//				//没有重量，取首重价格
//				freightAmount += firstMatchItem.getFirstWeightFee();
//			}else{
//				//重量*数量
//				Double totalProductWeight = proWeiXinWeight * cartItem.getAmount();
//				//加上首重
//				freightAmount += firstMatchItem.getFirstWeightFee();
//				if(totalProductWeight > firstMatchItem.getFirstWeight()){
//					//大于首重，计算差值
//					//向上取整
//					double ceilNum = Math.ceil((totalProductWeight - firstMatchItem.getFirstWeight()) / firstMatchItem.getContinuedWeight());
//					freightAmount += ceilNum * firstMatchItem.getContinuedWeightFee();
//				}
//			}
//		}
		
		return freightAmount;
		
	}
}

