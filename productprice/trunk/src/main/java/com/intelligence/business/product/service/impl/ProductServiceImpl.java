package com.intelligence.business.product.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.intelligence.business.base.service.ICodeNameService;
import com.intelligence.business.price.entity.ProductUserPrice;
import com.intelligence.business.price.service.IPriceInterfaceService;
import com.intelligence.business.product.dao.IProductDao;
import com.intelligence.business.product.entity.Product;
import com.intelligence.business.product.entity.ProductFilter;
import com.intelligence.business.product.entity.ProductFront;
import com.intelligence.business.product.entity.ProductTypeProp;
import com.intelligence.business.product.service.IProductService;
import com.intelligence.common.cache.MemcacheTools;
import com.intelligence.common.constant.StringCon;
import com.intelligence.common.exception.ErrorCode;
import com.intelligence.common.exception.PowerException;
import com.intelligence.common.utils.StrUtils;

@Service
public class ProductServiceImpl implements IProductService {

	/**
	 * 日志
	 */
	public final static Logger logger = LoggerFactory
			.getLogger(ProductServiceImpl.class);

	/**
	 * memcache工具
	 */
	@Resource
	private MemcacheTools memcacheTools;
	
	/**
	 * 键值映射服务层
	 */
	@Resource
	private ICodeNameService iCodeNameService;
	
	/**
	 * 价格服务接口层
	 */
	@Resource
	private IPriceInterfaceService iPriceInterfaceService;
	
	/**
	 * 商品DAO
	 */
	@Resource
	private IProductDao iProductDao;

	/**
	 * 新增商品
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Integer addProduct(HashMap<String, Object> productParam)
			throws PowerException {
		try {
			// 获取当前时间
			Date currentTime = new Date();

			// 新增详情
			HashMap<String, Object> productDetail = new HashMap<String, Object>();
			productDetail.put("detail", productParam.get("detail"));
			productDetail.put("addTime", currentTime);
			Integer detailId = iProductDao.addDetail(productDetail);
			productDetail = null;
			productParam.remove("detail");

			// 初始化商品主从标记，默认初始化为独立主商品
			productParam.put("masterFlag", 2);
			
			// 新增商品
			productParam.put("detailId", detailId);
			productParam.put("addTime", currentTime);
			return iProductDao.addProduct(productParam);

		} catch (PowerException e) {
			throw e;
		}
	}

	/**
	 * 新增组合商品子商品
	 */
	public boolean addProductSub(HashMap<String, Object> productParam) throws PowerException {
		try {
			// 校验当前商品
			Integer groupFlag = iProductDao.queryProductIsGroup((Integer)productParam.get("productId"));
			
			// 新增组合商品子商品
			if (1 == groupFlag) {
				iProductDao.addProductSub(productParam);
				return true;
			} else {
				return false;
			}

		} catch (PowerException e) {
			throw e;
		}
	}
    
    /**
	 * 获取商品信息
	 * 
	 * @param params
	 * @return
	 * @throws PowerException
	 */
	public HashMap<String, Object> getProductInfo(HashMap<String, Object> params) throws PowerException {
		try {
			// 判断检索关键词是否是英文
			if (StrUtils.isEnglish(params.get("key").toString())) {
				params.put("productNameEn", params.get("key"));
			} else {
				params.put("productName", params.get("key"));
			}
			// 处理分类
			Integer categoryId = (Integer)params.get("categoryId");
			if (0 != categoryId % 1000) {
				params.put("categoryIdTh", categoryId);
			} else if (0 == categoryId % 1000) {
				params.put("categoryIdTo", categoryId);
				params.put("categoryIdToE", categoryId + 999);
			} else if (0 == categoryId % 1000000) {
				params.put("categoryIdTo", categoryId);
				params.put("categoryIdToE", categoryId + 999999);
			}
			// 设置分表表名参数
			params.put("productTbl", getFrontProductTbl(params));
			// 处理排序
			StringBuffer orderSb = new StringBuffer();
			if (("price").equals(params.get("orderField"))) {
				orderSb.append("a.price");
			} else if (("sellNum").equals(params.get("orderField"))) {
				orderSb.append("a.sell_num");
			} else if (("addTime").equals(params.get("orderField"))) {
				orderSb.append("a.add_time");
			}
			orderSb.append(StringCon.SPACE.getValue());
			if (("DESC").equals(params.get("orderSeq"))) {
				orderSb.append("DESC");
			} else {
				orderSb.append("ASC");
			}
			params.put("orderNeed", orderSb);
			
			// 根据检索条件，获取商品ID数据
			HashMap<String, Object> proIdsData = iProductDao.queryFrontProducts(params);
				
			// 如果没有数据，则直接返回空
			if (0 == (Integer)proIdsData.get("count")) {
				return proIdsData;
			} else {
				// 如果有数据，则取缓存数据
				ArrayList<Product> listRows = new ArrayList<Product>();
				@SuppressWarnings("unchecked")
				ArrayList<ProductFront> proIds = (ArrayList<ProductFront>)proIdsData.get("rows");
				for (int i = 0, l = proIds.size(); i < l; i++) {
					// 处理商品数据
					Product productData = iCodeNameService.mapProductIdData(proIds.get(i).getProductId());
					// 处理价格
					productData.setUniPrice(proIds.get(i).getUniPrice());
					// 添加商品数据
					listRows.add(productData);				
				}
				HashMap<String, Object> result = new HashMap<String, Object>();
				result.put("count", (Integer)proIdsData.get("count"));
				result.put("rows", listRows);
				return result;
			}	
		} catch (PowerException e) {
		    throw e;
		}
	}
	
