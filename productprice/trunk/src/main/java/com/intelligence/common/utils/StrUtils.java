/**
 * 
 */
package com.intelligence.common.utils;


/**
 * @author p-sunhao
 *
 */
public class StrUtils {
	
	/**
	 * 判断是不是英文字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isEnglish(String str) {
		str = str.replaceAll(" ", "");
		return str.matches("[0-9a-zA-Z]*");
	}
	
}
