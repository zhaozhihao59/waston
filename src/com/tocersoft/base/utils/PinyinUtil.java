package com.tocersoft.base.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 将汉字转为拼音输出
 * 
 * @author vinartis
 * @createDate Nov 18, 2013
 */
public class PinyinUtil {
	/**
	 * 将汉字转为拼音输出 不带声调
	 * 
	 * @param inputString
	 * @return
	 */
	public static String toPinyin(String inputString) {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		char[] input = inputString.trim().toCharArray();
		StringBuffer output = new StringBuffer("");
		try {
			for (int i = 0; i < input.length; i++) {
				if (Character.toString(input[i]).matches("[\u4E00-\u9FA5]+")) {
					String[] temp = PinyinHelper.toHanyuPinyinStringArray(
							input[i], format);
					output.append(temp[0]);
				} else {
					output.append(Character.toString(input[i]));
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return output.toString();
	}
}
