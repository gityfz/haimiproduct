package com.intelligence.common.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import com.intelligence.common.constant.UrlCodeBook;
import com.intelligence.common.exception.ErrorCode;
import com.intelligence.common.exception.PowerException;
import com.intelligence.common.http.HttpClientTools;

public class RateUtils {

	/**
	 * 美元符号
	 */
	private final static String CURRENCY_USD = "USD";
	
	/**
	 * 人民币符号
	 */
	private final static String CURRENCY_CNY = "CNY";
	
	/**
	 * 欧元符号
	 */
	private final static String CURRENCY_EUR = "EUR";
	
	/**
	 * 韩元符号
	 */
	private final static String CURRENCY_KER = "KER";
	
	/**
	 * 日元符号
	 */
	private final static String CURRENCY_JPY = "JPY";
	
	/**
	 * 澳币符号
	 */
	private final static String CURRENCY_AUD = "AUD";
	
	/**
	 * 汇率标记
	 */
	private final static String CURRENCY_RATE_FLAG = "successed";
	
	/**
	 * 汇率密钥
	 */
	private final static String CURRENCY_RATE_KEY = "fd9eed05c57c531c97a054a529dd203e";
	
	/**
	 * 获取当前汇率
	 * 
	 * 
	 * @param fromCurreny
	 * @param toCurreny
	 * @return
	 * @throws PowerException
	 */
	public static BigDecimal getCurrentCurrency(String fromCurreny, String toCurreny) throws PowerException {
		HashMap<String, Object> iaoParam = new HashMap<String, Object>();
		iaoParam.put("key", CURRENCY_RATE_KEY);
		iaoParam.put("from", fromCurreny);
		iaoParam.put("to", toCurreny);
		HashMap<String, Object> iaoConfig = new HashMap<String, Object>();
		iaoConfig.put("paramFormat", HttpClientTools.getStringFormat());
		iaoConfig.put("resultFormat", HttpClientTools.getJsonFormat());
		try {
			HashMap<String, Object> iaoResult = HttpClientTools.get(UrlCodeBook.URL_CURRENCY_EXCHANGE.getUrl(), iaoParam, iaoConfig);
			if (CURRENCY_RATE_FLAG.equals(iaoResult.get("reason")) && null != iaoResult.get("result")) {
				@SuppressWarnings("unchecked")
				ArrayList<HashMap<String, Object>> result = (ArrayList<HashMap<String, Object>>)iaoResult.get("result");
				return new BigDecimal(result.get(0).get("result").toString());
			} else {
				return null;
			}
		} catch (ClassCastException e) {
			throw new PowerException(ErrorCode.CAST_FAIL.getErrorCode(), e);
		} catch (PowerException e) {
			throw e;
		}
	}

	/**
	 * @return the currencyUsd
	 */
	public static String getCurrencyUsd() {
		return CURRENCY_USD;
	}

	/**
	 * @return the currencyCny
	 */
	public static String getCurrencyCny() {
		return CURRENCY_CNY;
	}

	/**
	 * @return the currencyEur
	 */
	public static String getCurrencyEur() {
		return CURRENCY_EUR;
	}

	/**
	 * @return the currencyKer
	 */
	public static String getCurrencyKer() {
		return CURRENCY_KER;
	}

	/**
	 * @return the currencyJpy
	 */
	public static String getCurrencyJpy() {
		return CURRENCY_JPY;
	}

	/**
	 * @return the currencyAud
	 */
	public static String getCurrencyAud() {
		return CURRENCY_AUD;
	}

	/**
	 * @return the currencyRateFlag
	 */
	public static String getCurrencyRateFlag() {
		return CURRENCY_RATE_FLAG;
	}

	/**
	 * @return the currencyRateKey
	 */
	public static String getCurrencyRateKey() {
		return CURRENCY_RATE_KEY;
	}
	
}
