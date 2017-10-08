package com.intelligence.business.product.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.intelligence.business.product.dao.IProductTypeDao;
import com.intelligence.business.product.entity.ProductType;
import com.intelligence.business.product.service.IProductTypeService;
import com.intelligence.common.exception.ErrorCode;
import com.intelligence.common.exception.PowerException;

@Service
public class ProductTypeServiceImpl implements IProductTypeService {

    /**
     * 记录日志
     */
    public final static Logger logger = LoggerFactory
	    .getLogger(ProductTypeServiceImpl.class);

    /**
     * 商品品类DAO层
     */
    @Resource
    private IProductTypeDao iProductTypeDao;

    /**
     * 新增一条商品品类信息
     * @throws Exception 
     */
    public Integer addProductType(HashMap<String, Object> params) throws PowerException   {
	try {
	    // 获取时间
	    Date currentTime = new Date();
	    params.put("addTime", currentTime);
	    //插入失败计数
	    if(null == params.get("addCount")){
		params.put("addCount",0);
	    }
	    StringBuffer productType = new StringBuffer();
	    //拆分父类productType
	    Map<String,String> productTypeSplitMap = this.splitProductType(Integer.valueOf(params.get("parentId").toString()));
	    //获取父类品类层级
	    Integer productTypeLevel = Integer.valueOf(productTypeSplitMap.get("productTypeLevel"));
	    //根据父类对应下级子类集合元素数量获取分类Id
	    StringBuffer childId = new StringBuffer(Integer.toString(this.getTypeChild(Integer.valueOf(params.get("parentId").toString())).size() + 1));
	    //限制最大id
	    if(999<Integer.valueOf(childId.toString())){
		return null;
	    }
	    //补齐三位id
	    for(int i=0,j=childId.length();i<3-j;i++){
		childId =childId.insert(0, "0");
	    }
	    
	    if(0 == productTypeLevel){
		//0根级
		productType = childId.append("000000"); 
	    }else if (1 == productTypeLevel){
		//一级品类
		productType = childId.insert(0,productTypeSplitMap.get("strFir")).append("000"); 
	    }else if (2 == productTypeLevel){
		//二级品类
		productType = childId.insert(0,productTypeSplitMap.get("strSec")).insert(0, productTypeSplitMap.get("strFir")); 
	    }else{
		//三级品类
		return null;
	    }
	    params.put("productType",productType.toString());
	    params.put("productTypeLevel", productTypeLevel+1);
	    return iProductTypeDao.addProductType(params);
	} catch(Exception e){
	    if(e instanceof DuplicateKeyException){
		int addCount = Integer.valueOf(params.get("addCount").toString())+1;
		//最多失败10次
		if(addCount > 10){
		    throw new PowerException("数据库插入唯一约束冲突异常失败次数过多",ErrorCode.ADD_FAIL.getErrorCode(),e);
		}
		params.replace("addCount", addCount);
		this.addProductType(params);
		throw new PowerException("数据库插入唯一约束冲突异常",ErrorCode.ADD_FAIL.getErrorCode(),e);
	    } else {
	        throw (PowerException)e;
	    }
	    }
    }

    /**
     * 更新商品分类表信息
     */
    @Override
    public Integer updateProductType(HashMap<String, Object> params)
	    throws PowerException {
	// TODO Auto-generated method stub
	try {
	    // 若入参不含任何改动，返回0
	    if (1 >= params.size()) {
		return 0;
	    } else {
		return iProductTypeDao.updateProductType(params);
	    }
	} catch (PowerException e) {
	    throw e;
	}

    }

    /**
     * 根据品类id查询指定品类下级品类合集
     */
    @Override
    public List<ProductType> getTypeChild(int productType) throws PowerException {
	try {
	    StringBuffer startId = new StringBuffer();
	    StringBuffer endId = new StringBuffer();
	    String searchCondition = null;
	    Map<String,String> typeSplitMap = this.splitProductType(productType);
	    int productTypeLevel = Integer.valueOf(typeSplitMap.get("productTypeLevel").toString());
	    if (0 == productTypeLevel) {
		//根品类入参0，查询所有一级分类集合
		startId = startId.append("001000000");
		endId = endId.append("999000000");
		searchCondition = "%000000";
	    }else{
	    String strSec = typeSplitMap.get("strSec").toString();
	    String strFir = typeSplitMap.get("strFir").toString();
	    if (3 == productTypeLevel) {
		// 三级品类无子类
		return null;
	    } else if(2 == productTypeLevel){
		// 二级品类
		startId = startId.append(strFir).append(strSec).append("001");
		endId = endId.append(strFir).append(strSec).append("999");
		searchCondition = "%";
	    }else {
		// 一级品类
		startId = startId.append(strFir).append("001000");
		endId = endId.append(strFir).append("999000");
		searchCondition = "%000";
	    }
	    }
	    return iProductTypeDao.getProductTypeChild(startId.toString(), endId.toString(),searchCondition);
	} catch (PowerException e) {
	    throw e;
	}
    }

