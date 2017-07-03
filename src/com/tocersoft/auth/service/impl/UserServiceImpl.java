package com.tocersoft.auth.service.impl;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.auth.dao.IDepartDao;
import com.tocersoft.auth.dao.IRoleDao;
import com.tocersoft.auth.dao.IUserDao;
import com.tocersoft.auth.dto.UserCondition;
import com.tocersoft.auth.dto.UserVo;
import com.tocersoft.auth.entity.Depart;
import com.tocersoft.auth.entity.Role;
import com.tocersoft.auth.entity.User;
import com.tocersoft.auth.service.IRoleService;
import com.tocersoft.auth.service.IUserService;
import com.tocersoft.base.page.PageResult;
import com.tocersoft.base.utils.CommonUtil;
import com.tocersoft.base.utils.Constant;
import com.tocersoft.base.utils.DataUtil;
import com.tocersoft.base.utils.WebUtils;
@Service
@Transactional(value = "transactionManager")
public class UserServiceImpl  implements IUserService{
	
	@Resource
	private IUserDao userDao;
	@Resource
	private IRoleService roleService;
	@Resource 
	private IDepartDao departDao;
	@Resource
	private IRoleDao roleDao;
	
	@Override
	public void saveUser(User item, String roleIds) {
		if(StringUtils.isBlank(item.getId())){
			item.setState(1);	//默认启用账号
			item.setPassword(DataUtil.encryptionPw(item.getPassword()));	//密码进行2次加密
			//item.setId(UUIDUtil.uuid());
			//新增用户
			userDao.add(item);
			if(StringUtils.isNotBlank(roleIds)){
				Map<String,String> map = new HashMap<String,String>();
				String [] roleId = roleIds.split(",");
				for (int i = 0; i < roleId.length; i++) {
					map.put("userId", item.getId());
					map.put("roleId", roleId[i]);
					//新增角色
					userDao.addUserRole(map);
					map.clear();
				}
			}
		}else{
			User old = this.getUserDetailById(item.getId());
			old.setBirthday(item.getBirthday());
			old.setContact(item.getContact());
			old.setEmail(item.getEmail());
			old.setMobile(item.getMobile());
			old.setName(item.getName());
			old.setSex(item.getSex());
			old.setTel(item.getTel());
			old.setStaffId(item.getStaffId());
			//更新用户
			userDao.update(old);
			userDao.doBatchDelUserRoles(item.getId());
			if(StringUtils.isNotBlank(roleIds)){
				Map<String,String> map = new HashMap<String,String>();
				String [] roleId = roleIds.split(",");
				for (int i = 0; i < roleId.length; i++) {
					map.put("userId", item.getId());
					map.put("roleId", roleId[i]);
					//新增角色
					userDao.addUserRole(map);
					map.clear();
				}
			}
		}
	}
	
	/**
	 * 验证用户名是否存在
	 * @param item
	 * @param roleIds
	 */
	@Override
	public boolean alreadyExists(String account ){
		User user = userDao.getUserDetailByAccount(account);
		boolean res = true;
		if(user == null ){
			res = true;
		}else{
			res = false;
		}
		return res;
	}
	
	
	/**
	 * 更新用户 
	 **/
	@Override
	public void updateUserDetail(User item, String roleIds){
		User old = this.getUserDetailById(item.getId());
		old.setAccount(item.getAccount());
		old.setBirthday(item.getBirthday());
		old.setContact(item.getContact());
		old.setEmail(item.getEmail());
		old.setMobile(item.getMobile());
		old.setName(item.getName());
		old.setSex(item.getSex());
		old.setTel(item.getTel());
		old.setExt(item.getExt());
		old.setStaffId(item.getStaffId());
		old.setDepartId(item.getDepartId());
		//更新用户
		userDao.update(old);
		userDao.doBatchDelUserRoles(item.getId());
		if(StringUtils.isNotBlank(roleIds)){
			Map<String,String> map = new HashMap<String,String>();
			String [] roleId = roleIds.split(",");
			for (int i = 0; i < roleId.length; i++) {
				map.put("userId", item.getId());
				map.put("roleId", roleId[i]);
				//新增角色
				userDao.addUserRole(map);
				map.clear();
			}
		}
	}
	
