/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.logisticeditracking;


import com.mss.ediscv.editracking.*;
import com.mss.ediscv.util.ConnectionProvider;

import com.mss.ediscv.util.DataSourceDataProvider;
import com.mss.ediscv.util.DateUtility;
import com.mss.ediscv.util.ServiceLocatorException;

import com.mss.ediscv.util.WildCardSql;
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
import java.util.Iterator;
import java.util.List;

import java.util.Map;
import org.apache.log4j.Logger;
/**
 *
 * @author miracle
 */
public class LogisticTrackInOutServiceImpl implements LogisticTrackInOutService {
 Connection connection = null;
	PreparedStatement preparedStatement = null;
        Statement statement = null;
	ResultSet resultSet = null;
             String tmp_Recieved_From = "";
	String tmp_Recieved_ToTime = "";
	String strFormat = "yyyy-MM-dd";
	DateFormat myDateFormat = new SimpleDateFormat(strFormat);
	Calendar cal = new GregorianCalendar();
	java.util.Date now = cal.getTime();
	long time = now.getTime();
	java.sql.Date date = new java.sql.Date(time);
	
        private static Logger logger = Logger.getLogger(LogisticTrackInOutServiceImpl.class.getName());
        String responseString=null;
        private List docTypeList;
        private int inbound;
        private int outbound;
        private double filesize;
         private double filesize1;
        private ArrayList<LogisticTrackInOutBean> documentList;
      
