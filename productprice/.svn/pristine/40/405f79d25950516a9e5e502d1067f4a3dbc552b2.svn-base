package com.intelligence.business.product.dao;

import java.util.HashMap;

import com.intelligence.business.product.entity.Supplier;
import com.intelligence.common.exception.PowerException;

/**
 * @author 作者 ：bzp
 * @date 创建时间：2017年1月13日 下午1:58:33
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public interface ISupplierDao {
	/**
	 * 添加一个供应商信息
	 * @param supplierParam
	 * @return
	 * @throws PowerException 
	 */
	public Integer addSupplier(HashMap<String, Object> supplierParam) throws PowerException;

	/**
	 * 更新供应商信息
	 * @param supplierParam
	 * @return
	 */
	public Integer updateSupplier(HashMap<String, Object> supplierParam) throws PowerException;

	/**
	 * 查询供应商信息
	 * @param supplierId
	 * @return
	 */
	public Supplier selectSupplier(Integer supplierId) throws PowerException;

}
