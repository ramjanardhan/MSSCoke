<%-- 
    Document   : logisticsShipment
    Created on : Jun 27, 2013, 10:04:33 AM
    Author     : miracle
--%>


<%@page import="java.util.List"%>
<%@page import="com.mss.ediscv.logisticsshipment.LtShipmentBean"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>




<%@ taglib uri="/WEB-INF/tlds/dbgrid.tld" prefix="grd"%>
<%@ page import="com.freeware.gridtag.*"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException"%>
<%@ page import="com.mss.ediscv.util.AppConstants" %>
<%@ page import="com.mss.ediscv.util.ConnectionProvider" %>
<%@ page import="com.mss.ediscv.util.ServiceLocatorException" %>
<%@ page import="org.apache.log4j.Logger"%>

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
            src='<s:url value="/includes/js/dbgridDisplay.js"/>'></script>
        <script language="JavaScript"
        src='<s:url value="/includes/js/jquery-ui.js"/>'></script>
        <script language="JavaScript"
        src='<s:url value="/includes/js/DBGrid.js"/>'></script>
        <script language="JavaScript"
        src='<s:url value="/includes/js/DateValidation.js"/>'></script>
        <script language="JavaScript"
        src='<s:url value="/includes/js/GridNavigation.js"/>'></script>
 <script language="JavaScript"
        src='<s:url value="/includes/js/tablesorter.min.js"/>'></script>
        <script language="JavaScript"
        src='<s:url value="/includes/js/generalValidations.js"/>'></script>

        <script language="JavaScript"
        src='<s:url value="/includes/js/common.js"/>'></script>

        <script language="JavaScript"
        src='<s:url value="/includes/js/modernizr-1.5.min.js"/>'></script>

        <script language="JavaScript"
        src='<s:url value="/includes/js/GeneralAjax.js"/>'></script>
        <script language="JavaScript"
        src='<s:url value="/includes/js/dhtmlxcalendar.js"/>'></script>
        <script language="JavaScript"
        src='<s:url value="/includes/js/downloadAjax.js"/>'></script>
        <link href="../includes/images/truck_image.jpg" rel="shortcut icon"/>
        <script>


            /*$(function() {
             $( "#shpdatepicker" ).datepicker();
             $( "#shpdatepickerfrom" ).datepicker();
             });
             */
            var myCalendar;
            function doOnLoad() {
                myCalendar = new dhtmlXCalendarObject(["datepickerfrom", "datepickerTo"]);
                myCalendar.setSkin('omega');
                myCalendar.setDateFormat("%m/%d/%Y %H:%i");
                // myCalendar.hideTime();

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

            /*function getDetails(val,ponum){
             getLogisticsShipmentDetails(val,ponum);
             }*/

            function getDetails(val, ponum, id) {



                //var db = document.forms["purchaseForm"]["database"].value;
                var form = document.forms['ltshipmentForm'];
                var radios = form.elements["database"];
                var db=null;
                for(var i=0;i<radios.length;i++) {
                    if(radios[i].checked == true) {
                        db = radios[i].value;
                    }
                }
                //alert("This is in js id"+id);    

                getLogisticsShipmentDetails(val, ponum, id,db);
            }
            function resetvalues()
            {
                document.getElementById('buId').value = "";
                document.getElementById('datepickerfrom').value = "";
                document.getElementById('datepickerTo').value = "";
                document.getElementById('senderId').value = "";
                document.getElementById('senderName').value = "";
                // document.getElementById('asnNum').value="";
                document.getElementById('recName').value = "";
                // document.getElementById('bolNum').value="";
                //document.getElementById('poNum').value="";   
                //document.getElementById('sampleValue').value="1"; 
                //document.getElementById('ackStatus').value="-1"; 
                document.getElementById('status').value = "-1";
                document.getElementById('docType').value = "-1";
                document.getElementById('corrattribute').value = "-1";
                document.getElementById('corrattribute1').value = "-1";
                document.getElementById('corrvalue').value = "";
                document.getElementById('corrvalue1').value = "";
                $('#detail_box').hide();
                $('#gridDiv').hide();
            }
            function checkCorrelation() {

                //   alert("hiii");
                var db = document.forms["ltshipmentForm"]["database"].value;
                if (db == '') {
                    alert("Please select Database!!!");
                    return false;
                }
                var corrattr = document.getElementById('corrattribute').value;
                var corrval = document.getElementById('corrvalue').value;

                var corrattr1 = document.getElementById('corrattribute1').value;
                var corrval1 = document.getElementById('corrvalue1').value;



                if ((corrattr != "-1") && (corrval == "")) {
                    alert("please enter Correlation Value!!!");
                    return false;
                }
                if ((corrattr == "-1") && (corrval != "")) {
                    alert("please select Correlation!");
                    return false;
                }

                if ((corrattr1 != "-1") && (corrval1 == "")) {
                    alert("please enter Correlation Value!!!");
                    return false;
                }
                if ((corrattr1 == "-1") && (corrval1 != "")) {
                    alert("please select Correlation!");
                    return false;
                }



                var res = Formvalidation(document.getElementById('datepickerfrom').value, document.getElementById('datepickerTo').value);


                return res;
            }

            function gridNext(c) {
                var b = c.id;
                var e = parseInt(document.ltshipmentForm.txtStartGrid.value);
                var a = parseInt(document.ltshipmentForm.txtEndGrid.value);
                var d = parseInt(document.ltshipmentForm.txtMaxGrid.value);
                var form = document.forms['ltshipmentForm'];
                var radios = form.elements["database"];
                var db=null;
                for(var i=0;i<radios.length;i++) {
                    if(radios[i].checked == true) {
                        db = radios[i].value;
                    }
                }
                if (b == "Next") {
                    if (a < d)
                    {
                        document.location = "nextLtShipment.action?startValue=" + e + "&endValue=" + a + "&button=" + b+"&database="+db;
                    } else {
                        if (a == d) {
                            alert("You are already viewing last page!")
                        }
                    }
                } else {
                    if (b == "Previous")
                    {
                        if (e < d && e > 0) {
                            document.location = "nextLtShipment.action?startValue=" + e + "&endValue=" + a + "&button=" + b+"&database="+db;
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
                                document.location = "nextLtShipment.action?startValue=" + e + "&endValue=" + a + "&button=" + b+"&database="+db;
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
                                    document.location = "nextLtShipment.action?startValue=" + e + "&endValue=" + a + "&button=" + b+"&database="+db;
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
                var form = document.forms['ltshipmentForm'];
                var radios = form.elements["database"];
                var db=null;
                for(var i=0;i<radios.length;i++) {
                    if(radios[i].checked == true) {
                        db = radios[i].value;
                    }
                }
				
                document.location = "nextLtShipment.action?startValue=" + startValue + "&endValue=" + endValue + "&button=" + b+"&database="+db;

            }
        </script>
    </head>
    <%
        String check = null;
        if (request.getAttribute("check") != null) {
            check = request.getAttribute("check").toString();
        }
    %>
    <%!
        String strStartGrid;
        String strEndGrid;
        int resultCount = 0;
        int strIntStartGrid;
        int strIntEndGrid;
        List ltShipmentList = null;
        int noOfPages = 0;

    %>
    <%--<body onload="doOnLoad();initDate('shpdatepickerfrom','shpdatepicker','<%=check %>');setStyle('mainShipment','shipmentCurrId');">  --%>
    <body onload="doOnLoad();
            setStyle('logisticsShip', '');"> 
        <script type="text/javascript" src='<s:url value="/includes/js/wz_tooltip.js"/>'></script>
        <div id="wrapper">
            <div id="main">
                <header>
                    <div id="logo">

                        <s:include value="/includes/template/head.jsp"/>    

                    </div>
                    <nav>
                        <ul class="sf-menu" id="nav">

                            <s:include value="/includes/template/logisticsMenu.jsp"/>

                        </ul> 

                    </nav>

                </header>
                <div id="site_content">

                    <div id="orginloadoverlay1"></div>

                    <div id="orginloadpecialBox1">

                        <table style="width: 40%; height: 20%;">

                            <tr class="overlayHeader">
                                <td style="background: #03d1f2;height: 30px"><span class="overlayHeaderFonts" style="font-weight: bold;color: white;">Error Message</span> 

                                    <span style="float: right;">  <a  href="#" onmousedown="errorOverlay()" style="color:white;font-family: myHeaderFonts;">CLOSE</a> </span>

                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <s:form action="" name="addnoti">
                                        <table style="width: 100%; margin-top: -39px">

                                            <tr>

                                                <td> <s:textarea rows="5" cols="48" name="ErrorMsg" id="ErrorMsg" class="selectBoxOverlay"/> </td>

                                            </tr>


                                        </table>
                                    </s:form>

                                </td>    
                            </tr>

                        </table>
                    </div>

                    <div id="sidebar_container">


                        <div id="detail_box" style="display: none;"> 
                            <%--  <div class="sidebar">
                               <h3>Detail Information</h3>
                               <div class="sidebar_item">
                                 <h4>Detail 1</h4>
                                 <h5>Detail 2</h5>
                                  <h5>Detail 2</h5>
                               </div>
                                       </div> --%>
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
                            <h3>Search By Lt Shipments</h3>
                            <div &nbsp; style="alignment-adjust:central;" >
                                <%String contextPath = request.getContextPath();
                                %>
                                <table >
                                    <tbody >
                                        <s:form action="../logisticsshipment/ltShipmentSearch.action" method="post" name="ltshipmentForm" id="ltshipmentForm" theme="simple">
                                            <tr>
                                                <td class="lableLeft">Database&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;</td>
                                                <td><s:radio cssClass="myRadio" id="database" name="database" value="%{database}" list="#@java.util.LinkedHashMap@{'MSCVP':'Live','ARCHIVE':'Archive'}"/></td>
                                            </tr>                                            

                                            <tr>
                                                <td class="lableLeft">Date From </td>
                                                <td><%-- <input type="text" id="datepickerfrom" /> --%>
                                                    <%--  <input type="text" name="datepickerfrom" id="datepickerfrom" class="inputStyle" tabindex="2" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="datepickerfrom" value="%{datepickerfrom}" id="datepickerfrom" tabindex="1" onkeypress="return enterDateInvoice();"/>
                                                    <a href="javascript:copyValuTo('datepickerfrom','datepickerTo');"><img border="0" src="<%= contextPath%>/includes/images/lm/arrow.gif" width="7"
                                                                                                                           height="9"></a>
                                                </td>
                                                <td class="lableLeft">Date To </td>
                                                <td><%-- <input type="text" id="datepicker" /> --%>
                                                    <%--  <input type="text" name="datepicker" id="datepicker" class="inputStyle" tabindex="2" />  --%>
                                                    <s:textfield cssClass="inputStyle" name="datepickerTo" value="%{datepickerTo}" id="datepickerTo" tabindex="2" onkeypress="return enterDateInvoice();"/>
                                                </td>

                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Sender Id</td>
                                                <td><%-- <input type="text"> --%>
                                                    <%-- <input type="text" name="senderId" id="senderId" class="inputStyle" tabindex="2" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="senderId" id="senderId" value = "%{senderId}" tabindex="3"/>
                                                </td>
                                                <td class="lableLeft">Sender Name</td>
                                                <td><%-- <input type="text"> --%>
                                                    <%--  <input type="text" name="senderName" id="senderName" class="inputStyle" tabindex="2" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="senderName" id="senderName" value = "%{senderName}" tabindex="4"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Receiver ID</td>
                                                <td><%-- <input type="text"> --%>
                                                    <%--<input type="text" name="buId" id="buId" class="inputStyle" tabindex="2" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="receiverId" id="buId" value = "%{receiverId}" tabindex="5"/>
                                                </td>
                                                <td class="lableLeft">Receiver Name</td>
                                                <td><%-- <input type="text"> --%>
                                                    <%-- <input type="text" name="recName" id="recName" class="inputStyle" tabindex="2" /> --%>
                                                    <s:textfield cssClass="inputStyle" name="receiverName" id="recName" value = "%{receiverName}" tabindex="6"/>
                                                </td>
                                            </tr>


                                            <tr>
                                                <td class="lableLeft">Correlation </td>
                                                <td> 
                                                    <s:select headerKey="-1" headerValue="Select Attribute" list="correlationList" name="corrattribute" id="corrattribute" value="%{corrattribute}" tabindex="7" cssStyle="width : 150px"/>

                                                </td>
                                                <td class="lableLeft">Value </td>
                                                <td> 
                                                    <s:textfield cssClass="inputStyle" name="corrvalue" id="corrvalue" value="%{corrvalue}" tabindex="8"/>
                                                </td>

                                            </tr>
                                            <tr>
                                                <td class="lableLeft">Correlation </td>
                                                <td> 
                                                    <s:select headerKey="-1" headerValue="Select Attribute" list="correlationList" name="corrattribute1" id="corrattribute1" value="%{corrattribute1}" tabindex="7" cssStyle="width : 150px"/>

                                                </td>
                                                <td class="lableLeft">Value </td>
                                                <td> 
                                                    <s:textfield cssClass="inputStyle" name="corrvalue1" id="corrvalue1" value="%{corrvalue1}" tabindex="8"/>
                                                </td>

                                            </tr>
                                            <%--    <tr>
                                                   <td class="lableLeft">Correlation </td>
                                                   <td> 
                                                            <s:select headerKey="-1" headerValue="Select Attribute" list="correlationList" name="corrattribute2" id="corrattribute2" value="%{corrattribute2}" tabindex="7" cssStyle="width : 150px"/>
                                                        
                                                    </td>
                                                    <td class="lableLeft">Value </td>
                                                   <td> 
                                                        <s:textfield cssClass="inputStyle" name="corrvalue2" id="corrvalue2" value="%{corrvalue2}" tabindex="8"/>
                                                    </td>
                                                    
                                                </tr> --%>
                                            <%-- New search --%>

                                            <tr>
                                                <td class="lableLeft">Document Type</td>
                                                <td class="lableLeft">
                                                    <s:select headerKey="-1" headerValue="Select Type" list="docTypeList" name="docType" id="docType" value="%{docType}" tabindex="9" cssStyle="width : 150px"/>
                                                </td> 
                                                <td class="lableLeft">Status</td>
                                                <td class="lableLeft">
                                                    <s:select headerKey="-1" headerValue="Select Type" list="{'Success','Error','Warning'}" name="status" id="status" value="%{status}" tabindex="10" cssStyle="width : 150px"/> 
                                                </td>
                                                <%--   <td>  <s:select cssClass="inputStyle" headerKey="-1" headerValue="Select   Type" list="{'850', '860', '855', '856','810','820'}" name="docType" id="docType" tabindex="9" cssStyle="width : 150px"/></td>   --%>
                                            </tr>

                                            <%--<tr>
                                                    <td class="lableLeft">PO #
                                                    </td>
                                                    <td>
                                                       <s:textfield cssClass="inputStyle" name="poNum" id="poNum" tabindex="9"/>
                                                    </td>
                                            </tr>--%>
                                            <tr>
                                                <td style="background-color: white;">
                                                    <%--   <strong>  <s:submit value="Search" cssClass="button" onclick="return compareDates(document.getElementById('shpdatepickerfrom').value,document.getElementById('shpdatepicker').value)" tabindex="10"/></strong> --%>

                                                    <strong>  <s:submit value="Search" cssClass="button" onclick="return checkCorrelation();" tabindex="10"/></strong>
                                                    <%--         </td>
                                                             <td style="background-color: white;"> --%>
                                                    <%-- <s:reset value="Reset" cssClass="button" tabindex="11"/> --%>
                                                    <strong><input type="button" value="Reset" class="button" tabindex="11" onclick="return resetvalues();"/></strong>
                                                </td>
                                                <s:hidden name="sampleValue" id="sampleValue" value="2"/>
                                            </tr>
                                        </tbody>

                                    </table> 


                                </div>



                            </div>
                            <a><img src='../includes/images/dtp/cal_plus.gif' alt="nag"width="13" height="9" border="0" onclick="javascript:hideSearch()" id="fsCollImg"/></a>  
                        </div>

                        <s:if test="#session.ltShipmentList != null">
                            <div class="content" id="gridDiv" style="overflow-x:auto;">
                                <div class="content_item">
                                    <div class="grid_overflow" >
                                        <%!String cssValue = "whiteStripe";
                                    int resultsetTotal;%>
                                        <table align="left" id="results" width="690px"
                                               border="0" cellpadding="0" cellspacing="0" class="CSSTableGenerator">
                                            <%
                                                resultCount = 0;
                                                if (session.getAttribute("ltShipmentList") != null) {

                                                    ltShipmentList = (List) session.getAttribute("ltShipmentList");
                                                    //  out.println("searchResult size-->"+searchResult.size());
                                                    if (null != ltShipmentList && ltShipmentList.size() != 0) {
                                                        resultCount = ltShipmentList.size();
                                                    }
                                                }
                                            %>
                                            <%
                                                if (ltShipmentList.size() != 0) {
                                                    noOfPages = Integer.parseInt(session.getAttribute("noOfPages").toString());
                                            %>
                                            <%}%> 

                                            <input type="hidden" name="sec_lt_list" id="sec_lt_list" value="<%=ltShipmentList.size()%>"/>
                                          <thead>  <tr>
                                                <th>InstanceId</th>
                                                <th >Shipment #</th>
                                                <th >Customer/Carrier</th>  
                                                <th >Carrier Status</th>  
                                                <th>DateTime</th>
                                                <th>Direction</th>
                                                <th>Status</th>
                                                <th>Stop_Num</th>
                                                <th>RCT Order#</th>
                                              </tr></thead>
                                          <tbody>

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
                                                if (session.getAttribute("ltShipmentList") != null) {
                                            %>
                                            <input type="hidden" name="strIntStartGrid" id="strIntStartGrid" value="<%=strIntStartGrid%>"/> 
                                            <input type="hidden" name="strIntEndGrid" id="strIntEndGrid" value="<%=strIntEndGrid%>"/> 

                                            <%
                                                List ltShipmentList = (List) session.getAttribute("ltShipmentList");

                                                //resultCount = 0;
                                                if (null != ltShipmentList) {
                                                    resultCount = ltShipmentList.size();
                                                }

                                                for (int i = strIntStartGrid, j = 0; i < strIntEndGrid; i++, j++) {
                                                    LtShipmentBean ltShipmentBean = (LtShipmentBean) ltShipmentList.get(i);
                                                  //  logisticsLoadBean = (LogisticsLoadBean) list.get(i);
                                            %>
                                            <tr>
                                                <td>
                                                    <%
                                                        if (ltShipmentBean.getInstanceId() != null) {
                                                            out.println(ltShipmentBean.getInstanceId());
                                                        } else {
                                                            out.println("-");
                                                        }

                                                    %>
                                                </td>
                                                <%--<td>  <a href="javascript:getDetails('<%=ltShipmentBean.getAsnNum()%>','<%=ltShipmentBean.getPoNum()%>');">
                                                    <%
                                                    out.println(ltShipmentBean.getAsnNum());
                                                    %>
                                                   </a> 
                                                </td>--%>
                                                <td>  <a href="javascript:getDetails('<%=ltShipmentBean.getAsnNum()%>','<%=ltShipmentBean.getPoNum()%>','<%=ltShipmentBean.getId()%>');">
                                                        <%
                                                            if (ltShipmentBean.getAsnNum() != null) {
                                                                out.println(ltShipmentBean.getAsnNum());
                                                            } else {
                                                                out.println("MISSED");
                                                            }
                                                        %>
                                                    </a> 
                                                </td>

                                                <td>
                                                    <%
                                                        if (ltShipmentBean.getPartner() != null) {
                                                            out.println(ltShipmentBean.getPartner());
                                                        } else {
                                                            out.println("-");
                                                        }

                                                    %>
                                                </td>
                                                <td>
                                                    <%                                                            if (ltShipmentBean.getCarrierStatus() != null) {
                                                            if (ltShipmentBean.getCarrierStatus().equalsIgnoreCase("AA")) {
                                                                out.println(ltShipmentBean.getCarrierStatus() + "_pick_up appointment");
                                                            } else if (ltShipmentBean.getCarrierStatus().equalsIgnoreCase("AB")) {
                                                                out.println(ltShipmentBean.getCarrierStatus() + "_Delivery appointment");
                                                            } else if (ltShipmentBean.getCarrierStatus().equalsIgnoreCase("X3")) {
                                                                out.println(ltShipmentBean.getCarrierStatus() + "_Arrived at Pick_up Location");
                                                            } else if (ltShipmentBean.getCarrierStatus().equalsIgnoreCase("AF")) {
                                                                out.println(ltShipmentBean.getCarrierStatus() + "_Departed from pick_up Location");
                                                            } else if (ltShipmentBean.getCarrierStatus().equalsIgnoreCase("X1")) {
                                                                out.println(ltShipmentBean.getCarrierStatus() + "_Arrived at Delivery Location");
                                                            } else if (ltShipmentBean.getCarrierStatus().equalsIgnoreCase("D1")) {
                                                                out.println(ltShipmentBean.getCarrierStatus() + "_Completed Unloading Delivery Location");
                                                            } else if (ltShipmentBean.getCarrierStatus().equalsIgnoreCase("CD")) {
                                                                out.println(ltShipmentBean.getCarrierStatus() + "_Carrier Departed Delivery Location");
                                                            } else if (ltShipmentBean.getCarrierStatus().equalsIgnoreCase("X6")) {
                                                                out.println(ltShipmentBean.getCarrierStatus() + "_Enroute to Delivery Location");
                                                            } else if (ltShipmentBean.getCarrierStatus().equalsIgnoreCase("SD")) {
                                                                out.println(ltShipmentBean.getCarrierStatus() + "_Shipment Delay");
                                                            } else if (ltShipmentBean.getCarrierStatus().equalsIgnoreCase("CP")) {
                                                                out.println(ltShipmentBean.getCarrierStatus() + "_COMPLETED LOADING AT PICKUP");
                                                            } else {
                                                                out.println("-");
                                                            }
                                                        }
                                                    %>
                                                </td>


                                                <%-- <td>
                                                    <%
                                            out.println(shipmentBean.getShipmentDate());
                                            %>
                                                  <%
                                              if(logisticsDocBean.getStatus().equalsIgnoreCase("ERROR")){       
                                                         out.println("<font color='red'>"+logisticsDocBean.getStatus()+"</font>");
                                                     }else if(logisticsDocBean.getStatus().equalsIgnoreCase("SUCCESS")){
                                                         out.println("<font color='green'>"+logisticsDocBean.getStatus()+"</font>");
                                                     }else {
                                                          out.println("<font color='orange'>"+logisticsDocBean.getStatus()+"</font>");
                                                       }
                                                          if(ltShipmentBean.getCarrierStatus().equalsIgnoreCase("AA")){
                                            out.println("AA(Pick_up APPOINTMENT)");
                                                       }
                                            else if(ltShipmentBean.getCarrierStatus().equalsIgnoreCase("AB")){
                                                         out.println("<font color='green'>"+ltShipmentBean.getStatus()+"</font>");
                                                     } 
                                           %>
                                                </td>  --%>
                                                <td>
                                                    <%
                                                        //  out.println(shipmentBean.getDate_time_rec());
                                                        out.println(ltShipmentBean.getDateTime().toString().substring(0, ltShipmentBean.getDateTime().toString().lastIndexOf(":")));
                                                    %>
                                                </td>

                                                <%-- <td>
                                                     <%
                                             out.println(shipmentBean.getIsa());
                                             %>
                                                 </td>  --%>
                                                <%--  <td>
                                                      <%
                                              out.println(shipmentBean.getGsCtrl());
                                              %>
                                                  </td>  --%>
                                                <%--  <td>
                                                      <%
                                              out.println(shipmentBean.getStCtrl());
                                              %>
                                                  </td>  --%>
                                                <td>
                                                    <%
                                                        out.println(ltShipmentBean.getDirection().toUpperCase());
                                                    %>
                                                </td>
                                                <td>
                                                    <%
                                                        if (ltShipmentBean.getStatus() != null) {
                                                            if (ltShipmentBean.getStatus().equalsIgnoreCase("ERROR")) {
                                                                out.println("<font color='red'>" + ltShipmentBean.getStatus() + "</font>");
                                                                //out.println("<a href='javascript:errorOverlay("+ltShipmentBean.getFileId()+")'><font color='red'>" + ltShipmentBean.getStatus() + "</font></a>");
                                                            } else if (ltShipmentBean.getStatus().equalsIgnoreCase("SUCCESS")) {
                                                                out.println("<font color='green'>" + ltShipmentBean.getStatus().toUpperCase() + "</font>");
                                                            } else {
                                                                out.println("<font color='orange'>" + ltShipmentBean.getStatus().toUpperCase() + "</font>");
                                                            }
                                                        } else {
                                                            out.println("-");
                                                        }
                                                    %>
                                                </td>

                                                <td>
                                                    <%
                                                        if (ltShipmentBean.getStopNum() != null) {
                                                            out.println(ltShipmentBean.getStopNum());
                                                        } else {
                                                            out.println("-");
                                                        }
                                                    %>
                                                </td>
                                                <td>
                                                    <%
                                                        if (ltShipmentBean.getOrdernum() != null) {

                                                            out.println(ltShipmentBean.getOrdernum());

                                                        } else {
                                                            out.println("-");
                                                        }
                                                    %>
                                                </td>
                                            </tr>
                                            <%
                                                }
                                            %>
                                           </tbody> <tr>
                                                <td bgcolor="white" class="fieldLabelLeft" colspan="3" style="text-align: left; border: 0;">
                                                    <%if (strIntEndGrid != resultCount) {%>
                                                    Total Records : <%=resultCount%>&nbsp;Page <%=strIntEndGrid / 10%>  of <%=noOfPages%>
                                                    <%} else {%>
                                                    Total Records : <%=resultCount%>&nbsp;Page <%=noOfPages%>  of <%=noOfPages%>
                                                    <%}%>
                                                </td>
                                                <td colspan="7" align="right" bgcolor="white" style="text-align: right;border: 0;"><%    if (ltShipmentList.size() != 0) {%>
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

                                        <div id="resubmitLoading" align="center" style="display:none">
                                            <!--  <img  src="../includes/images/ajax-loader.gif" /> -->
                                            <font color="red">Loading...Please wait..</font>
                                        </div>

                                    </div>
                                    <%                                if (ltShipmentList.size() != 0) {
                                    %>
                                    <table align="right">
                                        <tr>
                                            <td style="background-color: white;">
                                                <strong><input type="button" value="Generate Excel" class="button" onclick="return gridDownloadforLtAsnRepo('ltShipment', 'xlsx');" onmouseover="Tip('Click here to generate an excel Report.')" onmouseout="UnTip()" id="excel"/></strong>
                                            </td>
                                        </tr>
                                    </table> 
                                    <%}%>
                                    <%-- process buttons end--%>
                                </div>


                            </s:if>
                        </s:form>

                    </div> 
                </div>
            </div>
            <footer> 
                <p><font color="white">&#169 <%= new java.text.SimpleDateFormat("yyyy").format(new java.util.Date())%> Miracle Software Systems, Inc. All rights reserved</font></p>
            </footer>
    </body>
</html>
