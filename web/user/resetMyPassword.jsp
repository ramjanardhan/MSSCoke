<%-- 
    Document   : resetMyPassword
    Created on : May 1, 2013, 11:22:41 AM
    Author     : miracle1
--%>




<%@page import="com.mss.ediscv.tp.TpBean"%>
<%-- <%@ page contentType="text/html" pageEncoding="UTF-8"%> --%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%--<%@page import="com.mss.ediscv.po.PurchaseOrderBean"%>--%>
<%@page import="com.mss.ediscv.util.AppConstants"%>


<html>
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

            function checkUserValues() {


                var oldPwd = document.getElementById("oldPwd").value;
                var newPwd = document.getElementById("newPwd").value;
                var confirmPwd = document.getElementById("confirmPwd").value;


                //if(((fname!=null)&&(fname!=""))&&((lname!=null)&&(lname!=""))&&((email!=null)&&(email!=""))&&((ophno!=null)&&(ophno!="")))
                if ((oldPwd == null) || (oldPwd == "")) {
                    alert("Please enter Old Password!");
                    return false;
                }
                if ((newPwd == null) || (newPwd == "")) {
                    alert("Please enter New Password!");
                    return false;
                }
                if ((confirmPwd == null) || (confirmPwd == "")) {
                    alert("Please enter Confirm Password!");
                    return false;
                }
                if ((confirmPwd != newPwd)) {
                    alert("New Password and Confirm Password must be equal!");
                    return false;
                }
                else
                {
                    return true;
                }
            }

            function resetvalues()
            {
                document.getElementById('oldPwd').value = "";
                document.getElementById('newPwd').value = "";
                document.getElementById('confirmPwd').value = "";

                //$('#detail_box').hide();
                //$('#gridDiv').hide();

            }

        </script>


    </head>
    <body onload="setStyle('mainTp', 'resetMyPwd');">
        <div id="wrapper">
            <div id="main">
                <header>
                    <div id="logo">
                        <s:include value="/includes/template/head.jsp"/>    

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


                                    <h5 id="detailInformation"></h5>

                                </div>
                            </div>


                            <div class="sidebar_base"></div>
                        </div>


                    </div>

                    <div class="content" id="gridDiv">
                        <div class="content_item">
                            <h3>Reset&nbsp;My&nbsp;Password</h3>     
                            <%
                                if (request.getAttribute("resultMessage") != null) {
                                    String resultMessage = request.getAttribute("resultMessage").toString();
                                    out.println(resultMessage);
                                }
                            %>
                            <div style="alignment-adjust:central;" >
                                <%String contextPath = request.getContextPath();
                                %>
                                <table >
                                    <tbody >
                                        <%-- ../user/createUser.action--%>
                                        <s:form action="../user/updateMyPwd.action" method="post" name="resetPwdForm" id="resetPwdForm" theme="simple">
                                            <tr>
                                                <td class="lableLeft">Old&nbsp;Password <font color="red">*</font></td>
                                                <td>
                                                    <%-- <input type="text" class="inputStyle" name="loginId" id="loginId" onkeypress="return handleEnter(this,event);" tabindex="1" /> --%>
                                                    <s:password cssClass="inputStyle" name="oldPwd" id="oldPwd" tabindex="1" maxLength="50"/>
                                                </td></tr>
                                            <tr>
                                                <td class="lableLeft">New&nbsp;Password<font color="red">*</font></td>
                                                <td>

                                                    <s:password cssClass="inputStyle" name="newPwd" id="newPwd" tabindex="2" maxLength="50"/>
                                                </td>

                                            </tr>

                                            <tr>
                                                <td class="lableLeft">Confirm&nbsp;Password <font color="red">*</font></td>
                                                <td>

                                                    <s:password cssClass="inputStyle" name="confirmPwd" id="confirmPwd" tabindex="3" maxLength="100"/>
                                                </td>



                                            </tr>


                                            <tr>
                                                <td style="background-color: white;" colspan="3">
                                                    <s:submit value="Update" cssClass="button" onclick="return checkUserValues();" tabindex="4"/>

                                                    <%-- <s:reset value="Reset" cssClass="button" tabindex="11"/> --%>
                                                    <strong><input type="button" value="Reset" class="button" tabindex="6" onclick="return resetvalues();"/></strong>

                                                    <%--   <input type="button" value="List" class="button" onclick="javascript:getList();" tabindex="7"/> --%>
                                                </td>
                                            </tr>


                                        </s:form>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>


                    <s:if test="#session.tpList != null"> 
                        <%--- GRid start --%>
                        <div class="content">
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
                                                    java.util.List list = (java.util.List) session.getAttribute(AppConstants.SES_TP_LIST);

                                                    if (list.size() != 0) {
                                                        TpBean tpBean;
                                                %>
                                                <tr>
                                                    <td >ID #</td>
                                                    <td >Name</td>
                                                    <%--   <td >TP INBOUND PATH</td>
                                                       <td >TP OUTBOUND PATH</td>  --%>
                                                    <td>Contact Info</td>
                                                    <td>Phone #</td>

                                                    <td>Department</td>
                                                    <td>EDI COMM ID</td>
                                                    <td>Qualifier</td>

                                                </tr>
                                                <tr >

                                                    <%
                                                        for (int i = 0; i < list.size(); i++) {
                                                            tpBean = (TpBean) list.get(i);

                                                            if (i % 2 == 0) {
                                                                cssValue = "whiteStripe";
                                                            } else {
                                                                cssValue = "grayEditSelection";
                                                            }
                                                    %>
                                                    <td ><%-- <a href="#"> --%>
                                                        <%
                                                            out.println(tpBean.getId());
                                                        %>
                                                        <%--  </a> --%>
                                                    </td>
                                                    <td>
                                                        <%
                                                            out.println(tpBean.getName());
                                                        %>
                                                    </td>
                                                    <td>
                                                        <a href="#" onclick="javascript:getContact('<%=tpBean.getContact()%>');" >Click Here</a>
                                                    </td>
                                                    <td>
                                                        <%
                                                            out.println(tpBean.getPhno());
                                                        %>
                                                    </td>
                                                    <td>
                                                        <%
                                                            out.println(tpBean.getDept());
                                                        %>
                                                    </td>
                                                    <td>
                                                        <%
                                                            out.println(tpBean.getCommid());
                                                        %>
                                                    </td>
                                                    <td>
                                                        <%
                                                            out.println(tpBean.getQualifier());
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
                <div id="footer">
                    <h2><font color="white">&#169 <%= new java.text.SimpleDateFormat("yyyy").format(new java.util.Date())%> Miracle Software Systems, Inc. All rights reserved</font></h2>
                </div>
            </div>        

        </div>  


    </body>
</html>

