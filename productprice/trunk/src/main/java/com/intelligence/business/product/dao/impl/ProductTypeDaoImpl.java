package com.intelligence.business.product.dao.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.intelligence.business.product.dao.IProductTypeDao;
import com.intelligence.business.product.entity.ProductType;
import com.intelligence.business.product.persistance.ProductTypeMapper;
import com.intelligence.common.exception.ErrorCode;
import com.intelligence.common.exception.PowerException;
import com.intelligence.common.log.LogUtils;

@Repository
public class ProductTypeDaoImpl implements IProductTypeDao{

	/**
	 * 日志
	 */
	public final static Logger logger = LoggerFactory.getLogger(ProductTypeDaoImpl.class);
	
	@Resource
	private ProductTypeMapper productTypeMapper;
	
	/**
	 * 新增一条品类记录
	 * 
	 * @param type
	 * @return
	 */
	public Integer addProductType(HashMap<String, Object> type) throws PowerException {
		// 日志
		logger.info(LogUtils.commonFormat("新增一条品类", type));
		try {
		    // 新增商品
		    productTypeMapper.insertProductType(type);
		    // 返回主键
		    return Integer.valueOf(type.get("productTypeId").toString());
		} catch (Exception e) {
		    if(e instanceof DuplicateKeyException){
			throw e;
		    }
		    throw new PowerException("新增商品价格中心品类异常",
				ErrorCode.ADD_FAIL.getErrorCode(), e);
		}
	}
	
	/**
	 * 更新品类记录
	 * 
	 * @param type
	 * @return
	 */
	public Integer updateProductType(HashMap<String, Object> type) throws PowerException {
		// 日志
		logger.info(LogUtils.commonFormat("更新品类", type));
		try {
		    // 更新商品
		    productTypeMapper.updateProductType(type);
		    // 返回主键
		    return Integer.valueOf(type.get("productTypeId").toString());
		} catch (Exception e) {
		    throw new PowerException("更新商品价格中心品类异常",
				ErrorCode.UPDATE_FAIL.getErrorCode(), e);
		}
	}

	/**
	 * 根据品类编号和查询条件查询指定品类下级品类合集
	 * 
	 * @param type
	 * @return
	 */
	public List<ProductType> getProductTypeChild(String startId,String endId,String searchCondition) throws PowerException{
	    logger.info(LogUtils.commonFormat("根据起始结束编号查询品类合集", startId,endId));
		try {
		    return productTypeMapper.getChildList(startId,endId,searchCondition);
		} catch (Exception e) {
		    throw new PowerException("查询商品中心品类异常",
				ErrorCode.QUERY_FAIL.getErrorCode(), e);
		}
	}

	/**
	 * 根据品类编号查询品类上级品类合集
	 * 
	 * @param type
	 * @return
	 */
	public ProductType getProductType(int productType)
		throws PowerException {
	    logger.info(LogUtils.commonFormat("获取指定品类编号的父品类信息", productType));
		try {
		    return productTypeMapper.getProductType(productType);
		} catch (Exception e) {
		    throw new PowerException("查询商品中心品类异常",
				ErrorCode.QUERY_FAIL.getErrorCode(), e);
		}
	}
	
	/**
	 * 根据二级或三级品类获取关联的品牌和供应商id集合
	 * 
	 * @param type
	 * @return
	 */
	public List<HashMap<String,Object>> querySupBraList(String queryCondition)
		throws PowerException {
	    logger.info(LogUtils.commonFormat("根据二级或三级品类获取关联的品牌和供应商id集合", queryCondition));
		try {
		    return productTypeMapper.querySupBraList(queryCondition);
		} catch (Exception e) {
		    throw new PowerException("根据二级或三级品类获取关联的品牌和供应商id集合异常",
				ErrorCode.QUERY_FAIL.getErrorCode(), e);
		}
	}

}
