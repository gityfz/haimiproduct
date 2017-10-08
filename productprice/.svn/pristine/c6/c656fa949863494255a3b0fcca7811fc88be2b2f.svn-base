package com.intelligence.business.price.persistance;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;

public interface PriceUnitMapper {
	
	@Select("SELECT weight_unit,price_unit FROM country WHERE country_id = #{id}")
    Map<String, Object> getCountryMap(@Param("id") int id);
    
    @Select("select max(id) from country")
    Integer getMaxId();
    
//    @Insert("INSERT INTO upload(id,name,path,weight,height,type) VALUES (#{id},#{name},#{path},#{weight},#{height},#{type})")
//    void insertUpload(@Param("id") int id,@Param("name") String name, @Param("path") String path, @Param("weight") String w, @Param("height") String h, @Param("type") String t);
    
//    @Update("update upload set a = #{a} WHERE id = #{id}")
//    void updateUpload(@Param("id") int id,@Param("a") long a);
    
    @Delete("DELETE FROM country WHERE country_id = #{id}")
    void deleteTest(@Param("id") long id);
    
    
    @Select("select	COUNT(0) from country")
    Integer queryTest();
}
