package com.intelligence.business.price.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.intelligence.business.price.entity.ProductUserPrice;
import com.intelligence.business.price.service.IPriceInterfaceService;
import com.intelligence.business.price.service.IPriceRateService;
import com.intelligence.business.price.service.IProductUserPriceService;
import com.intelligence.common.exception.PowerException;

@Service
public class PriceInterfaceServiceImpl implements IPriceInterfaceService {

	/**
	 * 日志
	 */
	public final static Logger logger = LoggerFactory.getLogger(PriceInterfaceServiceImpl.class);
	
	@Resource
	private IProductUserPriceService iProductUserPriceService;
	
	@Resource
	private IPriceRateService iPriceRateService;
	
	/**
	 * 获取用户价格配置
	 * 
	 * @return
	 * @throws PowerException
	 */
	public HashMap<String, HashMap<String, ProductUserPrice>> getUserPriceConfig(Integer userId) throws PowerException {
		try {
			return iProductUserPriceService.getUserPriceConfig(userId);
		} catch (PowerException e) {
			throw e;
		}
	}
	
	/**
	 * 根据国家获取汇率
	 * 
	 * @param countryId
	 * @return
	 * @throws PowerException
	 */
	public HashMap<Integer, BigDecimal> getRateByCountry() throws PowerException {
		try {
			return iPriceRateService.getRateByCountry();
		} catch (PowerException e) {
			throw e;
		}
	}
	
}
