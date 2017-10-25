<%-- 
    Document   : documentVisibility
    Created on : Jan 5, 2015, 10:13:15 AM
    Author     : miracle
--%>


<%@page import="java.util.List"%>
<%-- 
    Document   : GridDocRepository
    Created on : May 6, 2014, 4:24:46 PM
    Author     : miracle
--%>

<%@page import="com.mss.ediscv.documentVisibility.DocumentVisibilityBean"%>
<%-- <%@ page contentType="text/html" pageEncoding="UTF-8"%> --%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> --%>
<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page buffer="50kb" autoFlush="true" %>
<%@ taglib uri="/WEB-INF/tlds/dbgrid.tld" prefix="grd"%>
<%@ page import="com.freeware.gridtag.*"%>
<%@page import="java.sql.Connection"%>
<%@  page import="com.mss.ediscv.util.AppConstants"%>
<%@ page import="com.mss.ediscv.util.ConnectionProvider"%>
<%@ page import="java.sql.SQLException"%>

<!DOCTYPE html>
<html class=" js canvas canvastext geolocation crosswindowmessaging no-websqldatabase indexeddb hashchange historymanagement draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow opacity cssanimations csscolumns cssgradients no-cssreflections csstransforms no-csstransforms3d csstransitions  video audio localstorage sessionstorage webworkers applicationcache svg smil svgclippaths   fontface">
    <head>
        <title>Red Classic Transportation Service Portal</title>

        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta http-equiv="pragma" content="no-cache" />
        <meta http-equiv="cache-control" content="no-cache" />

        <%-- <meta name="description" content="website description" />
         <meta name="keywords" content="website keywords, website keywords" />
         <meta http-equiv="content-type" content="text/html; charset=UTF-8" />  --%>

        <link rel="stylesheet" href='<s:url value="/includes/css/style.css"/>'
              type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/jquery-ui.css"/>'
              type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/dhtmlxcalendar.css"/>'
              type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/dhtmlxcalendar_omega.css"/>'
              type="text/css">

        <link rel="stylesheet" href='<s:url value="/includes/css/footerstyle.css"/>'
              type="text/css"/>
        <link rel="stylesheet" href='<s:url value="/includes/css/GridStyle.css"/>'
              type="text/css"/>
        <script language="JavaScript"
        src='<s:url value="/includes/js/jquery-1.9.1.js"></s:url>'></script>
            <script language="JavaScript"
            src='<s:url value="/includes/js/jquery-ui.js"/>'></script>
        <script language="JavaScript"
        src='<s:url value="/includes/js/DBGrid.js"/>'></script>
        <script language="JavaScript"
        src='<s:url value="/includes/js/DateValidation.js"/>'></script>
        <script language="JavaScript"
        src='<s:url value="/includes/js/GridNavigation.js"/>'></script>
        <script language="JavaScript"
        src='<s:url value="/includes/js/dhtmlxcalendar.js"/>'></script>

        <script language="JavaScript"
        src='<s:url value="/includes/js/GeneralAjax.js"/>'></script>

        <script language="JavaScript"
        src='<s:url value="/includes/js/dbgridDisplay.js"/>'></script>

        <script language="JavaScript"
        src='<s:url value="/includes/js/common.js"/>'></script>
        <script language="JavaScript"
        src='<s:url value="/includes/js/modernizr-1.5.min.js"/>'></script>
        <script language="JavaScript"
        src='<s:url value="/includes/js/downloadAjax.js"/>'></script>
        <%--   <script language="JavaScript"
        src='<s:url value="/includes/js/generalValidations.js"/>'></script>  --%>
        <link href="../includes/images/truck_image.jpg" rel="shortcut icon"/>
        <script>
            var myCalendar;
            function doOnLoad() {
                myCalendar = new dhtmlXCalendarObject(["docdatepickerfrom", "docdatepicker"]);
                myCalendar.setSkin('omega');
                myCalendar.setDateFormat("%m/%d/%Y %H:%i");

            }
        </script>
        <script type="text/javascript">
            $(function () {
                $('#attach_box').click(function () {
                    $('#sec_box').show();
                    return false;
                });
            });
            $(function () {
                $('#detail_link').click(function () {
                    $('#detail_box').show();
                    return false;
                });
            });

            // New function to show the left grid

            function demo() {
                $(function () {

                    $('#detail_box').show();
                    return false;
                });

            }

            function getDetails(val) {
                //  alert("hiiii");    

                getDocVisibilityDetails(val);
            }
            function checkCorrelation() {

                // alert("hiii");
                /*  var corrattr = document.getElementById('corrattribute').value;
                 var corrval = document.getElementById('corrvalue').value;
                 
                 var corrattr1 = document.getElementById('corrattribute1').value;
                 var corrval1 = document.getElementById('corrvalue1').value;
                 
                 var corrattr2 = document.getElementById('corrattribute2').value;
                 var corrval2 = document.getElementById('corrvalue2').value;
                 
                 if((corrattr!="-1")&&(corrval=="")) {
                 alert("please enter Correlation Value!!!");
                 return false;
                 }
                 if((corrattr=="-1")&&(corrval!="")) {
                 alert("please select Correlation!");
                 return false;
                 }
                 
                 if((corrattr1!="-1")&&(corrval1=="")) {
                 alert("please enter Correlation Value!!!");
                 return false;
                 }
                 if((corrattr1=="-1")&&(corrval1!="")) {
                 alert("please select Correlation!");
                 return false;
                 }
                 
                 if((corrattr2!="-1")&&(corrval2=="")) {
                 alert("please enter Correlation Value!!!");
                 return false;
                 }
                 if((corrattr2=="-1")&&(corrval2!="")) {
                 alert("please select Correlation!");
                 return false;
                 } */

                var res = compareDates(document.getElementById('docdatepickerfrom').value, document.getElementById('docdatepicker').value);

                //alert(res);
                return res;
            }
            function resetvalues()
            {
                document.getElementById('docdatepickerfrom').value = "";
                document.getElementById('docdatepicker').value = "";
                document.getElementById('docSenderId').value = "";
                document.getElementById('docSenderName').value = "";
                document.getElementById('docBusId').value = "";
                document.getElementById('docRecName').value = "";
                //document.getElementById('docIsa').value="";
                document.getElementById('corrattribute').value = "-1";
                document.getElementById('corrvalue').value = "";
                document.getElementById('docType').value = "-1";
                document.getElementById('corrattribute1').value = "-1";
                document.getElementById('corrvalue1').value = "";
                document.getElementById('corrattribute2').value = "-1";
                document.getElementById('corrvalue2').value = "";

                document.getElementById('status').value = "-1";
                document.getElementById('ackStatus').value = "-1";

                $('#detail_box').hide();
                $('#gridDiv').hide();


            }
            /* $(document).ready(function() {
             $('ul.sf-menu').sooperfish();
             });*/
        </script>
        <script LANGUAGE="javascript">
            function doNavigate(pstrWhere, pintTot)
            {
                var strTmp;
                var intPg;

                strTmp = document.documentForm.txtCurr.value;
                intPg = parseInt(strTmp);
                if (isNaN(intPg))
                    intPg = 1;

                if ((pstrWhere == 'F' || pstrWhere == 'P') && intPg == 1)
                {
                    alert("You are already viewing first page!");
                    return;
                }
                else if ((pstrWhere == 'N' || pstrWhere == 'L') && intPg == pintTot)
                {
                    alert("You are already viewing last page!");
                    return;
                }

                if (pstrWhere == 'F')
                    intPg = 1;
                else if (pstrWhere == 'P')
                    intPg = intPg - 1;
                else if (pstrWhere == 'N')
                    intPg = intPg + 1;
                else if (pstrWhere == 'L')
                    intPg = pintTot;

                if (intPg < 1)
                    intPg = 1;
                if (intPg > pintTot)
                    intPg = pintTot;
                document.documentForm.txtCurr.value = intPg;
                document.documentForm.submit();
            }

            function doSort(pstrFld, pstrOrd)
            {
                document.documentForm.txtSortCol.value = pstrFld;
                document.documentForm.txtSortAsc.value = pstrOrd;
                document.documentForm.submit();
            }

            function goToPage(element) {
                //alert("in go to page");
                document.documentForm.txtCurr.value = element.options[element.selectedIndex].value;
                document.documentForm.submit();
            }

            /*For REsume type grid script
             * 
             * 
             */
            function gridNext(c) {
                var b = c.id;
                var e = parseInt(document.documentForm.txtStartGrid.value);
                var a = parseInt(document.documentForm.txtEndGrid.value);
                var d = parseInt(document.documentForm.txtMaxGrid.value);
                if (b == "Next") {
                    if (a < d)
                    {
                        document.location = "nextConsultantResume.action?startValue=" + e + "&endValue=" + a + "&button=" + b
                    } else {
                        if (a == d) {
                            alert("You are already viewing last page!")
                        }
                    }
                } else {
                    if (b == "Previous")
                    {
                        if (e < d && e > 0) {
                            document.location = "nextConsultantResume.action?startValue=" + e + "&endValue=" + a + "&button=" + b
                        } else {
                            if (e == 0) {
                                alert("You are already viewing first page!")
                            }
                        }
                    } else {
                        if (b == "First") {
                            if (e < d && e > 0) {
                                e = 0;
                                a = 30;
                                document.location = "nextConsultantResume.action?startValue=" + e + "&endValue=" + a + "&button=" + b
                            } else {
                                if (e == 0) {
                                    alert("You are already viewing first page!")
                                }
                            }
                        } else {
                            if (b == "Last") {
                                if (a < d) {
                                    e = d - 30;
                                    a = d;
                                    document.location = "nextConsultantResume.action?startValue=" + e + "&endValue=" + a + "&button=" + b
                                } else {
                                    if (a == d) {
                                        alert("You are already viewing last page!")
                                    }
                                }
                            }
                        }
                    }
                }
            }



            function goToPage() {
                var pageNumber = document.getElementById('pageNumber').value;
                var b = "Select";
                var startValue = ((parseInt(pageNumber) - 1) * 30);
                var endValue = parseInt(startValue) + 30;
                document.location = "nextConsultantResume.action?startValue=" + startValue + "&endValue=" + endValue + "&button=" + b

            }




        </script>

        <!-- Uncomment to get the Scrollable Div -->
        <!--<style type="text/css">       
      #gridDiv
      {
      overflow-y: hidden;
      overflow-x: scroll}
      </style>-->
        <!-- Uncomment to get Scrollable div -->

    </head>

    <%
        int intCurr = 1;
        int intSortOrd = 0;
        String strTmp = null;
        String strSQL = null;
        String strSortCol = null;
        String strSortOrd = "ASC";
        boolean blnSortAsc = true;

