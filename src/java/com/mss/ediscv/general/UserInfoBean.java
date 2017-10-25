
/*******************************************************************************
 *
 * Project : Miracle Supply Chain Visibility portal v2.0
 *
 * Package : com.mss.mscvp.general
 *
 * Date    : Mar 11, 2013 1:28:58 PM
 *
 * Author  : Nagireddy seerapu <nseerapu@miraclesoft.com>
 *
 * File    : UserInfoBean.java
 *
 * 
 * *****************************************************************************
 */
package com.mss.ediscv.general;

import java.sql.Timestamp;

public class UserInfoBean {
	private int userId;
	private String loginId;
	private String password;
	private String firstName;
	private String lastName;
	private String mailId;
	private int deptId;
	private String activeFlag;
	private Timestamp lastLoginTS;
	private Timestamp lastLogoutTS;
        
	
	/**
	 * @return the loginId
	 */
	public String getLoginId() {
		return loginId;
	}
	/**
	 * @param loginId the loginId to set
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the mailId
	 */
	public String getMailId() {
		return mailId;
	}
	/**
	 * @param mailId the mailId to set
	 */
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	
	/**
	 * @return the lastLogoutTS
	 */
	public Timestamp getLastLogoutTS() {
		return lastLogoutTS;
	}
	/**
	 * @param lastLogoutTS the lastLogoutTS to set
	 */
	public void setLastLogoutTS(Timestamp lastLogoutTS) {
		this.lastLogoutTS = lastLogoutTS;
	}
	
	/**
	 * @param lastLoginTS the lastLoginTS to set
	 */
	public void setLastLoginTS(Timestamp lastLoginTS) {
		this.lastLoginTS = lastLoginTS;
	}
	/**
	 * @return the lastLoginTS
	 */
	public Timestamp getLastLoginTS() {
		return lastLoginTS;
	}
	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	/**
	 * @return the deptId
	 */
	public int getDeptId() {
		return deptId;
	}
	/**
	 * @param activeFlag the activeFlag to set
	 */
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	/**
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return activeFlag;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	
}
