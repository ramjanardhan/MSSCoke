/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.logisticsinvoice;

import com.mss.ediscv.util.AppConstants;
import com.mss.ediscv.util.AuthorizationManager;
import com.mss.ediscv.util.DataSourceDataProvider;
import com.mss.ediscv.util.DateUtility;
import com.mss.ediscv.util.ServiceLocator;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author miracle
 */
public class LogisticsInvoiceAction extends ActionSupport implements ServletRequestAware {

    private HttpServletRequest httpServletRequest;
    private String resultType;
    private String sqlQuery;
    private String docSearchQuery;
    private String submitFrm;
    private String currentDsnName;
    private String datepickerfrom;
    private String datepickerTo;
    private String invSenderId;
    private String invSenderName;
    private String invRecId;
    private String invRecName;
    private String sampleValue;
    private String docType;
    //private String check;
    private String corrattribute;
    private String corrvalue;
    private String corrattribute1;
    private String corrvalue1;

    private String status;
    private String ackStatus;
    private List correlationList;
    private Map docTypeMap;
    private List docTypeList;
    private String tmwSenderid;
    private String tmwReceivererid;

    private List pageList;
    private int startValue;
    private int endValue;

    private List<LogisticsInvoiceBean> ltInvoiceList;
    private static Logger logger = Logger.getLogger(LogisticsInvoiceAction.class
            .getName());

