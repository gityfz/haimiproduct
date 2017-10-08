package com.intelligence.business.product.entity;

import java.io.Serializable;
import java.util.Date;

public class ProductBrand implements Serializable {
	
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 6038892921841332434L;

	private Integer brandId = null;
	
	private String brandName = null;
	
	private String brandNameEn = null;
	
	private String brandFirstLetter = null;
	
	private String brandIndex = null;
	
	private String brandLogo = null;
	
	private Integer countryId = null;
	
	private Integer detailId = null;
	
	private String detail = null;
	
	private String brandSite = null;
	
	private Integer delFlag = null;
	
	private Date addTime = null;

	private Date updateTime = null;

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
	 * @return the brandName
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * @return the detail
	 */
	public String getDetail() {
	    return detail;
	}

	/**
	 * @param detail the detail to set
	 */
	public void setDetail(String detail) {
	    this.detail = detail;
	}

	/**
	 * @param brandName the brandName to set
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	/**
	 * @return the brandNameEn
	 */
	public String getBrandNameEn() {
		return brandNameEn;
	}

	/**
	 * @param brandNameEn the brandNameEn to set
	 */
	public void setBrandNameEn(String brandNameEn) {
		this.brandNameEn = brandNameEn;
	}

	/**
	 * @return the brandFirstLetter
	 */
	public String getBrandFirstLetter() {
		return brandFirstLetter;
	}

	/**
	 * @param brandFirstLetter the brandFirstLetter to set
	 */
	public void setBrandFirstLetter(String brandFirstLetter) {
		this.brandFirstLetter = brandFirstLetter;
	}

	/**
	 * @return the brandIndex
	 */
	public String getBrandIndex() {
		return brandIndex;
	}

	/**
	 * @param brandIndex the brandIndex to set
	 */
	public void setBrandIndex(String brandIndex) {
		this.brandIndex = brandIndex;
	}

	/**
	 * @return the brandLogo
	 */
	public String getBrandLogo() {
		return brandLogo;
	}

	/**
	 * @param brandLogo the brandLogo to set
	 */
	public void setBrandLogo(String brandLogo) {
		this.brandLogo = brandLogo;
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
	 * @return the detailId
	 */
	public Integer getDetailId() {
		return detailId;
	}

	/**
	 * @param detailId the detailId to set
	 */
	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	/**
	 * @return the brandSite
	 */
	public String getBrandSite() {
		return brandSite;
	}

	/**
	 * @param brandSite the brandSite to set
	 */
	public void setBrandSite(String brandSite) {
		this.brandSite = brandSite;
	}

	/**
	 * @return the delFlag
	 */
	public Integer getDelFlag() {
		return delFlag;
	}

	/**
	 * @param delFlag the delFlag to set
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	    return "ProductBrand [brandId=" + brandId + ", brandName="
		    + brandName + ", brandNameEn=" + brandNameEn
		    + ", brandFirstLetter=" + brandFirstLetter
		    + ", brandIndex=" + brandIndex + ", brandLogo=" + brandLogo
		    + ", countryId=" + countryId + ", detailId=" + detailId
		    + ", detail=" + detail + ", brandSite=" + brandSite
		    + ", delFlag=" + delFlag + ", addTime=" + addTime
		    + ", updateTime=" + updateTime + "]";
	}


	

}
