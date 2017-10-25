/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.util;

import com.mss.ediscv.lfc.PoLifecycleBean;
import com.mss.ediscv.lfc.AsnLifecycleBean;
import com.mss.ediscv.lfc.InvoiceLifecycleBean;
import com.mss.ediscv.lfc.PaymentLifecycleBean;

import com.mss.ediscv.util.ConnectionProvider;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author miracle
 */
public class LifecycleUtility {
    
    private static LifecycleUtility _instance;
    
    public static LifecycleUtility getInstance() {

		if (_instance == null) {
			_instance = new LifecycleUtility();
		}
		return _instance;
	}
  
        private PoLifecycleBean poLifecycleBean;
        private AsnLifecycleBean asnLifecycleBean;
        private InvoiceLifecycleBean invoiceLifecycleBean;
        private PaymentLifecycleBean paymentLifecycleBean;
        
        /**new **/
        
        private ArrayList<PoLifecycleBean> poLifecycleBeanList;
        private ArrayList<AsnLifecycleBean> asnLifecycleBeanList;
        private ArrayList<InvoiceLifecycleBean> invoiceLifecycleBeanList;
        private ArrayList<PaymentLifecycleBean> PaymentLifecycleBeanList;
       
        Connection connection = null;
        Statement statement = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	CallableStatement callableStatement = null;