	/**
	 * 获取配置的价格
	 * 
	 * @param purchasePrice
	 * @param rate
	 * @param productUserPrice
	 * @return
	 */
	public BigDecimal getConfigPrice(BigDecimal purchasePrice, BigDecimal rate, ProductUserPrice productUserPrice) {
		if (!BigDecimal.ZERO.equals(productUserPrice.getPriceBalRate())) {
			return purchasePrice.multiply(rate.multiply(productUserPrice.getPriceBalRate()));
		} else if (!BigDecimal.ZERO.equals(productUserPrice.getPriceBal())) {
			return (purchasePrice.multiply(rate)).add(productUserPrice.getPriceBal());
		} else {
			return purchasePrice.multiply(rate);
		}
	}
    
	/**
	 * 根据当前天是一年的第几天获取前台表后缀
	 * 
	 * @return
	 */
	public boolean getProductFrontTblSub() {
		Calendar cal = Calendar.getInstance();
	    return (0 == cal.get(Calendar.DAY_OF_YEAR) % 2) ? false : true;		
	}
	
	/**
	 * 获取前台商品表名
	 * 
	 * @param param
	 * @return
	 */
	public String getFrontProductTbl(HashMap<String, Object> param) {
		// 初始化基础表名
		StringBuffer baseTblName = new StringBuffer("product_front_");
		// 拼接不规则分表国家ID尾缀
		if (null == param.get("countryId")) {
			baseTblName.append("master");
		} else {
			baseTblName.append(param.get("countryId"));
		}
		// 根据日期判断采用哪套表
		baseTblName.append(getProductFrontTblSub());
		// 返回表名
		return baseTblName.toString();
	}           
	
