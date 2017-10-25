/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.logisticreports;
//import com.mss.ediscv.reports.*;

import com.mss.ediscv.util.AppConstants;
import com.mss.ediscv.util.DataSourceDataProvider;
import com.mss.ediscv.util.DateUtility;
import com.mss.ediscv.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.log4j.Logger;

/**
 *
 * @author miracle
 */
public class LogisticReportsAction extends ActionSupport implements ServletRequestAware {

    private static Logger logger = Logger.getLogger(LogisticReportsAction.class.getName());

    private HttpServletRequest httpServletRequest;
    private String resultType;
    private String formAction;
    private List correlationList;
    private List docTypeList;
    private String docdatepicker;
    private String docdatepickerfrom;
    private String docSenderId;
    private String docSenderName;
    private String docType;
    private String status;
    private String ackStatus;
    private String docBusId;
    private String docRecName;
    private String check;
    // new fields added
    private List pageList;
    private int startValue;
    private int endValue;
    private List<LogisticReportsBean> documentList;
    private Map partnerMap;

    public String getLogisticReports() throws Exception {
        logger.info("Entered into the ::::SearchDocRepositorAction :::: prepare ");
        List corrList;
        List docList;
        setResultType(LOGIN);
        if (httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
            String defaultFlowId = httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_DEFAULT_FLOWID).toString();
            String defaultFlowName = DataSourceDataProvider.getInstance().getFlowNameByFlowID(defaultFlowId);
            httpServletRequest.getSession(false).removeAttribute(AppConstants.SES_LOG_DOC_LIST);
            httpServletRequest.getSession(false).removeAttribute(AppConstants.SES_LOGSHIPMENT_LIST);
            httpServletRequest.getSession(false).removeAttribute(AppConstants.SES_LOAD_LIST);
            httpServletRequest.getSession(false).removeAttribute(AppConstants.SES_LTRESPONSE_LIST);
            httpServletRequest.getSession(false).removeAttribute("searchString");
            httpServletRequest.getSession(false).removeAttribute("gridSize");
            httpServletRequest.getSession(false).removeAttribute("noOfPages");
            corrList = DataSourceDataProvider.getInstance().getCorrelationNames(0, 2);
            docList = DataSourceDataProvider.getInstance().getDocumentTypeList(0, 2);
            String roleIds = (String) httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID);

