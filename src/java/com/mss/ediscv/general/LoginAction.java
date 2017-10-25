
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
 * File    : LoginAction.java
 *
 * 
 * *****************************************************************************
 */
package com.mss.ediscv.general;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.mss.ediscv.util.AppConstants;
import com.mss.ediscv.util.ConnectionProvider;
import com.mss.ediscv.util.DataSourceDataProvider;
import com.mss.ediscv.util.DateUtility;
import com.mss.ediscv.util.PasswordUtil;
import com.mss.ediscv.util.Properties;
import com.mss.ediscv.util.ServiceLocator;
import com.mss.ediscv.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Set;
import org.apache.log4j.Logger;

public class LoginAction extends ActionSupport implements ServletRequestAware {

	

	private static Logger logger = Logger
			.getLogger(LoginAction.class.getName());

	/* resultType used to store type of the result */
	private String resultType = SUCCESS;

	/* httpServletRequest used to store HttpServletRequest instance */
	private HttpServletRequest httpServletRequest;

	/* loginId used to store loginId of the employee */
	private String loginId;

	/* password used to store password of the employee */
	private String password;

	private String changeDb;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		logger.info("Entered into the ::::LoginAction :::: execute()");

		
		UserInfoBean userInfoBean = null;
	try {
		GeneralService generalService = ServiceLocator.getGeneralService();
				
		HttpSession userSession = httpServletRequest.getSession(true);
		
		//userSession.setAttribute(AppConstants.PROP_CURRENT_DS_NAME, Properties.getProperty(AppConstants.PROP_PROD_DS_NAME));
	
		String dsnName =  Properties.getProperty(AppConstants.PROP_PROD_DS_NAME);
	System.out.println("DSN Name-->"+dsnName);
		userInfoBean = generalService.getUserInfo(getLoginId().trim()
				.toLowerCase(), dsnName);

		if (userInfoBean != null) {
                    System.out.println("userInfoBean get password-->"+userInfoBean.getPassword().trim());
			String decryptedPwd = PasswordUtil.decryptPwd(userInfoBean
					.getPassword().trim());
                        System.out.println("decryptedPwd-->"+decryptedPwd);
			if (decryptedPwd.equals(getPassword())) {
				if (logger.isDebugEnabled()) {
					logger.info("User With loginId " + loginId
							+ "Logged into EDI ONLINE PORTAL");
				}

				if ("A".equals(userInfoBean.getActiveFlag())) {
					Map<Integer, Integer> userRolesMap = new HashMap<Integer, Integer>();
					userRolesMap = generalService.getUserRoles(userInfoBean.getUserId(), dsnName);
					String primaryRole = Properties.getProperty(AppConstants.PROP_USER_DEF_ROLE);

					if (userRolesMap.get(1) != null) {
						primaryRole = String.valueOf(userRolesMap.get(1));
					}
                                        String primaryFlowId = DataSourceDataProvider.getInstance().getPrimaryFlowID(userInfoBean.getUserId());
                                        if(primaryFlowId!=null) {
					userSession.setAttribute(AppConstants.SES_ROLE_ID,primaryRole);
                                            System.out.println("primaryRole--->"+primaryRole);
					userSession.setAttribute(AppConstants.SES_LOGIN_ID,userInfoBean.getLoginId());
					userSession.setAttribute(AppConstants.SES_USER_NAME,userInfoBean.getFirstName() + " " + userInfoBean.getLastName());
					userSession.setAttribute(AppConstants.SES_LAST_LOGIN_TS,userInfoBean.getLastLoginTS().toString());

					userSession.setAttribute(AppConstants.SES_FIRST_DB,"Production Data");

					userSession.setAttribute(AppConstants.PROP_CURRENT_DS_NAME,Properties.getProperty(AppConstants.PROP_PROD_DS_NAME));
					
                                        
                                        Map usrFlowMap = DataSourceDataProvider.getInstance().getFlows(userInfoBean.getUserId());
                                       System.out.println("UserFlowss----->"+usrFlowMap);
                                        userSession.setAttribute(AppConstants.SES_USER_FLOW_MAP,usrFlowMap);
                                       System.out.println("Roleid--->"+primaryRole);
                                        
                                        System.out.println("RoleName--->"+DataSourceDataProvider.getInstance().getRoleNameByRoleId(primaryRole));
                                        
                                        userSession.setAttribute(AppConstants.SES_USER_ROLE_NAME,DataSourceDataProvider.getInstance().getRoleNameByRoleId(primaryRole));
                                        userSession.setAttribute(AppConstants.SES_STATES_MAP, DataSourceDataProvider.getInstance().getStates());  
                                        
                                                String Resulttype = "input";
                                            
                                            setResultType(DataSourceDataProvider.getInstance().getFlowNameByFlowID(primaryFlowId));
                                            userSession.setAttribute(AppConstants.SES_USER_DEFAULT_FLOWID,primaryFlowId);
                                            userSession.setAttribute(AppConstants.MSCVPROLE,DataSourceDataProvider.getInstance().getRoleNameByRoleId(primaryRole));
                                            }else {
                                                setResultType(INPUT);
                                                httpServletRequest.setAttribute(AppConstants.REQ_ERROR_INFO,"<span class=\"resultFailure\">Access Denied, Please contact Admin! </span>");
                                            }
                                         //   System.out.println("Result Type-->"+getResultType());
                                            if(primaryRole.equalsIgnoreCase("102")){
                                            setResultType("RCTSCSR");
                                            }
                                            else
                                               if(primaryRole.equalsIgnoreCase("103")){
                                            setResultType("INVOICE USER");
                                            }
				} else {

					httpServletRequest.setAttribute(AppConstants.REQ_ERROR_INFO,"<span class=\"resultFailure\">Sorry! Your account was InActive, Please contact Admin! </span>");
					setResultType(INPUT);
				}// Closing account active flag checking condition

			} else {

				if (logger.isDebugEnabled()) {
					logger.info("User With loginId " + loginId
							+ "Failed to Login into  EDI ONLINE PORTAL");
				}

				httpServletRequest.setAttribute(AppConstants.REQ_ERROR_INFO,
								"<b>Please Login with valid UserId and Password! </b>");
				setResultType(INPUT);
			}// Closing Password checking condition
		} else {
			httpServletRequest.setAttribute(AppConstants.REQ_ERROR_INFO,
							"<span class=\"resultFailure\">Please Login with valid UserId and Password! </span>");
			setResultType(INPUT);
		}
		logger.info("End of ::::LoginAction :::: execute()");
                /*if(getResultType().equalsIgnoreCase("msscvpAdmin")||getResultType().equalsIgnoreCase("Manufacturing")||
                        getResultType().equalsIgnoreCase("Logistics")||getResultType().equalsIgnoreCase("Retailer")){
                    
                            logUserAccess();
                           
                }*/
                
               
                
                System.out.println("result--->"+getResultType());
                if(getResultType().equals("input")){
                    httpServletRequest.getSession(false).removeAttribute(AppConstants.SES_LOGIN_ID);
                }
                }catch (Exception e) {
            setResultType(ERROR);
            httpServletRequest.getSession(false).setAttribute("exceptionMessage", "Unable to connect Database Please contact System Admin!");
            
        }
            System.out.println("getResultType---"+getResultType());
		return getResultType();
	}
