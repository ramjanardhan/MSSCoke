<%-- 
    Document   : Exception
    Created on : Mar 12, 2013, 2:14:50 AM
    Author     : Nagireddy
--%>
<%-- <%@ page contentType="text/html" pageEncoding="UTF-8"%> --%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException"%>
<%@ page import="com.mss.ediscv.util.AppConstants" %>
<%@ page import="com.mss.ediscv.util.ConnectionProvider" %>
<%@ page import="com.mss.ediscv.util.ServiceLocatorException" %>
<%@ page import="org.apache.log4j.Logger" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

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

        <link rel="stylesheet" href='<s:url value="/includes/css/footerstyle.css"/>'
              type="text/css"/>

        <script language="JavaScript"
        src='<s:url value="/includes/js/jquery-1.9.1.js"></s:url>'></script>
            <script language="JavaScript"
            src='<s:url value="/includes/js/jquery-ui.js"/>'></script>

        <script language="JavaScript"
        src='<s:url value="/includes/js/modernizr-1.5.min.js"/>'></script>
        <link href="../includes/images/truck_image.jpg" rel="shortcut icon"/>
        <script>
            $(function () {
                $("#datepicker").datepicker();
                $("#datepickerfrom").datepicker();
            });

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
        </script>
    </head>
    <body>
        <div id="wrapper">
            <div id="main">
                <header>
                    <div id="logo">

                        <s:include value="/includes/template/head.jsp"/>         
                    </div>

                    <!-- nav start -->
                    <nav>
                        <ul class="sf-menu" id="nav">


                            <%if (session.getAttribute(AppConstants.SES_USER_DEFAULT_FLOWID).toString().equals("2")) {%>
                            <s:include value="/includes/template/orderToCashMenu.jsp"/>
                            <%} else if (session.getAttribute(AppConstants.SES_USER_DEFAULT_FLOWID).toString().equals("3")) {%>
                            <s:include value="/includes/template/logisticsMenu.jsp"/>
                            <%}%>
                        </ul>  
                    </nav> 

                    <!-- nav end -->
                </header>
                <div id="site_content">
                    <div id="sidebar_container">


                        <div id="detail_box" style="display: none;"> 
                            <div class="sidebar">
                                <h3>Detail Information</h3>
                                <div class="sidebar_item">
                                    <h4>Detail 1</h4>
                                    <h5>Detail 2</h5>
                                    <h5>Detail 2</h5>
                                </div>
                            </div>


                            <div class="sidebar_base"></div>
                        </div>


                    </div>

                    <div class="content">
                        <div class="content_item">
                            <!-- heading for the content -->
                            <h3>Access Failed</h3>
                            <!-- end of the heading tag -->
                            <!-- content div start -->
                            <div &nbsp; style="alignment-adjust:central;" >
                                <%-- <img alt="Error Header" src="<s:url value="/includes/images/errorHeader_847x150.jpg"/>" width="700" height="120" border="0">--%>
                                <%-- <table >
                                <tbody >
                                 <tr>
                                     <td valign="top">  --%>
                                <%--<img alt="Error Header" src="<s:url value="/includes/images/errorHeader_847x150.jpg"/>" width="700" height="120" border="0">--%>
                                <sx:tabbedpanel id="AccessFailedPannel" cssStyle="width: 545px; height: 500px;padding-left:10px;padding-top:5px;" doLayout="true" >
                                    <sx:div id="AccessFailedTab" label="Access Failed"> 
                                        <table cellpadding="0" cellspacing="0" border="0" align="center">
                                            <tr>
                                                <td style="padding-top:40px;">
                                                    <object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0" width="462" height="284" id="header" align="middle">
                                                        <param name="allowScriptAccess" value="sameDomain" />
                                                        <param name="movie" value="/<%=AppConstants.CONTEXT_PATH%>/includes/flash/accessFailed_462x284.swf" />
                                                        <param name="quality" value="high" />
                                                        <param name="bgcolor" value="#000000" />
                                                        <embed src="/<%=AppConstants.CONTEXT_PATH%>/includes/flash/accessFailed_462x284.swf" quality="high" bgcolor="#000000" width="462" height="284" name="header" align="middle" allowScriptAccess="sameDomain" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
                                                    </object>                
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <font style="color: darkblue;font-size: 15px">Please select your choice from menu.</font> 
                                                </td>
                                            </tr>
                                        </table>
                                    </sx:div>
                                </sx:tabbedpanel>
                                <%--  </td>
                             </tr>
                             
                             
                            </tbody>
                          </table> --%>


                            </div>
                            <!-- end of the conetnt -->
                        </div>
                    </div>



                    <div id="sec_box" style="display: none;"> 
                        <div class="content">
                            <div class="content_item">


                                <div class="CSSTableGenerator" >


                                    <table >
                                        <tr align="center">
                                            <td >ISA #</td>
                                            <td >Document Type</td>
                                            <td >File Format</td>
                                            <td>Direction</td>
                                            <td>Date</td>
                                            <td>Status</td>
                                        </tr>
                                        <tr align="center">
                                            <td><a href="#" id="detail_link" >1001</a></td>
                                            <td>850</td>
                                            <td>EDI</td>
                                            <td>Inbound</a></td>
                                            <td>Feb 27 2013</td>
                                            <td>Shipped</td>
                                        </tr>
                                        <tr align="center">
                                            <td><a href="#" id="detail_link" >1001</a></td>
                                            <td>850</td>
                                            <td>EDI</td>
                                            <td>Inbound</a></td>
                                            <td>Feb 27 2013</td>
                                            <td>Shipped</td>
                                        </tr>

                                        <tr align="center">
                                            <td><a href="#" id="detail_link" >1001</a></td>
                                            <td>850</td>
                                            <td>EDI</td>
                                            <td>Inbound</a></td>
                                            <td>Feb 27 2013</td>
                                            <td>Shipped</td>
                                        </tr>
                                        <tr align="center">
                                            <td><a href="#" id="detail_link" >1001</a></td>
                                            <td>850</td>
                                            <td>EDI</td>
                                            <td>Inbound</a></td>
                                            <td>Feb 27 2013</td>
                                            <td>Shipped</td>
                                        </tr>
                                        <tr align="center">
                                            <td><a href="#" id="detail_link" >1001</a></td>
                                            <td>850</td>
                                            <td>EDI</td>
                                            <td>Inbound</a></td>
                                            <td>Feb 27 2013</td>
                                            <td>Shipped</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

            </div>
        </div>
    <footer> 
        <p><font color="white">&#169 <%= new java.text.SimpleDateFormat("yyyy").format(new java.util.Date())%> Miracle Software Systems, Inc. All rights reserved</font></p>
    </footer>
</body>
</html>

