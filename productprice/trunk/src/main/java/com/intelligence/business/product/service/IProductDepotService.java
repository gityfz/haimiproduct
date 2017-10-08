package com.intelligence.business.product.service;

import java.util.List;

import com.intelligence.common.exception.PowerException;

/**
 * @author Zhengyf
 *
 * @date 2017年3月9日
 */
public interface IProductDepotService {
    /**
     * 更具国家id查询仓库
     * @return
     * @throws PowerException
     */
    public List<Object> queryDepotByCountry(int countryId) throws PowerException;
}