	/**
	 * 根据分类查询品类和规格
	 * 
	 * @param params
	 * @return
	 * @throws PowerException
	 */
	public HashMap<String, ArrayList<HashMap<String, Object>>> getFrontProps(HashMap<String, Object> params) throws PowerException {
		try {
			// 初始化筛选项返回结果
			HashMap<String, ArrayList<HashMap<String, Object>>> productFilterRe = new HashMap<String, ArrayList<HashMap<String, Object>>>();
			productFilterRe.put("country", new ArrayList<HashMap<String, Object>>());
			productFilterRe.put("category", new ArrayList<HashMap<String, Object>>());
			productFilterRe.put("brand", new ArrayList<HashMap<String, Object>>());
			productFilterRe.put("prop", new ArrayList<HashMap<String, Object>>());
			// 初始化筛选项查询参数
			HashMap<String, Object> productFilterPa = new HashMap<String, Object>();
			HashMap<String, Object> tmp = null;
			// 初始化不规则分表表名
			productFilterPa.put("productTbl", getFrontProductTbl(params));
			// 分级处理筛选项查询
			if (0 == (Integer)params.get("categoryId")) {
				// 没选择分类，没选择国家或者没选择分类，选择了国家-3
				productFilterPa.put("filterLevel", 0);
				if (null != params.get("countryId")) {
					productFilterPa.put("countryId", params.get("countryId"));
				}
				ArrayList<ProductFilter> productFilters = iProductDao.queryProductFilter(productFilterPa);
				TreeSet<Integer> countryIds = new TreeSet<Integer>();
				TreeSet<Integer> categoryIds = new TreeSet<Integer>();
				for (int i = 0, l = productFilters.size(); i < l; i++) {
					countryIds.add(productFilters.get(i).getCountryId());
					categoryIds.add(productFilters.get(i).getCategoryId());
				}
				Iterator<Integer> itCategoryIds = categoryIds.iterator();
				while(itCategoryIds.hasNext()) {
					tmp = new HashMap<String, Object>();
					Integer categoryIdTmp = itCategoryIds.next();
					tmp.put("categoryId", categoryIdTmp);
					tmp.put("categoryName", iCodeNameService.mapCategoryCodeName(categoryIdTmp));
					productFilterRe.get("category").add(tmp);
				}
				if (null == params.get("countryId")) {
					Iterator<Integer> itCountryIds = countryIds.iterator();
					while(itCountryIds.hasNext()) {
						tmp = new HashMap<String, Object>();
						Integer countryIdTmp = itCountryIds.next();
						tmp.put("countryId", countryIdTmp);
						tmp.put("countryName", iCodeNameService.mapCountryCodeName(countryIdTmp));
						productFilterRe.get("country").add(tmp);
					}				
				} else {
					tmp = new HashMap<String, Object>();
					Integer countryIdTmp = (Integer)params.get("countryId");
					tmp.put("countryId", countryIdTmp);
					tmp.put("countryName", iCodeNameService.mapCountryCodeName(countryIdTmp));
					productFilterRe.get("country").add(tmp);
				}
			} else if (0 == (Integer)params.get("categoryId") % 1000000) {
				// 处理一级分类，两种传参，国家ID，分类ID
				ArrayList<ProductFilter> productFilters = null;
				Integer[] countryIdsPa = null;
				
				// 第一步，获取所拥有的国家
				if (null == params.get("countryId")) {
					productFilterPa.put("filterLevel", 0);
					productFilterPa.put("categoryId", params.get("categoryId"));
					productFilters = iProductDao.queryProductFilter(productFilterPa);
					TreeSet<Integer> countryIds = new TreeSet<Integer>();
					for (int i = 0, l = productFilters.size(); i < l; i++) {
						countryIds.add(productFilters.get(i).getCountryId());
					}
					Iterator<Integer> itCountryIds = countryIds.iterator();
					countryIdsPa = new Integer[countryIds.size()];
					while(itCountryIds.hasNext()) {
						tmp = new HashMap<String, Object>();
						Integer countryIdTmp = itCountryIds.next();
						tmp.put("countryId", countryIdTmp);
						tmp.put("countryName", iCodeNameService.mapCategoryCodeName(countryIdTmp));
						productFilterRe.get("country").add(tmp);
					}
				} else {
					countryIdsPa = new Integer[1];
					countryIdsPa[0] = (Integer)params.get("categoryId");
				}
				// 第二步，根据所拥有的国家，获取对应的二级分类
				productFilterPa.put("filterLevel", 1);
				productFilterPa.put("countryIds", countryIdsPa);
				productFilterPa.put("categoryIdS", params.get("categoryId"));
				productFilterPa.put("categoryIdE", (Integer)params.get("categoryId") + 1000000);
				productFilterPa.put("countryId", null);
				productFilterPa.put("categoryId", null);
				productFilters = iProductDao.queryProductFilter(productFilterPa);
				TreeSet<Integer> categoryIds = new TreeSet<Integer>();
				for (int i = 0, l = productFilters.size(); i < l; i++) {
					categoryIds.add(productFilters.get(i).getCategoryId());
				}
				Iterator<Integer> itCategoryIds = categoryIds.iterator();
				while(itCategoryIds.hasNext()) {
					tmp = new HashMap<String, Object>();
					Integer categoryIdTmp = itCategoryIds.next();
					tmp.put("categoryId", categoryIdTmp);
					tmp.put("categoryName", iCodeNameService.mapCategoryCodeName(categoryIdTmp));
					productFilterRe.get("category").add(tmp);
				}
			} else if (0 == (Integer)params.get("categoryId") % 1000) {
				// 处理二级分类
				productFilterPa.put("categoryIdS", params.get("categoryId"));
				productFilterPa.put("categoryIdE", (Integer)params.get("categoryId") + 1000);
				if (null != params.get("countryId")) {
					productFilterPa.put("countryId", params.get("countryId"));	
				}
				ArrayList<ProductFilter> productFilters = iProductDao.queryProductFilterLow(productFilterPa);
				TreeSet<Integer> brandIds = new TreeSet<Integer>();
				TreeSet<Integer> countryIds = new TreeSet<Integer>();
				TreeSet<Integer> categoryIds = new TreeSet<Integer>();
				for (int i = 0, l = productFilters.size(); i < l; i++) {
					brandIds.add(productFilters.get(i).getBrandId());
					countryIds.add(productFilters.get(i).getCountryId());
					categoryIds.add(productFilters.get(i).getCategoryId());
				}
				Iterator<Integer> itBrandIds = brandIds.iterator();
				while(itBrandIds.hasNext()) {
					tmp = new HashMap<String, Object>();
					Integer brandIdTmp = itBrandIds.next();
					tmp.put("brandId", brandIdTmp);
					tmp.put("brandName", iCodeNameService.mapBrandCodeName(brandIdTmp));
					productFilterRe.get("brand").add(tmp);
				}
				Iterator<Integer> itCountryIds = countryIds.iterator();
				while(itCountryIds.hasNext()) {
					tmp = new HashMap<String, Object>();
					Integer countryIdTmp = itCountryIds.next();
					tmp.put("countryId", countryIdTmp);
					tmp.put("countryName", iCodeNameService.mapCountryCodeName(countryIdTmp));
					productFilterRe.get("country").add(tmp);
				}
				Iterator<Integer> itCategoryIds = categoryIds.iterator();
				while(itCategoryIds.hasNext()) {
					tmp = new HashMap<String, Object>();
					Integer categoryIdTmp = itCategoryIds.next();
					tmp.put("categoryId", categoryIdTmp);
					tmp.put("categoryName", iCodeNameService.mapCategoryCodeName(categoryIdTmp));
					productFilterRe.get("category").add(tmp);
				}
			} else if (0 != (Integer)params.get("categoryId") % 1000) {
				// 处理三级分类
				productFilterPa.put("categoryIdTh", params.get("categoryId"));
				if (null != params.get("countryId")) {
					productFilterPa.put("countryId", params.get("countryId"));	
				}
				if (null != params.get("brandId")) {
					productFilterPa.put("brandId", params.get("brandId"));	
				}
				ArrayList<ProductFilter> productFilters = iProductDao.queryProductFilterLow(productFilterPa);
				TreeSet<Integer> propIds = new TreeSet<Integer>();
				TreeSet<Integer> brandIds = new TreeSet<Integer>();
				TreeSet<Integer> countryIds = new TreeSet<Integer>();
				for (int i = 0, l = productFilters.size(); i < l; i++) {
					propIds.add(productFilters.get(i).getPropId());
					brandIds.add(productFilters.get(i).getBrandId());
					countryIds.add(productFilters.get(i).getCountryId());
				}
				Iterator<Integer> itPropIds = propIds.iterator();
				while(itPropIds.hasNext()) {
					tmp = new HashMap<String, Object>();
					Integer propIdTmp = itPropIds.next();
					tmp.put("propId", propIdTmp);
					tmp.put("propName", iCodeNameService.mapBrandCodeName(propIdTmp));
					productFilterRe.get("prop").add(tmp);
				}
				Iterator<Integer> itBrandIds = brandIds.iterator();
				while(itBrandIds.hasNext()) {
					tmp = new HashMap<String, Object>();
					Integer brandIdTmp = itBrandIds.next();
					tmp.put("brandId", brandIdTmp);
					tmp.put("brandName", iCodeNameService.mapBrandCodeName(brandIdTmp));
					productFilterRe.get("brand").add(tmp);
				}
				Iterator<Integer> itCountryIds = countryIds.iterator();
				while(itCountryIds.hasNext()) {
					tmp = new HashMap<String, Object>();
					Integer countryIdTmp = itCountryIds.next();
					tmp.put("countryId", countryIdTmp);
					tmp.put("countryName", iCodeNameService.mapCountryCodeName(countryIdTmp));
					productFilterRe.get("country").add(tmp);
				}
			} else if (0 != (Integer)params.get("propId") % 1000) {
				// 处理规格
				productFilterPa.put("propId", params.get("propId"));
				if (null != params.get("countryId")) {
					productFilterPa.put("countryId", params.get("countryId"));	
				}
				if (null != params.get("brandId")) {
					productFilterPa.put("brandId", params.get("brandId"));	
				}
				ArrayList<ProductFilter> productFilters = iProductDao.queryProductFilterLow(productFilterPa);
				TreeSet<Integer> brandIds = new TreeSet<Integer>();
				TreeSet<Integer> countryIds = new TreeSet<Integer>();
				for (int i = 0, l = productFilters.size(); i < l; i++) {
					brandIds.add(productFilters.get(i).getBrandId());
					countryIds.add(productFilters.get(i).getCountryId());
				}
				Iterator<Integer> itBrandIds = brandIds.iterator();
				while(itBrandIds.hasNext()) {
					tmp = new HashMap<String, Object>();
					Integer brandIdTmp = itBrandIds.next();
					tmp.put("brandId", brandIdTmp);
					tmp.put("brandName", iCodeNameService.mapBrandCodeName(brandIdTmp));
					productFilterRe.get("brand").add(tmp);
				}
				Iterator<Integer> itCountryIds = countryIds.iterator();
				while(itCountryIds.hasNext()) {
					tmp = new HashMap<String, Object>();
					Integer countryIdTmp = itCountryIds.next();
					tmp.put("countryId", countryIdTmp);
					tmp.put("countryName", iCodeNameService.mapCountryCodeName(countryIdTmp));
					productFilterRe.get("country").add(tmp);
				}
			}
			
			// 返回结果
			return productFilterRe;
		} catch(PowerException e) {
			throw e;
		}
	}
	
