package com.intelligence.business.price.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.intelligence.business.price.dao.IProductUserPriceDao;
import com.intelligence.business.price.entity.ProductUserPrice;
import com.intelligence.business.price.service.IProductUserPriceService;
import com.intelligence.common.cache.MemcacheTools;
import com.intelligence.common.constant.MemcacheConstant;
import com.intelligence.common.exception.PowerException;

@Service
public class ProductUserPriceServiceImpl implements IProductUserPriceService {

    /**
     * 日志
     */
    public final static Logger logger = LoggerFactory
	    .getLogger(ProductUserPriceServiceImpl.class);

    /**
     * memcache工具
     */
    @Resource
    private MemcacheTools memcacheTools;

    /**
     * 商品用户价格DAO层
     */
    @Resource
    private IProductUserPriceDao iProductUserPriceDao;

    /**
     * 获取商品用户价格
     * 
     * @param pageParam
     * @return
     */
    public ArrayList<ProductUserPrice> queryProductUserPrice(
	    HashMap<String, Object> pageParam) throws PowerException {
	try {
	    return iProductUserPriceDao.queryProductUserPrice(pageParam);
	} catch (PowerException e) {
	    throw e;
	}
    }

    /**
     * 获取商品用户价格数量
     * 
     * @return
     */
    public Integer queryProductUserPriceCount() throws PowerException {
		try {
		    return iProductUserPriceDao.queryProductUserPriceCount();
		} catch (PowerException e) {
		    throw e;
		}
    }
		
	
	/**
	 * 获取用户价格配置
	 * 
	 * @return
	 * @throws PowerException
	 */
	public HashMap<String, HashMap<String, ProductUserPrice>> getUserPriceConfig(Integer userId) throws PowerException {
		try {
			StringBuffer memKey = new StringBuffer(MemcacheConstant.USER_PRICE_CONFIG);
			memKey.append(userId);
			@SuppressWarnings("unchecked")
			HashMap<String, HashMap<String, ProductUserPrice>> memData = (HashMap<String, HashMap<String, ProductUserPrice>>)memcacheTools.getMemData(memKey.toString());
			if (null == memData) {
				memData = new HashMap<String, HashMap<String, ProductUserPrice>>();
				ArrayList<ProductUserPrice> configList = iProductUserPriceDao.getUserPriceConfig(userId);
				HashMap<Integer, String> configTmp = new HashMap<Integer, String>();
				configTmp.put(0, "global");
				configTmp.put(1, "country");
				configTmp.put(2, "depot");
				configTmp.put(3, "productType");
				configTmp.put(4, "productId");
				for (int i = 0, l = configList.size(); i < l; i++) {
					String tmpKey = configTmp.get(configList.get(i).getContentType());
					if (null == memData.get(tmpKey)) {
						memData.put(tmpKey, new HashMap<String, ProductUserPrice>());
					}
					memData.get(tmpKey).put(configList.get(i).getContentId().toString(), configList.get(i));		
				}
			}
			
        	return memData;
        } catch (PowerException e) {
        	throw e;
        }
    }

    /**
     * 新增用户价格
     * 
     * @return
     * @throws PowerException
     */
    public Integer addUserPrice(HashMap<String, Object> params)
	    throws PowerException {
	try {
	    // 获取时间
	    Date currentTime = new Date();
	    params.put("addTime", currentTime);
	    return iProductUserPriceDao.addUserPrice(params);
	} catch (PowerException e) {
	    throw e;
	}

    }

    /**
     * 按条件查询用户价格
     * 
     * @return
     * @throws PowerException
     */
    public List<ProductUserPrice> queryUserPriceByCondition(
	    HashMap<String, Object> params) throws PowerException {
	try {
	    // 获取时间
	    return iProductUserPriceDao.queryUserPriceByCondition(params);
	} catch (PowerException e) {
	    throw e;
	}

    }
    
    /**
     * 删除对应id的用户价格配置
     * 
     * @return
     * @throws PowerException
     */
    public void deleteUserPrice(
	    Integer productUserPriceId) throws PowerException {
	try {
	    // 获取时间
	    iProductUserPriceDao.deleteUserPrice(productUserPriceId);
	} catch (PowerException e) {
	    throw e;
	}

    }
    
    /**
     * 更新用户价格配置
     * 
     * @return
     * @throws PowerException
     */
    public Integer updateProductUserPrice(
	    HashMap<String,Object> params) throws PowerException {
	try {
	    if(1 >=  params.size()){
		return 0;
	    }
	    
	    iProductUserPriceDao.updateProductUserPrice(params);
	    return Integer.valueOf(params.get("productUserPriceId").toString());
	    
	} catch (PowerException e) {
	    throw e;
	}

    }

}
