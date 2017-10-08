package com.intelligence.business.price.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intelligence.business.price.dao.IPriceUnitDao;
import com.intelligence.business.price.service.IPriceUnitService;

@Service
public class PriceUnitServiceImpl  implements IPriceUnitService{

	@Resource
	private IPriceUnitDao iPriceUnitDao;
	
	public String getPriceUnit(int id) {
		Map<String,Object> map = iPriceUnitDao.getCountryMap(id);
		return map.get("price_unit").toString();
	}

	public String getWeightUnit(int id) {
		Map<String,Object> map = iPriceUnitDao.getCountryMap(id);
		return map.get("weight_unit").toString();
	}

}
