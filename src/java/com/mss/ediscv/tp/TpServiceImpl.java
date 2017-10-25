/*
 ******************************************************************************
 *
 * Project : Miracle Supply Chain Visibility portal v2.0
 *
 * Package : com.mss.mscvp.util
 *
 * Date    : Mar 11, 2013 1:43:29 PM
 *
 * Author  : Nagireddy seerapu <nseerapu@miraclesoft.com>
 *
 * File    : POServiceImpl.java
 *
 * 
 * *****************************************************************************
 */
package com.mss.ediscv.tp;

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


import com.mss.ediscv.util.DateUtility;
import com.mss.ediscv.util.Properties;
import com.mss.ediscv.util.ServiceLocatorException;
import com.mss.ediscv.util.WildCardSql;
import java.io.File;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * @author miracle
 *
 */
public class TpServiceImpl implements TpService {
	
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
        Statement statement = null;
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
        String responseString=null;
	int callableStatementUpdateCount;
        private ArrayList<TpBean> tpList;
	//private ShipmentBean shipmentBean;
	private static Logger logger = Logger.getLogger(TpServiceImpl.class
			.getName());

	//@Override
	public String addTP(TpAction tpAction) throws ServiceLocatorException {
		
		StringBuffer shipmentSearchQuery = new StringBuffer();
		logger.info("Entered into the :::: TPServiceImpl :::: buildPOSQuery");
		    
		createTpDirectoy(tpAction);
		String id=tpAction.getId();
		String name=tpAction.getName();
                String tpInPath=tpAction.getTpInPath();
                String tpOutPath=tpAction.getTpOutPath();
                
		
                 //  System.out.println("shipment query-->"+shipmentSearchQuery.toString());
                    
                    
		logger.info("End of :::: POServiceImpl :::: buildPOSQuery");
		//return shipmentSearchQuery.toString();
                String searchQuery=shipmentSearchQuery.toString();
                try {
                        connection = ConnectionProvider.getInstance().getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO TP VALUES(?,?,?,?,?,?,?)");
                        preparedStatement.setString(1,id);
                        preparedStatement.setString(2,name.toUpperCase());
                        preparedStatement.setString(3,tpAction.getContact());
                        preparedStatement.setString(4, tpAction.getPhno());
                        preparedStatement.setString(5, tpAction.getDept());
                        preparedStatement.setString(6, tpAction.getCommid());
                        preparedStatement.setString(7, tpAction.getQualifier());
                        int count = preparedStatement.executeUpdate();
                        if(count>0)
                        {
                             tpAction.setId("");
                            tpAction.setName("");
                            tpAction.setContact("");
                            tpAction.setPhno("");
                            tpAction.setDept("");
                            tpAction.setCommid("")  ;
                            tpAction.setQualifier("");
                            
                            responseString = "<font color='green'>Trading Partner Inserted Successfully!! </font>";
                        }else
                        {
                             responseString = "<font color='red'>Sorry ! Please try again.</font>";
                        }
			//resultSet=statement.executeQuery(searchQuery);
                         
                        
                }catch (SQLException e) {
			System.out.println("I am in catch block coming in Shipment IMpl"+e.getMessage());
			// TODO Auto-generated catch block
                        responseString = "<font color='red'>Please try with different Id!</font>";
			e.printStackTrace();
		}catch(Exception ex){
                    responseString = "<font color='red'>Please try later!</font>";
			System.out.println("hi"+ex.getMessage());
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
                
                return responseString;
        }

        public ArrayList getTpList(TpAction tpAction) throws ServiceLocatorException {
		
		StringBuffer shipmentSearchQuery = new StringBuffer();
		logger.info("Entered into the :::: TPServiceImpl :::: buildPOSQuery");
		    
		
		String id=tpAction.getId();
		String name=tpAction.getName();
		 
                
          
                    shipmentSearchQuery.append("SELECT * FROM TP");
                     

                   // shipmentSearchQuery.append("order by ASN.ASN_NUMBER desc fetch first 50 rows only");
		
                    //System.out.println("shipment query-->"+shipmentSearchQuery.toString());
                    
                    
		logger.info("End of :::: POServiceImpl :::: buildPOSQuery");
		//return shipmentSearchQuery.toString();
                String searchQuery=shipmentSearchQuery.toString();
                try {
                        connection = ConnectionProvider.getInstance().getConnection();
			statement = connection.createStatement();
			resultSet=statement.executeQuery(searchQuery);
                        tpList = new ArrayList<TpBean>();
                        while(resultSet.next()) {
                            TpBean tpBean = new TpBean();
                            tpBean.setId(resultSet.getString("ID"));
                            tpBean.setName(resultSet.getString("NAME"));
                            tpBean.setContact(resultSet.getString("CONTACT_INFO"));
                            tpBean.setPhno(resultSet.getString("VENDOR_NUMBER"));
                            tpBean.setDept(resultSet.getString("DEPARTMENTS"));
                            tpBean.setCommid(resultSet.getString("EDI_COMM_ID"));
                            tpBean.setQualifier(resultSet.getString("QUALIFIER"));
                            // tpBean.setTpInPath(resultSet.getString("TP_INBOUND_PATH"));
                            //tpBean.setTpOutPath(resultSet.getString("TP_OUTBOUND_PATH"));
                            
                            tpList.add(tpBean);
                         
                        }
                        
                }catch (SQLException e) {
			System.out.println("I am in catch block coming in Shipment IMpl"+e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception ex){
			System.out.println("hi"+ex.getMessage());
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
                
                return tpList;
        }
        
        
    /**
     * @set the Trading partner Files location
     */
        
        public void createTpDirectoy(TpAction tpAction) {
            String id  = tpAction.getId();
            String name = tpAction.getName();
            
            File outboundFile = new File(Properties.getProperty("mscvp.tpPath")+name+"\\"+"OUTBOUND");
            File inboundFile = new File(Properties.getProperty("mscvp.tpPath")+name+"\\"+"INBOUND");
            outboundFile.mkdirs();
            inboundFile.mkdirs();
            tpAction.setTpOutPath(outboundFile.getAbsolutePath());
            tpAction.setTpInPath(inboundFile.getAbsolutePath());
            
            
        }
 
    
}
