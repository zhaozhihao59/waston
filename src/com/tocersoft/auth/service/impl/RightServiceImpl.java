package com.tocersoft.auth.service.impl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.auth.dao.IRightDao;
import com.tocersoft.auth.dao.IRoleDao;
import com.tocersoft.auth.entity.Right;
import com.tocersoft.auth.service.IRightService;
import com.tocersoft.cms.service.impl.CmsChannelServiceImpl;

@Repository("permissionServiceImpl")
@Transactional(value = "transactionManager")
public class RightServiceImpl implements IRightService{
	private Logger logger = Logger.getLogger(CmsChannelServiceImpl.class);

	@Resource(name="rightDaoImpl")
	private IRightDao rightDao;

	@Resource(name="roleDaoImpl")
	private IRoleDao iroledao;
	
	@Override
	public List<Right> getAllEnablePermissions() {
		return rightDao.getAllEnableRights();
	}

	@Override
	public List<Right> getAllEnablePermissionsByUserId(String userId) {
		return rightDao.getAllEnableRightsByUserId(userId);
	}
	@Override
	public Map<Right, List<Map<Right, List<Map<Right, List<Right>>>>>> loadAllEnablePermissions(
			String userId) {
		Map<Right, List<Map<Right, List<Map<Right, List<Right>>>>>> firstMap 
		= new LinkedHashMap<Right, List<Map<Right, List<Map<Right, List<Right>>>>>>();
		
		// 加载权限列表
		List<Right> allEnablePermissionList = new ArrayList<Right>();
		if(userId.equals("38e338bd-4dfc-1030-abed-2ff74c950868")){
			allEnablePermissionList = this.getAllEnablePermissions();
		}else{
			allEnablePermissionList = this.getAllEnablePermissionsByUserId(userId);//this.getAllEnablePermissions(type);
		}
		// 组装权限数据
		List<Right> firstPermissionList = this.loadPermissionList(allEnablePermissionList, 1);
		List<Right> secondPermissionList = this.loadPermissionList(allEnablePermissionList, 2);
		List<Right> thirdPermissionList = this.loadPermissionList(allEnablePermissionList, 3);
		List<Right> forthPermissionList = this.loadPermissionList(allEnablePermissionList, 4);
		
		for (int i = 0; i < firstPermissionList.size(); i++) {
			Right firstPermission = firstPermissionList.get(i);
			List<Map<Right, List<Map<Right, List<Right>>>>> secondList = firstMap
					.get(firstPermission);
			if (null == secondList) {
				secondList = new ArrayList<Map<Right, List<Map<Right, List<Right>>>>>();
				firstMap.put(firstPermission, secondList);
			}

			// 加入二级菜单
			Map<Right, List<Map<Right, List<Right>>>> secondMap = new LinkedHashMap<Right, List<Map<Right, List<Right>>>>();
			secondList.add(secondMap); // 二级列表放map

			for (int j = 0; j < secondPermissionList.size(); j++) {
				Right secondPermission = secondPermissionList.get(j);
				if (secondPermission.getParentId().equals(firstPermission.getId())) {

					List<Map<Right, List<Right>>> thirdList = new LinkedList<Map<Right, List<Right>>>();

					secondMap.put(secondPermission, thirdList);

					for (int k = 0; k < thirdPermissionList.size(); k++) {
						Right thirdPermission = thirdPermissionList.get(k);

						if (thirdPermission.getParentId().equals(secondPermission.getId())) {

							Map<Right, List<Right>> thirdMap = new LinkedHashMap<Right, List<Right>>();
							thirdList.add(thirdMap);

							List<Right> forthList = new LinkedList<Right>();

							thirdMap.put(thirdPermission, forthList);

							for (int n = 0; n < forthPermissionList.size(); n++) {
								Right forthPermission = forthPermissionList
										.get(n);

								if (forthPermission.getParentId().equals(thirdPermission.getId())) {
									forthList.add(forthPermission);
								}

							}

						}

					}

				}
			}

		}
		return firstMap;
	}
	/**
	 * 根据上级编号和等级加载权限
	 * 
	 * @param parentId
	 * @param rank
	 * @return
	 */
	private List<Right> loadPermissionList(List<Right> allEnablePermissionList, int rank) {
		List<Right> list = new ArrayList<Right>();
		
		for(int i=0;i<allEnablePermissionList.size();i++){
			Right permission = allEnablePermissionList.get(i);
			if(permission.getLocation().intValue() == rank){
				list.add(permission);
			}
		}
		return list;
		
	}