        // public PoLifecycleBean addPoLifeCycleBean(String poNumber) throws ServiceLocatorException 
         public ArrayList<PoLifecycleBean> addPoLifeCycleBean(String poNumber) throws ServiceLocatorException 
         {
                   
                   
                   poLifecycleBeanList = new ArrayList<PoLifecycleBean>();
                   
                   StringBuffer lifeCycleQuery = new StringBuffer();
                   String poNum = poNumber;
                 //  System.out.println("IN PO LFC");
                  // poLifecycleBean.setRes("0");
          
                  lifeCycleQuery.append("select FILES.FILE_ID, FILES.FILE_TYPE, "
                          + "FILES.TRANSACTION_TYPE, FILES.DIRECTION,FILES.DATE_TIME_RECEIVED, "
                          + "FILES.ST_CONTROL_NUMBER, FILES.GS_CONTROL_NUMBER,FILES.SENDER_ID, "
                          + "FILES.RECEIVER_ID, FILES.STATUS, PO.SAP_IDOC_NUMBER, "
                          + "PO.ISA_CONTROL_NUMBER,PO.PO_NUMBER ,PO.ORDER_DATE, "
                          + "PO.PO_VALUE,PO.ORDER_STATUS,PO.SO_NUMBER,"
                          + "PO.ITEM_QTY,FILES.ACK_STATUS,"
                          +" FILES.PRE_TRANS_FILEPATH,FILES.POST_TRANS_FILEPATH,"
                          + "FILES.ORG_FILEPATH,FILES.REPROCESSSTATUS,"
                          + "FILES.ACK_FILE_ID as ACK_FILE_ID "
                          + "FROM PO LEFT OUTER JOIN FILES ON "
                          + "(PO.PO_NUMBER = FILES.PRI_KEY_VAL AND PO.FILE_ID = FILES.FILE_ID) "
                          + "WHERE PO.PO_NUMBER LIKE '%"+poNum+"%'"
                          +" ORDER BY FILES.DATE_TIME_RECEIVED"); 
                  
                  
                  
                 System.out.println("Po LIFE CYCLE query--->"+lifeCycleQuery.toString());
                 
                    String searchQuery = lifeCycleQuery.toString();
                 try {
                        connection = ConnectionProvider.getInstance().getConnection();
                        statement = connection.createStatement();
                        resultSet=statement.executeQuery(searchQuery);
                        
                        
                        while(resultSet.next()) {
                            poLifecycleBean = new PoLifecycleBean();
                            
                            poLifecycleBean.setFileType(resultSet.getString("FILE_TYPE"));
                            poLifecycleBean.setTranType(resultSet.getString("TRANSACTION_TYPE"));
                            poLifecycleBean.setSenderId(resultSet.getString("SENDER_ID"));
                            poLifecycleBean.setRecId(resultSet.getString("RECEIVER_ID"));
                            poLifecycleBean.setDirection(resultSet.getString("DIRECTION"));
                            poLifecycleBean.setDatetimeRec(resultSet.getTimestamp("DATE_TIME_RECEIVED"));
                            poLifecycleBean.setStatus(resultSet.getString("STATUS"));   
                            poLifecycleBean.setPoNumber(resultSet.getString("PO_NUMBER"));  
                            
                            poLifecycleBean.setFileId(resultSet.getString("FILE_ID"));
                            poLifecycleBean.setStCtrlNum(resultSet.getString("ST_CONTROL_NUMBER"));
                            poLifecycleBean.setGsCtrlNum(resultSet.getString("GS_CONTROL_NUMBER"));
                            
                            if(resultSet.getString("SAP_IDOC_Number")!=null && !"".equals(resultSet.getString("SAP_IDOC_Number"))){ 
                            poLifecycleBean.setSapIdocNum(resultSet.getString("SAP_IDOC_NUMBER"));
                            }else{
                               poLifecycleBean.setSapIdocNum("0"); 
                            }
                            
                            
                            poLifecycleBean.setPreFile(resultSet.getString("PRE_TRANS_FILEPATH"));
                            poLifecycleBean.setPostTranFile(resultSet.getString("POST_TRANS_FILEPATH"));
                            poLifecycleBean.setOrgFile(resultSet.getString("ORG_FILEPATH"));
                            poLifecycleBean.setAckFile(resultSet.getString("ACK_FILE_ID"));
                            
                            poLifecycleBean.setPodate(resultSet.getDate("ORDER_DATE").toString());
                            poLifecycleBean.setPoValue(resultSet.getString("PO_VALUE"));
                            poLifecycleBean.setPoStatus(resultSet.getString("ORDER_STATUS"));
                            
                            if(resultSet.getString("SO_NUMBER")!=null && !"".equals(resultSet.getString("SO_NUMBER"))){
                            poLifecycleBean.setSoNumber(resultSet.getString("SO_NUMBER"));
                            }else{
                               poLifecycleBean.setSoNumber("0"); 
                            }
                            
                            poLifecycleBean.setIteamQty(resultSet.getString("ITEM_QTY"));
                            poLifecycleBean.setAckStatus(resultSet.getString("ACK_STATUS"));
                            
                            poLifecycleBean.setBolNumber("0");
                            poLifecycleBean.setIsaDate("0");
                            poLifecycleBean.setIsaTime("0");
                            poLifecycleBean.setInvAmt("0");
                            poLifecycleBean.setChequeNum("0");
                            poLifecycleBean.setIsaCtrlNum("0");
                            poLifecycleBean.setAsnNumber("0");
                            poLifecycleBean.setInvNumber("0");
                            poLifecycleBean.setRes("1");
                            
                            poLifecycleBean.setReProcessStatus(resultSet.getString("REPROCESSSTATUS"));
                            
                            poLifecycleBeanList.add(poLifecycleBean);
                            
                        }
                       // lifecycleBeans.setPoLifecycleBean(poLifecycleBean);
               
                        
                 }   
                 catch (SQLException e) {
			//System.out.println("I am in catch block coming in IMpl");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception ex){
			//System.out.println("hi"+ex.getMessage());
		}finally{
	           try{
	               if(resultSet!=null){
	                   resultSet.close();
	                   resultSet = null;
	               }
	               if(statement!=null){
	                   statement.close();
	                   statement = null;
	               }
	               
	               if(connection != null){
	                   connection.close();
	                   connection = null;
	               }
	           }catch (SQLException se){
	               throw new ServiceLocatorException(se);
	           }
	       }
                 
               //  System.out.println("Size in LFC PO Action --->"+poLifecycleBeanList.size());
               return poLifecycleBeanList;
         }
         
         
          public ArrayList<AsnLifecycleBean> addAsnLifecycleBean(String poNumber) throws ServiceLocatorException 
         {
                   asnLifecycleBeanList = new ArrayList<AsnLifecycleBean>();
                   StringBuffer lifeCycleQuery = new StringBuffer();
                  String poNum = poNumber;
                 // System.out.println("LifeCycleUtill ASN---->"+poNum);
                 // asnLifecycleBean.setRes("0");
                  lifeCycleQuery.append("select ASN.ASN_NUMBER,FILES.FILE_ID,"
                          + "FILES.FILE_TYPE, FILES.TRANSACTION_TYPE, FILES.DIRECTION,"
                          + "FILES.DATE_TIME_RECEIVED, FILES.ST_CONTROL_NUMBER, "
                          + "FILES.GS_CONTROL_NUMBER,FILES.SENDER_ID, FILES.RECEIVER_ID, "
                          + "FILES.STATUS, ASN.PO_NUMBER ,"
                          +" FILES.PRE_TRANS_FILEPATH,FILES.POST_TRANS_FILEPATH,ASN.ISA_NUMBER,ASN.ISA_DATE,ASN.ISA_TIME,ASN.BOL_NUMBER,"
                          + "FILES.ORG_FILEPATH,FILES.ACK_STATUS,FILES.REPROCESSSTATUS,"
                          + "FILES.ACK_FILE_ID as ACK_FILE_ID "
                          + "from ASN LEFT OUTER JOIN FILES ON "
                          + "(ASN.FILE_ID=FILES.FILE_ID) WHERE ASN.PO_NUMBER LIKE '%"+poNum+"%'"
                          +" ORDER BY FILES.DATE_TIME_RECEIVED"); 
                  
                   
                            
		
                    System.out.println("ASN lifeCycleQuery-->"+lifeCycleQuery.toString());
                    String searchQuery = lifeCycleQuery.toString();

                 
                 try {
                        connection = ConnectionProvider.getInstance().getConnection();
                        statement = connection.createStatement();
                        resultSet=statement.executeQuery(searchQuery);
                        
                      
                        
                        while(resultSet.next()) {
                            asnLifecycleBean = new AsnLifecycleBean();
                            
                            asnLifecycleBean.setFileType(resultSet.getString("FILE_TYPE"));
                            asnLifecycleBean.setTranType(resultSet.getString("TRANSACTION_TYPE"));
                            asnLifecycleBean.setSenderId(resultSet.getString("SENDER_ID"));
                            asnLifecycleBean.setRecId(resultSet.getString("RECEIVER_ID"));
                            asnLifecycleBean.setDirection(resultSet.getString("DIRECTION"));
                            asnLifecycleBean.setDatetimeRec(resultSet.getTimestamp("DATE_TIME_RECEIVED"));
                            asnLifecycleBean.setStatus(resultSet.getString("STATUS"));   
                            asnLifecycleBean.setPoNumber(resultSet.getString("PO_NUMBER"));  
                            asnLifecycleBean.setAsnNumber(resultSet.getString("ASN_NUMBER"));
                            asnLifecycleBean.setPoNumber(resultSet.getString("PO_NUMBER")); 
                            asnLifecycleBean.setAckStatus(resultSet.getString("ACK_STATUS"));
                            
                           // ,ASN.ISA_NUMBER,ASN.ISA_DATE,ASN.ISA_TIME,ASN.BOL_NUMBER,"
                            
                            asnLifecycleBean.setBolNumber(resultSet.getString("BOL_NUMBER"));
                            asnLifecycleBean.setIsaDate(resultSet.getString("ISA_DATE"));
                            asnLifecycleBean.setIsaTime(resultSet.getString("ISA_TIME"));
                            asnLifecycleBean.setIsaCtrlNum(resultSet.getString("ISA_NUMBER"));
                            
                            asnLifecycleBean.setFileId(resultSet.getString("FILE_ID"));
                            asnLifecycleBean.setStCtrlNum(resultSet.getString("ST_CONTROL_NUMBER"));
                            asnLifecycleBean.setGsCtrlNum(resultSet.getString("GS_CONTROL_NUMBER"));
                            asnLifecycleBean.setSapIdocNum("0");
                            
                            asnLifecycleBean.setPreFile(resultSet.getString("PRE_TRANS_FILEPATH"));
                            asnLifecycleBean.setPostTranFile(resultSet.getString("POST_TRANS_FILEPATH"));
                            asnLifecycleBean.setOrgFile(resultSet.getString("ORG_FILEPATH"));
                            asnLifecycleBean.setAckFile(resultSet.getString("ACK_FILE_ID"));
                            
                            asnLifecycleBean.setIsaCtrlNum("0");
                            asnLifecycleBean.setInvNumber("0");
                            
                            
                            
                            asnLifecycleBean.setPodate("0");
                            asnLifecycleBean.setPoValue("0");
                            asnLifecycleBean.setPoStatus("0");
                            asnLifecycleBean.setSoNumber("0");
                            asnLifecycleBean.setIteamQty("0");
                            
                            
                            
                            asnLifecycleBean.setInvAmt("0");
                            asnLifecycleBean.setChequeNum("0");
                            
                            asnLifecycleBean.setInvNumber("0");
                            asnLifecycleBean.setRes("1");
                            
                            asnLifecycleBean.setReProcessStatus(resultSet.getString("REPROCESSSTATUS"));
                           asnLifecycleBeanList.add(asnLifecycleBean);
                            
                        }
                       // lifecycleBeans.setAsnLifecycleBean(asnLifecycleBean);
               
                        
                 }   
                 catch (SQLException e) {
			//System.out.println("I am in catch block coming in IMpl");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception ex){
			//System.out.println("hi"+ex.getMessage());
		}finally{
	           try{
	               if(resultSet!=null){
	                   resultSet.close();
	                   resultSet = null;
	               }
	               if(statement!=null){
	                   statement.close();
	                   statement = null;
	               }
	               
	               if(connection != null){
	                   connection.close();
	                   connection = null;
	               }
	           }catch (SQLException se){
	               throw new ServiceLocatorException(se);
	           }
	       }
               return asnLifecycleBeanList;
         }
          
       
          public ArrayList<InvoiceLifecycleBean> addInvoiceLifecycleBean(String poNumber) throws ServiceLocatorException 
         {
                   invoiceLifecycleBeanList = new ArrayList<InvoiceLifecycleBean>();
                   StringBuffer lifeCycleQuery = new StringBuffer();
                  String poNum = poNumber;
                // System.out.println("LifeCycleService impl INVOICE---->"+poNum);
                  //invoiceLifecycleBean.setRes("0");
                  lifeCycleQuery.append("select INVOICE.INVOICE_NUMBER,FILES.FILE_ID, FILES.FILE_TYPE, "
                          + "FILES.TRANSACTION_TYPE, FILES.DIRECTION,"
                          + "FILES.DATE_TIME_RECEIVED, FILES.ST_CONTROL_NUMBER, FILES.GS_CONTROL_NUMBER,"
                          + "FILES.SENDER_ID, FILES.RECEIVER_ID, FILES.STATUS, INVOICE.PO_NUMBER,"
                          +" FILES.PRE_TRANS_FILEPATH,FILES.POST_TRANS_FILEPATH,"
                          + "FILES.ORG_FILEPATH,INVOICE.INVOICE_AMOUNT,INVOICE.INVOICE_DATE,INVOICE.ISA_NUMBER"
                          + ", INVOICE.ISA_DATE,INVOICE.ISA_TIME,FILES.REPROCESSSTATUS,"
                          + "FILES.ACK_FILE_ID as ACK_FILE_ID,FILES.ACK_STATUS "
                          + "from INVOICE LEFT OUTER JOIN "
                          + "FILES ON (INVOICE.FILE_ID=FILES.FILE_ID) WHERE INVOICE.PO_NUMBER LIKE '%"+poNum+"%'"
                          +" ORDER BY FILES.DATE_TIME_RECEIVED");                 
		
                    System.out.println("lINVOICE ifeCycleQuery-->"+lifeCycleQuery.toString());
                    String searchQuery = lifeCycleQuery.toString();

                 
                 try {
                        connection = ConnectionProvider.getInstance().getConnection();
                        statement = connection.createStatement();
                        resultSet=statement.executeQuery(searchQuery);
                         
                        while(resultSet.next()) {
                         invoiceLifecycleBean = new InvoiceLifecycleBean();
                         
                            invoiceLifecycleBean.setFileType(resultSet.getString("FILE_TYPE"));
                            invoiceLifecycleBean.setTranType(resultSet.getString("TRANSACTION_TYPE"));
                            invoiceLifecycleBean.setSenderId(resultSet.getString("SENDER_ID"));
                            invoiceLifecycleBean.setRecId(resultSet.getString("RECEIVER_ID"));
                            invoiceLifecycleBean.setDirection(resultSet.getString("DIRECTION"));
                            invoiceLifecycleBean.setDatetimeRec(resultSet.getTimestamp("DATE_TIME_RECEIVED"));
                            invoiceLifecycleBean.setStatus(resultSet.getString("STATUS"));   
                            invoiceLifecycleBean.setPoNumber(resultSet.getString("PO_NUMBER"));  
                            
                            invoiceLifecycleBean.setFileId(resultSet.getString("FILE_ID"));
                            invoiceLifecycleBean.setStCtrlNum(resultSet.getString("ST_CONTROL_NUMBER"));
                            invoiceLifecycleBean.setGsCtrlNum(resultSet.getString("GS_CONTROL_NUMBER"));
                            invoiceLifecycleBean.setSapIdocNum("0");
                            invoiceLifecycleBean.setIsaCtrlNum(" ");
                            invoiceLifecycleBean.setAsnNumber("0");
                            invoiceLifecycleBean.setInvNumber(resultSet.getString("INVOICE_NUMBER"));
                            invoiceLifecycleBean.setPreFile(resultSet.getString("PRE_TRANS_FILEPATH"));
                            invoiceLifecycleBean.setPostTranFile(resultSet.getString("POST_TRANS_FILEPATH"));
                            invoiceLifecycleBean.setOrgFile(resultSet.getString("ORG_FILEPATH"));
                            invoiceLifecycleBean.setAckFile(resultSet.getString("ACK_FILE_ID"));
                            invoiceLifecycleBean.setAckStatus(resultSet.getString("ACK_STATUS"));
                            invoiceLifecycleBean.setIsaCtrlNum("0");
                            invoiceLifecycleBean.setInvNumber("0");
                            invoiceLifecycleBean.setBolNumber("0");
                            
                          
                            
                            invoiceLifecycleBean.setPodate("0");
                            invoiceLifecycleBean.setPoValue("0");
                            invoiceLifecycleBean.setPoStatus("0");
                            invoiceLifecycleBean.setSoNumber("0");
                            invoiceLifecycleBean.setIteamQty("0");
                            
                            invoiceLifecycleBean.setIsaDate(resultSet.getString("ISA_DATE"));
                            invoiceLifecycleBean.setIsaTime(resultSet.getString("ISA_TIME"));
                            invoiceLifecycleBean.setIsaCtrlNum(resultSet.getString("ISA_NUMBER"));
                            
                            invoiceLifecycleBean.setInvAmt(resultSet.getString("INVOICE_AMOUNT"));
                            invoiceLifecycleBean.setChequeNum("0");
                            invoiceLifecycleBean.setReProcessStatus(resultSet.getString("REPROCESSSTATUS"));
                            
                           // invoiceLifecycleBean.setInvNumber("0");
                            
                            invoiceLifecycleBean.setRes("1");
                            
                           invoiceLifecycleBeanList.add(invoiceLifecycleBean);
                        }
                       // lifecycleBeans.setInvoiceLifecycleBean(invoiceLifecycleBean);
               
                        
                 }   
                 catch (SQLException e) {
			//System.out.println("I am in catch block coming in IMpl");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception ex){
			//System.out.println("hi"+ex.getMessage());
		}finally{
	           try{
	               if(resultSet!=null){
	                   resultSet.close();
	                   resultSet = null;
	               }
	               if(statement!=null){
	                   statement.close();
	                   statement = null;
	               }
	               
	               if(connection != null){
	                   connection.close();
	                   connection = null;
	               }
	           }catch (SQLException se){
	               throw new ServiceLocatorException(se);
	           }
	       }
               return invoiceLifecycleBeanList;
         }
         
          
          public ArrayList<PaymentLifecycleBean> addPaymentLifecycleBean(String poNumber) throws ServiceLocatorException 
         {
                   PaymentLifecycleBeanList = new ArrayList<PaymentLifecycleBean>();
                   
                   StringBuffer lifeCycleQuery = new StringBuffer();
                  String poNum = poNumber;
                 // System.out.println("LifeCycleService impl PAYMENTS---->"+poNum);
                 // paymentLifecycleBean.setRes("1");
                  lifeCycleQuery.append("select FILES.FILE_ID, FILES.FILE_TYPE, FILES.TRANSACTION_TYPE,"
                          + "FILES.DIRECTION,FILES.DATE_TIME_RECEIVED, FILES.ST_CONTROL_NUMBER, "
                          + "FILES.GS_CONTROL_NUMBER,FILES.SENDER_ID, FILES.RECEIVER_ID, FILES.STATUS, "
                          + "PAYMENT.PO_NUMBER ,"
                          +" FILES.PRE_TRANS_FILEPATH,FILES.POST_TRANS_FILEPATH,"
                          + "FILES.ORG_FILEPATH,FILES.ACK_STATUS,FILES.REPROCESSSTATUS,"
                          + "FILES.ACK_FILE_ID as ACK_FILE_ID, PAYMENT.CHECK_NUMBER "
                          + "from PAYMENT LEFT OUTER JOIN "
                          + " FILES ON (PAYMENT.FILE_ID=FILES.FILE_ID) WHERE PAYMENT.PO_NUMBER LIKE '%"+poNum+"%'"
                          +" ORDER BY FILES.DATE_TIME_RECEIVED"); 
		
                    System.out.println("PAYMENT lifeCycleQuery-->"+lifeCycleQuery.toString());
                    String searchQuery = lifeCycleQuery.toString();

                 
                 try {
                        connection = ConnectionProvider.getInstance().getConnection();
                        statement = connection.createStatement();
                        resultSet=statement.executeQuery(searchQuery);
                        
                      
                        
                        while(resultSet.next()) {
                            paymentLifecycleBean = new PaymentLifecycleBean();
                            paymentLifecycleBean.setFileType(resultSet.getString("FILE_TYPE"));
                            paymentLifecycleBean.setTranType(resultSet.getString("TRANSACTION_TYPE"));
                            paymentLifecycleBean.setSenderId(resultSet.getString("SENDER_ID"));
                            paymentLifecycleBean.setRecId(resultSet.getString("RECEIVER_ID"));
                            paymentLifecycleBean.setDirection(resultSet.getString("DIRECTION"));
                            paymentLifecycleBean.setDatetimeRec(resultSet.getTimestamp("DATE_TIME_RECEIVED"));
                            paymentLifecycleBean.setStatus(resultSet.getString("STATUS"));   
                            paymentLifecycleBean.setPoNumber(resultSet.getString("PO_NUMBER"));  
                            
                            paymentLifecycleBean.setFileId(resultSet.getString("FILE_ID"));
                            paymentLifecycleBean.setStCtrlNum(resultSet.getString("ST_CONTROL_NUMBER"));
                            paymentLifecycleBean.setGsCtrlNum(resultSet.getString("GS_CONTROL_NUMBER"));
                            paymentLifecycleBean.setSapIdocNum("0");
                            paymentLifecycleBean.setAckStatus(resultSet.getString("ACK_STATUS"));
                            paymentLifecycleBean.setAsnNumber("0");
                            
                            
                            paymentLifecycleBean.setPreFile(resultSet.getString("PRE_TRANS_FILEPATH"));
                            paymentLifecycleBean.setPostTranFile(resultSet.getString("POST_TRANS_FILEPATH"));
                            paymentLifecycleBean.setOrgFile(resultSet.getString("ORG_FILEPATH"));
                            paymentLifecycleBean.setAckFile(resultSet.getString("ACK_FILE_ID"));
                            
                             paymentLifecycleBean.setIsaCtrlNum("0");
                            paymentLifecycleBean.setInvNumber("0");
                            
                            
                          
                            paymentLifecycleBean.setBolNumber("0");
                            paymentLifecycleBean.setPodate("0");
                            paymentLifecycleBean.setPoValue("0");
                            paymentLifecycleBean.setPoStatus("0");
                            paymentLifecycleBean.setSoNumber("0");
                            paymentLifecycleBean.setIteamQty("0");
                            
                            paymentLifecycleBean.setIsaDate("0");
                            paymentLifecycleBean.setIsaTime("0");
                            paymentLifecycleBean.setIsaCtrlNum("0");
                            
                            paymentLifecycleBean.setInvAmt("0");
                            paymentLifecycleBean.setChequeNum(resultSet.getString("CHECK_NUMBER"));
                               paymentLifecycleBean.setReProcessStatus(resultSet.getString("REPROCESSSTATUS"));
                            
                            paymentLifecycleBean.setRes("1");
                            
                            PaymentLifecycleBeanList.add(paymentLifecycleBean);
                        }
                        //lifecycleBeans.setPaymentLifecycleBean(paymentLifecycleBean);
               
                        
                 }   
                 catch (SQLException e) {
			//System.out.println("I am in catch block coming in IMpl");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception ex){
			//System.out.println("hi"+ex.getMessage());
		}finally{
	           try{
	               if(resultSet!=null){
	                   resultSet.close();
	                   resultSet = null;
	               }
	               if(statement!=null){
	                   statement.close();
	                   statement = null;
	               }
	               
	               if(connection != null){
	                   connection.close();
	                   connection = null;
	               }
	           }catch (SQLException se){
	               throw new ServiceLocatorException(se);
	           }
	       }
               return PaymentLifecycleBeanList;
         }

          
          /**
           * Detail info 
           * 
           */
          
