<%-- 
    Document   : index
    Created on : Mar 11, 2013, 8:54:46 AM
    Author     : Nagireddy Seerapu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Red Classic Transportation Service Portal</title>
        <meta name="description" content="website description" />
        <meta name="keywords" content="website keywords, website keywords" />
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
        <!--  <link rel="stylesheet" type="text/css" href="/includes/css/style.css" /> -->
        <link rel="stylesheet" href='<s:url value="/includes/css/style.css"/>'
              type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/jquery-ui.css"/>'
              type="text/css">

        <link rel="stylesheet" href='<s:url value="/includes/css/footerstyle.css"/>'
              type="text/css"/>

        <script language="JavaScript"
        src='<s:url value="/includes/js/modernizr-1.5.min.js"/>'></script>

        <script language="JavaScript"
        src='<s:url value="/includes/js/jquery-1.9.1.js"></s:url>'></script>
            <script language="JavaScript"
            src='<s:url value="/includes/js/jquery-ui.js"/>'></script>
        <%--   <script language="JavaScript"
          src='<s:url value="/includes/js/jquery-latest.js"/>'></script> --%>
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
                        <%-- <div id="logo_text">
                            <h1><a href="<s:url action="/general/index"/>">Miracle Supply Chain Visiblity Portal</a></h1>
                          </div>   --%>
                        <s:include value="/includes/template/indexHead.jsp"/>  

                    </div>
                    <nav>
                        <ul class="sf-menu" id="nav">
                            <%-- <li class="current"><a href="index.html">Manfacturing : Order to Cash</a></li> --%>

                            <li class="current"><a href="<s:url action="general/index"/>">Manufacturing : Order to Cash</a></li>
                            <li><a href="<s:url action="general/ltIndex"/>">Logistics : Load Tendering</a></li>
                            <li ><a href="#">Retailer : Warehouse to Stores</a></li>


                        </ul>
                    </nav>
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
                            <h2>Order to Cash Flow</h2>
                            <div &nbsp; style="alignment-adjust:central;" >


                                <p> Miracle Supply Chain Visibility provides a complete view of your inbound supply and outbound fulfillment activity across your supply chain network by providing and leveraging connectivity to all your supply chain trading partners, and enterprise applications.</p>
                                <p> Supply chain visibility (SCV) is the ability of parts, components or products in transit to be tracked from the manufacturer to their final destination. The goal of SCV is to improve and strengthen the supply chain by making data readily available to all stakeholders, including the customer. Supply chain visibility technology promotes quick response to change by allowing privileged users to take action and reshape demand or redirect supply.</p>
                                <p> Search link to  
                                    <!--  <a href="/MSCVP/doc/DocRepository.jsp">Document Repository</a></p> -->
                                    <a href="<s:url action="general/login"/>">Document Repository</a> </p>
                            </div>



                        </div>
                    </div>



                    <div id="sec_box" style="display: none;"> 
                        <div class="content">
                            <div class="content_item" >

                                <div class="CSSTableGenerator" >



                                    <table  border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">

                                        <tr align="center">
                                            <td class="tbd">PO #</td>
                                            <td class="tbd">Item Qty</td>
                                            <td class="tbd">Invoice No </td>
                                            <td class="tbd">Invoice Amount</td>
                                        </tr>
                                        <tr>
                                            <td><a href="#" id="detail_link" >1001</></td>
                                            <td class="tbd">3</td>
                                            <td class="tbd">6</td>
                                            <td class="tbd">$300</td>
                                        </tr>
                                        <tr>
                                            <td><a href="#" id="detail_link" >1001</></td>
                                            <td class="tbd">3</td>
                                            <td class="tbd">6</td>
                                            <td class="tbd">$300</td>
                                        </tr>

                                        <tr>
                                            <td><a href="#" id="detail_link" >1001</></td>
                                            <td class="tbd">3</td>
                                            <td class="tbd">6</td>
                                            <td class="tbd">$300</td>
                                        </tr>

                                        <tr>
                                            <td><a href="#" id="detail_link" >1001</></td>
                                            <td class="tbd">3</td>
                                            <td class="tbd">6</td>
                                            <td class="tbd">$300</td>
                                        </tr>

                                        <tr>
                                            <td><a href="#" id="detail_link" >1001</></td>
                                            <td class="tbd">3</td>
                                            <td class="tbd">6</td>
                                            <td class="tbd">$300</td>
                                        </tr>

                                    </table>


                                </div>


                            </div>





                        </div>
                    </div>
                </div>
                <%--   <div id="footer">
                       <h2><font color="white">&#169 2013 Miracle Software Systems, Inc. All rights reserved</font></h2>
                              </div>--%>
            </div> 
        </div>
        <footer> 
            <p><font color="white">&#169 <%= new java.text.SimpleDateFormat("yyyy").format(new java.util.Date())%> Miracle Software Systems, Inc. All rights reserved</font></p>
        </footer>
    </body>
</html>
