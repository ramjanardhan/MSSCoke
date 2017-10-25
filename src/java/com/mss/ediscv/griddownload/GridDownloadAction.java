/**
 * *****************************************************************************
 *
 * Project : Miracle Supply Chain Visibility Portal v1.0
 *
 * Package : com.mss.ediscv.griddownload
 *
 * Date : april 17, 2013 5:22:19 pm
 *
 * Author : Santish kola <skola2@miraclesoft.com>
 *
 * File : GridDownloadAction.java
 *
 *
 * *****************************************************************************
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.griddownload;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
//import com.mss.ediscv.doc.DocRepositoryBean;

import com.mss.ediscv.documentVisibility.DocumentVisibilityBean;
import com.mss.ediscv.editracking.TrackInOutBean;
//import com.mss.ediscv.inv.InvoiceBean;
import com.mss.ediscv.logisticeditracking.LogisticTrackInOutBean;
import com.mss.ediscv.logisticreports.LogisticReportsBean;
import com.mss.ediscv.logisticsdoc.LogisticsDocBean;
import com.mss.ediscv.logisticsinvoice.LogisticsInvoiceAction;
import com.mss.ediscv.logisticsinvoice.LogisticsInvoiceBean;
import com.mss.ediscv.logisticsloadtendering.LogisticsLoadBean;
import com.mss.ediscv.logisticsshipment.LtShipmentBean;
import com.mss.ediscv.ltResponse.LtResponseBean;
//import com.mss.ediscv.payments.PaymentBean;
//import com.mss.ediscv.po.PurchaseOrderBean;
//import com.mss.ediscv.reports.ReportsBean;
//import com.mss.ediscv.shipment.ShipmentBean;
import com.mss.ediscv.util.AppConstants;
import com.mss.ediscv.util.ConnectionProvider;
import com.mss.ediscv.util.DateUtility;
import com.mss.ediscv.util.Properties;
import com.mss.ediscv.util.ServiceLocator;
import com.mss.ediscv.util.ServiceLocatorException;
import com.mss.ediscv.util.WildCardSql;
import com.opensymphony.xwork2.Action;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//import java.security.Timestamp;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import java.sql.Timestamp;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Ajay Tummapala <atummapala@miraclesoft.com>
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 */
public class GridDownloadAction implements
        Action, ServletRequestAware, ServletResponseAware {

    private String contentDisposition = "FileName=inline";
    public InputStream inputStream;
    public OutputStream outputStream;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private String fileName;
    private String downloadType;
    private String sheetType;
    private String inbound;
    private String outbound;
    private int scheduleId;
    private String reportattachment;
    private String startDate;
    private String dateTo;
    private String dateFrom;
    private String invSenderName;
    private String invReceiverId;
    private String invReceiverName;
    private String docType;
    private String corrattribute;
    private String corrvalue;
    private String corrattribute1;
    private String corrvalue1;
    private String status;
    private String ackStatus;
    private String tmwSenderid;
    private String invSenderId;
    private String docSenderId;
    private String docSenderName;
    private String docBusId;
    private String docRecName;
    private String corrattribute2;
    private String corrvalue2;
    private String senderId;
    private String senderName;
    private String receiverId;
    private String receiverName;
    private String buId;
    private String recName;
    private String datepickerfrom;
    private String datepickerTo;

    public String getDatepickerTo() {
        return datepickerTo;
    }

    public void setDatepickerTo(String datepickerTo) {
        this.datepickerTo = datepickerTo;
    }

    public String getDatepickerfrom() {
        return datepickerfrom;
    }

    public void setDatepickerfrom(String datepickerfrom) {
        this.datepickerfrom = datepickerfrom;
    }

    public String getBuId() {
        return buId;
    }

    public void setBuId(String buId) {
        this.buId = buId;
    }

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getCorrattribute2() {
        return corrattribute2;
    }

    public void setCorrattribute2(String corrattribute2) {
        this.corrattribute2 = corrattribute2;
    }

    public String getCorrvalue2() {
        return corrvalue2;
    }

    public void setCorrvalue2(String corrvalue2) {
        this.corrvalue2 = corrvalue2;
    }

    public String getDocBusId() {
        return docBusId;
    }

    public void setDocBusId(String docBusId) {
        this.docBusId = docBusId;
    }

    public String getDocRecName() {
        return docRecName;
    }

    public void setDocRecName(String docRecName) {
        this.docRecName = docRecName;
    }

    public String getDocSenderId() {
        return docSenderId;
    }

    public void setDocSenderId(String docSenderId) {
        this.docSenderId = docSenderId;
    }

    public String getDocSenderName() {
        return docSenderName;
    }

    public void setDocSenderName(String docSenderName) {
        this.docSenderName = docSenderName;
    }

    public String getInvSenderId() {
        return invSenderId;
    }

    public void setInvSenderId(String invSenderId) {
        this.invSenderId = invSenderId;
    }
    private String tmwReceivererid;

    public String getAckStatus() {
        return ackStatus;
    }

    public void setAckStatus(String ackStatus) {
        this.ackStatus = ackStatus;
    }

    public String getCorrattribute() {
        return corrattribute;
    }

    public void setCorrattribute(String corrattribute) {
        this.corrattribute = corrattribute;
    }

    public String getCorrattribute1() {
        return corrattribute1;
    }

    public void setCorrattribute1(String corrattribute1) {
        this.corrattribute1 = corrattribute1;
    }

    public String getCorrvalue() {
        return corrvalue;
    }

    public void setCorrvalue(String corrvalue) {
        this.corrvalue = corrvalue;
    }

    public String getCorrvalue1() {
        return corrvalue1;
    }

    public void setCorrvalue1(String corrvalue1) {
        this.corrvalue1 = corrvalue1;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getInvReceiverId() {
        return invReceiverId;
    }

    public void setInvReceiverId(String invReceiverId) {
        this.invReceiverId = invReceiverId;
    }

    public String getInvReceiverName() {
        return invReceiverName;
    }

    public void setInvReceiverName(String invReceiverName) {
        this.invReceiverName = invReceiverName;
    }

    public String getInvSenderName() {
        return invSenderName;
    }

    public void setInvSenderName(String invSenderName) {
        this.invSenderName = invSenderName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTmwReceivererid() {
        return tmwReceivererid;
    }

    public void setTmwReceivererid(String tmwReceivererid) {
        this.tmwReceivererid = tmwReceivererid;
    }

    public String getTmwSenderid() {
        return tmwSenderid;
    }

    public void setTmwSenderid(String tmwSenderid) {
        this.tmwSenderid = tmwSenderid;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getInbound() {
        return inbound;
    }

    public void setInbound(String inbound) {
        this.inbound = inbound;
    }

    public String getOutbound() {
        return outbound;
    }

    public void setOutbound(String outbound) {
        this.outbound = outbound;
    }

    /**
     * Creates a new instance of DownloadAction
     */
    public GridDownloadAction() {
    }

    @Override
    public String execute() throws Exception {
        return null;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        String responseString = "";
        try {
            String fileLocation = "";
            System.out.println("getSheetType-->" + getSheetType());
            if (getSheetType().equals("trackSummary") && getDownloadType().equals("xls")) {
                fileLocation = docReportTrackingSummaryExcelDownload();
            } else if (getSheetType().equals("trackInOut") && getDownloadType().equals("xls")) {
                fileLocation = docReportTrackingInOutExcelDownload();
            } else if (getSheetType().equals("trackInquiry") && getDownloadType().equals("xls")) {
                fileLocation = docReportTrackingInquiryExcelDownload();
            } //documentReport/
            else if (getSheetType().equals("logisticsDoc") && getDownloadType().equals("xlsx")) {
                try {
                    fileLocation = gridDownloadforLtdocrepo();
                } catch (ServiceLocatorException ex) {
                    Logger.getLogger(GridDownloadAction.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(GridDownloadAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (getSheetType().equals("loadTendering") && getDownloadType().equals("xlsx")) {
                try {
                    fileLocation = loadTenderingExcelDownload();
                } catch (ServiceLocatorException ex) {
                    Logger.getLogger(GridDownloadAction.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(GridDownloadAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (getSheetType().equals("ltResponse") && getDownloadType().equals("xlsx")) {
                try {
                    fileLocation = ltResponseExcelDownload();
                } catch (ServiceLocatorException ex) {
                    Logger.getLogger(GridDownloadAction.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(GridDownloadAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (getSheetType().equals("ltShipment") && getDownloadType().equals("xlsx")) {
                try {
                    fileLocation = gridDownloadforLtAsnRepo();
                } catch (ServiceLocatorException ex) {
                    Logger.getLogger(GridDownloadAction.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(GridDownloadAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (getSheetType().equals("ltInvoice") && getDownloadType().equals("xlsx")) {
                try {
                    fileLocation = ltInvoiceExcelDownload();
                } catch (ServiceLocatorException ex) {
                    Logger.getLogger(GridDownloadAction.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(GridDownloadAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            } //
            else if (getSheetType().equals("docVisibility") && getDownloadType().equals("xls")) {
                fileLocation = docVisibilityExcelDownload();
            } else if (getSheetType().equals("dash") && getDownloadType().equals("xlsx")) {
                fileLocation = dashVisibilityExcelDownload(getInbound(), getOutbound());
            } else if (getSheetType().equals("dash") && getDownloadType().equals("pdf")) {
                fileLocation = dashVisibilityPdfDownload(getInbound(), getOutbound());
            } // new excel sheets for logstic reports  
            else if (getSheetType().equals("logistictrackInOut") && getDownloadType().equals("xls")) {
                fileLocation = logisticReportTrackingInOutExcelDownload();
            } else if (getSheetType().equals("logistictrackInquiry") && getDownloadType().equals("xls")) {
                fileLocation = logisticReportTrackingInquiryExcelDownload();
            } else if (getSheetType().equals("logistictrackSummary") && getDownloadType().equals("xls")) {
                fileLocation = logisticReportTrackingSummaryExcelDownload();
            } else if (getSheetType().equals("logisticsReport") && getDownloadType().equals("xlsx")) {
                try {
                    fileLocation = gridDownloadforLtReports();
                } catch (ServiceLocatorException ex) {
                    Logger.getLogger(GridDownloadAction.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(GridDownloadAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            httpServletResponse.setContentType("application/force-download");
            File file = new File(fileLocation);
            Date date = new Date();
            fileName = (date.getYear() + 1900) + "_" + (date.getMonth() + 1) + "_" + date.getDate() + "_" + file.getName();
            if (file.exists()) {
                inputStream = new FileInputStream(file);
                outputStream = httpServletResponse.getOutputStream();
                httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
                int noOfBytesRead = 0;
                byte[] byteArray = null;
                while (true) {
                    byteArray = new byte[1024];
                    noOfBytesRead = inputStream.read(byteArray);
                    if (noOfBytesRead == 0) {
                        break;
                    }
                    outputStream.write(byteArray, 0, noOfBytesRead);
                }
                responseString = "downLoaded!!";
                httpServletResponse.setContentType(getDownloadType());
                httpServletResponse.getWriter().write(responseString);
            } else {
                throw new FileNotFoundException("File not found");
            }
        } catch (FileNotFoundException ex) {
            try {
                httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
            } catch (IOException ex1) {
                Logger.getLogger(GridDownloadAction.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {

                inputStream.close();
                outputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /*
     * Method for Excel Format Logistics DocRepository Download
     */
    public String ltInvoiceExcelDownload() throws ServiceLocatorException, SQLException {
        StringBuffer invSearchQuery = new StringBuffer();
        String filePath = "";
        StringBuffer sb = null;
        Connection connection = null;
        PreparedStatement preStmt = null;
        ResultSet resultSet = null;
        HashMap map = null;
        String tmp_Recieved_From = "";
        String tmp_Recieved_ToTime = "";
        dateTo = getDateTo();
        dateFrom = getDateFrom();
        senderId = getSenderId();
        senderName = getSenderName();
        receiverId = getReceiverId();
        receiverName = getReceiverName();
        if (getDocType() != null && !getDocType().equals("-1")) {
            docType = getDocType();
        }
        corrattribute = getCorrattribute();
        corrvalue = getCorrvalue();
        corrattribute1 = getCorrattribute1();
        corrvalue1 = getCorrvalue1();
        status = getStatus();
        ackStatus = getAckStatus();
        List finalList = new ArrayList();
        Date date = new Date();
        try {
            File file = new File(Properties.getProperty("mscvp.ltInvoiceCreationPath"));
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + File.separator + "ltInvoice.xlsx");
            invSearchQuery.append("SELECT ("
                    + "TRANSPORT_INVOICE.INVOICE_NUMBER ) as INVOICE_NUMBER,"
                    + "FILES.FILE_ID as FILE_ID,TRANSPORT_INVOICE.ID as ID,FILES.ID AS IdFiles,"
                    + "TP2.NAME as REC_NAME,"
                    + "TRANSPORT_INVOICE.PO_NUMBER as PO_NUMBER,"
                    + "TRANSPORT_INVOICE.SHIPMENT_ID as SHIPMENT_ID,FILES.DATE_TIME_RECEIVED,"
                    + "TRANSPORT_INVOICE.TOTAL_WEIGHT as TOTAL_WEIGHT,FILES.ERR_MESSAGE,"
                    + "TRANSPORT_INVOICE.TOTAL_AMOUNT as TOTAL_AMOUNT,"
                    + "TRANSPORT_INVOICE.INVOICE_DATE as INVOICE_DATE,FILES.SENDER_ID,FILES.RECEIVER_ID,"
                    + "FILES.STATUS as STATUS,"
                    + "FILES.TMW_SENDERID as TMW_SENDERID,"
                    + "FILES.TMW_RECEIVERID as TMW_RECEIVERID,"
                    + "FILES.ACK_STATUS as ACK_STATUS"
                    + " FROM TRANSPORT_INVOICE "
                    + "LEFT OUTER JOIN FILES ON (TRANSPORT_INVOICE.FILE_ID =FILES.FILE_ID) "
                    + " LEFT OUTER JOIN TP TP1 ON (TP1.ID=FILES.TMW_SENDERID) "
                    + " LEFT OUTER JOIN TP TP2 ON (TP2.ID=FILES.TMW_RECEIVERID)"
                    + " LEFT OUTER JOIN TP TP3 ON (TP3.ID=FILES.SENDER_ID)"
                    + " LEFT OUTER JOIN TP TP4 ON (TP4.ID=FILES.RECEIVER_ID)");
            invSearchQuery.append(" WHERE 1=1 ");
            if (dateFrom != null && !"".equals(dateFrom)) {
                tmp_Recieved_From = DateUtility.getInstance().DateViewToDBCompare(dateFrom);
                invSearchQuery.append(" AND FILES.DATE_TIME_RECEIVED >= '" + tmp_Recieved_From
                        + "'");
            }
            if (dateTo != null && !"".equals(dateTo)) {
                tmp_Recieved_ToTime = DateUtility.getInstance().DateViewToDBCompare(dateTo);
                invSearchQuery.append(" AND FILES.DATE_TIME_RECEIVED <= '" + tmp_Recieved_ToTime
                        + "'");
            }

            if (corrattribute != null && corrattribute.equalsIgnoreCase("Invoice Number")) {
                if (corrvalue != null && !"".equals(corrvalue.trim())) {
                    invSearchQuery.append(WildCardSql.getWildCardSql1("TRANSPORT_INVOICE.INVOICE_NUMBER",
                            corrvalue.trim().toUpperCase()));
                }
            }
            if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("Invoice Number")) {
                if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                    invSearchQuery.append(WildCardSql.getWildCardSql1("TRANSPORT_INVOICE.INVOICE_NUMBER",
                            corrvalue1.trim().toUpperCase()));
                }
            }
            if (corrattribute != null && corrattribute.equalsIgnoreCase("PO Number")) {
                if (corrvalue != null && !"".equals(corrvalue.trim())) {
                    invSearchQuery.append(WildCardSql.getWildCardSql1("TRANSPORT_INVOICE.PO_NUMBER",
                            corrvalue.trim().toUpperCase()));
                }
            }
            if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("PO Number")) {
                if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                    invSearchQuery.append(WildCardSql.getWildCardSql1("TRANSPORT_INVOICE.PO_NUMBER",
                            corrvalue1.trim().toUpperCase()));
                }
            }
            if (corrattribute != null && corrattribute.equalsIgnoreCase("Shipment Number")) {
                if (corrvalue != null && !"".equals(corrvalue.trim())) {
                    invSearchQuery.append(WildCardSql.getWildCardSql1("TRANSPORT_INVOICE.SHIPMENT_ID",
                            corrvalue.trim().toUpperCase()));
                }
            }
            if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("Shipment Number")) {
                if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                    invSearchQuery.append(WildCardSql.getWildCardSql1("TRANSPORT_INVOICE.SHIPMENT_ID",
                            corrvalue1.trim().toUpperCase()));
                }
            }
            if (docType != null && !"-1".equals(docType.trim())) {
                invSearchQuery.append(WildCardSql.getWildCardSql1("FILES.TRANSACTION_TYPE",
                        docType.trim()));
            }
            //Status
            if (status != null && !"-1".equals(status.trim())) {
                invSearchQuery.append(WildCardSql.getWildCardSql1("FILES.STATUS",
                        status.trim()));
            }
            //ACK_STATUS
            if (ackStatus != null && !"-1".equals(ackStatus.trim())) {
                invSearchQuery.append(WildCardSql.getWildCardSql1("FILES.ACK_STATUS",
                        ackStatus.trim()));
            }

            if (senderId != null && !"".equals(senderId.trim())) {
                invSearchQuery.append(" AND (FILES.SENDER_ID like '%" + senderId + "%' OR FILES.TMW_SENDERID like '%" + senderId + "%')");
            }

            if (receiverId != null && !"".equals(receiverId.trim())) {
                invSearchQuery.append(" AND (FILES.RECEIVER_ID like '%" + receiverId + "%' OR FILES.TMW_RECEIVERID like '%" + receiverId + "%')");
            }

            if (senderName != null && !"".equals(senderName.trim())) {

                invSearchQuery.append(" AND (TP3.NAME like '%" + senderName + "%' OR TP1.NAME like '%" + senderName + "%')");

            }
            if (receiverName != null && !"".equals(receiverName.trim())) {
                invSearchQuery.append(" AND (TP4.NAME like '%" + receiverName + "%' OR TP2.NAME like '%" + receiverName + "%')");

            }

            invSearchQuery.append(" order by FILES.DATE_TIME_RECEIVED DESC");
            System.out.println("Logistics Inv query-->" + invSearchQuery.toString());
            String searchQuery = invSearchQuery.toString();

            int j = 1;
            connection = ConnectionProvider.getInstance().getConnection();
            Statement statement = null;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(searchQuery);
            String InvoiceDate = "";

            while (resultSet.next()) {
                String InstanceId = resultSet.getString("FILE_ID");
                String Partner = resultSet.getString("REC_NAME");
                String InvoiceNo = resultSet.getString("INVOICE_NUMBER");
                String PoNo = resultSet.getString("PO_NUMBER");
                String ItemQuantity = resultSet.getString("TOTAL_WEIGHT");
                String InvoiceAmt = resultSet.getString("TOTAL_AMOUNT");
                String ShipmentId = resultSet.getString("SHIPMENT_ID");
                if (resultSet.getString("INVOICE_DATE") != null) {
                    InvoiceDate = resultSet.getString("INVOICE_DATE");
                }

                String Status = resultSet.getString("STATUS");
                map = new HashMap();
                map.put("SNO", String.valueOf(j));
                map.put("InstanceId", InstanceId);
                map.put("Partner", Partner);
                map.put("InvoiceNo", InvoiceNo);
                map.put("PoNo", PoNo);
                map.put("ItemQuantity", ItemQuantity);
                map.put("InvoiceAmt", InvoiceAmt);
                map.put("ShipmentId", ShipmentId);
                map.put("InvoiceDate", InvoiceDate);
                map.put("Status", Status);

                finalList.add(map);
                j++;
            }
            if (finalList.size() > 0) {
                filePath = file.getAbsolutePath() + Properties.getProperty("os.compatability") + "ltInvoice.xlsx";
                XSSFWorkbook xssfworkbook = new XSSFWorkbook();
                XSSFSheet sheet = xssfworkbook.createSheet("ltInvoice");
                XSSFCellStyle cs = xssfworkbook.createCellStyle();
                cs.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                cs.setAlignment(XSSFCellStyle.ALIGN_LEFT);
                cs.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                cs.setBorderTop((short) 1); // single line border
                cs.setBorderBottom((short) 1); // single line border

                XSSFCellStyle headercs = xssfworkbook.createCellStyle();
                headercs.setFillForegroundColor(IndexedColors.AQUA.index);
                headercs.setAlignment(XSSFCellStyle.ALIGN_CENTER);
                headercs.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                headercs.setBorderTop((short) 1); // single line border
                headercs.setBorderBottom((short) 1); // single line border

                XSSFCellStyle cs1 = xssfworkbook.createCellStyle();
                cs1.setFillForegroundColor(IndexedColors.GREEN.index);
                cs1.setAlignment(XSSFCellStyle.ALIGN_LEFT);
                cs1.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                cs1.setBorderTop((short) 1); // single line border
                cs1.setBorderBottom((short) 1); // single line border

                XSSFCellStyle cs2 = xssfworkbook.createCellStyle();
                cs2.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.index);
                cs2.setAlignment(XSSFCellStyle.ALIGN_LEFT);
                cs2.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                cs2.setBorderTop((short) 1); // single line border
                cs2.setBorderBottom((short) 1); // single line border

                XSSFCellStyle cs3 = xssfworkbook.createCellStyle();
                cs3.setFillForegroundColor(IndexedColors.LIME.index);
                cs3.setAlignment(XSSFCellStyle.ALIGN_LEFT);
                cs3.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                cs3.setBorderTop((short) 1); // single line border
                cs3.setBorderBottom((short) 1); // single line border

                XSSFCellStyle cellStyle = xssfworkbook.createCellStyle();
                XSSFCellStyle cellStyle1 = xssfworkbook.createCellStyle();
                XSSFCellStyle cellStyle2 = xssfworkbook.createCellStyle();
                XSSFCellStyle cellStyle3 = xssfworkbook.createCellStyle();
                XSSFCellStyle cellStyleHead = xssfworkbook.createCellStyle();
                XSSFFont font1 = xssfworkbook.createFont();
                XSSFFont font2 = xssfworkbook.createFont();
                XSSFFont font3 = xssfworkbook.createFont();
                XSSFFont fontHead = xssfworkbook.createFont();

                XSSFDataFormat df = xssfworkbook.createDataFormat();
                XSSFRow row = sheet.createRow((int) 0);
                XSSFCell cell = row.createCell((short) 0);
                XSSFCell cell1 = row.createCell((short) 1);
                XSSFCell cell2 = row.createCell((short) 2);
                XSSFCell cell3 = row.createCell((short) 3);
                XSSFCell cell4 = row.createCell((short) 4);
                XSSFCell cell5 = row.createCell((short) 5);
                XSSFCell cell6 = row.createCell((short) 6);
                XSSFCell cell7 = row.createCell((short) 7);
                XSSFCell cell8 = row.createCell((short) 8);
                XSSFCell cell9 = row.createCell((short) 9);

                XSSFRow row1 = sheet.createRow((int) 1);
                Cell cellX = row1.createCell((short) 1);
                cellX.setCellValue("Invoice:-Created Date : " + (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate());
                font1.setColor(IndexedColors.GREEN.index);
                cellStyle1.setFont(font1);
                cellX.setCellStyle(cellStyle1);
                sheet.addMergedRegion(CellRangeAddress.valueOf("B2:F2"));
//gnr
                XSSFRow row2 = sheet.createRow((int) 2);
                Cell cel2 = row2.createCell((short) 2);
                cel2.setCellValue("");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(XSSFCellStyle.ALIGN_CENTER);
                cel2.setCellStyle(cellStyleHead);
                sheet.addMergedRegion(CellRangeAddress.valueOf("C2:F2"));

                XSSFRow row3 = sheet.createRow((int) 3);
                Cell cel3 = row3.createCell((short) 0);
                cel3.setCellValue("Search Criteria :");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(XSSFCellStyle.ALIGN_CENTER);
                cel2.setCellStyle(cellStyleHead);

                row = sheet.createRow((int) 4);
                XSSFCell cella31 = row.createCell((short) 1);
                cella31.setCellValue("DateFrom");
                cella31.setCellStyle(cs2);
                XSSFCell cellb31 = row.createCell((short) 2);
                if (("").equals(getDateFrom()) || (getDateFrom() == null)) {
                    cellb31.setCellValue("--");
                } else {
                    cellb31.setCellValue(getDateFrom());
                }

                XSSFCell cellc31 = row.createCell((short) 4);
                cellc31.setCellValue("DateTo");
                cellc31.setCellStyle(cs2);
                XSSFCell celld31 = row.createCell((short) 5);
                if (("").equals(getDateTo()) || (getDateTo() == null)) {
                    celld31.setCellValue("--");
                } else {
                    celld31.setCellValue(getDateTo());
                }

                row = sheet.createRow((int) 5);

                XSSFCell cella41 = row.createCell((short) 1);
                cella41.setCellValue("SenderId");
                cella41.setCellStyle(cs2);
                XSSFCell cellb41 = row.createCell((short) 2);
                if (("-1").equals(getSenderId()) || (getSenderId() == null)) {
                    cellb41.setCellValue("--");
                } else {
                    cellb41.setCellValue(getSenderId());
                }

                XSSFCell cellc41 = row.createCell((short) 4);
                cellc41.setCellValue("SenderName");
                cellc41.setCellStyle(cs2);
                XSSFCell celld41 = row.createCell((short) 5);
                if (("-1").equals(getSenderName()) || (getSenderName() == null)) {
                    celld41.setCellValue("--");
                } else {
                    celld41.setCellValue(getSenderName());
                }

                row = sheet.createRow((int) 6);

                XSSFCell cella51 = row.createCell((short) 1);
                cella51.setCellValue("RecieverId");
                cella51.setCellStyle(cs2);
                XSSFCell cellb51 = row.createCell((short) 2);
                if (("-1").equals(getReceiverId()) || (getReceiverId() == null)) {
                    cellb51.setCellValue("--");
                } else {
                    cellb51.setCellValue(getReceiverId());
                }

                XSSFCell cellc51 = row.createCell((short) 4);
                cellc51.setCellValue("RecieverName");
                cellc51.setCellStyle(cs2);
                XSSFCell celld51 = row.createCell((short) 5);
                if (("-1").equals(getReceiverName()) || (getReceiverName() == null)) {
                    celld51.setCellValue("--");
                } else {
                    celld51.setCellValue(getReceiverName());
                }

                row = sheet.createRow((int) 7);

                XSSFCell cella61 = row.createCell((short) 1);
                cella61.setCellValue("Correlation");
                cella61.setCellStyle(cs2);
                XSSFCell cellb61 = row.createCell((short) 2);
                if (("-1").equals(getCorrattribute()) || (getCorrattribute() == null)) {
                    cellb61.setCellValue("--");
                } else {
                    cellb61.setCellValue(getCorrattribute());
                }

                XSSFCell cellc61 = row.createCell((short) 4);
                cellc61.setCellValue("Value");
                cellc61.setCellStyle(cs2);
                XSSFCell celld61 = row.createCell((short) 5);
                if (("").equals(getCorrvalue()) || (getCorrvalue() == null)) {
                    celld61.setCellValue("--");
                } else {
                    celld61.setCellValue(getCorrvalue());
                }

                row = sheet.createRow((int) 8);

                XSSFCell cella71 = row.createCell((short) 1);
                cella71.setCellValue("Correlation");
                cella71.setCellStyle(cs2);
                XSSFCell cellb71 = row.createCell((short) 2);
                if (("-1").equals(getCorrattribute1()) || (getCorrattribute1() == null)) {
                    cellb71.setCellValue("--");
                } else {
                    cellb71.setCellValue(getCorrattribute1());
                }

                XSSFCell cellc71 = row.createCell((short) 4);
                cellc71.setCellValue("Value");
                cellc71.setCellStyle(cs2);
                XSSFCell celld71 = row.createCell((short) 5);
                if (("").equals(getCorrvalue1()) || (getCorrvalue1() == null)) {
                    celld71.setCellValue("--");
                } else {
                    celld71.setCellValue(getCorrvalue1());
                }

                row = sheet.createRow((int) 9);

                XSSFCell cella91 = row.createCell((short) 1);
                cella91.setCellValue("DocType");
                cella91.setCellStyle(cs2);
                XSSFCell cellb91 = row.createCell((short) 2);
                if (("-1").equals(getDocType()) || (getDocType() == null)) {
                    cellb91.setCellValue("--");
                } else {
                    cellb91.setCellValue(getDocType());
                }

                XSSFCell cellc91 = row.createCell((short) 4);
                cellc91.setCellValue("Status");
                cellc91.setCellStyle(cs2);
                XSSFCell celld91 = row.createCell((short) 5);
                if (("-1").equals(getStatus()) || (getStatus() == null)) {
                    celld91.setCellValue("--");
                } else {
                    celld91.setCellValue(getStatus());
                }

                row = sheet.createRow((int) 11);
                XSSFCell cella1 = row.createCell((short) 0);
                cella1.setCellValue("SNO");
                cella1.setCellStyle(cs2);
                XSSFCell cellb1 = row.createCell((short) 1);
                cellb1.setCellValue("InstanceId");
                cellb1.setCellStyle(cs2);
                XSSFCell cellc1 = row.createCell((short) 2);
                cellc1.setCellValue("Partner");
                cellc1.setCellStyle(cs2);
                XSSFCell celld1 = row.createCell((short) 3);
                celld1.setCellValue("InvoiceNo");
                celld1.setCellStyle(cs2);
                XSSFCell celle1 = row.createCell((short) 4);
                celle1.setCellValue("PoNo");
                celle1.setCellStyle(cs2);
                XSSFCell cellf1 = row.createCell((short) 5);
                cellf1.setCellValue("ItemQuantity");
                cellf1.setCellStyle(cs2);
                XSSFCell cellg1 = row.createCell((short) 6);
                cellg1.setCellValue("InvoiceAmt");
                cellg1.setCellStyle(cs2);
                XSSFCell cellh1 = row.createCell((short) 7);
                cellh1.setCellValue("ShipmentId");
                cellh1.setCellStyle(cs2);
                XSSFCell celli1 = row.createCell((short) 8);
                celli1.setCellValue("InvoiceDate");
                celli1.setCellStyle(cs2);
                XSSFCell cellj1 = row.createCell((short) 9);
                cellj1.setCellValue("Status");
                cellj1.setCellStyle(cs2);

                int count1 = 0;
                if (finalList.size() > 0) {
                    Map docRepMap = null;
                    for (int i = 0; i < finalList.size(); i++) {
                        docRepMap = (Map) finalList.get(i);
                        row = sheet.createRow((int) i + 13);

                        cell = row.createCell((short) 0);
                        cell.setCellValue((String) docRepMap.get("SNO"));

                        cell1 = row.createCell((short) 1);
                        cell1.setCellValue((String) docRepMap.get("InstanceId"));

                        cell2 = row.createCell((short) 2);
                        cell2.setCellValue((String) docRepMap.get("Partner"));

                        cell3 = row.createCell((short) 3);
                        cell3.setCellValue((String) docRepMap.get("InvoiceNo"));

                        cell4 = row.createCell((short) 4);
                        cell4.setCellValue((String) docRepMap.get("PoNo"));

                        cell5 = row.createCell((short) 5);
                        cell5.setCellValue((String) docRepMap.get("ItemQuantity"));

                        cell6 = row.createCell((short) 6);
                        cell6.setCellValue((String) docRepMap.get("InvoiceAmt"));

                        cell7 = row.createCell((short) 7);
                        cell7.setCellValue(((String) docRepMap.get("ShipmentId")));

                        cell8 = row.createCell((short) 8);

                        if ((String) docRepMap.get("InvoiceDate") != null) {
                            cell8.setCellValue(((String) docRepMap.get("InvoiceDate")));
                        } else {
                        }

                        cell9 = row.createCell((short) 9);
                        cell9.setCellValue((String) docRepMap.get("Status"));

                        count1 = count1 + 1;
                    }
                    row = sheet.createRow((int) count1 + 14);

                    XSSFCell cellaz1 = row.createCell((short) 8);
                    cellaz1.setCellValue("Total : ");
                    cellaz1.setCellStyle(cs2);
                    XSSFCell cellbz1 = row.createCell((short) 9);
                    cellbz1.setCellValue(count1);
                }
                sheet.autoSizeColumn((short) 0);
                sheet.autoSizeColumn((short) 1);
                sheet.autoSizeColumn((short) 2);
                sheet.autoSizeColumn((short) 3);
                sheet.autoSizeColumn((short) 4);
                sheet.autoSizeColumn((short) 5);
                sheet.autoSizeColumn((short) 6);
                sheet.autoSizeColumn((short) 7);
                sheet.autoSizeColumn((short) 8);
                sheet.autoSizeColumn((short) 9);

                xssfworkbook.write(fileOut);
                fileOut.flush();
                fileOut.close();
            }

        } catch (FileNotFoundException fne) {
            fne.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preStmt != null) {
                    preStmt.close();
                    preStmt = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
        System.out.println("path=" + filePath);
        return filePath;
    }

    /*
     * Method for Excel Format Load Tendering Download
     */
    public String loadTenderingExcelDownload() throws ServiceLocatorException, SQLException, FileNotFoundException {
        String filePath = "";
        String tmp_Recieved_From = "";
        String tmp_Recieved_ToTime = "";
        StringBuffer SearchQuery = new StringBuffer();
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        List finalList = new ArrayList();
        corrattribute = getCorrattribute();
        corrvalue = getCorrvalue();
        corrattribute2 = getCorrattribute2();
        corrvalue2 = getCorrvalue2();
        corrattribute1 = getCorrattribute1();
        corrvalue1 = getCorrvalue1();
        docType = getDocType();
        status = getStatus();
        ackStatus = getAckStatus();
        docBusId = getDocBusId();
        senderId = getSenderId();
        senderName = getSenderName();
        receiverId = getReceiverId();
        receiverName = getReceiverName();
        dateTo = getDateTo();
        dateFrom = getDateFrom();
        File file = new File(Properties.getProperty("mscvp.loadTenderingCreationPath"));
        if (!file.exists()) {
            file.mkdirs();
        }
        Date date = new Date();
        FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + File.separator + "loadTendering.xlsx");
        SearchQuery.append("SELECT  tf.ID as ID,tf.FILE_ID as file_id,tf.ISA_NUMBER as isa_number,tl.SHIPMENT_ID as SHIPMENT_ID,TF.TRANSACTION_PURPOSE as TRANSACTION_PURPOSE, "
                + "tf.FILE_TYPE as file_type,tf.FILE_ORIGIN as file_origin,tf.TRANSACTION_TYPE as tran_type,tf.TMW_SENDERID as TMW_SENDERID,tf.TMW_RECEIVERID as TMW_RECEIVERID,"
                + "tf.ACK_STATUS as ack_status,tf.DIRECTION as direction,tf.DATE_TIME_RECEIVED as datetime,tf.SENDER_ID,tf.RECEIVER_ID,"
                + "tf.STATUS as status,tp1.NAME as name,tf.SEC_KEY_VAL as secval,tf.REPROCESSSTATUS as REPROCESSSTATUS "
                + "FROM Transport_loadtender tl LEFT OUTER JOIN FILES TF ON "
                + "(tl.FILE_ID=tf.FILE_ID and tl.SHIPMENT_ID=tf.PRI_KEY_VAL)  "
                + " LEFT OUTER JOIN TP TP1 ON (TP1.ID=TF.TMW_SENDERID) "
                + " LEFT OUTER JOIN TP TP2 ON (TP2.ID=TF.TMW_RECEIVERID)"
                + " LEFT OUTER JOIN TP TP3 ON (TP3.ID=TF.SENDER_ID)"
                + " LEFT OUTER JOIN TP TP4 ON (TP4.ID=TF.RECEIVER_ID)");

        SearchQuery.append(" WHERE 1=1 AND tf.FLOWFLAG LIKE '%L%'");
        if (corrattribute != null && corrattribute.equalsIgnoreCase("Shipment Number")) // || corrattribute.equalsIgnoreCase("Invoice Number")  || corrattribute.equalsIgnoreCase("Shipment Number") || corrattribute.equalsIgnoreCase("Cheque Number") )
        {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                SearchQuery.append(WildCardSql.getWildCardSql1("tl.SHIPMENT_ID",
                        corrvalue.trim().toUpperCase()));
            }
        }

        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("Shipment Number")) // || corrattribute.equalsIgnoreCase("Invoice Number")  || corrattribute.equalsIgnoreCase("Shipment Number") || corrattribute.equalsIgnoreCase("Cheque Number") )
        {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                SearchQuery.append(WildCardSql.getWildCardSql1("tl.SHIPMENT_ID",
                        corrvalue1.trim().toUpperCase()));
            }
        }

        if (corrattribute2 != null && corrattribute2.equalsIgnoreCase("Shipment Number")) // || corrattribute.equalsIgnoreCase("Invoice Number")  || corrattribute.equalsIgnoreCase("Shipment Number") || corrattribute.equalsIgnoreCase("Cheque Number") )
        {
            if (corrvalue2 != null && !"".equals(corrvalue2.trim())) {
                SearchQuery.append(WildCardSql.getWildCardSql1("tl.SHIPMENT_ID",
                        corrvalue2.trim().toUpperCase()));
            }
        }

        if (corrattribute != null && corrattribute.equalsIgnoreCase("BOL Number")) {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                SearchQuery.append(WildCardSql.getWildCardSql1("tl.BOL_NUMBER",
                        corrvalue.trim().toUpperCase()));
            }
        }

        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("BOL Number")) {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                SearchQuery.append(WildCardSql.getWildCardSql1("tl.BOL_NUMBER",
                        corrvalue1.trim().toUpperCase()));
            }
        }

        if (corrattribute2 != null && corrattribute2.equalsIgnoreCase("BOL Number")) {
            if (corrvalue2 != null && !"".equals(corrvalue2.trim())) {
                SearchQuery.append(WildCardSql.getWildCardSql1("tl.BOL_NUMBER",
                        corrvalue2.trim().toUpperCase()));
            }
        }
        // isa

        if (corrattribute != null && corrattribute.equalsIgnoreCase("PO Number")) {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                SearchQuery.append(WildCardSql.getWildCardSql1("tl.PO_NUMBER",
                        corrvalue.trim().toUpperCase()));
            }
        }

        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("PO Number")) {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                SearchQuery.append(WildCardSql.getWildCardSql1("tl.PO_NUMBER",
                        corrvalue1.trim().toUpperCase()));
            }
        }

        if (corrattribute2 != null && corrattribute2.equalsIgnoreCase("PO Number")) {
            if (corrvalue2 != null && !"".equals(corrvalue2.trim())) {
                SearchQuery.append(WildCardSql.getWildCardSql1("tl.PO_NUMBER",
                        corrvalue2.trim().toUpperCase()));
            }
        }
        // bol
        if (corrattribute != null && corrattribute.equalsIgnoreCase("CO Number")) {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                SearchQuery.append(WildCardSql.getWildCardSql1("tl.CO_NUMBER",
                        corrvalue.trim().toUpperCase()));
            }
        }
        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("CO Number")) {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                SearchQuery.append(WildCardSql.getWildCardSql1("tl.CO_NUMBER",
                        corrvalue1.trim().toUpperCase()));
            }
        }
        if (corrattribute2 != null && corrattribute2.equalsIgnoreCase("CO Number")) {
            if (corrvalue2 != null && !"".equals(corrvalue2.trim())) {
                SearchQuery.append(WildCardSql.getWildCardSql1("tl.CO_NUMBER",
                        corrvalue2.trim().toUpperCase()));
            }
        }

        if (docType != null && !"-1".equals(docType.trim())) {
            SearchQuery.append(WildCardSql.getWildCardSql1("TF.TRANSACTION_TYPE",
                    docType.trim()));
        }
        //Status
        if (status != null && !"-1".equals(status.trim())) {
            SearchQuery.append(WildCardSql.getWildCardSql1("TF.STATUS",
                    status.trim()));
        }
        //ACK_STATUS
        if (ackStatus != null && !"-1".equals(ackStatus.trim())) {
            SearchQuery.append(WildCardSql.getWildCardSql1("TF.ACK_STATUS",
                    ackStatus.trim()));
        }

        if (receiverId != null && !"".equals(receiverId.trim())) {
            SearchQuery.append(" AND (TF.RECEIVER_ID like '%" + receiverId + "%' OR TF.TMW_RECEIVERID like '%" + receiverId + "%')");
        }

        if (senderId != null && !"".equals(senderId.trim())) {
            SearchQuery.append(" AND (TF.SENDER_ID like '%" + senderId + "%' OR TF.TMW_SENDERID like '%" + senderId + "%')");
        }

        if (senderName != null && !"".equals(senderName.trim())) {

            SearchQuery.append(" AND (TP3.NAME like '%" + senderName + "%' OR TP1.NAME like '%" + senderName + "%')");

        }

        if (receiverName != null && !"".equals(receiverName.trim())) {
            SearchQuery.append(" AND (TP4.NAME like '%" + receiverName + "%' OR TP2.NAME like '%" + receiverName + "%')");

        }

        if (dateTo != null && !"".equals(dateTo)) {
            tmp_Recieved_From = DateUtility.getInstance().DateViewToDBCompare(dateTo);
            SearchQuery.append(" AND tf.DATE_TIME_RECEIVED <= '" + tmp_Recieved_From
                    + "'");
        }
        if (dateFrom != null && !"".equals(dateFrom)) {
            tmp_Recieved_From = DateUtility.getInstance().DateViewToDBCompare(dateFrom);
            SearchQuery.append(" AND tf.DATE_TIME_RECEIVED >= '" + tmp_Recieved_From
                    + "'");
        }
        SearchQuery.append(" order by DATE_TIME_RECEIVED DESC ");
        System.out.println("load query-->" + SearchQuery.toString());
        String searchQuery = SearchQuery.toString();
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            String name = "";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(searchQuery);
            int j = 1;
            while (resultSet.next()) {

                int id = resultSet.getInt("ID");
                String file_Id = resultSet.getString("file_id");
                String file_Origin = resultSet.getString("file_origin");
                String file_Type = resultSet.getString("file_type");
                String isa_Number = resultSet.getString("isa_number");
                String tran_Type = resultSet.getString("tran_type");
                String direction = resultSet.getString("direction");
                Timestamp dt = resultSet.getTimestamp("datetime");
                String datetime = dt.toString().substring(0, dt.toString().lastIndexOf(":"));
                String status = resultSet.getString("status");
                String transactionpurpose = resultSet.getString("TRANSACTION_PURPOSE");
                if (resultSet.getString("name") != null) {
                    name = resultSet.getString("name");
                } else {
                    name = "";
                }
                String secval = resultSet.getString("secval");
                String reprocessstatus = resultSet.getString("REPROCESSSTATUS");
                String ackstatus = resultSet.getString("ack_status");
                String shipmentid = resultSet.getString("SHIPMENT_ID");
                String tmwsenderid = resultSet.getString("TMW_SENDERID");
                String tmwreceiverid = resultSet.getString("TMW_RECEIVERID");

                Map map = new HashMap();
                map.put("SNO", String.valueOf(j));
                map.put("id", id);
                map.put("file_Id", file_Id);
                map.put("file_Origin", file_Origin);
                map.put("file_Type", file_Type);
                map.put("isa_Number", isa_Number);
                map.put("tran_Type", tran_Type);
                map.put("direction", direction);
                map.put("datetime", datetime);
                map.put("status1", status);
                map.put("transactionpurpose", transactionpurpose);
                map.put("name", name);
                map.put("secval", secval);
                map.put("reprocessstatus", reprocessstatus);
                map.put("ackstatus", ackstatus);
                map.put("shipmentid", shipmentid);
                map.put("tmwsenderid", tmwsenderid);
                map.put("tmwreceiverid", tmwreceiverid);

                finalList.add(map);
                j++;
            }

            if (finalList.size() > 0) {
                filePath = file.getAbsolutePath() + Properties.getProperty("os.compatability") + "loadTendering.xlsx";
                XSSFWorkbook xssfworkbook = new XSSFWorkbook();
                XSSFSheet sheet = xssfworkbook.createSheet("LoadTendering");
                XSSFCellStyle cs = xssfworkbook.createCellStyle();
                cs.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                cs.setAlignment(XSSFCellStyle.ALIGN_LEFT);
                cs.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                cs.setBorderTop((short) 1); // single line border
                cs.setBorderBottom((short) 1); // single line border
                XSSFCellStyle headercs = xssfworkbook.createCellStyle();
                headercs.setFillForegroundColor(IndexedColors.AQUA.index);
                headercs.setAlignment(XSSFCellStyle.ALIGN_CENTER);
                headercs.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                headercs.setBorderTop((short) 1); // single line border
                headercs.setBorderBottom((short) 1); // single line border

                XSSFCellStyle cs1 = xssfworkbook.createCellStyle();
                cs1.setFillForegroundColor(IndexedColors.GREEN.index);
                cs1.setAlignment(XSSFCellStyle.ALIGN_LEFT);
                cs1.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                cs1.setBorderTop((short) 1); // single line border
                cs1.setBorderBottom((short) 1); // single line border

                XSSFCellStyle cs2 = xssfworkbook.createCellStyle();
                cs2.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.index);
                cs2.setAlignment(XSSFCellStyle.ALIGN_LEFT);
                cs2.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                cs2.setBorderTop((short) 1); // single line border
                cs2.setBorderBottom((short) 1); // single line border

                XSSFCellStyle cs3 = xssfworkbook.createCellStyle();
                cs3.setFillForegroundColor(IndexedColors.LIME.index);
                cs3.setAlignment(XSSFCellStyle.ALIGN_LEFT);
                cs3.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                cs3.setBorderTop((short) 1); // single line border
                cs3.setBorderBottom((short) 1); // single line border

                XSSFCellStyle cellStyle = xssfworkbook.createCellStyle();
                XSSFCellStyle cellStyle1 = xssfworkbook.createCellStyle();
                XSSFCellStyle cellStyle2 = xssfworkbook.createCellStyle();
                XSSFCellStyle cellStyle3 = xssfworkbook.createCellStyle();
                XSSFCellStyle cellStyleHead = xssfworkbook.createCellStyle();
                XSSFFont font1 = xssfworkbook.createFont();
                XSSFFont font2 = xssfworkbook.createFont();
                XSSFFont font3 = xssfworkbook.createFont();
                XSSFFont fontHead = xssfworkbook.createFont();

                XSSFDataFormat df = xssfworkbook.createDataFormat();
                XSSFRow row = sheet.createRow((int) 0);
                XSSFCell cell = row.createCell((short) 0);
                XSSFCell cell1 = row.createCell((short) 1);
                XSSFCell cell2 = row.createCell((short) 2);
                XSSFCell cell3 = row.createCell((short) 3);
                XSSFCell cell4 = row.createCell((short) 4);
                XSSFCell cell5 = row.createCell((short) 5);
                XSSFCell cell6 = row.createCell((short) 6);
                XSSFCell cell7 = row.createCell((short) 7);
                XSSFCell cell8 = row.createCell((short) 8);
                XSSFCell cell9 = row.createCell((short) 9);

                XSSFRow row1 = sheet.createRow((int) 1);
                Cell cellX = row1.createCell((short) 1);
                cellX.setCellValue("Load Tendering:-Created Date : " + (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate());
                font1.setColor(IndexedColors.GREEN.index);
                cellStyle1.setFont(font1);
                cellX.setCellStyle(cellStyle1);
                sheet.addMergedRegion(CellRangeAddress.valueOf("B2:F2"));

//gnr
                XSSFRow row2 = sheet.createRow((int) 2);
                Cell cel2 = row2.createCell((short) 2);
                cel2.setCellValue("");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(XSSFCellStyle.ALIGN_CENTER);
                cel2.setCellStyle(cellStyleHead);
                sheet.addMergedRegion(CellRangeAddress.valueOf("C2:F2"));

                XSSFRow row3 = sheet.createRow((int) 3);
                Cell cel3 = row3.createCell((short) 0);
                cel3.setCellValue("Search Criteria :");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(XSSFCellStyle.ALIGN_CENTER);
                cel2.setCellStyle(cellStyleHead);

                row = sheet.createRow((int) 4);
                XSSFCell cella31 = row.createCell((short) 1);
                cella31.setCellValue("DateFrom");
                cella31.setCellStyle(cs2);
                XSSFCell cellb31 = row.createCell((short) 2);
                if (("").equals(getDateFrom()) || (getDateFrom() == null)) {
                    cellb31.setCellValue("--");
                } else {
                    cellb31.setCellValue(getDateFrom());
                }

                XSSFCell cellc31 = row.createCell((short) 4);
                cellc31.setCellValue("DateTo");
                cellc31.setCellStyle(cs2);
                XSSFCell celld31 = row.createCell((short) 5);
                if (("").equals(getDateTo()) || (getDateTo() == null)) {
                    celld31.setCellValue("--");
                } else {
                    celld31.setCellValue(getDateTo());
                }

                row = sheet.createRow((int) 5);

                XSSFCell cella41 = row.createCell((short) 1);
                cella41.setCellValue("SenderId");
                cella41.setCellStyle(cs2);
                XSSFCell cellb41 = row.createCell((short) 2);
                if (("-1").equals(getSenderId()) || (getSenderId() == null)) {
                    cellb41.setCellValue("--");
                } else {
                    cellb41.setCellValue(getSenderId());
                }

                XSSFCell cellc41 = row.createCell((short) 4);
                cellc41.setCellValue("SenderName");
                cellc41.setCellStyle(cs2);
                XSSFCell celld41 = row.createCell((short) 5);
                if (("-1").equals(getSenderName()) || (getSenderName() == null)) {
                    celld41.setCellValue("--");
                } else {
                    celld41.setCellValue(getSenderName());
                }

                row = sheet.createRow((int) 6);

                XSSFCell cella51 = row.createCell((short) 1);
                cella51.setCellValue("RecieverId");
                cella51.setCellStyle(cs2);
                XSSFCell cellb51 = row.createCell((short) 2);
                if (("-1").equals(getReceiverId()) || (getReceiverId() == null)) {
                    cellb51.setCellValue("--");
                } else {
                    cellb51.setCellValue(getReceiverId());
                }

                XSSFCell cellc51 = row.createCell((short) 4);
                cellc51.setCellValue("RecieverName");
                cellc51.setCellStyle(cs2);
                XSSFCell celld51 = row.createCell((short) 5);
                if (("-1").equals(getReceiverName()) || (getReceiverName() == null)) {
                    celld51.setCellValue("--");
                } else {
                    celld51.setCellValue(getReceiverName());
                }

                row = sheet.createRow((int) 7);

                XSSFCell cella61 = row.createCell((short) 1);
                cella61.setCellValue("Correlation");
                cella61.setCellStyle(cs2);
                XSSFCell cellb61 = row.createCell((short) 2);
                if (("-1").equals(getCorrattribute()) || (getCorrattribute() == null)) {
                    cellb61.setCellValue("--");
                } else {
                    cellb61.setCellValue(getCorrattribute());
                }

                XSSFCell cellc61 = row.createCell((short) 4);
                cellc61.setCellValue("Value");
                cellc61.setCellStyle(cs2);
                XSSFCell celld61 = row.createCell((short) 5);
                if (("").equals(getCorrvalue()) || (getCorrvalue() == null)) {
                    celld61.setCellValue("--");
                } else {
                    celld61.setCellValue(getCorrvalue());
                }

                row = sheet.createRow((int) 8);

                XSSFCell cella71 = row.createCell((short) 1);
                cella71.setCellValue("Correlation");
                cella71.setCellStyle(cs2);
                XSSFCell cellb71 = row.createCell((short) 2);
                if (("-1").equals(getCorrattribute1()) || (getCorrattribute1() == null)) {
                    cellb71.setCellValue("--");
                } else {
                    cellb71.setCellValue(getCorrattribute1());
                }

                XSSFCell cellc71 = row.createCell((short) 4);
                cellc71.setCellValue("Value");
                cellc71.setCellStyle(cs2);
                XSSFCell celld71 = row.createCell((short) 5);
                if (("").equals(getCorrvalue1()) || (getCorrvalue1() == null)) {
                    celld71.setCellValue("--");
                } else {
                    celld71.setCellValue(getCorrvalue1());
                }
                row = sheet.createRow((int) 9);

                XSSFCell cella81 = row.createCell((short) 1);
                cella81.setCellValue("Correlation");
                cella81.setCellStyle(cs2);
                XSSFCell cellb81 = row.createCell((short) 2);
                if (("-1").equals(getCorrattribute2()) || (getCorrattribute2() == null)) {
                    cellb81.setCellValue("--");
                } else {
                    cellb81.setCellValue(getCorrattribute2());
                }
                XSSFCell cellc81 = row.createCell((short) 4);
                cellc81.setCellValue("Value");
                cellc81.setCellStyle(cs2);
                XSSFCell celld81 = row.createCell((short) 5);
                if (("").equals(getCorrvalue2()) || (getCorrvalue2() == null)) {
                    celld81.setCellValue("--");
                } else {
                    celld81.setCellValue(getCorrvalue2());
                }
                //  
                row = sheet.createRow((int) 10);

                XSSFCell cella91 = row.createCell((short) 1);
                cella91.setCellValue("DocType");
                cella91.setCellStyle(cs2);
                XSSFCell cellb91 = row.createCell((short) 2);
                if (("-1").equals(getDocType()) || (getDocType() == null)) {
                    cellb91.setCellValue("--");
                } else {
                    cellb91.setCellValue(getDocType());
                }

                XSSFCell cellc91 = row.createCell((short) 4);
                cellc91.setCellValue("Status");
                cellc91.setCellStyle(cs2);
                XSSFCell celld91 = row.createCell((short) 5);
                if (("-1").equals(getStatus()) || (getStatus() == null)) {
                    celld91.setCellValue("--");
                } else {
                    celld91.setCellValue(getStatus());
                }
                row = sheet.createRow((int) 12);
                XSSFCell cella1 = row.createCell((short) 0);
                cella1.setCellValue("SNO");
                cella1.setCellStyle(cs2);
                XSSFCell cellb1 = row.createCell((short) 1);
                cellb1.setCellValue("FileFormat");
                cellb1.setCellStyle(cs2);
                XSSFCell cellc1 = row.createCell((short) 2);
                cellc1.setCellValue("InstanceId");
                cellc1.setCellStyle(cs2);
                XSSFCell celld1 = row.createCell((short) 3);
                celld1.setCellValue("Shipment");
                celld1.setCellStyle(cs2);
                XSSFCell celle1 = row.createCell((short) 4);
                celle1.setCellValue("Partner");
                celle1.setCellStyle(cs2);
                XSSFCell cellf1 = row.createCell((short) 5);
                cellf1.setCellValue("DateTime");
                cellf1.setCellStyle(cs2);
                XSSFCell cellg1 = row.createCell((short) 6);
                cellg1.setCellValue("Direction");
                cellg1.setCellStyle(cs2);
                XSSFCell cellh1 = row.createCell((short) 7);
                cellh1.setCellValue("Status");
                cellh1.setCellStyle(cs2);
                XSSFCell celli1 = row.createCell((short) 8);
                celli1.setCellValue("TransPurpose");
                celli1.setCellStyle(cs2);
                int count1 = 0;
                if (finalList.size() > 0) {
                    Map docRepMap = null;
                    for (int i = 0; i < finalList.size(); i++) {
                        docRepMap = (Map) finalList.get(i);
                        row = sheet.createRow((int) i + 13);

                        cell = row.createCell((short) 0);
                        cell.setCellValue((String) docRepMap.get("SNO"));

                        cell1 = row.createCell((short) 1);
                        cell1.setCellValue((String) docRepMap.get("file_Type"));

                        cell2 = row.createCell((short) 2);
                        cell2.setCellValue((String) docRepMap.get("file_Id"));

                        cell3 = row.createCell((short) 3);
                        cell3.setCellValue((String) docRepMap.get("shipmentid"));

                        cell4 = row.createCell((short) 4);
                        cell4.setCellValue((String) docRepMap.get("name"));

                        cell5 = row.createCell((short) 5);
                        cell5.setCellValue((String) docRepMap.get("datetime"));

                        cell6 = row.createCell((short) 6);
                        cell6.setCellValue((String) docRepMap.get("direction"));

                        cell7 = row.createCell((short) 7);

                        cell7.setCellValue(((String) docRepMap.get("status1")));

                        String transactionpurpose = (String) docRepMap.get("transactionpurpose");
                        cell8 = row.createCell((short) 8);

                        if (transactionpurpose != null) {
                            if (transactionpurpose.equalsIgnoreCase("N")) {
                                cell8.setCellValue("New Order");
                            } else if (transactionpurpose.equalsIgnoreCase("U")) {
                                cell8.setCellValue("Update");
                            } else if (transactionpurpose.equalsIgnoreCase("C")) {
                                cell8.setCellValue("Cancel");
                            } else {
                                cell8.setCellValue(transactionpurpose);
                            }
                        }

                        count1 = count1 + 1;
                    }
                    row = sheet.createRow((int) count1 + 14);

                    XSSFCell cellaz1 = row.createCell((short) 8);
                    cellaz1.setCellValue("Total : ");
                    cellaz1.setCellStyle(cs2);
                    XSSFCell cellbz1 = row.createCell((short) 9);
                    cellbz1.setCellValue(count1);
                }
                sheet.autoSizeColumn((short) 0);
                sheet.autoSizeColumn((short) 1);
                sheet.autoSizeColumn((short) 2);
                sheet.autoSizeColumn((short) 3);
                sheet.autoSizeColumn((short) 4);
                sheet.autoSizeColumn((short) 5);
                sheet.autoSizeColumn((short) 6);
                sheet.autoSizeColumn((short) 7);
                sheet.autoSizeColumn((short) 8);
                sheet.autoSizeColumn((short) 9);

                xssfworkbook.write(fileOut);
                fileOut.flush();
                fileOut.close();
            }

        } catch (FileNotFoundException fne) {
            fne.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
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
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
        System.out.println("path=" + filePath);
        return filePath;
    }
    /*
     * Method for Excel Format Load Tendering Download
     */

    public String ltResponseExcelDownload() throws ServiceLocatorException, SQLException, FileNotFoundException {

        String filePath = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String tmp_Recieved_From = "";
        String tmp_Recieved_ToTime = "";
        StringBuffer ltResponseSearchQuery = new StringBuffer();
        List ltResponseList1;
        corrattribute = getCorrattribute();
        corrvalue = getCorrvalue();
        corrattribute1 = getCorrattribute1();
        corrvalue1 = getCorrvalue1();
        docType = getDocType();
        status = getStatus();
        ackStatus = getAckStatus();
        receiverId = getReceiverId();
        receiverName = getReceiverName();
        senderId = getSenderId();
        senderName = getSenderName();
        dateTo = getDateTo();
        dateFrom = getDateFrom();
        Date date = new Date();
        File file = new File(Properties.getProperty("mscvp.ltResponseCreationPath"));
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + File.separator + "ltResponse.xlsx");

        ltResponseSearchQuery.append("SELECT DISTINCT(FILES.FILE_ID) as FILE_ID,TRANSPORT_LT_RESPONSE.REF_ID,TRANSPORT_LT_RESPONSE.SHIPMENT_ID as SHIPMENT_ID,"
                + "FILES.ISA_NUMBER as ISA_NUMBER,FILES.FILE_TYPE as FILE_TYPE,FILES.ID as ID,FILES.TMW_SENDERID as TMW_SENDERID,FILES.TMW_RECEIVERID as TMW_RECEIVERID,"
                + "FILES.FILE_ORIGIN as FILE_ORIGIN,FILES.TRANSACTION_TYPE as TRANSACTION_TYPE,FILES.SENDER_ID,FILES.RECEIVER_ID,"
                + "FILES.DIRECTION as DIRECTION,FILES.DATE_TIME_RECEIVED as DATE_TIME_RECEIVED,"
                + "FILES.STATUS as STATUS,FILES.ACK_STATUS as ACK_STATUS,TP2.NAME as RECEIVER_NAME,"
                + "FILES.SEC_KEY_VAL,FILES.REPROCESSSTATUS,TRANSPORT_LT_RESPONSE.RESPONSE_STATUS "
                + "FROM TRANSPORT_LT_RESPONSE "
                + "LEFT OUTER JOIN FILES ON (TRANSPORT_LT_RESPONSE.FILE_ID =FILES.FILE_ID)"
                + "LEFT OUTER JOIN TP TP1 ON (TP1.ID=FILES.TMW_SENDERID) "
                + "LEFT OUTER JOIN TP TP2 ON (TP2.ID=FILES.TMW_RECEIVERID)"
                + "LEFT OUTER JOIN TP TP3 ON (TP3.ID=FILES.SENDER_ID)"
                + "LEFT OUTER JOIN TP TP4 ON (TP4.ID=FILES.RECEIVER_ID)");

        ltResponseSearchQuery.append(" WHERE 1=1 AND FILES.FLOWFLAG = 'L' ");

        if (corrattribute != null && corrattribute.equalsIgnoreCase("Order Number")) // || corrattribute.equalsIgnoreCase("Invoice Number")  || corrattribute.equalsIgnoreCase("Shipment Number") || corrattribute.equalsIgnoreCase("Cheque Number") )
        {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                ltResponseSearchQuery.append(WildCardSql.getWildCardSql1("TRANSPORT_LT_RESPONSE.REF_ID",
                        corrvalue.trim().toUpperCase()));
            }
        }

        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("Order Number")) // || corrattribute.equalsIgnoreCase("Invoice Number")  || corrattribute.equalsIgnoreCase("Shipment Number") || corrattribute.equalsIgnoreCase("Cheque Number") )
        {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                ltResponseSearchQuery.append(WildCardSql.getWildCardSql1("TRANSPORT_LT_RESPONSE.REF_ID",
                        corrvalue1.trim().toUpperCase()));
            }
        }

        if (corrattribute != null && corrattribute.equalsIgnoreCase("Shipment Number")) {
            if (corrvalue != null && !"".equals(corrvalue.trim())) {
                ltResponseSearchQuery.append(WildCardSql.getWildCardSql1("TRANSPORT_LT_RESPONSE.SHIPMENT_ID",
                        corrvalue.trim().toUpperCase()));
            }
        }

        if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("Shipment Number")) {
            if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                ltResponseSearchQuery.append(WildCardSql.getWildCardSql1("TRANSPORT_LT_RESPONSE.SHIPMENT_ID",
                        corrvalue1.trim().toUpperCase()));
            }
        }

        if (docType != null && !"-1".equals(docType.trim())) {
            ltResponseSearchQuery.append(WildCardSql.getWildCardSql1("FILES.TRANSACTION_TYPE",
                    docType.trim()));
        }
        //Status
        if (status != null && !"-1".equals(status.trim())) {
            ltResponseSearchQuery.append(WildCardSql.getWildCardSql1("FILES.STATUS",
                    status.trim()));
        }
        //ACK_STATUS
        if (ackStatus != null && !"-1".equals(ackStatus.trim())) {
            ltResponseSearchQuery.append(WildCardSql.getWildCardSql1("FILES.ACK_STATUS",
                    ackStatus.trim()));
        }

        if (receiverId != null && !"".equals(receiverId.trim())) {
            ltResponseSearchQuery.append("AND (FILES.RECEIVER_ID like '%" + receiverId + "%' OR FILES.TMW_RECEIVERID like '%" + receiverId + "%')");
        }

        if (senderId != null && !"".equals(senderId.trim())) {
            ltResponseSearchQuery.append("AND (FILES.SENDER_ID like '%" + senderId + "%' OR FILES.TMW_SENDERID like '%" + senderId + "%')");
        }

        if (senderName != null && !"".equals(senderName.trim())) {

            ltResponseSearchQuery.append(" AND (TP3.NAME like '%" + senderName + "%' OR TP1.NAME like '%" + senderName + "%')  ");

        }

        if (receiverName != null && !"".equals(receiverName.trim())) {
            ltResponseSearchQuery.append("AND (TP4.NAME like '%" + receiverName + "%' OR TP2.NAME like '%" + receiverName + "%')");

        }

        if (dateTo != null && !"".equals(dateTo)) {
            tmp_Recieved_ToTime = DateUtility.getInstance().DateViewToDBCompare(dateTo);
            ltResponseSearchQuery.append(" AND FILES.DATE_TIME_RECEIVED <= '" + tmp_Recieved_ToTime
                    + "'");
        }
        if (dateFrom != null && !"".equals(dateFrom)) {
            tmp_Recieved_From = DateUtility.getInstance().DateViewToDBCompare(dateFrom);
            ltResponseSearchQuery.append(" AND FILES.DATE_TIME_RECEIVED >= '" + tmp_Recieved_From
                    + "'");
        }
        ltResponseSearchQuery.append(" order by DATE_TIME_RECEIVED DESC ");
        System.out.println("Lt Response query-->" + ltResponseSearchQuery.toString());
        String searchQuery = ltResponseSearchQuery.toString();
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(searchQuery);
            ltResponseList1 = new ArrayList<LtResponseBean>();
            int j = 1;
            while (resultSet.next()) {
                String file_id = resultSet.getString("FILE_ID");
                String file_Origin = resultSet.getString("FILE_ORIGIN");
                String file_Type = resultSet.getString("FILE_TYPE");
                String isa_number = resultSet.getString("ISA_NUMBER");
                String transaction_Type = resultSet.getString("TRANSACTION_TYPE");
                String direction = resultSet.getString("DIRECTION");
                String status = resultSet.getString("STATUS");
                String receiver_Name = resultSet.getString("RECEIVER_NAME");
                String seckey_Val = resultSet.getString("SEC_KEY_VAL");
                String reprocesstatus = resultSet.getString("REPROCESSSTATUS");
                String responseStauts = resultSet.getString("RESPONSE_STATUS");
                String ackstatus = resultSet.getString("ACK_STATUS");
                String ref_id = resultSet.getString("REF_ID");
                String shipment_id = resultSet.getString("SHIPMENT_ID");
                String tmwsenderid = resultSet.getString("TMW_SENDERID");
                String tmwreceiverid = resultSet.getString("TMW_RECEIVERID");
                Map map = new HashMap();
                map.put("SNO", String.valueOf(j));
                map.put("file_id", file_id);
                map.put("file_Origin", file_Origin);
                map.put("file_Type", file_Type);
                map.put("isa_number", isa_number);
                map.put("transaction_Type", transaction_Type);
                map.put("direction", direction);
                map.put("status", status);
                map.put("receiver_Name", receiver_Name);
                map.put("seckey_Val", seckey_Val);
                map.put("reprocesstatus", reprocesstatus);
                map.put("responseStauts", responseStauts);
                map.put("ackstatus", ackstatus);
                map.put("ref_id", ref_id);
                map.put("shipment_id", shipment_id);
                map.put("tmwsenderid", tmwsenderid);
                map.put("tmwreceiverid", tmwreceiverid);
                map.put("partner", receiver_Name);
                ltResponseList1.add(map);
                j++;
            }

            if (ltResponseList1.size() > 0) {
                filePath = file.getAbsolutePath() + Properties.getProperty("os.compatability") + "ltResponse.xlsx";

                XSSFWorkbook xssfworkbook = new XSSFWorkbook();
                XSSFSheet sheet = xssfworkbook.createSheet("ltResponse");
                XSSFCellStyle cs = xssfworkbook.createCellStyle();
                cs.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                cs.setAlignment(XSSFCellStyle.ALIGN_LEFT);
                cs.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                cs.setBorderTop((short) 1); // single line border
                cs.setBorderBottom((short) 1); // single line border

                XSSFCellStyle headercs = xssfworkbook.createCellStyle();
                headercs.setFillForegroundColor(IndexedColors.AQUA.index);
                headercs.setAlignment(XSSFCellStyle.ALIGN_CENTER);
                headercs.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                headercs.setBorderTop((short) 1); // single line border
                headercs.setBorderBottom((short) 1); // single line border

                XSSFCellStyle cs1 = xssfworkbook.createCellStyle();
                cs1.setFillForegroundColor(IndexedColors.GREEN.index);
                cs1.setAlignment(XSSFCellStyle.ALIGN_LEFT);
                cs1.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                cs1.setBorderTop((short) 1); // single line border
                cs1.setBorderBottom((short) 1); // single line border

                XSSFCellStyle cs2 = xssfworkbook.createCellStyle();
                cs2.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.index);
                cs2.setAlignment(XSSFCellStyle.ALIGN_LEFT);
                cs2.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                cs2.setBorderTop((short) 1); // single line border
                cs2.setBorderBottom((short) 1); // single line border

                XSSFCellStyle cs3 = xssfworkbook.createCellStyle();
                cs3.setFillForegroundColor(IndexedColors.LIME.index);
                cs3.setAlignment(XSSFCellStyle.ALIGN_LEFT);
                cs3.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                cs3.setBorderTop((short) 1); // single line border
                cs3.setBorderBottom((short) 1); // single line border

                XSSFCellStyle cellStyle = xssfworkbook.createCellStyle();
                XSSFCellStyle cellStyle1 = xssfworkbook.createCellStyle();
                XSSFCellStyle cellStyle2 = xssfworkbook.createCellStyle();
                XSSFCellStyle cellStyle3 = xssfworkbook.createCellStyle();
                XSSFCellStyle cellStyleHead = xssfworkbook.createCellStyle();
                XSSFFont font1 = xssfworkbook.createFont();
                XSSFFont font2 = xssfworkbook.createFont();
                XSSFFont font3 = xssfworkbook.createFont();
                XSSFFont fontHead = xssfworkbook.createFont();

                XSSFDataFormat df = xssfworkbook.createDataFormat();
                XSSFRow row = sheet.createRow((int) 0);
                XSSFCell cell = row.createCell((short) 0);
                XSSFCell cell1 = row.createCell((short) 1);
                XSSFCell cell2 = row.createCell((short) 2);
                XSSFCell cell3 = row.createCell((short) 3);
                XSSFCell cell4 = row.createCell((short) 4);
                XSSFCell cell5 = row.createCell((short) 5);
                XSSFCell cell6 = row.createCell((short) 6);
                XSSFCell cell7 = row.createCell((short) 7);
                XSSFCell cell8 = row.createCell((short) 8);
                XSSFCell cell9 = row.createCell((short) 9);

                XSSFRow row1 = sheet.createRow((int) 1);
                Cell cellX = row1.createCell((short) 1);
                cellX.setCellValue("Response:-Created Date : " + (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate());
                font1.setColor(IndexedColors.GREEN.index);
                cellStyle1.setFont(font1);
                cellX.setCellStyle(cellStyle1);
                sheet.addMergedRegion(CellRangeAddress.valueOf("B2:F2"));
//gnr
                XSSFRow row2 = sheet.createRow((int) 2);
                Cell cel2 = row2.createCell((short) 2);
                cel2.setCellValue("");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(XSSFCellStyle.ALIGN_CENTER);
                cel2.setCellStyle(cellStyleHead);
                sheet.addMergedRegion(CellRangeAddress.valueOf("C2:F2"));

                XSSFRow row3 = sheet.createRow((int) 3);
                Cell cel3 = row3.createCell((short) 0);
                cel3.setCellValue("Search Criteria :");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(XSSFCellStyle.ALIGN_CENTER);
                cel2.setCellStyle(cellStyleHead);

                row = sheet.createRow((int) 4);
                XSSFCell cella31 = row.createCell((short) 1);
                cella31.setCellValue("DateFrom");
                cella31.setCellStyle(cs2);
                XSSFCell cellb31 = row.createCell((short) 2);
                if (("").equals(getDateFrom()) || (getDateFrom() == null)) {
                    cellb31.setCellValue("--");
                } else {
                    cellb31.setCellValue(getDateFrom());
                }

                XSSFCell cellc31 = row.createCell((short) 4);
                cellc31.setCellValue("DateTo");
                cellc31.setCellStyle(cs2);
                XSSFCell celld31 = row.createCell((short) 5);
                if (("").equals(getDateTo()) || (getDateTo() == null)) {
                    celld31.setCellValue("--");
                } else {
                    celld31.setCellValue(getDateTo());
                }

                row = sheet.createRow((int) 5);

                XSSFCell cella41 = row.createCell((short) 1);
                cella41.setCellValue("SenderId");
                cella41.setCellStyle(cs2);
                XSSFCell cellb41 = row.createCell((short) 2);
                if (("-1").equals(getSenderId()) || (getSenderId() == null)) {
                    cellb41.setCellValue("--");
                } else {
                    cellb41.setCellValue(getSenderId());
                }

                XSSFCell cellc41 = row.createCell((short) 4);
                cellc41.setCellValue("SenderName");
                cellc41.setCellStyle(cs2);
                XSSFCell celld41 = row.createCell((short) 5);
                if (("-1").equals(getSenderName()) || (getSenderName() == null)) {
                    celld41.setCellValue("--");
                } else {
                    celld41.setCellValue(getSenderName());
                }

                row = sheet.createRow((int) 6);
                XSSFCell cella51 = row.createCell((short) 1);
                cella51.setCellValue("RecieverId");
                cella51.setCellStyle(cs2);
                XSSFCell cellb51 = row.createCell((short) 2);
                if (("-1").equals(getReceiverId()) || (getReceiverId() == null)) {
                    cellb51.setCellValue("--");
                } else {
                    cellb51.setCellValue(getReceiverId());
                }

                XSSFCell cellc51 = row.createCell((short) 4);
                cellc51.setCellValue("RecieverName");
                cellc51.setCellStyle(cs2);
                XSSFCell celld51 = row.createCell((short) 5);
                if (("-1").equals(getReceiverName()) || (getReceiverName() == null)) {
                    celld51.setCellValue("--");
                } else {
                    celld51.setCellValue(getReceiverName());
                }

                row = sheet.createRow((int) 7);
                XSSFCell cella61 = row.createCell((short) 1);
                cella61.setCellValue("Correlation");
                cella61.setCellStyle(cs2);
                XSSFCell cellb61 = row.createCell((short) 2);
                if (("-1").equals(getCorrattribute()) || (getCorrattribute() == null)) {
                    cellb61.setCellValue("--");
                } else {
                    cellb61.setCellValue(getCorrattribute());
                }

                XSSFCell cellc61 = row.createCell((short) 4);
                cellc61.setCellValue("Value");
                cellc61.setCellStyle(cs2);
                XSSFCell celld61 = row.createCell((short) 5);
                if (("").equals(getCorrvalue()) || (getCorrvalue() == null)) {
                    celld61.setCellValue("--");
                } else {
                    celld61.setCellValue(getCorrvalue());
                }

                row = sheet.createRow((int) 8);

                XSSFCell cella71 = row.createCell((short) 1);
                cella71.setCellValue("Correlation");
                cella71.setCellStyle(cs2);
                XSSFCell cellb71 = row.createCell((short) 2);
                if (("-1").equals(getCorrattribute1()) || (getCorrattribute1() == null)) {
                    cellb71.setCellValue("--");
                } else {
                    cellb71.setCellValue(getCorrattribute1());
                }

                XSSFCell cellc71 = row.createCell((short) 4);
                cellc71.setCellValue("Value");
                cellc71.setCellStyle(cs2);
                XSSFCell celld71 = row.createCell((short) 5);
                if (("").equals(getCorrvalue1()) || (getCorrvalue1() == null)) {
                    celld71.setCellValue("--");
                } else {
                    celld71.setCellValue(getCorrvalue1());
                }
                row = sheet.createRow((int) 9);

                XSSFCell cella91 = row.createCell((short) 1);
                cella91.setCellValue("DocType");
                cella91.setCellStyle(cs2);
                XSSFCell cellb91 = row.createCell((short) 2);
                if (("-1").equals(getDocType()) || (getDocType() == null)) {
                    cellb91.setCellValue("--");
                } else {
                    cellb91.setCellValue(getDocType());
                }

                XSSFCell cellc91 = row.createCell((short) 4);
                cellc91.setCellValue("Status");
                cellc91.setCellStyle(cs2);
                XSSFCell celld91 = row.createCell((short) 5);
                if (("-1").equals(getStatus()) || (getStatus() == null)) {
                    celld91.setCellValue("--");
                } else {
                    celld91.setCellValue(getStatus());
                }
                System.out.println("The Starting");
                row = sheet.createRow((int) 11);
                XSSFCell cella1 = row.createCell((short) 0);
                cella1.setCellValue("SNO");
                cella1.setCellStyle(cs2);
                XSSFCell cellb1 = row.createCell((short) 1);
                cellb1.setCellValue("FileFormat");
                cellb1.setCellStyle(cs2);
                XSSFCell cellc1 = row.createCell((short) 2);
                cellc1.setCellValue("InstanceId");
                cellc1.setCellStyle(cs2);
                XSSFCell celld1 = row.createCell((short) 3);
                celld1.setCellValue("OrderId");
                celld1.setCellStyle(cs2);
                XSSFCell celle1 = row.createCell((short) 4);
                celle1.setCellValue("Shipment");
                celle1.setCellStyle(cs2);
                XSSFCell cellf1 = row.createCell((short) 5);
                cellf1.setCellValue("TransType");
                cellf1.setCellStyle(cs2);
                XSSFCell cellg1 = row.createCell((short) 6);
                cellg1.setCellValue("Direction");
                cellg1.setCellStyle(cs2);
                XSSFCell cellh1 = row.createCell((short) 7);
                cellh1.setCellValue("Status");
                cellh1.setCellStyle(cs2);
                XSSFCell celli1 = row.createCell((short) 8);
                celli1.setCellValue("ResponseStatus");
                celli1.setCellStyle(cs2);
                XSSFCell cellj1 = row.createCell((short) 9);
                cellj1.setCellValue("partner");
                cellj1.setCellStyle(cs2);

                int count1 = 0;
                if (ltResponseList1.size() > 0) {
                    Map docRepMap = null;
                    for (int i = 0; i < ltResponseList1.size(); i++) {
                        docRepMap = (Map) ltResponseList1.get(i);
                        row = sheet.createRow((int) i + 12);
                        cell = row.createCell((short) 0);
                        cell.setCellValue((String) docRepMap.get("SNO"));

                        cell1 = row.createCell((short) 1);
                        cell1.setCellValue((String) docRepMap.get("file_Type"));

                        cell2 = row.createCell((short) 2);
                        cell2.setCellValue((String) docRepMap.get("file_id"));

                        cell3 = row.createCell((short) 3);
                        cell3.setCellValue((String) docRepMap.get("ref_id"));

                        cell4 = row.createCell((short) 4);
                        cell4.setCellValue((String) docRepMap.get("shipment_id"));

                        cell5 = row.createCell((short) 5);
                        cell5.setCellValue((String) docRepMap.get("transaction_Type"));

                        cell6 = row.createCell((short) 6);
                        cell6.setCellValue((String) docRepMap.get("direction"));

                        cell7 = row.createCell((short) 7);
                        cell7.setCellValue(((String) docRepMap.get("status")));

                        String responseStatus = (String) docRepMap.get("responseStauts");
                        cell8 = row.createCell((short) 8);

                        if (responseStatus != null) {
                            if (responseStatus.equalsIgnoreCase("A")) {
                                cell8.setCellValue("ACCEPT");
                            } else if (responseStatus.equalsIgnoreCase("D")) {
                                cell8.setCellValue("DECLINE");
                            }
                        } else {
                            cell8.setCellValue("-");
                        }
                        cell9 = row.createCell((short) 9);
                        cell9.setCellValue(((String) docRepMap.get("receiver_Name")));
                        count1 = count1 + 1;
                    }
                    row = sheet.createRow((int) count1 + 14);

                    XSSFCell cellaz1 = row.createCell((short) 8);
                    cellaz1.setCellValue("Total : ");
                    cellaz1.setCellStyle(cs2);
                    XSSFCell cellbz1 = row.createCell((short) 9);
                    cellbz1.setCellValue(count1);
                }
                sheet.autoSizeColumn((short) 0);
                sheet.autoSizeColumn((short) 1);
                sheet.autoSizeColumn((short) 2);
                sheet.autoSizeColumn((short) 3);
                sheet.autoSizeColumn((short) 4);
                sheet.autoSizeColumn((short) 5);
                sheet.autoSizeColumn((short) 6);
                sheet.autoSizeColumn((short) 7);
                sheet.autoSizeColumn((short) 8);
                sheet.autoSizeColumn((short) 9);

                xssfworkbook.write(fileOut);
                fileOut.flush();
                fileOut.close();
            }

        } catch (FileNotFoundException fne) {
            fne.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
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
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
        return filePath;
    }

    /*
     * Method to download excel for Lt Shipment
     * Date : 07/02/2013
     */
    public String gridDownloadforLtAsnRepo() throws ServiceLocatorException, SQLException {
        StringBuffer ltShipmentSearchQuery = new StringBuffer();
        String filePath = "";
        StringBuffer sb = null;
        Connection connection = null;
        PreparedStatement preStmt = null;
        ResultSet resultSet = null;
        HashMap map = null;
        String tmp_Recieved_From = "";
        String tmp_Recieved_ToTime = "";
        dateTo = getDateTo();
        dateFrom = getDateFrom();
        senderId = getSenderId();
        senderName = getSenderName();
        receiverId = getReceiverId();
        receiverName = getReceiverName();
        if (getDocType() != null && !getDocType().equals("-1")) {
            docType = getDocType();
        }
        corrattribute = getCorrattribute();
        corrvalue = getCorrvalue();
        corrattribute1 = getCorrattribute1();
        corrvalue1 = getCorrvalue1();
        status = getStatus();
        ackStatus = getAckStatus();
        List finalList = new ArrayList();
        Date date = new Date();
        try {
            File file = new File(Properties.getProperty("mscvp.ltShipmentCreationPath"));
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + File.separator + "ltShipment.xlsx");
            ltShipmentSearchQuery.append("SELECT (FILES.FILE_ID) as FILE_ID,TRANSPORT_SHIPMENT.STOP_SEQ_NUM,"
                    + "FILES.ISA_NUMBER as ISA_NUMBER,FILES.FILE_TYPE as FILE_TYPE,FILES.CARRIER_STATUS  as CARRIER_STATUS,FILES.ID AS IDFILE,"
                    + "FILES.FILE_ORIGIN as FILE_ORIGIN,FILES.TRANSACTION_TYPE as TRANSACTION_TYPE,FILES.TMW_SENDERID as TMW_SENDERID,FILES.TMW_RECEIVERID as TMW_RECEIVERID,"
                    + "FILES.DIRECTION as DIRECTION,FILES.DATE_TIME_RECEIVED as DATE_TIME_RECEIVED,FILES.SENDER_ID,FILES.RECEIVER_ID,"
                    + "FILES.STATUS as STATUS,FILES.ACK_STATUS as ACK_STATUS,TP2.NAME as RECEIVER_NAME,"
                    + "FILES.SEC_KEY_VAL,FILES.REPROCESSSTATUS,FILES.STOP_NUM,TP2.NAME as RECEIVER_NAME,TRANSPORT_SHIPMENT.SHIPMENT_ID,TRANSPORT_SHIPMENT.ID as ID,TRANSPORT_SHIPMENT.PO_NUMBER "
                    + "FROM TRANSPORT_SHIPMENT "
                    + "LEFT OUTER JOIN FILES ON (TRANSPORT_SHIPMENT.FILE_ID =FILES.FILE_ID)"
                    + "LEFT OUTER JOIN TP TP1 ON (TP1.ID=FILES.TMW_SENDERID) "
                    + "LEFT OUTER JOIN TP TP2 ON (TP2.ID=FILES.TMW_RECEIVERID)"
                    + "LEFT OUTER JOIN TP TP3 ON (TP3.ID=FILES.SENDER_ID)"
                    + "LEFT OUTER JOIN TP TP4 ON (TP4.ID=FILES.RECEIVER_ID)");

            ltShipmentSearchQuery.append(" WHERE 1=1 AND FILES.FLOWFLAG = 'L' ");
            // FOr PO
            if (corrattribute != null && corrattribute.equalsIgnoreCase("BOL Number")) // || corrattribute.equalsIgnoreCase("Invoice Number")  || corrattribute.equalsIgnoreCase("Shipment Number") || corrattribute.equalsIgnoreCase("Cheque Number") )
            {
                if (corrvalue != null && !"".equals(corrvalue.trim())) {
                    ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("TRANSPORT_SHIPMENT.BOL_NUMBER",
                            corrvalue.trim().toUpperCase()));
                }
            }

            if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("BOL Number")) // || corrattribute.equalsIgnoreCase("Invoice Number")  || corrattribute.equalsIgnoreCase("Shipment Number") || corrattribute.equalsIgnoreCase("Cheque Number") )
            {
                if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                    ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("TRANSPORT_SHIPMENT.BOL_NUMBER",
                            corrvalue1.trim().toUpperCase()));
                }
            }

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

            if (corrattribute != null && corrattribute.equalsIgnoreCase("Order Number")) {
                if (corrvalue != null && !"".equals(corrvalue.trim())) {
                    ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.SEC_KEY_VAL",
                            corrvalue.trim().toUpperCase()));
                }
            }

            if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("Shipment Number")) {
                if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                    ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("TRANSPORT_SHIPMENT.SHIPMENT_ID",
                            corrvalue1.trim().toUpperCase()));
                }
            }

            if (corrattribute != null && corrattribute.equalsIgnoreCase("PO Number")) {
                if (corrvalue != null && !"".equals(corrvalue.trim())) {
                    ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("TRANSPORT_SHIPMENT.PO_NUMBER",
                            corrvalue.trim().toUpperCase()));
                }
            }

            if (corrattribute != null && corrattribute.equalsIgnoreCase("PO Number")) {
                if (corrvalue != null && !"".equals(corrvalue.trim())) {
                    ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("TRANSPORT_SHIPMENT.PO_NUMBER",
                            corrvalue.trim().toUpperCase()));
                }
            }

            if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("PO Number")) {
                if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                    ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("TRANSPORT_SHIPMENT.PO_NUMBER",
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

            if (docType != null && !"-1".equals(docType.trim())) {
                ltShipmentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.TRANSACTION_TYPE",
                        docType.trim()));
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
                ltShipmentSearchQuery.append("AND (TP4.NAME like '%" + receiverName + "%' OR TP2.NAME like '%" + receiverName + "%')");

            }

            if (dateTo != null && !"".equals(dateTo)) {
                tmp_Recieved_From = DateUtility.getInstance().DateViewToDBCompare(dateTo);
                ltShipmentSearchQuery.append(" AND FILES.DATE_TIME_RECEIVED <= '" + tmp_Recieved_From
                        + "'");
            }
            if (dateFrom != null && !"".equals(dateFrom)) {
                tmp_Recieved_From = DateUtility.getInstance().DateViewToDBCompare(dateFrom);
                ltShipmentSearchQuery.append(" AND FILES.DATE_TIME_RECEIVED >= '" + tmp_Recieved_From
                        + "'");
            }
            ltShipmentSearchQuery.append(" order by DATE_TIME_RECEIVED DESC ");
            System.out.println("Logistics Shipment query-->" + ltShipmentSearchQuery.toString());
            String searchQuery = ltShipmentSearchQuery.toString();
            int j = 1;
            connection = ConnectionProvider.getInstance().getConnection();
            Statement statement = null;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(searchQuery);
            String InvoiceDate = "";
            while (resultSet.next()) {
                String InstanceId = resultSet.getString("FILE_ID");
                String ShipId = resultSet.getString("SHIPMENT_ID");
                String Partner = resultSet.getString("RECEIVER_NAME");
                String cs = resultSet.getString("CARRIER_STATUS");
                String carrierStatus = "";
                if (cs != null) {
                    if (cs.equalsIgnoreCase("AA")) {
                        carrierStatus = (cs + "_pick_up appointment");
                    } else if (cs.equalsIgnoreCase("AB")) {
                        carrierStatus = (cs + "_Delivery appointment");
                    } else if (cs.equalsIgnoreCase("X3")) {
                        carrierStatus = (cs + "_Arrived at Pick_up Location");
                    } else if (cs.equalsIgnoreCase("AF")) {
                        carrierStatus = (cs + "_Departed from pick_up Location");
                    } else if (cs.equalsIgnoreCase("X1")) {
                        carrierStatus = (cs + "_Arrived at Delivery Location");
                    } else if (cs.equalsIgnoreCase("D1")) {
                        carrierStatus = (cs + "_Completed Unloading Delivery Location");
                    } else if (cs.equalsIgnoreCase("CD")) {
                        carrierStatus = (cs + "_Carrier Departed Delivery Location");
                    }
                }
                Timestamp dt = resultSet.getTimestamp("DATE_TIME_RECEIVED");
                String DateTime = dt.toString().substring(0, dt.toString().lastIndexOf(":"));
                String Direction = resultSet.getString("DIRECTION");
                String Status = resultSet.getString("STATUS");
                String StopNumber = resultSet.getString("STOP_NUM");
                String OrderNumber = resultSet.getString("SEC_KEY_VAL");
                map = new HashMap();
                map.put("SNO", String.valueOf(j));
                map.put("InstanceId", InstanceId);
                map.put("ShipId", ShipId);
                map.put("Partner", Partner);
                map.put("carrierStatus", carrierStatus);
                map.put("DateTime", DateTime);
                map.put("Direction", Direction);
                map.put("Status", Status);
                map.put("StopNumber", StopNumber);
                map.put("OrderNumber", OrderNumber);
                finalList.add(map);
                j++;
            }
            if (finalList.size() > 0) {
                filePath = file.getAbsolutePath() + Properties.getProperty("os.compatability") + "ltShipment.xlsx";
                XSSFWorkbook xssfworkbook = new XSSFWorkbook();
                XSSFSheet sheet = xssfworkbook.createSheet("Logistics Shipment");
                XSSFCellStyle cs = xssfworkbook.createCellStyle();
                cs.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                cs.setAlignment(XSSFCellStyle.ALIGN_LEFT);
                cs.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                cs.setBorderTop((short) 1); // single line border
                cs.setBorderBottom((short) 1); // single line border

                XSSFCellStyle headercs = xssfworkbook.createCellStyle();
                headercs.setFillForegroundColor(IndexedColors.AQUA.index);
                headercs.setAlignment(XSSFCellStyle.ALIGN_CENTER);
                headercs.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                headercs.setBorderTop((short) 1); // single line border
                headercs.setBorderBottom((short) 1); // single line border

                XSSFCellStyle cs1 = xssfworkbook.createCellStyle();
                cs1.setFillForegroundColor(IndexedColors.GREEN.index);
                cs1.setAlignment(XSSFCellStyle.ALIGN_LEFT);
                cs1.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                cs1.setBorderTop((short) 1); // single line border
                cs1.setBorderBottom((short) 1); // single line border

                XSSFCellStyle cs2 = xssfworkbook.createCellStyle();
                cs2.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.index);
                cs2.setAlignment(XSSFCellStyle.ALIGN_LEFT);
                cs2.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                cs2.setBorderTop((short) 1); // single line border
                cs2.setBorderBottom((short) 1); // single line border

                XSSFCellStyle cs3 = xssfworkbook.createCellStyle();
                cs3.setFillForegroundColor(IndexedColors.LIME.index);
                cs3.setAlignment(XSSFCellStyle.ALIGN_LEFT);
                cs3.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                cs3.setBorderTop((short) 1); // single line border
                cs3.setBorderBottom((short) 1); // single line border

                XSSFCellStyle cellStyle = xssfworkbook.createCellStyle();
                XSSFCellStyle cellStyle1 = xssfworkbook.createCellStyle();
                XSSFCellStyle cellStyle2 = xssfworkbook.createCellStyle();
                XSSFCellStyle cellStyle3 = xssfworkbook.createCellStyle();
                XSSFCellStyle cellStyleHead = xssfworkbook.createCellStyle();
                XSSFFont font1 = xssfworkbook.createFont();
                XSSFFont font2 = xssfworkbook.createFont();
                XSSFFont font3 = xssfworkbook.createFont();
                XSSFFont fontHead = xssfworkbook.createFont();

                XSSFDataFormat df = xssfworkbook.createDataFormat();
                XSSFRow row = sheet.createRow((int) 0);
                XSSFCell cell = row.createCell((short) 0);
                XSSFCell cell1 = row.createCell((short) 1);
                XSSFCell cell2 = row.createCell((short) 2);
                XSSFCell cell3 = row.createCell((short) 3);
                XSSFCell cell4 = row.createCell((short) 4);
                XSSFCell cell5 = row.createCell((short) 5);
                XSSFCell cell6 = row.createCell((short) 6);
                XSSFCell cell7 = row.createCell((short) 7);
                XSSFCell cell8 = row.createCell((short) 8);
                XSSFCell cell9 = row.createCell((short) 9);

                XSSFRow row1 = sheet.createRow((int) 1);
                Cell cellX = row1.createCell((short) 1);
                cellX.setCellValue("logisticsShipment:-Created Date : " + (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate());
                font1.setColor(IndexedColors.GREEN.index);
                cellStyle1.setFont(font1);
                cellX.setCellStyle(cellStyle1);
                sheet.addMergedRegion(CellRangeAddress.valueOf("B2:F2"));
//gnr
                XSSFRow row2 = sheet.createRow((int) 2);
                Cell cel2 = row2.createCell((short) 2);
                cel2.setCellValue("");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(XSSFCellStyle.ALIGN_CENTER);
                cel2.setCellStyle(cellStyleHead);
                sheet.addMergedRegion(CellRangeAddress.valueOf("C2:F2"));

                XSSFRow row3 = sheet.createRow((int) 3);
                Cell cel3 = row3.createCell((short) 0);
                cel3.setCellValue("Search Criteria :");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cel2.setCellStyle(cellStyleHead);

                row = sheet.createRow((int) 4);
                //Cell cell = row1.createCell((short) 1);
                XSSFCell cella31 = row.createCell((short) 1);
                cella31.setCellValue("DateFrom");
                cella31.setCellStyle(cs2);
                XSSFCell cellb31 = row.createCell((short) 2);
                if (("").equals(getDateFrom()) || (getDateFrom() == null)) {
                    cellb31.setCellValue("--");
                } else {
                    cellb31.setCellValue(getDateFrom());
                }

                XSSFCell cellc31 = row.createCell((short) 4);
                cellc31.setCellValue("DateTo");
                cellc31.setCellStyle(cs2);
                XSSFCell celld31 = row.createCell((short) 5);
                if (("").equals(getDateTo()) || (getDateTo() == null)) {
                    celld31.setCellValue("--");
                } else {
                    celld31.setCellValue(getDateTo());
                }

                row = sheet.createRow((int) 5);

                XSSFCell cella41 = row.createCell((short) 1);
                cella41.setCellValue("SenderId");
                cella41.setCellStyle(cs2);
                XSSFCell cellb41 = row.createCell((short) 2);
                if (("-1").equals(getSenderId()) || (getSenderId() == null)) {
                    cellb41.setCellValue("--");
                } else {
                    cellb41.setCellValue(getSenderId());
                }

                XSSFCell cellc41 = row.createCell((short) 4);
                cellc41.setCellValue("SenderName");
                cellc41.setCellStyle(cs2);
                XSSFCell celld41 = row.createCell((short) 5);
                if (("-1").equals(getSenderName()) || (getSenderName() == null)) {
                    celld41.setCellValue("--");
                } else {
                    celld41.setCellValue(getSenderName());
                }

                row = sheet.createRow((int) 6);

                XSSFCell cella51 = row.createCell((short) 1);
                cella51.setCellValue("RecieverId");
                cella51.setCellStyle(cs2);
                XSSFCell cellb51 = row.createCell((short) 2);
                if (("-1").equals(getReceiverId()) || (getReceiverId() == null)) {
                    cellb51.setCellValue("--");
                } else {
                    cellb51.setCellValue(getReceiverId());
                }

                XSSFCell cellc51 = row.createCell((short) 4);
                cellc51.setCellValue("RecieverName");
                cellc51.setCellStyle(cs2);
                XSSFCell celld51 = row.createCell((short) 5);
                if (("-1").equals(getReceiverName()) || (getReceiverName() == null)) {
                    celld51.setCellValue("--");
                } else {
                    celld51.setCellValue(getReceiverName());
                }

                row = sheet.createRow((int) 7);

                XSSFCell cella61 = row.createCell((short) 1);
                cella61.setCellValue("Correlation");
                cella61.setCellStyle(cs2);
                XSSFCell cellb61 = row.createCell((short) 2);
                if (("-1").equals(getCorrattribute()) || (getCorrattribute() == null)) {
                    cellb61.setCellValue("--");
                } else {
                    cellb61.setCellValue(getCorrattribute());
                }

                XSSFCell cellc61 = row.createCell((short) 4);
                cellc61.setCellValue("Value");
                cellc61.setCellStyle(cs2);
                XSSFCell celld61 = row.createCell((short) 5);
                if (("").equals(getCorrvalue()) || (getCorrvalue() == null)) {
                    celld61.setCellValue("--");
                } else {
                    celld61.setCellValue(getCorrvalue());
                }

                row = sheet.createRow((int) 8);

                XSSFCell cella71 = row.createCell((short) 1);
                cella71.setCellValue("Correlation");
                cella71.setCellStyle(cs2);
                XSSFCell cellb71 = row.createCell((short) 2);
                if (("-1").equals(getCorrattribute1()) || (getCorrattribute1() == null)) {
                    cellb71.setCellValue("--");
                } else {
                    cellb71.setCellValue(getCorrattribute1());
                }

                XSSFCell cellc71 = row.createCell((short) 4);
                cellc71.setCellValue("Value");
                cellc71.setCellStyle(cs2);
                XSSFCell celld71 = row.createCell((short) 5);
                if (("").equals(getCorrvalue1()) || (getCorrvalue1() == null)) {
                    celld71.setCellValue("--");
                } else {
                    celld71.setCellValue(getCorrvalue1());
                }
                row = sheet.createRow((int) 9);

                XSSFCell cella81 = row.createCell((short) 1);
                cella81.setCellValue("DocType");
                cella81.setCellStyle(cs2);
                XSSFCell cellb81 = row.createCell((short) 2);
                if (("-1").equals(getDocType()) || (getDocType() == null)) {
                    cellb81.setCellValue("--");
                } else {
                    cellb81.setCellValue(getDocType());
                }

                XSSFCell cellc81 = row.createCell((short) 4);
                cellc81.setCellValue("Status");
                cellc81.setCellStyle(cs2);
                XSSFCell celld81 = row.createCell((short) 5);
                if (("-1").equals(getStatus()) || (getStatus() == null)) {
                    celld81.setCellValue("--");
                } else {
                    celld81.setCellValue(getStatus());
                }

                row = sheet.createRow((int) 11);
                XSSFCell cella1 = row.createCell((short) 0);
                cella1.setCellValue("SNO");
                cella1.setCellStyle(cs2);
                XSSFCell cellb1 = row.createCell((short) 1);
                cellb1.setCellValue("InstanceId");
                cellb1.setCellStyle(cs2);
                XSSFCell cellc1 = row.createCell((short) 2);
                cellc1.setCellValue("ShipId");
                cellc1.setCellStyle(cs2);
                XSSFCell celld1 = row.createCell((short) 3);
                celld1.setCellValue("Partner");
                celld1.setCellStyle(cs2);
                XSSFCell celle1 = row.createCell((short) 4);
                celle1.setCellValue("carrierStatus");
                celle1.setCellStyle(cs2);
                XSSFCell cellf1 = row.createCell((short) 5);
                cellf1.setCellValue("DateTime");
                cellf1.setCellStyle(cs2);
                XSSFCell cellg1 = row.createCell((short) 6);
                cellg1.setCellValue("Direction");
                cellg1.setCellStyle(cs2);

                XSSFCell celli1 = row.createCell((short) 7);
                celli1.setCellValue("Status");
                celli1.setCellStyle(cs2);

                XSSFCell cellh1 = row.createCell((short) 8);
                cellh1.setCellValue("StopNumber");
                cellh1.setCellStyle(cs2);

                XSSFCell cellk1 = row.createCell((short) 9);
                cellk1.setCellValue("OrderNumber");
                cellk1.setCellStyle(cs2);

                int count1 = 0;
                if (finalList.size() > 0) {
                    Map docRepMap = null;
                    for (int i = 0; i < finalList.size(); i++) {
                        docRepMap = (Map) finalList.get(i);
                        row = sheet.createRow((int) i + 13);

                        cell = row.createCell((short) 0);
                        cell.setCellValue((String) docRepMap.get("SNO"));

                        cell1 = row.createCell((short) 1);
                        cell1.setCellValue((String) docRepMap.get("InstanceId"));

                        cell2 = row.createCell((short) 2);
                        cell2.setCellValue((String) docRepMap.get("ShipId"));

                        cell3 = row.createCell((short) 3);
                        cell3.setCellValue((String) docRepMap.get("Partner"));

                        cell4 = row.createCell((short) 4);
                        cell4.setCellValue((String) docRepMap.get("carrierStatus"));

                        cell5 = row.createCell((short) 5);
                        cell5.setCellValue((String) docRepMap.get("DateTime"));

                        cell6 = row.createCell((short) 6);
                        cell6.setCellValue((String) docRepMap.get("Direction"));

                        cell7 = row.createCell((short) 7);

                        cell7.setCellValue(((String) docRepMap.get("Status")));

                        cell8 = row.createCell((short) 8);

                        cell8.setCellValue(((String) docRepMap.get("StopNumber")));

                        cell9 = row.createCell((short) 9);

                        cell9.setCellValue(((String) docRepMap.get("OrderNumber")));

                        count1 = count1 + 1;
                    }
                    row = sheet.createRow((int) count1 + 14);

                    XSSFCell cellaz1 = row.createCell((short) 8);
                    cellaz1.setCellValue("Total : ");
                    cellaz1.setCellStyle(cs2);
                    XSSFCell cellbz1 = row.createCell((short) 9);
                    cellbz1.setCellValue(count1);
                }
                sheet.autoSizeColumn((short) 0);
                sheet.autoSizeColumn((short) 1);
                sheet.autoSizeColumn((short) 2);
                sheet.autoSizeColumn((short) 3);
                sheet.autoSizeColumn((short) 4);
                sheet.autoSizeColumn((short) 5);
                sheet.autoSizeColumn((short) 6);
                sheet.autoSizeColumn((short) 7);
                sheet.autoSizeColumn((short) 8);
                sheet.autoSizeColumn((short) 9);

                xssfworkbook.write(fileOut);
                fileOut.flush();
                fileOut.close();
            }

        } catch (FileNotFoundException fne) {
            fne.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preStmt != null) {
                    preStmt.close();
                    preStmt = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
        return filePath;
    }

    /*
     * Method to download excel for Lt Invoice
     * Date : 07/02/2013
     */
    public String gridDownloadforLtdocrepo() throws ServiceLocatorException, SQLException {
        StringBuffer documentSearchQuery = new StringBuffer();
        String filePath = "";
        Connection connection = null;
        PreparedStatement preStmt = null;
        ResultSet resultSet = null;
        HashMap map = null;
        String tmp_Recieved_From = "";
        String tmp_Recieved_ToTime = "";
        dateTo = getDateTo();
        dateFrom = getDateFrom();
        senderId = getSenderId();
        receiverName = getReceiverName();
        receiverId = getReceiverId();
        senderName = getSenderName();
        if (getDocType() != null && !getDocType().equals("-1")) {
            docType = getDocType();
        }
        corrattribute = getCorrattribute();
        corrvalue = getCorrvalue();
        corrattribute1 = getCorrattribute1();
        corrvalue1 = getCorrvalue1();
        corrattribute2 = getCorrattribute2();
        corrvalue2 = getCorrvalue2();
        status = getStatus();
        ackStatus = getAckStatus();
        List finalList = new ArrayList();
        Date date = new Date();

        try {
            File file = new File(Properties.getProperty("mscvp.logisticsDocCreationPath"));
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + File.separator + "logisticsDoc.xlsx");
            documentSearchQuery.append("SELECT (FILES.FILE_ID) as FILE_ID,FILES.ID as ID,"
                    + "FILES.ISA_NUMBER as ISA_NUMBER,FILES.FILE_TYPE as FILE_TYPE,FILES.CARRIER_STATUS,FILES.PRI_KEY_VAL  as PRI_KEY_VAL,FILES.SENDER_ID,FILES.RECEIVER_ID,"
                    + "FILES.FILE_ORIGIN as FILE_ORIGIN,FILES.TRANSACTION_TYPE as TRANSACTION_TYPE,FILES.TMW_SENDERID as TMW_SENDERID,FILES.TMW_RECEIVERID as TMW_RECEIVERID,"
                    + "FILES.DIRECTION as DIRECTION,FILES.DATE_TIME_RECEIVED as DATE_TIME_RECEIVED,"
                    + "FILES.STATUS as STATUS,FILES.ACK_STATUS as ACK_STATUS,TP2.NAME as RECEIVER_NAME,TP1.NAME as SENDER_NAME,trsrsp.REF_ID,"
                    + "FILES.SEC_KEY_VAL,FILES.REPROCESSSTATUS,FILES.FILENAME,trsrsp.RESPONSE_STATUS,FILES.TRANSACTION_PURPOSE FROM FILES "
                    + "LEFT OUTER JOIN TRANSPORT_LT_RESPONSE trsrsp on (trsrsp.FILE_ID=FILES.FILE_ID)"
                    + " LEFT OUTER JOIN TP TP1 ON (TP1.ID=FILES.TMW_SENDERID) "
                    + " LEFT OUTER JOIN TP TP2 ON (TP2.ID=FILES.TMW_RECEIVERID)"
                    + " LEFT OUTER JOIN TP TP3 ON (TP3.ID=FILES.SENDER_ID)"
                    + " LEFT OUTER JOIN TP TP4 ON (TP4.ID=FILES.RECEIVER_ID)");

            documentSearchQuery.append(" WHERE 1=1 AND FLOWFLAG LIKE '%L%'");

            if (dateFrom != null && !"".equals(dateFrom)) {
                tmp_Recieved_From = DateUtility.getInstance().DateViewToDBCompare(dateFrom);
                documentSearchQuery.append(" AND FILES.DATE_TIME_RECEIVED >= '" + tmp_Recieved_From + "'");
            }
            if (dateTo != null && !"".equals(dateTo)) {
                tmp_Recieved_ToTime = DateUtility.getInstance().DateViewToDBCompare(dateTo);
                documentSearchQuery.append(" AND FILES.DATE_TIME_RECEIVED <= '" + tmp_Recieved_ToTime + "'");
            }
            if (corrattribute != null && corrattribute.equalsIgnoreCase("PO Number")) {
                if (corrvalue != null && !"".equals(corrvalue.trim())) {
                    documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.SEC_KEY_VAL", corrvalue.trim().toUpperCase()));
                }
            }
            if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("PO Number")) // || corrattribute.equalsIgnoreCase("Invoice Number")  || corrattribute.equalsIgnoreCase("Shipment Number") || corrattribute.equalsIgnoreCase("Cheque Number") )
            {
                if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                    documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.SEC_KEY_VAL",
                            corrvalue1.trim().toUpperCase()));
                }
            }

            if (corrattribute2 != null && corrattribute2.equalsIgnoreCase("PO Number")) // || corrattribute.equalsIgnoreCase("Invoice Number")  || corrattribute.equalsIgnoreCase("Shipment Number") || corrattribute.equalsIgnoreCase("Cheque Number") )
            {
                if (corrvalue2 != null && !"".equals(corrvalue2.trim())) {
                    documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.SEC_KEY_VAL",
                            corrvalue2.trim().toUpperCase()));
                }
            }
            if (corrattribute != null && corrattribute.equalsIgnoreCase("Order Number")) // || corrattribute.equalsIgnoreCase("Invoice Number")  || corrattribute.equalsIgnoreCase("Shipment Number") || corrattribute.equalsIgnoreCase("Cheque Number") )
            {
                if (corrvalue != null && !"".equals(corrvalue.trim())) {
                    documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.SEC_KEY_VAL",
                            corrvalue.trim().toUpperCase()));
                }
            }

            if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("Order Number")) // || corrattribute.equalsIgnoreCase("Invoice Number")  || corrattribute.equalsIgnoreCase("Shipment Number") || corrattribute.equalsIgnoreCase("Cheque Number") )
            {
                if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                    documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.SEC_KEY_VAL",
                            corrvalue1.trim().toUpperCase()));
                }
            }

            if (corrattribute2 != null && corrattribute2.equalsIgnoreCase("Order Number")) // || corrattribute.equalsIgnoreCase("Invoice Number")  || corrattribute.equalsIgnoreCase("Shipment Number") || corrattribute.equalsIgnoreCase("Cheque Number") )
            {
                if (corrvalue2 != null && !"".equals(corrvalue2.trim())) {
                    documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.SEC_KEY_VAL",
                            corrvalue2.trim().toUpperCase()));
                }
            }

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
            // bol
            if (corrattribute != null && corrattribute.equalsIgnoreCase("BOL Number")) {
                if (corrvalue != null && !"".equals(corrvalue.trim())) {
                    documentSearchQuery.append(WildCardSql.getWildCardSql1("ten.BOL_NUMBER",
                            corrvalue.trim().toUpperCase()));
                }
            }
            if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("BOL Number")) {
                if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                    documentSearchQuery.append(WildCardSql.getWildCardSql1("ten.BOL_NUMBER",
                            corrvalue1.trim().toUpperCase()));
                }
            }
            if (corrattribute2 != null && corrattribute2.equalsIgnoreCase("BOL Number")) {
                if (corrvalue2 != null && !"".equals(corrvalue2.trim())) {
                    documentSearchQuery.append(WildCardSql.getWildCardSql1("ten.BOL_NUMBER",
                            corrvalue2.trim().toUpperCase()));
                }
            }

            // CO
            if (corrattribute != null && corrattribute.equalsIgnoreCase("CO Number")) {
                if (corrvalue != null && !"".equals(corrvalue.trim())) {
                    documentSearchQuery.append(WildCardSql.getWildCardSql1("ten.CO_NUMBER",
                            corrvalue.trim().toUpperCase()));
                }
            }
            if (corrattribute1 != null && corrattribute1.equalsIgnoreCase("CO Number")) {
                if (corrvalue1 != null && !"".equals(corrvalue1.trim())) {
                    documentSearchQuery.append(WildCardSql.getWildCardSql1("ten.CO_NUMBER",
                            corrvalue1.trim().toUpperCase()));
                }
            }
            if (corrattribute2 != null && corrattribute2.equalsIgnoreCase("CO Number")) {
                if (corrvalue2 != null && !"".equals(corrvalue2.trim())) {
                    documentSearchQuery.append(WildCardSql.getWildCardSql1("ten.CO_NUMBER",
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
            if (docType != null && !"-1".equals(docType.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.TRANSACTION_TYPE",
                        docType.trim()));
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

            if (receiverId != null && !"".equals(receiverId.trim())) {
                documentSearchQuery.append(" AND (FILES.RECEIVER_ID like '%" + receiverId + "%' OR FILES.TMW_RECEIVERID like '%" + receiverId + "%')");
            }

            if (senderId != null && !"".equals(senderId.trim())) {
                documentSearchQuery.append(" AND (FILES.SENDER_ID like '%" + senderId + "%' OR FILES.TMW_SENDERID like '%" + senderId + "%')");
            }

            if (senderName != null && !"".equals(senderName.trim())) {

                documentSearchQuery.append(" AND (TP3.NAME like '%" + senderName + "%' OR TP1.NAME like '%" + senderName + "%')");

            }

            if (receiverName != null && !"".equals(receiverName.trim())) {
                documentSearchQuery.append(" AND (TP4.NAME like '%" + receiverName + "%' OR TP2.NAME like '%" + receiverName + "%')");

            }

            documentSearchQuery.append(" order by DATE_TIME_RECEIVED DESC");
            System.out.println("DOC GDA queryquery-->" + documentSearchQuery.toString());
            String searchQuery = documentSearchQuery.toString();

            int j = 1;
            connection = ConnectionProvider.getInstance().getConnection();
            Statement statement = null;

            statement = connection.createStatement();

            resultSet = statement.executeQuery(searchQuery);
            String InvoiceDate = "";
            String Partner = null;
            String TransactionStatus = "";

            while (resultSet.next()) {
                int Id = resultSet.getInt("ID");
                String InstanceId = resultSet.getString("FILE_ID");
                String Direction = resultSet.getString("DIRECTION");
                if (Direction != null && Direction.equalsIgnoreCase("")) {
                    if (Direction.equalsIgnoreCase("INBOUND")) {
                        Partner = resultSet.getString("SENDER_NAME");
                    }
                    if (Direction.equalsIgnoreCase("OUTBOUND")) {
                        Partner = resultSet.getString("RECEIVER_NAME");
                    }
                }
                String FileType = resultSet.getString("FILE_TYPE");
                String ShipId = resultSet.getString("PRI_KEY_VAL");
                Timestamp dt = resultSet.getTimestamp("DATE_TIME_RECEIVED");
                String DateTime = dt.toString().substring(0, dt.toString().lastIndexOf(":"));
                String TransactionType = resultSet.getString("TRANSACTION_TYPE");
                if (TransactionType != null) {
                    if (TransactionType.equalsIgnoreCase("204")) {
                        TransactionStatus = resultSet.getString("TRANSACTION_PURPOSE");
                    } else if (TransactionType.equalsIgnoreCase("214")) {
                        TransactionStatus = resultSet.getString("CARRIER_STATUS");
                    } else if (TransactionType.equalsIgnoreCase("990")) {
                        TransactionStatus = resultSet.getString("RESPONSE_STATUS");
                    }
                }
                String Status = resultSet.getString("STATUS");
                if (TransactionType != null) {

                    if (TransactionType.equalsIgnoreCase("204")) {

                        if (TransactionStatus == null) {
                            TransactionStatus = "--";
                        } else if (TransactionStatus.equalsIgnoreCase("N")) {

                            TransactionStatus = "New Order";
                        } else if (TransactionStatus.equalsIgnoreCase("U")) {
                            TransactionStatus = "Update";
                        } else if (TransactionStatus.equalsIgnoreCase("C")) {
                            TransactionStatus = "Cancel";
                        } else {
                            TransactionStatus = "-";
                        }

                    } else if (TransactionType.equalsIgnoreCase("214")) {
                        if (TransactionStatus == null) {
                            TransactionStatus = "--";
                        } else if (TransactionStatus.equalsIgnoreCase("AA")) {

                            TransactionStatus = TransactionStatus + "_pick_up appointment";
                        } else if (TransactionStatus.equalsIgnoreCase("AB")) {
                            TransactionStatus = TransactionStatus + "_Delivery appointment";
                        } else if (TransactionStatus.equalsIgnoreCase("X3")) {
                            TransactionStatus = TransactionStatus + "_Arrived at Pick_up Location";
                        } else if (TransactionStatus.equalsIgnoreCase("AF")) {
                            TransactionStatus = TransactionStatus + "_Departed from pick_up Location";
                        } else if (TransactionStatus.equalsIgnoreCase("X1")) {
                            TransactionStatus = TransactionStatus + "_Arrived at Delivery Location";
                        } else if (TransactionStatus.equalsIgnoreCase("D1")) {
                            TransactionStatus = TransactionStatus + "_Completed Unloading Delivery Location";
                        } else if (TransactionStatus.equalsIgnoreCase("CD")) {
                            TransactionStatus = TransactionStatus + "_Carrier Departed Delivery Location";
                        } else {
                            TransactionStatus = "-";
                        }
                    } else if (TransactionType.equalsIgnoreCase("990")) {
                        if (TransactionStatus == null) {
                            TransactionStatus = "--";
                        } else if (TransactionStatus.equalsIgnoreCase("A")) {

                            TransactionStatus = "Accept";
                        } else if (TransactionStatus.equalsIgnoreCase("D")) {
                            TransactionStatus = "Decline";
                        } else if (TransactionStatus.equalsIgnoreCase("C")) {
                            TransactionStatus = "Cancel";
                        } else {
                            TransactionStatus = "---";
                        }

                    } else if (TransactionType.equalsIgnoreCase("210")) {
                        if (TransactionStatus == null) {
                            TransactionStatus = "----";
                        } else {
                            TransactionStatus = TransactionStatus;
                        }
                    } else {
                        TransactionStatus = "----";
                    }

                }

                String OrderNo = resultSet.getString("SEC_KEY_VAL");

                map = new HashMap();
                map.put("SNO", String.valueOf(j));
                map.put("InstanceId", InstanceId);
                map.put("Direction", Direction);
                map.put("Partner", Partner);
                map.put("FileType", FileType);
                map.put("ShipId", ShipId);
                map.put("DateTime", DateTime);
                map.put("TransactionType", TransactionType);
                map.put("Status", Status);
                map.put("TransactionStatus", TransactionStatus);
                map.put("OrderNo", OrderNo);
                finalList.add(map);
                j++;
            }
            if (finalList.size() > 0) {
                filePath = file.getAbsolutePath() + Properties.getProperty("os.compatability") + "logisticsDoc.xlsx";

                XSSFWorkbook xssfworkbook = new XSSFWorkbook();
                XSSFSheet sheet = xssfworkbook.createSheet("Logistics DocRepository");

                XSSFCellStyle cs = xssfworkbook.createCellStyle();
                cs.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                cs.setAlignment(XSSFCellStyle.ALIGN_LEFT);
                cs.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                cs.setBorderTop((short) 1); // single line border
                cs.setBorderBottom((short) 1); // single line border

                XSSFCellStyle headercs = xssfworkbook.createCellStyle();
                headercs.setFillForegroundColor(IndexedColors.AQUA.index);
                headercs.setAlignment(XSSFCellStyle.ALIGN_CENTER);
                headercs.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                headercs.setBorderTop((short) 1); // single line border
                headercs.setBorderBottom((short) 1); // single line border

                XSSFCellStyle cs1 = xssfworkbook.createCellStyle();
                cs1.setFillForegroundColor(IndexedColors.GREEN.index);
                cs1.setAlignment(XSSFCellStyle.ALIGN_LEFT);
                cs1.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                cs1.setBorderTop((short) 1); // single line border
                cs1.setBorderBottom((short) 1); // single line border

                XSSFCellStyle cs2 = xssfworkbook.createCellStyle();
                cs2.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.index);
                cs2.setAlignment(XSSFCellStyle.ALIGN_LEFT);
                cs2.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                cs2.setBorderTop((short) 1); // single line border
                cs2.setBorderBottom((short) 1); // single line border

                XSSFCellStyle cs3 = xssfworkbook.createCellStyle();
                cs3.setFillForegroundColor(IndexedColors.LIME.index);
                cs3.setAlignment(XSSFCellStyle.ALIGN_LEFT);
                cs3.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                cs3.setBorderTop((short) 1); // single line border
                cs3.setBorderBottom((short) 1); // single line border

                XSSFCellStyle cellStyle = xssfworkbook.createCellStyle();
                XSSFCellStyle cellStyle1 = xssfworkbook.createCellStyle();
                XSSFCellStyle cellStyle2 = xssfworkbook.createCellStyle();
                XSSFCellStyle cellStyle3 = xssfworkbook.createCellStyle();
                XSSFCellStyle cellStyleHead = xssfworkbook.createCellStyle();
                XSSFFont font1 = xssfworkbook.createFont();
                XSSFFont font2 = xssfworkbook.createFont();
                XSSFFont font3 = xssfworkbook.createFont();
                XSSFFont fontHead = xssfworkbook.createFont();

                XSSFDataFormat df = xssfworkbook.createDataFormat();
                XSSFRow row = sheet.createRow((int) 0);
                XSSFCell cell = row.createCell((short) 0);
                XSSFCell cell1 = row.createCell((short) 1);
                XSSFCell cell2 = row.createCell((short) 2);
                XSSFCell cell3 = row.createCell((short) 3);
                XSSFCell cell4 = row.createCell((short) 4);
                XSSFCell cell5 = row.createCell((short) 5);
                XSSFCell cell6 = row.createCell((short) 6);
                XSSFCell cell7 = row.createCell((short) 7);
                XSSFCell cell8 = row.createCell((short) 8);
                XSSFCell cell9 = row.createCell((short) 9);
                XSSFCell cell10 = row.createCell((short) 10);

                XSSFRow row1 = sheet.createRow((int) 1);
                Cell cellX = row1.createCell((short) 1);
                cellX.setCellValue("logisticsDoc:-Created Date : " + (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate());
                font1.setColor(IndexedColors.GREEN.index);
                cellStyle1.setFont(font1);
                cellX.setCellStyle(cellStyle1);
                sheet.addMergedRegion(CellRangeAddress.valueOf("B2:F2"));

//gnr
                XSSFRow row2 = sheet.createRow((int) 2);
                Cell cel2 = row2.createCell((short) 2);
                cel2.setCellValue("");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cel2.setCellStyle(cellStyleHead);
                sheet.addMergedRegion(CellRangeAddress.valueOf("C2:F2"));

                XSSFRow row3 = sheet.createRow((int) 3);
                Cell cel3 = row3.createCell((short) 0);
                cel3.setCellValue("Search Criteria :");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cel2.setCellStyle(cellStyleHead);

                row = sheet.createRow((int) 4);
                //Cell cell = row1.createCell((short) 1);
                XSSFCell cella31 = row.createCell((short) 1);
                cella31.setCellValue("DateFrom");
                cella31.setCellStyle(cs2);
                XSSFCell cellb31 = row.createCell((short) 2);
                if (("").equals(getDateFrom()) || (getDateFrom() == null)) {
                    cellb31.setCellValue("--");
                } else {
                    cellb31.setCellValue(getDateFrom());
                }

                XSSFCell cellc31 = row.createCell((short) 4);
                cellc31.setCellValue("DateTo");
                cellc31.setCellStyle(cs2);
                XSSFCell celld31 = row.createCell((short) 5);
                if (("").equals(getDateTo()) || (getDateTo() == null)) {
                    celld31.setCellValue("--");
                } else {
                    celld31.setCellValue(getDateTo());
                }

                row = sheet.createRow((int) 5);

                XSSFCell cella41 = row.createCell((short) 1);
                cella41.setCellValue("SenderId");
                cella41.setCellStyle(cs2);
                XSSFCell cellb41 = row.createCell((short) 2);
                if (("-1").equals(getSenderId()) || (getSenderId() == null)) {
                    cellb41.setCellValue("--");
                } else {
                    cellb41.setCellValue(getSenderId());
                }

                XSSFCell cellc41 = row.createCell((short) 4);
                cellc41.setCellValue("SenderName");
                cellc41.setCellStyle(cs2);
                XSSFCell celld41 = row.createCell((short) 5);
                if (("-1").equals(getSenderName()) || (getSenderName() == null)) {
                    celld41.setCellValue("--");
                } else {
                    celld41.setCellValue(getSenderName());
                }

                row = sheet.createRow((int) 6);

                XSSFCell cella51 = row.createCell((short) 1);
                cella51.setCellValue("RecieverId");
                cella51.setCellStyle(cs2);
                XSSFCell cellb51 = row.createCell((short) 2);
                if (("-1").equals(getReceiverId()) || (getReceiverId() == null)) {
                    cellb51.setCellValue("--");
                } else {
                    cellb51.setCellValue(getReceiverId());
                }

                XSSFCell cellc51 = row.createCell((short) 4);
                cellc51.setCellValue("RecieverName");
                cellc51.setCellStyle(cs2);
                XSSFCell celld51 = row.createCell((short) 5);
                if (("-1").equals(getReceiverName()) || (getReceiverName() == null)) {
                    celld51.setCellValue("--");
                } else {
                    celld51.setCellValue(getReceiverName());
                }

                row = sheet.createRow((int) 7);

                XSSFCell cella61 = row.createCell((short) 1);
                cella61.setCellValue("Correlation");
                cella61.setCellStyle(cs2);
                XSSFCell cellb61 = row.createCell((short) 2);
                if (("-1").equals(getCorrattribute()) || (getCorrattribute() == null)) {
                    cellb61.setCellValue("--");
                } else {
                    cellb61.setCellValue(getCorrattribute());
                }

                XSSFCell cellc61 = row.createCell((short) 4);
                cellc61.setCellValue("Value");
                cellc61.setCellStyle(cs2);
                XSSFCell celld61 = row.createCell((short) 5);
                if (("").equals(getCorrvalue()) || (getCorrvalue() == null)) {
                    celld61.setCellValue("--");
                } else {
                    celld61.setCellValue(getCorrvalue());
                }

                row = sheet.createRow((int) 8);

                XSSFCell cella71 = row.createCell((short) 1);
                cella71.setCellValue("Correlation");
                cella71.setCellStyle(cs2);
                XSSFCell cellb71 = row.createCell((short) 2);
                if (("-1").equals(getCorrattribute1()) || (getCorrattribute1() == null)) {
                    cellb71.setCellValue("--");
                } else {
                    cellb71.setCellValue(getCorrattribute1());
                }
                XSSFCell cellc71 = row.createCell((short) 4);
                cellc71.setCellValue("Value");
                cellc71.setCellStyle(cs2);
                XSSFCell celld71 = row.createCell((short) 5);
                if (("").equals(getCorrvalue1()) || (getCorrvalue1() == null)) {
                    celld71.setCellValue("--");
                } else {
                    celld71.setCellValue(getCorrvalue1());
                }

                row = sheet.createRow((int) 9);

                XSSFCell cella81 = row.createCell((short) 1);
                cella81.setCellValue("Correlation");
                cella81.setCellStyle(cs2);
                XSSFCell cellb81 = row.createCell((short) 2);
                if (("-1").equals(getCorrattribute2()) || (getCorrattribute2() == null)) {
                    cellb81.setCellValue("--");
                } else {
                    cellb81.setCellValue(getCorrattribute2());
                }

                XSSFCell cellc81 = row.createCell((short) 4);
                cellc81.setCellValue("Value");
                cellc81.setCellStyle(cs2);
                XSSFCell celld81 = row.createCell((short) 5);
                if (("").equals(getCorrvalue2()) || (getCorrvalue2() == null)) {
                    celld81.setCellValue("--");
                } else {
                    celld81.setCellValue(getCorrvalue2());
                }
                row = sheet.createRow((int) 10);

                XSSFCell cella91 = row.createCell((short) 1);
                cella91.setCellValue("DocType");
                cella91.setCellStyle(cs2);
                XSSFCell cellb91 = row.createCell((short) 2);
                if (("-1").equals(getDocType()) || (getDocType() == null)) {
                    cellb91.setCellValue("--");
                } else {
                    cellb91.setCellValue(getDocType());
                }

                XSSFCell cellc91 = row.createCell((short) 4);
                cellc91.setCellValue("Status");
                cellc91.setCellStyle(cs2);
                XSSFCell celld91 = row.createCell((short) 5);
                if (("-1").equals(getStatus()) || (getStatus() == null)) {
                    celld91.setCellValue("--");
                } else {
                    celld91.setCellValue(getStatus());
                }

                row = sheet.createRow((int) 12);
                XSSFCell cella1 = row.createCell((short) 0);
                cella1.setCellValue("SNO");
                cella1.setCellStyle(cs2);
                XSSFCell cellb1 = row.createCell((short) 1);
                cellb1.setCellValue("InstanceId");
                cellb1.setCellStyle(cs2);
                XSSFCell cellc1 = row.createCell((short) 2);
                cellc1.setCellValue("Direction");
                cellc1.setCellStyle(cs2);
                XSSFCell celld1 = row.createCell((short) 3);
                celld1.setCellValue("Partner");
                celld1.setCellStyle(cs2);
                XSSFCell celle1 = row.createCell((short) 4);
                celle1.setCellValue("FileType");
                celle1.setCellStyle(cs2);
                XSSFCell cellf1 = row.createCell((short) 5);
                cellf1.setCellValue("ShipId");
                cellf1.setCellStyle(cs2);
                XSSFCell cellg1 = row.createCell((short) 6);
                cellg1.setCellValue("DateTime");
                cellg1.setCellStyle(cs2);
                XSSFCell cellh1 = row.createCell((short) 7);
                cellh1.setCellValue("TransactionType");
                cellh1.setCellStyle(cs2);
                XSSFCell celli1 = row.createCell((short) 8);
                celli1.setCellValue("Status");
                celli1.setCellStyle(cs2);

                XSSFCell cellj1 = row.createCell((short) 9);
                cellj1.setCellValue("TransactionStatus");
                cellj1.setCellStyle(cs2);

                XSSFCell cellk1 = row.createCell((short) 10);
                cellk1.setCellValue("OrderNo");
                cellk1.setCellStyle(cs2);

                int count1 = 0;
                if (finalList.size() > 0) {
                    Map docRepMap = null;
                    for (int i = 0; i < finalList.size(); i++) {
                        docRepMap = (Map) finalList.get(i);
                        row = sheet.createRow((int) i + 13);

                        cell = row.createCell((short) 0);
                        cell.setCellValue((String) docRepMap.get("SNO"));

                        cell1 = row.createCell((short) 1);
                        cell1.setCellValue((String) docRepMap.get("InstanceId"));

                        cell2 = row.createCell((short) 2);
                        cell2.setCellValue((String) docRepMap.get("Direction"));

                        cell3 = row.createCell((short) 3);
                        cell3.setCellValue((String) docRepMap.get("Partner"));

                        cell4 = row.createCell((short) 4);
                        cell4.setCellValue((String) docRepMap.get("FileType"));

                        cell5 = row.createCell((short) 5);
                        cell5.setCellValue((String) docRepMap.get("ShipId"));

                        cell6 = row.createCell((short) 6);
                        cell6.setCellValue((String) docRepMap.get("DateTime"));

                        cell7 = row.createCell((short) 7);

                        cell7.setCellValue(((String) docRepMap.get("TransactionType")));

                        cell8 = row.createCell((short) 8);

                        cell8.setCellValue(((String) docRepMap.get("Status")));

                        cell9 = row.createCell((short) 9);

                        cell9.setCellValue(((String) docRepMap.get("TransactionStatus")));

                        cell10 = row.createCell((short) 10);

                        cell10.setCellValue(((String) docRepMap.get("OrderNo")));

                        count1 = count1 + 1;
                    }
                    row = sheet.createRow((int) count1 + 14);

                    XSSFCell cellaz1 = row.createCell((short) 8);
                    cellaz1.setCellValue("Total : ");
                    cellaz1.setCellStyle(cs2);
                    XSSFCell cellbz1 = row.createCell((short) 9);
                    cellbz1.setCellValue(count1);
                }
                sheet.autoSizeColumn((short) 0);
                sheet.autoSizeColumn((short) 1);
                sheet.autoSizeColumn((short) 2);
                sheet.autoSizeColumn((short) 3);
                sheet.autoSizeColumn((short) 4);
                sheet.autoSizeColumn((short) 5);
                sheet.autoSizeColumn((short) 6);
                sheet.autoSizeColumn((short) 7);
                sheet.autoSizeColumn((short) 8);
                sheet.autoSizeColumn((short) 9);

                xssfworkbook.write(fileOut);
                fileOut.flush();
                fileOut.close();
            }

        } catch (FileNotFoundException fne) {
            fne.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preStmt != null) {
                    preStmt.close();
                    preStmt = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
        System.out.println("path=" + filePath);
        return filePath;
    }

    /*
     * Method for DocumentVisibility Excel download
     * Author : Santosh Kola
     * Date : 01/05/2014
     */
    public String docVisibilityExcelDownload() {
        String filePath = "";
        try {
            java.util.List list = (java.util.List) httpServletRequest.getSession(false).getAttribute("searchResult");
            File file = new File(Properties.getProperty("mscvp.docCreationPath"));
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + Properties.getProperty("os.compatability") + "DocVisibility.xls");
            filePath = file.getAbsolutePath() + Properties.getProperty("os.compatability") + "DocVisibility.xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("DocVisibility");
            HSSFRow row1;
            DocumentVisibilityBean documentVisibilityBean = null;

            if (list.size() != 0) {
                HSSFCellStyle cellStyle = workbook.createCellStyle();
                HSSFCellStyle cellStyle1 = workbook.createCellStyle();
                HSSFCellStyle cellStyle2 = workbook.createCellStyle();
                HSSFCellStyle cellStyle3 = workbook.createCellStyle();
                HSSFCellStyle cellStyleHead = workbook.createCellStyle();
                HSSFFont font1 = workbook.createFont();
                HSSFFont font2 = workbook.createFont();
                HSSFFont font3 = workbook.createFont();
                HSSFFont font4 = workbook.createFont();
                HSSFFont fontHead = workbook.createFont();
                fontHead.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                font4.setColor(HSSFColor.WHITE.index);
                cellStyle.setFillForegroundColor(HSSFColor.BLACK.index);
                cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellStyle.setFont(font4);
                Date date = new Date();
                row1 = worksheet.createRow((short) 0);
                HSSFCell cellpo0 = row1.createCell((short) 0);
                HSSFCell cellpo1 = row1.createCell((short) 1);
                HSSFCell cellpo2 = row1.createCell((short) 2);
                HSSFCell cellpo3 = row1.createCell((short) 3);
                HSSFCell cellpo4 = row1.createCell((short) 4);
                HSSFCell cellpo5 = row1.createCell((short) 5);
                HSSFCell cellpo6 = row1.createCell((short) 6);
                HSSFCell cellpo7 = row1.createCell((short) 7);
                row1 = worksheet.createRow((short) 1);
                Cell cell = row1.createCell((short) 1);
                cell.setCellValue("Doc Repositry:-Created Date : " + (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate());
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cell.setCellStyle(cellStyleHead);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("B2:F2"));

                row1 = worksheet.createRow((short) 3);
                HSSFCell cella1 = row1.createCell((short) 0);
                cella1.setCellValue("Instance_Id");
                cella1.setCellStyle(cellStyle);
                HSSFCell cellb1 = row1.createCell((short) 1);
                cellb1.setCellValue("FileType");
                cellb1.setCellStyle(cellStyle);
                HSSFCell cellc1 = row1.createCell((short) 2);
                cellc1.setCellValue("Date Created");
                cellc1.setCellStyle(cellStyle);
                HSSFCell celld1 = row1.createCell((short) 3);
                celld1.setCellValue("TransType");
                celld1.setCellStyle(cellStyle);
                HSSFCell celle1 = row1.createCell((short) 4);
                celle1.setCellValue("Sender Id");
                celle1.setCellStyle(cellStyle);
                HSSFCell cellf1 = row1.createCell((short) 5);
                cellf1.setCellValue("Receiver Id");
                cellf1.setCellStyle(cellStyle);
                HSSFCell cellg1 = row1.createCell((short) 6);
                cellg1.setCellValue("IC #");
                cellg1.setCellStyle(cellStyle);
                HSSFCell cellh1 = row1.createCell((short) 7);
                cellh1.setCellValue("FC #");
                cellh1.setCellStyle(cellStyle);
                HSSFCell celli1 = row1.createCell((short) 8);
                celli1.setCellValue("MC #");
                celli1.setCellStyle(cellStyle);

                for (int i = 0; i < list.size(); i++) {
                    documentVisibilityBean = (DocumentVisibilityBean) list.get(i);
                    row1 = worksheet.createRow((short) i + 4);

                    HSSFCell cellA1 = row1.createCell((short) 0);
                    cellA1.setCellValue(documentVisibilityBean.getInstanceId());

                    HSSFCell cellB1 = row1.createCell((short) 1);
                    cellB1.setCellValue(documentVisibilityBean.getFile_type());

                    HSSFCell cellC1 = row1.createCell((short) 2);
                    cellC1.setCellValue(documentVisibilityBean.getDate_time_rec().toString().substring(0, documentVisibilityBean.getDate_time_rec().toString().lastIndexOf(":")));

                    HSSFCell cellD1 = row1.createCell((short) 3);
                    cellD1.setCellValue(documentVisibilityBean.getTransaction_type());

                    HSSFCell cellE1 = row1.createCell((short) 4);
                    cellE1.setCellValue(documentVisibilityBean.getSenderId());

                    HSSFCell cellF1 = row1.createCell((short) 5);
                    cellF1.setCellValue(documentVisibilityBean.getReceiverId());

                    HSSFCell cellG1 = row1.createCell((short) 6);
                    cellG1.setCellValue(documentVisibilityBean.getInterchange_ControlNo());

                    HSSFCell cellH1 = row1.createCell((short) 7);
                    cellH1.setCellValue(documentVisibilityBean.getFunctional_ControlNo());

                    HSSFCell cellI1 = row1.createCell((short) 8);
                    cellI1.setCellValue(documentVisibilityBean.getMessage_ControlNo());
                }

            }
            worksheet.autoSizeColumn((short) 0);
            worksheet.autoSizeColumn((short) 1);
            worksheet.autoSizeColumn((short) 2);
            worksheet.autoSizeColumn((short) 3);
            worksheet.autoSizeColumn((short) 4);
            worksheet.autoSizeColumn((short) 5);
            worksheet.autoSizeColumn((short) 6);
            worksheet.autoSizeColumn((short) 7);
            worksheet.autoSizeColumn((short) 8);

            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    /*
     **Dashboard pie charts excel generation
     */
    public String dashVisibilityExcelDownload(String inbound, String outbound) {
        String filePath = "";
        try {
            StringTokenizer inboundst = new StringTokenizer(inbound, "^");
            StringTokenizer outboundst = new StringTokenizer(outbound, "^");
            List inbounddata = new ArrayList();
            String inboundvalue = null;
            String outboundvalue = null;
            while (inboundst.hasMoreTokens()) {
                inboundvalue = inboundst.nextToken();
                StringTokenizer inboundst1 = new StringTokenizer(inboundvalue, "|");
                while (inboundst1.hasMoreTokens()) {
                    inbounddata.add(inboundst1.nextToken());
                }
            }

            List outbounddata = new ArrayList();
            while (outboundst.hasMoreTokens()) {
                outboundvalue = outboundst.nextToken();
                StringTokenizer outboundst1 = new StringTokenizer(outboundvalue, "|");
                while (outboundst1.hasMoreTokens()) {
                    outbounddata.add(outboundst1.nextToken());
                }
            }
            File file = new File(Properties.getProperty("mscvp.docCreationPath"));
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + File.separator + "DashboardReport.xlsx");
            filePath = file.getAbsolutePath() + File.separator + "DashboardReport.xlsx";
            XSSFWorkbook workbook = new XSSFWorkbook();
            org.apache.poi.ss.usermodel.Sheet my_sheet = workbook.createSheet("DashboardPiechartReport");
            XSSFSheet worksheet = workbook.createSheet("DashboardReportData");
            XSSFRow row1;
            DefaultPieDataset inbound_chart_data = new DefaultPieDataset();
            DefaultPieDataset outbound_chart_data = new DefaultPieDataset();
            String Inboundvalue = null;
            Number Inboundvalue2 = 0;
            String Inboundvalue1 = null;
            String Outboundvalue = null;
            String Outboundvalue1 = null;
            Number Outboundvalue2 = 0;
            Date date = new Date();
            XSSFCellStyle cellStyle = workbook.createCellStyle();
            XSSFCellStyle cellStyleHead = workbook.createCellStyle();
            XSSFFont font4 = workbook.createFont();
            XSSFFont fontHead = workbook.createFont();
            fontHead.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font4.setColor(IndexedColors.WHITE.index);
            cellStyle.setFillForegroundColor(IndexedColors.BLACK.index);
            cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
            cellStyle.setFont(font4);
            if (inbounddata.size() != 0 || outbounddata.size() != 0) {
                row1 = worksheet.createRow((int) 0);
                row1 = worksheet.createRow((int) 1);
                Cell cell = row1.createCell((short) 1);
                cell.setCellValue("Dashboard Reports:-Created Date : " + (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate());
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(XSSFCellStyle.ALIGN_CENTER);
                cell.setCellStyle(cellStyleHead);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("B2:F2"));
                int j = 0;
                row1 = worksheet.createRow((int) j + 3);
                if (inbounddata.size() != 0) {
                    XSSFCell cell1 = row1.createCell((short) 0);
                    cell1.setCellValue("PartnerName");
                    XSSFCell cell2 = row1.createCell((short) 1);
                    cell2.setCellValue("INBOUND");
                }
                if (outbounddata.size() != 0) {
                    if (inbounddata.size() != 0) {
                        XSSFCell cell3 = row1.createCell((short) 2);
                        cell3.setCellValue("PartnerName");
                        XSSFCell cell4 = row1.createCell((short) 3);
                        cell4.setCellValue("OUTBOUND");
                    } else {
                        XSSFCell cell3 = row1.createCell((short) 0);
                        cell3.setCellValue("PartnerName");
                        XSSFCell cell4 = row1.createCell((short) 1);
                        cell4.setCellValue("OUTBOUND");
                    }
                }
                for (int i = 0; i < inbounddata.size();) {
                    Inboundvalue = String.valueOf(inbounddata.get(i));
                    Inboundvalue1 = String.valueOf(inbounddata.get(i + 1));
                    row1 = worksheet.createRow((int) j + 4);
                    Inboundvalue2 = (Number) Integer.parseInt(Inboundvalue1);
                    XSSFCell cellA1 = row1.createCell((short) 0);
                    cellA1.setCellValue(Inboundvalue);
                    XSSFCell cellA2 = row1.createCell((short) 1);
                    cellA2.setCellValue(Inboundvalue1);
                    i = i + 2;
                    j = j + 1;
                    inbound_chart_data.setValue(Inboundvalue, Inboundvalue2);
                }

                j = 0;
                for (int i = 0; i < outbounddata.size();) {
                    Outboundvalue = String.valueOf(outbounddata.get(i));
                    Outboundvalue1 = String.valueOf(outbounddata.get(i + 1));
                    Outboundvalue2 = (Number) Integer.parseInt(Outboundvalue1);
                    if (inbounddata.size() != 0 && i < inbounddata.size()) {
                        row1 = worksheet.getRow((short) j + 4);
                        XSSFCell cellA1 = row1.createCell((short) 2);
                        cellA1.setCellValue(Outboundvalue);
                        XSSFCell cellA2 = row1.createCell((short) 3);
                        cellA2.setCellValue(Outboundvalue1);
                    } else {
                        row1 = worksheet.createRow((int) j + 4);
                        if (inbounddata.size() != 0) {
                            XSSFCell cellA1 = row1.createCell((short) 2);
                            cellA1.setCellValue(Outboundvalue);
                            XSSFCell cellA2 = row1.createCell((short) 3);
                            cellA2.setCellValue(Outboundvalue1);
                        } else {
                            XSSFCell cellA1 = row1.createCell((short) 0);
                            cellA1.setCellValue(Outboundvalue);
                            XSSFCell cellA2 = row1.createCell((short) 1);
                            cellA2.setCellValue(Outboundvalue1);
                        }
                    }
                    i = i + 2;
                    j = j + 1;
                    outbound_chart_data.setValue(Outboundvalue, Outboundvalue2);
                }

                worksheet.autoSizeColumn((short) 0);
                worksheet.autoSizeColumn((short) 1);
                worksheet.autoSizeColumn((short) 2);
                worksheet.autoSizeColumn((short) 3);
                worksheet.autoSizeColumn((short) 4);
                worksheet.autoSizeColumn((short) 5);
                worksheet.autoSizeColumn((short) 6);
                worksheet.autoSizeColumn((short) 7);
                worksheet.autoSizeColumn((short) 8);

            }
            /* Specify the height and width of the Pie Chart */
            int width = 480; /* Width of the chart */

            int height = 350; /* Height of the chart */

            float quality = 1; /* Quality factor */

            Drawing drawing = my_sheet.createDrawingPatriarch();
            Row row = my_sheet.createRow((int) 1);
            Cell cell = row.createCell((short) 5);
            cell.setCellValue("Dashboard Pie Charts Reports:-Created Date : " + (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate());
            cellStyleHead.setFont(fontHead);
            cellStyleHead.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cell.setCellStyle(cellStyleHead);
            my_sheet.addMergedRegion(CellRangeAddress.valueOf("F2:N2"));
            if (inbounddata.size() != 0) {
                //System.out.println("inbound_chart_data"+inbound_chart_data);
                JFreeChart inboundPieChart = ChartFactory.createPieChart("Partner Inbound Transcations", inbound_chart_data, true, true, false);
                /* We don't want to create an intermediate file. So, we create a byte array output stream 
                 and byte array input stream
                 And we pass the chart data directly to input stream through this */
                /* Write chart as JPG to Output Stream */
                ByteArrayOutputStream inbound_out = new ByteArrayOutputStream();
                ChartUtilities.writeChartAsJPEG(inbound_out, quality, inboundPieChart, width, height);
                int inbound_picture_id = workbook.addPicture(inbound_out.toByteArray(), workbook.PICTURE_TYPE_JPEG);
                inbound_out.close();

                /* Create the drawing container */

                /* Create an anchor point */
                ClientAnchor inbound_anchor = new XSSFClientAnchor();
                /* Define top left corner, and we can resize picture suitable from there */
                inbound_anchor.setCol1(2);
                inbound_anchor.setRow1(5);
                /* Invoke createPicture and pass the anchor point and ID */
                Picture inbound_picture = drawing.createPicture(inbound_anchor, inbound_picture_id);

                /* Call resize method, which resizes the image */
                inbound_picture.resize();
            }
            if (outbounddata.size() != 0) {
                ByteArrayOutputStream outbound_out = new ByteArrayOutputStream();
                JFreeChart outboundPieChart = ChartFactory.createPieChart("Partner Outbound Transcations", outbound_chart_data, true, true, false);
                ChartUtilities.writeChartAsJPEG(outbound_out, quality, outboundPieChart, width, height);
                int outbound_picture_id = workbook.addPicture(outbound_out.toByteArray(), workbook.PICTURE_TYPE_JPEG);
                outbound_out.close();
                ClientAnchor outbound_anchor = new XSSFClientAnchor();
                /* Define top left corner, and we can resize picture suitable from there */
                if (inbounddata.size() != 0) {
                    outbound_anchor.setCol1(10);
                    outbound_anchor.setRow1(5);
                } else {
                    outbound_anchor.setCol1(2);
                    outbound_anchor.setRow1(5);
                }
                /* Invoke createPicture and pass the anchor point and ID */
                Picture outbound_picture = drawing.createPicture(outbound_anchor, outbound_picture_id);
                outbound_picture.resize();
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

    /*
     **Dashboard pie charts pdf generation
     */
    public String dashVisibilityPdfDownload(String inbound, String outbound) {
        String filePath = "";
        try {
            StringTokenizer inboundst = new StringTokenizer(inbound, "^");
            StringTokenizer outboundst = new StringTokenizer(outbound, "^");
            List inbounddata = new ArrayList();
            String inboundvalue = null;
            String outboundvalue = null;
            while (inboundst.hasMoreTokens()) {
                inboundvalue = inboundst.nextToken();
                StringTokenizer inboundst1 = new StringTokenizer(inboundvalue, "|");
                while (inboundst1.hasMoreTokens()) {
                    inbounddata.add(inboundst1.nextToken());
                }
            }
            List outbounddata = new ArrayList();
            while (outboundst.hasMoreTokens()) {
                outboundvalue = outboundst.nextToken();
                StringTokenizer outboundst1 = new StringTokenizer(outboundvalue, "|");
                while (outboundst1.hasMoreTokens()) {
                    outbounddata.add(outboundst1.nextToken());
                }
            }
            File file = new File(Properties.getProperty("mscvp.docCreationPath"));
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + File.separator + "DashboardReport.pdf");
            filePath = file.getAbsolutePath() + File.separator + "DashboardReport.pdf";
            DefaultPieDataset inbound_chart_data = new DefaultPieDataset();
            DefaultPieDataset outbound_chart_data = new DefaultPieDataset();
            String Inboundvalue = null;
            Number Inboundvalue2 = 0;
            String Inboundvalue1 = null;
            String Outboundvalue = null;
            String Outboundvalue1 = null;
            Number Outboundvalue2 = 0;
            PdfPTable inboundtable = new PdfPTable(2);
            PdfPTable outboundtable = new PdfPTable(2);
            inboundtable.setWidthPercentage(50.00f);
            outboundtable.setWidthPercentage(50.00f);
            if (inbounddata.size() != 0 || outbounddata.size() != 0) {
                inboundtable.addCell("PartnerName");
                inboundtable.addCell("INBOUND");
                for (int i = 0; i < inbounddata.size();) {
                    Inboundvalue = String.valueOf(inbounddata.get(i));
                    Inboundvalue1 = String.valueOf(inbounddata.get(i + 1));
                    Inboundvalue2 = (Number) Integer.parseInt(Inboundvalue1);
                    i = i + 2;
                    inbound_chart_data.setValue(Inboundvalue, Inboundvalue2);
                    inboundtable.addCell(Inboundvalue);
                    inboundtable.addCell(Inboundvalue1);
                    inboundtable.completeRow();
                }

                outboundtable.addCell("PartnerName");
                outboundtable.addCell("OUTBOUND");
                for (int i = 0; i < outbounddata.size();) {
                    Outboundvalue = String.valueOf(outbounddata.get(i));
                    Outboundvalue1 = String.valueOf(outbounddata.get(i + 1));
                    Outboundvalue2 = (Number) Integer.parseInt(Outboundvalue1);
                    i = i + 2;
                    outbound_chart_data.setValue(Outboundvalue, Outboundvalue2);
                    outboundtable.addCell(Outboundvalue);
                    outboundtable.addCell(Outboundvalue1);
                    outboundtable.completeRow();
                }

            }
            /* Specify the height and width of the Pie Chart */
            int width = 380; /* Width of the chart */

            int height = 400; /* Height of the chart */

            Date date = new Date();
            PdfWriter writer = null;
            Document document = new Document(PageSize.A3);
            writer = PdfWriter.getInstance(document, fileOut);
            document.open();
            Paragraph par = new Paragraph("Dashboard Pie Reports:-Created Date : " + (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate(), FontFactory.getFont("Arial", 26f));
            par.setAlignment(Paragraph.ALIGN_CENTER);

            document.add(par);

//my_pie_chart_data.setValue("a",50);
            if (inbounddata.size() != 0) {
                //System.out.println("inbound_chart_data"+inbound_chart_data);

                JFreeChart inboundPieChart = ChartFactory.createPieChart("Partner Inbound Transcations", inbound_chart_data, true, true, false);
                PdfContentByte contentByte = writer.getDirectContent();
                PdfTemplate template = contentByte.createTemplate(width, height);
                Graphics2D graphics2d = template.createGraphics(width, height,
                        new DefaultFontMapper());
                Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width,
                        height);

                inboundPieChart.draw(graphics2d, rectangle2d);

                graphics2d.dispose();
                if (outbounddata.size() != 0) {
                    contentByte.addTemplate(template, 30, 630);
                } else {
                    contentByte.addTemplate(template, 210, 630);
                }
                //document.right();

                //document.setMargins(0, 0, 0,0);
            }
            if (outbounddata.size() != 0) {
                // document.newPage();      
                JFreeChart outboundPieChart = ChartFactory.createPieChart("Partner Outbound Transcations", outbound_chart_data, true, true, false);
                PdfContentByte contentByte = writer.getDirectContent();
                PdfTemplate template = contentByte.createTemplate(width, height);
                Graphics2D graphics2d = template.createGraphics(width, height,
                        new DefaultFontMapper());
                Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width,
                        height);

                outboundPieChart.draw(graphics2d, rectangle2d);

                graphics2d.dispose();
                if (inbounddata.size() != 0) {
                    contentByte.addTemplate(template, 430, 630);
                } else {
                    contentByte.addTemplate(template, 210, 630);
                }
            }

            /* if(inbounddata.size()!=0||outbounddata.size()!=0)
             {*/
            document.newPage();
            //inboundtable.setHorizontalAlignment(inboundtable.ALIGN_LEFT);
            Paragraph par1 = new Paragraph("Dashboard Inbound and outbound Reports ", FontFactory.getFont("Arial", 26f));
            par1.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(par1);
            if (inbounddata.size() != 0) {
                Paragraph par2 = new Paragraph("INBOUND REPORTS ", FontFactory.getFont("Arial", 18f));
                par2.setSpacingBefore(20.00f);
                document.add(par2);
                inboundtable.setSpacingBefore(20.00f);
                inboundtable.setHorizontalAlignment(inboundtable.ALIGN_LEFT);
                document.add(inboundtable);
            }
            if (outbounddata.size() != 0) {
                Paragraph par3 = new Paragraph("OUTBOUND REPORTS ", FontFactory.getFont("Arial", 18f));
                par3.setSpacingBefore(20.00f);
                document.add(par3);
                outboundtable.setSpacingBefore(20.00f);
                outboundtable.setHorizontalAlignment(outboundtable.ALIGN_LEFT);
                document.add(outboundtable);
            }
            //  }
            document.close();
            fileOut.flush();
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

    /*
     * Reports edi tracking summary Generation
     * Date : 29/07/2015
     * 
     * 
     */
    public String docReportTrackingSummaryExcelDownload() {
        String filePath = "";
        try {
            // java.util.List list = (java.util.List) httpServletRequest.getSession(false).getAttribute(AppConstants.SES_DOC_LIST);
            java.util.List list = (java.util.List) httpServletRequest.getSession(false).getAttribute(AppConstants.SES_DOCREPORT_LIST);
            File file = new File(Properties.getProperty("mscvp.docCreationPath"));
            int inboundTotal = 0;
            int outboundTotal = 0;
            double filesizeTotal = 0;
            double filesizeTotal1 = 0;
            int total = 0;
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + File.separator + "EDITrackingSummaryReport.xls");
            filePath = file.getAbsolutePath() + File.separator + "EDITrackingSummaryReport.xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("EDITrackingSummaryReport");
            HSSFRow row1;
            TrackInOutBean trackInOutBean = null;
            // index from 0,0... cell A1 is cell(0,0)

            if (list.size() != 0) {
                HSSFCellStyle cellStyle = workbook.createCellStyle();

                HSSFCellStyle cellStyleHead = workbook.createCellStyle();

                HSSFFont font4 = workbook.createFont();
                HSSFFont fontHead = workbook.createFont();
                fontHead.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                // fontHead.setFontHeightInPoints((short)15);  //for font Size
                font4.setColor(HSSFColor.WHITE.index);

                cellStyle.setFillForegroundColor(HSSFColor.BLACK.index);
                cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellStyle.setFont(font4);
                //start	
                Date date = new Date();

                row1 = worksheet.createRow((short) 1);
                Cell cell = row1.createCell((short) 0);
                cell.setCellValue("EDI Tracking Summary:-Created Date : " + (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate());
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cell.setCellStyle(cellStyleHead);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("A2:F2"));
                //end
                row1 = worksheet.createRow((short) 3);

                HSSFCell cella1 = row1.createCell((short) 0);
                cella1.setCellValue("TRANSACTION TYPE");
                cella1.setCellStyle(cellStyle);
                //HSSFCellStyle cellStyle = workbook.createCellStyle(); 
                HSSFCell cellb1 = row1.createCell((short) 1);
                cellb1.setCellValue("Partner");
                cellb1.setCellStyle(cellStyle);
                HSSFCell cellc1 = row1.createCell((short) 2);
                cellc1.setCellValue("FILE_SIZE");
                cellc1.setCellStyle(cellStyle);
                HSSFCell celld1 = row1.createCell((short) 3);
                celld1.setCellValue("IN");
                celld1.setCellStyle(cellStyle);
                HSSFCell celle1 = row1.createCell((short) 4);
                celle1.setCellValue("OUT");
                celle1.setCellStyle(cellStyle);
                HSSFCell cellf1 = row1.createCell((short) 5);
                cellf1.setCellValue("Total");
                cellf1.setCellStyle(cellStyle);
                int j = 0;
                String trans_type = "";
                for (int i = 0; i < list.size(); i++) {
                    trackInOutBean = (TrackInOutBean) list.get(i);
                    row1 = worksheet.createRow((short) i + 5);

                    if (trackInOutBean.getTransaction_type() != null && !"".equalsIgnoreCase(trackInOutBean.getTransaction_type())) {
                        trans_type = trackInOutBean.getTransaction_type();
                        //cellA1.setCellValue(); 
                    }
                    HSSFCell cellB1 = row1.createCell((short) 1);

                    cellB1.setCellValue(trackInOutBean.getPname());

                    HSSFCell cellC1 = row1.createCell((short) 2);

                    cellC1.setCellValue(trackInOutBean.getFilesizeTotal());

                    HSSFCell cellD1 = row1.createCell((short) 3);

                    cellD1.setCellValue(trackInOutBean.getInbound());

                    HSSFCell cellE1 = row1.createCell((short) 4);

                    cellE1.setCellValue(trackInOutBean.getOutbound());
                    HSSFCell cellF1 = row1.createCell((short) 5);
                    cellF1.setCellValue(trackInOutBean.getTotal());
                    if (trackInOutBean.getPname().equalsIgnoreCase("Total")) {
                        row1 = worksheet.getRow((short) j + 5);
                        HSSFCell cellA1 = row1.createCell((short) 0);
                        cellA1.setCellValue(trans_type);
                        worksheet.addMergedRegion(new CellRangeAddress(
                                j + 5, //first row (0-based)
                                i + 5, //last row  (0-based)
                                0, //first column (0-based)
                                0 //last column  (0-based)
                        ));
                        j = i + 1;

                        inboundTotal = inboundTotal + trackInOutBean.getInbound();
                        outboundTotal = outboundTotal + trackInOutBean.getOutbound();
                        filesizeTotal = filesizeTotal + trackInOutBean.getFilesizeTotal();
                        //  filesizeTotal1=filesizeTotal1+trackInOutBean.getFilesize1();
                        total = total + trackInOutBean.getTotal();
                    }

                }
                row1 = worksheet.createRow((short) list.size() + 5);

                //worksheet.addMergedRegion()
                HSSFCell cellA1 = row1.createCell((short) 0);
                cellA1.setCellValue("Total");
                HSSFCell cellB1 = row1.createCell((short) 1);
                cellB1.setCellValue("");
                HSSFCell cellC1 = row1.createCell((short) 2);
                cellC1.setCellValue(filesizeTotal);
                HSSFCell cellD1 = row1.createCell((short) 3);
                cellD1.setCellValue(inboundTotal);
                HSSFCell cellE1 = row1.createCell((short) 4);
                cellE1.setCellValue(outboundTotal);
                HSSFCell cellF1 = row1.createCell((short) 5);
                cellF1.setCellValue(total);
                worksheet.addMergedRegion(new CellRangeAddress(
                        list.size() + 5, //first row (0-based)
                        list.size() + 5, //last row  (0-based)
                        0, //first column (0-based)
                        1 //last column  (0-based)
                ));

                worksheet.autoSizeColumn((short) 0);
                worksheet.autoSizeColumn((short) 1);
                worksheet.autoSizeColumn((short) 2);
                worksheet.autoSizeColumn((short) 3);
                worksheet.autoSizeColumn((short) 4);
                worksheet.autoSizeColumn((short) 5);
                worksheet.autoSizeColumn((short) 6);
                worksheet.autoSizeColumn((short) 7);
                worksheet.autoSizeColumn((short) 8);
                worksheet.autoSizeColumn((short) 9);

            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    /*
     * Reports edi tracking In/Out Generation
     * Date : 29/07/2015
     */
    public String docReportTrackingInOutExcelDownload() {
        String filePath = "";
        try {
            // java.util.List list = (java.util.List) httpServletRequest.getSession(false).getAttribute(AppConstants.SES_DOC_LIST);
            java.util.List list = (java.util.List) httpServletRequest.getSession(false).getAttribute(AppConstants.SES_DOCREPORT_LIST);
            File file = new File(Properties.getProperty("mscvp.docCreationPath"));
            int inboundTotal = 0;
            int outboundTotal = 0;
            int inbounddocTotal = 0;
            int outbounddocTotal = 0;
            int docTotal = 0;
            int allTotal = 0;

            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + File.separator + "EDITrackinginoutReport.xls");
            filePath = file.getAbsolutePath() + File.separator + "EDITrackinginoutReport.xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("EDITrackinginoutReport");
            HSSFRow row1;
            TrackInOutBean trackInOutBean = null;

            if (list.size() != 0) {
                trackInOutBean = (TrackInOutBean) list.get(0);
                ArrayList inboundList = trackInOutBean.getInboundList();
                ArrayList outboundList = trackInOutBean.getOutboundList();
                ArrayList docType = trackInOutBean.getDocumentTypeList();
                ArrayList dateMonth = trackInOutBean.getDateMonth();
                ArrayList dateMonthdocType = trackInOutBean.getDateMonthdocType();

                HSSFCellStyle cellStyle = workbook.createCellStyle();
                HSSFCellStyle cellStyleHead = workbook.createCellStyle();
                HSSFFont font4 = workbook.createFont();
                HSSFFont fontHead = workbook.createFont();
                fontHead.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                font4.setColor(HSSFColor.WHITE.index);

                cellStyle.setFillForegroundColor(HSSFColor.BLACK.index);
                cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellStyle.setFont(font4);

                Date date = new Date();
                row1 = worksheet.createRow((short) 1);
                Cell cell = row1.createCell((short) 1);
                cell.setCellValue("EDI Tracking IN/OUT:-Created Date : " + (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate());
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cell.setCellStyle(cellStyleHead);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("B2:J2"));
                row1 = worksheet.createRow((short) 3);

                HSSFCell cella1 = row1.createCell((short) 0);
                cella1.setCellValue("TRANSACTION TYPE");
                cella1.setCellStyle(cellStyle);
                HSSFCell cellb1 = row1.createCell((short) 1);
                cellb1.setCellValue("Direction");
                cellb1.setCellStyle(cellStyle);
                worksheet.autoSizeColumn((short) 0);
                worksheet.autoSizeColumn((short) 1);
                for (int i = 0; i < dateMonth.size(); i++) {
                    HSSFCell cellc1 = row1.createCell((short) i + 2);
                    cellc1.setCellValue((String) dateMonth.get(i));
                    cellc1.setCellStyle(cellStyle);
                    worksheet.autoSizeColumn((short) i + 2);
                }
                HSSFCell celld1 = row1.createCell((short) dateMonth.size() + 2);
                celld1.setCellValue("Total");
                celld1.setCellStyle(cellStyle);
                worksheet.autoSizeColumn((short) dateMonth.size() + 2);
                int k = 0;
                for (int i = 0; i < docType.size(); i++) {
                    int j = 0;
                    if (inboundList.contains(docType.get(i))) {
                        j = j + 1;
                    }
                    if (outboundList.contains(docType.get(i))) {
                        j = j + 1;
                    }
                    if (j == 1) {
                        row1 = worksheet.createRow((short) i + k + 4);
                        HSSFCell cellA1 = row1.createCell((short) 0);
                        cellA1.setCellValue((String) docType.get(i));
                        worksheet.addMergedRegion(new CellRangeAddress(
                                i + k + 4, //first row (0-based)
                                i + k + 5, //last row  (0-based)
                                0, //first column (0-based)
                                0 //last column  (0-based)
                        ));

                        if (inboundList.contains(docType.get(i))) {
                            HSSFCell cellB1 = row1.createCell((short) 1);
                            cellB1.setCellValue("Inbound");
                        }
                        if (outboundList.contains(docType.get(i))) {
                            HSSFCell cellC1 = row1.createCell((short) 1);
                            cellC1.setCellValue("Outbound");
                        }
                        row1 = worksheet.createRow((short) i + k + 5);
                        HSSFCell cellD1 = row1.createCell((short) 1);
                        cellD1.setCellValue("Total");
                        k = k + 1;
                    }
                    if (j == 2) {
                        row1 = worksheet.createRow((short) i + k + 4);
                        HSSFCell cellA1 = row1.createCell((short) 0);
                        cellA1.setCellValue((String) docType.get(i));
                        worksheet.addMergedRegion(new CellRangeAddress(
                                i + k + 4, //first row (0-based)
                                i + k + 6, //last row  (0-based)
                                0, //first column (0-based)
                                0 //last column  (0-based)
                        ));
                        HSSFCell cellB1 = row1.createCell((short) 1);
                        cellB1.setCellValue("Inbound");
                        row1 = worksheet.createRow((short) i + k + 5);
                        HSSFCell cellC1 = row1.createCell((short) 1);
                        cellC1.setCellValue("Outbound");
                        row1 = worksheet.createRow((short) i + k + 6);
                        HSSFCell cellD1 = row1.createCell((short) 1);
                        cellD1.setCellValue("Total");
                        k = k + 2;
                    }

                }
                row1 = worksheet.createRow((short) docType.size() + k + 4);
                HSSFCell cellA1 = row1.createCell((short) 0);
                cellA1.setCellValue("Total");
                worksheet.addMergedRegion(new CellRangeAddress(
                        docType.size() + k + 4, //first row (0-based)
                        docType.size() + k + 4, //last row  (0-based)
                        0, //first column (0-based)
                        1 //last column  (0-based)
                ));

                int inboundvalue = 0;
                int outboundvalue = 0;
                int inoutTotal = 0;
                for (int j = 0; j < dateMonthdocType.size(); j++) {
                    ArrayList temp = (ArrayList) dateMonthdocType.get(j);
                    ArrayList olddoctype = new ArrayList();

                    int total = 0;
                    k = 0;
                    for (int i = 0; i < docType.size(); i++) {

                        for (int l = 1; l < temp.size(); l = l + 4) {
                            int m = 0;
                            if (temp.get(l).equals(docType.get(i))) {
                                inboundvalue = (Integer) temp.get(l + 1);
                                outboundvalue = (Integer) temp.get(l + 2);
                                inoutTotal = (Integer) temp.get(l + 3);
                                if (inboundList.contains(docType.get(i))) {
                                    m = m + 1;
                                }
                                if (outboundList.contains(docType.get(i))) {
                                    m = m + 1;
                                }
                                if (m == 1) {
                                    row1 = worksheet.getRow((short) k + 4);
                                    if (inboundList.contains(docType.get(i))) {
                                        HSSFCell cellB1 = row1.createCell((short) j + 2);
                                        cellB1.setCellValue(inboundvalue);

                                    }
                                    if (outboundList.contains(docType.get(i))) {
                                        HSSFCell cellC1 = row1.createCell((short) j + 2);

                                        cellC1.setCellValue(outboundvalue);

                                    }
                                    row1 = worksheet.getRow((short) k + 5);
                                    HSSFCell cellD1 = row1.createCell((short) j + 2);

                                    cellD1.setCellValue(inoutTotal);
                                    k = k + 2;
                                }

                                if (m == 2) {
                                    row1 = worksheet.getRow((short) k + 4);
                                    HSSFCell cellB1 = row1.createCell((short) j + 2);
                                    cellB1.setCellValue(inboundvalue);
                                    row1 = worksheet.getRow((short) k + 5);

                                    HSSFCell cellC1 = row1.createCell((short) j + 2);

                                    cellC1.setCellValue(outboundvalue);
                                    row1 = worksheet.getRow((short) k + 6);
                                    HSSFCell cellD1 = row1.createCell((short) j + 2);

                                    cellD1.setCellValue(inoutTotal);

                                    k = k + 3;
                                }
                                total = (Integer) temp.get(l + 3) + total;

                            } else {
                                if (!temp.contains(docType.get(i)) && !olddoctype.contains((String) docType.get(i))) {
                                    olddoctype.add((String) docType.get(i));
                                    if (inboundList.contains(docType.get(i))) {
                                        m = m + 1;
                                    }
                                    if (outboundList.contains(docType.get(i))) {
                                        m = m + 1;
                                    }
                                    if (m == 1) {
                                        row1 = worksheet.getRow((short) k + 4);
                                        if (inboundList.contains(docType.get(i))) {
                                            HSSFCell cellB1 = row1.createCell((short) j + 2);
                                            cellB1.setCellValue(0);
                                        }
                                        if (outboundList.contains(docType.get(i))) {
                                            HSSFCell cellC1 = row1.createCell((short) j + 2);
                                            cellC1.setCellValue(0);
                                        }
                                        row1 = worksheet.getRow((short) k + 5);
                                        HSSFCell cellD1 = row1.createCell((short) j + 2);
                                        cellD1.setCellValue(0);
                                        k = k + 2;
                                    }

                                    if (m == 2) {

                                        row1 = worksheet.getRow((short) k + 4);
                                        HSSFCell cellB1 = row1.createCell((short) j + 2);
                                        cellB1.setCellValue(0);
                                        row1 = worksheet.getRow((short) k + 5);
                                        HSSFCell cellC1 = row1.createCell((short) j + 2);
                                        cellC1.setCellValue(0);
                                        row1 = worksheet.getRow((short) k + 6);
                                        HSSFCell cellD1 = row1.createCell((short) j + 2);
                                        cellD1.setCellValue(0);
                                        k = k + 3;
                                    }
                                }
                            }

                        }
                    }

                    row1 = worksheet.getRow((short) k + 4);
                    HSSFCell cellB1 = row1.createCell((short) j + 2);
                    cellB1.setCellValue(total);
                }
                int m = 0;
                for (int j = 0; j < docType.size(); j++) {
                    for (int i = 0; i < dateMonthdocType.size(); i++) {
                        ArrayList temp1 = (ArrayList) dateMonthdocType.get(i);
                        for (int l = 1; l < temp1.size(); l = l + 4) {
                            if (temp1.get(l).equals(docType.get(j))) {
                                inbounddocTotal = (Integer) temp1.get(l + 1) + inbounddocTotal;
                                outbounddocTotal = (Integer) temp1.get(l + 2) + outbounddocTotal;
                                docTotal = (Integer) temp1.get(l + 3) + docTotal;
                            }
                        }
                    }
                    k = 0;
                    if (inboundList.contains(docType.get(j))) {
                        k = k + 1;
                    }
                    if (outboundList.contains(docType.get(j))) {
                        k = k + 1;
                    }
                    if (k == 1) {

                        row1 = worksheet.getRow((short) j + m + 4);

                        if (inboundList.contains(docType.get(j))) {
                            HSSFCell cellB1 = row1.createCell((short) dateMonth.size() + 2);
                            cellB1.setCellValue(inbounddocTotal);
                        }
                        if (outboundList.contains(docType.get(j))) {
                            HSSFCell cellC1 = row1.createCell((short) dateMonth.size() + 2);
                            cellC1.setCellValue(outbounddocTotal);
                        }
                        row1 = worksheet.getRow((short) j + m + 5);
                        HSSFCell cellD1 = row1.createCell((short) dateMonth.size() + 2);
                        cellD1.setCellValue(docTotal);
                        m = m + 1;
                    }

                    if (k == 2) {
                        row1 = worksheet.getRow((short) j + m + 4);

                        HSSFCell cellB1 = row1.createCell((short) dateMonth.size() + 2);
                        cellB1.setCellValue(inbounddocTotal);
                        row1 = worksheet.getRow((short) j + m + 5);
                        HSSFCell cellC1 = row1.createCell((short) dateMonth.size() + 2);
                        cellC1.setCellValue(outbounddocTotal);
                        row1 = worksheet.getRow((short) j + m + 6);
                        HSSFCell cellD1 = row1.createCell((short) dateMonth.size() + 2);
                        cellD1.setCellValue(docTotal);
                        m = m + 2;
                    }

                    allTotal = allTotal + docTotal;
                    inbounddocTotal = 0;
                    outbounddocTotal = 0;
                    docTotal = 0;
                }
                row1 = worksheet.getRow((short) docType.size() + m + 4);
                HSSFCell cellB1 = row1.createCell((short) dateMonth.size() + 2);
                cellB1.setCellValue(allTotal);
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    public String docReportTrackingInquiryExcelDownload() {
        String filePath = "";
        try {
            java.util.List list = (java.util.List) httpServletRequest.getSession(false).getAttribute(AppConstants.SES_DOCREPORT_LIST);
            File file = new File(Properties.getProperty("mscvp.docCreationPath"));
            if (!file.exists()) {
                file.mkdirs();
            }

            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + File.separator + "docEdiTrackingInquiryReport.xls");
            filePath = file.getAbsolutePath() + File.separator + "docEdiTrackingInquiryReport.xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("EdiTrackingInquiry");
            HSSFRow row1;
            TrackInOutBean docRepositoryBean = null;

            if (list.size() != 0) {
                HSSFCellStyle cellStyle = workbook.createCellStyle();
                HSSFCellStyle cellStyle1 = workbook.createCellStyle();
                HSSFCellStyle cellStyle2 = workbook.createCellStyle();
                HSSFCellStyle cellStyle3 = workbook.createCellStyle();
                HSSFCellStyle cellStyleHead = workbook.createCellStyle();
                HSSFFont font1 = workbook.createFont();
                HSSFFont font2 = workbook.createFont();
                HSSFFont font3 = workbook.createFont();
                HSSFFont font4 = workbook.createFont();
                HSSFFont fontHead = workbook.createFont();
                fontHead.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                font4.setColor(HSSFColor.WHITE.index);

                cellStyle.setFillForegroundColor(HSSFColor.BLACK.index);
                cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellStyle.setFont(font4);
                //start	
                Date date = new Date();
                row1 = worksheet.createRow((short) 0);
                HSSFCell cellpo0 = row1.createCell((short) 0);
                HSSFCell cellpo1 = row1.createCell((short) 1);
                HSSFCell cellpo2 = row1.createCell((short) 2);
                HSSFCell cellpo3 = row1.createCell((short) 3);
                HSSFCell cellpo4 = row1.createCell((short) 4);

                row1 = worksheet.createRow((short) 1);

                Cell cell = row1.createCell((short) 1);
                cell.setCellValue("Edi Tracking Inquiry:-Created Date : " + (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate());
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cell.setCellStyle(cellStyleHead);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("B2:F2"));
                row1 = worksheet.createRow((short) 3);

                HSSFCell cella1 = row1.createCell((short) 0);
                cella1.setCellValue("Trans_Type");
                cella1.setCellStyle(cellStyle);

                HSSFCell cellb1 = row1.createCell((short) 1);
                cellb1.setCellValue("Date Sent");
                cellb1.setCellStyle(cellStyle);

                HSSFCell cellc1 = row1.createCell((short) 2);
                cellc1.setCellValue("Date Acked");
                cellc1.setCellStyle(cellStyle);

                HSSFCell celld1 = row1.createCell((short) 3);
                celld1.setCellValue("ACK Code");
                celld1.setCellStyle(cellStyle);

                HSSFCell celle1 = row1.createCell((short) 4);
                celle1.setCellValue("Partner");
                celle1.setCellStyle(cellStyle);

                for (int i = 0; i < list.size(); i++) {
                    docRepositoryBean = (TrackInOutBean) list.get(i);

                    row1 = worksheet.createRow((short) i + 4);

                    HSSFCell cellA1 = row1.createCell((short) 0);
                    cellA1.setCellValue(docRepositoryBean.getTransaction_type());

                    HSSFCell cellB1 = row1.createCell((short) 1);
                    cellB1.setCellValue(docRepositoryBean.getDate_time_rec().toString().substring(0, docRepositoryBean.getDate_time_rec().toString().lastIndexOf(":")));

                    HSSFCell cellC1 = row1.createCell((short) 2);
                    cellC1.setCellValue(docRepositoryBean.getDate_time_rec().toString().substring(0, docRepositoryBean.getDate_time_rec().toString().lastIndexOf(":")));

                    HSSFCell cellD1 = row1.createCell((short) 3);

                    if (docRepositoryBean.getAckStatus() != null) {
                        if (docRepositoryBean.getAckStatus().equalsIgnoreCase("ACCEPTED")) {

                            font1.setColor(HSSFColor.GREEN.index);
                            cellD1.setCellValue(docRepositoryBean.getAckStatus().toUpperCase());
                            cellStyle1.setFont(font1);
                            cellD1.setCellStyle(cellStyle1);
                        } else if (docRepositoryBean.getAckStatus().equalsIgnoreCase("REJECTED")) {

                            font2.setColor(HSSFColor.RED.index);
                            cellD1.setCellValue(docRepositoryBean.getAckStatus().toUpperCase());
                            cellStyle2.setFont(font2);
                            cellD1.setCellStyle(cellStyle2);
                        } else {

                            font3.setColor(HSSFColor.ORANGE.index);
                            cellD1.setCellValue(docRepositoryBean.getAckStatus().toUpperCase());
                            cellStyle3.setFont(font3);
                            cellD1.setCellStyle(cellStyle3);
                        }
                    }
                    HSSFCell cellE1 = row1.createCell((short) 4);

                    cellE1.setCellValue(docRepositoryBean.getPname());
                }
                worksheet.autoSizeColumn((short) 0);
                worksheet.autoSizeColumn((short) 1);
                worksheet.autoSizeColumn((short) 2);
                worksheet.autoSizeColumn((short) 3);
                worksheet.autoSizeColumn((short) 4);

            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    public String reportDownloads() {

        try {
            this.setScheduleId(Integer.parseInt(httpServletRequest.getParameter("Id").toString()));
            this.setReportattachment(ServiceLocator.getGridDownloadService().getReportattachment(this.getScheduleId(), this.getStartDate()));
            httpServletResponse.setContentType("application/force-download");

            File file = new File(getReportattachment());
            fileName = file.getName();
            inputStream = new FileInputStream(file);
            outputStream = httpServletResponse.getOutputStream();
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            int noOfBytesRead = 0;
            byte[] byteArray = null;
            while (true) {
                byteArray = new byte[1024];
                noOfBytesRead = inputStream.read(byteArray);
                if (noOfBytesRead == -1) {
                    break;
                }
                outputStream.write(byteArray, 0, noOfBytesRead);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;

    }

// new action implement for logistic reports
    public String logisticReportTrackingInOutExcelDownload() {
        String filePath = "";
        try {
            java.util.List list = (java.util.List) httpServletRequest.getSession(false).getAttribute(AppConstants.SES_DOCREPORT_LIST);
            File file = new File(Properties.getProperty("mscvp.docCreationPath"));
            int inboundTotal = 0;
            int outboundTotal = 0;

            int inbounddocTotal = 0;
            int outbounddocTotal = 0;
            int docTotal = 0;
            int allTotal = 0;

            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + File.separator + "EDITrackinginoutlogisticReport.xls");
            filePath = file.getAbsolutePath() + File.separator + "EDITrackinginoutlogisticReport.xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("EDITrackinginoutlogisticReport");
            HSSFRow row1;
            LogisticTrackInOutBean trackInOutBean = null;

            if (list.size() != 0) {
                trackInOutBean = (LogisticTrackInOutBean) list.get(0);
                ArrayList inboundList = trackInOutBean.getInboundList();
                ArrayList outboundList = trackInOutBean.getOutboundList();
                ArrayList docType = trackInOutBean.getDocumentTypeList();
                ArrayList dateMonth = trackInOutBean.getDateMonth();
                ArrayList dateMonthdocType = trackInOutBean.getDateMonthdocType();

                HSSFCellStyle cellStyle = workbook.createCellStyle();
                HSSFCellStyle cellStyleHead = workbook.createCellStyle();
                HSSFFont font4 = workbook.createFont();
                HSSFFont fontHead = workbook.createFont();
                fontHead.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                font4.setColor(HSSFColor.WHITE.index);
                cellStyle.setFillForegroundColor(HSSFColor.BLACK.index);
                cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellStyle.setFont(font4);
                Date date = new Date();
                row1 = worksheet.createRow((short) 1);
                Cell cell = row1.createCell((short) 1);
                cell.setCellValue("EDI Tracking IN/OUT:-Created Date : " + (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate());
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cell.setCellStyle(cellStyleHead);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("B2:J2"));
                row1 = worksheet.createRow((short) 3);

                HSSFCell cella1 = row1.createCell((short) 0);
                cella1.setCellValue("TRANSACTION TYPE");
                cella1.setCellStyle(cellStyle);
                HSSFCell cellb1 = row1.createCell((short) 1);
                cellb1.setCellValue("Direction");
                cellb1.setCellStyle(cellStyle);
                worksheet.autoSizeColumn((short) 0);
                worksheet.autoSizeColumn((short) 1);
                for (int i = 0; i < dateMonth.size(); i++) {
                    HSSFCell cellc1 = row1.createCell((short) i + 2);
                    cellc1.setCellValue((String) dateMonth.get(i));
                    cellc1.setCellStyle(cellStyle);
                    worksheet.autoSizeColumn((short) i + 2);
                }
                HSSFCell celld1 = row1.createCell((short) dateMonth.size() + 2);
                celld1.setCellValue("Total");
                celld1.setCellStyle(cellStyle);
                worksheet.autoSizeColumn((short) dateMonth.size() + 2);
                int k = 0;
                for (int i = 0; i < docType.size(); i++) {
                    int j = 0;
                    if (inboundList.contains(docType.get(i))) {
                        j = j + 1;
                    }
                    if (outboundList.contains(docType.get(i))) {
                        j = j + 1;
                    }
                    if (j == 1) {
                        row1 = worksheet.createRow((short) i + k + 4);
                        HSSFCell cellA1 = row1.createCell((short) 0);
                        cellA1.setCellValue((String) docType.get(i));
                        worksheet.addMergedRegion(new CellRangeAddress(
                                i + k + 4, //first row (0-based)
                                i + k + 5, //last row  (0-based)
                                0, //first column (0-based)
                                0 //last column  (0-based)
                        ));

                        if (inboundList.contains(docType.get(i))) {
                            HSSFCell cellB1 = row1.createCell((short) 1);
                            cellB1.setCellValue("Inbound");
                        }
                        if (outboundList.contains(docType.get(i))) {
                            HSSFCell cellC1 = row1.createCell((short) 1);
                            cellC1.setCellValue("Outbound");
                        }
                        row1 = worksheet.createRow((short) i + k + 5);
                        HSSFCell cellD1 = row1.createCell((short) 1);
                        cellD1.setCellValue("Total");
                        k = k + 1;
                    }

                    if (j == 2) {
                        row1 = worksheet.createRow((short) i + k + 4);
                        HSSFCell cellA1 = row1.createCell((short) 0);
                        cellA1.setCellValue((String) docType.get(i));
                        worksheet.addMergedRegion(new CellRangeAddress(
                                i + k + 4, //first row (0-based)
                                i + k + 6, //last row  (0-based)
                                0, //first column (0-based)
                                0 //last column  (0-based)
                        ));
                        HSSFCell cellB1 = row1.createCell((short) 1);
                        cellB1.setCellValue("Inbound");
                        row1 = worksheet.createRow((short) i + k + 5);
                        HSSFCell cellC1 = row1.createCell((short) 1);
                        cellC1.setCellValue("Outbound");
                        row1 = worksheet.createRow((short) i + k + 6);
                        HSSFCell cellD1 = row1.createCell((short) 1);
                        cellD1.setCellValue("Total");
                        k = k + 2;
                    }

                }
                row1 = worksheet.createRow((short) docType.size() + k + 4);
                HSSFCell cellA1 = row1.createCell((short) 0);
                cellA1.setCellValue("Total");
                worksheet.addMergedRegion(new CellRangeAddress(
                        docType.size() + k + 4, //first row (0-based)
                        docType.size() + k + 4, //last row  (0-based)
                        0, //first column (0-based)
                        1 //last column  (0-based)
                ));

                int inboundvalue = 0;
                int outboundvalue = 0;
                int inoutTotal = 0;
                for (int j = 0; j < dateMonthdocType.size(); j++) {
                    ArrayList temp = (ArrayList) dateMonthdocType.get(j);
                    ArrayList olddoctype = new ArrayList();

                    int total = 0;
                    k = 0;
                    for (int i = 0; i < docType.size(); i++) {

                        for (int l = 1; l < temp.size(); l = l + 4) {
                            int m = 0;
                            if (temp.get(l).equals(docType.get(i))) {
                                inboundvalue = (Integer) temp.get(l + 1);
                                outboundvalue = (Integer) temp.get(l + 2);
                                inoutTotal = (Integer) temp.get(l + 3);
                                if (inboundList.contains(docType.get(i))) {
                                    m = m + 1;
                                }
                                if (outboundList.contains(docType.get(i))) {
                                    m = m + 1;
                                }
                                if (m == 1) {
                                    row1 = worksheet.getRow((short) k + 4);
                                    if (inboundList.contains(docType.get(i))) {
                                        HSSFCell cellB1 = row1.createCell((short) j + 2);
                                        cellB1.setCellValue(inboundvalue);
                                    }
                                    if (outboundList.contains(docType.get(i))) {
                                        HSSFCell cellC1 = row1.createCell((short) j + 2);

                                        cellC1.setCellValue(outboundvalue);
                                    }
                                    row1 = worksheet.getRow((short) k + 5);
                                    HSSFCell cellD1 = row1.createCell((short) j + 2);

                                    cellD1.setCellValue(inoutTotal);
                                    k = k + 2;
                                }

                                if (m == 2) {
                                    row1 = worksheet.getRow((short) k + 4);
                                    HSSFCell cellB1 = row1.createCell((short) j + 2);
                                    cellB1.setCellValue(inboundvalue);
                                    row1 = worksheet.getRow((short) k + 5);

                                    HSSFCell cellC1 = row1.createCell((short) j + 2);

                                    cellC1.setCellValue(outboundvalue);
                                    row1 = worksheet.getRow((short) k + 6);
                                    HSSFCell cellD1 = row1.createCell((short) j + 2);

                                    cellD1.setCellValue(inoutTotal);

                                    k = k + 3;
                                }
                                total = (Integer) temp.get(l + 3) + total;

                            } else {
                                if (!temp.contains(docType.get(i)) && !olddoctype.contains((String) docType.get(i))) {
                                    olddoctype.add((String) docType.get(i));
                                    if (inboundList.contains(docType.get(i))) {
                                        m = m + 1;
                                    }
                                    if (outboundList.contains(docType.get(i))) {
                                        m = m + 1;
                                    }
                                    if (m == 1) {
                                        row1 = worksheet.getRow((short) k + 4);
                                        if (inboundList.contains(docType.get(i))) {
                                            HSSFCell cellB1 = row1.createCell((short) j + 2);
                                            cellB1.setCellValue(0);
                                        }
                                        if (outboundList.contains(docType.get(i))) {
                                            HSSFCell cellC1 = row1.createCell((short) j + 2);
                                            cellC1.setCellValue(0);
                                        }
                                        row1 = worksheet.getRow((short) k + 5);
                                        HSSFCell cellD1 = row1.createCell((short) j + 2);
                                        cellD1.setCellValue(0);
                                        k = k + 2;
                                    }

                                    if (m == 2) {

                                        row1 = worksheet.getRow((short) k + 4);
                                        HSSFCell cellB1 = row1.createCell((short) j + 2);
                                        cellB1.setCellValue(0);
                                        row1 = worksheet.getRow((short) k + 5);
                                        HSSFCell cellC1 = row1.createCell((short) j + 2);
                                        cellC1.setCellValue(0);
                                        row1 = worksheet.getRow((short) k + 6);
                                        HSSFCell cellD1 = row1.createCell((short) j + 2);
                                        cellD1.setCellValue(0);
                                        k = k + 3;
                                    }
                                }
                            }

                        }
                    }

                    row1 = worksheet.getRow((short) k + 4);
                    HSSFCell cellB1 = row1.createCell((short) j + 2);
                    cellB1.setCellValue(total);
                }
                int m = 0;
                for (int j = 0; j < docType.size(); j++) {
                    for (int i = 0; i < dateMonthdocType.size(); i++) {
                        ArrayList temp1 = (ArrayList) dateMonthdocType.get(i);
                        for (int l = 1; l < temp1.size(); l = l + 4) {
                            if (temp1.get(l).equals(docType.get(j))) {
                                inbounddocTotal = (Integer) temp1.get(l + 1) + inbounddocTotal;
                                outbounddocTotal = (Integer) temp1.get(l + 2) + outbounddocTotal;
                                docTotal = (Integer) temp1.get(l + 3) + docTotal;
                            }
                        }
                    }
                    k = 0;
                    if (inboundList.contains(docType.get(j))) {
                        k = k + 1;
                    }
                    if (outboundList.contains(docType.get(j))) {
                        k = k + 1;
                    }
                    if (k == 1) {

                        row1 = worksheet.getRow((short) j + m + 4);

                        if (inboundList.contains(docType.get(j))) {
                            HSSFCell cellB1 = row1.createCell((short) dateMonth.size() + 2);
                            cellB1.setCellValue(inbounddocTotal);
                        }
                        if (outboundList.contains(docType.get(j))) {
                            HSSFCell cellC1 = row1.createCell((short) dateMonth.size() + 2);
                            cellC1.setCellValue(outbounddocTotal);
                        }
                        row1 = worksheet.getRow((short) j + m + 5);
                        HSSFCell cellD1 = row1.createCell((short) dateMonth.size() + 2);
                        cellD1.setCellValue(docTotal);
                        m = m + 1;
                    }

                    if (k == 2) {
                        row1 = worksheet.getRow((short) j + m + 4);

                        HSSFCell cellB1 = row1.createCell((short) dateMonth.size() + 2);
                        cellB1.setCellValue(inbounddocTotal);
                        row1 = worksheet.getRow((short) j + m + 5);
                        HSSFCell cellC1 = row1.createCell((short) dateMonth.size() + 2);
                        cellC1.setCellValue(outbounddocTotal);
                        row1 = worksheet.getRow((short) j + m + 6);
                        HSSFCell cellD1 = row1.createCell((short) dateMonth.size() + 2);
                        cellD1.setCellValue(docTotal);
                        m = m + 2;
                    }

                    allTotal = allTotal + docTotal;
                    inbounddocTotal = 0;
                    outbounddocTotal = 0;
                    docTotal = 0;
                }
                row1 = worksheet.getRow((short) docType.size() + m + 4);
                HSSFCell cellB1 = row1.createCell((short) dateMonth.size() + 2);
                cellB1.setCellValue(allTotal);
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    public String logisticReportTrackingInquiryExcelDownload() {
        String filePath = "";
        try {
            java.util.List list = (java.util.List) httpServletRequest.getSession(false).getAttribute(AppConstants.SES_DOCREPORT_LIST);
            File file = new File(Properties.getProperty("mscvp.docCreationPath"));
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + File.separator + "LogisticEdiTrackingInquiryReport.xls");
            filePath = file.getAbsolutePath() + File.separator + "LogisticEdiTrackingInquiryReport.xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("LogisticEdiTrackingInquiryReport");
            HSSFRow row1;
            LogisticTrackInOutBean docRepositoryBean = null;

            if (list.size() != 0) {
                HSSFCellStyle cellStyle = workbook.createCellStyle();
                HSSFCellStyle cellStyle1 = workbook.createCellStyle();
                HSSFCellStyle cellStyle2 = workbook.createCellStyle();
                HSSFCellStyle cellStyle3 = workbook.createCellStyle();
                HSSFCellStyle cellStyleHead = workbook.createCellStyle();
                HSSFFont font1 = workbook.createFont();
                HSSFFont font2 = workbook.createFont();
                HSSFFont font3 = workbook.createFont();
                HSSFFont font4 = workbook.createFont();
                HSSFFont fontHead = workbook.createFont();
                fontHead.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                font4.setColor(HSSFColor.WHITE.index);
                cellStyle.setFillForegroundColor(HSSFColor.BLACK.index);
                cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellStyle.setFont(font4);
                Date date = new Date();
                row1 = worksheet.createRow((short) 0);
                HSSFCell cellpo0 = row1.createCell((short) 0);
                HSSFCell cellpo1 = row1.createCell((short) 1);
                HSSFCell cellpo2 = row1.createCell((short) 2);
                HSSFCell cellpo3 = row1.createCell((short) 3);
                HSSFCell cellpo4 = row1.createCell((short) 4);
                row1 = worksheet.createRow((short) 1);
                Cell cell = row1.createCell((short) 1);
                cell.setCellValue("Edi Tracking Inquiry:-Created Date : " + (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate());
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cell.setCellStyle(cellStyleHead);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("B2:F2"));
                //end
                row1 = worksheet.createRow((short) 3);

                HSSFCell cella1 = row1.createCell((short) 0);
                cella1.setCellValue("Trans_Type");
                cella1.setCellStyle(cellStyle);

                HSSFCell cellb1 = row1.createCell((short) 1);
                cellb1.setCellValue("Date Sent");
                cellb1.setCellStyle(cellStyle);

                HSSFCell cellc1 = row1.createCell((short) 2);
                cellc1.setCellValue("Date Acked");
                cellc1.setCellStyle(cellStyle);

                HSSFCell celld1 = row1.createCell((short) 3);
                celld1.setCellValue("ACK Code");
                celld1.setCellStyle(cellStyle);

                HSSFCell celle1 = row1.createCell((short) 4);
                celle1.setCellValue("Partner");
                celle1.setCellStyle(cellStyle);
                for (int i = 0; i < list.size(); i++) {
                    docRepositoryBean = (LogisticTrackInOutBean) list.get(i);

                    row1 = worksheet.createRow((short) i + 4);

                    HSSFCell cellA1 = row1.createCell((short) 0);
                    cellA1.setCellValue(docRepositoryBean.getTransaction_type());

                    HSSFCell cellB1 = row1.createCell((short) 1);
                    cellB1.setCellValue(docRepositoryBean.getDate_time_rec().toString().substring(0, docRepositoryBean.getDate_time_rec().toString().lastIndexOf(":")));

                    HSSFCell cellC1 = row1.createCell((short) 2);
                    cellC1.setCellValue(docRepositoryBean.getDate_time_rec().toString().substring(0, docRepositoryBean.getDate_time_rec().toString().lastIndexOf(":")));

                    HSSFCell cellD1 = row1.createCell((short) 3);

                    if (docRepositoryBean.getAckStatus() != null) {
                        if (docRepositoryBean.getAckStatus().equalsIgnoreCase("ACCEPTED")) {
                            font1.setColor(HSSFColor.GREEN.index);
                            cellD1.setCellValue(docRepositoryBean.getAckStatus().toUpperCase());
                            cellStyle1.setFont(font1);
                            cellD1.setCellStyle(cellStyle1);
                        } else if (docRepositoryBean.getAckStatus().equalsIgnoreCase("REJECTED")) {
                            font2.setColor(HSSFColor.RED.index);
                            cellD1.setCellValue(docRepositoryBean.getAckStatus().toUpperCase());
                            cellStyle2.setFont(font2);
                            cellD1.setCellStyle(cellStyle2);
                        } else {
                            font3.setColor(HSSFColor.ORANGE.index);
                            cellD1.setCellValue(docRepositoryBean.getAckStatus().toUpperCase());
                            cellStyle3.setFont(font3);
                            cellD1.setCellStyle(cellStyle3);
                        }
                    }
                    HSSFCell cellE1 = row1.createCell((short) 4);
                    cellE1.setCellValue(docRepositoryBean.getPname());
                }
                worksheet.autoSizeColumn((short) 0);
                worksheet.autoSizeColumn((short) 1);
                worksheet.autoSizeColumn((short) 2);
                worksheet.autoSizeColumn((short) 3);
                worksheet.autoSizeColumn((short) 4);
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    public String logisticReportTrackingSummaryExcelDownload() {
        String filePath = "";
        try {
            java.util.List list = (java.util.List) httpServletRequest.getSession(false).getAttribute(AppConstants.SES_DOCREPORT_LIST);
            File file = new File(Properties.getProperty("mscvp.docCreationPath"));
            int inboundTotal = 0;
            int outboundTotal = 0;
            double filesizeTotal = 0;
            double filesizeTotal1 = 0;
            int total = 0;
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + File.separator + "EDITrackinglogisticSummaryReport.xls");
            filePath = file.getAbsolutePath() + File.separator + "EDITrackinglogisticSummaryReport.xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("EDITrackinglogisticSummaryReport");
            HSSFRow row1;
            LogisticTrackInOutBean trackInOutBean = null;

            if (list.size() != 0) {
                HSSFCellStyle cellStyle = workbook.createCellStyle();
                HSSFCellStyle cellStyleHead = workbook.createCellStyle();

                HSSFFont font4 = workbook.createFont();
                HSSFFont fontHead = workbook.createFont();

                fontHead.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                font4.setColor(HSSFColor.WHITE.index);

                cellStyle.setFillForegroundColor(HSSFColor.BLACK.index);
                cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellStyle.setFont(font4);
                //start	
                Date date = new Date();
                row1 = worksheet.createRow((short) 1);
                Cell cell = row1.createCell((short) 0);
                cell.setCellValue("EDI Tracking Summary:-Created Date : " + (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate());
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cell.setCellStyle(cellStyleHead);
                worksheet.addMergedRegion(CellRangeAddress.valueOf("A2:F2"));
                row1 = worksheet.createRow((short) 3);

                HSSFCell cella1 = row1.createCell((short) 0);
                cella1.setCellValue("TRANSACTION TYPE");
                cella1.setCellStyle(cellStyle);

                HSSFCell cellb1 = row1.createCell((short) 1);
                cellb1.setCellValue("Partner");
                cellb1.setCellStyle(cellStyle);

                HSSFCell cellc1 = row1.createCell((short) 2);
                cellc1.setCellValue("FILE_SIZE");
                cellc1.setCellStyle(cellStyle);

                HSSFCell celld1 = row1.createCell((short) 3);
                celld1.setCellValue("IN");
                celld1.setCellStyle(cellStyle);

                HSSFCell celle1 = row1.createCell((short) 4);
                celle1.setCellValue("OUT");
                celle1.setCellStyle(cellStyle);

                HSSFCell cellf1 = row1.createCell((short) 5);
                cellf1.setCellValue("Total");
                cellf1.setCellStyle(cellStyle);

                int j = 0;
                String trans_type = "";
                for (int i = 0; i < list.size(); i++) {
                    trackInOutBean = (LogisticTrackInOutBean) list.get(i);
                    row1 = worksheet.createRow((short) i + 5);

                    if (trackInOutBean.getTransaction_type() != null && !"".equalsIgnoreCase(trackInOutBean.getTransaction_type())) {
                        trans_type = trackInOutBean.getTransaction_type();
                    }

                    HSSFCell cellB1 = row1.createCell((short) 1);
                    cellB1.setCellValue(trackInOutBean.getPname());

                    HSSFCell cellC1 = row1.createCell((short) 2);
                    cellC1.setCellValue(trackInOutBean.getFilesizeTotal());

                    HSSFCell cellD1 = row1.createCell((short) 3);
                    cellD1.setCellValue(trackInOutBean.getInbound());

                    HSSFCell cellE1 = row1.createCell((short) 4);

                    cellE1.setCellValue(trackInOutBean.getOutbound());
                    HSSFCell cellF1 = row1.createCell((short) 5);
                    cellF1.setCellValue(trackInOutBean.getTotal());
                    if (trackInOutBean.getPname().equalsIgnoreCase("Total")) {
                        System.out.println("tans type" + trans_type);
                        row1 = worksheet.getRow((short) j + 5);
                        HSSFCell cellA1 = row1.createCell((short) 0);
                        cellA1.setCellValue(trans_type);
                        worksheet.addMergedRegion(new CellRangeAddress(
                                j + 5, //first row (0-based)
                                i + 5, //last row  (0-based)
                                0, //first column (0-based)
                                0 //last column  (0-based)
                        ));
                        j = i + 1;

                        inboundTotal = inboundTotal + trackInOutBean.getInbound();
                        outboundTotal = outboundTotal + trackInOutBean.getOutbound();
                        filesizeTotal = filesizeTotal + trackInOutBean.getFilesizeTotal();
                        total = total + trackInOutBean.getTotal();
                    }

                }
                row1 = worksheet.createRow((short) list.size() + 5);

                HSSFCell cellA1 = row1.createCell((short) 0);
                cellA1.setCellValue("Total");
                HSSFCell cellB1 = row1.createCell((short) 1);
                cellB1.setCellValue("");
                HSSFCell cellC1 = row1.createCell((short) 2);
                cellC1.setCellValue(filesizeTotal);
                HSSFCell cellD1 = row1.createCell((short) 3);
                cellD1.setCellValue(inboundTotal);
                HSSFCell cellE1 = row1.createCell((short) 4);
                cellE1.setCellValue(outboundTotal);
                HSSFCell cellF1 = row1.createCell((short) 5);
                cellF1.setCellValue(total);
                worksheet.addMergedRegion(new CellRangeAddress(
                        list.size() + 5, //first row (0-based)
                        list.size() + 5, //last row  (0-based)
                        0, //first column (0-based)
                        1 //last column  (0-based)
                ));

                worksheet.autoSizeColumn((short) 0);
                worksheet.autoSizeColumn((short) 1);
                worksheet.autoSizeColumn((short) 2);
                worksheet.autoSizeColumn((short) 3);
                worksheet.autoSizeColumn((short) 4);
                worksheet.autoSizeColumn((short) 5);
                worksheet.autoSizeColumn((short) 6);
                worksheet.autoSizeColumn((short) 7);
                worksheet.autoSizeColumn((short) 8);
                worksheet.autoSizeColumn((short) 9);

            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    public String gridDownloadforLtReports() throws ServiceLocatorException, SQLException {
        String userRoleId = httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID).toString();
        StringBuffer documentSearchQuery = new StringBuffer();
        String filePath = "";
        StringBuffer sb = null;
        Connection connection = null;
        PreparedStatement preStmt = null;
        ResultSet resultSet = null;
        HashMap map = null;
        String tmp_Recieved_From = "";
        String tmp_Recieved_ToTime = "";
        dateTo = getDateTo();
        dateFrom = getDateFrom();
        senderId = getSenderId();
        senderName = getSenderName();
        receiverId = getReceiverId();
        receiverName = getReceiverName();

        if (getDocType() != null && !getDocType().equals("-1")) {
            docType = getDocType();
        }
        System.out.println("The doctype  download method " + docType);
        status = getStatus();
        ackStatus = getAckStatus();
        List finalList = new ArrayList();
        Date date = new Date();
        try {
            System.out.println("The Logistic Reports  In try block");
            File file = new File(Properties.getProperty("mscvp.logisticsDocCreationPath"));
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + File.separator + "logisticsDoc.xlsx");
            System.out.println("The Logistics DocReports  before Query");
            documentSearchQuery.append("SELECT (FILES.FILE_ID) as FILE_ID,"
                    + "FILES.ISA_NUMBER as ISA_NUMBER,FILES.FILE_TYPE as FILE_TYPE,FILES.CARRIER_STATUS,FILES.SEC_KEY_TYPE,"
                    + "FILES.FILE_ORIGIN as FILE_ORIGIN,FILES.TRANSACTION_TYPE as TRANSACTION_TYPE,trsrsp.RESPONSE_STATUS,FILES.TRANSACTION_PURPOSE, "
                    + "FILES.DIRECTION as DIRECTION,FILES.DATE_TIME_RECEIVED as DATE_TIME_RECEIVED,"
                    + "FILES.STATUS as STATUS,FILES.ACK_STATUS as ACK_STATUS,TP2.NAME as RECEIVER_NAME,TP1.NAME as SENDER_NAME,FILES.SENDER_ID,FILES.RECEIVER_ID,"
                    + "FILES.SEC_KEY_VAL,FILES.REPROCESSSTATUS,FILES.FILENAME,FILES.PRI_KEY_VAL FROM FILES "
                    + "LEFT OUTER JOIN TRANSPORT_LT_RESPONSE trsrsp on (trsrsp.FILE_ID=FILES.FILE_ID)"
                    + " LEFT OUTER JOIN TP TP1 ON (TP1.ID=FILES.TMW_SENDERID) "
                    + " LEFT OUTER JOIN TP TP2 ON (TP2.ID=FILES.TMW_RECEIVERID)"
                    + " LEFT OUTER JOIN TP TP3 ON (TP3.ID=FILES.SENDER_ID)"
                    + " LEFT OUTER JOIN TP TP4 ON (TP4.ID=FILES.RECEIVER_ID)");
            documentSearchQuery.append(" WHERE 1=1 AND FLOWFLAG LIKE '%L%'");
            if (userRoleId.equals("102")) {
                documentSearchQuery.append(" AND FILES.TRANSACTION_TYPE !='210' ");
            } else if (userRoleId.equals("103")) {
                documentSearchQuery.append(" AND FILES.TRANSACTION_TYPE ='210' ");

            }

            if (docType != null && !"-1".equals(docType.trim())) {
                documentSearchQuery.append(WildCardSql.getWildCardSql1("FILES.TRANSACTION_TYPE",
                        docType.trim()));
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

            if (receiverId != null && !"".equals(receiverId.trim())) {
                documentSearchQuery.append(" AND (FILES.RECEIVER_ID like '%" + receiverId + "%' OR FILES.TMW_RECEIVERID like '%" + receiverId + "%')");
            }

            if (senderId != null && !"".equals(senderId.trim())) {
                documentSearchQuery.append(" AND (FILES.SENDER_ID like '%" + senderId + "%' OR FILES.TMW_SENDERID like '%" + senderId + "%')");
            }

            if (senderName != null && !"".equals(senderName.trim())) {

                documentSearchQuery.append(" AND (TP3.NAME like '%" + senderName + "%' OR TP1.NAME like '%" + senderName + "%')");

            }

            if (receiverName != null && !"".equals(receiverName.trim())) {
                documentSearchQuery.append(" AND (TP4.NAME like '%" + receiverName + "%' OR TP2.NAME like '%" + receiverName + "%')");

            }

            if (dateFrom != null && !"".equals(dateFrom)) {
                tmp_Recieved_From = DateUtility.getInstance().DateViewToDBCompare(dateFrom);
                System.out.println("tmp_Recieved_From---inDoc---->" + tmp_Recieved_From);
                documentSearchQuery.append(" AND FILES.DATE_TIME_RECEIVED >= '" + tmp_Recieved_From
                        + "'");
            }
            if (dateTo != null && !"".equals(dateTo)) {
                tmp_Recieved_ToTime = DateUtility.getInstance().DateViewToDBCompare(dateTo);
                System.out.println("tmp_Recieved_ToTime-----ININV ---->" + tmp_Recieved_ToTime);
                documentSearchQuery.append(" AND FILES.DATE_TIME_RECEIVED <= '" + tmp_Recieved_ToTime
                        + "'");
            }

            documentSearchQuery.append(" order by DATE_TIME_RECEIVED DESC");
            System.out.println("Logistics reports queryquery prasad-->" + documentSearchQuery.toString());
            String searchQuery = documentSearchQuery.toString();

            int j = 1;
            connection = ConnectionProvider.getInstance().getConnection();
            Statement statement = null;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(searchQuery);
            String InvoiceDate = "";
            String Partner = null;
            String TransactionStatus = "";
            String invoicenum = "";
            String shipmentNum = "";
            while (resultSet.next()) {
                String InstanceId = resultSet.getString("FILE_ID");
                String Direction = resultSet.getString("DIRECTION");
                if (Direction != null && Direction.equalsIgnoreCase("")) {
                    if (Direction.equalsIgnoreCase("INBOUND")) {
                        Partner = resultSet.getString("SENDER_NAME");
                    }
                    if (Direction.equalsIgnoreCase("OUTBOUND")) {
                        Partner = resultSet.getString("RECEIVER_NAME");
                    }
                }
                String FileType = resultSet.getString("FILE_TYPE");
                String TransactionType = resultSet.getString("TRANSACTION_TYPE");
                String sektype = resultSet.getString("SEC_KEY_TYPE");
                if (TransactionType != null) {
                    if ((TransactionType.equalsIgnoreCase("214")) || (TransactionType.equalsIgnoreCase("990"))) {
                        if (sektype.equalsIgnoreCase("ORDERNUMBER")) {
                            invoicenum = resultSet.getString("SEC_KEY_VAL");
                        }

                    } //                            else if(trans.equalsIgnoreCase("990")){
                    //                                  if(sektype.equalsIgnoreCase("ORDERNUMBER")){
                    //                                   logisticsreportBean.setOrderNum(resultSet.getString("SEC_KEY_VAL"));  
                    //                                }
                    //                            } 
                    else if (TransactionType.equalsIgnoreCase("210")) {
                        if (sektype.equalsIgnoreCase("IN")) {
                            invoicenum = resultSet.getString("SEC_KEY_VAL");
                        }

                    } else if (TransactionType.equalsIgnoreCase("204")) {

                        invoicenum = resultSet.getString("PRI_KEY_VAL");

                    }

                    if (TransactionType.equalsIgnoreCase("204")) {
                        TransactionStatus = resultSet.getString("TRANSACTION_PURPOSE");
                    } else if (TransactionType.equalsIgnoreCase("214")) {
                        TransactionStatus = resultSet.getString("CARRIER_STATUS");
                    } else if (TransactionType.equalsIgnoreCase("990")) {
                        TransactionStatus = resultSet.getString("RESPONSE_STATUS");
                    }
                }
                if (TransactionType != null) {

                    if (TransactionType.equalsIgnoreCase("204")) {

                        if (TransactionStatus == null) {
                            TransactionStatus = "--";
                        } else if (TransactionStatus.equalsIgnoreCase("N")) {

                            TransactionStatus = "New Order";
                        } else if (TransactionStatus.equalsIgnoreCase("U")) {
                            TransactionStatus = "Update";
                        } else if (TransactionStatus.equalsIgnoreCase("C")) {
                            TransactionStatus = "Cancel";
                        } else {
                            TransactionStatus = "-";
                        }

                    } else if (TransactionType.equalsIgnoreCase("214")) {
                        if (TransactionStatus == null) {
                            TransactionStatus = "--";
                        } else if (TransactionStatus.equalsIgnoreCase("AA")) {

                            TransactionStatus = TransactionStatus + "_pick_up appointment";
                        } else if (TransactionStatus.equalsIgnoreCase("AB")) {
                            TransactionStatus = TransactionStatus + "_Delivery appointment";
                        } else if (TransactionStatus.equalsIgnoreCase("X3")) {
                            TransactionStatus = TransactionStatus + "_Arrived at Pick_up Location";
                        } else if (TransactionStatus.equalsIgnoreCase("AF")) {
                            TransactionStatus = TransactionStatus + "_Departed from pick_up Location";
                        } else if (TransactionStatus.equalsIgnoreCase("X1")) {
                            TransactionStatus = TransactionStatus + "_Arrived at Delivery Location";
                        } else if (TransactionStatus.equalsIgnoreCase("D1")) {
                            TransactionStatus = TransactionStatus + "_Completed Unloading Delivery Location";
                        } else if (TransactionStatus.equalsIgnoreCase("CD")) {
                            TransactionStatus = TransactionStatus + "_Carrier Departed Delivery Location";
                        } else {
                            TransactionStatus = "-";
                        }
                    } else if (TransactionType.equalsIgnoreCase("990")) {
                        if (TransactionStatus == null) {
                            TransactionStatus = "--";
                        } else if (TransactionStatus.equalsIgnoreCase("A")) {

                            TransactionStatus = "Accept";
                        } else if (TransactionStatus.equalsIgnoreCase("D")) {
                            TransactionStatus = "Decline";
                        } else if (TransactionStatus.equalsIgnoreCase("C")) {
                            TransactionStatus = "Cancel";
                        } else {
                            TransactionStatus = "---";
                        }

                    } else if (TransactionType.equalsIgnoreCase("210")) {
                        if (TransactionStatus == null) {
                            TransactionStatus = "----";
                        } else {
                            TransactionStatus = TransactionStatus;
                        }
                    } else {
                        TransactionStatus = "----";
                    }

                }

                Timestamp dt = resultSet.getTimestamp("DATE_TIME_RECEIVED");
                String DateTime = dt.toString().substring(0, dt.toString().lastIndexOf(":"));
                //String TransactionType = resultSet.getString("TRANSACTION_TYPE");
                String Status = resultSet.getString("STATUS");

                map = new HashMap();
                map.put("SNO", String.valueOf(j));
                map.put("InstanceId", InstanceId);
                map.put("Direction", Direction);
                map.put("Partner", Partner);
                map.put("FileType", FileType);
                map.put("DateTime", DateTime);
                map.put("TransactionType", TransactionType);
                map.put("Status", Status);
                map.put("TransactionStatus", TransactionStatus);
                map.put("invoicenum", invoicenum);
                finalList.add(map);
                j++;
            }
            if (finalList.size() > 0) {
                filePath = file.getAbsolutePath() + Properties.getProperty("os.compatability") + "logisticsDoc.xlsx";
                XSSFWorkbook xssfworkbook = new XSSFWorkbook();
                XSSFSheet sheet = xssfworkbook.createSheet("Logistics DocRepository");
                XSSFCellStyle cs = xssfworkbook.createCellStyle();
                cs.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                cs.setAlignment(XSSFCellStyle.ALIGN_LEFT);
                cs.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                cs.setBorderTop((short) 1); // single line border
                cs.setBorderBottom((short) 1); // single line border

                XSSFCellStyle headercs = xssfworkbook.createCellStyle();
                headercs.setFillForegroundColor(IndexedColors.AQUA.index);
                headercs.setAlignment(XSSFCellStyle.ALIGN_CENTER);
                headercs.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                headercs.setBorderTop((short) 1); // single line border
                headercs.setBorderBottom((short) 1); // single line border

                XSSFCellStyle cs1 = xssfworkbook.createCellStyle();
                cs1.setFillForegroundColor(IndexedColors.GREEN.index);
                cs1.setAlignment(XSSFCellStyle.ALIGN_LEFT);
                cs1.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                cs1.setBorderTop((short) 1); // single line border
                cs1.setBorderBottom((short) 1); // single line border

                XSSFCellStyle cs2 = xssfworkbook.createCellStyle();
                cs2.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.index);
                cs2.setAlignment(XSSFCellStyle.ALIGN_LEFT);
                cs2.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                cs2.setBorderTop((short) 1); // single line border
                cs2.setBorderBottom((short) 1); // single line border

                XSSFCellStyle cs3 = xssfworkbook.createCellStyle();
                cs3.setFillForegroundColor(IndexedColors.LIME.index);
                cs3.setAlignment(XSSFCellStyle.ALIGN_LEFT);
                cs3.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                cs3.setBorderTop((short) 1); // single line border
                cs3.setBorderBottom((short) 1); // single line border

                XSSFCellStyle cellStyle = xssfworkbook.createCellStyle();
                XSSFCellStyle cellStyle1 = xssfworkbook.createCellStyle();
                XSSFCellStyle cellStyle2 = xssfworkbook.createCellStyle();
                XSSFCellStyle cellStyle3 = xssfworkbook.createCellStyle();
                XSSFCellStyle cellStyleHead = xssfworkbook.createCellStyle();
                XSSFFont font1 = xssfworkbook.createFont();
                XSSFFont font2 = xssfworkbook.createFont();
                XSSFFont font3 = xssfworkbook.createFont();
                XSSFFont fontHead = xssfworkbook.createFont();

                XSSFDataFormat df = xssfworkbook.createDataFormat();
                XSSFRow row = sheet.createRow((int) 0);
                XSSFCell cell = row.createCell((short) 0);
                XSSFCell cell1 = row.createCell((short) 1);
                XSSFCell cell2 = row.createCell((short) 2);
                XSSFCell cell3 = row.createCell((short) 3);
                XSSFCell cell4 = row.createCell((short) 4);
                XSSFCell cell5 = row.createCell((short) 5);
                XSSFCell cell6 = row.createCell((short) 6);
                XSSFCell cell7 = row.createCell((short) 7);
                XSSFCell cell8 = row.createCell((short) 8);
                XSSFCell cell9 = row.createCell((short) 9);

                XSSFRow row1 = sheet.createRow((int) 1);
                Cell cellX = row1.createCell((short) 1);
                cellX.setCellValue("logisticsDoc:-Created Date : " + (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate());
                font1.setColor(IndexedColors.GREEN.index);
                cellStyle1.setFont(font1);
                cellX.setCellStyle(cellStyle1);
                sheet.addMergedRegion(CellRangeAddress.valueOf("B2:F2"));

//gnr
                XSSFRow row2 = sheet.createRow((int) 2);
                Cell cel2 = row2.createCell((short) 2);
                cel2.setCellValue("");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(XSSFCellStyle.ALIGN_CENTER);
                cel2.setCellStyle(cellStyleHead);
                sheet.addMergedRegion(CellRangeAddress.valueOf("C2:F2"));

                XSSFRow row3 = sheet.createRow((int) 3);
                Cell cel3 = row3.createCell((short) 0);
                cel3.setCellValue("Search Criteria :");
                cellStyleHead.setFont(fontHead);
                cellStyleHead.setAlignment(XSSFCellStyle.ALIGN_CENTER);
                cel2.setCellStyle(cellStyleHead);

                row = sheet.createRow((int) 4);
                XSSFCell cella31 = row.createCell((short) 1);
                cella31.setCellValue("DateFrom");
                cella31.setCellStyle(cs2);
                XSSFCell cellb31 = row.createCell((short) 2);
                if (("").equals(getDateFrom()) || (getDateFrom() == null)) {
                    cellb31.setCellValue("--");
                } else {
                    cellb31.setCellValue(getDateFrom());
                }

                XSSFCell cellc31 = row.createCell((short) 4);
                cellc31.setCellValue("DateTo");
                cellc31.setCellStyle(cs2);
                XSSFCell celld31 = row.createCell((short) 5);
                if (("").equals(getDateTo()) || (getDateTo() == null)) {
                    celld31.setCellValue("--");
                } else {
                    celld31.setCellValue(getDateTo());
                }

                row = sheet.createRow((int) 5);

                XSSFCell cella41 = row.createCell((short) 1);
                cella41.setCellValue("SenderId");
                cella41.setCellStyle(cs2);
                XSSFCell cellb41 = row.createCell((short) 2);
                if (("-1").equals(getSenderId()) || (getSenderId() == null)) {
                    cellb41.setCellValue("--");
                } else {
                    cellb41.setCellValue(getSenderId());
                }

                XSSFCell cellc41 = row.createCell((short) 4);
                cellc41.setCellValue("SenderName");
                cellc41.setCellStyle(cs2);
                XSSFCell celld41 = row.createCell((short) 5);
                if (("-1").equals(getSenderName()) || (getSenderName() == null)) {
                    celld41.setCellValue("--");
                } else {
                    celld41.setCellValue(getSenderName());
                }

                row = sheet.createRow((int) 6);

                XSSFCell cella51 = row.createCell((short) 1);
                cella51.setCellValue("RecieverId");
                cella51.setCellStyle(cs2);
                XSSFCell cellb51 = row.createCell((short) 2);
                if (("-1").equals(getReceiverId()) || (getReceiverId() == null)) {
                    cellb51.setCellValue("--");
                } else {
                    cellb51.setCellValue(getReceiverId());
                }

                XSSFCell cellc51 = row.createCell((short) 4);
                cellc51.setCellValue("RecieverName");
                cellc51.setCellStyle(cs2);
                XSSFCell celld51 = row.createCell((short) 5);
                if (("-1").equals(getReceiverName()) || (getReceiverName() == null)) {
                    celld51.setCellValue("--");
                } else {
                    celld51.setCellValue(getReceiverName());
                }

                row = sheet.createRow((int) 7);

                XSSFCell cella61 = row.createCell((short) 1);
                cella61.setCellValue("Correlation");
                cella61.setCellStyle(cs2);
                XSSFCell cellb61 = row.createCell((short) 2);
                if (("-1").equals(getCorrattribute()) || (getCorrattribute() == null)) {
                    cellb61.setCellValue("--");
                } else {
                    cellb61.setCellValue(getCorrattribute());
                }

                XSSFCell cellc61 = row.createCell((short) 4);
                cellc61.setCellValue("Value");
                cellc61.setCellStyle(cs2);
                XSSFCell celld61 = row.createCell((short) 5);
                if (("").equals(getCorrvalue()) || (getCorrvalue() == null)) {
                    celld61.setCellValue("--");
                } else {
                    celld61.setCellValue(getCorrvalue());
                }

                row = sheet.createRow((int) 8);

                XSSFCell cella71 = row.createCell((short) 1);
                cella71.setCellValue("Correlation");
                cella71.setCellStyle(cs2);
                XSSFCell cellb71 = row.createCell((short) 2);
                if (("-1").equals(getCorrattribute1()) || (getCorrattribute1() == null)) {
                    cellb71.setCellValue("--");
                } else {
                    cellb71.setCellValue(getCorrattribute1());
                }

                XSSFCell cellc71 = row.createCell((short) 4);
                cellc71.setCellValue("Value");
                cellc71.setCellStyle(cs2);
                XSSFCell celld71 = row.createCell((short) 5);
                if (("").equals(getCorrvalue1()) || (getCorrvalue1() == null)) {
                    celld71.setCellValue("--");
                } else {
                    celld71.setCellValue(getCorrvalue1());
                }

                row = sheet.createRow((int) 9);

                XSSFCell cella81 = row.createCell((short) 1);
                cella81.setCellValue("Correlation");
                cella81.setCellStyle(cs2);
                XSSFCell cellb81 = row.createCell((short) 2);
                if (("-1").equals(getCorrattribute2()) || (getCorrattribute2() == null)) {
                    cellb81.setCellValue("--");
                } else {
                    cellb81.setCellValue(getCorrattribute2());
                }

                XSSFCell cellc81 = row.createCell((short) 4);
                cellc81.setCellValue("Value");
                cellc81.setCellStyle(cs2);
                XSSFCell celld81 = row.createCell((short) 5);
                if (("").equals(getCorrvalue2()) || (getCorrvalue2() == null)) {
                    celld81.setCellValue("--");
                } else {
                    celld81.setCellValue(getCorrvalue2());
                }
                row = sheet.createRow((int) 10);
                XSSFCell cella91 = row.createCell((short) 1);
                cella91.setCellValue("DocType");
                cella91.setCellStyle(cs2);
                XSSFCell cellb91 = row.createCell((short) 2);
                if (("-1").equals(getDocType()) || (getDocType() == null)) {
                    cellb91.setCellValue("--");
                } else {
                    cellb91.setCellValue(getDocType());
                }
                XSSFCell cellc91 = row.createCell((short) 4);
                cellc91.setCellValue("Status");
                cellc91.setCellStyle(cs2);
                XSSFCell celld91 = row.createCell((short) 5);
                if (("-1").equals(getStatus()) || (getStatus() == null)) {
                    celld91.setCellValue("--");
                } else {
                    celld91.setCellValue(getStatus());
                }
                row = sheet.createRow((int) 11);
                XSSFCell cella1 = row.createCell((short) 0);
                cella1.setCellValue("SNO");
                cella1.setCellStyle(cs2);
                XSSFCell cellb1 = row.createCell((short) 1);
                cellb1.setCellValue("InstanceId");
                cellb1.setCellStyle(cs2);
                XSSFCell cellc1 = row.createCell((short) 2);
                cellc1.setCellValue("Direction");
                cellc1.setCellStyle(cs2);
                XSSFCell celld1 = row.createCell((short) 3);
                celld1.setCellValue("Partner");
                celld1.setCellStyle(cs2);
                XSSFCell celle1 = row.createCell((short) 4);
                celle1.setCellValue("FileType");
                celle1.setCellStyle(cs2);
                XSSFCell cellg1 = row.createCell((short) 5);
                cellg1.setCellValue("DateTime");
                cellg1.setCellStyle(cs2);
                XSSFCell cellh1 = row.createCell((short) 6);
                cellh1.setCellValue("TransactionType");
                cellh1.setCellStyle(cs2);
                XSSFCell celli1 = row.createCell((short) 7);
                celli1.setCellValue("Status");
                celli1.setCellStyle(cs2);
                XSSFCell cellf1 = row.createCell((short) 8);
                cellf1.setCellValue("TranStatus");
                cellf1.setCellStyle(cs2);
                XSSFCell cellj1 = row.createCell((short) 9);
                cellj1.setCellValue("DocNumber");
                cellj1.setCellStyle(cs2);
                int count1 = 0;
                if (finalList.size() > 0) {
                    Map docRepMap = null;
                    for (int i = 0; i < finalList.size(); i++) {
                        docRepMap = (Map) finalList.get(i);
                        row = sheet.createRow((int) i + 13);

                        cell = row.createCell((short) 0);
                        cell.setCellValue((String) docRepMap.get("SNO"));

                        cell1 = row.createCell((short) 1);
                        cell1.setCellValue((String) docRepMap.get("InstanceId"));

                        cell2 = row.createCell((short) 2);
                        cell2.setCellValue((String) docRepMap.get("Direction"));

                        cell3 = row.createCell((short) 3);
                        cell3.setCellValue((String) docRepMap.get("Partner"));

                        cell4 = row.createCell((short) 4);
                        cell4.setCellValue((String) docRepMap.get("FileType"));
                        cell5 = row.createCell((short) 5);
                        cell5.setCellValue((String) docRepMap.get("DateTime"));

                        cell6 = row.createCell((short) 6);
                        cell6.setCellValue(((String) docRepMap.get("TransactionType")));

                        cell7 = row.createCell((short) 7);
                        cell7.setCellValue(((String) docRepMap.get("Status")));
                        cell8 = row.createCell((short) 8);
                        cell8.setCellValue(((String) docRepMap.get("TransactionStatus")));
                        cell9 = row.createCell((short) 9);
                        cell9.setCellValue(((String) docRepMap.get("invoicenum")));

                        count1 = count1 + 1;
                    }
                    row = sheet.createRow((int) count1 + 14);
                    XSSFCell cellaz1 = row.createCell((short) 9);
                    cellaz1.setCellValue("Total : ");
                    cellaz1.setCellStyle(cs2);
                    XSSFCell cellbz1 = row.createCell((short) 10);
                    cellbz1.setCellValue(count1);
                }
                sheet.autoSizeColumn((short) 0);
                sheet.autoSizeColumn((short) 1);
                sheet.autoSizeColumn((short) 2);
                sheet.autoSizeColumn((short) 3);
                sheet.autoSizeColumn((short) 4);
                sheet.autoSizeColumn((short) 5);
                sheet.autoSizeColumn((short) 6);
                sheet.autoSizeColumn((short) 7);
                sheet.autoSizeColumn((short) 8);
                sheet.autoSizeColumn((short) 9);
                // sheet.autoSizeColumn((short) 9);
                xssfworkbook.write(fileOut);
                fileOut.flush();
                fileOut.close();
            }

        } catch (FileNotFoundException fne) {
            fne.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preStmt != null) {
                    preStmt.close();
                    preStmt = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
        System.out.println("path=" + filePath);
        return filePath;
    }

    /**
     * @return the downloadType
     */
    public String getDownloadType() {
        return downloadType;
    }

    /**
     * @param downloadType the downloadType to set
     */
    public void setDownloadType(String downloadType) {
        this.downloadType = downloadType;
    }

    /**
     * @return the sheetType
     */
    public String getSheetType() {
        return sheetType;
    }

    /**
     * @param sheetType the sheetType to set
     */
    public void setSheetType(String sheetType) {
        this.sheetType = sheetType;
    }

    public String getReportattachment() {
        return reportattachment;
    }

    public void setReportattachment(String reportattachment) {
        this.reportattachment = reportattachment;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
