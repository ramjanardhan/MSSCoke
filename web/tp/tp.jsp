<%-- 
    Document   : PurchaseOrder
    Created on : Mar 11, 2013, 10:59:42 AM
    Author     : Nagiredddy Seerapu
--%>

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
        <script>
            /* $(function() {
             $( "#podatepicker" ).datepicker();
             $( "#podatepickerfrom" ).datepicker();
             });
             */
            var myCalendar;
            function doOnLoad() {
                document.getElementById("update").style.display = 'none';
                myCalendar = new dhtmlXCalendarObject(["podatepickerfrom", "podatepicker"]);
                myCalendar.setSkin('omega');
                myCalendar.setDateFormat("%m/%d/%Y");
                myCalendar.hideTime();

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

            $('.check_link').click(function ()
            {
                var thisCheck = $(this);
                if (thischeck.is(':checked'))
                {
                    $('#check_box').show();
                }
            });
            // New function to show the left grid

            function getDetails(val) {
                getPoDetails(val);
            }
            function getList()
            {
                // alert('hello');
                tradingPartnerForm.action = "../tp/getTpList.action";
                tradingPartnerForm.submit();
            }
            function checkTpValues() {
                // alert("hiiiiii");
                var id = document.getElementById("id").value;
                var name = document.getElementById("name").value;
                var commid = document.getElementById("commid").value;
                var phno = document.getElementById("phno").value;
                // alert("id--->"+id);
                if ((id == null) || (id == "")) {
                    alert("Please Enter Id!");
                    return false;
                } else if ((name == null) || (name == "")) {
                    alert("Please enter Name!");
                    return false;
                }
                else if ((commid == null) || (commid == "")) {
                    alert("Please enter EDI COMM ID!");
                    return false;
                } else if ((phno == null) || (phno == "")) {
                    alert("Please enter Phone Number!");
                    return false;
                }
                else
                {
                    return true;
                    //alert("Please enter atleast Id,Name!");
                }
            }

            function resetvalues()
            {
                document.getElementById('id').value = "";
                document.getElementById('name').value = "";
                document.getElementById('contact').value = "";
                document.getElementById('phno').value = "";
                document.getElementById('dept').value = "";
                document.getElementById('commid').value = "";
                document.getElementById('qualifier').value = "";

                $('#detail_box').hide();
                $('#gridDiv').hide();

            }

            function getContact(detail)
            {
                // alert("in contact"+detail);

                $(function () {

                    $('#detail_box').show();
                    return false;

                });
                var details = detail;
                var detailsDIV = document.getElementById("detailInformation");
                detailsDIV.innerHTML = "";
                detailsDIV.innerHTML = details;


            }
        </script>


    </head>
    <body onload="doOnLoad();">
        <script type="text/javascript" src='<s:url value="/includes/js/wz_tooltip.js"/>'></script>
        <div id="wrapper">
            <div id="main">
                <header>
                    <div id="logo">
                        <s:include value="/includes/template/head.jsp"/>    

                    </div>
                    <nav>
                        <ul class="sf-menu" id="nav">
                            <%-- <li><a href="<s:url action="../doc/orderToCash"/>">Document Repository</a></li>
                             <li ><a href="<s:url action="../po/purchaseOrder"/>">Purchase Orders</a></li>
                             <li><a href="<s:url action="../shipment/shipmentAction"/>">Shipments</a></li>
                             <li><a href="<s:url action="../inv/invoiceAction"/>">Invoices</a></li>
                             <li><a href="<s:url action="../payment/paymentAction"/>">Payments</a></li>
                              <%
                               // out.println(session.getAttribute(AppConstants.SES_ROLE_ID));
                                
                                if(session.getAttribute(AppConstants.SES_ROLE_ID).equals("101")){
                             %>
                             <li class="current"><a href="<s:url action="../tp/addTP"/>">Trading Partner</a></li>
                             <%}%>  --%>
                            <s:include value="/includes/template/tpMenu.jsp"/>
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

                    <div class="content" >
                        <div class="content_item" id="searchdiv">
                            <h3>Trading Partner</h3>     
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
                                        <s:form action="../tp/tpAdd.action" method="post" name="tradingPartnerForm" id="tradingPartnerForm" theme="simple">
                                            <tr>
                                                <td class="lableLeft">Id <font color="red">*</font></td>
                                                <td>
                                                    <%-- <input type="text" class="inputStyle" name="loginId" id="loginId" onkeypress="return handleEnter(this,event);" tabindex="1" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="id" id="id" tabindex="1"/>
                                                </td>

                                                <td class="lableLeft">Name<font color="red">*</font></td>
                                                <td>

                                                    <s:textfield cssClass="inputStyle" name="name" id="name" tabindex="2" onchange="return makeUpperCase(this);"/>
                                                </td>

                                            </tr>

                                            <tr>
                                                <td class="lableLeft" rowspan="3">Contact </td>
                                                <td rowspan="3">
                                                    <%-- <input type="text" class="inputStyle" name="loginId" id="loginId" onkeypress="return handleEnter(this,event);" tabindex="1" /> --%>
                                                    <s:textarea cssClass="inputStyle" name="contact" id="contact" tabindex="3" cols="18" rows="3"/>
                                                </td>

                                                <td class="lableLeft">Phone Number<font color="red">*</font></td>
                                                <td>

                                                    <s:textfield cssClass="inputStyle" name="phno" id="phno" tabindex="4" onkeypress="return isNumberKey(event);return formatPhone(this);" maxlength="14"/>
                                                </td>

                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Department</td>
                                                <td>

                                                    <s:textfield cssClass="inputStyle" name="dept" id="dept" tabindex="5"/>
                                                </td>

                                            </tr>
                                            <tr>
                                                <td class="lableLeft">EDI Comm Id <font color="red">*</font></td>
                                                <td>  <s:textfield cssClass="inputStyle" name="commid" id="commid" tabindex="6" onchange="return makeUpperCase(this);return alphaNumeric(this);"/></td>

                                            </tr>

                                            <tr>


                                                <td class="lableLeft">Qualifier</td>
                                                <td>

                                                    <s:textfield cssClass="inputStyle" name="qualifier" id="qualifier" tabindex="7"/>
                                                </td>

                                            </tr>

                                            <%-- <tr>
                                                 <td style="background-color: white;" colspan="3">
                                                     <s:submit value="Add" cssClass="button" onclick="return checkTpValues();" tabindex="3"/>
                                                 
                                                  
                                                  <strong><input type="button" value="Reset" class="button" tabindex="4" onclick="return resetvalues();"/></strong>
                                                 
                                                      <input type="button" value="List" class="button" onclick="javascript:getList();" tabindex="5"/>
                                                 </td>  
                                             </tr>  --%>
                                            <tr id="add">
                                                <td style="background-color: white;" colspan="3">
                                                    <s:submit   value="Add" cssClass="button" onclick="return checkTpValues();" tabindex="8"/>

                                                    <%-- <s:reset value="Reset" cssClass="button" tabindex="11"/> --%>
                                                    <strong><input type="button" value="Reset" class="button" tabindex="9" onclick="return resetvalues();"/></strong>

                                                    <input type="button" value="List" class="button" onclick="javascript:getList();" tabindex="10"/>
                                                </td>
                                            </tr>
                                            <tr id="update">
                                                <td style="background-color: white;" colspan="3">

                                                    <strong><input type="button" value="Update" class="button" tabindex="8" onclick="return doTpUpdate();"/></strong>
                                                        <%-- <s:reset value="Reset" cssClass="button" tabindex="11"/> --%>
                                                        <%-- <strong><input type="button" value="Reset" class="button" tabindex="9" onclick="return resetvalues();"/></strong>  --%>

                                                    <%-- <input type="button" value="List" class="button" onclick="javascript:getList();" tabindex="10"/>  --%>
                                                </td>
                                            </tr>


                                        </s:form>
                                    </tbody>
                                </table>
                            </div>

                        </div>
                        <a><img src='../includes/images/dtp/cal_plus.gif' alt="nag"width="13" height="9" border="0" onclick="javascript:hideSearch()" id="fsCollImg"/></a>  
                    </div>


                    <s:if test="#session.tpList != null"> 
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
                                                    java.util.List list = (java.util.List) session.getAttribute(AppConstants.SES_TP_LIST);

                                                    if (list.size() != 0) {
                                                        TpBean tpBean;
                                                %>
                                                <tr>
                                                    <td >ID # </td>
                                                    <td >Name </td>
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

                                                        <a href="#" onclick="javascript:doEdit('<%=tpBean.getId()%>');" onmouseover="Tip('Click here to Edit.')" onmouseout="UnTip()"><%
                                                            out.println(tpBean.getId());
                                                            %></a>

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
                <div id="footer">
                    <h2><font color="white">&#169 <%= new java.text.SimpleDateFormat("yyyy").format(new java.util.Date())%> Miracle Software Systems, Inc. All rights reserved</font></h2>
                </div>
            </div>        

        </div>  


    </body>
</html>
