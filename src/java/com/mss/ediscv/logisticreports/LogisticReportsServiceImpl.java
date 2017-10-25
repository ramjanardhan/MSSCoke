/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.logisticreports;

//import com.mss.ediscv.reports.*;
import com.mss.ediscv.util.AppConstants;
import com.mss.ediscv.util.ConnectionProvider;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author miracle
 */
public class LogisticReportsServiceImpl implements LogisticReportsService {

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

    private static Logger logger = Logger.getLogger(LogisticReportsServiceImpl.class.getName());
    String responseString = null;
    private ArrayList<LogisticReportsBean> documentList;

    public ArrayList<LogisticReportsBean> getDocumentList(LogisticReportsAction logisticreportsAction, String roleId, HttpSession hsession, HttpServletRequest httpServletRequest) throws ServiceLocatorException {
        StringBuffer documentSearchQuery = new StringBuffer();
        logger.info("Entered into the :::: ReportsServiceImpl :::: getDocumentList");

        String docdatepicker = logisticreportsAction.getDocdatepicker();
        String docdatepickerfrom = logisticreportsAction.getDocdatepickerfrom();
        String docSenderId = "";
        if (logisticreportsAction.getDocSenderId() != null && !"".equals(logisticreportsAction.getDocSenderId().trim())) {
        docSenderId = logisticreportsAction.getDocSenderId();
        }
        String docSenderName = "";
        if (logisticreportsAction.getDocSenderName() != null && !"".equals(logisticreportsAction.getDocSenderName().trim())) {
        docSenderName = logisticreportsAction.getDocSenderName();
        }
        String docBusId = "";
        if (logisticreportsAction.getDocBusId() != null && !"".equals(logisticreportsAction.getDocBusId().trim())) {
        docBusId = logisticreportsAction.getDocBusId();
        }
        String docRecName = "";
        if (logisticreportsAction.getDocRecName() != null && !"".equals(logisticreportsAction.getDocRecName().trim())) {
        docRecName = logisticreportsAction.getDocRecName();
        }
        String doctype = "";
        if (logisticreportsAction.getDocType() != null && !logisticreportsAction.getDocType().equals("-1")) {
            doctype = logisticreportsAction.getDocType();
        }

        String status = logisticreportsAction.getStatus();
        String ackStatus = logisticreportsAction.getAckStatus();

        documentSearchQuery.append("SELECT DISTINCT TOP 500 (FILES.FILE_ID) as FILE_ID,"
                + "FILES.ISA_NUMBER as ISA_NUMBER,FILES.FILE_TYPE as FILE_TYPE,FILES.CARRIER_STATUS,"
                + "FILES.FILE_ORIGIN as FILE_ORIGIN,FILES.TRANSACTION_TYPE as TRANSACTION_TYPE,FILES.PRI_KEY_TYPE,"
                + "FILES.DIRECTION as DIRECTION,FILES.DATE_TIME_RECEIVED as DATE_TIME_RECEIVED,FILES.SEC_KEY_TYPE,"
                + "FILES.STATUS as STATUS,FILES.ACK_STATUS as ACK_STATUS,TP2.NAME as RECEIVER_NAME,TP1.NAME as SENDER_NAME,FILES.SENDER_ID,FILES.RECEIVER_ID,"
                + "FILES.SEC_KEY_VAL,FILES.REPROCESSSTATUS,FILES.FILENAME,FILES.PRI_KEY_VAL,trsrsp.RESPONSE_STATUS,FILES.TRANSACTION_PURPOSE FROM FILES "
                + "LEFT OUTER JOIN TRANSPORT_LT_RESPONSE trsrsp on (trsrsp.FILE_ID=FILES.FILE_ID)"
                + " LEFT OUTER JOIN TP TP1 ON (TP1.ID=FILES.TMW_SENDERID) "
                + " LEFT OUTER JOIN TP TP2 ON (TP2.ID=FILES.TMW_RECEIVERID)"
                + " LEFT OUTER JOIN TP TP3 ON (TP3.ID=FILES.SENDER_ID)"
                + " LEFT OUTER JOIN TP TP4 ON (TP4.ID=FILES.RECEIVER_ID)");

        documentSearchQuery.append(" WHERE 1=1 AND FLOWFLAG LIKE '%L%'");
        //////////////////
        if (roleId.equals("102")) {
            documentSearchQuery.append(" AND FILES.TRANSACTION_TYPE !='210' ");
        } else if (roleId.equals("103")) {
            documentSearchQuery.append(" AND FILES.TRANSACTION_TYPE ='210' ");

        }

                /////////
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

//                if (docBusId != null && !"".equals(docBusId.trim())) {
//			documentSearchQuery.append(WildCardSql.getWildCardSql1("TP2.ID",
//					docBusId.trim().toUpperCase()));
//		}
        if (docBusId != null && !"".equals(docBusId.trim())) {
            documentSearchQuery.append(" AND (FILES.RECEIVER_ID like '%" + docBusId + "%' OR FILES.TMW_RECEIVERID like '%" + docBusId + "%')");
        }

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

        //LogisticReportsBean
        documentSearchQuery.append(" order by DATE_TIME_RECEIVED DESC");
                  //documentSearchQuery.append(" order by DATE_TIME_RECEIVED DESC fetch first 50 rows only");        
        // documentSearchQuery.append(" WITH UR");
        System.out.println("DOC queryquery prasad-->" + documentSearchQuery.toString());
        String searchQuery = documentSearchQuery.toString();

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            statement = connection.createStatement();

            resultSet = statement.executeQuery(searchQuery);

            documentList = new ArrayList<LogisticReportsBean>();

            while (resultSet.next()) {
                LogisticReportsBean logisticsreportBean = new LogisticReportsBean();
                logisticsreportBean.setFile_id(resultSet.getString("FILE_ID"));
                logisticsreportBean.setFile_origin(resultSet.getString("FILE_ORIGIN"));
                logisticsreportBean.setFile_type(resultSet.getString("FILE_TYPE"));
                logisticsreportBean.setIsa_number(resultSet.getString("ISA_NUMBER"));
                String trans = resultSet.getString("TRANSACTION_TYPE");
                logisticsreportBean.setTransaction_type(trans);
                String sektype = resultSet.getString("SEC_KEY_TYPE");
                String Direction = resultSet.getString("DIRECTION");
                logisticsreportBean.setDirection(Direction);
                //logisticsreportBean.setDirection(resultSet.getString("DIRECTION"));
                logisticsreportBean.setDate_time_rec(resultSet.getTimestamp("DATE_TIME_RECEIVED"));
                logisticsreportBean.setStatus(resultSet.getString("STATUS"));

                if (Direction.equalsIgnoreCase("INBOUND")) {
                    logisticsreportBean.setPname(resultSet.getString("SENDER_NAME"));
                }
                if (Direction.equalsIgnoreCase("OUTBOUND")) {
                    logisticsreportBean.setPname(resultSet.getString("RECEIVER_NAME"));
                }
                //if(trans!=null)   {   
                if ((trans.equalsIgnoreCase("214")) || (trans.equalsIgnoreCase("990"))) {
                    if (sektype.equalsIgnoreCase("ORDERNUMBER")) {
                        logisticsreportBean.setSce_key_val(resultSet.getString("SEC_KEY_VAL"));
                    }

                } //                            else if(trans.equalsIgnoreCase("990")){
                //                                  if(sektype.equalsIgnoreCase("ORDERNUMBER")){
                //                                   logisticsreportBean.setOrderNum(resultSet.getString("SEC_KEY_VAL"));  
                //                                }
                //                            } 
                else if (trans.equalsIgnoreCase("210")) {
                    if (sektype.equalsIgnoreCase("IN")) {
                        logisticsreportBean.setInv_Num(resultSet.getString("SEC_KEY_VAL"));
                    }

                } else if (trans.equalsIgnoreCase("204")) {

                    logisticsreportBean.setShipmentNumber(resultSet.getString("PRI_KEY_VAL"));

                }

                logisticsreportBean.setReProcessStatus(resultSet.getString("REPROCESSSTATUS"));

                if (resultSet.getString("TRANSACTION_TYPE").equalsIgnoreCase("204")) {
                    logisticsreportBean.setTransactionStatus(resultSet.getString("TRANSACTION_PURPOSE"));
                } else if (resultSet.getString("TRANSACTION_TYPE").equalsIgnoreCase("214")) {
                    logisticsreportBean.setTransactionStatus(resultSet.getString("CARRIER_STATUS"));
                    //System.out.println("CARRIER_STATUS"+resultSet.getString("CARRIER_STATUS"));
                } else if (resultSet.getString("TRANSACTION_TYPE").equalsIgnoreCase("990")) {
                    logisticsreportBean.setTransactionStatus(resultSet.getString("RESPONSE_STATUS"));
                }

                logisticsreportBean.setReProcessStatus(resultSet.getString("REPROCESSSTATUS"));
                logisticsreportBean.setAckStatus(resultSet.getString("ACK_STATUS"));
                logisticsreportBean.setFile_name(resultSet.getString("FILENAME"));
                documentList.add(logisticsreportBean);
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
                if (statement != null) {
                    statement.close();
                    statement = null;
                }

                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }
        // System.out.println("Length--->"+purchaseList.size());
        return documentList;
    }
}
