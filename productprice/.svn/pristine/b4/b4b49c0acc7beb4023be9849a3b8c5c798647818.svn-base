/**
 * 
 */
package com.intelligence.common.utils;

import java.util.HashSet;

/**
 * @author p-sunhao
 *
 */
public class NumUtils {
	
	/**
	 * 获取无序的连续数字
	 * 
	 * @param start
	 * @param limit
	 * @return
	 */
	public static HashSet<Integer> generateContinueNumNoSeq(Integer start, Integer limit) {
		HashSet<Integer> setRe = new HashSet<Integer>();
		if (limit >= start) {
			for (int i = start, m = limit + 1; i < m; i++) {
				setRe.add(i);
			}
		}
		return setRe;
	}

	/**
	 * 转换数字
	 * 
	 * @param obj
	 * @return
	 */
	public static Integer toNum(Object obj) {
		try {
			return Integer.valueOf(obj.toString());
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 判断是不是数字
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNum(Object obj) {
		try {
			Integer.valueOf(obj.toString());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 判断是不是浮点数
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isDouble(Object obj) {
		try {
			Double.valueOf(obj.toString());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
