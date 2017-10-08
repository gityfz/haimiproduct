package com.intelligence.business.product.dao.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.intelligence.business.product.dao.ISupplierDao;
import com.intelligence.business.product.entity.Supplier;
import com.intelligence.business.product.persistance.SupplierMapper;
import com.intelligence.common.exception.ErrorCode;
import com.intelligence.common.exception.PowerException;
import com.intelligence.common.log.LogUtils;

/**
 * @author 作者 E-mail:bzp
 * @date 创建时间：2017年1月13日 下午2:02:40
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Repository
public class SupplierDaoImpl implements ISupplierDao {
	/**
	 * 日志
	 */
	public final static Logger logger = LoggerFactory
			.getLogger(SupplierDaoImpl.class);

	@Resource
	private SupplierMapper supplierMapper;
	/**
	 * 新增一条供应商信息
	 */
	@Override
	public Integer addSupplier(HashMap<String, Object> supplierParam) throws PowerException{
		// TODO Auto-generated method stub
		try {
			// 日志
			logger.info(LogUtils.commonFormat("新增一条供应商信息", supplierParam));
			// 新增供应商
			supplierMapper.insertSupplier(supplierParam);
		} catch (Exception e) {
		    throw new PowerException("新增供应商信息异常",
				ErrorCode.ADD_FAIL.getErrorCode(), e);
		}
		// 返回主键
		return Integer.valueOf(supplierParam.get("supplierId").toString());
	}
	
	/**
	 * 更新供应商信息
	 */
	@Override
	public Integer updateSupplier(HashMap<String, Object> supplierParam) throws PowerException{
		// TODO Auto-generated method stub
		try {
			// 日志
			logger.info(LogUtils.commonFormat("更新一条供应商信息", supplierParam));
			// 更新供应商
			supplierMapper.updateSupplier(supplierParam);
		} catch (Exception e) {
		    throw new PowerException("更新供应商信息异常",
				ErrorCode.QUERY_FAIL.getErrorCode(), e);
		}
		// 返回主键
		return Integer.valueOf(supplierParam.get("supplierId").toString());
	}
	
	/**
	 * 查询供应商信息
	 */
	@Override
	public Supplier selectSupplier(Integer supplierId) throws PowerException{
		// TODO Auto-generated method stub
		try {
			// 日志
			logger.info(LogUtils.commonFormat("查询一条供应商信息", supplierId));
			// 更新供应商
			return supplierMapper.selectSupplier(supplierId);
		} catch (Exception e) {
		    throw new PowerException("更新供应商信息异常",
				ErrorCode.UPDATE_FAIL.getErrorCode(), e);
		}
	}

}
