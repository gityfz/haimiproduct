package com.intelligence.business.product.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.intelligence.business.product.entity.ProductBrand;
import com.intelligence.business.product.service.IProductBrandService;
import com.intelligence.common.base.BaseController;
import com.intelligence.common.constant.StringCon;
import com.intelligence.common.exception.ErrorCode;
import com.intelligence.common.exception.PowerException;
import com.intelligence.common.log.LogUtils;
import com.intelligence.common.utils.NumUtils;

@Controller
@RequestMapping(value = "/product-brand")
public class ProductBrandController extends BaseController {
	
	/**
	 * 日志
	 */
	public final static Logger logger = LoggerFactory.getLogger(ProductBrandController.class);

	/**
	 * 品牌业务层
	 */
	@Resource
	private IProductBrandService iProductBrandService;
	
	/**
	 * 新增品牌接口
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws PowerException
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addProductBrand(HttpServletRequest request, HttpServletResponse response) throws IOException, PowerException {
		try {
			// 获取参数
			HashMap<String, Object> params = this.getMapParams(request, response);
			// 记录日志
			logger.info(LogUtils.commonFormat("新增一条品牌记录接口入参", params, "/product-brand/add", "post"));
			// 校验入参
			String paramMsg = StringCon.EMPTY_STR.getValue();
			if (null == params.get("brandName")) {
				// 品牌名称
				paramMsg = "brandName";
			} else if (null == params.get("brandFirstLetter")) {
				// 品牌首字母
				paramMsg = "brandFirstLetter";
			} else if (null == params.get("brandIndex")) {
				// 品牌拼音索引
				paramMsg = "brandIndex";
			} else if (null == params.get("brandLogo")) {
				// 品牌logo
				paramMsg = "brandLogo";
			} else if (!NumUtils.isNum(params.get("countryId"))) {
				// 国家ID
				paramMsg = "countryId";
			} else if (null == params.get("brandSite")) {
				// 品牌网站
				paramMsg = "brandSite";
			}
			
			if (!StringCon.EMPTY_STR.getValue().equals(paramMsg)) {
				// 返回结果，参数错误
				this.sendResult(response, null, false, paramMsg + ErrorCode.PARAMS_FAIL.getErrorMessage(), ErrorCode.PARAMS_FAIL.getErrorCode());
			} else {
				// 新增商品
				Integer productId = iProductBrandService.addProductBrand(params);
				// 返回结果
				this.sendResult(response, productId);				
			}
			
		} catch (PowerException e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			this.sendResult(response, null, false, e.getErrorMsg(), e.getErrorCode());
		}
	}
	
	/**
	 * 更新品牌记录
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws PowerException
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void updateProductBrand(HttpServletRequest request, HttpServletResponse response) throws IOException, PowerException {
		try {
			// 获取参数
			HashMap<String, Object> params = this.getMapParams(request, response);
			// 记录日志
			logger.info(LogUtils.commonFormat("更新一条品牌记录接口入参", params, "/product-brand/update", "post"));
			// 校验入参
			String paramMsg = StringCon.EMPTY_STR.getValue();
			if (null == params.get("brandId")) {
				// 品牌id
				paramMsg = "brandId";
			} else if (null != params.get("countryId") & !NumUtils.isNum(params.get("countryId"))){
			    	//国家ID
			    	paramMsg = "countryId"; 
			} 
			if (!StringCon.EMPTY_STR.getValue().equals(paramMsg)) {
				// 返回结果，参数错误
				this.sendResult(response, null, false, paramMsg + ErrorCode.PARAMS_FAIL.getErrorMessage(), ErrorCode.PARAMS_FAIL.getErrorCode());
			} else {
				// 新增商品
				Integer productId = iProductBrandService.updateProductBrand(params);
				// 返回结果
				this.sendResult(response, productId);				
			}
			
		} catch (PowerException e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			this.sendResult(response, null, false, e.getErrorMsg(), e.getErrorCode());
		}
	}
	
	/**
	 * 查询品牌记录
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws PowerException
	 */
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public void selectProductBrand(HttpServletRequest request, HttpServletResponse response) throws IOException, PowerException {
	try{
	    	// 获取参数
		Integer brandId = Integer.valueOf(request.getParameter("brandId"));
		// 记录日志
		logger.info(LogUtils.commonFormat("查询一条品牌记录接口入参", brandId, "/product-brand/select", "get"));
		
		if(null != brandId){
		 // 查询商品
			ProductBrand productBrand = iProductBrandService.selectProductBrand(brandId);
			// 返回结果
			this.sendResult(response, productBrand);				
		}else{
		    this.sendResult(response, null, false,  ErrorCode.PARAMS_FAIL.getErrorMessage(), ErrorCode.PARAMS_FAIL.getErrorCode());
		}
	}catch(PowerException e){
	    if(logger.isDebugEnabled()){
		e.printStackTrace();
	    }
	    this.sendResult(response,null,false,e.getErrorMsg(),e.getErrorCode());
	    
	    
	}
	
	}
}
