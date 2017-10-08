package com.intelligence.business.product.persistance;

import java.util.HashMap;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.intelligence.business.product.entity.Supplier;

/**
 * @author 作者 E-mail: bzp
 * @date 创建时间：2017年1月13日 下午2:09:07
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public interface SupplierMapper {

	/**
	 * 添加一条供应商信息
	 * @param supplierParam
	 */
	void insertSupplier(HashMap<String, Object> supplierParam);

	/**
	 * 添加一条供应商信息
	 * @param supplierParam
	 */
	void updateSupplier(HashMap<String, Object> supplierParam);

	/**
	 * 根据id查询供应商信息
	 * @param supplierId
	 * @return
	 */
	 @Select("select supplier_name as supplierName,supplier_short_name as supplierShortName, supplier_type as suplierType, contact_person as contactPerson, contact_phone as contactPhone , contact_email as contactEmail, "
	 	+ "contact_fax as contactFax, account_bank as accountBank ,account_name as accountName,login_time as loginTime,login_ip as loginIp,add_time as addTime,"
	 	+ "account_card_no as accountCardNo , supplier_pass as supplierPass ,supplier_address as supplierAddress  from supplier WHERE id = #{supplierId}")
	Supplier selectSupplier(@Param("supplierId")Integer supplierId);

}
