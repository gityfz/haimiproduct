package com.intelligence.business.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intelligence.business.product.entity.Category;
import com.intelligence.common.exception.PowerException;

/**
 * @author 作者 E-mail: bzp
 * @date 创建时间：2017年1月13日 下午4:44:43
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public interface IProductCategoryService {
	
	/**
	 * 添加一条分类表信息
	 * 
	 * @param params
	 * @return
	 */
	public Integer addCategory(HashMap<String, Object> params) throws PowerException;
	
	/**
	 * 更新一条分类表信息
	 * 
	 * @param params
	 * @return
	 */
	public Integer updateCategory(HashMap<String, Object> params) throws PowerException;
	
	/**
	 * 获取指定分类的所有下级分类集合
	 * 
	 * @param categoryId
	 * @return
	 * @throws PowerException 
	 */
	public List<Category> getCategoryChild(int categoryId) throws PowerException;
	
	/**
	 * 获取指定分类的上级分类
	 * 
	 * @param categoryId
	 * @return
	 */
	public Category getCategoryParent(int categoryId) throws PowerException;
	
	/**
	  * 拆分categoryId
	  * 
	  * @param categoryId
	  * @return
	  */
	public Map<String, String> splitCategoryId(int categoryId) ;

}
