/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.b2bchannel;
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
public class B2BChannelAction extends ActionSupport implements
ServletRequestAware{
     private static Logger logger = Logger.getLogger(B2BChannelAction.class
			.getName());
 private HttpServletRequest httpServletRequest;   
 private String resultType;
 private String formAction;
 /*
  * B2Bchannel form fields start
  * 
  */
 private int b2bChannelId;
 private String partnerName;
 private String status;
 private String direction;
 private String protocol;
 private String host;
 private String userName;
 private String producerMailBox;
 private String consumerMailBox;
 private String pollingCode;
 private String appId;
 private String senderId;
 private String receiverId;
 private String createdBy;
 private String flowFlag;
 
 
   public String getB2BChannelList() throws Exception {
            
           // System.out.println("in tp Action----");
		logger.info("Entered into the ::::B2BChannelAction :::: getB2BChannelList ");
		setResultType(LOGIN);
	       if(getHttpServletRequest().getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
                  // setFormAction("addPartner");
                    if(getHttpServletRequest().getSession(false).getAttribute(AppConstants.SES_B2BCHANNEL_LIST)!=null)
                   getHttpServletRequest().getSession(false).removeAttribute(AppConstants.SES_B2BCHANNEL_LIST);
                   resultType = SUCCESS;
                  
               }
 return resultType;
  }
 
   public String addB2BChannel() throws Exception {
            
           // System.out.println("in tp Action----");
		logger.info("Entered into the ::::B2BChannelAction :::: addB2BChannel ");
		setResultType(LOGIN);
	       if(getHttpServletRequest().getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
                  setFormAction("doAddB2BChannel");
                   resultType = SUCCESS;
                  
               }
 return resultType;
  }
 
   public String doAddB2BChannel() throws Exception {
            
           // System.out.println("in tp Action----");
		logger.info("Entered into the ::::B2BChannelAction :::: doAddB2BChannel ");
		setResultType(LOGIN);
	       if(getHttpServletRequest().getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
                   setCreatedBy(getHttpServletRequest().getSession(false).getAttribute(AppConstants.SES_LOGIN_ID).toString());
                  setFormAction("doAddB2BChannel");
               String resultMessage=ServiceLocator.getB2BChannelService().addB2BChannel(this);
                 getHttpServletRequest().setAttribute(AppConstants.REQ_RESULT_MSG,resultMessage);
                   resultType = SUCCESS;
                  
               }
 return resultType;
  }
   
   
            public String b2BChannelListSearch() throws Exception {
            
           // System.out.println("in tp Action----");
		logger.info("Entered into the ::::B2BChannelAction :::: b2BChannelListSearch ");
		setResultType(LOGIN);
	       if(getHttpServletRequest().getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
                 //  setCreatedBy(getHttpServletRequest().getSession(false).getAttribute(AppConstants.SES_LOGIN_ID).toString());
               ArrayList<B2BChannelBean> b2bChannelList = ServiceLocator.getB2BChannelService().buildB2BChannelQuery(this);
                   getHttpServletRequest().getSession(false).setAttribute(AppConstants.SES_B2BCHANNEL_LIST, b2bChannelList);
                   
                   resultType = SUCCESS;
                  
               }
 return resultType;
  }
            
             public String b2bchannelEdit() throws Exception {
            
           // System.out.println("in tp Action----");
		logger.info("Entered into the ::::B2BChannelAction :::: b2bchannelEdit ");
		setResultType(LOGIN);
	       if(getHttpServletRequest().getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
                 setFormAction("doEditB2BChannel");
                   
                  
                 
                    ServiceLocator.getB2BChannelService().b2BChannelEdit(this);
                   
                   resultType = SUCCESS;
                  
               }
 return resultType;
  }
   public String doEditB2BChannel() throws Exception {
            
           // System.out.println("in tp Action----");
		logger.info("Entered into the ::::B2BChannelAction :::: doEditB2BChannel ");
		setResultType(LOGIN);
	       if(getHttpServletRequest().getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
                   setCreatedBy(getHttpServletRequest().getSession(false).getAttribute(AppConstants.SES_LOGIN_ID).toString());
                  setFormAction("doEditB2BChannel");
               String resultMessage=ServiceLocator.getB2BChannelService().doEditB2BChannel(this);
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
     * @return the b2bChannelId
     */
    public int getB2bChannelId() {
        return b2bChannelId;
    }

    /**
     * @param b2bChannelId the b2bChannelId to set
     */
    public void setB2bChannelId(int b2bChannelId) {
        this.b2bChannelId = b2bChannelId;
    }

    /**
     * @return the partnerName
     */
    public String getPartnerName() {
        return partnerName;
    }

    /**
     * @param partnerName the partnerName to set
     */
    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
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
     * @return the protocol
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * @param protocol the protocol to set
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the producerMailBox
     */
    public String getProducerMailBox() {
        return producerMailBox;
    }

    /**
     * @param producerMailBox the producerMailBox to set
     */
    public void setProducerMailBox(String producerMailBox) {
        this.producerMailBox = producerMailBox;
    }

    /**
     * @return the consumerMailBox
     */
    public String getConsumerMailBox() {
        return consumerMailBox;
    }

    /**
     * @param consumerMailBox the consumerMailBox to set
     */
    public void setConsumerMailBox(String consumerMailBox) {
        this.consumerMailBox = consumerMailBox;
    }

    /**
     * @return the pollingCode
     */
    public String getPollingCode() {
        return pollingCode;
    }

    /**
     * @param pollingCode the pollingCode to set
     */
    public void setPollingCode(String pollingCode) {
        this.pollingCode = pollingCode;
    }

    /**
     * @return the appId
     */
    public String getAppId() {
        return appId;
    }

    /**
     * @param appId the appId to set
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * @return the senderId
     */
    public String getSenderId() {
        return senderId;
    }

    /**
     * @param senderId the senderId to set
     */
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    /**
     * @return the receiverId
     */
    public String getReceiverId() {
        return receiverId;
    }

    /**
     * @param receiverId the receiverId to set
     */
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the flowFlag
     */
    public String getFlowFlag() {
        return flowFlag;
    }

    /**
     * @param flowFlag the flowFlag to set
     */
    public void setFlowFlag(String flowFlag) {
        this.flowFlag = flowFlag;
    }
}
