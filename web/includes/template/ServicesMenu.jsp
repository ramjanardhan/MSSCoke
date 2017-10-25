<%-- 
    Document   : ServicesMenu
    Created on : May 10, 2013, 1:52:24 AM
    Author     : miracle
--%>

<%@page import="com.mss.ediscv.util.AppConstants"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

     <li id="mainTp"><a href="#">Config<span class="sf-arrow"></span></a>
              <ul style="width:95px">
           <%--  <%
           if(session.getAttribute(AppConstants.SES_ROLE_ID).equals("1")){
                %>
                   <li  style="width:128px; " id="createUser" class="notselected"><a href="<s:url action="../user/userAction"/>">Create&nbsp;User</a></li>
                   <li  style="width:128px; " id="resetPwd" class="notselected"><a href="<s:url action="../user/resetUserPwd"/>">Reset&nbsp;User&nbsp;Pwd</a></li>
                   <li style="width:128px" id="searchUser" class="notselected"><a href="<s:url action="../user/doSearchUser"/>" >User&nbsp;Search</a></li>
                  <%}%> --%>
           
                 <%
            if(session.getAttribute(AppConstants.SES_ROLE_ID).equals("100")|| session.getAttribute(AppConstants.SES_ROLE_ID).equals("101")|| session.getAttribute(AppConstants.SES_ROLE_ID).equals("102")|| session.getAttribute(AppConstants.SES_ROLE_ID).equals("103")){
          %>
             <li style="width:128px" id="partnerList" class="notselected"><a href="<s:url action="../partner/getPartnerList.action"/>" >Partner</a></li>
             <%--<li style="width:128px" id="routingList" class="notselected"><a href="<s:url action="../routing/getRoutingList.action"/>" >Routing</a></li>--%>
            <%-- <li style="width:128px" id="b2bChannelList" class="notselected"><a href="<s:url action="../b2bchannel/getB2BChannelList.action"/>" >B2BChannel</a></li>--%>
              <%-- <li style="width:128px" id="delChnInfo" class="notselected"><a href="<s:url action="../partner/getDelChannelInf.action"/>" >Delivery&nbsp;Channel</a></li> --%>
            <%--  <li style="width:128px" id="delChnInfo" class="notselected"><a href="<s:url action="../partner/deliveryChannelList.action"/>" >Delivery&nbsp;Channel</a></li> --%>
            <%--    <li style="width:128px" id="schdular" class="notselected"><a href="<s:url action="../partner/getSchedular.action"/>" >scheduler</a></li>--%>
             <%--    <li style="width:128px" id="addTp" class="notselected"><a href="<s:url action="../tradingPartner/addTradingPartner"/>" >Create&nbsp;TP</a></li>
                  <li style="width:128px" id="searchTp" class="notselected"><a href="<s:url action="../tradingPartner/doSearchTp"/>" >TP&nbsp;Search</a></li>
               <li style="width:128px" id="schdular" class="notselected"><a href="<s:url action="../partner/getSchedular.action"/>" >scheduler</a></li>   
                  <li style="width:128px" id="purge" class="notselected"><a href="<s:url action="../purge/purging"/>" >Purge</a></li>
                 --%>
                 <%}
            if(session.getAttribute(AppConstants.SES_ROLE_ID).equals("1")){
            %>
                 
                 
                 <li  style="width:128px; " id="createUser" class="notselected"><a href="<s:url action="../user/userAction"/>">Create&nbsp;User</a></li>
                   <li  style="width:128px; " id="resetPwd" class="notselected"><a href="<s:url action="../user/resetUserPwd"/>">Reset&nbsp;User&nbsp;Pwd</a></li>
                   <li style="width:128px" id="searchUser" class="notselected"><a href="<s:url action="../user/doSearchUser"/>" >User&nbsp;Search</a></li>
                   
                   <%}%>
                       <%
            if(!session.getAttribute(AppConstants.SES_ROLE_ID).equals("1")|| session.getAttribute(AppConstants.SES_ROLE_ID).equals("101")|| session.getAttribute(AppConstants.SES_ROLE_ID).equals("102")|| session.getAttribute(AppConstants.SES_ROLE_ID).equals("103")){
            %>
                 
                  <li  style="width:128px; " id="resetMyPwd" class="notselected"><a href="<s:url action="../user/resetMyPwd"/>">Reset&nbsp;MY&nbsp;Pwd</a></li>
                 <%}%>
              </ul> 