    public String execute() throws Exception {
        logger.info("Entered into the ::::LogisticsInvoiceAction :::: prepare ");
        List corrList;
        List docList;
        setResultType(LOGIN);
        if (httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
            //setResultType("accessFailed");
            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID).toString());
            //if(AuthorizationManager.getInstance().isAuthorizedUser("L_INVOICE",userRoleId)){  
            String defaultFlowId = httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_DEFAULT_FLOWID).toString();
            String defaultFlowName = DataSourceDataProvider.getInstance().getFlowNameByFlowID(defaultFlowId);
            httpServletRequest.getSession(false).removeAttribute(AppConstants.SES_LTRESPONSE_LIST);
            httpServletRequest.getSession(false).removeAttribute(AppConstants.SES_LOG_DOC_LIST);
            httpServletRequest.getSession(false).removeAttribute(AppConstants.SES_LOGSHIPMENT_LIST);
            httpServletRequest.getSession(false).removeAttribute(AppConstants.SES_LOAD_LIST);
            httpServletRequest.getSession(false).removeAttribute(AppConstants.SES_LTINVOICE_LIST);
            httpServletRequest.getSession(false).removeAttribute("searchString");
            httpServletRequest.getSession(false).removeAttribute("gridSize");
            httpServletRequest.getSession(false).removeAttribute("noOfPages");
            corrList = DataSourceDataProvider.getInstance().getCorrelationNames(4, 2);
            docList = DataSourceDataProvider.getInstance().getDocumentTypeList(4, 2);
            //setDatepickerTo(DateUtility.getInstance().getCurrentMySqlDateTime1());
            setCorrelationList(corrList);
            setDocTypeList(docList);
            if (!defaultFlowName.equals("Logistics")) {
                defaultFlowId = DataSourceDataProvider.getInstance().getFlowIdByFlowName("Logistics");
                httpServletRequest.getSession(false).setAttribute(AppConstants.SES_USER_DEFAULT_FLOWID, defaultFlowId);
            }
            setResultType(SUCCESS);
            //}
        }

        logger.info("End of ::::LogisticsInvoiceAction :::: prepare ");
        return getResultType();
    }

    public String getInvoiceSearchQuery() throws Exception {
        // System.out.println("getQuery---");
        int gridSize;
        int strStartGrid = 0;
        int strEndGrid = 0;
        setResultType(LOGIN);
        if (httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
            //setResultType("accessFailed");
            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID).toString());
            //if(AuthorizationManager.getInstance().isAuthorizedUser("L_INVOICE",userRoleId)){  
            try {
                if (logger.isDebugEnabled()) {
                    logger
                            .info("Entered into the ::::SearchLogisticsInvoiceAction :::: getInvoiceSearchQuery ");
                }
                execute();
                HttpSession session = httpServletRequest.getSession(true);
                httpServletRequest.getSession(false).removeAttribute(AppConstants.SES_LTRESPONSE_LIST);
                session.removeAttribute("searchString");
                session.removeAttribute("gridSize");
                session.removeAttribute("noOfPages");
                ltInvoiceList = ServiceLocator.getLogInvoiceService().buildLogInvoiceQuery(this, httpServletRequest);
                httpServletRequest.getSession(false).setAttribute(AppConstants.SES_LTINVOICE_LIST, ltInvoiceList);
                System.out.println("list size-----" + ltInvoiceList.size());
                if (ltInvoiceList.size() > 0) {
                    gridSize = ltInvoiceList.size();
                    System.out.println("searchResult list size:::" + gridSize);
                    session.setAttribute("gridSize", gridSize);
                    //System.out.println("grid size::::::::"+session.getAttribute("gridSize"));
                    List currentPageList = new ArrayList();
                    if (ltInvoiceList.size() < 10) {
                        strStartGrid = 0;
                        httpServletRequest.setAttribute("strStartGrid", strStartGrid);
                        strEndGrid = ltInvoiceList.size();
                        httpServletRequest.setAttribute("strEndGrid", strEndGrid);
                    } else {
                        strStartGrid = 0;
                        httpServletRequest.setAttribute("strStartGrid", strStartGrid);
                        strEndGrid = 10;
                        httpServletRequest.setAttribute("strEndGrid", strEndGrid);

                    }
                    int noOfPages = 0;

                    System.out.println("ltInvoiceList.size()-->" + ltInvoiceList.size());

                    if (ltInvoiceList.size() % 10 == 0) {
                        noOfPages = ltInvoiceList.size() / 10;
                    } else {
                        noOfPages = (ltInvoiceList.size() / 10) + 1;
                    }

                    session.setAttribute("noOfPages", noOfPages);
                    for (int i = 1; i <= noOfPages; i++) {
                        currentPageList.add(i);
                    }

                    setPageList(currentPageList);
                    resultType = SUCCESS;
                } else {
                    strStartGrid = 0;
                    httpServletRequest.setAttribute("strStartGrid", strStartGrid);
                    strEndGrid = 0;
                    httpServletRequest.setAttribute("strEndGrid", strEndGrid);
                    resultType = SUCCESS;
                    //setResultMessage("<font color=\"red\" size=\"1.5\">Sorry! Please Try Search Again !</font>");
                }
                //  System.out.println("value"+getSampleValue());
                setResultType(SUCCESS);

            } catch (Exception ex) {
                if (logger.isDebugEnabled()) {
                    logger
                            .error("problem :: "
                                    + ex.getMessage()
                                    + " method name :: getInvoiceSearchQuery() :: class name :: "
                                    + getClass().getName());
                }
                httpServletRequest.getSession(false).setAttribute(
                        AppConstants.REQ_EXCEPTION_MSG, ex.getMessage());
                setResultType("error");
            }
            //}
        }
        logger.info("End of ::::SearchLogisticsInvoiceAction :::: getInvoiceSearchQuery ");
        return getResultType();
    }

    public String doNextLtInvoice() {
        System.out.println("doNextLtReports------");
        int strStartGrid = 0;
        int strEndGrid = 0;
        int pageSize = 0;
        int gridSplit = 0;

        try {
            List corrList;
            List docList;
            corrList = DataSourceDataProvider.getInstance().getCorrelationNames(0, 2);
            docList = DataSourceDataProvider.getInstance().getDocumentTypeList(0, 2);
            setCorrelationList(corrList);
            setDocTypeList(docList);
            int gettxtStartGrid = Integer.parseInt(httpServletRequest.getParameter("startValue"));
            int gettxtEndGrid = Integer.parseInt(httpServletRequest.getParameter("endValue"));
            String buttonValue = httpServletRequest.getParameter("button");
            //System.out.println("button :::::::"+buttonValue);

            HttpSession session = httpServletRequest.getSession(true);
            String gridSize = session.getAttribute("gridSize").toString();
            int gridLength = Integer.parseInt(gridSize);

            //List documentList = (List) session.getAttribute("documentList");
            // System.out.println("doNextLtReports----size---"+documentList.size());
            List ltInvoiceList = (List) session.getAttribute("ltInvoiceList");
            System.out.println("doNextLtReports----size---" + ltInvoiceList.size());
            /*
             if(buttonValue.equalsIgnoreCase("Next")){
             strStartGrid = 151;
             strEndGrid = 175;
             
             httpServletRequest.setAttribute("strStartGrid",strStartGrid);
             httpServletRequest.setAttribute("strEndGrid",strEndGrid);
             }*/
            if (buttonValue.equalsIgnoreCase("Next")) {

                if (gridLength != gettxtEndGrid) {

                    gridSplit = gettxtEndGrid;
                    gridSplit = gridLength - gridSplit;

                    if (gridSplit >= 10) {
                        strStartGrid = gettxtStartGrid + 10;
                        strEndGrid = gettxtEndGrid + 10;
                    } else {
                        strStartGrid = gettxtStartGrid + 10;
                        strEndGrid = gettxtEndGrid + gridSplit;
                    }
                } else {
                    strStartGrid = 0;
                    strEndGrid = 0;
                }

                httpServletRequest.setAttribute("strStartGrid", strStartGrid);
                httpServletRequest.setAttribute("strEndGrid", strEndGrid);
            }

            if (buttonValue.equalsIgnoreCase("Previous")) {

                gridSplit = gettxtStartGrid;
                //System.out.println("gridSplit11:::::::"+gridSplit);
                gridSplit = gridLength - gridSplit;
                //System.out.println("gridSplit22:::::::"+gridSplit);

                /*
                 if(gridSplit >30){
                 strStartGrid = gettxtStartGrid - 30;
                 strEndGrid = gettxtEndGrid - 30;
                 }else{
                 strStartGrid = 0;
                 strEndGrid = 0;
                 }*/
                if (gridSplit > 0) {
                    strStartGrid = gettxtStartGrid - 10;
                    //strEndGrid = gettxtEndGrid - 30;
                    strEndGrid = strStartGrid + 10;
                }

                httpServletRequest.setAttribute("strStartGrid", strStartGrid);
                httpServletRequest.setAttribute("strEndGrid", strEndGrid);
            }

            if (buttonValue.equalsIgnoreCase("First")) {

                strStartGrid = 0;
                strEndGrid = strStartGrid + 10;

                httpServletRequest.setAttribute("strStartGrid", strStartGrid);
                httpServletRequest.setAttribute("strEndGrid", strEndGrid);
            }

            if (buttonValue.equalsIgnoreCase("Last")) {

                strStartGrid = gridLength - 10;
                //int pageNum = gridLength / 10;
                // strStartGrid = (pageNum * 10);
                strEndGrid = gridLength;
                System.out.println("strStartGrid-->" + strStartGrid);
                System.out.println("strEndGrid-->" + strEndGrid);
                httpServletRequest.setAttribute("strStartGrid", strStartGrid);
                httpServletRequest.setAttribute("strEndGrid", strEndGrid);
            }
            if (buttonValue.equalsIgnoreCase("Select")) {
                httpServletRequest.setAttribute("strStartGrid", getStartValue());
                if (ltInvoiceList.size() > getEndValue()) {
                    httpServletRequest.setAttribute("strEndGrid", getEndValue());
                } else {
                    httpServletRequest.setAttribute("strEndGrid", ltInvoiceList.size());
                }
            }
            List currentPageList = new ArrayList();
            int noOfPages = 0;

            if (ltInvoiceList.size() % 10 == 0) {
                noOfPages = ltInvoiceList.size() / 10;
            } else {
                noOfPages = (ltInvoiceList.size() / 10) + 1;
            }

            session.setAttribute("noOfPages", noOfPages);
            for (int i = 1; i <= noOfPages; i++) {
                currentPageList.add(i);
            }

            setPageList(currentPageList);
            //  execute();

        } catch (Exception ex) {
            ex.printStackTrace();
            httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
            resultType = ERROR;
        }

        return "success";
    }

    @Override

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * @return the resultType
     */
    public String getResultType() {
        return resultType;
    }

    /**
     * @param resultType the resultType to set
     */
    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    /**
     * @return the sqlQuery
     */
    public String getSqlQuery() {
        return sqlQuery;
    }

    /**
     * @param sqlQuery the sqlQuery to set
     */
    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    /**
     * @return the docSearchQuery
     */
    public String getDocSearchQuery() {
        return docSearchQuery;
    }

    /**
     * @param docSearchQuery the docSearchQuery to set
     */
    public void setDocSearchQuery(String docSearchQuery) {
        this.docSearchQuery = docSearchQuery;
    }

    /**
     * @return the submitFrm
     */
    public String getSubmitFrm() {
        return submitFrm;
    }

    /**
     * @param submitFrm the submitFrm to set
     */
    public void setSubmitFrm(String submitFrm) {
        this.submitFrm = submitFrm;
    }

    /**
     * @return the currentDsnName
     */
    public String getCurrentDsnName() {
        return currentDsnName;
    }

    /**
     * @param currentDsnName the currentDsnName to set
     */
    public void setCurrentDsnName(String currentDsnName) {
        this.currentDsnName = currentDsnName;
    }

    /**
     * @return the sampleValue
     */
    public String getSampleValue() {
        return sampleValue;
    }

    /**
     * @param sampleValue the sampleValue to set
     */
    public void setSampleValue(String sampleValue) {
        this.sampleValue = sampleValue;
    }

    /**
     * @return the docType
     */
    public String getDocType() {
        return docType;
    }

    /**
     * @param docType the docType to set
     */
    public void setDocType(String docType) {
        this.docType = docType;
    }

    /**
     * @return the corrattribute
     */
    public String getCorrattribute() {
        return corrattribute;
    }

    /**
     * @param corrattribute the corrattribute to set
     */
    public void setCorrattribute(String corrattribute) {
        this.corrattribute = corrattribute;
    }

    /**
     * @return the corrvalue
     */
    public String getCorrvalue() {
        return corrvalue;
    }

    /**
     * @param corrvalue the corrvalue to set
     */
    public void setCorrvalue(String corrvalue) {
        this.corrvalue = corrvalue;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the ackStatus
     */
    public String getAckStatus() {
        return ackStatus;
    }

    /**
     * @param ackStatus the ackStatus to set
     */
    public void setAckStatus(String ackStatus) {
        this.ackStatus = ackStatus;
    }

    /**
     * @return the correlationList
     */
    public List getCorrelationList() {
        return correlationList;
    }

    /**
     * @param correlationList the correlationList to set
     */
    public void setCorrelationList(List correlationList) {
        this.correlationList = correlationList;
    }

    /**
     * @return the docTypeMap
     */
    public Map getDocTypeMap() {
        return docTypeMap;
    }

    /**
     * @param docTypeMap the docTypeMap to set
     */
    public void setDocTypeMap(Map docTypeMap) {
        this.docTypeMap = docTypeMap;
    }

    /**
     * @return the invSenderId
     */
    public String getInvSenderId() {
        return invSenderId;
    }

    /**
     * @param invSenderId the invSenderId to set
     */
    public void setInvSenderId(String invSenderId) {
        this.invSenderId = invSenderId;
    }

    /**
     * @return the invSenderName
     */
    public String getInvSenderName() {
        return invSenderName;
    }

    /**
     * @param invSenderName the invSenderName to set
     */
    public void setInvSenderName(String invSenderName) {
        this.invSenderName = invSenderName;
    }

    /**
     * @return the invReceiverId
     */
//    public String getInvReceiverId() {
//        return invReceiverId;
//    }
    /**
     * @param invReceiverId the invReceiverId to set
     */
//    public void setInvReceiverId(String invReceiverId) {
//        this.invReceiverId = invReceiverId;
//    }
    /**
     * @return the invReceiverName
     */
//    public String getInvReceiverName() {
//        return invReceiverName;
//    }
    /**
     * @param invReceiverName the invReceiverName to set
     */
//    public void setInvReceiverName(String invReceiverName) {
//        this.invReceiverName = invReceiverName;
//    }
    /**
     * @return the corrattribute1
     */
    public String getCorrattribute1() {
        return corrattribute1;
    }

    /**
     * @param corrattribute1 the corrattribute1 to set
     */
    public void setCorrattribute1(String corrattribute1) {
        this.corrattribute1 = corrattribute1;
    }

    /**
     * @return the corrvalue1
     */
    public String getCorrvalue1() {
        return corrvalue1;
    }

    /**
     * @param corrvalue1 the corrvalue1 to set
     */
    public void setCorrvalue1(String corrvalue1) {
        this.corrvalue1 = corrvalue1;
    }

    /**
     * @return the docTypeList
     */
    public List getDocTypeList() {
        return docTypeList;
    }

    /**
     * @param docTypeList the docTypeList to set
     */
    public void setDocTypeList(List docTypeList) {
        this.docTypeList = docTypeList;
    }

    /**
     * @return the datepickerfrom
     */
    public String getDatepickerfrom() {
        return datepickerfrom;
    }

    /**
     * @param datepickerfrom the datepickerfrom to set
     */
    public void setDatepickerfrom(String datepickerfrom) {
        this.datepickerfrom = datepickerfrom;
    }

    /**
     * @return the datepickerTo
     */
    public String getDatepickerTo() {
        return datepickerTo;
    }

    /**
     * @param datepickerTo the datepickerTo to set
     */
    public void setDatepickerTo(String datepickerTo) {
        this.datepickerTo = datepickerTo;
    }

    public String getInvRecName() {
        return invRecName;
    }

    public void setInvRecName(String invRecName) {
        this.invRecName = invRecName;
    }

    public String getInvRecId() {
        return invRecId;
    }

    public void setInvRecId(String invRecId) {
        this.invRecId = invRecId;
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

    public List getPageList() {
        return pageList;
    }

    public void setPageList(List pageList) {
        this.pageList = pageList;
    }

    public int getStartValue() {
        return startValue;
    }

    public void setStartValue(int startValue) {
        this.startValue = startValue;
    }

    public int getEndValue() {
        return endValue;
    }

    public void setEndValue(int endValue) {
        this.endValue = endValue;
    }
}