//strSQL = "SELECT  TOP 200  fs.ID as FILE_ID,INSTANCEID,fs.FILETYPE as FILE_TYPE,fs.TRANSACTIONTYPE as TRANSACTION_TYPE,fs.DIRECTION as DIRECTION,fs.SENDERID as SENDERID,TP1.NAME as SENDERNAME,fs.STATUS as STATUS,fs.ACKSTATUS as ACK_STATUS,fs.RECEIVERID as RECEIVERID,TP2.NAME as RECEIVER_NAME,fs.ISACONTROLNUMBER as ISA_NUMBER,fs.GSCONTROLNUMBER as GS_NUMBER,fs.REPROCESSSTATUS as REPROCESSSTATUS,fs.CREATEDDATE as CREATEDDATE FROM FILES fs LEFT OUTER JOIN TP TP1 ON (TP1.ID=fs.SENDERID) LEFT OUTER JOIN TP TP2 ON (TP2.ID=fs.RECEIVERID) WHERE 1=1 AND iFlag IN ('P1','P2')   order by CREATEDDATE DESC";
//strSQL = "SELECT fs.ID as FILE_ID,fs.INSTANCEID as INSTANCEID ,fs.SENDERID as SENDERID FROM FILES fs";
        if (session.getAttribute(AppConstants.SES_LOG_DOC_LIST) != null) {

            strSQL = session.getAttribute(AppConstants.SES_LOG_DOC_LIST).toString();
        }
