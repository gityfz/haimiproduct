package com.intelligence.business.price.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import com.intelligence.business.price.entity.CurrencyRate;
import com.intelligence.common.exception.PowerException;

/**
 * 定时刷新汇率
 * @author 作者 E-mail:bzp
 * @date 创建时间：2017年1月10日 下午3:58:48
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public interface IPriceRateDao {
	
	/**
	 *  查询全部数据
	 *  
	 * @param currency_rate
	 * @param fromCurrency
	 */
	public void updateRate(BigDecimal currency_rate, String fromCurrency);
	
	/**
	 * 获取货币汇率
	 * 
	 * @return
	 */
	public ArrayList<CurrencyRate> queryCurrencyRate() throws PowerException;

	/**
	 * 批量更新汇率
	 * 
	 * @param tablename
	 * @param insertcolumn
	 * @param insertData
	 * @param defaultcolumn
	 * @param defaultdata
	 * @param addAttribute
	 * @throws PowerException
	 */
	public void updateCurrencyRateBatch(ArrayList<HashMap<String, Object>> insertData) throws PowerException;
	
}
