/**
 * 
 */
package com.intelligence.common.constant;

/**
 * @author p-sunhao
 *
 */
public enum DateCon {

	// 年月日时分秒
	yyyy_MM_dd_HH_mm_ss("yyyy-MM-dd HH:mm:ss"),
	
	// 年月日时分秒汉字
	yyyy_MM_dd_HH_mm_ss_CH("yyyy年MM月dd日  hh点:mm分:ss秒"),
	
	// 年月日		
	yyyy_MM_dd("yyyy-MM-dd"),
	
	// 年月日汉字		
	yyyy_MM_dd_CH("yyyy年MM月dd日");
		
	// 字符值
	private String value = null;

    /**
     * 字符串枚举类构造函数
     * 
     * @param id
     * @param message
     */
	DateCon(String val) {
    	this.value = val;
    }

	/**
     * @return the value
     */
    public String getValue() {
        return this.value;
    }


}