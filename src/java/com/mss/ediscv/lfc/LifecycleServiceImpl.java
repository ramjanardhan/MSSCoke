/**
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mss.ediscv.lfc;

import com.mss.ediscv.util.AppConstants;
import com.mss.ediscv.util.DateUtility;
import com.mss.ediscv.util.LifecycleUtility;
import com.mss.ediscv.util.ServiceLocatorException;
import com.mss.ediscv.util.WildCardSql;
import java.sql.*;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;


public class LifecycleServiceImpl implements LifecycleService {
	
	
	Connection connection = null;
        Statement statement = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	CallableStatement callableStatement = null;
       
        
        private LifecycleBeans lifecycleBeans;
        
        private ArrayList<PoLifecycleBean> poLifecycleBeanList;
        private ArrayList<AsnLifecycleBean> asnLifecycleBeanList;
        private ArrayList<InvoiceLifecycleBean> invoiceLifecycleBeanList;
        private ArrayList<PaymentLifecycleBean> PaymentLifecycleBeanList;
      
	private static Logger logger = Logger.getLogger(com.mss.ediscv.lfc.LifecycleServiceImpl.class
			.getName());

	//@Override
	 //public LifecycleBeans buildLifeCycleBeans(LifecycleAction lifecycleAction,HttpServletRequest httpServletRequest)throws ServiceLocatorException {
        public void buildLifeCycleBeans(LifecycleAction lifecycleAction,HttpServletRequest httpServletRequest)throws ServiceLocatorException {
           
		logger.info("Entered into the :::: PurchaseOrderServiceImpl :::: buildPurchaseQuery");
                lifecycleBeans = new LifecycleBeans();
                
                  String Ponum = lifecycleAction.getPoNumber();
                  
                  LifecycleUtility lifecycleUtility = new LifecycleUtility();
                 
                  
                 //lifecycleBeans.setPoLifecycleBeanList(lifecycleUtility.addPoLifeCycleBean(Ponum));
                 poLifecycleBeanList = lifecycleUtility.addPoLifeCycleBean(Ponum);
                 httpServletRequest.getSession(false).setAttribute(AppConstants.LFC_SES_PO_LIST,poLifecycleBeanList);
                 
                 // System.out.println("PO Length--->"+poLifecycleBeanList.size());
                  
                  /** ASN process**/
                 // System.out.println("ASN start");
                  
                  asnLifecycleBeanList = lifecycleUtility.addAsnLifecycleBean(Ponum);
                  httpServletRequest.getSession(false).setAttribute(AppConstants.LFC_SES_ASN_LIST,asnLifecycleBeanList);
                  
               //  System.out.println("ASN Length--->"+asnLifecycleBeanList.size());
                 
                 
                 /** INVOICE **/
               //  System.out.println("INVOICE start");
                  
                  invoiceLifecycleBeanList = lifecycleUtility.addInvoiceLifecycleBean(Ponum);
                  httpServletRequest.getSession(false).setAttribute(AppConstants.LFC_SES_INVOICE_LIST,invoiceLifecycleBeanList);
                  
               //  System.out.println("INVOICE Length--->"+invoiceLifecycleBeanList.size());
                 
                 /** PAYMENT **/
                // System.out.println("PAYMENT start");
                  
                  PaymentLifecycleBeanList = lifecycleUtility.addPaymentLifecycleBean(Ponum);
                  httpServletRequest.getSession(false).setAttribute(AppConstants.LFC_SES_PAYMENT_LIST,PaymentLifecycleBeanList);
                  
               //  System.out.println("PAYMENT Length--->"+invoiceLifecycleBeanList.size());
                 
                 
                 
                /* if(lifecycleBeans.getPoLifecycleBean().getStatus().equalsIgnoreCase("success")){
                         lifecycleBeans.setAsnLifecycleBean(lifecycleUtility.addAsnLifecycleBean(Ponum));
                       
                         if(lifecycleBeans.getAsnLifecycleBean().getRes().equals("1") && lifecycleBeans.getAsnLifecycleBean().getStatus().equalsIgnoreCase("success")){
                                 lifecycleBeans.setInvoiceLifecycleBean(lifecycleUtility.addInvoiceLifecycleBean(Ponum));
                                 System.out.println("INVStatus--->"+lifecycleBeans.getPoLifecycleBean().getStatus());
                                 
                                 if(lifecycleBeans.getInvoiceLifecycleBean().getRes().equals("1") && lifecycleBeans.getInvoiceLifecycleBean().getStatus().equalsIgnoreCase("success")){
                                     lifecycleBeans.setPaymentLifecycleBean(lifecycleUtility.addPaymentLifecycleBean(Ponum));
                                     
                                     
                                    // System.out.println("PayStatus--->"+lifecycleBeans.getPoLifecycleBean().getStatus());
                                 }else{
                                     //System.out.println("In else 0");
                                     lifecycleBeans.getPaymentLifecycleBean().setRes("0");
                                 }
                         }else{
                            // System.out.println("In else 1");
                             lifecycleBeans.getInvoiceLifecycleBean().setRes("0");
                             lifecycleBeans.getPaymentLifecycleBean().setRes("0");
                         }
                 }else{
                    // System.out.println("In else 2");
                     lifecycleBeans.getAsnLifecycleBean().setRes("0");
                     lifecycleBeans.getInvoiceLifecycleBean().setRes("0");
                     lifecycleBeans.getPaymentLifecycleBean().setRes("0");
                     
                 }*/
                 
                
               // return lifecycleBeans;
            }

}
