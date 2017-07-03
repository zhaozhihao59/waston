package com.tocersoft.cms.service.impl;

import java.text.NumberFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.cms.dao.ICmsArticleDao;
import com.tocersoft.cms.dao.ICmsChannelDao;
import com.tocersoft.cms.entity.CmsArticle;
import com.tocersoft.cms.entity.CmsChannel;
import com.tocersoft.cms.service.ICmsChannelService;

@Service
@Transactional(value = "transactionManager")
public class CmsChannelServiceImpl implements ICmsChannelService{
	
	private Logger logger = Logger.getLogger(CmsChannelServiceImpl.class);
	@Resource
	private ICmsChannelDao cmsChannelDao;
	@Resource
	private ICmsArticleDao cmsArticleDao;
	@Override
	public void getChannelListByPage(PageResult<CmsChannel> pageResult) {
		RowBounds bounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		//查询栏目结果集
		List<CmsChannel> list = cmsChannelDao.getChannelListByPage(bounds);
		pageResult.setResult(list);
		//查询总行数
		int count  = cmsChannelDao.getChannelListByPageCount();
		pageResult.setRows(count);
	}
	
	@Override
	public void doSave(CmsChannel item) {
		if(StringUtils.isNotBlank(item.getId())){
			//修改
			cmsChannelDao.update(item);
		}else{
			//添加
			cmsChannelDao.add(item);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CmsChannel> getCmsChannelAll() {
		List<CmsChannel> list = cmsChannelDao.getCmsChannelAll();
		if(null != list && list.size() > 0){
			return list;
		}
		return Collections.EMPTY_LIST;
	}
	
	@Override
	public void delChannel(String channelId) {
		cmsChannelDao.delChannel(channelId);
	}
	@Override
	public CmsChannel getCmsChannelById(String channelId) {
		return cmsChannelDao.getCmsChannelById(channelId);
	}
	@Override
	public List<CmsChannel> getCmsChannelRight() {
		return cmsChannelDao.getCmsChannelRight();
	}
	
	@Override
	public void showRight(String channelId) {
		if(StringUtils.isNotBlank(channelId)){
			String [] selIds = channelId.split(",");
			cmsChannelDao.showRight(selIds, 1);
		}
	}
	@Override
	public void unShowRight(String channelId) {
		if(StringUtils.isNotBlank(channelId)){
			String [] selIds = channelId.split(",");
			cmsChannelDao.showRight(selIds, 0);
		}
	}
	
	/**
	 * 根据上级类别ID获得类别列表
	 * 
	 * @param parentId
	 * @return
	 */
	@Override
	public List<CmsChannel> listChannelByPid(String parent) {
		return cmsChannelDao.listChannelByPid(parent);
	}
	
	
	/**
	 * 根据语言查询
	 * @return
	 */
	public List<CmsChannel> listChannelByIsEn(Integer isEn){
		return cmsChannelDao.listChannelByIsEn(isEn);
	}
	
	//////////////////////
	@Override
	public void doAddCmsChannel(String parentId, String name, Integer sort) {
		CmsChannel cmsChannel = null;
		String codeNum = "";
		if (StringUtils.isNotBlank(parentId) && !parentId.equals("0")) {
			cmsChannel = cmsChannelDao.getById(parentId);
			codeNum = createCodeNumByAdd(parentId);
			// 更新上级节点，是否为父节点，更新为是
			if(cmsChannel.getIsParent() == null || cmsChannel.getIsParent() != null && cmsChannel.getIsParent().intValue() == 0){
				// isParent设为1-是；isDelete设为1-不可删除
				cmsChannelDao.updateIsParentDeleteById(cmsChannel.getId(), 1, 1);
			}
		} else {
			parentId="0";
			// 获取一级类别最大codeNum
			codeNum = cmsChannelDao.getMaxCodeNum(parentId);
			if (StringUtils.isBlank(codeNum)) {
				codeNum = "0000";
			}
			int num = (Integer.parseInt(codeNum.toString()) + 1);
			NumberFormat num1 = NumberFormat.getNumberInstance();
			num1.setMinimumIntegerDigits(4);
			codeNum = num1.format(num);
			codeNum = codeNum.replaceAll(",", "");
		}
		int level = 1;
		if (null != cmsChannel) {
			level = cmsChannel.getLevel() + 1;
		}
		doAddCmsChannel(parentId, name, sort, codeNum, level);
	}
	
	/**
	 * 添加类别属性
	 */
	private void doAddCmsChannel(String parentId, String name, int sort,
			String codeNum, int level) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parent", parentId);
		map.put("name", name);
		map.put("sort", sort);
		map.put("codeNum", codeNum);
		map.put("level", level);
		// 默认添加进来都为0-用户
		map.put("state", 0);
		cmsChannelDao.doAddCmsChannel(map);
	}
	/**
	 * 新增生成编号
	 * 
	 * @return
	 */
	public String createCodeNumByAdd(String parentId) {
		String codeNum = "";
		codeNum = cmsChannelDao.getMaxCodeNum(parentId);
		String code = "";
		if (null == codeNum) {
			code = cmsChannelDao.getCodeNum(parentId);
			codeNum = code + "-0001";
		} else {
			codeNum = createCodeNum(codeNum);
		}
		return codeNum;
	}
	
	/**
	 * 根据给定的code值生成下一个code，规则是
	 * 
	 * <pre>
	 * 0001 生成 0002
	 * 0000-2222 生成 0000-2223
	 * 0001-9999-9998 生成 0001-9999-9999
	 * 第一段取值：0-999
	 * 其他段取值：0-9999
	 * </pre>
	 * 
	 * @param code
	 * @return
	 */
	private String createCodeNum(String code) {
		int codeNum = code.indexOf("-");
		String lastCode = "";
		if (codeNum > 0) {
			String[] codeArr = code.split("-");
			int len = codeArr.length;// code包含几段
			lastCode = codeArr[len - 1];
			int lastCodeNum = Integer.parseInt(lastCode);
			lastCodeNum++;
			if (lastCodeNum > 9999 && len > 1) {
				logger.error("生成的类别codeNum失败：生成的类别codeNum值不能大于 9999 ");
				return null;
			} else if (lastCodeNum > 999 && len == 1) {
				logger.error("生成的类别codeNum失败：生成的第一段类别codeNum值不能大于 999 ");
				return null;
			}
			lastCode = String.valueOf(lastCodeNum);
			int codeLen = lastCode.length();
			switch (codeLen) {
			case 1:
				lastCode = "000" + lastCode;
				break;
			case 2:
				lastCode = "00" + lastCode;
				break;
			case 3:
				lastCode = "0" + lastCode;
				break;
			}
			int i = code.lastIndexOf("-");
			if (i != -1) {
				i++;
				lastCode = code.substring(0, i) + lastCode;
			}
		} else {
			lastCode = code + "-0001";
		}

		return lastCode;
	}
	@Override
	public void doUpdateCmsChannel(String id, String name, Integer sort) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("name", name);
			map.put("sort", sort);
			cmsChannelDao.doUpdateCmsChannel(map);
		}
	@Override
	public CmsChannel getById(String nodeId) {
		return cmsChannelDao.getById(nodeId);
	}
	
