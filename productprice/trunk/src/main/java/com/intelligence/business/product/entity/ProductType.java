package com.intelligence.business.product.entity;

import java.io.Serializable;
import java.util.Date;

public class ProductType implements Serializable{

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -3554334063293328655L;

	private Integer productTypeId = null;
	
	private Integer productType = null;
	
	private String productTypeName = null;
	
	private Integer productTypeLevel = null;
	
	private Integer delFlag = null;
	
	private Date addTime = null;
	
	private Date updateTime = null;

	/**
	 * @return the productTypeId
	 */
	public Integer getProductTypeId() {
		return productTypeId;
	}

	/**
	 * @param productTypeId the productTypeId to set
	 */
	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	/**
	 * @return the productType
	 */
	public Integer getProductType() {
		return productType;
	}

	/**
	 * @param productType the productType to set
	 */
	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	/**
	 * @return the productTypeName
	 */
	public String getProductTypeName() {
		return productTypeName;
	}

	/**
	 * @param productTypeName the productTypeName to set
	 */
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	/**
	 * @return the productTypeLevel
	 */
	public Integer getProductTypeLevel() {
		return productTypeLevel;
	}

	/**
	 * @param productTypeLevel the productTypeLevel to set
	 */
	public void setProductTypeLevel(Integer productTypeLevel) {
		this.productTypeLevel = productTypeLevel;
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
		return "ProductType [productTypeId=" + productTypeId + ", productType="
				+ productType + ", productTypeName=" + productTypeName
				+ ", productTypeLevel=" + productTypeLevel + ", delFlag="
				+ delFlag + ", addTime=" + addTime + ", updateTime="
				+ updateTime + "]";
	}
	
}
