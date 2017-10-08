package com.intelligence.business.product.dao;

import java.util.HashMap;
import java.util.List;

import com.intelligence.business.product.entity.ProductType;
import com.intelligence.common.exception.PowerException;

public interface IProductTypeDao {

	/**
	 * 新增商品品类
	 * @param productType
	 * @return
	 * @throws PowerException
	 */
    	public Integer addProductType(HashMap<String, Object> productType) throws PowerException;

	/**
	 * 根据品类编号和查询条件查询指定品类下级品类合集
	 * @param searchCondition 
	 * @param parseInt,searchCondition
	 * @param searchCondition
	 */
	public List<ProductType> getProductTypeChild(String startId, String endId, String searchCondition) throws PowerException;
	
	/**
	 * 获取指定品类编号的分类信息
	 * 
	 * @param productType
	 * @return ProductType
	 */
	public ProductType getProductType(int productType) throws PowerException;

	/**
	 * 更新商品品类记录
	 * @param params
	 * @return
	 */
	public Integer updateProductType(HashMap<String, Object> params) throws PowerException;

	/**
	 * 根据二级或三级品类获取关联的品牌和供应商id集合
	 * @param queryCondition
	 * @return
	 */
	public List<HashMap<String, Object>> querySupBraList(String queryCondition) throws PowerException;

	
	
}
