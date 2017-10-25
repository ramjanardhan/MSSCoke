<%-- 
    Document   : delChnInfo
    Created on : Feb 2, 2015, 11:57:14 AM
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
        <%--  <script language="JavaScript"
          src='<s:url value="/includes/js/dhtmlxcalendar.js"/>'></script>--%>
        <script language="JavaScript"
        src='<s:url value="/includes/js/dbgridDisplay.js"/>'></script>

        <script language="JavaScript"
        src='<s:url value="/includes/js/tpvalidations.js"/>'></script>
        <script language="JavaScript"
        src='<s:url value="/includes/js/overlay.js"/>'></script>

        <%--          <script language="JavaScript">


     
  </script>
<script type="text/javascript"> 
  

</script>  --%>


    </head>
    <body onload="setStyle('mainTp', 'delChnInfo');">
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
                    <!-- Start Overlay -->
                    <div id="partneroverlay"></div>
                    <!-- End Overlay -->
                    <!-- Start Special Centered Box -->
                    <div id="partnerspecialBox" >
                        <s:form theme="simple">
                            <table style="width: 100%;">

                                <tr> 

                                    <td colspan="3" style="background-color: #88E2F8;">
                                        <p class="overlayHeaderFonts">Partner Name</p>    
                                    </td>
                                </tr>
                                <tr>
                                    <td class="lableLeft" style="background-color: #88E2F8;">Partners&nbsp;List:</td>
                                    <td style="background-color: #88E2F8;">
                                        <s:select  
                                            name="partnerMapId" 
                                            id="partnerMapId"
                                            headerKey="1"
                                            headerValue="-- Please Select --"
                                            list="partnerMap" 
                                            tabindex="13" cssStyle="width : 150px"
                                            value="" />

                                    </td>
                                    <td style="background-color: white;">
                                        <div class="closeButton">
                                            <a href="#" onmousedown="partnertoggleOverlay(2)" class="button">OK</a>
                                        </div>
                                    </td>
                                </tr>    

                            </table>
                        </s:form>
                    </div>
                    <!-- end Special Centered Box -->
                    <div id="routeroverlay"></div>
                    <!-- End Overlay -->
                    <!-- Start Special Centered Box -->
                    <div id="routerspecialBox" >
                        <s:form theme="simple">
                            <table style="width: 100%;">
                                <tr>
                                    <td colspan="3" style="background-color: #88E2F8;">
                                        <p class="overlayHeaderFonts">Router Name</p>    
                                    </td>
                                </tr>
                                <tr>
                                    <td class="lableLeft" style="background-color: #88E2F8;">Routers&nbsp;List:</td>
                                    <td style="background-color: #88E2F8;">
                                        <s:select  
                                            name="routerMap" 
                                            id="routerMap"
                                            headerKey="1"
                                            headerValue="-- Please Select --"
                                            list="routerMap" 
                                            tabindex="13" cssStyle="width : 150px"
                                            value=""  />

                                    </td>
                                    <td style="background-color: white;">
                                        <div  class="closeButton">
                                            <a href="#" onmousedown="routertoggleOverlay(2)" class="button">OK</a>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </s:form>
                    </div>
                    <!-- end Special Centered Box -->
                    <!-- Business Process overlay Start -->
                    <div id="businessoverlay"></div>
                    <!-- End Overlay -->
                    <!-- Start Special Centered Box -->
                    <div id="businessspecialBox" >
                        <s:form theme="simple">
                            <table style="width: 100%;">
                                <tr>
                                    <td colspan="3" style="background-color: #88E2F8;">
                                        <p class="overlayHeaderFonts">Business&nbsp;Process&nbsp;Name</p>    
                                    </td>
                                </tr>
                                <tr>
                                    <td class="lableLeft" style="background-color: #88E2F8;">Business&nbsp;Process&nbsp;List:</td>
                                    <td style="background-color: #88E2F8;">
                                        <s:select  
                                            name="businessProcessMapId" 
                                            id="businessProcessMapId"
                                            headerKey="1"
                                            headerValue="-- Please Select --"
                                            list="businessProcessMap" 
                                            tabindex="13" cssStyle="width : 150px"
                                            value=""  />

                                    </td>
                                    <td style="background-color: white;">
                                        <div  class="closeButton">
                                            <a href="#" onmousedown="businesstoggleOverlay(2);" class="button">OK</a>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </s:form>
                    </div>
                    <!-- end Special Centered Box -->

                    <!-- Business process overlay end -->

                    <!-- translation overlay start -->

                    <div id="translationoverlay"></div>

                    <!-- Start Special Centered Box -->
                    <div id="translationspecialBox" >
                        <s:form theme="simple">
                            <table style="width: 100%;">
                                <tr>
                                    <td colspan="3" style="background-color: #88E2F8;">
                                        <p class="overlayHeaderFonts">Translation&nbsp;Map&nbsp;Name</p>    
                                    </td>
                                </tr>
                                <tr>
                                    <td class="lableLeft" style="background-color: #88E2F8;">Translation&nbsp;List:</td>
                                    <td style="background-color: #88E2F8;">
                                        <s:select  
                                            name="translationMapId" 
                                            id="translationMapId"
                                            headerKey="1"
                                            headerValue="-- Please Select --"
                                            list="translationMap" 
                                            tabindex="13" cssStyle="width : 150px"
                                            value=""  />

                                    </td>
                                    <td style="background-color: white;">
                                        <div  class="closeButton">
                                            <a href="#" onmousedown="translationtoggleOverlay(2);" class="button">OK</a>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </s:form>
                    </div>
                    <!-- end Special Centered Box -->


                    <!-- translation overlay end -->

                    <!-- DocumentExtractmap overlay start -->

                    <div id="documentoverlay"></div>

                    <!-- Start Special Centered Box -->
                    <div id="documentspecialBox" >
                        <s:form theme="simple">
                            <table style="width: 100%;">
                                <tr>
                                    <td colspan="3" style="background-color: #88E2F8;">
                                        <p class="overlayHeaderFonts">Document&nbsp;Extract&nbsp;Map&nbsp;Name</p>    
                                    </td>
                                </tr>
                                <tr>
                                    <td class="lableLeft" style="background-color: #88E2F8;">Document&nbsp;Extract&nbsp;List:</td>
                                    <td style="background-color: #88E2F8;">
                                        <s:select  
                                            name="documentExtractMapId" 
                                            id="documentExtractMapId"
                                            headerKey="1"
                                            headerValue="-- Please Select --"
                                            list="documentExtractMap" 
                                            tabindex="13" cssStyle="width : 150px"
                                            value=""  />

                                    </td>
                                    <td style="background-color: white;">
                                        <div  class="closeButton">
                                            <a href="#" onmousedown="documenttoggleOverlay(2);" class="button">OK</a>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </s:form>
                    </div>
                    <!-- end Special Centered Box -->


                    <!-- DocumentExtractmap overlay end -->


                    <!-- Producer overlay start -->

                    <div id="produceroverlay"></div>

                    <!-- Start Special Centered Box -->
                    <div id="producerspecialBox" >
                        <s:form theme="simple">
                            <table style="width: 100%;">
                                <tr>
                                    <td colspan="3" style="background-color: #88E2F8;">
                                        <p class="overlayHeaderFonts">Producer&nbsp;Mail&nbsp;Box</p>    
                                    </td>
                                </tr>
                                <tr>
                                    <td class="lableLeft" style="background-color: #88E2F8;">Producer&nbsp;MailBox&nbsp;List:</td>
                                    <td style="background-color: #88E2F8;">
                                        <s:select  
                                            name="producerMailMapId" 
                                            id="producerMailMapId"
                                            headerKey="1"
                                            headerValue="-- Please Select --"
                                            list="producerMailMap" 
                                            tabindex="13" cssStyle="width : 150px"
                                            value=""  />

                                    </td>
                                    <td style="background-color: white;">
                                        <div  class="closeButton">
                                            <a href="#" onmousedown="producertoggleOverlay(2);" class="button">OK</a>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </s:form>
                    </div>
                    <!-- end Special Centered Box -->


                    <!-- Production overlay end -->




                    <!-- Encoding Starts-->
                    <div id="encodingoverlay"></div>

                    <div id="encodingspecialBox" >
                        <s:form theme="simple">
                            <table style="width: 100%;">
                                <tr>
                                    <td colspan="3" style="background-color: #88E2F8;">
                                        <p class="overlayHeaderFonts">Encoding&nbsp;&nbsp;Box</p>    
                                    </td>
                                </tr>
                                <tr>
                                    <td class="lableLeft" style="background-color: #88E2F8;">Encoding&nbsp;MBox&nbsp;List:</td>
                                    <td style="background-color: #88E2F8;">
                                        <s:select  
                                            name="encodingMailMapId" 
                                            id="encodingMailMapId"
                                            headerKey="1"
                                            headerValue="-- Please Select --"
                                            list="encodingMailMap" 
                                            tabindex="13" cssStyle="width : 150px"
                                            value=""  />

                                    </td>
                                    <td style="background-color: white;">
                                        <div  class="closeButton">
                                            <a href="#" onmousedown="encodingtoggleOverlay(2);" class="button">OK</a>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </s:form>
                    </div>

                    <!-- Encoding end -->

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
                            <s:if test="%{formAction=='doAddDeliveryChannelInfo'}">
                                <h3>Delivery&nbsp;Channel&nbsp;Information Add</h3>   
                            </s:if>
                            <s:else>
                                <h3>Delivery&nbsp;Channel&nbsp;Information Edit</h3>   
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

                                <s:form action="%{formAction}" method="post" name="deliveryChannelForm" id="deliveryChannelForm" theme="simple">
                                    <s:hidden name="deliveryChannelId" value="%{deliveryChannelId}" id="deliveryChannelId"/>

                                    <table >
                                        <tbody >
                                            <%--    <s:hidden name="routingId" value="%{routingId}" id="routingId"/> --%>
                                            <tr>
                                                <td class="lableLeft">Partner&nbsp;Name<font color="red">*</font></td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="partnerName" id="partnerName" tabindex="1" value="%{partnerName}" cssStyle="width:300px;"/>
                                                    <s:hidden name="partnerId" value="%{partnerId}" id="partnerId"/>
                                                    <div id="partnerImage" align="center" style="display: none"><img  src="../includes/images/ajax-loader.gif" /></div>
                                                    <span id="partnerDetails"></span>
                                                </td>
                                                <td style="background-color: white;">
                                                    <strong><input type="button" value="Select" class="button" tabindex="33" onclick="return partnertoggleOverlay(1);"/></strong>
                                                </td>

                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Routing&nbsp;Name<font color="red">*</font></td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="routingName" id="routingName" tabindex="2" value="%{routingName}" cssStyle="width:300px;"/>
                                                    <s:hidden name="routerId" value="%{routerId}" id="routerId"/>
                                                    <div id="routerImage" align="center" style="display: none"><img  src="../includes/images/ajax-loader.gif" /></div>
                                                    <span id="routerDetails"></span>
                                                </td>
                                                <td style="background-color: white;">
                                                    <strong><input type="button" value="Select" class="button" tabindex="33" onclick="return routertoggleOverlay(1);"/></strong>
                                                </td>

                                            </tr>


                                            <tr>
                                                <td class="lableLeft">Sequence<font color="red">*</font></td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="sequence" id="sequence" tabindex="2" value="%{sequence}" />

                                                </td>


                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Business Process Name</td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="businessProcessName" id="businessProcessName" tabindex="2" value="%{businessProcessName}" cssStyle="width:300px;"/>
                                                    <s:hidden name="businessProcessId" value="%{businessProcessId}" id="businessProcessId"/>
                                                    <div id="businessImage" align="center" style="display: none"><img  src="../includes/images/ajax-loader.gif" /></div>
                                                    <span id="businessDetails"></span>
                                                </td>
                                                <td style="background-color: white;">
                                                    <strong><input type="button" value="Select" class="button" tabindex="33" onclick="return businesstoggleOverlay(1);"/></strong>
                                                </td>

                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Translation&nbsp;Map&nbsp;Name</td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="translationMapName" id="translationMapName" value="%{translationMapName}" tabindex="4" cssStyle="width:300px;"/>
                                                    <s:hidden name="translationId" value="%{translationId}" id="translationId"/>
                                                    <div id="tarnslationImage" align="center" style="display: none"><img  src="../includes/images/ajax-loader.gif" /></div>
                                                    <span id="translationDetails"></span>
                                                </td>

                                                <td style="background-color: white;">
                                                    <strong><input type="button" value="Select" class="button" tabindex="33" onclick="return translationtoggleOverlay(1);"/></strong>
                                                </td>

                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Document&nbsp;Extract&nbsp;Map&nbsp;Name</td>
                                                <td>

                                                    <s:textfield cssClass="inputStyle" name="docExtractMapName" id="docExtractMapName" value="%{docExtractMapName}" tabindex="4" cssStyle="width:300px;"/>
                                                    <s:hidden name="documentExtarctId" value="%{documentExtarctId}" id="documentExtarctId"/>
                                                    <%--  <s:select headerKey="-1" headerValue="Select State" list="statesMap" name="state" id="state" value="%{state}" tabindex="11" cssStyle="width : 150px"/> --%>
                                                </td>
                                                <td style="background-color: white;">
                                                    <strong><input type="button" value="Select" class="button" tabindex="33" onclick="return documenttoggleOverlay(1);"/></strong>
                                                </td>
                                            </tr>
                                            <tr>


                                                <td class="lableLeft">Archive&nbsp;Flag</td>
                                                <td>

                                                    <s:select list="#@java.util.LinkedHashMap@{'0':'YES','1':'NO'}" name="archiveFlag" id="archiveFlag" value="%{archiveFlag}" tabindex="13" cssStyle="width : 150px"/>
                                                </td>

                                            </tr>

                                            <tr>
                                                <td class="lableLeft">Archive&nbsp;Directory</td>
                                                <td>

                                                    <s:textfield cssClass="inputStyle" name="archiveDirectory" id="archiveDirectory" value="%{archiveDirectory}" tabindex="4" cssStyle="width:300px;"/>
                                                    <%--  <s:select headerKey="-1" headerValue="Select State" list="statesMap" name="state" id="state" value="%{state}" tabindex="11" cssStyle="width : 150px"/> --%>
                                                </td>

                                            </tr> <tr>
                                                <td class="lableLeft">Output&nbsp;Filename</td>
                                                <td>

                                                    <s:textfield cssClass="inputStyle" name="outputFileName" id="outputFileName" value="%{outputFileName}" tabindex="4" cssStyle="width:300px;"/>
                                                    <%--  <s:select headerKey="-1" headerValue="Select State" list="statesMap" name="state" id="state" value="%{state}" tabindex="11" cssStyle="width : 150px"/> --%>
                                                </td>

                                            </tr>       
                                            <tr>


                                                <td class="lableLeft">Output&nbsp;Format</td>
                                                <td>

                                                    <s:select list="#@java.util.LinkedHashMap@{'APP':'APP','EDI':'EDI','XML':'XML'}" name="outputFormat" id="outputFormat" value="%{outputFormat}" tabindex="13" cssStyle="width : 150px"/>
                                                </td>

                                            </tr>  <tr>
                                                <td class="lableLeft">Producer&nbsp;Mailbox</td>
                                                <td>

                                                    <s:textfield cssClass="inputStyle" name="producerMailBox" id="producerMailBox" value="%{producerMailBox}" tabindex="4" cssStyle="width:300px;"/>
                                                    <%--  <s:select headerKey="-1" headerValue="Select State" list="statesMap" name="state" id="state" value="%{state}" tabindex="11" cssStyle="width : 150px"/> --%>
                                                    <s:hidden name="producerMailBoxId" value="%{producerMailBoxId}" id="producerMailBoxId"/>
                                                </td>
                                                <td style="background-color: white;">
                                                    <strong><input type="button" value="Select" class="button" tabindex="33" onclick="return producertoggleOverlay(1);"/></strong>
                                                </td>
                                            </tr> <tr>


                                                <td class="lableLeft">Status</td>
                                                <td>

                                                    <s:select list="#@java.util.LinkedHashMap@{'ACTIVE':'ACTIVE','INACTIVE':'INACTIVE'}" name="status" id="status" value="%{status}" tabindex="13" cssStyle="width : 150px"/>
                                                </td>

                                            </tr>

                                            <tr>
                                                <td class="lableLeft">Encoding&nbsp;</td>
                                                <td>

                                                    <s:textfield cssClass="inputStyle" name="encodingMailBoxName" id="encodingMailBoxName" value="%{encodingMailBoxName}" tabindex="4" cssStyle="width:300px;"/>
                                                    <%--  <s:select headerKey="-1" headerValue="Select State" list="statesMap" name="state" id="state" value="%{state}" tabindex="11" cssStyle="width : 150px"/> --%>
                                                    <s:hidden name="encodingMailBoxId" value="%{encodingMailBoxId}" id="encodingMailBoxId"/>
                                                </td>
                                                <td style="background-color: white;">
                                                    <strong><input type="button" value="Select" class="button" tabindex="33" onclick="return encodingtoggleOverlay(1);"/></strong>
                                                </td>
                                            </tr>



                                            <tr>
                                                <td style="background-color: white;" colspan="3">

                                                    <%--    <%
                                                       
                                                        if(request.getAttribute("tppageId").toString().equals("0"))         {                       
                                                        %> --%>
                                                    <s:submit value="Save" cssClass="button" tabindex="32"/> 
                                                    <strong><input type="button" value="Cancel" class="button" tabindex="33" onclick="return resetvalues();"/></strong>
                                                        <%-- <%}else{%>
                                                         
                                                             <s:submit value="Update" cssClass="button" onclick="return checkTpValues();" tabindex="32"/> 
                                                             <strong><input type="button" value="BackToList" class="button" onclick="return getTpList();" tabindex="33"/></strong>
                                                         <%}%> --%>

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

