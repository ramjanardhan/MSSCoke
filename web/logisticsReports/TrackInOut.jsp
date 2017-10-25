<%-- 
    Document   : TrackInOut
    Created on : Aug 7, 2015, 6:54:11 PM
    Author     : miracle
--%>


<%@page import="com.mss.ediscv.logisticeditracking.LogisticTrackInOutBean"%>
<%@page import="com.mss.ediscv.editracking.TrackInOutBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.mss.ediscv.util.DataSourceDataProvider"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.mss.ediscv.util.AppConstants"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> --%>
<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page buffer="50kb" autoFlush="true" %>
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

            function resetvalues()
            {

                document.getElementById('docdatepickerfrom').value = "";
                document.getElementById('docdatepicker').value = "";

                $('#detail_box').hide();
                $('#gridDiv').hide();

            }
        </script>
    </head>

    <%--<body onload="doOnLoad();initDateTime('docdatepickerfrom','docdatepicker','<%=check %>');setStyle('docRep','');">  --%>

    <body onload="doOnLoad();
        setStyle('logisticreportsId', 'logistictrackInOutId');">
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
                            <h3>EDI Tracking IN/OUT</h3>

                            <div style="alignment-adjust:central;" >
                                <%String contextPath = request.getContextPath();
                                %>
                                <table >
                                    <tbody >
                                        <s:form action="../logisticsReports/trackInOutSearch.action" method="post" name="trackInOutForm" id="trackInOutForm" theme="simple">
                                            <tr>
                                                <td class="lableLeft">From </td>
                                                <td><%-- <input type="text" id="datepickerfrom" /> --%>
                                                    <%--  <input type="text" name="datepickerfrom" id="datepickerfrom" class="inputStyle" tabindex="2" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="docdatepickerfrom" id="docdatepickerfrom"  value="%{docdatepickerfrom}" tabindex="1"  onkeypress="return enterDateRepository();"/>
                                                    <a href="javascript:copyValuTo('docdatepickerfrom','docdatepicker');"><img border="0" src="<%= contextPath%>/includes/images/lm/arrow.gif" width="7"
                                                                                                                               height="9"></a>
                                                </td>
                                                <td class="lableLeft">To </td>
                                                <td><%-- <input type="text" id="datepicker" /> --%>
                                                    <%--  <input type="text" name="datepicker" id="datepicker" class="inputStyle" tabindex="2" />  --%>
                                                    <s:textfield cssClass="inputStyle" name="docdatepicker"  value="%{docdatepicker}" id="docdatepicker" tabindex="2"  onkeypress="return enterDateRepository();"/>
                                                </td>

                                            </tr>

                                            <tr>  <%-- return compareDates(document.getElementById('docdatepickerfrom').value,document.getElementById('docdatepicker').value); --%>
                                                <td style="background-color: white;" colspan="2">
                                                    <s:submit value="Search" cssClass="button" tabindex="16" onclick="return checkDates();"/>

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

                                                    trackInOutBean = (LogisticTrackInOutBean) list.get(0);
                                                    ArrayList inboundList = trackInOutBean.getInboundList();
                                                    ArrayList outboundList = trackInOutBean.getOutboundList();
                                                    ArrayList docType = trackInOutBean.getDocumentTypeList();
                                                    ArrayList dateMonth = trackInOutBean.getDateMonth();
                                                    ArrayList dateMonthdocType = trackInOutBean.getDateMonthdocType();
                                                    int inbounddocTotal = 0;
                                                    int outbounddocTotal = 0;
                                                    int docTotal = 0;
                                                    int allTotal = 0;
                                                    System.out.println("doctypelist=" + docType + "dateMonthdocType=" + dateMonthdocType + "dateMonth" + dateMonth + "inbountList=" + inboundList + "outboundList=" + outboundList);
                                                    if (!docType.isEmpty() && !dateMonthdocType.isEmpty()) {
                                            %>
                                            <div id="track_in_out">EDI Tracking IN\OUT</div>

                                            <table align="left" id="results" border="0" cellpadding="0" cellspacing="0" class="CSSTableGenerator">
                                                <tr>
                                                    <td >TRANSACTION TYPE</td> 
                                                    <td >Direction</td>
                                                </tr>
                                                <%
                                                    for (int i = 0; i < docType.size(); i++) {
                                                        if (i % 2 == 0) {
                                                            cssValue = "whiteStripe";
                                                        } else {
                                                            cssValue = "grayEditSelection";
                                                        }
                                                %>
                                                <tr>
                                                    <td rowspan="2" id="doctype<%=docType.get(i)%>" style=" border-right: 1px solid;text-align: left;">
                                                        <%
                                                            if (inboundList.contains(docType.get(i)) && outboundList.contains(docType.get(i))) {
                                                        %>
                                                        <a class="plus_minus_anchor" href="javascript:toggle('<%=docType.get(i)%>',4,'total<%=i%>')"  >
                                                            <div id="total<%=i%>" class="plus_minus"></div>
                                                        </a>
                                                        <%
                                                        } else {
                                                        %>
                                                        <a class="plus_minus_anchor" href="javascript:toggle('<%=docType.get(i)%>',3,'total<%=i%>')" >
                                                            <div id="total<%=i%>" class="plus_minus" ></div>
                                                        </a>
                                                        <%
                                                            }
                                                        %>
                                                        <span style="margin-left: 60px;">
                                                            <%
                                                                out.println(docType.get(i).toString().trim());
                                                            %></span>
                                                    </td>
                                                </tr>
                                                <%
                                                    if (inboundList.contains(docType.get(i))) {
                                                %>
                                                <tr id="inbound<%=docType.get(i)%>" style="display: none">
                                                    <td>Inbound</td>
                                                </tr>
                                                <%
                                                    }
                                                    if (outboundList.contains(docType.get(i))) {
                                                %>
                                                <tr id="outbound<%=docType.get(i)%>" style="display: none">
                                                    <td>Outbound</td>
                                                </tr> 
                                                <%
                                                    }
                                                %>
                                                <tr><td>Total</td></tr>
                                                <%
                                                    }
                                                %>
                                                <tr><td colspan="2" >Total</td></tr>
                                            </table>

                                            <%
                                                for (int j = 0; j < dateMonthdocType.size(); j++) {
                                            %>
                                            <table align="left" id="results" border="0" cellpadding="0" cellspacing="0" class="CSSTableGenerator" style="border-left: 0px;">

                                                <%
                                                    ArrayList temp = (ArrayList) dateMonthdocType.get(j);
                                                    ArrayList olddoctype = new ArrayList();
                                                    int total = 0;
                                                  //System.out.println("list"+temp.get(0));
%>
                                                <tr>
                                                    <td><%=temp.get(0)%></td>
                                                </tr>
                                                <%
                                                    for (int i = 0; i < docType.size(); i++) {
                                                        for (int l = 1; l < temp.size(); l = l + 4) {
                                                          //System.out.println("temp="+temp.get(l)+"l="+l);
                                                            // System.out.println("temp="+temp.get(m));
                                                            if (temp.get(l).equals(docType.get(i))) {
                                                                //System.out.println("doc type"+docType.get(i)+"temp="+temp.get(l));
                                                                if (inboundList.contains(docType.get(i))) {
                                                %>
                                                <tr class="inboundvalue<%=docType.get(i)%>" style="display: none"> 
                                                    <td ><%=temp.get(l + 1)%></td>
                                                </tr>
                                                <%
                                                    }
                                                    if (outboundList.contains(docType.get(i))) {
                                                %>
                                                <tr class="outboundvalue<%=docType.get(i)%>" style="display: none"> 
                                                    <td ><%=temp.get(l + 2)%></td>
                                                </tr>
                                                <%
                                                    }
                                                %>
                                                <tr> 
                                                    <td><%=temp.get(l + 3)%></td>
                                                </tr>
                                                <%
                                                    total = (Integer) temp.get(l + 3) + total;
                                                } else {
                                                    if (!temp.contains(docType.get(i)) && !olddoctype.contains((String) docType.get(i))) {
                                                        olddoctype.add((String) docType.get(i));
                                                        //System.out.println("else doc type"+docType.get(i)+"temp="+temp.get(l)+"olddoctype="+olddoctype);
                                                        if (inboundList.contains(docType.get(i))) {
                                                %>
                                                <tr class="inboundvalue<%=docType.get(i)%>" style="display: none"> 
                                                    <td >0</td>
                                                </tr>
                                                <%
                                                    }
                                                    if (outboundList.contains(docType.get(i))) {
                                                %>
                                                <tr class="outboundvalue<%=docType.get(i)%>" style="display: none">  
                                                    <td >0</td>
                                                </tr>
                                                <%
                                                    }
                                                %>
                                                <tr> 
                                                    <td >0</td>
                                                </tr>
                                                <%
                                                                }
                                                            }
                                                        }
                                                        // System.out.println("list"+temp.get(l));
                                                    }
                                                %>
                                                <tr> 
                                                    <td ><%=total%></td>
                                                </tr>
                                            </table>
                                            <%
                                                }
                                            %>
                                            <table align="left" id="results" border="0" cellpadding="0" cellspacing="0" class="CSSTableGenerator" style="border-left: 0px;">
                                                <tr>
                                                    <td>Total</td>
                                                </tr>
                                                <%
                                                    for (int k = 0; k < docType.size(); k++) {
                                                        for (int i = 0; i < dateMonthdocType.size(); i++) {
                                                            ArrayList temp1 = (ArrayList) dateMonthdocType.get(i);
                                                            for (int l = 1; l < temp1.size(); l = l + 4) {
                                                                if (temp1.get(l).equals(docType.get(k))) {
                                                                    inbounddocTotal = (Integer) temp1.get(l + 1) + inbounddocTotal;
                                                                    outbounddocTotal = (Integer) temp1.get(l + 2) + outbounddocTotal;
                                                                    docTotal = (Integer) temp1.get(l + 3) + docTotal;
                                                                }
                                                            }
                                                        }
                                                        if (inboundList.contains(docType.get(k))) {
                                                %>
                                                <tr class="inboundvalue<%=docType.get(k)%>" style="display: none"> 
                                                    <td ><%=inbounddocTotal%></td>
                                                </tr>
                                                <%
                                                    }
                                                    if (outboundList.contains(docType.get(k))) {
                                                %>
                                                <tr class="outboundvalue<%=docType.get(k)%>" style="display: none"> 
                                                    <td ><%=outbounddocTotal%></td>
                                                </tr>
                                                <%
                                                    }
                                                %>
                                                <tr> 
                                                    <td ><%=docTotal%></td>
                                                </tr>
                                                <%
                                                        // System.out.println("doctype"+docType.get(j)+"inbound total"+inbounddocTotal+"outbound total"+outbounddocTotal+" total"+docTotal);
                                                        allTotal = allTotal + docTotal;
                                                        inbounddocTotal = 0;
                                                        outbounddocTotal = 0;
                                                        docTotal = 0;
                                                    }
                                                %>
                                                <tr> 
                                                    <td><%=allTotal%></td>
                                                </tr>
                                            </table>
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
                                                <% }

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
                                        if (list.size() != 0) {

                                            LogisticTrackInOutBean trackInOutBean;

                                            trackInOutBean = (LogisticTrackInOutBean) list.get(0);
                                            ArrayList docType = trackInOutBean.getDocumentTypeList();
                                            ArrayList dateMonthdocType = trackInOutBean.getDateMonthdocType();
                                            if (!docType.isEmpty() && !dateMonthdocType.isEmpty()) {
                                    %>
                                    <tr >
                                        <td align="right" colspan="28" style="background-color: white;">
                                            <div align="right" id="pageNavPosition"></div>
                                        </td>
                                    </tr> 
                                    <%}
                }%>
                                </table>

                            </div>
                            <%-- Process butttons  start --%>
                            <%
                                if (list.size() != 0) {
                                    LogisticTrackInOutBean trackInOutBean;

                                    trackInOutBean = (LogisticTrackInOutBean) list.get(0);
                                    ArrayList docType = trackInOutBean.getDocumentTypeList();
                                    ArrayList dateMonthdocType = trackInOutBean.getDateMonthdocType();
                                    if (!docType.isEmpty() && !dateMonthdocType.isEmpty()) {

                            %>
                            <table align="right">
                                <tr>
                                    <td style="background-color: white;">
                                        <strong><input type="button" value="Generate Excel" class="button" onclick="return gridDownload('logistictrackInOut', 'xls');" onmouseover="Tip('Click here to generate an excel Report.')" onmouseout="UnTip()" id="excel"/></strong>
                                    </td>
                                </tr>
                            </table> 
                            <%}
          }%>
                            <%-- process buttons end--%>
                            <%-- Grid End --%>

                        </div>
                    </s:if> 


                </div> 
                <script language="javascript">
                    function toggle(docType, k, id) {
                        //  alert(document.getElementsByClassName("inboundvalue"+docType).length);
                        var inbound = document.getElementById("inbound" + docType);
                        var doctype = document.getElementById("doctype" + docType);
                        var inboundvalue = document.getElementsByClassName("inboundvalue" + docType);
                        var outboundvalue = document.getElementsByClassName("outboundvalue" + docType);
                        var outbound = document.getElementById("outbound" + docType);
                        //alert(ele1.length);
                        //alert("value"+ele3[0]);      
                        var id = document.getElementById(id);
                        //alert(text);
                        if (inbound != null && inbound != '') {
                            if (inbound.style.display == "none") {

                                inbound.removeAttribute("style");
                                doctype.rowSpan = k;
                                for (var i = 0; i < inboundvalue.length; i++) {
                                    inboundvalue[i].removeAttribute("style");
                                }
                                //document.getElementById("toggleTd"+j).style.borderBottom="none";

                                // 	id.innerHTML = "-";
                                id.style.backgroundImage = "url('../includes/images/minus.png')";
                            }
                            else {

                                inbound.style.display = "none";
                                doctype.rowSpan = "2";
                                for (var i = 0; i < inboundvalue.length; i++) {
                                    inboundvalue[i].style.display = "none";
                                }
                                //id.innerHTML = "+";
                                id.style.backgroundImage = "url('../includes/images/plus.png')";
                            }
                        }
                        if (outbound != null && outbound != '') {
                            if (outbound.style.display == "none") {

                                outbound.removeAttribute("style");
                                doctype.rowSpan = k;
                                for (var i = 0; i < outboundvalue.length; i++) {
                                    outboundvalue[i].removeAttribute("style");
                                }
                                //document.getElementById("toggleTd"+j).style.borderBottom="none";

                                //	id.innerHTML = "-";
                                id.style.backgroundImage = "url('../includes/images/minus.png')";
                            }
                            else {

                                outbound.style.display = "none";
                                doctype.rowSpan = "2";
                                for (var i = 0; i < outboundvalue.length; i++) {
                                    outboundvalue[i].style.display = "none";
                                }
                                //id.innerHTML = "+";
                                id.style.backgroundImage = "url('../includes/images/plus.png')";
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

