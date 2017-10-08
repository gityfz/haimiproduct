package com.intelligence.business.product.dao;

import java.util.HashMap;
import java.util.Map;

import com.intelligence.business.product.entity.ProductBrand;
import com.intelligence.common.exception.PowerException;

public interface IProductBrandDao {

	/**
	 * 新增一条品牌
	 * 
	 * 
	 * @param brand
	 * @return
	 */
	public Integer addProductBrand(HashMap<String, Object> brand) throws PowerException;
	
	/**
	 * 根据id获取品牌信息
	 * 
	 * 
	 * @param brand
	 * @return
	 */
	public ProductBrand selectBrandById(int id) throws PowerException;

	/**
	 * 更新品牌信息
	 * @param brandParam
	 * @return
	 */
	public Integer updateProductBrand(HashMap<String, Object> brandParam) throws PowerException;

	/**
	 * 查询品牌信息
	 * @param brandId
	 * @return
	 */
	public ProductBrand selectProductBrand(Integer brandId) throws PowerException;
	
}
