package com.intelligence.business.product.service.impl;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.intelligence.business.product.dao.ISupplierDao;
import com.intelligence.business.product.entity.Supplier;
import com.intelligence.business.product.service.ISupplierService;
import com.intelligence.common.exception.PowerException;

/**
 * @author 作者 E-mail: bzp
 * @date 创建时间：2017年1月13日 下午1:48:17
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Service
public class SupplierServiceImpl implements ISupplierService {
	/**
	 * 日志
	 */
	public final static Logger logger = LoggerFactory
			.getLogger(SupplierServiceImpl.class);

	/**
	 * 供应商DAO
	 */
	@Resource
	private ISupplierDao iSupplierDao;
    /**
     * 新增一条供应商信息
     */
	@Override
	public Integer addSupplier(HashMap<String, Object> supplierParam)  throws PowerException{
		// TODO Auto-generated method stub
		// 获取当前时间
		try {
			Date currentTime = new Date();
			supplierParam.put("addTime", currentTime);
			return iSupplierDao.addSupplier(supplierParam);
		} catch (PowerException e) {
			throw e;
		}

	}
	
	/**
	 * 更新供应商
	 * 
	 * @param supplierParam
	 * @return
	 * @throws PowerException
	 */
	public Integer updateSupplier(HashMap<String, Object> supplierParam) throws PowerException{
	    try {
		//若没有传入参数，返回0
  		if(1>= supplierParam.size()){
  		    return 0;
  		}
		return iSupplierDao.updateSupplier(supplierParam);
		
	} catch (PowerException e) {
		throw e;
	}
	    
	}
	
	
	/**
	 * 查询供应商
	 * 
	 * @param supplierId
	 * @return Map<>
	 * @throws PowerException
	 */
	public Supplier selectSupplier(Integer supplierId) throws PowerException{
	    try {
		//若没有传入参数，返回0
		return iSupplierDao.selectSupplier(supplierId);
	} catch (PowerException e) {
		throw e;
	}
	    
	}


}
