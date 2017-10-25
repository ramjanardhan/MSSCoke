<%-- 
    Document   : DeliveryChannelList
    Created on : Feb 9, 2015, 3:53:59 AM
    Author     : miracle
--%>

<%@page import="com.mss.ediscv.partner.PartnerBean"%>
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


            function goToAddDeliverChannelInfo() {
                window.location = "../partner/getDelChannelInf.action";
            }





        </script>


    </head>
    <body  onload="setStyle('mainTp', 'delChnInfo')">
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
                                <h3>Delivery Channel Info</h3>
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
                            <h3>Deliver Channel Search</h3>     
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
                                        <s:form action="../partner/deliveryChannelSearch.action" method="post" name="deliverChannelForm" id="deliverChannelForm" theme="simple">
                                            <tr>
                                                <td class="lableLeft">Partner&nbsp;Name</td>
                                                <td>
                                                    <%-- <s:textfield cssClass="inputStyle" name="partnerName" id="partnerName" tabindex="1" value="%{partnerName}" onchange="fieldLengthValidator(this);"/> --%>
                                                    <s:select  
                                                        name="partnerId" 
                                                        id="partnerId"
                                                        headerKey="1"
                                                        headerValue="-- Please Select --"
                                                        list="partnerMap" 
                                                        tabindex="13" cssStyle="width : 150px"
                                                        value="" />
                                                </td>

                                                <td class="lableLeft">Routing Name</td>
                                                <td>

                                                    <%--  <s:select list="#@java.util.LinkedHashMap@{'ACTIVE':'ACTIVE','INACTIVE':'INACTIVE'}" name="status" id="status" value="%{status}" tabindex="13" cssStyle="width : 150px"/> --%>
                                                    <s:select  
                                                        name="routerId" 
                                                        id="routerId"
                                                        headerKey="1"
                                                        headerValue="-- Please Select --"
                                                        list="routerMap" 
                                                        tabindex="13" cssStyle="width : 150px"
                                                        value=""  />
                                                </td>

                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Business Process Name</td>
                                                <td>
                                                    <s:select  
                                                        name="businessProcessId" 
                                                        id="businessProcessId"
                                                        headerKey="1"
                                                        headerValue="-- Please Select --"
                                                        list="businessProcessMap" 
                                                        tabindex="13" cssStyle="width : 150px"
                                                        value=""  />
                                                </td>


                                                <td class="lableLeft">Translation&nbsp;Map&nbsp;Name</td>
                                                <td>
                                                    <s:select  
                                                        name="translationId" 
                                                        id="translationId"
                                                        headerKey="1"
                                                        headerValue="-- Please Select --"
                                                        list="translationMap" 
                                                        tabindex="13" cssStyle="width : 150px"
                                                        value=""  />

                                                </td>

                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Document&nbsp;Extract&nbsp;Map&nbsp;Name</td>
                                                <td>
                                                    <s:select  
                                                        name="documentExtarctId" 
                                                        id="documentExtarctId"
                                                        headerKey="1"
                                                        headerValue="-- Please Select --"
                                                        list="documentExtractMap" 
                                                        tabindex="13" cssStyle="width : 150px"
                                                        value=""  />
                                                </td>


                                                <td class="lableLeft">Producer&nbsp;Mail&nbsp;Box</td>
                                                <td>
                                                    <s:select  
                                                        name="producerMailBoxId" 
                                                        id="producerMailBoxId"
                                                        headerKey="1"
                                                        headerValue="-- Please Select --"
                                                        list="producerMailMap" 
                                                        tabindex="13" cssStyle="width : 150px"
                                                        value=""  />
                                                    <%--  <s:select headerKey="-1" headerValue="Select State" list="statesMap" name="state" id="state" value="%{state}" tabindex="11" cssStyle="width : 150px"/> --%>
                                                </td>

                                            </tr>
                                            <tr>

                                                <td class="lableLeft">Encoding</td>
                                                <td>
                                                    <s:select  
                                                        name="encodingMailBoxId" 
                                                        id="encodingMailBoxId"
                                                        headerKey="1"
                                                        headerValue="-- Please Select --"
                                                        list="encodingMailMap" 
                                                        tabindex="13" cssStyle="width : 150px"
                                                        value=""  />
                                                    <%--  <s:select headerKey="-1" headerValue="Select State" list="statesMap" name="state" id="state" value="%{state}" tabindex="11" cssStyle="width : 150px"/> --%>
                                                </td>
                                            </tr>
                                            <tr >
                                                <td style="background-color: white;">
                                                    <strong><input type="button" value="Add" class="button" tabindex="33" onclick="goToAddDeliverChannelInfo();"/></strong>
                                                        <%-- <strong><input type="button" value="Export" class="button"/></strong>
                                                         <strong><input type="button" value="ExportAll" class="button"/></strong> --%>
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


                    <s:if test="#session.deliverChannelList != null"> 
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
                                                    java.util.List list = (java.util.List) session.getAttribute(AppConstants.SES_DELIVERY_CHANNEL_LIST);

                                                    if (list.size() != 0) {
                                                        PartnerBean partnerBean;
                                                %>
                                                <tr>
                                                    <td >Action</td>
                                                    <td >PartnerName </td>
                                                    <td>RoutingName</td>
                                                    <td>Sequence</td>
                                                    <td>BPName </td>
                                                    <td>TranslationMapName </td>
                                                    <td>Status </td>

                                                    <%--     <td>Changed Date</td>
                                                         <td>ChangesUser </td> --%>


                                                </tr>
                                                <tr >

                                                    <%
                                                        for (int i = 0; i < list.size(); i++) {
                                                            partnerBean = (PartnerBean) list.get(i);

                                                            if (i % 2 == 0) {
                                                                cssValue = "whiteStripe";
                                                            } else {
                                                                cssValue = "grayEditSelection";
                                                            }
                                                    %>
                                                    <td style="text-align: left"><%-- <a href="#"> --%>
                                                        <%
                                                            int id = partnerBean.getDeliveryChannelId();
                                                        %>
                                                        <s:url var="myUrl" action="../partner/deliveryChannelEdit.action">
                                                            <s:param name="deliveryChannelId"><%=id%></s:param>
                                                            <%--    <s:param name="tpid" value="%{tpid}"></s:param>
                                                                <s:param name="tpname" value="%{tpname}"></s:param> --%>

                                                        </s:url>

                                                        <s:a href='%{#myUrl}' onmouseover="Tip('Click here to Edit DeliveryChannelInfo.')" onmouseout="UnTip()"><img src="../includes/images/Edit.gif"></s:a>

                                                        <%--  </a> --%>
                                                    </td>

                                                    <td style="text-align: left">
                                                        <a href="#" onclick="getDeliveryChannelDetails('<%=partnerBean.getDeliveryChannelId()%>')" onmouseover="Tip('Click here to view Detail Info.')" onmouseout="UnTip()"> 
                                                            <%
                                                                out.println(partnerBean.getPartnerName());
                                                            %></a>
                                                    </td>

                                                    <td style="text-align: left">
                                                        <%
                                                            out.println(partnerBean.getRoutingName());
                                                        %>
                                                    </td>
                                                    <td style="text-align: left">
                                                        <%
                                                            out.println(partnerBean.getSequence());
                                                        %>
                                                    </td>
                                                    <td style="text-align: left">
                                                        <%
                                                            out.println(partnerBean.getBusinessProcessName());
                                                        %>
                                                    </td>
                                                    <td style="text-align: left">
                                                        <%
                                                            out.println(partnerBean.getTranslationMapName());
                                                        %>
                                                    </td> <td style="text-align: left">
                                                        <%
                                                            out.println(partnerBean.getStatus());
                                                        %>
                                                    </td> <%-- <td style="text-align: left">
                                                        <%
                                                out.println(partnerBean.getChangedDate());
                                                %>
                                                    </td> <td style="text-align: left">
                                                        <%
                                                out.println(partnerBean.getChangedBy());
                                                %>
                                                    </td> --%>

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
