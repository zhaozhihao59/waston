package com.tocersoft.member.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.base.utils.DataUtil;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.email.service.SysMailService;
import com.tocersoft.member.dao.IMemberDao;
import com.tocersoft.member.dto.MemberCondition;
import com.tocersoft.member.entity.Member;
import com.tocersoft.member.service.IMemberService;

@Service("memberServiceImpl")
@Transactional(value = "transactionManager")
public class MemberServiceImpl implements IMemberService{

	@Resource(name = "memberDaoImpl")
	private IMemberDao memberDao;
	@Resource
	private SysMailService sysMailService;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listMemberByPage(PageResult<Member> pageResult,MemberCondition condition){
		int rows = memberDao.listMemberByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<Member> list = memberDao.listMemberByPage(rowBounds,condition);
		pageResult.setResult(list);
	}
	
	
	/**
	 * 用于自动补全控件
	 * key匹配如下字段：1）姓名，2）帐号，3）手机号，4）邮箱，5）微信号
	 * @param key 模糊查询关键词
	 */
	@Override
	public List<Member> listMemberByKey(String key) {
		List<Member> list = memberDao.listMemberByKey(key);
		return list;
	}
	
	/**
	 * 
	 *@param memberId
	 *@return
	 * <pre>
	 * method description :
	 *		以会员id查找所有同队的组员
	 * </pre>
	 * @author J.殷嘉健
	 * @date 2015年3月10日下午3:49:10
	 */
	@Override
	public List<Member> listMemberByTeamMember(String memberId) {
		return memberDao.listMemberByTeamMember(memberId);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 会员信息
	 */
	public Member getMemberById(String id){
		return memberDao.getMemberById(id);
	}

	/**
	 * 新增
	 * @param item 会员信息
	 */
	public void add(Member item){
		// 保存Member实体
		item.setCreateDate(new Date());
		item.setAccount(item.getEmail());
		// 加密密码
		String encPwd = DataUtil.encryptionPw(item.getPassword());
		item.setPassword(encPwd);
		
		memberDao.add(item);
		
	}
	
	/**
	 * 会员注册
	 * @param item 		会员信息
	 * @param applyId 	注册申请ID
	 */
	@Override
	public void regist(Member item, String applyId){
		item.setCreateDate(new Date());
		item.setAccount(item.getEmail());
		// 加密密码
		String encPwd = DataUtil.encryptionPw(item.getPassword());
		item.setPassword(encPwd);
		
		memberDao.add(item);
	}

	/**
	 * 修改
	 * @param item 会员信息
	 */
	public void update(Member item){
		// 更新实体
		item.setUpdateDate(new Date());
		memberDao.update(item);
		
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		memberDao.delByIds(ids);
	}

	/**
	 * 根据会员帐号查询
	 * @param 	account		会员帐号
	 * @return 	会员信息
	 */
	@Override
	public Member getMemberByAccount(String account) {
		return memberDao.getMemberByAccount(account);
	}
	
	/**
	 * 根据会员邮箱查询
	 * @param 	email		会员邮箱
	 * @return 	会员信息
	 */
	@Override
	public Member getMemberByEmail(String email) {
		return memberDao.getMemberByEmail(email);
	}

	/**
	 * 根据会员帐号、邮箱、手机与密码查询
	 * @param 	account		会员帐号、邮箱、手机
	 * @return 	会员信息
	 */
	@Override
	public Member getMemberByAccountPwd(String account, String password) {
		String encPwd = DataUtil.encryptionPw(password);
		return memberDao.getMemberByAccountPwd(account,encPwd);
	}
	
	/**
	 * 快速报名时生成会员
	 * @param item 会员信息
	 */
	@Override
	public void addMemberByEnroll(Member item) {
		// 保存Member实体
		item.setCreateDate(new Date());
		item.setAccount(item.getMobile());
		// 加密密码
		String encPwd = DataUtil.encryptionPw(item.getPassword());
		item.setPassword(encPwd);
		memberDao.add(item);
		
	}

	/**
	 * 发送激活邮件链接
	 * @param item
	 */
	public void sendUrlEmail(Member item){
		// 基础路径
		String baseUrl = WebUtils.getBasePath();
		// 邮件激活地址
		String activeEmailUrl = baseUrl + "register/activeEmail.htm?item.id="+item.getId();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("name",item.getName());
		data.put("activeEmailUrl", activeEmailUrl);
		sysMailService.sendEmail("1", "邮箱激活提醒",item.getEmail(), data);
	}

	/**
	 * 修改邮箱状态
	 * @param item
	 */
	public void updateEmailState(String memberId,Integer emailactive){
		memberDao.updateEmailState(memberId, emailactive);
	}
}