	/**
	 * 获取前端商品详情
	 * 
	 * @param params
	 * @return
	 * @throws PowerException
	 */
	public HashMap<String, Object> getFrontProductDetail(HashMap<String, Object> params) throws PowerException {
		try {
			// 初始化返回结果
			HashMap<String, Object> result = new HashMap<String, Object>();
			
			// 获取商品数据
			Integer productId = (Integer)params.get("productId");
			Product productData = iCodeNameService.mapProductIdData(productId);
			// 获取价格配置
			HashMap<String, HashMap<String, ProductUserPrice>> priceConfig = iPriceInterfaceService.getUserPriceConfig(0);
			// 获取汇率
			HashMap<Integer, BigDecimal> rateMap = iPriceInterfaceService.getRateByCountry();
			// 处理价格
			BigDecimal rateTmp = rateMap.get(productData.getCountryId());
			if (null != priceConfig.get("productId")) {
				HashMap<String, ProductUserPrice> configTmp = priceConfig.get("productId");
				if (null != configTmp.get(productData.getProductId())) {
					productData.setUniPrice(this.getConfigPrice(productData.getUniPurchasePrice(), rateTmp, configTmp.get(productData.getProductId())));
				}
			} else if (null != priceConfig.get("productType")) {
				HashMap<String, ProductUserPrice> configTmp = priceConfig.get("productType");
				if (null != configTmp.get(productData.getProductType())) {
					productData.setUniPrice(this.getConfigPrice(productData.getUniPurchasePrice(), rateTmp, configTmp.get(productData.getProductType())));
				}
			}  else if (null != priceConfig.get("country")) {
				HashMap<String, ProductUserPrice> configTmp = priceConfig.get("country");
				if (null != configTmp.get(productData.getCountryId())) {
					productData.setUniPrice(this.getConfigPrice(productData.getUniPurchasePrice(), rateTmp, configTmp.get(productData.getCountryId())));
				}
			} else if (null != priceConfig.get("global")) {
				HashMap<String, ProductUserPrice> configTmp = priceConfig.get("global");
				if (null != configTmp.get(0)) {
					productData.setUniPrice(this.getConfigPrice(productData.getUniPurchasePrice(), rateTmp, configTmp.get(0)));
				}
			} else {
				productData.setUniPrice(productData.getUniPurchasePrice().multiply(rateTmp));
			}
			result.put("baseInfo", productData);
			
			// 获取详情
			StringBuffer memKey = new StringBuffer("detail");
			memKey.append(productData.getDetailId());
			StringBuilder detail= (StringBuilder)memcacheTools.getMemData(memKey.toString());
			if (null == detail) {
				detail = iProductDao.queryProductDetail(productData.getDetailId());
				memcacheTools.setMemData(memKey.toString(), 7200, detail);
			}
			result.put("detail", detail);
			detail = null;
			
			// 获取不同主从产品的规格
			if (1 == productData.getMasterFlag()) {
				memKey = new StringBuffer("props");
				memKey.append(productId);
				@SuppressWarnings("unchecked")
				HashMap<String, Object> props = (HashMap<String, Object>)memcacheTools.getMemData(memKey.toString());
				if (null == props) {
					props = new HashMap<String, Object>();
					ArrayList<HashMap<String, Object>> proPropData = iProductDao.queryProductProp(productId);
					HashSet<Integer> parentPropIds = new HashSet<Integer>();
					ProductTypeProp ptpTmp = null;
					for(int i = 0, l = proPropData.size(); i < l; i++) {
						ptpTmp = iCodeNameService.mapPtpCodeInfo((Integer)proPropData.get(i).get("propId"));
						proPropData.get(i).put("propParentId", ptpTmp.getPropParentId());
						proPropData.get(i).put("propValue", ptpTmp.getPropValue());
						parentPropIds.add(ptpTmp.getPropParentId());
					}
					props.put("props", proPropData);
					ArrayList<ProductTypeProp> ptpParents = new ArrayList<ProductTypeProp>();
					Iterator<Integer> itParentPropIds = parentPropIds.iterator();
					while (itParentPropIds.hasNext()) {
						ptpParents.add(iCodeNameService.mapPtpCodeInfo(itParentPropIds.next()));
					}
					props.put("propParents", proPropData);
					memcacheTools.setMemData(memKey.toString(), 7200, props);
				}
				result.putAll(props);
			} 
			
			// 返回结果
			return result;	
		} catch (PowerException e) {
			throw e;
		}
	}
    
