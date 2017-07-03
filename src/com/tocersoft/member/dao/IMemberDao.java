package com.tocersoft.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.member.entity.Member;
import com.tocersoft.member.dto.MemberCondition;

@Repository("memberDaoImpl")
public interface IMemberDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<Member> listMemberByPage(RowBounds bounds,@Param("condition") MemberCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listMemberByPageCount(@Param("condition") MemberCondition condition);
	
	/**
	 * 用于自动补全控件
	 * key匹配如下字段：1）姓名，2）帐号，3）手机号，4）邮箱，5）微信号
	 * @param key 模糊查询关键词
	 */
	List<Member> listMemberByKey(@Param("key")String key);

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
	List<Member> listMemberByTeamMember(@Param("memberid")String memberId);
	
	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 会员信息
	 */
	Member getMemberById(@Param("id")String id);
	
	/**
	 * 根据会员帐号查询
	 * @param 	account		会员帐号
	 * @return 	会员信息
	 */
	Member getMemberByAccount(@Param("account")String account);
	
	/**
	 * 根据会员邮箱查询
	 * @param 	email		会员邮箱
	 * @return 	会员信息
	 */
	Member getMemberByEmail(@Param("email")String email);
	
	/**
	 * 
	 *@param mobile
	 *@return Member
	 * <pre>
	 * method description :
	 *		根据会员手机号查询
	 * </pre>
	 * @author J.殷嘉健
	 * @date 2015年3月10日下午4:29:43
	 */
	Member getMemberByMobile(@Param("mobile")String mobile);

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
	 * 根据会员帐号、邮箱、手机与密码查询
	 * @param 	account		会员帐号、邮箱、手机
	 * @return 	会员信息
	 */
	Member getMemberByAccountPwd(@Param("account")String account, @Param("password")String password);
	
	/**
	 * 修改邮箱状态
	 * @param item
	 */
	void updateEmailState(@Param("memberId")String memberId,@Param("emailactive")Integer emailactive);
}

