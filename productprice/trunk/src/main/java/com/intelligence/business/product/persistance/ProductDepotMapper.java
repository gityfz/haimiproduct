package com.intelligence.business.product.persistance;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Zhengyf
 *
 * @date 2017年3月9日
 */
public interface ProductDepotMapper {

    /**
     * @param countryId
     * @return
     */
    @Select("select depot_id as depotId from product_depot where country_id = #{countryId} and del_flag=0")
    List<HashMap<String,Object>> queryDepotByCountry(@Param(value="countryId")int countryId);

}
