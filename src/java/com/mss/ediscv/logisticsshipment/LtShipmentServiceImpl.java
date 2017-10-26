/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.logisticsshipment;

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
import org.apache.log4j.Logger;

/**
 *
 * @author miracle
 */
public class LtShipmentServiceImpl implements LtShipmentService {

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String tmp_Recieved_From = "";
    String tmp_Recieved_ToTime = "";
    String strFormat = "yyyy-MM-dd";
    DateFormat myDateFormat = new SimpleDateFormat(strFormat);
    Calendar cal = new GregorianCalendar();
    java.util.Date now = cal.getTime();
    long time = now.getTime();
    java.sql.Date date = new java.sql.Date(time);

    int callableStatementUpdateCount;
    private ArrayList<LtShipmentBean> ltShipmentBeanList;
    private LtShipmentBean ltShipmentBean;

    private static Logger logger = Logger.getLogger(LtShipmentServiceImpl.class
            .getName());

    public ArrayList getLtResponseArchiveList(LogisticsShipmentAction logisticsShipmentAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException {
        StringBuffer ltShipmentSearchQuery = new StringBuffer();
        logger.info("Entered into the :::: LtResponseServiceImpl :::: getLtResponseList");
        System.out.println("archive lt response");
        String datepickerTo = logisticsShipmentAction.getDatepickerTo();
        String datepickerfrom = logisticsShipmentAction.getDatepickerfrom();
        String senderId = "";
        if (logisticsShipmentAction.getSenderId() != null && !"".equals(logisticsShipmentAction.getSenderId().trim())) {
        senderId = logisticsShipmentAction.getSenderId();
        }
        String senderName = "";
        if (logisticsShipmentAction.getSenderName() != null && !"".equals(logisticsShipmentAction.getSenderName().trim())) {
        senderName = logisticsShipmentAction.getSenderName();
        }
        String receiverId = "";
        if (logisticsShipmentAction.getReceiverId() != null && !"".equals(logisticsShipmentAction.getReceiverId().trim())) {
        receiverId = logisticsShipmentAction.getReceiverId();
        }
        String receiverName = "";
        if (logisticsShipmentAction.getReceiverName() != null && !"".equals(logisticsShipmentAction.getReceiverName().trim())) {
        receiverName = logisticsShipmentAction.getReceiverName();
        }
        //String docIsa= logisticsDocAction.getDocIsa();
        String doctype = "";
        if (logisticsShipmentAction.getDocType() != null && !logisticsShipmentAction.getDocType().equals("-1")) {
            doctype = logisticsShipmentAction.getDocType();
        }

        String corrattribute = logisticsShipmentAction.getCorrattribute();
        String corrvalue = logisticsShipmentAction.getCorrvalue();
        String corrattribute1 = logisticsShipmentAction.getCorrattribute1();
        String corrvalue1 = logisticsShipmentAction.getCorrvalue1();

        String status = logisticsShipmentAction.getStatus();
        String ackStatus = logisticsShipmentAction.getAckStatus();
        /*String docPoNum= docRepositoryAction.getDocPoNum();
         String ponum= docRepositoryAction.getPonumber();
         String asnnum= docRepositoryAction.getAsnnumber();
         String invnum= docRepositoryAction.getInvnumber();
         String bolnum= docRepositoryAction.getBolNum();
         String chequeNum = docRepositoryAction.getChequeNum();*/

        ltShipmentSearchQuery.append("SELECT DISTINCT TOP 500 (ARCHIVE_FILES.FILE_ID) as FILE_ID,ARCHIVE_TRANSPORT_SHIPMENT.STOP_SEQ_NUM,"
                + "ARCHIVE_FILES.ISA_NUMBER as ISA_NUMBER,ARCHIVE_FILES.FILE_TYPE as FILE_TYPE,ARCHIVE_FILES.CARRIER_STATUS  as CARRIER_STATUS,ARCHIVE_FILES.ID AS IDFILE,"
                + "ARCHIVE_FILES.FILE_ORIGIN as FILE_ORIGIN,ARCHIVE_FILES.TRANSACTION_TYPE as TRANSACTION_TYPE,ARCHIVE_FILES.TMW_SENDERID as TMW_SENDERID,ARCHIVE_FILES.TMW_RECEIVERID as TMW_RECEIVERID,"
                + "ARCHIVE_FILES.DIRECTION as DIRECTION,ARCHIVE_FILES.DATE_TIME_RECEIVED as DATE_TIME_RECEIVED,ARCHIVE_FILES.SENDER_ID,ARCHIVE_FILES.RECEIVER_ID,"
                + "ARCHIVE_FILES.STATUS as STATUS,ARCHIVE_FILES.ACK_STATUS as ACK_STATUS,TP1.NAME as SENDER_NAME,"
                + "ARCHIVE_FILES.SEC_KEY_VAL,ARCHIVE_FILES.REPROCESSSTATUS,ARCHIVE_FILES.STOP_NUM,TP2.NAME as RECEIVER_NAME,ARCHIVE_TRANSPORT_SHIPMENT.SHIPMENT_ID,ARCHIVE_TRANSPORT_SHIPMENT.ID as ID,ARCHIVE_TRANSPORT_SHIPMENT.PO_NUMBER "
                + "FROM ARCHIVE_TRANSPORT_SHIPMENT "
                + "LEFT OUTER JOIN ARCHIVE_FILES ON (ARCHIVE_TRANSPORT_SHIPMENT.FILE_ID =ARCHIVE_FILES.FILE_ID)"
                + "LEFT OUTER JOIN TP TP1 ON (TP1.ID=ARCHIVE_FILES.TMW_SENDERID) "
                + "LEFT OUTER JOIN TP TP2 ON (TP2.ID=ARCHIVE_FILES.TMW_RECEIVERID)"
                + "LEFT OUTER JOIN TP TP3 ON (TP3.ID=ARCHIVE_FILES.SENDER_ID)"
                + "LEFT OUTER JOIN TP TP4 ON (TP4.ID=ARCHIVE_FILES.RECEIVER_ID)");

        ltShipmentSearchQuery.append(" WHERE 1=1 AND ARCHIVE_FILES.FLOWFLAG = 'L' ");

        // FOr PO
        if (corrattribute != null && corrattribute.equalsIgnoreCase("Instance ID")) // || corrattribute.equalsIgnoreCase("Invoice Number")  || corrattribute.equalsIgnoreCase("Shipment Number") || corrattribute.equalsIgnoreCase("Cheque Number") )
        {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.FILE_ID",
                        corrvalue.trim().toUpperCase()));
            }
        }

        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("Instance ID")) // || corrattribute.equalsIgnoreCase("Invoice Number")  || corrattribute.equalsIgnoreCase("Shipment Number") || corrattribute.equalsIgnoreCase("Cheque Number") )
        {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.FILE_ID",
                        corrvalue1.trim().toUpperCase()));
            }
        }

                // For Invoice / Shipment / Cheque
        //if(corrattribute1.equalsIgnoreCase("PO Number") || corrattribute1.equalsIgnoreCase("Invoice Number")  || corrattribute1.equalsIgnoreCase("Shipment Number") || corrattribute1.equalsIgnoreCase("Cheque Number") )
        if (corrattribute != null && corrattribute.equalsIgnoreCase("Order Number")) {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.SEC_KEY_VAL",
                        corrvalue.trim().toUpperCase()));
            }
        }

        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("Order Number")) {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.SEC_KEY_VAL",
                        corrvalue1.trim().toUpperCase()));
            }
        }

        if (corrattribute != null && corrattribute.equalsIgnoreCase("Shipment Number")) {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_TRANSPORT_SHIPMENT.SHIPMENT_ID",
                        corrvalue.trim().toUpperCase()));
            }
        }

        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("Shipment Number")) {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_TRANSPORT_SHIPMENT.SHIPMENT_ID",
                        corrvalue1.trim().toUpperCase()));
            }
        }

        if (corrattribute != null && corrattribute.equalsIgnoreCase("Stop Seq Number")) {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_TRANSPORT_SHIPMENT.STOP_SEQ_NUM",
                        corrvalue.trim().toUpperCase()));
            }
        }

        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("Stop Seq Number")) {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_TRANSPORT_SHIPMENT.STOP_SEQ_NUM",
                        corrvalue1.trim().toUpperCase()));
            }
        }

        if (doctype != null && !"".equals(doctype.trim())) {
            ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.TRANSACTION_TYPE",
                    doctype.trim()));
        }
        //Status
        if (status != null && !"-1".equals(status.trim())) {
            ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.STATUS",
                    status.trim()));
        }
        //ACK_STATUS
        if (ackStatus != null && !"-1".equals(ackStatus.trim())) {
            ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("ARCHIVE_FILES.ACK_STATUS",
                    ackStatus.trim()));
        }

        if (receiverId != null && !"".equals(receiverId.trim())) {
            ltShipmentSearchQuery.append("AND (ARCHIVE_FILES.RECEIVER_ID like '%" + receiverId + "%' OR ARCHIVE_FILES.TMW_RECEIVERID like '%" + receiverId + "%')");
        }

        if (senderId != null && !"".equals(senderId.trim())) {
            ltShipmentSearchQuery.append("AND (ARCHIVE_FILES.SENDER_ID like '%" + senderId + "%' OR ARCHIVE_FILES.TMW_SENDERID like '%" + senderId + "%')");
        }

        if (senderName != null && !"".equals(senderName.trim())) {

            ltShipmentSearchQuery.append(" AND (TP3.NAME like '%" + senderName + "%' OR TP1.NAME like '%" + senderName + "%')  ");

        }

        if (receiverName != null && !"".equals(receiverName.trim())) {
            ltShipmentSearchQuery.append(" AND (TP4.NAME like '%" + receiverName + "%' OR TP2.NAME like '%" + receiverName + "%')");

        }

        if (datepickerTo != null && !"".equals(datepickerTo)) {
            tmp_Recieved_From = DateUtility.getInstance().DateViewToDBCompare(datepickerTo);
            ltShipmentSearchQuery.append(" AND ARCHIVE_FILES.DATE_TIME_RECEIVED <= '" + tmp_Recieved_From
                    + "'");
        }
        if (datepickerfrom != null && !"".equals(datepickerfrom)) {
            tmp_Recieved_From = DateUtility.getInstance().DateViewToDBCompare(datepickerfrom);
            ltShipmentSearchQuery.append(" AND ARCHIVE_FILES.DATE_TIME_RECEIVED >= '" + tmp_Recieved_From
                    + "'");
        }
        ltShipmentSearchQuery.append(" order by DATE_TIME_RECEIVED DESC ");

                  //ltShipmentSearchQuery.append(" order by DATE_TIME_RECEIVED DESC fetch first 50 rows only");        
        // documentSearchQuery.append(" WITH UR");
        System.out.println("Logistics Shipment query-->" + ltShipmentSearchQuery.toString());
        String searchQuery = ltShipmentSearchQuery.toString();

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            statement = connection.createStatement();

            resultSet = statement.executeQuery(searchQuery);

            ltShipmentBeanList = new ArrayList<LtShipmentBean>();

            while (resultSet.next()) {

                ltShipmentBean = new LtShipmentBean();
                ltShipmentBean.setInstanceId(resultSet.getString("FILE_ID"));
                ltShipmentBean.setDateTime(resultSet.getTimestamp("DATE_TIME_RECEIVED"));
                ltShipmentBean.setAsnNum(resultSet.getString("SHIPMENT_ID"));
                ltShipmentBean.setDirection(resultSet.getString("DIRECTION"));
                //ltResponseBean.set(resultSet.getTimestamp("DATE_TIME_RECEIVED"));
                ltShipmentBean.setStatus(resultSet.getString("STATUS"));
                if(resultSet.getString("DIRECTION").equalsIgnoreCase("INBOUND")){
                    ltShipmentBean.setPartner(resultSet.getString("SENDER_NAME"));
                }   
                if(resultSet.getString("DIRECTION").equalsIgnoreCase("OUTBOUND")){
                    ltShipmentBean.setPartner(resultSet.getString("RECEIVER_NAME"));
                }
                //ltShipmentBean.setPartner(resultSet.getString("RECEIVER_NAME"));
                ltShipmentBean.setPoNum(resultSet.getString("PO_NUMBER"));
                ltShipmentBean.setStopNum(resultSet.getString("STOP_NUM"));

                ltShipmentBean.setAckStatus(resultSet.getString("ACK_STATUS"));
                ltShipmentBean.setCarrierStatus(resultSet.getString("CARRIER_STATUS"));
                ltShipmentBean.setId(resultSet.getInt("ID"));
                ltShipmentBean.setOrdernum(resultSet.getString("SEC_KEY_VAL"));
                ltShipmentBean.setFileId(resultSet.getInt("IDFILE"));

                ltShipmentBean.setTmwSenderid(resultSet.getString("TMW_SENDERID"));
                ltShipmentBean.setTmwReceivererid(resultSet.getString("TMW_RECEIVERID"));

                            //System.out.println("ordernum0"+resultSet.getString("SEC_KEY_VAL"));  IDFILE
                ltShipmentBeanList.add(ltShipmentBean);
            }

        } catch (SQLException e) {
            System.out.println("I am in catch block coming in IMpl");
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
        //System.out.println("Length--->"+ltShipmentBeanList.size());
        return ltShipmentBeanList;
    }
    
    public ArrayList getLtResponseList(LogisticsShipmentAction logisticsShipmentAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException {
                StringBuffer ltShipmentSearchQuery = new StringBuffer();
        logger.info("Entered into the :::: LtResponseServiceImpl :::: getLtResponseList");

        String datepickerTo = logisticsShipmentAction.getDatepickerTo();
        String datepickerfrom = logisticsShipmentAction.getDatepickerfrom();
        String senderId = "";
        if (logisticsShipmentAction.getSenderId() != null && !"".equals(logisticsShipmentAction.getSenderId().trim())) {
        senderId = logisticsShipmentAction.getSenderId();
        }
        String senderName = "";
        if (logisticsShipmentAction.getSenderName() != null && !"".equals(logisticsShipmentAction.getSenderName().trim())) {
        senderName = logisticsShipmentAction.getSenderName();
        }
        String receiverId = "";
        if (logisticsShipmentAction.getReceiverId() != null && !"".equals(logisticsShipmentAction.getReceiverId().trim())) {
        receiverId = logisticsShipmentAction.getReceiverId();
        }
        String receiverName = "";
        if (logisticsShipmentAction.getReceiverName() != null && !"".equals(logisticsShipmentAction.getReceiverName().trim())) {
        receiverName = logisticsShipmentAction.getReceiverName();
        }
        //String docIsa= logisticsDocAction.getDocIsa();
        String doctype = "";
        if (logisticsShipmentAction.getDocType() != null && !logisticsShipmentAction.getDocType().equals("-1")) {
            doctype = logisticsShipmentAction.getDocType();
        }

        String corrattribute = logisticsShipmentAction.getCorrattribute();
        String corrvalue = logisticsShipmentAction.getCorrvalue();
        String corrattribute1 = logisticsShipmentAction.getCorrattribute1();
        String corrvalue1 = logisticsShipmentAction.getCorrvalue1();

        String status = logisticsShipmentAction.getStatus();
        String ackStatus = logisticsShipmentAction.getAckStatus();
        /*String docPoNum= docRepositoryAction.getDocPoNum();
         String ponum= docRepositoryAction.getPonumber();
         String asnnum= docRepositoryAction.getAsnnumber();
         String invnum= docRepositoryAction.getInvnumber();
         String bolnum= docRepositoryAction.getBolNum();
         String chequeNum = docRepositoryAction.getChequeNum();*/

        ltShipmentSearchQuery.append("SELECT DISTINCT TOP 500 (FILES.FILE_ID) as FILE_ID,TRANSPORT_SHIPMENT.STOP_SEQ_NUM,"
                + "FILES.ISA_NUMBER as ISA_NUMBER,FILES.FILE_TYPE as FILE_TYPE,FILES.CARRIER_STATUS  as CARRIER_STATUS,FILES.ID AS IDFILE,"
                + "FILES.FILE_ORIGIN as FILE_ORIGIN,FILES.TRANSACTION_TYPE as TRANSACTION_TYPE,FILES.TMW_SENDERID as TMW_SENDERID,FILES.TMW_RECEIVERID as TMW_RECEIVERID,"
                + "FILES.DIRECTION as DIRECTION,FILES.DATE_TIME_RECEIVED as DATE_TIME_RECEIVED,FILES.SENDER_ID,FILES.RECEIVER_ID,"
                + "FILES.STATUS as STATUS,FILES.ACK_STATUS as ACK_STATUS,TP1.NAME as SENDER_NAME,"
                + "FILES.SEC_KEY_VAL,FILES.REPROCESSSTATUS,FILES.STOP_NUM,TP2.NAME as RECEIVER_NAME,TRANSPORT_SHIPMENT.SHIPMENT_ID,TRANSPORT_SHIPMENT.ID as ID,TRANSPORT_SHIPMENT.PO_NUMBER "
                + "FROM TRANSPORT_SHIPMENT "
                + "LEFT OUTER JOIN FILES ON (TRANSPORT_SHIPMENT.FILE_ID =FILES.FILE_ID)"
                + "LEFT OUTER JOIN TP TP1 ON (TP1.ID=FILES.TMW_SENDERID) "
                + "LEFT OUTER JOIN TP TP2 ON (TP2.ID=FILES.TMW_RECEIVERID)"
                + "LEFT OUTER JOIN TP TP3 ON (TP3.ID=FILES.SENDER_ID)"
                + "LEFT OUTER JOIN TP TP4 ON (TP4.ID=FILES.RECEIVER_ID)");

        ltShipmentSearchQuery.append(" WHERE 1=1 AND FILES.FLOWFLAG = 'L' ");

        // FOr PO
        if (corrattribute != null && corrattribute.equalsIgnoreCase("Instance ID")) // || corrattribute.equalsIgnoreCase("Invoice Number")  || corrattribute.equalsIgnoreCase("Shipment Number") || corrattribute.equalsIgnoreCase("Cheque Number") )
        {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.FILE_ID",
                        corrvalue.trim().toUpperCase()));
            }
        }

        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("Instance ID")) // || corrattribute.equalsIgnoreCase("Invoice Number")  || corrattribute.equalsIgnoreCase("Shipment Number") || corrattribute.equalsIgnoreCase("Cheque Number") )
        {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.FILE_ID",
                        corrvalue1.trim().toUpperCase()));
            }
        }

                // For Invoice / Shipment / Cheque
        //if(corrattribute1.equalsIgnoreCase("PO Number") || corrattribute1.equalsIgnoreCase("Invoice Number")  || corrattribute1.equalsIgnoreCase("Shipment Number") || corrattribute1.equalsIgnoreCase("Cheque Number") )
        if (corrattribute != null && corrattribute.equalsIgnoreCase("Order Number")) {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.SEC_KEY_VAL",
                        corrvalue.trim().toUpperCase()));
            }
        }

        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("Order Number")) {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.SEC_KEY_VAL",
                        corrvalue1.trim().toUpperCase()));
            }
        }

        if (corrattribute != null && corrattribute.equalsIgnoreCase("Shipment Number")) {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("TRANSPORT_SHIPMENT.SHIPMENT_ID",
                        corrvalue.trim().toUpperCase()));
            }
        }

        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("Shipment Number")) {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("TRANSPORT_SHIPMENT.SHIPMENT_ID",
                        corrvalue1.trim().toUpperCase()));
            }
        }

        if (corrattribute != null && corrattribute.equalsIgnoreCase("Stop Seq Number")) {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("TRANSPORT_SHIPMENT.STOP_SEQ_NUM",
                        corrvalue.trim().toUpperCase()));
            }
        }

        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("Stop Seq Number")) {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("TRANSPORT_SHIPMENT.STOP_SEQ_NUM",
                        corrvalue1.trim().toUpperCase()));
            }
        }

        if (doctype != null && !"".equals(doctype.trim())) {
            ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.TRANSACTION_TYPE",
                    doctype.trim()));
        }
        //Status
        if (status != null && !"-1".equals(status.trim())) {
            ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.STATUS",
                    status.trim()));
        }
        //ACK_STATUS
        if (ackStatus != null && !"-1".equals(ackStatus.trim())) {
            ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.ACK_STATUS",
                    ackStatus.trim()));
        }

        if (receiverId != null && !"".equals(receiverId.trim())) {
            ltShipmentSearchQuery.append("AND (FILES.RECEIVER_ID like '%" + receiverId + "%' OR FILES.TMW_RECEIVERID like '%" + receiverId + "%')");
        }

        if (senderId != null && !"".equals(senderId.trim())) {
            ltShipmentSearchQuery.append("AND (FILES.SENDER_ID like '%" + senderId + "%' OR FILES.TMW_SENDERID like '%" + senderId + "%')");
        }

        if (senderName != null && !"".equals(senderName.trim())) {

            ltShipmentSearchQuery.append(" AND (TP3.NAME like '%" + senderName + "%' OR TP1.NAME like '%" + senderName + "%')  ");

        }

        if (receiverName != null && !"".equals(receiverName.trim())) {
            ltShipmentSearchQuery.append(" AND (TP4.NAME like '%" + receiverName + "%' OR TP2.NAME like '%" + receiverName + "%')");

        }

        if (datepickerTo != null && !"".equals(datepickerTo)) {
            tmp_Recieved_From = DateUtility.getInstance().DateViewToDBCompare(datepickerTo);
            ltShipmentSearchQuery.append(" AND FILES.DATE_TIME_RECEIVED <= '" + tmp_Recieved_From
                    + "'");
        }
        if (datepickerfrom != null && !"".equals(datepickerfrom)) {
            tmp_Recieved_From = DateUtility.getInstance().DateViewToDBCompare(datepickerfrom);
            ltShipmentSearchQuery.append(" AND FILES.DATE_TIME_RECEIVED >= '" + tmp_Recieved_From
                    + "'");
        }
        ltShipmentSearchQuery.append(" order by DATE_TIME_RECEIVED DESC ");

                  //ltShipmentSearchQuery.append(" order by DATE_TIME_RECEIVED DESC fetch first 50 rows only");        
        // documentSearchQuery.append(" WITH UR");
        System.out.println("Logistics Shipment query-->" + ltShipmentSearchQuery.toString());
        String searchQuery = ltShipmentSearchQuery.toString();

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            statement = connection.createStatement();

            resultSet = statement.executeQuery(searchQuery);

            ltShipmentBeanList = new ArrayList<LtShipmentBean>();

            while (resultSet.next()) {

                ltShipmentBean = new LtShipmentBean();
                ltShipmentBean.setInstanceId(resultSet.getString("FILE_ID"));
                ltShipmentBean.setDateTime(resultSet.getTimestamp("DATE_TIME_RECEIVED"));
                ltShipmentBean.setAsnNum(resultSet.getString("SHIPMENT_ID"));
                ltShipmentBean.setDirection(resultSet.getString("DIRECTION"));
                //ltResponseBean.set(resultSet.getTimestamp("DATE_TIME_RECEIVED"));
                ltShipmentBean.setStatus(resultSet.getString("STATUS"));
                if(resultSet.getString("DIRECTION").equalsIgnoreCase("INBOUND")){
                    ltShipmentBean.setPartner(resultSet.getString("SENDER_NAME"));
                }   
                if(resultSet.getString("DIRECTION").equalsIgnoreCase("OUTBOUND")){
                    ltShipmentBean.setPartner(resultSet.getString("RECEIVER_NAME"));
                }
                //ltShipmentBean.setPartner(resultSet.getString("RECEIVER_NAME"));
                ltShipmentBean.setPoNum(resultSet.getString("PO_NUMBER"));
                ltShipmentBean.setStopNum(resultSet.getString("STOP_NUM"));

                ltShipmentBean.setAckStatus(resultSet.getString("ACK_STATUS"));
                ltShipmentBean.setCarrierStatus(resultSet.getString("CARRIER_STATUS"));
                ltShipmentBean.setId(resultSet.getInt("ID"));
                ltShipmentBean.setOrdernum(resultSet.getString("SEC_KEY_VAL"));
                ltShipmentBean.setFileId(resultSet.getInt("IDFILE"));

                ltShipmentBean.setTmwSenderid(resultSet.getString("TMW_SENDERID"));
                ltShipmentBean.setTmwReceivererid(resultSet.getString("TMW_RECEIVERID"));

                            //System.out.println("ordernum0"+resultSet.getString("SEC_KEY_VAL"));  IDFILE
                ltShipmentBeanList.add(ltShipmentBean);
            }

        } catch (SQLException e) {
            System.out.println("I am in catch block coming in IMpl");
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
        //System.out.println("Length--->"+ltShipmentBeanList.size());
        return ltShipmentBeanList;
}
}
