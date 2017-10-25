<%-- 
    Document   : B2BChannelList
    Created on : Feb 2, 2015, 9:28:34 AM
    Author     : miracle
--%>


<%@page import="com.mss.ediscv.b2bchannel.B2BChannelBean"%>
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
        <link href="../includes/images/truck_image.jpg" rel="shortcut icon"/>

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


            function goToAddB2Bchannel() {
                window.location = "../b2bchannel/addB2BChannel.action";
            }

        </script>


    </head>
    <body  onload="setStyle('mainTp', 'b2bChannelList')">
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
                                <h3>B2B Channel Details</h3>
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
                            <h3>B2B Channel Search</h3>     
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
                                        <s:form action="../b2bchannel/b2BChannelListSearch.action" method="post" name="b2bchannelSearchForm" id="b2bchannelSearchForm" theme="simple">
                                            <tr>
                                                <td class="lableLeft">Partner&nbsp;Name</td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="partnerName" id="partnerName" tabindex="1" value="%{partnerName}"/>
                                                </td>

                                                <td class="lableLeft">Status</td>
                                                <td>

                                                    <s:select headerKey="" headerValue="" list="#@java.util.LinkedHashMap@{'ACTIVE':'ACTIVE','INACTIVE':'INACTIVE'}" name="status" id="status" value="%{status}" tabindex="13" />
                                                </td>

                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Direction</td>
                                                <td>
                                                    <s:select headerKey="" headerValue="" list="#@java.util.LinkedHashMap@{'INBOUND':'INBOUND','OUTBOUND':'OUTBOUND'}" name="direction" id="direction" value="%{direction}" tabindex="13" />

                                                </td>


                                                <td class="lableLeft">Protocol</td>
                                                <td>
                                                    <s:select headerKey="" headerValue="" list="#@java.util.LinkedHashMap@{'FTP Client GET':'FTP Client GET','FTP Client PUT':'FTP Client PUT','Inbound Mailbox Routing':'Inbound Mailbox Routing'}" name="protocol" id="protocol" value="%{protocol}" tabindex="13" />

                                                </td>

                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Host</td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="host" id="host" value="%{host}" tabindex="4" />
                                                </td>


                                                <td class="lableLeft">Username</td>
                                                <td><s:textfield cssClass="inputStyle" name="userName" id="userName" value="%{userName}" tabindex="5" /> </td>

                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Producer&nbsp;Mailbox</td>
                                                <td><s:textfield cssClass="inputStyle" name="producerMailBox" id="producerMailBox" value="%{producerMailBox}" tabindex="5" /> </td>

                                                <td class="lableLeft">Consumer&nbsp;Mailbox</td>
                                                <td><s:textfield cssClass="inputStyle" name="consumerMailBox" id="consumerMailBox" value="%{consumerMailBox}" tabindex="5" /> </td>
                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Polling&nbsp;Code</td>
                                                <td><s:textfield cssClass="inputStyle" name="pollingCode" id="pollingCode" value="%{pollingCode}" tabindex="5" /> </td>

                                                <td class="lableLeft">App&nbsp;ID</td>
                                                <td><s:textfield cssClass="inputStyle" name="appId" id="appId" value="%{appId}" tabindex="5" /> </td>
                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Sender&nbsp;Id</td>
                                                <td><s:textfield cssClass="inputStyle" name="senderId" id="senderId" value="%{senderId}" tabindex="5" /> </td>

                                                <td class="lableLeft">Receiver&nbsp;Id</td>
                                                <td><s:textfield cssClass="inputStyle" name="receiverId" id="receiverId" value="%{receiverId}" tabindex="5" /> </td>
                                            </tr>
                                            <tr >
                                                <td style="background-color: white;">
                                                    <strong><input type="button" value="Add" class="button" tabindex="33" onclick="goToAddB2Bchannel();"/></strong>
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


                    <s:if test="#session.b2bChannelList != null"> 
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
                                                    java.util.List list = (java.util.List) session.getAttribute(AppConstants.SES_B2BCHANNEL_LIST);

                                                    if (list.size() != 0) {
                                                        B2BChannelBean b2BChannelBean;
                                                %>
                                                <tr>
                                                    <td >ACTION</td>
                                                    <td >PartnerName </td>

                                                    <td>Protocol</td>
                                                    <td>Site</td>


                                                    <td>Status </td>
                                                    <td>CreatedDate </td>
                                                    <td>ChangedDate </td>
                                                    <td>ChangedBy</td>


                                                </tr>
                                                <tr >

                                                    <%
                                                        for (int i = 0; i < list.size(); i++) {
                                                            b2BChannelBean = (B2BChannelBean) list.get(i);

                                                            if (i % 2 == 0) {
                                                                cssValue = "whiteStripe";
                                                            } else {
                                                                cssValue = "grayEditSelection";
                                                            }
                                                    %>
                                                    <td style="text-align: left"><%-- <a href="#"> --%>
                                                        <%
                                                            int id = b2BChannelBean.getB2bChannelId();
                                                        %>
                                                        <s:url var="myUrl" action="../b2bchannel/b2bchannelEdit.action">
                                                            <s:param name="b2bChannelId"><%=id%></s:param>


                                                        </s:url>

                                                        <s:a href='%{#myUrl}' onmouseover="Tip('Click here to Edit Trading Partner.')" onmouseout="UnTip()">
                                                            <img src="../includes/images/Edit.gif"></img>
                                                        </s:a>

                                                        <%--  </a> --%>
                                                    </td>

                                                    <td style="text-align: left">
                                                        <a href="#" onclick="getB2bDetailInformation('<%=b2BChannelBean.getB2bChannelId()%>')" onmouseover="Tip('Click here to view Detail Info.')" onmouseout="UnTip()"> 
                                                            <%
                                                                out.println(b2BChannelBean.getPartnerName());
                                                            %>
                                                        </a> 
                                                    </td>

                                                    <td style="text-align: left">
                                                        <%
                                                            out.println(b2BChannelBean.getProtocol());
                                                        %>
                                                    </td>
                                                    <td style="text-align: left">
                                                        <%
                                                            out.println(b2BChannelBean.getHost());
                                                        %>
                                                    </td>
                                                    <td style="text-align: left">
                                                        <%
                                                            out.println(b2BChannelBean.getStatus());
                                                        %>
                                                    </td>
                                                    <td style="text-align: left">
                                                        <%
                                                            out.println(b2BChannelBean.getCreatedDate());
                                                        %>
                                                    </td> <td style="text-align: left">
                                                        <%
                                                            out.println(b2BChannelBean.getModifiedDate());
                                                        %>
                                                    </td>
                                                    <td style="text-align: left">
                                                        <%
                                                            out.println(b2BChannelBean.getModifiedBy());
                                                        %>
                                                    </td>
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