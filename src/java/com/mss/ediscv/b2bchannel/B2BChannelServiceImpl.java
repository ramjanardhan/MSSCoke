/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.b2bchannel;

import com.mss.ediscv.util.ConnectionProvider;
import com.mss.ediscv.util.DateUtility;
import com.mss.ediscv.util.ServiceLocatorException;
import com.mss.ediscv.util.WildCardSql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author miracle
 */
public class B2BChannelServiceImpl implements B2BChannelService{
      Connection connection = null;
	PreparedStatement preparedStatement = null;
        Statement statement = null;
	ResultSet resultSet = null;
	
        private static Logger logger = Logger.getLogger(B2BChannelServiceImpl.class.getName());
        String responseString=null;
    public String addB2BChannel(B2BChannelAction b2BChannelAction) throws ServiceLocatorException{
          
          try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO B2BCHANNELSLIST( TP_ID, STATUS, DIRECTION, PROTOCOL, HOST, USERNAME, PRODUCERMAILBOX, CONSUMERMAILBOX, POOLINGCODE, APPID, SENDERID, RECEIVERID,CREATEDDATE,CREATEDBY) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            
            preparedStatement.setString(1, b2BChannelAction.getPartnerName());
            preparedStatement.setString(2, b2BChannelAction.getStatus());
            preparedStatement.setString(3, b2BChannelAction.getDirection());
            preparedStatement.setString(4, b2BChannelAction.getProtocol());
            preparedStatement.setString(5, b2BChannelAction.getHost());
            preparedStatement.setString(6, b2BChannelAction.getUserName());
            preparedStatement.setString(7, b2BChannelAction.getProducerMailBox());
            preparedStatement.setString(8, b2BChannelAction.getConsumerMailBox());
            preparedStatement.setString(9, b2BChannelAction.getPollingCode());
            preparedStatement.setString(10, b2BChannelAction.getAppId());
            preparedStatement.setString(11, b2BChannelAction.getSenderId());
            preparedStatement.setString(12, b2BChannelAction.getReceiverId());
           
            preparedStatement.setTimestamp(13, DateUtility.getInstance().getCurrentDB2Timestamp());
             preparedStatement.setString(14, b2BChannelAction.getCreatedBy());
            
             int i=preparedStatement.executeUpdate();
             if(i>0){
                 responseString= "<font color='green'>B2BChannel added succesfully.</font>";
             }else {
                 responseString= "<font color='red'>Please try again!</font>";
             }
             
            
        }catch (SQLException e) {
			System.out.println("I am in catch block coming in TP IMpl"+e.getMessage());
			// TODO Auto-generated catch block
                        responseString = "<font color='red'>"+e.getMessage()+"</font>";
			e.printStackTrace();
		}catch(Exception ex){
                    responseString = "<font color='red'>Please try again!</font>";
			System.out.println("hi"+ex.getMessage());
		}finally{
	           try{
	             
	               if(preparedStatement!=null){
	                   preparedStatement.close();
	                   preparedStatement = null;
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
    
    
    
    public ArrayList<B2BChannelBean> buildB2BChannelQuery(B2BChannelAction b2BChannelAction) throws ServiceLocatorException {
        ArrayList<B2BChannelBean> b2bChannelList = new ArrayList<B2BChannelBean>();
        StringBuffer b2bChannelSearchQuery = new StringBuffer();
        try {
              connection = ConnectionProvider.getInstance().getConnection();
              b2bChannelSearchQuery.append("SELECT * FROM B2BCHANNELSLIST WHERE 1=1");
               if (b2BChannelAction.getPartnerName() != null && !"".equals(b2BChannelAction.getPartnerName().trim())) {
			b2bChannelSearchQuery.append(WildCardSql.getWildCardSql1("TP_ID",b2BChannelAction.getPartnerName().trim().toUpperCase()));
					
		}
                if (b2BChannelAction.getStatus() != null && !"".equals(b2BChannelAction.getStatus().trim())) {
			b2bChannelSearchQuery.append(WildCardSql.getWildCardSql1("STATUS",b2BChannelAction.getStatus().trim().toUpperCase()));
					
		}  if (b2BChannelAction.getDirection() != null && !"".equals(b2BChannelAction.getDirection().trim())) {
			b2bChannelSearchQuery.append(WildCardSql.getWildCardSql1("DIRECTION",b2BChannelAction.getDirection().trim().toUpperCase()));
					
		}  if (b2BChannelAction.getProtocol() != null && !"".equals(b2BChannelAction.getProtocol().trim())) {
			b2bChannelSearchQuery.append(WildCardSql.getWildCardSql1("PROTOCOL",b2BChannelAction.getProtocol().trim().toUpperCase()));
					
		}  if (b2BChannelAction.getHost() != null && !"".equals(b2BChannelAction.getHost().trim())) {
			b2bChannelSearchQuery.append(WildCardSql.getWildCardSql1("HOST",b2BChannelAction.getHost().trim().toUpperCase()));
					
		}  if (b2BChannelAction.getUserName() != null && !"".equals(b2BChannelAction.getUserName().trim())) {
			b2bChannelSearchQuery.append(WildCardSql.getWildCardSql1("USERNAME",b2BChannelAction.getUserName().trim().toUpperCase()));
					
		}
                if (b2BChannelAction.getProducerMailBox() != null && !"".equals(b2BChannelAction.getProducerMailBox().trim())) {
			b2bChannelSearchQuery.append(WildCardSql.getWildCardSql1("PRODUCERMAILBOX",b2BChannelAction.getProducerMailBox().trim().toUpperCase()));
					
		}if (b2BChannelAction.getConsumerMailBox() != null && !"".equals(b2BChannelAction.getConsumerMailBox().trim())) {
			b2bChannelSearchQuery.append(WildCardSql.getWildCardSql1("CONSUMERMAILBOX",b2BChannelAction.getConsumerMailBox().trim().toUpperCase()));
					
		}if (b2BChannelAction.getPollingCode() != null && !"".equals(b2BChannelAction.getPollingCode().trim())) {
			b2bChannelSearchQuery.append(WildCardSql.getWildCardSql1("POOLINGCODE",b2BChannelAction.getPollingCode().trim().toUpperCase()));
					
		}if (b2BChannelAction.getAppId() != null && !"".equals(b2BChannelAction.getAppId().trim())) {
			b2bChannelSearchQuery.append(WildCardSql.getWildCardSql1("APPID",b2BChannelAction.getAppId().trim().toUpperCase()));
					
		}if (b2BChannelAction.getSenderId() != null && !"".equals(b2BChannelAction.getSenderId().trim())) {
			b2bChannelSearchQuery.append(WildCardSql.getWildCardSql1("SENDERID",b2BChannelAction.getSenderId().trim().toUpperCase()));
					
		}if (b2BChannelAction.getReceiverId() != null && !"".equals(b2BChannelAction.getReceiverId().trim())) {
			b2bChannelSearchQuery.append(WildCardSql.getWildCardSql1("RECEIVERID",b2BChannelAction.getReceiverId().trim().toUpperCase()));
					
		}
                System.out.println("Query-->"+b2bChannelSearchQuery.toString());
               preparedStatement = connection.prepareStatement(b2bChannelSearchQuery.toString());
               
               resultSet = preparedStatement.executeQuery();
               while(resultSet.next()){
                   B2BChannelBean b2BChannelBean = new B2BChannelBean();
                   b2BChannelBean.setB2bChannelId(resultSet.getInt("B2BCHANNELS_ID"));
                   b2BChannelBean.setPartnerName(resultSet.getString("TP_ID"));
                   b2BChannelBean.setStatus(resultSet.getString("STATUS"));
                   b2BChannelBean.setDirection(resultSet.getString("DIRECTION"));
                   b2BChannelBean.setProtocol(resultSet.getString("PROTOCOL"));
                   b2BChannelBean.setHost(resultSet.getString("HOST"));
                   b2BChannelBean.setUserName(resultSet.getString("USERNAME"));
                   b2BChannelBean.setProducerMailBox(resultSet.getString("PRODUCERMAILBOX"));
                   b2BChannelBean.setConsumerMailBox(resultSet.getString("CONSUMERMAILBOX"));
                   b2BChannelBean.setPollingCode(resultSet.getString("POOLINGCODE"));
                   b2BChannelBean.setAppId(resultSet.getString("APPID"));
                   b2BChannelBean.setSenderId(resultSet.getString("SENDERID"));
                   b2BChannelBean.setReceiverId(resultSet.getString("RECEIVERID"));
                   b2BChannelBean.setCreatedDate(resultSet.getString("CREATEDDATE"));
                   b2BChannelBean.setModifiedDate(resultSet.getString("MODIFIEDDATE"));
                   b2BChannelBean.setModifiedBy(resultSet.getString("MODIFIEDBY"));
                   b2bChannelList.add(b2BChannelBean);
               }
               
               
        }catch (SQLException e) {
			System.out.println("I am in catch block coming in TP IMpl"+e.getMessage());
			// TODO Auto-generated catch block
                        responseString = "<font color='red'>Please try with different Id!</font>";
			e.printStackTrace();
		}catch(Exception ex){
                    responseString = "<font color='red'>Please try again!</font>";
			System.out.println("hi"+ex.getMessage());
		}finally{
	           try{
	             if(resultSet!=null){
	                   resultSet.close();
	                   resultSet = null;
	               }
	               if(preparedStatement!=null){
	                   preparedStatement.close();
	                   preparedStatement = null;
	               }
	               
	               if(connection != null){
	                   connection.close();
	                   connection = null;
	               }
	           }catch (SQLException se){
	               throw new ServiceLocatorException(se);
	           }
	       }
        
        return b2bChannelList;
    }
    
      public B2BChannelAction b2BChannelEdit(B2BChannelAction b2BChannelAction) throws ServiceLocatorException {
          //  ArrayList<B2BChannelBean> b2bChannelList = new ArrayList<B2BChannelBean>();
        StringBuffer b2bChannelSearchQuery = new StringBuffer();
        try {
              connection = ConnectionProvider.getInstance().getConnection();
              b2bChannelSearchQuery.append("SELECT * FROM B2BCHANNELSLIST WHERE B2BCHANNELS_ID = "+b2BChannelAction.getB2bChannelId());
               
                System.out.println("Query-->"+b2bChannelSearchQuery.toString());
               preparedStatement = connection.prepareStatement(b2bChannelSearchQuery.toString());
               
               resultSet = preparedStatement.executeQuery();
               while(resultSet.next()){
                  // B2BChannelBean b2BChannelBean = new B2BChannelBean();
                   b2BChannelAction.setB2bChannelId(resultSet.getInt("B2BCHANNELS_ID"));
                   b2BChannelAction.setPartnerName(resultSet.getString("TP_ID"));
                   b2BChannelAction.setStatus(resultSet.getString("STATUS"));
                   b2BChannelAction.setDirection(resultSet.getString("DIRECTION"));
                   b2BChannelAction.setProtocol(resultSet.getString("PROTOCOL"));
                   b2BChannelAction.setHost(resultSet.getString("HOST"));
                   b2BChannelAction.setUserName(resultSet.getString("USERNAME"));
                   b2BChannelAction.setProducerMailBox(resultSet.getString("PRODUCERMAILBOX"));
                   b2BChannelAction.setConsumerMailBox(resultSet.getString("CONSUMERMAILBOX"));
                   b2BChannelAction.setPollingCode(resultSet.getString("POOLINGCODE"));
                   b2BChannelAction.setAppId(resultSet.getString("APPID"));
                   b2BChannelAction.setSenderId(resultSet.getString("SENDERID"));
                   b2BChannelAction.setReceiverId(resultSet.getString("RECEIVERID"));
                 //  b2BChannelBean.setCreatedDate(resultSet.getString("CREATEDDATE"));
                //   b2BChannelBean.setModifiedDate(resultSet.getString("MODIFIEDDATE"));
                 //  b2BChannelBean.setModifiedBy(resultSet.getString("MODIFIEDBY"));
                 //  b2bChannelList.add(b2BChannelBean);
               }
               
               
        }catch (SQLException e) {
			System.out.println("I am in catch block coming in TP IMpl"+e.getMessage());
			// TODO Auto-generated catch block
                        responseString = "<font color='red'>Please try with different Id!</font>";
			e.printStackTrace();
		}catch(Exception ex){
                    responseString = "<font color='red'>Please try again!</font>";
			System.out.println("hi"+ex.getMessage());
		}finally{
	           try{
	             if(resultSet!=null){
	                   resultSet.close();
	                   resultSet = null;
	               }
	               if(preparedStatement!=null){
	                   preparedStatement.close();
	                   preparedStatement = null;
	               }
	               
	               if(connection != null){
	                   connection.close();
	                   connection = null;
	               }
	           }catch (SQLException se){
	               throw new ServiceLocatorException(se);
	           }
	       }
          
          return b2BChannelAction;
      }
      public String doEditB2BChannel(B2BChannelAction b2BChannelAction) throws ServiceLocatorException{
           
          try {
            connection = ConnectionProvider.getInstance().getConnection();
         //   preparedStatement = connection.prepareStatement("INSERT INTO B2BCHANNELSLIST( TP_ID, STATUS, DIRECTION, PROTOCOL, HOST, USERNAME, PRODUCERMAILBOX, CONSUMERMAILBOX, POOLINGCODE, APPID, SENDERID, RECEIVERID,CREATEDDATE,CREATEDBY) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
               preparedStatement = connection.prepareStatement("UPDATE B2BCHANNELSLIST SET TP_ID =?, STATUS=?, DIRECTION=?, PROTOCOL=?, HOST=?, USERNAME=?, PRODUCERMAILBOX=?, CONSUMERMAILBOX=?, POOLINGCODE=?, APPID=?, SENDERID=?, RECEIVERID=?,MODIFIEDDATE=?,MODIFIEDBY=? WHERE B2BCHANNELS_ID=?");
            
            preparedStatement.setString(1, b2BChannelAction.getPartnerName());
            preparedStatement.setString(2, b2BChannelAction.getStatus());
            preparedStatement.setString(3, b2BChannelAction.getDirection());
            preparedStatement.setString(4, b2BChannelAction.getProtocol());
            preparedStatement.setString(5, b2BChannelAction.getHost());
            preparedStatement.setString(6, b2BChannelAction.getUserName());
            preparedStatement.setString(7, b2BChannelAction.getProducerMailBox());
            preparedStatement.setString(8, b2BChannelAction.getConsumerMailBox());
            preparedStatement.setString(9, b2BChannelAction.getPollingCode());
            preparedStatement.setString(10, b2BChannelAction.getAppId());
            preparedStatement.setString(11, b2BChannelAction.getSenderId());
            preparedStatement.setString(12, b2BChannelAction.getReceiverId());
           
            preparedStatement.setTimestamp(13, DateUtility.getInstance().getCurrentDB2Timestamp());
             preparedStatement.setString(14, b2BChannelAction.getCreatedBy());
             preparedStatement.setInt(15, b2BChannelAction.getB2bChannelId());
            
             int i=preparedStatement.executeUpdate();
             if(i>0){
                 responseString= "<font color='green'>B2BChannel updated succesfully.</font>";
             }else {
                 responseString= "<font color='red'>Please try again!</font>";
             }
             
            
        }catch (SQLException e) {
			System.out.println("I am in catch block coming in TP IMpl"+e.getMessage());
			// TODO Auto-generated catch block
                        responseString = "<font color='red'>Please try with different Id!</font>";
			e.printStackTrace();
		}catch(Exception ex){
                    responseString = "<font color='red'>Please try again!</font>";
			System.out.println("hi"+ex.getMessage());
		}finally{
	           try{
	             
	               if(preparedStatement!=null){
	                   preparedStatement.close();
	                   preparedStatement = null;
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
}
