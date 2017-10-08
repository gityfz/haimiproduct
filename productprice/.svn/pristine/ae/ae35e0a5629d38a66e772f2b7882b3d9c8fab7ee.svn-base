package com.intelligence.business.price.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.intelligence.business.price.entity.ProductUserPrice;
import com.intelligence.common.exception.PowerException;

public interface IProductUserPriceDao {

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
	 * 批量更新用户价格
	 * 
	 * @param userId
	 * @param rate
	 * @param countryId
	 * @throws PowerException
	 */
	public void updateUserPrice(BigDecimal rate, Integer countryId) throws PowerException;
	
	/**
	 * 获取用户价格配置数据
	 * 
	 * @param userId
	 * @return
	 * @throws PowerException
	 */
	public ArrayList<ProductUserPrice> getUserPriceConfig(Integer userId) throws PowerException;

	/**
	 * 新增用户价格
	 * @param params
	 * @return
	 */
	public Integer addUserPrice(HashMap<String, Object> params) throws PowerException;

	/**
	 * 条件查询用户价格配置数据
	 * @param params
	 * @return
	 */
	public ArrayList<ProductUserPrice> queryUserPriceByCondition(HashMap<String, Object> params) throws PowerException;
	
	/**
	 * 删除用户价格配置数据
	 * @param productUserPriceId
	 * @return
	 */
	public void deleteUserPrice(Integer productUserPriceId) throws PowerException;

	/**
	 * 更新用户价格
	 * @param params
	 */
	public void updateProductUserPrice(HashMap<String, Object> params) throws PowerException;

}
