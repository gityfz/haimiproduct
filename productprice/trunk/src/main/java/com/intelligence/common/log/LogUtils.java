/**
 * 自定义封装日志java文件
 */
package com.intelligence.common.log;

/**
 * 自定义封装日志类
 * 
 * @author p-sunhao
 * @date 2014-06-03 15:44:30
 */
public class LogUtils {
	
	/**
	 * 常量空格
	 */
	public final static String SPACE = " ";
	
	/**
	 * 常量参数
	 */
	public final static String PARAMS = "参数：";
	
	/**
	 * 数据开始
	 */
	public final static int DATA_START = 0;
	
	/**
	 * 元素限制
	 */
	public final static int ELE_LIMIT = 100;
	
	/**
	 * 数据限制
	 */
	public final static int DATA_LIMIT = 200;
	
	/**
	 * SQL限制
	 */
	public final static int SQL_LIMIT = 500;
	
	/**
	 * 常规日志输出
	 * 
	 * @param msg
	 * @param objects
	 * @return
	 */
	public static String commonFormat(String msg, Object... objects) {
		StringBuffer logMsg = new StringBuffer(msg);
		logMsg.append(SPACE);
		logMsg.append(PARAMS);
		for (Object obj: objects) {
			logMsg.append(obj.toString());
			logMsg.append(SPACE);
		}
		return logMsg.toString();
	}
	
	/**
	 * 接口日志输出
	 * 
	 * @param msg
	 * @param objects
	 * @return
	 */
	public static String interfaceFormat(String msg, String url, String type, Object data) {
		StringBuffer logMsg = new StringBuffer(msg);
		logMsg.append(SPACE);
		logMsg.append(url);
		logMsg.append(SPACE);
		logMsg.append(type);
		logMsg.append(SPACE);
		logMsg.append(data.toString());
		
		return logMsg.toString();
	}
	
	/**
	 * SQL日志输出
	 * 
	 * @param msg
	 * @param objects
	 * @return
	 */
	public static String sqlFormat(String msg, String sql) {
		StringBuffer logMsg = new StringBuffer(msg);
		logMsg.append(SPACE);
		logMsg.append(sql.toString().substring(DATA_START, sql.toString().length() > ELE_LIMIT ? ELE_LIMIT : sql.toString().length()));
		return logMsg.toString();
	}
	
}