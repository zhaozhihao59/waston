package com.tocersoft.base.tags;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 如果传入值为null或空字符串 用于在页面输出默认值 “-”
 * 
 * @author vinartis
 * @createDate Aug 5, 2013
 */
public class DefaultValue {

	@SuppressWarnings("unchecked")
	public static Object d(Object input) {
		if (input == null) {
			return "-";
		}
		if (input instanceof String && StringUtils.isBlank(input.toString())) {
			return "-";
		}
		if (input instanceof List) {
			List list = (List) input;
			if (list.size() == 0) {
				return "-";
			}
		}
		return input;
	}
	
	public static String en(String input) {
		if(StringUtils.isNotBlank(input)){
			try {
				return URLEncoder.encode(input, "utf-8");
			} catch (UnsupportedEncodingException e) {
				return input;
			}
		}
		return input;
	}

	/**
	 * 文本截取，按照给定的长度截取字符，一个中文占两个英文字符宽度
	 * 
	 * @param input
	 *            输入文本
	 * @param maxLen
	 *            英文字符宽度，最大值
	 * @return
	 */
	public static Object e(String input, Integer maxLen) {
		if (StringUtils.isNotBlank(input)) {
			int len = 0;
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < input.length(); i++) {
				char c = input.charAt(i);
				if (isLetter(c)) {
					len = len + 1;
				} else {
					len = len + 2;
				}
				if (len > maxLen) {
					break;
				}
				sb.append(c);
			}
			if (len <= maxLen) {
				return input;
			}
			return sb.toString() + "...";
		} else {
			return input;
		}
	}
	
	/**
	 * 判断是不是字母数字或单字节字符
	 * @param c
	 * @return
	 */
	public static boolean isLetter(char c){
		int k = 0x80;
		return c/k==0?true:false;
	}
}
