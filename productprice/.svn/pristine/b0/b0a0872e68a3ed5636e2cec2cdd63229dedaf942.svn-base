package com.intelligence.business.base.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

import net.rubyeye.xmemcached.exception.MemcachedException;

import org.springframework.stereotype.Service;

import com.intelligence.business.base.dao.ICodeNameDao;
import com.intelligence.business.base.service.ICodeNameService;
import com.intelligence.business.product.entity.Category;
import com.intelligence.business.product.entity.Country;
import com.intelligence.business.product.entity.Image;
import com.intelligence.business.product.entity.Product;
import com.intelligence.business.product.entity.ProductBrand;
import com.intelligence.business.product.entity.ProductType;
import com.intelligence.business.product.entity.ProductTypeProp;
import com.intelligence.business.product.entity.Supplier;
import com.intelligence.common.cache.CacheManager;
import com.intelligence.common.cache.MemcacheTools;
import com.intelligence.common.exception.PowerException;

@Service
public class CodeNameServiceImpl implements ICodeNameService {

	/**
	 * 键值对DAO层
	 */
	@Resource
	private ICodeNameDao iCodeNameDao;
	
	/**
	 * memcache工具
	 */
	@Resource
	private MemcacheTools memcacheTools;
	
	/**
	 * 匹配品牌编码名称
	 * 
	 * 
	 * @param brandId
	 * @return
	 * @throws PowerException
	 */
	public String mapBrandCodeName(Integer brandId) throws PowerException {
		HashMap<String, Integer> codeNameParam = this.getCodeNameRange(brandId);
		StringBuffer codeNameKey = new StringBuffer("brandCodeName");
		codeNameKey.append(codeNameParam.get("start"));
		codeNameKey.append(codeNameParam.get("end"));
		HashMap<String, Object> codeNameData = this.getCodeNameData(codeNameKey.toString());
		if (null == codeNameData) {
			ArrayList<ProductBrand> brandCodeNames = iCodeNameDao.getBrandCodeName(codeNameParam);
			if (null == brandCodeNames) {
				return null;
			}
			codeNameData = new HashMap<String, Object>();
			for (int i = 0, l = brandCodeNames.size(); i < l; i++) {
				codeNameData.put(brandCodeNames.get(i).getBrandId().toString(), brandCodeNames.get(i));				
			}
			this.setCodeNameData(codeNameKey.toString(), codeNameData);
		}
		return ((ProductBrand)codeNameData.get(brandId.toString())).getBrandName();
	}
	
	/**
	 * 匹配商品ID数据
	 * 
	 * 
	 * @param brandId
	 * @return
	 * @throws PowerException
	 */
	public Product mapProductIdData(Integer productId) throws PowerException {
		HashMap<String, Integer> codeNameParam = this.getCodeNameRange(productId);
		StringBuffer codeNameKey = new StringBuffer("productIdData");
		codeNameKey.append(codeNameParam.get("start"));
		codeNameKey.append(codeNameParam.get("end"));
		HashMap<String, Object> codeNameData = this.getCodeNameData(codeNameKey.toString());
		if (null == codeNameData) {
			ArrayList<Product> productIdData = iCodeNameDao.getProductCodeData(codeNameParam);
			if (null == productIdData) {
				return null;
			}
			codeNameData = new HashMap<String, Object>();
			for (int i = 0, l = productIdData.size(); i < l; i++) {
				String tmpImg = productIdData.get(i).getHeadImg();
				String []imgs = tmpImg.split(",");
				for (int j = 0, le = imgs.length; j < le; j++) {
					String []imgsTmp = imgs[j].split("-");
					if (2 == imgsTmp.length && ("P").equals(imgsTmp[0])) {
						productIdData.get(i).setHeadImgPC(this.mapImgCodeUrl(Integer.valueOf(imgsTmp[1])));
					} else if (2 == imgsTmp.length && ("M").equals(imgsTmp[0])) {
						productIdData.get(i).setHeadImgM(this.mapImgCodeUrl(Integer.valueOf(imgsTmp[1])));
					}
				}
				codeNameData.put(productIdData.get(i).getProductId().toString(), productIdData.get(i));				
			}
			this.setCodeNameData(codeNameKey.toString(), codeNameData);
		}
		return (Product)codeNameData.get(productId.toString());
	}

