package com.intelligence.business.product.dao;

import java.util.HashMap;
import java.util.List;

import com.intelligence.common.exception.PowerException;

/**
 * @author Zhengyf
 *
 * @date 2017年3月9日
 */
public interface IProductDepotDao {

    /**
     * @param countryId
     * @return
     */
    List<HashMap<String, Object>> queryDepotByCountry(int countryId) throws PowerException;

}
