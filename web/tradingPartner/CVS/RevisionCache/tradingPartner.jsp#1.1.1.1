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
        <title>Miracle Supply Chain Visibility portal</title>
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
        
        
<%--          <script language="JavaScript">


     
  </script>
<script type="text/javascript"> 
  

</script>  --%>

 
    </head>
    <body onload="defaultHide();setStyle('mainTp','addTp');">
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
        <%if(session.getAttribute(AppConstants.SES_USER_DEFAULT_FLOWID).toString().equals("2")){%>
        <s:include value="/includes/template/orderToCashMenu.jsp"/>
        <%}else if(session.getAttribute(AppConstants.SES_USER_DEFAULT_FLOWID).toString().equals("3")){%>
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
    <s:hidden name="tppageId" value="%{tppageId}" id="tppageId"/>
   
      <%--  <s:textfield value="%{formAction}"/>--%>
      <div class="content" >
        <div class="content_item" id="searchdiv">
         <h3>Trading Partner</h3>     
         <%
         if(request.getAttribute(AppConstants.REQ_RESULT_MSG)!=null)
          {
                 String reqponseString = request.getAttribute(AppConstants.REQ_RESULT_MSG).toString();
                 //request.getSession(false).removeAttribute("responseString");
                 out.println(reqponseString);
          }
                 %>
		<div style="alignment-adjust:central;" >
                    <%String contextPath = request.getContextPath();
                    %>
               <table >
		<tbody >
                   <s:form action="%{formAction}" method="post" name="tradingPartnerForm" id="tradingPartnerForm" theme="simple">
                        <s:hidden name="tpid" value="%{tpid}" id="tpid"/>
                        <s:hidden name="tpname" value="%{tpname}" id="tpname"/>
                        <tr id="row1">
                            <td class="lableLeft">EDI&nbsp;Comm&nbsp;Id <font color="red">*</font></td>
                            <td>
                                <s:textfield cssClass="inputStyle" name="commId" id="commId" tabindex="1" value="%{commId}" onchange="fieldLengthValidator(this);makeUpperCase(this);"/>
                            </td>
                            
                            <td class="lableLeft">EDI&nbsp;Comm&nbsp;Name<font color="red">*</font></td>
				<td>
                                
                                    <s:textfield cssClass="inputStyle" name="commName" id="commName" tabindex="2" value="%{commName}" onchange="fieldLengthValidator(this);makeUpperCase(this);Onlycharectors(this);" />
				</td>
                        </tr>
                        <tr>
                            <td class="lableLeft">Trading&nbsp;Partner&nbsp;Name </td>
                            <td>
                                <s:textfield cssClass="inputStyle" name="tradingPartnerName" id="tradingPartnerName" tabindex="3" value="%{tradingPartnerName}"  onchange="fieldLengthValidator(this);makeUpperCase(this);Onlycharectors(this);"/>
                            </td>
                            
                            <td class="lableLeft">Contact&nbsp;Name</td>
				<td>
                                
                                    <s:textfield cssClass="inputStyle" name="contactName" id="contactName" tabindex="4" value="%{contactName}" onchange="fieldLengthValidator(this);makeUpperCase(this);Onlycharectors(this);"/>
				</td>
                            
			</tr>
                        
                      
                          <tr>
                            <td class="lableLeft">BVR&nbsp;UDI&nbsp;CommId </td>
                            <td>
                                <s:textfield cssClass="inputStyle" name="bvrUdiCommId" id="bvrUdiCommId" tabindex="5" value="%{bvrUdiCommId}" onchange="fieldLengthValidator(this);makeUpperCase(this);"/>
                            </td>
                            
                            <td class="lableLeft">Name</td>
				<td>
                                
                                    <s:textfield cssClass="inputStyle" name="bvrUdiName" id="bvrUdiName" tabindex="6" value="%{bvrUdiName}" onchange="fieldLengthValidator(this);makeUpperCase(this);Onlycharectors(this);"/>
				</td>
                            
			</tr>
                
                           <tr id="row2">
                            <td class="lableLeft">Phone&nbsp;Number </td>
                            <td>
                                <s:textfield cssClass="inputStyle" name="phno" id="phno" value="%{phno}" tabindex="7" onchange="generalFormatPhone(this);fieldLengthValidator(this);"/>
                            </td>
                            
                            <td class="lableLeft">Email</td>
				<td>
                                
                                    <s:textfield cssClass="inputStyle" name="email" id="email" value="%{email}" tabindex="8" onchange="fieldLengthValidator(this);allSmalls(this);" onblur="validateEmail(this);"/>
				</td>
                            
			</tr>
                          <tr id="row3">
                            <td class="lableLeft">Address </td>
                            <td>
                                <s:textfield cssClass="inputStyle" name="address" id="address" value="%{address}" tabindex="9" onchange="fieldLengthValidator(this);"/>
                            </td>
                            
                            <td class="lableLeft">City</td>
				<td>
                                
                                    <s:textfield cssClass="inputStyle" name="city" id="city" value="%{city}" tabindex="10" onchange="fieldLengthValidator(this);makeUpperCase(this);Onlycharectors(this);"/>
				</td>
                            
			</tr>
                          <tr id="row4">
                            <td class="lableLeft">State </td>
                            <td>
                             
                                <s:select headerKey="-1" headerValue="Select State" list="statesMap" name="state" id="state" value="%{state}" tabindex="11" cssStyle="width : 150px"/>
                            </td>
                            
                            <td class="lableLeft">Zip</td>
				<td>
                                
                                    <s:textfield cssClass="inputStyle" name="zip" id="zip" value="%{zip}" tabindex="12" onchange="fieldLengthValidator(this);"/>
				</td>
                            
			</tr>
                       
                                 <tr>
                            <td class="lableLeft">Network </td>
                            <td>
                              
                                <s:select headerKey="-1" headerValue="Select Network" list="#@java.util.LinkedHashMap@{'6X5':'6X5','SP5':'SP5','INVOICE':'INVOICE','AS2':'AS2'}" name="network" id="network" value="%{network}" tabindex="13" cssStyle="width : 150px"/>
                            </td>
                            
                            <td class="lableLeft">URL</td>
				<td>
                                
                                    <s:textfield cssClass="inputStyle" name="url" id="url" value="%{url}" tabindex="14" onchange="fieldLengthValidator(this);allSmalls(this);"/>
				</td>
                            
			</tr>
                        <tr>
                             <td class="lableLeft">PO&nbsp;Type</td>
                             <td class="lableLeft">
                                 Basic&nbsp;: <s:checkbox name="basic" id="basic" fieldValue="true" label="BASIC" tabindex="15"/>
                                  SOQ&nbsp;: <s:checkbox name="soq" id ="soq" fieldValue="true" label="SOQ" tabindex="16"/>  
				</td>
                             
                     <%--   </tr>
                        <tr >--%>
                            
                             <td class="lableLeft">Invoice&nbsp;Type</td>
                             <td class="lableLeft">
                                Store&nbsp;: <s:checkbox name="store" fieldValue="true" label="STORE" id="store" tabindex="17"/>
                                  Master&nbsp;: <s:checkbox name="master" id="master" fieldValue="true" label="MASTER" tabindex="18"/>  
				</td>
                                
                             
                        </tr>
                        <tr >
                            <td class="lableLeft">Developing&nbsp;: </td><td class="lableLeft">
                            <%--     <s:checkbox name="developing" fieldValue="true" label="DEVELOPING"/>   --%>
                            <s:radio name="developing" id= "developing" list="#@java.util.LinkedHashMap@{'Y':'Yes','N':'No'}" value="%{developing}" tabindex="19"/>
                            </td>
                             <td class="lableLeft">Vendor&nbsp;#</td>
				<td>
                                
                                    <s:textfield cssClass="inputStyle" name="vendorNo" id="vendorNo" value="%{vendorNo}" tabindex="20"  onchange="fieldLengthValidator(this);makeUpperCase(this);"/>
				</td>
                            
                            
                        </tr>
                           <tr >
                               <td class="lableLeft">Order&nbsp;DUNS </td>
                            <td>
                                <s:textfield cssClass="inputStyle" name="orderDuns" id="orderDuns" value="%{orderDuns}" tabindex="21" onchange="fieldLengthValidator(this);makeUpperCase(this);"/>
                            </td>
                             <td class="lableLeft">Ship&nbsp;DUNS</td>
				<td>
                                
                                    <s:textfield cssClass="inputStyle" name="shipDuns" id="shipDuns" value="%{shipDuns}" tabindex="22" onchange="fieldLengthValidator(this);makeUpperCase(this);"/>
				</td>
                             
			</tr>
                        <tr>
                                <td class="lableLeft">Pay&nbsp;DUNS </td>
                            <td>
                                <s:textfield cssClass="inputStyle" name="payDuns" id="payDuns" value="%{payDuns}"  tabindex="23" onchange="fieldLengthValidator(this);makeUpperCase(this);"/>
                            </td>
                                <td class="lableLeft">Department&nbsp;# </td>
                            <td>
                                <s:textfield cssClass="inputStyle" name="deptNo" id="deptNo" value="%{deptNo}" tabindex="24" onchange="fieldLengthValidator(this);makeUpperCase(this);"/>
                            </td>
                        </tr>
                        
                        <tr>
                            <td class="lableLeft" colspan="4">Buyer&nbsp;:</td>
                        </tr>
                        <tr>
                               <td class="lableLeft">Name </td>
                            <td>
                                <s:textfield cssClass="inputStyle" name="buyerName" id="buyerName" value="%{buyerName}" tabindex="25" onchange="fieldLengthValidator(this);makeUpperCase(this);Onlycharectors(this);"/>
                            </td>
                              <td class="lableLeft">Phone </td>
                            <td>
                                <s:textfield cssClass="inputStyle" name="buyerPhone" id="buyerPhone" onchange="generalFormatPhone(this);fieldLengthValidator(this);" value="%{buyerPhone}" tabindex="26" />
                            </td>
                        </tr>
                        <tr>
                              <td class="lableLeft">Email </td>
                              <td colspan="3">
                                <s:textfield cssClass="inputStyle" name="buyerEmail" onblur="validateEmail(this);allSmalls(this);" id="buyerEmail" value="%{buyerEmail}" tabindex="27" onchange="fieldLengthValidator(this);"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="lableLeft" colspan="4">Customer&nbsp;Service:</td>
                        </tr>
                        <tr>
                               <td class="lableLeft">Name </td>
                            <td>
                                <s:textfield cssClass="inputStyle" name="csName" id="csName" value="%{csName}" tabindex="28" onchange="fieldLengthValidator(this);makeUpperCase(this);Onlycharectors(this);"/>
                            </td>
                              <td class="lableLeft">Phone </td>
                            <td>
                                <s:textfield cssClass="inputStyle" name="csPhone" id="csPhone" value="%{csPhone}" onchange="generalFormatPhone(this);fieldLengthValidator(this);" tabindex="29" />
                            </td>
                        </tr>
                        <tr>
                              <td class="lableLeft">Email </td>
                              <td colspan="3">
                                <s:textfield cssClass="inputStyle" name="csEmail" id="csEmail" onblur="validateEmail(this);allSmalls(this);" value="%{csEmail}" tabindex="30" onchange="fieldLengthValidator(this);"/>
                            </td>
                        </tr>
                       <tr>
                            <td class="lableLeft">Notes </td>
                            <td colspan="3">
                                <s:textarea cssClass="inputStyle" name="notes" id="notes" value="%{notes}" tabindex="31" cols="51" rows="3" onchange="return noteValidate(document.tradingPartnerForm.notes.value);"/>
                            </td>
                       </tr>  
                     
                       
                       <tr>
                            <td style="background-color: white;" colspan="3">
                               
                                <%
                                // out.println("pageId-->"+request.getAttribute("tppageId").toString());
                                if(request.getAttribute("tppageId").toString().equals("0"))         {                       
                                %>
                                   <s:submit   value="Finish" cssClass="button" onclick="return checkTpValues();" tabindex="32"/> 
                                    <strong><input type="button" value="Reset" class="button" tabindex="33" onclick="return resetvalues();"/></strong>
                                <%}else{%>
                                
                                    <s:submit value="Update" cssClass="button" onclick="return checkTpValues();" tabindex="32"/> 
                                    <strong><input type="button" value="BackToList" class="button" onclick="return getTpList();" tabindex="33"/></strong>
                                <%}%>
                             
                            </td>
                        </tr>
              
                       

                </s:form>
                </tbody>
                    </table>
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
         <p><font color="white">&#169 2013 Miracle Software Systems, Inc. All rights reserved</font></p>
	  </footer>
    </body>
</html>

