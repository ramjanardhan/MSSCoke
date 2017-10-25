<%-- 
    Document   : docVisibilityMenu
    Created on : Jan 5, 2015, 11:55:02 AM
    Author     : miracle
--%>



<%@page import="com.mss.ediscv.util.AppConstants"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
 
               <%--    <%
                    //100,101,0,0,106,107
            if(!session.getAttribute(AppConstants.SES_ROLE_ID).equals("1")){
          %>  --%>
                <li id="ltDocRepository"><a href="<s:url action="../documentVisibility/DocumentVisibility.action"/>">DOC&nbsp;Repository</a></li>
                
        <%--            <%//}
                    //100,101,0,0,106,107
            if(session.getAttribute(AppConstants.SES_ROLE_ID).equals("100")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("101")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("106")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("107")){
          %>
                <li id="ltLoadTendering"><a href="<s:url action="../logisticsloadtendering/loadtendering.action"/>">LoadTendering</a></li>
         
                
               
               
               <%}
                 
            //L_SHIPMENT = 100,0,102,103,106,107
            if(session.getAttribute(AppConstants.SES_ROLE_ID).equals("100")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("102")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("103")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("106")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("107")){
                %>
                <li id="mainShipment"><a href="#">SupplyChain<span class="sf-arrow"></span></a>
                <ul style="width:95px">
                    <li id="logisticsShip" style="width:100px;" class="notselected"><a href="<s:url action="../logisticsshipment/ltshipment.action"/>">Shipment</a></li>
                </ul>
                </li>
                <%}  %>
                
                <%
                  //L_INVOICE = 100,0,0,103,106,107
                if(session.getAttribute(AppConstants.SES_ROLE_ID).equals("100")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("103")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("106")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("107")){
                %>
                 <li id="mainFinance"><a href="#">Finance<span class="sf-arrow"></span></a>
                <ul style="width:95px">
                <li id="logisticsinv" style="width:95px;" class="notselected"><a href="<s:url action="../logisticsinvoice/ltinvoice.action"/>">Invoice</a></li>
                </ul>
                </li>
                <%}%>  
                --%>
         
         <%--     <s:include value="ServicesMenu.jsp"/> --%>
      