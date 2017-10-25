<%-- 
    Document   : DocRepository
    Created on : Mar 11, 2013, 10:03:37 AM
    Author     : Nagireddy seerapu 
--%>
<%@page import="com.mss.ediscv.util.DataSourceDataProvider"%>
<%@page import="com.mss.ediscv.issues.IssueBean"%>
<%-- <%@ page contentType="text/html" pageEncoding="UTF-8"%> --%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> --%>
<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<%@page import="com.mss.ediscv.doc.DocRepositoryBean"%>
<%@ taglib uri="/WEB-INF/tlds/dbgrid.tld" prefix="grd"%>
<%@ page import="com.freeware.gridtag.*"%>
<%@page import="java.sql.Connection"%>
<%@  page import="com.mss.ediscv.util.AppConstants"%>
<%@ page import="com.mss.ediscv.util.ConnectionProvider"%>
<%@ page import="java.sql.SQLException"%>

<!DOCTYPE html>
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
        <%--   <script language="JavaScript"
        src='<s:url value="/includes/js/generalValidations.js"/>'></script>  --%>
        <script>
            var myCalendar;
            function doOnLoad() {
                myCalendar = new dhtmlXCalendarObject(["issuedatepickerfrom", "issuedatepicker"]);
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

            function getDetails(val, ponum) {
                getDocDetails(val, ponum);
            }

            function resetvalues()
            {

                document.getElementById('issuedatepickerfrom').value = "";
                document.getElementById('issuedatepicker').value = "";
                document.getElementById('category').value = "-1";
                document.getElementById('priority').value = "-1";
                document.getElementById('assignment').value = "";
                document.getElementById('tid').value = "";

                $('#detail_box').hide();
                $('#gridDiv').hide();

            }


        </script>




    </head>
    <%
        String check = null;
        if (request.getAttribute("check") != null) {
            check = request.getAttribute("check").toString();
        }

    //System.out.println("check-->"+check);
%>
    <body onload="doOnLoad();
        initDateTime('issuedatepickerfrom', 'issuedatepicker', '<%=check%>');
        setStyle('docRep', '');">

        <script type="text/javascript" src='<s:url value="/includes/js/wz_tooltip.js"/>'></script>
        <div id="wrapper">
            <div id="main">
                <header>
                    <div id="logo">
                        <s:include value="/includes/template/head.jsp"/>         
                    </div>
                    <nav>
                        <ul class="sf-menu" id="nav">
                            <s:include value="/includes/template/orderToCashMenu.jsp"/>
                        </ul> 
                    </nav>
                </header>
                <div id="site_content">
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
                            <%if (request.getAttribute("searchType").toString().contains("Issues")) {%>
                            <h3>Search Issues</h3>
                            <%} else {%>
                            <h3>Search Tasks</h3>
                            <%}%>
                            <div &nbsp; style="alignment-adjust:central;" >
                                <%String contextPath = request.getContextPath();
                                %>
                                <table >
                                    <tbody >
                                        <s:form action="%{formAction}" method="post" name="searchIssueForm" id="searchIssueForm" theme="simple" >

                                            <tr>
                                                <td class="lableLeft">Date From </td>
                                                <td><%-- <input type="text" id="datepickerfrom" /> --%>
                                                    <%--  <input type="text" name="datepickerfrom" id="datepickerfrom" class="inputStyle" tabindex="2" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="issuedatepickerfrom" id="issuedatepickerfrom"  value="%{issuedatepickerfrom}" tabindex="1"  onkeypress="enterDate();"/>
                                                    <a href="javascript:copyValuTo('issuedatepickerfrom','issuedatepicker');"><img border="0" src="<%= contextPath%>/includes/images/lm/arrow.gif" width="7"
                                                                                                                                   height="9"></a>
                                                </td>
                                                <td class="lableLeft">Date To </td>
                                                <td><%-- <input type="text" id="datepicker" /> --%>
                                                    <%--  <input type="text" name="datepicker" id="datepicker" class="inputStyle" tabindex="2" />  --%>
                                                    <s:textfield cssClass="inputStyle" name="issuedatepicker"  value="%{issuedatepicker}" id="issuedatepicker" tabindex="2"  onkeypress="enterDate();"/>
                                                </td>

                                            </tr>

                                            <%if (request.getAttribute("searchType").toString().contains("All")) {%>
                                            <tr>
                                                <td class="lableLeft">Category 
                                                <td>
                                                    <s:select headerKey="-1" headerValue="Select Type" list="categoryMap" name="category" id="category" value="%{category}" tabindex="3" cssStyle="width : 150px"/>
                                                </td>

                                                <td class="lableLeft">Priority 
                                                <td>
                                                    <s:select headerKey="-1" headerValue="Select Type" list="priorityMap" name="priority" id="priority" value="%{priority}" tabindex="3" cssStyle="width : 150px"/>

                                                </td>

                                            </tr>

                                            <tr>
                                                <td class="lableLeft" rowspan="2">Assignment 
                                                <td rowspan="2">
                                                    <s:select headerKey="-1"   list="usersMap" name="assignment" id="assignment" value="%{assignment}" tabindex="3" multiple="true" cssStyle="width : 220px"/>
                                                    <%--<s:textfield cssClass="inputStyle" name="commId" id="commId" tabindex="1" value="%{commId}" onchange="return makeUpperCase(this);" maxLength="50"/> --%>
                                                </td>

                                                <td class="lableLeft">Ticket ID
                                                <td>
                                                    <s:textfield cssClass="inputStyle" name="tid" id="tid" value="%{tid}" tabindex="5"  />
                                                    <%--<s:textfield cssClass="inputStyle" name="tpStatus" id="tpStatus" tabindex="2" value="%{tpStatus}" />  --%>
                                                </td>

                                            </tr>

                                            <%}%>



                                            <tr></tr>

                                            <tr>
                                                <td style="background-color: white;" colspan="3">

                                                    <s:submit   value="Search" cssClass="button"  tabindex="28"/> 
                                                    <strong><input type="button" value="Reset" class="button" tabindex="30" onclick="return resetvalues();"/></strong>
                                                </td>
                                            </tr>
                                        </s:form>

                                    </tbody>
                                </table> 

                            </div>
                            <%--  out.print("contextPath-->"+contextPath); --%>


                        </div>
                        <a><img src='../includes/images/dtp/cal_plus.gif'  width="13" height="9" border="0" onclick="javascript:hideSearch()" id="fsCollImg"/></a>  
                    </div>


                    <s:if test="#session.issuesList != null"> 
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
                                                    java.util.List list = (java.util.List) session.getAttribute(AppConstants.SES_ISSUE_LIST);

                                                    if (list.size() != 0) {
                                                        IssueBean issueBean;
                                                %>
                                                <tr>
                                                    <td>TICKET ID</td>
                                                    <td >CATEGORY</td> 
                                                    <td >PRIORITY</td>
                                                    <td >CREATED_DATE</td>

                                                    <td >DEV. EST TIME</td>

                                                    <td >STATUS</td>
                                                    <td >ASSIGNED TO</td>


                                                </tr>
                                                <tr>

                                                    <%
                                                        for (int i = 0; i < list.size(); i++) {
                                                            issueBean = (IssueBean) list.get(i);

                                                            if (i % 2 == 0) {
                                                                cssValue = "whiteStripe";
                                                            } else {
                                                                cssValue = "grayEditSelection";
                                                            }
                                                    %>
                                                    <td ><%-- <a href="#"> --%>
                                                        <%
                                                            int id = issueBean.getId();
                                                            String searchType = request.getAttribute("searchType").toString();
                                                        %>
                                                        <s:url var="myUrl" action="../issues/issueEdit.action">
                                                            <s:param name="id"><%=id%></s:param>
                                                            <s:param name="searchType"><%=searchType%></s:param>
                                                            <%--  <s:param name="tpid" value="%{tpid}"></s:param>
                                                              <s:param name="tpname" value="%{tpname}"></s:param> --%>

                                                        </s:url>

                                                        <s:a href='%{#myUrl}' onmouseover="Tip('Click here to Edit Issue')" onmouseout="UnTip()"><%
                                                            out.println(id);
                                                            %></s:a>

                                                        <%--  </a> --%>
                                                    </td>
                                                    <td>
                                                        <%
                                                            out.println(issueBean.getCategory());
                                                        %>
                                                    </td>
                                                    <td><%-- <a href="javascript:getDetails('<%=issueBean.getFile_id()%>','<%=issueBean.getPoNumber()%>');">--%>
                                                        <%
                                                            out.println(issueBean.getPriority());
                                                        %>
                                                        </a>
                                                    </td>
                                                    <td>
                                                        <%
                                                            out.println(issueBean.getCreated_date().toString().substring(0, issueBean.getCreated_date().toString().lastIndexOf(":")));
                                                        %>

                                                    </td> 
                                                    <td>
                                                        <%
                                                            out.println(issueBean.getDevEstTime());

                                                        %>

                                                    </td>


                                                    <td>
                                                        <%                        out.println(issueBean.getStatus());
                                                        %>

                                                    </td>  



                                                    <td>
                                                        <%
                                                            out.println(issueBean.getAssignTo());
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
                                                                // out.println("<img  border='0' align='top'  src='"+contextPath+"/includes/images/alert.gif'/><b> No Records Found to Display!</b>");
                                                                out.println("<img  border='0' align='top'  src='" + contextPath + "/includes/images/alert.gif'/><b>No records found for the given search criteria. Please try a different search criteria!</b>");
                                                            }

                                                        %>
                                                    </td>
                                                </tr>
                                            </table>

                                        </td>
                                    </tr>
                                    <%                 if (list.size() != 0) {
                                    %>
                                    <tr >
                                        <td align="right" colspan="28" style="background-color: white;">
                                            <div align="right" id="pageNavPosition">hello</div>
                                        </td>
                                    </tr> 
                                    <% }%>
                                </table>

                            </div>
                            <%-- Process butttons  start --%>
                            <%--    <table align="right">
                                     <tr>
                                          <td style="background-color: white;">
                             <strong><input type="button" value="Generate Excel" class="button" onclick="return gridDownload('document','xls');" id="excel"/></strong>
                             </td>
                                     </tr>
                                 </table> --%>
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
