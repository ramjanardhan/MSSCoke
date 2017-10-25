/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.user;

import com.mss.ediscv.util.AppConstants;
import com.mss.ediscv.util.AuthorizationManager;
import com.mss.ediscv.util.DataSourceDataProvider;
import com.mss.ediscv.util.ServiceLocator;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ParameterAware;

/**
 *
 * @author miracle1
 */
public class UserAction extends ActionSupport implements ServletRequestAware, ParameterAware {

    private HttpServletRequest httpServletRequest;
    private static Logger logger = Logger.getLogger(UserAction.class.getName());
    private String resultType;

    private String fname;
    private String lname;
    private String email;
    private String ophno;
    private String createdBy;
    private String active;
    private String deptId;
    private String status;
    private String role;

    /*
     * for reset password
     */
    private String loginId;
    private String confirmPwd;
    private String newPwd;
    private String oldPwd;

    private String currentAction;
    private String userPageId;

    private String id;
    String resultMessage;
    ServiceLocator serviceLocator;
    private List userList;
//for Assign Flows
    private int userRoleId;

    private UserBean userBean;
    private int userId;
    private String userName;
    private Map assignedFlowsMap;
    private Map notAssignedFlowsMap;

    private Map primaryFlowsList;
    private String primaryFlow;

    private Map parameters;

    private List addedFlowsList;
    private Map UserRolesMap;

    private List pageList;
    private int startValue;
    private int endValue;

    public String prepare() throws Exception {
        logger.info("Entered into the ::::UserAction :::: prepare ");
        setResultType(LOGIN);
        if (httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {

            HttpSession httpSession = httpServletRequest.getSession(false);

            try {

                httpSession.removeAttribute(AppConstants.SES_PAYMENT_LIST);
                httpSession.removeAttribute(AppConstants.SES_SHIPMENT_LIST);
                httpSession.removeAttribute(AppConstants.SES_DOC_LIST);
                httpSession.removeAttribute(AppConstants.SES_INV_LIST);
                httpSession.removeAttribute(AppConstants.SES_PO_LIST);
                httpSession.removeAttribute(AppConstants.SES_USER_LIST);
                httpServletRequest.getSession(false).removeAttribute("searchString");
                httpServletRequest.getSession(false).removeAttribute("gridSize");
                httpServletRequest.getSession(false).removeAttribute("noOfPages");
                //System.out.println("roles map--->"+DataSourceDataProvider.getInstance().getMsscvpRoles());

                setUserRolesMap(DataSourceDataProvider.getInstance().getMsscvpRoles());
                setCurrentAction("../user/createUser.action");
                setUserPageId("0");
                setResultType(SUCCESS);
            } catch (Exception ex) {
                if (logger.isDebugEnabled()) {
                    logger.error("problem :: " + ex.getMessage()
                            + " method name :: prepare() :: class name :: "
                            + getClass().getName());
                }
                httpServletRequest.getSession(false).setAttribute(
                        AppConstants.REQ_EXCEPTION_MSG, ex.getMessage());
                setResultType("error");
            }
        }

        logger.info("End of ::::UserAction :::: prepare ");
        return getResultType();
    }

    public String resetMyPassword() throws Exception {
        logger.info("Entered into the ::::UserAction :::: resetMyPassword ");
        setResultType(LOGIN);
        if (httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
            prepare();
        }

        return getResultType();
    }

    public String resetUserPassword() throws Exception {
        logger.info("Entered into the ::::UserAction :::: resetUserPassword ");
        setResultType(LOGIN);
        if (httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
            setResultType("accessFailed");
            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("USER_EDIT", userRoleId)) {
                prepare();
            }
        }
        return getResultType();
    }

