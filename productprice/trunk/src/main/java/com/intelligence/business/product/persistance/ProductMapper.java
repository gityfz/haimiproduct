package com.intelligence.business.product.persistance;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.intelligence.business.product.entity.Product;
import com.intelligence.business.product.entity.ProductFilter;
import com.intelligence.business.product.entity.ProductFront;

public interface ProductMapper {

	/**
	 * 新增商品
	 * 
	 * @param product
	 */
	void insertProduct(HashMap<String, Object> product);

	/**
	 * 新增明细
	 * 
	 * @param product
	 */
	void insertDetail(HashMap<String, Object> detail);
	
	/**
	 * 更新明细
	 * 
	 * @param product
	 */
	void updateDetail(HashMap<String, Object> detail);
	
	/**
	 * 获取商品数据
	 * 
	 * @param productParam
	 * @return
	 */
	ArrayList<Product> queryProductData(HashMap<String, Object> productParam);
	
	/**
	 * 获取商品数据
	 * 
	 * @param productParam
	 * @return
	 */
	ArrayList<ProductFront> queryFrontProductIds(HashMap<String, Object> productParam);
	
	/**
	 * 获取前端商品ID群数量
	 * 
	 * @param productParam
	 * @return
	 */
	Integer queryFrontProductIdsCount(HashMap<String, Object> productParam);
	
	/**
	 * 查询商品筛选项
	 * 
	 * @param productFilterParam
	 * @return
	 */
	ArrayList<ProductFilter> queryProductFilter(HashMap<String, Object> productFilterParam);
	
	/**
	 * 查询商品低级筛选项
	 * 
	 * @param productParam
	 * @return
	 */
	ArrayList<ProductFilter> queryProductFilterLow(HashMap<String, Object> productParam);
	
	/**
	 * 获取商品属性
	 * 
	 * @param productMasterId
	 * @return
	 */
	ArrayList<HashMap<String, Object>> queryProductProp(Integer productMasterId);

	/**
	 * 新增商品分类映射关系记录
	 * 
	 * @param params
	 */
	void insertProductCategory(HashMap<String, Object> params);

	/**
	 * 获取商品详情
	 * 
	 * @param productId
	 * @return
	 */
	StringBuilder queryProductDetail(Integer productId);
	
	/**
	 * 查看商品是否是组合商品
	 * 
	 * @param productId
	 * @return
	 */
	@Select("select group_flag from product where product_id = #{productId} and del_flag = 0")
	Integer queryProductIsGroup(@Param("productId") Integer productId);
	
	/**
	 * 新增组合商品子商品
	 * 
	 * @param productId
	 * @param subProductId
	 * @param subNum
	 * @param addTime
	 */
	@Insert("INSERT INTO product_group(product_id,sub_product_id,sub_num,add_time)VALUES (#{productId},#{subProductId},#{subNum},#{addTime})")
	void addProductSub(@Param("productId") Integer productId, @Param("subProductId") Integer subProductId, 
							@Param("subNum") Integer subNum, @Param("addTime") Date addTime);
	
	/**
	 * 删除组合商品子商品
	 * 
	 * @param productSubDbId
	 * @return
	 */
	@Update("UPDATE product_group SET del_flag = 1 WHERE del_flag = 0 and id = #{productSubDbId}")
	Integer delProductSub(@Param("productSubDbId") Integer productSubDbId);
	
	/**
	 * 下线商品主表
	 * 
	 * @param productId
	 */
	@Update("UPDATE ${productTbl} SET product_id = 10000000 + product_id WHERE id = #{productId}")
	void offlineProduct(@Param("productTbl") String productTbl, @Param("productId") Integer productId);
	
	/**
	 * 更新商品
	 * 
	 * @param productParam
	 */
	void updateProduct(HashMap<String, Object> productParam);
	
