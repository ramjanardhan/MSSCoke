<%-- 
    Document   : shipmentMenu
    Created on : Apr 16, 2013, 10:49:04 PM
    Author     : miracle
--%>
<%@page import="com.mss.ediscv.util.AppConstants"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<li><a href="<s:url action="../doc/orderToCash"/>">Document Repository</a></li>
<%
            if(session.getAttribute(AppConstants.SES_ROLE_ID).equals("101")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("100")){
          %>
         <li ><a href="#">Order Management<span class="sf-arrow"></span></a>
         <ul >
             <li><a href="<s:url action="../po/purchaseOrder"/>">Purchase Order</a></li>
            </ul>
          </li>
           <%}
            if(session.getAttribute(AppConstants.SES_ROLE_ID).equals("101")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("102")){
          %>
          <li class="current"><a href="#"> Supply Chains<span class="sf-arrow"></span></a>
              <ul style="width: 100px">
                  <li style="width: 118px" class="current"><a href="<s:url action="../shipment/shipmentAction"/>">Shipments</a></li>
              </ul>  
          </li>
          <%}
            if(session.getAttribute(AppConstants.SES_ROLE_ID).equals("101")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("103")){
          %>
          <li><a href="#">Financials<span class="sf-arrow"></span></a>
              <ul style="width:100px">
                  <li style="width:98px"><a href="<s:url action="../inv/invoiceAction"/>">Invoices</a></li>
                   <li style="width:98px"><a href="<s:url action="../payment/paymentAction"/>">Payments</a></li>
          
              </ul> 
          </li>
          <%}
            // out.println(session.getAttribute(AppConstants.SES_ROLE_ID));
             
             if(session.getAttribute(AppConstants.SES_ROLE_ID).equals("101")){
          %>
        <%-- <li><a href="<s:url action="../tp/addTP"/>">Trading Partner</a></li> --%>
         <li><a href="#">Trading Partner<span class="sf-arrow"></span></a>
              <ul style="width:100px">
                  <li style="width:128px"><a href="<s:url action="../tradingPartner/addTradingPartner"/>">Add&nbsp;TP</a></li>
                  <li style="width:128px"><a href="<s:url action="../tradingPartner/doSearchTp"/>">TP&nbsp;Search</a></li>
          
              </ul> 
          <%}%>