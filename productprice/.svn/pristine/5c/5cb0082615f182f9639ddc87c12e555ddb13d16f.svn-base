package com.intelligence.business.price.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.intelligence.business.price.service.IPriceUnitService;
import com.intelligence.common.base.BaseController;
import com.intelligence.common.cache.CacheManager;
import com.intelligence.common.exception.PowerException;
import com.intelligence.common.log.LogUtils;


@Component
@Controller
@RequestMapping(value = "/business-priceunit")
public class PriceUnitController extends BaseController {
	/**
	 * 日志
	 */
	public final static Logger logger = LoggerFactory.getLogger(PriceUnitController.class);
	
	@Resource
	private IPriceUnitService priceUnitService;
	
	@Resource
	private HttpServletRequest httpRequest;
	
	@Resource
	private HttpServletResponse httpResponse;

	
	
	/**
	 * 根据国家id获取重量和价格单位
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws PowerException 
	 */
	@RequestMapping(value = "/getpriceunit",method = RequestMethod.GET)
	public ModelAndView getPriceUnit(@RequestParam(value = "countryid")  int id ,HttpServletRequest request, HttpServletResponse response) throws IOException, PowerException {
		request.setCharacterEncoding("utf-8");
		ModelAndView view = new ModelAndView("/price");
		String priceUnit = null;
		String weightUnit = null;
		
		/**
		 * 获取缓存单例对象
		 */
		CacheManager cache= CacheManager.getManager();
		
		/**
		 * 缓存中存在则从缓存中获取数据，否则连接数据库获取
		 */
		if(cache.getCache(id+"p")!=null){
			priceUnit=cache.getCache(id+"p").toString();
			if (logger.isInfoEnabled()) {
				logger.info(LogUtils.commonFormat("缓存获取价格单位", priceUnit));
			}
		}
		else{
		    priceUnit=priceUnitService.getPriceUnit(id);
		    cache.setCache(id+"p",(Object)priceUnit, (long)6000);
		    if (logger.isInfoEnabled()) {
				logger.info(LogUtils.commonFormat("连接数据库获取价格单位", priceUnit));
			}
		}
		if(cache.getCache(id+"w")!=null){
			weightUnit=cache.getCache(id+"w").toString();
			if (logger.isInfoEnabled()) {
				logger.info(LogUtils.commonFormat("缓存获取重量单位", weightUnit));
			}
		}else{
			weightUnit=priceUnitService.getWeightUnit(id);
			cache.setCache(id+"w",(Object)weightUnit, (long)6000);
			if (logger.isInfoEnabled()) {
				logger.info(LogUtils.commonFormat("连接数据库获取重量单位", weightUnit));
			}
		}
		/**
		 * 参数加入视图中返回
		 */
		view.addObject("PRICEUNIT", priceUnit);
		view.addObject("WEIGHTUNIT", weightUnit);
		return view;
	}



}