        int fromYear=0;
        int toYear=0;
        int fromMonth=0;
        int toMonth=0;
    @Override
    public ArrayList<LogisticTrackInOutBean> getDocumentList(LogisticTrackInOutAction logistictrackInOutAction) throws ServiceLocatorException {
                StringBuffer documentSearchQuery = new StringBuffer();
		logger.info("Entered into the :::: ReportsServiceImpl :::: getDocumentList");
                String docdatepicker= logistictrackInOutAction.getDocdatepicker();
                String docdatepickerfrom= logistictrackInOutAction.getDocdatepickerfrom();
                docTypeList=DataSourceDataProvider.getInstance().getDocumentTypeList();
                String temp="No Value";
                ArrayList temperoryList=new ArrayList();
                ArrayList inboundList=new ArrayList();
                ArrayList outboundList=new ArrayList();
                ArrayList documentTypeList=new ArrayList();
                ArrayList dateMonth=new ArrayList();
                ArrayList dateMonthdocType=new ArrayList();
                documentSearchQuery.append("select count(DIRECTION) as total from FILES where DIRECTION=? and TRANSACTION_TYPE=? and FLOWFLAG='L'");
                if (docdatepicker != null && !"".equals(docdatepicker) && docdatepickerfrom != null && !"".equals(docdatepickerfrom))
                   {
                          documentSearchQuery.append(" and year(DATE_TIME_RECEIVED)=? and month(DATE_TIME_RECEIVED)=?");
                          fromYear=DateUtility.getInstance().monthYear(docdatepickerfrom,"year");
                           System.out.println("from year------>"+fromYear);
                          toYear=DateUtility.getInstance().monthYear(docdatepicker,"year");
                           System.out.println("toYear------>"+toYear);
                          fromMonth=DateUtility.getInstance().monthYear(docdatepickerfrom,"month");
                          System.out.println("fromMonth------>"+fromMonth);
                          toMonth=DateUtility.getInstance().monthYear(docdatepicker,"month");
                            System.out.println("toMonth------>"+toMonth);
                   }
                System.out.println("DOC queryquery prasad-->"+documentSearchQuery.toString());
                String searchQuery = documentSearchQuery.toString();
                inbound=0;
                outbound=0;
                try {
                        connection = ConnectionProvider.getInstance().getConnection();
                        preparedStatement = connection.prepareStatement(searchQuery);
                        LogisticTrackInOutBean trackInOutBean;
                        documentList  = new ArrayList<LogisticTrackInOutBean>();
                        for(int k=fromYear;k<=toYear;k++)
                        {
                            for(int j=fromMonth;j<13;j++)
                            {
                             for(int i=0;i<docTypeList.size();i++)
                                {
                                //System.out.println("i="+k+"j="+j);
                                preparedStatement.setString(1, "INBOUND");
                                preparedStatement.setString(2, (String)docTypeList.get(i));
                                preparedStatement.setInt(3, k);
                                preparedStatement.setInt(4, j);
                                //preparedStatement.setString(5, "L");
                                resultSet = preparedStatement.executeQuery();
                                if(resultSet.next()) 
                                {
                                    inbound = resultSet.getInt("total");
                                }
                                resultSet.close();
                                preparedStatement.setString(1, "OUTBOUND");
                                preparedStatement.setString(2, (String)docTypeList.get(i));
                                preparedStatement.setInt(3, k);
                                preparedStatement.setInt(4, j);
                               // preparedStatement.setString(5, "L");
                                resultSet = preparedStatement.executeQuery();
                                if(resultSet.next()) 
                                {
                                    outbound = resultSet.getInt("total");
                                }
                                resultSet.close();
                                if(inbound!=0||outbound!=0)
                                {
                                if(!documentTypeList.contains((String)docTypeList.get(i))){
                                documentTypeList.add((String)docTypeList.get(i));}
                                if(temp!=null&&!temp.equalsIgnoreCase(k+"/"+j))
                                {
                                temperoryList.add(k+"/"+j);
                                temp=k+"/"+j;
                                dateMonth.add(k+"/"+j);
                                }
                                temperoryList.add((String)docTypeList.get(i));
                                temperoryList.add(inbound);
                                temperoryList.add(outbound);
                                temperoryList.add(inbound+outbound);
                                if(inbound!=0)
                                {
                                    inboundList.add((String)docTypeList.get(i));
                                }
                                if(outbound!=0)
                                {
                                    outboundList.add((String)docTypeList.get(i));
                                }
                                }
                                }
                                if(!temperoryList.isEmpty())
                                {
                                dateMonthdocType.add(temperoryList.clone());
                                }
                                temperoryList.clear(); 
                                if(j==toMonth&&k==toYear)
                                {
                                    break;
                                }
                                if(j==12)
                                {
                                    fromMonth=1;
                                }
                        } 
                   }
                            System.out.println("doctypelist="+documentTypeList+"dateMonthdocType="+dateMonthdocType+"dateMonth"+dateMonth+"inbountList="+inboundList+"outboundList="+outboundList);
                            trackInOutBean = new LogisticTrackInOutBean();
                            trackInOutBean.setDocumentTypeList(documentTypeList);
                            trackInOutBean.setDateMonthdocType(dateMonthdocType);
                            trackInOutBean.setDateMonth(dateMonth);
                            trackInOutBean.setInboundList(inboundList);
                            trackInOutBean.setOutboundList(outboundList);
                            documentList.add(trackInOutBean);  
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
               
                  
                  return documentList;
                 
    }
    
    
    @Override
    public ArrayList<LogisticTrackInOutBean> getSummaryDetails(LogisticTrackInOutAction logistictrackInOutAction) throws ServiceLocatorException {
                StringBuffer documentSearchQuery = new StringBuffer();
		logger.info("Entered into the :::: TrackInOutServiceImpl :::: getSummaryDetails");
                String docdatepicker= logistictrackInOutAction.getDocdatepicker();
                String docdatepickerfrom= logistictrackInOutAction.getDocdatepickerfrom();
                   String docNetworkvan="";
                  if(!logistictrackInOutAction.getDocNetworkvan().equals("-1"))
                  docNetworkvan = logistictrackInOutAction.getDocNetworkvan();
                docTypeList=DataSourceDataProvider.getInstance().getDocumentTypeList();
                Map partnerMap=DataSourceDataProvider.getInstance().getPartnerMap();
                int inboundTotal=0;
                int outboundTotal=0;
                double filesizeTotal=0;
                double filesizeTotal1=0;
                LogisticTrackInOutBean trackInOutBean;
                documentSearchQuery.append("select count(DIRECTION) as total,cast(SUM(FILE_SIZE)/1024 as decimal(10,2)) as total_size from FILES where DIRECTION=? and TRANSACTION_TYPE=?");
                documentSearchQuery.append(" and (SENDER_ID = ? or RECEIVER_ID=?) and FLOWFLAG='L'");
                
                  if (docNetworkvan != null && !"".equals(docNetworkvan.trim())) {
			documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.NETWORK_VAN",
					docNetworkvan.trim()));
		}
                if (docdatepicker != null && !"".equals(docdatepicker)) {
	               tmp_Recieved_From = DateUtility.getInstance().DateViewToDBCompare(docdatepicker);
			documentSearchQuery.append(" AND FILES.DATE_TIME_RECEIVED <= '" + tmp_Recieved_From
					+ "'");
		}
		if (docdatepickerfrom != null && !"".equals(docdatepickerfrom) ) {
			  tmp_Recieved_From = DateUtility.getInstance().DateViewToDBCompare(docdatepickerfrom);
			documentSearchQuery.append(" AND FILES.DATE_TIME_RECEIVED >= '" + tmp_Recieved_From
					+ "'");
		}
                System.out.println("DOC queryquery prasad-->"+documentSearchQuery.toString());
                String searchQuery = documentSearchQuery.toString();
                inbound=0;
                outbound=0;
                filesize=0;
                filesize1=0;
                try {
                        connection = ConnectionProvider.getInstance().getConnection();
                        preparedStatement = connection.prepareStatement(searchQuery);
                        documentList  = new ArrayList<LogisticTrackInOutBean>();
                        Iterator entries;
                        for(int i=0;i<docTypeList.size();i++)
                             {
                            entries = partnerMap.entrySet().iterator();
                            while (entries.hasNext()) {
                            Map.Entry thisEntry = (Map.Entry) entries.next();
                            Object key = thisEntry.getKey();
                            Object value = thisEntry.getValue();
                            preparedStatement.setString(1, "INBOUND");
                            preparedStatement.setString(2, (String)docTypeList.get(i));
                            preparedStatement.setString(3, (String)key);
                            preparedStatement.setString(4, (String)key);
                            resultSet = preparedStatement.executeQuery();
                            if(resultSet.next()) 
                            {
                                inbound = resultSet.getInt("total");
                                filesize= resultSet.getDouble("total_size");
                        
                            }
                            resultSet.close();
                            preparedStatement.setString(1, "OUTBOUND");
                            preparedStatement.setString(2, (String)docTypeList.get(i));
                            preparedStatement.setString(3, (String)key);
                            preparedStatement.setString(4, (String)key);
                          
                            resultSet = preparedStatement.executeQuery();
                            if(resultSet.next()) 
                            {
                                outbound = resultSet.getInt("total");
                               filesize1= resultSet.getDouble("total_size");
                       
                            }
                            resultSet.close();
                            if(inbound!=0||outbound!=0)
                            {
                            trackInOutBean = new LogisticTrackInOutBean();
                            trackInOutBean.setTransaction_type("");
                            trackInOutBean.setPname((String)value);
                            trackInOutBean.setFilesize(filesize);
                              System.out.println(filesize);
                            trackInOutBean.setFilesize1(filesize1);
                            System.out.println("filesize1========"+filesize1);
                            trackInOutBean.setFilesizeTotal(filesize+filesize1);
                            trackInOutBean.setInbound(inbound);
                            trackInOutBean.setOutbound(outbound);
                            trackInOutBean.setTotal(inbound+outbound);
                            filesizeTotal=filesizeTotal+filesize;
                            System.out.println("filesizeTotal========"+filesizeTotal);
                           filesizeTotal1=filesizeTotal1+filesize1;
                           System.out.println("filesizeTotal1========"+filesizeTotal1);
                            inboundTotal=inboundTotal+inbound;
                            outboundTotal=outboundTotal+outbound;
                            //System.out.println("Document type"+docTypeList.get(i)+"inbound="+inbound+"outbound"+outbound+"inbound total="+inboundTotal+"outbound total"+outboundTotal+"i="+i+"docTypeList.size()"+docTypeList.size());
                            documentList.add(trackInOutBean);  
                            } 
                            }
                            if(inboundTotal!=0||outboundTotal!=0)
                            {
                            trackInOutBean = new LogisticTrackInOutBean();
                            trackInOutBean.setTransaction_type((String)docTypeList.get(i));
                            trackInOutBean.setPname("Total");
                            trackInOutBean.setFilesizeTotal(filesizeTotal+filesizeTotal1);
                            trackInOutBean.setInbound(inboundTotal);
                            trackInOutBean.setOutbound(outboundTotal);
                            trackInOutBean.setTotal(inboundTotal+outboundTotal);
                            //System.out.println("Document type"+docTypeList.get(i)+"inbound="+inbound+"outbound"+outbound+"inbound total="+inboundTotal+"outbound total"+outboundTotal+"i="+i+"docTypeList.size()"+docTypeList.size());
                            documentList.add(trackInOutBean);
                            inboundTotal=0;
                            outboundTotal=0;
                            filesizeTotal=0;
                            filesizeTotal1=0;
                              }
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
                 
        return documentList;
    }
     @Override
    public ArrayList<LogisticTrackInOutBean> getInquiryDetails(LogisticTrackInOutAction logistictrackInOutAction) throws ServiceLocatorException {
                StringBuffer documentSearchQuery = new StringBuffer();
		logger.info("Entered into the :::: TrackInOutServiceImpl :::: getInquiryDetails");
                String docdatepicker= logistictrackInOutAction.getDocdatepicker();
                String docdatepickerfrom= logistictrackInOutAction.getDocdatepickerfrom();
                    String doctype="";
                    String partner="";
                  if(!logistictrackInOutAction.getDocType().equals("-1"))
                  doctype = logistictrackInOutAction.getDocType();
                 if(!logistictrackInOutAction.getPartnerMapId().equals("-1"))
                  partner = logistictrackInOutAction.getPartnerMapId();
                  
                documentSearchQuery.append("SELECT FILES.TRANSACTION_TYPE as TRANSACTION_TYPE,FILES.DIRECTION as DIRECTION, "
                          + "FILES.DATE_TIME_RECEIVED as DATE_TIME_RECEIVED,FILES.ACK_STATUS as ACK_STATUS,"
                          + "TP.NAME as RECEIVER_NAME,TP.NAME as SENDER_NAME FROM FILES " 
                          + "LEFT OUTER JOIN TP ON (TP.ID=FILES.SENDER_ID) OR (TP.ID=FILES.RECEIVER_ID) AND FLOWFLAG LIKE '%L%'");
                          
                documentSearchQuery.append(" WHERE 1=1 ");
             
                
                 if (doctype != null && !"".equals(doctype.trim())) {
			documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.TRANSACTION_TYPE",
					doctype.trim()));
		}  
                  if (partner != null && !"".equals(partner.trim())) {
			documentSearchQuery.append(" AND TP.ID='"+ partner.trim().toUpperCase() 
                                +"'");
                  }
         //     if(partner != null && !"".equals(partner.trim())) {
	//	documentSearchQuery.append(" AND TP1.ID='"+ partner.trim().toUpperCase() 
                         //      +"'");
             //     }
               
