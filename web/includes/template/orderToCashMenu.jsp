<%-- 
    Document   : orderToCashMenu
    Created on : Apr 25, 2013, 11:40:02 AM
    Author     : Santosh
--%>

<%@page import="com.mss.ediscv.util.AppConstants"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

 <li id="docRep"><a href="<s:url action="../doc/orderToCash"/>">Document Repository</a></li>
 <%
            if(session.getAttribute(AppConstants.SES_ROLE_ID).equals("100")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("101")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("104")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("105")){
          %>
         <li id="mainOm"><a href="#">Order Management<span class="sf-arrow"></span></a>
         <ul >
             <li id="omCurrId" class="notselected"><a href="<s:url action="../po/purchaseOrder"/>">Purchase Order</a></li>
            </ul>
          </li>
           <%}
            if(session.getAttribute(AppConstants.SES_ROLE_ID).equals("100")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("102")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("104")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("105")){
          %>
          <li id="mainShipment"><a href="#"> Supply Chain<span class="sf-arrow"></span></a>
              <ul style="width: 100px">
                  <li style="width: 118px" id="shipmentCurrId" class="notselected"><a href="<s:url action="../shipment/shipmentAction"/>">Shipments</a></li>
              </ul>  
          </li>
           <%}
            if(session.getAttribute(AppConstants.SES_ROLE_ID).equals("100")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("103")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("105")){
          %>
          <li id="mainFinance"><a href="#">Financials<span class="sf-arrow"></span></a>
              <ul style="width:100px">
                  <li style="width:98px" id="invCurrId" class="notselected"><a href="<s:url action="../inv/invoiceAction"/>">Invoices</a></li>
                   <li style="width:98px" id="paymentCurrId" class="notselected"><a href="<s:url action="../payment/paymentAction"/>">Payments</a></li>
          
              </ul> 
          </li>
          <%}%>
           <li id="reportsId"><a href="#">Reports<span class="sf-arrow"></span></a>
           <ul style="width:100px">
               <li style="width:170px" id="excelCurrId" class="notselected"><a href="<s:url action="../reports/getReports"/>">Excel&nbsp;Reports</a></li>
               <li style="width:170px" id="trackInOutId" class="notselected"><a href="<s:url action="../reports/getTrackInOut"/>">EDI&nbsp;Tracking&nbsp;IN/OUT</a></li>
               <li style="width:170px" id="trackSummary" class="notselected"><a href="<s:url action="../reports/getTrackSummary"/>">EDI&nbsp;Tracking&nbsp;Summary</a></li>
               <li style="width:170px" id="trackInquiry" class="notselected"><a href="<s:url action="../reports/getTrackInquiry"/>">EDI&nbsp;Tracking&nbsp;Inquiry</a></li>
               <li style="width:170px" id="dashboardCurrId" class="notselected"><a href="<s:url action="../reports/dashboard.action"/>">Dashboard</a></li>
           </ul> 
           
           </li>
         <%-- <li class="current"><a href="<s:url action="../tp/addTP"/>">Trading Partner</a></li> --%>
                  
          
         <s:include value="ServicesMenu.jsp"/>
              
             <%-- <li id="mainTs"><a href="#">Tracking System<span class="sf-arrow"></span></a>
        <ul >
            <li id="TsCurrId" class="notselected"><a href="<s:url action="../issues/createIssue"/>">Create Issue</a></li>
             <li id="TsCurrId" class="notselected"><a href="<s:url action="../issues/searchIssue?searchType=AllIssues"/>">Search Issue</a></li>
            
             
             <li id="myIssues" class="notselected"><a href="<s:url action="../issues/searchIssue?searchType=MyIssues"/>">Search My Issues</a></li>
             <li id="myTasks" class="notselected"><a href="<s:url action="../issues/searchTasks?searchType=MyTasks"/>">Search My Tasks</a></li>
             
           </ul>
         </li>  --%>
          

