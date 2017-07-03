package com.tocersoft.base.utils;

/**
 * UUID 工具类
 * @author lenovo
 *
 */
public class UUIDUtil {

	/**
	 * 生成uuid 去除"-"
	 * @return
	 */
	 public static String uuid(){
 	       String uuid=java.util.UUID.randomUUID().toString();
 	       return uuid.replaceAll("-", "");
	 }
}
