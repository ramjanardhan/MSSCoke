/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.logisticsshipment;

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
public class LogisticsShipmentAction extends ActionSupport implements ServletRequestAware {

    private HttpServletRequest httpServletRequest;

    private String resultType;
    private String sqlQuery;
    private String docSearchQuery;
    private String submitFrm;
    private String currentDsnName;
    private String status;
    private String senderId;
    private String senderName;
    private String receiverId;
    private String receiverName;
    private String datepickerfrom;
    private String datepickerTo;
    private String ackStatus;
    private List correlationList;
    private String docType;
    private List docTypeList;
    private String corrattribute;
    private String corrvalue;
    private String corrattribute1;
    private String corrvalue1;
    private List pageList;
    private int startValue;
    private int endValue;
    private String database;

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
    private static Logger logger = Logger.getLogger(LogisticsShipmentAction.class
            .getName());

    private List<LtShipmentBean> ltShipmentList;

    public String execute() throws Exception {
        logger.info("Entered into the ::::LogisticsShipmentAction :::: execute ");
        List corrList;
        List docList;
        setResultType(LOGIN);
        if (httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
            //setResultType("accessFailed");
            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID).toString());
            //if(AuthorizationManager.getInstance().isAuthorizedUser("L_SHIPMENT",userRoleId)){  
            String defaultFlowId = httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_DEFAULT_FLOWID).toString();
            String defaultFlowName = DataSourceDataProvider.getInstance().getFlowNameByFlowID(defaultFlowId);
            httpServletRequest.getSession(false).removeAttribute(AppConstants.SES_LTRESPONSE_LIST);
            httpServletRequest.getSession(false).removeAttribute(AppConstants.SES_LOG_DOC_LIST);
            httpServletRequest.getSession(false).removeAttribute(AppConstants.SES_LOGSHIPMENT_LIST);
            httpServletRequest.getSession(false).removeAttribute(AppConstants.SES_LOAD_LIST);
            httpServletRequest.getSession(false).removeAttribute(AppConstants.SES_LTSHIPMENT_LIST);
            httpServletRequest.getSession(false).removeAttribute("searchString");
            httpServletRequest.getSession(false).removeAttribute("gridSize");
            httpServletRequest.getSession(false).removeAttribute("noOfPages");
            corrList = DataSourceDataProvider.getInstance().getCorrelationNames(3, 2);

            docList = DataSourceDataProvider.getInstance().getDocumentTypeList(3, 2);
            //setDatepickerTo(DateUtility.getInstance().getCurrentMySqlDateTime1());
            setCorrelationList(corrList);
            setDocTypeList(docList);
            if (!defaultFlowName.equals("Logistics")) {
                defaultFlowId = DataSourceDataProvider.getInstance().getFlowIdByFlowName("Logistics");
                httpServletRequest.getSession(false).setAttribute(AppConstants.SES_USER_DEFAULT_FLOWID, defaultFlowId);
            }
            if ("ARCHIVE".equals(getDatabase())) {
                setDatabase("ARCHIVE");
            } else {
                setDatabase("MSCVP");
            }
            setResultType(SUCCESS);
            //}
        }
        logger.info("End of ::::LogisticsShipmentAction :::: execute ");
        return getResultType();
    }

    public String getShipmentSearchList() throws Exception {
        int gridSize;
        int strStartGrid = 0;
        int strEndGrid = 0;
        setResultType(LOGIN);
        if (httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
            //setResultType("accessFailed");
            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID).toString());
            //if(AuthorizationManager.getInstance().isAuthorizedUser("L_SHIPMENT",userRoleId)){  
            try {
                if (logger.isDebugEnabled()) {
                    logger
                            .info("Entered into the ::::LogisticsShipmentAction :::: getShipmentSearchList ");
                }
                execute();
                HttpSession session = httpServletRequest.getSession(true);
                httpServletRequest.getSession(false).removeAttribute(AppConstants.SES_LTRESPONSE_LIST);
                session.removeAttribute("searchString");
                session.removeAttribute("gridSize");
                session.removeAttribute("noOfPages");
                if ("ARCHIVE".equals(getDatabase())) {
                    ltShipmentList = ServiceLocator.getLogShipmentService().getLtResponseArchiveList(this, httpServletRequest);
                } else {
                    ltShipmentList = ServiceLocator.getLogShipmentService().getLtResponseList(this, httpServletRequest);

                }
                httpServletRequest.getSession(false).setAttribute(AppConstants.SES_LTSHIPMENT_LIST, ltShipmentList);
                System.out.println("list size-----" + ltShipmentList.size());
                if (ltShipmentList.size() > 0) {
                    gridSize = ltShipmentList.size();
                    System.out.println("searchResult list size:::" + gridSize);
                    session.setAttribute("gridSize", gridSize);
                    //System.out.println("grid size::::::::"+session.getAttribute("gridSize"));
                    List currentPageList = new ArrayList();
                    if (ltShipmentList.size() < 10) {
                        strStartGrid = 0;
                        httpServletRequest.setAttribute("strStartGrid", strStartGrid);
                        strEndGrid = ltShipmentList.size();
                        httpServletRequest.setAttribute("strEndGrid", strEndGrid);
                    } else {
                        strStartGrid = 0;
                        httpServletRequest.setAttribute("strStartGrid", strStartGrid);
                        strEndGrid = 10;
                        httpServletRequest.setAttribute("strEndGrid", strEndGrid);

                    }
                    int noOfPages = 0;

                    System.out.println("searchResult.size()-->" + ltShipmentList.size());

                    if (ltShipmentList.size() % 10 == 0) {
                        noOfPages = ltShipmentList.size() / 10;
                    } else {
                        noOfPages = (ltShipmentList.size() / 10) + 1;
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
                                    + " method name :: getShipmentSearchList() :: class name :: "
                                    + getClass().getName());
                }
                httpServletRequest.getSession(false).setAttribute(
                        "exceptionMessage", ex.getMessage());
                setResultType(ERROR);
            }
            //}
        }
        logger.info("End of ::::LogisticsShipmentAction :::: getShipmentSearchList ");
        return getResultType();
    }

    public String doNextLtShipment() {

        int strStartGrid = 0;
        int strEndGrid = 0;
        int pageSize = 0;
        int gridSplit = 0;

        try {
            List corrList;
            List docList;
            corrList = DataSourceDataProvider.getInstance().getCorrelationNames(3, 2);
            docList = DataSourceDataProvider.getInstance().getDocumentTypeList(3, 2);
            setCorrelationList(corrList);
            setDocTypeList(docList);
             if ("ARCHIVE".equals(getDatabase())) {
                setDatabase("ARCHIVE");
            } else {
                setDatabase("MSCVP");
            }
            int gettxtStartGrid = Integer.parseInt(httpServletRequest.getParameter("startValue"));
            int gettxtEndGrid = Integer.parseInt(httpServletRequest.getParameter("endValue"));
            String buttonValue = httpServletRequest.getParameter("button");
            //System.out.println("button :::::::"+buttonValue);

            HttpSession session = httpServletRequest.getSession(true);
            String gridSize = session.getAttribute("gridSize").toString();
            int gridLength = Integer.parseInt(gridSize);

            List ltShipmentList = (List) session.getAttribute("ltShipmentList");

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
                if (ltShipmentList.size() > getEndValue()) {
                    httpServletRequest.setAttribute("strEndGrid", getEndValue());
                } else {
                    httpServletRequest.setAttribute("strEndGrid", ltShipmentList.size());
                }
            }
            List currentPageList = new ArrayList();
            int noOfPages = 0;

            if (ltShipmentList.size() % 10 == 0) {
                noOfPages = ltShipmentList.size() / 10;
            } else {
                noOfPages = (ltShipmentList.size() / 10) + 1;
            }

            session.setAttribute("noOfPages", noOfPages);
            for (int i = 1; i <= noOfPages; i++) {
                currentPageList.add(i);
            }

            setPageList(currentPageList);
            //  execute();

        } catch (Exception ex) {
            //ex.printStackTrace();
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
     * @return the senderId
     */
    public String getSenderId() {
        return senderId;
    }

    /**
     * @param senderId the senderId to set
     */
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    /**
     * @return the senderName
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * @param senderName the senderName to set
     */
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    /**
     * @return the receiverId
     */
    public String getReceiverId() {
        return receiverId;
    }

    /**
     * @param receiverId the receiverId to set
     */
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * @return the receiverName
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * @param receiverName the receiverName to set
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
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