	@Override
	public void delCmsChannel(CmsChannel channel){
		// 删除栏目
		cmsChannelDao.delCmsChannelById(channel.getId());
		// 查询上一级是否父节点与是否可删除；
		String pid = channel.getParent();
		List<CmsChannel> subChannel = cmsChannelDao.listChannelByPid(pid);
		// 如果同级没有其他节点，则上一级不是父节点
		if(null == subChannel || null != subChannel && subChannel.size() == 0){
			// 此时需要验证上级是否可删除
			Integer isDelete = this.checkChannelIsDeleteById(pid);
			cmsChannelDao.updateIsParentDeleteById(pid, 0, isDelete);
		}
	}

	/**
	 * 检查栏目是否能被删除
	 * 1、该栏目下是否有子栏目，若有，则不能删除；
	 * 2、该栏目下是否有文章，若有，则不能删除；
	 * @param 	id	栏目ID
	 * @return	0 - 可删除 1 - 不可删除
	 */
	@Override
	public Integer checkChannelIsDeleteById(String id) {
		// 1、该栏目下是否有子栏目，若有，则不能删除；
		List<CmsChannel> subChannel = cmsChannelDao.listChannelByPid(id);
		if(null != subChannel && subChannel.size() > 0){
			return 1;
		}
		// 2、该栏目下是否有文章，若有，则不能删除；
		List<CmsArticle> articles = cmsArticleDao.getArticleByChannelId(id);
		if(null != articles && articles.size() > 0){
			return 1;
		}
		return 0;
	}
	
}
