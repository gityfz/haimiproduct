package com.intelligence.business.product.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.intelligence.business.base.service.ICodeNameService;
import com.intelligence.business.product.service.IProductService;
import com.intelligence.common.base.BaseController;
import com.intelligence.common.cache.MemcacheTools;
import com.intelligence.common.constant.StringCon;
import com.intelligence.common.exception.ErrorCode;
import com.intelligence.common.exception.PowerException;
import com.intelligence.common.log.LogUtils;
import com.intelligence.common.utils.NumUtils;

@Controller
@RequestMapping(value = "/product")
public class ProductController extends BaseController {

	/**
	 * 日志
	 */
	public final static Logger logger = LoggerFactory
			.getLogger(ProductController.class);

	/**
	 * 商品业务层
	 */
	@Resource
	private IProductService iProductService;

	@Resource
	private ICodeNameService iCodeNameService;
	
	@Resource
	private MemcacheTools memcacheTools;
	
	@RequestMapping(value = "/test2", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void test2(@RequestParam("fullName") String fullName) throws PowerException {
//		List<Integer> testArr = new ArrayList<Integer>(500);
//		StringBuffer sb = new StringBuffer();
//		for (int i = 1000000; i < 1003000; i++) {
////			testArr.add(i);
//			sb.append(i);
//		}
//		long pre = System.currentTimeMillis();
//		memcacheTools.setMemData("test", 60, sb);
//		memcacheTools.getMemData("test");
////		for(int i = 1; i < 10000; i++) {
////			iCodeNameService.mapBrandCodeName(1);
////			iCodeNameService.mapSupplierCodeName(2);
////			iCodeNameService.mapCountryCodeName(1);
////			iCodeNameService.mapProductTypeCodeName(1);
////			iCodeNameService.mapCategoryCodeName(1);
////		}
//		this.sendResult(response, System.currentTimeMillis() - pre);
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void test(HttpServletRequest request, HttpServletResponse response) throws PowerException {
		long pre = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {			
		}
		this.sendResult(response, System.currentTimeMillis() - pre);
	}
	
	/**
	 * 新增商品
	 * 
	 * @param request
	 * @param response
	 * @throws PowerException
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addProduct(HttpServletRequest request, HttpServletResponse response) throws PowerException {
		try {
			// 获取参数
			HashMap<String, Object> params = this.getMapParams(request,
					response);
			// 记录日志
			logger.info(LogUtils.commonFormat("新增一条产品接口入参", params,
					"/product/add", "post"));
			// 校验入参
			String paramMsg = StringCon.EMPTY_STR.getValue();
			if (!NumUtils.isNum(params.get("productType"))) {
				// 商品类型
				paramMsg = "productType";
			} else if (null == params.get("productName")) {
				// 商品名称
				paramMsg = "productName";
			} else if (null == params.get("unit")) {
				// 单位
				paramMsg = "unit";
			} else if (null == params.get("headImg")) {
				// 头图
				paramMsg = "headImg";
			} else if (!NumUtils.isNum(params.get("supplierId"))) {
				// 供应商ID
				paramMsg = "supplierId";
			} else if (!NumUtils.isNum(params.get("countryId"))) {
				// 国家ID
				paramMsg = "countryId";
			} else if (!NumUtils.isNum(params.get("brandId"))) {
				// 品牌ID
				paramMsg = "brandId";
			} else if (null != params.get("topRank") 
						&& NumUtils.isNum(params.get("topRank"))) {
				// 置顶权重
				paramMsg = "topRank";
			} else if (!NumUtils.isDouble(params.get("weight"))) {
				// 重量
				paramMsg = "weight";
			} else if (0 != NumUtils.toNum(params.get("checkFlag"))
					&& 1 != NumUtils.toNum(params.get("checkFlag"))) {
				// 审核标记
				paramMsg = "checkFlag";
			} else if (!NumUtils.isNum(params.get("sellLimit"))) {
				// 售卖限制
				paramMsg = "sellLimit";
			} else if (!NumUtils.isNum(params.get("uniPurchasePrice"))) {
				// 统一采购价
				paramMsg = "uniPurchasePrice";
			} else if (!NumUtils.isNum(params.get("uniReferPrice"))) {
				// 统一关联价
				paramMsg = "uniReferPrice";
			} else if (0 != NumUtils.toNum(params.get("groupFlag"))
					&& 1 != NumUtils.toNum(params.get("groupFlag"))) {
				// 组合标记
				paramMsg = "groupFlag";
			}

			if (!StringCon.EMPTY_STR.getValue().equals(paramMsg)) {
				// 返回结果，参数错误
				this.sendResult(response, null, false, paramMsg
						+ ErrorCode.PARAMS_FAIL.getErrorMessage(),
						ErrorCode.PARAMS_FAIL.getErrorCode());
			} else {
				// 新增商品
				Integer productId = iProductService.addProduct(params);
				// 返回结果
				this.sendResult(response, productId);
			}

		} catch (PowerException e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			this.sendResult(response, null, false, e.getErrorMsg(),
					e.getErrorCode());
		}
	}
	
	/**
	 * 新增组合商品子商品
	 * 
	 * @param request
	 * @param response
	 * @throws PowerException
	 */
	@RequestMapping(value = "/addProductSub", method = RequestMethod.POST)
	public void addProductSub(HttpServletRequest request, HttpServletResponse response) throws PowerException {
		try {
			// 获取参数
			HashMap<String, Object> params = this.getMapParams(request,
					response);
			// 记录日志
			logger.info(LogUtils.commonFormat("新增组合商品子商品接口入参", params,
					"/product/addProductSub", "post"));
			// 校验入参
			String paramMsg = StringCon.EMPTY_STR.getValue();
			if (!NumUtils.isNum(params.get("productId"))) {
				// 商品ID
				paramMsg = "productId";
			} else if (!NumUtils.isNum(params.get("productSubId"))) {
				// 子商品ID
				paramMsg = "productSubId";
			} else if (null == params.get("subNum")) {
				// 子商品数量
				paramMsg = "subNum";
			}

			if (!StringCon.EMPTY_STR.getValue().equals(paramMsg)) {
				// 返回结果，参数错误
				this.sendResult(response, null, false, paramMsg
						+ ErrorCode.PARAMS_FAIL.getErrorMessage(),
						ErrorCode.PARAMS_FAIL.getErrorCode());
			} else {
				// 新增子商品
				boolean flag = iProductService.addProductSub(params);
				// 返回结果
				this.sendResult(response, flag);
			}

		} catch (PowerException e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			this.sendResult(response, null, false, e.getErrorMsg(),
					e.getErrorCode());
		}
	}
	
	/**
	 * 删除组合商品子商品
	 * 
	 * @param request
	 * @param response
	 * @throws PowerException
	 */
	@RequestMapping(value = "/delProductSub", method = RequestMethod.POST)
	public void delProductSub(HttpServletRequest request, HttpServletResponse response) throws PowerException {
		try {
			// 获取参数
			HashMap<String, Object> params = this.getMapParams(request,
					response);
			// 记录日志
			logger.info(LogUtils.commonFormat("删除组合商品子商品接口入参", params,
					"/product/addProductSub", "post"));
			// 校验入参
			String paramMsg = StringCon.EMPTY_STR.getValue();
			if (!NumUtils.isNum(params.get("productSubDbId"))) {
				// 子商品数据库ID
				paramMsg = "productSubDbId";
			} else if (!NumUtils.isNum(params.get("productId"))) {
				// 商品ID
				paramMsg = "productId";
			}

			if (!StringCon.EMPTY_STR.getValue().equals(paramMsg)) {
				// 返回结果，参数错误
				this.sendResult(response, null, false, paramMsg
						+ ErrorCode.PARAMS_FAIL.getErrorMessage(),
						ErrorCode.PARAMS_FAIL.getErrorCode());
			} else {
				// 新增子商品
				Enum<ErrorCode> errorCode = iProductService.delProductSub(params);
				 
				// 返回结果
				if (errorCode.equals(ErrorCode.DELETE_SUCCESS)) {
					this.sendResult(response, null, true, ErrorCode.PARAMS_FAIL.getErrorMessage(),
							ErrorCode.DELETE_SUCCESS.getErrorCode());
				} else if (errorCode.equals(ErrorCode.DOU_DEL_FAIL)) {
					this.sendResult(response, null, false, ErrorCode.DOU_DEL_FAIL.getErrorMessage(),
							ErrorCode.DOU_DEL_FAIL.getErrorCode());	
				} else if (errorCode.equals(ErrorCode.ORDER_PRO_DEL_FAIL)) {
					this.sendResult(response, null, false, ErrorCode.ORDER_PRO_DEL_FAIL.getErrorMessage(),
							ErrorCode.ORDER_PRO_DEL_FAIL.getErrorCode());	
				}
			}

		} catch (PowerException e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			this.sendResult(response, null, false, e.getErrorMsg(),
					e.getErrorCode());
		}
	}
	
	/**
	 * 更新商品
	 * 
	 * @param request
	 * @param response
	 * @throws PowerException
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void updateProduct(HttpServletRequest request, HttpServletResponse response) throws PowerException {
		try {
			// 获取参数
			HashMap<String, Object> params = this.getMapParams(request, response);
			// 记录日志
			logger.info(LogUtils.commonFormat("更新商品接口入参", params, "/product/update", "post"));
			// 校验入参
			String paramMsg = StringCon.EMPTY_STR.getValue();
			if (!NumUtils.isNum(params.get("productId"))) {
				// 产品ID
				paramMsg = "productId";
			} else if (null == params.get("productName")) {
				// 商品名称
				paramMsg = "productName";
			} else if (null == params.get("unit")) {
				// 单位
				paramMsg = "unit";
			} else if (null == params.get("headImg")) {
				// 头图
				paramMsg = "headImg";
			} else if (!NumUtils.isNum(params.get("supplierId"))) {
				// 供应商ID
				paramMsg = "supplierId";
			} else if (!NumUtils.isNum(params.get("countryId"))) {
				// 国家ID
				paramMsg = "countryId";
			} else if (!NumUtils.isNum(params.get("brandId"))) {
				// 品牌ID
				paramMsg = "brandId";
			} else if (null != params.get("topRank") 
						&& NumUtils.isNum(params.get("topRank"))) {
				// 置顶权重
				paramMsg = "topRank";
			} else if (!NumUtils.isDouble(params.get("weight"))) {
				// 重量
				paramMsg = "weight";
			} else if (0 != NumUtils.toNum(params.get("checkFlag"))
					&& 1 != NumUtils.toNum(params.get("checkFlag"))) {
				// 审核标记
				paramMsg = "checkFlag";
			} else if (!NumUtils.isNum(params.get("sellLimit"))) {
				// 售卖限制
				paramMsg = "sellLimit";
			} else if (!NumUtils.isNum(params.get("uniPurchasePrice"))) {
				// 统一采购价
				paramMsg = "uniPurchasePrice";
			} else if (!NumUtils.isNum(params.get("uniReferPrice"))) {
				// 统一关联价
				paramMsg = "uniReferPrice";
			} else if (!NumUtils.isNum(params.get("detailId"))) {
				// 详情ID
				paramMsg = "detailId";
			}

			if (!StringCon.EMPTY_STR.getValue().equals(paramMsg)) {
				// 返回结果，参数错误
				this.sendResult(response, null, false, paramMsg
						+ ErrorCode.PARAMS_FAIL.getErrorMessage(),
						ErrorCode.PARAMS_FAIL.getErrorCode());
			} else {
				// 更新商品
				iProductService.updateProduct(params);
				// 返回结果
				this.sendResult(response, null);
			}

		} catch (PowerException e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			this.sendResult(response, null, false, e.getErrorMsg(),
					e.getErrorCode());
		}
	}

	/**
	 *  商品详情查看
	 *  
	 * @param productId
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/queryDetail", method = RequestMethod.GET)
	public void getProductDetailInfo(HttpServletRequest request, HttpServletResponse response) throws PowerException{
		HashMap<String, Object> data = null;
		try { 
			// 获取参数
			HashMap<String, Object> params = this.getMapParams(request, response);
			// 记录日志
			logger.info(LogUtils.commonFormat("商品详情查看", params,
					"/product/queryDetail", "get"));
			// 检验入参
			String paramMsg = StringCon.EMPTY_STR.getValue();
			if (NumUtils.isNum(params.get("productId"))) {
				// 商品id
				paramMsg = "productId";
			}
			if (StringCon.EMPTY_STR.getValue().equals(paramMsg)) {
				// 返回结果，参数错误
				this.sendResult(response, null, false, paramMsg
						+ ErrorCode.PARAMS_FAIL.getErrorMessage(),
						ErrorCode.PARAMS_FAIL.getErrorCode());
			} else {
				// 查询商品
				data = iProductService.getProductDetailInfo((Integer)params.get("productId"));
				// 返回结果
				this.sendResult(response, data);
			}
		} catch (PowerException e) {
			// TODO: handle exception
			throw e;
		}
	}
    
    @RequestMapping(value="/testCode",method = RequestMethod.GET)
    public void testCodeNameService(HttpServletRequest request,
	    HttpServletResponse response) throws IOException, PowerException {
	
    }
    
	/**
	 * 查看前台商品列表
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws PowerException
	 */
    @RequestMapping(value="/frontproducts",method = RequestMethod.GET)
    public void queryFrontProductList(HttpServletRequest request,
    	    HttpServletResponse response) throws IOException, PowerException {
    	try {
			// 获取参数
			HashMap<String, Object> params = this.getMapParams(request,
					response);
			// 记录日志
			logger.info(LogUtils.commonFormat("前台商品列表查询接口入参", params,
					"/product/frontproducts", "post"));
			
			// 校验入参
			String paramMsg = StringCon.EMPTY_STR.getValue();
			if (!NumUtils.isNum(params.get("page"))) {
				// 页数
				paramMsg = "page";
			} else if (!NumUtils.isNum(params.get("size"))) {
				// 页大小
				paramMsg = "size";
			} else if (null != params.get("productId") && !NumUtils.isNum(params.get("productId"))) {
				// 商品ID
				paramMsg = "productId";
			} else if (null != params.get("categoryId") && !NumUtils.isNum(params.get("categoryId"))) {
				// 分类ID
				paramMsg = "categoryId";
			} else if (null != params.get("countryId") && !NumUtils.isNum(params.get("countryId"))) {
				// 国家ID
				paramMsg = "countryId";
			} else if (null != params.get("propId") && !NumUtils.isNum(params.get("propId"))) {
				// 属性ID
				paramMsg = "propId";
			} else if (null != params.get("propId") && !NumUtils.isNum(params.get("productType"))) {
				// 品类
				paramMsg = "productType";
			} else if (null != params.get("userId") && !NumUtils.isNum(params.get("userId"))) {
				// 用户ID
				paramMsg = "userId";
			} else if (null != params.get("countryId") && !NumUtils.isNum(params.get("brandId"))) {
				// 品牌ID
				paramMsg = "brandId";
			}

			if (!StringCon.EMPTY_STR.getValue().equals(paramMsg)) {
				// 返回结果，参数错误
				this.sendResult(response, null, false, paramMsg
						+ ErrorCode.PARAMS_FAIL.getErrorMessage(),
						ErrorCode.PARAMS_FAIL.getErrorCode());
			} else {
				// 调用查询接口查询数据并返回结果
				this.sendResult(response, iProductService.getProductInfo(params));
			}

		} catch (PowerException e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			this.sendResult(response, null, false, e.getErrorMsg(),
					e.getErrorCode());
		}
    }
    
    /**
	 * 根据分类查询品类规格
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws PowerException
	 */
    @RequestMapping(value="/frontprops",method = RequestMethod.GET)
    public void queryFrontProps(HttpServletRequest request,
    	    HttpServletResponse response) throws IOException, PowerException {
    	try {
			// 获取参数
			HashMap<String, Object> params = this.getMapParams(request,
					response);
			// 记录日志
			logger.info(LogUtils.commonFormat("根据分类查询品类规格入参", params,
					"/product/frontprops", "post"));
			
			// 校验入参
			String paramMsg = StringCon.EMPTY_STR.getValue();
			if (!NumUtils.isNum(params.get("categoryId"))) {
				// 分类ID
				paramMsg = "categoryId";
			}

			if (!StringCon.EMPTY_STR.getValue().equals(paramMsg)) {
				// 返回结果，参数错误
				this.sendResult(response, null, false, paramMsg
						+ ErrorCode.PARAMS_FAIL.getErrorMessage(),
						ErrorCode.PARAMS_FAIL.getErrorCode());
			} else {
				// 调用查询接口查询数据并返回结果
				this.sendResult(response, iProductService.getFrontProps(params));
			}

		} catch (PowerException e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			this.sendResult(response, null, false, e.getErrorMsg(),
					e.getErrorCode());
		}
    }
    
    /**
	 * 查询前端商品详情页
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws PowerException
	 */
    @RequestMapping(value="/frontproductdetail",method = RequestMethod.GET)
    public void queryFrontProductDetail(HttpServletRequest request,
    	    HttpServletResponse response) throws IOException, PowerException {
    	try {
			// 获取参数
			HashMap<String, Object> params = this.getMapParams(request,
					response);
			// 记录日志
			logger.info(LogUtils.commonFormat("查询前端商品详情页入参", params,
					"/product/frontproductdetail", "post"));
			
			// 校验入参
			String paramMsg = StringCon.EMPTY_STR.getValue();
			if (!NumUtils.isNum(params.get("productId"))) {
				// 商品ID
				paramMsg = "productId";
			}

			if (!StringCon.EMPTY_STR.getValue().equals(paramMsg)) {
				// 返回结果，参数错误
				this.sendResult(response, null, false, paramMsg
						+ ErrorCode.PARAMS_FAIL.getErrorMessage(),
						ErrorCode.PARAMS_FAIL.getErrorCode());
			} else {
				// 调用查询接口查询数据并返回结果
				this.sendResult(response, iProductService.getFrontProductDetail(params));
			}

		} catch (PowerException e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			this.sendResult(response, null, false, e.getErrorMsg(),
					e.getErrorCode());
		}
    }

    /**
     * 新增商品分类映射关系
     * 
     * @param request
     * @param response
     * @throws IOException
     * @throws PowerException
     * @author zhengyf
     */
    @RequestMapping(value = "/addProductCategory", method = RequestMethod.POST)
    public void addProductCategory(HttpServletRequest request,
	    HttpServletResponse response) throws PowerException {
	try {
	    // 获取参数
	    HashMap<String, Object> params = this.getMapParams(request,
		    response);
	    // 记录日志
	    logger.info(LogUtils.commonFormat("新增商品分类关系集合接口入参", params,
		    "/product/addProductCategory", "post"));
	    // 校验入参
	    String paramMsg = StringCon.EMPTY_STR.getValue();
	    if (!NumUtils.isNum(params.get("productId"))) {
		// 商品Id
		paramMsg = "productId";
	    } else if (null == params.get("categoryList")) {
		// 商品对应的分类编号列表(三级分类)
		paramMsg = "categoryList";
	    }

	    if (!StringCon.EMPTY_STR.getValue().equals(paramMsg)) {
		// 返回结果，参数错误
		this.sendResult(response, null, false, paramMsg
			+ ErrorCode.PARAMS_FAIL.getErrorMessage(),
			ErrorCode.PARAMS_FAIL.getErrorCode());
	    } else {
		// 新增商品
		Integer productId = iProductService.addProductCategory(params);
		// 返回结果
		this.sendResult(response, productId);
	    }

	} catch (PowerException e) {
	    if (logger.isDebugEnabled()) {
		e.printStackTrace();
	    }
	    this.sendResult(response, null, false, e.getErrorMsg(),
		    e.getErrorCode());
	}
    }

    /**
     * 查询商品分类映射关系
     * 
     * @param request
     * @param response
     * @throws IOException
     * @throws PowerException
     * @author zhengyf
     */
    @RequestMapping(value = "/selectProductCategory", method = RequestMethod.GET)
    public void selectProductCategory(HttpServletRequest request,
	    HttpServletResponse response) throws PowerException {
	try {
	    // 获取参数
	    String productId  = request.getParameter("productId");
	    // 记录日志
	    logger.info(LogUtils.commonFormat("查询商品分类关系集合接口入参", productId,
		    "/product/selectProductCategory", "get"));

	    if (productId == null || !NumUtils.isNum(productId)) {
		// 返回结果，参数错误
		this.sendResult(response, null, false, "productId"
			+ ErrorCode.PARAMS_FAIL.getErrorMessage(),
			ErrorCode.PARAMS_FAIL.getErrorCode());
	    } else {
		// 新增商品
		List<Integer> categoryList = iProductService
			.selectProductCategory(Integer.valueOf(productId));
		// 返回结果
		this.sendResult(response, categoryList);
	    }

	} catch (PowerException e) {
	    if (logger.isDebugEnabled()) {
		e.printStackTrace();
	    }
	    this.sendResult(response, null, false, e.getErrorMsg(),
		    e.getErrorCode());
	}
    }

    /**
     * 更新商品分类映射关系表
     * 
     * @param request
     * @param response
     * @throws IOException
     * @throws PowerException
     * @author zhengyf
     */
    @RequestMapping(value = "/updateProductCategory", method = RequestMethod.POST)
    public void updateProductCategory(HttpServletRequest request,
	    HttpServletResponse response) throws PowerException {
	try {
	    HashMap<String, Object> params = this.getMapParams(request,
		    response);
	    // 记录日志
	    logger.info(LogUtils.commonFormat("更新商品分类关系集合接口入参", params,
		    "/product/updateProductCategory", "post"));
	    // 校验入参
	    String paramMsg = StringCon.EMPTY_STR.getValue();
	    if (!NumUtils.isNum(params.get("productId"))) {
		// 商品Id
		paramMsg = "productId";
	    } else if (null == params.get("categoryList")) {
		// 商品对应的分类编号列表(三级分类)
		paramMsg = "categoryList";
	    }

	    if (!StringCon.EMPTY_STR.getValue().equals(paramMsg)) {
		// 返回结果，参数错误
		this.sendResult(response, null, false, paramMsg
			+ ErrorCode.PARAMS_FAIL.getErrorMessage(),
			ErrorCode.PARAMS_FAIL.getErrorCode());
	    } else {
		// 新增商品
		iProductService.updateProductCategory(params);
		// 返回结果
		this.sendResult(response, "success");
	    }

	} catch (PowerException e) {
	    if (logger.isDebugEnabled()) {
		e.printStackTrace();
	    }
	    this.sendResult(response, null, false, e.getErrorMsg(),
		    e.getErrorCode());
	}
    }

    /**
     * 删除商品分类映射关系表
     * 
     * @param request
     * @param response
     * @throws IOException
     * @throws PowerException
     * @author zhengyf
     */
    @RequestMapping(value = "/deleteProductCategory", method = RequestMethod.POST)
    public void deleteProductCategory(HttpServletRequest request,
	    HttpServletResponse response) throws PowerException {
	try {
	    HashMap<String, Object> params = this.getMapParams(request,
		    response);
	    // 记录日志
	    logger.info(LogUtils.commonFormat("删除商品分类关系集合接口入参", params,
		    "/product/updateProductCategory", "post"));
	    // 校验入参
	    String paramMsg = StringCon.EMPTY_STR.getValue();
	    if (!NumUtils.isNum(params.get("productId"))) {
		// 商品Id
		paramMsg = "productId";
	    } else if (null == params.get("categoryList")) {
		// 商品对应的分类编号列表(三级分类)
		paramMsg = "categoryList";
	    }

	    if (!StringCon.EMPTY_STR.getValue().equals(paramMsg)) {
		// 返回结果，参数错误
		this.sendResult(response, null, false, paramMsg
			+ ErrorCode.PARAMS_FAIL.getErrorMessage(),
			ErrorCode.PARAMS_FAIL.getErrorCode());
	    } else {
		// 新增商品
		iProductService.deleteProductCategory(params);
		// 返回结果
		this.sendResult(response, "success");
	    }

	} catch (PowerException e) {
	    if (logger.isDebugEnabled()) {
		e.printStackTrace();
	    }
	    this.sendResult(response, null, false, e.getErrorMsg(),
		    e.getErrorCode());
	}
    }
    
    
    /**
     * 主从商品复制
     * 
     * @param request
     * @param response
     * @throws IOException
     * @throws PowerException
     * @author zhengyf
     */
    @RequestMapping(value = "/masterProductCopy", method = RequestMethod.GET)
    public void masterProductCopy(HttpServletRequest request,
	    HttpServletResponse response) throws PowerException {
	try {
	    //  获取参数
	    String productMasterId = request.getParameter("productMasterId");
	    // 记录日志
	    logger.info(LogUtils.commonFormat("主从商品复制", productMasterId, "/product/masterProductCopy",
	    	"get"));
	    // 校验入参
	    if (productMasterId == null | !NumUtils.isNum(productMasterId)){
		// 返回结果，参数错误
		this.sendResult(response, null, false, "productMasterId"
			+ ErrorCode.PARAMS_FAIL.getErrorMessage(),
			ErrorCode.PARAMS_FAIL.getErrorCode());
	    } else {
		// 新增商品主键
		Integer newId = iProductService.masterProductCopy(Integer.valueOf(productMasterId));
		// 返回结果
		this.sendResult(response, newId);
	    }

	} catch (PowerException e) {
	    if (logger.isDebugEnabled()) {
		e.printStackTrace();
	    }
	    this.sendResult(response, null, false, e.getErrorMsg(),
		    e.getErrorCode());
	}
    }
    
    /**
     * 跨品类商品复制
     * 
     * @param request
     * @param response
     * @throws IOException
     * @throws PowerException
     * @author zhengyf
     */
    @RequestMapping(value = "/changeTypeCopy", method = RequestMethod.GET)
    public void changeTypeProductCopy(HttpServletRequest request,
	    HttpServletResponse response) throws PowerException {
	try {
	    //  获取参数
	    HashMap<String,Object> params = this.getMapParams(request, response);
	    // 记录日志
	    logger.info(LogUtils.commonFormat("跨品类商品复制接口", params, "/product/changeTypeCopy",
	    	"get"));
	    // 校验入参
	    String paramMsg = StringCon.EMPTY_STR.getValue();
	    if (!NumUtils.isNum(params.get("oriProductId"))) {
		// 原商品id
		paramMsg = "oriProductId";
	    } else if (!NumUtils.isNum(params.get("productType"))) {
		// 商品品类编号
		paramMsg = "productType";
	    }
	    if (!StringCon.EMPTY_STR.getValue().equals(paramMsg)){
		// 返回结果，参数错误
		this.sendResult(response, null, false, "paramMsg"
			+ ErrorCode.PARAMS_FAIL.getErrorMessage(),
			ErrorCode.PARAMS_FAIL.getErrorCode());
	    } else {
		// 新增商品
		Integer newId = iProductService.changeTypeProductCopy(params);
		// 返回结果
		this.sendResult(response, newId);
	    }

	} catch (PowerException e) {
	    if (logger.isDebugEnabled()) {
		e.printStackTrace();
	    }
	    this.sendResult(response, null, false, e.getErrorMsg(),
		    e.getErrorCode());
	}
    }
    
	
}
