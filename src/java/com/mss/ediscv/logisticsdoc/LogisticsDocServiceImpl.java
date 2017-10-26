/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.logisticsdoc;

import com.mss.ediscv.util.ConnectionProvider;
import com.mss.ediscv.util.DateUtility;
import com.mss.ediscv.util.ServiceLocatorException;
import com.mss.ediscv.util.WildCardSql;
import java.sql.*;
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
public class LogisticsDocServiceImpl implements LogisticsDocService {

    Connection connection = null;
    Statement statement = null;
    Statement statement1 = null;
    Statement statement2 = null;
    Statement statement3 = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSet resultSet1 = null;
    ResultSet resultSet2 = null;
    ResultSet resultSet3 = null;
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
    private ArrayList<LogisticsDocBean> documentList;
    private LogisticsDocBean logisticsBean;
    //Map<String,String> keyValue=new HashMap<String,String>();

    private static Logger logger = Logger.getLogger(com.mss.ediscv.logisticsdoc.LogisticsDocServiceImpl.class
            .getName());

    public ArrayList<LogisticsDocBean> buildDocumentQuery(LogisticsDocAction logisticsDocAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException {
        StringBuffer documentSearchQuery = new StringBuffer();
        logger.info("Entered into the :::: PurchaseOrderServiceImpl :::: buildPurchaseQuery");

        String docdatepicker = logisticsDocAction.getDocdatepicker();
        String docdatepickerfrom = logisticsDocAction.getDocdatepickerfrom();
        String docSenderId = "";
        if (logisticsDocAction.getDocSenderId() != null && !"".equals(logisticsDocAction.getDocSenderId().trim())) {
            docSenderId = logisticsDocAction.getDocSenderId();
        }
        String docSenderName = "";
        if (logisticsDocAction.getDocSenderName() != null && !"".equals(logisticsDocAction.getDocSenderName().trim())) {
            docSenderName = logisticsDocAction.getDocSenderName();
        }
        String docBusId = "";
        if (logisticsDocAction.getDocBusId() != null && !"".equals(logisticsDocAction.getDocBusId().trim())) {
            docBusId = logisticsDocAction.getDocBusId();
        }
        String docRecName = "";
        if (logisticsDocAction.getDocRecName() != null && !"".equals(logisticsDocAction.getDocRecName().trim())) {
            docRecName = logisticsDocAction.getDocRecName();
        }
        String docIsa = "";
        if (logisticsDocAction.getDocIsa() != null && !"".equals(logisticsDocAction.getDocIsa().trim())) {
            docIsa = logisticsDocAction.getDocIsa();
        }
        String doctype = "";
        if (logisticsDocAction.getDocType() != null && !logisticsDocAction.getDocType().equals("-1")) {
            doctype = logisticsDocAction.getDocType();
        }

        String corrattribute = logisticsDocAction.getCorrattribute();
        String corrvalue = logisticsDocAction.getCorrvalue();
        String corrattribute1 = logisticsDocAction.getCorrattribute1();
        String corrvalue1 = logisticsDocAction.getCorrvalue1();
        String corrattribute2 = logisticsDocAction.getCorrattribute2();
        String corrvalue2 = logisticsDocAction.getCorrvalue2();

        String status = logisticsDocAction.getStatus();
        String ackStatus = logisticsDocAction.getAckStatus();
        /*String docPoNum= docRepositoryAction.getDocPoNum();
         String ponum= docRepositoryAction.getPonumber();
         String asnnum= docRepositoryAction.getAsnnumber();
         String invnum= docRepositoryAction.getInvnumber();
         String bolnum= docRepositoryAction.getBolNum();
         String chequeNum = docRepositoryAction.getChequeNum();*/

        documentSearchQuery.append("SELECT DISTINCT TOP 500 (FILES.FILE_ID) as FILE_ID,FILES.ID as ID,"
                + "FILES.ISA_NUMBER as ISA_NUMBER,FILES.FILE_TYPE as FILE_TYPE,FILES.CARRIER_STATUS,FILES.PRI_KEY_VAL  as PRI_KEY_VAL,FILES.SENDER_ID,FILES.RECEIVER_ID,"
                + "FILES.FILE_ORIGIN as FILE_ORIGIN,FILES.TRANSACTION_TYPE as TRANSACTION_TYPE,FILES.TMW_SENDERID as TMW_SENDERID,FILES.TMW_RECEIVERID as TMW_RECEIVERID,"
                + "FILES.DIRECTION as DIRECTION,FILES.DATE_TIME_RECEIVED as DATE_TIME_RECEIVED,"
                + "FILES.STATUS as STATUS,FILES.ACK_STATUS as ACK_STATUS,TP2.NAME as RECEIVER_NAME,TP1.NAME as SENDER_NAME,trsrsp.REF_ID,"
                + "FILES.SEC_KEY_VAL,FILES.REPROCESSSTATUS,FILES.FILENAME,trsrsp.RESPONSE_STATUS,FILES.TRANSACTION_PURPOSE FROM FILES "
                //+ "FROM FILES LEFT OUTER JOIN Transport_loadtender ten on (ten.FILE_ID=FILES.FILE_ID and ten.SHIPMENT_ID=FILES.PRI_KEY_VAL) "
                + "LEFT OUTER JOIN TRANSPORT_LT_RESPONSE trsrsp on (trsrsp.FILE_ID=FILES.FILE_ID)"
                //                          + " LEFT OUTER JOIN TP TP1 "
                //                          + "ON (TP1.ID=FILES.TMW_SENDERID) LEFT OUTER JOIN TP TP2 ON (TP2.ID=FILES.TMW_RECEIVERID)");
                + " LEFT OUTER JOIN TP TP1 ON (TP1.ID=FILES.TMW_SENDERID) "
                + " LEFT OUTER JOIN TP TP2 ON (TP2.ID=FILES.TMW_RECEIVERID)"
                + " LEFT OUTER JOIN TP TP3 ON (TP3.ID=FILES.SENDER_ID)"
                + " LEFT OUTER JOIN TP TP4 ON (TP4.ID=FILES.RECEIVER_ID)");
        //raja
        documentSearchQuery.append(" WHERE FLOWFLAG LIKE '%L%' ");
        //LogisticsDocBean logisticsdocBean1 = new LogisticsDocBean();
        if (corrattribute != null && corrattribute.equalsIgnoreCase("Order Number")) // || corrattribute.equalsIgnoreCase("Invoice Number")  || corrattribute.equalsIgnoreCase("Shipment Number") || corrattribute.equalsIgnoreCase("Cheque Number") )
        {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                System.out.println("ordernumber is "+corrvalue);
                Connection con = ConnectionProvider.getInstance().getConnection();
                try {
                    
                    statement2 = con.createStatement();
                    //String queryPriKey = "SELECT PRI_KEY_VAL,TRANSACTION_TYPE FROM FILES WHERE SEC_KEY_VAL='"+corrvalue+"'";
                    String queryPriKey = "SELECT PRI_KEY_VAL, TRANSACTION_TYPE FROM FILES WHERE PRI_KEY_VAL = (SELECT MAX(PRI_KEY_VAL) FROM FILES WHERE SEC_KEY_VAL ='" + corrvalue + "')";
                    System.out.println("query --> " + queryPriKey);
                    resultSet2 = statement2.executeQuery(queryPriKey);
                    System.out.println("in ordernumbe try");
                    if(resultSet2.next()){

                        System.out.println("resultSet2.next()");
                        System.out.println("resultSet2.next() "+resultSet2.getString("TRANSACTION_TYPE"));
                        System.out.println("resultSet2.next() "+resultSet2.getString("PRI_KEY_VAL"));
                        
                        if (resultSet2.getString("TRANSACTION_TYPE").equalsIgnoreCase("204")) {
                            System.out.println(" 204 TRANSACTION_TYPE" + resultSet2.getString("TRANSACTION_TYPE"));
                            documentSearchQuery.append("AND (FILES.PRI_KEY_VAL='"+resultSet2.getString("PRI_KEY_VAL")+"' OR FILES.PRI_KEY_VAL='"+corrvalue.trim().toUpperCase()+"')");
//                            documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.PRI_KEY_VAL",
//                                    resultSet2.getString("PRI_KEY_VAL")));
                            System.out.println("204 appended");
                            
                        } else{
                            System.out.println(" other TRANSACTION_TYPE" + resultSet2.getString("TRANSACTION_TYPE"));
//                            documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.SEC_KEY_VAL",
//                                    corrvalue.trim().toUpperCase()));
                            documentSearchQuery.append("AND (FILES.SEC_KEY_TYPE='"+resultSet2.getString("PRI_KEY_VAL")+"' OR FILES.SEC_KEY_TYPE='"+corrvalue.trim().toUpperCase()+"')" );
                            //documentSearchQuery.append("AND FILES.SEC_KEY_TYPE='ORDERNUMBER'");
                            System.out.println("other appended");
                        }
                  
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (resultSet2 != null) {
                            resultSet2.close();
                            resultSet2 = null;
                        }
//                        if (resultSet3 != null) {
//                            resultSet3.close();
//                            resultSet3 = null;
//                        }
                        if (statement2 != null) {
                            statement2.close();
                            statement2 = null;
                        }
//                        if (statement3 != null) {
//                            statement3.close();
//                            statement3 = null;
//                        }
                        if (con != null) {
                            con.close();
                            con = null;
                        }
                    } catch (SQLException se) {
                        throw new ServiceLocatorException(se);
                    }
                }
            }
        }

        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("Order Number")) // || corrattribute.equalsIgnoreCase("Invoice Number")  || corrattribute.equalsIgnoreCase("Shipment Number") || corrattribute.equalsIgnoreCase("Cheque Number") )
        {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                Connection con = ConnectionProvider.getInstance().getConnection();
                try {
                    System.out.println("in ordernumbe try");
                    
                    System.out.println("con " + con);
                    statement2 = con.createStatement();
                    //String query = "SELECT PRI_KEY_VAL,TRANSACTION_TYPE  from FILES WHERE SEC_KEY_TYPE='ORDERNUMBER' AND SEC_KEY_VAL ='" + corrvalue + "'";
                    String query = "SELECT PRI_KEY_VAL, TRANSACTION_TYPE FROM FILES WHERE PRI_KEY_VAL = (SELECT MAX(PRI_KEY_VAL) FROM FILES WHERE SEC_KEY_VAL ='" + corrvalue1 + "')";
                    //String query = "SELECT PRI_KEY_VAL,TRANSACTION_TYPE FROM FILES WHERE SEC_KEY_VAL='"+corrvalue1+"'";
                    System.out.println("query --> " + query);
                    resultSet2 = statement2.executeQuery(query);
                    if (resultSet2.next()) {
                        if (resultSet2.getString("TRANSACTION_TYPE").equals("204")) {
                            System.out.println(" 204 TRANSACTION_TYPE" + resultSet2.getString("TRANSACTION_TYPE"));
                            documentSearchQuery.append("AND (FILES.PRI_KEY_VAL='"+resultSet2.getString("PRI_KEY_VAL")+"' OR FILES.PRI_KEY_VAL='"+corrvalue1.trim().toUpperCase()+"')");
//                            documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.PRI_KEY_VAL",
//                                    resultSet2.getString("PRI_KEY_VAL")));
                            System.out.println("204 appended");
                        } else {
                            System.out.println(" other TRANSACTION_TYPE" + resultSet2.getString("TRANSACTION_TYPE"));
//                            documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.SEC_KEY_VAL",
//                                    corrvalue1.trim().toUpperCase()));
                            documentSearchQuery.append("AND (FILES.SEC_KEY_TYPE='"+resultSet2.getString("PRI_KEY_VAL")+"' OR FILES.SEC_KEY_TYPE='"+corrvalue1.trim().toUpperCase()+"')" );
                            //documentSearchQuery.append("AND FILES.SEC_KEY_TYPE='ORDERNUMBER'");
                            System.out.println("other appended");
                        }
                    }
                    //resultSet2.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    try {
                        if (resultSet2 != null) {
                            resultSet2.close();
                            resultSet2 = null;
                        }
                        if (statement2 != null) {
                            statement2.close();
                            statement2 = null;
                        }
                        if (con != null) {
                            con.close();
                            con = null;
                        }
                    } catch (SQLException se) {
                        throw new ServiceLocatorException(se);
                    }
                }
            }
        }

        if (corrattribute2 != null && corrattribute2.equalsIgnoreCase("Order Number")) // || corrattribute.equalsIgnoreCase("Invoice Number")  || corrattribute.equalsIgnoreCase("Shipment Number") || corrattribute.equalsIgnoreCase("Cheque Number") )
        {
            if (corrvalue2 != null && !"".equals(corrvalue2.trim())) {
                Connection con = ConnectionProvider.getInstance().getConnection();
                try {
                    System.out.println("in ordernumbe try");
                    
                    System.out.println("con " + con);
                    statement2 = con.createStatement();
                    //String query = "SELECT PRI_KEY_VAL,TRANSACTION_TYPE  from FILES WHERE SEC_KEY_TYPE='ORDERNUMBER' AND SEC_KEY_VAL ='" + corrvalue + "'";
                    String query = "SELECT PRI_KEY_VAL, TRANSACTION_TYPE FROM FILES WHERE PRI_KEY_VAL = (SELECT MAX(PRI_KEY_VAL) FROM FILES WHERE SEC_KEY_VAL ='" + corrvalue2 + "')";
                    //String query = "SELECT PRI_KEY_VAL,TRANSACTION_TYPE FROM FILES WHERE SEC_KEY_VAL='"+corrvalue2+"'";
                    System.out.println("query --> " + query);
                    resultSet2 = statement2.executeQuery(query);
                    if (resultSet2.next()) {
                        if (resultSet2.getString("TRANSACTION_TYPE").equals("204")) {
                            System.out.println(" 204 TRANSACTION_TYPE" + resultSet2.getString("TRANSACTION_TYPE"));
                            documentSearchQuery.append("AND (FILES.PRI_KEY_VAL='"+resultSet2.getString("PRI_KEY_VAL")+"' OR FILES.PRI_KEY_VAL='"+corrvalue2.trim().toUpperCase()+"')");
//                            documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.PRI_KEY_VAL",
//                                    resultSet2.getString("PRI_KEY_VAL")));
                            System.out.println("204 appended");
                        } else {
                            System.out.println(" other TRANSACTION_TYPE" + resultSet2.getString("TRANSACTION_TYPE"));
//                            documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.SEC_KEY_VAL",
//                                    corrvalue2.trim().toUpperCase()));
                            documentSearchQuery.append("AND (FILES.SEC_KEY_TYPE='"+resultSet2.getString("PRI_KEY_VAL")+"' OR FILES.SEC_KEY_TYPE='"+corrvalue2.trim().toUpperCase()+"')" );
                            //documentSearchQuery.append("AND FILES.SEC_KEY_TYPE='ORDERNUMBER'");
                            System.out.println("other appended");
                        }
                    }
                    //resultSet2.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    try {
                        if (resultSet2 != null) {
                            resultSet2.close();
                            resultSet2 = null;
                        }
                        if (statement2 != null) {
                            statement2.close();
                            statement2 = null;
                        }
                        if (con != null) {
                            con.close();
                            con = null;
                        }
                    } catch (SQLException se) {
                        throw new ServiceLocatorException(se);
                    }
                }
            }
        }

        // For Invoice / Shipment / Chequecorrattribute2.equalsIgnoreCase("Invoice Number")  || 
        //if(corrattribute1.equalsIgnoreCase("PO Number") || corrattribute1.equalsIgnoreCase("Invoice Number")  || corrattribute1.equalsIgnoreCase("Shipment Number") || corrattribute1.equalsIgnoreCase("Cheque Number") )
        if (corrattribute != null && corrattribute.equalsIgnoreCase("Shipment Number")) {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.PRI_KEY_VAL",
                        corrvalue.trim().toUpperCase()));
            }
        }

        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("Shipment Number")) {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.PRI_KEY_VAL",
                        corrvalue1.trim().toUpperCase()));
            }
        }

        if (corrattribute2 != null && corrattribute2.equalsIgnoreCase("Shipment Number")) {
            if (corrvalue2 != null && !"".equals(corrvalue2.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.PRI_KEY_VAL",
                        corrvalue2.trim().toUpperCase()));
            }
        }

        if (corrattribute != null && corrattribute.equalsIgnoreCase("Invoice Number")) {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.SEC_KEY_VAL",
                        corrvalue.trim().toUpperCase()));
            }
        }

        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("Invoice Number")) {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.SEC_KEY_VAL",
                        corrvalue1.trim().toUpperCase()));
            }
        }

        if (corrattribute2 != null && corrattribute2.equalsIgnoreCase("Invoice Number")) {
            if (corrvalue2 != null && !"".equals(corrvalue2.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.SEC_KEY_VAL",
                        corrvalue2.trim().toUpperCase()));
            }
        }
        // isa 

        if (corrattribute != null && corrattribute.equalsIgnoreCase("ISA Number")) {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.ISA_Number",
                        corrvalue.trim().toUpperCase()));
            }
        }

        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("ISA Number")) {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.ISA_Number",
                        corrvalue1.trim().toUpperCase()));
            }
        }

        if (corrattribute2 != null && corrattribute2.equalsIgnoreCase("ISA Number")) {
            if (corrvalue2 != null && !"".equals(corrvalue2.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.ISA_Number",
                        corrvalue2.trim().toUpperCase()));
            }
        }

        // GS number
        // isa 
        if (corrattribute != null && corrattribute.equalsIgnoreCase("GS Number")) {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.GS_CONTROL_Number",
                        corrvalue.trim().toUpperCase()));
            }
        }

        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("GS Number")) {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.GS_CONTROL_Number",
                        corrvalue1.trim().toUpperCase()));
            }
        }

        if (corrattribute2 != null && corrattribute2.equalsIgnoreCase("GS Number")) {
            if (corrvalue2 != null && !"".equals(corrvalue2.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.GS_CONTROL_Number",
                        corrvalue2.trim().toUpperCase()));
            }
        }
        // -------------------------------------
        // bol
        if (corrattribute != null && corrattribute.equalsIgnoreCase("Instance ID")) {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.FILE_ID",
                        corrvalue.trim().toUpperCase()));
            }
        }
        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("Instance ID")) {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.FILE_ID",
                        corrvalue1.trim().toUpperCase()));
            }
        }
        if (corrattribute2 != null && corrattribute2.equalsIgnoreCase("Instance ID")) {
            if (corrvalue2 != null && !"".equals(corrvalue2.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.FILE_ID",
                        corrvalue2.trim().toUpperCase()));
            }
        }

        // end -----------------------------------------CO
        //file name
        if (corrattribute != null && corrattribute.equalsIgnoreCase("FILE NAME")) {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.FILENAME",
                        corrvalue.trim().toUpperCase()));
            }
        }
        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("FILE NAME")) {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.FILENAME",
                        corrvalue1.trim().toUpperCase()));
            }
        }
        if (corrattribute2 != null && corrattribute2.equalsIgnoreCase("FILE NAME")) {
            if (corrvalue2 != null && !"".equals(corrvalue2.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.FILENAME",
                        corrvalue2.trim().toUpperCase()));
            }
        }
        //end --------------------------------------------filename
        if (doctype != null && !"".equals(doctype.trim())) {
            documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.TRANSACTION_TYPE",
                    doctype.trim()));
        }
        //Status
        if (status != null && !"-1".equals(status.trim())) {
            documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.STATUS",
                    status.trim()));
        }
        //ACK_STATUS
        if (ackStatus != null && !"-1".equals(ackStatus.trim())) {
            documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.ACK_STATUS",
                    ackStatus.trim()));
        }
        // gs number
        if (corrattribute != null && corrattribute.equalsIgnoreCase("GS Number")) {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.GS_CONTROL_NUMBER",
                        corrvalue.trim().toUpperCase()));
            }
        }
        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("GS Number")) {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.GS_CONTROL_NUMBER",
                        corrvalue1.trim().toUpperCase()));
            }
        }
        if (corrattribute2 != null && corrattribute2.equalsIgnoreCase("GS Number")) {
            if (corrvalue2 != null && !"".equals(corrvalue2.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.GS_CONTROL_NUMBER",
                        corrvalue2.trim().toUpperCase()));
            }
        }

