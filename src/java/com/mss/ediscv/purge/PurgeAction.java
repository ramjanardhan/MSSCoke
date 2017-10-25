/*
 * Author : Santosh Kola
 * Created Date : 07/01/2013
 * 
 */
package com.mss.ediscv.purge;

import com.mss.ediscv.util.AppConstants;
import com.mss.ediscv.util.AuthorizationManager;
import com.mss.ediscv.util.DataSourceDataProvider;
import com.mss.ediscv.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
/**
 *
 * @author miracle
 */
public class PurgeAction extends ActionSupport implements
ServletRequestAware{
    private HttpServletRequest httpServletRequest;
    
    private String datepickerfrom;
    private String datepickerTo;
    
    private String transType;
    private String resultType;
    private String resultMessage;
    private String dayCount;
    
    public String execute(){
        resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
            resultType = "accessFailed";
                   int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID).toString());
                 if(AuthorizationManager.getInstance().isAuthorizedUser("PURGE_PROCESS",userRoleId)){  
            resultType = SUCCESS;
                 }
        }
        return resultType;
    }
    
    
    
    public String doPurgeProcess() {
    resultType = LOGIN;
        if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
            resultType = "accessFailed";
                   int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID).toString());
                 if(AuthorizationManager.getInstance().isAuthorizedUser("PURGE_PROCESS",userRoleId)){  
            try {
           
           resultMessage =  ServiceLocator.getPurgeService().purgeProcess(this);
            //resultMessage = "<font color='green'>Purge Process Completed Successfully</font>";
           setDayCount("");
           setTransType("-1");
            httpServletRequest.setAttribute(AppConstants.REQ_RESULT_MSG,resultMessage);
            resultType = SUCCESS;
            }catch (Exception e) {
                resultType = ERROR;
                e.printStackTrace();
            }
                 }
        }
    return resultType;
}
   
       @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    /**
     * @return the datepickerfrom
     */
    public String getDatepickerfrom() {
        return datepickerfrom;
    }

    /**
     * @param datepickerfrom the datepickerfrom to set
     */
    public void setDatepickerfrom(String datepickerfrom) {
        this.datepickerfrom = datepickerfrom;
    }

    /**
     * @return the docdatepickerTo
     */
    public String getDatepickerTo() {
        return datepickerTo;
    }

    /**
     * @param docdatepickerTo the docdatepickerTo to set
     */
    public void setDatepickerTo(String datepickerTo) {
        this.datepickerTo = datepickerTo;
    }

    /**
     * @return the transType
     */
    public String getTransType() {
        return transType;
    }

    /**
     * @param transType the transType to set
     */
    public void setTransType(String transType) {
        this.transType = transType;
    }

    /**
     * @return the dayCount
     */
    public String getDayCount() {
        return dayCount;
    }

    /**
     * @param dayCount the dayCount to set
     */
    public void setDayCount(String dayCount) {
        this.dayCount = dayCount;
    }
}
