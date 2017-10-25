<%-- 
    Document   : RoutingList
    Created on : Feb 2, 2015, 7:56:13 AM
    Author     : miracle
--%>


<%@page import="com.mss.ediscv.routing.RoutingBean"%>
<%@page import="com.mss.ediscv.tradingPartner.TradingPartnerBean"%>
<%@page import="com.mss.ediscv.tp.TpBean"%>
<%-- <%@ page contentType="text/html" pageEncoding="UTF-8"%> --%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="com.mss.ediscv.po.PurchaseOrderBean"%>
<%@page import="com.mss.ediscv.util.AppConstants"%>


<html>
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
        <%-- <script language="JavaScript"
       src='<s:url value="/includes/js/DateValidation.js"/>'></script> --%>
        <script language="JavaScript"
        src='<s:url value="/includes/js/GridNavigation.js"/>'></script>
        <script language="JavaScript"
        src='<s:url value="/includes/js/generalValidations.js"/>'></script>
        <script language="JavaScript"
        src='<s:url value="/includes/js/GeneralAjax.js"/>'></script>

        <script language="JavaScript"
        src='<s:url value="/includes/js/modernizr-1.5.min.js"/>'></script>

        <script language="JavaScript"
        src='<s:url value="/includes/js/common.js"/>'></script>
        <script language="JavaScript"
        src='<s:url value="/includes/js/dhtmlxcalendar.js"/>'></script>
        <script language="JavaScript"
        src='<s:url value="/includes/js/dbgridDisplay.js"/>'></script>

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

            $('.check_link').click(function ()
            {
                var thisCheck = $(this);
                if (thischeck.is(':checked'))
                {
                    $('#check_box').show();
                }
            });
            // New function to show the left grid


            function goToAddRouting() {
                window.location = "../routing/addRouting.action";
            }

        </script>


    </head>
    <body  onload="setStyle('mainTp', 'routingList')">
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
                    <div id="sidebar_container">


                        <div id="detail_box" style="display: none;"> 
                            <div class="sidebar">
                                <h3>Routing Details</h3>
                                <div class="sidebar_item">

                                    <div id="loadingImage" align="center"><img  src="../includes/images/ajax-loader.gif" /></div>
                                    <h5 id="detailInformation"></h5>

                                </div>
                            </div>


                            <div class="sidebar_base"></div>
                        </div>


                    </div>

                    <div class="content" >
                        <div class="content_item" id="searchdiv">
                            <h3>Routing Search</h3>     
                            <%
                                if (request.getSession(false).getAttribute("responseString") != null) {
                                    String reqponseString = request.getSession(false).getAttribute("responseString").toString();
                                    request.getSession(false).removeAttribute("responseString");
                                    out.println(reqponseString);
                                }
                            %>
                            <div style="alignment-adjust:central;" >
                                <%String contextPath = request.getContextPath();
                                %>
                                <table >
                                    <tbody >
                                        <s:form action="../routing/routingSearch.action" method="post" name="routingSearchForm" id="routingSearchForm" theme="simple">
                                            <tr>
                                                <td class="lableLeft">Name<font color="red">*</font></td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="name" id="name" tabindex="1" value="%{name}"/>
                                                </td>

                                                <td class="lableLeft">Status</td>
                                                <td>

                                                    <%-- <s:select headerKey="" headerValue="" list="#@java.util.LinkedHashMap@{'ACTIVE':'ACTIVE','INACTIVE':'INACTIVE'}" name="status" id="status" value="%{status}" tabindex="13" /> --%>
                                                    <s:select list="{'ACTIVE','INACTIVE'}" name="status" id="status" value="%{status}" tabindex="13" cssStyle="width : 150px"/>
                                                </td>

                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Acceptor&nbsp;Lookup&nbsp;Alias<font color="red">*</font></td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="acceptorLookupAlias" id="acceptorLookupAlias" tabindex="2" value="%{acceptorLookupAlias}" />
                                                </td>


                                                <td class="lableLeft">Envelope</td>
                                                <td>

                                                    <s:select headerKey="" headerValue="" list="#@java.util.LinkedHashMap@{'DIFFERED':'DIFFERED','IMMEDIATE':'IMMEDIATE'}" name="envelope" id="envelope" value="%{envelope}" tabindex="13" />
                                                </td>

                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Internal&nbsp;Route&nbsp;Email</td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="internalRouteEmail" id="internalRouteEmail" value="%{internalRouteEmail}" tabindex="4" />
                                                </td>


                                                <td class="lableLeft">Destination&nbsp;Mailbox</td>
                                                <td><s:textfield cssClass="inputStyle" name="destMailBox" id="destMailBox" value="%{destMailBox}" tabindex="5" /> </td>

                                            </tr>
                                            <tr>
                                                <td class="lableLeft">System&nbsp;Type</td>
                                                <td>

                                                    <s:select list="#@java.util.LinkedHashMap@{'Not Applicable':'Not Applicable'}" name="systemType" id="systemType" value="%{systemType}" tabindex="13" />
                                                    <%--  <s:select headerKey="-1" headerValue="Select State" list="statesMap" name="state" id="state" value="%{state}" tabindex="11" cssStyle="width : 150px"/> --%>
                                                </td>


                                                <td class="lableLeft">Direction<font color="red">*</font></td>
                                                <td>
                                                    <s:select list="#@java.util.LinkedHashMap@{'INBOUND':'INBOUND','OUTBOUND':'OUTBOUND'}" name="direction" id="direction" value="%{direction}" tabindex="13" />

                                                </td>
                                            </tr>
                                            <tr >
                                                <td style="background-color: white;">
                                                    <strong><input type="button" value="Add" class="button" tabindex="33" onclick="goToAddRouting();"/></strong>
                                                        <s:submit value="Search" cssClass="button" tabindex="8"/>

                                                </td>

                                            </tr>



                                        </s:form>
                                    </tbody>
                                </table>
                            </div>

                        </div>
                        <a><img src='../includes/images/dtp/cal_plus.gif' alt="nag"width="13" height="9" border="0" onclick="javascript:hideSearch()" id="fsCollImg"/></a>  
                    </div>


                    <s:if test="#session.routingList != null"> 
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
                                                    java.util.List list = (java.util.List) session.getAttribute(AppConstants.SES_ROUTING_LIST);

                                                    if (list.size() != 0) {
                                                        RoutingBean routingBean;
                                                %>
                                                <tr>
                                                    <td >Action</td>
                                                    <td >Name </td>
                                                    <%--   <td >TP INBOUND PATH</td>
                                                       <td >TP OUTBOUND PATH</td>  --%>
                                                    <td>Acceptor&nbsp;Lookup&nbsp;Alias</td>
                                                    <%-- <td>Envelope</td> --%>
                                                    <td>Direction </td>
                                                    <td>InternalRouteEmail </td>
                                                    <td>DestinationMailBox </td>
                                                    <td>SystemType</td>
                                                    <td>Status</td>
                                                    <%--   <td>CreatedDate</td>
                                                         <td>Changeddate</td> --%>
                                                </tr>
                                                <tr >

                                                    <%
                                                        for (int i = 0; i < list.size(); i++) {
                                                            routingBean = (RoutingBean) list.get(i);

                                                            if (i % 2 == 0) {
                                                                cssValue = "whiteStripe";
                                                            } else {
                                                                cssValue = "grayEditSelection";
                                                            }
                                                    %>
                                                    <td style="text-align: left"><%-- <a href="#"> --%>
                                                        <%
                                                            int id = routingBean.getRoutingId();
                                                        %>
                                                        <s:url var="myUrl" action="../routing/routingEdit.action">
                                                            <s:param name="routingId"><%=id%></s:param>
                                                        </s:url>

                                                        <s:a href='%{#myUrl}' onmouseover="Tip('Click here to Edit Trading Partner.')" onmouseout="UnTip()">
                                                            <img src="../includes/images/Edit.gif">
                                                        </s:a>

                                                        <%--  </a> --%>
                                                    </td>

                                                    <td style="text-align: left">
                                                        <a href="#" onclick="getRoutingDetails('<%=routingBean.getRoutingId()%>')" onmouseover="Tip('Click here to view Detail Info.')" onmouseout="UnTip()"> 
                                                            <%
                                                                out.println(routingBean.getName());
                                                            %>
                                                        </a> 
                                                    </td>

                                                    <td style="text-align: left">
                                                        <%
                                                            out.println(routingBean.getAcceptorLookupAlias());
                                                        %>
                                                    </td>
                                                    <%-- <td style="text-align: left">
                                                        <%
                                                out.println(routingBean.getEnvelope());
                                                %>
                                                    </td> --%>
                                                    <td style="text-align: left">
                                                        <%
                                                            out.println(routingBean.getDirection());
                                                        %>
                                                    </td>

                                                    <td style="text-align: left">
                                                        <%
                                                            out.println(routingBean.getInternalRouteEmail());
                                                        %>
                                                    </td> <td style="text-align: left">
                                                        <%
                                                            out.println(routingBean.getDestMailBox());
                                                        %>
                                                    </td> <td style="text-align: left">
                                                        <%
                                                            out.println(routingBean.getSystemType());
                                                        %>
                                                    </td> <td style="text-align: left">
                                                        <%
                                                            out.println(routingBean.getStatus());
                                                        %>
                                                    </td><%-- <td style="text-align: left">
                                                        <%
                                                out.println(routingBean.getCreatedDate());
                                                %>
                                                    </td> <td style="text-align: left">
                                                        <%
                                                out.println(routingBean.getChangedDate());
                                                %> 
                                                    </td>  --%>
                                                    <%-- <td>
                                                         <%
                                                 out.println(tpBean.getTpInPath());
                                                 %>
                                                     </td>
                                                     
                                                     <td>
                                                         <%
                                                 out.println(tpBean.getTpOutPath());
                                                 %>
                                                     </td>  --%>

                                                </tr>
                                                <%
                                                    }
                                                } else {
                                                %>
                                                <tr><td>
                                                        <%
                                                                // String contextPath = request.getContextPath();
                                                                out.println("<img  border='0' align='top'  src='" + contextPath + "/includes/images/alert.gif'/><b> No Records Found to Display!</b>");
                                                            }

                                                        %>
                                                    </td>
                                                </tr>
                                            </table>

                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" colspan="28" style="background-color: white;">
                                            <div align="right" id="pageNavPosition">hello</div>
                                        </td>
                                    </tr>           
                                </table>
                            </div>
                            <%-- Process butttons  start --%>
                            <table align="left" 
                                   width="690px" border="0" cellpadding="0"
                                   cellspacing="0">


                            </table>
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
                <%-- <div id="footer">
                   <h2><font color="white">&#169 2013 Miracle Software Systems, Inc. All rights reserved</font></h2>
                          </div> --%>
            </div>        

        </div>  
        <footer> 
            <p><font color="white">&#169 <%= new java.text.SimpleDateFormat("yyyy").format(new java.util.Date())%> Miracle Software Systems, Inc. All rights reserved</font></p>
        </footer>

    </body>
</html>