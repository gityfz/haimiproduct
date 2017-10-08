package com.intelligence.business.price.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品用户价格实体
 * 
 * @author 004
 *
 */
public class ProductUserPrice implements Serializable {

	/**
	 * 序列化号
	 */
	private static final long serialVersionUID = 5648510779501321655L;

	/**
	 * 商品用户价格ID
	 */
	private Integer productUserPriceId;
	
	/**
	 * 内容ID
	 */
	private Integer contentId;
	
	/**
	 * 内容类型
	 */
	private Integer contentType;
	
	/**
	 * 用户ID
	 */
	private Integer userId;
	
	/**
	 * 国家ID
	 */
	private Integer countryId;
	
	/**
	 * 商品ID
	 */
	private Integer productId;
	
	/**
	 * 仓库ID
	 */
	private Integer depotId;
	
	/**
	 * 临时价格
	 */
	private BigDecimal priceTmp;
	
	/**
	 * 价格差
	 */
	private BigDecimal priceBal;
	
	/**
	 * 参考价格差
	 */
	private BigDecimal priceReferBal;
	
	/**
	 * 价格差率
	 */
	private BigDecimal priceBalRate;
	
	/**
	 * 参考价格差率
	 */
	private BigDecimal priceReferBalRate;
	
	/**
	 * 新增时间
	 */
	private Date addTime;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * @return the productUserPriceId
	 */
	public Integer getProductUserPriceId() {
		return productUserPriceId;
	}

	/**
	 * @param productUserPriceId the productUserPriceId to set
	 */
	public void setProductUserPriceId(Integer productUserPriceId) {
		this.productUserPriceId = productUserPriceId;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the priceTmp
	 */
	public BigDecimal getPriceTmp() {
		return priceTmp;
	}

	/**
	 * @param priceTmp the priceTmp to set
	 */
	public void setPriceTmp(BigDecimal priceTmp) {
		this.priceTmp = priceTmp;
	}

	/**
	 * @return the priceBal
	 */
	public BigDecimal getPriceBal() {
		return priceBal;
	}

	/**
	 * @param priceBal the priceBal to set
	 */
	public void setPriceBal(BigDecimal priceBal) {
		this.priceBal = priceBal;
	}

	/**
	 * @return the priceReferBal
	 */
	public BigDecimal getPriceReferBal() {
		return priceReferBal;
	}

	/**
	 * @param priceReferBal the priceReferBal to set
	 */
	public void setPriceReferBal(BigDecimal priceReferBal) {
		this.priceReferBal = priceReferBal;
	}

	/**
	 * @return the priceBalRate
	 */
	public BigDecimal getPriceBalRate() {
		return priceBalRate;
	}

	/**
	 * @param priceBalRate the priceBalRate to set
	 */
	public void setPriceBalRate(BigDecimal priceBalRate) {
		this.priceBalRate = priceBalRate;
	}

	/**
	 * @return the priceReferBalRate
	 */
	public BigDecimal getPriceReferBalRate() {
		return priceReferBalRate;
	}

	/**
	 * @param priceReferBalRate the priceReferBalRate to set
	 */
	public void setPriceReferBalRate(BigDecimal priceReferBalRate) {
		this.priceReferBalRate = priceReferBalRate;
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
	 * @return the contentId
	 */
	public Integer getContentId() {
		return contentId;
	}

	/**
	 * @param contentId the contentId to set
	 */
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	/**
	 * @return the contentType
	 */
	public Integer getContentType() {
		return contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(Integer contentType) {
		this.contentType = contentType;
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
	 * @return the depotId
	 */
	public Integer getDepotId() {
	    return depotId;
	}

	/**
	 * @param depotId the depotId to set
	 */
	public void setDepotId(Integer depotId) {
	    this.depotId = depotId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	    return "ProductUserPrice [productUserPriceId=" + productUserPriceId
		    + ", contentId=" + contentId + ", contentType="
		    + contentType + ", userId=" + userId + ", countryId="
		    + countryId + ", productId=" + productId + ", depotId="
		    + depotId + ", priceTmp=" + priceTmp + ", priceBal="
		    + priceBal + ", priceReferBal=" + priceReferBal
		    + ", priceBalRate=" + priceBalRate + ", priceReferBalRate="
		    + priceReferBalRate + ", addTime=" + addTime
		    + ", updateTime=" + updateTime + "]";
	}

	
}