          public PoLifecycleBean getLFCPoDetails(String poNumber,String fileID) throws ServiceLocatorException 
          {
                   //poLifecycleBeanList = new ArrayList<PoLifecycleBean>();
                   poLifecycleBean = new PoLifecycleBean();
                   
                   StringBuffer lifeCycleQuery = new StringBuffer();
                   String poNum = poNumber;
                  // System.out.println("IN PO LFC");
                  // poLifecycleBean.setRes("0");
          
                  lifeCycleQuery.append("select FILES.FILE_ID, FILES.FILE_TYPE, "
                          + "FILES.TRANSACTION_TYPE, FILES.DIRECTION,FILES.DATE_TIME_RECEIVED, "
                          + "FILES.ST_CONTROL_NUMBER, FILES.GS_CONTROL_NUMBER,FILES.SENDER_ID, "
                          + "FILES.RECEIVER_ID, FILES.STATUS, PO.SAP_IDOC_NUMBER, "
                          + "FILES.ISA_NUMBER, FILES.ISA_DATE, FILES.ISA_TIME, "
                          + "PO.PO_NUMBER ,PO.ORDER_DATE, "
                          + "PO.PO_VALUE,PO.ORDER_STATUS,PO.SO_NUMBER,"
                          + "PO.ITEM_QTY,FILES.ACK_STATUS,"
                          + "TP1.NAME as SENDER_NAME,TP2.NAME as RECEIVER_NAME,"
                          +" FILES.PRE_TRANS_FILEPATH,FILES.POST_TRANS_FILEPATH,"
                          + "FILES.ORG_FILEPATH,"
                          + "FILES.ACK_FILE_ID as ACK_FILE_ID "
                          + "FROM PO LEFT OUTER JOIN FILES ON "
                          + "(PO.PO_NUMBER = FILES.PRI_KEY_VAL AND PO.FILE_ID = FILES.FILE_ID) "
                          +"LEFT OUTER JOIN TP TP1 ON (TP1.ID=FILES.SENDER_ID) LEFT OUTER JOIN TP TP2 ON (TP2.ID = FILES.RECEIVER_ID) "
                          + "WHERE FLOWFLAG like 'M' AND PO.PO_NUMBER LIKE '%"+poNum+"%' AND FILES.FILE_ID LIKE '%"+fileID+"%'"
                          +" ORDER BY FILES.DATE_TIME_RECEIVED"); 
                  
                  
                  
                 //System.out.println("PO LFC Details query--->"+lifeCycleQuery.toString());
                 
                    String searchQuery = lifeCycleQuery.toString();
                 try {
                        connection = ConnectionProvider.getInstance().getConnection();
                        statement = connection.createStatement();
                        resultSet=statement.executeQuery(searchQuery);
                        
                        
                        while(resultSet.next()) {
                            
                            poLifecycleBean.setFileType(resultSet.getString("FILE_TYPE"));
                            poLifecycleBean.setTranType(resultSet.getString("TRANSACTION_TYPE"));
                            poLifecycleBean.setSenderId(resultSet.getString("SENDER_ID"));
                            poLifecycleBean.setRecId(resultSet.getString("RECEIVER_ID"));
                            poLifecycleBean.setDirection(resultSet.getString("DIRECTION"));
                            poLifecycleBean.setDatetimeRec(resultSet.getTimestamp("DATE_TIME_RECEIVED"));
                            poLifecycleBean.setStatus(resultSet.getString("STATUS"));   
                            poLifecycleBean.setPoNumber(resultSet.getString("PO_NUMBER"));  
                            
                            poLifecycleBean.setSenName(resultSet.getString("SENDER_NAME"));
                            poLifecycleBean.setRecName(resultSet.getString("RECEIVER_NAME"));
                            
                            poLifecycleBean.setFileId(resultSet.getString("FILE_ID"));
                            poLifecycleBean.setStCtrlNum(resultSet.getString("ST_CONTROL_NUMBER"));
                            poLifecycleBean.setGsCtrlNum(resultSet.getString("GS_CONTROL_NUMBER"));
                            
                            if(resultSet.getString("SAP_IDOC_NUMBER") != null && !"".equals(resultSet.getString("SAP_IDOC_NUMBER"))){
                            poLifecycleBean.setSapIdocNum(resultSet.getString("SAP_IDOC_NUMBER"));
                            }
                            else {
                                poLifecycleBean.setSapIdocNum("0");
                            }
                            
                            poLifecycleBean.setPreFile(resultSet.getString("PRE_TRANS_FILEPATH"));
                            poLifecycleBean.setPostTranFile(resultSet.getString("POST_TRANS_FILEPATH"));
                            poLifecycleBean.setOrgFile(resultSet.getString("ORG_FILEPATH"));
                            poLifecycleBean.setAckFile(resultSet.getString("ACK_FILE_ID"));
                            
                            poLifecycleBean.setPodate(resultSet.getDate("ORDER_DATE").toString());
                            poLifecycleBean.setPoValue(resultSet.getString("PO_VALUE"));
                            poLifecycleBean.setPoStatus(resultSet.getString("ORDER_STATUS"));
                            if(resultSet.getString("SO_NUMBER") != null && !"".equals(resultSet.getString("SO_NUMBER")))
                            {
                            poLifecycleBean.setSoNumber(resultSet.getString("SO_NUMBER"));
                            }else {
                                poLifecycleBean.setSoNumber("0");
                            }
                            poLifecycleBean.setIteamQty(resultSet.getString("ITEM_QTY"));
                            poLifecycleBean.setAckStatus(resultSet.getString("ACK_STATUS"));
                            
                            poLifecycleBean.setBolNumber("0");
                            poLifecycleBean.setIsaDate(resultSet.getString("ISA_DATE"));
                            poLifecycleBean.setIsaTime(resultSet.getString("ISA_TIME"));
                            poLifecycleBean.setIsaCtrlNum(resultSet.getString("ISA_NUMBER"));
                            poLifecycleBean.setInvAmt("0");
                            poLifecycleBean.setChequeNum("0");
                            
                            poLifecycleBean.setAsnNumber("0");
                            poLifecycleBean.setInvNumber("0");
                            poLifecycleBean.setRes("1");
                          
                        }
                 
                 }   
                 catch (SQLException e) {
			e.printStackTrace();
		}catch(Exception ex){
			//System.out.println("hi"+ex.getMessage());
		}finally{
	           try{
	               if(resultSet!=null){
	                   resultSet.close();
	                   resultSet = null;
	               }
	               if(statement!=null){
	                   statement.close();
	                   statement = null;
	               }
	               
	               if(connection != null){
	                   connection.close();
	                   connection = null;
	               }
	           }catch (SQLException se){
	               throw new ServiceLocatorException(se);
	           }
	       }
              
                 return poLifecycleBean;
         }
          
