<%-- 
    Document   : DocRepository
    Created on : Mar 11, 2013, 10:03:37 AM
    Author     : Nagireddy seerapu 
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
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
<style>
    a
    {

        margin:2px;
        text-decoration:none;

    }
</style>
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
        <link rel="stylesheet" href='https://cdnjs.cloudflare.com/ajax/libs/paginationjs/2.0.8/pagination.css' type="text/css">
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
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
            <!--<script src="https://tablesorter.com/jquery.tablesorter.min.js"></script>-->
            <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>-->
            <!--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>-->
            <script language="JavaScript"
            src='<s:url value="/includes/js/jquery-ui.js"/>'></script>
        <script language="JavaScript"
        src='<s:url value="/includes/js/DBGrid.js"/>'></script>
        <script language="JavaScript"
        src='<s:url value="/includes/js/tablesorter.min.js"/>'></script>
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

            function getDetails(val, id) {
                getLogisticsDocDetails(val, id);
            }
            function checkCorrelation() {

                //   alert("hiii");
                var corrattr = document.getElementById('corrattribute').value;
                var corrval = document.getElementById('corrvalue').value;

                var corrattr1 = document.getElementById('corrattribute1').value;
                var corrval1 = document.getElementById('corrvalue1').value;

                var corrattr2 = document.getElementById('corrattribute2').value;
                var corrval2 = document.getElementById('corrvalue2').value;

                /*  if((corrattr!="-1")&&(corrattr1!="-1")&&(corrattr2!="-1")) {
                 
                 }*/

                //alert("corrattr-->"+corrattr+" corrval-->"+corrval);
                if ((corrattr != "-1") && (corrval == "")) {
                    alert("please enter Correlation Value!!!");
                    return false;
                }
                if ((corrattr == "-1") && (corrval != "")) {
                    alert("please select Correlation!");
                    return false;
                }

                if ((corrattr1 != "-1") && (corrval1 == "")) {
                    alert("please enter Correlation Value!!!");
                    return false;
                }
                if ((corrattr1 == "-1") && (corrval1 != "")) {
                    alert("please select Correlation!");
                    return false;
                }

                if ((corrattr2 != "-1") && (corrval2 == "")) {
                    alert("please enter Correlation Value!!!");
                    return false;
                }
                if ((corrattr2 == "-1") && (corrval2 != "")) {
                    alert("please select Correlation!");
                    return false;
                }

                var res = compareDates(document.getElementById('docdatepickerfrom').value, document.getElementById('docdatepicker').value);

                // alert(res);
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
                //document.getElementById('ackStatus').value="-1"; 

                $('#detail_box').hide();
                $('#gridDiv').hide();

            }
            /* $(document).ready(function() {
             $('ul.sf-menu').sooperfish();
             });*/



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
                        document.location = "nextLogisticDoc.action?startValue=" + e + "&endValue=" + a + "&button=" + b
                    } else {
                        if (a == d) {
                            alert("You are already viewing last page!")
                        }
                    }
                } else {
                    if (b == "Previous")
                    {
                        if (e < d && e > 0) {
                            document.location = "nextLogisticDoc.action?startValue=" + e + "&endValue=" + a + "&button=" + b
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
                                document.location = "nextLogisticDoc.action?startValue=" + e + "&endValue=" + a + "&button=" + b
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
                                    document.location = "nextLogisticDoc.action?startValue=" + e + "&endValue=" + a + "&button=" + b
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
                document.location = "nextLogisticDoc.action?startValue=" + startValue + "&endValue=" + endValue + "&button=" + b

            }

        </script>
        <script src="text/javascript">

        </script>



    </head>
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

                        <gcse:search></gcse:search>
                    </div>
                    <nav>
                        <ul class="sf-menu" id="nav">

                            <s:include value="/includes/template/logisticsMenu.jsp"/>
                        </ul> 
                    </nav>
                </header>
                <div id="site_content">


                    <div id="orginloadoverlay1"></div>

                    <div id="orginloadpecialBox1">

                        <table style="width: 40%; height: 20%;">

                            <tr class="overlayHeader">
                                <td style="background: #03d1f2;height: 30px"><span class="overlayHeaderFonts" style="font-weight: bold;color: white;">Error Message</span> 

                                    <span style="float: right;">  <a  href="#" onmousedown="errorOverlay()" style="color:white;font-family: myHeaderFonts;">CLOSE</a> </span>

                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <s:form action="" name="addnoti">
                                        <table style="width: 100%; margin-top: -39px">

                                            <tr>

                                                <td> <s:textarea rows="5" cols="48" name="ErrorMsg" id="ErrorMsg" class="selectBoxOverlay"/> </td>

                                            </tr>


                                        </table>
                                    </s:form>

                                </td>    
                            </tr>

                        </table>
                    </div>

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

                            <div &nbsp; style="alignment-adjust:central;" >
                                <%String contextPath = request.getContextPath();
                                %>
                                <table >
                                    <tbody >
                                        <s:form action="../logisticsdoc/docSearch.action" method="post" name="documentForm" id="documentForm" theme="simple">

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
                                                    <s:submit value="Search" cssClass="button" onclick="return checkCorrelation();" tabindex="12"/>
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


                        <s:if test="#session.searchResult != null">  
                            <%--- GRid start --%>
                            <div class="content" id="gridDiv" >
                                <div class="content_item">
                                    <div class="grid_overflow" style="overflow-x:auto;">
                                        <table align="left" id="results" width="690px" 
                                               border="0" cellpadding="0" cellspacing="0" class="CSSTableGenerator">

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
                                            <!--                                        <tr><td colspan="12">
                                                                                            <img src="/ediscv/includes/images/green.jpg"/>&nbsp;Success & Resubmitted&nbsp;&nbsp;
                                            
                                                                                            <img src="/ediscv/includes/images/blue.png"/>&nbsp;Success&nbsp;&nbsp;
                                                                                            <img src="/ediscv/includes/images/red.jpg"/>&nbsp;Error&nbsp;&nbsp; 
                                            
                                            
                                                                                            <img src="/ediscv/includes/images/pink.jpg"/>&nbsp;Error & Resubmitted&nbsp;&nbsp;
                                                                                        </td>
                                                                                    </tr>-->
                                            <%}%> 
                                            <input type="hidden" name="sec_lt_list" id="sec_lt_list" value="10"/> 


                                            <thead>
                                                <!--<td>SNO</td>-->



                                            <th>FileFormat</th>
                                            <th>InstId</th>
                                            <th>Customer/Carrier</th>
                                            <th>Shipment</th>
                                            <th>DateTime</th>
                                            <th>TransType</th>
                                            <th>Direction</th>
                                            <th>Status</th>
                                            <th>TransStatus</th>
                                            <th>RCT Order# </th>

                                            <!--                                            <th sortable="true">FileFormat</th>
                                                                                        <th sortable="true">InstId</th>
                                                                                        <th sortable="true">Customer Name</th>
                                                                                        <th sortable="true">Shipment</th>
                                                                                        <th sortable="true">DateTime</th>
                                                                                        <th sortable="true">TransType</th>
                                                                                        <th sortable="true">Direction</th>
                                                                                        <th sortable="true">Status</th>
                                                                                        <th sortable="true">TransStatus</th>
                                                                                        <th sortable="true">RCT Order# </th>-->

                                            <!--                                            <display:column title="FileFormat" sortable="true"   />
                                                                                        <display:column title="InstId" sortable="true"   />
                                                                                        <display:column title="Customer Name" sortable="true"   />
                                                                                        <display:column title="Shipment" sortable="true"   />
                                                                                        <display:column title="DateTime" sortable="true"   />
                                                                                        <display:column title="TransType" sortable="true"   />
                                                                                        <display:column title="Direction" sortable="true"   />
                                                                                        <display:column title="Status" sortable="true"   />
                                                                                        <display:column title="TransStatus" sortable="true"   />
                                                                                        <display:column title="RCT Order#" sortable="true"   />-->

                                            <!--                                            <th><display:column title="FileFormat" sortable="true"   /></th>
                                                                                        <th><display:column title="InstId" sortable="true"   /></th>
                                                                                        <th><display:column title="Customer Name" sortable="true"   /></th>
                                                                                        <th><display:column title="Shipment" sortable="true"   /></th>
                                                                                        <th><display:column title="DateTime" sortable="true"   /></th>
                                                                                        <th><display:column title="TransType" sortable="true"   /></th>
                                                                                        <th><display:column title="Direction" sortable="true"   /></th>
                                                                                        <th><display:column title="Status" sortable="true"   /></th>
                                                                                        <th><display:column title="TransStatus" sortable="true"   /></th>
                                                                                        <th><display:column title="RCT Order#" sortable="true"   /></th>-->

                                            </thead>
                                            <tbody>
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

                                                for (int i = strIntStartGrid, j = 0; i < strIntEndGrid; i++, j++) {
                                                    LogisticsDocBean logisticsDocBean = (LogisticsDocBean) searchResult.get(i);

                                            %>

                                            <tr CLASS="gridRowEven">
                                                <%--  <td><%=i + 1%></td>--%>

                                                <td>
                                                    <%                                                        if (logisticsDocBean.getFile_type() != null) {

                                                            out.println(logisticsDocBean.getFile_type());
                                                        } else {
                                                            out.println("-");
                                                        }
                                                    %>

                                                </td>
                                                <td><a href="javascript:getDetails('<%=logisticsDocBean.getFile_id()%>','<%=logisticsDocBean.getId()%>');">
                                                        <%

                                                            if (logisticsDocBean.getFile_id() != null) {

                                                                out.println(logisticsDocBean.getFile_id());
                                                            } else {
                                                                out.println("-");
                                                            }

                                                        %>
                                                    </a>
                                                </td>
                                                <td>
                                                    <%                                                        if (logisticsDocBean.getPname() != null) {
                                                            out.println(logisticsDocBean.getPname());
                                                        } else {
                                                            out.println("-");
                                                        }

                                                    %>

                                                </td>

                                                <td>
                                                    <%                                                        if (logisticsDocBean.getShipmentId() != null) {
                                                            out.println(logisticsDocBean.getShipmentId());
                                                        } else {
                                                            out.println("-");
                                                        }

                                                    %>

                                                </td>

                                                <td>
                                                    <%                                                        out.println(logisticsDocBean.getDate_time_rec().toString().substring(0, logisticsDocBean.getDate_time_rec().toString().lastIndexOf(":")));
                                                    %>

                                                </td>   
                                                <td>
                                                    <%
                                                        if (logisticsDocBean.getTransaction_type().equalsIgnoreCase("204")) {

                                                            out.println(logisticsDocBean.getTransaction_type() + "-LoadTender");

                                                        } else if (logisticsDocBean.getTransaction_type().equalsIgnoreCase("990")) {

                                                            out.println(logisticsDocBean.getTransaction_type() + "-LoadTender Response");

                                                        } else if (logisticsDocBean.getTransaction_type().equalsIgnoreCase("214")) {

                                                            out.println(logisticsDocBean.getTransaction_type() + "-Shipment Status");

                                                        } else if (logisticsDocBean.getTransaction_type().equalsIgnoreCase("210")) {

                                                            out.println(logisticsDocBean.getTransaction_type() + "-Invoice");

                                                        }

                                                    %>

                                                </td>
                                                <td>
                                                    <%                                                        out.println(logisticsDocBean.getDirection());
                                                    %>

                                                </td>  


                                                <td>
                                                    <%
                                                        if (logisticsDocBean.getStatus() != null) {
                                                            if (logisticsDocBean.getStatus().equalsIgnoreCase("ERROR")) {

                                                                out.println("<font color='red'>" + logisticsDocBean.getStatus() + "</font>");
                                                                //out.println("<a href='javascript:errorOverlay("+logisticsDocBean.getId()+")'><font color='red'>" + logisticsDocBean.getStatus() + "</font></a>");
                                                            } else if (logisticsDocBean.getStatus().equalsIgnoreCase("SUCCESS")) {
                                                                out.println("<font color='green'>" + logisticsDocBean.getStatus() + "</font>");
                                                            } else {
                                                                out.println("<font color='orange'>" + logisticsDocBean.getStatus() + "</font>");
                                                            }
                                                        } else {
                                                            out.println("-");
                                                        }
                                                    %>

                                                </td>

                                                <td>
                                                    <%
                                                        if (logisticsDocBean.getTransaction_type().equalsIgnoreCase("214")) {
                                                            if (logisticsDocBean.getTransactionStatus() == null) {
                                                                out.println("-");
                                                            } else if (logisticsDocBean.getTransactionStatus().equalsIgnoreCase("AA")) {

                                                                out.println(logisticsDocBean.getTransactionStatus() + "_pick_up appointment");
                                                            } else if (logisticsDocBean.getTransactionStatus().equalsIgnoreCase("AB")) {
                                                                out.println(logisticsDocBean.getTransactionStatus() + "_Delivery appointment");
                                                            } else if (logisticsDocBean.getTransactionStatus().equalsIgnoreCase("X6")) {
                                                                out.println(logisticsDocBean.getTransactionStatus() + "_Enroute to Delivery Location");
                                                            } else if (logisticsDocBean.getTransactionStatus().equalsIgnoreCase("X3")) {
                                                                out.println(logisticsDocBean.getTransactionStatus() + "_Arrived at Pick_up Location");
                                                            } else if (logisticsDocBean.getTransactionStatus().equalsIgnoreCase("AF")) {
                                                                out.println(logisticsDocBean.getTransactionStatus() + "_Departed from pick_up Location");
                                                            } else if (logisticsDocBean.getTransactionStatus().equalsIgnoreCase("X1")) {
                                                                out.println(logisticsDocBean.getTransactionStatus() + "_Arrived at Delivery Location");
                                                            } else if (logisticsDocBean.getTransactionStatus().equalsIgnoreCase("D1")) {
                                                                out.println(logisticsDocBean.getTransactionStatus() + "_Completed Unloading Delivery Location");
                                                            } else if (logisticsDocBean.getTransactionStatus().equalsIgnoreCase("CD")) {
                                                                out.println(logisticsDocBean.getTransactionStatus() + "_Carrier Departed Delivery Location");
                                                            } else if (logisticsDocBean.getTransactionStatus().equalsIgnoreCase("SD")) {
                                                                out.println(logisticsDocBean.getTransactionStatus() + "_Shipment Delay");
                                                            } else if (logisticsDocBean.getTransactionStatus().equalsIgnoreCase("CP")) {
                                                                out.println(logisticsDocBean.getTransactionStatus() + "_COMPLETED LOADING AT PICKUP");
                                                            } else {
                                                                out.println("-");
                                                            }
                                                        } else if (logisticsDocBean.getTransaction_type().equalsIgnoreCase("204")) {
                                                            //  System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"+logisticsDocBean.getTransactionStatus());
                                                            if (logisticsDocBean.getTransactionStatus() == null) {
                                                                out.println("-");
                                                            } else if (logisticsDocBean.getTransactionStatus().equalsIgnoreCase("N")) {

                                                                out.println("New Order");
                                                            } else if (logisticsDocBean.getTransactionStatus().equalsIgnoreCase("U")) {
                                                                out.println("Update");
                                                            } else if (logisticsDocBean.getTransactionStatus().equalsIgnoreCase("C")) {
                                                                out.println("Cancel");
                                                            } else {
                                                                out.println("-");
                                                            }

                                                        } else if (logisticsDocBean.getTransaction_type().equalsIgnoreCase("990")) {
                                                            if (logisticsDocBean.getTransactionStatus() == null) {
                                                                out.println("-");
                                                            } else if (logisticsDocBean.getTransactionStatus().equalsIgnoreCase("A")) {

                                                                out.println("Accept");
                                                            } else if (logisticsDocBean.getTransactionStatus().equalsIgnoreCase("D")) {
                                                                out.println("Decline");
                                                            } else if (logisticsDocBean.getTransactionStatus().equalsIgnoreCase("C")) {
                                                                out.println("Cancel");
                                                            } else {
                                                                out.println("-");
                                                            }

                                                        } else if (logisticsDocBean.getTransaction_type().equalsIgnoreCase("210")) {
                                                            if (logisticsDocBean.getTransactionStatus() == null) {
                                                                out.println("-");
                                                            } else {
                                                                out.println(logisticsDocBean.getTransactionStatus());
                                                            }
                                                        } else {
                                                            //   System.out.println("else parthhhhhhh");
                                                            out.println("--");
                                                            // out.println(logisticsDocBean.getTransactionStatus());
                                                        }


                                                    %>

                                                </td>

                                                <td>
                                                    <%                                                        if (logisticsDocBean.getOrdernum() != null) {

             
                                                                out.println(logisticsDocBean.getOrdernum());

                                                            

                                                        }else{
                                                        out.println("-");
                                                    }
                                                    %>


                                                </td>
                                            </tr>
                                            <%
                                                }
                                            %>
                                            </tbody>
                                            <tr>
                                                <td bgcolor="white" class="fieldLabelLeft" colspan="5" style="text-align: left; border: 0;">
                                                    <%if (strIntEndGrid != resultCount) {%>
                                                    Total Records : <%=resultCount%>&nbsp;Page <%=strIntEndGrid / 10%>  of <%=noOfPages%>
                                                    <%} else {%>
                                                    Total Records : <%=resultCount%>&nbsp;Page <%=noOfPages%>  of <%=noOfPages%>
                                                    <%}%>
                                                </td>
                                                <td colspan="7" align="right" bgcolor="white" style="text-align: right;border: 0;"><%    if (searchResult.size() != 0) {%>
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
                                        </table>                                     </div>
                                    <div id="resubmitLoading" align="center" style="display:none">
                                        <!--  <img  src="../includes/images/ajax-loader.gif" /> -->
                                        <font color="red">Loading...Please wait..</font>
                                    </div>
                                </div>
                                <!--<div align="right" id="pageNavPosition" style="margin-right: 0vw;display: none"></div>-->
                                <%-- Process butttons  start --%>
                                <%
                                    if (searchResult.size() != 0) {
                                %>
                                <table align="right">
                                    <tr>
                                        <td style="background-color: white;">
                                            <strong><input type="button" value="Generate Excel" class="button" onclick="return gridDownloadforLtdocrepo('logisticsDoc', 'xlsx');" onmouseover="Tip('Click here to generate an excel Report.')" onmouseout="UnTip()" id="excel"/></strong>
                                        </td>
                                    </tr>
                                </table> 
                                <%}%>

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
