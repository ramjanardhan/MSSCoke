/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.documentVisibility;

import com.mss.ediscv.util.AppConstants;
import com.mss.ediscv.util.ConnectionProvider;
import com.mss.ediscv.util.DateUtility;
import com.mss.ediscv.util.ServiceLocatorException;
import com.mss.ediscv.util.WildCardSql;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
/**
 *
 * @author miracle
 */
public class DocumentVisibilityServiceImpl implements DocumentVisibilityService{
       
     Connection connection = null;
        Statement statement = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	CallableStatement callableStatement = null;
        
	String tmp_Recieved_From = "";
	String tmp_Recieved_ToTime = "";
	String strFormat = "yyyy-MM-dd";
	DateFormat myDateFormat = new SimpleDateFormat(strFormat);
	Calendar cal = new GregorianCalendar();
	java.util.Date now = cal.getTime();
	long time = now.getTime();
	java.sql.Date date = new java.sql.Date(time);
        
	int callableStatementUpdateCount;
	private ArrayList<DocumentVisibilityBean> documentList;
	private DocumentVisibilityBean documentVisibilityBean;

	private static Logger logger = Logger.getLogger(DocumentVisibilityServiceImpl.class
			.getName());
        
        
        
        
        
    public ArrayList<DocumentVisibilityBean> buildDocumentQuery(DocumentVisibilityAction documentVisibilityAction,HttpServletRequest httpServletRequest)throws ServiceLocatorException {
            StringBuffer documentSearchQuery = new StringBuffer();
		logger.info("Entered into the :::: LogisticsDocServiceImpl :::: buildPurchaseQuery");
                 
           documentSearchQuery.append("SELECT ID,FILE_ID,FILE_TYPE,TRAN_MESS_TYPE,SENDER_ID,RECEIVER_ID,INTERCHANGE_CONTROLNO,FUNCTIONAL_CONTROLNO,MESSAGE_CONTROLNO,DATE_TIME_RECEIVED,REPROCESSSTATUS,STATUS,ACK_STATUS FROM DOCUMENT_TRACKING WHERE 1=1 ");
                 
                if (documentVisibilityAction.getDocdatepicker() != null && !"".equals(documentVisibilityAction.getDocdatepicker())) {
			  tmp_Recieved_From = DateUtility.getInstance().DateViewToDBCompare(documentVisibilityAction.getDocdatepicker());
			documentSearchQuery.append(" AND DATE_TIME_RECEIVED <= '" + tmp_Recieved_From
					+ "'");
		}
		if (documentVisibilityAction.getDocdatepickerfrom() != null && !"".equals(documentVisibilityAction.getDocdatepickerfrom())) {
			  tmp_Recieved_From = DateUtility.getInstance().DateViewToDBCompare(documentVisibilityAction.getDocdatepickerfrom());
			documentSearchQuery.append(" AND DATE_TIME_RECEIVED >= '" + tmp_Recieved_From
					+ "'");
		}
                  if (documentVisibilityAction.getDocSenderId() != null && !"".equals(documentVisibilityAction.getDocSenderId().trim())) {
			documentSearchQuery.append(WildCardSql.getWildCardSql1("SENDER_ID",
					documentVisibilityAction.getDocSenderId().trim().toUpperCase()));
		}  if (documentVisibilityAction.getDocReceiverId() != null && !"".equals(documentVisibilityAction.getDocReceiverId().trim())) {
			documentSearchQuery.append(WildCardSql.getWildCardSql1("RECEIVER_ID",
					documentVisibilityAction.getDocReceiverId().trim().toUpperCase()));
		}
                
                 //Status
                 if (documentVisibilityAction.getStatus() != null && !"-1".equals(documentVisibilityAction.getStatus().trim())) {
			documentSearchQuery.append(WildCardSql.getWildCardSql1("STATUS",
					documentVisibilityAction.getStatus().trim()));
		}
                 //ACK_STATUS
                 if (documentVisibilityAction.getAckStatus() != null && !"-1".equals(documentVisibilityAction.getAckStatus().trim())) {
			documentSearchQuery.append(WildCardSql.getWildCardSql1("ACK_STATUS",
					documentVisibilityAction.getAckStatus().trim()));
		}
                
                
                 
                    String searchQuery = documentSearchQuery.toString();
                   System.out.println("query--------------------->"+searchQuery);
                    //searchQuery  = "SELECT * FROM FILES";
                 
                 try {
                     connection = ConnectionProvider.getInstance().getConnection();

                    
			statement = connection.createStatement();
			
			resultSet=statement.executeQuery(searchQuery);
                        
                        documentList  = new ArrayList<DocumentVisibilityBean>();
                        
                        while(resultSet.next()) {
                            DocumentVisibilityBean documentVisibilityBean = new DocumentVisibilityBean();
                            documentVisibilityBean.setId(resultSet.getInt("ID"));
                         documentVisibilityBean.setInstanceId(resultSet.getString("FILE_ID"));
                            // Uncommenting ISA Number to display : 17/01/2014
                          if(resultSet.getString("INTERCHANGE_CONTROLNO") !=null && !"".equals(resultSet.getString("INTERCHANGE_CONTROLNO")))
                            documentVisibilityBean.setInterchange_ControlNo(resultSet.getString("INTERCHANGE_CONTROLNO"));
                          else
                              documentVisibilityBean.setInterchange_ControlNo("--");
                            documentVisibilityBean.setTransaction_type(resultSet.getString("TRAN_MESS_TYPE"));
                           
                            documentVisibilityBean.setDate_time_rec(resultSet.getTimestamp("DATE_TIME_RECEIVED"));
                            //documentVisibilityBean.setStatus(resultSet.getString("STATUS"));
                            // SENDER ID passing to UI : Added on 15/01/2014
                            documentVisibilityBean.setSenderId(resultSet.getString("SENDER_ID"));
                            documentVisibilityBean.setFile_type(resultSet.getString("FILE_TYPE"));
                             // SENDER ID passing to UI : Added on 15/01/2014
                            
                           
                            // SENDERNAME passing to UI : Added on 16/01/2014
                            // RECEIVERID passing to UI : Added on 16/01/2014
                            documentVisibilityBean.setReceiverId(resultSet.getString("RECEIVER_ID"));
                            documentVisibilityBean.setReProcessStatus(resultSet.getString("REPROCESSSTATUS"));
                             documentVisibilityBean.setStatus(resultSet.getString("STATUS"));
                            // Uncommenting Receiver_Name to display in UI
                          
                            //logisticsdocBean.setPoNumber(resultSet.getString("SEC_KEY_VAL"));
                           
                            // Adding GS_Number to pass to UI : 17/01/2014
                            
                            System.out.println("FUNCTIONAL_CONTROLNO-->"+resultSet.getString("FUNCTIONAL_CONTROLNO")+"----");
                            if(resultSet.getString("FUNCTIONAL_CONTROLNO") !=null && !"".equals(resultSet.getString("FUNCTIONAL_CONTROLNO")))
                            documentVisibilityBean.setFunctional_ControlNo(resultSet.getString("FUNCTIONAL_CONTROLNO"));
                            else
                                documentVisibilityBean.setFunctional_ControlNo("--");
                              if(resultSet.getString("MESSAGE_CONTROLNO") !=null && !"".equals(resultSet.getString("MESSAGE_CONTROLNO")))
                            documentVisibilityBean.setMessage_ControlNo(resultSet.getString("MESSAGE_CONTROLNO"));
                            else
                                documentVisibilityBean.setMessage_ControlNo("--");
                            
                            
                            documentList.add(documentVisibilityBean);  
                        }
                        
                 }   
                 catch (SQLException e) {
			System.out.println("I am in catch block coming in IMpl");
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
                // System.out.println("Length--->"+purchaseList.size());
                return documentList;
            } 

}
