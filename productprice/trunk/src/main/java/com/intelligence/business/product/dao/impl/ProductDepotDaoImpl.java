package com.intelligence.business.product.dao.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.intelligence.business.product.dao.IProductDepotDao;
import com.intelligence.business.product.persistance.ProductDepotMapper;
import com.intelligence.common.exception.ErrorCode;
import com.intelligence.common.exception.PowerException;
import com.intelligence.common.log.LogUtils;

/**
 * @author Zhengyf
 *
 * @date 2017年3月9日
 */
@Repository
public class ProductDepotDaoImpl implements IProductDepotDao{
    /**
	 * 日志
	 */
	public final static Logger logger = LoggerFactory
			.getLogger(ProductDepotDaoImpl.class);

	@Resource
	private ProductDepotMapper productDepotMapper;
	/**
	 * 根据国家id查询仓库集合
	 */
    public List<HashMap<String,Object>> queryDepotByCountry(int countryId)  throws PowerException {
	//记录日志
	logger.info(LogUtils.commonFormat("根据国家id查询仓库集合", countryId));
	try {
	    return productDepotMapper.queryDepotByCountry(countryId);
	}catch(Exception e){
	    throw new PowerException("根据国家id查询仓库集合",
			ErrorCode.QUERY_FAIL.getErrorCode(), e);
	}
    }

}
