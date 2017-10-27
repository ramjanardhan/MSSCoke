/*
 * AjaxHandlerAction.java
 *
 * Created on June 11, 2008, 12:22 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.ediscv.ajax;

 

import com.mss.ediscv.util.AppConstants;
import com.mss.ediscv.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;





/**
 *
 * @author miracle
 */
public class AjaxHandlerAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
    
    /**
     * Creating a reference variable for HttpServletRequest.
     */
    private HttpServletRequest httpServletRequest;
    
    /**
     * Creating a reference variable for HttpServletResponse.
     */
    private HttpServletResponse httpServletResponse;
    private int id;
    private String responseString;
    private String poNumber;
    private String poInst;
    
    private String asnNumber;
    private String invNumber;
    
    private String isaNumber;
    
    private String poList;
    // new field for asn copy
     private String asnList;
     private String invList;
      private String paymentList;
    private String type;
    
    private String fileId;
    
    private String tpId;
    private String name;
    private String dept;
    private String commid;
    private String contact;
    private String phno;
    private String qualifier;
     private String refId;
     private String loadList;
     private int docId;
     
     // new screen fields
     private String partnerId;
     private String routingId;
     private String b2bChannelId;
     private String routerId;
     private String businessProcessId;
    /** Creates a new instance of AjaxHandlerAction */
     
     private int deliveryChannelId;
     
     
     // Dash board fields
     private String startDate;
     private String endDate;
     private String senderId;
     private String receiverId;
     private String docType;
     private String ackStatus;

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
     private String status;
     private String direction;
     private String database;
     
     
     
    public AjaxHandlerAction() {
    }
    
    
    public String getPoDetails() {
        if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                responseString = ServiceLocator.getAjaxHandlerService().getPoDetails(getPoNumber(),getPoInst()).toString();              
               // System.out.println("responseString--->"+responseString);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
    
    public String getAsnDetails() {
         if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                responseString = ServiceLocator.getAjaxHandlerService().getASNDetails(getAsnNumber(),getPoNumber(),getFileId()).toString();              
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
    
    public String getInvDetails() {
         if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                 responseString = ServiceLocator.getAjaxHandlerService().getInvDetails(getInvNumber(),getPoNumber(),getFileId()).toString();              
                // System.out.println("responseString----->"+responseString);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
    
    
    public String getDocDetails() {
         if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                System.out.println("in actionnnnnn");
                System.out.println(getIsaNumber());
                System.out.println(getPoNumber());
                System.out.println(getId());
                System.out.println(getDatabase());
                 responseString = ServiceLocator.getAjaxHandlerService().getDocDetails(getIsaNumber(),getPoNumber(),getId(),getDatabase()).toString();              
                 //System.out.println("responseString--->"+responseString);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
    
     public String getReportDeleteDetails() {
         if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                //System.out.println("in actionnnnnn");
                 responseString = ServiceLocator.getAjaxHandlerService().getReportDeleteDetails(getId()).toString();              
                 //System.out.println("responseString--->"+responseString);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
    
    
    public String getPaymentDetails() {
       if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                 responseString = ServiceLocator.getAjaxHandlerService().getPaymentDetails(getFileId()).toString();              
                 
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null; 
    }
    
    public String getDocCopy() {
         if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                
               // System.err.println("poList----->"+getPoList().toString());
                responseString = ServiceLocator.getAjaxHandlerService().getDocCopy(getPoList().toString(),getType().toString());              
                // System.out.println("responseString--->"+responseString);
                 httpServletResponse.setContentType("text/xml");
                 httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
    
   //get asn datails for copy
    
       public String getDocASNCopy() {
         if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                
               // System.err.println("poList----->"+getPoList().toString());
                responseString = ServiceLocator.getAjaxHandlerService().getDocASNCopy(getAsnList().toString(),getType().toString());              
                // System.out.println("responseString--->"+responseString);
                 httpServletResponse.setContentType("text/xml");
                 httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
       
          public String getInvCopy() {
         if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                
               // System.err.println("poList----->"+getPoList().toString());
                responseString = ServiceLocator.getAjaxHandlerService().getInvCopy(getInvList().toString(),getType().toString());              
                // System.out.println("responseString--->"+responseString);
                 httpServletResponse.setContentType("text/xml");
                 httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
                 public String getPaymentCopy() {
         if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                
               // System.err.println("poList----->"+getPoList().toString());
                responseString = ServiceLocator.getAjaxHandlerService().getInvCopy(getPaymentList().toString(),getType().toString());              
                // System.out.println("responseString--->"+responseString);
                 httpServletResponse.setContentType("text/xml");
                 httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
    
      public String getLoadCopy() {
         if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                
               // System.err.println("poList----->"+getPoList().toString());
                responseString = ServiceLocator.getAjaxHandlerService().getLoadCopy(getLoadList().toString(),getType().toString());              
                // System.out.println("responseString--->"+responseString);
                 httpServletResponse.setContentType("text/xml");
                 httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
    
    /**
     * 
     *  Life cycle Calls
     */
        public String getLifecycleDetails() {
         if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                 responseString = ServiceLocator.getAjaxHandlerService().getLifeCycleDetails(getPoNumber(),getFileId(),getType()).toString();              
                // System.out.println("responseString in action--->"+responseString);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
    
    
        /** Tp Add and Update**/
        
         /**
         * Tp Details 
         * 
         */
         public String getTpDetails() {
        if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                responseString = ServiceLocator.getAjaxHandlerService().getTpDetails(getTpId()).toString();              
               // System.out.println("responseString--->"+responseString);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
         /*
          * 
          * To Update tp Details
          */
             public String updateTpDetails() {
        if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                responseString = ServiceLocator.getAjaxHandlerService().updateTpDetails(this).toString();              
               // System.out.println("responseString--->"+responseString);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
  

     //for getting TradingPartner Detail Information
          public String getTpDetailInformation() {
        if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                responseString = ServiceLocator.getAjaxHandlerService().getTpDetailInformation(getTpId(),httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_DEFAULT_FLOWID).toString()).toString();              
               // System.out.println("responseString--->"+responseString);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }  
          
      public String getLogisticsDocDetails() {
         if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                //System.out.println("in actionnnnnn");
                 responseString = ServiceLocator.getAjaxHandlerService().getLogisticsDocDetails(getIsaNumber(),getId(),getDatabase()).toString();              
                // System.out.println("responseString--->"+responseString);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
      
 public String getLoadTenderingDetails() {
         if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                //System.out.println("in actionnnnnn");
                 responseString = ServiceLocator.getAjaxHandlerService().getLoadTenderingDetails(getIsaNumber(),getPoNumber(),getId()).toString();              
                 System.out.println("responseString--->"+responseString);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
 public String getLtResponseDetails() {
         if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                //System.out.println("in actionnnnnn");
                responseString = ServiceLocator.getAjaxHandlerService().getLtResponseDetails(getFileId(), getRefId()).toString();              
                // System.out.println("responseString--->"+responseString);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
 
public String getLogisticsInvDetails() {
         if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                //System.out.println("in actionnnnnn");
                 responseString = ServiceLocator.getAjaxHandlerService().getLogisticsInvDetails(getInvNumber(),getId()).toString();              
                 //System.out.println("responseString--->"+responseString);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
 
  public String getLogisticsShipmentDetails() {
         if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                //System.out.println("in actionnnnnn");
                 //responseString = ServiceLocator.getAjaxHandlerService().getLogisticsShipmentDetails(getAsnNumber(),getPoNumber()).toString(); 
                responseString = ServiceLocator.getAjaxHandlerService().getLogisticsShipmentDetails(getAsnNumber(),getPoNumber(),getId(),getDatabase()).toString();
                 //System.out.println("responseString--->"+responseString);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
  
  
  /*Method for document visibility detalinfo
   * Author : Santosh Kola
   * Date : 01-06-2014
   * 
   */
 public String getDocVisibilityDetails() {
         if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                //System.out.println("in actionnnnnn");
                 responseString = ServiceLocator.getAjaxHandlerService().getDocVisibilityDetails(getDocId());              
                 System.out.println("responseStringAction--->"+responseString);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
  
  
  // new screens
 public String getPartnerDetails() {
         if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                //System.out.println("in actionnnnnn");
                 responseString = ServiceLocator.getAjaxHandlerService().getPartnerDetails(getPartnerId());              
                 System.err.println("responseStringAction--->"+responseString);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
  
  public String getRoutingDetails() {
         if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                //System.out.println("in actionnnnnn");
                 responseString = ServiceLocator.getAjaxHandlerService().getRoutingDetails(getRoutingId());              
                 System.err.println("responseStringAction--->"+responseString);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
  
   
  public String getB2bChannelDetails() {
         if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                //System.out.println("in actionnnnnn");
                 responseString = ServiceLocator.getAjaxHandlerService().getB2bChannelDetails(getB2bChannelId());              
                 System.err.println("responseStringAction--->"+responseString);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
    public String getPartnerInfo() {
         if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                //System.out.println("in actionnnnnn");
                 responseString = ServiceLocator.getAjaxHandlerService().getPartnerInfo(getPartnerId());              
                 System.err.println("responseStringAction--->"+responseString);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
  
   public String getRouterInfo() {
         if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                System.out.println("in actionnnnnn------->"+getRouterId());
                 responseString = ServiceLocator.getAjaxHandlerService().getRouterInfo(getRouterId());              
                 System.out.println("responseStringAction--->"+responseString);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
 public String getBusinessProcessInfo() {
         if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                System.out.println("in getBusinessProcessId------->"+getBusinessProcessId());
                 responseString = ServiceLocator.getAjaxHandlerService().getBusinessProcessInfo(getBusinessProcessId());              
                 System.out.println("responseStringAction--->"+responseString);
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
  public String getDeliveryChannelDetails() {
         if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                
               // System.err.println("poList----->"+getPoList().toString());
                responseString = ServiceLocator.getAjaxHandlerService().getDeliveryChannelDetails(getDeliveryChannelId());              
                // System.out.println("responseString--->"+responseString);
                 httpServletResponse.setContentType("text/xml");
                 httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
  
  
  /*Method for displaying Dashboarddetails
   * Date : 02/19/2015
   * 
   */
   public String getDashboardDetails() {
         if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                   String roleId=(String)httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID);
                   System.out.println("roleId===="+roleId);
                responseString = ServiceLocator.getAjaxHandlerService().getDashboardDetails(this,roleId); 
                System.out.println("StartDate-->"+getStartDate());
                System.out.println("EndDate-->"+getEndDate());
                System.out.println("SenderId-->"+getSenderId());
                System.out.println("ReceiverId-->"+getReceiverId());
                System.out.println("DocumentType-->"+getDocType());
                
                // responseString="hiii";
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
   
   // new action for schdular task
   
 public String getReportOverlayDetails() {
         if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                //System.out.println("in actionnnnnn");
                 responseString = ServiceLocator.getAjaxHandlerService().getReportOverlayDetails(getId(),getStartDate()).toString();              
                 //System.out.println("responseString--->"+responseString);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }

        
      public String getLogisticsDocErrorDetails() {
         if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null){
            try {
                System.out.println("in actionnnnnn"+getId());
                 responseString = ServiceLocator.getAjaxHandlerService().getLogisticsDocErrorDetails(getId());              
                 System.out.println("responseString--->"+responseString);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
      
 
 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    /**
     *
     * This method is used to set the Servlet Request
     * @param httpServletRequest
     */
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    /**
     *
     * This method is used to set the Servlet Response
     * @param httpServletResponse
     */
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    /**
     * @return the poNumber
     */
    public String getPoNumber() {
        return poNumber;
    }

    /**
     * @param poNumber the poNumber to set
     */
    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }
 
    /**
     * @return the asnNumber
     */
    public String getAsnNumber() {
        return asnNumber;
    }

    /**
     * @param asnNumber the asnNumber to set
     */
    public void setAsnNumber(String asnNumber) {
        this.asnNumber = asnNumber;
    }

    /**
     * @return the invNumber
     */
    public String getInvNumber() {
        return invNumber;
    }

    /**
     * @param invNumber the invNumber to set
     */
    public void setInvNumber(String invNumber) {
        this.invNumber = invNumber;
    }

    /**
     * @return the isaNumber
     */
    public String getIsaNumber() {
        return isaNumber;
    }

    /**
     * @param isaNumber the isaNumber to set
     */
    public void setIsaNumber(String isaNumber) {
        this.isaNumber = isaNumber;
    }

    /**
     * @return the poList
     */
    public String getPoList() {
        return poList;
    }

    /**
     * @param poList the poList to set
     */
    public void setPoList(String poList) {
        this.poList = poList;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the poInst
     */
    public String getPoInst() {
        return poInst;
    }

    /**
     * @param poInst the poInst to set
     */
    public void setPoInst(String poInst) {
        this.poInst = poInst;
    }

    /**
     * @return the fileId
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * @param fileId the fileId to set
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * @return the tpId
     */
    public String getTpId() {
        return tpId;
    }

    /**
     * @param tpId the tpId to set
     */
    public void setTpId(String tpId) {
        this.tpId = tpId;
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
     * @return the dept
     */
    public String getDept() {
        return dept;
    }

    /**
     * @param dept the dept to set
     */
    public void setDept(String dept) {
        this.dept = dept;
    }

    /**
     * @return the commid
     */
    public String getCommid() {
        return commid;
    }

    /**
     * @param commid the commid to set
     */
    public void setCommid(String commid) {
        this.commid = commid;
    }

    /**
     * @return the contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @return the phno
     */
    public String getPhno() {
        return phno;
    }

    /**
     * @param phno the phno to set
     */
    public void setPhno(String phno) {
        this.phno = phno;
    }

    /**
     * @return the qualifier
     */
    public String getQualifier() {
        return qualifier;
    }

    /**
     * @param qualifier the qualifier to set
     */
    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    /**
     * @return the refId
     */
    public String getRefId() {
        return refId;
    }

    /**
     * @param refId the refId to set
     */
    public void setRefId(String refId) {
        this.refId = refId;
    }

    /**
     * @return the loadList
     */
    public String getLoadList() {
        return loadList;
    }

    /**
     * @param loadList the loadList to set
     */
    public void setLoadList(String loadList) {
        this.loadList = loadList;
    }

    /**
     * @return the docId
     */
    public int getDocId() {
        return docId;
    }

    /**
     * @param docId the docId to set
     */
    public void setDocId(int docId) {
        this.docId = docId;
    }

    /**
     * @return the partnerId
     */
    public String getPartnerId() {
        return partnerId;
    }

    /**
     * @param partnerId the partnerId to set
     */
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    /**
     * @return the routingId
     */
    public String getRoutingId() {
        return routingId;
    }

    /**
     * @param routingId the routingId to set
     */
    public void setRoutingId(String routingId) {
        this.routingId = routingId;
    }

    /**
     * @return the b2bChannelId
     */
    public String getB2bChannelId() {
        return b2bChannelId;
    }

    /**
     * @param b2bChannelId the b2bChannelId to set
     */
    public void setB2bChannelId(String b2bChannelId) {
        this.b2bChannelId = b2bChannelId;
    }

    /**
     * @return the routerId
     */
    public String getRouterId() {
        return routerId;
    }

    /**
     * @param routerId the routerId to set
     */
    public void setRouterId(String routerId) {
        this.routerId = routerId;
    }

    /**
     * @return the businessProcessId
     */
    public String getBusinessProcessId() {
        return businessProcessId;
    }

    /**
     * @param businessProcessId the businessProcessId to set
     */
    public void setBusinessProcessId(String businessProcessId) {
        this.businessProcessId = businessProcessId;
    }

    /**
     * @return the deliveryChannelId
     */
    public int getDeliveryChannelId() {
        return deliveryChannelId;
    }

    /**
     * @param deliveryChannelId the deliveryChannelId to set
     */
    public void setDeliveryChannelId(int deliveryChannelId) {
        this.deliveryChannelId = deliveryChannelId;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
     * @return the docType
     */
    public String getDocType() {
        return docType;
    }

    /**
     * @param docType the docType to set
     */
    public void setDocType(String docType) {
        this.docType = docType;
    }

    /**
     * @return the ackStatus
     */
    public String getAckStatus() {
        return ackStatus;
    }

    /**
     * @param ackStatus the ackStatus to set
     */
    public void setAckStatus(String ackStatus) {
        this.ackStatus = ackStatus;
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

    public String getAsnList() {
        return asnList;
    }

    public void setAsnList(String asnList) {
        this.asnList = asnList;
    }

    public String getInvList() {
        return invList;
    }

    public void setInvList(String invList) {
        this.invList = invList;
    }

    public String getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(String paymentList) {
        this.paymentList = paymentList;
    }
	

    
}

