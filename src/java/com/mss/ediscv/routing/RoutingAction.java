/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.routing;
import com.mss.ediscv.util.AppConstants;
import com.mss.ediscv.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.log4j.Logger;
/**
 *
 * @author miracle
 */
public class RoutingAction extends ActionSupport implements
ServletRequestAware{
     private static Logger logger = Logger.getLogger(RoutingAction.class
			.getName());
 private HttpServletRequest httpServletRequest;   
 private String resultType;
 private String formAction;
 
 
 private int routingId;
 private String name;
 private String acceptorLookupAlias;
 private String direction;
 private String internalRouteEmail;
 private String systemType;
 private String status;
 private String destMailBox;
 private String envelope;
 
 
   public String addRouting() throws Exception {
            
           // System.out.println("in tp Action----");
		logger.info("Entered into the ::::RoutingAction :::: addRouting ");
		setResultType(LOGIN);
	       if(getHttpServletRequest().getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
                   setFormAction("doAddRouting");
                   
                  resultType = SUCCESS;
               }
 return resultType;
  }
    public String doAddRouting() throws Exception {
            
           // System.out.println("in tp Action----");
		logger.info("Entered into the ::::RoutingAction :::: doAddRouting ");
		setResultType(LOGIN);
	       if(getHttpServletRequest().getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
                   setFormAction("doAddRouting");
                   
                  
                  logger.info("name-->"+getName());
                   logger.info("acceptorLookupAlias-->"+getAcceptorLookupAlias());
                   logger.info("direction-->"+getDirection());
                   
                   logger.info("internalRouteEmail-->"+getInternalRouteEmail());
                   logger.info("systemType-->"+getSystemType());
                   logger.info("status-->"+getStatus());
                   logger.info("destMailBox-->"+getDestMailBox());
                   String resultMessage = ServiceLocator.getRoutingService().addRouting(this);
                   getHttpServletRequest().setAttribute(AppConstants.REQ_RESULT_MSG,resultMessage);
                   
                  resultType = SUCCESS;
               }
 return resultType;
  }
 
 public String getRoutingList() throws Exception {
            
           // System.out.println("in tp Action----");
		logger.info("Entered into the ::::RoutingAction :::: getRoutingList ");
		setResultType(LOGIN);
	       if(getHttpServletRequest().getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
                   logger.info("status11-->"+getStatus());
                  // setFormAction("addPartner");
                   if(getHttpServletRequest().getSession(false).getAttribute(AppConstants.SES_ROUTING_LIST)!=null)
                   getHttpServletRequest().getSession(false).removeAttribute(AppConstants.SES_ROUTING_LIST);
                   resultType = SUCCESS;
                  
               }
 return resultType;
  }
 
 
 public String routingSearch() throws Exception{
     // System.out.println("in tp Action----");
		logger.info("Entered into the ::::RoutingAction :::: routingSearch ");
		setResultType(LOGIN);
	       if(getHttpServletRequest().getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
                  // setFormAction("addPartner");
                   logger.info("status11123-->"+getStatus());
                   ArrayList<RoutingBean> routingList = ServiceLocator.getRoutingService().buildRoutingQuery(this);
                   getHttpServletRequest().getSession(false).setAttribute(AppConstants.SES_ROUTING_LIST, routingList);
                   
                   
                   resultType = SUCCESS;
                  
               }
 return resultType;
 }
 
 public String routingEdit() throws Exception {
       // System.out.println("in tp Action----");
		logger.info("Entered into the ::::RoutingAction :::: routingEdit ");
		setResultType(LOGIN);
	       if(getHttpServletRequest().getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
                   setFormAction("doEditRouting");
                   
                  
                 
                    ServiceLocator.getRoutingService().getRouting(this);
                  // getHttpServletRequest().setAttribute(AppConstants.REQ_RESULT_MSG,resultMessage);
                   
                  resultType = SUCCESS;
               }
 return resultType;
 }
 public String doEditRouting() throws Exception {
            
           // System.out.println("in tp Action----");
		logger.info("Entered into the ::::RoutingAction :::: doEditRouting ");
		setResultType(LOGIN);
	       if(getHttpServletRequest().getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
                   setFormAction("doEditRouting");
                   
                  
                  logger.info("name-->"+getName());
                   logger.info("acceptorLookupAlias-->"+getAcceptorLookupAlias());
                   logger.info("direction-->"+getDirection());
                   
                   logger.info("internalRouteEmail-->"+getInternalRouteEmail());
                   logger.info("systemType-->"+getSystemType());
                   logger.info("status-->"+getStatus());
                   logger.info("destMailBox-->"+getDestMailBox());
                   String resultMessage = ServiceLocator.getRoutingService().editRouting(this);
                   getHttpServletRequest().setAttribute(AppConstants.REQ_RESULT_MSG,resultMessage);
                   
                  resultType = SUCCESS;
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

    /**
     * @return the formAction
     */
    public String getFormAction() {
        return formAction;
    }

    /**
     * @param formAction the formAction to set
     */
    public void setFormAction(String formAction) {
        this.formAction = formAction;
    }

    /**
     * @return the routingId
     */
    public int getRoutingId() {
        return routingId;
    }

    /**
     * @param routingId the routingId to set
     */
    public void setRoutingId(int routingId) {
        this.routingId = routingId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the acceptorLookupAlias
     */
    public String getAcceptorLookupAlias() {
        return acceptorLookupAlias;
    }

    /**
     * @param acceptorLookupAlias the acceptorLookupAlias to set
     */
    public void setAcceptorLookupAlias(String acceptorLookupAlias) {
        this.acceptorLookupAlias = acceptorLookupAlias;
    }

    /**
     * @return the direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * @return the internalRouteEmail
     */
    public String getInternalRouteEmail() {
        return internalRouteEmail;
    }

    /**
     * @param internalRouteEmail the internalRouteEmail to set
     */
    public void setInternalRouteEmail(String internalRouteEmail) {
        this.internalRouteEmail = internalRouteEmail;
    }

    /**
     * @return the systemType
     */
    public String getSystemType() {
        return systemType;
    }

    /**
     * @param systemType the systemType to set
     */
    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the destMailBox
     */
    public String getDestMailBox() {
        return destMailBox;
    }

    /**
     * @param destMailBox the destMailBox to set
     */
    public void setDestMailBox(String destMailBox) {
        this.destMailBox = destMailBox;
    }

    /**
     * @return the envelope
     */
    public String getEnvelope() {
        return envelope;
    }

    /**
     * @param envelope the envelope to set
     */
    public void setEnvelope(String envelope) {
        this.envelope = envelope;
    }

}
