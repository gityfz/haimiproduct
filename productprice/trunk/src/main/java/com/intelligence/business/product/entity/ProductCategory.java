package com.intelligence.business.product.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2017年1月13日 下午4:00:57
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class ProductCategory implements Serializable{
        /**
         * 序列化id 
         */
    	private static final long serialVersionUID = -1535695611918329730L;

	private Integer id;

	private Integer productId;

	private Integer categoryId;

	private Date addTime;
	
	private Date updateTime;

	/**
	 * @return the productCategoryId
	 */
	public Integer getId() {
	    return id;
	}

	/**
	 * @param productCategoryId the productCategoryId to set
	 */
	public void setId(Integer id) {
	    this.id = id;
	}

	/**
	 * @return the productId
	 */
	public Integer getProductId() {
	    return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Integer productId) {
	    this.productId = productId;
	}

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
	    return "ProductCategory [id=" + id
		    + ", productId=" + productId + ", categoryId=" + categoryId
		    + ", addTime=" + addTime + ", updateTime=" + updateTime
		    + "]";
	}

	

	

}