//                if (docBusId != null && !"".equals(docBusId.trim())) {
//			documentSearchQuery.append(WildCardSql.getWildCardSql1("TP2.ID",
//					docBusId.trim().toUpperCase()));
//		}
        if (docBusId != null && !"".equals(docBusId.trim())) {
            documentSearchQuery.append(" AND (FILES.RECEIVER_ID like '%" + docBusId + "%' OR FILES.TMW_RECEIVERID like '%" + docBusId + "%')");
        }

//                
//                if (docSenderId != null && !"".equals(docSenderId.trim())) {
//			documentSearchQuery.append(WildCardSql.getWildCardSql1("TP1.ID",
//					docSenderId.trim().toUpperCase()));
//		}
        if (docSenderId != null && !"".equals(docSenderId.trim())) {
            documentSearchQuery.append(" AND (FILES.SENDER_ID like '%" + docSenderId + "%' OR FILES.TMW_SENDERID like '%" + docSenderId + "%')");
        }

//                  if (docSenderName != null && !"".equals(docSenderName.trim())) {
//			documentSearchQuery.append(WildCardSql.getWildCardSql1("TP1.NAME",
//					docSenderName.trim().toUpperCase()));
//		}
        if (docSenderName != null && !"".equals(docSenderName.trim())) {

            documentSearchQuery.append(" AND (TP3.NAME like '%" + docSenderName + "%' OR TP1.NAME like '%" + docSenderName + "%')");

        }

