package com.tocersoft.auth.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.cms.service.impl.CmsChannelServiceImpl;
import com.tocersoft.auth.dao.IDepartDao;
import com.tocersoft.auth.dao.IUserDao;
import com.tocersoft.auth.entity.Depart;
import com.tocersoft.auth.entity.User;
import com.tocersoft.auth.service.IDepartService;

import com.tocersoft.auth.dto.DepartCondition;

@Repository("departServiceImpl")
@Transactional(value = "transactionManager")
public class DepartServiceImpl implements IDepartService{
	private Logger logger = Logger.getLogger(CmsChannelServiceImpl.class);

	@Resource(name = "departDaoImpl")
	private IDepartDao departDao;
	
	@Resource(name = "userDaoImpl")
	private IUserDao userDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	@Override
	public void listDepartByPage(PageResult<Depart> pageResult,DepartCondition condition){
		int rows = departDao.listDepartByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<Depart> list = departDao.listDepartByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 部门表
	 */
	@Override
	public Depart getDepartById(String id){
		return departDao.getDepartById(id);
	}

	/**
	 * 新增
	 * @param item 部门表
	 */
	@Override
	public void add(Depart item){
		departDao.add(item);
	}

	/**
	 * 修改
	 * @param item 部门表
	 */
	@Override
	public void update(Depart item){
		departDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	@Override
	public void delByIds(String[] ids){
		departDao.delByIds(ids);
	}
///////////////////////
	/**
	 * 根据上级ID查询部门列表
	 * @param pageResult 分页对象
	 * @param value 上级ID
	 */
	@Override
	public List<Depart> listDepartByPid(String pid) {
		return departDao.listDepartByPid(pid);
	}
	
	@Override
	public void doAddDepart(String parentId, String name, Integer sort,String note,int isParent) {
		Depart depart = null;
		String codeNum = "";
		if (StringUtils.isNotBlank(parentId) && !parentId.equals("0")) {
			depart = departDao.getDepartById(parentId);
			//codeNum = createCodeNumByAdd(parentId);
			// 更新上级节点，是否为父节点，更新为是
			if(depart.getIsParent() == null || depart.getIsParent() != null && depart.getIsParent().intValue() == 0){
				// isParent设为1-是；isDelete设为1-不可删除
				departDao.updateIsParentDeleteById(depart.getId(), 1, 1);
			}
		}
	//		else {
//			parentId="0";
//			// 获取一级类别最大codeNum
//			codeNum = departDao.getMaxCodeNum(parentId);
//			if (StringUtils.isBlank(codeNum)) {
//				codeNum = "0000";
//			}
//			int num = (Integer.parseInt(codeNum.toString()) + 1);
//			NumberFormat num1 = NumberFormat.getNumberInstance();
//			num1.setMinimumIntegerDigits(4);
//			codeNum = num1.format(num);
//			codeNum = codeNum.replaceAll(",", "");
//		}
		int level = 1;
		if (null != depart) {
			level = depart.getLevel() + 1;
		}
		doAddDepartNew(parentId, name, sort, codeNum, level,note);
	}


	/**
	 * 添加类别属性
	 */
	@Override
	public void doAddDepartNew(String parentId, String name, int sort,
			String codeNum, int level,String note) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("parentId", parentId);
			map.put("name", name);
			map.put("sort", sort);
			map.put("codeNum", codeNum);
			map.put("level", level);
			map.put("note", note);
			
			// 默认添加进来都为0-用户
			departDao.doAddDepartNew(map);
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
	@Override
	public String createCodeNum(String code) {
		int codeNum = code.indexOf("-");
		String lastCode = "";
//		if (codeNum > 0) {
//			String[] codeArr = code.split("-");
//			int len = codeArr.length;// code包含几段
//			lastCode = codeArr[len - 1];
//			int lastCodeNum = Integer.parseInt(lastCode);
//			lastCodeNum++;
//			if (lastCodeNum > 9999 && len > 1) {
//				logger.error("生成的类别codeNum失败：生成的类别codeNum值不能大于 9999 ");
//				return null;
//			} else if (lastCodeNum > 999 && len == 1) {
//				logger.error("生成的类别codeNum失败：生成的第一段类别codeNum值不能大于 999 ");
//				return null;
//			}
//			lastCode = String.valueOf(lastCodeNum);
//			int codeLen = lastCode.length();
//			switch (codeLen) {
//			case 1:
//				lastCode = "000" + lastCode;
//				break;
//			case 2:
//				lastCode = "00" + lastCode;
//				break;
//			case 3:
//				lastCode = "0" + lastCode;
//				break;
//			}
//			int i = code.lastIndexOf("-");
//			if (i != -1) {
//				i++;
//				lastCode = code.substring(0, i) + lastCode;
//			}
//		} else {
//			lastCode = code + "-0001";
//		}

		return lastCode;
	}
	
	/**
	 * 新增生成编号
	 * 
	 * @return
	 */
	@Override
	public String createCodeNumByAdd(String parentId) {
		String codeNum = "";
//		codeNum = departDao.getMaxCodeNum(parentId);
//		String code = "";
//		if (null == codeNum) {
//			//code = departDao.getCodeNum(parentId);
//			codeNum = code + "-0001";
//		} else {
//			codeNum = createCodeNum(codeNum);
//		}
		return codeNum;
	}

	/**
	 * 修改 部门
	 * @param map
	 */
	@Override
	public void doUpdateDepart(String id, String name, Integer sort,String note) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("name", name);
		map.put("sort", sort);
		map.put("note", note);
		departDao.doUpdateDepart(map);		
	}

	@Override
	public int delDepart(Depart depart) {
		// 查询上一级是否父节点与是否可删除；
		String pid = depart.getParentId();
		String id=depart.getId();
		if(checkDepartIsDeleteById(id)!=1){
			// 删除部门
			departDao.delDepartById(depart.getId());
		}else{
			//返回action  1表示不能删除
			return 1;
		}
//		List<Depart> subDepart = departDao.listDepartByPid(pid);
//		// 如果同级没有其他节点，则上一级不是父节点
//		if(null == subDepart || null != subDepart && subDepart.size() == 0){
//			// 此时需要验证上级是否可删除
//			Integer isDelete = this.checkDepartIsDeleteById(id);
//			if(isDelete!=1){
//				departDao.updateIsParentDeleteById(pid, 0, isDelete);
//			}
//		}
		return 0;
	}
 
	/**
	 * 检查部门是否能被删除
	 * 1、该部门下是否有子部门，若有，则不能删除；
	 * 2、该部门下是否有用户，若有，则不能删除；
	 * @param 	id	部门ID
	 * @return	0 - 可删除 1 - 不可删除
	 */
	@Override
	public Integer checkDepartIsDeleteById(String id) {
		// 1、该部门下是否有子部门，若有，则不能删除；
		List<Depart> subDepart = departDao.listDepartByPid(id);
		if(null != subDepart && subDepart.size() > 0){
			return 1;
		}
		// 2、该部门下是否有用户，若有，则不能删除；
		List<User> users = userDao.getUserByDepartId(id);
		if(null != users && users.size() > 0){
			return 1;
		}
		return 0;
	}

	@Override
	public List<Depart> listAllDepart() {
		return departDao.listAllDepart();
	}
	
	 
}

