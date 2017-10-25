<%-- 
    Document   : DashBoard
    Created on : Feb 19, 2015, 4:55:22 AM
    Author     : miracle
--%>


<%--<%@page import="com.mss.ediscv.reports.ReportsBean"%>--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> --%>
<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page buffer="50kb" autoFlush="true" %>

<%--<%@page import="com.mss.ediscv.doc.DocRepositoryBean"%>--%>

<%@page import="java.sql.Connection"%>
<%@  page import="com.mss.ediscv.util.AppConstants"%>


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
        src='<s:url value="/includes/js/common.js"/>'></script>
        <script language="JavaScript"
        src='<s:url value="/includes/js/modernizr-1.5.min.js"/>'></script>
        <script language="JavaScript"
        src='<s:url value="/includes/js/downloadAjax.js"/>'></script>
        <script language="JavaScript"
        src='<s:url value="/includes/js/overlay.js"/>'></script>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <link href="../includes/images/truck_image.jpg" rel="shortcut icon"/>

        <%-- <script>
                        var myCalendar;
                       function doOnLoad() {
                               myCalendar = new dhtmlXCalendarObject(["docdatepickerfrom","docdatepicker"]);
                               myCalendar.setSkin('omega');
                               myCalendar.setDateFormat("%m/%d/%Y %H:%i");
               
                       }
         </script> --%>
        <script>
            google.load("visualization", "1", {packages: ["corechart"]});

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



            function resetvalues()
            {

                document.getElementById('docdatepickerfrom').value = "";
                document.getElementById('docdatepicker').value = "";
                //document.getElementById('docBusId').value="";
                document.getElementById('partnerMapId').value = "ALL";

                document.getElementById('docType').value = "-1";


                document.getElementById('status').value = "-1";
                //document.getElementById('ackStatus').value="-1"; 

                $('#detail_box').hide();
                $('#gridDiv').hide();

            }


            /* $(document).ready(function() {
             $('ul.sf-menu').sooperfish();
             });*/






        </script>




    </head>

    <%--<body onload="doOnLoad();initDateTime('docdatepickerfrom','docdatepicker','<%=check %>');setStyle('docRep','');">  --%>

    <body onload="doOnLoad();
        setStyle('logisticreportsId', 'logisticdashboardCurrId');">
        <script type="text/javascript" src='<s:url value="/includes/js/wz_tooltip.js"/>'></script>
        <div id="wrapper">
            <div id="main">
                <header>
                    <div id="logo">
                        <s:include value="/includes/template/head.jsp"/>       


                    </div>
                    <nav>
                        <ul class="sf-menu" id="nav">

                            <s:include value="/includes/template/logisticsMenu.jsp"/>
                        </ul> 
                    </nav>
                </header>
                <div id="site_content">

                    <div id="erroroverlay"></div>
                    <!-- End Overlay -->
                    <!-- Start Special Centered Box -->
                    <div id="errorspecialBox" >
                        <s:form theme="simple">
                            <table style="width: 100%;">

                                <tr> 

                                    <td colspan="3" style="background-color: #88E2F8;">
                                        <p class="overlayHeaderFonts">Error&nbsp;Message</p>    
                                    </td>
                                </tr>
                                <tr>

                                    <td style="background-color: #88E2F8;">
                                        <span id="errorMessage"></span>

                                    </td>
                                    <td style="background-color: white;">
                                        <div class="closeButton">
                                            <a href="#" onmousedown="getErrorMessage();" class="button">OK</a>
                                        </div>
                                    </td>
                                </tr>    

                            </table>
                        </s:form>
                    </div>
                    <!-- end Special Centered Box -->



                    <div id="sidebar_container">


                        <div id="detail_box" style="display: none;"> 
                            <div class="sidebar">
                                <h3>Detail Information</h3>
                                <div class="sidebar_item">

                                    <!--    <div id="loadingImage" align="center"><img  src="../includes/images/ajax-loader.gif" /></div> -->

                                    <h5 id="detailInformation"></h5>

                                </div>
                            </div>


                            <div class="sidebar_base"></div>
                        </div>
                    </div>

                    <div class="content">
                        <div class="content_item" id="searchdiv">
                            <h3>Dashboard</h3>

                            <div style="alignment-adjust:central;" >
                                <%String contextPath = request.getContextPath();
                                %>
                                <table >
                                    <tbody >
                                        <s:form action="#" method="post" name="dashboardForm" id="dashboardForm" theme="simple">

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
                                            <%-- <tr>
                                                    <td class="lableLeft">Sender Id</td>
                                                    <td>
                                                       <s:textfield cssClass="inputStyle" name="docSenderId" id="docSenderId" value="%{docSenderId}" tabindex="3"/>
                                                    </td>
                                                   <td class="lableLeft">Receiver Id</td>
                                                    <td>
                                                        <s:textfield cssClass="inputStyle" name="docReceiverId" id="docReceiverId" value="%{docReceiverId}" tabindex="5"/>
                                                    </td>
                                            </tr> --%>

                                            <tr>
                                                <td class="lableLeft">TradingPartner&nbsp;Name:</td>
                                                <td >
                                                    <s:select  
                                                        name="partnerMapId" 
                                                        id="partnerMapId"
                                                        headerKey="ALL"
                                                        headerValue="ALL"
                                                        list="partnerMap" 
                                                        tabindex="13" cssStyle="width : 150px"
                                                        value="" />
                                                </td>
                                                <%--  <td class="lableLeft">Direction:</td>
                                            <td >
                                                <s:select list="{'ALL','INBOUND','OUTBOUND'}" name="direction" id="direction" value="%{direction}" tabindex="13" cssStyle="width : 150px"/>
                                                
                                            </td> --%>
                                                <td class="lableLeft">Document Type</td>
                                                <td class="lableLeft">
                                                    <%-- <s:select headerKey="-1" headerValue="Select Type" list="docTypeList" name="docType" id="docType" value="%{docType}" tabindex="13" cssStyle="width : 150px"/> --%>
                                                    <s:select headerKey="-1" headerValue="Select Type" list="docTypeList" name="docType" id="docType" value="%{docType}" tabindex="13" cssStyle="width : 150px"/>
                                                </td> 
                                            </tr>


                                            <tr>

                                                <td class="lableLeft">Status</td>
                                                <td class="lableLeft">
                                                    <s:select headerKey="-1" headerValue="Select Type" list="{'Success','Error','Warning'}" name="status" id="status" value="%{status}" tabindex="14" cssStyle="width : 150px"/> 
                                                </td>
                                                <%--   <td>  <s:select cssClass="inputStyle" headerKey="-1" headerValue="Select   Type" list="{'850', '860', '855', '856','810','820'}" name="docType" id="docType" tabindex="9" cssStyle="width : 150px"/></td>  
                                                  <td class="lableLeft">Ack Status</td>
                                                 <td class="lableLeft">
                                                    <s:select headerKey="-1" headerValue="Select Type" list="{'Overdue','Accepted','Rejected'}" name="ackStatus" id="ackStatus" value="%{ackStatus}" tabindex="15" cssStyle="width : 150px"/> </td> --%>
                                            </tr>

                                            <tr>  <%-- return compareDates(document.getElementById('docdatepickerfrom').value,document.getElementById('docdatepicker').value); --%>
                                                <td style="background-color: white;" colspan="2">
                                                    <%--     <s:submit value="Search" cssClass="button" tabindex="16"/> --%>
                                                    <strong><input type="button" value="Generate" class="button" tabindex="16" onclick="getDashboardDeatls();"/></strong>

                                                    <strong><input type="button" value="Reset" class="button" tabindex="17" onclick="return resetvalues();"/></strong>
                                                </td>
                                                <s:hidden name="sampleValue" id="sampleValue" value="2"/>
                                            </tr>
                                        </tbody>

                                    </s:form>


                                </table> 

                            </div>
                            <%--  out.print("contextPath-->"+contextPath); --%>


                        </div>
                        <a><img src='../includes/images/dtp/cal_plus.gif' alt="nag"width="13" height="9" border="0" onclick="javascript:hideSearch()" id="fsCollImg"/></a>  
                    </div>



                    <%--- GRid start --%>
                    <div class="content" id="gridDiv" style="display: none">
                        <div class="content_item">

                            <div id="loadingImage" align="center"><img  src="../includes/images/ajax-loader.gif" /></div>
                                <s:hidden id="inboundTrans" name="inboundTrans"/>
                                <s:hidden id="outboundTrans" name="outboundTrans"/>
                            <table id="tblCharts" style="display: none">
                                <tr><td><div id="inboundPiechart" style="width: 400px; height: 350px;"></div></td>
                                    <td><div id="outboundPiechart" style="width: 400px; height: 350px;"></div></td>
                                </tr>
                                <tr>  
                                    <td style="background-color: white;">
                                        <strong><input type="button" value="Generate Excel" class="button" onclick="return gridDashboardDownload('dash', 'xlsx', document.getElementById('inboundTrans').value, document.getElementById('outboundTrans').value);" onmouseover="Tip('Click here to generate an excel Report.')" onmouseout="UnTip()" id="excel"/> </strong>  
                                    </td>
                                    <td style="background-color: white;">
                                        <strong><input type="button" value="Generate PDF" class="button" onclick="return gridDashboardDownload('dash', 'pdf', document.getElementById('inboundTrans').value, document.getElementById('outboundTrans').value);" onmouseover="Tip('Click here to generate an excel Report.')" onmouseout="UnTip()" id="excel"/> </strong>  
                                    </td>
                                </tr>
                            </table>


                        </div>

                        <%-- process buttons end--%>
                        <%-- Grid End --%>

                    </div>



                </div> 
                <script type="text/javascript">
                    var pager = new Pager('results', 10);
                    pager.init();
                    pager.showPageNav('pager', 'pageNavPosition');
                    pager.showPage(1);
                </script>
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
