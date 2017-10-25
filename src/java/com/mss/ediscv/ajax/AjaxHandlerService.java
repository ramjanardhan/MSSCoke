/*
 * AjaxHandlerService.java
 *
 *  
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.ediscv.ajax;

import com.mss.ediscv.util.ServiceLocatorException;
import java.util.List;

 
 

/**
 *
 * @author miracle
 */
public interface AjaxHandlerService {
    
    /** Creates a new instance of AjaxHandlerService */
    
         
     public String getPoDetails(String poNumber,String poInst)throws ServiceLocatorException;
     
     public String getASNDetails(String asnNumber,String poNumber,String fileID)throws ServiceLocatorException;
     public String getInvDetails(String invNumber,String poNumber,String fileID)throws ServiceLocatorException;
     public String getPaymentDetails(String fileId)throws ServiceLocatorException;
     public String getDocDetails(String isaNumber,String poNum,int id) throws ServiceLocatorException;
     public String getReportDeleteDetails(int id) throws ServiceLocatorException;
     
     public String getDocCopy(String poList,String type) throws ServiceLocatorException; 
     public String getDocASNCopy(String asnList,String type) throws ServiceLocatorException; 
      public String getInvCopy(String invList,String type) throws ServiceLocatorException; 
       public String getPaymentCopy(String paymentList,String type) throws ServiceLocatorException;
     public String getLoadCopy(String loadList,String type) throws ServiceLocatorException; 
     
     
     /**
      *  Life cycle Actions
      * 
      */
     public String getLifeCycleDetails(String poNumber,String fileId,String type)throws ServiceLocatorException;
     
     /**
      * 
      * TP Ajax cals
      */
     public String getTpDetails(String tpId) throws ServiceLocatorException;
     public String updateTpDetails(AjaxHandlerAction ajaxHandlerAction)throws ServiceLocatorException;
     
     
     public String getTpDetailInformation(String tpId,String defFlowId) throws ServiceLocatorException;
     public String getLogisticsDocDetails(String instanceid,int id)throws ServiceLocatorException;
     public String getLoadTenderingDetails(String instanceid,String ponum,int id)throws ServiceLocatorException;
     public String getLtResponseDetails(String invNum,String ponum)throws ServiceLocatorException;
     public String getLogisticsInvDetails(String invNum,int id)throws ServiceLocatorException;
     public String getLogisticsShipmentDetails(String asnNum,String ponum,int id)throws ServiceLocatorException;
     public String getDocVisibilityDetails(int docId)throws ServiceLocatorException;
     
     public String getPartnerDetails(String partnerId)throws ServiceLocatorException;
     public String getRoutingDetails(String routingId)throws ServiceLocatorException;
     public String getB2bChannelDetails(String b2bChannelId)throws ServiceLocatorException;
     public String getPartnerInfo(String partnerId)throws ServiceLocatorException;
      public String getRouterInfo(String routerName)throws ServiceLocatorException;
      public String getBusinessProcessInfo(String businessProcessId)throws ServiceLocatorException;
      
      public String getDeliveryChannelDetails(int deliveryChannelId)throws ServiceLocatorException;
      //getDashboardDetails
      public String getDashboardDetails(AjaxHandlerAction ajaxHandlerAction,String roleId)throws ServiceLocatorException;
      public String getReportOverlayDetails(int id,String startDate) throws ServiceLocatorException;
      
  public String getLogisticsDocErrorDetails(int id)throws ServiceLocatorException;
   
      
}
