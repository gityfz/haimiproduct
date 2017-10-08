package com.intelligence.business.product.persistance;

import java.util.HashMap;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.intelligence.business.product.entity.ProductBrand;

public interface ProductBrandMapper {

	/**
	 * 新增品牌
	 * 
	 * @param productBrand
	 */
	void insertProductBrand(HashMap<String, Object> productBrand);
	
	/**
	 * 新增明细
	 * 
	 * @param detail
	 */
	void insertDetail(HashMap<String, Object> detail);
	
	/**
	 * 根据id获取品牌信息
	 * 
	 * @param id
	 */
	ProductBrand selectBrandById(@Param(value="id")int id);

	/**
	 * 更新品牌信息
	 * @param brandParam
	 */
	void updateProductBrand(HashMap<String, Object> brandParam);

	/**
	 * 查询品牌信息
	 * @param brandId
	 * @return
	 */
	@Select("SELECT b.id as brandId,b.brand_name as brandName, b.brand_name_en as brandNameEn ,b.brand_first_letter as brandFirstLetter,"
		+ "b.brand_index as brandIndex,b.brand_logo as brandLogo, b.country_id as countryId, b.detail_id as detailId,"
		+ "b.brand_site as brandSite ,d.detail  FROM brand  b  left join detail d on b.detail_id = d.id WHERE b.id = #{brandId} "
		+ "and b.del_flag = 0 and d.del_flag = 0")
	ProductBrand selectProductBrand(@Param(value="brandId")Integer brandId);
}
