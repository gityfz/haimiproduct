package com.intelligence.business.product.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品过滤实体
 * 
 * @author 004
 *
 */
public class ProductFilter implements Serializable {

	/**
	 * 序列号ID
	 */
	private static final long serialVersionUID = 796093106231548040L;

	/**
	 * 分类ID
	 */
	private Integer categoryId = null;
	
	/**
	 * 国家ID
	 */
	private Integer countryId = null;
	
	/**
	 * 属性ID
	 */ 
	private Integer propId = null;
	
	/**
	 * 品牌ID
	 */ 
	private Integer brandId = null;
	
	/**
	 * 过滤等级
	 */
	private Integer filterLevel = null;
	
	/**
	 * 新增时间
	 */
	private Date addTime = null;
	
	/**
	 * 更新时间
	 */
	private Date updateTime = null;

	/**
	 * @return the categoryId
	 */
	public Integer getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the countryId
	 */
	public Integer getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return the filterLevel
	 */
	public Integer getFilterLevel() {
		return filterLevel;
	}

	/**
	 * @param filterLevel the filterLevel to set
	 */
	public void setFilterLevel(Integer filterLevel) {
		this.filterLevel = filterLevel;
	}

	/**
	 * @return the addTime
	 */
	public Date getAddTime() {
		return addTime;
	}

	/**
	 * @param addTime the addTime to set
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the brandId
	 */
	public Integer getBrandId() {
		return brandId;
	}

	/**
	 * @param brandId the brandId to set
	 */
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	/**
	 * @return the propId
	 */
	public Integer getPropId() {
		return propId;
	}

	/**
	 * @param propId the propId to set
	 */
	public void setPropId(Integer propId) {
		this.propId = propId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductFilter [categoryId=" + categoryId + ", countryId="
				+ countryId + ", propId=" + propId + ", brandId=" + brandId
				+ ", filterLevel=" + filterLevel + ", addTime=" + addTime
				+ ", updateTime=" + updateTime + "]";
	}
	
}