    public String userSearch() throws Exception {
        logger.info("Entered into the ::::UserAction :::: userSearch ");
        setResultType(LOGIN);
        if (httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
            setResultType("accessFailed");
            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("USER_EDIT", userRoleId)) {
                prepare();
            }
        }
        return getResultType();
    }

    public String doCreateUser() throws Exception {
        logger.info("Entered into the ::::UserAction :::: doCreateUser ");
        setResultType(LOGIN);
        if (httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
            setResultType("accessFailed");
            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("USER_EDIT", userRoleId)) {
                prepare();
            }
        }
        return getResultType();
    }

    public String createUser() throws Exception {
        logger.info("Entered into the ::::UserAction :::: createUser ");
        setResultType(LOGIN);
        if (httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
            resultType = "accessFailed";
            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("USER_EDIT", userRoleId)) {
                boolean isUserExist = false;
                isUserExist = ServiceLocator.getUserService().userCheckExist(this);
                if (isUserExist) {
                    resultMessage = "<font color=\"red\" size=\"1.5\">Oops! This User registered already In our System!</font>";
                    setResultType(INPUT);

                } else {
                    //setDeptId(1001);
                    //setActive("A");
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_LOGIN_ID).toString());
                    int i = 0;
                    resultMessage = ServiceLocator.getUserService().addUser(this);

                    resetValues();

                    setResultType(SUCCESS);

                }
                setUserRolesMap(DataSourceDataProvider.getInstance().getMsscvpRoles());
            }
        }
        setCurrentAction("../user/createUser.action");
        setUserPageId("0");
        httpServletRequest.setAttribute(AppConstants.REQ_RESULT_MSG, resultMessage);
        return getResultType();
    }

    public void resetValues() {
        setActive("-1");
        setDeptId("-1");
        setEmail("");
        setFname("");
        setLname("");
        setOphno("");
        setRole("-1");
        setStatus("-1");
        setConfirmPwd("");
        setLoginId("");
        setNewPwd("");

    }

    public String updateUserPwd() throws Exception {
        logger.info("Entered into the ::::UserAction :::: updateUserPwd ");

        resultType = LOGIN;
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
            resultType = "accessFailed";
            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("USER_EDIT", userRoleId)) {
                try {

                    if (!(getNewPwd().equals("")) && !(getConfirmPwd().equals(""))) {
                        int updatedRows = ServiceLocator.getUserService().updateUserPwd(this);
                        if (updatedRows == 1) {//isReset
                            resetValues();
                            resultMessage = "<font color='green'>You have changed User password succesfully </font>";
                            resultType = SUCCESS;
                        } else {
                            resultMessage = "<font color='red'>Sorry!Please enter valid password! Or Your are not authorized person to change the above person password!</font>";
                            resultType = INPUT;
                        }
                    } else {
                        resultMessage = "<font color='red'>Sorry!Please enter password!</font> ";
                    }
                    httpServletRequest.setAttribute("resultMessage", resultMessage);

                    resultType = SUCCESS;

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }
            }//Close Session Checking
        }
        return resultType;
    }

    public String updateMyPwd() throws Exception {
//        applicationDataProvider = applicationDataProvider.getInstance();
        try {
            resultType = LOGIN;
            if (httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {

                try {
                    setLoginId(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_LOGIN_ID).toString());
                    //setLoginId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID).toString());
                    //boolean isReset = ServiceLocator.getGeneralService().updatePassword(this);
                    int updatedRows = ServiceLocator.getUserService().updateMyPwd(this);
                    if (updatedRows == 1) {//isReset
                        resultMessage = "<font color=\"green\" size=\"1.5\">Congrats! You have changed your password succesfully </font>";
                        resultType = SUCCESS;
                    } else {
                        resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! We are not able to change your password. Please enter valid password! </font>";
                        resultType = INPUT;
                    }
                    httpServletRequest.setAttribute("resultMessage", resultMessage);
                    resultType = SUCCESS;

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }

            }//Closing Sessiong Checking

        } catch (Exception ex) {
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
            resultType = ERROR;
        }
        return resultType;

    }//end of the resetPassword() method

    public String getUserSearchList() throws Exception {
        //  System.out.println("getQuery---");
        int gridSize;
        int strStartGrid = 0;
        int strEndGrid = 0;
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
            resultType = "accessFailed";
            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("USER_EDIT", userRoleId)) {
                try {
                    if (logger.isDebugEnabled()) {
                        logger
                                .info("Entered into the ::::UserAction :::: getUserSearchList ");
                    }

                    prepare();
                    HttpSession session = httpServletRequest.getSession(true);
                    httpServletRequest.getSession(false).removeAttribute(AppConstants.SES_LTRESPONSE_LIST);
                    session.removeAttribute("searchString");
                    session.removeAttribute("gridSize");
                    session.removeAttribute("noOfPages");
                    setUserList(ServiceLocator.getUserService().getSearchUserList(this, httpServletRequest));

                    httpServletRequest.getSession(false).setAttribute(AppConstants.SES_USER_LIST, getUserList());
                    System.out.println("userList size-----" + userList.size());
                    if (userList.size() > 0) {
                        gridSize = userList.size();
                        System.out.println("partnerList list size:::" + gridSize);
                        session.setAttribute("gridSize", gridSize);
                        //System.out.println("grid size::::::::"+session.getAttribute("gridSize"));
                        List currentPageList = new ArrayList();
                        if (userList.size() < 10) {
                            strStartGrid = 0;
                            httpServletRequest.setAttribute("strStartGrid", strStartGrid);
                            strEndGrid = userList.size();
                            httpServletRequest.setAttribute("strEndGrid", strEndGrid);
                        } else {
                            strStartGrid = 0;
                            httpServletRequest.setAttribute("strStartGrid", strStartGrid);
                            strEndGrid = 10;
                            httpServletRequest.setAttribute("strEndGrid", strEndGrid);

                        }
                        int noOfPages = 0;

                        System.out.println("userList.size()-->" + userList.size());

                        if (userList.size() % 10 == 0) {
                            noOfPages = userList.size() / 10;
                        } else {
                            noOfPages = (userList.size() / 10) + 1;
                        }

                        session.setAttribute("noOfPages", noOfPages);
                        for (int i = 1; i <= noOfPages; i++) {
                            currentPageList.add(i);
                        }

                        setPageList(currentPageList);
                        //resultType = SUCCESS;
                        setResultType(SUCCESS);
                    } else {
                        strStartGrid = 0;
                        httpServletRequest.setAttribute("strStartGrid", strStartGrid);
                        strEndGrid = 0;
                        httpServletRequest.setAttribute("strEndGrid", strEndGrid);
                        //resultType = SUCCESS;
                        setResultType(SUCCESS);
                    }
                    //resultType = SUCCESS;
                    setResultType(SUCCESS);
                } catch (Exception ex) {
                    if (logger.isDebugEnabled()) {
                        logger
                                .error("problem :: "
                                        + ex.getMessage()
                                        + " method name :: getUserSearchList() :: class name :: "
                                        + getClass().getName());
                    }
                    httpServletRequest.getSession(false).setAttribute(
                            AppConstants.REQ_EXCEPTION_MSG, ex.getMessage());
                    resultType = "error";
                }
            }
        }
        logger.info("End of ::::UserAction :::: getUserSearchList ");
        //return resultType;
        return getResultType();
    }

    public String doNextUserList() {
        System.out.println("doNextUserList------");
        int strStartGrid = 0;
        int strEndGrid = 0;
        int pageSize = 0;
        int gridSplit = 0;

        try {
//            List corrList;
//            List docList;
//            corrList = DataSourceDataProvider.getInstance().getCorrelationNames(0, 2);
//            docList = DataSourceDataProvider.getInstance().getDocumentTypeList(0, 2);
//            setCorrelationList(corrList);
//            setDocTypeList(docList);
            int gettxtStartGrid = Integer.parseInt(httpServletRequest.getParameter("startValue"));
            int gettxtEndGrid = Integer.parseInt(httpServletRequest.getParameter("endValue"));
            String buttonValue = httpServletRequest.getParameter("button");
            //System.out.println("button :::::::"+buttonValue);

            HttpSession session = httpServletRequest.getSession(true);
            String gridSize = session.getAttribute("gridSize").toString();
            int gridLength = Integer.parseInt(gridSize);

            //List documentList = (List) session.getAttribute("documentList");
            // System.out.println("doNextLtReports----size---"+documentList.size());
            List userList = (List) session.getAttribute("userList");
            System.out.println("doNextLtReports----size---" + userList.size());
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
                if (userList.size() > getEndValue()) {
                    httpServletRequest.setAttribute("strEndGrid", getEndValue());
                } else {
                    httpServletRequest.setAttribute("strEndGrid", userList.size());
                }
            }
            List currentPageList = new ArrayList();
            int noOfPages = 0;

            if (userList.size() % 10 == 0) {
                noOfPages = userList.size() / 10;
            } else {
                noOfPages = (userList.size() / 10) + 1;
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

    public String userEdit() throws Exception {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
            resultType = "accessFailed";
            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("USER_EDIT", userRoleId)) {
                try {

                    if (logger.isDebugEnabled()) {
                        logger
                                .info("Entered into the ::::UserAction :::: userEdit ");
                    }

                    //prepare();
                    //System.out.println("tpId-->"+getTpid());
                    //System.out.println("tp Name-->"+getTpname());
                    String responseString = "";
                    //setStatesMap(DataSourceDataProvider.getInstance().getStates());
                    // setStatesMap((HashMap)httpServletRequest.getSession(false).getAttribute(AppConstants.SES_STATES_MAP));
                    // setCreatedBy(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_LOGIN_ID).toString());  
                    ServiceLocator.getUserService().editUser(this);
                    httpServletRequest.setAttribute(AppConstants.REQ_RESULT_MSG, responseString);
                    // resetValues();
                    setUserRolesMap(DataSourceDataProvider.getInstance().getMsscvpRoles());

                    setCurrentAction("../user/doUpdateUser.action");
                    setUserPageId("1");
                    //for setting search variables
                    //   setSearchFields();
                    resultType = SUCCESS;

                } catch (Exception ex) {
                    if (logger.isDebugEnabled()) {
                        logger
                                .error("problem :: "
                                        + ex.getMessage()
                                        + " method name :: userEdit() :: class name :: "
                                        + getClass().getName());
                    }
                    httpServletRequest.getSession(false).setAttribute(
                            AppConstants.REQ_EXCEPTION_MSG, ex.getMessage());
                    resultType = "error";
                }
            }
        }
        logger.info("End of ::::UserAction :::: userEdit ");
        return resultType;
    }

    public String backToSearchList() throws Exception {
        getUserSearchList();
        return SUCCESS;
    }

    public String doUpdateUser() throws Exception {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
            resultType = "accessFailed";
            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("USER_EDIT", userRoleId)) {
                try {

                    if (logger.isDebugEnabled()) {
                        logger
                                .info("Entered into the ::::UserAction :::: doUpdateUser ");
                    }

                    prepare();
                    //setStatesMap((HashMap)httpServletRequest.getSession(false).getAttribute(AppConstants.SES_STATES_MAP));
                    String responseString = "";
                    setCreatedBy(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_LOGIN_ID).toString());
                    responseString = ServiceLocator.getUserService().updateUser(this);
                    //    setSearchValues();
                    //getTradingSearchQuery();
                    httpServletRequest.setAttribute(AppConstants.REQ_RESULT_MSG, responseString);
                    setCurrentAction("../user/doUpdateUser.action");
                    //setSearchFields();
                    setUserPageId("1");
                    // resetValues();
                    //        setTppageId("1");   

                    resultType = SUCCESS;

                } catch (Exception ex) {
                    if (logger.isDebugEnabled()) {
                        logger
                                .error("problem :: "
                                        + ex.getMessage()
                                        + " method name :: doUpdateUser() :: class name :: "
                                        + getClass().getName());
                    }
                    httpServletRequest.getSession(false).setAttribute(
                            AppConstants.REQ_EXCEPTION_MSG, ex.getMessage());
                    resultType = "error";
                }
            }
        }
        logger.info("End of ::::UserAction :::: doUpdateUser ");
        return resultType;
    }

    /*
     * 
     * Date : 05/03/2013
     * Method : getAssingnedRoles
     * Author : Santosh
     */
    public String getAssingnedFlows() {

        resultType = LOGIN;
        DataSourceDataProvider dataSourceDataProvider = null;
        if (httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
            resultType = "accessFailed";
            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("USER_EDIT", userRoleId)) {
                setUserRoleId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID).toString()));
                resultType = "accessFailed";
                try {
                    dataSourceDataProvider = DataSourceDataProvider.getInstance();

                    UserBean ub = ServiceLocator.getUserService().userDetails(getUserId());
                    setUserBean(ub);

                    setAssignedFlowsMap(dataSourceDataProvider.getAssignedFlows(getUserId()));

                    setNotAssignedFlowsMap(dataSourceDataProvider.getNotAssignedFlows(getUserId()));

                    setPrimaryFlowsList(dataSourceDataProvider.getFlowbyflowKey(AppConstants.FLOWS_OPTIONS));

                    resultType = SUCCESS;
                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    ex.printStackTrace();
                    resultType = ERROR;
                }
            }
        }

        return resultType;
    }

    public String getTransferFlow() throws Exception {
        resultType = LOGIN;
        /*
         *This if loop is to check whether there is Session or not
         **/
        if (httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
            resultType = "accessFailed";
            int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID).toString());
            if (AuthorizationManager.getInstance().isAuthorizedUser("USER_EDIT", userRoleId)) {

                userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID).toString());
                resultType = "accessFailed";
                /*
                 *This if loop is to check the Authentication
                 **/

                try {
                    /**
                     *
                     *
                     * @param Taking form submitted values of addedRolesList
                     * @return String array rightParams
                     *
                     *
                     */

                    String[] rightParams = (String[]) parameters.get("addedFlowsList");

                    //System.out.println("rightParams1-->"+rightParams[1]);
                    /**
                     *
                     *
                     * @param Taking form submitted values of
                     * leftSideEmployeeRoles
                     * @return String array leftParams
                     *
                     *
                     */
                    String[] leftParams = (String[]) parameters.get("leftSideUserFlows");

                    // System.out.println("leftParams1-->"+leftParams[1]);
                    /**
                     *
                     *
                     * @param Taking rightParams array,getEmpRoleId method of
                     * this object and getPrimaryRole method of this object.
                     * @return result of inserted rows in the database.
                     *
                     *
                     */
                    int insertedRows = ServiceLocator.getUserService().insertFlows(rightParams, this.getUserId(), Integer.parseInt(getPrimaryFlow()));
                    //searchPrepare();
                    resultType = SUCCESS;
                    resultMessage = "<font color=\"green\" size=\"1.5\">Flows has been successfully Added!</font>";
                    httpServletRequest.setAttribute(AppConstants.REQ_RESULT_MSG, resultMessage);

                } catch (Exception ex) {
                    //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                    httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                    resultType = ERROR;
                }

            }//Close Session Checking
        }
        return resultType;

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
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the ophno
     */
    public String getOphno() {
        return ophno;
    }

    /**
     * @param ophno the ophno to set
     */
    public void setOphno(String ophno) {
        this.ophno = ophno;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the active
     */
    public String getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(String active) {
        this.active = active;
    }

    /**
     * @return the deptId
     */
    public String getDeptId() {
        return deptId;
    }

    /**
     * @param deptId the deptId to set
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId;
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
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * @param loginId the loginId to set
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * @return the confirmPwd
     */
    public String getConfirmPwd() {
        return confirmPwd;
    }

    /**
     * @param confirmPwd the confirmPwd to set
     */
    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

    /**
     * @return the newPwd
     */
    public String getNewPwd() {
        return newPwd;
    }

    /**
     * @param newPwd the newPwd to set
     */
    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    /**
     * @return the oldPwd
     */
    public String getOldPwd() {
        return oldPwd;
    }

    /**
     * @param oldPwd the oldPwd to set
     */
    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    /**
     * @return the currentAction
     */
    public String getCurrentAction() {
        return currentAction;
    }

    /**
     * @param currentAction the currentAction to set
     */
    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }

    /**
     * @return the userPageId
     */
    public String getUserPageId() {
        return userPageId;
    }

    /**
     * @param userPageId the userPageId to set
     */
    public void setUserPageId(String userPageId) {
        this.userPageId = userPageId;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the userList
     */
    public List getUserList() {
        return userList;
    }

    /**
     * @param userList the userList to set
     */
    public void setUserList(List userList) {
        this.userList = userList;
    }

    /**
     * @return the userRoleId
     */
    public int getUserRoleId() {
        return userRoleId;
    }

    /**
     * @param userRoleId the userRoleId to set
     */
    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    /**
     * @return the userBean
     */
    public UserBean getUserBean() {
        return userBean;
    }

    /**
     * @param userBean the userBean to set
     */
    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the assignedFlowsMap
     */
    public Map getAssignedFlowsMap() {
        return assignedFlowsMap;
    }

    /**
     * @param assignedFlowsMap the assignedFlowsMap to set
     */
    public void setAssignedFlowsMap(Map assignedFlowsMap) {
        this.assignedFlowsMap = assignedFlowsMap;
    }

    /**
     * @return the notAssignedFlowsMap
     */
    public Map getNotAssignedFlowsMap() {
        return notAssignedFlowsMap;
    }

    /**
     * @param notAssignedFlowsMap the notAssignedFlowsMap to set
     */
    public void setNotAssignedFlowsMap(Map notAssignedFlowsMap) {
        this.notAssignedFlowsMap = notAssignedFlowsMap;
    }

    /**
     * @return the primaryFlowsList
     */
    public Map getPrimaryFlowsList() {
        return primaryFlowsList;
    }

    /**
     * @param primaryFlowsList the primaryFlowsList to set
     */
    public void setPrimaryFlowsList(Map primaryFlowsList) {
        this.primaryFlowsList = primaryFlowsList;
    }

    /**
     * @return the primaryFlow
     */
    public String getPrimaryFlow() {
        return primaryFlow;
    }

    /**
     * @param primaryFlow the primaryFlow to set
     */
    public void setPrimaryFlow(String primaryFlow) {
        this.primaryFlow = primaryFlow;
    }

    /**
     * @return the parameters
     */
    public Map getParameters() {
        return parameters;
    }

    /**
     * @param parameters the parameters to set
     */
    public void setParameters(Map parameters) {
        this.parameters = parameters;
    }

    /**
     * @return the addedFlowsList
     */
    public List getAddedFlowsList() {
        return addedFlowsList;
    }

    /**
     * @param addedFlowsList the addedFlowsList to set
     */
    public void setAddedFlowsList(List addedFlowsList) {
        this.addedFlowsList = addedFlowsList;
    }

    /**
     * @return the UserRolesMap
     */
    public Map getUserRolesMap() {
        return UserRolesMap;
    }

    /**
     * @param UserRolesMap the UserRolesMap to set
     */
    public void setUserRolesMap(Map UserRolesMap) {
        this.UserRolesMap = UserRolesMap;
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
