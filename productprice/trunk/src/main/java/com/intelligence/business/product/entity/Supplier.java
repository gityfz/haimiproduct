package com.intelligence.business.product.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 添加一条供应商信息
 * @author 作者 :bzp
 * @date 创建时间：2017年1月13日 上午10:30:40
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class Supplier implements Serializable {
    
        /**
         * 序列化ID
         */
        private static final long serialVersionUID = -5934028927135447704L;

	private Integer supplierId;

	private String supplierName;

	private String supplierShortName;

	private String supplierType;

	private String contactPerson;

	private String contactPhone;

	private String contactEmail;

	private String contactFax;

	private String accountBank;

	private String accountName;

	private String accountCardNo;

	private String settleType;

	private String supplierAccount;

	private String supplierPass;

	private String supplierAddress;

	private Date addTime;

	/**
	 * @return the supplierId
	 */
	public Integer getSupplierId() {
		return supplierId;
	}

	/**
	 * @param supplierId
	 *            the supplierId to set
	 */
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	/**
	 * @return the supplierName
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * @param supplierName
	 *            the supplierName to set
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * @return the supplierShortName
	 */
	public String getSupplierShortName() {
		return supplierShortName;
	}

	/**
	 * @param supplierShortName
	 *            the supplierShortName to set
	 */
	public void setSupplierShortName(String supplierShortName) {
		this.supplierShortName = supplierShortName;
	}

	/**
	 * @return the supplierType
	 */
	public String getSupplierType() {
		return supplierType;
	}

	/**
	 * @param supplierType
	 *            the supplierType to set
	 */
	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}

	/**
	 * @return the contactPerson
	 */
	public String getContactPerson() {
		return contactPerson;
	}

	/**
	 * @param contactPerson
	 *            the contactPerson to set
	 */
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	/**
	 * @return the contactPhone
	 */
	public String getContactPhone() {
		return contactPhone;
	}

	/**
	 * @param contactPhone
	 *            the contactPhone to set
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	/**
	 * @return the contactEmail
	 */
	public String getContactEmail() {
		return contactEmail;
	}

	/**
	 * @param contactEmail
	 *            the contactEmail to set
	 */
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	/**
	 * @return the contactFax
	 */
	public String getContactFax() {
		return contactFax;
	}

	/**
	 * @param contactFax
	 *            the contactFax to set
	 */
	public void setContactFax(String contactFax) {
		this.contactFax = contactFax;
	}

	/**
	 * @return the accountBank
	 */
	public String getAccountBank() {
		return accountBank;
	}

	/**
	 * @param accountBank
	 *            the accountBank to set
	 */
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName
	 *            the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * @return the accountCardNo
	 */
	public String getAccountCardNo() {
		return accountCardNo;
	}

	/**
	 * @param accountCardNo
	 *            the accountCardNo to set
	 */
	public void setAccountCardNo(String accountCardNo) {
		this.accountCardNo = accountCardNo;
	}

	/**
	 * @return the settleType
	 */
	public String getSettleType() {
		return settleType;
	}

	/**
	 * @param settleType
	 *            the settleType to set
	 */
	public void setSettleType(String settleType) {
		this.settleType = settleType;
	}

	/**
	 * @return the supplierAccount
	 */
	public String getSupplierAccount() {
		return supplierAccount;
	}

	/**
	 * @param supplierAccount
	 *            the supplierAccount to set
	 */
	public void setSupplierAccount(String supplierAccount) {
		this.supplierAccount = supplierAccount;
	}

	/**
	 * @return the supplierPass
	 */
	public String getSupplierPass() {
		return supplierPass;
	}

	/**
	 * @param supplierPass
	 *            the supplierPass to set
	 */
	public void setSupplierPass(String supplierPass) {
		this.supplierPass = supplierPass;
	}

	/**
	 * @return the supplierAddress
	 */
	public String getSupplierAddress() {
		return supplierAddress;
	}

	/**
	 * @param supplierAddress
	 *            the supplierAddress to set
	 */
	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	/**
	 * @return the addTime
	 */
	public Date getAddTime() {
		return addTime;
	}

	/**
	 * @param addTime
	 *            the addTime to set
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Supplier [supplierId=" + supplierId + "," + "supplierName="
				+ supplierName + "," + " supplierShortName="
				+ supplierShortName + ", " + "supplierType=" + supplierType
				+ "," + "contactPerson=" + contactPerson + ", "
				+ "contactPhone=" + contactPhone + "," + "contactEmail="
				+ contactEmail + ", " + "contactFax=" + contactFax + ", "
				+ "accountBank=" + accountBank + "," + "accountName="
				+ accountName + ", " + "accountCardNo=" + accountCardNo + ","
				+ " settleType=" + settleType + "," + "supplierAccount="
				+ supplierAccount + "," + "supplierPass=" + supplierPass + ","
				+ " supplierAddress=" + supplierAddress + "," + "addTime="
				+ addTime + "]";
	}

	

}
