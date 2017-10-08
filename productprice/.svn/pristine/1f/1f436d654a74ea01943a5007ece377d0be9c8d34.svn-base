package com.intelligence.business.product.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.intelligence.business.product.entity.Category;
import com.intelligence.business.product.service.IProductCategoryService;
import com.intelligence.common.base.BaseController;
import com.intelligence.common.constant.StringCon;
import com.intelligence.common.exception.ErrorCode;
import com.intelligence.common.exception.PowerException;
import com.intelligence.common.utils.NumUtils;

/**
 * @author 作者 E-mail: bzp
 * @date 创建时间：2017年1月13日 下午4:11:55
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Component
@RequestMapping("/product-category")
public class ProductCategoryController extends BaseController {
	/**
	 * 日志
	 */

	public final static Logger logger = LoggerFactory
			.getLogger(ProductCategoryController.class);
	/**
	 * 商品分类业务层
	 */
	@Resource
	private IProductCategoryService iProductCategoryService;

	/**
	 * 新增一条分类表记录
	 * @param request
	 * @param response
	 * @throws PowerException
	 */
	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
	public void addCategory(HttpServletRequest request,
			HttpServletResponse response) throws PowerException
		{
		try {
			// 获取参数
			HashMap<String, Object> params = this.getMapParams(request,
					response);
			// 添加日志
			logger.info("添加一条分类表接口入参", params, "/product-category/addCategory", "post");
			// 校验入参
			String paramsMsg = StringCon.EMPTY_STR.getValue();
			if (!NumUtils.isNum(params.get("parentId"))) {
				// 父分类编号
				paramsMsg = "parentId";
			} else if (null == params.get("categoryName")) {
				// 分类名称
				paramsMsg = "categoryName";
			} else if (null == params.get("categoryIndex")) {
				// 分类索引
				paramsMsg = "categoryIndex";
			} 
			if (!StringCon.EMPTY_STR.getValue().equals(paramsMsg)) {
				// 返回结果，参数错误
				this.sendResult(response, null, false, paramsMsg
						+ ErrorCode.PARAMS_FAIL.getErrorMessage(),
						ErrorCode.PARAMS_FAIL.getErrorCode());
			} else {
				// 添加商品分类
				Integer categoryId = iProductCategoryService
						.addCategory(params);
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
	 * 更新分类表记录
	 * @param request
	 * @param response
	 * @throws PowerException
	 */
	@RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
	public void updateCategory(HttpServletRequest request,
			HttpServletResponse response) throws PowerException
		{
		try {
			// 获取参数
			HashMap<String, Object> params = this.getMapParams(request,
					response);
			// 添加日志
			logger.info("更新分类表信息接口入参", params, "/product-category/updateCategory", "post");
			// 校验入参
			String paramsMsg = StringCon.EMPTY_STR.getValue();
			if (!NumUtils.isNum(params.get("id"))) {
				// 分类id
				paramsMsg = "id";
			} 
			if (!StringCon.EMPTY_STR.getValue().equals(paramsMsg)) {
				// 返回结果，参数错误
				this.sendResult(response, null, false, paramsMsg
						+ ErrorCode.PARAMS_FAIL.getErrorMessage(),
						ErrorCode.PARAMS_FAIL.getErrorCode());
			} else {
				// 添加商品分类
				Integer categoryId = iProductCategoryService
						.updateCategory(params);
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
	 * 获取指定分类的下级分类集合
	 * 
	 * @param id 分类id
	 * @return
	 * @throws PowerException 
	 */
	@RequestMapping(value = "/getChild", method = RequestMethod.GET)
	public void getChild(HttpServletRequest request,
			HttpServletResponse response) throws PowerException{
	    try{
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		// 添加日志
		logger.info("获取指定分类编号子分类集合接口入参", categoryId, "/product-category/getChild", "get");
		List<Category> childList = iProductCategoryService.getCategoryChild(categoryId);
		this.sendResult(response, childList);
	    }
	    catch(PowerException e) {
		if (logger.isDebugEnabled()) {
			e.printStackTrace();
		}
	    }
	
	}
	
	/**
	 * 获取指定分类的父级分类信息
	 * 
	 * @param categoryId 分类编号
	 * @return
	 * @throws PowerException 
	 */
	@RequestMapping(value = "/getParent", method = RequestMethod.GET)
	public void getParent(HttpServletRequest request,
			HttpServletResponse response) throws PowerException{
	    try{
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		// 添加日志
		logger.info("获取指定分类id父分类信息接口入参", categoryId, "/product-category/getParent", "get");
		Category parentCategory = iProductCategoryService.getCategoryParent(categoryId);
		this.sendResult(response, parentCategory);
	    }
	    catch(PowerException e) {
		if (logger.isDebugEnabled()) {
			e.printStackTrace();
		}
	    }
	
	}
	

}
