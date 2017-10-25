<%-- 
    Document   : Reports
    Created on : Feb 6, 2015, 12:37:20 PM
    Author     : miracle
--%>

<%@page import="com.mss.ediscv.schdular.SchdularBean"%>
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
            function doOnLoad() {
                myCalendar = new dhtmlXCalendarObject(["schStartdate"]);
                myCalendar.setSkin('omega');
                myCalendar.setDateFormat("%m/%d/%Y %H:%i");

            }
        </script>
        <style>
            div#overlay {
                display: none;
                z-index: 2;
                background: #000;
                position: fixed;
                width: 100%;
                height: 100%;
                top: 0px;
                left: 0px;
                text-align: center;
            }
            div#specialBox {
                display: none;
                position: absolute;
                z-index: 3;
                margin: 10px auto 0px auto;
                width: 403px; 
                height: 200;
                background: #FFF;
                left: 38%;

                color: #000;
            }







        </style>
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



            /*function resetvalues()
             {
     
             document.getElementById('docSenderId').value="";
             document.getElementById('docSenderName').value="";
             document.getElementById('docBusId').value="";
             document.getElementById('docRecName').value="";
     
     
             $('#detail_box').hide();
             $('#gridDiv').hide();
     
             }*/
            /* $(document).ready(function() {
             $('ul.sf-menu').sooperfish();
             });*/

            function goToSchdularReport() {
                window.location = "../partner/getSchedularAdd.action";
            }


            function getReportDelete(id) {
                //alert("hiiii"+id);    

                getDeleteReport(id);


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
                    <!-- end Special Centered Box  -->



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
                            <h3>Schdular Task</h3>
                            <!-- Overlay end -->
                            <div id="overlay"></div>              
                            <div id="specialBox">
                                <s:form theme="simple"  align="center" name="ProjectResources" action="%{currentAction}" method="post" enctype="multipart/form-data" onsubmit="return validateForm();"   >
                                    <table align="center" border="0" cellspacing="0" style="width:100%;">
                                        <tr style="background-color: #8ce2f7;">                               
                                            <td colspan="3" style="background-color: #8ce2f7">
                                                <h3 style="color:darkblue;" align="left">
                                                    <span id="headerLabel"></span>

                                                    <td colspan="3" style="background-color: #8ce2f7;position: absolute; right: 0px;" align="right">
                                                        <a href="#" onclick="BDMOverlay('0')" >
                                                            <img src="../includes/images/dtp/close.gif" /> 
                                                        </a>  
                                                    </td>
                                                </h3>
                                            </td>
                                        </tr>
                                        <tr>

                                            <td class="lableLeft">Date From </td>
                                            <td><%-- <input type="text" id="datepickerfrom" /> --%>
                                                <%--  <input type="text" name="datepickerfrom" id="datepickerfrom" class="inputStyle" tabindex="2" /> --%>
                                                <s:textfield cssClass="inputStyle" name="schStartdate" id="schStartdate"  value="%{schStartdate}" tabindex="1"  onclick="CalenderOnChange(this);" onblur="return enterSchedulerDates();"/>
                                                <s:hidden name="scheduleid" id="scheduleid" value="2"/>
                                                <a href="javascript:copyValuTo('schStartdate');"></a>
                                            </td>
                                            <td>
                                                <label id="downloadLink"> 

                                                    <s:a href="javascript:DownloadSchedulerReport()" onmouseover="Tip('Click here to download Report.')" onmouseout="UnTip()">
                                                        <%

                                                            out.println("download");
                                                        %> 
                                                    </s:a>
                                                </label>
                                                <label id="downloadMessage"></label>
                                            </td>

                                        </tr>
                                    </table>
                                </s:form> 
                            </div>
                            <div style="alignment-adjust:central;" >
                                <%String contextPath = request.getContextPath();
                                %>


                                <table>
                                    <tbody >
                                        <s:form action="../partner/getSchedularsearch.action" method="post" name="reportsattachForm" id="reportsattachForm" theme="simple">  


                                            <tr>
                                                <td class="lableLeft">Status</td>
                                                <td class="lableLeft"> 
                                                    <s:select  list="{'Active','InActive'}" name="status" id="status" value="%{status}" tabindex="2" cssStyle="width : 150px" />

                                                </td> 
                                            </tr>
                                            <tr>  <%-- return compareDates(document.getElementById('docdatepickerfrom').value,document.getElementById('docdatepicker').value); --%>
                                                <td style="background-color: white;" colspan="2">
                                                    <s:submit value="Search" cssClass="button" tabindex="16"/>

                                                    <strong><input type="button" value="Add" class="button" tabindex="33" onclick="goToSchdularReport();"/></strong>
                                                </td>
                                                <s:hidden name="sampleValue" id="sampleValue" value="2"/>
                                            </tr>
                                        </tbody>

                                    </s:form>


                                </table> 

                            </div>



                            <%--  out.print("contextPath-->"+contextPath); --%>


                        </div>
                        <%--  <a><img src='../includes/images/dtp/cal_plus.gif' alt="nag"width="13" height="9" border="0" onclick="javascript:hideSearch()" id="fsCollImg"/></a>  --%> 
                    </div>

                    <s:if test="#session.schdularList != null"> 
                        <%--   <div class="content" id="gridDiv"> --%>
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
                                                java.util.List list = (java.util.List) session.getAttribute(AppConstants.SES_SCHDULAR_LIST);

                                                if (list.size() != 0) {
                                                    SchdularBean schdularBean;
                                            %>
                                            <tr>
                                                <td>Title</td>                
                                                <td>Type</td> 
                                                <td>Schedular Time</td>
                                                <td>Status</td>
                                                <td>Delete</td>
                                                <td>Download</td>
                                            </tr>
                                            <tr>

                                                <%
                                                    for (int i = 0; i < list.size(); i++) {
                                                        schdularBean = (SchdularBean) list.get(i);

                                                        if (i % 2 == 0) {
                                                            cssValue = "whiteStripe";
                                                        } else {
                                                            cssValue = "grayEditSelection";
                                                        }
                                                %>

                                                <td><%-- <a href="#"> --%>
                                                    <%
                                                        int id = schdularBean.getId();
                                                    %>
                                                    <s:url var="myUrl" action="../partner/getSchedularEdit.action">
                                                        <s:param name="id"><%=id%></s:param>                        
                                                        <s:param name="status" value="%{status}"></s:param>  
                                                        <%--    <s:param name="tpid" value="%{tpid}"></s:param>
                                                            <s:param name="tpname" value="%{tpname}"></s:param> --%>

                                                    </s:url>

                                                    <s:a href='%{#myUrl}' onmouseover="Tip('Click here to Edit Partner.')" onmouseout="UnTip()">
                                                        <%

                                                            out.println(schdularBean.getSchtitle());
                                                        %> 
                                                    </s:a>

                                                    <%--  </a> --%>
                                                </td>


                                                <td>
                                                    <%
                                                        out.println(schdularBean.getSchtype());
                                                    %>

                                                </td>   

                                                <td>
                                                    <%
                                                        out.println(schdularBean.getSchhrFormat());
                                                    %>

                                                </td>
                                                <td>
                                                    <%
                                                        out.println(schdularBean.getStatus());
                                                    %>

                                                </td> 


                                                <td>

                                                    <a href="javascript:getReportDelete('<%=schdularBean.getId()%>');">

                                                        <%
                                                            out.println("delete");
                                                        %>
                                                    </a>
                                                </td>

                                                <td> <a href="javascript:BDMOverlay('<%=schdularBean.getId()%>');">
                                                        <%
                                                            out.println("Download");
                                                        %>
                                                    </a>
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

                                        <div id="load" style="color: green;display: none;">Loading..</div>
                                        <div id="resultMessage"></div>
                                        <div align="right" id="pageNavPosition">hello</div>
                                    </td>
                                </tr> 
                                <% }%>
                            </table>

                        </div>

                        <%--   </div> --%>
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