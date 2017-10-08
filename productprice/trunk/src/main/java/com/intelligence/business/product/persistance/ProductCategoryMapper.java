package com.intelligence.business.product.persistance;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.intelligence.business.product.entity.Category;

/**
 * @author 作者 E-mail: bzp
 * @date 创建时间：2017年1月13日 下午4:59:21
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public interface ProductCategoryMapper {
	
	/**
	 * 添加一条分类表信息
	 * 
	 * @param params
	 */
	void insertCategory(HashMap<String, Object> params);
	
	/**
	 * 获取指定商品分类的子分类集合
	 * @param categoryId ,searchCondition
	 * @param List
	 * @return
	 */
	List<Category> getChildList(@Param(value="startId") String startId,@Param(value="endId") String endId,@Param(value="searchCondition") String searchCondition);

	/**
	 * 获取指定商品分类的分类信息
	 * @param categoryId
	 * @return
	 */
	Category getCategory(@Param(value="categoryId") int categoryId);

	
	/**
	 * 更新分类表信息
	 * 
	 * @param params
	 */
	Integer updateCategory(HashMap<String, Object> params);

	

}
