package com.intelligence.business.base.service;

import java.util.HashMap;

import com.intelligence.business.product.entity.Product;
import com.intelligence.business.product.entity.ProductTypeProp;
import com.intelligence.common.exception.PowerException;

public interface ICodeNameService {

	/**
	 * 匹配品牌编码名称
	 * 
	 * 
	 * @param brandId
	 * @return
	 * @throws PowerException
	 */
	public String mapBrandCodeName(Integer brandId) throws PowerException;

	/**
	 * 匹配供应商编码名称
	 * 
	 * 
	 * @param supplierId
	 * @return
	 * @throws PowerException
	 */
	public String mapSupplierCodeName(Integer supplierId) throws PowerException;

	/**
	 * 匹配国家编码单位
	 * 
	 * 
	 * @param countryId
	 * @return
	 * @throws PowerException
	 */
	public HashMap<String, Object> mapCountryCodeName(Integer countryId) throws PowerException;
	
	/**
	 * 匹配品类编码名称
	 * 
	 * 
	 * @param productTypeId
	 * @return
	 * @throws PowerException
	 */
	public String mapProductTypeCodeName(Integer productTypeId) throws PowerException;
	
	/**
	 * 匹配分类编码名称
	 * 
	 * 
	 * @param categoryId
	 * @return
	 * @throws PowerException
	 */
	public String mapCategoryCodeName(Integer categoryId) throws PowerException;
	
	/**
	 * 获取键值匹配范围
	 * 
	 * @param id
	 * @return
	 */
	public HashMap<String, Integer> getCodeNameRange(Integer id);
	
	/**
	 * 匹配商品ID数据
	 * 
	 * 
	 * @param brandId
	 * @return
	 * @throws PowerException
	 */
	public Product mapProductIdData(Integer productId) throws PowerException;
	
	/**
	 * 匹配图片ID地址
	 * 
	 * @param imgId
	 * @return
	 * @throws PowerException
	 */
	public String mapImgCodeUrl(Integer imgId) throws PowerException;
	
	/**
	 * 匹配属性ID数据
	 * 
	 * @param propId
	 * @return
	 * @throws PowerException
	 */
	public ProductTypeProp mapPtpCodeInfo(Integer propId) throws PowerException;
	

}
