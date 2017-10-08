package com.intelligence.business.price.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.intelligence.business.price.dao.IPriceRateDao;
import com.intelligence.business.price.entity.CurrencyRate;
import com.intelligence.business.price.persistance.PriceRateMapper;
import com.intelligence.common.exception.ErrorCode;
import com.intelligence.common.exception.PowerException;
import com.intelligence.common.log.LogUtils;

/**
 * 定时刷新汇率
 * @author 作者 E-mail: bzp
 * @date 创建时间：2017年1月10日 下午3:58:01
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Component
@Repository
public class PriceRateDaoImpl implements IPriceRateDao {
	
	/**
	 * 日志
	 */
	public final static Logger logger = LoggerFactory.getLogger(PriceRateDaoImpl.class);

	/**
	 * 价格汇率映射
	 */
	@Resource
	private PriceRateMapper priceRateMapper;

	@Override
	public void updateRate(BigDecimal currency_rate, String fromCurrency) {
		// TODO Auto-generated method stub
		priceRateMapper.updateRate(currency_rate, fromCurrency);
	}
	
	/**
	 * 获取货币汇率
	 * 
	 * @return
	 */
	public ArrayList<CurrencyRate> queryCurrencyRate() throws PowerException {
		// 日志
		logger.info(LogUtils.commonFormat("获取货币汇率"));
		try {
			return priceRateMapper.queryCurrencyRate();
		} catch (Exception e) {
			throw new PowerException("获取货币汇率异常", ErrorCode.QUERY_FAIL.getErrorCode(), e);
		}
	}
	
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
	public void updateCurrencyRateBatch(ArrayList<HashMap<String, Object>> insertData) throws PowerException {
		// 日志
		logger.info(LogUtils.commonFormat("获取货币汇率", insertData));
		try {
			// 表名
			String tablename = "currency_rate";
			// 常用字段
			HashSet<String> insertcolumn = new HashSet<String>();
			insertcolumn.add("id");
			insertcolumn.add("currency_rate");
			
			// 批量更新汇率
			priceRateMapper.updateCurrencyRateBatch(tablename, insertcolumn, insertData, null, null, null);
		} catch (Exception e) {
			throw new PowerException("批量更新汇率异常", ErrorCode.QUERY_FAIL.getErrorCode(), e);
		}
	}

}