	/**
	 * 更新通用详情
	 * 
	 * @param productId
	 */
	@Update("UPDATE detail SET detail = #{detail} WHERE id = #{detailId}")
	void updateComDetail(@Param("detail") StringBuilder detail, @Param("detailId") Integer detailId);
	
//	@Insert("INSERT INTO upload_imgs(img_url,img_type,img_width,img_height,add_time)VALUES (#{imgUrl},#{imgType},'100','100',NOW())")
	@Select("select img_id from upload_imgs2 where img_type = #{imgType}")
//	@Update("update upload_imgs4 set img_url = #{imgUrl} where img_type = #{imgType}")
	List<Integer> insertTest(@Param("imgUrl") String a, @Param("imgType") String b);

	/**
	 * 查询商品对应分类映射关系记录
	 * 
	 * @param productId
	 * @return
	 */
	List<Integer> selectProductCategory(@Param(value="productId") Integer productId);

	/**
	 * 删除商品对应分类映射关系记录
	 * 
	 * @param params
	 */
	void deleteProductCategory(HashMap<String, Object> params);

	/**
	 * 主从复制商品
	 * @param productCopy
	 */
	void masterProductCopy(HashMap<String, Object> productCopy);

	/**
	 * 跨品类商品复制
	 * @param productCopy
	 */
	void changeTypeProductCopy(HashMap<String, Object> productCopy);
	
	
	
	//临时
	/**
	 * 查看商品信息
	 * 
	 * @param productId
	 * @return
	 */
	@Select("SELECT id as productId, product_type as productType ,product_name as productName , product_name_en as productNameEn , barcode , "
		+ "unit,head_img as headImg , supplier_id as supplierId , country_id as countryId , brand_id as brandId,weight,check_flag as checkFlag,"
		+ "master_flag as masterFlag , detail_id as detailId , product_master_id as productMasterId , sell_num as sellNum , sell_limit as sellLimit,"
		+ "top_rank as topRank,uni_purchase_price as uniPurchasePrice , uni_refer_price as uniReferPrice , group_flag as groupFlag , del_flag as delFlag from product WHERE id = #{id}")
	HashMap<String,Object> queryProduct(@Param(value="id") Integer productId);

	/**
	 * @param productCopy
	 */
	void productCopy(HashMap<String, Object> productCopy);

	
	

