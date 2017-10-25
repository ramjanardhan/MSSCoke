 <%-- 
    Document   : PurchaseOrder
    Created on : Mar 11, 2013, 10:59:42 AM
    Author     : Nagiredddy Seerapu
--%>

<%@page import="com.mss.ediscv.tradingPartner.TradingPartnerBean"%>
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
        <script language="JavaScript"
        src='<s:url value="/includes/js/dhtmlxcalendar.js"/>'></script>
          <script language="JavaScript"
        src='<s:url value="/includes/js/dbgridDisplay.js"/>'></script>
  
<script type="text/javascript"> 
   $(function() {
       $('#attach_box').click(function() {
           $('#sec_box').show();
           return false;
       });        
   });
    $(function() {
       $('#detail_link').click(function() {
           $('#detail_box').show();
           return false;
       });        
   });
   
   $('.check_link').click (function ()
{
var thisCheck = $(this);
if (thischeck.is (':checked'))
{
  $('#check_box').show();
}
});
      // New function to show the left grid

  


</script>

 
    </head>
    <body  onload="setStyle('mainTp','searchTp')">
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
          <h3>Trading Partner Info</h3>
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
         <h3>Trading Partner</h3>     
         <%
         if(request.getSession(false).getAttribute("responseString")!=null)
          {
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
                    <s:form action="../tradingPartner/tpSearch.action" method="post" name="tradingPartnerForm" id="tradingPartnerForm" theme="simple">
                     <tr>
                            <td class="lableLeft">Id  </td>
                            <td>
                               <%-- <input type="text" class="inputStyle" name="loginId" id="loginId" onkeypress="return handleEnter(this,event);" tabindex="1" /> --%>
                                <s:textfield cssClass="inputStyle" name="tpid" id="tpid" tabindex="1"/>
                            </td>
                            
                            <td class="lableLeft">Name </td>
				<td>
                                
                                    <s:textfield cssClass="inputStyle" name="tpname" id="tpname" tabindex="2" onchange="return makeUpperCase(this);"/>
				</td>
                            
			</tr>
                        
               
                       <tr >
                            <td style="background-color: white;" colspan="3">
                                <s:submit   value="Search" cssClass="button" tabindex="8"/>
                                
                            
                                  
                            </td>
                        </tr>
                        
                       

                </s:form>
                </tbody>
                    </table>
            </div>
              
        </div>
                <a><img src='../includes/images/dtp/cal_plus.gif' alt="nag"width="13" height="9" border="0" onclick="javascript:hideSearch()" id="fsCollImg"/></a>  
      </div>

               
                <s:if test="#session.tradingpartnerList != null"> 
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
             java.util.List list = (java.util.List) session.getAttribute(AppConstants.SES_TRADINGPARTNER_LIST);
             
            if(list.size()!=0){
             TradingPartnerBean tpBean;
             %>
             <tr>
                 <td >ID # </td>
                <td >Name </td>
              <%--   <td >TP INBOUND PATH</td>
                 <td >TP OUTBOUND PATH</td>  --%>
              <td>Contact Info</td>
              <td>Phone #</td>
               
               
              <td>Email </td>
              
            
            </tr>
            <tr >
             
             <%
            for (int i = 0; i < list.size(); i++) {
            tpBean = (TradingPartnerBean) list.get(i);

            if (i % 2 == 0) {
            cssValue = "whiteStripe";
            } else {
            cssValue = "grayEditSelection";
            }
           %>
           <td style="text-align: left"><%-- <a href="#"> --%>
                 <%
                    String id = tpBean.getId(); 
                 %>
                    <s:url var="myUrl" action="../tradingPartner/tpEdit.action">
                        <s:param name="id"><%=id%></s:param>
                        <s:param name="tpid" value="%{tpid}"></s:param>
                        <s:param name="tpname" value="%{tpname}"></s:param>
                        
                    </s:url>
    
                 <s:a href='%{#myUrl}' onmouseover="Tip('Click here to Edit Trading Partner.')" onmouseout="UnTip()"><%
                    out.println(id);
                    %></s:a>
                
                   <%--  </a> --%>
                </td>

                <td style="text-align: left">
                    <a href="#" onclick="getTpDetailInformation('<%=tpBean.getId()%>')" onmouseover="Tip('Click here to view Detail Info.')" onmouseout="UnTip()">
                    <%
            out.println(tpBean.getName());
            %></a>
                </td>
                 
                 <td style="text-align: left">
                    <%
            out.println(tpBean.getAddress());
            %>
                </td>
                 <td style="text-align: left">
                    <%
            out.println(tpBean.getPhno());
            %>
                </td>
                 <td style="text-align: left">
                    <%
            out.println(tpBean.getEmail());
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
            }
            else
            {
                 %>
                 <tr><td>
                 <%
                // String contextPath = request.getContextPath();
            out.println("<img  border='0' align='top'  src='"+contextPath+"/includes/images/alert.gif'/><b> No Records Found to Display!</b>");
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
      <%-- <div id="footer">
         <h2><font color="white">&#169 2013 Miracle Software Systems, Inc. All rights reserved</font></h2>
		</div> --%>
        </div>        
   
  </div>  
     <footer> 
         <p><font color="white">&#169 2013 Miracle Software Systems, Inc. All rights reserved</font></p>
	  </footer>
   
    </body>
</html>