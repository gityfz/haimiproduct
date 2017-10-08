package com.intelligence.business.price.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intelligence.business.price.entity.ProductUserPrice;
import com.intelligence.common.exception.PowerException;

public interface IProductUserPriceService {

	/**
	 * 获取商品用户价格
	 * 
	 * @param pageParam
	 * @return
	 */
	public ArrayList<ProductUserPrice> queryProductUserPrice(HashMap<String, Object> pageParam) throws PowerException;
	
	/**
	 * 获取商品用户价格数量
	 * 
	 * @return
	 */
	public Integer queryProductUserPriceCount() throws PowerException;
	
	/**
	 * 获取用户价格配置
	 * 
	 * @return
	 * @throws PowerException
	 */
	public HashMap<String, HashMap<String, ProductUserPrice>> getUserPriceConfig(Integer userId) throws PowerException;

	/**
	 * 新增用户价格
	 * 
	 * @param params
	 * @return
	 */
	public Integer addUserPrice(HashMap<String, Object> params) throws PowerException;

	/**
	 * 按条件查询用户价格
	 * @param params
	 * @return
	 */
	public List<ProductUserPrice> queryUserPriceByCondition(
		HashMap<String, Object> params) throws PowerException;

	/**
	 * 删除用户价格
	 * @param productUserPriceId
	 */
	public void deleteUserPrice(Integer productUserPriceId) throws PowerException;

	/**
	 * 更新用户价格
	 * @param params
	 * @return
	 */
	public Integer updateProductUserPrice(HashMap<String, Object> params) throws PowerException;
	
	
	
}