         public AsnLifecycleBean getLFCAsnDetails(String poNumber,String fileId) throws ServiceLocatorException 
         {
                   asnLifecycleBean = new AsnLifecycleBean();
                   
                   StringBuffer lifeCycleQuery = new StringBuffer();
                  String poNum = poNumber;
                 // System.out.println("LifeCycleUtill ASN---->"+poNum);
                 // asnLifecycleBean.setRes("0");
                  lifeCycleQuery.append("select ASN.ASN_NUMBER,FILES.FILE_ID,"
                          + "FILES.FILE_TYPE, FILES.TRANSACTION_TYPE, FILES.DIRECTION,"
                          + "FILES.DATE_TIME_RECEIVED, FILES.ST_CONTROL_NUMBER, "
                          + "FILES.GS_CONTROL_NUMBER,FILES.SENDER_ID, FILES.RECEIVER_ID, "
                          + "FILES.STATUS, ASN.PO_NUMBER ,"
                          + "TP1.NAME as SENDER_NAME,TP2.NAME as RECEIVER_NAME,"
                          +" FILES.PRE_TRANS_FILEPATH,FILES.POST_TRANS_FILEPATH,FILES.ISA_NUMBER,FILES.ISA_DATE,FILES.ISA_TIME,ASN.BOL_NUMBER,"
                          + "FILES.ORG_FILEPATH,FILES.ACK_STATUS,"
                          + "FILES.ACK_FILE_ID as ACK_FILE_ID "
                          + "from ASN LEFT OUTER JOIN FILES ON "
                          + "(ASN.FILE_ID=FILES.FILE_ID) "
                          +"LEFT OUTER JOIN TP TP1 ON (TP1.ID=FILES.SENDER_ID) LEFT OUTER JOIN TP TP2 ON (TP2.ID = FILES.RECEIVER_ID) "
                          + "WHERE FLOWFLAG like 'M' AND ASN.PO_NUMBER LIKE '%"+poNum+"%' AND FILES.FILE_ID LIKE '%"+fileId+"%'"
                          +" ORDER BY FILES.DATE_TIME_RECEIVED"); 
                  
                   
                            
		
                   // System.out.println("ASN lifeCycle Details Query-->"+lifeCycleQuery.toString());
                    String searchQuery = lifeCycleQuery.toString();

                 
                 try {
                        connection = ConnectionProvider.getInstance().getConnection();
                        statement = connection.createStatement();
                        resultSet=statement.executeQuery(searchQuery);
                        
                      
                        
                        while(resultSet.next()) {
                            
                            
                            asnLifecycleBean.setFileType(resultSet.getString("FILE_TYPE"));
                            asnLifecycleBean.setTranType(resultSet.getString("TRANSACTION_TYPE"));
                            asnLifecycleBean.setSenderId(resultSet.getString("SENDER_ID"));
                            asnLifecycleBean.setRecId(resultSet.getString("RECEIVER_ID"));
                            asnLifecycleBean.setDirection(resultSet.getString("DIRECTION"));
                            asnLifecycleBean.setDatetimeRec(resultSet.getTimestamp("DATE_TIME_RECEIVED"));
                            asnLifecycleBean.setStatus(resultSet.getString("STATUS"));   
                            asnLifecycleBean.setPoNumber(resultSet.getString("PO_NUMBER"));  
                            asnLifecycleBean.setAsnNumber(resultSet.getString("ASN_NUMBER"));
                            asnLifecycleBean.setPoNumber(resultSet.getString("PO_NUMBER")); 
                            asnLifecycleBean.setAckStatus(resultSet.getString("ACK_STATUS"));
                            
                           // ,ASN.ISA_NUMBER,ASN.ISA_DATE,ASN.ISA_TIME,ASN.BOL_NUMBER,"
                            
                            asnLifecycleBean.setBolNumber(resultSet.getString("BOL_NUMBER"));
                            asnLifecycleBean.setIsaDate(resultSet.getString("ISA_DATE"));
                            asnLifecycleBean.setIsaTime(resultSet.getString("ISA_TIME"));
                            asnLifecycleBean.setIsaCtrlNum(resultSet.getString("ISA_NUMBER"));
                            
                            asnLifecycleBean.setFileId(resultSet.getString("FILE_ID"));
                            asnLifecycleBean.setStCtrlNum(resultSet.getString("ST_CONTROL_NUMBER"));
                            asnLifecycleBean.setGsCtrlNum(resultSet.getString("GS_CONTROL_NUMBER"));
                            asnLifecycleBean.setSapIdocNum("0");
                            
                            asnLifecycleBean.setPreFile(resultSet.getString("PRE_TRANS_FILEPATH"));
                            asnLifecycleBean.setPostTranFile(resultSet.getString("POST_TRANS_FILEPATH"));
                            asnLifecycleBean.setOrgFile(resultSet.getString("ORG_FILEPATH"));
                            asnLifecycleBean.setAckFile(resultSet.getString("ACK_FILE_ID"));
                            
                            asnLifecycleBean.setIsaCtrlNum(resultSet.getString("ISA_NUMBER"));
                            asnLifecycleBean.setInvNumber("0");
                            
                            asnLifecycleBean.setSenName(resultSet.getString("SENDER_NAME"));
                            asnLifecycleBean.setRecName(resultSet.getString("RECEIVER_NAME"));
                            
                            asnLifecycleBean.setPodate("0");
                            asnLifecycleBean.setPoValue("0");
                            asnLifecycleBean.setPoStatus("0");
                            asnLifecycleBean.setSoNumber("0");
                            asnLifecycleBean.setIteamQty("0");
                            
                            
                            
                            asnLifecycleBean.setInvAmt("0");
                            asnLifecycleBean.setChequeNum("0");
                            
                            asnLifecycleBean.setInvNumber("0");
                            asnLifecycleBean.setRes("1");
                            
                          
                            
                        }
                       // lifecycleBeans.setAsnLifecycleBean(asnLifecycleBean);
               
                        
                 }   
                 catch (SQLException e) {
			//System.out.println("I am in catch block coming in IMpl");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception ex){
			//System.out.println("hi"+ex.getMessage());
		}finally{
	           try{
	               if(resultSet!=null){
	                   resultSet.close();
	                   resultSet = null;
	               }
	               if(statement!=null){
	                   statement.close();
	                   statement = null;
	               }
	               
	               if(connection != null){
	                   connection.close();
	                   connection = null;
	               }
	           }catch (SQLException se){
	               throw new ServiceLocatorException(se);
	           }
	       }
               return asnLifecycleBean;
         }
          