//                if (docRecName != null && !"".equals(docRecName.trim())) {
//			documentSearchQuery.append(WildCardSql.getWildCardSql1("TP2.NAME",
//					docRecName.trim().toUpperCase()));
//		}
        if (docRecName != null && !"".equals(docRecName.trim())) {
            documentSearchQuery.append(" AND (TP4.NAME like '%" + docRecName + "%' OR TP2.NAME like '%" + docRecName + "%')");

        }

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
        documentSearchQuery.append(" order by DATE_TIME_RECEIVED DESC");
      
        // documentSearchQuery.append(" order by DATE_TIME_RECEIVED DESC fetch first 50 rows only");        
        // documentSearchQuery.append(" WITH UR");
        System.out.println("DOC LDSI queryquery-->" + documentSearchQuery.toString());
        String searchQuery = documentSearchQuery.toString();
        //System.out.println("documentSearchQuery"+documentSearchQuery.toString());

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            statement = connection.createStatement();
            statement1 = connection.createStatement();

            resultSet = statement.executeQuery(searchQuery);

            documentList = new ArrayList<LogisticsDocBean>();

            while (resultSet.next()) {
                LogisticsDocBean logisticsdocBean = new LogisticsDocBean();
                logisticsdocBean.setId(resultSet.getInt("ID"));
                logisticsdocBean.setFile_id(resultSet.getString("FILE_ID"));
                logisticsdocBean.setFile_origin(resultSet.getString("FILE_ORIGIN"));
                logisticsdocBean.setFile_type(resultSet.getString("FILE_TYPE"));
                logisticsdocBean.setIsa_number(resultSet.getString("ISA_NUMBER"));
                logisticsdocBean.setTransaction_type(resultSet.getString("TRANSACTION_TYPE"));

                String Direction = resultSet.getString("DIRECTION");
                logisticsdocBean.setDirection(Direction);
                logisticsdocBean.setDate_time_rec(resultSet.getTimestamp("DATE_TIME_RECEIVED"));
                logisticsdocBean.setStatus(resultSet.getString("STATUS"));

                if (Direction.equalsIgnoreCase("INBOUND")) {
                    logisticsdocBean.setPname(resultSet.getString("SENDER_NAME"));
                }
                if (Direction.equalsIgnoreCase("OUTBOUND")) {
                    logisticsdocBean.setPname(resultSet.getString("RECEIVER_NAME"));
                }
                //logisticsdocBean.setPname(resultSet.getString("SENDER_NAME"));
                //logisticsdocBean.setPoNumber(resultSet.getString("SEC_KEY_VAL"));
                logisticsdocBean.setReProcessStatus(resultSet.getString("REPROCESSSTATUS"));

                if (resultSet.getString("TRANSACTION_TYPE").equalsIgnoreCase("204")) {
                    logisticsdocBean.setTransactionStatus(resultSet.getString("TRANSACTION_PURPOSE"));
                } else if (resultSet.getString("TRANSACTION_TYPE").equalsIgnoreCase("214")) {
                    logisticsdocBean.setTransactionStatus(resultSet.getString("CARRIER_STATUS"));
                    //System.out.println("CARRIER_STATUS"+resultSet.getString("CARRIER_STATUS"));
                } else if (resultSet.getString("TRANSACTION_TYPE").equalsIgnoreCase("990")) {
                    logisticsdocBean.setTransactionStatus(resultSet.getString("RESPONSE_STATUS"));
                }

                logisticsdocBean.setAckStatus(resultSet.getString("ACK_STATUS"));
                logisticsdocBean.setFile_name(resultSet.getString("FILENAME"));
                logisticsdocBean.setShipmentId(resultSet.getString("PRI_KEY_VAL"));
                if (resultSet.getString("TRANSACTION_TYPE").equalsIgnoreCase("204")) {
                    if (resultSet.getString("direction").equalsIgnoreCase("INBOUND")) {
                        String queryString = "SELECT SEC_KEY_VAL as orderNum FROM FILES WHERE PRI_KEY_VAL='" + resultSet.getString("PRI_KEY_VAL") + "' AND SEC_KEY_TYPE='ORDERNUMBER'";
                        System.out.println("query string is " + queryString);
                        resultSet1 = statement1.executeQuery(queryString);
                        if (resultSet1.next()) {
                            //System.out.println("resultSet1.next()");
                            if (resultSet1.getString("orderNum") != null) {
                                //System.out.println("resultSet1.getString(orderNum)");
                                logisticsdocBean.setOrdernum(resultSet1.getString("orderNum"));
                            } else {
                                //System.out.println("resultSet1 else");
                                logisticsdocBean.setOrdernum("-");
                            }
                            //System.out.println("resultSet1" + resultSet1.getString("orderNum"));
                            //System.out.println("getOrdernum()" + logisticsdocBean.getOrdernum());
                            //resultSet1.close();

                        } else {
                            //System.out.println("result set empty");
                            logisticsdocBean.setOrdernum("-");
                        }
                    } else {
                        logisticsdocBean.setOrdernum(resultSet.getString("SEC_KEY_VAL"));
                    }
                } else {
                    logisticsdocBean.setOrdernum(resultSet.getString("SEC_KEY_VAL"));
                }
                //System.out.println("logisticsdocBean.getOrdernum();" + logisticsdocBean.getOrdernum());
                logisticsdocBean.setTmwSenderid(resultSet.getString("TMW_SENDERID"));
                logisticsdocBean.setTmwReceivererid(resultSet.getString("TMW_RECEIVERID"));
                //System.out.println("the secondry key value iss-------->"+resultSet.getString("SEC_KEY_VAL"));
                documentList.add(logisticsdocBean);

            }

        } catch (SQLException e) {
            //System.out.println("I am in catch block coming in IMpl");
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception ex) {
            //System.out.println("hi"+ex.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (statement1 != null) {
                    statement1.close();
                    statement1 = null;
                }
                if (statement2 != null) {
                    statement2.close();
                    statement2 = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }
        //System.out.println("Length--->"+documentList.size());
        return documentList;
    }
    
    public ArrayList<LogisticsDocBean> buildDocumentArchiveQuery(LogisticsDocAction logisticsDocAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException {
        StringBuffer documentSearchQuery = new StringBuffer();
        logger.info("Entered into the :::: PurchaseOrderServiceImpl :::: buildPurchaseQuery");
        System.out.println("In Doc Search Archive Query");
        String docdatepicker = logisticsDocAction.getDocdatepicker();
        String docdatepickerfrom = logisticsDocAction.getDocdatepickerfrom();
        String docSenderId = "";
        if (logisticsDocAction.getDocSenderId() != null && !"".equals(logisticsDocAction.getDocSenderId().trim())) {
            docSenderId = logisticsDocAction.getDocSenderId();
        }
        String docSenderName = "";
        if (logisticsDocAction.getDocSenderName() != null && !"".equals(logisticsDocAction.getDocSenderName().trim())) {
            docSenderName = logisticsDocAction.getDocSenderName();
        }
        String docBusId = "";
        if (logisticsDocAction.getDocBusId() != null && !"".equals(logisticsDocAction.getDocBusId().trim())) {
            docBusId = logisticsDocAction.getDocBusId();
        }
        String docRecName = "";
        if (logisticsDocAction.getDocRecName() != null && !"".equals(logisticsDocAction.getDocRecName().trim())) {
            docRecName = logisticsDocAction.getDocRecName();
        }
        String docIsa = "";
        if (logisticsDocAction.getDocIsa() != null && !"".equals(logisticsDocAction.getDocIsa().trim())) {
            docIsa = logisticsDocAction.getDocIsa();
        }
        String doctype = "";
        if (logisticsDocAction.getDocType() != null && !logisticsDocAction.getDocType().equals("-1")) {
            doctype = logisticsDocAction.getDocType();
        }

        String corrattribute = logisticsDocAction.getCorrattribute();
        String corrvalue = logisticsDocAction.getCorrvalue();
        String corrattribute1 = logisticsDocAction.getCorrattribute1();
        String corrvalue1 = logisticsDocAction.getCorrvalue1();
        String corrattribute2 = logisticsDocAction.getCorrattribute2();
        String corrvalue2 = logisticsDocAction.getCorrvalue2();

        String status = logisticsDocAction.getStatus();
        String ackStatus = logisticsDocAction.getAckStatus();
        /*String docPoNum= docRepositoryAction.getDocPoNum();
         String ponum= docRepositoryAction.getPonumber();
         String asnnum= docRepositoryAction.getAsnnumber();
         String invnum= docRepositoryAction.getInvnumber();
         String bolnum= docRepositoryAction.getBolNum();
         String chequeNum = docRepositoryAction.getChequeNum();*/

        documentSearchQuery.append("SELECT DISTINCT TOP 500 (ARCHIVE_FILES.FILE_ID) as FILE_ID,ARCHIVE_FILES.ID as ID,"
                + "ARCHIVE_FILES.ISA_NUMBER as ISA_NUMBER,ARCHIVE_FILES.FILE_TYPE as FILE_TYPE,ARCHIVE_FILES.CARRIER_STATUS,ARCHIVE_FILES.PRI_KEY_VAL  as PRI_KEY_VAL,ARCHIVE_FILES.SENDER_ID,ARCHIVE_FILES.RECEIVER_ID,"
                + "ARCHIVE_FILES.FILE_ORIGIN as FILE_ORIGIN,ARCHIVE_FILES.TRANSACTION_TYPE as TRANSACTION_TYPE,ARCHIVE_FILES.TMW_SENDERID as TMW_SENDERID,ARCHIVE_FILES.TMW_RECEIVERID as TMW_RECEIVERID,"
                + "ARCHIVE_FILES.DIRECTION as DIRECTION,ARCHIVE_FILES.DATE_TIME_RECEIVED as DATE_TIME_RECEIVED,"
                + "ARCHIVE_FILES.STATUS as STATUS,ARCHIVE_FILES.ACK_STATUS as ACK_STATUS,TP2.NAME as RECEIVER_NAME,TP1.NAME as SENDER_NAME,trsrsp.REF_ID,"
                + "ARCHIVE_FILES.SEC_KEY_VAL,ARCHIVE_FILES.REPROCESSSTATUS,ARCHIVE_FILES.FILENAME,trsrsp.RESPONSE_STATUS,ARCHIVE_FILES.TRANSACTION_PURPOSE FROM ARCHIVE_FILES "
                //+ "FROM FILES LEFT OUTER JOIN Transport_loadtender ten on (ten.FILE_ID=FILES.FILE_ID and ten.SHIPMENT_ID=FILES.PRI_KEY_VAL) "
                + "LEFT OUTER JOIN TRANSPORT_LT_RESPONSE trsrsp on (trsrsp.FILE_ID=ARCHIVE_FILES.FILE_ID)"
                //                          + " LEFT OUTER JOIN TP TP1 "
                //                          + "ON (TP1.ID=FILES.TMW_SENDERID) LEFT OUTER JOIN TP TP2 ON (TP2.ID=FILES.TMW_RECEIVERID)");
                + " LEFT OUTER JOIN TP TP1 ON (TP1.ID=ARCHIVE_FILES.TMW_SENDERID) "
                + " LEFT OUTER JOIN TP TP2 ON (TP2.ID=ARCHIVE_FILES.TMW_RECEIVERID)"
                + " LEFT OUTER JOIN TP TP3 ON (TP3.ID=ARCHIVE_FILES.SENDER_ID)"
                + " LEFT OUTER JOIN TP TP4 ON (TP4.ID=ARCHIVE_FILES.RECEIVER_ID)");
        //raja
        documentSearchQuery.append(" WHERE FLOWFLAG LIKE '%L%' ");
        //LogisticsDocBean logisticsdocBean1 = new LogisticsDocBean();
        if (corrattribute != null && corrattribute.equalsIgnoreCase("Order Number")) // || corrattribute.equalsIgnoreCase("Invoice Number")  || corrattribute.equalsIgnoreCase("Shipment Number") || corrattribute.equalsIgnoreCase("Cheque Number") )
        {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                System.out.println("ordernumber is "+corrvalue);
                Connection con = ConnectionProvider.getInstance().getConnection();
                try {
                    
                    statement2 = con.createStatement();
                    //String queryPriKey = "SELECT PRI_KEY_VAL,TRANSACTION_TYPE FROM FILES WHERE SEC_KEY_VAL='"+corrvalue+"'";
                    String queryPriKey = "SELECT PRI_KEY_VAL, TRANSACTION_TYPE FROM ARCHIVE_FILES WHERE PRI_KEY_VAL = (SELECT MAX(PRI_KEY_VAL) FROM ARCHIVE_FILES WHERE SEC_KEY_VAL ='" + corrvalue + "')";
                    System.out.println("query --> " + queryPriKey);
                    resultSet2 = statement2.executeQuery(queryPriKey);
                    System.out.println("in ordernumbe try");
                    if(resultSet2.next()){

                        System.out.println("resultSet2.next()");
                        System.out.println("resultSet2.next() "+resultSet2.getString("TRANSACTION_TYPE"));
                        System.out.println("resultSet2.next() "+resultSet2.getString("PRI_KEY_VAL"));
                        
                        if (resultSet2.getString("TRANSACTION_TYPE").equalsIgnoreCase("204")) {
                            System.out.println(" 204 TRANSACTION_TYPE" + resultSet2.getString("TRANSACTION_TYPE"));
                            documentSearchQuery.append("AND (ARCHIVE_FILES.PRI_KEY_VAL='"+resultSet2.getString("PRI_KEY_VAL")+"' OR ARCHIVE_FILES.PRI_KEY_VAL='"+corrvalue.trim().toUpperCase()+"')");
//                            documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.PRI_KEY_VAL",
//                                    resultSet2.getString("PRI_KEY_VAL")));
                            System.out.println("204 appended");
                            
                        } else{
                            System.out.println(" other TRANSACTION_TYPE" + resultSet2.getString("TRANSACTION_TYPE"));
//                            documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.SEC_KEY_VAL",
//                                    corrvalue.trim().toUpperCase()));
                            documentSearchQuery.append("AND (ARCHIVE_FILES.SEC_KEY_TYPE='"+resultSet2.getString("PRI_KEY_VAL")+"' OR ARCHIVE_FILES.SEC_KEY_TYPE='"+corrvalue.trim().toUpperCase()+"')" );
                            //documentSearchQuery.append("AND FILES.SEC_KEY_TYPE='ORDERNUMBER'");
                            System.out.println("other appended");
                        }
                  
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (resultSet2 != null) {
                            resultSet2.close();
                            resultSet2 = null;
                        }
//                        if (resultSet3 != null) {
//                            resultSet3.close();
//                            resultSet3 = null;
//                        }
                        if (statement2 != null) {
                            statement2.close();
                            statement2 = null;
                        }
//                        if (statement3 != null) {
//                            statement3.close();
//                            statement3 = null;
//                        }
                        if (con != null) {
                            con.close();
                            con = null;
                        }
                    } catch (SQLException se) {
                        throw new ServiceLocatorException(se);
                    }
                }
            }
        }

        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("Order Number")) // || corrattribute.equalsIgnoreCase("Invoice Number")  || corrattribute.equalsIgnoreCase("Shipment Number") || corrattribute.equalsIgnoreCase("Cheque Number") )
        {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                Connection con = ConnectionProvider.getInstance().getConnection();
                try {
                    System.out.println("in ordernumbe try");
                    
                    System.out.println("con " + con);
                    statement2 = con.createStatement();
                    //String query = "SELECT PRI_KEY_VAL,TRANSACTION_TYPE  from FILES WHERE SEC_KEY_TYPE='ORDERNUMBER' AND SEC_KEY_VAL ='" + corrvalue + "'";
                    String query = "SELECT PRI_KEY_VAL, TRANSACTION_TYPE FROM ARCHIVE_FILES WHERE PRI_KEY_VAL = (SELECT MAX(PRI_KEY_VAL) FROM ARCHIVE_FILES WHERE SEC_KEY_VAL ='" + corrvalue1 + "')";
                    //String query = "SELECT PRI_KEY_VAL,TRANSACTION_TYPE FROM FILES WHERE SEC_KEY_VAL='"+corrvalue1+"'";
                    System.out.println("query --> " + query);
                    resultSet2 = statement2.executeQuery(query);
                    if (resultSet2.next()) {
                        if (resultSet2.getString("TRANSACTION_TYPE").equals("204")) {
                            System.out.println(" 204 TRANSACTION_TYPE" + resultSet2.getString("TRANSACTION_TYPE"));
                            documentSearchQuery.append("AND (ARCHIVE_FILES.PRI_KEY_VAL='"+resultSet2.getString("PRI_KEY_VAL")+"' OR ARCHIVE_FILES.PRI_KEY_VAL='"+corrvalue1.trim().toUpperCase()+"')");
//                            documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.PRI_KEY_VAL",
//                                    resultSet2.getString("PRI_KEY_VAL")));
                            System.out.println("204 appended");
                        } else {
                            System.out.println(" other TRANSACTION_TYPE" + resultSet2.getString("TRANSACTION_TYPE"));
//                            documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.SEC_KEY_VAL",
//                                    corrvalue1.trim().toUpperCase()));
                            documentSearchQuery.append("AND (ARCHIVE_FILES.SEC_KEY_TYPE='"+resultSet2.getString("PRI_KEY_VAL")+"' OR ARCHIVE_FILES.SEC_KEY_TYPE='"+corrvalue1.trim().toUpperCase()+"')" );
                            //documentSearchQuery.append("AND FILES.SEC_KEY_TYPE='ORDERNUMBER'");
                            System.out.println("other appended");
                        }
                    }
                    //resultSet2.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    try {
                        if (resultSet2 != null) {
                            resultSet2.close();
                            resultSet2 = null;
                        }
                        if (statement2 != null) {
                            statement2.close();
                            statement2 = null;
                        }
                        if (con != null) {
                            con.close();
                            con = null;
                        }
                    } catch (SQLException se) {
                        throw new ServiceLocatorException(se);
                    }
                }
            }
        }

        if (corrattribute2 != null && corrattribute2.equalsIgnoreCase("Order Number")) // || corrattribute.equalsIgnoreCase("Invoice Number")  || corrattribute.equalsIgnoreCase("Shipment Number") || corrattribute.equalsIgnoreCase("Cheque Number") )
        {
            if (corrvalue2 != null && !"".equals(corrvalue2.trim())) {
                Connection con = ConnectionProvider.getInstance().getConnection();
                try {
                    System.out.println("in ordernumbe try");
                    
                    System.out.println("con " + con);
                    statement2 = con.createStatement();
                    //String query = "SELECT PRI_KEY_VAL,TRANSACTION_TYPE  from FILES WHERE SEC_KEY_TYPE='ORDERNUMBER' AND SEC_KEY_VAL ='" + corrvalue + "'";
                    String query = "SELECT PRI_KEY_VAL, TRANSACTION_TYPE FROM ARCHIVE_FILES WHERE PRI_KEY_VAL = (SELECT MAX(PRI_KEY_VAL) FROM ARCHIVE_FILES WHERE SEC_KEY_VAL ='" + corrvalue2 + "')";
                    //String query = "SELECT PRI_KEY_VAL,TRANSACTION_TYPE FROM FILES WHERE SEC_KEY_VAL='"+corrvalue2+"'";
                    System.out.println("query --> " + query);
                    resultSet2 = statement2.executeQuery(query);
                    if (resultSet2.next()) {
                        if (resultSet2.getString("TRANSACTION_TYPE").equals("204")) {
                            System.out.println(" 204 TRANSACTION_TYPE" + resultSet2.getString("TRANSACTION_TYPE"));
                            documentSearchQuery.append("AND (ARCHIVE_FILES.PRI_KEY_VAL='"+resultSet2.getString("PRI_KEY_VAL")+"' OR ARCHIVE_FILES.PRI_KEY_VAL='"+corrvalue2.trim().toUpperCase()+"')");
//                            documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.PRI_KEY_VAL",
//                                    resultSet2.getString("PRI_KEY_VAL")));
                            System.out.println("204 appended");
                        } else {
                            System.out.println(" other TRANSACTION_TYPE" + resultSet2.getString("TRANSACTION_TYPE"));
//                            documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.SEC_KEY_VAL",
//                                    corrvalue2.trim().toUpperCase()));
                            documentSearchQuery.append("AND (ARCHIVE_FILES.SEC_KEY_TYPE='"+resultSet2.getString("PRI_KEY_VAL")+"' OR ARCHIVE_FILES.SEC_KEY_TYPE='"+corrvalue2.trim().toUpperCase()+"')" );
                            //documentSearchQuery.append("AND FILES.SEC_KEY_TYPE='ORDERNUMBER'");
                            System.out.println("other appended");
                        }
                    }
                    //resultSet2.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    try {
                        if (resultSet2 != null) {
                            resultSet2.close();
                            resultSet2 = null;
                        }
                        if (statement2 != null) {
                            statement2.close();
                            statement2 = null;
                        }
                        if (con != null) {
                            con.close();
                            con = null;
                        }
                    } catch (SQLException se) {
                        throw new ServiceLocatorException(se);
                    }
                }
            }
        }

        // For Invoice / Shipment / Chequecorrattribute2.equalsIgnoreCase("Invoice Number")  || 
        //if(corrattribute1.equalsIgnoreCase("PO Number") || corrattribute1.equalsIgnoreCase("Invoice Number")  || corrattribute1.equalsIgnoreCase("Shipment Number") || corrattribute1.equalsIgnoreCase("Cheque Number") )
        if (corrattribute != null && corrattribute.equalsIgnoreCase("Shipment Number")) {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.PRI_KEY_VAL",
                        corrvalue.trim().toUpperCase()));
            }
        }

        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("Shipment Number")) {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.PRI_KEY_VAL",
                        corrvalue1.trim().toUpperCase()));
            }
        }

        if (corrattribute2 != null && corrattribute2.equalsIgnoreCase("Shipment Number")) {
            if (corrvalue2 != null && !"".equals(corrvalue2.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.PRI_KEY_VAL",
                        corrvalue2.trim().toUpperCase()));
            }
        }

        if (corrattribute != null && corrattribute.equalsIgnoreCase("Invoice Number")) {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.SEC_KEY_VAL",
                        corrvalue.trim().toUpperCase()));
            }
        }

        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("Invoice Number")) {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.SEC_KEY_VAL",
                        corrvalue1.trim().toUpperCase()));
            }
        }

        if (corrattribute2 != null && corrattribute2.equalsIgnoreCase("Invoice Number")) {
            if (corrvalue2 != null && !"".equals(corrvalue2.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.SEC_KEY_VAL",
                        corrvalue2.trim().toUpperCase()));
            }
        }
        // isa 

        if (corrattribute != null && corrattribute.equalsIgnoreCase("ISA Number")) {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.ISA_Number",
                        corrvalue.trim().toUpperCase()));
            }
        }

        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("ISA Number")) {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.ISA_Number",
                        corrvalue1.trim().toUpperCase()));
            }
        }

        if (corrattribute2 != null && corrattribute2.equalsIgnoreCase("ISA Number")) {
            if (corrvalue2 != null && !"".equals(corrvalue2.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.ISA_Number",
                        corrvalue2.trim().toUpperCase()));
            }
        }

        // GS number
        // isa 
        if (corrattribute != null && corrattribute.equalsIgnoreCase("GS Number")) {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.GS_CONTROL_Number",
                        corrvalue.trim().toUpperCase()));
            }
        }

        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("GS Number")) {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.GS_CONTROL_Number",
                        corrvalue1.trim().toUpperCase()));
            }
        }

        if (corrattribute2 != null && corrattribute2.equalsIgnoreCase("GS Number")) {
            if (corrvalue2 != null && !"".equals(corrvalue2.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.GS_CONTROL_Number",
                        corrvalue2.trim().toUpperCase()));
            }
        }
        // -------------------------------------
        // bol
        if (corrattribute != null && corrattribute.equalsIgnoreCase("Instance ID")) {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.FILE_ID",
                        corrvalue.trim().toUpperCase()));
            }
        }
        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("Instance ID")) {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.FILE_ID",
                        corrvalue1.trim().toUpperCase()));
            }
        }
        if (corrattribute2 != null && corrattribute2.equalsIgnoreCase("Instance ID")) {
            if (corrvalue2 != null && !"".equals(corrvalue2.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.FILE_ID",
                        corrvalue2.trim().toUpperCase()));
            }
        }

        // end -----------------------------------------CO
        //file name
        if (corrattribute != null && corrattribute.equalsIgnoreCase("FILE NAME")) {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.FILENAME",
                        corrvalue.trim().toUpperCase()));
            }
        }
        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("FILE NAME")) {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.FILENAME",
                        corrvalue1.trim().toUpperCase()));
            }
        }
        if (corrattribute2 != null && corrattribute2.equalsIgnoreCase("FILE NAME")) {
            if (corrvalue2 != null && !"".equals(corrvalue2.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.FILENAME",
                        corrvalue2.trim().toUpperCase()));
            }
        }
        //end --------------------------------------------filename
        if (doctype != null && !"".equals(doctype.trim())) {
            documentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.TRANSACTION_TYPE",
                    doctype.trim()));
        }
        //Status
        if (status != null && !"-1".equals(status.trim())) {
            documentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.STATUS",
                    status.trim()));
        }
        //ACK_STATUS
        if (ackStatus != null && !"-1".equals(ackStatus.trim())) {
            documentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.ACK_STATUS",
                    ackStatus.trim()));
        }
        // gs number
        if (corrattribute != null && corrattribute.equalsIgnoreCase("GS Number")) {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.GS_CONTROL_NUMBER",
                        corrvalue.trim().toUpperCase()));
            }
        }
        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("GS Number")) {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.GS_CONTROL_NUMBER",
                        corrvalue1.trim().toUpperCase()));
            }
        }
        if (corrattribute2 != null && corrattribute2.equalsIgnoreCase("GS Number")) {
            if (corrvalue2 != null && !"".equals(corrvalue2.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.GS_CONTROL_NUMBER",
                        corrvalue2.trim().toUpperCase()));
            }
        }

