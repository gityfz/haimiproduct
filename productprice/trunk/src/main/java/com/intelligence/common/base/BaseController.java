/**
	 * 与前台交互的基类java文件
 */
package com.intelligence.common.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.intelligence.common.constant.StringCon;
import com.intelligence.common.constant.SymbolConstant;
import com.intelligence.common.exception.ErrorCode;
import com.intelligence.common.exception.PowerException;
import com.intelligence.common.utils.JsonUtils;

/**
 * 与前台交互的基类
 * 
 * @author sunhao
 * @date 2014-08-27 16:59:55
 */
public class BaseController {
	
	/**
	 * 提交方式get
	 */
	private static final String SUBMIT_WAY_GET = "GET";
	
	/**
	 * 提交方式delete
	 */
	private static final String SUBMIT_WAY_DELETE = "DELETE";

	/**
	 * 获取参数
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws PowerException 
	 */
	public JSONObject getParams(HttpServletRequest request, HttpServletResponse response) throws PowerException {
		try {
			// 获取请求数据
			String requestData = null;
			if (SUBMIT_WAY_GET == request.getMethod() || SUBMIT_WAY_DELETE == request.getMethod()) {
				// get  delete方式
				requestData = request.getQueryString();
			} else {
				// 其他方式
				String line = null;
				BufferedReader reader = null;
				StringBuffer paramTemp = new StringBuffer();
				reader = request.getReader();
			    while ((line = reader.readLine()) != null) {
			    	paramTemp.append(line); 
			    }
			    requestData = paramTemp.toString();
			}
			requestData = java.net.URLDecoder.decode(requestData,"utf-8");
			
			// 转换成JSON
			return JSONObject.fromObject(requestData);
		} catch (IOException e) {
			throw new PowerException(ErrorCode.PARAMS_FAIL.getErrorCode());
		} catch (JSONException e) {
			throw new PowerException(ErrorCode.PARAMS_FAIL.getErrorCode());
		}
	}
	
	/**
	 * 获取hashmap形式的参数
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws PowerException
	 */
	public HashMap<String, Object> getMapParams(HttpServletRequest request, HttpServletResponse response) throws PowerException {
		try {
			// 获取请求数据
			String requestData = null;
			if (SUBMIT_WAY_GET == request.getMethod() || SUBMIT_WAY_DELETE == request.getMethod()) {
				// get  delete方式
				requestData = request.getQueryString();
			} else {
				// 其他方式
				String line = null;
				BufferedReader reader = null;
				StringBuffer paramTemp = new StringBuffer();
				reader = request.getReader();
			    while ((line = reader.readLine()) != null) {
			    	paramTemp.append(line); 
			    }
			    requestData = paramTemp.toString();
			}
			requestData = java.net.URLDecoder.decode(requestData,"utf-8");
			
			// 转换成JSON
			return JsonUtils.getMapFromJson(requestData);
		} catch (IOException e) {
			throw new PowerException(ErrorCode.PARAMS_FAIL.getErrorCode());
		} catch (JSONException e) {
			throw new PowerException(ErrorCode.PARAMS_FAIL.getErrorCode());
		}
	}
	
	/**
	 * 发送结果，可以设置所有选项
	 * 
	 * @param data
	 * @param success
	 * @param msg
	 * @param errorCode
	 */
	public void sendResult(HttpServletResponse response, Object data, Boolean success, String msg, Integer errorCode) {
		PrintWriter out = null;
		try {
			response.setContentType(StringCon.CONTENT_TYPE_TEXT_HTML_UTF8.getValue());
			out = response.getWriter();
			out.print(generateResult(data, success, msg, errorCode));
		} catch (IOException e) {
		} finally {
			try {
				out.flush();
				out.close();
			} finally {
				out = null;
			}
		}
	}
	
	/**
	 * 发送结果，只能设置data，默认成功
	 * 
	 * @param response
	 * @param data
	 */
	public void sendResult(HttpServletResponse response, Object data) {
		PrintWriter out = null;
		try {
			response.setContentType(StringCon.CONTENT_TYPE_TEXT_HTML_UTF8.getValue());
			out = response.getWriter();
			out.print(generateResult(data, SymbolConstant.getTrue(), ErrorCode.COMMON_SUCCESS.getErrorMessage(), ErrorCode.COMMON_SUCCESS.getErrorCode()));
		} catch (IOException e) {
		} finally {
			try {
				out.flush();
				out.close();
			} finally {
				out = null;
			}
		}
	}
	
	/**
	 * 生成结果
	 * 
	 * @param data
	 * @param success
	 * @param msg
	 * @param errorCode
	 * @return
	 * @throws JsonProcessingException 
	 */
	public String generateResult(Object data, Boolean success, String msg, Integer errorCode) throws JsonProcessingException {
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("errorCode", errorCode);
		result.put("msg", msg);
		result.put("success", success);
		result.put("data", data);
		return JsonUtils.getJsonFromObject(result);
	}
	
}