    /**
     * 根据品类编号查询指定品类上级品类信息
     */
    @Override
    public ProductType getTypeParent(int productType) throws PowerException {
	try {
	    StringBuffer parentId = new StringBuffer();
	    Map<String,String>  typeSplitMap = this.splitProductType(productType);
	    Integer productTypeLevel = Integer.valueOf(typeSplitMap.get("productTypeLevel"));
	    //根级品类无父类
	    if (0 == productTypeLevel) {
		return null;
	    }else{
	    //拆分categoryId
	    String strSec = typeSplitMap.get("strSec");
	    String strFir = typeSplitMap.get("strFir");
	    if (1 == productTypeLevel) {
		// 一级品类父分类为根级
		parentId = parentId.append("0");
	    } else if (2 == productTypeLevel) {
		// 二级品类父类为一级
		parentId = parentId.append(strFir).append("000000");
	    } else {
		// 三级品类父类为二级
		parentId = parentId.append(strFir).append(strSec).append("000");
	    }
	    }
	    return iProductTypeDao.getProductType(Integer
		    .parseInt(parentId.toString()));
	} catch (PowerException e) {
	    throw e;
	}
    }
    
    /**
     * 根据二级或三级品类获取关联的品牌和供应商id集合
     */
    @Override
    public HashMap<String,Object> querySupBraList(int productType) throws PowerException {
	try {
	    //拆分品类
	    Map<String, String> splitMap =this.splitProductType(productType);
	    //查询条件
	    StringBuffer queryCondition = new StringBuffer();
	    //判断品类层级
	    if(splitMap.get("productTypeLevel").equals("2")){
		queryCondition.append(splitMap.get("strFir")).append(splitMap.get("strSec")).append("%");
	    }else if(splitMap.get("productTypeLevel").equals("3")){
		queryCondition.append(Integer.toString(productType));
	    }else{
		//不支持一级品类查询
		return null;
	    }
	    
	    //从数据库获取list
	    List<HashMap<String,Object>> list= iProductTypeDao.querySupBraList(queryCondition.toString());
	    //新建返回map
	    HashMap<String,Object> returnMap = new HashMap<String,Object>();
	    HashSet<Object> brandSet = new HashSet<Object>();
	    HashSet<Object> supplierSet = new HashSet<Object>();
	    //品牌供应商分别存入对应set集合
	    for(HashMap<String,Object> map:list){
		if(map.get("brandId")!=null){brandSet.add(map.get("brandId"));}
		if(map.get("supplierId")!=null){supplierSet.add(map.get("supplierId"));}
	    }
	    returnMap.put("brandIdList",(Object)brandSet);
	    returnMap.put("supplierIdList",(Object)supplierSet);
	    return returnMap;
	} catch (PowerException e) {
	    throw e;
	}
    }
    
    /**
     * 拆分productType
     * @param productType
     * @return
     */
    public Map<String, String> splitProductType(int productType) {
	// 拆分productType
	Map<String,String> returnMap = new HashMap<String,String>();
	int productTypeLevel = 0;
	if(0 == productType){
	    returnMap.put("productTypeLevel", Integer.toString(productTypeLevel));
	    return returnMap;
	}
	String productTypeString = Integer.toString(productType);
	String strThr = productTypeString.substring(productTypeString.length() - 3);
	String strSec = productTypeString.substring(productTypeString.length() - 6, productTypeString.length() - 3);
	String strFir = productTypeString.substring(0,productTypeString.length() - 6);
	
	returnMap.put("strThr", strThr);
	returnMap.put("strSec", strSec);
	returnMap.put("strFir", strFir);
	if ("000".equals(strThr) & "000".equals(strSec)) {
		// 一级品类
	    	productTypeLevel=1;
	    } else if ("000".equals(strThr) & !"000".equals(strSec)) {
		// 二级品类
		productTypeLevel=2;
	    } else if (!"000".equals(strSec) & !"000".equals(strThr)) {
		// 三级品类
		productTypeLevel=3;
	    }
	returnMap.put("productTypeLevel", Integer.toString(productTypeLevel));
	return returnMap;
    }
    
}