         public InvoiceLifecycleBean getLFCInvoiceDetails(String poNumber,String fileId) throws ServiceLocatorException 
         {
                   invoiceLifecycleBean = new InvoiceLifecycleBean();
                   StringBuffer lifeCycleQuery = new StringBuffer();
                  String poNum = poNumber;
               //  System.out.println("LifeCycleService impl INVOICE---->"+poNum);
                  //invoiceLifecycleBean.setRes("0");
                  lifeCycleQuery.append("select INVOICE.INVOICE_NUMBER,FILES.FILE_ID, FILES.FILE_TYPE, "
                          + "FILES.TRANSACTION_TYPE, FILES.DIRECTION,"
                          + "FILES.DATE_TIME_RECEIVED, FILES.ST_CONTROL_NUMBER, FILES.GS_CONTROL_NUMBER,"
                          + "FILES.SENDER_ID, FILES.RECEIVER_ID, FILES.STATUS, INVOICE.PO_NUMBER,"
                          +" FILES.PRE_TRANS_FILEPATH,FILES.POST_TRANS_FILEPATH,"
                          + "FILES.ORG_FILEPATH,INVOICE.INVOICE_AMOUNT,INVOICE.INVOICE_DATE,FILES.ISA_NUMBER"
                          + ", FILES.ISA_DATE,FILES.ISA_TIME,"
                          + "TP1.NAME as SENDER_NAME,TP2.NAME as RECEIVER_NAME,"
                          + "FILES.ACK_FILE_ID as ACK_FILE_ID,FILES.ACK_STATUS "
                          + "from INVOICE LEFT OUTER JOIN "
                          + "FILES ON (INVOICE.FILE_ID=FILES.FILE_ID) "
                          +"LEFT OUTER JOIN TP TP1 ON (TP1.ID=FILES.SENDER_ID) LEFT OUTER JOIN TP TP2 ON (TP2.ID = FILES.RECEIVER_ID) "
                          + "WHERE FLOWFLAG like 'M' AND INVOICE.PO_NUMBER LIKE '%"+poNum+"%' AND FILES.FILE_ID LIKE '%"+fileId+"%'"
                          +" ORDER BY FILES.DATE_TIME_RECEIVED");                 
		
                    //System.out.println("lINVOICE LifeCycle Details Query-->"+lifeCycleQuery.toString());
                    String searchQuery = lifeCycleQuery.toString();

                 
                 try {
                        connection = ConnectionProvider.getInstance().getConnection();
                        statement = connection.createStatement();
                        resultSet=statement.executeQuery(searchQuery);
                         
                        while(resultSet.next()) {
                         
                         
                            invoiceLifecycleBean.setFileType(resultSet.getString("FILE_TYPE"));
                            invoiceLifecycleBean.setTranType(resultSet.getString("TRANSACTION_TYPE"));
                            invoiceLifecycleBean.setSenderId(resultSet.getString("SENDER_ID"));
                            invoiceLifecycleBean.setRecId(resultSet.getString("RECEIVER_ID"));
                            invoiceLifecycleBean.setDirection(resultSet.getString("DIRECTION"));
                            invoiceLifecycleBean.setDatetimeRec(resultSet.getTimestamp("DATE_TIME_RECEIVED"));
                            invoiceLifecycleBean.setStatus(resultSet.getString("STATUS"));   
                            invoiceLifecycleBean.setPoNumber(resultSet.getString("PO_NUMBER"));  
                            
                            invoiceLifecycleBean.setFileId(resultSet.getString("FILE_ID"));
                            invoiceLifecycleBean.setStCtrlNum(resultSet.getString("ST_CONTROL_NUMBER"));
                            invoiceLifecycleBean.setGsCtrlNum(resultSet.getString("GS_CONTROL_NUMBER"));
                            invoiceLifecycleBean.setSapIdocNum("0");
                            invoiceLifecycleBean.setIsaCtrlNum("0");
                            invoiceLifecycleBean.setAsnNumber("0");
                            invoiceLifecycleBean.setInvNumber(resultSet.getString("INVOICE_NUMBER"));
                            invoiceLifecycleBean.setPreFile(resultSet.getString("PRE_TRANS_FILEPATH"));
                            invoiceLifecycleBean.setPostTranFile(resultSet.getString("POST_TRANS_FILEPATH"));
                            invoiceLifecycleBean.setOrgFile(resultSet.getString("ORG_FILEPATH"));
                            invoiceLifecycleBean.setAckFile(resultSet.getString("ACK_FILE_ID"));
                            invoiceLifecycleBean.setAckStatus(resultSet.getString("ACK_STATUS"));
                            invoiceLifecycleBean.setIsaCtrlNum("0");
                            invoiceLifecycleBean.setInvNumber("0");
                            invoiceLifecycleBean.setBolNumber("0");
                            
                            invoiceLifecycleBean.setSenName(resultSet.getString("SENDER_NAME"));
                            invoiceLifecycleBean.setRecName(resultSet.getString("RECEIVER_NAME"));
                          
                            
                            invoiceLifecycleBean.setPodate("0");
                            invoiceLifecycleBean.setPoValue("0");
                            invoiceLifecycleBean.setPoStatus("0");
                            invoiceLifecycleBean.setSoNumber("0");
                            invoiceLifecycleBean.setIteamQty("0");
                            
                            invoiceLifecycleBean.setIsaDate(resultSet.getString("ISA_DATE"));
                            invoiceLifecycleBean.setIsaTime(resultSet.getString("ISA_TIME"));
                            invoiceLifecycleBean.setIsaCtrlNum(resultSet.getString("ISA_NUMBER"));
                            
                            invoiceLifecycleBean.setInvAmt(resultSet.getString("INVOICE_AMOUNT"));
                            invoiceLifecycleBean.setChequeNum("0");
                            
                           // invoiceLifecycleBean.setInvNumber("0");
                            
                            invoiceLifecycleBean.setRes("1");
                            
                          
                        }
                       // lifecycleBeans.setInvoiceLifecycleBean(invoiceLifecycleBean);
               
                        
                 }   
                 catch (SQLException e) {
			//System.out.println("I am in catch block coming in IMpl");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception ex){
			//System.out.println("hi"+ex.getMessage());
		}finally{
	           try{
	               if(resultSet!=null){
	                   resultSet.close();
	                   resultSet = null;
	               }
	               if(statement!=null){
	                   statement.close();
	                   statement = null;
	               }
	               
	               if(connection != null){
	                   connection.close();
	                   connection = null;
	               }
	           }catch (SQLException se){
	               throw new ServiceLocatorException(se);
	           }
	       }
               return invoiceLifecycleBean;
         }
         
