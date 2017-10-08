package com.intelligence.business.product.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.intelligence.business.product.entity.ProductType;
import com.intelligence.business.product.service.IProductTypeService;
import com.intelligence.common.base.BaseController;
import com.intelligence.common.constant.StringCon;
import com.intelligence.common.exception.ErrorCode;
import com.intelligence.common.exception.PowerException;
import com.intelligence.common.log.LogUtils;
import com.intelligence.common.utils.NumUtils;

/**
 * @author Zhengyf
 * @date 创建时间：2017年2月13日
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Controller
@RequestMapping(value = "/product-type")
public class ProductTypeController extends BaseController{
	
	/**
	 * 日志
	 */
	public final static Logger logger = LoggerFactory.getLogger(ProductTypeController.class);
	
	/**
	 * 品类业务层
	 */
	@Resource
	private IProductTypeService iProductTypeService;
	
        /**
         * 新增一条品类信息
         * @param request
         * @param response
         * @throws IOException
         * @throws PowerException
         */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public void addType(HttpServletRequest request,HttpServletResponse response) throws IOException,PowerException{
		try {
			// 获取参数
			HashMap<String, Object> params = this.getMapParams(request, response);
			// 记录日志
			logger.info(LogUtils.commonFormat("新增一条品类接口入参", params, "/product-type/add", "post"));
			// 校验入参
			String paramMsg = StringCon.EMPTY_STR.getValue();
			if(!NumUtils.isNum(params.get("parentId"))){
				//父品类编号
				paramMsg = "parentId";
			}else if (null == params.get("productTypeName")) {
				// 父分类编号
				paramMsg = "productTypeName";
			}			
			if (!StringCon.EMPTY_STR.getValue().equals(paramMsg)) {
				// 返回结果，参数错误
				this.sendResult(response, null, false, paramMsg + ErrorCode.PARAMS_FAIL.getErrorMessage(), ErrorCode.PARAMS_FAIL.getErrorCode());
			} else {
				// 新增品类
				Integer productTypeId = iProductTypeService.addProductType(params);
				// 返回结果
				this.sendResult(response, productTypeId);				
			}
			
		} catch (PowerException e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			this.sendResult(response, null, false, e.getErrorMsg(), e.getErrorCode());
		}
	}
	
	/**
	 * 更新商品分类表记录
	 * @param request
	 * @param response
	 * @throws PowerException
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void updateProductType(HttpServletRequest request,
			HttpServletResponse response) throws PowerException
		{
		try {
			// 获取参数
			HashMap<String, Object> params = this.getMapParams(request,
					response);
			// 添加日志
			logger.info("更新商品品类表信息接口入参", params, "/product-type/update", "post");
			// 校验入参
			String paramsMsg = StringCon.EMPTY_STR.getValue();
			if (!NumUtils.isNum(params.get("productTypeId"))) {
				// 分类id
				paramsMsg = "productTypeId";
			} 
			if (!StringCon.EMPTY_STR.getValue().equals(paramsMsg)) {
				// 返回结果，参数错误
				this.sendResult(response, null, false, paramsMsg
						+ ErrorCode.PARAMS_FAIL.getErrorMessage(),
						ErrorCode.PARAMS_FAIL.getErrorCode());
			} else {
				// 添加商品分类
				Integer categoryId = iProductTypeService
						.updateProductType(params);
				// 返回结果
				this.sendResult(response, categoryId);
			}
		} catch (PowerException e) {
			// TODO Auto-generated catch block
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			this.sendResult(response, null, false, e.getErrorMsg(),
					e.getErrorCode());
		}

	}
	
	
	/**
	 * 获取指定id品类的子品类集合
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws PowerException
	 */
	@RequestMapping(value="/getChild",method=RequestMethod.GET)
	public void getChild(HttpServletRequest request,HttpServletResponse response) throws IOException,PowerException{
	    try{
		int productType = Integer.parseInt(request.getParameter("productType"));
		// 记录日志
		logger.info(LogUtils.commonFormat("获取指定品类编号子品类集合接口入参", productType, "/product-type/getChild", "get"));
		List<ProductType> childList = iProductTypeService.getTypeChild(productType);
		this.sendResult(response, childList);
	    }
	    catch(PowerException e){
		if (logger.isDebugEnabled()) {
			e.printStackTrace();
		}
		this.sendResult(response, null, false, e.getErrorMsg(), e.getErrorCode());
	    }
	}
	
	/**
	 * 获取指定id品类的父品类信息
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws PowerException
	 */
	@RequestMapping(value="/getParent",method=RequestMethod.GET)
	public void getParent(HttpServletRequest request,HttpServletResponse response) throws IOException,PowerException{
	    try{
		int productType = Integer.parseInt(request.getParameter("productType"));
		// 记录日志
		logger.info(LogUtils.commonFormat("获取指定品类编号父品类接口入参", productType, "/product-type/getParent", "get"));
		ProductType parent = iProductTypeService.getTypeParent(productType);
		this.sendResult(response, parent);
	    }
	    catch(PowerException e){
		if (logger.isDebugEnabled()) {
			e.printStackTrace();
		}
		this.sendResult(response, null, false, e.getErrorMsg(), e.getErrorCode());
	    }
	    
	}
	
	/**
	 * 根据二级或三级品类获取关联的品牌和供应商id集合
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws PowerException
	 */
	@RequestMapping(value="/querySupBraList",method=RequestMethod.GET)
	public void querySupBraList(HttpServletRequest request,HttpServletResponse response) throws IOException,PowerException{
	    try{
		int productType = Integer.parseInt(request.getParameter("productType"));
		// 记录日志
		logger.info(LogUtils.commonFormat("根据二级或三级品类获取关联的品牌和供应商id集合", productType, "/product-type/querySupBraList", "get"));
		HashMap<String,Object> listMap = iProductTypeService.querySupBraList(productType);
		this.sendResult(response, listMap);
	    }
	    catch(PowerException e){
		if (logger.isDebugEnabled()) {
			e.printStackTrace();
		}
		this.sendResult(response, null, false, e.getErrorMsg(), e.getErrorCode());
	    }
	    
	}
	
}
