<%-- 
    Document   : PartnerList
    Created on : Feb 2, 2015, 6:32:41 AM
    Author     : miracle
--%>


<%@page import="java.util.List"%>
<%@page import="com.mss.ediscv.partner.PartnerBean"%>
<%@page import="com.mss.ediscv.tradingPartner.TradingPartnerBean"%>
<%@page import="com.mss.ediscv.tp.TpBean"%>
<%-- <%@ page contentType="text/html" pageEncoding="UTF-8"%> --%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%-- <%@page import="com.mss.ediscv.po.PurchaseOrderBean"%>--%>
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
        src='<s:url value="/includes/js/tablesorter.min.js"/>'></script>
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


            function resetvalues()
            {
                document.getElementById('partnerName').value = "";
                //document.getElementById('status').value="";
                document.getElementById('internalIdentifier').value = "";
                document.getElementById('partnerIdentifier').value = "";
                document.getElementById('applicationId').value = "";
                document.getElementById('countryCode').value = "";


                document.getElementById('status').value = "ACTIVE";

                $('#detail_box').hide();
                $('#gridDiv').hide();

            }

            function gridNext(c) {
                var b = c.id;
                var e = parseInt(document.partnerSearchForm.txtStartGrid.value);
                var a = parseInt(document.partnerSearchForm.txtEndGrid.value);
                var d = parseInt(document.partnerSearchForm.txtMaxGrid.value);
                if (b == "Next") {
                    if (a < d)
                    {
                        document.location = "nextPartList.action?startValue=" + e + "&endValue=" + a + "&button=" + b
                    } else {
                        if (a == d) {
                            alert("You are already viewing last page!")
                        }
                    }
                } else {
                    if (b == "Previous")
                    {
                        if (e < d && e > 0) {
                            document.location = "nextPartList.action?startValue=" + e + "&endValue=" + a + "&button=" + b
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
                                document.location = "nextPartList.action?startValue=" + e + "&endValue=" + a + "&button=" + b
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
                                    document.location = "nextPartList.action?startValue=" + e + "&endValue=" + a + "&button=" + b
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
                document.location = "nextPartList.action?startValue=" + startValue + "&endValue=" + endValue + "&button=" + b

            }



            function goToAddPartner() {
                window.location = "../partner/addPartner.action";
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
        List partnerList = null;
        int noOfPages = 0;
    %>
    <body  onload="setStyle('mainTp', 'partnerList')">
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
                                <h3>Partner Info</h3>
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
                            <h3>Partner Search</h3>     
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
                                        <s:form action="../partner/partnerSearch.action" method="post" name="partnerSearchForm" id="partnerSearchForm" theme="simple">
                                            <tr>
                                                <td class="lableLeft">Partner&nbsp;Name</td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="partnerName" id="partnerName" tabindex="1" value="%{partnerName}" onchange="fieldLengthValidator(this);"/>
                                                </td>

                                                <td class="lableLeft">Status</td>
                                                <td>

                                                    <s:select list="#@java.util.LinkedHashMap@{'ACTIVE':'ACTIVE','INACTIVE':'INACTIVE'}" name="status" id="status" value="%{status}" tabindex="13" headerkey="-1" cssStyle="width : 150px"/>
                                                </td>

                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Internal&nbsp;Identifier</td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="internalIdentifier" id="internalIdentifier" tabindex="2" value="%{internalIdentifier}" onchange="fieldLengthValidator(this);"/>
                                                </td>


                                                <td class="lableLeft">Partner&nbsp;Identifier</td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="partnerIdentifier" id="partnerIdentifier" tabindex="3" value="%{partnerIdentifier}" onchange="fieldLengthValidator(this);makeUpperCase(this);"/>
                                                </td>

                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Application&nbsp;ID</td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="applicationId" id="applicationId" value="%{applicationId}" tabindex="4" onchange="fieldLengthValidator(this);"/>
                                                </td>


                                                <td class="lableLeft">Country&nbsp;Code</td>
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="countryCode" id="countryCode" value="%{countryCode}" tabindex="5" onchange="fieldLengthValidator(this);"/>
                                                    <%--  <s:select headerKey="-1" headerValue="Select State" list="statesMap" name="state" id="state" value="%{state}" tabindex="11" cssStyle="width : 150px"/> --%>
                                                </td>

                                            </tr>
                                            <tr >
                                                <td style="background-color: white;">
                                                    <strong><input type="button" value="Add" class="button" tabindex="33" onclick="goToAddPartner();"/></strong>
                                                        <s:submit value="Search" cssClass="button" tabindex="8"/>
                                                    <strong><input type="button" value="Reset" class="button" tabindex="13" onclick="return resetvalues();"/></strong>

                                                </td>

                                            </tr>



                                        </tbody>
                                    </table>
                                </div>

                            </div>
                            <a><img src='../includes/images/dtp/cal_plus.gif' alt="nag"width="13" height="9" border="0" onclick="javascript:hideSearch()" id="fsCollImg"/></a>  
                        </div>


                        <s:if test="#session.partnerList != null"> 
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
                                                if (session.getAttribute("partnerList") != null) {

                                                    partnerList = (List) session.getAttribute("partnerList");
                                                    //  out.println("searchResult size-->"+searchResult.size());
                                                    if (null != partnerList && partnerList.size() != 0) {
                                                        resultCount = partnerList.size();
                                                    }
                                                }
                                            %>
                                            <%
                                                if (partnerList.size() != 0) {
                                                    noOfPages = Integer.parseInt(session.getAttribute("noOfPages").toString());
                                            %>

                                            <%}%> 
                                            <input type="hidden" name="sec_lt_list" id="sec_lt_list" value="partnerList"/> 
                                           <thead> <tr>
                                                <th >Action</th>
                                                <th >PartnerName </th>
                                                <th>InternalIdentifier</th>
                                                <th>PartnerIdentifier</th>
                                                <th>ApplicationId </th>
                                                <th>CountryCode </th>
                                                <th>Status </th>
                                                <th>CreatedDate </th>
                                            </tr></thead><tbody>

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
                                                if (session.getAttribute("partnerList") != null) {
                                            %>
                                            <input type="hidden" name="strIntStartGrid" id="strIntStartGrid" value="<%=strIntStartGrid%>"/> 
                                            <input type="hidden" name="strIntEndGrid" id="strIntEndGrid" value="<%=strIntEndGrid%>"/> 

                                            <%
                                                List partnerList = (List) session.getAttribute("partnerList");

                                                //resultCount = 0;
                                                if (null != partnerList) {
                                                    resultCount = partnerList.size();
                                                }

                                                for (int i = strIntStartGrid, j = 0; i < strIntEndGrid; i++, j++) {
                                                    PartnerBean partnerBean = (PartnerBean) partnerList.get(i);

                                            %>

                                            <tr>

                                                <td style="text-align: left"><%-- <a href="#"> --%>
                                                    <%                                                            String id = partnerBean.getPartnerIdentifier();
                                                    %>
                                                    <s:url var="myUrl" action="../partner/partnerEdit.action">
                                                        <s:param name="partnerIdentifier"><%=id%></s:param>
                                                        <%--    <s:param name="tpid" value="%{tpid}"></s:param>
                                                            <s:param name="tpname" value="%{tpname}"></s:param> --%>

                                                    </s:url>

                                                    <s:a href='%{#myUrl}' onmouseover="Tip('Click here to Edit Partner.')" onmouseout="UnTip()"><img src="../includes/images/Edit.gif"></s:a>

                                                    <%--  </a> --%>
                                                </td>

                                                <td style="text-align: left">
                                                    <a href="#" onclick="getPartnerDetails('<%=partnerBean.getPartnerIdentifier()%>')" onmouseover="Tip('Click here to view Detail Info.')" onmouseout="UnTip()"> 
                                                        <%
                                                            out.println(partnerBean.getPartnerName());
                                                        %></a>
                                                </td>

                                                <td style="text-align: left">
                                                    <%
                                                        out.println(partnerBean.getInternalIdentifier());
                                                    %>
                                                </td>
                                                <td style="text-align: left">
                                                    <%
                                                        out.println(partnerBean.getPartnerIdentifier());
                                                    %>
                                                </td>
                                                <td style="text-align: left">
                                                    <%
                                                        out.println(partnerBean.getApplicationId());
                                                    %>
                                                </td>
                                                <td style="text-align: left">
                                                    <%
                                                        out.println(partnerBean.getCountryCode());
                                                    %>
                                                </td> <td style="text-align: left">
                                                    <%
                                                        out.println(partnerBean.getStatus());
                                                    %>
                                                </td> <td style="text-align: left">
                                                    <%
                                                        out.println(partnerBean.getCreatedDate());
                                                    %>
                                                </td>

                                            </tr>
                                            <%
                                                }
                                            %>
                                           </tbody> <tr>
                                                <td bgcolor="white" class="fieldLabelLeft" colspan="4" style="text-align: left; border: 0;">
                                                    <%if (strIntEndGrid != resultCount) {%>
                                                    Total Records : <%=resultCount%>&nbsp;Page <%=strIntEndGrid / 10%>  of <%=noOfPages%>
                                                    <%} else {%>
                                                    Total Records : <%=resultCount%>&nbsp;Page <%=noOfPages%>  of <%=noOfPages%>
                                                    <%}%>
                                                </td>
                                                <td colspan="7" align="right" bgcolor="white" style="text-align: right;border: 0;"><%    if (partnerList.size() != 0) {%>
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