          public PaymentLifecycleBean getLFCPaymentDetails(String poNumber,String fileId) throws ServiceLocatorException 
         {
                   paymentLifecycleBean = new PaymentLifecycleBean();
                   
                   StringBuffer lifeCycleQuery = new StringBuffer();
                  String poNum = poNumber;
               //   System.out.println("LifeCycleService impl PAYMENTS---->"+poNum);
                 // paymentLifecycleBean.setRes("1");
                  lifeCycleQuery.append("select FILES.FILE_ID, FILES.FILE_TYPE, FILES.TRANSACTION_TYPE,"
                          + "FILES.DIRECTION,FILES.DATE_TIME_RECEIVED, FILES.ST_CONTROL_NUMBER, "
                          + "FILES.GS_CONTROL_NUMBER,FILES.SENDER_ID, FILES.RECEIVER_ID, FILES.STATUS, "
                          + "PAYMENT.PO_NUMBER ,FILES.ISA_NUMBER as ISA_NUMBER,FILES.ISA_DATE as ISA_DATE,FILES.ISA_TIME as ISA_TIME,"
                          +" FILES.PRE_TRANS_FILEPATH,FILES.POST_TRANS_FILEPATH,"
                          + "FILES.ORG_FILEPATH,FILES.ACK_STATUS,"
                          + "TP1.NAME as SENDER_NAME,TP2.NAME as RECEIVER_NAME,"
                          + "FILES.ACK_FILE_ID as ACK_FILE_ID, PAYMENT.CHECK_NUMBER "
                          + "from PAYMENT LEFT OUTER JOIN "
                          + " FILES ON (PAYMENT.FILE_ID=FILES.FILE_ID) "
                          +"LEFT OUTER JOIN TP TP1 ON (TP1.ID=FILES.SENDER_ID) LEFT OUTER JOIN TP TP2 ON (TP2.ID = FILES.RECEIVER_ID) "
                          + " WHERE FLOWFLAG like 'M' AND PAYMENT.PO_NUMBER LIKE '%"+poNum+"%' AND FILES.FILE_ID LIKE '%"+fileId+"%'"
                          +" ORDER BY FILES.DATE_TIME_RECEIVED"); 
		
                   // System.out.println("PAYMENT lifeCycle Details Query-->"+lifeCycleQuery.toString());
                    String searchQuery = lifeCycleQuery.toString();

                 
                 try {
                        connection = ConnectionProvider.getInstance().getConnection();
                        statement = connection.createStatement();
                        resultSet=statement.executeQuery(searchQuery);
                        
                      
                        
                        while(resultSet.next()) {
                            
                            paymentLifecycleBean.setFileType(resultSet.getString("FILE_TYPE"));
                            paymentLifecycleBean.setTranType(resultSet.getString("TRANSACTION_TYPE"));
                            paymentLifecycleBean.setSenderId(resultSet.getString("SENDER_ID"));
                            paymentLifecycleBean.setRecId(resultSet.getString("RECEIVER_ID"));
                            paymentLifecycleBean.setDirection(resultSet.getString("DIRECTION"));
                            paymentLifecycleBean.setDatetimeRec(resultSet.getTimestamp("DATE_TIME_RECEIVED"));
                            paymentLifecycleBean.setStatus(resultSet.getString("STATUS"));   
                            paymentLifecycleBean.setPoNumber(resultSet.getString("PO_NUMBER"));  
                            
                            paymentLifecycleBean.setFileId(resultSet.getString("FILE_ID"));
                            paymentLifecycleBean.setStCtrlNum(resultSet.getString("ST_CONTROL_NUMBER"));
                            paymentLifecycleBean.setGsCtrlNum(resultSet.getString("GS_CONTROL_NUMBER"));
                            paymentLifecycleBean.setSapIdocNum("0");
                            paymentLifecycleBean.setAckStatus(resultSet.getString("ACK_STATUS"));
                            paymentLifecycleBean.setAsnNumber("0");
                            
                            paymentLifecycleBean.setSenName(resultSet.getString("SENDER_NAME"));
                            paymentLifecycleBean.setRecName(resultSet.getString("RECEIVER_NAME"));
                            
                            
                            paymentLifecycleBean.setPreFile(resultSet.getString("PRE_TRANS_FILEPATH"));
                            paymentLifecycleBean.setPostTranFile(resultSet.getString("POST_TRANS_FILEPATH"));
                            paymentLifecycleBean.setOrgFile(resultSet.getString("ORG_FILEPATH"));
                            paymentLifecycleBean.setAckFile(resultSet.getString("ACK_FILE_ID"));
                            
                             paymentLifecycleBean.setIsaCtrlNum(resultSet.getString("ISA_NUMBER"));
                            paymentLifecycleBean.setInvNumber("0");
                            
                            
                          
                            paymentLifecycleBean.setBolNumber("0");
                            paymentLifecycleBean.setPodate("0");
                            paymentLifecycleBean.setPoValue("0");
                            paymentLifecycleBean.setPoStatus("0");
                            paymentLifecycleBean.setSoNumber("0");
                            paymentLifecycleBean.setIteamQty("0");
                            
                            paymentLifecycleBean.setIsaDate(resultSet.getString("ISA_DATE"));
                            paymentLifecycleBean.setIsaTime(resultSet.getString("ISA_TIME"));
                            //paymentLifecycleBean.setIsaCtrlNum("0");
                            
                            paymentLifecycleBean.setInvAmt("0");
                            paymentLifecycleBean.setChequeNum(resultSet.getString("CHECK_NUMBER"));
                            
                            paymentLifecycleBean.setRes("1");
                            
                           
                        }
                        //lifecycleBeans.setPaymentLifecycleBean(paymentLifecycleBean);
               
                        
                 }   
                 catch (SQLException e) {
			//System.out.println("I am in catch block coming in IMpl");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception ex){
			//System.out.println("hi"+ex.getMessage());
		}finally{
	           try{
	               if(resultSet!=null){
	                   resultSet.close();
	                   resultSet = null;
	               }
	               if(statement!=null){
	                   statement.close();
	                   statement = null;
	               }
	               
	               if(connection != null){
	                   connection.close();
	                   connection = null;
	               }
	           }catch (SQLException se){
	               throw new ServiceLocatorException(se);
	           }
	       }
               return paymentLifecycleBean;
         }
}