//out.println("strSQL-->"+strSQL);

        Connection objCnn = null;
        Class objDrvCls = null;

//ctx = new InitialContext();
//envCtx = (Context) ctx.lookup("java:comp/env");
//ds = (DataSource) envCtx.lookup("jdbc/DBGrid");
//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//objCnn = DriverManager.getConnection("jdbc:sqlserver://172.17.4.110:1433;databaseName=MSCVPDB","sa", "casteam");
        //  objCnn = ConnectionProvider.getInstance().getConnection();     
//objCnn = ds.getConnection();
        strTmp = request.getParameter("txtCurr");
        try {
            if (strTmp != null) {
                intCurr = Integer.parseInt(strTmp);
            }
        } catch (NumberFormatException NFEx) {
        }
        strSortCol = request.getParameter("txtSortCol");
        strSortOrd = request.getParameter("txtSortAsc");
        if (strSortCol == null) {
            strSortCol = "INSTANCEID";
        }
        if (strSortOrd == null) {
            strSortOrd = "ASC";
        }
        blnSortAsc = (strSortOrd.equals("ASC"));
    %>
    <%
        String check = null;
        if (request.getAttribute("check") != null) {
            check = request.getAttribute("check").toString();
        }

        //System.out.println("check-->"+check);
    %>





    <%!
        String strStartGrid;
        String strEndGrid;
        // String searchString;
        String pathName;

        int resultCount = 0;
        int strIntStartGrid;
        int strIntEndGrid;
        List searchResult = null;
        int noOfPages = 0;
    %>
    <body onload="doOnLoad();
            setStyle('ltDocRepository', '');">
        <script type="text/javascript" src='<s:url value="/includes/js/wz_tooltip.js"/>'></script>
        <div id="wrapper">
            <div id="main">
                <header>
                    <div id="logo">
                        <s:include value="/includes/template/head.jsp"/>       


                    </div>
                    <nav>
                        <ul class="sf-menu" id="nav">

                            <%--    <s:include value="/includes/template/logisticsMenu.jsp"/> --%>
                            <s:include value="/includes/template/docVisibilityMenu.jsp"/>
                        </ul> 
                    </nav>
                </header>
                <div id="site_content">
                    <div id="sidebar_container">
                        <div id="detail_box" style="display: none;"> 
                            <div class="sidebar">
                                <h3>Detail Information</h3>
                                <div class="sidebar_item">

                                    <div id="loadingImage" align="center"><img  src="../includes/images/ajax-loader.gif" /></div>

                                    <h5 id="detailInformation"></h5>

                                </div>
                            </div>


                            <div class="sidebar_base"></div>
                        </div>
                    </div>

                    <div class="content">
                        <div class="content_item" id="searchdiv">
                            <h3>Search Document Repository</h3>

                            <div style="alignment-adjust:central;" >
                                <%String contextPath = request.getContextPath();
                                %>
                                <table border="0">
                                    <tbody >
                                        <s:form action="../documentVisibility/docSearch.action" method="post" name="documentForm" id="documentForm" theme="simple">

                                            <tr>
                                                <td class="lableLeft">Date From </td>
                                                <td><%-- <input type="text" id="datepickerfrom" /> --%>
                                                    <%--  <input type="text" name="datepickerfrom" id="datepickerfrom" class="inputStyle" tabindex="2" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="docdatepickerfrom" id="docdatepickerfrom"  value="%{docdatepickerfrom}" tabindex="1"  onkeypress="enterDate();"/>
                                                    <a href="javascript:copyValuTo('docdatepickerfrom','docdatepicker');"><img border="0" src="<%= contextPath%>/includes/images/lm/arrow.gif" width="7"
                                                                                                                               height="9"></a>
                                                </td>
                                                <td class="lableLeft">Date To </td>
                                                <td><%-- <input type="text" id="datepicker" /> --%>
                                                    <%--  <input type="text" name="datepicker" id="datepicker" class="inputStyle" tabindex="2" />  --%>
                                                    <s:textfield cssClass="inputStyle" name="docdatepicker"  value="%{docdatepicker}" id="docdatepicker" tabindex="2"  onkeypress="enterDate();"/>
                                                </td>
                                                <!-- Testing -->

                                                <!-- Testing -->
                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Sender Id</td>
                                                <td><%-- <input type="text"> --%>
                                                    <%-- <input type="text" name="senderId" id="senderId" class="inputStyle" tabindex="2" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="docSenderId" id="docSenderId" value="%{docSenderId}" tabindex="3"/>
                                                </td>
                                                <td class="lableLeft">Receiver Id</td>
                                                <td><%-- <input type="text"> --%>
                                                    <%--<input type="text" name="buId" id="buId" class="inputStyle" tabindex="2" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="docReceiverId" id="docReceiverId" value="%{docBusId}" tabindex="5"/>
                                                </td>

                                                <!-- Testing -->
                                            </tr>
                                            <tr>
                                                <!-- Testing -->
                                                <td class="lableLeft">Status</td>
                                                <td class="lableLeft">
                                                    <s:select headerKey="-1" headerValue="Select Type" list="{'Success','Error','Warning'}" name="status" id="status" value="%{status}" tabindex="10" cssStyle="width : 150px"/> 
                                                </td>

                                                <!-- Testing -->
                                                <td class="lableLeft">Ack Status</td>
                                                <td class="lableLeft">
                                                    <s:select headerKey="-1" headerValue="Select Type" list="{'Overdue','Accepted','Rejected'}" name="ackStatus" id="ackStatus" value="%{ackStatus}" tabindex="11" cssStyle="width : 150px"/> 
                                                    <!-- Testing -->
                                            </tr>

                                            <%--
                                            <tr>
                                           <td class="lableLeft">Correlation </td>
                                           <td> 
                                                    <s:select headerKey="-1" headerValue="Select Attribute" list="correlationList" name="corrattribute" id="corrattribute" value="%{corrattribute}" tabindex="7" cssStyle="width : 150px"/>
                                                
                                            </td>
                                            <td class="lableLeft">Value </td>
                                           <td> 
                                                <s:textfield cssClass="inputStyle" name="corrvalue" id="corrvalue" value="%{corrvalue}" tabindex="8"/>
                                            </td>
                                            
                                        </tr>
                                        <tr>
                                           <td class="lableLeft">Correlation </td>
                                           <td> 
                                                    <s:select headerKey="-1" headerValue="Select Attribute" list="correlationList" name="corrattribute1" id="corrattribute1" value="%{corrattribute1}" tabindex="7" cssStyle="width : 150px"/>
                                                
                                            </td>
                                            <td class="lableLeft">Value </td>
                                           <td> 
                                                <s:textfield cssClass="inputStyle" name="corrvalue1" id="corrvalue1" value="%{corrvalue1}" tabindex="8"/>
                                            </td>
                                            
                                        </tr>
                                        <tr>
                                           <td class="lableLeft">Correlation </td>
                                           <td> 
                                                    <s:select headerKey="-1" headerValue="Select Attribute" list="correlationList" name="corrattribute2" id="corrattribute2" value="%{corrattribute2}" tabindex="7" cssStyle="width : 150px"/>
                                                
                                            </td>
                                            <td class="lableLeft">Value </td>
                                           <td> 
                                                <s:textfield cssClass="inputStyle" name="corrvalue2" id="corrvalue2" value="%{corrvalue2}" tabindex="8"/>
                                            </td>
                                            
                                        </tr> --%>
                                            <%-- New search --%>

                                            <tr>


                                                <%--   <td>  <s:select cssClass="inputStyle" headerKey="-1" headerValue="Select   Type" list="{'850', '860', '855', '856','810','820'}" name="docType" id="docType" tabindex="9" cssStyle="width : 150px"/></td>   --%>
                                            </tr>
                                            <tr>
                                            </tr>
                                            <tr>  <%-- return compareDates(document.getElementById('docdatepickerfrom').value,document.getElementById('docdatepicker').value); --%>
                                                <td style="background-color: white;" colspan="2">
                                                    <s:submit value="Search" cssClass="button" onclick="return checkCorrelation();" tabindex="12"/>
                                                    <%-- </td>
                                                     <td style="background-color: white;">--%>
                                                    <%--  <s:reset value="Reset" cssClass="button"/> --%>
                                                    <strong><input type="button" value="Reset" class="button" tabindex="13" onclick="return resetvalues();"/></strong>
                                                </td>
                                                <s:hidden name="sampleValue" id="sampleValue" value="2"/>
                                            </tr>
                                        </tbody>




                                    </table> 

                                </div>
                                <%--  out.print("contextPath-->"+contextPath); --%>


                            </div>
                            <a><img src='../includes/images/dtp/cal_plus.gif' alt="nag" width="13" height="9" border="0" onclick="javascript:hideSearch()" id="fsCollImg"/></a>  
                        </div>


                        <s:if test="#session.searchResult != null"> 
                            <%--- GRid start --%>
                            <div class="content" id="gridDiv">
                                <div class="content_item">

                                    <table id="tblResSearch" cellpadding='1' cellspacing='1' border='0' class="gridTable" width='90%' align="center" >
                                        <tbody>

                                            <%
                                                resultCount = 0;
                                                if (session.getAttribute("searchResult") != null) {

                                                    searchResult = (List) session.getAttribute("searchResult");
                                                    //  out.println("searchResult size-->"+searchResult.size());
                                                    if (null != searchResult && searchResult.size() != 0) {
                                                        resultCount = searchResult.size();
                                                    }
                                                }
                                            %>
                                            <%
                                                if (searchResult.size() != 0) {
                                                    noOfPages = Integer.parseInt(session.getAttribute("noOfPages").toString());
                                            %>
                                            <tr><td colspan="12">
                                                    <img src="/ediscv/includes/images/green.jpg"/>&nbsp;Success & Resubmitted&nbsp;&nbsp;

                                                    <img src="/ediscv/includes/images/blue.png"/>&nbsp;Success&nbsp;&nbsp;
                                                    <img src="/ediscv/includes/images/red.jpg"/>&nbsp;Error&nbsp;&nbsp; 


                                                    <img src="/ediscv/includes/images/pink.jpg"/>&nbsp;Error & Resubmitted&nbsp;&nbsp;
                                                </td>
                                            </tr>
                                            <%}%> 
                                        <input type="hidden" name="sec_lt_list" id="sec_lt_list" value="30"/> 
                                        <%--    <tr>
                                              <td bgcolor="white" class="fieldLabelLeft" colspan="5">
                                                   Total Records : <%=resultCount%>
                                               </td>
                                               <td colspan="6" align="right" bgcolor="white" >
                                                   <input type="button" name="First" id="First" value="First" class="buttonBg" 
                                                          onclick="gridNext(this);" align="right">
                                                   <input type="button" name="Previous" id="Previous" value="Previous" class="buttonBg" 
                                                          onclick="gridNext(this);" align="right">  
                                                   <input type="button" name="Next" id="Next" value="Next" class="buttonBg" 
                                                          onclick="gridNext(this);" align="right">
                                                   <input type="button" name="Last" id="Last" value="Last" class="buttonBg" 
                                                          onclick="gridNext(this);" align="right">
                                               </td> 
                                           </tr> --%>

                                        <tr class="gridHeader">
                                            <%--   <td width="120" height="20px"><B>S.NO</B></td>
                                               <td width="120" height="40px">Resume Name</td>
                                               <td width="120" height="30px">Download</td> --%>
                                            <th style="text-align:center;white-space:nowrap">SNO</th>
                                            <th style="text-align:center;white-space:nowrap">InstanceId</th>
                                            <th style="text-align:center;white-space:nowrap">FileType</th>

                                            <th style="text-align:center;white-space:nowrap">Date&nbsp;Created</th>
                                                <%-- <td >ISA #</td>  --%>

                                            <%-- <td >DOC_ORIGIN</td> --%>
                                            <th style="text-align:center;white-space:nowrap">TransType</th>
                                            <!-- Commenting the Directions and displaying Sender Id in place : 15/01/2014 -->
                                            <%--<td >Direction</td>--%>
                                            <th style="text-align:center;white-space:nowrap">Sender Id</th>
                                            <!-- Commenting the Status and displaying Sender Name in place : 15/01/2014 -->
                                            <%--<td >Status</td>--%>
                                            <%--     <th style="text-align:center;white-space:nowrap">Sender&nbsp;Name</th> --%>
                                            <!-- Commenting the Ack Status and displaying ReceiverId in place : 16/01/2014 -->
                                            <!--<td >Ack&nbsp;Status</td> -->
                                            <th style="text-align:center;white-space:nowrap">Receiver&nbsp;Id</th>
                                            <!-- Commenting the Reprocess and displaying ReceiverName in place : 16/01/2014 -->
                                            <!--<td >Reprocess</td> -->
                                            <%--   <th style="text-align:center;white-space:nowrap">Receiver&nbsp;Name</th> --%>
                                            <!-- Commenting the check box and displaying ISA number and GS Number in place : 17/01/2014 -->
                                            <!-- <td>#</td> -->
                                            <th style="text-align:center;white-space:nowrap">IC&nbsp;#</th>
                                            <th style="text-align:center;white-space:nowrap">FC&nbsp;#</th>
                                            <th style="text-align:center;white-space:nowrap">MC&nbsp;#</th>
                                                <%--          <% 
                                                   if(session.getAttribute(AppConstants.SES_ROLE_ID).equals("100")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("104")){      
                                                %>
                                                     <th style="text-align:center;white-space:nowrap">&nbsp;&nbsp;&nbsp;#&nbsp;&nbsp;&nbsp;</th> 
                                                     <%}%> --%>
                                        </tr>

                                        <%

                                            if (request.getAttribute("strStartGrid") != null) {
                                                strStartGrid = request.getAttribute("strStartGrid").toString();
                                                strIntStartGrid = Integer.parseInt(strStartGrid);
                                            }
                                            //else{   strStartGrid = null;  }

                                            if (request.getAttribute("strEndGrid") != null) {
                                                strEndGrid = request.getAttribute("strEndGrid").toString();
                                                strIntEndGrid = Integer.parseInt(strEndGrid);
                                            }
                                        //else{   strEndGrid = null;   }
                                        %>                                                    


                                        <%
                                            if (session.getAttribute("searchResult") != null) {
                                        %>
                                        <input type="hidden" name="strIntStartGrid" id="strIntStartGrid" value="<%=strIntStartGrid%>"/> 
                                        <input type="hidden" name="strIntEndGrid" id="strIntEndGrid" value="<%=strIntEndGrid%>"/> 
                                        <%
                                            List searchResult = (List) session.getAttribute("searchResult");

                                            //resultCount = 0;
                                            if (null != searchResult) {
                                                resultCount = searchResult.size();
                                            }

                                            //if(request.getAttribute("strStartGrid") != "0"){
                                            for (int i = strIntStartGrid, j = 0; i < strIntEndGrid; i++, j++) {
                                                DocumentVisibilityBean documentVisibilityBean = (DocumentVisibilityBean) searchResult.get(i);
                                            // D:\ProjectFiles\resumes\2008\Sep\1\Ashvin_Veligandla.doc -- it looks

                                            //pathArray[j] = path;
                                                //out.println(pathArray[j]);
                                        /*
                                                 String[] pathArray = path.split("\\");
                                                 for(int index = 0;index<pathArray.length;index++) {
                                                 path = pathArray[index]+"\\"+"\\";
                                                 out.println(path);
                                                 }*/
                                            //out.println(path);
                                        %>
                                        <TR CLASS="gridRowEven">
                                            <td style="text-align:center;white-space:nowrap"><%=i + 1%></td>
                                            <td style="text-align:center;white-space:nowrap"><a style="color:#00B2FF;" href="javascript:getDetails('<%=documentVisibilityBean.getId()%>');">
                                                    <input type="hidden" name="Instance<%=i%>" id="Instance<%=i%>" value="<%=documentVisibilityBean.getInstanceId()%>"/>   
                                                    <input type="hidden" name="FileId<%=i%>" id="FileId<%=i%>" value="<%=documentVisibilityBean.getId()%>"/>  
                                                    <input type="hidden" name="text<%=i%>" id="text<%=i%>" value="<%=documentVisibilityBean.getTransaction_type()%>"/>
                                                    <%
                                                        //out.println(logisticsDocBean.getFile_id());
                                                        out.println(documentVisibilityBean.getInstanceId());
                                                    %>
                                                </a>
                                            </td>
                                            <td style="text-align:center;white-space:nowrap">
                                                <%
                                                    out.println(documentVisibilityBean.getFile_type());
                                                %>

                                            </td>                                  
                                            <%--                                               <%
                                          if(documentVisibilityBean.getStatus().equalsIgnoreCase("error")&&documentVisibilityBean.getReProcessStatus().equalsIgnoreCase("RESUBMITTED"))
                                             {%>
                                             
                                             <td style="text-align:center;white-space:nowrap">
                                                   
                                                   <a style="color:#C00067;" href="javascript:getDetails('<%=documentVisibilityBean.getId()%>','<%=documentVisibilityBean.getPoNumber()%>');">
                                                    <input type="hidden" name="Instance<%=i%>" id="Instance<%=i%>" value="<%=documentVisibilityBean.getInstanceId()%>"/>   
                                                   <input type="hidden" name="FileId<%=i%>" id="FileId<%=i%>" value="<%=documentVisibilityBean.getId()%>"/>  
                                                    <input type="hidden" name="text<%=i%>" id="text<%=i%>" value="<%=documentVisibilityBean.getTransaction_type()%>"/>
                                                    
                                                       <%
                                                   out.println(documentVisibilityBean.getInstanceId());
                                                   %>
                                                   </a>
                                           </td>
                                   <% }else if(documentVisibilityBean.getStatus().equalsIgnoreCase("error")){ %>
                                    <td style="text-align:center;white-space:nowrap"><a style="color:#C10003;" href="javascript:getDetails('<%=documentVisibilityBean.getId()%>','<%=documentVisibilityBean.getPoNumber()%>');">
                                                   <input type="hidden" name="Instance<%=i%>" id="Instance<%=i%>" value="<%=documentVisibilityBean.getInstanceId()%>"/>   
                                                   <input type="hidden" name="FileId<%=i%>" id="FileId<%=i%>" value="<%=documentVisibilityBean.getId()%>"/>  
                                                    <input type="hidden" name="text<%=i%>" id="text<%=i%>" value="<%=documentVisibilityBean.getTransaction_type()%>"/>
                                                       <%
                                                   //out.println(logisticsDocBean.getFile_id());
                                                   out.println(documentVisibilityBean.getInstanceId());
                                                   %>
                                                   </a>
                                           </td>
                                   <% } else if(documentVisibilityBean.getReProcessStatus().equalsIgnoreCase("RESUBMITTED")){ %>
                                           
                                <td style="text-align:center;white-space:nowrap"><a style="color:#14552B;" href="javascript:getDetails('<%=documentVisibilityBean.getId()%>','<%=documentVisibilityBean.getPoNumber()%>');">
                                                   <input type="hidden" name="Instance<%=i%>" id="Instance<%=i%>" value="<%=documentVisibilityBean.getInstanceId()%>"/>   
                                                   <input type="hidden" name="FileId<%=i%>" id="FileId<%=i%>" value="<%=documentVisibilityBean.getId()%>"/>  
                                                    <input type="hidden" name="text<%=i%>" id="text<%=i%>" value="<%=documentVisibilityBean.getTransaction_type()%>"/>
                                                       <%
                                                   //out.println(logisticsDocBean.getFile_id());
                                                   out.println(documentVisibilityBean.getInstanceId());
                                                   %>
                                                   </a>
                                           </td>
                                   <% } else {%>
                                   <td style="text-align:center;white-space:nowrap"><a style="color:#00B2FF;" href="javascript:getDetails('<%=documentVisibilityBean.getId()%>','<%=documentVisibilityBean.getPoNumber()%>');">
                                                   <input type="hidden" name="Instance<%=i%>" id="Instance<%=i%>" value="<%=documentVisibilityBean.getInstanceId()%>"/>   
                                                   <input type="hidden" name="FileId<%=i%>" id="FileId<%=i%>" value="<%=documentVisibilityBean.getId()%>"/>  
                                                    <input type="hidden" name="text<%=i%>" id="text<%=i%>" value="<%=documentVisibilityBean.getTransaction_type()%>"/>
                                                       <%
                                                   //out.println(logisticsDocBean.getFile_id());
                                                   out.println(documentVisibilityBean.getInstanceId());
                                                   %>
                                                   </a>
                                           </td>
                                   <%}%> 
                                            --%>



                                            <td><!-- testing -->
                                                <%
                                                    //out.println(logisticsDocBean.getDate_time_rec().toString().substring(0, logisticsDocBean.getDate_time_rec().toString().lastIndexOf(":")));
                                                    out.println(documentVisibilityBean.getDate_time_rec().toString().substring(2, documentVisibilityBean.getDate_time_rec().toString().lastIndexOf(":")));
                                                %>

                                            </td>   
                                            <td style="text-align:center;white-space:nowrap">
                                                <%
                                                    out.println(documentVisibilityBean.getTransaction_type());
                                                %>

                                            </td>
                                            <td style="text-align:center;white-space:nowrap">
                                                <%-- Commenting the "directions" in grid : modified on 15/01/2014
                                         <%
                                                 out.println(logisticsDocBean.getDirection());
                                         %>
                                                --%>
                                                <%-- Displaying senderId in place of direction in grid : Added on 15/01/2014 --%>
                                                <%
                                                    out.println(documentVisibilityBean.getSenderId());
                                                %>
                                            </td>  


                                            <%-- Commenting the Status in grid : modified on 15/01/2014
                                           <td>
                                           <%
                                              if(logisticsDocBean.getStatus().equalsIgnoreCase("ERROR")){       
                                                         out.println("<font color='red'>"+logisticsDocBean.getStatus()+"</font>");
                                                     }else if(logisticsDocBean.getStatus().equalsIgnoreCase("SUCCESS")){
                                                         out.println("<font color='green'>"+logisticsDocBean.getStatus()+"</font>");
                                                     }else {
                                                          out.println("<font color='orange'>"+logisticsDocBean.getStatus()+"</font>");
                                                       }
                                                       
                                           %>
                                                 
                                          </td>--%>
                                            <%-- Displaying sender Name in place of direction in grid : Added on 15/01/2014 --%>

                                            <%--   <td>
                                                       <%
                                                       
                                                         //out.println(logisticsDocBean.getAckStatus());
                                                         if(logisticsDocBean.getAckStatus().equalsIgnoreCase("REJECTED")){       
                                                              out.println("<font color='red'>"+logisticsDocBean.getAckStatus()+"</font>");
                                                          }else if(logisticsDocBean.getAckStatus().equalsIgnoreCase("ACCEPTED")){
                                                              out.println("<font color='green'>"+logisticsDocBean.getAckStatus()+"</font>");
                                                          }else {
                                                               out.println("<font color='orange'>"+logisticsDocBean.getAckStatus()+"</font>");
                                                            }
                                                                           
                                                         %>
                                                     </td> --%>
                                            <td style="text-align:center;white-space:nowrap">

                                                <%-- Commenting the Ack Status in grid : modified on 16/01/2014
                                                   <%
                                           //out.println(logisticsDocBean.getReProcessStatus());
                                           if(logisticsDocBean.getAckStatus()!=null){
                                                       out.println(logisticsDocBean.getAckStatus().toUpperCase());
                                                       
                                                   }else {
                                                        out.println("-");
                                                   }
                                           %>
                                                 
                                          </td>--%>
                                                <%-- Displaying Receiver id in grid : Added in 16/01/2014 --%>
                                                <%
                                                    out.println(documentVisibilityBean.getReceiverId());
                                                %>
                                            </td>

                                            <td style="text-align:center;white-space:nowrap">
                                                <%
                                                    out.println(documentVisibilityBean.getInterchange_ControlNo());
                                                %>
                                            </td>
                                            <td style="text-align:center;white-space:nowrap">
                                                <%
                                                    out.println(documentVisibilityBean.getFunctional_ControlNo());
                                                %>
                                            </td>
                                            <td style="text-align:center;white-space:nowrap">
                                                <%
                                                    out.println(documentVisibilityBean.getMessage_ControlNo());
                                                %>
                                            </td>
                                            <%--        <% 
                                                   if(session.getAttribute(AppConstants.SES_ROLE_ID).equals("100")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("104")){      
                                                %>
                                               
                                                
                                                <td style="text-align:center;white-space:nowrap"> 
                                                    <input type = "checkbox" name ="check_List" id = "check_List" value="<%= documentVisibilityBean.getId()%>" />&nbsp; &nbsp;  
                                                  </td>  
                                              
                                                  <%
                                                   }
                                                    %> --%>
                                            <%--   <TD class="title"><%=pathName.substring((pathName.lastIndexOf("/",pathName.length())+1),pathName.length())%></TD>
                                              
                                               <td class="title"><A href='javascript: getResumeDownload( "<%=path%>");'>Download/View</A></td>
                                            --%>
                                        </TR>

                                        <%
                                            }
                                        %>

                                        <tr>
                                            <td bgcolor="white" class="fieldLabelLeft" colspan="5">
                                                <%if (strIntEndGrid != resultCount) {%>
                                                Total Records : <%=resultCount%>&nbsp;Page <%=strIntEndGrid / 30%>  of <%=noOfPages%>
                                                <%} else {%>
                                                Total Records : <%=resultCount%>&nbsp;Page <%=noOfPages%>  of <%=noOfPages%>
                                                <%}%>
                                            </td>
                                            <td colspan="7" align="right" bgcolor="white" ><%    if (searchResult.size() != 0) {%>
                                                <input type="button" name="First" id="First" value="First" class="buttonBg" 
                                                       onclick="gridNext(this);" align="right">
                                                <input type="button" name="Previous" id="Previous" value="Previous" class="buttonBg" 
                                                       onclick="gridNext(this);" align="right"> 
                                                (<%=strIntStartGrid + 1%> - <%=strIntEndGrid%> of <%=resultCount%>)
                                                <input type="button" name="Next" id="Next" value="Next" class="buttonBg" 
                                                       onclick="gridNext(this);" align="right">
                                                <input type="button" name="Last" id="Last" value="Last" class="buttonBg" 
                                                       onclick="gridNext(this);" align="right">

                                                <s:select list="pageList" name="pageNumber" id="pageNumber" headerKey="select" headerValue="select" onchange="goToPage();" />
                                                <%}%>

                                            </td>
                                        </tr>
                                        <%-- <%}  %>--%>

                                        <%
                                            }
                                        %>

                                        <input type="hidden" name="txtStartGrid" value="<%=strStartGrid%>"/>
                                        <input type="hidden" name="txtEndGrid" value="<%=strEndGrid%>"/>
                                        <input type="hidden" name="txtMaxGrid" value="<%=resultCount%>"/>

                                        </tbody>
                                    </table>
                                    <div id="resubmitLoading" align="center" style="display:none">
                                        <!--  <img  src="../includes/images/ajax-loader.gif" /> -->
                                        <font color="red">Loading...Please wait..</font>
                                    </div>
                                </div>
                                <%-- Process butttons  start --%>
                                <%
                                    if (searchResult.size() != 0) {
                                %>
                                <table align="right" border="0">

                                    <tr>
                                        <%--          <%
                                            if(session.getAttribute(AppConstants.SES_ROLE_ID).equals("100")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("104") ) {
                                            %>
                                            <td style="background-color: white;">
                                            <strong><input type="button" value="ReSubmit" class="button" onmouseover="Tip('Click here to Resubmit.')" onmouseout="UnTip()" onclick="return doResubmit(document.getElementById('sec_lt_list').value);"/></strong>
                                            </td> 
                                       
                                            <%}%>
                                        --%>

                                        <td style="background-color: white;">
                                            <strong><input type="button" value="Generate Excel" class="button" onclick="return gridDownload('docVisibility', 'xls');" onmouseover="Tip('Click here to generate an excel Report.')" onmouseout="UnTip()" id="excel"/></strong>
                                        </td>
                                    </tr>
                                </table> 
                                <%}%>
                                <%-- process buttons end--%>
                                <%-- Grid End --%>

                            </div>
                        </s:if>

                    </s:form>

                </div> 

            </div>
        </div>

        <%--    <div id="footer">  --%>
    <footer> 
        <p><font color="white">&#169 <%= new java.text.SimpleDateFormat("yyyy").format(new java.util.Date())%> Miracle Software Systems, Inc. All rights reserved</font></p>
    </footer>   
    <%--   	</div> --%>

</body>
</html>