	/**
	 * 更新银行帐号
	 * @param item
	 * @param roleIds
	 */
	@Override
	public void updateBankAccount(String bankAccount,String id){
		userDao.updateBankAccount(bankAccount, id);
	}
	/**
	 * 确认银行帐号状态
	 * @param item
	 * @param roleIds
	 */
	@Override
	public void updateBankStatus(String id){
		userDao.updateBankStatus(id);
	}
	
	@Override
	public boolean judgeAccountExist(String account) {
		User user = userDao.getUserByAccount(account);
		return null != user;
	}
	
	@Override
	public List<User> getAllUser(UserCondition condition) {
		List<User> userList = userDao.getAllUser(condition);
		return userList;
	}
	
	@Override
	public List<User> getAllUserAndAdmin() {
		List<User> userList = userDao.getAllUserAndAdmin();
		return userList;
	}
	
	@Override
	public boolean judgeAnotherAccountExist(String account, String id) {
		User user = userDao.getUserAnotherByAccount(account,id);
		return null != user;
	}
	@Override
	public void getUserByPage(PageResult<UserVo> pageResult,UserCondition condition) {
		//查询条件
		condition.setAdmin(Constant.ADMIN_USER);
		//进行like检查
		CommonUtil.checkEscapeValue(condition);
		RowBounds bounds = new RowBounds(pageResult.getCurrentPageIndex(), pageResult.getPageSize());
		//获取结果集
		List<User> list = userDao.getUserListByPage(bounds,condition);
		List<UserVo> voList = new ArrayList<UserVo>(list.size());
		pageResult.setResult(voList);
		//获取总行数
		int count  = userDao.getUserByListPageCount(condition);
		pageResult.setRows(count);
		
		//封装
		for(int i=0;i<list.size();i++){
			User user = list.get(i);
			UserVo vo = new UserVo();
			//拷贝属性
			BeanUtils.copyProperties(user, vo,new String[]{"roles","authorities"});
			
			StringBuffer roles = new StringBuffer();
			List<Role> roleList = roleService.getRoleByUserId(user.getId());
			for(int j=0; j < roleList.size();j++){
				if(j>0){
					roles.append(",");
				}
				Role role = roleList.get(j);
				roles.append(role.getName());
			}
			vo.setRoleNames(roles.toString());
			voList.add(vo);
		}
	}
	
	@Override
	public void updateState(User item) {
		if(item.getState()==2){
			// 设置禁用期限
			Date date = new Date();
			Calendar sc = Calendar.getInstance();
			sc.setTime(date);
			sc.add(Calendar.MONTH, 1);
			sc.set(Calendar.DAY_OF_MONTH, 5);
			sc.set(Calendar.HOUR, 0);
			sc.set(Calendar.MINUTE, 0);
			Date newDate = sc.getTime();
			item.setStateDate(newDate);
		}
		userDao.updateState(item);
	}
	@Override
	
	public User getUserDetailById(String id) {
		User user = userDao.getUserDetailById(id);
		if(null != user){
			List<Role> roleList = roleService.getRoleByUserId(user.getId());
			user.setRoles(roleList);
		}
		return user;
	}
	
	@Override
	public void resetPwd(User item) {
		item.setPassword(DataUtil.encryptionPw(item.getPassword()));
		// 如果是本人修改 则修改初始密码状态
		if( item.getIsChangePwd()!=null){
			User old = this.getUserDetailById(item.getId());
			old.setIsChangePwd(item.getIsChangePwd());
			userDao.update(old);
		}
		userDao.resetPwd(item);
	}
	@Override
	public void delUserById(String id) {
		if(StringUtils.isNotBlank(id)){
			userDao.updateUser(id);
			String [] sel = id.split(",");
			userDao.doBatchDelUserRole(sel);
		}
		
	}
	@Override
	public void doBatchDelUser(String selIds) {
		String [] sel = selIds.split(",");
		if(StringUtils.isNotBlank(sel.toString())){
			//先删除用户和角色的关系
			userDao.doBatchDelUserRole(sel);
			//在删除，假删
			userDao.doBatchDelUser(sel);
		}
	}
	@Override
	public void doManagerContr(String selIds,String manageId) {
		String [] sel = selIds.split(",");
		if(StringUtils.isNotBlank(selIds.toString())){
			for (int i = 0; i < sel.length; i++) {
				departDao.doManagerContr(manageId,sel[i]);
				userDao.doManagerContr(sel[i]);
			}
		}
	}
	@Override
	public void doManagerNoContr(String selIds,String manageId) {
		String [] sel = selIds.split(",");
		if(StringUtils.isNotBlank(selIds.toString())){
			for (int i = 0; i < sel.length; i++) {
				departDao.doManagerNoContr(manageId,sel[i]);
				userDao.doManagerNoContr(sel[i]);
			}
		}
	}
	@Override
	public void updateUser(User item) {
		userDao.update(item);
	}

