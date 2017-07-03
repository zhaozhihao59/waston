package com.tocersoft.member.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tocersoft.member.entity.Member;
import com.tocersoft.base.page.PageResult;
import com.tocersoft.member.dto.MemberCondition;

public interface IMemberService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listMemberByPage(PageResult<Member> pageResult,MemberCondition condition);
	
	/**
	 * 用于自动补全控件
	 * key匹配如下字段：1）姓名，2）帐号，3）手机号，4）邮箱，5）微信号
	 * @param key 模糊查询关键词
	 */
	List<Member> listMemberByKey(String key);
	
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
	List<Member> listMemberByTeamMember(String memberId);

	/**
	 * 根据会员帐号查询
	 * @param 	account		会员帐号
	 * @return 	会员信息
	 */
	Member getMemberByAccount(String account);
	
	/**
	 * 根据会员邮箱查询
	 * @param 	email		会员邮箱
	 * @return 	会员信息
	 */
	Member getMemberByEmail(String email);
	
	/**
	 * 根据会员帐号、邮箱、手机与密码查询
	 * @param 	account		会员帐号、邮箱、手机
	 * @return 	会员信息
	 */
	Member getMemberByAccountPwd(String account, String password);
	
	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 会员信息
	 */
	Member getMemberById(String id);

	/**
	 * 新增
	 * @param item 会员信息
	 */
	void add(Member item);
	
	/**
	 * 快速报名时生成会员
	 * @param item 会员信息
	 */
	void addMemberByEnroll(Member item);
	
	/**
	 * 会员注册
	 * @param item 		会员信息
	 * @param applyId 	注册申请ID
	 */
	void regist(Member item, String applyId);

	/**
	 * 修改
	 * @param item 会员信息
	 */
	void update(Member item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);
	
	/**
	 * 发送激活邮件链接
	 * @param item
	 */
	void sendUrlEmail(Member item);
	
	/**
	 * 修改邮箱状态
	 * @param item
	 */
	void updateEmailState(String memberId,Integer emailactive);
}

