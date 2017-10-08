package com.intelligence.business.price.persistance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.intelligence.business.base.persistance.sql.SqlBatchProvider;
import com.intelligence.business.price.entity.CurrencyRate;

/**
 * 定时刷新汇率
 * @author 作者 E-mail: bzp
 * @date 创建时间：2017年1月10日 下午4:05:01
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public interface PriceRateMapper {

	@Select("select country_id from currency_rate")
	public List<Object> SelectRate();

	@Update("update currency_rate set currency_rate = #{currency_rate} WHERE currency_flag = #{fromCurrency}")
	void updateRate(@Param("currency_rate") BigDecimal currency_rate,
			@Param("fromCurrency") String fromCurrency);
	
	/**
	 * 获取货币汇率
	 * 
	 * @return
	 */
	public ArrayList<CurrencyRate> queryCurrencyRate();

	/**
     * 批量更新汇率
     * 
     * @param tablename
     * @param insertcolumn
     * @param defaultcolumn
     * @param defaultdata
     * @param addAttribute
     */
    @SelectProvider(type = SqlBatchProvider.class, method = "getInsertUpdMySql")  
    @Options(useCache = true, flushCache = false, timeout = 10000)
    void updateCurrencyRateBatch(@Param("tablename") String tablename, @Param("insertcolumn") HashSet<String> insertcolumn,
    								@Param("insertdata") ArrayList<HashMap<String, Object>> insertData, @Param("defaultcolumn") HashSet<String> defaultcolumn,
    								@Param("defaultdata") HashMap<String, Object> defaultdata, @Param("addAttribute") HashMap<String, Object> addAttribute);
	
}
