<%-- 
    Document   : logisticsMenu
    Created on : May 8, 2013, 12:16:42 PM
    Author     : miracle
--%>


<%@page import="com.mss.ediscv.util.AppConstants"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


           
         <%-- <li class="current"><a href="<s:url action="../tp/addTP"/>">Trading Partner</a></li> --%>
                  
          
         <%-- <li id="logisticsMain"><a href="<s:url action="../logistics/Logistics.action"/>">Logistics</a> --%>
                   <%
                    //100,101,0,0,106,107
            if(session.getAttribute(AppConstants.SES_ROLE_ID).equals("1")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("101")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("100")){
               // System.out.println("roleId"+session.getAttribute(AppConstants.SES_ROLE_ID));
          %>
          <li id="ltDocRepository" style="margin-left: -72px"><a  href="<s:url action="../logisticsdoc/Logistics.action"/>">DOC&nbsp;Repository</a></li>
                
                    <%}
                    //100,101,0,0,106,107
            if(session.getAttribute(AppConstants.SES_ROLE_ID).equals("100")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("101")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("106")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("107")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("102")){
          %>
                <li id="ltLoadTendering"><a href="<s:url action="../logisticsloadtendering/loadtendering.action"/>">LoadTendering</a></li>
                <%}
            //100,0,102,0,106,107
            if(session.getAttribute(AppConstants.SES_ROLE_ID).equals("100")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("102")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("106")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("107")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("101")){
          %>
                <li  id="ltResponseMain"><a href="<s:url action="../ltResponse/ltResponse.action"/>">Load Tender Response</a></li>
                <%}
            //L_SHIPMENT = 100,0,102,103,106,107
            if(session.getAttribute(AppConstants.SES_ROLE_ID).equals("100")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("102")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("106")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("107")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("101")){
                %>
             <li id="logisticsShip"><a  href="<s:url action="../logisticsshipment/ltshipment.action"/>">Shipment Status</a></li>   
                <%--<li id="mainShipment"><a href="#">Supply Chain<span class="sf-arrow"></span></a>
                <ul style="width:95px">
                    <li id="logisticsShip" style="width:100px;" class="notselected"><a href="<s:url action="../logisticsshipment/ltshipment.action"/>">Shipment</a></li>
                </ul>
                </li>
                --%>
                <%}
            //L_INVOICE = 100,0,0,103,106,107
            if(session.getAttribute(AppConstants.SES_ROLE_ID).equals("100")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("103")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("106")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("107")||session.getAttribute(AppConstants.SES_ROLE_ID).equals("101")){
            %>
            <li id="logisticsinv"><a  href="../logisticsinvoice/ltinvoice.action"/>Invoice</a></li>      
            <%-- <li id="mainFinance"><a href="#">Finance<span class="sf-arrow"></span></a>
                <ul style="width:95px">
                <li id="logisticsinv" style="width:95px;" class="notselected"><a href="<s:url action="../logisticsinvoice/ltinvoice.action"/>">Invoice</a></li>
                </ul>
                </li>
            --%>
                <%}%>
                
            <li id="logisticreportsId"><a href="#">LogisticReports<span class="sf-arrow"></span></a>
           <ul style="width:100px">
               <li style="width:170px" id="logisticCurrId" class="notselected"><a href="<s:url action="../logisticsReports/getLogisticReports"/>">Excel&nbsp;Reports</a></li>
               <%-- <li style="width:170px" id="logistictrackInOutId" class="notselected"><a href="<s:url action="../logisticsReports/getTrackInOut"/>">EDI&nbsp;Tracking&nbsp;IN/OUT</a></li>--%>
               <%--<li style="width:170px" id="logistictrackSummary" class="notselected"><a href="<s:url action="../logisticsReports/getTrackSummary"/>">EDI&nbsp;Tracking&nbsp;Summary</a></li>
               <li style="width:170px" id="logistictrackInquiry" class="notselected"><a href="<s:url action="../logisticsReports/getTrackInquiry"/>">EDI&nbsp;Tracking&nbsp;Inquiry</a></li> --%>
               <li style="width:170px" id="logisticdashboardCurrId" class="notselected"><a href="<s:url action="../logisticsReports/dashboard"/>">Dashboard</a></li>
           </ul>  
              <s:include value="ServicesMenu.jsp"/>
            <%--   <ul style="width:95px">
            <%
           if(session.getAttribute(AppConstants.SES_ROLE_ID).equals("1")){
                %>
                   <li  style="width:128px; " id="createUser" class="notselected"><a href="<s:url action="../user/userAction"/>">Create&nbsp;User</a></li>
                   <li  style="width:128px; " id="resetPwd" class="notselected"><a href="<s:url action="../user/resetUserPwd"/>">Reset&nbsp;User&nbsp;Pwd</a></li>
                   <li style="width:128px" id="searchUser" class="notselected"><a href="<s:url action="../user/doSearchUser"/>" >User&nbsp;Search</a></li>
             
          <li  style="width:128px; " id="resetMyPwd" class="notselected"><a href="<s:url action="../user/resetMyPwd"/>">Reset&nbsp;MY&nbsp;Pwd</a></li>
                 
         
              </ul> --%>