	/**
	 * 匹配国家编码单位
	 * 
	 * 
	 * @param countryId
	 * @return
	 * @throws PowerException
	 */
	public HashMap<String,Object> mapCountryCodeName(Integer countryId) throws PowerException {
		HashMap<String, Integer> codeNameParam = this.getCodeNameRange(countryId);
		StringBuffer codeNameKey = new StringBuffer("countryCodeName");
		codeNameKey.append(codeNameParam.get("start"));
		codeNameKey.append(codeNameParam.get("end"));
		HashMap<String, Object> codeNameData = this.getCodeNameData(codeNameKey.toString());
		if (null == codeNameData) {
			ArrayList<Country> brandCodeNames = iCodeNameDao.getCountryCodeName(codeNameParam);
			if (null == brandCodeNames) {
				return null;
			}
			codeNameData = new HashMap<String, Object>();
			for (int i = 0, l = brandCodeNames.size(); i < l; i++) {
				codeNameData.put(brandCodeNames.get(i).getCountryId().toString(), brandCodeNames.get(i));				
			}
			this.setCodeNameData(codeNameKey.toString(), codeNameData);
		}
		HashMap<String, Object> resultMap = new HashMap<String,Object>();
		resultMap.put("priceUnit", (Object)((Country)codeNameData.get(countryId.toString())).getPriceUnit());
		resultMap.put("weightUnit",(Object)((Country)codeNameData.get(countryId.toString())).getWeightUnit());
		return resultMap;
	}
	

	/**
	 * 匹配供应商编码名称
	 * 
	 * 
	 * @param brandId
	 * @return
	 * @throws PowerException
	 */
	public String mapSupplierCodeName(Integer supplierId) throws PowerException {
	    HashMap<String,Integer> codeNameParam = this.getCodeNameRange(supplierId);
	    StringBuffer codeNameKey = new StringBuffer("supplierCodeName");
	    codeNameKey.append(codeNameParam.get("start"));
	    codeNameKey.append(codeNameParam.get("end"));
	    HashMap<String,Object> codeNameData = this.getCodeNameData(codeNameKey.toString());
	    if(null == codeNameData){
		ArrayList<Supplier> supplierCodeNames = iCodeNameDao.getSupplierCodeName(codeNameParam);
		if(null == supplierCodeNames){
		    return null;
		}
		codeNameData = new HashMap<String, Object>();
		for (int i = 0, l = supplierCodeNames.size(); i < l; i++) {
			codeNameData.put(supplierCodeNames.get(i).getSupplierId().toString(), supplierCodeNames.get(i));				
		}
		this.setCodeNameData(codeNameKey.toString(), codeNameData);
	    }
	    return ((Supplier)codeNameData.get(supplierId.toString())).getSupplierName();
	}
	
	/**
	 * 匹配产品品类编码名称
	 * 
	 * 
	 * @param productTypeId
	 * @return
	 * @throws PowerException
	 */
	public String mapProductTypeCodeName(Integer productTypeId) throws PowerException {
		HashMap<String, Integer> codeNameParam = this.getCodeNameRange(productTypeId);
		StringBuffer codeNameKey = new StringBuffer("productTypeCodeName");
		codeNameKey.append(codeNameParam.get("start"));
		codeNameKey.append(codeNameParam.get("end"));
		HashMap<String, Object> codeNameData = this.getCodeNameData(codeNameKey.toString());
		if (null == codeNameData) {
			ArrayList<ProductType> productTypeCodeNames = iCodeNameDao.getProductTypeCodeName(codeNameParam);
			if (null == productTypeCodeNames) {
				return null;
			}
			codeNameData = new HashMap<String, Object>();
			for (int i = 0, l = productTypeCodeNames.size(); i < l; i++) {
				codeNameData.put(productTypeCodeNames.get(i).getProductTypeId().toString(), productTypeCodeNames.get(i));				
			}
			this.setCodeNameData(codeNameKey.toString(), codeNameData);
		}
		return ((ProductType)codeNameData.get(productTypeId.toString())).getProductTypeName();
	}
	
	/**
	 * 匹配分类编码名称
	 * 
	 * 
	 * @param categoryId
	 * @return
	 * @throws PowerException
	 */
	public String mapCategoryCodeName(Integer categoryId) throws PowerException {
		HashMap<String, Integer> codeNameParam = this.getCodeNameRange(categoryId);
		StringBuffer codeNameKey = new StringBuffer("categoryCodeName");
		codeNameKey.append(codeNameParam.get("start"));
		codeNameKey.append(codeNameParam.get("end"));
		HashMap<String, Object> codeNameData = this.getCodeNameData(codeNameKey.toString());
		if (null == codeNameData) {
			ArrayList<Category> categoryCodeNames = iCodeNameDao.getCategoryCodeName(codeNameParam);
			if (null == categoryCodeNames) {
				return null;
			}
			codeNameData = new HashMap<String, Object>();
			for (int i = 0, l = categoryCodeNames.size(); i < l; i++) {
				codeNameData.put(categoryCodeNames.get(i).getCategoryId().toString(), categoryCodeNames.get(i));				
			}
			this.setCodeNameData(codeNameKey.toString(), codeNameData);
		}
		return ((Category)codeNameData.get(categoryId.toString())).getCategoryName();
	}
	
