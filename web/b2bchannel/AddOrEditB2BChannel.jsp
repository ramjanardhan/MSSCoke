<%-- 
    Document   : AddOrEditB2BChannel
    Created on : Feb 3, 2015, 4:14:25 AM
    Author     : miracle
--%>


<%-- <%@ page contentType="text/html" pageEncoding="UTF-8"%> --%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


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
        <%--  <script language="JavaScript"
          src='<s:url value="/includes/js/dhtmlxcalendar.js"/>'></script>--%>
        <script language="JavaScript"
        src='<s:url value="/includes/js/dbgridDisplay.js"/>'></script>

        <script language="JavaScript"
        src='<s:url value="/includes/js/tpvalidations.js"/>'></script>
        <link href="../includes/images/truck_image.jpg" rel="shortcut icon"/>

        <%--          <script language="JavaScript">


     
  </script>
<script type="text/javascript"> 
  

</script>  --%>


    </head>
    <body onload="setStyle('mainTp', 'b2bChannelList');">
        <script type="text/javascript" src='<s:url value="/includes/js/wz_tooltip.js"/>'></script>
        <div id="wrapper">
            <div id="main">
                <header>
                    <div id="logo">
                        <s:include value="/includes/template/head.jsp"/>    

                    </div>
                    <nav>
                        <ul class="sf-menu" id="nav">

                            <%-- <s:include value="/includes/template/addTPMenu.jsp"/> --%>
                            <%-- <s:include value="/includes/template/mainMenu.jsp"/> --%>
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
                                <h3>Contact Information</h3>
                                <div class="sidebar_item">


                                    <h5 id="detailInformation"></h5>

                                </div>
                            </div>


                            <div class="sidebar_base"></div>
                        </div>


                    </div>


                    <%--  <s:textfield value="%{formAction}"/>--%>
                    <div class="content" >
                        <div class="content_item" id="searchdiv">
                            <s:if test="%{formAction == 'doAddB2BChannel'}">
                                <h3>B2BChannel&nbsp;Add</h3>   
                            </s:if><s:else>
                                <h3>B2BChannel&nbsp;Edit</h3>   
                            </s:else>
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

                                <s:form action="%{formAction}" method="post" name="b2bChannelForm" id="b2bChannelForm" theme="simple">
                                    <%--  <s:hidden name="tpid" value="%{tpid}" id="tpid"/>
                                      <s:hidden name="tpname" value="%{tpname}" id="tpname"/> --%>
                                    <table >
                                        <tbody >
                                            <s:hidden name="b2bChannelId" value="%{b2bChannelId}" id="b2bChannelId"/>
                                            <tr>
                                                <td class="lableLeft">Partner&nbsp;Name<font color="red">*</font></td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="partnerName" id="partnerName" tabindex="1" value="%{partnerName}" cssStyle="width:300px;"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Status</td>
                                                <td>
                                                    <s:select list="#@java.util.LinkedHashMap@{'ACTIVE':'ACTIVE','INACTIVE':'INACTIVE'}" name="status" id="status" value="%{status}" tabindex="13" cssStyle="width : 150px"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Direction<font color="red">*</font></td>
                                                <td>
                                                    <s:select list="#@java.util.LinkedHashMap@{'INBOUND':'INBOUND','OUTBOUND':'OUTBOUND'}" name="direction" id="direction" value="%{direction}" tabindex="13" cssStyle="width : 150px"/>

                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Protocol<font color="red">*</font></td>
                                                <td>
                                                    <s:select headerKey="" headerValue="" list="#@java.util.LinkedHashMap@{'FTP Client GET':'FTP Client GET','FTP Client PUT':'FTP Client PUT','Inbound Mailbox Routing':'Inbound Mailbox Routing'}" name="protocol" id="protocol" value="%{protocol}" tabindex="13" cssStyle="width : 150px"/>

                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Host<font color="red">*</font></td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="host" id="host" tabindex="1" value="%{host}" cssStyle="width:300px;"/>
                                                </td>
                                            </tr><tr>
                                                <td class="lableLeft">UserName<font color="red">*</font></td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="userName" id="userName" tabindex="1" value="%{userName}" cssStyle="width:300px;"/>
                                                </td>
                                            </tr><tr>
                                                <td class="lableLeft">Producer&nbsp;MailBox<font color="red">*</font></td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="producerMailBox" id="producerMailBox" tabindex="1" value="%{producerMailBox}" cssStyle="width:300px;"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Consumer&nbsp;MailBox<font color="red">*</font></td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="consumerMailBox" id="consumerMailBox" tabindex="1" value="%{consumerMailBox}" cssStyle="width:300px;"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Pooling&nbsp;Code<font color="red">*</font></td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="pollingCode" id="pollingCode" tabindex="1" value="%{pollingCode}" cssStyle="width:300px;"/>
                                                </td>
                                            </tr> <tr>
                                                <td class="lableLeft">App&nbsp;ID<font color="red">*</font></td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="appId" id="appId" tabindex="1" value="%{appId}" cssStyle="width:300px;"/>
                                                </td>
                                            </tr><tr>
                                                <td class="lableLeft">Sender&nbsp;ID<font color="red">*</font></td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="senderId" id="senderId" tabindex="1" value="%{senderId}" cssStyle="width:300px;"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Receiver&nbsp;ID<font color="red">*</font></td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="receiverId" id="receiverId" tabindex="1" value="%{receiverId}" cssStyle="width:300px;"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="background-color: white;" colspan="3">
                                                    <s:submit value="Save" cssClass="button" tabindex="32"/> 
                                                    <strong><input type="button" value="Cancel" class="button" tabindex="33" onclick="return resetvalues();"/></strong>
                                                </td>
                                            </tr>    

                                        </tbody>
                                    </table>


                                </s:form>

                            </div>

                        </div>
                        <a><img src='../includes/images/dtp/cal_plus.gif' alt="nag"width="13" height="9" border="0" onclick="javascript:hideSearch()" id="fsCollImg"/></a>  
                    </div>    
                </div> 

                <%--    
                   <div id="footer">
                     <h2><font color="white">&#169 2013 Miracle Software Systems, Inc. All rights reserved</font></h2>
                            </div> --%>
            </div>        

        </div>  

        <footer> 
            <p><font color="white">&#169 <%= new java.text.SimpleDateFormat("yyyy").format(new java.util.Date())%> Miracle Software Systems, Inc. All rights reserved</font></p>
        </footer>
    </body>
</html>


