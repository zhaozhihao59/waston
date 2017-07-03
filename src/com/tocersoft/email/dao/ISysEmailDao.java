package com.tocersoft.email.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tocersoft.email.entity.SysEmail;

@Repository
public interface ISysEmailDao {

	/**
	 * 保存Email信息
	 * 
	 * @param email
	 *            邮件实体
	 */
	public void add(SysEmail email);

	public List<SysEmail> listTenNoSendMail();
	
	public void updateEmailState(@Param("id")String id,@Param("state")Integer state);

}