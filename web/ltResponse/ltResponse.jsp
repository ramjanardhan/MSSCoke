<%-- 
    Document   : ltResponse
    Created on : May 14, 2013, 8:40:39 AM
    Author     : Santosh
--%>

<%@page import="java.util.List"%>
<%@page import="com.mss.ediscv.ltResponse.LtResponseBean"%>
<%@page import="com.mss.ediscv.ltResponse.LtResponse"%>

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
        src='<s:url value="/includes/js/tablesorter.min.js"/>'></script>
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

            function getDetails(fileId, refId) {
                //  alert("hiiii");    

                getLtResponseDetails(fileId, refId);
            }
            function checkCorrelation() {

                //   alert("hiii");
                var corrattr = document.getElementById('corrattribute').value;
                var corrval = document.getElementById('corrvalue').value;

                var corrattr1 = document.getElementById('corrattribute1').value;
                var corrval1 = document.getElementById('corrvalue1').value;



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



                var res = compareDates(document.getElementById('datepickerfrom').value, document.getElementById('datepickerTo').value);

                // alert(res);
                return res;
            }
            function resetvalues()
            {
                document.getElementById('datepickerfrom').value = "";
                document.getElementById('datepickerTo').value = "";
                document.getElementById('senderId').value = "";
                document.getElementById('senderName').value = "";
                document.getElementById('receiverId').value = "";
                document.getElementById('receiverName').value = "";
                //document.getElementById('docIsa').value="";
                document.getElementById('corrattribute').value = "-1";
                document.getElementById('corrvalue').value = "";
                document.getElementById('docType').value = "-1";
                document.getElementById('corrattribute1').value = "-1";
                document.getElementById('corrvalue1').value = "";


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
                var e = parseInt(document.ltResponseForm.txtStartGrid.value);
                var a = parseInt(document.ltResponseForm.txtEndGrid.value);
                var d = parseInt(document.ltResponseForm.txtMaxGrid.value);
                if (b == "Next") {
                    if (a < d)
                    {
                        document.location = "nextLtResponse.action?startValue=" + e + "&endValue=" + a + "&button=" + b
                    } else {
                        if (a == d) {
                            alert("You are already viewing last page!")
                        }
                    }
                } else {
                    if (b == "Previous")
                    {
                        if (e < d && e > 0) {
                            document.location = "nextLtResponse.action?startValue=" + e + "&endValue=" + a + "&button=" + b
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
                                document.location = "nextLtResponse.action?startValue=" + e + "&endValue=" + a + "&button=" + b
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
                                    document.location = "nextLtResponse.action?startValue=" + e + "&endValue=" + a + "&button=" + b
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
                document.location = "nextLtResponse.action?startValue=" + startValue + "&endValue=" + endValue + "&button=" + b

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
        //System.out.println("check-->"+check);
        String strStartGrid;
        String strEndGrid;
        int resultCount = 0;
        int strIntStartGrid;
        int strIntEndGrid;
        List ltResponseList = null;
        int noOfPages = 0;

    %>
    <body onload="doOnLoad();
            setStyle('ltResponseMain', '');">
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
                            <h3>Search LT Response</h3>

                            <div &nbsp; style="alignment-adjust:central;" >
                                <%String contextPath = request.getContextPath();
                                %>
                                <table >
                                    <tbody >
                                        <s:form action="../ltResponse/doSearchltResponse.action" method="post" name="ltResponseForm" id="ltResponseForm" theme="simple">
                                            <tr>
                                                <td class="lableLeft">Database&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;</td>
                                                <td><s:radio cssClass="myRadio" id="database" name="database" value="%{database}" list="#@java.util.LinkedHashMap@{'MSCVP':'Live','ARCHIVE':'Archive'}"/></td>
                                            </tr>                                            

                                            <tr>
                                                <td class="lableLeft"><s:label value="Date From"/> </td>
                                                <td><%-- <input type="text" id="datepickerfrom" /> --%>
                                                    <%--  <input type="text" name="datepickerfrom" id="datepickerfrom" class="inputStyle" tabindex="2" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="datepickerfrom" id="datepickerfrom"  value="%{datepickerfrom}" tabindex="1"  onkeypress="return enterDateResponse();"/>
                                                    <a href="javascript:copyValuTo('datepickerfrom','docdatepickerTo');"><img border="0" src="<%= contextPath%>/includes/images/lm/arrow.gif" width="7"
                                                                                                                              height="9"></a>
                                                </td>
                                                <td class="lableLeft">Date To </td>
                                                <td><%-- <input type="text" id="datepicker" /> --%>
                                                    <%--  <input type="text" name="datepicker" id="datepicker" class="inputStyle" tabindex="2" />  --%>
                                                    <s:textfield cssClass="inputStyle" name="datepickerTo"  value="%{datepickerTo}" id="datepickerTo" tabindex="2"  onkeypress="return enterDateResponse();"/>
                                                </td>

                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Sender Id</td>
                                                <td><%-- <input type="text"> --%>
                                                    <%-- <input type="text" name="senderId" id="senderId" class="inputStyle" tabindex="2" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="senderId" id="senderId" value="%{senderId}" tabindex="3"/>
                                                </td>
                                                <td class="lableLeft">Sender Name</td>
                                                <td><%-- <input type="text"> --%>
                                                    <%--  <input type="text" name="senderName" id="senderName" class="inputStyle" tabindex="2" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="senderName" id="senderName" value="%{senderName}" tabindex="4"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Receiver Id</td>
                                                <td><%-- <input type="text"> --%>
                                                    <%--<input type="text" name="buId" id="buId" class="inputStyle" tabindex="2" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="receiverId" id="receiverId" value="%{receiverId}" tabindex="5"/>
                                                </td>
                                                <td class="lableLeft">Receiver Name</td>
                                                <td><%-- <input type="text"> --%>
                                                    <%-- <input type="text" name="recName" id="recName" class="inputStyle" tabindex="2" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="receiverName" id="receiverName" value="%{receiverName}" tabindex="6"/>
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
                            <a><img src='../includes/images/dtp/cal_plus.gif' alt="nag" width="13" height="9" border="0" onclick="javascript:hideSearch()" id="fsCollImg"/></a>  
                        </div>


                        <s:if test="#session.ltResponseList != null"> 
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
                                                if (session.getAttribute("ltResponseList") != null) {

                                                    ltResponseList = (List) session.getAttribute("ltResponseList");
                                                    //  out.println("searchResult size-->"+searchResult.size());
                                                    if (null != ltResponseList && ltResponseList.size() != 0) {
                                                        resultCount = ltResponseList.size();
                                                    }
                                                }
                                            %>
                                            <%
                                                if (ltResponseList.size() != 0) {
                                                    noOfPages = Integer.parseInt(session.getAttribute("noOfPages").toString());
                                            %>
                                            <%}%> 

                                            <input type="hidden" name="sec_lt_list" id="sec_lt_list" value="<%=ltResponseList.size()%>"/>
                                            <thead><tr>
                                                <th >FileFormat</th> 
                                                <th >InstanceId</th>
                                                <th >RCT Order#</th>
                                                <th>DateTime</th>
                                                <th >Shipment</th>
                                                <th >TransType</th>
                                                <th >Direction</th>
                                                <th >Status</th>
                                                <th>Response Status</th>
                                                <th>Customer/Carrier</th>

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
                                                if (session.getAttribute("ltResponseList") != null) {
                                            %>
                                            <input type="hidden" name="strIntStartGrid" id="strIntStartGrid" value="<%=strIntStartGrid%>"/> 
                                            <input type="hidden" name="strIntEndGrid" id="strIntEndGrid" value="<%=strIntEndGrid%>"/> 

                                            <%
                                                List ltResponseList = (List) session.getAttribute("ltResponseList");

                                                //resultCount = 0;
                                                if (null != ltResponseList) {
                                                    resultCount = ltResponseList.size();
                                                }

                                                for (int i = strIntStartGrid, j = 0; i < strIntEndGrid; i++, j++) {
                                                    LtResponseBean ltResponseBean = (LtResponseBean) ltResponseList.get(i);
                                                  //  logisticsLoadBean = (LogisticsLoadBean) list.get(i);
                                            %>


                                            <tr>
                                                <td>
                                                    <%
                                                        if (ltResponseBean.getFileType() != null) {
                                                            out.println(ltResponseBean.getFileType());
                                                        } else {
                                                            out.println("-");
                                                        }
                                                    %>

                                                </td>
                                                <%--<td><a href="javascript:getDetails('<%=ltResponseBean.getFileId()%>','<%=ltResponseBean.getRefId()%>');">
                                                    <%
                                                    out.println(ltResponseBean.getFileId());
                                                    %>
                                                    </a>
                                            </td>--%>

                                                <td>
                                                    <%
                                                        if (ltResponseBean.getFileId() != null) {
                                                            out.println(ltResponseBean.getFileId());
                                                        } else {
                                                            out.println("-");
                                                        }
                                                    %>

                                                </td>

                                                <td><a href="javascript:getDetails('<%=ltResponseBean.getFileId()%>','<%=ltResponseBean.getRefId()%>');">
                                                        <%
                                                            if (ltResponseBean.getRefId() != null) {
                                                                out.println(ltResponseBean.getRefId());
                                                            } else {
                                                                out.println("-");
                                                            }


                                                        %>
                                                    </a>     
                                                </td>

                                                <td>
                                                    <%                                                                out.println(ltResponseBean.getDate_time_rec().toString().substring(0, ltResponseBean.getDate_time_rec().toString().lastIndexOf(":")));
                                                    %>

                                                </td>

                                                <td>
                                                    <%                                                            if (ltResponseBean.getShipmentId() != null) {
                                                            out.println(ltResponseBean.getShipmentId());
                                                        } else {
                                                            out.println("-");
                                                        }


                                                    %>

                                                </td>

                                                <%--   <td>
                                                             <%
                                                     out.println(ltResponseBean.getDate_time_rec().toString().substring(0, logisticsShipmentBean.getDate_time_rec().toString().lastIndexOf(":")));
                                                     %>
                                                            
                                                    </td>  --%>

                                                <%-- <td> 
                                                         <%
                                                         out.println(docRepositoryBean.getIsa_number());
                                                         %>
                                                        
                                                     </td>  --%>



                                                <td>
                                                    <%                                                            out.println(ltResponseBean.getTransType());
                                                    %>

                                                </td>
                                                <td>
                                                    <%
                                                        out.println(ltResponseBean.getDirection());
                                                    %>

                                                </td>  


                                                <td>
                                                    <%
                                                        if (ltResponseBean.getStatus().equalsIgnoreCase("ERROR")) {
                                                            out.println("<font color='red'>" + ltResponseBean.getStatus() + "</font>");
                                                            //out.println("<a href='javascript:errorOverlay("+ltResponseBean.getId()+")'><font color='red'>" + ltResponseBean.getStatus() + "</font></a>");
                                                        } else if (ltResponseBean.getStatus().equalsIgnoreCase("SUCCESS")) {
                                                            out.println("<font color='green'>" + ltResponseBean.getStatus().toUpperCase() + "</font>");
                                                        } else {
                                                            out.println("<font color='orange'>" + ltResponseBean.getStatus().toUpperCase() + "</font>");
                                                        }

                                                    %>

                                                </td>

                                                <td>
                                                    <%                                                            if (ltResponseBean.getResponseStatus() != null) {
                                                            if (ltResponseBean.getResponseStatus().equalsIgnoreCase("A")) {
                                                                out.println("ACCEPT");
                                                            } else if (ltResponseBean.getResponseStatus().equalsIgnoreCase("D")) {
                                                                out.println("DECLINE");
                                                            }
                                                        }
                                                        // if(ltResponseBean.getResponseStatus().equalsIgnoreCase("A"))
                                                        //   {                          
                                                        // out.println("ACCEPT");
                                                        //     }
                                                        //  else if(ltResponseBean.getResponseStatus().equalsIgnoreCase("D")){

                                                        // out.println("DECLINE");
                                                        //  }
                                                    %>

                                                </td> 

                                                <td>
                                                    <%                                                            if (ltResponseBean.getPartnerName() != null) {
                                                            out.println(ltResponseBean.getPartnerName());
                                                        } else {
                                                            out.println("-");
                                                        }
                                                    %>

                                                </td> 
                                            </tr>
                                            <%
                                                }
                                            %>
                                        </tbody>  <tr>
                                                <td bgcolor="white" class="fieldLabelLeft" colspan="3" style="text-align: left; border: 0;">
                                                    <%if (strIntEndGrid != resultCount) {%>
                                                    Total Records : <%=resultCount%>&nbsp;Page <%=strIntEndGrid / 10%>  of <%=noOfPages%>
                                                    <%} else {%>
                                                    Total Records : <%=resultCount%>&nbsp;Page <%=noOfPages%>  of <%=noOfPages%>
                                                    <%}%>
                                                </td>
                                                <td colspan="7" align="right" bgcolor="white" style="text-align: right;border: 0;"><%    if (ltResponseList.size() != 0) {%>
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
                                    <%                                if (ltResponseList.size() != 0) {
                                    %>
                                    <table align="right">
                                        <tr>
                                            <td style="background-color: white;">
                                                <strong><input type="button" value="Generate Excel" class="button" onclick="return gridDownloadforLtresponse('ltResponse', 'xlsx');" onmouseover="Tip('Click here to generate an excel Report.')" onmouseout="UnTip()" id="excel"/></strong>
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

