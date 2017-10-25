<%-- 
    Document   : ltResponse
    Created on : May 14, 2013, 8:40:39 AM
    Author     : Santosh
--%>

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
        <link href="../includes/images/truck_image.jpg" rel="shortcut icon"/>

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
            function checkValues() {

                //   alert("hiii");

                //var res = false;
                var returnType = false;
                var days = document.getElementById('dayCount').value;
                var transactionType = document.getElementById('transType').value;
                if (transactionType == "-1")
                    transactionType = "All";
                if (days != "") {
                    var r = confirm("Confirm to delete " + days + " days before " + transactionType + " transaction records");
                    if (r == true)
                    {
                        returnType = true;
                    }
                    else
                    {
                        returnType = false;
                    }
                    return returnType;
                } else {
                    alert("Please enter days count !");
                    return false;
                }




                // var res = compareDates(document.getElementById('datepickerfrom').value,document.getElementById('datepickerTo').value);

                // alert(res);

            }
            function resetvalues()
            {
                // document.getElementById('datepickerfrom').value="";
                // document.getElementById('datepickerTo').value="";
                document.getElementById('dayCount').value = "";
                document.getElementById('transType').value = "-1";

                $('#detail_box').hide();
                $('#gridDiv').hide();

            }
            /* $(document).ready(function() {
             $('ul.sf-menu').sooperfish();
             });*/
        </script>




    </head>
    <%
        String check = null;
        if (request.getAttribute("check") != null) {
            check = request.getAttribute("check").toString();
        }

        //System.out.println("check-->"+check);
    %>
    <body onload="doOnLoad();
            setStyle('mainTp', 'purge');">
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

                            <%if (session.getAttribute(AppConstants.SES_USER_DEFAULT_FLOWID).toString().equals("2")) {%>
                            <s:include value="/includes/template/orderToCashMenu.jsp"/>
                            <%} else if (session.getAttribute(AppConstants.SES_USER_DEFAULT_FLOWID).toString().equals("3")) {%>
                            <s:include value="/includes/template/logisticsMenu.jsp"/>
                            <%}%>
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
                            <h3>Purge&nbsp;Process</h3>
                            <%
                                if (request.getAttribute(AppConstants.REQ_RESULT_MSG) != null) {
                                    String reqponseString = request.getAttribute(AppConstants.REQ_RESULT_MSG).toString();
                                    //request.getSession(false).removeAttribute("responseString");
                                    out.println(reqponseString);
                                }
                            %>
                            <div style="alignment-adjust:central;" >
                                <%String contextPath = request.getContextPath();
                                %>
                                <table >
                                    <tbody >
                                        <s:form action="../purge/purgeProcess.action" method="post" name="purgeProcessForm" id="purgeProcessForm" theme="simple">

                                            <%--  <tr>
                                                   <td class="lableLeft"><s:label value="Date From"/> </td>
                                                        <td>
                                                          <s:textfield cssClass="inputStyle" name="datepickerfrom" id="datepickerfrom"  value="%{datepickerfrom}" tabindex="1"  onkeypress="enterDate();"/>
                                                          <a href="javascript:copyValuTo('datepickerfrom','datepickerTo');"><img border="0" src="<%= contextPath%>/includes/images/lm/arrow.gif" width="7"
                                                                                                                        height="9"></a>
                                                        </td>
                                                <td class="lableLeft">Date To </td>
                                                        <td>
                                                          <s:textfield cssClass="inputStyle" name="datepickerTo"  value="%{datepickerTo}" id="datepickerTo" tabindex="2"  onkeypress="enterDate();"/>
                                                        </td>
                                                        
                                                </tr>
                                                <tr>
                                                        <td class="lableLeft">Sender Id</td>
                                                        <td>
                                                           <s:textfield cssClass="inputStyle" name="senderId" id="senderId" value="%{senderId}" tabindex="3"/>
                                                        </td>
                                                        <td class="lableLeft">Sender Name</td>
                                                        <td>
                                                          <s:textfield cssClass="inputStyle" name="senderName" id="senderName" value="%{senderName}" tabindex="4"/>
                                                        </td>
                                                </tr> --%>







                                            <tr>
                                                <td class="lableLeft">Day&nbsp;Count<font color="red">*</font></td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="dayCount" id="dayCount"  tabindex="3" onblur="return validatenumber(this);"/>
                                                </td>
                                                <td class="lableLeft">Transaction&nbsp;Type</td>
                                                <td class="lableLeft">
                                                    <s:select headerKey="-1" headerValue="Select Type" list="#@java.util.LinkedHashMap@{'204':'Loadtender','990':'Response','210':'Shipment','214':'Invoice'}" name="transType" id="transType"  tabindex="11" cssStyle="width : 150px"/> 
                                            </tr>
                                            <tr>  <%-- return compareDates(document.getElementById('docdatepickerfrom').value,document.getElementById('docdatepicker').value); --%>
                                                <td style="background-color: white;" colspan="2">
                                                    <s:submit value="Purge" cssClass="button" onclick="return checkValues();" tabindex="12"/>
                                                    <%-- </td>
                                                     <td style="background-color: white;">--%>
                                                    <%--  <s:reset value="Reset" cssClass="button"/> --%>
                                                    <strong><input type="button" value="Reset" class="button" tabindex="13" onclick="return resetvalues();"/></strong>
                                                </td>
                                                <s:hidden name="sampleValue" id="sampleValue" value="2"/>
                                            </tr>
                                        </tbody>

                                    </s:form>


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
                                <%!String cssValue = "whiteStripe";
                                    int resultsetTotal;%>

                                <table align="left" width="710px"
                                       border="0" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td style="background-color: white;">

                                            <table align="left" id="results" width="690px"
                                                   border="0" cellpadding="0" cellspacing="0" class="CSSTableGenerator">
                                                <%
                                                    java.util.List list = (java.util.List) session.getAttribute(AppConstants.SES_LTRESPONSE_LIST);

                                                    if (list.size() != 0) {
                                                        LtResponseBean ltResponseBean;
                                                %>
                                                <tr>
                                                    <%-- <td >ISA #</td>
                                                     <td >File Format</td>
                                                     <td>Direction</td>
                                                     <td >Date</td>
                                                     <td>Status</td>  --%>
                                                    <td >FileFormat</td> 
                                                    <td >InstanceId</td>
                                                    <td >Partner</td>

                                                    <%--   <td >DATETIME</td>
                                                        <td >ISA #</td>  --%>

                                                    <%-- <td >DOC_ORIGIN</td> --%>
                                                    <td >TransType</td>
                                                    <td >Direction</td>

                                                    <td >Status</td>
                                                    <td >AckStatus</td>  
                                                    <td >Reprocess</td>

                                                </tr>
                                                <tr>

                                                    <%
                                                        for (int i = 0; i < list.size(); i++) {
                                                            ltResponseBean = (LtResponseBean) list.get(i);

                                                            if (i % 2 == 0) {
                                                                cssValue = "whiteStripe";
                                                            } else {
                                                                cssValue = "grayEditSelection";
                                                            }
                                                    %>
                                                    <td>
                                                        <%
                                                            out.println(ltResponseBean.getFileType());
                                                        %>

                                                    </td>
                                                    <td><a href="javascript:getDetails('<%=ltResponseBean.getFileId()%>','<%=ltResponseBean.getRefId()%>');">
                                                            <%
                                                                out.println(ltResponseBean.getFileId());
                                                            %>
                                                        </a>
                                                    </td>
                                                    <td>
                                                        <%
                                                            out.println(ltResponseBean.getPartnerName());


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
                                                        <%                       out.println(ltResponseBean.getTransType());
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
                                                                out.println("<font color='red'>" + ltResponseBean.getStatus().toUpperCase() + "</font>");
                                                            } else if (ltResponseBean.getStatus().equalsIgnoreCase("SUCCESS")) {
                                                                out.println("<font color='green'>" + ltResponseBean.getStatus().toUpperCase() + "</font>");
                                                            } else {
                                                                out.println("<font color='orange'>" + ltResponseBean.getStatus().toUpperCase() + "</font>");
                                                            }

                                                        %>

                                                    </td>
                                                    <td>
                                                        <%                      if (ltResponseBean.getAckStatus().equalsIgnoreCase("REJECTED")) {
                                                                out.println("<font color='red'>" + ltResponseBean.getAckStatus().toUpperCase() + "</font>");
                                                            } else if (ltResponseBean.getAckStatus().equalsIgnoreCase("ACCEPTED")) {
                                                                out.println("<font color='green'>" + ltResponseBean.getAckStatus().toUpperCase() + "</font>");
                                                            } else {
                                                                out.println("<font color='orange'>" + ltResponseBean.getAckStatus().toUpperCase() + "</font>");
                                                            }

                                                        %>
                                                    </td> 
                                                    <td>
                                                        <%                       if (ltResponseBean.getReprocess() != null) {
                                                                out.println(ltResponseBean.getReprocess().toUpperCase());

                                                            } else {
                                                                out.println("-");
                                                            }
                                                        %>

                                                    </td>



                                                </tr>
                                                <%
                                                    }
                                                } else {
                                                %>
                                                <tr><td>
                                                        <%
                                                                // String contextPath = request.getContextPath();
                                                                // out.println("<img  border='0' align='top'  src='"+contextPath+"/includes/images/alert.gif'/><b> No Records Found to Display!</b>");
                                                                out.println("<img  border='0' align='top'  src='" + contextPath + "/includes/images/alert.gif'/><b>No records found for the given search criteria. Please try a different search criteria!</b>");
                                                            }

                                                        %>
                                                    </td>
                                                </tr>
                                            </table>

                                        </td>
                                    </tr>
                                    <%                 if (list.size() != 0) {
                                    %>
                                    <tr >
                                        <td align="right" colspan="28" style="background-color: white;">
                                            <div align="right" id="pageNavPosition">hello</div>
                                        </td>
                                    </tr> 
                                    <% }%>
                                </table>

                            </div>
                            <%-- Process butttons  start --%>
                            <%
                                if (list.size() != 0) {
                            %>
                            <table align="right">
                                <tr>
                                    <td style="background-color: white;">
                                        <strong><input type="button" value="Generate Excel" class="button" onclick="return gridDownload('ltResponse', 'xls');" onmouseover="Tip('Click here to generate an excel Report.')" onmouseout="UnTip()" id="excel"/></strong>
                                    </td>
                                </tr>
                            </table> 
                            <%}%>
                            <%-- process buttons end--%>
                            <%-- Grid End --%>

                        </div>
                    </s:if> 


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

