package com.intelligence.business.base.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.intelligence.business.base.dao.ICodeNameDao;
import com.intelligence.business.base.persistance.CodeNameMapper;
import com.intelligence.business.product.entity.Category;
import com.intelligence.business.product.entity.Country;
import com.intelligence.business.product.entity.Image;
import com.intelligence.business.product.entity.Product;
import com.intelligence.business.product.entity.ProductBrand;
import com.intelligence.business.product.entity.ProductType;
import com.intelligence.business.product.entity.ProductTypeProp;
import com.intelligence.business.product.entity.Supplier;
import com.intelligence.common.exception.ErrorCode;
import com.intelligence.common.exception.PowerException;
import com.intelligence.common.log.LogUtils;

@Repository
public class CodeNameDaoImpl implements ICodeNameDao {
	
	/**
	 * 日志
	 */
	public final static Logger logger = LoggerFactory.getLogger(CodeNameDaoImpl.class);

	@Resource
	private CodeNameMapper codeNameMapper;
	
	/**
	 * 获取品牌编码名称键值对
	 * 
	 * 
	 * @param param
	 * @return
	 * @throws PowerException 
	 */
	public ArrayList<ProductBrand> getBrandCodeName(HashMap<String, Integer> param) throws PowerException {
		// 日志
		logger.info(LogUtils.commonFormat("获取品牌编码名称键值对", param));
		try {
			return codeNameMapper.queryBrandCodeName(param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PowerException("获取品牌编码名称键值对异常",
					ErrorCode.QUERY_FAIL.getErrorCode(), e);
		}
	}
	
	/**
	 * 获取供应商名称键值对
	 * 
	 * 
	 * @param param
	 * @return
	 * @throws PowerException 
	 */
	public ArrayList<Supplier> getSupplierCodeName(HashMap<String, Integer> param) throws PowerException {
		// 日志
		logger.info(LogUtils.commonFormat("获取供应商编码名称键值对", param));
		try {
			return codeNameMapper.querySupplierCodeName(param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PowerException("获取供应商编码名称键值对异常",
					ErrorCode.QUERY_FAIL.getErrorCode(), e);
		}
	}
	
	/**
	 * 获取国家单位键值对
	 * 
	 * 
	 * @param param
	 * @return
	 * @throws PowerException 
	 */
	public ArrayList<Country> getCountryCodeName(HashMap<String, Integer> param) throws PowerException {
		// 日志
		logger.info(LogUtils.commonFormat("获取国家单位键值对", param));
		try {
			return codeNameMapper.queryCountryCodeName(param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PowerException("获取国家单位键值对异常",
					ErrorCode.QUERY_FAIL.getErrorCode(), e);
		}
	}
	
	/**
	 * 获取产品品类编码名称键值对
	 * 
	 * 
	 * @param param
	 * @return
	 * @throws PowerException 
	 */
	public ArrayList<ProductType> getProductTypeCodeName(HashMap<String, Integer> param) throws PowerException {
		// 日志
		logger.info(LogUtils.commonFormat("获取产品品类编码名称键值对", param));
		try {
			return codeNameMapper.queryProductTypeCodeName(param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PowerException("获取产品品类编码名称键值对异常",
					ErrorCode.QUERY_FAIL.getErrorCode(), e);
		}
	}
	
	/**
	 * 获取分类编码名称键值对
	 * 
	 * 
	 * @param param
	 * @return
	 * @throws PowerException 
	 */
	public ArrayList<Category> getCategoryCodeName(HashMap<String, Integer> param) throws PowerException {
		// 日志
		logger.info(LogUtils.commonFormat("获取分类编码名称键值对", param));
		try {
			return codeNameMapper.queryCategoryCodeName(param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PowerException("获取分类编码名称键值对异常",
					ErrorCode.QUERY_FAIL.getErrorCode(), e);
		}
	}
	
	/**
	 * 获取图片ID地址
	 * 
	 * @param category
	 */
	public ArrayList<Image> getImgCodeUrl(HashMap<String, Integer> param) throws PowerException {
		// 日志
		logger.info(LogUtils.commonFormat("获取图片ID地址键值对", param));
		try {
			return codeNameMapper.queryImgCodeUrl(param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PowerException("获取图片ID地址键值对异常",
					ErrorCode.QUERY_FAIL.getErrorCode(), e);
		}
	}
	
	/**
	 * 获取产品ID数据
	 * 
	 * @param param
	 * @return
	 */
	public ArrayList<Product> getProductCodeData(HashMap<String, Integer> param) throws PowerException {
		// 日志
		logger.info(LogUtils.commonFormat("获取产品ID数据", param));
		try {
			return codeNameMapper.queryProductCodeData(param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PowerException("获取产品ID数据异常",
					ErrorCode.QUERY_FAIL.getErrorCode(), e);
		}
	}
	
	/**
	 * 获取属性信息数据
	 * 
	 * @param param
	 * @return
	 */
	public ArrayList<ProductTypeProp> queryPtpCodeInfo(HashMap<String, Integer> param) throws PowerException {
		// 日志
		logger.info(LogUtils.commonFormat("获取属性信息数据", param));
		try {
			return codeNameMapper.queryPtpCodeInfo(param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PowerException("获取属性信息数据异常",
					ErrorCode.QUERY_FAIL.getErrorCode(), e);
		}
	}

}
