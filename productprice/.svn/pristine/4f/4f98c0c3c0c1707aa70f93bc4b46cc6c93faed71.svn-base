package com.intelligence.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.intelligence.common.constant.DateCon;
import com.intelligence.common.constant.StringCon;

public class DateUtils {

    /** 
     * 取当前系统日期，并按指定格式或者是默认格式返回 
     * 
     * @param format 
     * @return 
     */  
    public static String getNow(String format){
        return dateToString(new Date(), format);  
    }  
    
    /** 
     * 将字符型转换为指定格式日期型 
     * 
     * @param _date 需要转换成日期的字符串 
     * @param format 与需要转换成日期的字符串相匹配的格式 
     * @return 
     */  
    public static Date stringToDate(String dateStr,String format){
    	// 获取格式
        if(null == format || StringCon.EMPTY_STR.equals(format)) {  
            format = DateCon.yyyy_MM_dd_HH_mm_ss.getValue();  
        }  
        
        // 初始化转换类
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        
        // 转换为日期
        Date date=null;  
        try {  
            date=sdf.parse(dateStr);  
        } catch (ParseException e) {
        }  
        
        // 返回结果
        return date;  
    }  
    
    /** 
     * 将日期型转换为指定格式的字符串 
     * @param date 日期 
     * @param format 格式 
     * @return 
     */  
    public static String dateToString(Date date,String format){  
    	// 获取格式
        if(null == format || StringCon.EMPTY_STR.equals(format)) {  
            format = DateCon.yyyy_MM_dd_HH_mm_ss.getValue();  
        } 
        
        // 转换日期
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        return sdf.format(date);  
    } 
	
}
