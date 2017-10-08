package com.intelligence.business.price.persistance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.intelligence.business.price.entity.ProductUserPrice;


/**
 * 商品价格映射
 * 
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public interface ProductUserPriceMapper {

	/**
	 * 获取商品用户价格
	 * 
	 * @param pageParam
	 * @return
	 */
	public ArrayList<ProductUserPrice> queryProductUserPrice(HashMap<String, Object> pageParam);
	
	/**
	 * 获取商品用户价格数量
	 * 
	 * @return
	 */
	public Integer queryProductUserPriceCount();
	
	/**
     * 更新用户配置价格
     * 
     * @param currency_rate
     * @param fromCurrency
     */
    @Update("update product_front_#{suffix} set uni_price_tmp = uni_price_ori * #{rate} WHERE country_id = #{countryId}")
    public void updateUserPrice(@Param("suffix") Integer suffix, @Param("rate") BigDecimal rate, @Param("countryId") Integer countryId);
    
    /**
     * 查询用户配置价格
     * 
     * @param currency_rate
     * @param fromCurrency
     */
    @Update("select content_id as contentId, content_type as contentType, user_id as userId, price_bal as priceBal, price_bal_rate as priceBalRate from product_user_price WHERE del_flag = 0 and user_id in(0, #{userId})")
    public ArrayList<ProductUserPrice> getUserPriceConfig(@Param("userId") Integer userId);

    /**
     * 新增用户配置价格
     * @param params
     * @return
     */
    public void addUserPrice(HashMap<String, Object> params);

    /**
     * 条件查询用户配置价格
     * @param params
     * @return
     */
    public ArrayList<ProductUserPrice> queryUserPriceByCondition( HashMap<String, Object> params);

    /**
     * 删除用户价格配置
     * @param productUserPriceId
     */
    @Update("update product_user_price set del_flag=1 where id = #{productUserPriceId} and del_flag = 0 ")
    public void deleteUserPrice(@Param(value="productUserPriceId") Integer productUserPriceId);

    /**
     * 更新用户价格配置
     * @param params
     */
    public void updateProductUserPrice(HashMap<String, Object> params);
	
}
