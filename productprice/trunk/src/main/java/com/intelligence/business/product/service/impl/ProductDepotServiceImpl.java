package com.intelligence.business.product.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.intelligence.business.product.dao.IProductDepotDao;
import com.intelligence.business.product.service.IProductDepotService;
import com.intelligence.common.exception.PowerException;

/**
 * @author Zhengyf
 *
 * @date 2017年3月9日
 */
@Service
public class ProductDepotServiceImpl implements IProductDepotService{
    /**
	 * 日志
	 */
	public final static Logger logger = LoggerFactory
			.getLogger(ProductDepotServiceImpl.class);
	
	
	@Resource
	private IProductDepotDao iProductDepotDao;

	/**
	 * 根据国家id查询仓库集合
	 */
	public ArrayList<Object> queryDepotByCountry(int countryId) throws PowerException {
	    try{
	    List<HashMap<String,Object>> depotList =  iProductDepotDao.queryDepotByCountry(countryId);
	    ArrayList<Object> returnList = new ArrayList<Object>();
	    for(HashMap<String,Object> map:depotList){
		if(map.get("depotId") != null){
		    returnList.add(map.get("depotId"));
		}
	    }
	    return returnList;
	    }catch(PowerException e){
		throw e;
	    }
	}
}
