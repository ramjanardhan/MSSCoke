/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.manifacturing;

import com.mss.ediscv.util.AppConstants;
import com.mss.ediscv.util.DataSourceDataProvider;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author miracle1
 */
public class ManifacturingAction extends ActionSupport implements ServletRequestAware{
private HttpServletRequest httpServletRequest;
public String execute() throws Exception{
   //   System.out.println("In ManifacturingAction Execute");
        String defaultFlowId = httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_DEFAULT_FLOWID).toString();
        String defaultFlowName = DataSourceDataProvider.getInstance().getFlowNameByFlowID(defaultFlowId);
    if (!defaultFlowName.equals("Manufacturing")){
        defaultFlowId = DataSourceDataProvider.getInstance().getFlowIdByFlowName("Manufacturing");
        httpServletRequest.getSession(false).setAttribute(AppConstants.SES_USER_DEFAULT_FLOWID, defaultFlowId);
    }
    return SUCCESS;
}
    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
       this.httpServletRequest = httpServletRequest;
    }
    
    
}
