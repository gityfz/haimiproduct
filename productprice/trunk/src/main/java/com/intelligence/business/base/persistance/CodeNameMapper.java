package com.intelligence.business.base.persistance;

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

public interface CodeNameMapper {
	
	/**
	 * 获取品牌编码名称
	 * 
	 * @param product
	 */
	ArrayList<ProductBrand> queryBrandCodeName(HashMap<String, Integer> param);
	
	/**
	 * 获取供应商编码名称
	 * 
	 * @param supplier
	 */
	ArrayList<Supplier> querySupplierCodeName(HashMap<String, Integer> param);
	
	/**
	 * 获取国家编码单位
	 * 
	 * @param country
	 */
	ArrayList<Country> queryCountryCodeName(HashMap<String, Integer> param);
	
	/**
	 * 获取产品品类编码名称
	 * 
	 * @param productType
	 */
	ArrayList<ProductType> queryProductTypeCodeName(HashMap<String, Integer> param);
	
	/**
	 * 获取分类编码名称
	 * 
	 * @param category
	 */
	ArrayList<Category> queryCategoryCodeName(HashMap<String, Integer> param);
	
	/**
	 * 获取图片ID地址
	 * 
	 * @param category
	 */
	ArrayList<Image> queryImgCodeUrl(HashMap<String, Integer> param);
	
	/**
	 * 获取产品ID数据
	 * 
	 * @param param
	 * @return
	 */
	ArrayList<Product> queryProductCodeData(HashMap<String, Integer> param);
	
	/**
	 * 获取属性信息数据
	 * 
	 * @param param
	 * @return
	 */
	ArrayList<ProductTypeProp> queryPtpCodeInfo(HashMap<String, Integer> param);
	
}
