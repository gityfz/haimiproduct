package com.intelligence.business.product.dao.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.intelligence.business.product.dao.IProductCategoryDao;
import com.intelligence.business.product.entity.Category;
import com.intelligence.business.product.persistance.ProductCategoryMapper;
import com.intelligence.common.exception.ErrorCode;
import com.intelligence.common.exception.PowerException;
import com.intelligence.common.log.LogUtils;

/**
 * 
 * @author Zhengyf
 *
 * @date 2017年2月13日
 */
@Repository
public class ProductCategoryDaoImpl implements IProductCategoryDao {

	/**
	 * 日志
	 */
	public final static Logger logger = LoggerFactory
			.getLogger(ProductCategoryDaoImpl.class);

	@Resource
	private ProductCategoryMapper productCategoryMapper;

	
	/**
	 * 添加分类表信息
	 */
	@Override
	public Integer addCategory(HashMap<String, Object> params) throws PowerException {
		// TODO Auto-generated method stub
		try {
			logger.info(LogUtils.commonFormat("新增一条分类表信息", params));
			// 新增分类表记录
			productCategoryMapper.insertCategory(params);
			
		} catch (Exception e) {
		    if(e instanceof DuplicateKeyException){
			throw e;
		    }
		    throw new PowerException("新增分类表信息异常",
				ErrorCode.ADD_FAIL.getErrorCode(), e);
		}
		// 返回主键
		return Integer.valueOf(params.get("id").toString());
		
	}

	/**
	 * 更新分类表信息
	 */
	@Override
	public Integer updateCategory(HashMap<String, Object> params) throws PowerException {
		// TODO Auto-generated method stub
		try {
			logger.info(LogUtils.commonFormat("更新一条分类表信息", params));
			// 新增商品分类
			productCategoryMapper.updateCategory(params);
			
		} catch (Exception e) {
		    throw new PowerException("更新分类表信息异常",
				ErrorCode.UPDATE_FAIL.getErrorCode(), e);
		}
		// 返回主键
		return Integer.valueOf(params.get("id").toString());
		
	}
	
	/**
	 * 获取指定分类编号的分类信息
	 */
	@Override
	public Category getCategoryMap(int categoryId) throws PowerException{
	    try {
		// 根据categoryId查询商品分类父类信息
		Category result = productCategoryMapper.getCategory(categoryId);
		
		logger.info(LogUtils.commonFormat("查询一条商品分类的父级信息", result));
		return result;
	    } catch (Exception e) {
	    throw new PowerException("查询商品分类信息异常",
			ErrorCode.QUERY_FAIL.getErrorCode(), e);
	    }
	}

	/**
	 * 获取指定分类编号的所有下级子类合集
	 */
	@Override
	public List<Category> getChildList(String startId,String endId,String searchCondition) throws PowerException{
	    try {
		List<Category> result = productCategoryMapper.getChildList(startId,endId,searchCondition);
		logger.info(LogUtils.commonFormat("查询一条商品分类的子类集合信息", result));
		return result;
	    } catch (Exception e) {
	    throw new PowerException("查询商品分类信息异常",
			ErrorCode.QUERY_FAIL.getErrorCode(), e);
	    }
	}

	
}