	/**
	 * 根据父级ID查询递归查询下级操作权限
	 * @param 	pid		父级ID
	 * @return
	 */
	@Override
	public List<Right> listRightByPid(String pid, Integer level) {
		List<Right> rights = listRightByPidLoop(pid, level);
		return rights;
	}
	
	private List<Right> listRightByPidLoop(String pid, Integer level){
		
		// 符合条件的权限结果集
		List<Right> rights = null;
		
		// 如果级数 > 0，说明至少1级才能查出结果
		if(level > 0){
			// 查询PID下的权限
			rights = rightDao.listRightByPid(pid);
			// 查过一级，级数-1
			level--;
			// 递归查询下级权限
			for(Right right : rights){
				// 逐一调当前的递归方法进行逐级下查
				List<Right> childRights = listRightByPidLoop(right.getId(), level);
				// 将子权限查询后存入权限实体中
				right.setChildRights(childRights);
			}
		}
		
		return rights;
	}
 
	/**
	 * 新增生成编号
	 * 
	 * @return
	 */
	@Override
	public String createCodeNumByAdd(String parentId) {
		String codeNum = "";
//		codeNum = rightDao.getMaxCodeNum(parentId);
//		String code = "";
//		if (null == codeNum) {
//			code = rightDao.getCodeNum(parentId);
//			codeNum = code + "-0001";
//		} else {
//			codeNum = createCodeNum(codeNum);
//		}
		return codeNum;
	}
	
	@Override
	public void doAddRight(String parentId, String name, Integer sort,String tip,String fuUrl,Integer location) {
		Right right = null;
		String codeNum = "";
		if (StringUtils.isNotBlank(parentId) && !parentId.equals("0")) {
			right = rightDao.getRightById(parentId);
			//codeNum = createCodeNumByAdd(parentId);
			// 更新上级节点，是否为父节点，更新为是
//			if(right.get == null || right.getIsParent() != null && right.getIsParent().intValue() == 0){
//				// isParent设为1-是；isDelete设为1-不可删除
//				RightDao.updateIsParentDeleteById(Right.getId(), 1, 1);
//			}
		} else {
			parentId="0";
			// 获取一级类别最大codeNum
			codeNum = rightDao.getMaxCodeNum(parentId);
			if (StringUtils.isBlank(codeNum)) {
				codeNum = "0000";
			}
			int num = (Integer.parseInt(codeNum.toString()) + 1);
			NumberFormat num1 = NumberFormat.getNumberInstance();
			num1.setMinimumIntegerDigits(4);
			codeNum = num1.format(num);
			codeNum = codeNum.replaceAll(",", "");
		}
//		int level = 1;
//		if (null != right) {
//			level = right.getLevel() + 1;
//		}
		doAddRightNew(parentId, name, sort, codeNum, tip, fuUrl,location);
	}

	/**
	 * 添加类别属性
	 */
	@Override
	public void doAddRightNew(String parentId, String name, int sort,
			String codeNum, String tip,String fuUrl,Integer location) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("parentId", parentId);
			map.put("name", name);
			map.put("sort", sort);
			map.put("codeNum", codeNum);
			map.put("tip", tip);
			map.put("fuUrl", fuUrl);
			map.put("location", location);
			
			// 默认添加进来都为0-用户
			rightDao.doAddRightNew(map);
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
	 * 根据ID查询
	 * @param id 主键
	 * @return 部门表
	 */
	@Override
	public Right getRightById(String id){
		return rightDao.getRightById(id);
	}

	@Override
	public void doUpdateRight(String id, String name, Integer sort,String tip, String fuUrl,Integer location) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("name", name);
		map.put("sort", sort);
		map.put("tip", tip);
		map.put("fuUrl", fuUrl);
		//map.put("location", location);
		rightDao.doUpdateRight(map);		
	}

	@Override
	public int delRight(Right right) {
		// 查询上一级是否父节点与是否可删除；
		String pid = right.getParentId();
		String id=right.getId();
		if(checkDepartIsDeleteById(id)!=1){
			// 删除
			rightDao.delRightById(right.getId());
		}else{
			//返回action  1表示不能删除
			return 1;
		}
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
		List<Right> subRight = rightDao.listRightByPid(id);
		if(null != subRight && subRight.size() > 0){
			return 1;
		}
		// 2、该部门下是否有用户，若有，则不能删除；
//		List<Right> rights = iroledao.getRoleByRoleId(id);
//		if(null != rights && rights.size() > 0){
//			return 1;
//		}
		return 0;
	}

	/**
	 * 添加类别
	 */
	@Override
	public void add(Right right) {
		rightDao.add(right);
	}

	@Override
	public void doUpdateRight(Right right) {
		rightDao.doUpdateRight(right);
	}

 
	
}
