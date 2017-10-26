<%-- 
    Document   : DocRepository
    Created on : Mar 11, 2013, 10:03:37 AM
    Author     : Nagireddy seerapu 
--%>
<%@page import="java.util.List"%>
<%@page import="com.mss.ediscv.logisticsinvoice.LogisticsInvoiceBean"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> --%>
<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<%--<%@page import="com.mss.ediscv.doc.DocRepositoryBean"%>--%>
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
                myCalendar = new dhtmlXCalendarObject(["datepickerfrom", "datepickerTo"]);
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


            function checkCorrelation() {

                //   alert("hiii");
                var corrattr = document.getElementById('corrattribute').value;
                var corrval = document.getElementById('corrvalue').value;



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



                var res = compareDates(document.getElementById('datepickerfrom').value, document.getElementById('datepickerTo').value);

                // alert(res);
                return res;
            }
            function resetvalues()
            {
                document.getElementById('datepickerfrom').value = "";
                document.getElementById('datepickerTo').value = "";
                document.getElementById('invSenderId').value = "";
                document.getElementById('invSenderName').value = "";
                document.getElementById('invRecId').value = "";
                document.getElementById('invRecName').value = "";
                //document.getElementById('docIsa').value="";
                document.getElementById('corrattribute').value = "-1";
                document.getElementById('corrvalue').value = "";
                document.getElementById('corrattribute1').value = "-1";
                document.getElementById('corrvalue1').value = "";
                document.getElementById('docType').value = "-1";


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
                var e = parseInt(document.logisticsForm.txtStartGrid.value);
                var a = parseInt(document.logisticsForm.txtEndGrid.value);
                var d = parseInt(document.logisticsForm.txtMaxGrid.value);
                if (b == "Next") {
                    if (a < d)
                    {
                        document.location = "nextLtInvoice.action?startValue=" + e + "&endValue=" + a + "&button=" + b
                    } else {
                        if (a == d) {
                            alert("You are already viewing last page!")
                        }
                    }
                } else {
                    if (b == "Previous")
                    {
                        if (e < d && e > 0) {
                            document.location = "nextLtInvoice.action?startValue=" + e + "&endValue=" + a + "&button=" + b
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
                                document.location = "nextLtInvoice.action?startValue=" + e + "&endValue=" + a + "&button=" + b
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
                                    document.location = "nextLtInvoice.action?startValue=" + e + "&endValue=" + a + "&button=" + b
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
                document.location = "nextLtInvoice.action?startValue=" + startValue + "&endValue=" + endValue + "&button=" + b

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
        List ltInvoiceList = null;
        int noOfPages = 0;

    %>
    <body onload="doOnLoad();
            setStyle('logisticsinv', '');">
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
                            <h3>Search Lt&nbsp;Invoice</h3>

                            <div &nbsp; style="alignment-adjust:central;" >
                                <%String contextPath = request.getContextPath();
                                %>
                                <table >
                                    <tbody >
                                        <s:form action="../logisticsinvoice/invoiceSearch.action" method="post" name="logisticsForm" id="logisticsForm" theme="simple">

                                            <tr>
                                                <td class="lableLeft">Database&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;</td>
                                                <td><s:radio cssClass="myRadio" id="database" name="database" value="%{database}" list="#@java.util.LinkedHashMap@{'MSCVP':'Live','ARCHIVE':'Archive'}"/></td>
                                            </tr>                                            


                                            <tr>
                                                <td class="lableLeft"><s:label value="Date From"/> </td>
                                                <td><%-- <input type="text" id="datepickerfrom" /> --%>
                                                    <%--  <input type="text" name="datepickerfrom" id="datepickerfrom" class="inputStyle" tabindex="2" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="datepickerfrom" id="datepickerfrom"  value="%{datepickerfrom}" tabindex="1"  onkeypress="return enterDateInvoice();"/>
                                                    <a href="javascript:copyValuTo('datepickerfrom','datepickerTo');"><img border="0" src="<%= contextPath%>/includes/images/lm/arrow.gif" width="7"
                                                                                                                           height="9"></a>
                                                </td>
                                                <td class="lableLeft">Date To </td>
                                                <td><%-- <input type="text" id="datepicker" /> --%>
                                                    <%--  <input type="text" name="datepicker" id="datepicker" class="inputStyle" tabindex="2" />  --%>
                                                    <s:textfield cssClass="inputStyle" name="datepickerTo"  value="%{datepickerTo}" id="datepickerTo" tabindex="2"  onkeypress="return enterDateInvoice();"/>
                                                </td>

                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Sender Id</td>
                                                <td><%-- <input type="text"> --%>
                                                    <%-- <input type="text" name="senderId" id="senderId" class="inputStyle" tabindex="2" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="invSenderId" id="invSenderId" value="%{invSenderId}" tabindex="3"/>
                                                </td>
                                                <td class="lableLeft">Sender Name</td>
                                                <td><%-- <input type="text"> --%>
                                                    <%--  <input type="text" name="senderName" id="senderName" class="inputStyle" tabindex="2" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="invSenderName" id="invSenderName" value="%{invSenderName}" tabindex="4"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Receiver Id</td>
                                                <td><%-- <input type="text"> --%>
                                                    <%--<input type="text" name="buId" id="buId" class="inputStyle" tabindex="2" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="invRecId" id="invRecId" value="%{invRecId}" tabindex="5"/>
                                                </td>
                                                <td class="lableLeft">Receiver Name</td>
                                                <td><%-- <input type="text"> --%>
                                                    <%-- <input type="text" name="recName" id="recName" class="inputStyle" tabindex="2" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="invRecName" id="invRecName" value="%{invRecName}" tabindex="6"/>
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
                                            <%--    <tr>
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


                        <s:if test="#session.ltInvoiceList != null"> 
                            <%--- GRid start --%>
                            <div class="content" id="gridDiv">
                                <div class="content_item">
                                    <div class="grid_overflow" >
                                        <%!String cssValue = "whiteStripe";
                                            int resultsetTotal;%>
                                        <table align="left" id="results" width="690px"
                                               border="0" cellpadding="0" cellspacing="0" class="CSSTableGenerator">

                                            <%
                                                resultCount = 0;
                                                if (session.getAttribute("ltInvoiceList") != null) {

                                                    ltInvoiceList = (List) session.getAttribute("ltInvoiceList");
                                                    //  out.println("searchResult size-->"+searchResult.size());
                                                    if (null != ltInvoiceList && ltInvoiceList.size() != 0) {
                                                        resultCount = ltInvoiceList.size();
                                                    }
                                                }
                                            %>
                                            <%
                                                if (ltInvoiceList.size() != 0) {
                                                    noOfPages = Integer.parseInt(session.getAttribute("noOfPages").toString());
                                            %>
                                            <%}%> 

                                            <input type="hidden" name="sec_lt_list" id="sec_lt_list" value="<%=ltInvoiceList.size()%>"/>
                                            <thead>       <tr>
                                                    <!--<td >I</td>-->  
                                                    <th >Instance&nbsp;Id</th>
                                                    <th >Customer/Carrier</th>
                                                    <th style="width: 83px;">Invoice#</th>
                                                    <th>DateTime</th>
                                                    <th >Shipment</th>
                                                    <th >Item&nbsp;Qty</th>
                                                    <th >Inv&nbsp;Amount</th>
                                                    <th >Inv&nbsp;Date</th>
                                                    <th >Status</th>
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
                                                    if (session.getAttribute("ltInvoiceList") != null) {
                                                %>
                                            <input type="hidden" name="strIntStartGrid" id="strIntStartGrid" value="<%=strIntStartGrid%>"/> 
                                            <input type="hidden" name="strIntEndGrid" id="strIntEndGrid" value="<%=strIntEndGrid%>"/> 

                                            <%
                                                List ltInvoiceList = (List) session.getAttribute("ltInvoiceList");

                                                //resultCount = 0;
                                                if (null != ltInvoiceList) {
                                                    resultCount = ltInvoiceList.size();
                                                }

                                                for (int i = strIntStartGrid, j = 0; i < strIntEndGrid; i++, j++) {
                                                    LogisticsInvoiceBean logisticsInvoiceBean = (LogisticsInvoiceBean) ltInvoiceList.get(i);
                                                  //  logisticsLoadBean = (LogisticsLoadBean) list.get(i);
                                            %>

                                            <tr>
                                                <%--
                                                <td>

                                                    <%
                                                        if (logisticsInvoiceBean.getStatus().equalsIgnoreCase("SUCCESS")) {
                                                            //out.println("<font color='green'>"+logisticsInvoiceBean.getStatus().toUpperCase()+"</font>");
                                                            out.println("<img    src='../includes/images/greens.png'  height='15px' width='15px'/>");
                                                        }

                                                        if (logisticsInvoiceBean.getStatus().equalsIgnoreCase("ERROR")) {
                                                            if (logisticsInvoiceBean.getErrormsg().toLowerCase().contains("sid")) {
                                                                //  System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk nnnnnnnnnnnnnnnnnnn");
                                                                //out.println("red"+);

                                                                out.println("<img    src='../includes/images/reds.png'  height='15px' width='15px'/>");
                                                            } else {
                                                                //System.out.println("yellow");
                                                                out.println("<img   src='../includes/images/orange1.png' height='15px' width='15px'/>");
                                                            }
                                                        }
                                                    %>

                                                </td>
                                                --%>

                                                <td>
                                                    <%
                                                        if (logisticsInvoiceBean.getInstanceId() != null && !"".equals(logisticsInvoiceBean.getInstanceId())) {
                                                            out.println(logisticsInvoiceBean.getInstanceId());
                                                        } else {
                                                            out.println("-");
                                                        }
                                                    %>
                                                </td>
                                                <td>
                                                    <% //logisticsDocBean.getStatus() != null && !"".equals(logisticsDocBean.getStatus())
                                                        if (logisticsInvoiceBean.getPartner() != null && !"".equals(logisticsInvoiceBean.getPartner())) {
                                                            out.println(logisticsInvoiceBean.getPartner());
                                                        } else {
                                                            out.println("-");
                                                        }
                                                    %>
                                                </td>
                                                <td >  <a href="javascript:getLogisticsInvDetails('<%=logisticsInvoiceBean.getInvoiceNumber()%>','<%=logisticsInvoiceBean.getId()%>');"  >
                                                        <%
                                                            if (logisticsInvoiceBean.getInvoiceNumber() != null && !"".equals(logisticsInvoiceBean.getInvoiceNumber())) {
                                                                out.println(logisticsInvoiceBean.getInvoiceNumber());
                                                            } else {
                                                                out.println("-");
                                                            }


                                                        %>
                                                    </a>
                                                </td>
                                                <td>
                                                    <%                                                        if (logisticsInvoiceBean.getDate_time_rec() != null && !"".equals(logisticsInvoiceBean.getDate_time_rec())) {
                                                            out.println(logisticsInvoiceBean.getDate_time_rec().toString().substring(0, logisticsInvoiceBean.getDate_time_rec().toString().lastIndexOf(":")));
                                                        } else {
                                                            out.println("-");
                                                        }
                                                    %>

                                                </td>

                                                <td>
                                                    <%                        if (logisticsInvoiceBean.getShipmentId() != null && !"".equals(logisticsInvoiceBean.getShipmentId())) {
                                                            out.println(logisticsInvoiceBean.getShipmentId());
                                                        } else {
                                                            out.println("-");
                                                        }
                                                    %>
                                                </td>

                                                <%--  <td>
                                                     <%
                                             out.println(logisticsInvoiceBean.getPoNumber());
                                             %>
                                                 </td>--%>
                                                <td>
                                                    <%
                                                        if (logisticsInvoiceBean.getItemQty() != null && !"".equals(logisticsInvoiceBean.getItemQty())) {
                                                            out.println(logisticsInvoiceBean.getItemQty());
                                                        } else {
                                                            out.println("-");
                                                        }
                                                    %>

                                                </td>
                                                <td>
                                                    <%
                                                        if (logisticsInvoiceBean.getInvAmount() != null && !"".equals(logisticsInvoiceBean.getInvAmount())) {
                                                            out.println(logisticsInvoiceBean.getInvAmount());
                                                        } else {
                                                            out.println("-");
                                                        }
                                                    %>

                                                </td>
                                                <td>
                                                    <%
                                                        if (logisticsInvoiceBean.getInvDate() != null && !"".equals(logisticsInvoiceBean.getInvDate())) {
                                                            out.println(logisticsInvoiceBean.getInvDate());
                                                        } else {
                                                            out.println("-");
                                                        }
                                                    %>

                                                </td>
                                                <td>
                                                    <%
                                                        // out.println(invoiceBean.getStatus());
                                                        if (logisticsInvoiceBean.getStatus() != null && !"".equals(logisticsInvoiceBean.getStatus())) {
                                                            if (logisticsInvoiceBean.getStatus().equalsIgnoreCase("SUCCESS")) {
                                                                out.println("<font color='green'>" + logisticsInvoiceBean.getStatus().toUpperCase() + "</font>");
                                                            } else if (logisticsInvoiceBean.getStatus().equalsIgnoreCase("ERROR")) {
                                                                if (logisticsInvoiceBean.getErrormsg().toLowerCase().contains(" sid ")) {
                                                                    //System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk nnnnnnnnnnnnnnnnnnn");
                                                                    out.println("<a  href='javascript:errorOverlay(" + logisticsInvoiceBean.getIdFiles() + ")'><font color='red'>" + logisticsInvoiceBean.getStatus() + "</font> </a>");
                                                                } else {
                                                                    //System.out.println("yellow");
                                                                    out.println("<a href='javascript:errorOverlay(" + logisticsInvoiceBean.getIdFiles() + ")'><font color='orange'>" + logisticsInvoiceBean.getStatus() + "</font></a>");
                                                                }
                                                            } else {
                                                                out.println("<font color='orange'>" + logisticsInvoiceBean.getStatus().toUpperCase() + "</font>");
                                                            }
                                                        } else {
                                                            out.println("-");
                                                        }


                                                    %>
                                                </td>




                                            </tr>
                                            <%                                                    }
                                            %>
                                            </tbody><tr>
                                                <td bgcolor="white" class="fieldLabelLeft" colspan="3" style="text-align: left; border: 0;">
                                                    <%if (strIntEndGrid != resultCount) {%>
                                                    Total Records : <%=resultCount%>&nbsp;Page <%=strIntEndGrid / 10%>  of <%=noOfPages%>
                                                    <%} else {%>
                                                    Total Records : <%=resultCount%>&nbsp;Page <%=noOfPages%>  of <%=noOfPages%>
                                                    <%}%>
                                                </td>
                                                <td colspan="7" align="right" bgcolor="white" style="text-align: right;border: 0;"><%    if (ltInvoiceList.size() != 0) {%>
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
                                    <%                if (ltInvoiceList.size() != 0) {
                                    %>
                                    <table align="right">
                                        <tr>
                                            <td style="background-color: white;">
                                                <strong><input type="button" value="Generate Excel" class="button" onclick="return gridDownloadForLtInvoice('ltInvoice', 'xlsx');" onmouseover="Tip('Click here to generate an excel Report.')" onmouseout="UnTip()" id="excel"/></strong>
                                            </td>
                                        </tr>
                                    </table> 
                                    <%}%>
                                    <%-- process buttons end--%>
                                    <%-- Grid End --%>

                                </div>
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
