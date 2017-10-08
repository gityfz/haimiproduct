package com.intelligence.business.price.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intelligence.business.price.dao.IPriceUnitDao;
import com.intelligence.business.price.persistance.PriceUnitMapper;

@Repository
public class PriceUnitDaoImpl implements IPriceUnitDao{
	
	@Resource
	private PriceUnitMapper priceUnitMapper;
	
	
	public Map<String, Object> getCountryMap(int id) {
		return priceUnitMapper.getCountryMap(id);
	}
	
}
