package com.intelligence.business.product.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.intelligence.business.product.entity.ProductType;
import com.intelligence.common.exception.PowerException;

public interface IProductTypeService {

	/**
	 * 新增品类
	 * 
	 * @param params
	 * @return
	 * @throws PowerException 
	 */
	public Integer addProductType(HashMap<String, Object> params) throws PowerException;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws PowerException
	 */
	public List<ProductType> getTypeChild(int id) throws PowerException;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws PowerException
	 */
	public ProductType getTypeParent(int id) throws PowerException;

	/**
	 * @param params
	 * @return
	 */
	public Integer updateProductType(HashMap<String, Object> params) throws PowerException;

	/**
	 * 根据二级或三级品类获取关联的品牌和供应商id集合
	 * @param productType
	 * @return
	 */
	public HashMap<String, Object> querySupBraList(int productType) throws PowerException;
	

}
