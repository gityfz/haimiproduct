package com.intelligence.common.check;

public class TypeCheck {

	/**
	 * 检验整形
	 * 
	 * @param intParam
	 * @return
	 */
	public static boolean checkInteger(Object intParam) {
		boolean result = false;
		try {
			Integer.valueOf((Integer)intParam);
			result = true;
		} catch (Exception e) {	
		}
		return result;
	}
	
}
