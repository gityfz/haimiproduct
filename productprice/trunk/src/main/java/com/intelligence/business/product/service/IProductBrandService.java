package com.intelligence.business.product.service;

import java.util.HashMap;

import com.intelligence.business.product.entity.ProductBrand;
import com.intelligence.common.exception.PowerException;

public interface IProductBrandService {

	/**
	 * 新增品牌
	 * 
	 * @param brandParam
	 * @return
	 * @throws PowerException
	 */
	public Integer addProductBrand(HashMap<String, Object> brandParam) throws PowerException;
	
	/**
	 * 更新品牌
	 * 
	 * @param brandParam
	 * @return
	 * @throws PowerException
	 */
	public Integer updateProductBrand(HashMap<String, Object> brandParam) throws PowerException;

	/**
	 * 查询品牌
	 * @param brandId
	 * @return 
	 */
	public ProductBrand selectProductBrand(Integer brandId) throws PowerException;
}
