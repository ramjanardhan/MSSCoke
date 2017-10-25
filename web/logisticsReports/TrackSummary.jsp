<%-- 
    Document   : TrackSummary
    Created on : Aug 10, 2015, 3:08:55 PM
    Author     : miracle
--%>


<%@page import="com.mss.ediscv.logisticeditracking.LogisticTrackInOutBean"%>
<%@page import="com.mss.ediscv.editracking.TrackInOutBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.mss.ediscv.util.DataSourceDataProvider"%>

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
        setStyle('logisticreportsId', 'logistictrackSummary');">
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

                    <div class="content">
                        <div class="content_item" id="searchdiv">
                            <h3>EDI Tracking Summary</h3>
                            <div style="alignment-adjust:central;" >
                                <%String contextPath = request.getContextPath();
                                %>
                                <table >
                                    <tbody >
                                        <s:form action="../logisticsReports/trackSummarySearch.action" method="post" name="trackSummaryForm" id="trackSummaryForm" theme="simple">
                                            <tr>
                                                <td class="lableLeft">Date From </td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="docdatepickerfrom" id="docdatepickerfrom"  value="%{docdatepickerfrom}" tabindex="1"  onkeypress="return enterDateRepository();"/>
                                                    <a href="javascript:copyValuTo('docdatepickerfrom','docdatepicker');"><img border="0" src="<%= contextPath%>/includes/images/lm/arrow.gif" width="7"
                                                                                                                               height="9"></a>
                                                </td>
                                                <td class="lableLeft">Date To </td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="docdatepicker"  value="%{docdatepicker}" id="docdatepicker" tabindex="2"  onkeypress="return enterDateRepository();"/>
                                                </td>

                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Network/Van</td>
                                                <td class="lableLeft">
                                                    <s:select headerKey="-1" headerValue="Select Type" list="networklanlist" name="docNetworkvan" id="docNetworkvan" value="%{docNetworkvan}" tabindex="3" cssStyle="width : 150px"/> 
                                                </td>       
                                            </tr> 


                                            <tr>  <%-- return compareDates(document.getElementById('docdatepickerfrom').value,document.getElementById('docdatepicker').value); --%>
                                                <td style="background-color: white;" colspan="2">
                                                    <s:submit value="Search" cssClass="button" tabindex="16"/>

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


                    <s:if test="#session.documentReportList != null"> 
                        <%--- GRid start --%>
                        <div class="content" id="gridDiv" style="overflow-x:auto;">
                            <div class="content_item">
                                <%!String cssValue = "whiteStripe";
                                    int resultsetTotal;
                                    int total = 0;
                                    int inboundTotal = 0;
                                    int outboundTotal = 0;
                                    double filesizeTotal = 0;
                                    double filesizeTotal1 = 0;
                                    List docTypeList = new ArrayList();

                                %>
                                <%
                                    try {
                                        docTypeList = DataSourceDataProvider.getInstance().getDocumentTypeList();
                                    } catch (Exception e) {

                                    }
                                %>
                                <table align="left" width="100%"
                                       border="0" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td style="background-color: white;">


                                            <%
                                                java.util.List list = (java.util.List) session.getAttribute(AppConstants.SES_DOCREPORT_LIST);

                                                if (list.size() != 0) {

                                                    LogisticTrackInOutBean trackInOutBean;

                                            %>

                                            <div style="text-align: center; font-weight: bold; font-size: 25px; margin-bottom: 20px;">EDI Tracking Summary</div>
                                            <table align="left" id="results" width="100%"
                                                   border="0" cellpadding="0" cellspacing="0" class="CSSTableGenerator">
                                                <tr>

                                                    <td >TRANSACTION TYPE</td> 
                                                    <td >Partner</td>
                                                    <td>FILE_SIZE</td>
                                                    <td >IN</td>
                                                    <td >OUT</td>
                                                    <td>Total</td>
                                                </tr>


                                                <%                 int j = 0;
                                                    for (int i = 0; i < list.size(); i++) {
                                                        trackInOutBean = (LogisticTrackInOutBean) list.get(i);

                                                        if (i % 2 == 0) {
                                                            cssValue = "whiteStripe";
                                                        } else {
                                                            cssValue = "grayEditSelection";
                                                        }

                                                        if (!trackInOutBean.getPname().equalsIgnoreCase("Total")) {%>
                                                <tr id="toggleText<%=i%>" style="display: none">
                                                    <%} else {%>
                                                <tr>
                                                    <%}%>
                                                    <td id="toggleTd<%=i%>">
                                                        <%

                                                            if (trackInOutBean.getPname().equalsIgnoreCase("Total")) {
                                                        %><a href="javascript:toggle('total<%=i%>',<%=i%>,<%=j%>)" style="position: relative; left: 64px;" >

                                                            <div id="total<%=i%>" class="plus_minus"></div>

                                                        </a><%
                                                                j = i + 1;
                                                            }
                                                            out.println(trackInOutBean.getTransaction_type());
                                                        %>

                                                    </td>
                                                    <td>
                                                        <%
                                                            out.println(trackInOutBean.getPname());
                                                        %>

                                                    </td>
                                                    <%--   <td>
                                                          <%
                                                          if(trackInOutBean.getInbound()!=0)
                                                       {
                                                          out.println(trackInOutBean.getFilesize());
                                                          if(trackInOutBean.getPname().equalsIgnoreCase("Total"))
                                                          {
                                                          filesizeTotal=filesizeTotal+trackInOutBean.getFilesize();
                                                          } 
                                                       }
                                                        if(trackInOutBean.getOutbound()!=0)
                                                       {
                                                          out.println(trackInOutBean.getFilesize1());
                                                          if(trackInOutBean.getPname().equalsIgnoreCase("Total"))
                                                          {
                                                          filesizeTotal1=filesizeTotal1+trackInOutBean.getFilesize1();
                                                          } 
                                                       }

           %>
                 
          </td>--%>
                                                    <td>   
                                                        <%
                                                            if (trackInOutBean.getPname().equalsIgnoreCase("Total")) {
                                                                filesizeTotal = filesizeTotal + trackInOutBean.getFilesizeTotal();
                                                            }
                                                            if (trackInOutBean.getFilesizeTotal() != 0) {
                                                                out.println(trackInOutBean.getFilesizeTotal() + "kb");
                                                            } else {
                                                                out.println(trackInOutBean.getFilesizeTotal());
                                                            }
                                                        %>
                                                    </td>
                                                    <td>
                                                        <%
                                                            out.println(trackInOutBean.getInbound());
                                                            if (trackInOutBean.getPname().equalsIgnoreCase("Total")) {
                                                                inboundTotal = inboundTotal + trackInOutBean.getInbound();
                                                            }
                                                        %>

                                                    </td>


                                                    <td>
                                                        <%
                                                            out.println(trackInOutBean.getOutbound());
                                                            if (trackInOutBean.getPname().equalsIgnoreCase("Total")) {
                                                                outboundTotal = outboundTotal + trackInOutBean.getOutbound();
                                                            }
                                                        %>

                                                    </td>  
                                                    <td>
                                                        <%
                                                            if (trackInOutBean.getPname().equalsIgnoreCase("Total")) {
                                                                total = total + trackInOutBean.getTotal();
                                                            }
                                                            out.println(trackInOutBean.getTotal());
                                                        %>

                                                    </td>




                                                </tr>
                                                <%
                                                    }
                                                %>
                                                <tr>
                                                    <td colspan="2">Total</td>
                                                    <td><%= filesizeTotal + "kb"%></td>  
                                                    <td><%= inboundTotal%></td>
                                                    <td><%= outboundTotal%></td>
                                                    <td><%= total%></td>
                                                </tr> 
                                                <%

                                                } else {
                                                %>
                                                <table align="left" id="results" width="690px"
                                                       border="0" cellpadding="0" cellspacing="0" class="CSSTableGenerator">
                                                    <tr><td>
                                                            <%
                                                           // String contextPath = request.getContextPath();
                                                                // out.println("<img  border='0' align='top'  src='"+contextPath+"/includes/images/alert.gif'/><b> No Records Found to Display!</b>");
                                                                out.println("<img  border='0' align='top'  src='" + contextPath + "/includes/images/alert.gif'/><b>No records found for the given search criteria. Please try a different search criteria!</b>");
                                                            %>
                                                        </td>
                                                    </tr>
                                                    <% } %>



                                                </table>

                                        </td>
                                    </tr>
                                    <%
                                        total = 0;
                                        inboundTotal = 0;
                                        outboundTotal = 0;
                                        filesizeTotal = 0;
                                        filesizeTotal1 = 0;
                                        if (list.size() != 0) {
                                    %>
                                    <tr >
                                        <td align="right" colspan="28" style="background-color: white;">
                                            <div align="right" id="pageNavPosition"></div>
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
                                        <strong><input type="button" value="Generate Excel" class="button" onclick="return gridDownload('logistictrackSummary', 'xls');" onmouseover="Tip('Click here to generate an excel Report.')" onmouseout="UnTip()" id="excel"/></strong>
                                    </td>
                                </tr>
                            </table> 
                            <%}%>
                            <%-- process buttons end--%>
                            <%-- Grid End --%>

                        </div>
                    </s:if> 


                </div> 
                <script language="javascript">
                    function toggle(id, i, k) {

                        for (var j = k; j < i; j++)
                        {

                            var ele = document.getElementById("toggleText" + j);

                            var text = document.getElementById(id);
                            if (ele.style.display == "none") {

                                ele.removeAttribute("style");
                                document.getElementById("toggleTd" + j).style.borderBottom = "none";

                                //text.innerHTML = "-";
                                text.style.backgroundImage = "url('../includes/images/minus.png')";
                            }
                            else {

                                ele.style.display = "none";
                                //text.innerHTML = "+";
                                text.style.backgroundImage = "url('../includes/images/plus.png')";
                            }
                        }
                    }

                </script>
                <%-- <script type="text/javascript">
                 var pager = new Pager('results', 10); 
                 pager.init(); 
                 pager.showPageNav('pager', 'pageNavPosition'); 
                 pager.showPage(1);
                 </script>--%>
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