	@Override
	public int getUserCount() {
		return userDao.getUserCount();
	}

	@Override
	public List<User> listUserNo(String name) {
		return userDao.listUserNo(name);
	}
	
	@Override
	public List<User> listUserByNameAndProductId(String name,String productId) {
		return userDao.listUserByNameAndProductId(name,productId);
	}

	@Override
	public void doImport(String tempPath) {
		POIFSFileSystem fs = null;
		HSSFWorkbook wb = null;
		try {
			String path = WebUtils.getRealPath(tempPath);
			fs = new POIFSFileSystem(new FileInputStream(path));
			wb = new HSSFWorkbook(fs);
		} catch (Exception e) {
		 
		}
		
		if (null == wb) {
			return;
		}
		
		HSSFSheet sheet = wb.getSheetAt(0);
		// 获得总行数
		int rows = sheet.getLastRowNum();
		// 遍历所有行
		
		for (int i = 1; i <= rows; i++) {
			HSSFRow row = sheet.getRow(i);
			if(row == null){
				continue;
			}
			User item = new User();
			item.setState(1);	//默认启用账号
			//用户名
			String username = CommonUtil.getCellValue(row.getCell(0));
			
			item.setAccount(username);
			//密码
			String password = CommonUtil.getCellValue(row.getCell(1));
			if(StringUtils.isNotBlank(password)){
				item.setPassword(DataUtil.encryptionPw(password));
				item.setIsChangePwd(1);
			}else{
				item.setPassword(DataUtil.encryptionPw("11111111"));
				item.setIsChangePwd(0);
			}
			//部门
			String depart = CommonUtil.getCellValue(row.getCell(2));
			if(StringUtils.isNotBlank(depart)){
				List<Depart> departList = departDao.getDepartByName(depart);
				if(departList.size() > 0){
					Depart dep = departList.get(0);
					item.setDepartId(dep.getId());
					item.setDepartName(dep.getName());
				}
			}
			
			//中文名
			String cnName = CommonUtil.getCellValue(row.getCell(4));
			item.setName(cnName);
			//英文名
			String enName = CommonUtil.getCellValue(row.getCell(5));
			item.setEnName(enName);
			//电话直线
			String line = CommonUtil.getCellValue(row.getCell(6));
			item.setTel(line);
			//分机
			String ext = CommonUtil.getCellValue(row.getCell(7));
			item.setExt(ext);
			//email
			String email = CommonUtil.getCellValue(row.getCell(8));
			item.setEmail(email);
			//手机号
			String phone = CommonUtil.getCellValue(row.getCell(9));
			item.setMobile(phone);
			//性别
			String sex = CommonUtil.getCellValue(row.getCell(10));
			if(StringUtils.isNotBlank(sex)){
				item.setSex(1);
				if("女".equals(sex)){
					item.setSex(0);
				}
			}
			userDao.add(item);
			//职位
			String position = CommonUtil.getCellValue(row.getCell(3));
			if(StringUtils.isNotBlank(position)){
				List<Role> roleList = roleDao.getAllEnableRoles();
				for (Role role : roleList) {
					if(StringUtils.equals(role.getName(), position)){
						Map<String,String> map = new HashMap<String,String>();
						map.put("userId", item.getId());
						map.put("roleId", role.getId());
						userDao.addUserRole(map);
					}
				}
			}
		}
	}

	@Override
	public User getUserDetailByDepartId(String departId) {
		
		return null;
	}

 

}
