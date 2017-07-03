package com.tocersoft.base.utils;

/**
 * 系统中的常量
 * 
 * @author tocersoft
 * 
 */
public interface Constant {
	
	/**
	 * 当前系统语言
	 */
	public static final String SYSTEM_LANGUAGE="SYSTEM_LANGUAGE";
	/**
	 * 后台管理系统admin账号
	 */
	public static final String ADMIN_USER="admin";
	/**
	 * 网站基准路径
	 */
	public static final String BASE_PATH = "BASE_PATH";
	/**
	 * 后台 当前在线员工
	 */
	public static final String CURRENT_OPERATOR = "CURRENT_OPERATOR";
	/**
	 * 当前在线的教师
	 */
	public static final String CURRENT_TEACHER="CURRENT_TEACHER";
	/**
	 * 当前在线的学生
	 */
	public static final String CURRENT_STUDENT="CURRENT_STUDENT";
	/**
	 * 操作系统名称
	 */
	public static final String OS_NAME = "OS_NAME";
	/**
	 * 前台登录用户session key
	 */
	public static final String FRONT_USER = "user";
	/**
	 * cpu信息
	 */
	public static final String CPU_INFO = "CPU_INFO";
	/**
	 * 操作系统版本
	 */
	public static final String OS_VERSION = "OS_VERSION";
	/**
	 * jvm版本
	 */
	public static final String JAVA_VM_VERSION = "JAVA_VM_VERSION";
	/**
	 * jdk版本
	 */
	public static final String JAVA_VERSION = "JAVA_VERSION";
	/**
	 * servlet版本
	 */
	public static final String SERVLET_VERSION = "SERVLET_VERSION";
	/** 国际化 */
	public static final String WW_TRANS_I18N_LOCALE = "WW_TRANS_I18N_LOCALE";
	
	/** 数据的状态 正常*/
	public static final Integer STATE_ENABLED=1;
	
	/** 数据的状态 禁用*/
	public static final Integer STATE_DISABLED=0;

	/**
	 * 显示栏目列表排列图案
	 */
	public static final String CHANNEL_LEVEL_PIC = "|- ";

	/**
	 * HTML空白符
	 */
	public static final String HTML_BLANK = "&nbsp;";

	/**
	 * 连接符号
	 */
	public static final String SYMBOL_JOIN = "-";
	
	/**
	 * 创建人默认值
	 */
	public static final String CREATE_BY_DEFAULT = "系统创建";
	/**
	 * 更新人默认值
	 */
	public static final String UPDATE_BY_DEFAULT = "系统更新";
	
	/**
	 * 管理员ID 
	 */
	public static final String MANAGER_ADMIN_ID = "dd00e9479ed34a199281abc043003f9b";
	
	/**
	 * 网站版本号
	 */
	public static final String SYS_VERSION = "sysVersion";
	
	/**
	 * 前台登录用户session key
	 */
//	public static final String FRONT_USER = "user";
	
	/** 
	 * 用于表示一个请求是否是ajax请求 
	 */
	public static final String XMLHTTPREQUEST = "XMLHttpRequest";
	
	/** 验证码session key* */
	public static final String CAPTCHA_CODE = "captcha_code";
	public static final String JAPTCHACODE = "JAPTCHACODE";
	/** 手机验证码 */
	public static final String MOBILE_CAPTCHA = "MOBILE_CAPTCHA";
	/** 接收手机验证码的手机 */
	public static final String MOBILE = "MOBILE";
	/** 默认密码 */
	public static final String DEFAULTPASSWORD = "123456";
	
	/** 工作性质 - NPS */
	public static final String WORK_TYPE_NPS = "网优";
	/** 工作性质 - CARE */
	public static final String WORK_TYPE_CARE = "CARE";
	/** 工作性质 - NI */
	public static final String WORK_TYPE_NI = "NI";
	
	/** 费用类型 */
	public static final String COST_TYPE = "36e0f9e665ef11e3b53400e04dbb1c03";
	/** 请假类型 */
	public static final String LEAVE_TYPE = "1d0af17075b211e38df600e04dbb1c03";
	
	/** 审批类型-请假审批 */
	public static final String AUDIT_TYPE_VACATION = "f1cdb21e678711e3b53400e04dbb1c03";
	/** 审批类型-报销审批 */
	public static final String AUDIT_TYPE_REIMBURSEMENT = "f1ce1cae678711e3b53400e04dbb1c03";
	/** 审批类型-项目日报审批 */
	public static final String AUDIT_TYPE_DAYREPORT = "f1ce3004678711e3b53400e04dbb1c03";
	/** 审批类型-出差审批 */
	public static final String AUDIT_TYPE_TRAVEL = "104b0588776011e38df600e04dbb1c03";
	/** 审批类型-工资卡申请审批*/
	public static final String AUDIT_TYPE_SALARY_CARD = "a2a5ae2c7e7e11e3b09f00e04dbb1c03";
	/** 审批类型-招聘申请审批 */
	public static final String AUDIT_TYPE_RECRUITMENT = "b4cda0e67f1911e3b09f00e04dbb1c03";
	
	/** 角色类型-项目经理 */
	public static final String ROLE_TYPE_PRJ_MANAGER = "1a953866692f11e39f4ef0def1afa7a1";
	
	/**
	 * 单据类型
	 * @author      miaoshuai
	 * @email       miaoshuai@tocersfot.com
	 * @company		www.tocersoft.com
	 * @create-time 2013-12-29 下午03:19:45
	 * @version     1.0
	 */
	public enum ApproveType {
		Leave, Reimbursement, DayReport, Travel,SalaryCard,Recruitment
	}
	
	/** visa折扣代码 */
	public static final String VISA_DISCOUNT_CODE = "2014APOFFER";
	
	/** PayPal接口返回Token */
	public static final String PAYPAL_TOKEN = "PayPal_Token";
	/** 正在支付的订单ID */
	public static final String CURRENT_PAY_ORDER_ID = "Current_Pay_Order_id";
}