public void logUserAccess() throws Exception {
        try{
            //System.err.println("in log user access");
                if(getHttpServletRequest().getSession(false).getAttribute(AppConstants.SES_LOGIN_ID) != null){
                    String UserId=getHttpServletRequest().getSession(false).getAttribute(AppConstants.SES_LOGIN_ID).toString();
                    String forwarded = httpServletRequest.getHeader("X-FORWARDED-FOR");
                    String via = httpServletRequest.getHeader("VIA");
                    String remote = httpServletRequest.getRemoteAddr();
                    String agent = httpServletRequest.getHeader("User-Agent");
                    String location = httpServletRequest.getLocalAddr();
                  
                    Timestamp accessedtime=DateUtility.getInstance().getCurrentDB2Timestamp();
                    Connection connection=null;
                    Statement stmt=null;
                    ResultSet resultSet=null;
                    boolean isInserted=false;
                    String query = null;
                    try{
                        connection = ConnectionProvider.getInstance().getConnection();
                        query = "insert into LOGUSERACCESS(LoginId,X_FORWARDED_FOR1,VIA, REMOTE_ADDR,User_Agent,DateAccessed)"
                                + " values('"+UserId+"','"+forwarded+"','"+via+"','"+remote+"','"+agent+"','"+accessedtime+"')";
                    stmt=connection.createStatement();
                        int x = stmt.executeUpdate(query);
                        stmt.close();
                        if(x>0){
                            isInserted=true;
                        }
                    }catch(SQLException sql){
                        sql.printStackTrace();
                        throw new ServiceLocatorException(sql);
                    }finally{
                        try{
                            if(stmt!=null){
                                stmt.close();
                                stmt = null;
                            }
                            if(connection!=null){
                                connection.close();
                                connection = null;
                            }
                        }catch(SQLException sqle){
                            throw new ServiceLocatorException(sqle);
                        }
                    }
                    
                 
            }
        }catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            ex.printStackTrace();
            getHttpServletRequest().getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        //   return resultType;
    }
	/**
	 * method is used to invalidate session
	 */

	public String switchDB() throws Exception {
		logger.info("Entered into the ::::LoginAction :::: switchDB()");

		HttpSession userSession = httpServletRequest.getSession(false);

		String db = userSession.getAttribute(AppConstants.SES_FIRST_DB)
				.toString();
		if (db.startsWith("Ar")) {
			userSession.setAttribute(AppConstants.PROP_CURRENT_DS_NAME,
					Properties.getProperty(AppConstants.PROP_PROD_DS_NAME));
			userSession.setAttribute(AppConstants.SES_FIRST_DB,
					"Production Data");
		} else {
			userSession.setAttribute(AppConstants.PROP_CURRENT_DS_NAME,
					Properties.getProperty(AppConstants.PROP_ARCH_DS_NAME));
			userSession.setAttribute(AppConstants.SES_FIRST_DB, "Archive Data");
		}
		logger.info("End of ::::LoginAction :::: switchDB()");
		return "success";

	}

	public String doLogout() throws Exception {
		logger.info("Entered into the ::::LoginAction :::: doLogout()");
		try {
			if (httpServletRequest.getSession(false) != null) {
				httpServletRequest.getSession(false).invalidate();

			}
			setResultType(SUCCESS);
		} catch (Exception ex) {
			// List errorMsgList = ExceptionToListUtility.errorMessages(ex);
			httpServletRequest.getSession(false).setAttribute(
					AppConstants.REQ_ERROR_INFO, ex.toString());
			if (logger.isDebugEnabled()) {
				logger.error("Exception is :: LoginAction:: doLogout()"
						+ ex.toString());
			}
			setResultType(ERROR);
		}
		logger.info("End of ::::LoginAction :::: doLogout()");
		return getResultType();
	}// end of the doLogout method

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(
	 * javax.servlet.http.HttpServletRequest)
	 */
	//@Override
	public void setServletRequest(HttpServletRequest reqObj) {
		this.setHttpServletRequest(reqObj);
	}

	/**
	 * @param resultType
	 *            the resultType to set
	 */
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	/**
	 * @return the resultType
	 */
	public String getResultType() {
		return resultType;
	}

	/**
	 * @param httpServletRequest
	 *            the httpServletRequest to set
	 */
	public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}

	/**
	 * @return the httpServletRequest
	 */
	public HttpServletRequest getHttpServletRequest() {
		return httpServletRequest;
	}


	/**
	 * @param loginId
	 *            the loginId to set
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * @return the loginId
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param changeDb
	 *            the changeDb to set
	 */
	public void setChangeDb(String changeDb) {
		this.changeDb = changeDb;
	}

	/**
	 * @return the changeDb
	 */
	public String getChangeDb() {
		return changeDb;
	}
}