	/**
	 * 删除组合商品子商品
	 * 
	 * @param params
	 * @return
	 * @throws PowerException
	 */
	public Enum<ErrorCode> delProductSub(HashMap<String, Object> params) throws PowerException {
		try {
			// 判断商品下单
//			ErrorCode.ORDER_PRO_DEL_FAIL
			// 删除组合商品子商品
			Integer flag = iProductDao.delProductSub((Integer)params.get("productSubId"));
			if (1 == flag) {
				return ErrorCode.DELETE_SUCCESS; 
			} else {
				return ErrorCode.DOU_DEL_FAIL;
			}
		} catch (PowerException e) {
			throw e;
		}
	}
	
	/**
	 * 更新商品
	 * 
	 * @param params
	 * @throws PowerException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateProduct(HashMap<String, Object> params) throws PowerException {
		try {
			// 下架商品
			if (1 == (Integer)params.get("checkFlag")) {
				iProductDao.offlineProduct((Integer)params.get("productId"));
			}
			
			// 更新商品
			params.put("updateTime", new Date());
			iProductDao.updateProduct(params);
			
			// 更新详情
			if (null != params.get("detail")) {
				iProductDao.updateComDetail((StringBuilder)params.get("detail"), (Integer)params.get("detailId"));				
			}
			
		} catch (PowerException e) {
			throw e;
		}
	}
	
	/**
	 * 查看商品詳情
	 * 
	 * @param params
	 * @return
	 * @throws PowerException
	 */
	public HashMap<String, Object> getProductDetailInfo(Integer productId) throws PowerException {
		
		try {
			// 获取商品基本信息
			HashMap<String, Object> baseParam = new HashMap<String, Object>();
			baseParam.put("id", productId);
			ArrayList<Product> productBaseInfo = iProductDao.queryProductData(baseParam);
			if (null == productBaseInfo) {
				throw new PowerException(ErrorCode.NO_PRODUCT_FAIL.getErrorMessage(), ErrorCode.NO_PRODUCT_FAIL.getErrorCode());
			}
			Product baseInfo = productBaseInfo.get(0);
			
			// 获取商品详情
			StringBuilder detail = iProductDao.queryProductDetail(baseInfo.getDetailId());
			
			// 获取商品分类关联
			
			
			// 获取商品仓库关联
			
			
			// 获取商品规格属性
			
			
			
		} catch (PowerException e) {
			throw e;
		}
		
		return null;
		
	}
	
    
    
    
     /**
     * 新增商品分类映射关系记录
     */
    public Integer addProductCategory(HashMap<String, Object> Params)
	    throws PowerException {
	try {
	    // 获取当前时间
	    Date currentTime = new Date();
	    Params.put("addTime", currentTime);
	    // 新增商品
	    return iProductDao.addProductCategory(Params);

	} catch (PowerException e) {
	    throw e;
	}
    }

