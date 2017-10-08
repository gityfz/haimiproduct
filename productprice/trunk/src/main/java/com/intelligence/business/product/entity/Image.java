package com.intelligence.business.product.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品实体
 * 
 * @author 004
 *
 */
public class Image implements Serializable {
	
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = -1606041041040389951L;
	
	/**
	 * 图片ID
	 */
	private Integer imgId = null;
	
	/**
	 * 图片地址
	 */
	private String imgUrl = null;
	
	/**
	 * 图片类型
	 */
	private String imgType = null;
	
	/**
	 * 图片宽度
	 */
	private Integer imgWidth = null;
	
	/**
	 * 图片高度
	 */
	private Integer imgHeight = null;
	
	/**
	 * 新增时间
	 */
	private Date addTime = null;
	
	/**
	 * 更新时间
	 */
	private Date updateTime = null;
	/**
	 * @return the imgId
	 */
	public Integer getImgId() {
		return imgId;
	}

	/**
	 * @param imgId the imgId to set
	 */
	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	/**
	 * @return the imgUrl
	 */
	public String getImgUrl() {
		return imgUrl;
	}

	/**
	 * @param imgUrl the imgUrl to set
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/**
	 * @return the imgType
	 */
	public String getImgType() {
		return imgType;
	}

	/**
	 * @param imgType the imgType to set
	 */
	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

	/**
	 * @return the imgWidth
	 */
	public Integer getImgWidth() {
		return imgWidth;
	}

	/**
	 * @param imgWidth the imgWidth to set
	 */
	public void setImgWidth(Integer imgWidth) {
		this.imgWidth = imgWidth;
	}

	/**
	 * @return the imgHeight
	 */
	public Integer getImgHeight() {
		return imgHeight;
	}

	/**
	 * @param imgHeight the imgHeight to set
	 */
	public void setImgHeight(Integer imgHeight) {
		this.imgHeight = imgHeight;
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
		return "Image [imgId=" + imgId + ", imgUrl=" + imgUrl + ", imgType="
				+ imgType + ", imgWidth=" + imgWidth + ", imgHeight="
				+ imgHeight + ", addTime=" + addTime + ", updateTime="
				+ updateTime + "]";
	}
	
}
