<%-- 
    Document   : userSearch
    Created on : May 2, 2013, 3:18:48 AM
    Author     : miracle1
--%>

<%@page import="java.util.List"%>
<%@page import="com.mss.ediscv.user.UserBean"%>
<%@page import="com.mss.ediscv.tradingPartner.TradingPartnerBean"%>
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

            function gridNext(c) {
                var b = c.id;
                var e = parseInt(document.userSearchListForm.txtStartGrid.value);
                var a = parseInt(document.userSearchListForm.txtEndGrid.value);
                var d = parseInt(document.userSearchListForm.txtMaxGrid.value);
                if (b == "Next") {
                    if (a < d)
                    {
                        document.location = "nextUserList.action?startValue=" + e + "&endValue=" + a + "&button=" + b
                    } else {
                        if (a == d) {
                            alert("You are already viewing last page!")
                        }
                    }
                } else {
                    if (b == "Previous")
                    {
                        if (e < d && e > 0) {
                            document.location = "nextUserList.action?startValue=" + e + "&endValue=" + a + "&button=" + b
                        } else {
                            if (e == 0) {
                                alert("You are already viewing first page!")
                            }
                        }
                    } else {
                        if (b == "First") {
                            if (e < d && e > 0) {
                                e = 0;
                                a = 10;
                                document.location = "nextUserList.action?startValue=" + e + "&endValue=" + a + "&button=" + b
                            } else {
                                if (e == 0) {
                                    alert("You are already viewing first page!")
                                }
                            }
                        } else {
                            if (b == "Last") {
                                if (a < d) {
                                    e = d - 10;
                                    a = d;
                                    document.location = "nextUserList.action?startValue=" + e + "&endValue=" + a + "&button=" + b
                                } else {
                                    if (a == d) {
                                        alert("You are already viewing last page!")
                                    }
                                }
                            }
                        }
                    }
                }
            }



            function goToPage() {
                var pageNumber = document.getElementById('pageNumber').value;
                var b = "Select";
                var startValue = ((parseInt(pageNumber) - 1) * 10);
                var endValue = parseInt(startValue) + 10;
                document.location = "nextUserList.action?startValue=" + startValue + "&endValue=" + endValue + "&button=" + b

            }


        </script>


    </head>
    <%!
        String strStartGrid;
        String strEndGrid;
        // String searchString;
        String pathName;

        int resultCount = 0;
        int strIntStartGrid;
        int strIntEndGrid;
        List userList = null;
        int noOfPages = 0;
    %>
    <body  onload="setStyle('mainTp', 'searchUser')">
        <script type="text/javascript" src='<s:url value="/includes/js/wz_tooltip.js"/>'></script>
        <div id="wrapper">
            <div id="main">
                <header>
                    <div id="logo">
                        <s:include value="/includes/template/head.jsp"/>    

                    </div>
                    <nav>
                        <ul class="sf-menu" id="nav">

                            <s:include value="/includes/template/msscvpAdminMenu.jsp"/>
                        </ul> 
                    </nav>
                </header>
                <div id="site_content">
                    <div id="sidebar_container">


                        <div id="detail_box" style="display: none;"> 
                            <div class="sidebar">
                                <h3>User Search</h3>
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
                            <h3>User Search</h3>     
                            <%
                                if (request.getSession(false).getAttribute("responseString") != null) {
                                    String reqponseString = request.getSession(false).getAttribute("responseString").toString();
                                    request.getSession(false).removeAttribute("responseString");
                                    out.println(reqponseString);
                                }
                            %>
                            <div &nbsp; style="alignment-adjust:central;" >
                                <%String contextPath = request.getContextPath();
                                %>
                                <table >
                                    <tbody >
                                        <s:form action="../user/userSearchList.action" method="post" name="userSearchListForm" id="userSearchListForm" theme="simple">
                                            <tr>
                                                <td class="lableLeft">First&nbsp;Name </td>
                                                <td>
                                                    <%-- <input type="text" class="inputStyle" name="loginId" id="loginId" onkeypress="return handleEnter(this,event);" tabindex="1" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="fname" id="fname" tabindex="1" maxLength="50" value="%{fname}"/>
                                                </td>

                                                <td class="lableLeft">Last&nbsp;Name</td>
                                                <td>

                                                    <s:textfield cssClass="inputStyle" name="lname" id="lname" tabindex="2" maxLength="50" value="%{lname}"/>
                                                </td>

                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Login&nbsp;Id </td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="loginId" id="loginId" tabindex="3" value="%{loginId}"/>
                                                </td>
                                                <td class="lableLeft">Status </td>
                                                <td>

                                                    <s:select cssClass="inputStyle" list="#@java.util.LinkedHashMap@{'A':'Active','I':'Inactive','T':'Terminated'}" headerKey="-1" headerValue="Select Status" name="status" id="status" tabindex="4" cssStyle="width : 150px" value="%{status}"/>
                                                </td>


                                            </tr>

                                            <tr >
                                                <td style="background-color: white;" colspan="3">
                                                    <s:submit   value="Search" cssClass="button" tabindex="5"/>



                                                </td>
                                            </tr>



                                        </tbody>
                                    </table>
                                </div>

                            </div>
                            <a><img src='../includes/images/dtp/cal_plus.gif' alt="nag"width="13" height="9" border="0" onclick="javascript:hideSearch()" id="fsCollImg"/></a>  
                        </div>


                        <s:if test="#session.userList != null"> 
                            <%--- GRid start --%>
                            <div class="content" id="gridDiv">
                                <div class="content_item">
                                    <div class="grid_overflow">
                                        <%!String cssValue = "whiteStripe";
                                            int resultsetTotal;%>
                                        <table align="left" id="results" width="690px"
                                               border="0" cellpadding="0" cellspacing="0" class="CSSTableGenerator">
                                            <%
                                                resultCount = 0;
                                                if (session.getAttribute("userList") != null) {

                                                    userList = (List) session.getAttribute("userList");
                                                    //  out.println("searchResult size-->"+searchResult.size());
                                                    if (null != userList && userList.size() != 0) {
                                                        resultCount = userList.size();
                                                    }
                                                }
                                            %>
                                            <%
                                                if (userList.size() != 0) {
                                                    noOfPages = Integer.parseInt(session.getAttribute("noOfPages").toString());
                                            %>

                                            <%}%> 
                                            <input type="hidden" name="sec_lt_list" id="sec_lt_list" value="userList"/> 
                                            <tr>
                                                <td >#</td>
                                                <td >Login&nbspId </td>
                                                <td >Name </td>
                                                <td>Email</td>
                                                <td>Office&nbsp;Phone #</td>
                                                <td>Status</td>
                                            </tr>

                                            <%

                                                if (request.getAttribute("strStartGrid") != null) {
                                                    strStartGrid = request.getAttribute("strStartGrid").toString();
                                                    System.out.println("strStartGrid----  " + strStartGrid);
                                                    strIntStartGrid = Integer.parseInt(strStartGrid);
                                                }
                                                //else{   strStartGrid = null;  }

                                                if (request.getAttribute("strEndGrid") != null) {
                                                    strEndGrid = request.getAttribute("strEndGrid").toString();
                                                    strIntEndGrid = Integer.parseInt(strEndGrid);
                                                }
                                //else{   strEndGrid = null;   }
                                            %>                                                    


                                            <%
                                                if (session.getAttribute("userList") != null) {
                                            %>
                                            <input type="hidden" name="strIntStartGrid" id="strIntStartGrid" value="<%=strIntStartGrid%>"/> 
                                            <input type="hidden" name="strIntEndGrid" id="strIntEndGrid" value="<%=strIntEndGrid%>"/> 

                                            <%
                                                List userList = (List) session.getAttribute("userList");

                                                //resultCount = 0;
                                                if (null != userList) {
                                                    resultCount = userList.size();
                                                }

                                                for (int i = strIntStartGrid, j = 0; i < strIntEndGrid; i++, j++) {
                                                    UserBean userBean = (UserBean) userList.get(i);

                                            %>

                                            <tr >
                                                <td style="text-align: left">
                                                    <s:url var="myUrl" action="../user/getAssingnedFlows.action">
                                                        <s:param name="userId"><%=userBean.getId()%></s:param>
                                                    </s:url>
                                                    <s:a href='%{#myUrl}' onmouseover="Tip('Click here to Assign flow(s).')" onmouseout="UnTip()">
                                                        <%

                                                            out.println(i + 1);
                                                        %>  
                                                    </s:a>


                                                </td>
                                                <td style="text-align: left">
                                                    <%
                                                        String id = userBean.getId();
                                                    %>
                                                    <s:url var="myUrl" action="../user/userEdit.action">
                                                        <s:param name="id"><%=id%></s:param>

                                                        <s:param name="fname" value="%{fname}"></s:param>
                                                        <s:param name="lname" value="%{lname}"></s:param> 
                                                        <s:param name="loginId" value="%{loginId}"></s:param>
                                                        <s:param name="status" value="%{status}"></s:param>

                                                    </s:url>
                                                    <s:a href='%{#myUrl}' onmouseover="Tip('Click here to Edit User Details.')" onmouseout="UnTip()">
                                                        <%

                                                            out.println(userBean.getLoginId());
                                                        %>  
                                                    </s:a>

                                                </td>
                                                <td style="text-align: left"><%-- <a href="#"> --%>
                                                    <%
                                                        // String Name = userBean.getName(); 
                                                        out.println(userBean.getName());
                                                    %>


                                                </td>

                                                <td style="text-align: left">
                                                    <%
                                                        out.println(userBean.getEmail());
                                                    %>
                                                </td>
                                                <td style="text-align: left">
                                                    <%
                                                        out.println(userBean.getOphno());
                                                    %>
                                                </td>
                                                <%--    <td style="text-align: left">
                                                     <%
                                             out.println(userBean.getRoleId());
                                             %>
                                                 </td> --%>
                                                <td style="text-align: left">
                                                    <%
                                                        out.println(userBean.getStatus());
                                                    %>
                                                </td> 


                                            </tr>
                                            <%
                                                }
                                            %>
                                            <tr>
                                                <td bgcolor="white" class="fieldLabelLeft" colspan="3" style="text-align: left; border: 0;">
                                                    <%if (strIntEndGrid != resultCount) {%>
                                                    Total Records : <%=resultCount%>&nbsp;Page <%=strIntEndGrid / 10%>  of <%=noOfPages%>
                                                    <%} else {%>
                                                    Total Records : <%=resultCount%>&nbsp;Page <%=noOfPages%>  of <%=noOfPages%>
                                                    <%}%>
                                                </td>
                                                <td colspan="7" align="right" bgcolor="white" style="text-align: right;border: 0;"><%    if (userList.size() != 0) {%>
                                                    <input type="button" name="First" id="First" value="<<" class="buttonBg" 
                                                           onclick="gridNext(this);" align="right">
                                                    <input type="button" name="Previous" id="Previous" value="<" class="buttonBg" 
                                                           onclick="gridNext(this);" align="right"> 
                                                    (<%=strIntStartGrid + 1%> - <%=strIntEndGrid%> of <%=resultCount%>)
                                                    <%--(<%=strIntStartGrid %> - <%=strIntEndGrid%> of <%=resultCount%>)--%>
                                                    <input type="button" name="Next" id="Next" value=">" class="buttonBg" 
                                                           onclick="gridNext(this);" align="right">
                                                    <input type="button" name="Last" id="Last" value=">>" class="buttonBg" 
                                                           onclick="gridNext(this);" align="right">

                                                    <s:select list="pageList" name="pageNumber" id="pageNumber" headerKey="select" headerValue="select" onchange="goToPage();" />
                                                    <%}%>

                                                </td>
                                            </tr>
                                            <%
                                                }

                                            %>
                                            <input type="hidden" name="txtStartGrid" value="<%=strStartGrid%>"/>
                                            <input type="hidden" name="txtEndGrid" value="<%=strEndGrid%>"/>
                                            <input type="hidden" name="txtMaxGrid" value="<%=resultCount%>"/>
                                        </table> 
                                    </div>
                                    <div id="resubmitLoading" align="center" style="display:none">
                                        <!--  <img  src="../includes/images/ajax-loader.gif" /> -->
                                        <font color="red">Loading...Please wait..</font>
                                    </div>
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

                    </s:form>
                </div> 

            </div>
        </div>
        <%--    <div id="footer">  --%>   
        <footer> 
            <p><font color="white">&#169 <%= new java.text.SimpleDateFormat("yyyy").format(new java.util.Date())%> Miracle Software Systems, Inc. All rights reserved</font></p>
        </footer>

    </body>
</html>