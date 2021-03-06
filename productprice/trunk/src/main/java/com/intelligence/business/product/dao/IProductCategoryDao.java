package com.intelligence.business.product.dao;

import java.util.HashMap;
import java.util.List;

import com.intelligence.business.product.entity.Category;
import com.intelligence.common.exception.PowerException;

/**
 * 
 * @author Zhengyf
 *
 * @date 2017年2月13日
 */
public interface IProductCategoryDao {
	
	/**
	 * 添加商品分类信息
	 * 
	 * @param params
	 * @return
	 */
	public Integer addCategory(HashMap<String, Object> params) throws PowerException;
	
	/**
	 * 获取指定分类编号的分类信息
	 * 
	 * @param CategoryId
	 * @return Category
	 */
	public Category getCategoryMap(int categoryId) throws PowerException;
	
	/**
	 * 获取指定分类的下一级子类集合
	 * 
	 * @param startId 子分类起始编号,endId 子分类结束编号,searchCondition 数据库查询限制条件
	 * @param categoryLevel 
	 * @return List<Category>
	 */
	public List<Category> getChildList(String startId,String endId,String searchCondition) throws PowerException;

	/**
	 * 更新分类表信息
	 * @param params
	 * @return
	 * @throws PowerException
	 */
	public Integer updateCategory(HashMap<String, Object> params)
		throws PowerException;

	
}
