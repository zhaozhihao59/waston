package com.tocersoft.base.interceptors;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tocersoft.base.page.PageResult;
import com.tocersoft.base.utils.DoubleUtil;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.cms.entity.CmsArticle;
import com.tocersoft.cms.entity.CmsChannel;
import com.tocersoft.cms.service.ICmsArticleService;
import com.tocersoft.cms.service.ICmsChannelService;
import com.tocersoft.order.entity.OrderCartItem;
import com.tocersoft.product.dto.ProductCondition;
import com.tocersoft.product.entity.Product;
import com.tocersoft.product.entity.ProductBrand;
import com.tocersoft.product.entity.ProductCategory;
import com.tocersoft.product.service.IProductBrandService;
import com.tocersoft.product.service.IProductCategoryService;
import com.tocersoft.product.service.IProductService;
import com.tocersoft.system.entity.SysDictItem;
import com.tocersoft.system.service.ISysDictItemService;

/**
 * 摘要：前台拦截器
 * @creator 方泉
 * @create-time Dec 26, 2011 10:45:30 AM
 */
@Component("frontInterceptor")
public class FrontInterceptor extends AbstractInterceptor {

	/** */
	private static final long serialVersionUID = -3316470408259781621L;
	private Logger logger = Logger.getLogger(FrontInterceptor.class);
	
	@Resource
	private ISysDictItemService sysDictItemService;
	@Resource
	private IProductCategoryService categoryService;
	@Resource
	private IProductBrandService productBrandService;
	@Resource
	private IProductService productService;
	@Resource
	private ICmsChannelService cmsChannelService;
	@Resource
	private ICmsArticleService cmsArticleService;
	
	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		HttpSession session = WebUtils.getSession();
		
		// 获取网站帮助信息
		List<CmsChannel> cmsChannels = (List<CmsChannel>) session.getAttribute("foot_cmsChannels");
		if(null == cmsChannels){
			cmsChannels = cmsChannelService.listChannelByIsEn(0);
			for (CmsChannel cmsChannel : cmsChannels) {
				List<CmsArticle> cmsArticles = cmsArticleService.getArticleByChannelId(cmsChannel.getId());
				cmsChannel.setCmsArticles(cmsArticles);
			}
			if(cmsChannels != null && cmsChannels.size()>0){
				session.setAttribute("foot_cmsChannels", cmsChannels);
			}
		}
		
		// 获取网站英文帮助信息(英文)
		List<CmsChannel> cmsChannelsEn = (List<CmsChannel>) session.getAttribute("foot_cmsChannels_en");
		if(null == cmsChannelsEn){
			cmsChannelsEn = cmsChannelService.listChannelByIsEn(1);
			for (CmsChannel cmsChannel : cmsChannelsEn) {
				List<CmsArticle> cmsArticles = cmsArticleService.getArticleByChannelId(cmsChannel.getId());
				cmsChannel.setCmsArticles(cmsArticles);
			}
			if(cmsChannelsEn != null && cmsChannelsEn.size()>0){
				session.setAttribute("foot_cmsChannels_en", cmsChannelsEn);
			}
		}
		
		return invocation.invoke();
	}
}