    /**
     * 查询商品分类映射关系记录
     */
    public List<Integer> selectProductCategory(Integer productId)
	    throws PowerException {
	try {
	    // 新增商品
	    return iProductDao.selectProductCategory(productId);
	} catch (PowerException e) {
	    throw e;
	}
    }

    /**
     * 删除商品分类映射关系记录
     */
    public void deleteProductCategory(HashMap<String, Object> params)
	    throws PowerException {
	try {
	    // 删除商品
	    iProductDao.deleteProductCategory(params);
	} catch (PowerException e) {
	    throw e;
	}
    }

    /**
     * 更新商品分类映射关系记录
     */
    @SuppressWarnings("unchecked")
    public void updateProductCategory(HashMap<String, Object> params)
	    throws PowerException {
	try {
	    // 更新商品
	    Integer productId = Integer.valueOf(params.get("productId")
		    .toString());

	    // 更新列表
	    List<Integer> updateList = (List<Integer>) params
		    .get("categoryList");

	    // 删除列表
	    List<Integer> deleteList = new ArrayList<Integer>();

	    List<Integer> categoryList = this.selectProductCategory(productId);
	    // 遍历数据库中的映射关系和更新的映射关系
	    for (Integer sqlCategoryId : categoryList) {
		boolean deleteMark = true;
		for (Iterator<Integer> it = updateList.iterator(); it.hasNext();) {
		    Integer value = it.next();
		    if (value.equals(sqlCategoryId)) {
			deleteMark = false;
			it.remove();
		    }
		}
		if (deleteMark) {
		    // 需要删除
		    deleteList.add(sqlCategoryId);
		}
	    }
	    // 完成不重复项的新增操作
	    if (updateList != null & 0 < updateList.size()) {
		params.replace("categoryList", updateList);
		this.addProductCategory(params);
	    }
	    // 完成删除项的删除操作
	    if (deleteList != null & 0 < deleteList.size()) {
		params.replace("categoryList", deleteList);
		this.deleteProductCategory(params);
	    }
	} catch (PowerException e) {
	    throw e;
	}
    }
    
