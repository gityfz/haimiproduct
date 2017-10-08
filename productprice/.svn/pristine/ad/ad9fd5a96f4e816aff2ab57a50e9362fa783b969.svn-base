package com.intelligence.business.product.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.intelligence.common.exception.ErrorCode;
import com.intelligence.common.exception.PowerException;

public interface IProductService {

	/**
	 * 新增商品
	 * 
	 * @param productParam
	 * @return
	 * @throws PowerException
	 */
	public Integer addProduct(HashMap<String, Object> productParam) throws PowerException;
	
	/**
	 * 新增组合商品子商品
	 * 
	 * @param productParam
	 * @throws PowerException
	 */
	public boolean addProductSub(HashMap<String, Object> productParam) throws PowerException;
	
	/**
	 * 获取商品信息
	 * 
	 * @param params
	 * @return
	 * @throws PowerException
	 */
	public HashMap<String, Object> getProductInfo(HashMap<String, Object> params) throws PowerException;
	
	/**
	 * 根据分类查询品类和规格
	 * 
	 * @param params
	 * @return
	 * @throws PowerException
	 */
	public HashMap<String, ArrayList<HashMap<String, Object>>> getFrontProps(HashMap<String, Object> params) throws PowerException;
	
	/**
	 * 获取前端商品详情
	 * 
	 * @param params
	 * @return
	 * @throws PowerException
	 */
	public HashMap<String, Object> getFrontProductDetail(HashMap<String, Object> params) throws PowerException;
	
	/**
	 * 删除组合商品子商品
	 * 
	 * @param params
	 * @return
	 * @throws PowerException
	 */
	public Enum<ErrorCode> delProductSub(HashMap<String, Object> params) throws PowerException;
	
	/**
	 * 更新商品
	 * 
	 * @param params
	 * @throws PowerException
	 */
	public void updateProduct(HashMap<String, Object> params) throws PowerException;
	
	/**
	 * 查看商品詳情
	 * 
	 * @param params
	 * @return
	 * @throws PowerException
	 */
	public HashMap<String, Object> getProductDetailInfo(Integer productId) throws PowerException;
	
	/**
	 * 新增商品分类关系
	 * @param params
	 * @return
	 */
	public Integer addProductCategory(HashMap<String, Object> params) throws PowerException;
	
	/**
	 * 查询商品分类关系
	 * @param productId
	 * @return
	 */
	public List<Integer> selectProductCategory(Integer productId) throws PowerException;

	/**
	 * 更新商品分类关系
	 * @param params
	 * @return
	 */
	public void updateProductCategory(HashMap<String, Object> params) throws PowerException;

	/**
	 * 删除商品分类关系
	 * @param params
	 * @return
	 */
	public void deleteProductCategory(HashMap<String, Object> params) throws PowerException;

	/**
	 * 主从商品复制
	 * @param productMasterId
	 */
	public Integer masterProductCopy(Integer productMasterId) throws PowerException;

	/**
	 * 跨品类商品复制
	 * @param valueOf
	 */
	public Integer changeTypeProductCopy(HashMap<String,Object> params) throws PowerException;

}
