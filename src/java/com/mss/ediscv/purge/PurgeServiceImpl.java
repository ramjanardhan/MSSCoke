/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.purge;

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
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.log4j.Logger;
/**
 *
 * @author miracle
 */
public class PurgeServiceImpl implements PurgeService {
    
    Connection connection = null;
	PreparedStatement preparedStatement = null;
        Statement statement = null;
	ResultSet resultSet = null;
	CallableStatement callableStatement = null;
        private static Logger logger = Logger.getLogger(PurgeServiceImpl.class.getName());
        String responseString=null;
        
        public String purgeProcess(PurgeAction purgeAction) throws ServiceLocatorException {
            
        logger.info("Entered into the :::: PurgeServiceImpl :::: purgeProcess");
        String dayCount = purgeAction.getDayCount();
        String transType = purgeAction.getTransType();
        Map deleteMap = new TreeMap();
        List priKeyList = new ArrayList();
        StringBuffer queryString = new StringBuffer("");
        
         try {
            connection = ConnectionProvider.getInstance().getConnection();
            //queryString.append("select Id, Transaction_Type,FILE_ID   from FILES  where DATEDIFF(day,DATE_TIME_RECEIVED,GETDATE()) > "+dayCount);
            queryString.append("select Id, Transaction_Type,FILE_ID   from FILES  where (CURRENT TIMESTAMP -DATE_TIME_RECEIVED) > "+dayCount);
            //(CURRENT TIMESTAMP -DATE_TIME_RECEIVED)
            if(!transType.equals("-1")) {
                queryString.append(" AND Transaction_Type = '"+transType+"'");
            }
            preparedStatement = connection.prepareStatement(queryString.toString());
            System.out.println("Purging queryString -->"+queryString.toString());
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                deleteMap.put(resultSet.getString("FILE_ID"), resultSet.getString("Transaction_Type"));
            }
            
            Set set = deleteMap.entrySet(); 
            Iterator i = set.iterator(); 
             while(i.hasNext()) {
                Map.Entry me = (Map.Entry)i.next();

                deleteReocords((String)me.getKey(),(String)me.getValue());
                }
             responseString = "<font color='green'>Purge Process Completed Successfully</font>";
            
         }catch (SQLException e) {
			System.out.println("I am in catch block coming in PurgeServiceImpl"+e.getMessage());
			// TODO Auto-generated catch block
                        responseString = "<font color='red'>Please try Again</font>";
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
        
        
        return responseString;
        
      }
public void deleteReocords(String fileId,String transType) throws ServiceLocatorException{
    Connection connection=null;
    Statement statement = null;
    
    try {
            connection = ConnectionProvider.getInstance().getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String fileQuery = "DELETE FROM FILES WHERE File_ID='"+fileId+"'";
            System.out.println("Delete File Query-->"+fileQuery);
             statement.addBatch(fileQuery);
             //'204':'Loadtender','990':'Response','210':'Shipment','214':'Invoice'}
             String transQuery= "";
             if(transType.equals("204")) {
                 transQuery = "DELETE FROM TRANSPORT_LOADTENDER WHERE File_ID='"+fileId+"'";
             }else if(transType.equals("990")) {
                 transQuery = "DELETE FROM TRANSPORT_LT_RESPONSE WHERE File_ID='"+fileId+"'";
             }else if(transType.equals("210")) {
                 transQuery = "DELETE FROM TRANSPORT_SHIPMENT WHERE File_ID='"+fileId+"'";
             }else if(transType.equals("214")) {
                 transQuery = "DELETE FROM TRANSPORT_INVOICE WHERE File_ID='"+fileId+"'";
             }
             System.out.println("Delete transQuery-->"+transQuery);
             statement.addBatch(transQuery);
            int[] count = statement.executeBatch();
            System.out.println("count length-->"+count.length);
            connection.commit();
            
         }catch (SQLException e) {
			
			e.printStackTrace();
		}catch(Exception ex){
                   
			ex.printStackTrace();
		}finally{
	           try{
	             
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
    
    
    //-----
}
}
