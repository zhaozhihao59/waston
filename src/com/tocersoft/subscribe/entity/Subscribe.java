package com.tocersoft.subscribe.entity;
import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 订阅表
 * 
 * @creator
 * @create-time 2015-04-22 16:38:26
 */
public class Subscribe extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 邮箱 */
	private String email;
	/** 手机号码 */
	private String mobile;
	/** 订阅者姓名 */
	private String name;
	/** 公司名称 */
	private String companyName;
	/** 职务 */
	private String position;
	/** 订阅状态：0-正常订阅，1-退订 */
	private int state;
	/** 期刊类型ID（栏目ID） */
	private String channelId;
	/** 期刊类型名称 */
	private String channelName;

	/** 邮箱 */
	public String getEmail(){
		return this.email;
	}

	/** 邮箱 */
	public void setEmail(String email){
		this.email = email;
	}
	/** 手机号码 */
	public String getMobile(){
		return this.mobile;
	}

	/** 手机号码 */
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	/** 订阅者姓名 */
	public String getName(){
		return this.name;
	}

	/** 订阅者姓名 */
	public void setName(String name){
		this.name = name;
	}
	/** 公司名称 */
	public String getCompanyName(){
		return this.companyName;
	}

	/** 公司名称 */
	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}
	/** 职务 */
	public String getPosition(){
		return this.position;
	}

	/** 职务 */
	public void setPosition(String position){
		this.position = position;
	}
	/** 订阅状态：0-正常订阅，1-退订 */
	public int getState(){
		return this.state;
	}

	/** 订阅状态：0-正常订阅，1-退订 */
	public void setState(int state){
		this.state = state;
	}
	/** 期刊类型ID（栏目ID） */
	public String getChannelId(){
		return this.channelId;
	}

	/** 期刊类型ID（栏目ID） */
	public void setChannelId(String channelId){
		this.channelId = channelId;
	}
	/** 期刊类型名称 */
	public String getChannelName(){
		return this.channelName;
	}

	/** 期刊类型名称 */
	public void setChannelName(String channelName){
		this.channelName = channelName;
	}
}