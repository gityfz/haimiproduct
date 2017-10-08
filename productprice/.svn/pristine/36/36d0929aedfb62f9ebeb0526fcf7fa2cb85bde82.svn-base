/**
 * 通用异常java文件
 * 
 * @author p-sunhao
 * @date 2013-11-26
 */
package com.intelligence.common.exception;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intelligence.common.log.LogUtils;

/**
 * 通用异常java类
 * 
 * @author p-sunhao
 */
public class PowerException extends Exception {
	
	/**
	 * 生成的序列号
	 */
	private static final long serialVersionUID = 5862208217729019852L;
	
	/**
	 * 日志类
	 */
	public final static Logger logger = LoggerFactory.getLogger(PowerException.class);

	/**
	 * 错误编码
	 */
	private Integer errorCode = null;
	
	/**
	 * 错误说明
	 */
	private String errorMsg = null;
	
	/**
	 * 错误线程
	 */
	private Throwable errorCause = null;
	
	/**
	 * 错误数据
	 */
	private Object errorData = null;
	
	/**
	 * 默认构造函数
	 */
	public PowerException() {
		super();
		// 设置默认异常编码
		this.setErrorCode(ErrorCode.COMMON_FAIL.getErrorCode());
		// 设置默认异常信息
		this.setErrorMsg(ErrorCode.COMMON_FAIL.getErrorMessage());
	}
	
	/**
	 * 初始化异常信息的构造函数
	 * 
	 * @param errorMsg 
	 * 			java.lang.String
	 */
	public PowerException(String errorMsg) {
		super();
		logger.error(LogUtils.commonFormat(errorMsg));
		// 设置默认异常编码
		this.setErrorCode(ErrorCode.COMMON_FAIL.getErrorCode());
		// 设置异常信息
		this.setErrorMsg(errorMsg);
	}
	
	/**
	 * 初始化异常编码的构造函数
	 * 
	 * @param errorCode 
	 * 			java.lang.Long
	 */
	public PowerException(Integer errorCode) {
		super();
		logger.error(LogUtils.commonFormat(errorCode.toString()));
		// 设置异常编码
		this.setErrorCode(errorCode);
	}

	/**
	 * 设置异常线程的构造函数
	 * 
	 * @param errorCause 
	 * 			java.lang.Throwable
	 */
	public PowerException(Throwable errorCause) {
		super();
		logger.error(LogUtils.commonFormat(ErrorCode.COMMON_FAIL.getErrorMessage(), errorCause));
		// 设置异常线程
		this.setErrorCause(errorCause);
	}
	
	/**
	 * 设置异常信息和异常编码的构造函数
	 * 
	 * @param errorMsg 
	 * 			java.lang.String
	 * @param errorCode 
	 * 			java.lang.Long
	 */
	public PowerException(String errorMsg, Integer errorCode) {
		super();
		logger.error(LogUtils.commonFormat(errorMsg, errorCode));
		// 设置异常信息
		this.setErrorMsg(errorMsg);
		// 设置异常编码
		this.setErrorCode(errorCode);
	}
	
	/**
	 * 设置异常信息和异常线程的构造函数
	 * 
	 * @param errorMsg 
	 * 			java.lang.String
	 * @param errorCause 
	 * 			java.lang.Throwable
	 */
	public PowerException(String errorMsg, Throwable errorCause) {
		super();
		logger.error(LogUtils.commonFormat(errorMsg, errorCause));
		// 设置异常信息
		this.setErrorMsg(errorMsg);
		// 设置异常线程
		this.setErrorCause(errorCause);
	}
	
	/**
	 * 设置异常编码和异常线程的构造函数
	 * 
	 * @param errorCode 
	 * 			java.lang.Long
	 * @param errorCause 
	 * 			java.lang.Throwable
	 */
	public PowerException(Integer errorCode, Throwable errorCause) {
		super();
		logger.error(LogUtils.commonFormat(errorCode.toString(), errorCause));
		// 设置异常编码
		this.setErrorCode(errorCode);
		// 设置异常线程
		this.setErrorCause(errorCause);
	}
	
	/**
	 * 设置异常信息，异常编码和异常线程的构造函数
	 * 
	 * @param errorMsg 
	 * 			java.lang.String
	 * @param errorCode 
	 * 			java.lang.Long
	 * @param errorCause 
	 * 			java.lang.Throwable
	 */
	public PowerException(String errorMsg, Integer errorCode, Throwable errorCause) {
		super();
		logger.error(LogUtils.commonFormat(errorMsg, errorCode, errorCause));
		// 设置异常信息
		this.setErrorMsg(errorMsg);
		// 设置异常编码
		this.setErrorCode(errorCode);
		// 设置异常线程
		this.setErrorCause(errorCause);
	}
	
	/**
	 * 获取异常message
	 * 
	 * @return
	 * 			java.lang.String
	 */
	@Override
	public String getMessage() {
		// 返回父类的异常信息
		return super.getMessage();
	}

	/**
	 * 获取异常堆栈
	 * 
	 * @return
	 * 			java.lang.StackTraceElement	
	 */
	@Override
	public StackTraceElement[] getStackTrace() {
		// 返回父类的异常堆栈
		return super.getStackTrace();
	}

	/**
	 * 获取异常线程的string形态
	 * 
	 * @return
	 * 			java.lang.String
	 */
	@Override
	public String toString() {
		// 返回异常线程的string形态
		return super.toString();
	}

	/**
	 * 生成异常结果
	 * 
	 * @return
	 */
	public JSONObject generateExcepResult() {
		JSONObject result = new JSONObject();
		result.put("errorCode", this.getErrorCode());
		result.put("msg", this.getErrorMsg());
		result.put("success", false);
		result.put("data", null);
		return result;
	}
	
	/**
	 * 获得errorCode
	 *
	 * @return errorCode
	 * 			Long
	 */
	public Integer getErrorCode() {
		return errorCode;
	}

	/**
	 * 设置errorCode
	 *
	 * @param errorCode 
	 *			Long
	 */
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * 获得errorMsg
	 *
	 * @return errorMsg
	 * 			String
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * 设置errorMsg
	 *
	 * @param errorMsg 
	 *			String
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * 获得errorCause
	 *
	 * @return errorCause
	 * 			Throwable
	 */
	public Throwable getErrorCause() {
		return errorCause;
	}

	/**
	 * 设置errorCause
	 *
	 * @param errorCause 
	 *			Throwable
	 */
	public void setErrorCause(Throwable errorCause) {
		this.errorCause = errorCause;
	}

	/**
	 * @return the errorData
	 */
	public Object getErrorData() {
		return errorData;
	}

	/**
	 * @param errorData the errorData to set
	 */
	public void setErrorData(Object errorData) {
		this.errorData = errorData;
	}
	
}