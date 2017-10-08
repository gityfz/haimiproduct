package com.intelligence.business.product.persistance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.intelligence.autodev.persistance.sql.SqlInsertProvider;
import com.intelligence.autodev.persistance.sql.SqlProvider;

public interface ProductMapMapper {

    @Select("SELECT a, b FROM test WHERE id = #{id}")
    Map<String, Object> getTest(@Param("id") long id);
    
    @Insert("INSERT INTO test(a, b) VALUES (#{a},#{b})")
    void insertTest(@Param("id") long a, @Param("id") long b);
    
    @Update("update test set a = #{id}")
    void updateTest(@Param("id") long id);
    
    @Delete("DELETE FROM test WHERE id = #{id}")
    void deleteTest(@Param("id") long id);
    
    Integer queryTest();
    
    /** 
     * get test bean by UID. 
     *  
     * @param id 
     * @return 
     */  
    @SelectProvider(type = SqlProvider.class, method = "getTestSql")  
    @Options(useCache = true, flushCache = false, timeout = 10000)  
    @Results(value = {  
            // @Result(id = true, property = "id", column = "test_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),  
            @Result(property = "a", column = "a", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "b", column = "b", javaType = String.class, jdbcType = JdbcType.VARCHAR)})  
    public List<HashMap<String, Object>> getTestSql(@Param("id") Integer id);
    
    /**
     * 批量插入数据
     * 
     * @param tablename
     * @param insertcolumn
     * @param defaultcolumn
     * @param defaultdata
     * @param addAttribute
     */
    @SelectProvider(type = SqlInsertProvider.class, method = "getInsertSql")  
    @Options(useCache = true, flushCache = false, timeout = 10000)
    void insertMultiTest(@Param("tablename") String tablename, @Param("insertcolumn") HashSet<String> insertcolumn,
    		@Param("insertdata") ArrayList<HashMap<String, Object>> b, @Param("defaultcolumn") HashSet<String> defaultcolumn,
    		@Param("defaultdata") HashMap<String, Object> defaultdata, @Param("addAttribute") HashMap<String, Object> addAttribute);
	
	
}