	/**
	 * 匹配图片ID地址
	 * 
	 * @param imgId
	 * @return
	 * @throws PowerException
	 */
	public String mapImgCodeUrl(Integer imgId) throws PowerException {
		HashMap<String, Integer> codeNameParam = this.getCodeNameRange(imgId);
		StringBuffer codeNameKey = new StringBuffer("imgCodeUrl");
		codeNameKey.append(codeNameParam.get("start"));
		codeNameKey.append(codeNameParam.get("end"));
		HashMap<String, Object> codeNameData = this.getCodeNameData(codeNameKey.toString());
		if (null == codeNameData) {
			ArrayList<Image> imgCodeUrls = iCodeNameDao.getImgCodeUrl(codeNameParam);
			if (null == imgCodeUrls) {
				return null;
			}
			codeNameData = new HashMap<String, Object>();
			for (int i = 0, l = imgCodeUrls.size(); i < l; i++) {
				codeNameData.put(imgCodeUrls.get(i).getImgId().toString(), imgCodeUrls.get(i));				
			}
			this.setCodeNameData(codeNameKey.toString(), codeNameData);
		}
		return ((Image)codeNameData.get(imgId.toString())).getImgUrl();
	}
	
	/**
	 * 匹配属性ID数据
	 * 
	 * @param propId
	 * @return
	 * @throws PowerException
	 */
	public ProductTypeProp mapPtpCodeInfo(Integer propId) throws PowerException {
		HashMap<String, Integer> codeNameParam = this.getCodeNameRange(propId);
		StringBuffer codeNameKey = new StringBuffer("ptpCodeInfo");
		codeNameKey.append(codeNameParam.get("start"));
		codeNameKey.append(codeNameParam.get("end"));
		HashMap<String, Object> codeNameData = this.getCodeNameData(codeNameKey.toString());
		if (null == codeNameData) {
			ArrayList<ProductTypeProp> PtpCodeInfo = iCodeNameDao.queryPtpCodeInfo(codeNameParam);
			if (null == PtpCodeInfo) {
				return null;
			}
			codeNameData = new HashMap<String, Object>();
			for (int i = 0, l = PtpCodeInfo.size(); i < l; i++) {
				codeNameData.put(PtpCodeInfo.get(i).getPropId().toString(), PtpCodeInfo.get(i));				
			}
			this.setCodeNameData(codeNameKey.toString(), codeNameData);
		}
		return (ProductTypeProp)codeNameData.get(propId.toString());
	}
	
	
	/**
	 * 获取键值匹配范围
	 * 
	 * @param id
	 * @return
	 */
	public HashMap<String, Integer> getCodeNameRange(Integer id) {
		HashMap<String, Integer> range = new HashMap<String, Integer>();
		range.put("start", Integer.valueOf(id / 50) * 50);
		range.put("end", (Integer.valueOf(id / 50) + 1) * 50);
		return range;
	}

	/**
	 * 获取键值匹配数据
	 * 
	 * @param codeNameKey
	 * @return
	 * @throws IOException
	 * @throws TimeoutException
	 * @throws InterruptedException
	 * @throws MemcachedException
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> getCodeNameData(String codeNameKey) throws PowerException {
		if (null != CacheManager.getManager().getCache(codeNameKey)) {
			return (HashMap<String, Object>) CacheManager.getManager().getCache(codeNameKey);
		} else if (null != memcacheTools.getMemData(codeNameKey)) {
			HashMap<String, Object> memData = (HashMap<String, Object>) memcacheTools.getMemData(codeNameKey);
			CacheManager.getManager().setCache(codeNameKey, memData, 60000l);
			return memData;
		} else {
			return null;			
		}
	}
	
	/**
	 * 设置键值匹配数据
	 * 
	 * @param codeNameKey
	 * @param memData
	 * @throws IOException
	 * @throws TimeoutException
	 * @throws InterruptedException
	 * @throws MemcachedException
	 */
	public void setCodeNameData(String codeNameKey, HashMap<String, Object> memData) throws PowerException {
		memcacheTools.setMemData(codeNameKey, 10800, memData);
		CacheManager.getManager().setCache(codeNameKey, memData, 60000l);
	}

	
	
	
}