//                if (docBusId != null && !"".equals(docBusId.trim())) {
//			documentSearchQuery.append(WildCardSql.getWildCardSql1("TP2.ID",
//					docBusId.trim().toUpperCase()));
//		}
        if (docBusId != null && !"".equals(docBusId.trim())) {
            documentSearchQuery.append(" AND (ARCHIVE_FILES.RECEIVER_ID like '%" + docBusId + "%' OR ARCHIVE_FILES.TMW_RECEIVERID like '%" + docBusId + "%')");
        }

//                
//                if (docSenderId != null && !"".equals(docSenderId.trim())) {
//			documentSearchQuery.append(WildCardSql.getWildCardSql1("TP1.ID",
//					docSenderId.trim().toUpperCase()));
//		}
        if (docSenderId != null && !"".equals(docSenderId.trim())) {
            documentSearchQuery.append(" AND (ARCHIVE_FILES.SENDER_ID like '%" + docSenderId + "%' OR ARCHIVE_FILES.TMW_SENDERID like '%" + docSenderId + "%')");
        }

//                  if (docSenderName != null && !"".equals(docSenderName.trim())) {
//			documentSearchQuery.append(WildCardSql.getWildCardSql1("TP1.NAME",
//					docSenderName.trim().toUpperCase()));
//		}
        if (docSenderName != null && !"".equals(docSenderName.trim())) {

            documentSearchQuery.append(" AND (TP3.NAME like '%" + docSenderName + "%' OR TP1.NAME like '%" + docSenderName + "%')");

        }

