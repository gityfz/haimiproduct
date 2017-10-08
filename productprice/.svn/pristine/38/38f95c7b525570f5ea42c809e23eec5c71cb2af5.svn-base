package com.intelligence.business.product.service;

import java.util.HashMap;

import com.intelligence.business.product.entity.Supplier;
import com.intelligence.common.exception.PowerException;

/**
 * @author 作者 E-mail: bzp
 * @date 创建时间：2017年1月13日 下午1:44:12
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public interface ISupplierService {
	/**
	 * 新增一条供应商信息
	 * @param supplierParam
	 * @return
	 */
	public Integer addSupplier(HashMap<String, Object> supplierParam) throws PowerException;

	/**
	 * 更新一条供应商信息
	 * @param params
	 * @return
	 */
	public Integer updateSupplier(HashMap<String, Object> params) throws PowerException;

	/**
	 * @param supplierId
	 * @return
	 */
	public Supplier selectSupplier(Integer supplierId) throws PowerException;

}
