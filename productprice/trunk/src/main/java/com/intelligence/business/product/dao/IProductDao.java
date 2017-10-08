package com.intelligence.business.product.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.intelligence.business.product.entity.Product;
import com.intelligence.business.product.entity.ProductFilter;
import com.intelligence.common.exception.PowerException;

public interface IProductDao {

	/**
	 * 新增一条商品
	 * 
	 * 
	 * @param product
	 * @return
	 * @throws PowerException
	 */
	public Integer addProduct(HashMap<String, Object> product)
			throws PowerException;

	/**
	 * 新增一条详情
	 * 
	 * 
	 * @param detail
	 * @return
	 * @throws PowerException
	 */

	public Integer addDetail(HashMap<String, Object> detail) throws PowerException;


	/**
	 * 更新详情信息
	 * @param brandDetail
	 */
	public Integer updateDetail(HashMap<String, Object> brandDetail) throws PowerException;

	/**
	 * 获取商品数据
	 * 
	 * 
	 * @param productParam
	 * @return
	 * @throws PowerException
	 */
	public ArrayList<Product> queryProductData(HashMap<String, Object> productParam) throws PowerException;
	
	/**
	 * 获取前端商品数据
	 * 
	 * @param productParam
	 * @return
	 * @throws PowerException
	 */
	public HashMap<String, Object> queryFrontProducts(HashMap<String, Object> productParam) throws PowerException;
	
	/**
	 * 查询商品筛选项
	 * 
	 * @param productFilterParam
	 * @return
	 */
	public ArrayList<ProductFilter> queryProductFilter(HashMap<String, Object> productFilterParam) throws PowerException;
	
	/**
	 * 查询商品低级筛选项
	 * 
	 * @param productParam
	 * @return
	 */
	public ArrayList<ProductFilter> queryProductFilterLow(HashMap<String, Object> productParam) throws PowerException;
	
	/**
	 * 获取商品属性
	 * 
	 * @param productMasterId
	 * @return
	 */
	public ArrayList<HashMap<String, Object>> queryProductProp(Integer productMasterId) throws PowerException;
	
	/**
	 * 获取商品详情
	 * 
	 * @param productId
	 * @return
	 */
	public StringBuilder queryProductDetail(Integer detailId) throws PowerException;
	
	/**
	 * 新增组合商品子商品
	 * 
	 * @param productId
	 * @param subProductId
	 * @param subNum
	 * @param addTime
	 */
	public void addProductSub(HashMap<String, Object> productSubParam) throws PowerException;
	
	/**
	 * 查看商品是否是组合商品
	 * 
	 * @param productId
	 * @return
	 */
	public Integer queryProductIsGroup(Integer productId) throws PowerException;
	
	/**
	 * 删除组合商品子商品
	 * 
	 * @param productSubDbId
	 * @return
	 * @throws PowerException
	 */
	public Integer delProductSub(Integer productSubDbId) throws PowerException;
	
	/**
	 * 下线商品
	 * 
	 * @param productSubDbId
	 * @throws PowerException
	 */
	public void offlineProduct(Integer productId) throws PowerException;
	
	/**
	 * 更新商品
	 * 
	 * @param productParam
	 */
	public void updateProduct(HashMap<String, Object> productParam) throws PowerException;
	
	/**
	 * 更新通用详情
	 * 
	 * @param productId
	 */
	public void updateComDetail(StringBuilder detail, Integer detailId) throws PowerException;

	/**
	 * 新增商品分类映射关系记录
	 * @param Params
	 * @param Integer
	 * @return
	 */
	public Integer addProductCategory(HashMap<String, Object> Params)throws PowerException;

	/**
	 * 查询商品分类映射关系记录
	 * @param productId
	 * @param List
	 * @return
	 */
	public List<Integer> selectProductCategory(Integer peoductId) throws PowerException;

	/**
	 * 删除商品分类映射关系记录
	 * @param productId
	 * @return
	 */
	public void deleteProductCategory(HashMap<String,Object> params) throws PowerException;

	/**
	 * 主从商品复制
	 * @param masterMap
	 */
	public Integer masterProductCopy(HashMap<String, Object> masterMap) throws PowerException;

	/**
	 * 跨品类主从商品复制
	 * @param masterMap
	 */
	public Integer changeTypeProductCopy(HashMap<String, Object> masterMap) throws PowerException;

	//临时
	/**
	 * 查询商品信息
	 * @param valueOf
	 * @return
	 */
	public HashMap<String, Object> queryProduct(Integer valueOf) throws PowerException;
}