    	/**
	 * 主从商品复制
	 * @param productMasterId
	 */
	public Integer masterProductCopy(Integer productMasterId) throws PowerException{
	    try{
		HashMap<String,Object> masterMap = iProductDao.queryProduct(productMasterId);
		// 获取时间
		Date currentTime = new Date();
		masterMap.put("addTime", currentTime);
		//置顶权重置0
		masterMap.replace("topRank",0);
		//移除审核标记
		masterMap.remove("checkFlag");
		//移除组合商品标记
		masterMap.remove("groupFlag");
		//设置父商品id
		masterMap.replace("productMasterId", productMasterId);
		//移除主键
		masterMap.remove("productId");
		//设置子商品标记
		masterMap.replace("masterFlag", 0);
		return iProductDao.masterProductCopy(masterMap);
	    }catch(PowerException e){
		throw e;
	    }
	}
	
	/**
	 * 跨品类（主商品-主商品）商品复制
	 * @param productMasterId
	 */
	public Integer changeTypeProductCopy(HashMap<String,Object> params) throws PowerException{
	    try{
		HashMap<String,Object> masterMap = iProductDao.queryProduct(Integer.valueOf(params.get("oriProductId").toString()));
		// 获取时间
		Date currentTime = new Date();
		masterMap.put("addTime", currentTime);
		//移除父商品id
		masterMap.remove("productMasterId");
		//移除主键
		masterMap.remove("productId");
		//移除审核标记
		masterMap.remove("checkFlag");
		//移除详情
		masterMap.remove("detailId");
		//移除组合商品标记
		masterMap.remove("groupFlag");
		//置顶权重置0
		masterMap.replace("topRank",0);
		//设置品类
		masterMap.replace("productType", params.get("productType"));

		return iProductDao.changeTypeProductCopy(masterMap);
	    }catch(PowerException e){
		throw e;
	    }
	}
    
    
}