            /*
             if (roleIds.equals("101")) {
             setDocTypeList(DataSourceDataProvider.getInstance().getDocumentTypeList(0, 2));
             } else if (roleIds.equals("102")) {
             // System.out.println("leee////"+DataSourceDataProvider.getInstance().getDocumentTypeList(2,4).size());
             //setDocTypeList(DataSourceDataProvider.getInstance().getDocumentTypeList(4,2));
             //System.out.println("get list...."+getDocTypeList().size());
             setDocTypeList(DataSourceDataProvider.getInstance().getDocumentTypeListInvoice(210, 2));
             System.out.println("get list...." + getDocTypeList().size());
             } else if (roleIds.equals("103")) {
             //setDocTypeList(DataSourceDataProvider.getInstance().getDocumentTypeList(4,2));

             //SELECT NAME FROM DOCUMENTTYPES where GROUP_ID=2 and ID!=210;
             setDocTypeList(DataSourceDataProvider.getInstance().getDocumentTypeList(4, 2));
             System.out.println("get list...." + getDocTypeList().size());
             }
             */
            setCorrelationList(corrList);
            setDocTypeList(docList);
            if (!defaultFlowName.equals("Logistics")) {
                defaultFlowId = DataSourceDataProvider.getInstance().getFlowIdByFlowName("Logistics");
                httpServletRequest.getSession(false).setAttribute(AppConstants.SES_USER_DEFAULT_FLOWID, defaultFlowId);
            }
            setResultType(SUCCESS);
        }
        logger.info("End of ::::SearchDOCUMENTSAction :::: prepare ");
        return getResultType();
    }
    //documentList = ServiceLocator.getLogisticReportsService().getDocumentList(this);

    public String logisticreportsSearch() {
        int gridSize;
        int strStartGrid = 0;
        int strEndGrid = 0;
        setResultType(LOGIN);

        if (httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
            try {
                if (logger.isDebugEnabled()) {
                    logger
                            .info("Entered into the ::::SearchpuchaseAction :::: getPurchaseSearchQuery ");
                }
                //execute();
                HttpSession hsession = httpServletRequest.getSession(false);
                if (getCheck() == null) {
                    // System.out.println("in if"+getCheck());
                    setCheck("1");
                } else if (getCheck().equals("")) {
                    // System.out.println("in else"+getCheck());
                    setCheck("1");
                }

                //System.out.println("setCheck-->"+getCheck());
                List corrList;
                List docList;

                corrList = DataSourceDataProvider.getInstance().getCorrelationNames(0, 2);
                docList = DataSourceDataProvider.getInstance().getDocumentTypeList(0, 2);
                setCorrelationList(corrList);
                setDocTypeList(docList);

                String roleIds = (String) httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID);
                /*
                 if (roleIds.equals("101")) {
                 setDocTypeList(DataSourceDataProvider.getInstance().getDocumentTypeList(0, 2));
                 } else if (roleIds.equals("102")) {

                 setDocTypeList(DataSourceDataProvider.getInstance().getDocumentTypeListInvoice(210, 2));
                 System.out.println("get list...." + getDocTypeList().size());
                 } else if (roleIds.equals("103")) {

                 setDocTypeList(DataSourceDataProvider.getInstance().getDocumentTypeList(4, 2));
                 System.out.println("get list...." + getDocTypeList().size());
                 }
                 */
                HttpSession session = httpServletRequest.getSession(true);
                httpServletRequest.getSession(false).removeAttribute(AppConstants.SES_LOG_DOC_LIST);
                session.removeAttribute("searchString");
                session.removeAttribute("gridSize");
                session.removeAttribute("noOfPages");
                documentList = ServiceLocator.getLogisticReportsService().getDocumentList(this, roleIds, hsession, httpServletRequest);
                System.out.println("the logis  list is --->" + documentList);
                httpServletRequest.getSession(false).setAttribute(AppConstants.SES_LOG_DOC_LIST, documentList);
                System.out.println("list size-----" + documentList.size());
                if (documentList.size() > 0) {
                    gridSize = documentList.size();
                    System.out.println("searchResult list size:::" + gridSize);
                    session.setAttribute("gridSize", gridSize);
                    //System.out.println("grid size::::::::"+session.getAttribute("gridSize"));
                    List currentPageList = new ArrayList();
                    if (documentList.size() < 10) {
                        strStartGrid = 0;
                        httpServletRequest.setAttribute("strStartGrid", strStartGrid);
                        strEndGrid = documentList.size();
                        httpServletRequest.setAttribute("strEndGrid", strEndGrid);
                    } else {
                        strStartGrid = 0;
                        httpServletRequest.setAttribute("strStartGrid", strStartGrid);
                        strEndGrid = 10;
                        httpServletRequest.setAttribute("strEndGrid", strEndGrid);

                    }
                    int noOfPages = 0;

                    System.out.println("documentList.size()-->" + documentList.size());

                    if (documentList.size() % 10 == 0) {
                        noOfPages = documentList.size() / 10;
                    } else {
                        noOfPages = (documentList.size() / 10) + 1;
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
                                    + " method name :: getPOSearchQuery() :: class name :: "
                                    + getClass().getName());
                }
                httpServletRequest.getSession(false).setAttribute(
                        AppConstants.REQ_EXCEPTION_MSG, ex.getMessage());
                setResultType("error");
            }
        }
        logger.info("End of ::::SearchPOAction :::: getPOSearchQuery ");
        return getResultType();
    }

    public String doNextLtReports() {
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
            List documentList = (List) session.getAttribute("logdocumentList");
            System.out.println("doNextLtReports----size---" + documentList.size());
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
                if (documentList.size() > getEndValue()) {
                    httpServletRequest.setAttribute("strEndGrid", getEndValue());
                } else {
                    httpServletRequest.setAttribute("strEndGrid", documentList.size());
                }
            }
            List currentPageList = new ArrayList();
            int noOfPages = 0;

            if (documentList.size() % 10 == 0) {
                noOfPages = documentList.size() / 10;
            } else {
                noOfPages = (documentList.size() / 10) + 1;
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

    public String getDashboard() {
        System.out.println("in tp Action testing----");
        logger.info("Entered into the ::::ReportsAction :::: getDashboard ");
        setResultType(LOGIN);
        if (httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
            try {
                List docList;
                List corrList;
                corrList = DataSourceDataProvider.getInstance().getCorrelationNames(0, 2);
                docList = DataSourceDataProvider.getInstance().getDocumentTypeList(0, 2);
                setCorrelationList(corrList);
                setDocTypeList(docList);

                String roleIds = (String) httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID);
                /*
                 if (roleIds.equals("101")) {
                 setDocTypeList(DataSourceDataProvider.getInstance().getDocumentTypeList(0, 2));
                 } else if (roleIds.equals("102")) {
                 // System.out.println("leee////"+DataSourceDataProvider.getInstance().getDocumentTypeList(2,4).size());
                 //setDocTypeList(DataSourceDataProvider.getInstance().getDocumentTypeList(4,2));
                 //System.out.println("get list...."+getDocTypeList().size());
                 setDocTypeList(DataSourceDataProvider.getInstance().getDocumentTypeListInvoice(210, 2));
                 //System.out.println("get list...."+getDocTypeList().size());
                 } else if (roleIds.equals("103")) {
                 //setDocTypeList(DataSourceDataProvider.getInstance().getDocumentTypeList(4,2));

                 //SELECT NAME FROM DOCUMENTTYPES where GROUP_ID=2 and ID!=210;
                 setDocTypeList(DataSourceDataProvider.getInstance().getDocumentTypeList(4, 2));
                 //System.out.println("get list...."+getDocTypeList().size());
                 }
                 */
                setPartnerMap(DataSourceDataProvider.getInstance().getDashboardPartnerMap());
                resultType = SUCCESS;
                setResultType(SUCCESS);
            } catch (Exception exception) {
                setResultType(ERROR);
            }
        }
        return getResultType();
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
     * @return the formAction
     */
    public String getFormAction() {
        return formAction;
    }

    /**
     * @param formAction the formAction to set
     */
    public void setFormAction(String formAction) {
        this.formAction = formAction;
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
     * @return the docSenderId
     */
    public String getDocSenderId() {
        return docSenderId;
    }

    /**
     * @param docSenderId the docSenderId to set
     */
    public void setDocSenderId(String docSenderId) {
        this.docSenderId = docSenderId;
    }

    /**
     * @return the docSenderName
     */
    public String getDocSenderName() {
        return docSenderName;
    }

    /**
     * @param docSenderName the docSenderName to set
     */
    public void setDocSenderName(String docSenderName) {
        this.docSenderName = docSenderName;
    }

    /**
     * @return the docReceiverId
     */
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
     * @return the partnerMap
     */
    public Map getPartnerMap() {
        return partnerMap;
    }

    /**
     * @param partnerMap the partnerMap to set
     */
    public void setPartnerMap(Map partnerMap) {
        this.partnerMap = partnerMap;
    }

    public String getDocdatepicker() {
        return docdatepicker;
    }

    public void setDocdatepicker(String docdatepicker) {
        this.docdatepicker = docdatepicker;
    }

    public String getDocdatepickerfrom() {
        return docdatepickerfrom;
    }

    public void setDocdatepickerfrom(String docdatepickerfrom) {
        this.docdatepickerfrom = docdatepickerfrom;
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

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
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

    public List getPageList() {
        return pageList;
    }

    public void setPageList(List pageList) {
        this.pageList = pageList;
    }

}
