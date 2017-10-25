<%-- 
    Document   : DocRepository
    Created on : Mar 11, 2013, 10:03:37 AM
    Author     : Nagireddy seerapu 
--%>
<%@page import="java.util.List"%>
<%@page import="com.mss.ediscv.logisticreports.LogisticReportsBean"%>
<%@page import="com.mss.ediscv.logisticsdoc.LogisticsDocBean"%>
<%-- <%@ page contentType="text/html" pageEncoding="UTF-8"%> --%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> --%>
<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

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
        <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
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
        src='<s:url value="/includes/js/tablesorter.min.js"/>'></script>
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

            function getDetails(val, ponum) {
                //  alert("hiiii");    

                getLogisticsDocDetails(val, ponum);
            }
            function checkDates()
            {
                //alert("hi");
                var docdatepickerfrom = document.getElementById('docdatepickerfrom').value;
                var docdatepicker = document.getElementById('docdatepicker').value;

                var res = compareDates(docdatepickerfrom, docdatepicker);

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
                document.getElementById('docType').value = "-1";
                /*document.getElementById('corrvalue').value=""; 
                 document.getElementById('docType').value="-1"; 
                 document.getElementById('corrattribute1').value="-1"; 
                 document.getElementById('corrvalue1').value=""; 
                 document.getElementById('corrattribute2').value="-1"; 
                 document.getElementById('corrvalue2').value=""; */

                document.getElementById('status').value = "-1";
                //document.getElementById('ackStatus').value="-1"; 

                $('#detail_box').hide();
                $('#gridDiv').hide();

            }
            /* $(document).ready(function() {
             $('ul.sf-menu').sooperfish();
             });*/

            function gridNext(c) {
                var b = c.id;
                var e = parseInt(document.documentForm.txtStartGrid.value);
                var a = parseInt(document.documentForm.txtEndGrid.value);
                var d = parseInt(document.documentForm.txtMaxGrid.value);
                if (b == "Next") {
                    if (a < d)
                    {
                        document.location = "nextLtReports.action?startValue=" + e + "&endValue=" + a + "&button=" + b
                    } else {
                        if (a == d) {
                            alert("You are already viewing last page!")
                        }
                    }
                } else {
                    if (b == "Previous")
                    {
                        if (e < d && e > 0) {
                            document.location = "nextLtReports.action?startValue=" + e + "&endValue=" + a + "&button=" + b
                        } else {
                            if (e == 0) {
                                alert("You are already viewing first page!")
                            }
                        }
                    } else {
                        if (b == "First") {
                            if (e < d && e > 0) {
                                e = 0;
                                a = 10;
                                document.location = "nextLtReports.action?startValue=" + e + "&endValue=" + a + "&button=" + b
                            } else {
                                if (e == 0) {
                                    alert("You are already viewing first page!")
                                }
                            }
                        } else {
                            if (b == "Last") {
                                if (a < d) {
                                    e = d - 10;
                                    a = d;
                                    document.location = "nextLtReports.action?startValue=" + e + "&endValue=" + a + "&button=" + b
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
                var startValue = ((parseInt(pageNumber) - 1) * 10);
                var endValue = parseInt(startValue) + 10;
                document.location = "nextLtReports.action?startValue=" + startValue + "&endValue=" + endValue + "&button=" + b

            }
        </script>




    </head>
    <%
        String check = null;
        if (request.getAttribute("check") != null) {
            check = request.getAttribute("check").toString();
        }
    %>
    <%!
        String strStartGrid;
        String strEndGrid;
        int resultCount = 0;
        int strIntStartGrid;
        int strIntEndGrid;
        List logdocumentList = null;
        int noOfPages = 0;

    %>
    <body onload="doOnLoad();
            setStyle('logisticCurrId', 'logisticreportsId');">
        <script type="text/javascript" src='<s:url value="/includes/js/wz_tooltip.js"/>'></script>
        <div id="wrapper">
            <div id="main">
                <header>
                    <div id="logo">
                        <s:include value="/includes/template/head.jsp"/>       

                        <gcse:search></gcse:search>
                    </div>
                    <nav>
                        <ul class="sf-menu" id="nav">

                            <s:include value="/includes/template/logisticsMenu.jsp"/>
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
                            <h3>Search Logistic Reports</h3>

                            <div &nbsp; style="alignment-adjust:central;" >
                                <%String contextPath = request.getContextPath();
                                %>
                                <table >
                                    <tbody >
                                        <s:form action="../logisticsReports/logisticreportsSearch.action" method="post" name="documentForm" id="documentForm" theme="simple">

                                            <tr>
                                                <td class="lableLeft">Date From </td>
                                                <td><%-- <input type="text" id="datepickerfrom" /> --%>
                                                    <%--  <input type="text" name="datepickerfrom" id="datepickerfrom" class="inputStyle" tabindex="2" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="docdatepickerfrom" id="docdatepickerfrom"  value="%{docdatepickerfrom}" tabindex="1"  onkeypress="return enterDateRepository();"/>
                                                    <a href="javascript:copyValuTo('docdatepickerfrom','docdatepicker');"><img border="0" src="<%= contextPath%>/includes/images/lm/arrow.gif" width="7"
                                                                                                                               height="9"></a>
                                                </td>
                                                <td class="lableLeft">Date To </td>
                                                <td><%-- <input type="text" id="datepicker" /> --%>
                                                    <%--  <input type="text" name="datepicker" id="datepicker" class="inputStyle" tabindex="2" />  --%>
                                                    <s:textfield cssClass="inputStyle" name="docdatepicker"  value="%{docdatepicker}" id="docdatepicker" tabindex="2"  onkeypress="return enterDateRepository();"/>
                                                </td>

                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Sender Id</td>
                                                <td><%-- <input type="text"> --%>
                                                    <%-- <input type="text" name="senderId" id="senderId" class="inputStyle" tabindex="2" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="docSenderId" id="docSenderId" value="%{docSenderId}" tabindex="3"/>
                                                </td>
                                                <td class="lableLeft">Sender Name</td>
                                                <td><%-- <input type="text"> --%>
                                                    <%--  <input type="text" name="senderName" id="senderName" class="inputStyle" tabindex="2" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="docSenderName" id="docSenderName" value="%{docSenderName}" tabindex="4"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Receiver Id</td>
                                                <td><%-- <input type="text"> --%>
                                                    <%--<input type="text" name="buId" id="buId" class="inputStyle" tabindex="2" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="docBusId" id="docBusId" value="%{docBusId}" tabindex="5"/>
                                                </td>
                                                <td class="lableLeft">Receiver Name</td>
                                                <td><%-- <input type="text"> --%>
                                                    <%-- <input type="text" name="recName" id="recName" class="inputStyle" tabindex="2" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="docRecName" id="docRecName" value="%{docRecName}" tabindex="6"/>
                                                </td>
                                            </tr>


                                            <%-- New search --%>

                                            <tr>
                                                <td class="lableLeft">Document Type</td>
                                                <td class="lableLeft">
                                                    <s:select headerKey="-1" headerValue="Select Type" list="docTypeList" name="docType" id="docType" value="%{docType}" tabindex="9" cssStyle="width : 150px"/>
                                                </td> 
                                                <td class="lableLeft">Status</td>
                                                <td class="lableLeft">
                                                    <s:select headerKey="-1" headerValue="Select Type" list="{'Success','Error','Warning'}" name="status" id="status" value="%{status}" tabindex="10" cssStyle="width : 150px"/> 
                                                </td>
                                                <%--   <td>  <s:select cssClass="inputStyle" headerKey="-1" headerValue="Select   Type" list="{'850', '860', '855', '856','810','820'}" name="docType" id="docType" tabindex="9" cssStyle="width : 150px"/></td>   --%>
                                            </tr>

                                            <tr>  <%-- return compareDates(document.getElementById('docdatepickerfrom').value,document.getElementById('docdatepicker').value); --%>
                                                <td style="background-color: white;" colspan="2">
                                                    <s:submit value="Search" cssClass="button" onclick="return checkDates();" tabindex="12" />
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
                            <a><img src='../includes/images/dtp/cal_plus.gif' alt="nag"width="13" height="9" border="0" onclick="javascript:hideSearch()" id="fsCollImg"/></a>  
                        </div>


                        <s:if test="#session.logdocumentList != null"> 
                            <%--- GRid start --%>
                            <div class="content" id="gridDiv">
                                <div class="content_item">
                                    <div class="grid_overflow" style="overflow-x:auto;">
                                        <%!String cssValue = "whiteStripe";
                                    int resultsetTotal;%>
                                        <table align="left" id="results" width="690px"
                                               border="0" cellpadding="0" cellspacing="0" class="CSSTableGenerator">

                                            <%
                                                resultCount = 0;
                                                if (session.getAttribute("logdocumentList") != null) {

                                                    logdocumentList = (List) session.getAttribute("logdocumentList");
                                                    //  out.println("searchResult size-->"+searchResult.size());
                                                    if (null != logdocumentList && logdocumentList.size() != 0) {
                                                        resultCount = logdocumentList.size();
                                                    }
                                                }
                                            %>
                                            <%
                                                if (logdocumentList.size() != 0) {
                                                    noOfPages = Integer.parseInt(session.getAttribute("noOfPages").toString());
                                            %>
                                            <%}%> 

                                            <input type="hidden" name="sec_lt_list" id="sec_lt_list" value="<%=logdocumentList.size()%>"/>
                                           <thead> <tr>
                                                <th >FileFormat</th> 
                                                <th >InstanceId</th>
                                                <th >Customer/Carrier</th>
                                                <th >DateTime</th>
                                                <th >TransType</th>
                                                <th >Direction</th>
                                                <th >Status</th>
                                                <th >TranStatus</th>
                                                <th >DocNumber </th>
                                            </tr></thead><tbody>

                                            <%

                                                if (request.getAttribute("strStartGrid") != null) {
                                                    strStartGrid = request.getAttribute("strStartGrid").toString();
                                                    System.out.println("strStartGrid----  " + strStartGrid);
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
                                                if (session.getAttribute("logdocumentList") != null) {
                                            %>
                                            <input type="hidden" name="strIntStartGrid" id="strIntStartGrid" value="<%=strIntStartGrid%>"/> 
                                            <input type="hidden" name="strIntEndGrid" id="strIntEndGrid" value="<%=strIntEndGrid%>"/> 

                                            <%
                                                List logdocumentList = (List) session.getAttribute("logdocumentList");

                                                //resultCount = 0;
                                                if (null != logdocumentList) {
                                                    resultCount = logdocumentList.size();
                                                }

                                                for (int i = strIntStartGrid, j = 0; i < strIntEndGrid; i++, j++) {
                                                    LogisticReportsBean logisticsReportBean = (LogisticReportsBean) logdocumentList.get(i);
                                                  //  logisticsLoadBean = (LogisticsLoadBean) list.get(i);
                                            %>

                                            <tr>
                                                <td>
                                                    <%
                                                        if (logisticsReportBean.getFile_type() != null) {
                                                            out.println(logisticsReportBean.getFile_type());
                                                        } else {
                                                            out.println("-");
                                                        }
                                                    %>

                                                </td>
                                                <td><%--<a href="javascript:getDetails('<%=logisticsReportBean.getFile_id()%>','<%=logisticsReportBean.getPoNumber()%>');"> --%>
                                                    <%
                                                        if (logisticsReportBean.getFile_id() != null) {
                                                            out.println(logisticsReportBean.getFile_id());
                                                        } else {
                                                            out.println("-");
                                                        }
                                                    %>
                                                    <%-- </a> --%>
                                                </td>
                                                <td>
                                                    <%
                                                        if (logisticsReportBean.getPname() != null) {
                                                            out.println(logisticsReportBean.getPname());
                                                        } else {
                                                            out.println("-");
                                                        }


                                                    %>

                                                </td>


                                                <td>
                                                    <%                                                            out.println(logisticsReportBean.getDate_time_rec().toString().substring(0, logisticsReportBean.getDate_time_rec().toString().lastIndexOf(":")));
                                                    %>

                                                </td>   
                                                <td>
                                                    <%
                                                        out.println(logisticsReportBean.getTransaction_type());
                                                    %>

                                                </td>
                                                <td>
                                                    <%
                                                        out.println(logisticsReportBean.getDirection());
                                                    %>

                                                </td>  


                                                <td>
                                                    <%
                                                        if (logisticsReportBean.getStatus() != null) {
                                                            if (logisticsReportBean.getStatus().equalsIgnoreCase("ERROR")) {
                                                                out.println("<font color='red'>" + logisticsReportBean.getStatus() + "</font>");
                                                            } else if (logisticsReportBean.getStatus().equalsIgnoreCase("SUCCESS")) {
                                                                out.println("<font color='green'>" + logisticsReportBean.getStatus() + "</font>");
                                                            } else {
                                                                out.println("<font color='orange'>" + logisticsReportBean.getStatus() + "</font>");
                                                            }
                                                        } else {
                                                            out.println("-");
                                                        }

                                                    %>

                                                </td>

                                                <td>
                                                    <%                                                                if (logisticsReportBean.getTransaction_type().equalsIgnoreCase("214")) {
                                                            if (logisticsReportBean.getTransactionStatus() == null) {
                                                                out.println("-");
                                                            } else if (logisticsReportBean.getTransactionStatus().equalsIgnoreCase("AA")) {

                                                                out.println(logisticsReportBean.getTransactionStatus() + "_pick_up appointment");
                                                            } else if (logisticsReportBean.getTransactionStatus().equalsIgnoreCase("AB")) {
                                                                out.println(logisticsReportBean.getTransactionStatus() + "_Delivery appointment");
                                                            } else if (logisticsReportBean.getTransactionStatus().equalsIgnoreCase("X3")) {
                                                                out.println(logisticsReportBean.getTransactionStatus() + "_Arrived at Pick_up Location");
                                                            } else if (logisticsReportBean.getTransactionStatus().equalsIgnoreCase("AF")) {
                                                                out.println(logisticsReportBean.getTransactionStatus() + "_Departed from pick_up Location");
                                                            } else if (logisticsReportBean.getTransactionStatus().equalsIgnoreCase("X1")) {
                                                                out.println(logisticsReportBean.getTransactionStatus() + "_Arrived at Delivery Location");
                                                            } else if (logisticsReportBean.getTransactionStatus().equalsIgnoreCase("D1")) {
                                                                out.println(logisticsReportBean.getTransactionStatus() + "_Completed Unloading Delivery Location");
                                                            } else if (logisticsReportBean.getTransactionStatus().equalsIgnoreCase("CD")) {
                                                                out.println(logisticsReportBean.getTransactionStatus() + "_Carrier Departed Delivery Location");
                                                            } else if (logisticsReportBean.getTransactionStatus().equalsIgnoreCase("SD")) {
                                                                out.println(logisticsReportBean.getTransactionStatus() + "_Shipment Delay");
                                                            } else if (logisticsReportBean.getTransactionStatus().equalsIgnoreCase("CP")) {
                                                                out.println(logisticsReportBean.getTransactionStatus() + "_COMPLETED LOADING AT PICKUP");
                                                            } else {
                                                                out.println("-");
                                                            }
                                                        } else if (logisticsReportBean.getTransaction_type().equalsIgnoreCase("204")) {
                                                            //  System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"+logisticsDocBean.getTransactionStatus());
                                                            if (logisticsReportBean.getTransactionStatus() == null) {
                                                                out.println("-");
                                                            } else if (logisticsReportBean.getTransactionStatus().equalsIgnoreCase("N")) {

                                                                out.println("New Order");
                                                            } else if (logisticsReportBean.getTransactionStatus().equalsIgnoreCase("U")) {
                                                                out.println("Update");
                                                            } else if (logisticsReportBean.getTransactionStatus().equalsIgnoreCase("C")) {
                                                                out.println("Cancel");
                                                            } else {
                                                                out.println("-");
                                                            }

                                                        } else if (logisticsReportBean.getTransaction_type().equalsIgnoreCase("990")) {
                                                            if (logisticsReportBean.getTransactionStatus() == null) {
                                                                out.println("-");
                                                            } else if (logisticsReportBean.getTransactionStatus().equalsIgnoreCase("A")) {

                                                                out.println("Accept");
                                                            } else if (logisticsReportBean.getTransactionStatus().equalsIgnoreCase("D")) {
                                                                out.println("Decline");
                                                            } else if (logisticsReportBean.getTransactionStatus().equalsIgnoreCase("C")) {
                                                                out.println("Cancel");
                                                            } else {
                                                                out.println("-");
                                                            }

                                                        } else if (logisticsReportBean.getTransaction_type().equalsIgnoreCase("210")) {
                                                            if (logisticsReportBean.getTransactionStatus() == null) {
                                                                out.println("-");
                                                            } else {
                                                                out.println(logisticsReportBean.getTransactionStatus());
                                                            }
                                                        } else {
                                                            //   System.out.println("else parthhhhhhh");
                                                            out.println("--");
                                                            // out.println(logisticsDocBean.getTransactionStatus());
                                                        }


                                                    %>

                                                </td>

                                                <td>
                                                    <%                                                            if ((logisticsReportBean.getTransaction_type().equalsIgnoreCase("214")) || (logisticsReportBean.getTransaction_type().equalsIgnoreCase("990"))) {
                                                            if (logisticsReportBean.getSce_key_val() != null) {
                                                                out.println(logisticsReportBean.getSce_key_val());
                                                            } else {
                                                                out.println("-");
                                                            }
                                                        } else if (logisticsReportBean.getTransaction_type().equalsIgnoreCase("204")) {
                                                            if (logisticsReportBean.getShipmentNumber() != null) {
                                                                out.println(logisticsReportBean.getShipmentNumber());
                                                            } else {
                                                                out.println("-");
                                                            }
                                                        } else if (logisticsReportBean.getTransaction_type().equalsIgnoreCase("210")) {
                                                            if (logisticsReportBean.getInv_Num() != null) {
                                                                out.println(logisticsReportBean.getInv_Num());
                                                            } else {
                                                                out.println("-");
                                                            }
                                                        }

                                                    %>

                                                </td>
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
                                                <%--<td>
                                                       <%
                                               //out.println(logisticsDocBean.getReProcessStatus());
                                               if(logisticsReportBean.getReProcessStatus()!=null){
                                                           out.println(logisticsReportBean.getReProcessStatus().toUpperCase());
                                                           
                                                       }else {
                                                            out.println("-");
                                                       }
                                               %>
                                                     
                                              </td>--%>



                                            </tr>
                                            <%                                                    }
                                            %>
                                       </tbody>     <tr>
                                                <td bgcolor="white" class="fieldLabelLeft" colspan="3" style="text-align: left; border: 0;">
                                                    <%if (strIntEndGrid != resultCount) {%>
                                                    Total Records : <%=resultCount%>&nbsp;Page <%=strIntEndGrid / 10%>  of <%=noOfPages%>
                                                    <%} else {%>
                                                    Total Records : <%=resultCount%>&nbsp;Page <%=noOfPages%>  of <%=noOfPages%>
                                                    <%}%>
                                                </td>
                                                <td colspan="7" align="right" bgcolor="white" style="text-align: right;border: 0;"><%    if (logdocumentList.size() != 0) {%>
                                                    <input type="button" name="First" id="First" value="<<" class="buttonBg" 
                                                           onclick="gridNext(this);" align="right">
                                                    <input type="button" name="Previous" id="Previous" value="<" class="buttonBg" 
                                                           onclick="gridNext(this);" align="right"> 
                                                    (<%=strIntStartGrid + 1%> - <%=strIntEndGrid%> of <%=resultCount%>)
                                                    <%--(<%=strIntStartGrid %> - <%=strIntEndGrid%> of <%=resultCount%>)--%>
                                                    <input type="button" name="Next" id="Next" value=">" class="buttonBg" 
                                                           onclick="gridNext(this);" align="right">
                                                    <input type="button" name="Last" id="Last" value=">>" class="buttonBg" 
                                                           onclick="gridNext(this);" align="right">

                                                    <s:select list="pageList" name="pageNumber" id="pageNumber" headerKey="select" headerValue="select" onchange="goToPage();" />
                                                    <%}%>

                                                </td>
                                            </tr>
                                            <%
                                                }

                                            %>
                                            <input type="hidden" name="txtStartGrid" value="<%=strStartGrid%>"/>
                                            <input type="hidden" name="txtEndGrid" value="<%=strEndGrid%>"/>
                                            <input type="hidden" name="txtMaxGrid" value="<%=resultCount%>"/>
                                        </table>

                                        <div id="resubmitLoading" align="center" style="display:none">
                                            <!--  <img  src="../includes/images/ajax-loader.gif" /> -->
                                            <font color="red">Loading...Please wait..</font>
                                        </div>

                                    </div>
                                    <%                                if (logdocumentList.size() != 0) {
                                    %>
                                    <table align="right">
                                        <tr>
                                            <td style="background-color: white;">
                                                <strong><input type="button" value="Generate Excel" class="button" onclick="return gridDownloadforLtReports('logisticsReport', 'xlsx');" onmouseover="Tip('Click here to generate an excel Report.')" onmouseout="UnTip()" id="excel"/></strong>
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
            <footer> 
                <p><font color="white">&#169 <%= new java.text.SimpleDateFormat("yyyy").format(new java.util.Date())%> Miracle Software Systems, Inc. All rights reserved</font></p>
            </footer>   
            <%--   	</div> --%>

    </body>
</html>
