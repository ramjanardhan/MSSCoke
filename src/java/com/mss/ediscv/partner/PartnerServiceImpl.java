/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.partner;

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
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author miracle
 */
public class PartnerServiceImpl implements PartnerService{
      Connection connection = null;
	PreparedStatement preparedStatement = null;
        Statement statement = null;
	ResultSet resultSet = null;
	
        private static Logger logger = Logger.getLogger(PartnerServiceImpl.class.getName());
        String responseString=null;
    public String addPartner(PartnerAction partnerAction) throws ServiceLocatorException {
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO TP(ID, NAME, FLOW_FLAG, CREATED_BY, CREATED_TS, STATUS) VALUES (?, ?, ?, ?, ?,?)");
            
            preparedStatement.setString(1, partnerAction.getPartnerIdentifier());
            preparedStatement.setString(2, partnerAction.getPartnerName());
            preparedStatement.setString(3, partnerAction.getFlowFlag());
            preparedStatement.setString(4, partnerAction.getCreatedBy());
            preparedStatement.setTimestamp(5, DateUtility.getInstance().getCurrentDB2Timestamp());
            preparedStatement.setString(6, partnerAction.getStatus());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            preparedStatement = null;
            preparedStatement = connection.prepareStatement("INSERT INTO TP_DETAILS(TP_ID,TP_NAME,INTERNALIDENTIFIER,APPLICATIONID,STATE) VALUES(?,?,?,?,?)");
            preparedStatement.setString(1,partnerAction.getPartnerIdentifier());
            preparedStatement.setString(2,partnerAction.getPartnerName());
            preparedStatement.setString(3,partnerAction.getInternalIdentifier());
            preparedStatement.setString(4,partnerAction.getApplicationId());
            preparedStatement.setString(5,partnerAction.getCountryCode());
             int i=preparedStatement.executeUpdate();
             if(i>0){
                 responseString= "<font color='green'>Partner added succesfully.</font>";
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
    
    
    public ArrayList<PartnerBean> buildPartnerQuery(PartnerAction partnerAction, HttpServletRequest http) throws ServiceLocatorException {
        ArrayList<PartnerBean> partnerList = new ArrayList<PartnerBean>();
        StringBuffer partnerSearchQuery = new StringBuffer();
        try {
              connection = ConnectionProvider.getInstance().getConnection();
              partnerSearchQuery.append("select tp.ID as PartnerId,tp.NAME as PartnerName,tp_details.INTERNALIDENTIFIER,tp_details.APPLICATIONID,tp_details.STATE,tp.STATUS,tp.MODIFIED_TS,tp.MODIFIED_BY,tp.CREATED_TS from tp LEFT OUTER JOIN tp_details on(tp_details.TP_ID=tp.ID) WHERE 1=1 AND tp.FLOW_FLAG='"+partnerAction.getFlowFlag()+"'");
               if (partnerAction.getPartnerIdentifier() != null && !"".equals(partnerAction.getPartnerIdentifier().trim())) {
			partnerSearchQuery.append(WildCardSql.getWildCardSql1("TP_DETAILS.TP_ID",partnerAction.getPartnerIdentifier().trim().toUpperCase()));
					
		}
               if (partnerAction.getPartnerName() != null && !"".equals(partnerAction.getPartnerName().trim())) {
			partnerSearchQuery.append(WildCardSql.getWildCardSql1("TP_DETAILS.TP_NAME",partnerAction.getPartnerName().trim().toUpperCase()));
					
		} if (partnerAction.getStatus() != null && !"".equals(partnerAction.getStatus().trim())) {
			partnerSearchQuery.append(WildCardSql.getWildCardSql1("TP.STATUS",partnerAction.getStatus().trim().toUpperCase()));
					
		} if (partnerAction.getInternalIdentifier() != null && !"".equals(partnerAction.getInternalIdentifier().trim())) {
			partnerSearchQuery.append(WildCardSql.getWildCardSql1("TP_DETAILS.INTERNALIDENTIFIER",partnerAction.getInternalIdentifier().trim().toUpperCase()));
					
		} if (partnerAction.getCountryCode() != null && !"".equals(partnerAction.getCountryCode().trim())) {
			partnerSearchQuery.append(WildCardSql.getWildCardSql1("TP_DETAILS.STATE",partnerAction.getCountryCode().trim().toUpperCase()));
					
		}if (partnerAction.getApplicationId() != null && !"".equals(partnerAction.getApplicationId().trim())) {
			partnerSearchQuery.append(WildCardSql.getWildCardSql1("TP_DETAILS.APPLICATIONID",partnerAction.getApplicationId().trim().toUpperCase()));
					
		}
                
                System.out.println("Query-->"+partnerSearchQuery.toString());
               preparedStatement = connection.prepareStatement(partnerSearchQuery.toString());
               
               resultSet = preparedStatement.executeQuery();
               while(resultSet.next()){
                   PartnerBean partnerBean = new PartnerBean();
                   partnerBean.setPartnerIdentifier(resultSet.getString("PartnerId"));
                   partnerBean.setPartnerName(resultSet.getString("PartnerName"));
                   partnerBean.setInternalIdentifier(resultSet.getString("INTERNALIDENTIFIER"));
                   partnerBean.setApplicationId(resultSet.getString("APPLICATIONID"));
                   partnerBean.setCountryCode(resultSet.getString("STATE"));
                   partnerBean.setStatus(resultSet.getString("STATUS"));
                 //  partnerBean.setCreatedBy(resultSet.getString("CREATED_BY"));
                   partnerBean.setCreatedDate(resultSet.getString("CREATED_TS"));
                   partnerBean.setChangedBy(resultSet.getString("MODIFIED_BY"));
                  partnerBean.setChangedDate(resultSet.getString("MODIFIED_TS"));
                  partnerList.add(partnerBean);
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
        
        return partnerList;
    }
    public PartnerAction partnerEdit(PartnerAction partnerAction) throws ServiceLocatorException{
        StringBuffer partnerSearchQuery = new StringBuffer();
        //PartnerBean partnerBean = new PartnerBean();
        try {
             connection = ConnectionProvider.getInstance().getConnection();
             partnerSearchQuery.append("select tp.ID as PartnerId,tp.NAME as PartnerName,tp_details.INTERNALIDENTIFIER,tp_details.APPLICATIONID,tp_details.STATE,tp.STATUS,tp.MODIFIED_TS,tp.MODIFIED_BY,tp.CREATED_TS from tp LEFT OUTER JOIN tp_details on(tp_details.TP_ID=tp.ID) WHERE 1=1 AND tp.ID='"+partnerAction.getPartnerIdentifier()+"'");
               System.out.println("Query-->"+partnerSearchQuery.toString());
               preparedStatement = connection.prepareStatement(partnerSearchQuery.toString());
               
               resultSet = preparedStatement.executeQuery();
             
             if(resultSet.next()){
             partnerAction.setPartnerIdentifier(resultSet.getString("PartnerId"));
                   partnerAction.setPartnerName(resultSet.getString("PartnerName"));
                   partnerAction.setInternalIdentifier(resultSet.getString("INTERNALIDENTIFIER"));
                   partnerAction.setApplicationId(resultSet.getString("APPLICATIONID"));
                   partnerAction.setCountryCode(resultSet.getString("STATE"));
                   partnerAction.setStatus(resultSet.getString("STATUS"));
                 //  partnerBean.setCreatedBy(resultSet.getString("CREATED_BY"));
                //   partnerBean.setCreatedDate(resultSet.getString("CREATED_TS"));
                 //  partnerBean.setChangedBy(resultSet.getString("MODIFIED_BY"));
                 // partnerBean.setChangedDate(resultSet.getString("MODIFIED_TS"));
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
        return partnerAction;
    }
    public String editPartner(PartnerAction partnerAction) throws ServiceLocatorException {
         try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("UPDATE TP SET NAME=?,MODIFIED_BY=?,MODIFIED_TS=?, STATUS=? WHERE ID=?");
            
           
            preparedStatement.setString(1, partnerAction.getPartnerName());
            preparedStatement.setString(2, partnerAction.getCreatedBy());
            preparedStatement.setTimestamp(3, DateUtility.getInstance().getCurrentDB2Timestamp());
            preparedStatement.setString(4, partnerAction.getStatus());
             preparedStatement.setString(5, partnerAction.getPartnerIdentifier());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            preparedStatement = null;
          //  preparedStatement = connection.prepareStatement("INSERT INTO TP_DETAILS(TP_ID,TP_NAME,INTERNALIDENTIFIER,APPLICATIONID,STATE) VALUES(?,?,?,?,?)");
              preparedStatement = connection.prepareStatement("UPDATE TP_DETAILS SET TP_NAME=?,INTERNALIDENTIFIER=?,APPLICATIONID=?,STATE=? WHERE TP_ID =?");
            
            preparedStatement.setString(1,partnerAction.getPartnerName());
            preparedStatement.setString(2,partnerAction.getInternalIdentifier());
            preparedStatement.setString(3,partnerAction.getApplicationId());
            preparedStatement.setString(4,partnerAction.getCountryCode());
            preparedStatement.setString(5,partnerAction.getPartnerIdentifier());
             int i=preparedStatement.executeUpdate();
             if(i>0){
                 responseString= "<font color='green'>Partner updated succesfully.</font>";
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
    
    public String addDeliveryChannelInfo(PartnerAction partnerAction) throws ServiceLocatorException {
        try {
            connection = ConnectionProvider.getInstance().getConnection();
      //      preparedStatement = connection.prepareStatement("INSERT INTO DELIVERYCHNNELINFO(PARTNER_ID, ROUTING_ID, SEQUENCE, BUSINESSPROCESSNAME, TRANSLATIONMAP, DOCEXTRACTMAP, ARCHIVEFLAG, ARCHIVEDIRCTORY, OUTPUTFILENAME, OUTPUTFORMAT, PRODUCERMAILBOX, STATUS) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement = connection.prepareStatement("INSERT INTO DELIVERYCHNNELINFO(PARTNER_ID, ROUTING_ID, SEQUENCE, BUSINESSPROCESSNAME, TRANSLATIONMAP, DOCEXTRACTMAP, ARCHIVEFLAG, ARCHIVEDIRCTORY, OUTPUTFILENAME, OUTPUTFORMAT, PRODUCERMAILBOX,STATUS,ENCODING) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, partnerAction.getPartnerId());
           preparedStatement.setInt(2, Integer.parseInt(partnerAction.getRouterId()));
            preparedStatement.setInt(3, partnerAction.getSequence());
            preparedStatement.setString(4, partnerAction.getBusinessProcessId());
            preparedStatement.setInt(5, partnerAction.getTranslationId());
            preparedStatement.setInt(6, partnerAction.getDocumentExtarctId());
            preparedStatement.setInt(7, partnerAction.getArchiveFlag());
            preparedStatement.setString(8, partnerAction.getArchiveDirectory());
            preparedStatement.setString(9, partnerAction.getOutputFileName());
            preparedStatement.setString(10, partnerAction.getOutputFormat());
            preparedStatement.setInt(11, partnerAction.getProducerMailBoxId());
            preparedStatement.setString(12, partnerAction.getStatus());
           preparedStatement.setString(13,partnerAction.getEncodingMailBoxId());
          
             int i=preparedStatement.executeUpdate();
             if(i>0){
                 responseString= "<font color='green'>Delivery channel information added succesfully.</font>";
             }else {
                 responseString= "<font color='red'>Please try again!</font>";
             }
             
            
        }catch (SQLException e) {
			System.out.println("I am in catch block coming in TP IMpl"+e.getMessage());
			// TODO Auto-generated catch block
                        responseString = "<font color='red'>Exception:"+e.getMessage()+"</font>";
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
   public ArrayList<PartnerBean> buildDeliverChannelQuery(PartnerAction partnerAction) throws ServiceLocatorException{  
    ArrayList<PartnerBean> partnerList = new ArrayList<PartnerBean>();
        StringBuffer deliverChannelSearchQuery = new StringBuffer();
        try {
              connection = ConnectionProvider.getInstance().getConnection();
              deliverChannelSearchQuery.append("select DELIVERYCHNNELINFO.DELIVERYCHN_ID,DELIVERYCHNNELINFO.PARTNER_ID as PartnerId,TP.NAME as PartnerName,DELIVERYCHNNELINFO.ROUTING_ID as routingId,ROUTERINFO.ROUTER_NAME as routingName,bp.REL_ID as bpId,bp.RELNAME as bpName,trans.REL_ID as transId,trans.RELNAME as transName,dem.REL_ID as demId,dem.RELNAME as demName,pmb.REL_ID as pmbId,pmb.RELNAME as pmbName,DELEVERYCHANNELDESCRPTION.VALUE as encodingId,DELEVERYCHANNELDESCRPTION.DESCRIPTION as encodingName,SEQUENCE,ARCHIVEFLAG,ARCHIVEDIRCTORY,OUTPUTFILENAME,OUTPUTFORMAT,DELIVERYCHNNELINFO.STATUS  from (((((((DELIVERYCHNNELINFO  JOIN TP on (TP.ID=DELIVERYCHNNELINFO.PARTNER_ID) ) JOIN ROUTERINFO on (ROUTERINFO.ROUTER_ID=DELIVERYCHNNELINFO.ROUTING_ID)) ");
              deliverChannelSearchQuery.append(" JOIN PROCESSRELATEDINFO bp on (bp.REL_ID=DELIVERYCHNNELINFO.BUSINESSPROCESSNAME))");
              deliverChannelSearchQuery.append(" JOIN PROCESSRELATEDINFO trans on (trans.REL_ID=DELIVERYCHNNELINFO.TRANSLATIONMAP))");
              deliverChannelSearchQuery.append(" JOIN PROCESSRELATEDINFO dem on (dem.REL_ID=DELIVERYCHNNELINFO.DOCEXTRACTMAP))");
              deliverChannelSearchQuery.append(" JOIN PROCESSRELATEDINFO pmb on (pmb.REL_ID=DELIVERYCHNNELINFO.PRODUCERMAILBOX))");
              deliverChannelSearchQuery.append(" JOIN DELEVERYCHANNELDESCRPTION on (DELEVERYCHANNELDESCRPTION.VALUE=DELIVERYCHNNELINFO.ENCODING)) WHERE 1=1 ");
              
          /*     if (partnerAction.getPartnerIdentifier() != null && !"".equals(partnerAction.getPartnerIdentifier().trim())) {
			partnerSearchQuery.append(WildCardSql.getWildCardSql1("TP_DETAILS.TP_ID",partnerAction.getPartnerIdentifier().trim().toUpperCase()));
					
		}
               if (partnerAction.getPartnerName() != null && !"".equals(partnerAction.getPartnerName().trim())) {
			partnerSearchQuery.append(WildCardSql.getWildCardSql1("TP_DETAILS.TP_NAME",partnerAction.getPartnerName().trim().toUpperCase()));
					
		} if (partnerAction.getStatus() != null && !"".equals(partnerAction.getStatus().trim())) {
			partnerSearchQuery.append(WildCardSql.getWildCardSql1("TP.STATUS",partnerAction.getStatus().trim().toUpperCase()));
					
		} if (partnerAction.getInternalIdentifier() != null && !"".equals(partnerAction.getInternalIdentifier().trim())) {
			partnerSearchQuery.append(WildCardSql.getWildCardSql1("TP_DETAILS.INTERNALIDENTIFIER",partnerAction.getInternalIdentifier().trim().toUpperCase()));
					
		} if (partnerAction.getCountryCode() != null && !"".equals(partnerAction.getCountryCode().trim())) {
			partnerSearchQuery.append(WildCardSql.getWildCardSql1("TP_DETAILS.STATE",partnerAction.getCountryCode().trim().toUpperCase()));
					
		}if (partnerAction.getApplicationId() != null && !"".equals(partnerAction.getApplicationId().trim())) {
			partnerSearchQuery.append(WildCardSql.getWildCardSql1("TP_DETAILS.APPLICATIONID",partnerAction.getApplicationId().trim().toUpperCase()));
					
		}*/
                
                System.out.println("Query-->"+deliverChannelSearchQuery.toString());
               preparedStatement = connection.prepareStatement(deliverChannelSearchQuery.toString());
               
               resultSet = preparedStatement.executeQuery();
               while(resultSet.next()){
                   PartnerBean partnerBean = new PartnerBean();
                 //  partnerBean.setDocumentChannelId(documentChannelId);
                   partnerBean.setDeliveryChannelId(resultSet.getInt("DELIVERYCHN_ID"));
                   partnerBean.setPartnerId(resultSet.getString("PartnerId"));
                   
                   partnerBean.setPartnerName(resultSet.getString("PartnerName"));
                   partnerBean.setRouterId(resultSet.getString("routingId"));
                   partnerBean.setRoutingName(resultSet.getString("routingName"));
                   partnerBean.setSequence(resultSet.getInt("SEQUENCE"));
                   partnerBean.setBusinessProcessId(resultSet.getString("bpId"));
                   partnerBean.setBusinessProcessName(resultSet.getString("bpName"));
                   
                   partnerBean.setTranslationId(resultSet.getInt("transId"));
                   partnerBean.setTranslationMapName(resultSet.getString("transName"));
                   partnerBean.setDocumentExtarctId(resultSet.getInt("demId"));
                   partnerBean.setDocExtractMapName(resultSet.getString("demName"));
                   partnerBean.setArchiveFlag(resultSet.getInt("ARCHIVEFLAG"));
                   partnerBean.setArchiveDirectory(resultSet.getString("ARCHIVEDIRCTORY"));
                   partnerBean.setOutputFileName(resultSet.getString("OUTPUTFILENAME"));
                   partnerBean.setOutputFormat(resultSet.getString("OUTPUTFORMAT"));
                   partnerBean.setProducerMailBoxId(resultSet.getInt("pmbId"));
                   partnerBean.setProducerMailBox(resultSet.getString("pmbName"));
                   partnerBean.setStatus(resultSet.getString("STATUS"));
                   partnerBean.setEncodingMailBoxId(resultSet.getString("encodingId"));
                   partnerBean.setEncodingMailBoxName(resultSet.getString("encodingName"));
                 //  partnerBean.setCreatedBy(resultSet.getString("CREATED_BY"));
                  
                  partnerList.add(partnerBean);
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
        
        return partnerList;
   }
   
    public PartnerAction deliveryChannelEdit(PartnerAction partnerAction) throws ServiceLocatorException {
          StringBuffer deliverChannelSearchQuery = new StringBuffer();
        //PartnerBean partnerBean = new PartnerBean();
        try {
             connection = ConnectionProvider.getInstance().getConnection();
                 deliverChannelSearchQuery.append("select DELIVERYCHNNELINFO.DELIVERYCHN_ID,DELIVERYCHNNELINFO.PARTNER_ID as PartnerId,TP.NAME as PartnerName,DELIVERYCHNNELINFO.ROUTING_ID as routingId,ROUTERINFO.ROUTER_NAME as routingName,bp.REL_ID as bpId,bp.RELNAME as bpName,trans.REL_ID as transId,trans.RELNAME as transName,dem.REL_ID as demId,dem.RELNAME as demName,pmb.REL_ID as pmbId,pmb.RELNAME as pmbName,DELEVERYCHANNELDESCRPTION.VALUE as encodingId,DELEVERYCHANNELDESCRPTION.DESCRIPTION as encodingName,SEQUENCE,ARCHIVEFLAG,ARCHIVEDIRCTORY,OUTPUTFILENAME,OUTPUTFORMAT,DELIVERYCHNNELINFO.STATUS   from (((((((DELIVERYCHNNELINFO  JOIN TP on (TP.ID=DELIVERYCHNNELINFO.PARTNER_ID) ) JOIN ROUTERINFO on (ROUTERINFO.ROUTER_ID=DELIVERYCHNNELINFO.ROUTING_ID)) ");
              deliverChannelSearchQuery.append(" JOIN PROCESSRELATEDINFO bp on (bp.REL_ID=DELIVERYCHNNELINFO.BUSINESSPROCESSNAME))");
              deliverChannelSearchQuery.append(" JOIN PROCESSRELATEDINFO trans on (trans.REL_ID=DELIVERYCHNNELINFO.TRANSLATIONMAP))");
              deliverChannelSearchQuery.append(" JOIN PROCESSRELATEDINFO dem on (dem.REL_ID=DELIVERYCHNNELINFO.DOCEXTRACTMAP))");
              deliverChannelSearchQuery.append(" JOIN PROCESSRELATEDINFO pmb on (pmb.REL_ID=DELIVERYCHNNELINFO.PRODUCERMAILBOX))");
              deliverChannelSearchQuery.append(" JOIN DELEVERYCHANNELDESCRPTION on (DELEVERYCHANNELDESCRPTION.VALUE=DELIVERYCHNNELINFO.ENCODING)) WHERE DELIVERYCHN_ID="+partnerAction.getDeliveryChannelId());
               System.out.println("queryString--> "+deliverChannelSearchQuery.toString());
               System.out.println("Query-->"+deliverChannelSearchQuery.toString());
               preparedStatement = connection.prepareStatement(deliverChannelSearchQuery.toString());
               
               resultSet = preparedStatement.executeQuery();
             
             if(resultSet.next()){
                  partnerAction.setDeliveryChannelId(resultSet.getInt("DELIVERYCHN_ID"));
             partnerAction.setPartnerName(resultSet.getString("PartnerName"));
             partnerAction.setPartnerId(resultSet.getString("PartnerId"));
                  
                 partnerAction.setRoutingName(resultSet.getString("routingName"));
                  partnerAction.setRouterId(resultSet.getString("routingId"));
                  partnerAction.setSequence(resultSet.getInt("SEQUENCE"));
                  partnerAction.setBusinessProcessName(resultSet.getString("bpName"));
                  partnerAction.setBusinessProcessId(resultSet.getString("bpId"));
                  partnerAction.setTranslationMapName(resultSet.getString("transName"));
                  partnerAction.setTranslationId(resultSet.getInt("transId"));
                  partnerAction.setDocExtractMapName(resultSet.getString("demName"));
                  partnerAction.setDocumentExtarctId(resultSet.getInt("demId"));
                     partnerAction.setArchiveFlag(resultSet.getInt("ARCHIVEFLAG"));
                   partnerAction.setArchiveDirectory(resultSet.getString("ARCHIVEDIRCTORY"));
                   partnerAction.setOutputFileName(resultSet.getString("OUTPUTFILENAME"));
                   partnerAction.setOutputFormat(resultSet.getString("OUTPUTFORMAT"));
                   partnerAction.setProducerMailBoxId(resultSet.getInt("pmbId"));
                   partnerAction.setProducerMailBox(resultSet.getString("pmbName"));
                   partnerAction.setStatus(resultSet.getString("STATUS"));
                   partnerAction.setEncodingMailBoxId(resultSet.getString("encodingId"));
                   partnerAction.setEncodingMailBoxName(resultSet.getString("encodingName"));
                  
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
        return partnerAction;
    }
    
    public String editDeliveryChannel(PartnerAction partnerAction) throws ServiceLocatorException{
         try {
            connection = ConnectionProvider.getInstance().getConnection();
      //      preparedStatement = connection.prepareStatement("INSERT INTO DELIVERYCHNNELINFO(PARTNER_ID, ROUTING_ID, SEQUENCE, BUSINESSPROCESSNAME, TRANSLATIONMAP, DOCEXTRACTMAP, ARCHIVEFLAG, ARCHIVEDIRCTORY, OUTPUTFILENAME, OUTPUTFORMAT, PRODUCERMAILBOX, STATUS) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
         //   preparedStatement = connection.prepareStatement("INSERT INTO DELIVERYCHNNELINFO(PARTNER_ID, ROUTING_ID, SEQUENCE, BUSINESSPROCESSNAME, TRANSLATIONMAP, DOCEXTRACTMAP, ARCHIVEFLAG, ARCHIVEDIRCTORY, OUTPUTFILENAME, OUTPUTFORMAT, PRODUCERMAILBOX,STATUS,ENCODING) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
               preparedStatement = connection.prepareStatement("UPDATE DELIVERYCHNNELINFO SET PARTNER_ID =?, ROUTING_ID = ?, SEQUENCE=?, BUSINESSPROCESSNAME=?, TRANSLATIONMAP=?, DOCEXTRACTMAP=?, ARCHIVEFLAG=?, ARCHIVEDIRCTORY=?, OUTPUTFILENAME=?, OUTPUTFORMAT=?, PRODUCERMAILBOX=?,STATUS=?,ENCODING=? WHERE DELIVERYCHN_ID=?");
            preparedStatement.setString(1, partnerAction.getPartnerId());
           preparedStatement.setInt(2, Integer.parseInt(partnerAction.getRouterId()));
            preparedStatement.setInt(3, partnerAction.getSequence());
            preparedStatement.setString(4, partnerAction.getBusinessProcessId());
            preparedStatement.setInt(5, partnerAction.getTranslationId());
            preparedStatement.setInt(6, partnerAction.getDocumentExtarctId());
            preparedStatement.setInt(7, partnerAction.getArchiveFlag());
            preparedStatement.setString(8, partnerAction.getArchiveDirectory());
            preparedStatement.setString(9, partnerAction.getOutputFileName());
            preparedStatement.setString(10, partnerAction.getOutputFormat());
            preparedStatement.setInt(11, partnerAction.getProducerMailBoxId());
            preparedStatement.setString(12, partnerAction.getStatus());
           preparedStatement.setString(13,partnerAction.getEncodingMailBoxId());
          preparedStatement.setInt(14,partnerAction.getDeliveryChannelId());
             int i=preparedStatement.executeUpdate();
             if(i>0){
                 responseString= "<font color='green'>Delivery channel information updated succesfully.</font>";
             }else {
                 responseString= "<font color='red'>Please try again!</font>";
             }
             
            
        }catch (SQLException e) {
			System.out.println("I am in catch block coming in TP IMpl"+e.getMessage());
			// TODO Auto-generated catch block
                        responseString = "<font color='red'>Exception:"+e.getMessage()+"</font>";
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