              if (docdatepicker != null && !"".equals(docdatepicker)) {
	               tmp_Recieved_From = DateUtility.getInstance().DateViewToDBCompare(docdatepicker);
			documentSearchQuery.append(" AND FILES.DATE_TIME_RECEIVED <= '" + tmp_Recieved_From
					+ "'");
		}
		if (docdatepickerfrom != null && !"".equals(docdatepickerfrom)) {
			  tmp_Recieved_From = DateUtility.getInstance().DateViewToDBCompare(docdatepickerfrom);
			documentSearchQuery.append(" AND FILES.DATE_TIME_RECEIVED >= '" + tmp_Recieved_From
					+ "'");
		}
                
                  documentSearchQuery.append(" order by DATE_TIME_RECEIVED DESC fetch first 50 rows only");        
              
                  System.out.println("DOC queryquery prasad-->"+documentSearchQuery.toString());
                    String searchQuery = documentSearchQuery.toString();

                 
                 try {
                        connection = ConnectionProvider.getInstance().getConnection();
                        statement = connection.createStatement();
			resultSet=statement.executeQuery(searchQuery);
                        documentList  = new ArrayList<LogisticTrackInOutBean>();
                        while(resultSet.next()) 
                        {
                           LogisticTrackInOutBean trackInOutBean = new LogisticTrackInOutBean();
                           trackInOutBean.setTransaction_type(resultSet.getString("TRANSACTION_TYPE"));
                           String direction = resultSet.getString("DIRECTION");
                           trackInOutBean.setDirection(direction);
                           trackInOutBean.setDate_time_rec(resultSet.getTimestamp("DATE_TIME_RECEIVED"));
                         //  if(direction!=null&&direction.equalsIgnoreCase("INBOUND")){
                                trackInOutBean.setPname(resultSet.getString("SENDER_NAME"));
                         //   }
                         //  if(direction!=null&&direction.equalsIgnoreCase("OUTBOUND")){
                                trackInOutBean.setPname(resultSet.getString("RECEIVER_NAME"));
                         //  }
                              
                           trackInOutBean.setAckStatus(resultSet.getString("ACK_STATUS"));
                           documentList.add(trackInOutBean);  
                           //System.out.println("TRANSACTION_TYPE"+resultSet.getString("TRANSACTION_TYPE")+"DIRECTION"+resultSet.getString("DIRECTION"));
                           //System.out.println("Doc List"+documentList.size());
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
   
         return documentList;
    }
}
