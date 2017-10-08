package com.intelligence.business.product.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intelligence.business.product.service.IProductDepotService;
import com.intelligence.common.base.BaseController;
import com.intelligence.common.exception.ErrorCode;
import com.intelligence.common.exception.PowerException;
import com.intelligence.common.log.LogUtils;
import com.intelligence.common.utils.NumUtils;

/**
 * @author Zhengyf
 *
 * @date 2017年3月9日
 */
@Component
@Controller
@RequestMapping(value = "/product-depot")
public class ProductDepotController extends BaseController{
    /**
	 * 日志
	 */
	public final static Logger logger = LoggerFactory.getLogger(ProductDepotController.class);
	
	/**
	 * 仓库业务层
	 */
	@Resource
	private IProductDepotService iProductDepotService;
	
	/**
	 * 根据国家id查询仓库集合
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/queryDepot")
	public void queryDepotByCountry(HttpServletRequest request,HttpServletResponse response){
	 //获取参数
	    try{
	    Integer countryId = Integer.valueOf(request.getParameter("countryId"));
	    // 记录日志
	    logger.info(LogUtils.commonFormat("根据国家id查询仓库号", countryId,"/product-depot/queryDepot", "get"));
	    // 校验入参
	    if (countryId==null | !NumUtils.isNum(countryId)){
		this.sendResult(response, null, false, "countryId" + ErrorCode.PARAMS_FAIL.getErrorMessage(), ErrorCode.PARAMS_FAIL.getErrorCode());
	    }else{
		List<Object> depotList = iProductDepotService.queryDepotByCountry(countryId);
		this.sendResult(response, depotList);
	    }
	    }catch(PowerException e){
		if(logger.isDebugEnabled()){
		e.printStackTrace();
		}
		this.sendResult(response, null, false, e.getErrorMsg(), e.getErrorCode());
	    }
	}
	
	

}
