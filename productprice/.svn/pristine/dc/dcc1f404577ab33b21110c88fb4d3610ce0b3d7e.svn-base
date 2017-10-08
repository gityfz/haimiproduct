package com.intelligence.business.product.controller;

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

import com.intelligence.business.price.entity.ProductUserPrice;
import com.intelligence.business.price.service.IProductUserPriceService;
import com.intelligence.common.base.BaseController;
import com.intelligence.common.constant.StringCon;
import com.intelligence.common.exception.ErrorCode;
import com.intelligence.common.exception.PowerException;
import com.intelligence.common.log.LogUtils;
import com.intelligence.common.utils.NumUtils;

/**
 * @author Zhengyf
 *
 * @date 2017年2月27日
 */

@Controller
@RequestMapping(value = "/product-userprice")
public class ProductUserPriceController extends BaseController {

    /**
     * 日志
     */
    public final static Logger logger = LoggerFactory
	    .getLogger(ProductUserPriceController.class);

    /**
     * 供应商业务层
     */
    @Resource
    private IProductUserPriceService iProductUserPriceService;

    /**
     * 新增一条用户价格记录
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addUserPrice(HttpServletRequest request,
	    HttpServletResponse response) {
	try {
	    // 获取参数
	    HashMap<String, Object> params = this.getMapParams(request,
		    response);
	    // 记录日志
	    logger.info(LogUtils.commonFormat("添加一条用户价格接口入参", params,
		    "/product-userprice/add", "post"));
	    // 校验入参
	    String paramsMsg = StringCon.EMPTY_STR.getValue();

	    if (!NumUtils.isNum(params.get("countryId"))) {
		// 国家编号
		paramsMsg = "countryId";
	    } else if (!NumUtils.isNum(params.get("productId"))) {
		// 商品编号
		paramsMsg = "productId";
	    } else if (!NumUtils.isNum(params.get("depotId"))) {
		// 仓库编号
		paramsMsg = "depotId";
	    } else if (!NumUtils.isNum(params.get("userId"))) {
		// 用户id
		paramsMsg = "userId";
	    } else if (null == params.get("priceBal")) {
		// 价格变动量
		paramsMsg = "priceBal";
	    } else if (null == params.get("priceReferBal")) {
		// 参考价格变动量
		paramsMsg = "priceReferBal";
	    } else if (null == params.get("priceBalRate")) {
		// 价格变动率
		paramsMsg = "priceBalRate";
	    } else if (null == params.get("priceReferBalRate")) {
		// 参考价格变动率
		paramsMsg = "priceReferBalRate";
	    }
	    if (!StringCon.EMPTY_STR.getValue().equals(paramsMsg)) {
		// 返回结果，参数错误
		this.sendResult(response, null, false, paramsMsg
			+ ErrorCode.PARAMS_FAIL.getErrorMessage(),
			ErrorCode.PARAMS_FAIL.getErrorCode());
	    } else {
		// 新增供应商
		Integer supplierId = iProductUserPriceService
			.addUserPrice(params);
		// 返回结果
		this.sendResult(response, supplierId);
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
     * 根据条件查询用户配置价格
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/queryByConditon", method = RequestMethod.GET)
    public void queryUserPriceByCondition(HttpServletRequest request,
	    HttpServletResponse response) {
	try {
	    // 获取参数
	    HashMap<String, Object> params = this.getMapParams(request,
		    response);
	    // 记录日志
	    logger.info(LogUtils.commonFormat("条件查询用户价格接口入参", params,
		    "/product-userprice/queryByConditon", "get"));

	    // 校验入参
	    String paramsMsg = StringCon.EMPTY_STR.getValue();

	    if (!NumUtils.isNum(params.get("start"))) {
		// 查询起始
		paramsMsg = "start";
	    } else if (!NumUtils.isNum(params.get("limit"))) {
		// 查询条数
		paramsMsg = "limit";
	    } else if (params.get("productId") != null
		    & !NumUtils.isNum(params.get("productId"))) {
		// 商品Id
		paramsMsg = "productId";
	    } else if (params.get("userId") != null
		    & !NumUtils.isNum(params.get("userId"))) {
		// 用户Id
		paramsMsg = "userId";
	    } else if (params.get("countryId") != null
		    & !NumUtils.isNum(params.get("countryId"))) {
		// 国家ID
		paramsMsg = "countryId";
	    } else if (params.get("depotId") != null
		    & !NumUtils.isNum(params.get("depotId"))) {
		// 仓库ID
		paramsMsg = "depotId";
	    } else if (params.get("productType") != null
		    & !NumUtils.isNum(params.get("productType"))) {
		// 品类
		paramsMsg = "productType";
	    } else if (null == params.get("productId") & // 商品id
		    null == params.get("userId") & // 用户id
		    null == params.get("countryId") & // 国家ID
		    null == params.get("depotId") & // 仓库Id
		    null == params.get("productType") // 品类编号
	    ) {
		// 查询条件为空
		paramsMsg = "emptyqueryconditon";
	    }
	    if (!StringCon.EMPTY_STR.getValue().equals(paramsMsg)) {
		// 返回结果，参数错误
		this.sendResult(response, null, false, paramsMsg
			+ ErrorCode.PARAMS_FAIL.getErrorMessage(),
			ErrorCode.PARAMS_FAIL.getErrorCode());
	    } else {
		// 查询供应商
		List<ProductUserPrice> userPriceList = iProductUserPriceService
			.queryUserPriceByCondition(params);
		// 返回结果
		this.sendResult(response, userPriceList);
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
     * 根据条件查询用户配置价格
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void deleteUserPrice(HttpServletRequest request,
	    HttpServletResponse response) {
	try{
	    Integer productUserPriceId = Integer.valueOf(request.getParameter("productUserPriceId"));
	 // 记录日志
	    logger.info(LogUtils.commonFormat("删除用户价格接口入参", productUserPriceId,
		    "/product-userprice/delete", "get"));
	    
	    iProductUserPriceService.deleteUserPrice(productUserPriceId);
	 // 返回结果
	 		this.sendResult(response, "delete success");
	}
	catch (PowerException e) {
	    // TODO Auto-generated catch block
	    if (logger.isDebugEnabled()) {
		e.printStackTrace();
	    }
	    this.sendResult(response, null, false, e.getErrorMsg(),
		    e.getErrorCode());
	}
    }
    
    /**
     * 更新用户价格记录
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateUserPrice(HttpServletRequest request,
	    HttpServletResponse response) {
	try {
	    // 获取参数
	    HashMap<String, Object> params = this.getMapParams(request,
		    response);
	    // 记录日志
	    logger.info(LogUtils.commonFormat("添加一条用户价格接口入参", params,
		    "/product-userprice/update", "post"));
	    // 校验入参
	    String paramsMsg = StringCon.EMPTY_STR.getValue();

	    if (!NumUtils.isNum(params.get("productUserPriceId"))) {
		// 商品编号
		paramsMsg = "productUserPriceId";
	    }
	    
	    if (!StringCon.EMPTY_STR.getValue().equals(paramsMsg)) {
		// 返回结果，参数错误
		this.sendResult(response, null, false, paramsMsg
			+ ErrorCode.PARAMS_FAIL.getErrorMessage(),
			ErrorCode.PARAMS_FAIL.getErrorCode());
	    } else {
		// 更新供应商
		Integer id = iProductUserPriceService
			.updateProductUserPrice(params);
		// 返回结果
		this.sendResult(response, id);
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

}
