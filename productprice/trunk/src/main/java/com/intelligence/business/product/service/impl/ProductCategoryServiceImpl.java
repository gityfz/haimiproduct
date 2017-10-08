package com.intelligence.business.product.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.intelligence.business.product.dao.IProductCategoryDao;
import com.intelligence.business.product.entity.Category;
import com.intelligence.business.product.service.IProductCategoryService;
import com.intelligence.common.exception.ErrorCode;
import com.intelligence.common.exception.PowerException;

/**
 * @author 作者 E-mail:bzp
 * @date 创建时间：2017年1月13日 下午4:47:23
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {
    /**
     * 日志
     */
    public final static Logger logger = LoggerFactory
	    .getLogger(ProductCategoryServiceImpl.class);

    /**
     * 商品分类DAO
     */
    @Resource
    private IProductCategoryDao iProductCategoryDao;

    /**
     * 添加一条分类表信息
     */
    @Override
    public Integer addCategory(HashMap<String, Object> params)
	    throws PowerException {
	// TODO Auto-generated method stub
	try {
	    // 获取时间
	    Date currentTime = new Date();
	    params.put("addTime", currentTime);
	    StringBuffer categoryId = new StringBuffer();
	    //插入失败计数
	    if(null == params.get("addCount")){
		params.put("addCount",0);
	    }
	    
	    //拆分父类categoryId
	    Map<String,String> categorySplitMap = this.splitCategoryId(Integer.valueOf(params.get("parentId").toString()));
	    //获取父类分类层级
	    Integer categoryLevel = Integer.valueOf(categorySplitMap.get("categoryLevel"));
	    //根据父类对应下级子类集合元素数量获取分类Id
	    StringBuffer childId =new StringBuffer(Integer.toString(this.getCategoryChild(Integer.valueOf(params.get("parentId").toString())).size() + 1));
	    //补齐三位id
	    for(int i=0,j=childId.length();i<3-j;i++){
		childId.insert(0,"0");
	    }
	    //限制最大id
	    if(999<Integer.valueOf(childId.toString())){
		return null;
	    }
	    
	    if(0 == categoryLevel){
		//0根级
		categoryId.append(childId.toString()).append("000000"); 
	    }else if (1 == categoryLevel){
		//一级分类
		categoryId.append(categorySplitMap.get("strFir")).append(childId.toString()).append("000"); 
	    }else if (2 == categoryLevel){
		//二级分类
		categoryId = categoryId.append(categorySplitMap.get("strFir")).append(categorySplitMap.get("strSec")).append(childId.toString()); 
	    }else{
		//三级分类
		return null;
	    }
	    params.put("categoryId",categoryId.toString());
	    params.put("categoryLevel", categoryLevel+1);
	    return iProductCategoryDao.addCategory(params);
	} catch (Exception e) {
	    if(e instanceof DuplicateKeyException){
		int addCount = Integer.valueOf(params.get("addCount").toString())+1;
		//最多失败10次
		if(addCount > 10){
		    throw new PowerException("数据库插入唯一约束冲突异常失败次数过多",ErrorCode.ADD_FAIL.getErrorCode(),e);
		}
		params.replace("addCount", addCount);
		this.addCategory(params);
		throw new PowerException("数据库插入唯一约束冲突异常",ErrorCode.ADD_FAIL.getErrorCode(),e);
	    } else {
	        throw (PowerException)e;
	    }
	}

    }

    /**
     * 更新分类表信息
     */
    @Override
    public Integer updateCategory(HashMap<String, Object> params)
	    throws PowerException {
	// TODO Auto-generated method stub
	try {// 若入参不含任何改动，返回0
	    if (1 >= params.size()) {
		return 0;
	    } else {
		return iProductCategoryDao.updateCategory(params);
	    }
	} catch (PowerException e) {
	    throw e;
	}

    }

    
    /**
     * 获取指定分类的子分类集合
     * 
     * @param categoryId
     * @return List<Category>
     */
    public List<Category> getCategoryChild(int categoryId)
	    throws PowerException {
	try {
	    StringBuffer startId =new StringBuffer();
	    StringBuffer endId = new StringBuffer();
	    String searchCondition = null;
	    Map<String,String> categorySplitMap = this.splitCategoryId(categoryId);
	    Integer categoryLevel = Integer.valueOf(categorySplitMap.get("categoryLevel"));
	    if (0 == categoryLevel ) {
		//根分类入参0，查询所有一级分类集合
		startId.append("001000000");
		endId.append("999000000");
		searchCondition = "%000000";
	    } else {
		String strSec = categorySplitMap.get("strSec");
		String strFir = categorySplitMap.get("strFir");
		if (3 == categoryLevel) {
		    // 三级分类无子分类
		    return null;
		} else if (2 == categoryLevel) {
		    // 二级分类
		    startId.append(strFir).append(strSec).append("001");
		    searchCondition = "%";
		    endId.append(strFir).append(strSec).append("999");
		} else {
		    // 一级分类
		    startId.append(strFir).append("001000");
		    endId.append(strFir).append("999000");
		    searchCondition = "%000";
		}
	    }
	    return iProductCategoryDao.getChildList(startId.toString(), endId.toString(),
		    searchCondition);

	} catch (PowerException e) {
	    throw e;
	}
    }

    /**
     * 获取指定分类的父分类信息
     * 
     * @param categoryId
     * @return Category
     */
    public Category getCategoryParent(int categoryId) throws PowerException {
	try {
	    StringBuffer parentId = new StringBuffer();
	    Map<String,String>  categorySplitMap = this.splitCategoryId(categoryId);
	    Integer categoryLevel = Integer.valueOf(categorySplitMap.get("categoryLevel"));
	    //根级分类无父类
	    if (0 == categoryLevel) {
		return null;
	    }else{
	    //拆分categoryId
	    String strSec = categorySplitMap.get("strSec");
	    String strFir = categorySplitMap.get("strFir");
	    if (1 == categoryLevel) {
		// 一级分类父分类为根级
		parentId.append("0");
	    } else if (2 == categoryLevel) {
		// 二级分类父类为一级
		parentId.append(strFir).append("000000");
	    } else {
		// 三级分类父类为二级
		parentId.append(strFir).append(strSec).append("000");
	    }
	    }
	    return iProductCategoryDao.getCategoryMap(Integer
		    .parseInt(parentId.toString()));

	} catch (PowerException e) {
	    throw e;
	}
    }
    
    /**
     * 拆分categoryId
     * @param categoryId
     * @return
     */
    public Map<String, String> splitCategoryId(int categoryId) {
	// 拆分categoryId
	Map<String,String> returnMap = new HashMap<String,String>();
	int categoryLevel = 0;
	if(0 == categoryId){
	    returnMap.put("categoryLevel", Integer.toString(categoryLevel));
	    return returnMap;
	}
	String categoryIdString = Integer.toString(categoryId);
	String strThr = categoryIdString
		.substring(categoryIdString.length() - 3);
	String strSec = categoryIdString.substring(
		categoryIdString.length() - 6, categoryIdString.length() - 3);
	String strFir = categoryIdString.substring(0,
		categoryIdString.length() - 6);
	returnMap.put("strThr", strThr);
	returnMap.put("strSec", strSec);
	returnMap.put("strFir", strFir);
	if ("000".equals(strThr) & "000".equals(strSec)) {
		// 一级分类
	    	categoryLevel=1;
	    } else if ("000".equals(strThr) & !"000".equals(strSec)) {
		// 二级分类
		categoryLevel=2;
	    } else if (!"000".equals(strSec) & !"000".equals(strThr)) {
		// 三级分类
		categoryLevel=3;
	    }
	returnMap.put("categoryLevel", Integer.toString(categoryLevel));
	return returnMap;
    }
}
