<%-- 
    Document   : Reports
    Created on : Feb 6, 2015, 12:37:20 PM
    Author     : miracle
--%>

<%@page import="com.mss.ediscv.reports.ReportsBean"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> --%>
<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page buffer="50kb" autoFlush="true" %>

<%@page import="com.mss.ediscv.doc.DocRepositoryBean"%>

<%@page import="java.sql.Connection"%>
<%@  page import="com.mss.ediscv.util.AppConstants"%>


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
        <script language="JavaScript"
        src='<s:url value="/includes/js/overlay.js"/>'></script>
        <script>
            var myCalendar;
            function doOnLoad() {//schdatepicker,reportfromdate,reporttodate
                myCalendar = new dhtmlXCalendarObject(["schdatepicker", "reportfromdate", "reporttodate"]);
                myCalendar.setSkin('omega');
                myCalendar.setDateFormat("%m/%d/%Y %H:%i");

            }
        </script>
        <script type="text/javascript">

            function checkDates()
            {

                var docdatepickerfrom = document.getElementById('docdatepickerfrom').value;
                var docdatepicker = document.getElementById('docdatepicker').value;
                if (docdatepickerfrom == "" && docdatepicker == "") {
                    alert("Please enter Date From and Date To");
                    return false;
                }
                var res = compareDates(docdatepickerfrom, docdatepicker);

                return res;
            }

            function checkUservalues() {

                //alert("hiii");
                var schtitle = document.getElementById('schtitle').value;
                var schType = document.getElementById('schType').value;
                var schhours = document.getElementById('schhours').value;
                // var schhrFormat = document.getElementById('schhrFormat').value;
                var userEmail = document.getElementById('userEmail').value;
                var reportsType = document.getElementById('reportsType').value;

                /*  if((corrattr!="-1")&&(corrattr1!="-1")&&(corrattr2!="-1")) {
                 
                 }*/

                //alert("corrattr-->"+corrattr+" corrval-->"+corrval);
                if (schtitle == "") {
                    alert("please enter schtitle Value!!!");
                    return false;
                }
                if (schType == "-1") {
                    alert("please select schType!");
                    return false;
                }

                if (schhours == "-1") {
                    alert("please enter schhours Value!!!");
                    return false;
                }
                // if(schhrFormat=="AM") {
                // alert("please select schhrFormat!");
                //   return false;
                // }

                if (userEmail == "") {
                    alert("please enter userEmail Value!!!");
                    return false;
                }
                if (reportsType == "-1") {
                    alert("please select reportsType!");
                    return false;
                }

                //var res = compareDates(document.getElementById('docdatepickerfrom').value,document.getElementById('docdatepicker').value);

                // alert(res);
                //return res;
            }

            function Reportstype()
            {
                //alert("hi");
                var reporttype = document.getElementById('reportsType').value;
                if (reporttype == "EditrackingIn/Out" || reporttype == "EditrackingSummary" || reporttype == "EditrackingInquiry" || reporttype == "Dashbord") {
                    alert("Undaer processing(InActive)");
                    return false;
                }
            }

            function resetvalues()
            {

                document.getElementById('schtitle').value = "";
                document.getElementById('schType').value = "-1";
                document.getElementById('schhours').value = "-1";
                document.getElementById('schhrFormat').value = "AM";
                document.getElementById('extranalmailids').value = "";
            }
            document.getElementById('reportsType').value = "";
            /* $(document).ready(function() { Reportstype
             $('ul.sf-menu').sooperfish();
             });*/

            function getUserList() {
                // var tpid = document.getElementById("tpid").value;
                // var tpname = document.getElementById("tpname").value;
                location.href = "../partner/getSchedularsearch.action";
                return true;
            }
        </script>  
    </head>

    <%--<body onload="doOnLoad();initDateTime('docdatepickerfrom','docdatepicker','<%=check %>');setStyle('docRep','');">  --%>

    <body onload="doOnLoad();
            setStyle('mainTp', 'schdular');">
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
                            <%--<s:include value="/includes/template/orderToCashMenu.jsp"/>
                          <s:include value="/includes/template/mainMenu.jsp"/> --%>
                            <%if (session.getAttribute(AppConstants.SES_USER_DEFAULT_FLOWID).toString().equals("2")) {%>
                            <s:include value="/includes/template/orderToCashMenu.jsp"/>
                            <%} else if (session.getAttribute(AppConstants.SES_USER_DEFAULT_FLOWID).toString().equals("3")) {%>
                            <s:include value="/includes/template/logisticsMenu.jsp"/>
                            <%}%>
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

                                    <div id="loadingImage" align="center"><img  src="../includes/images/ajax-loader.gif" /></div>

                                    <h5 id="detailInformation"></h5>

                                </div>
                            </div>


                            <div class="sidebar_base"></div>
                        </div>
                    </div>
                    <%-- <s:hidden name="id" value="%(id}" id="id"/>--%>
                    <s:hidden name="userPageId" value="%{userPageId}" id="userPageId"/>
                    <div class="content" id="gridDiv">
                        <div class="content_item">
                            <%
                                // out.println("pageId-->"+request.getAttribute("tppageId").toString());
                                if (request.getAttribute("userPageId").toString().equals("0")) {
                            %>
                            <h3>Schdular Creation</h3>    
                            <%} else {%>
                            <h3>Update Schdular</h3> 
                            <%}%>
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
                                        <s:form action="%{currentAction}" method="post" name="schdularForm" id="schdularForm" theme="simple">
                                            <s:hidden name="id" value="%{id}" id="id"/>
                                            <tr span style="background-color: #FFFFcc"></tr>   

                                            <tr>

                                                <td class="lableLeft">Schedule&nbsp;Title </td>
                                                <td colspan="4"><%-- <input type="text" id="datepickerfrom" /> --%>
                                                    <%--  <input type="text" name="datepickerfrom" id="datepickerfrom" class="inputStyle" tabindex="2" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="schtitle" id="schtitle"  value="%{schtitle}" tabindex="1" cssStyle="width : 500px"/>

                                                </td>
                                            </tr>
                                            <tr>

                                                <td class="lableLeft">Schedule&nbsp;Type</td>
                                                <td class="lableLeft"> 
                                                    <s:select headerKey="-1" headerValue="All" list="{'Daily','Weekely'}" name="schType" id="schType" value="%{schType}" tabindex="2" cssStyle="width : 150px" onchange="Showfun();"/>

                                                </td>     
                                                <td class="lableLeft">Schedule&nbsp;Time</td>
                                                <td class="lableLeft">   
                                                    <s:select headerKey="-1" headerValue="HH" list="{'01','02','03','04','05','06','07','08','09','10','11','12'}" name="schhours" id="schhours" value="%{schhours}" tabindex="2" cssStyle="width : 50px"/>
                                                    <s:select  list="{'AM','PM'}" name="schhrFormat" id="schhrFormat" value="%{schhrFormat}" tabindex="2" cssStyle="width : 50px"/>
                                                    <%--<s:select headerKey="-1" headerValue="MM" list="{'00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22',23,'24','25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40','41','42','43','44','45','46','47','48','49','50','51','52','53','54','55','56','57','58','59','60'}" name="schminutes" id="schminutes" value="%{schminutes}" tabindex="2" cssStyle="width : 150px"/>     --%>
                                                </td>
                                            </tr>                             

                                            <tr> 
                                                <td class="lableLeft">Active Users </td>
                                                <td>
                                                    <s:select headerKey="-1" multiple="true" required="true" list="userMap" name="userEmail" id="userEmail" value="receiverids" tabindex="6" cssStyle="width : 180px"/>
                                                </td>              
                                                <td class="lableLeft">ExternalUserEmailIds </td>
                                                <td colspan="4">
                                                    <s:textarea cssClass="inputStyle" name="extranalmailids" id="extranalmailids"  value="%{extranalmailids}" tabindex="7" cssStyle="width : 180px" onchange="SchEmailValidator(this);checkEmails(this);"/>

                                                </td> 	
                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Reports&nbsp;Type</td>
                                                <td class="lableLeft"> 
                                                    <s:select headerKey="-1" headerValue="All" list="{'ExcelReport','EditrackingIn/Out','EditrackingSummary','EditrackingInquiry','Dashbord'}" name="reportsType" id="reportsType" value="%{reportsType}" tabindex="2" cssStyle="width : 150px" onchange="Reportstype();"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="background-color: white;" colspan="3">
                                                    <%
                                                        // out.println("pageId-->"+request.getAttribute("tppageId").toString());
                                                        if (request.getAttribute("userPageId").toString().equals("0")) {
                                                    %>
                                                    <s:submit value="Add" cssClass="button" onclick="return checkUservalues();" tabindex="8"/>
                                                    <strong><input type="button" value="Reset" class="button" tabindex="9" onclick="return resetvalues();"/></strong>
                                                    <strong><input type="button" value="BackToList" class="button" onclick="return getUserList();" tabindex="9"/></strong>
                                                        <%} else {%>
                                                        <s:submit value="Update" cssClass="button" tabindex="8"/> 
                                                    <strong><input type="button" value="BackToList" class="button" onclick="return getUserList();" tabindex="9"/></strong>       

                                                    <%}%>
                                                </td>
                                            </tr>
                                        </tbody>

                                    </s:form>


                                </table> 

                            </div>
                            <%--  out.print("contextPath-->"+contextPath); --%>


                        </div>
                        <a><img src='../includes/images/dtp/cal_plus.gif' alt="nag"width="13" height="9" border="0" onclick="javascript:hideSearch()" id="fsCollImg"/></a>  
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
