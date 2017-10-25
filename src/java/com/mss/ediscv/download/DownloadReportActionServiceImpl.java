/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.download;

import com.mss.ediscv.util.ConnectionProvider;
import com.mss.ediscv.util.DateUtility;
import com.mss.ediscv.util.ServiceLocatorException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author miracle
 */
public class DownloadReportActionServiceImpl implements DownloadReportActionService {
    
    
     public String getReportattachment(int scheduleId,String startDate) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        String reportpath = "Nodata";
        String queryString="";
        ResultSet resultSet = null;
     //statement = connection.createStatement() UPDATE SCHEDULER SET SCH_STATUS = 'InActive' WHERE SCH_ID =" + id;
       
        //queryString = "select DOCUMENT.ISA_NUMBER,DOCUMENT.DOCUMENT_TYPE,FILES.SENDER_ID,FILES.RECEIVER_ID,FILES.PRE_TRANS_FILEPATH,FILES.POST_TRANS_FILEPATH from DOCUMENT LEFT OUTER JOIN FILES on (DOCUMENT.FILE_ID= FILES.FILE_ID) where FILES.ISA_NUMBER LIKE '%"+isaNumber+"%'";
        
        
        startDate =DateUtility.getInstance().DateViewToDBCompare(startDate);
        startDate=startDate.substring(0, 10);
        queryString = "SELECT SCH_REPORTPATH from SCH_LOOKUPS where SCH_ID="+scheduleId+" and date(SCH_RUNDATE) = DATE('"+startDate+"')"; 
        
     
        System.out.println("QUERY IS "+queryString);
        

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet =statement.executeQuery(queryString);
           if (resultSet.next()) {
               reportpath = resultSet.getString("SCH_REPORTPATH");
               
           }
      
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
        } finally {
            try {

                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
         System.out.println("this is string >> "+reportpath);
        return reportpath;
    }
    
    
}