	// @Select("SELECT a, b FROM test WHERE id = #{id}")
	// Map<String, Object> getTest(@Param("id") long id);
	//
	// @Insert("INSERT INTO test(a, b) VALUES (#{a},#{b})")
	// void insertTest(@Param("id") long a, @Param("id") long b);
	//
	// @Update("update test set a = #{id}")
	// void updateTest(@Param("id") long id);
	//
	// @Delete("DELETE FROM test WHERE id = #{id}")
	// void deleteTest(@Param("id") long id);
	//
	//
	//
	//
	// /**
	// * get test bean by UID.
	// *
	// * @param id
	// * @return
	// */
	// @SelectProvider(type = SqlProvider.class, method = "getTestSql")
	// @Options(useCache = true, flushCache = false, timeout = 10000)
	// @Results(value = {
	// // @Result(id = true, property = "id", column = "test_id", javaType =
	// String.class, jdbcType = JdbcType.VARCHAR),
	// @Result(property = "a", column = "a", javaType = String.class, jdbcType =
	// JdbcType.VARCHAR),
	// @Result(property = "b", column = "b", javaType = String.class, jdbcType =
	// JdbcType.VARCHAR)})
	// public List<HashMap<String, Object>> getTestSql(@Param("id") Integer id);
	//
	// /**
	// * 批量插入数据
	// *
	// * @param tablename
	// * @param insertcolumn
	// * @param defaultcolumn
	// * @param defaultdata
	// * @param addAttribute
	// */
	// @SelectProvider(type = SqlInsertProvider.class, method = "getInsertSql")
	// @Options(useCache = true, flushCache = false, timeout = 10000)
	// void insertMultiTest(@Param("tablename") String tablename,
	// @Param("insertcolumn") HashSet<String> insertcolumn,
	// @Param("insertdata") ArrayList<HashMap<String, Object>> b,
	// @Param("defaultcolumn") HashSet<String> defaultcolumn,
	// @Param("defaultdata") HashMap<String, Object> defaultdata,
	// @Param("addAttribute") HashMap<String, Object> addAttribute);

	
	
//    @Select("SELECT a, b FROM test WHERE id = #{id}")
//    Map<String, Object> getTest(@Param("id") long id);
//    
//    @Insert("INSERT INTO test(a, b) VALUES (#{a},#{b})")
//    void insertTest(@Param("id") long a, @Param("id") long b);
//    
//    @Update("update test set a = #{id}")
//    void updateTest(@Param("id") long id);
//    
//    @Delete("DELETE FROM test WHERE id = #{id}")
//    void deleteTest(@Param("id") long id);
//    
//    
//    
//    
//    /** 
//     * get test bean by UID. 
//     *  
//     * @param id 
//     * @return 
//     */  
//    @SelectProvider(type = SqlProvider.class, method = "getTestSql")  
//    @Options(useCache = true, flushCache = false, timeout = 10000)  
//    @Results(value = {  
//            // @Result(id = true, property = "id", column = "test_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),  
//            @Result(property = "a", column = "a", javaType = String.class, jdbcType = JdbcType.VARCHAR),
//            @Result(property = "b", column = "b", javaType = String.class, jdbcType = JdbcType.VARCHAR)})  
//    public List<HashMap<String, Object>> getTestSql(@Param("id") Integer id);
//    
//    /**
//     * 批量插入数据
//     * 
//     * @param tablename
//     * @param insertcolumn
//     * @param defaultcolumn
//     * @param defaultdata
//     * @param addAttribute
//     */
//    @SelectProvider(type = SqlInsertProvider.class, method = "getInsertSql")  
//    @Options(useCache = true, flushCache = false, timeout = 10000)
//    void insertMultiTest(@Param("tablename") String tablename, @Param("insertcolumn") HashSet<String> insertcolumn,
//    		@Param("insertdata") ArrayList<HashMap<String, Object>> b, @Param("defaultcolumn") HashSet<String> defaultcolumn,
//    		@Param("defaultdata") HashMap<String, Object> defaultdata, @Param("addAttribute") HashMap<String, Object> addAttribute);
	
	

	
	
	
//    @Select("SELECT a, b FROM test WHERE id = #{id}")
//    Map<String, Object> getTest(@Param("id") long id);
//    
//    @Insert("INSERT INTO test(a, b) VALUES (#{a},#{b})")
//    void insertTest(@Param("id") long a, @Param("id") long b);
//    
//    @Update("update test set a = #{id}")
//    void updateTest(@Param("id") long id);
//    
//    @Delete("DELETE FROM test WHERE id = #{id}")
//    void deleteTest(@Param("id") long id);
//    
//    
//    
//    
//    /** 
//     * get test bean by UID. 
//     *  
//     * @param id 
//     * @return 
//     */  
//    @SelectProvider(type = SqlProvider.class, method = "getTestSql")  
//    @Options(useCache = true, flushCache = false, timeout = 10000)  
//    @Results(value = {  
//            // @Result(id = true, property = "id", column = "test_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),  
//            @Result(property = "a", column = "a", javaType = String.class, jdbcType = JdbcType.VARCHAR),
//            @Result(property = "b", column = "b", javaType = String.class, jdbcType = JdbcType.VARCHAR)})  
//    public List<HashMap<String, Object>> getTestSql(@Param("id") Integer id);
//    
//    /**
//     * 批量插入数据
//     * 
//     * @param tablename
//     * @param insertcolumn
//     * @param defaultcolumn
//     * @param defaultdata
//     * @param addAttribute
//     */
//    @SelectProvider(type = SqlInsertProvider.class, method = "getInsertSql")  
//    @Options(useCache = true, flushCache = false, timeout = 10000)
//    void insertMultiTest(@Param("tablename") String tablename, @Param("insertcolumn") HashSet<String> insertcolumn,
//    		@Param("insertdata") ArrayList<HashMap<String, Object>> b, @Param("defaultcolumn") HashSet<String> defaultcolumn,
//    		@Param("defaultdata") HashMap<String, Object> defaultdata, @Param("addAttribute") HashMap<String, Object> addAttribute);
	
	
}
