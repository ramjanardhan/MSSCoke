/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.switchflow;

import com.mss.ediscv.util.AppConstants;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author miracle1
 */
public class SwitchFlowAction extends ActionSupport implements ServletRequestAware{
    private int flowId;
    private HttpServletRequest httpServletRequest;
    
    private String resultType;

    
    
    public String prepare() throws Exception {
        
        setResultType(LOGIN);
       
	       if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
                  
        if(flowId == 2) {
            setResultType("Manufacturing");
        }else if(flowId == 3) {
           setResultType("Logistics"); 
        }else if(flowId == 4) {
            setResultType("Retailer"); 
        }else if(flowId == 5) {
            setResultType("DocumentVisibility"); 
        }else if(flowId == 1) {
            setResultType("msscvpAdmin"); 
        }else {
            setResultType(ERROR); 
        }
        
    }
        return getResultType();
    }
    
    
    
    
    /**
     * @return the flowId
     */
    public int getFlowId() {
        return flowId;
    }

    /**
     * @param flowId the flowId to set
     */
    public void setFlowId(int flowId) {
        this.flowId = flowId;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * @return the resultType
     */
    public String getResultType() {
        return resultType;
    }

    /**
     * @param resultType the resultType to set
     */
    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
    
}