//                if (docRecName != null && !"".equals(docRecName.trim())) {
//			documentSearchQuery.append(WildCardSql.getWildCardSql1("TP2.NAME",
//					docRecName.trim().toUpperCase()));
//		}
        if (docRecName != null && !"".equals(docRecName.trim())) {
            documentSearchQuery.append(" AND (TP4.NAME like '%" + docRecName + "%' OR TP2.NAME like '%" + docRecName + "%')");

        }

        if (docdatepicker != null && !"".equals(docdatepicker)) {
            tmp_Recieved_From = DateUtility.getInstance().DateViewToDBCompare(docdatepicker);
            documentSearchQuery.append(" AND ARCHIVE_FILES.DATE_TIME_RECEIVED <= '" + tmp_Recieved_From
                    + "'");
        }
        if (docdatepickerfrom != null && !"".equals(docdatepickerfrom)) {
            tmp_Recieved_From = DateUtility.getInstance().DateViewToDBCompare(docdatepickerfrom);
            documentSearchQuery.append(" AND ARCHIVE_FILES.DATE_TIME_RECEIVED >= '" + tmp_Recieved_From
                    + "'");
        }
        documentSearchQuery.append(" order by DATE_TIME_RECEIVED DESC");
      
        // documentSearchQuery.append(" order by DATE_TIME_RECEIVED DESC fetch first 50 rows only");        
        // documentSearchQuery.append(" WITH UR");
        System.out.println("DOC LDSI queryquery-->" + documentSearchQuery.toString());
        String searchQuery = documentSearchQuery.toString();
        //System.out.println("documentSearchQuery"+documentSearchQuery.toString());

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            statement = connection.createStatement();
            statement1 = connection.createStatement();

            resultSet = statement.executeQuery(searchQuery);

            documentList = new ArrayList<LogisticsDocBean>();

            while (resultSet.next()) {
                LogisticsDocBean logisticsdocBean = new LogisticsDocBean();
                logisticsdocBean.setId(resultSet.getInt("ID"));
                logisticsdocBean.setFile_id(resultSet.getString("FILE_ID"));
                logisticsdocBean.setFile_origin(resultSet.getString("FILE_ORIGIN"));
                logisticsdocBean.setFile_type(resultSet.getString("FILE_TYPE"));
                logisticsdocBean.setIsa_number(resultSet.getString("ISA_NUMBER"));
                logisticsdocBean.setTransaction_type(resultSet.getString("TRANSACTION_TYPE"));

                String Direction = resultSet.getString("DIRECTION");
                logisticsdocBean.setDirection(Direction);
                logisticsdocBean.setDate_time_rec(resultSet.getTimestamp("DATE_TIME_RECEIVED"));
                logisticsdocBean.setStatus(resultSet.getString("STATUS"));

                if (Direction.equalsIgnoreCase("INBOUND")) {
                    logisticsdocBean.setPname(resultSet.getString("SENDER_NAME"));
                }
                if (Direction.equalsIgnoreCase("OUTBOUND")) {
                    logisticsdocBean.setPname(resultSet.getString("RECEIVER_NAME"));
                }
                //logisticsdocBean.setPname(resultSet.getString("SENDER_NAME"));
                //logisticsdocBean.setPoNumber(resultSet.getString("SEC_KEY_VAL"));
                logisticsdocBean.setReProcessStatus(resultSet.getString("REPROCESSSTATUS"));

                if (resultSet.getString("TRANSACTION_TYPE").equalsIgnoreCase("204")) {
                    logisticsdocBean.setTransactionStatus(resultSet.getString("TRANSACTION_PURPOSE"));
                } else if (resultSet.getString("TRANSACTION_TYPE").equalsIgnoreCase("214")) {
                    logisticsdocBean.setTransactionStatus(resultSet.getString("CARRIER_STATUS"));
                    //System.out.println("CARRIER_STATUS"+resultSet.getString("CARRIER_STATUS"));
                } else if (resultSet.getString("TRANSACTION_TYPE").equalsIgnoreCase("990")) {
                    logisticsdocBean.setTransactionStatus(resultSet.getString("RESPONSE_STATUS"));
                }

                logisticsdocBean.setAckStatus(resultSet.getString("ACK_STATUS"));
                logisticsdocBean.setFile_name(resultSet.getString("FILENAME"));
                logisticsdocBean.setShipmentId(resultSet.getString("PRI_KEY_VAL"));
                if (resultSet.getString("TRANSACTION_TYPE").equalsIgnoreCase("204")) {
                    if (resultSet.getString("direction").equalsIgnoreCase("INBOUND")) {
                        String queryString = "SELECT SEC_KEY_VAL as orderNum FROM ARCHIVE_FILES WHERE PRI_KEY_VAL='" + resultSet.getString("PRI_KEY_VAL") + "' AND SEC_KEY_TYPE='ORDERNUMBER'";
                        System.out.println("query string is " + queryString);
                        resultSet1 = statement1.executeQuery(queryString);
                        if (resultSet1.next()) {
                            //System.out.println("resultSet1.next()");
                            if (resultSet1.getString("orderNum") != null) {
                                //System.out.println("resultSet1.getString(orderNum)");
                                logisticsdocBean.setOrdernum(resultSet1.getString("orderNum"));
                            } else {
                                //System.out.println("resultSet1 else");
                                logisticsdocBean.setOrdernum("-");
                            }
                            //System.out.println("resultSet1" + resultSet1.getString("orderNum"));
                            //System.out.println("getOrdernum()" + logisticsdocBean.getOrdernum());
                            //resultSet1.close();

                        } else {
                            //System.out.println("result set empty");
                            logisticsdocBean.setOrdernum("-");
                        }
                    } else {
                        logisticsdocBean.setOrdernum(resultSet.getString("SEC_KEY_VAL"));
                    }
                } else {
                    logisticsdocBean.setOrdernum(resultSet.getString("SEC_KEY_VAL"));
                }
                //System.out.println("logisticsdocBean.getOrdernum();" + logisticsdocBean.getOrdernum());
                logisticsdocBean.setTmwSenderid(resultSet.getString("TMW_SENDERID"));
                logisticsdocBean.setTmwReceivererid(resultSet.getString("TMW_RECEIVERID"));
                //System.out.println("the secondry key value iss-------->"+resultSet.getString("SEC_KEY_VAL"));
                documentList.add(logisticsdocBean);

            }

        } catch (SQLException e) {
            //System.out.println("I am in catch block coming in IMpl");
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception ex) {
            //System.out.println("hi"+ex.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (statement1 != null) {
                    statement1.close();
                    statement1 = null;
                }
                if (statement2 != null) {
                    statement2.close();
                    statement2 = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }
        //System.out.println("Length--->"+documentList.size());
        return documentList;
    }


}
