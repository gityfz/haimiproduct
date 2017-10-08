package com.intelligence.business.product.service.impl;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.intelligence.business.product.dao.IProductBrandDao;
import com.intelligence.business.product.dao.IProductDao;
import com.intelligence.business.product.entity.ProductBrand;
import com.intelligence.business.product.service.IProductBrandService;
import com.intelligence.common.exception.PowerException;

@Service
public class ProductBrandServiceImpl implements IProductBrandService {

	/**
	 * 日志
	 */
	public final static Logger logger = LoggerFactory.getLogger(ProductBrandServiceImpl.class);
	
	/**
	 * 品牌DAO层
	 */
	@Resource
	private IProductBrandDao iProductBrandDao;
	
	/**
	 * 商品DAO层
	 */
	@Resource
	private IProductDao iProductDao;
	
	public Integer addProductBrand(HashMap<String, Object> brandParam) throws PowerException {
		try {
			// 获取当前时间
			Date currentTime = new Date();
			
			// 新增详情
			HashMap<String, Object> brandDetail = new HashMap<String, Object>();
			brandDetail.put("detail", brandParam.get("detail"));
			brandDetail.put("addTime", currentTime);
			Integer detailId = iProductDao.addDetail(brandDetail);
			brandDetail = null;
			brandParam.remove("detail");
			
			// 新增品牌
			brandParam.put("detailId", detailId);
			brandParam.put("addTime", currentTime);
			return iProductBrandDao.addProductBrand(brandParam);
			
		} catch (PowerException e) {
			throw e;
		}
	}
	
	/**
	 * 更新品牌
	 * 
	 * @param brandParam
	 * @return
	 * @throws PowerException
	 */
	public Integer updateProductBrand(HashMap<String, Object> brandParam) throws PowerException{
	    try {
		//若没有传入参数，返回0
  		if(1>= brandParam.size()){
  		    return 0;
  		}
		if(null != brandParam.get("detail")){
		// 更新详情
		HashMap<String, Object> brandDetail = new HashMap<String, Object>();
		brandDetail.put("detail", brandParam.get("detail"));
		brandDetail.put("detailId",iProductBrandDao.selectBrandById((int)brandParam.get("brandId")).getDetailId());
		iProductDao.updateDetail(brandDetail);
		brandDetail = null;
		brandParam.remove("detail");
		}
		return iProductBrandDao.updateProductBrand(brandParam);
		
	} catch (PowerException e) {
		throw e;
	}
	    
	}
	
	/**
	 * 查询品牌
	 * 
	 * @param brandId
	 * @return
	 * @throws PowerException
	 */
	public ProductBrand selectProductBrand(Integer brandId) throws PowerException{
	    try {
		
		return iProductBrandDao.selectProductBrand(brandId);
		
	} catch (PowerException e) {
		throw e;
	}
	    
	}
}
