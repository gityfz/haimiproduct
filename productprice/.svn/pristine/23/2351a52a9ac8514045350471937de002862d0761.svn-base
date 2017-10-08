package com.intelligence.common.utils;


import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	
	/**
	 * jackson解析实例
	 */
	private static ObjectMapper mapper = null;
    
	/**
	 * 私有化构造函数
	 */
    private JsonUtils(){}  
  
    /**
     * 单例初始化jackson解析实例
     */
    static{  
    	mapper = new ObjectMapper(); 
    }
    
    /**
     * 获得jackson解析实例
     * 
     * @return
     */
    public static ObjectMapper getMapperInstance() {
        return mapper;  
    }
    
    /**
     * 将JSON转换成MAP
     * 
     * @param jsonStr
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
	public static HashMap<String, Object> getMapFromJson(String jsonStr) throws JsonParseException, JsonMappingException, IOException {
        return getMapperInstance().readValue(jsonStr, HashMap.class);  
    }
    
    /**
     * 将对象转换成JSON字符串
     * 
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public static String getJsonFromObject(Object obj) throws JsonProcessingException {
    	return getMapperInstance().writeValueAsString(obj);
    }
    
}
