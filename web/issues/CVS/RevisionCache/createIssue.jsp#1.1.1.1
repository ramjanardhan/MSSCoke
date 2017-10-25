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
        src='<s:url value="/includes/js/issueValidations.js"/>'></script>
        
        <script language="JavaScript"
        src='<s:url value="/includes/js/common.js"/>'></script>
      <%--  <script language="JavaScript"
        src='<s:url value="/includes/js/dhtmlxcalendar.js"/>'></script>--%>
          <script language="JavaScript"
        src='<s:url value="/includes/js/dbgridDisplay.js"/>'></script>
          <script language="JavaScript">

var myCalendar;

     
  </script>
            <script language="JavaScript">
                
               
function resetvalues()
{

    
    $('#detail_box').hide();
    
  // showinit();
    //$('#gridDiv').hide();
   
}

function getIssueList(){
   // var tpid = document.getElementById("tpid").value;
  //  var tpname = document.getElementById("tpname").value;
  var searchType = document.getElementById('searchType').value;
    location.href = "../issues/searchIssue.action?searchType="+searchType;
     return true;
}
</script>

 
    </head>
    <body >
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
         <s:include value="/includes/template/orderToCashMenu.jsp"/>
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
  
   
      <%--  <s:textfield value="%{formAction}"/>--%>
      <div class="content" >
        <div class="content_item" id="searchdiv">
         <h3>Create New Issue</h3>     
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
                   
                    
                    <s:form action="%{formAction}" method="post" name="createIssueForm" id="createIssueForm" theme="simple" enctype="multipart/form-data" onsubmit="return clientValidate();">
                      
                       <s:hidden  name="group" id="group" value="%{group}" tabindex="5"  cssStyle="display:none;"/>
                       <s:hidden name = "id" id = "id" value = "%{id}"/>
                       <s:hidden name = "searchType" id = "searchType" value = "%{searchType}"/>
                        <tr>
                            <td class="lableLeft">Category <font color="red">*</font></td>
                            <td>
                                <s:select headerKey="-1" headerValue="Select Type" list="categoryMap" name="category" id="category" value="%{category}" tabindex="3" cssStyle="width : 150px"/>
                            </td>
                            
                            <td class="lableLeft">Priority <font color="red">*</font></td>
				<td>
                                <s:select headerKey="-1" headerValue="Select Type" list="priorityMap" name="priority" id="priority" value="%{priority}" tabindex="3" cssStyle="width : 150px"/>
                                   
				</td>
                            
			</tr>
                        
                         <tr>
                            <td class="lableLeft" rowspan="2">Assignment <font color="red">*</font></td>
                            <td rowspan="2">
                                <s:select headerKey="-1"  list="usersMap" name="assignment" id="assignment" value="%{selectUsers}" tabindex="3" multiple="true" cssStyle="width : 150px"/>
                                <%--<s:textfield cssClass="inputStyle" name="commId" id="commId" tabindex="1" value="%{commId}" onchange="return makeUpperCase(this);" maxLength="50"/> --%>
                            </td>
    <td class="lableLeft" >Status <font color="red">*</font></td>
                            <td >
                                <s:select headerKey="-1" headerValue="Select Type"  list="#@java.util.LinkedHashMap@{'Open':'Open','InProcess':'InProcess','Closed':'Closed'}" name="status" id="status" value="%{status}" tabindex="3"  cssStyle="width : 150px"/>
                                <%--<s:textfield cssClass="inputStyle" name="commId" id="commId" tabindex="1" value="%{commId}" onchange="return makeUpperCase(this);" maxLength="50"/> --%>
                            </td>
			</tr>
  
                          <tr>
                            <td class="lableLeft">Estimated Dev.Time</td>
				<td>
                                
                                    <s:textfield cssClass="inputStyle" name="time" id="time" value="%{time}" tabindex="8"  maxLength="5" cssStyle="width : 50px"/>&nbsp;Hrs
				</td>

			</tr>
                          <tr>
                            <td class="lableLeft">Summary <font color="red">*</font></td>
                           <td colspan="3">
                               <s:textfield cssClass="inputStyle" name="summary" id="summary" value="%{summary}" tabindex="5"  onblur="javascript:checkLength();" cssStyle="width : 615px"/>
                           </td>
                        </tr>
                        
                          <tr>
                              <td class="lableLeft" rowspan="2">Initial Description </td>
                              <td rowspan="2" colspan="3">
                                <s:textarea cssClass="inputStyle" rows="5" cols="75" name="desc" id="desc" onblur="javascript:checkDescLength();" value="%{desc}"  tabindex="7"  />
                            </td>
			</tr>
                        <tr></tr>
                          <% 
if(request.getAttribute("update")==null)                        
{
%>
                          <tr>
                              
                              
                               <td class="lableLeft">Files Category <font color="red">*</font></td>
				<td>
                                 <s:select headerKey="-1" headerValue="Select Type"  list="#@java.util.LinkedHashMap@{'Spec':'Spec','BP File':'BP File','Map File':'Map File'}" name="fcategory" id="fcategory" value="%{fcategory}" tabindex="3"  cssStyle="width : 150px"/>
                                </td>
                            <td class="lableLeft">Files <font color="red">*</font></td>
				<td>
                                 <s:file name="upload" label="File"/>
                                </td>
                         </tr>
                          <tr>
                            <td style="background-color: white;" colspan="3">

                               <s:submit   value="Submit" cssClass="button"  tabindex="28"/> 
                                <strong><input type="button" value="Reset" class="button" tabindex="30" onclick="return resetvalues();"/></strong>
                              </td>
                        </tr>
                         <% }else {%>
                        <tr>
                            <td style="background-color: white;" colspan="3">

                               <s:submit   value="Update" cssClass="button"  tabindex="28"/> 
                                <strong><input type="button" value="Cancel" class="button" tabindex="30" onclick="return getIssueList();"/></strong>
                              </td>
                        </tr>
                         
                         <% }%>
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
        
        <script>
             document.getElementById( 'group' ).value = document.getElementById( 'userFlowMap' ).value;
        </script>
    </body>
</html>

