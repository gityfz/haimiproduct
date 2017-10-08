/**
 * 
 */
package com.intelligence.autodev.persistance.sql;

import java.util.Map;

/**
 * @author p-sunhao
 *
 */
public class SqlProvider {
	

	/** 
     * get test by id sql script. 
     *  
     * @param parameters 
     * @return 
     */  
    public String getTestSql(Map<String, Object> parameters) {  
        String id = (String) parameters.get("id");  
//        BEGIN();  
//        SELECT("test_id, test_text");  
//        FROM(TABLE_NAME);  
//        if (uid != null) {  
//            WHERE("test_id = #{id,javaType=string,jdbcType=VARCHAR}");  
//        }  
//        return SQL();  
		return "select a, b from test where id = "+id;
    }  
    
    /**
     * 获取原生SQL
     * 
     * @param parameters
     * @return
     */
    public String getOriginalSql(Map<String, Object> parameters) {
    	return (String) parameters.get("sql");
    }
    
    /**
     * 获取单表查询SQL
     * 
     * @param parameters
     * @return
     */
    public String getSingleQuerySql(Map<String, Object> parameters) {
    	
    	
    	
    	
    	return (String) parameters.get("sql");
    }
    
    
}
