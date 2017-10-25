
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
 * File    : GeneralServiceImpl.java
 *
 * 
 * *****************************************************************************
 */
package com.mss.ediscv.general;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.mss.ediscv.util.ConnectionProvider;
import com.mss.ediscv.util.ServiceLocatorException;

public class GeneralServiceImpl implements GeneralService {

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	private static Logger logger = Logger.getLogger(GeneralServiceImpl.class
			.getName());

	/**
	 * constructor of GeneralServiceIml
	 */
	public GeneralServiceImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bcbsm.edi.general.GeneralService#getUserInfo(java.lang.String)
	 */
	//@Override
	public UserInfoBean getUserInfo(String loginId, String dsnName)
			throws ServiceLocatorException {
		logger
				.info("Entered into the ::::GeneralServiceImpl :::: getUserInfo()");
			
		
		UserInfoBean userInfoBean = null;

		String sqlQuery = "SELECT ID,LOGINID,PASSWD,FNME,LNME,EMAIL,DEPT_ID,ACTIVE,LAST_LOGIN_TS,LAST_LOGOUT_TS FROM M_USER WHERE LOGINID=?";
		try {
			//System.out.println("Before !!!");
			connection = ConnectionProvider.getInstance().getConnection();
			//System.out.println("Connected !!!-->"+connection);
			preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, loginId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				userInfoBean = new UserInfoBean();
				userInfoBean.setUserId(resultSet.getInt("ID"));
				userInfoBean.setLoginId(resultSet.getString("LOGINID"));
				userInfoBean.setPassword(resultSet.getString("PASSWD"));
				userInfoBean.setFirstName(resultSet.getString("FNME"));
				userInfoBean.setLastName(resultSet.getString("LNME"));
				userInfoBean.setMailId(resultSet.getString("EMAIL"));
				userInfoBean.setDeptId(resultSet.getInt("DEPT_ID"));
				userInfoBean.setActiveFlag(resultSet.getString("ACTIVE"));
				userInfoBean.setLastLoginTS(resultSet
						.getTimestamp("LAST_LOGIN_TS"));
				userInfoBean.setLastLogoutTS(resultSet
						.getTimestamp("LAST_LOGOUT_TS"));
			}
		} catch (Exception ex) {
                    
                    
                    System.err.println("ex-->"+ex.getMessage());
			if (logger.isDebugEnabled()) {
				logger
						.error("Exception is :: GeneralServiceImpl:: getUserInfo()"
								+ ex.toString());
			}
			throw new ServiceLocatorException(ex);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
					resultSet = null;
				}
				if (preparedStatement != null) {
					preparedStatement.close();
					preparedStatement = null;
				}
				if (connection != null) {
					connection.close();
					connection = null;
				}
			} catch (SQLException sqle) {
				if (logger.isDebugEnabled()) {
					logger
							.error("SQLException is :: GeneralServiceImpl:: getUserInfo()"
									+ sqle.toString());
				}
				throw new ServiceLocatorException(sqle);
			}
		}// closing finally block
		logger.info("End of ::::GeneralServiceImpl :::: getUserInfo()");
                System.out.println("user info-->"+userInfoBean);
		return userInfoBean;
                
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bcbsm.edi.general.GeneralService#getUserRoles(int)
	 */
	//@Override
	public Map<Integer, Integer> getUserRoles(int userId, String dsnName)
			throws ServiceLocatorException {
		logger
				.info("Entered into the ::::GeneralServiceImpl :::: getUserRoles()");
		Map<Integer, Integer> rolesMap = new HashMap<Integer, Integer>();

		String sqlQuery = "SELECT PRIORITY,ROLE_ID FROM M_USER_ROLES WHERE USER_ID=? ORDER BY PRIORITY";
		try {
			connection = ConnectionProvider.getInstance()
					.getConnection();
			preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				rolesMap.put(resultSet.getInt("PRIORITY"), resultSet
						.getInt("ROLE_ID"));
			}
		} catch (Exception ex) {
			if (logger.isDebugEnabled()) {
				logger
						.error("Exception is :: GeneralServiceImpl:: getUserRoles()"
								+ ex.toString());
			}
			throw new ServiceLocatorException(ex);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
					resultSet = null;
				}
				if (preparedStatement != null) {
					preparedStatement.close();
					preparedStatement = null;
				}
				if (connection != null) {
					connection.close();
					connection = null;
				}
			} catch (SQLException sqle) {
				if (logger.isDebugEnabled()) {
					logger
							.error("SQLException is :: GeneralServiceImpl:: getUserRoles()"
									+ sqle.toString());
				}
				throw new ServiceLocatorException(sqle);
			}
		}
		logger.info("End of ::::GeneralServiceImpl :::: getUserRoles()");
		return rolesMap;
	}

}
