package com.intelligence.business.price.service;

import java.math.BigDecimal;
import java.util.HashMap;

import com.intelligence.business.price.entity.ProductUserPrice;
import com.intelligence.common.exception.PowerException;

public interface IPriceInterfaceService {

	/**
	 * 获取用户价格配置
	 * 
	 * @return
	 * @throws PowerException
	 */
	public HashMap<String, HashMap<String, ProductUserPrice>> getUserPriceConfig(Integer userId) throws PowerException;
	
	/**
	 * 根据国家获取汇率
	 * 
	 * @param countryId
	 * @return
	 * @throws PowerException
	 */
	public HashMap<Integer, BigDecimal> getRateByCountry() throws PowerException;
	
}
