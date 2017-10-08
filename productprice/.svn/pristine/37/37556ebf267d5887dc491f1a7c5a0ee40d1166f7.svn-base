package com.intelligence.business.base.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.intelligence.business.product.entity.Category;
import com.intelligence.business.product.entity.Country;
import com.intelligence.business.product.entity.Image;
import com.intelligence.business.product.entity.Product;
import com.intelligence.business.product.entity.ProductBrand;
import com.intelligence.business.product.entity.ProductType;
import com.intelligence.business.product.entity.ProductTypeProp;
import com.intelligence.business.product.entity.Supplier;
import com.intelligence.common.exception.PowerException;

public interface ICodeNameDao {

	/**
	 * 获取品牌编码名称键值对
	 * 
	 * 
	 * @param param
	 * @return
	 * @throws PowerException 
	 */
	public ArrayList<ProductBrand> getBrandCodeName(HashMap<String, Integer> param) throws PowerException;
	
	/**
	 * 获取供应商编码名称键值对
	 * 
	 * 
	 * @param param
	 * @return
	 * @throws PowerException 
	 */
	public ArrayList<Supplier> getSupplierCodeName(HashMap<String, Integer> param) throws PowerException;
	
	/**
	 * 获取国家单位键值对
	 * 
	 * 
	 * @param param
	 * @return
	 * @throws PowerException 
	 */
	public ArrayList<Country> getCountryCodeName(HashMap<String, Integer> param) throws PowerException;
	
	/**
	 * 获取产品品类编码名称键值对
	 * 
	 * 
	 * @param param
	 * @return
	 * @throws PowerException 
	 */
	public ArrayList<ProductType> getProductTypeCodeName(HashMap<String, Integer> param) throws PowerException;
	
	/**
	 * 获取分类编码名称键值对
	 * 
	 * 
	 * @param param
	 * @return
	 * @throws PowerException 
	 */
	public ArrayList<Category> getCategoryCodeName(HashMap<String, Integer> param) throws PowerException;
	
	/**
	 * 获取图片ID地址
	 * 
	 * @param category
	 */
	public ArrayList<Image> getImgCodeUrl(HashMap<String, Integer> param) throws PowerException;
	
	/**
	 * 获取产品ID数据
	 * 
	 * @param param
	 * @return
	 */
	public ArrayList<Product> getProductCodeData(HashMap<String, Integer> param) throws PowerException;
	
	/**
	 * 获取属性信息数据
	 * 
	 * @param param
	 * @return
	 */
	public ArrayList<ProductTypeProp> queryPtpCodeInfo(HashMap<String, Integer> param) throws PowerException;
	
}
