package com.intelligence.business.product.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Zhengyf
 *
 * @date 2017年1月19日
 */
public class Country implements Serializable{
    
     /**
     * 序列化id
     */
    private static final long serialVersionUID = -9173922420081175479L;

	private Integer id = null;
    	
        private Integer countryId = null;
        
        private String weightUnit = null;
	
        private String priceUnit = null;
        
        private String logo = null;
        
        private Integer delFlag = null;

        private Date addTime = null;
        
        private Date updateTime = null;

	/**
	 * @return the id
	 */
	public Integer getId() {
	    return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
	    this.id = id;
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
	 * @return the weightUnit
	 */
	public String getWeightUnit() {
	    return weightUnit;
	}

	/**
	 * @param weightUnit the weightUnit to set
	 */
	public void setWeightUnit(String weightUnit) {
	    this.weightUnit = weightUnit;
	}

	/**
	 * @return the priceUnit
	 */
	public String getPriceUnit() {
	    return priceUnit;
	}

	/**
	 * @param priceUnit the priceUnit to set
	 */
	public void setPriceUnit(String priceUnit) {
	    this.priceUnit = priceUnit;
	}

	/**
	 * @return the logo
	 */
	public String getLogo() {
	    return logo;
	}

	/**
	 * @param logo the logo to set
	 */
	public void setLogo(String logo) {
	    this.logo = logo;
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
	    return "Country [id=" + id + ", countryId=" + countryId
		    + ", weightUnit=" + weightUnit + ", priceUnit=" + priceUnit
		    + ", logo=" + logo + ", delFlag=" + delFlag + ", addTime="
		    + addTime + ", updateTime=" + updateTime + "]";
	}
}
