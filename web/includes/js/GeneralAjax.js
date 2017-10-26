/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function getXMLHttpRequest() {
  var xmlHttpReq = false;
  // to create XMLHttpRequest object in non-Microsoft browsers
  if (window.XMLHttpRequest) {
    xmlHttpReq = new XMLHttpRequest();
  } else if (window.ActiveXObject) {
    try {
      // to create XMLHttpRequest object in later versions
      // of Internet Explorer
      xmlHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (exp1) {
      try {
        // to create XMLHttpRequest object in older versions
        // of Internet Explorer
        xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
      } catch (exp2) {
        xmlHttpReq = false;
      }
    }
  }
  return xmlHttpReq;
}

/**
 * TP method
 */
function readyStateHandlerText1(req,responseTextHandler){
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
             //   (document.getElementById("loadingImage")).style.display = "none";
                responseTextHandler(req.responseXML);
            } else {
                alert("HTTP error"+req.status+" : "+req.statusText);
            }
        }
        else {
          
           // (document.getElementById("loadingImage")).style.display = "block";
        }
    }
}



 function readyStateHandlerText(req,responseTextHandler){
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("loadingImage")).style.display = "none";
                responseTextHandler(req.responseXML);
            } else {
                alert("HTTP error"+req.status+" : "+req.statusText);
            }
        }
        else {
          
            (document.getElementById("loadingImage")).style.display = "block";
        }
    }
}  



 function readyStateHandlerLoadText(req,responseTextHandler){
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("loadingImage")).style.display = "none";
                responseTextHandler(req.responseText);
            } else {
                alert("HTTP error"+req.status+" : "+req.statusText);
            }
        }
        else {
          
            (document.getElementById("loadingImage")).style.display = "block";
        }
    }
}  

function getPoDetails(number,number1){
    var num=number;
    var num1=number1; 
    $(function() {
       
           $('#detail_box').show();
           return false;
               
   });
  var req = getXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populatePoDetails); 
    var url="../ajax/getPoDetails.action?poNumber="+num+"&poInst="+num1;
    req.open("POST",url,"true");
   // req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populatePoDetails(responseXML)
{
   // alert("responseXML--->"+responseXML);
     var details = responseXML.getElementsByTagName("DETAILS")[0];
     
//   alert("datails--->"+details);
     var detail = details.childNodes[0];
    var chk=detail.getElementsByTagName("VALID")[0];  
    
     if(chk.childNodes[0].nodeValue =="true") {
    
            var detail = details.childNodes[0];
            var fileID = detail.getElementsByTagName("FILEID")[0].childNodes[0].nodeValue; 
            var poNUM = detail.getElementsByTagName("PONUM")[0].childNodes[0].nodeValue; 
            var poDATE = detail.getElementsByTagName("PODATE")[0].childNodes[0].nodeValue; 
            var poValue = detail.getElementsByTagName("POVALUE")[0].childNodes[0].nodeValue;
            //new adding
            var SHIP_DATE = detail.getElementsByTagName("SHIP_DATE")[0].childNodes[0].nodeValue; 
           // var ISA_CONTROL_NUMBER = detail.getElementsByTagName("ISA_CONTROL_NUMBER")[0].childNodes[0].nodeValue; 
            var ROUTINGS = detail.getElementsByTagName("ROUTINGS")[0].childNodes[0].nodeValue; 
            var INVOICED_AMOUNT = detail.getElementsByTagName("INVOICED_AMOUNT")[0].childNodes[0].nodeValue;
             var PAYMENT_RECEIVED = detail.getElementsByTagName("PAYMENT_RECEIVED")[0].childNodes[0].nodeValue; 
            var SHIP_ADDRESS_ID = detail.getElementsByTagName("SHIP_ADDRESS_ID")[0].childNodes[0].nodeValue; 
            var BILL_ADDRESS_ID = detail.getElementsByTagName("BILL_ADDRESS_ID")[0].childNodes[0].nodeValue; 
           
            var sapIdocNum = detail.getElementsByTagName("SAPIDOCNUM")[0].childNodes[0].nodeValue;
            var SENDER_ID = detail.getElementsByTagName("SENDER_ID")[0].childNodes[0].nodeValue;
            var RECEIVER_ID = detail.getElementsByTagName("RECEIVER_ID")[0].childNodes[0].nodeValue; 
            
            var SENDER_NAME = detail.getElementsByTagName("SENDER_NAME")[0].childNodes[0].nodeValue;
            var RECEIVER_NAME = detail.getElementsByTagName("RECEIVER_NAME")[0].childNodes[0].nodeValue;
            
            var deilvaryName = detail.getElementsByTagName("DELSTATUS")[0].childNodes[0].nodeValue;   
            var itemQty = detail.getElementsByTagName("ITEMQTY")[0].childNodes[0].nodeValue;
            var sonumber = detail.getElementsByTagName("SO_NUMBER")[0].childNodes[0].nodeValue;
            var PRE_TRANS_FILEPATH = detail.getElementsByTagName("PRETRANSFILEPATH")[0].childNodes[0].nodeValue;
            var POST_TRANS_FILEPATH = detail.getElementsByTagName("POSTTRANSFILEPATH")[0].childNodes[0].nodeValue; 
            var ORGFILEPATH = detail.getElementsByTagName("ORGFILEPATH")[0].childNodes[0].nodeValue;
            var ORDER_STATUS = detail.getElementsByTagName("ORDER_STATUS")[0].childNodes[0].nodeValue; 
            var ERRMESSAGE = detail.getElementsByTagName("ERR_MESSAGE")[0].childNodes[0].nodeValue;
            var ACKFILEID = detail.getElementsByTagName("ACKFILEID")[0].childNodes[0].nodeValue;
            
            var ISA_NUMBER = detail.getElementsByTagName("ISA_NUMBER")[0].childNodes[0].nodeValue;
            var GS_CONTROL_NUMBER = detail.getElementsByTagName("GS_CONTROL_NUMBER")[0].childNodes[0].nodeValue;
            var ST_CONTROL_NUMBER = detail.getElementsByTagName("ST_CONTROL_NUMBER")[0].childNodes[0].nodeValue;
            var ISA_DATE = detail.getElementsByTagName("ISA_DATE")[0].childNodes[0].nodeValue;
            var ISA_TIME = detail.getElementsByTagName("ISA_TIME")[0].childNodes[0].nodeValue;
             
             var TRANSACTION_TYPE = detail.getElementsByTagName("TRANSACTION_TYPE")[0].childNodes[0].nodeValue;
             var STATUS = detail.getElementsByTagName("STATUS")[0].childNodes[0].nodeValue; 
             var FILE_TYPE = detail.getElementsByTagName("FILE_TYPE")[0].childNodes[0].nodeValue; 
             
             		   
             // Sap Detals
               
                var SAP_DETAILS = detail.getElementsByTagName("SAP_DETAILS")[0].childNodes[0].nodeValue;
                var SAP_USER ;
                var IDOC_NUMBER ;
                var PO_NUMBER ;
                var PO_DATE ;
                var IDOC_STATUS_CODE ;
                var IDOC_STATUS_DESCRIPTION ;
                if(SAP_DETAILS!='NO'){
                    
                        SAP_USER = detail.getElementsByTagName("SAP_USER")[0].childNodes[0].nodeValue;
               IDOC_NUMBER = detail.getElementsByTagName("IDOC_NUMBER")[0].childNodes[0].nodeValue;
                PO_NUMBER = detail.getElementsByTagName("PO_NUMBER")[0].childNodes[0].nodeValue;
                 PO_DATE = detail.getElementsByTagName("PO_DATE")[0].childNodes[0].nodeValue;
                  IDOC_STATUS_CODE = detail.getElementsByTagName("IDOC_STATUS_CODE")[0].childNodes[0].nodeValue;
                   IDOC_STATUS_DESCRIPTION = detail.getElementsByTagName("IDOC_STATUS_DESCRIPTION")[0].childNodes[0].nodeValue;
			
                }
          	  
             
             //alert("ACKFILEID-->"+ACKFILEID);
           // var routings = detail.getElementsByTagName("ROUTINGS")[0].childNodes[0].nodeValue;  
           
           var details="<table style='margin: 0 0 0 0;padding: 0px 0px;'>"+
                "<tr><td class='ajaxTd'>Instance Id :</td><td class='ajaxTd'>"+fileID+"</td></tr>";
                  details = details +"<tr><td class='ajaxTd'>PO # :</td><td class='ajaxTd'>"+poNUM+"</td></tr>";
                  if(poDATE != "NO"){
                  details = details +"<tr><td class='ajaxTd'>Order_Date :</td><td class='ajaxTd'>"+poDATE+"</td></tr>";
                  }
                  if(poValue != "NO"){
                  details = details +"<tr><td class='ajaxTd'>PO Value :</td><td class='ajaxTd'>"+poValue+"</td></tr>";
                  }
                  if(ORDER_STATUS != "NO"){
                  details = details +"<tr><td class='ajaxTd'>ORDER_STATUS :</td><td class='ajaxTd'>"+ORDER_STATUS+"</td></tr>";
                  }
                  if(SHIP_DATE != "NO"){
                details = details + "<tr><td class='ajaxTd'>SHIP_DATE :</td><td class='ajaxTd'>"+SHIP_DATE+"</td></tr>";
                  }           
                  if(ROUTINGS != "NO"){
                   details = details +  "<tr><td class='ajaxTd'>ROUTINGS :</td><td class='ajaxTd'>"+ROUTINGS+"</td></tr>";
                  }
                  if(INVOICED_AMOUNT != "NO"){
                  details = details +   "<tr><td class='ajaxTd'>INVOICED_AMOUNT :</td><td class='ajaxTd'>"+INVOICED_AMOUNT+"</td></tr>";
                  }
                  if(PAYMENT_RECEIVED != "NO"){
               details = details + "<tr><td class='ajaxTd'>PAYMENT_RECEIVED:</td><td class='ajaxTd'>"+PAYMENT_RECEIVED+"</td></tr>";
                  }
                  if(SHIP_ADDRESS_ID != "NO"){
                  details = details +   "<tr><td class='ajaxTd'>SHIP_ADDRESS_ID :</td><td class='ajaxTd'>"+SHIP_ADDRESS_ID+"</td></tr>";
                  }
                  if(BILL_ADDRESS_ID != "NO"){
                  details = details +   "<tr><td class='ajaxTd'>BILL_ADDRESS_ID:</td><td class='ajaxTd'>"+BILL_ADDRESS_ID+"</td></tr>";
                  }
                  
	            if(sonumber != "NO"){
                    details = details + "<tr><td class='ajaxTd'>SO&nbsp;# :</td><td class='ajaxTd'>"+sonumber+"</td></tr>";
					}
		    if(sapIdocNum != "NO"){
                    details = details + "<tr><td class='ajaxTd'>SAPIDOC&nbsp;# :</td><td class='ajaxTd'>"+sapIdocNum+"</td></tr>";
					}
                     if(deilvaryName != "NO"){                   
                    details = details + "<tr><td class='ajaxTd'>IDOC Status :</td><td class='ajaxTd'>"+deilvaryName+"</td></tr>";
                     }  
                     details = details + "<tr><td class='ajaxTd'>PO&nbsp;Quantity&nbsp;:</td><td class='ajaxTd'>"+itemQty+"</td></tr>"+
                         "<tr><td class='ajaxTd'>Document&nbsp;Type&nbsp;:</td><td class='ajaxTd'>"+FILE_TYPE+"</td></tr>"+
                         "<tr><td class='ajaxTd'>Transaction&nbspType&nbsp;:</td><td class='ajaxTd'>"+TRANSACTION_TYPE+"</td></tr>";
                    /*details = details + "<tr><td class='ajaxTd'>Sender ID :</td><td class='ajaxTd'>"+SENDER_ID+"</td></tr>"+
                    "<tr><td class='ajaxTd'>Receiver&nbsp;Id&nbsp;:</td><td class='ajaxTd'>"+RECEIVER_ID+"</td></tr>"+*/
                    details = details + "<tr><td class='ajaxTd'><b>Sender Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                   details = details + "<tr><td class='ajaxTd'> Id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+SENDER_ID+"</td></tr>";
                   details = details + "<tr><td class='ajaxTd'> Name&nbsp;:&nbsp;</td><td class='ajaxTd'>"+SENDER_NAME+"</td></tr>";

                   details = details + "<tr><td class='ajaxTd'><b>Receiver Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                   details = details + "<tr><td class='ajaxTd'>Id&nbsp; :&nbsp;</td><td class='ajaxTd'>"+RECEIVER_ID+"</td></tr>";
                   details = details + "<tr><td class='ajaxTd'> Name&nbsp;: &nbsp;</td><td class='ajaxTd'>"+RECEIVER_NAME+"</td></tr>";

                    details = details + "<tr><td class='ajaxTd'>ISA #:</td><td class='ajaxTd'>"+ISA_NUMBER+"</td></tr>";
                    
                    details = details + "<tr><td class='ajaxTd'>GS #:</td><td class='ajaxTd'>"+GS_CONTROL_NUMBER+"</td></tr>";
                    details = details + "<tr><td class='ajaxTd'>ST #:</td><td class='ajaxTd'>"+ST_CONTROL_NUMBER+"</td></tr>";
                    details = details + "<tr><td class='ajaxTd'>ISA Date:</td><td class='ajaxTd'>"+ISA_DATE+"</td></tr>";
                    details = details + "<tr><td class='ajaxTd'>ISA Time:</td><td class='ajaxTd'>"+ISA_TIME+"</td></tr>";
            
            if(STATUS.toUpperCase() == "ERROR"){
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp;</td><td class='ajaxTd'><font color='red'>"+STATUS.toUpperCase()+"</font></td></tr>";
            }else if(STATUS.toUpperCase() == "SUCCESS"){
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp; </td><td class='ajaxTd'><font color='green'>"+STATUS.toUpperCase()+"</font></td></tr>";
            }else {
               details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp; </td><td class='ajaxTd'><font color='orange'>"+STATUS.toUpperCase()+"</font></td></tr>"; 
            }
            
            if(PRE_TRANS_FILEPATH == "No File"){
               details = details + "<tr><td class='ajaxTd'>Pre-Translation&nbsp;:</td><td class='ajaxTd'> -- </tr></td>";
            }else{
               details = details + "<tr><td class='ajaxTd'>Pre-Translation&nbsp;:</td><td class='ajaxTd'> <a href=\"../download/getAttachment.action?locationAvailable="+PRE_TRANS_FILEPATH+"\">Download</a></td></tr>";
            }
            
            if(POST_TRANS_FILEPATH == "No File"){
                details = details + "<tr><td class='ajaxTd'>Post-Translation&nbsp;:</td><td class='ajaxTd'> -- </tr></td>";
            }else{
                details = details + "<tr><td class='ajaxTd'>Post-Translation&nbsp;:</td><td class='ajaxTd'> <a href=\"../download/getAttachment.action?locationAvailable="+POST_TRANS_FILEPATH+"\">Download</a></td></tr>";
            }
            
            if(ACKFILEID == "No File"){
                details = details + "<tr><td class='ajaxTd'>997&nbsp;Ack&nbsp;File&nbsp;:</td><td class='ajaxTd'> -- </tr></td>";
            }else{
                details = details + "<tr><td class='ajaxTd'>997&nbsp;Ack&nbsp;File :</td><td class='ajaxTd'> <a href=\"../download/getAttachment.action?locationAvailable="+ACKFILEID+"\">Download</a></td></tr> ";
            }
           
            //alert(ERRMESSAGE);
            if(ERRMESSAGE != "NO MSG"){
                details = details + "<tr><td class='ajaxTd'>Error&nbsp;Message&nbsp;:</td><td class='ajaxTd'> <font color='red'>"+ERRMESSAGE+"</font></td></tr>";
            }
            
          if(SAP_DETAILS!='NO'){
	 details = details + "<tr><td class='ajaxTd'>SAP_USER&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+SAP_USER+"</td></tr>"+
                      "<tr><td class='ajaxTd'>IDOC&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+IDOC_NUMBER+"</td></tr>";
                    details = details + "<tr><td class='ajaxTd'>PO&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+PO_NUMBER+"</td></tr>"+
                      "<tr><td class='ajaxTd'>PO_DATE&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+PO_DATE+"</td></tr>";
                    details = details + "<tr><td class='ajaxTd' colspan=2><b>IDOC&nbsp;STATUS:<b></td></tr>";
                    details = details + "<tr><td class='ajaxTd'>CODE&nbsp;# &nbsp;:</td><td class='ajaxTd'>"+IDOC_STATUS_CODE+"</td></tr>"+
                      "<tr><td class='ajaxTd'>DESCRIPTION&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+IDOC_STATUS_DESCRIPTION+"</td></tr>";
                     }
            details = details + "</table>";
               
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
      var details = " <h5 >Sorry ! No Results Found</h5>";
                    
    }
     var detailsDIV=document.getElementById("detailInformation");
	detailsDIV.innerHTML="";
	detailsDIV.innerHTML=details;

 
}

/*Reterving ASN Details using ASN Number
 *DATE : 03/21/2013
 * 
 */

function getAsnDetails(number,ponum,fileId){
    
    var num=number;
    var poNum=ponum;
  // alert("poNumber-->"+num);
  // var req = new XMLHttpRequest();
  $(function() {
       
           $('#detail_box').show();
           return false;
               
   });
  var req = getXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populateASNDetails); 

    var url="../ajax/getASNDetails.action?asnNumber="+num+"&poNumber="+poNum+"&fileId="+fileId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateASNDetails(responseXML)
{

   
   var details = responseXML.getElementsByTagName("DETAILS")[0];
    var detail = details.childNodes[0];
    var chk=detail.getElementsByTagName("VALID")[0];  
    
     if(chk.childNodes[0].nodeValue =="true") {
    
            var detail = details.childNodes[0];
            var fileID = detail.getElementsByTagName("FILEID")[0].childNodes[0].nodeValue; 
            var asnNum = detail.getElementsByTagName("ASNNUMBER")[0].childNodes[0].nodeValue;   
            var poNum = detail.getElementsByTagName("PONUMBER")[0].childNodes[0].nodeValue; 
            var bolNum = detail.getElementsByTagName("BOLNUMBER")[0].childNodes[0].nodeValue;  
            var isaNum = detail.getElementsByTagName("ISANUMBER")[0].childNodes[0].nodeValue; 
            var isaDate = detail.getElementsByTagName("ISADATE")[0].childNodes[0].nodeValue; 
            var isaTime = detail.getElementsByTagName("ISATIME")[0].childNodes[0].nodeValue; 
            var STATUS = detail.getElementsByTagName("STATUS")[0].childNodes[0].nodeValue; 
            var PRE_TRANS_FILEPATH = detail.getElementsByTagName("PRETRANSFILEPATH")[0].childNodes[0].nodeValue;
            var POST_TRANS_FILEPATH = detail.getElementsByTagName("POSTTRANSFILEPATH")[0].childNodes[0].nodeValue; 
            var ORGFILEPATH = detail.getElementsByTagName("ORGFILEPATH")[0].childNodes[0].nodeValue;
            var ERRMESSAGE = detail.getElementsByTagName("ERR_MESSAGE")[0].childNodes[0].nodeValue;
            var ACKFILEID = detail.getElementsByTagName("ACKFILEID")[0].childNodes[0].nodeValue;
            var SHIPDATE = detail.getElementsByTagName("SHIPDATE")[0].childNodes[0].nodeValue;
          
            var SENDER_ID = detail.getElementsByTagName("SENDER_ID")[0].childNodes[0].nodeValue;
            var RECEIVER_ID = detail.getElementsByTagName("RECEIVER_ID")[0].childNodes[0].nodeValue; 
            var SENDER_NAME = detail.getElementsByTagName("SENDER_NAME")[0].childNodes[0].nodeValue;
            var RECEIVER_NAME = detail.getElementsByTagName("RECEIVER_NAME")[0].childNodes[0].nodeValue;
            
            var ST_CONTROL_NUMBER = detail.getElementsByTagName("ST_CONTROL_NUMBER")[0].childNodes[0].nodeValue;
            var GS_CONTROL_NUMBER = detail.getElementsByTagName("GS_CONTROL_NUMBER")[0].childNodes[0].nodeValue;
            var TRANSACTION_TYPE = detail.getElementsByTagName("TRANSACTION_TYPE")[0].childNodes[0].nodeValue;
            var FILE_TYPE = detail.getElementsByTagName("FILE_TYPE")[0].childNodes[0].nodeValue; 
           // Sap Detals
               
                var SAP_DETAILS = detail.getElementsByTagName("SAP_DETAILS")[0].childNodes[0].nodeValue;
                var SAP_USER ;
                var IDOC_NUMBER ;
                var PO_NUMBER ;
                var PO_DATE ;
                var IDOC_STATUS_CODE ;
                var IDOC_STATUS_DESCRIPTION ;
                if(SAP_DETAILS!='NO'){
                    
                        SAP_USER = detail.getElementsByTagName("SAP_USER")[0].childNodes[0].nodeValue;
               IDOC_NUMBER = detail.getElementsByTagName("IDOC_NUMBER")[0].childNodes[0].nodeValue;
                PO_NUMBER = detail.getElementsByTagName("PO_NUMBER")[0].childNodes[0].nodeValue;
                 PO_DATE = detail.getElementsByTagName("PO_DATE")[0].childNodes[0].nodeValue;
                  IDOC_STATUS_CODE = detail.getElementsByTagName("IDOC_STATUS_CODE")[0].childNodes[0].nodeValue;
                   IDOC_STATUS_DESCRIPTION = detail.getElementsByTagName("IDOC_STATUS_DESCRIPTION")[0].childNodes[0].nodeValue;
			
                }     
          
           var details="<table style='margin: 0 0 0 0;padding: 0px 0px;'> ";
           details = details +  "<tr><td class='ajaxTd'>Instance Id :</td><td class='ajaxTd'>"+fileID+"</td></tr>"+
                    "<tr><td class='ajaxTd'>ASN # :</td><td class='ajaxTd'>"+asnNum+"</td></tr>"+
                    "<tr><td class='ajaxTd'>PO&nbsp;&nbsp;#:</td><td class='ajaxTd'>"+poNum+"</td></tr>"+
                    "<tr><td class='ajaxTd'>BOL&nbsp;&nbsp;#:</td><td class='ajaxTd'>"+bolNum+"</td></tr>";
                 details = details + "<tr><td class='ajaxTd'>Ship Date :</td><td class='ajaxTd'>"+SHIPDATE+"</td></tr>"+
                     "<tr><td class='ajaxTd'>Document&nbsp;Type&nbsp;:</td><td class='ajaxTd'>"+FILE_TYPE+"</td></tr>"+
                     "<tr><td class='ajaxTd'>Transaction&nbspType&nbsp;:</td><td class='ajaxTd'>"+TRANSACTION_TYPE+"</td></tr>";
          
                
                  details = details + "<tr><td class='ajaxTd'><b>Sender Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                       details = details + "<tr><td class='ajaxTd'> Id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+SENDER_ID+"</td></tr>";
                       details = details + "<tr><td class='ajaxTd'> Name&nbsp;:&nbsp;</td><td class='ajaxTd'>"+SENDER_NAME+"</td></tr>";
               
                       details = details + "<tr><td class='ajaxTd'><b>Receiver Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
               details = details + "<tr><td class='ajaxTd'>Id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+RECEIVER_ID+"</td></tr>";
               details = details + "<tr><td class='ajaxTd'> Name&nbsp;:&nbsp;</td><td class='ajaxTd'>"+RECEIVER_NAME+"</td></tr>";
               details =details+"<tr><td class='ajaxTd'>ISA&nbsp;&nbsp;#:</td><td class='ajaxTd'>"+isaNum+"</td></tr>";
               
               
              details = details + "<tr><td class='ajaxTd'>GS #  :</td><td class='ajaxTd'>"+GS_CONTROL_NUMBER+"</td></tr>";
                      
                     details = details +"<tr><td class='ajaxTd'>ST #  :</td><td class='ajaxTd'>"+ST_CONTROL_NUMBER+"</td></tr>";
              details = details +  "<tr><td class='ajaxTd'>ISA Date  :</td><td class='ajaxTd'>"+isaDate+"</td></tr>"+
                    "<tr><td class='ajaxTd'>ISA Time :</td><td class='ajaxTd'>"+isaTime+"</td></tr>";
                    
                
            if(STATUS.toUpperCase() == "ERROR"){
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp;</td><td class='ajaxTd'><font color='red'>"+STATUS.toUpperCase()+"</font></td></tr>";
            }else if(STATUS.toUpperCase() == "SUCCESS"){
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp; </td><td class='ajaxTd'><font color='green'>"+STATUS.toUpperCase()+"</font></td></tr>";
            }else {
               details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp; </td><td class='ajaxTd'><font color='orange'>"+STATUS.toUpperCase()+"</font></td></tr>"; 
            }
  
            
            if(PRE_TRANS_FILEPATH == "No File"){
               details = details + "<tr><td class='ajaxTd'>Pre-Translation&nbsp;:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
               details = details + "<tr><td class='ajaxTd'>Pre-Translation&nbsp;:</td><td class='ajaxTd'> <a href=\"../download/getAttachment.action?locationAvailable="+PRE_TRANS_FILEPATH+"\">Download</a></td></tr>";
            }
            
            if(POST_TRANS_FILEPATH == "No File"){
                details = details + "<tr><td class='ajaxTd'>Post-Translation&nbsp;:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
                details = details + "<tr><td class='ajaxTd'>Post-Translation&nbsp;:</td><td class='ajaxTd'> <a href=\"../download/getAttachment.action?locationAvailable="+POST_TRANS_FILEPATH+"\">Download</a></td></tr>";
            }
            
            if(ACKFILEID == "No File"){
                details = details + "<tr><td class='ajaxTd'>997&nbsp;Ack&nbsp;File&nbsp;:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
                details = details + "<tr><td class='ajaxTd'>997&nbsp;Ack&nbsp;File&nbsp;:</td><td class='ajaxTd'> <a href=\"../download/getAttachment.action?locationAvailable="+ACKFILEID+"\">Download</a></td></tr>";
            }
            //alert(ERRMESSAGE);
            if(ERRMESSAGE != "NO MSG"){
                details = details + "<tr><td class='ajaxTd'>Error&nbsp;Message :</td><td class='ajaxTd'> <font color='red'>"+ERRMESSAGE+"</font></td></tr>";
            }
              
				  if(SAP_DETAILS!='NO'){
				  details = details + "<tr><td class='ajaxTd'>SAP_USER&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+SAP_USER+"</td></tr>"+
                      "<tr><td class='ajaxTd'>IDOC&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+IDOC_NUMBER+"</td></tr>";
                    details = details + "<tr><td class='ajaxTd'>PO&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+PO_NUMBER+"</td></tr>"+
                      "<tr><td class='ajaxTd'>PO_DATE&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+PO_DATE+"</td></tr>";
                    details = details + "<tr><td class='ajaxTd' colspan=2><b>IDOC&nbsp;STATUS:<b></td></tr>";
                    details = details + "<tr><td class='ajaxTd'>CODE&nbsp;# &nbsp;:</td><td class='ajaxTd'>"+IDOC_STATUS_CODE+"</td></tr>"+
                      "<tr><td class='ajaxTd'>DESCRIPTION&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+IDOC_STATUS_DESCRIPTION+"</td></tr>";
                     }
     details = details + "</table>";
          
      //  }
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
      var details = " <h5 >Sorry ! No Results Found</h5>";
                    
    }
     var detailsDIV=document.getElementById("detailInformation");
	detailsDIV.innerHTML="";
	detailsDIV.innerHTML=details;
    /*  $(function() {
       
           $('#detail_box').show();
           return false;
               
   });*/
 
}

/*
 * Reteriving Invoice Table Details
 * Date : 03/21/2013
 * 
 */
function getInvDetails(number,ponum,fileID){
    var num=number;
   //alert("inv number-->"+num);
 //  var req = new XMLHttpRequest();
 $(function() {
       
           $('#detail_box').show();
           return false;
               
   });
 var req = getXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populateInvDetails); 

    var url="../ajax/getInvDetails.action?invNumber="+num+"&poNumber="+ponum+"&fileId="+fileID;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateInvDetails(responseXML)
{
     var details = responseXML.getElementsByTagName("DETAILS")[0];
    var detail = details.childNodes[0];
    var chk=detail.getElementsByTagName("VALID")[0];  
    
     if(chk.childNodes[0].nodeValue =="true") {   
            var detail = details.childNodes[0];
            var fileID = detail.getElementsByTagName("FILEID")[0].childNodes[0].nodeValue; 
            var invNum = detail.getElementsByTagName("INVNUMBER")[0].childNodes[0].nodeValue;   
            var poNum = detail.getElementsByTagName("PONUMBER")[0].childNodes[0].nodeValue; 
            var itemQty = detail.getElementsByTagName("ITEMQTY")[0].childNodes[0].nodeValue;  
            var invAmt = detail.getElementsByTagName("INVAMT")[0].childNodes[0].nodeValue;  
            var invDate = detail.getElementsByTagName("INVDATE")[0].childNodes[0].nodeValue;
            var isaNum = detail.getElementsByTagName("ISANUM")[0].childNodes[0].nodeValue; 
            var isaDate = detail.getElementsByTagName("ISADATE")[0].childNodes[0].nodeValue; 
            var isaTime = detail.getElementsByTagName("ISATIME")[0].childNodes[0].nodeValue; 
            var STATUS = detail.getElementsByTagName("STATUS")[0].childNodes[0].nodeValue; 
            var PRE_TRANS_FILEPATH = detail.getElementsByTagName("PRETRANSFILEPATH")[0].childNodes[0].nodeValue;
            var POST_TRANS_FILEPATH = detail.getElementsByTagName("POSTTRANSFILEPATH")[0].childNodes[0].nodeValue; 
            var ORGFILEPATH = detail.getElementsByTagName("ORGFILEPATH")[0].childNodes[0].nodeValue;
            var ERRMESSAGE = detail.getElementsByTagName("ERR_MESSAGE")[0].childNodes[0].nodeValue;
            var ACKFILEID = detail.getElementsByTagName("ACKFILEID")[0].childNodes[0].nodeValue;
            
             var SENDER_ID = detail.getElementsByTagName("SENDER_ID")[0].childNodes[0].nodeValue;
            var RECEIVER_ID = detail.getElementsByTagName("RECEIVER_ID")[0].childNodes[0].nodeValue; 
            var SENDER_NAME = detail.getElementsByTagName("SENDER_NAME")[0].childNodes[0].nodeValue;
            var RECEIVER_NAME = detail.getElementsByTagName("RECEIVER_NAME")[0].childNodes[0].nodeValue;
            
            var TRANSACTION_TYPE = detail.getElementsByTagName("TRANSACTION_TYPE")[0].childNodes[0].nodeValue;
            var ST_CONTROL_NUMBER = detail.getElementsByTagName("ST_CONTROL_NUMBER")[0].childNodes[0].nodeValue;
            var GS_CONTROL_NUMBER = detail.getElementsByTagName("GS_CONTROL_NUMBER")[0].childNodes[0].nodeValue;
            var FILETYPE = detail.getElementsByTagName("FILETYPE")[0].childNodes[0].nodeValue;
         // alert(deilvaryName+" "+poValue+ " "+ routings+ " "+invoice+" "+itemQty);
          
          // Sap Detals
               
                var SAP_DETAILS = detail.getElementsByTagName("SAP_DETAILS")[0].childNodes[0].nodeValue;
                var SAP_USER ;
                var IDOC_NUMBER ;
                var PO_NUMBER ;
                var PO_DATE ;
                var IDOC_STATUS_CODE ;
                var IDOC_STATUS_DESCRIPTION ;
                if(SAP_DETAILS!='NO'){
                    
                        SAP_USER = detail.getElementsByTagName("SAP_USER")[0].childNodes[0].nodeValue;
               IDOC_NUMBER = detail.getElementsByTagName("IDOC_NUMBER")[0].childNodes[0].nodeValue;
                PO_NUMBER = detail.getElementsByTagName("PO_NUMBER")[0].childNodes[0].nodeValue;
                 PO_DATE = detail.getElementsByTagName("PO_DATE")[0].childNodes[0].nodeValue;
                  IDOC_STATUS_CODE = detail.getElementsByTagName("IDOC_STATUS_CODE")[0].childNodes[0].nodeValue;
                   IDOC_STATUS_DESCRIPTION = detail.getElementsByTagName("IDOC_STATUS_DESCRIPTION")[0].childNodes[0].nodeValue;
			
                }
          	  
           var details="<table style='margin: 0 0 0 0;padding: 0px 0px;'>"+
                    "<tr><td class='ajaxTd'>Instance Id :</td><td class='ajaxTd'>"+fileID+"</td></tr>"+
                    "<tr><td class='ajaxTd'>Invoice # :</td><td class='ajaxTd'>"+invNum+"</td></tr>"+
                    "<tr><td class='ajaxTd'>PO #:</td><td class='ajaxTd'>"+poNum+"</td></tr>"+
                    "<tr><td class='ajaxTd'>Invoice Value :</td><td class='ajaxTd'>"+invAmt+"</td></tr>";
               if(invDate!="NO"){   
                   details = details + "<tr><td class='ajaxTd'>Invoice DATE :</td><td class='ajaxTd'>"+invDate+"</td></tr>";
               }
            details = details + "<tr><td class='ajaxTd'>Item Quantity  :</td><td class='ajaxTd'>"+itemQty+"</td></tr>";
             details = details +   "<tr><td class='ajaxTd'>Document&nbsp;Type&nbsp;:</td><td class='ajaxTd'>"+FILETYPE+"</td></tr>";
             details = details +    "<tr><td class='ajaxTd'>Transaction&nbspType&nbsp;:</td><td class='ajaxTd'>"+TRANSACTION_TYPE+"</td></tr>";
             details = details + "<tr><td class='ajaxTd'><b>Sender Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
             details = details + "<tr><td class='ajaxTd'> Id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+SENDER_ID+"</td></tr>";
             details = details + "<tr><td class='ajaxTd'> Name&nbsp;:&nbsp;</td><td class='ajaxTd'>"+SENDER_NAME+"</td></tr>";
               
              details = details + "<tr><td class='ajaxTd'><b>Receiver Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
               details = details + "<tr><td class='ajaxTd'>Id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+RECEIVER_ID+"</td></tr>";
               details = details + "<tr><td class='ajaxTd'> Name&nbsp;:&nbsp;</td><td class='ajaxTd'>"+RECEIVER_NAME+"</td></tr>";
               
               details = details + "<tr><td class='ajaxTd'>ISA #  :</td><td class='ajaxTd'>"+isaNum+"</td></tr>";
                   
            details = details + "<tr><td class='ajaxTd'>GS #  :</td><td class='ajaxTd'>"+GS_CONTROL_NUMBER+"</td></tr>";
            details = details + "<tr><td class='ajaxTd'>ST #  :</td><td class='ajaxTd'>"+ST_CONTROL_NUMBER+"</td></tr>";
               
            details  = details+ "<tr><td class='ajaxTd'>ISA date  :</td><td class='ajaxTd'>"+isaDate+"</td></tr>"+
                    "<tr><td class='ajaxTd'>ISA Time :</td><td class='ajaxTd'>"+isaTime+"</td></tr>";
            if(STATUS.toUpperCase() == "ERROR"){
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp;</td><td class='ajaxTd'><font color='red'>"+STATUS.toUpperCase()+"</font></td></tr>";
            }else if(STATUS.toUpperCase() == "SUCCESS"){
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp; </td><td class='ajaxTd'><font color='green'>"+STATUS.toUpperCase()+"</font></td></tr>";
            }else {
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp; </td><td class='ajaxTd'><font color='orange'>"+STATUS.toUpperCase()+"</font></td></tr>";
            }
                    //"<tr><td class='ajaxTd'><font color='red'>Status :</font></td><td class='ajaxTd'><font color='red'>"+STATUS+"</font></td></tr>";
    
             /*    if(ORGFILEPATH == "No File"){
               details = details + "<tr><td class='ajaxTd' >Original File&nbsp:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
               details = details + "<tr><td class='ajaxTd' >Original File&nbsp;: </td><td class='ajaxTd'><a href=\"../download/getAttachment.action?locationAvailable="+ORGFILEPATH+"\">Download</a></td></tr>";
            }
            */
            if(PRE_TRANS_FILEPATH == "No File"){
               details = details + "<tr><td class='ajaxTd' >Pre-Translation&nbsp:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
               details = details + "<tr><td class='ajaxTd' >Pre-Translation&nbsp;:</td><td class='ajaxTd'> <a href=\"../download/getAttachment.action?locationAvailable="+PRE_TRANS_FILEPATH+"\">Download</a></td></tr>";
            }
            
            if(POST_TRANS_FILEPATH == "No File"){
                details = details + "<tr><td class='ajaxTd' >Post-Translation&nbsp;:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
                details = details + "<tr><td class='ajaxTd' >Post-Translation&nbsp;:</td><td class='ajaxTd'> <a href=\"../download/getAttachment.action?locationAvailable="+POST_TRANS_FILEPATH+"\">Download</a></td></tr>";
            }
            
            if(ACKFILEID == "No File"){
                details = details + "<tr><td class='ajaxTd' >997&nbsp;Ack&nbsp;File&nbsp;:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
                details = details + "<tr><td class='ajaxTd' >997&nbsp;Ack&nbsp;File&nbsp;:</td><td class='ajaxTd'> <a href=\"../download/getAttachment.action?locationAvailable="+ACKFILEID+"\">Download</a></td></tr>";
            }
            //alert(ERRMESSAGE);
            if(ERRMESSAGE != "NO MSG"){
                details = details + "<tr><td class='ajaxTd' >Error&nbsp;Message&nbsp;:</td><td class='ajaxTd'> <font color='red'>"+ERRMESSAGE+"</font></td></tr>";
            }
            				  
	 if(SAP_DETAILS!='NO'){
	details = details + "<tr><td class='ajaxTd'>SAP_USER&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+SAP_USER+"</td></tr>"+
                      "<tr><td class='ajaxTd'>IDOC&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+IDOC_NUMBER+"</td></tr>";
                    details = details + "<tr><td class='ajaxTd'>PO&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+PO_NUMBER+"</td></tr>"+
                      "<tr><td class='ajaxTd'>PO_DATE&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+PO_DATE+"</td></tr>";
                    details = details + "<tr><td class='ajaxTd' colspan=2><b>IDOC&nbsp;STATUS:<b></td></tr>";
                    details = details + "<tr><td class='ajaxTd'>CODE&nbsp;# &nbsp;:</td><td class='ajaxTd'>"+IDOC_STATUS_CODE+"</td></tr>"+
                      "<tr><td class='ajaxTd'>DESCRIPTION&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+IDOC_STATUS_DESCRIPTION+"</td></tr>";
                     }
            
           details = details +"</table>";
      //  }
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
      var details = " <h5 >Sorry ! No Results Found</h5>";
                    
    }
     var detailsDIV=document.getElementById("detailInformation");
	detailsDIV.innerHTML="";
	detailsDIV.innerHTML=details;
     /* $(function() {
       
           $('#detail_box').show();
           return false;
               
   });*/
 
}


/**
 * For doc Ajax call
 * 
 */
function getDocDetails(number,ponum,id,db){
    
    var num=number;
    var ponum = ponum;
    var id = id;
    alert(num +""+ponum+""+id+""+db)
    $(function() {
       
           $('#detail_box').show();
           return false;
               
   });
  /* alert(ponum);
   if(ponum=='')
   {
       ponum=null;
   }*/
  // alert(ponum);
   var req = getXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populateDocDetails); 
   var url="../ajax/getDocDetails.action?isaNumber="+num+"&poNumber="+ponum+"&id="+id+"&database="+db;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}



function populateDocDetails(responseXML)
{
    //alert("responseXML--->"+responseXML.toString());
    var details = responseXML.getElementsByTagName("DETAILS")[0];
    // alert("details--->"+details);
    var detail = details.childNodes[0];
   // alert("responseXML--->"+detail.getElementsByTagName("SEC_KEY_VAL")[0].childNodes[0].nodeValue);
    var chk=detail.getElementsByTagName("VALID")[0];  
    
   
   // alert(confirmMessage);
    
    
     if(chk.childNodes[0].nodeValue =="true") {
    
            var detail = details.childNodes[0];
            
            var fileid = detail.getElementsByTagName("FILEID")[0].childNodes[0].nodeValue;   
            var docType = detail.getElementsByTagName("FILETYPE")[0].childNodes[0].nodeValue; 
            
            var PRE_TRANS_FILEPATH = detail.getElementsByTagName("PRETRANSFILEPATH")[0].childNodes[0].nodeValue;
            var POST_TRANS_FILEPATH = detail.getElementsByTagName("POSTTRANSFILEPATH")[0].childNodes[0].nodeValue; 
            
            var SENDER_ID = detail.getElementsByTagName("SENDERID")[0].childNodes[0].nodeValue;
            var RECEIVER_ID = detail.getElementsByTagName("RECEIVERID")[0].childNodes[0].nodeValue; 
             var SENDER_NAME = detail.getElementsByTagName("SENDER_NAME")[0].childNodes[0].nodeValue;
            var RECEIVER_NAME = detail.getElementsByTagName("RECEIVER_NAME")[0].childNodes[0].nodeValue;
            
            var ISA_NUMBER = detail.getElementsByTagName("ISA_NUMBER")[0].childNodes[0].nodeValue; 
            var ISA_DATE = detail.getElementsByTagName("ISA_DATE")[0].childNodes[0].nodeValue; 
            var ISA_TIME = detail.getElementsByTagName("ISA_TIME")[0].childNodes[0].nodeValue; 
            var GS_CONTROL_NUMBER = detail.getElementsByTagName("GS_CONTROL_NUMBER")[0].childNodes[0].nodeValue; 
            var ST_CONTROL_NUMBER = detail.getElementsByTagName("ST_CONTROL_NUMBER")[0].childNodes[0].nodeValue; 
            //var TRAN_NUMBER = detail.getElementsByTagName("TRANSACTION_TYPE")[0].childNodes[0].nodeValue; 
            var SEC_KEY_VAL = detail.getElementsByTagName("SEC_KEY_VAL")[0].childNodes[0].nodeValue; 
            var PRI_KEY_TYPE = detail.getElementsByTagName("PRI_KEY_TYPE")[0].childNodes[0].nodeValue; 
            var PRI_KEY_VAL = detail.getElementsByTagName("PRI_KEY_VAL")[0].childNodes[0].nodeValue; 
            var ORG_FILEPATH = detail.getElementsByTagName("ORG_FILEPATH")[0].childNodes[0].nodeValue; 
            var ACKFILEID = detail.getElementsByTagName("ACKFILEID")[0].childNodes[0].nodeValue;
            var DIRECTION = detail.getElementsByTagName("DIRECTION")[0].childNodes[0].nodeValue;
            var ERRMESSAGE = detail.getElementsByTagName("ERR_MESSAGE")[0].childNodes[0].nodeValue;
            
            var TRANSACTION_TYPE = detail.getElementsByTagName("TRANSACTION_TYPE")[0].childNodes[0].nodeValue;
             var STATUS = detail.getElementsByTagName("STATUS")[0].childNodes[0].nodeValue; 
             
             // Sap Detals
               
                var SAP_DETAILS = detail.getElementsByTagName("SAP_DETAILS")[0].childNodes[0].nodeValue;
                var SAP_USER ;
                var IDOC_NUMBER ;
                var PO_NUMBER ;
                var PO_DATE ;
                var IDOC_STATUS_CODE ;
                var IDOC_STATUS_DESCRIPTION ;
                if(SAP_DETAILS!='NO'){
                    
                    SAP_USER = detail.getElementsByTagName("SAP_USER")[0].childNodes[0].nodeValue;
                    IDOC_NUMBER = detail.getElementsByTagName("IDOC_NUMBER")[0].childNodes[0].nodeValue;
                    PO_NUMBER = detail.getElementsByTagName("PO_NUMBER")[0].childNodes[0].nodeValue;
                    PO_DATE = detail.getElementsByTagName("PO_DATE")[0].childNodes[0].nodeValue;
                    IDOC_STATUS_CODE = detail.getElementsByTagName("IDOC_STATUS_CODE")[0].childNodes[0].nodeValue;
                    IDOC_STATUS_DESCRIPTION = detail.getElementsByTagName("IDOC_STATUS_DESCRIPTION")[0].childNodes[0].nodeValue;
			
                }
               /* var FILE_EXT_DETAILS = detail.getElementsByTagName("FILE_EXT_DETAILS")[0].childNodes[0].nodeValue;
                var FILE;
                var IDOC; 
              var PAYEE_NAME;
              var PAYEE_VENDOR_ID;
              var MONETARY_AMOUNT; 
              var PAYMENT_METHOD;
              var CURRENCY;
              var CHEQUE_NUMBER;
              var TRANSACTION_TRACE_CODE;
              var SHIP_TO_NAME; 
              var SHIP_TO_CODE; 
              var SHIP_TO_CITY; 
              var SHIP_TO_REGION;
              var SHIP_TO_POSTAL_CODE;
              var PARTNER_NAME;
             // var PO;
              var PO_DATE; 
              var PO_AMOUNT;
              var INVOICE; 
              var INVOICE_DATE;
              var INVOICE_TOTAL; 
              var SALES_AREA; 
              var SELLING_SALES_ORG;
              var SALES_ORDER;
              var LINE_COUNT; 
              var ITEM_LINE_COUNT;
              var RDD; 
                if(FILE_EXT_DETAILS!='NO'){
            
                  
                    if(TRANSACTION_TYPE=='850'){
                    //alert(PRI_KEY_TYPE);
                    PARTNER_NAME = detail.getElementsByTagName("PARTNER_NAME")[0].childNodes[0].nodeValue;
                    PO_DATE = detail.getElementsByTagName("PO_DATE")[0].childNodes[0].nodeValue;
                    PO_AMOUNT = detail.getElementsByTagName("PO_AMOUNT")[0].childNodes[0].nodeValue;
                    SHIP_TO_NAME = detail.getElementsByTagName("SHIP_TO_NAME")[0].childNodes[0].nodeValue;
                    SHIP_TO_CODE = detail.getElementsByTagName("SHIP_TO_CODE")[0].childNodes[0].nodeValue;
                    SHIP_TO_CITY = detail.getElementsByTagName("SHIP_TO_CITY")[0].childNodes[0].nodeValue;
                    SHIP_TO_POSTAL_CODE = detail.getElementsByTagName("SHIP_TO_POSTAL_CODE")[0].childNodes[0].nodeValue;
                    SHIP_TO_REGION = detail.getElementsByTagName("SHIP_TO_REGION")[0].childNodes[0].nodeValue;
                    RDD = detail.getElementsByTagName("RDD")[0].childNodes[0].nodeValue;
                    SELLING_SALES_ORG = detail.getElementsByTagName("SELLING_SALES_ORG")[0].childNodes[0].nodeValue;
                    LINE_COUNT = detail.getElementsByTagName("LINE_COUNT")[0].childNodes[0].nodeValue;
                    }
                    //alert(PRI_KEY_TYPE);
                      if(TRANSACTION_TYPE=='810')
                    {
                   // alert('Inside'+PRI_KEY_TYPE);
                    PARTNER_NAME = detail.getElementsByTagName("PARTNER_NAME")[0].childNodes[0].nodeValue;
                    INVOICE = detail.getElementsByTagName("INVOICE")[0].childNodes[0].nodeValue;
                    INVOICE_DATE = detail.getElementsByTagName("INVOICE_DATE")[0].childNodes[0].nodeValue;
                    SHIP_TO_NAME = detail.getElementsByTagName("SHIP_TO_NAME")[0].childNodes[0].nodeValue;
                    SHIP_TO_CODE = detail.getElementsByTagName("SHIP_TO_CODE")[0].childNodes[0].nodeValue;
                    SHIP_TO_CITY = detail.getElementsByTagName("SHIP_TO_CITY")[0].childNodes[0].nodeValue;
                    SHIP_TO_POSTAL_CODE = detail.getElementsByTagName("SHIP_TO_POSTAL_CODE")[0].childNodes[0].nodeValue;
                    SHIP_TO_REGION = detail.getElementsByTagName("SHIP_TO_REGION")[0].childNodes[0].nodeValue;
                    SALES_AREA = detail.getElementsByTagName("SALES_AREA")[0].childNodes[0].nodeValue;
                    ITEM_LINE_COUNT = detail.getElementsByTagName("ITEM_LINE_COUNT")[0].childNodes[0].nodeValue;
                    INVOICE_TOTAL = detail.getElementsByTagName("INVOICE_TOTAL")[0].childNodes[0].nodeValue;
                    IDOC = detail.getElementsByTagName("IDOC")[0].childNodes[0].nodeValue;
                    }
                    if(TRANSACTION_TYPE=='820'){
                    PARTNER_NAME = detail.getElementsByTagName("PARTNER_NAME")[0].childNodes[0].nodeValue;
                    PAYMENT_METHOD = detail.getElementsByTagName("PAYMENT_METHOD")[0].childNodes[0].nodeValue;
                    CURRENCY = detail.getElementsByTagName("CURRENCY")[0].childNodes[0].nodeValue;
                    TRANSACTION_TRACE_CODE = detail.getElementsByTagName("TRANSACTION_TRACE_CODE")[0].childNodes[0].nodeValue;
                    CHEQUE_NUMBER = detail.getElementsByTagName("CHEQUE_NUMBER")[0].childNodes[0].nodeValue;
                    PAYEE_VENDOR_ID = detail.getElementsByTagName("PAYEE_VENDOR_ID")[0].childNodes[0].nodeValue;
                    PAYEE_NAME = detail.getElementsByTagName("PAYEE_NAME")[0].childNodes[0].nodeValue;
                    MONETARY_AMOUNT = detail.getElementsByTagName("MONETARY_AMOUNT")[0].childNodes[0].nodeValue;
                    FILE = detail.getElementsByTagName("FILE")[0].childNodes[0].nodeValue;
                    
                    }
                   /* if(PRI_KEY_TYPE=='ASN'){
                    PARTNER_NAME = detail.getElementsByTagName("PARTNER_NAME")[0].childNodes[0].nodeValue;
                    PAYMENT_METHOD = detail.getElementsByTagName("PAYMENT_METHOD")[0].childNodes[0].nodeValue;
                    CURRENCY = detail.getElementsByTagName("CURRENCY")[0].childNodes[0].nodeValue;
                    TRANSACTION_TRACE_CODE = detail.getElementsByTagName("TRANSACTION_TRACE_CODE")[0].childNodes[0].nodeValue;
                   // CHEQUE_NUMBER = detail.getElementsByTagName("CHEQUE_NUMBER")[0].childNodes[0].nodeValue;
                    PAYEE_VENDOR_ID = detail.getElementsByTagName("PAYEE_VENDOR_ID")[0].childNodes[0].nodeValue;
                    PAYEE_NAME = detail.getElementsByTagName("PAYEE_NAME")[0].childNodes[0].nodeValue;
                    MONETARY_AMOUNT = detail.getElementsByTagName("MONETARY_AMOUNT")[0].childNodes[0].nodeValue;
                    IDOC = detail.getElementsByTagName("IDOC")[0].childNodes[0].nodeValue;
                    }*/
                    //       FILE = detail.getElementsByTagName("FILE")[0].childNodes[0].nodeValue;
                    //                SALES_ORDER = detail.getElementsByTagName("SALES_ORDER")[0].childNodes[0].nodeValue;
              //  IDOC = detail.getElementsByTagName("IDOC")[0].childNodes[0].nodeValue;
               // PAYEE_NAME = detail.getElementsByTagName("PAYEE_NAME")[0].childNodes[0].nodeValue;
               // PAYEE_VENDOR_ID = detail.getElementsByTagName("PAYEE_VENDOR_ID")[0].childNodes[0].nodeValue;
               // MONETARY_AMOUNT = detail.getElementsByTagName("MONETARY_AMOUNT")[0].childNodes[0].nodeValue;
               // PAYMENT_METHOD = detail.getElementsByTagName("PAYMENT_METHOD")[0].childNodes[0].nodeValue;
               // CURRENCY = detail.getElementsByTagName("CURRENCY")[0].childNodes[0].nodeValue;
               // CHEQUE_NUMBER = detail.getElementsByTagName("CHEQUE_NUMBER")[0].childNodes[0].nodeValue;
               // TRANSACTION_TRACE_CODE = detail.getElementsByTagName("TRANSACTION_TRACE_CODE")[0].childNodes[0].nodeValue;
                
               // SHIP_TO_POSTAL_CODE = detail.getElementsByTagName("SHIP_TO_POSTAL_CODE")[0].childNodes[0].nodeValue;
                
               // PO = detail.getElementsByTagName("PO")[0].childNodes[0].nodeValue;
                // INVOICE = detail.getElementsByTagName("INVOICE")[0].childNodes[0].nodeValue;
               // INVOICE_DATE = detail.getElementsByTagName("INVOICE_DATE")[0].childNodes[0].nodeValue;
               // INVOICE_TOTAL = detail.getElementsByTagName("INVOICE_TOTAL")[0].childNodes[0].nodeValue;
               // SALES_AREA = detail.getElementsByTagName("SALES_AREA")[0].childNodes[0].nodeValue;
                //SELLING_SALES_ORG = detail.getElementsByTagName("SELLING_SALES_ORG")[0].childNodes[0].nodeValue;
                //SALES_ORDER = detail.getElementsByTagName("SALES_ORDER")[0].childNodes[0].nodeValue;
                //LINE_COUNT = detail.getElementsByTagName("LINE_COUNT")[0].childNodes[0].nodeValue;
               // ITEM_LINE_COUNT = detail.getElementsByTagName("ITEM_LINE_COUNT")[0].childNodes[0].nodeValue;
                
                
			
              //  }
          
         
          var details = "<table style='margin: 0 0 0 0;padding: 0px 0px;'><tr><td class='ajaxTd'>File ID :&nbsp; &nbsp; </td><td class='ajaxTd'>"+fileid+"</td></tr>";
                      
                    if(SEC_KEY_VAL!="NULL"&&SEC_KEY_VAL!="")
                    {
                     details = details +  " <tr><td class='ajaxTd'>Purchase Order : </td><td class='ajaxTd'>"+SEC_KEY_VAL+"</td></tr>";
                    }
                  // alert(PRI_KEY_TYPE);
                   if(PRI_KEY_VAL=="NO"){
                   PRI_KEY_VAL="-";
                   }
                   if(PRI_KEY_TYPE != "PO"){
                   details = details + " <tr><td class='ajaxTd'>"+PRI_KEY_TYPE+" # : </td><td class='ajaxTd'>"+PRI_KEY_VAL+"</td></tr>";
                   }
                      details = details + "<tr><td class='ajaxTd'>Document Type&nbsp;:</td><td class='ajaxTd'>"+docType+"</td></tr>"+
                          "<tr><td class='ajaxTd'>Transaction Type&nbsp;:</td><td class='ajaxTd'>"+TRANSACTION_TYPE+"</td></tr>";
                      
                      //"<tr><td class='ajaxTd'>SENDER_ID :&nbsp; &nbsp;</td><td class='ajaxTd'>"+SENDER_ID+"</td></tr>"+
                      //"<tr><td class='ajaxTd'>RECEIVER_ID :&nbsp; &nbsp;</td><td class='ajaxTd'>"+RECEIVER_ID+"</td></tr>"+
                      
                     // alert("direction--->"+DIRECTION);
                      if(DIRECTION == "inbound"){
                          details = details + "<tr><td class='ajaxTd'><b>Sender Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                      }else{
                          details = details + "<tr><td class='ajaxTd'><b>Sender Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                      }
                      // details = details + "<tr><td class='ajaxTd'><b>Sender Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                       details = details + "<tr><td class='ajaxTd'> Sender Id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+SENDER_ID+"</td></tr>";
                       details = details + "<tr><td class='ajaxTd'> Sender Name&nbsp;:&nbsp;</td><td class='ajaxTd'>"+SENDER_NAME+"</td></tr>";
               
                        if(DIRECTION == "outbound"){
                          details = details + "<tr><td class='ajaxTd'><b>Receiver Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                      }else{
                          details = details + "<tr><td class='ajaxTd'><b>Receiver Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                      }
                      // details = details + "<tr><td class='ajaxTd'><b>Receiver Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                       details = details + "<tr><td class='ajaxTd'>Receiver Id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+RECEIVER_ID+"</td></tr>";
                       details = details + "<tr><td class='ajaxTd'>Receiver Name&nbsp;:&nbsp;</td><td class='ajaxTd'>"+RECEIVER_NAME+"</td></tr>";
               
                       
                      details = details + "<tr><td class='ajaxTd'>ISA CONTROL Number :&nbsp; &nbsp;</td><td class='ajaxTd'>"+ISA_NUMBER+"</td></tr>";
                      if(GS_CONTROL_NUMBER!="No GS CONTROL NUMBER") 
                      {
                      details = details +"<tr><td class='ajaxTd'>GS CONTROL NUMBER :&nbsp; &nbsp;</td><td class='ajaxTd'>"+GS_CONTROL_NUMBER+"</td></tr>";
                      }
                      details = details +"<tr><td class='ajaxTd'>ST CONTROL NUMBER :&nbsp; &nbsp;</td><td class='ajaxTd'>"+ST_CONTROL_NUMBER+"</td></tr>";
                  details  = details +"<tr><td class='ajaxTd'>ISA Date :&nbsp; &nbsp;</td><td class='ajaxTd'>"+ISA_DATE+"</td></tr>"+
                          "<tr><td class='ajaxTd'>ISA Time :&nbsp; &nbsp;</td><td class='ajaxTd'>"+ISA_TIME+"</td></tr>";
             
            /* if(TRAN_NUMBER!="856" && TRAN_NUMBER!="810"){
            if(ORG_FILEPATH == "No File"){
                details = details + "<tr><td class='ajaxTd'>Original File&nbsp;:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
                details = details + "<tr><td class='ajaxTd'>Original File&nbsp;:</td><td class='ajaxTd'><a href=\"../download/getAttachment.action?locationAvailable="+ORG_FILEPATH+"\">Download</a></td></tr>";
            }
            }*/
        
        if(STATUS.toUpperCase() == "ERROR"){
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp;</td><td class='ajaxTd'><font color='red'>"+STATUS.toUpperCase()+"</font></td></tr>";
            }else if(STATUS.toUpperCase() == "SUCCESS"){
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp; </td><td class='ajaxTd'><font color='green'>"+STATUS.toUpperCase()+"</font></td></tr>";
            }else {
               details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp; </td><td class='ajaxTd'><font color='orange'>"+STATUS.toUpperCase()+"</font></td></tr>"; 
            }
            
            if(PRE_TRANS_FILEPATH == "No File"){
               details = details + "<tr><td class='ajaxTd'>Pre Translation&nbsp;:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
               details = details + "<tr><td class='ajaxTd'>Pre Translation &nbsp;:</td><td class='ajaxTd'><a href=\"../download/getAttachment.action?locationAvailable="+PRE_TRANS_FILEPATH+"\">Download</a></td></tr>";
            }
            
            if(POST_TRANS_FILEPATH == "No File"){
                details = details + "<tr><td class='ajaxTd'>Post Translation&nbsp;:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
                details = details + "<tr><td class='ajaxTd'>Post Translation&nbsp;:</td><td class='ajaxTd'><a href=\"../download/getAttachment.action?locationAvailable="+POST_TRANS_FILEPATH+"\">Download</a></td></tr>";
            }
            
           // alert("TRAN_NUMBER--->"+TRAN_NUMBER);
            if(ACKFILEID == "No File"){
                details = details + "<tr><td class='ajaxTd'>997&nbsp;Acknowledge File&nbsp;:</td><td class='ajaxTd'> -- </tr></td>";
            }else{
                details = details + "<tr><td class='ajaxTd'>997&nbsp;Acknowledge File&nbsp;:</td><td class='ajaxTd'> <a href=\"../download/getAttachment.action?locationAvailable="+ACKFILEID+"\">Download</a></td></tr> ";
            }
            
            if(ERRMESSAGE != "NO MSG"){
                details = details + "<tr><td class='ajaxTd'>Error Message&nbsp;:</td><td class='ajaxTd'> <font color='red'>"+ERRMESSAGE+"</font></td></tr></table>";
            }
                     if(SAP_DETAILS!='NO'){
				  details = details + "<tr><td class='ajaxTd'>SAP USER&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+SAP_USER+"</td></tr>"+
                      "<tr><td class='ajaxTd'>IDOC Number&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+IDOC_NUMBER+"</td></tr>";
                    details = details + "<tr><td class='ajaxTd'>Purchase Order&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+PO_NUMBER+"</td></tr>"+
                      "<tr><td class='ajaxTd'>Purchase Order Date&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+PO_DATE+"</td></tr>";
                    details = details + "<tr><td class='ajaxTd' colspan=2><b>IDOC Status:<b></td></tr>";
                    details = details + "<tr><td class='ajaxTd'>IDOC Status Code&nbsp;# &nbsp;:</td><td class='ajaxTd'>"+IDOC_STATUS_CODE+"</td></tr>"+
                      "<tr><td class='ajaxTd'>IDOC Status Description&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+IDOC_STATUS_DESCRIPTION+"</td></tr>";
                     }
            /*  if(FILE_EXT_DETAILS!='NO'){
		  details = details + "<tr><td class='ajaxTd' colspan=2><b>Other Details:<b></td></tr>";
                 
                  if(TRANSACTION_TYPE=='850'){
                   if(PARTNER_NAME!='--'){
                    details = details + "<tr><td class='ajaxTd'>PARTNER NAME&nbsp;&nbsp;:</td><td class='ajaxTd'>"+PARTNER_NAME+"</td></tr>";
                       }
                   if(PO_DATE!='--'){
                     details = details + "<tr><td class='ajaxTd'>PO DATE&nbsp;&nbsp;:</td><td class='ajaxTd'>"+PO_DATE+"</td></tr>";
                         }
                     if(RDD!='--'){
                   details = details + "<tr><td class='ajaxTd'>RDD&nbsp;&nbsp;:</td><td class='ajaxTd'>"+RDD+"</td></tr>";
                    
                     }    
                    if(SHIP_TO_NAME!='--'){
                      details = details + "<tr><td class='ajaxTd'>SHIP TO NAME&nbsp;&nbsp;:</td><td class='ajaxTd'>"+SHIP_TO_NAME+"</td></tr>";
                   }
                    if(SHIP_TO_CODE!='--'){
                      details = details + "<tr><td class='ajaxTd'>SHIP TO CODE&nbsp;&nbsp;:</td><td class='ajaxTd'>"+SHIP_TO_CODE+"</td></tr>";
                   }
                    if(SHIP_TO_CITY!='--'){
                    details = details + "<tr><td class='ajaxTd'>SHIP TO CITY&nbsp;&nbsp;:</td><td class='ajaxTd'>"+SHIP_TO_CITY+"</td></tr>";
                    }
                    if(SHIP_TO_POSTAL_CODE!='--'){
                      details = details + "<tr><td class='ajaxTd'>SHIP TO POSTAL CODE&nbsp;&nbsp;:</td><td class='ajaxTd'>"+SHIP_TO_POSTAL_CODE+"</td></tr>";
                     }
                     if(SHIP_TO_REGION!='--'){
                      details = details + "<tr><td class='ajaxTd'>SHIP TO REGION&nbsp;&nbsp;:</td><td class='ajaxTd'>"+SHIP_TO_REGION+"</td></tr>";
                     }
                     if(SELLING_SALES_ORG!='--'){
                    details = details + "<tr><td class='ajaxTd'>SELLING SALES ORG&nbsp;&nbsp;:</td><td class='ajaxTd'>"+SELLING_SALES_ORG+"</td></tr>";
                          }
                       if(LINE_COUNT!='--'){
                  details = details + "<tr><td class='ajaxTd'>LINE COUNT&nbsp;&nbsp;:</td><td class='ajaxTd'>"+LINE_COUNT+"</td></tr>";
                            }
                     if(PO_AMOUNT!='--'){
                     details = details + "<tr><td class='ajaxTd'>PO AMOUNT :</td><td class='ajaxTd'>"+PO_AMOUNT+"</td></tr>";
                          }
                  }
                   
                  
                  
                    if(TRANSACTION_TYPE=='810'){
                       
                    
                   if(PARTNER_NAME!='--'){
                    details = details + "<tr><td class='ajaxTd'>PARTNER NAME&nbsp;&nbsp;:</td><td class='ajaxTd'>"+PARTNER_NAME+"</td></tr>";
                       }
                   if(INVOICE!='--'){
                     details = details + "<tr><td class='ajaxTd'>INVOICE&nbsp;&nbsp;:</td><td class='ajaxTd'>"+INVOICE+"</td></tr>";
                         }
                     if(INVOICE_DATE!='--'){
                   details = details + "<tr><td class='ajaxTd'>INVOICE DATE&nbsp;&nbsp;:</td><td class='ajaxTd'>"+INVOICE_DATE+"</td></tr>";
                    
                     }    
                    if(SHIP_TO_NAME!='--'){
                      details = details + "<tr><td class='ajaxTd'>SHIP TO NAME&nbsp;&nbsp;:</td><td class='ajaxTd'>"+SHIP_TO_NAME+"</td></tr>";
                   }
                    if(SHIP_TO_CODE!='--'){
                      details = details + "<tr><td class='ajaxTd'>SHIP TO CODE&nbsp;&nbsp;:</td><td class='ajaxTd'>"+SHIP_TO_CODE+"</td></tr>";
                   }
                    if(SHIP_TO_CITY!='--'){
                    details = details + "<tr><td class='ajaxTd'>SHIP TO CITY&nbsp;&nbsp;:</td><td class='ajaxTd'>"+SHIP_TO_CITY+"</td></tr>";
                    }
                    if(SHIP_TO_POSTAL_CODE!='--'){
                      details = details + "<tr><td class='ajaxTd'>SHIP TO POSTAL CODE&nbsp;&nbsp;:</td><td class='ajaxTd'>"+SHIP_TO_POSTAL_CODE+"</td></tr>";
                     }
                     if(SHIP_TO_REGION!='--'){
                      details = details + "<tr><td class='ajaxTd'>SHIP TO REGION&nbsp;&nbsp;:</td><td class='ajaxTd'>"+SHIP_TO_REGION+"</td></tr>";
                     }
                     if(SALES_AREA!='--'){
                    details = details + "<tr><td class='ajaxTd'>SALES AREA&nbsp;&nbsp;:</td><td class='ajaxTd'>"+SALES_AREA+"</td></tr>";
                          }
                       if(ITEM_LINE_COUNT!='--'){
                  details = details + "<tr><td class='ajaxTd'>ITEM LINE COUNT&nbsp;&nbsp;:</td><td class='ajaxTd'>"+ITEM_LINE_COUNT+"</td></tr>";
                            }
                     if(INVOICE_TOTAL!='--'){
                     details = details + "<tr><td class='ajaxTd'>INVOICE TOTAL :</td><td class='ajaxTd'>"+INVOICE_TOTAL+"</td></tr>";
                          }
                          if(IDOC!='--'){
                     details = details + "<tr><td class='ajaxTd'>IDOC :</td><td class='ajaxTd'>"+IDOC+"</td></tr>";
                          }
                  }
                  
                  
                    if(TRANSACTION_TYPE=='820'){
                   if(PARTNER_NAME!='--'){
                    details = details + "<tr><td class='ajaxTd'>PARTNER NAME&nbsp;&nbsp;:</td><td class='ajaxTd'>"+PARTNER_NAME+"</td></tr>";
                       }
                   if(PAYMENT_METHOD!='--'){
                     details = details + "<tr><td class='ajaxTd'>PAYMENT METHOD &nbsp;&nbsp;:</td><td class='ajaxTd'>"+PAYMENT_METHOD +"</td></tr>";
                         }
                     if(CURRENCY!='--'){
                   details = details + "<tr><td class='ajaxTd'>CURRENCY&nbsp;&nbsp;:</td><td class='ajaxTd'>"+CURRENCY+"</td></tr>";
                    
                     }    
                    if(TRANSACTION_TRACE_CODE!='--'){
                      details = details + "<tr><td class='ajaxTd'>TRANSACTION TRACE CODE&nbsp;&nbsp;:</td><td class='ajaxTd'>"+TRANSACTION_TRACE_CODE+"</td></tr>";
                   }
                    if(CHEQUE_NUMBER !='--'){
                    details = details + "<tr><td class='ajaxTd'>CHEQUE NUMBER &nbsp;&nbsp;:</td><td class='ajaxTd'>"+CHEQUE_NUMBER +"</td></tr>";
                    }
                    if(PAYEE_VENDOR_ID !='--'){
                      details = details + "<tr><td class='ajaxTd'>PAYEE VENDOR ID &nbsp;&nbsp;:</td><td class='ajaxTd'>"+PAYEE_VENDOR_ID +"</td></tr>";
                     }
                     if(PAYEE_NAME !='--'){
                      details = details + "<tr><td class='ajaxTd'>PAYEE NAME &nbsp;&nbsp;:</td><td class='ajaxTd'>"+PAYEE_NAME +"</td></tr>";
                     }
                     if(MONETARY_AMOUNT !='--'){
                    details = details + "<tr><td class='ajaxTd'>MONETARY AMOUNT &nbsp;&nbsp;:</td><td class='ajaxTd'>"+MONETARY_AMOUNT +"</td></tr>";
                          }
                       if(FILE!='--'){
                  details = details + "<tr><td class='ajaxTd'>FILE&nbsp;&nbsp;:</td><td class='ajaxTd'>"+FILE+"</td></tr>";
                            }
                    
                  }  
                  
                   /* if(PRI_KEY_TYPE=='ASN'){
                   if(PARTNER_NAME!='--'){
                    details = details + "<tr><td class='ajaxTd'>PARTNER NAME&nbsp;&nbsp;:</td><td class='ajaxTd'>"+PARTNER_NAME+"</td></tr>";
                       }
                   if(PAYMENT_METHOD!='--'){
                     details = details + "<tr><td class='ajaxTd'>PAYMENT METHOD &nbsp;&nbsp;:</td><td class='ajaxTd'>"+PAYMENT_METHOD +"</td></tr>";
                         }
                     if(CURRENCY!='--'){
                   details = details + "<tr><td class='ajaxTd'>CURRENCY&nbsp;&nbsp;:</td><td class='ajaxTd'>"+CURRENCY+"</td></tr>";
                    
                     }    
                    if(TRANSACTION_TRACE_CODE!='--'){
                      details = details + "<tr><td class='ajaxTd'>TRANSACTION TRACE CODE&nbsp;&nbsp;:</td><td class='ajaxTd'>"+TRANSACTION_TRACE_CODE+"</td></tr>";
                   }
                   
                    if(PAYEE_VENDOR_ID !='--'){
                      details = details + "<tr><td class='ajaxTd'>PAYEE VENDOR ID &nbsp;&nbsp;:</td><td class='ajaxTd'>"+PAYEE_VENDOR_ID +"</td></tr>";
                     }
                     if(PAYEE_NAME !='--'){
                      details = details + "<tr><td class='ajaxTd'>PAYEE NAME &nbsp;&nbsp;:</td><td class='ajaxTd'>"+PAYEE_NAME +"</td></tr>";
                     }
                     if(MONETARY_AMOUNT !='--'){
                    details = details + "<tr><td class='ajaxTd'>MONETARY AMOUNT &nbsp;&nbsp;:</td><td class='ajaxTd'>"+MONETARY_AMOUNT +"</td></tr>";
                          }
                       if(IDOC!='--'){
                  details = details + "<tr><td class='ajaxTd'>IDOC&nbsp;&nbsp;:</td><td class='ajaxTd'>"+IDOC+"</td></tr>";
                            }
                  }  */
              // }
                     
            details = details + "</table>";
           //alert("details--->"+details);
      //  }
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
      var details = " <h5 >Sorry ! No Results Found</h5>";
                    
    }
     var detailsDIV=document.getElementById("detailInformation");
	detailsDIV.innerHTML="";
	detailsDIV.innerHTML=details;
     /* $(function() {
       
           $('#detail_box').show();
           return false;
               
   });*/
 
}



/**
 * Doc copying using Ajax call
 * 
 */
 function getProces(btnValue,list) {

     var po_Files = "";
     if((btnValue.value =='ReTransmit')){
       //  alert("ReTransmit-->"+list);
       var res;
         for(var j=0; j<list;j++) {
             if(list == 1){
                res = document.purchaseForm.check_List.checked;
                 //alert("if res---->"+res);
             }
             
             else{
                res = document.purchaseForm.check_List[j].checked;  
             }
             //alert("else res---->"+res);
             if(res == true){
                po_Files =  po_Files + document.getElementById("text"+j).value +"|";
                po_Files =  po_Files + document.getElementById("Instance"+j).value +"^";
                alert("po_Files--"+po_Files);
             }
         }  
         // alert("length-->"+po_Files);
         if((po_Files != "")&& (po_Files != null)) {
                    var r=confirm("Please confirm retransmission of the selected PO!");
                    if(r == true) {
                    getcopy(po_Files,"POST");  
                    }
                    else {
                    return false;
                    }
        }
        else {
            alert("Please select checkbox(s) before ReTransmit");
        }
     }
 
     if((btnValue.value == 'ReSubmit')){
        // alert("ReSubmit-->"+list);
          for(var j=0; j<list;j++) { 
              var res;
             if(list == 1){
                res = document.purchaseForm.check_List.checked;
             }else{
                res = document.purchaseForm.check_List[j].checked;  
             }
             if(res == true){
                 po_Files =  po_Files + document.getElementById("text"+j).value +"|";
                   po_Files =  po_Files + document.getElementById("Instance"+j).value +"^"; 
             }
         }
         
         //alert("length-->"+po_Files);
         if((po_Files != "")&& (po_Files != null)) {
                    var r=confirm("Please confirm resubmission of the selected PO!");
                    if(r == true) {
                    getcopy(po_Files,"PRE");
                    }else {
                    return false;
                    }
            }
            else {
            alert("Please select checkbox(s) before ReSubmit");
            }
      }            
 }
 
   function populateDocCopy(responseText) {
     alert(responseText);
     document.getElementById("purchaseForm").submit();
 }
 

  function getcopy(PO_LIST,type){
  //var req = new XMLHttpRequest();
  var req = getXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerString(req,populateDocCopy); 
    var url="../ajax/getDocCopy.action?poList="+PO_LIST+"&type="+type;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
 }

 function readyStateHandlerString(req,responseTextHandler){
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {               
                responseTextHandler(req.responseText);
            } else {
                alert("HTTP error"+req.status+" : "+req.statusText);
            }
        }
    }
}
/**
 * Shipment copying using Ajax call
 * 
 */

 function getProcesAsn(btnValue,list) {
  // alert("hi");
     var asn_Files = "";
     if((btnValue.value =='ReTransmit')){
        // alert("ReTransmit-->"+list);
       var res;
      // alert(document.shipmentForm.check_List[0]);
         for(var j=0; j<list;j++) {
             if(list == 1){
                res = document.shipmentForm.check_List.checked;
             }else{
                res = document.shipmentForm.check_List[j].checked;  
             }
             //alert("res---->"+res);
             if(res == true){
                asn_Files =  asn_Files + document.getElementById("text"+j).value +"|";
                asn_Files =  asn_Files + document.getElementById("Instance"+j).value +"^";
                alert("po_Files--"+asn_Files);
             }
         }  
        //alert("length-->"+asn_Files);
         if((asn_Files != "")&& (asn_Files != null)) {
                    var r=confirm("Please confirm retransmission of the selected ASN!");
                    if(r == true) {
                    getAsncopy(asn_Files,"POST");  
                    }
                    else {
                    return false;
                    }
        }
        else {
            alert("Please select checkbox(s) before ReTransmit");
        }
     }
 
     if((btnValue.value == 'ReSubmit')){
        // alert("ReSubmit-->"+list);
          for(var j=0; j<list;j++) {
              
              var res;
             if(list == 1){
                res = document.shipmentForm.check_List.checked;
             }else{
                res = document.shipmentForm.check_List[j].checked;  
             }
             if(res == true){
                 asn_Files =  asn_Files + document.getElementById("text"+j).value +"|";
                   asn_Files =  asn_Files + document.getElementById("Instance"+j).value +"^"; 
             }
         }
         
        // alert("length-->"+asn_Files);
         if((asn_Files != "")&& (asn_Files != null)) {
                    var r=confirm("Please confirm resubmission of the selected ASN!");
                    if(r == true) {
                    getAsncopy(asn_Files,"PRE");
                    }else {
                    return false;
                    }
            }
            else {
            alert("Please select checkbox(s) before ReSubmit");
            }
      }            
 }
 
  function getAsncopy(ASN_LIST,type){
  //var req = new XMLHttpRequest();
  var req = getXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerString(req,populateDocAsnCopy); 
    var url="../ajax/getDocASNCopy.action?asnList="+ASN_LIST+"&type="+type;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
 }
 
   function populateDocAsnCopy(responseText) {
     alert(responseText);
     document.getElementById("shipmentForm").submit();
 }



/**
 * Invices copying using Ajax call
 * 
 */



 function getProcesInv(btnValue,list) {
  // alert("hi");
     var inv_Files = "";
     if((btnValue.value =='ReTransmit')){
        // alert("ReTransmit-->"+list);
       var res;
      // alert(document.shipmentForm.check_List[0]);
         for(var j=0; j<list;j++) {
             if(list == 1){
                res = document.invoiceForm.check_List.checked;
             }else{
                res = document.invoiceForm.check_List[j].checked;  
             }
             //alert("res---->"+res);
             if(res == true){
                inv_Files =  inv_Files + document.getElementById("text"+j).value +"|";
                inv_Files =  inv_Files + document.getElementById("Instance"+j).value +"^";
                alert("po_Files--"+inv_Files);
             }
         }  
        //alert("length-->"+asn_Files);
         if((inv_Files != "")&& (inv_Files != null)) {
                    var r=confirm("Please confirm retransmission of the selected INVOICE!");
                    if(r == true) {
                    getInvcopy(inv_Files,"POST");  
                    }else {
                    return false;
                    }
        }
        else {
            alert("Please select checkbox(s) before ReTransmit");
        }
     }
 
     if((btnValue.value == 'ReSubmit')){
        // alert("ReSubmit-->"+list);
          for(var j=0; j<list;j++) {
              
              var res;
             if(list == 1){
                res = document.invoiceForm.check_List.checked;
             }else{
                res = document.invoiceForm.check_List[j].checked;  
             }
             if(res == true){
                 inv_Files =  inv_Files + document.getElementById("text"+j).value +"|";
                   inv_Files =  inv_Files + document.getElementById("Instance"+j).value +"^"; 
             }
         }
         
        // alert("length-->"+asn_Files);
         if((inv_Files != "")&& (inv_Files != null)) {
                    var r=confirm("Please confirm resubmission of the selected INVOICE!");
                    if(r == true) {
                    getInvcopy(inv_Files,"PRE");
                    }else {
                    return false;
                    }
            }
            else {
            alert("Please select checkbox(s) before ReSubmit");
            }
      }            
 }
   function getInvcopy(INV_LIST,type){
  //var req = new XMLHttpRequest();
  var req = getXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerString(req,populateDocInvCopy); 
    var url="../ajax/getInvCopy.action?invList="+INV_LIST+"&type="+type;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
 }
 
   function populateDocInvCopy(responseText) {
     alert(responseText);
     document.getElementById("invoiceForm").submit();
 }




 function getProcesPayment(btnValue,list) {
  // alert("hi");
     var payment_Files = "";
     if((btnValue.value =='ReTransmit')){
        // alert("ReTransmit-->"+list);
       var res;
      // alert(document.shipmentForm.check_List[0]);
         for(var j=0; j<list;j++) {
             if(list == 1){
                res = document.paymentForm.check_List.checked;
             }else{
                res = document.paymentForm.check_List[j].checked;  
             }
             //alert("res---->"+res);
             if(res == true){
                payment_Files =  payment_Files + document.getElementById("text"+j).value +"|";
                payment_Files =  payment_Files + document.getElementById("Instance"+j).value +"^";
                alert("po_Files--"+payment_Files);
             }
         }  
        //alert("length-->"+asn_Files);
         if((payment_Files != "")&& (payment_Files != null)) {
                    var r=confirm("Please confirm retransmission of the selected PAYMENT!");
                    if(r == true) {
                    getPaymentcopy(payment_Files,"POST");  
                    }
                    else {
                    return false;
                    }
        }
        else {
            alert("Please select checkbox(s) before ReTransmit");
        }
     }
 
     if((btnValue.value == 'ReSubmit')){
        // alert("ReSubmit-->"+list);
          for(var j=0; j<list;j++) {
              
              var res;
             if(list == 1){
                res = document.paymentForm.check_List.checked;
             }else{
                res = document.paymentForm.check_List[j].checked;  
             }
             if(res == true){
                 payment_Files =  payment_Files + document.getElementById("text"+j).value +"|";
                   payment_Files =  payment_Files + document.getElementById("Instance"+j).value +"^"; 
             }
         }
         
        // alert("length-->"+asn_Files);
         if((payment_Files != "")&& (payment_Files != null)) {
                    var r=confirm("Please confirm resubmission of the selected PAYMENT!");
                    if(r == true) {
                    getPaymentcopy(payment_Files,"PRE");
                    }else {
                    return false;
                    }
            }
            else {
            alert("Please select checkbox(s) before ReSubmit");
            }
      }            
 }
   function getPaymentcopy(Payment_LIST,type){
  //var req = new XMLHttpRequest();
  var req = getXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerString(req,populateDocPaymentCopy); 
    var url="../ajax/getPaymentCopy.action?paymentList="+Payment_LIST+"&type="+type;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
 }
 
   function populateDocPaymentCopy(responseText) {
     alert(responseText);
     document.getElementById("paymentForm").submit();
 }
/**
 * LoadTender copying using Ajax call
 * 
 */
 function getloadTenderProcess(btnValue,list) {

     var load_Files = "";
     if((btnValue.value =='ReTransmit')){
       //  alert("ReTransmit-->"+list);
       var res;
         for(var j=0; j<list;j++) {
             if(list == 1){
                res = document.logisticsForm.check_List.checked;
             }else{
                res = document.logisticsForm.check_List[j].checked;  
             }
            // alert("res---->"+res);
             if(res == true){
                load_Files =  load_Files + document.getElementById("text"+j).value +"|";
                load_Files =  load_Files + document.getElementById("Instance"+j).value +"^";
                alert("load_Files"+load_Files);
             }
         }  
         // alert("length-->"+po_Files);
         if((load_Files != "")&& (load_Files != null)) {
                    var r=confirm("Please confirm retransmission of the selected Load Files!");
                    if(r == true) {
                    getLoadTenderCopy(load_Files,"POST");  
                    }
                    else {
                    return false;
                    }
        }
        else {
            alert("Please select checkbox(s) before ReTransmit");
        }
     }
 
     if((btnValue.value == 'ReSubmit')){
        // alert("ReSubmit-->"+list);
          for(var j=0; j<list;j++) { 
              var res;
             if(list == 1){
                res = document.logisticsForm.check_List.checked;
             }else{
                res = document.logisticsForm.check_List[j].checked;  
             }
             if(res == true){
                 load_Files =  load_Files + document.getElementById("text"+j).value +"|";
                   load_Files =  load_Files + document.getElementById("Instance"+j).value +"^"; 
             }
         }
         
         //alert("length-->"+po_Files);
         if((load_Files != "")&& (load_Files != null)) {
                    var r=confirm("Please confirm resubmission of the selected Load Files!");
                    if(r == true) {
                    getLoadTenderCopy(load_Files,"PRE");
                    }else {
                    return false;
                    }
            }
            else {
            alert("Please select checkbox(s) before ReSubmit");
            }
      }            
 }
 
 function getLoadTenderCopy(Load_LIST,type){
  //var req = new XMLHttpRequest();
  var req = getXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerString(req,populateLoadTenderCopy); 
    var url="../ajax/getLoadCopy.action?loadList="+Load_LIST+"&type="+type;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
 }
  function populateLoadTenderCopy(responseText) {
     alert(responseText);
     document.getElementById("logisticsForm").submit();
 }
/*
 *Get Payment Details
 */
function getPaymentDetails(fileId) {
    $(function() {
       
           $('#detail_box').show();
           return false;
               
   });
    var req = getXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populatePaymentDetails); 

    var url="../ajax/getPaymentDetails.action?fileId="+fileId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populatePaymentDetails(responseXML)
{
     var details = responseXML.getElementsByTagName("DETAILS")[0];
     
    // alert("datails--->"+details);
     var detail = details.childNodes[0];
    var chk=detail.getElementsByTagName("VALID")[0];  
    
     if(chk.childNodes[0].nodeValue =="true") {
    
            var detail = details.childNodes[0];
            
            var FILE_ID = detail.getElementsByTagName("FILE_ID")[0].childNodes[0].nodeValue; 
           // var Check_Number = detail.getElementsByTagName("Check_Number")[0].childNodes[0].nodeValue; 
            var SENDER_ID = detail.getElementsByTagName("SENDER_ID")[0].childNodes[0].nodeValue;
            var RECEIVER_ID = detail.getElementsByTagName("RECEIVER_ID")[0].childNodes[0].nodeValue; 
            
            var SENDER_NAME = detail.getElementsByTagName("SENDER_NAME")[0].childNodes[0].nodeValue;
            var RECEIVER_NAME = detail.getElementsByTagName("RECEIVER_NAME")[0].childNodes[0].nodeValue;
            
            var STATUS = detail.getElementsByTagName("STATUS")[0].childNodes[0].nodeValue;   
            
            var PRE_TRANS_FILEPATH = detail.getElementsByTagName("PRETRANSFILEPATH")[0].childNodes[0].nodeValue;
            var POST_TRANS_FILEPATH = detail.getElementsByTagName("POSTTRANSFILEPATH")[0].childNodes[0].nodeValue; 
            var ORGFILEPATH = detail.getElementsByTagName("ORGFILEPATH")[0].childNodes[0].nodeValue;
            var ACKFILE = detail.getElementsByTagName("ACKFILE")[0].childNodes[0].nodeValue;
            var ERRMESSAGE = detail.getElementsByTagName("ERR_MESSAGE")[0].childNodes[0].nodeValue;
            var Check_Number = detail.getElementsByTagName("Check_Number")[0].childNodes[0].nodeValue; 
             
             var ISA_NUMBER = detail.getElementsByTagName("ISA_NUMBER")[0].childNodes[0].nodeValue; 
             var ISA_DATE = detail.getElementsByTagName("ISA_DATE")[0].childNodes[0].nodeValue; 
             var ISA_TIME = detail.getElementsByTagName("ISA_TIME")[0].childNodes[0].nodeValue; 
             var GS_CONTROL_NUMBER = detail.getElementsByTagName("GS_CONTROL_NUMBER")[0].childNodes[0].nodeValue; 
             var ST_CONTROL_NUMBER = detail.getElementsByTagName("ST_CONTROL_NUMBER")[0].childNodes[0].nodeValue; 
             var TRANSACTION_TYPE = detail.getElementsByTagName("TRANSACTION_TYPE")[0].childNodes[0].nodeValue; 
             var SEC_KEY_VAL = detail.getElementsByTagName("SEC_KEY_VAL")[0].childNodes[0].nodeValue; 
             var INVOICE_NUMBER = detail.getElementsByTagName("INVOICE_NUMBER")[0].childNodes[0].nodeValue; 
             var FILETYPE = detail.getElementsByTagName("FILE_TYPE")[0].childNodes[0].nodeValue;  
           // Sap Detals
               
                var SAP_DETAILS = detail.getElementsByTagName("SAP_DETAILS")[0].childNodes[0].nodeValue;
                var SAP_USER ;
                var IDOC_NUMBER ;
                var PO_NUMBER ;
                var PO_DATE ;
                var IDOC_STATUS_CODE ;
                var IDOC_STATUS_DESCRIPTION ;
                if(SAP_DETAILS!='NO'){
                    
                        SAP_USER = detail.getElementsByTagName("SAP_USER")[0].childNodes[0].nodeValue;
               IDOC_NUMBER = detail.getElementsByTagName("IDOC_NUMBER")[0].childNodes[0].nodeValue;
                PO_NUMBER = detail.getElementsByTagName("PO_NUMBER")[0].childNodes[0].nodeValue;
                 PO_DATE = detail.getElementsByTagName("PO_DATE")[0].childNodes[0].nodeValue;
                  IDOC_STATUS_CODE = detail.getElementsByTagName("IDOC_STATUS_CODE")[0].childNodes[0].nodeValue;
                   IDOC_STATUS_DESCRIPTION = detail.getElementsByTagName("IDOC_STATUS_DESCRIPTION")[0].childNodes[0].nodeValue;
			
                }
                                
           // var routings = detail.getElementsByTagName("ROUTINGS")[0].childNodes[0].nodeValue;  
           
           var details = " <table style='margin: 0 0 0 0;padding: 0px 0px;'><tr><td class='ajaxTd'>Instance Id  :&nbsp; &nbsp;</td><td class='ajaxTd'> "+FILE_ID+"</td></tr>";
           //alert(Check_Number);
               if(Check_Number != "NO"){
                   details = details + "<tr><td class='ajaxTd'>Cheque # :&nbsp; &nbsp;</td><td class='ajaxTd'>"+Check_Number+"</td></tr>";
               }else{
                   details = details + "<tr><td class='ajaxTd'>Cheque # :&nbsp; &nbsp;</td><td class='ajaxTd'>-- </td></tr>";
               }
                  details = details + "<tr><td class='ajaxTd'>PO # :&nbsp; &nbsp;</td><td class='ajaxTd'>"+SEC_KEY_VAL+"</td></tr>"
                                    + "<tr><td class='ajaxTd'>Invoice # :&nbsp; &nbsp;</td><td class='ajaxTd'>"+INVOICE_NUMBER+"</td></tr>"
                                    +"<tr><td class='ajaxTd'>Document&nbsp;Type&nbsp;:</td><td class='ajaxTd'>"+FILETYPE+"</td></tr>"
                                    +"<tr><td class='ajaxTd'>Transaction&nbsp;Type :&nbsp; &nbsp;</td><td class='ajaxTd'>"+TRANSACTION_TYPE+"</td></tr>";
                  
               /* details = details + "<tr><td class='ajaxTd'>SENDER_ID :&nbsp; &nbsp;</td><td class='ajaxTd'>"+SENDER_ID+"</td></tr>"+
                "<tr><td class='ajaxTd'>RECEIVER_ID :&nbsp; &nbsp;</td><td class='ajaxTd'>"+RECEIVER_ID+"</td></tr>";*/
            
            details = details + "<tr><td class='ajaxTd'><b>Sender Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                       details = details + "<tr><td class='ajaxTd'> Id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+SENDER_ID+"</td></tr>";
                       details = details + "<tr><td class='ajaxTd'> Name&nbsp;:&nbsp;</td><td class='ajaxTd'>"+SENDER_NAME+"</td></tr>";
               
                       details = details + "<tr><td class='ajaxTd'><b>Receiver Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
               details = details + "<tr><td class='ajaxTd'>Id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+RECEIVER_ID+"</td></tr>";
               details = details + "<tr><td class='ajaxTd'> Name&nbsp;:&nbsp;</td><td class='ajaxTd'>"+RECEIVER_NAME+"</td></tr>";
             
                          details = details + "<tr><td class='ajaxTd'>ISA #:&nbsp; &nbsp;</td><td class='ajaxTd'>"+ISA_NUMBER+"</td></tr>"
                                    
                                    + "<tr><td class='ajaxTd'>GS #:&nbsp; &nbsp;</td><td class='ajaxTd'>"+GS_CONTROL_NUMBER+"</td></tr>"
                                    +"<tr><td class='ajaxTd'>ST #:&nbsp; &nbsp;</td><td class='ajaxTd'>"+ST_CONTROL_NUMBER+"</td></tr>";
                                details = details +"<tr><td class='ajaxTd'>ISA Date:&nbsp; &nbsp;</td><td class='ajaxTd'>"+ISA_DATE+"</td></tr>"
                                    +"<tr><td class='ajaxTd'>ISA Time:&nbsp; &nbsp;</td><td class='ajaxTd'>"+ISA_TIME+"</td></tr>"
               
            if(STATUS.toUpperCase() == "ERROR"){
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp;</td><td class='ajaxTd'><font color='red'>"+STATUS.toUpperCase()+"</font></td></tr>";
            }else if(STATUS.toUpperCase() == "SUCCESS"){
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp; </td><td class='ajaxTd'><font color='green'>"+STATUS.toUpperCase()+"</font></td></tr>";
            }else {
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp; </td><td class='ajaxTd'><font color='orange'>"+STATUS.toUpperCase()+"</font></td></tr>";
            }
            
        /*    if(ORGFILEPATH == "No File"){
               details = details + "<tr><td class='ajaxTd'>Original File&nbsp;:</td><td> -- </td class='ajaxTd'></tr>";
            }else{
               details = details + "<tr><td class='ajaxTd'>Original File&nbsp;:</td><td class='ajaxTd'><a href=\"../download/getAttachment.action?locationAvailable="+ORGFILEPATH+"\">Download</a></td></tr>";
            }*/
            
            if(PRE_TRANS_FILEPATH == "No File"){
               details = details + "<tr><td class='ajaxTd'>Pre-Translation&nbsp;:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
               details = details + "<tr><td class='ajaxTd'>Pre-Translation&nbsp;:</td><td class='ajaxTd'><a href=\"../download/getAttachment.action?locationAvailable="+POST_TRANS_FILEPATH+"\">Download</a></td></tr>";
            }
            
            if(POST_TRANS_FILEPATH == "No File"){
                details = details + "<tr><td class='ajaxTd'>Post-Translation&nbsp;:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
                details = details + "<tr><td class='ajaxTd'>Post-Translation&nbsp;:</td><td class='ajaxTd'><a href=\"../download/getAttachment.action?locationAvailable="+POST_TRANS_FILEPATH+"\">Download</a></td></tr>";
            }
            
            if(ACKFILE == "No File"){
                details = details + "<tr><td class='ajaxTd'>997&nbsp;Ack&nbsp;File&nbsp;:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
                details = details + "<tr><td class='ajaxTd'>997&nbsp;Ack&nbsp;File&nbsp;:</td><td class='ajaxTd'><a href=\"../download/getAttachment.action?locationAvailable="+ACKFILE+"\">Download</a></td></tr>";
            }
          
            
            //alert(ERRMESSAGE);
            if(ERRMESSAGE != "NO MSG"){
               
                details = details + "<tr><td class='ajaxTd'>Error&nbsp;Message&nbsp;:&nbsp; &nbsp;</td><td class='ajaxTd'><font color='red'>"+ERRMESSAGE+"</font></td></tr>"
          
            }
               // "<h5 >PRE_TRANS_FILE :&nbsp; &nbsp; <a href=\"../download/getAttachment.action?locationAvailable="+PRE_TRANS_FILEPATH+"\">PRE_TRANS_FILE</a></h5>"+
              //  "<h5 >POST_TRANS_FILE :&nbsp; &nbsp; <a href=\"../download/getAttachment.action?locationAvailable="+POST_TRANS_FILEPATH+"\">POST_TRANS_FILE</a></h5>";
                if(SAP_DETAILS!='NO'){
				  details = details + "<tr><td class='ajaxTd'>SAP_USER&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+SAP_USER+"</td></tr>"+
                      "<tr><td class='ajaxTd'>IDOC&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+IDOC_NUMBER+"</td></tr>";
                    details = details + "<tr><td class='ajaxTd'>PO&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+PO_NUMBER+"</td></tr>"+
                      "<tr><td class='ajaxTd'>PO_DATE&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+PO_DATE+"</td></tr>";
                    details = details + "<tr><td class='ajaxTd' colspan=2><b>IDOC&nbsp;STATUS:<b></td></tr>";
                    details = details + "<tr><td class='ajaxTd'>CODE&nbsp;# &nbsp;:</td><td class='ajaxTd'>"+IDOC_STATUS_CODE+"</td></tr>"+
                      "<tr><td class='ajaxTd'>DESCRIPTION&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+IDOC_STATUS_DESCRIPTION+"</td></tr>";
                     }
                     
                  details = details + "</table>";   
               
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
      var details = " <h5 >Sorry ! No Results Found</h5>";
                    
    }
     var detailsDIV=document.getElementById("detailInformation");
	detailsDIV.innerHTML="";
	detailsDIV.innerHTML=details;
     /* $(function() {
       
           $('#detail_box').show();
           return false;
               
   });*/
 
}


 


 
 /** 
  * New 
  * To add and update TP
  */
 //for editing TP Details
 
function doEdit(id) {
  var id=id;
    var req = getXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText1(req,populateTpDetails); 

    var url="../ajax/getTpDetails.action?tpId="+id;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
 
 function populateTpDetails(responseXML)
 {
     
      var details = responseXML.getElementsByTagName("DETAILS")[0];
     
    // alert("datails--->"+details);
     var detail = details.childNodes[0];
    var chk=detail.getElementsByTagName("VALID")[0];  
    
     if(chk.childNodes[0].nodeValue =="true") {
    
            var detail = details.childNodes[0];
            
            var ID = detail.getElementsByTagName("ID")[0].childNodes[0].nodeValue; 
           // var Check_Number = detail.getElementsByTagName("Check_Number")[0].childNodes[0].nodeValue; 
            var NAME = detail.getElementsByTagName("NAME")[0].childNodes[0].nodeValue;
            var CONTACT_INFO = detail.getElementsByTagName("CONTACT_INFO")[0].childNodes[0].nodeValue; 
            
            var VENDOR_NUMBER = detail.getElementsByTagName("VENDOR_NUMBER")[0].childNodes[0].nodeValue;   
            
            var DEPARTMENTS = detail.getElementsByTagName("DEPARTMENTS")[0].childNodes[0].nodeValue;
            var EDI_COMM_ID = detail.getElementsByTagName("EDI_COMM_ID")[0].childNodes[0].nodeValue; 
            var QUALIFIER = detail.getElementsByTagName("QUALIFIER")[0].childNodes[0].nodeValue;
            
            document.getElementById('id').value=ID;
            document.getElementById('id').disabled = true;
            document.getElementById('name').value=NAME;
            document.getElementById('contact').value=CONTACT_INFO;
            document.getElementById('phno').value=VENDOR_NUMBER;
            document.getElementById('dept').value=DEPARTMENTS;
            document.getElementById('commid').value=EDI_COMM_ID;
            document.getElementById('qualifier').value=QUALIFIER;
   document.getElementById("add").style.display = 'none';
   document.getElementById("update").style.display = 'table-row';
            
     }     
 }
 function doTpUpdate()
 {
   //  alert("do up");
     var id = document.getElementById("id").value;
    var name = document.getElementById("name").value;
    if(((id!=null)&&(id!=""))&&(name!=null)&&(name!=""))
        {
     //  alert("in do Update");
       
    var id = document.getElementById('id').value;
    document.getElementById('id').disabled = false;
    var name=document.getElementById('name').value ;
    var contact=document.getElementById('contact').value ;
    var phno = document.getElementById('phno').value ;
    var dept=document.getElementById('dept').value ;
    var commid=document.getElementById('commid').value ;
    var qualifier=document.getElementById('qualifier').value ;
       
     var req = getXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerString(req,showResultForUpdate); 

    var url="../ajax/updateTpDetails.action?tpId="+id+"&name="+name+"&contact="+contact+"&phno="+phno+"&dept="+dept+"&commid="+commid+"&qualifier="+qualifier;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);  
       
        }
    else
        { 
             alert("Please enter atleast Id and Name!!!");
        }           
 }
 
 function showResultForUpdate(result)
 {
     alert(result);
     document.getElementById('id').value="";
            document.getElementById('id').value = "";
            document.getElementById('name').value="";
            document.getElementById('contact').value="";
            document.getElementById('phno').value="";
            document.getElementById('dept').value="";
            document.getElementById('commid').value="";
            document.getElementById('qualifier').value="";
   document.getElementById("add").style.display = 'table-row';
   document.getElementById("update").style.display = 'none';
     
 }



function getTpDetailInformation(tpId)
{
     $(function() {
       
           $('#detail_box').show();
           return false;
               
   });
   var tpname = name;
    var req = getXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populateTpDetailInformation); 

    var url="../ajax/getTpDetailInformation.action?tpId="+tpId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}

function populateTpDetailInformation(responseXML)
{
     var details = responseXML.getElementsByTagName("DETAILS")[0];
     
    // alert("datails--->"+details);
     var detail = details.childNodes[0];
    var chk=detail.getElementsByTagName("VALID")[0];  
    
     if(chk.childNodes[0].nodeValue =="true") {
    
            var detail = details.childNodes[0];
            var id = detail.getElementsByTagName("ID")[0].childNodes[0].nodeValue; 
            var city = detail.getElementsByTagName("CITY")[0].childNodes[0].nodeValue; 
            var zip = detail.getElementsByTagName("ZIP")[0].childNodes[0].nodeValue; 
            
            //added 
            var vendor = detail.getElementsByTagName("VENDOR")[0].childNodes[0].nodeValue; 
            
            //var vendor = detail.getElementByTagName("VENDOR")[0].childNodes[0].nodeValue;
            var department = detail.getElementsByTagName("DEPARTMENT")[0].childNodes[0].nodeValue;
            
            var ship = detail.getElementsByTagName("SHIP")[0].childNodes[0].nodeValue;
            var payDuns = detail.getElementsByTagName("PAY_DUNS")[0].childNodes[0].nodeValue;
            var order = detail.getElementsByTagName("ORDER")[0].childNodes[0].nodeValue;
            var url = detail.getElementsByTagName("URL")[0].childNodes[0].nodeValue;
          //  var cert = detail.getElementsByTagName("CERT")[0].childNodes[0].nodeValue;
            
             //alert("ACKFILEID-->"+ACKFILEID);
           // var routings = detail.getElementsByTagName("ROUTINGS")[0].childNodes[0].nodeValue;  
           
           var details="<table style='margin: 0 0 0 0;padding: 0px 0px;'> ";
	            if(id != "NO"){
                    details = details + "<tr><td class='ajaxTd'>ID&nbsp;# :</td><td class='ajaxTd'>"+id+"</td></tr>";
                    }
                    else
                        {
                            details = details + "<tr><td class='ajaxTd'>ID&nbsp;# :</td><td class='ajaxTd'>--</td></tr>";
                        }
		    if(city != "NO"){
                    details = details + "<tr><td class='ajaxTd'>City&nbsp; :</td><td class='ajaxTd'>"+city+"</td></tr>";
					}
                    else
                        {
                                details = details + "<tr><td class='ajaxTd'>City&nbsp; :</td><td class='ajaxTd'>--</td></tr>";   
                        }
                     if(zip != "NO"){
                    details = details + "<tr><td class='ajaxTd'>Zip&nbsp; :</td><td class='ajaxTd'>"+zip+"</td></tr>";
					}
                    else
                        {
                                details = details + "<tr><td class='ajaxTd'>Zip&nbsp; :</td><td class='ajaxTd'>--</td></tr>";   
                        }   
              
                        
                 if(vendor != "NO"){
                    details = details + "<tr><td class='ajaxTd'>Vendor&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+vendor+"</td></tr>";
					}
                    else
                        {
                                details = details + "<tr><td class='ajaxTd'>Vendor&nbsp;Number :</td><td class='ajaxTd'>--</td></tr>";   
                        }         
                 
                   if(department != "NO"){
                    details = details + "<tr><td class='ajaxTd'>Department&nbsp; :</td><td class='ajaxTd'>"+department+"</td></tr>";
					}
                    else
                        {
                                details = details + "<tr><td class='ajaxTd'>Department&nbsp;Number&nbsp; :</td><td class='ajaxTd'>--</td></tr>";   
                        } 
                        
                         
                    
                    
                        if(ship != "NO"){
                    details = details + "<tr><td class='ajaxTd'>Ship&nbsp;Duns&nbsp; :</td><td class='ajaxTd'>"+ship+"</td></tr>";
					}
                    else
                        {
                                details = details + "<tr><td class='ajaxTd'>Ship&nbsp;Duns&nbsp; :</td><td class='ajaxTd'>--</td></tr>";   
                        }  
                    if(payDuns != "NO"){
                    details = details + "<tr><td class='ajaxTd'>Pay&nbsp;Duns&nbsp; :</td><td class='ajaxTd'>"+payDuns+"</td></tr>";
					}
                    else
                        {
                                details = details + "<tr><td class='ajaxTd'>Billings&nbsp;Duns&nbsp; :</td><td class='ajaxTd'>--</td></tr>";   
                        }      
                        
                       if(order != "NO"){
                    details = details + "<tr><td class='ajaxTd'>Order&nbsp;Duns&nbsp; :</td><td class='ajaxTd'>"+order+"</td></tr>";
					}
                    else
                        {
                                details = details + "<tr><td class='ajaxTd'>Order&nbsp;Duns :</td><td class='ajaxTd'>--</td></tr>";   
                        }    
           
                           if(url != "NO"){
                    details = details + "<tr><td class='ajaxTd'>URL&nbsp; :</td><td class='ajaxTd'>"+url+"</td></tr>";
					}
                    else
                        {
                                details = details + "<tr><td class='ajaxTd'>URL&nbsp; :</td><td class='ajaxTd'>--</td></tr>";   
                        } 
            
                     
            
          
            
               // "<h5 >PRE_TRANS_FILE :&nbsp; &nbsp; <a href=\"../download/getAttachment.action?locationAvailable="+PRE_TRANS_FILEPATH+"\">PRE_TRANS_FILE</a></h5>"+
              //  "<h5 >POST_TRANS_FILE :&nbsp; &nbsp; <a href=\"../download/getAttachment.action?locationAvailable="+POST_TRANS_FILEPATH+"\">POST_TRANS_FILE</a></h5>";
               
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
      var details = " <h5 >Sorry ! No Results Found</h5>";
                    
    }
     var detailsDIV=document.getElementById("detailInformation");
	detailsDIV.innerHTML="";
	detailsDIV.innerHTML=details;
    /*  $(function() {
       
           $('#detail_box').show();
           return false;
               
   });*/
 
}

function getLogisticsDocDetails(number,id,db){
    
    var num=number;
    var id = id;
    alert(db);
    $(function() {
       
           $('#detail_box').show();
           return false;
               
   });
   var req = getXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populateLogisticsDocDetails); 
   var url="../ajax/getLogisticsDocDetails.action?isaNumber="+num+"&id="+id+"&database="+db;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateLogisticsDocDetails(responseXML)
{
    var details = responseXML.getElementsByTagName("DETAILS")[0];
    var detail = details.childNodes[0];
    var chk=detail.getElementsByTagName("VALID")[0];  
    
     if(chk.childNodes[0].nodeValue =="true") {
    
            var detail = details.childNodes[0];
            var fileid = detail.getElementsByTagName("FILEID")[0].childNodes[0].nodeValue;   
            var docType = detail.getElementsByTagName("FILETYPE")[0].childNodes[0].nodeValue; 
            
            var PRE_TRANS_FILEPATH = detail.getElementsByTagName("PRETRANSFILEPATH")[0].childNodes[0].nodeValue;
            var POST_TRANS_FILEPATH = detail.getElementsByTagName("POSTTRANSFILEPATH")[0].childNodes[0].nodeValue; 
            
            var SENDER_ID = detail.getElementsByTagName("SENDERID")[0].childNodes[0].nodeValue;
            var RECEIVER_ID = detail.getElementsByTagName("RECEIVERID")[0].childNodes[0].nodeValue; 
             var SENDER_NAME = detail.getElementsByTagName("SENDER_NAME")[0].childNodes[0].nodeValue;
            var RECEIVER_NAME = detail.getElementsByTagName("RECEIVER_NAME")[0].childNodes[0].nodeValue;
            
            var ISA_DATE = detail.getElementsByTagName("ISA_DATE")[0].childNodes[0].nodeValue;
            
            var ISA_TIME = detail.getElementsByTagName("ISA_TIME")[0].childNodes[0].nodeValue;
            
            var ISA_NUMBER = detail.getElementsByTagName("ISA_NUMBER")[0].childNodes[0].nodeValue; 
            var GS_CONTROL_NUMBER = detail.getElementsByTagName("GS_CONTROL_NUMBER")[0].childNodes[0].nodeValue; 
            var ST_CONTROL_NUMBER = detail.getElementsByTagName("ST_CONTROL_NUMBER")[0].childNodes[0].nodeValue; 
            var TRAN_NUMBER = detail.getElementsByTagName("TRANSACTION_TYPE")[0].childNodes[0].nodeValue; 
            var SEC_KEY_VAL = detail.getElementsByTagName("SEC_KEY_VAL")[0].childNodes[0].nodeValue; 
            var PRI_KEY_TYPE = detail.getElementsByTagName("PRI_KEY_TYPE")[0].childNodes[0].nodeValue; 
            var PRI_KEY_VAL = detail.getElementsByTagName("PRI_KEY_VAL")[0].childNodes[0].nodeValue; 
            var ORG_FILEPATH = detail.getElementsByTagName("ORG_FILEPATH")[0].childNodes[0].nodeValue; 
            var ACKFILEID = detail.getElementsByTagName("ACKFILEID")[0].childNodes[0].nodeValue;
           //  var ISA_DATE = detail.getElementsByTagName("ISA_DATE")[0].childNodes[0].nodeValue;
            //  var ISA_TIME = detail.getElementsByTagName("ISA_TIME")[0].childNodes[0].nodeValue;
               var STATUS = detail.getElementsByTagName("STATUS")[0].childNodes[0].nodeValue;
               var DIRECTION = detail.getElementsByTagName("DIRECTION")[0].childNodes[0].nodeValue;
           
            var ERRMESSAGE = detail.getElementsByTagName("ERR_MESSAGE")[0].childNodes[0].nodeValue;
            
            var BOL_NUMBER = detail.getElementsByTagName("BOL_NUMBER")[0].childNodes[0].nodeValue;
            var CO_NUMBER = detail.getElementsByTagName("CO_NUMBER")[0].childNodes[0].nodeValue;
            var PO_NUMBER = detail.getElementsByTagName("PO_NUMBER")[0].childNodes[0].nodeValue;
            
            var TMW_SENDERID = detail.getElementsByTagName("TMW_SENDERID")[0].childNodes[0].nodeValue;
            var TMW_RECEIVERID = detail.getElementsByTagName("TMW_RECEIVERID")[0].childNodes[0].nodeValue;
          
          var details = " <table style='margin: 0 0 0 0;padding: 0px 0px;'><tr><td class='ajaxTd'>Instance Id # :&nbsp; &nbsp; </td><td class='ajaxTd'>"+fileid+"</td></tr>";
                      // " <tr><td class='ajaxTd'>Shipment # : </td><td class='ajaxTd'>"+SEC_KEY_VAL+"</td></tr>";
                  
                  // alert(PRI_KEY_TYPE);
                  details = details + " <tr><td class='ajaxTd'>Shipment # : </td><td class='ajaxTd'>"+PRI_KEY_VAL+"</td></tr>";
                   
                   /*if(PRI_KEY_TYPE != "SID"){
                   details = details + " <tr><td class='ajaxTd'>Response # : </td><td class='ajaxTd'>"+SEC_KEY_VAL+"</td></tr>";
                   }
                   
                   if(BOL_NUMBER != "NO"){
                   details = details + " <tr><td class='ajaxTd'>BOL # : </td><td class='ajaxTd'>"+BOL_NUMBER+"</td></tr>";
                   }*/
                if(CO_NUMBER != "NO"){
                   details = details + " <tr><td class='ajaxTd'>CO # : </td><td class='ajaxTd'>"+CO_NUMBER+"</td></tr>";
                   }
                   if(PO_NUMBER != "NO"){
                   details = details + " <tr><td class='ajaxTd'>PO # : </td><td class='ajaxTd'>"+PO_NUMBER+"</td></tr>";
                   }
                   
                      details = details + "<tr><td class='ajaxTd'>Document&nbsp;Type&nbsp;:</td><td class='ajaxTd'>"+docType+"</td></tr>";
                      details = details + "<tr><td class='ajaxTd'>Transaction&nbsp;Type&nbsp;:</td><td class='ajaxTd'>"+TRAN_NUMBER+"</td></tr>";
                      //<tr><td class='ajaxTd'>SENDER_ID :&nbsp; &nbsp;</td><td class='ajaxTd'>"+SENDER_ID+"</td></tr>"+
                      //<tr><td class='ajaxTd'>RECEIVER_ID :&nbsp; &nbsp;</td><td class='ajaxTd'>"+RECEIVER_ID+"</td></tr>"+
                      if(DIRECTION == "INBOUND"){
                       details = details + "<tr><td class='ajaxTd'><b>Sender Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                      }
                   else{
                       details = details + "<tr><td class='ajaxTd'><b>Sender Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                   }
                      
                       details = details + "<tr><td class='ajaxTd'> Id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+SENDER_ID+"</td></tr>";
                       details = details + "<tr><td class='ajaxTd'> company id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+TMW_SENDERID+"</td></tr>";
                       details = details + "<tr><td class='ajaxTd'> Name&nbsp;:&nbsp;</td><td class='ajaxTd'>"+SENDER_NAME+"</td></tr>";
                       if(DIRECTION == "OUTBOUND"){
                       details = details + "<tr><td class='ajaxTd'><b>Receiver Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                       }else
                           {
                       details = details + "<tr><td class='ajaxTd'><b>Receiver Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                           }
                           
                           details = details + "<tr><td class='ajaxTd'>Id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+RECEIVER_ID+"</td></tr>";
                           details = details + "<tr><td class='ajaxTd'> company id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+TMW_RECEIVERID+"</td></tr>";
                       details = details + "<tr><td class='ajaxTd'> Name&nbsp;:&nbsp;</td><td class='ajaxTd'>"+RECEIVER_NAME+"</td></tr>";
               
                       
                      details = details + "<tr><td class='ajaxTd'>ISA # :&nbsp; &nbsp;</td><td class='ajaxTd'>"+ISA_NUMBER+"</td></tr>"+
                         
                      "<tr><td class='ajaxTd'>GS # :&nbsp; &nbsp;</td><td class='ajaxTd'>"+GS_CONTROL_NUMBER+"</td></tr>"+
                      "<tr><td class='ajaxTd'>ST # :&nbsp; &nbsp;</td><td class='ajaxTd'>"+ST_CONTROL_NUMBER+"</td></tr>"+
                       "<tr><td class='ajaxTd'>ISA Date :&nbsp; &nbsp;</td><td class='ajaxTd'>"+ISA_DATE+"</td></tr>"+
                          "<tr><td class='ajaxTd'>ISA Time :&nbsp; &nbsp;</td><td class='ajaxTd'>"+ISA_TIME+"</td></tr>";
                          //"<tr><td class='ajaxTd'>Status :&nbsp; &nbsp;</td><td class='ajaxTd'>"+STATUS+"</td></tr>";
             
           if(STATUS.toUpperCase() == "ERROR"){
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp;</td><td class='ajaxTd'><font color='red'>"+STATUS+"</font></td></tr>";
            }else if(STATUS.toUpperCase() == "SUCCESS"){
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp; </td><td class='ajaxTd'><font color='green'>"+STATUS+"</font></td></tr>";
            }else {
               details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp; </td><td class='ajaxTd'><font color='orange'>"+STATUS+"</font></td></tr>"; 
            }
               if(PRI_KEY_VAL == "NO"){
                   details = details + " <tr><td class='ajaxTd'>ShipmentId&nbsp;# : </td><td class='ajaxTd'><font color='red'>Shipment Id is missing</td></td></tr>";
                   }  
            if(PRE_TRANS_FILEPATH == "No File"){
               details = details + "<tr><td class='ajaxTd'>Pre-Translation&nbsp;:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
               details = details + "<tr><td class='ajaxTd'>Pre-Translation&nbsp;:</td><td class='ajaxTd'><a href=\"../download/getAttachment.action?locationAvailable="+PRE_TRANS_FILEPATH+"\">Download</a></td></tr>";
            }
            if(POST_TRANS_FILEPATH == "No File"){
                details = details + "<tr><td class='ajaxTd'>Post-Translation&nbsp;:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
                details = details + "<tr><td class='ajaxTd'>Post-Translation&nbsp;:</td><td class='ajaxTd'><a href=\"../download/getAttachment.action?locationAvailable="+POST_TRANS_FILEPATH+"\">Download</a></td></tr>";
            }
            
           // alert("TRAN_NUMBER--->"+TRAN_NUMBER);
            if(ACKFILEID == "No File"){
                details = details + "<tr><td class='ajaxTd'>997&nbsp;Ack&nbsp;File&nbsp;:</td><td class='ajaxTd'> -- </tr></td>";
            }else{
                details = details + "<tr><td class='ajaxTd'>997&nbsp;Ack&nbsp;File&nbsp;:</td><td class='ajaxTd'> <a href=\"../download/getAttachment.action?locationAvailable="+ACKFILEID+"\">Download</a></td></tr> ";
            }
            
            if(ERRMESSAGE != "NO MSG"){
                details = details + "<tr><td class='ajaxTd'>Error&nbsp;Message&nbsp;:</td><td class='ajaxTd'> <font color='red'>"+ERRMESSAGE+"</font></td></tr></table>";
            }

            details = details + "</table>";
           //alert("details--->"+details);
      //  }
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
      var details = " <h5 >Sorry ! No Results Found</h5>";
                    
    }
     var detailsDIV=document.getElementById("detailInformation");
	detailsDIV.innerHTML="";
	detailsDIV.innerHTML=details;
     /* $(function() {
       
           $('#detail_box').show();
           return false;
               
   });*/
 
}
function getLoadTenderingDetails(number,ponum,id){
    // ponum = shipmentId
    
    var num=number;
    var ponum = ponum;
    var id = id;
   // alert('id'+id);
    $(function() {
       
           $('#detail_box').show();
           return false;
               
   });
   var req = getXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populateLoadTenderingDetails); 
   var url="../ajax/getLoadTenderingDetails.action?isaNumber="+num+"&poNumber="+ponum+"&id="+id;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateLoadTenderingDetails(responseXML)
{
   //alert("responseXML--->"+responseXML.toString());
    var details = responseXML.getElementsByTagName("DETAILS")[0];
    var detail = details.childNodes[0];
    var chk=detail.getElementsByTagName("VALID")[0];  
    
     if(chk.childNodes[0].nodeValue =="true") {
    
            var detail = details.childNodes[0];
            var fileid = detail.getElementsByTagName("FILEID")[0].childNodes[0].nodeValue;   
            var docType = detail.getElementsByTagName("FILETYPE")[0].childNodes[0].nodeValue; 
            
            var PRE_TRANS_FILEPATH = detail.getElementsByTagName("PRETRANSFILEPATH")[0].childNodes[0].nodeValue;
            var POST_TRANS_FILEPATH = detail.getElementsByTagName("POSTTRANSFILEPATH")[0].childNodes[0].nodeValue; 
            var DIRECTION = detail.getElementsByTagName("DIRECTION")[0].childNodes[0].nodeValue;
            var SENDER_ID = detail.getElementsByTagName("SENDERID")[0].childNodes[0].nodeValue;
            var RECEIVER_ID = detail.getElementsByTagName("RECEIVERID")[0].childNodes[0].nodeValue; 
            var SENDER_NAME = detail.getElementsByTagName("SENDER_NAME")[0].childNodes[0].nodeValue;
            var RECEIVER_NAME = detail.getElementsByTagName("RECEIVER_NAME")[0].childNodes[0].nodeValue;
            
            var	BOL_NUMBER = detail.getElementsByTagName("BOL_NUMBER")[0].childNodes[0].nodeValue; 	 
            var CO_NUMBER = detail.getElementsByTagName("CO_NUMBER")[0].childNodes[0].nodeValue;
            var TOTAL_WEIGHT = detail.getElementsByTagName("TOTAL_WEIGHT")[0].childNodes[0].nodeValue;
            var TOTAL_PIECES = detail.getElementsByTagName("TOTAL_PIECES")[0].childNodes[0].nodeValue;
            var TOTAL_VOLUME = detail.getElementsByTagName("TOTAL_VOLUME")[0].childNodes[0].nodeValue;
            
            var ISA_NUMBER = detail.getElementsByTagName("ISA_NUMBER")[0].childNodes[0].nodeValue; 
            var GS_CONTROL_NUMBER = detail.getElementsByTagName("GS_CONTROL_NUMBER")[0].childNodes[0].nodeValue; 
            var ST_CONTROL_NUMBER = detail.getElementsByTagName("ST_CONTROL_NUMBER")[0].childNodes[0].nodeValue; 
            var TRAN_NUMBER = detail.getElementsByTagName("TRANSACTION_TYPE")[0].childNodes[0].nodeValue; 
            var SEC_KEY_VAL = detail.getElementsByTagName("SEC_KEY_VAL")[0].childNodes[0].nodeValue; 
            var PRI_KEY_TYPE = detail.getElementsByTagName("PRI_KEY_TYPE")[0].childNodes[0].nodeValue; 
            var PRI_KEY_VAL = detail.getElementsByTagName("PRI_KEY_VAL")[0].childNodes[0].nodeValue; 
            
             var ISA_DATE = detail.getElementsByTagName("ISA_DATE")[0].childNodes[0].nodeValue; 
              var ISA_TIME = detail.getElementsByTagName("ISA_TIME")[0].childNodes[0].nodeValue; 
               var STATUS = detail.getElementsByTagName("STATUS")[0].childNodes[0].nodeValue; 
            //var ORG_FILEPATH = detail.getElementsByTagName("ORG_FILEPATH")[0].childNodes[0].nodeValue; 
            var ACKFILEID = detail.getElementsByTagName("ACKFILEID")[0].childNodes[0].nodeValue;
             var PO_NUMBER = detail.getElementsByTagName("PO_NUMBER")[0].childNodes[0].nodeValue;
           
            var ERRMESSAGE = detail.getElementsByTagName("ERR_MESSAGE")[0].childNodes[0].nodeValue;
            
            var TMW_SENDERID = detail.getElementsByTagName("TMW_SENDERID")[0].childNodes[0].nodeValue;
            var TMW_RECEIVERID = detail.getElementsByTagName("TMW_RECEIVERID")[0].childNodes[0].nodeValue;
          
          var details = " <table style='margin: 0 0 0 0;padding: 0px 0px;'><tr><td class='ajaxTd'>Instance Id # :&nbsp; &nbsp; </td><td class='ajaxTd'>"+fileid+"</td></tr>"+
                       " <tr><td class='ajaxTd'>Shipment # : </td><td class='ajaxTd'>"+SEC_KEY_VAL+"</td></tr>";
                  
                   //alert(PRI_KEY_TYPE);
                   if(PRI_KEY_VAL=="NO"){
                   PRI_KEY_VAL="-";
                   }
                   
                     //alert(PRI_KEY_TYPE);
                   if(CO_NUMBER=="NO"){
                   CO_NUMBER="-";
                   }
                   
                   
                    // alert(PRI_KEY_VAL);
                   if(BOL_NUMBER=="NO"){
                   BOL_NUMBER="-";
                   }
                    if(PO_NUMBER=="NO"){
                   PO_NUMBER="-";
                   }
                   
                    //alert(PO_NUMBER);
                   
                   if(TOTAL_VOLUME=="NO"){
                   TOTAL_VOLUME="-";
                   }
                   if(ST_CONTROL_NUMBER=="NO")
                   {
                       ST_CONTROL_NUMBER= "-";
                   }
                  
                      
                     // details = details + "<tr><td class='ajaxTd'>PO #:</td><td class='ajaxTd'>"+PO_NUMBER+"</td></tr>";
                      //details = details + "<tr><td class='ajaxTd'>BOL #:</td><td class='ajaxTd'>"+BOL_NUMBER+"</td></tr>";
                      //details = details + "<tr><td class='ajaxTd'>CO #:</td><td class='ajaxTd'>"+CO_NUMBER+"</td></tr>";
                      details = details + "<tr><td class='ajaxTd'>Total Weight :</td><td class='ajaxTd'>"+TOTAL_WEIGHT+"</td></tr>";
                      details = details + "<tr><td class='ajaxTd'>Volume :</td><td class='ajaxTd'>"+TOTAL_VOLUME+"</td></tr>";
                      details = details + "<tr><td class='ajaxTd'>Pieces :</td><td class='ajaxTd'>"+TOTAL_PIECES+"</td></tr>";
                      details = details + "<tr><td class='ajaxTd'>Document&nbsp;Type&nbsp;:</td><td class='ajaxTd'>"+docType+"</td></tr>";
                      
                      details = details + "<tr><td class='ajaxTd'>Transaction&nbsp;Type&nbsp;:</td><td class='ajaxTd'>"+TRAN_NUMBER+"</td></tr>";
                      //<tr><td class='ajaxTd'>SENDER_ID :&nbsp; &nbsp;</td><td class='ajaxTd'>"+SENDER_ID+"</td></tr>"+
                      //<tr><td class='ajaxTd'>RECEIVER_ID :&nbsp; &nbsp;</td><td class='ajaxTd'>"+RECEIVER_ID+"</td></tr>"+
                       details = details + "<tr><td class='ajaxTd'><b>Patner Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                       
                       
                       details = details + "<tr><td class='ajaxTd'> Id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+SENDER_ID+"</td></tr>";
                       details = details + "<tr><td class='ajaxTd'> company id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+TMW_SENDERID+"</td></tr>";
                       details = details + "<tr><td class='ajaxTd'> Name&nbsp;:&nbsp;</td><td class='ajaxTd'>"+SENDER_NAME+"</td></tr>";
               
                       details = details + "<tr><td class='ajaxTd'><b>Receiver Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                       
                       details = details + "<tr><td class='ajaxTd'>Id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+RECEIVER_ID+"</td></tr>";
                       details = details + "<tr><td class='ajaxTd'> company id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+TMW_RECEIVERID+"</td></tr>";
                       details = details + "<tr><td class='ajaxTd'> Name&nbsp;:&nbsp;</td><td class='ajaxTd'>"+RECEIVER_NAME+"</td></tr>";
               
                       
                      details = details + "<tr><td class='ajaxTd'>ISA # :&nbsp; &nbsp;</td><td class='ajaxTd'>"+ISA_NUMBER+"</td></tr>"+
                      "<tr><td class='ajaxTd'>GS # :&nbsp; &nbsp;</td><td class='ajaxTd'>"+GS_CONTROL_NUMBER+"</td></tr>"+
                      "<tr><td class='ajaxTd'>ST # :&nbsp; &nbsp;</td><td class='ajaxTd'>"+ST_CONTROL_NUMBER+"</td></tr>"+
                  "<tr><td class='ajaxTd'>ISA Date  :&nbsp; &nbsp;</td><td class='ajaxTd'>"+ISA_DATE+"</td></tr>"+
                  "<tr><td class='ajaxTd'>ISA Time :&nbsp; &nbsp;</td><td class='ajaxTd'>"+ISA_TIME+"</td></tr>";
                  
             
              if(STATUS.toUpperCase() == "ERROR"){
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp;</td><td class='ajaxTd'><font color='red'>"+STATUS+"</font></td></tr>";
            }else if(STATUS.toUpperCase() == "SUCCESS"){
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp; </td><td class='ajaxTd'><font color='green'>"+STATUS+"</font></td></tr>";
            }else {
               details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp; </td><td class='ajaxTd'><font color='orange'>"+STATUS+"</font></td></tr>"; 
            }
         if(PRI_KEY_VAL == "NO"){
                   details = details + " <tr><td class='ajaxTd'>ShipmentId&nbsp;# : </td><td class='ajaxTd'><font color='red'>Shipment Id is missing</td></td></tr>";
                   }  
                       if(PRE_TRANS_FILEPATH == "No File"){
               details = details + "<tr><td class='ajaxTd'>Pre-Translation&nbsp;:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
               details = details + "<tr><td class='ajaxTd'>Pre-Translation&nbsp;:</td><td class='ajaxTd'><a href=\"../download/getAttachment.action?locationAvailable="+PRE_TRANS_FILEPATH+"\">Download</a></td></tr>";
            }
            
            if(POST_TRANS_FILEPATH == "No File"){
                details = details + "<tr><td class='ajaxTd'>Post-Translation&nbsp;:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
                details = details + "<tr><td class='ajaxTd'>Post-Translation&nbsp;:</td><td class='ajaxTd'><a href=\"../download/getAttachment.action?locationAvailable="+POST_TRANS_FILEPATH+"\">Download</a></td></tr>";
            }
            
           // alert("TRAN_NUMBER--->"+TRAN_NUMBER);
            if(ACKFILEID == "No File"){
                details = details + "<tr><td class='ajaxTd'>997&nbsp;Ack&nbsp;File&nbsp;:</td><td class='ajaxTd'> -- </tr></td>";
            }else{
                details = details + "<tr><td class='ajaxTd'>997&nbsp;Ack&nbsp;File&nbsp;:</td><td class='ajaxTd'> <a href=\"../download/getAttachment.action?locationAvailable="+ACKFILEID+"\">Download</a></td></tr> ";
            }
            
            if(ERRMESSAGE != "NO MSG"){
                details = details + "<tr><td class='ajaxTd'>Error&nbsp;Message&nbsp;:</td><td class='ajaxTd'> <font color='red'>"+ERRMESSAGE+"</font></td></tr></table>";
            }

            details = details + "</table>";
           //alert("details--->"+details);
      //  }
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
      var details = " <h5 >Sorry ! No Results Found</h5>";
                    
    }
     var detailsDIV=document.getElementById("detailInformation");
	detailsDIV.innerHTML="";
	detailsDIV.innerHTML=details;
     /* $(function() {
       
           $('#detail_box').show();
           return false;
               
   });*/
 
}

function getLtResponseDetails(fileId,refId){
  //  alert("hii");
    //var num=number;
   //alert("inv number-->"+num);
 //  var req = new XMLHttpRequest();
 $(function() {
       
           $('#detail_box').show();
           return false;
               
   });
 var req = getXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populateLtResponseDetails); 
   
    var url="../ajax/getLtResponseDetails.action?fileId="+fileId+"&refId="+refId;
   
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateLtResponseDetails(responseXML)
{
     var details = responseXML.getElementsByTagName("DETAILS")[0];
    var detail = details.childNodes[0];
    var chk=detail.getElementsByTagName("VALID")[0];  
    
     if(chk.childNodes[0].nodeValue =="true") {   
            var detail = details.childNodes[0];
            var FILE_ID = detail.getElementsByTagName("FILE_ID")[0].childNodes[0].nodeValue; 
            var SHIPMENT_ID = detail.getElementsByTagName("SHIPMENT_ID")[0].childNodes[0].nodeValue;   
            var FILE_TYPE = detail.getElementsByTagName("FILE_TYPE")[0].childNodes[0].nodeValue; 
            var TRANSACTION_TYPE = detail.getElementsByTagName("TRANSACTION_TYPE")[0].childNodes[0].nodeValue;  
            var SENDER_ID = detail.getElementsByTagName("SENDER_ID")[0].childNodes[0].nodeValue;  
            var SENDER_NAME = detail.getElementsByTagName("SENDER_NAME")[0].childNodes[0].nodeValue; 
            var RECEIVER_ID = detail.getElementsByTagName("RECEIVER_ID")[0].childNodes[0].nodeValue; 
            var RECEIVER_NAME = detail.getElementsByTagName("RECEIVER_NAME")[0].childNodes[0].nodeValue; 
            var ISA_NUMBER = detail.getElementsByTagName("ISA_NUMBER")[0].childNodes[0].nodeValue; 
            var GS_CONTROL_NUMBER = detail.getElementsByTagName("GS_CONTROL_NUMBER")[0].childNodes[0].nodeValue;
            var ST_CONTROL_NUMBER = detail.getElementsByTagName("ST_CONTROL_NUMBER")[0].childNodes[0].nodeValue;
            var ISA_DATE = detail.getElementsByTagName("ISA_DATE")[0].childNodes[0].nodeValue;
            var ISA_TIME = detail.getElementsByTagName("ISA_TIME")[0].childNodes[0].nodeValue;
             var REFERENCE = detail.getElementsByTagName("REFERENCE")[0].childNodes[0].nodeValue;
             var STATUS = detail.getElementsByTagName("STATUS")[0].childNodes[0].nodeValue;
            var PRE_TRANS_FILEPATH = detail.getElementsByTagName("PRETRANSFILEPATH")[0].childNodes[0].nodeValue;
            var POST_TRANS_FILEPATH = detail.getElementsByTagName("POSTTRANSFILEPATH")[0].childNodes[0].nodeValue; 
           // var ORGFILEPATH = detail.getElementsByTagName("ORGFILEPATH")[0].childNodes[0].nodeValue;
            
            var ACKFILEID = detail.getElementsByTagName("ACKFILEID")[0].childNodes[0].nodeValue;
            var ERR_MESSAGE = detail.getElementsByTagName("ERR_MESSAGE")[0].childNodes[0].nodeValue;
            
            var TMW_SENDERID = detail.getElementsByTagName("TMW_SENDERID")[0].childNodes[0].nodeValue;
            var TMW_RECEIVERID = detail.getElementsByTagName("TMW_RECEIVERID")[0].childNodes[0].nodeValue;
             
            
          
         // alert(deilvaryName+" "+poValue+ " "+ routings+ " "+invoice+" "+itemQty);
          
          
           var details="<table style='margin: 0 0 0 0;padding: 0px 0px;'>"+
                    "<tr><td class='ajaxTd'>Instance&nbsp;Id&nbsp;:</td><td class='ajaxTd'>"+FILE_ID+"</td></tr>"+
                    "<tr><td class='ajaxTd'>Order&nbsp;Id&nbsp;:</td><td class='ajaxTd'>"+REFERENCE+"</td></tr>"+
                    "<tr><td class='ajaxTd'>Shipment&nbsp;Id&nbsp;:</td><td class='ajaxTd'>"+SHIPMENT_ID+"</td></tr>"+
                    "<tr><td class='ajaxTd'>Doc&nbsp;Type&nbsp;:</td><td class='ajaxTd'>"+FILE_TYPE+"</td></tr>"+
                    "<tr><td class='ajaxTd'>Transaction&nbsp;Type&nbsp;:</td><td class='ajaxTd'>"+TRANSACTION_TYPE+"</td></tr>";
                    
                   
                
                 details = details + "<tr><td class='ajaxTd'><b>Sender Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                 
                 
                 
                       details = details + "<tr><td class='ajaxTd'> Id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+SENDER_ID+"</td></tr>";
                       details = details + "<tr><td class='ajaxTd'> company id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+TMW_SENDERID+"</td></tr>";
                       details = details + "<tr><td class='ajaxTd'> Name&nbsp;:&nbsp;</td><td class='ajaxTd'>"+SENDER_NAME+"</td></tr>";
               
                       details = details + "<tr><td class='ajaxTd'><b>Partner Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                       
                       
               details = details + "<tr><td class='ajaxTd'>Id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+RECEIVER_ID+"</td></tr>";
               details = details + "<tr><td class='ajaxTd'> company id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+TMW_RECEIVERID+"</td></tr>";
               details = details + "<tr><td class='ajaxTd'> Name&nbsp;:&nbsp;</td><td class='ajaxTd'>"+RECEIVER_NAME+"</td></tr>";
               
               details = details + "<tr><td class='ajaxTd'>ISA #  :</td><td class='ajaxTd'>"+ISA_NUMBER+"</td></tr>"+
                    
                    "<tr><td class='ajaxTd'>GS # </td><td class='ajaxTd'>"+GS_CONTROL_NUMBER+"</td></tr>"
                +"<tr><td class='ajaxTd'>ST #  :</td><td class='ajaxTd'>"+ST_CONTROL_NUMBER+"</td></tr>"
                    + "<tr><td class='ajaxTd'>ISA date  :</td><td class='ajaxTd'>"+ISA_DATE+"</td></tr>"
                    + "<tr><td class='ajaxTd'>ISA Time :</td><td class='ajaxTd'>"+ISA_TIME+"</td></tr>";
            
            if(STATUS.toUpperCase() == "ERROR"){
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp;</td><td class='ajaxTd'><font color='red'>"+STATUS+"</font></td></tr>";
            }else if(STATUS.toUpperCase() == "SUCCESS"){
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp; </td><td class='ajaxTd'><font color='green'>"+STATUS+"</font></td></tr>";
            }else {
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp; </td><td class='ajaxTd'><font color='orange'>"+STATUS+"</font></td></tr>";
            }
             if(SHIPMENT_ID == "--"){
                   details = details + " <tr><td class='ajaxTd'>ShipmentId&nbsp;# : </td><td class='ajaxTd'><font color='red'>Shipment Id is missing</td></td></tr>";
                   }
                    //"<tr><td class='ajaxTd'><font color='red'>Status :</font></td><td class='ajaxTd'><font color='red'>"+STATUS+"</font></td></tr>";
    
              /*   if(ORGFILEPATH == "No File"){
               details = details + "<tr><td class='ajaxTd' >Original File&nbsp:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
               details = details + "<tr><td class='ajaxTd' >Original File&nbsp;: </td><td class='ajaxTd'><a href=\"../download/getAttachment.action?locationAvailable="+ORGFILEPATH+"\">Download</a></td></tr>";
            }*/
            
            if(PRE_TRANS_FILEPATH == "No File"){
               details = details + "<tr><td class='ajaxTd' >Pre-Translation&nbsp:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
               details = details + "<tr><td class='ajaxTd' >Pre-Translation&nbsp;:</td><td class='ajaxTd'> <a href=\"../download/getAttachment.action?locationAvailable="+PRE_TRANS_FILEPATH+"\">Download</a></td></tr>";
            }
            
            if(POST_TRANS_FILEPATH == "No File"){
                details = details + "<tr><td class='ajaxTd' >Post-Translation&nbsp;:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
                details = details + "<tr><td class='ajaxTd' >Post-Translation&nbsp;:</td><td class='ajaxTd'> <a href=\"../download/getAttachment.action?locationAvailable="+POST_TRANS_FILEPATH+"\">Download</a></td></tr>";
            }
            
            if(ACKFILEID == "No File"){
                details = details + "<tr><td class='ajaxTd' >997&nbsp;Ack&nbsp;File&nbsp;:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
                details = details + "<tr><td class='ajaxTd' >997&nbsp;Ack&nbsp;File&nbsp;:</td><td class='ajaxTd'> <a href=\"../download/getAttachment.action?locationAvailable="+ACKFILEID+"\">Download</a></td></tr>";
            }
            //alert(ERRMESSAGE);
            if(ERR_MESSAGE != "NO MSG"){
                details = details + "<tr><td class='ajaxTd' >Error&nbsp;Message&nbsp;:</td><td class='ajaxTd'> <font color='red'>"+ERR_MESSAGE+"</font></td></tr></table>"
            }
          
      //  }
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
      var details = " <h5 >Sorry ! No Results Found</h5>";
                    
    }
     var detailsDIV=document.getElementById("detailInformation");
	detailsDIV.innerHTML="";
	detailsDIV.innerHTML=details;
     /* $(function() {
       
           $('#detail_box').show();
           return false;
               
   });*/
 
}
/*
 * Method for displaying Details Information of LogisticsInvoice
 * Date : 06/27/2013
 * Author : santosh kola
 * 
 */
function getLogisticsInvDetails(number,id){
  //  alert("hii");
    var num=number;
    var id=id;
   //alert("inv number-->"+num);
 //  var req = new XMLHttpRequest();
 $(function() {
       
           $('#detail_box').show();
           return false;
               
   });
 var req = getXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populateLogisticsInvDetails); 
   
    var url="../ajax/getLogisticsInvDetails.action?invNumber="+num+"&id="+id;
   
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateLogisticsInvDetails(responseXML)
{
     var details = responseXML.getElementsByTagName("DETAILS")[0];
    var detail = details.childNodes[0];
    var chk=detail.getElementsByTagName("VALID")[0];  
    
     if(chk.childNodes[0].nodeValue =="true") {   
            var detail = details.childNodes[0];
            var fileID = detail.getElementsByTagName("FILEID")[0].childNodes[0].nodeValue; 
            var invNum = detail.getElementsByTagName("INVNUMBER")[0].childNodes[0].nodeValue;   
            var poNum = detail.getElementsByTagName("PONUMBER")[0].childNodes[0].nodeValue; 
            var itemQty = detail.getElementsByTagName("ITEMQTY")[0].childNodes[0].nodeValue;  
            var invAmt = detail.getElementsByTagName("INVAMT")[0].childNodes[0].nodeValue;  
            var isaNum = detail.getElementsByTagName("ISANUM")[0].childNodes[0].nodeValue; 
            var isaDate = detail.getElementsByTagName("ISADATE")[0].childNodes[0].nodeValue; 
            var isaTime = detail.getElementsByTagName("ISATIME")[0].childNodes[0].nodeValue; 
            var STATUS = detail.getElementsByTagName("STATUS")[0].childNodes[0].nodeValue; 
            var PRE_TRANS_FILEPATH = detail.getElementsByTagName("PRETRANSFILEPATH")[0].childNodes[0].nodeValue;
            var POST_TRANS_FILEPATH = detail.getElementsByTagName("POSTTRANSFILEPATH")[0].childNodes[0].nodeValue; 
            var ORGFILEPATH = detail.getElementsByTagName("ORGFILEPATH")[0].childNodes[0].nodeValue;
            var ERRMESSAGE = detail.getElementsByTagName("ERR_MESSAGE")[0].childNodes[0].nodeValue;
            var ACKFILEID = detail.getElementsByTagName("ACKFILEID")[0].childNodes[0].nodeValue;
            
             var SENDER_ID = detail.getElementsByTagName("SENDER_ID")[0].childNodes[0].nodeValue;
            var RECEIVER_ID = detail.getElementsByTagName("RECEIVER_ID")[0].childNodes[0].nodeValue; 
            var SENDER_NAME = detail.getElementsByTagName("SENDER_NAME")[0].childNodes[0].nodeValue;
            var RECEIVER_NAME = detail.getElementsByTagName("RECEIVER_NAME")[0].childNodes[0].nodeValue;
            
            var TRANSACTION_TYPE = detail.getElementsByTagName("TRANSACTION_TYPE")[0].childNodes[0].nodeValue;
            var ST_CONTROL_NUMBER = detail.getElementsByTagName("ST_CONTROL_NUMBER")[0].childNodes[0].nodeValue;
            var GS_CONTROL_NUMBER = detail.getElementsByTagName("GS_CONTROL_NUMBER")[0].childNodes[0].nodeValue;
            
            var TMW_SENDERID = detail.getElementsByTagName("TMW_SENDERID")[0].childNodes[0].nodeValue;
            var TMW_RECEIVERID = detail.getElementsByTagName("TMW_RECEIVERID")[0].childNodes[0].nodeValue;
          
         // alert(deilvaryName+" "+poValue+ " "+ routings+ " "+invoice+" "+itemQty);
          
          
           var details="<table style='margin: 0 0 0 0;padding: 0px 0px;'>"+
                    "<tr><td class='ajaxTd'>Instance Id :</td><td class='ajaxTd'>"+fileID+"</td></tr>"+
                    "<tr><td class='ajaxTd'>Shipment #:</td><td class='ajaxTd'>"+poNum+"</td></tr>"+
                    "<tr><td class='ajaxTd'>Invoice # :</td><td class='ajaxTd'>"+invNum+"</td></tr>"+
                    "<tr><td class='ajaxTd'>Transaction Type # :</td><td class='ajaxTd'>"+TRANSACTION_TYPE+"</td></tr>"+
                    "<tr><td class='ajaxTd'>Invoice Amount :</td><td class='ajaxTd'>"+invAmt+"</td></tr>"+
                   "<tr><td class='ajaxTd'>Item Quantity  :</td><td class='ajaxTd'>"+itemQty+"</td></tr>";
                   
                
                 details = details + "<tr><td class='ajaxTd'><b>Sender Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                 
                       
                       details = details + "<tr><td class='ajaxTd'> Id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+SENDER_ID+"</td></tr>";
                       details = details + "<tr><td class='ajaxTd'> Company Id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+TMW_SENDERID+"</td></tr>";  
                       details = details + "<tr><td class='ajaxTd'> Name&nbsp;:&nbsp;</td><td class='ajaxTd'>"+SENDER_NAME+"</td></tr>";
               
                       details = details + "<tr><td class='ajaxTd'><b>Receiver Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                       
                       
               details = details + "<tr><td class='ajaxTd'>Id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+RECEIVER_ID+"</td></tr>";
               details = details + "<tr><td class='ajaxTd'> Company Id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+TMW_RECEIVERID+"</td></tr>";  
               details = details + "<tr><td class='ajaxTd'> Name&nbsp;:&nbsp;</td><td class='ajaxTd'>"+RECEIVER_NAME+"</td></tr>";
               
               details = details + "<tr><td class='ajaxTd'>ISA #  :</td><td class='ajaxTd'>"+isaNum+"</td></tr>"+
                    "<tr><td class='ajaxTd'>ISA date  :</td><td class='ajaxTd'>"+isaDate+"</td></tr>"+
                    "<tr><td class='ajaxTd'>ISA Time :</td><td class='ajaxTd'>"+isaTime+"</td></tr>";
            
            details = details + "<tr><td class='ajaxTd'>ST #  :</td><td class='ajaxTd'>"+ST_CONTROL_NUMBER+"</td></tr>"+
                      "<tr><td class='ajaxTd'>GS #  :</td><td class='ajaxTd'>"+GS_CONTROL_NUMBER+"</td></tr>";
            
            if(STATUS.toUpperCase() == "ERROR"){
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp;</td><td class='ajaxTd'><font color='red'>"+STATUS+"</font></td></tr>";
            }else if(STATUS.toUpperCase() == "SUCCESS"){
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp; </td><td class='ajaxTd'><font color='green'>"+STATUS+"</font></td></tr>";
            }else {
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp; </td><td class='ajaxTd'><font color='orange'>"+STATUS+"</font></td></tr>";
            }
                    //"<tr><td class='ajaxTd'><font color='red'>Status :</font></td><td class='ajaxTd'><font color='red'>"+STATUS+"</font></td></tr>";
    
                 if(ORGFILEPATH == "No File"){
               details = details + "<tr><td class='ajaxTd' >Original File&nbsp:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
               details = details + "<tr><td class='ajaxTd' >Original File&nbsp;: </td><td class='ajaxTd'><a href=\"../download/getAttachment.action?locationAvailable="+ORGFILEPATH+"\">Download</a></td></tr>";
            }
            
            if(PRE_TRANS_FILEPATH == "No File"){
               details = details + "<tr><td class='ajaxTd' >Pre-Translation&nbsp:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
               details = details + "<tr><td class='ajaxTd' >Pre-Translation&nbsp;:</td><td class='ajaxTd'> <a href=\"../download/getAttachment.action?locationAvailable="+PRE_TRANS_FILEPATH+"\">Download</a></td></tr>";
            }
            
            if(POST_TRANS_FILEPATH == "No File"){
                details = details + "<tr><td class='ajaxTd' >Post-Translation&nbsp;:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
                details = details + "<tr><td class='ajaxTd' >Post-Translation&nbsp;:</td><td class='ajaxTd'> <a href=\"../download/getAttachment.action?locationAvailable="+POST_TRANS_FILEPATH+"\">Download</a></td></tr>";
            }
            
            if(ACKFILEID == "No File"){
                details = details + "<tr><td class='ajaxTd' >997&nbsp;Ack&nbsp;File&nbsp;:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
                details = details + "<tr><td class='ajaxTd' >997&nbsp;Ack&nbsp;File&nbsp;:</td><td class='ajaxTd'> <a href=\"../download/getAttachment.action?locationAvailable="+ACKFILEID+"\">Download</a></td></tr>";
            }
            //alert(ERRMESSAGE);
            if(ERRMESSAGE != "NO MSG"){
                details = details + "<tr><td class='ajaxTd' >Error&nbsp;Message&nbsp;:</td><td class='ajaxTd'> <font color='red'>"+ERRMESSAGE+"</font></td></tr></table>"
            }
          
      //  }
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
      var details = " <h5 >Sorry ! No Results Found</h5>";
                    
    }
     var detailsDIV=document.getElementById("detailInformation");
	detailsDIV.innerHTML="";
	detailsDIV.innerHTML=details;
     /* $(function() {
       
           $('#detail_box').show();
           return false;
               
   });*/
 
}
/*
 * Method for displaying Details Information of LogisticsShipment
 * Date : 06/27/2013
 * Author : santosh kola
 * 
 */
function getLogisticsShipmentDetails(number,ponum,id,db){
  //  alert("hii");
  
    var num=number;
    var ponumber=ponum;
    var id = id;
   //alert("this general ajax num-->"+num);
   //alert("this general ajax ponumber-->"+ponumber);
   //alert("this general ajax id-->"+id);
 //  var req = new XMLHttpRequest(); &id="+id
 $(function() {
       
           $('#detail_box').show();
           return false;
               
   });
 var req = getXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populateLogisticsShipmentDetails); 
   
    var url="../ajax/getLogisticsShipmentDetails.action?asnNumber="+num+"&poNumber="+ponum+"&id="+id+"&database="+db;
   
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function populateLogisticsShipmentDetails(responseXML)
{
     var details = responseXML.getElementsByTagName("DETAILS")[0];
    var detail = details.childNodes[0];
    var chk=detail.getElementsByTagName("VALID")[0];  
    
     if(chk.childNodes[0].nodeValue =="true") {   
            var detail = details.childNodes[0];
            var fileID = detail.getElementsByTagName("FILEID")[0].childNodes[0].nodeValue; 
            var asnNum = detail.getElementsByTagName("ASNNUMBER")[0].childNodes[0].nodeValue;   
            var poNum = detail.getElementsByTagName("PONUMBER")[0].childNodes[0].nodeValue; 
            var itemQty = detail.getElementsByTagName("ITEMQTY")[0].childNodes[0].nodeValue;  
            var asnVolume = detail.getElementsByTagName("ASNVOLUME")[0].childNodes[0].nodeValue;  
            var isaNum = detail.getElementsByTagName("ISANUM")[0].childNodes[0].nodeValue; 
            var isaDate = detail.getElementsByTagName("ISADATE")[0].childNodes[0].nodeValue; 
            var isaTime = detail.getElementsByTagName("ISATIME")[0].childNodes[0].nodeValue; 
            var STATUS = detail.getElementsByTagName("STATUS")[0].childNodes[0].nodeValue; 
            var PRE_TRANS_FILEPATH = detail.getElementsByTagName("PRETRANSFILEPATH")[0].childNodes[0].nodeValue;
            var POST_TRANS_FILEPATH = detail.getElementsByTagName("POSTTRANSFILEPATH")[0].childNodes[0].nodeValue; 
            var ORGFILEPATH = detail.getElementsByTagName("ORGFILEPATH")[0].childNodes[0].nodeValue;
            var ERRMESSAGE = detail.getElementsByTagName("ERR_MESSAGE")[0].childNodes[0].nodeValue;
            var ACKFILEID = detail.getElementsByTagName("ACKFILEID")[0].childNodes[0].nodeValue;
            
             var SENDER_ID = detail.getElementsByTagName("SENDER_ID")[0].childNodes[0].nodeValue;
            var RECEIVER_ID = detail.getElementsByTagName("RECEIVER_ID")[0].childNodes[0].nodeValue; 
            var SENDER_NAME = detail.getElementsByTagName("SENDER_NAME")[0].childNodes[0].nodeValue;
            var RECEIVER_NAME = detail.getElementsByTagName("RECEIVER_NAME")[0].childNodes[0].nodeValue;
            
            var TRANSACTION_TYPE = detail.getElementsByTagName("TRANSACTION_TYPE")[0].childNodes[0].nodeValue;
            var ST_CONTROL_NUMBER = detail.getElementsByTagName("ST_CONTROL_NUMBER")[0].childNodes[0].nodeValue;
            var GS_CONTROL_NUMBER = detail.getElementsByTagName("GS_CONTROL_NUMBER")[0].childNodes[0].nodeValue;
            
            var TMW_SENDERID = detail.getElementsByTagName("TMW_SENDERID")[0].childNodes[0].nodeValue;
            var TMW_RECEIVERID = detail.getElementsByTagName("TMW_RECEIVERID")[0].childNodes[0].nodeValue;
          
         // alert(deilvaryName+" "+poValue+ " "+ routings+ " "+invoice+" "+itemQty);
          
          
           var details="<table style='margin: 0 0 0 0;padding: 0px 0px;'>"+
                    "<tr><td class='ajaxTd'>Instance Id :</td><td class='ajaxTd'>"+fileID+"</td></tr>"+
                    "<tr><td class='ajaxTd'>PO #:</td><td class='ajaxTd'>"+poNum+"</td></tr>"+
                    "<tr><td class='ajaxTd'>Shipment # :</td><td class='ajaxTd'>"+asnNum+"</td></tr>"+
                    "<tr><td class='ajaxTd'>Transaction Type # :</td><td class='ajaxTd'>"+TRANSACTION_TYPE+"</td></tr>"+
                    "<tr><td class='ajaxTd'>Shipment&nbsp;Volume&nbsp;:</td><td class='ajaxTd'>"+asnVolume+"</td></tr>"+
                   "<tr><td class='ajaxTd'>Item Quantity  :</td><td class='ajaxTd'>"+itemQty+"</td></tr>";
                   
                
                 details = details + "<tr><td class='ajaxTd'><b>Sender Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                 
                 
                       details = details + "<tr><td class='ajaxTd'> Id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+SENDER_ID+"</td></tr>";
                       details = details + "<tr><td class='ajaxTd'> company id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+TMW_SENDERID+"</td></tr>";
                       details = details + "<tr><td class='ajaxTd'> Name&nbsp;:&nbsp;</td><td class='ajaxTd'>"+SENDER_NAME+"</td></tr>";
               
                       details = details + "<tr><td class='ajaxTd'><b>Receiver Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                       
               details = details + "<tr><td class='ajaxTd'>Id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+RECEIVER_ID+"</td></tr>";
               details = details + "<tr><td class='ajaxTd'> company id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+TMW_RECEIVERID+"</td></tr>";
               details = details + "<tr><td class='ajaxTd'> Name&nbsp;:&nbsp;</td><td class='ajaxTd'>"+RECEIVER_NAME+"</td></tr>";
               
               details = details + "<tr><td class='ajaxTd'>ISA #  :</td><td class='ajaxTd'>"+isaNum+"</td></tr>"+
                    "<tr><td class='ajaxTd'>ISA date  :</td><td class='ajaxTd'>"+isaDate+"</td></tr>"+
                    "<tr><td class='ajaxTd'>ISA Time :</td><td class='ajaxTd'>"+isaTime+"</td></tr>";
            
            details = details + "<tr><td class='ajaxTd'>ST #  :</td><td class='ajaxTd'>"+ST_CONTROL_NUMBER+"</td></tr>"+
                      "<tr><td class='ajaxTd'>GS #  :</td><td class='ajaxTd'>"+GS_CONTROL_NUMBER+"</td></tr>";
            
            if(STATUS.toUpperCase() == "ERROR"){
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp;</td><td class='ajaxTd'><font color='red'>"+STATUS+"</font></td></tr>";
            }else if(STATUS.toUpperCase() == "SUCCESS"){
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp; </td><td class='ajaxTd'><font color='green'>"+STATUS+"</font></td></tr>";
            }else {
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp; </td><td class='ajaxTd'><font color='orange'>"+STATUS+"</font></td></tr>";
            }
                    //"<tr><td class='ajaxTd'><font color='red'>Status :</font></td><td class='ajaxTd'><font color='red'>"+STATUS+"</font></td></tr>";
                      if(asnNum == "--"){
                   details = details + " <tr><td class='ajaxTd'>ShipmentId&nbsp;# : </td><td class='ajaxTd'><font color='red'>Shipment Id is missing</td></td></tr>";
                   }
                 if(ORGFILEPATH == "No File"){
               details = details + "<tr><td class='ajaxTd' >Original File&nbsp:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
               details = details + "<tr><td class='ajaxTd' >Original File&nbsp;: </td><td class='ajaxTd'><a href=\"../download/getAttachment.action?locationAvailable="+ORGFILEPATH+"\">Download</a></td></tr>";
            }
            
            if(PRE_TRANS_FILEPATH == "No File"){
               details = details + "<tr><td class='ajaxTd' >Pre-Translation&nbsp:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
               details = details + "<tr><td class='ajaxTd' >Pre-Translation&nbsp;:</td><td class='ajaxTd'> <a href=\"../download/getAttachment.action?locationAvailable="+PRE_TRANS_FILEPATH+"\">Download</a></td></tr>";
            }
            
            if(POST_TRANS_FILEPATH == "No File"){
                details = details + "<tr><td class='ajaxTd' >Post-Translation&nbsp;:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
                details = details + "<tr><td class='ajaxTd' >Post-Translation&nbsp;:</td><td class='ajaxTd'> <a href=\"../download/getAttachment.action?locationAvailable="+POST_TRANS_FILEPATH+"\">Download</a></td></tr>";
            }
            
            if(ACKFILEID == "No File"){
                details = details + "<tr><td class='ajaxTd' >997&nbsp;Ack&nbsp;File&nbsp;:</td><td class='ajaxTd'> -- </td></tr>";
            }else{
                details = details + "<tr><td class='ajaxTd' >997&nbsp;Ack&nbsp;File&nbsp;:</td><td class='ajaxTd'> <a href=\"../download/getAttachment.action?locationAvailable="+ACKFILEID+"\">Download</a></td></tr>";
            }
            //alert(ERRMESSAGE);
            if(ERRMESSAGE != "NO MSG"){
                details = details + "<tr><td class='ajaxTd' >Error&nbsp;Message&nbsp;:</td><td class='ajaxTd'> <font color='red'>"+ERRMESSAGE+"</font></td></tr></table>"
            }
          
      //  }
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
      var details = " <h5 >Sorry ! No Results Found</h5>";
                    
    }
     var detailsDIV=document.getElementById("detailInformation");
	detailsDIV.innerHTML="";
	detailsDIV.innerHTML=details;
     /* $(function() {
       
           $('#detail_box').show();
           return false;
               
   });*/
 
}
function getDocVisibilityDetails(id) {
    //alert("Id -->"+id);
    $(function() {
       
           $('#detail_box').show();
           return false;
               
   });
 var req = getXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populateDocVisibilityDetails); 
   
    var url="../ajax/getDocVisibilityDetails.action?docId="+id;
   
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateDocVisibilityDetails(responseXML){
  //  alert(responseXML);
    var details = responseXML.getElementsByTagName("DETAILS")[0];
    var detail = details.childNodes[0];
    var chk=detail.getElementsByTagName("VALID")[0];  
    
     if(chk.childNodes[0].nodeValue =="true") {   
            var detail = details.childNodes[0];
            var FILE_ID = detail.getElementsByTagName("FILE_ID")[0].childNodes[0].nodeValue; 
            var PARENT_FILE_ID = detail.getElementsByTagName("PARENT_FILE_ID")[0].childNodes[0].nodeValue;   
            var FILE_TYPE = detail.getElementsByTagName("FILE_TYPE")[0].childNodes[0].nodeValue; 
            var FILE_ORIGIN = detail.getElementsByTagName("FILE_ORIGIN")[0].childNodes[0].nodeValue;  
            var TRAN_MESS_TYPE = detail.getElementsByTagName("TRAN_MESS_TYPE")[0].childNodes[0].nodeValue;  
            var SENDER_ID = detail.getElementsByTagName("SENDER_ID")[0].childNodes[0].nodeValue; 
            var RECEIVER_ID = detail.getElementsByTagName("RECEIVER_ID")[0].childNodes[0].nodeValue; 
            var INTERCHANGE_CONTROLNO = detail.getElementsByTagName("INTERCHANGE_CONTROLNO")[0].childNodes[0].nodeValue; 
            var FUNCTIONAL_CONTROLNO = detail.getElementsByTagName("FUNCTIONAL_CONTROLNO")[0].childNodes[0].nodeValue; 
            var MESSAGE_CONTROLNO = detail.getElementsByTagName("MESSAGE_CONTROLNO")[0].childNodes[0].nodeValue;
            var DATE_TIME_RECEIVED = detail.getElementsByTagName("DATE_TIME_RECEIVED")[0].childNodes[0].nodeValue; 
            var DIRECTION = detail.getElementsByTagName("DIRECTION")[0].childNodes[0].nodeValue;
            var STATUS = detail.getElementsByTagName("STATUS")[0].childNodes[0].nodeValue;
            var ERR_MESSAGE = detail.getElementsByTagName("ERR_MESSAGE")[0].childNodes[0].nodeValue;
            
             var ACK_STATUS = detail.getElementsByTagName("ACK_STATUS")[0].childNodes[0].nodeValue;
            var ID = detail.getElementsByTagName("ID")[0].childNodes[0].nodeValue; 
            var ISA_TIME = detail.getElementsByTagName("ISA_TIME")[0].childNodes[0].nodeValue;
            var ISA_DATE = detail.getElementsByTagName("ISA_DATE")[0].childNodes[0].nodeValue;
            
          /*   var SAP_USER = detail.getElementsByTagName("SAP_USER")[0].childNodes[0].nodeValue;
              var IDOC_NUMBER = detail.getElementsByTagName("IDOC_NUMBER")[0].childNodes[0].nodeValue;
               var PO_NUMBER = detail.getElementsByTagName("PO_NUMBER")[0].childNodes[0].nodeValue;
                var PO_DATE = detail.getElementsByTagName("PO_DATE")[0].childNodes[0].nodeValue;
                 var IDOC_STATUS_CODE = detail.getElementsByTagName("IDOC_STATUS_CODE")[0].childNodes[0].nodeValue;
                  var IDOC_STATUS_DESCRIPTION = detail.getElementsByTagName("IDOC_STATUS_DESCRIPTION")[0].childNodes[0].nodeValue;
         */
         // alert(deilvaryName+" "+poValue+ " "+ routings+ " "+invoice+" "+itemQty);
          
          
           var details="<table style='margin: 0 0 0 0;padding: 0px 0px;'>"+
                    "<tr><td class='ajaxTd'>Instance Id :</td><td class='ajaxTd'>"+FILE_ID+"</td></tr>"+
                    "<tr><td class='ajaxTd'>Parent&nbsp;FileId #:</td><td class='ajaxTd'>"+PARENT_FILE_ID+"</td></tr>"+
                    "<tr><td class='ajaxTd'>File&nbsp;Type # :</td><td class='ajaxTd'>"+FILE_TYPE+"</td></tr>"+
                    "<tr><td class='ajaxTd'>File&nbsp;Origin # :</td><td class='ajaxTd'>"+FILE_ORIGIN+"</td></tr>"+
                    "<tr><td class='ajaxTd'>Transaction Type:</td><td class='ajaxTd'>"+TRAN_MESS_TYPE+"</td></tr>";
                
                
                       var appfieldsList = detail.getElementsByTagName("APPFIELDS")[0];
          var appFiled = appfieldsList.getElementsByTagName("APPFIELD");
          for(var i=0;i<appFiled.length;i++) {
        //alert("123");
        var appFiledInfo = appFiled[i];
        var att = appFiledInfo.getAttribute("label");
        var name = appFiledInfo.firstChild.nodeValue;
        details = details + "<tr><td class='ajaxTd'>"+att+"&nbsp;:&nbsp;</td><td class='ajaxTd'><font>"+name+"</font></td></tr>";
    }
                   
                
                 details = details + "<tr><td class='ajaxTd'><b>Sender Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                       details = details + "<tr><td class='ajaxTd'> Id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+SENDER_ID+"</td></tr>";
                     //  details = details + "<tr><td class='ajaxTd'> Name&nbsp;:&nbsp;</td><td class='ajaxTd'>"+SENDER_NAME+"</td></tr>";
               
                       details = details + "<tr><td class='ajaxTd'><b>Receiver Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
               details = details + "<tr><td class='ajaxTd'>Id&nbsp;:&nbsp;</td><td class='ajaxTd'>"+RECEIVER_ID+"</td></tr>";
             //  details = details + "<tr><td class='ajaxTd'> Name&nbsp;:&nbsp;</td><td class='ajaxTd'>"+RECEIVER_NAME+"</td></tr>";
               
               details = details + "<tr><td class='ajaxTd'>IC #  :</td><td class='ajaxTd'>"+INTERCHANGE_CONTROLNO+"</td></tr>"+
                    "<tr><td class='ajaxTd'>FC#  :</td><td class='ajaxTd'>"+FUNCTIONAL_CONTROLNO+"</td></tr>"+
                    "<tr><td class='ajaxTd'>MC# :</td><td class='ajaxTd'>"+MESSAGE_CONTROLNO+"</td></tr>";
            
            details = details + "<tr><td class='ajaxTd'>DateTime&nbsp;#  :</td><td class='ajaxTd'>"+DATE_TIME_RECEIVED+"</td></tr>"+
                      "<tr><td class='ajaxTd'>Direction #  :</td><td class='ajaxTd'>"+DIRECTION+"</td></tr>";
                      
            details = details + "<tr><td class='ajaxTd'>Ack&nbsp;Status #  :</td><td class='ajaxTd'>"+ACK_STATUS+"</td></tr>"
                      
                      
            details = details + "<tr><td class='ajaxTd'>Isa&nbsp;Time #  :</td><td class='ajaxTd'>"+ISA_TIME+"</td></tr>"+
                      "<tr><td class='ajaxTd'>Isa&nbsp;Date #  :</td><td class='ajaxTd'>"+ISA_DATE+"</td></tr>";
            
            if(STATUS.toUpperCase() == "ERROR"){
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp;</td><td class='ajaxTd'><font color='red'>"+STATUS+"</font></td></tr>";
            }else if(STATUS.toUpperCase() == "SUCCESS"){
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp; </td><td class='ajaxTd'><font color='green'>"+STATUS+"</font></td></tr>";
            }else {
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp; </td><td class='ajaxTd'><font color='orange'>"+STATUS+"</font></td></tr>";
            }
                    //"<tr><td class='ajaxTd'><font color='red'>Status :</font></td><td class='ajaxTd'><font color='red'>"+STATUS+"</font></td></tr>";
    
        
            //alert(ERRMESSAGE);
            if(ERR_MESSAGE != "NO MSG"){
                details = details + "<tr><td class='ajaxTd' >Error&nbsp;Message&nbsp;:</td><td class='ajaxTd'> <font color='red'>"+ERR_MESSAGE+"</font></td></tr>";
            }
          
      /*     details = details + "<tr><td class='ajaxTd'>SAP_USER&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+SAP_USER+"</td></tr>"+
                      "<tr><td class='ajaxTd'>IDOC&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+IDOC_NUMBER+"</td></tr>";
                    details = details + "<tr><td class='ajaxTd'>PO&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+PO_NUMBER+"</td></tr>"+
                      "<tr><td class='ajaxTd'>PO_DATE&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+PO_DATE+"</td></tr>";
                    details = details + "<tr><td class='ajaxTd' colspan=2><b>IDOC&nbsp;STATUS:<b></td></tr>";
                    details = details + "<tr><td class='ajaxTd'>CODE&nbsp;# &nbsp;:</td><td class='ajaxTd'>"+IDOC_STATUS_CODE+"</td></tr>"+
                      "<tr><td class='ajaxTd'>DESCRIPTION&nbsp;#&nbsp;:</td><td class='ajaxTd'>"+IDOC_STATUS_DESCRIPTION+"</td></tr>";*/
          details = details+"</table>";
          
      //  }
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
      var details = " <h5 >Sorry ! No Results Found</h5>";
                    
    }
     var detailsDIV=document.getElementById("detailInformation");
	detailsDIV.innerHTML="";
	detailsDIV.innerHTML=details;
}

/*Method for Routing list
 * Date : 02/03/2015
 * Author : Santosh Kola
 */

function changeDestLabel(element) {
   // alert(element.value);
    if(element.value=='INBOUND'){
        document.getElementById("directionLabel").innerHTML = 'INBOUND';
    }else {
        document.getElementById("directionLabel").innerHTML = 'OUTBOUND';
    }
    
}

function loadDestLabel() {
   // alert(element.value);
   var direction = document.getElementById("direction").value;
    if(direction=='INBOUND'){
        document.getElementById("directionLabel").innerHTML = 'INBOUND';
    }else if(direction=='OUTBOUND'){
        document.getElementById("directionLabel").innerHTML = 'OUTBOUND';
    }
    
}

/*Displaying Partner details
 * Author : Santosh Kola
 * Date : 02/03/2015
 */

function getPartnerDetails(partnerId){
   // alert("hii");
    $(function() {
       
           $('#detail_box').show();
           return false;
               
   });
  var req = getXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populatePartnerDetails); 
    var url="../ajax/getPartnerDetails.action?partnerId="+partnerId;
    req.open("POST",url,"true");
   // req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populatePartnerDetails(responseXML)
{
     var details = responseXML.getElementsByTagName("DETAILS")[0];
     
    // alert("datails--->"+details);
     var detail = details.childNodes[0];
    var chk=detail.getElementsByTagName("VALID")[0];  
    
     if(chk.childNodes[0].nodeValue =="true") {
    
            var detail = details.childNodes[0];
            var TP_ID = detail.getElementsByTagName("TP_ID")[0].childNodes[0].nodeValue; 
            var TP_NAME = detail.getElementsByTagName("TP_NAME")[0].childNodes[0].nodeValue; 
            var INTERNALIDENTIFIER = detail.getElementsByTagName("INTERNALIDENTIFIER")[0].childNodes[0].nodeValue; 
            var APPLICATIONID = detail.getElementsByTagName("APPLICATIONID")[0].childNodes[0].nodeValue;
            var STATE = detail.getElementsByTagName("STATE")[0].childNodes[0].nodeValue;
            var MODIFIED_TS = detail.getElementsByTagName("MODIFIED_TS")[0].childNodes[0].nodeValue;
            var MODIFIED_BY = detail.getElementsByTagName("MODIFIED_BY")[0].childNodes[0].nodeValue; 
            var CREATED_TS = detail.getElementsByTagName("CREATED_TS")[0].childNodes[0].nodeValue;
           
             
             //alert("ACKFILEID-->"+ACKFILEID);
           // var routings = detail.getElementsByTagName("ROUTINGS")[0].childNodes[0].nodeValue;  
           
           var details="<table style='margin: 0 0 0 0;padding: 0px 0px;'>"+
                   "<tr><td class='ajaxTd'>Partner&nbsp;Id:</td><td class='ajaxTd'>"+TP_ID+"</td></tr>"+
                   "<tr><td class='ajaxTd'>Partner&nbsp;Name:</td><td class='ajaxTd'>"+TP_NAME+"</td></tr>"+
                    "<tr><td class='ajaxTd'>InternalIdentifier:</td><td class='ajaxTd'>"+INTERNALIDENTIFIER+"</td></tr>"+
                    "<tr><td class='ajaxTd'>ApplicationId:</td><td class='ajaxTd'>"+APPLICATIONID+"</td></tr>"+
                    "<tr><td class='ajaxTd'>State:</td><td class='ajaxTd'>"+STATE+"</td></tr>";
	          
                   // details = details + "<tr><td class='ajaxTd'><b>CreatedDate</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                   details = details + "<tr><td class='ajaxTd'>CreatedDate</td><td class='ajaxTd'>"+CREATED_TS+"</td></tr>";
                   details = details + "<tr><td class='ajaxTd'> ChangedDate:&nbsp;</td><td class='ajaxTd'>"+MODIFIED_TS+"</td></tr>";

                  
                   details = details + "<tr><td class='ajaxTd'>ChangedBy:&nbsp;</td><td class='ajaxTd'>"+MODIFIED_BY+"</td></tr>";
                 
       
            details = details + "</table>";
               // "<h5 >PRE_TRANS_FILE :&nbsp; &nbsp; <a href=\"../download/getAttachment.action?locationAvailable="+PRE_TRANS_FILEPATH+"\">PRE_TRANS_FILE</a></h5>"+
              //  "<h5 >POST_TRANS_FILE :&nbsp; &nbsp; <a href=\"../download/getAttachment.action?locationAvailable="+POST_TRANS_FILEPATH+"\">POST_TRANS_FILE</a></h5>";
               
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
      var details = " <h5 >Sorry ! No Results Found</h5>";
                    
    }
     var detailsDIV=document.getElementById("detailInformation");
	detailsDIV.innerHTML="";
	detailsDIV.innerHTML=details;
    /*  $(function() {
       
           $('#detail_box').show();
           return false;
               
   });*/
 
}

function getRoutingDetails(routingId){
   // alert("hii");
    $(function() {
       
           $('#detail_box').show();
           return false;
               
   });
  var req = getXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populateRoutingDetails); 
    var url="../ajax/getRoutingDetails.action?routingId="+routingId;
    req.open("POST",url,"true");
   // req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateRoutingDetails(responseXML)
{
     var details = responseXML.getElementsByTagName("DETAILS")[0];
     
    // alert("datails--->"+details);
     var detail = details.childNodes[0];
    var chk=detail.getElementsByTagName("VALID")[0];  
    
     if(chk.childNodes[0].nodeValue =="true") {
    
            var detail = details.childNodes[0];
            var ROUTER_NAME = detail.getElementsByTagName("ROUTER_NAME")[0].childNodes[0].nodeValue; 
            var STATUS = detail.getElementsByTagName("STATUS")[0].childNodes[0].nodeValue; 
            var ACCEPTORLOOKUPALIAS = detail.getElementsByTagName("ACCEPTORLOOKUPALIAS")[0].childNodes[0].nodeValue; 
            var INTERNALROUTEREMAIL = detail.getElementsByTagName("INTERNALROUTEREMAIL")[0].childNodes[0].nodeValue;
            var DESTMAILBOX = detail.getElementsByTagName("DESTMAILBOX")[0].childNodes[0].nodeValue;
            var SYSTEMTYPE = detail.getElementsByTagName("SYSTEMTYPE")[0].childNodes[0].nodeValue;
            var DIRECTION = detail.getElementsByTagName("DIRECTION")[0].childNodes[0].nodeValue; 
            var ENVELOPE = detail.getElementsByTagName("ENVELOPE")[0].childNodes[0].nodeValue;
            var CREATEDDATE = detail.getElementsByTagName("CREATEDDATE")[0].childNodes[0].nodeValue;
            var MODIFIEDDATE = detail.getElementsByTagName("MODIFIEDDATE")[0].childNodes[0].nodeValue;
            
           
             
             //alert("ACKFILEID-->"+ACKFILEID);
           // var routings = detail.getElementsByTagName("ROUTINGS")[0].childNodes[0].nodeValue;  
           
           var details="<table style='margin: 0 0 0 0;padding: 0px 0px;'>"+
                   "<tr><td class='ajaxTd'>Roter&nbsp;Name:</td><td class='ajaxTd'>"+ROUTER_NAME+"</td></tr>"+
                   "<tr><td class='ajaxTd'>Status:</td><td class='ajaxTd'>"+STATUS+"</td></tr>"+
                    "<tr><td class='ajaxTd'>Acceptor&nbsp;Lookup&nbsp;Alias:</td><td class='ajaxTd'>"+ACCEPTORLOOKUPALIAS+"</td></tr>"+
                    "<tr><td class='ajaxTd'>Internal&nbsp;Route&nbsp;Email:</td><td class='ajaxTd'>"+INTERNALROUTEREMAIL+"</td></tr>"+
                    "<tr><td class='ajaxTd'>DestMailBox:</td><td class='ajaxTd'>"+DESTMAILBOX+"</td></tr>";
	          
                   // details = details + "<tr><td class='ajaxTd'><b>CreatedDate</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                   details = details + "<tr><td class='ajaxTd'>SystemType:</td><td class='ajaxTd'>"+SYSTEMTYPE+"</td></tr>";
                   details = details + "<tr><td class='ajaxTd'> Direction:</td><td class='ajaxTd'>"+DIRECTION+"</td></tr>";

                  
                   details = details + "<tr><td class='ajaxTd'>Envelope:&nbsp;</td><td class='ajaxTd'>"+ENVELOPE+"</td></tr>";
                    details = details + "<tr><td class='ajaxTd'>CreatedDate:&nbsp;</td><td class='ajaxTd'>"+CREATEDDATE+"</td></tr>";
                     details = details + "<tr><td class='ajaxTd'>ChangedDate:&nbsp;</td><td class='ajaxTd'>"+MODIFIEDDATE+"</td></tr>";
                 
       
            details = details + "</table>";
               // "<h5 >PRE_TRANS_FILE :&nbsp; &nbsp; <a href=\"../download/getAttachment.action?locationAvailable="+PRE_TRANS_FILEPATH+"\">PRE_TRANS_FILE</a></h5>"+
              //  "<h5 >POST_TRANS_FILE :&nbsp; &nbsp; <a href=\"../download/getAttachment.action?locationAvailable="+POST_TRANS_FILEPATH+"\">POST_TRANS_FILE</a></h5>";
               
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
      var details = " <h5 >Sorry ! No Results Found</h5>";
                    
    }
     var detailsDIV=document.getElementById("detailInformation");
	detailsDIV.innerHTML="";
	detailsDIV.innerHTML=details;
    /*  $(function() {
       
           $('#detail_box').show();
           return false;
               
   });*/
 
}

function getB2bDetailInformation(b2bChannelId){
   // alert("hii");
    $(function() {
       
           $('#detail_box').show();
           return false;
               
   });
  var req = getXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populateB2bChannelDetails); 
    var url="../ajax/getB2bChannelDetails.action?b2bChannelId="+b2bChannelId;
    req.open("POST",url,"true");
   // req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateB2bChannelDetails(responseXML)
{
     var details = responseXML.getElementsByTagName("DETAILS")[0];
     
    // alert("datails--->"+details);
     var detail = details.childNodes[0];
    var chk=detail.getElementsByTagName("VALID")[0];  
    
     if(chk.childNodes[0].nodeValue =="true") {
    
            var detail = details.childNodes[0];
            var TP_ID = detail.getElementsByTagName("TP_ID")[0].childNodes[0].nodeValue; 
            var STATUS = detail.getElementsByTagName("STATUS")[0].childNodes[0].nodeValue; 
            var DIRECTION = detail.getElementsByTagName("DIRECTION")[0].childNodes[0].nodeValue; 
            var PROTOCOL = detail.getElementsByTagName("PROTOCOL")[0].childNodes[0].nodeValue;
            var HOST = detail.getElementsByTagName("HOST")[0].childNodes[0].nodeValue;
            var USERNAME = detail.getElementsByTagName("USERNAME")[0].childNodes[0].nodeValue;
            var PRODUCERMAILBOX = detail.getElementsByTagName("PRODUCERMAILBOX")[0].childNodes[0].nodeValue; 
            var CONSUMERMAILBOX = detail.getElementsByTagName("CONSUMERMAILBOX")[0].childNodes[0].nodeValue;
            var POOLINGCODE = detail.getElementsByTagName("POOLINGCODE")[0].childNodes[0].nodeValue;
            var APPID = detail.getElementsByTagName("APPID")[0].childNodes[0].nodeValue;
            var SENDERID = detail.getElementsByTagName("SENDERID")[0].childNodes[0].nodeValue;
            var RECEIVERID = detail.getElementsByTagName("RECEIVERID")[0].childNodes[0].nodeValue;
           // var APPID = detail.getElementsByTagName("APPID")[0].childNodes[0].nodeValue;
            
           
             
             //alert("ACKFILEID-->"+ACKFILEID);
           // var routings = detail.getElementsByTagName("ROUTINGS")[0].childNodes[0].nodeValue;  
           
           var details="<table style='margin: 0 0 0 0;padding: 0px 0px;'>"+
                   "<tr><td class='ajaxTd'>Partner&nbsp;Name:</td><td class='ajaxTd'>"+TP_ID+"</td></tr>"+
                   "<tr><td class='ajaxTd'>Status:</td><td class='ajaxTd'>"+STATUS+"</td></tr>"+
                    "<tr><td class='ajaxTd'>Direction:</td><td class='ajaxTd'>"+DIRECTION+"</td></tr>"+
                    "<tr><td class='ajaxTd'>Protocol:</td><td class='ajaxTd'>"+PROTOCOL+"</td></tr>"+
                    "<tr><td class='ajaxTd'>Host:</td><td class='ajaxTd'>"+HOST+"</td></tr>";
	          
                   // details = details + "<tr><td class='ajaxTd'><b>CreatedDate</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                   details = details + "<tr><td class='ajaxTd'>UserName:</td><td class='ajaxTd'>"+USERNAME+"</td></tr>";
                   details = details + "<tr><td class='ajaxTd'> ProduceMailBox:</td><td class='ajaxTd'>"+PRODUCERMAILBOX+"</td></tr>";

                  
                   details = details + "<tr><td class='ajaxTd'>ConsumerMailBox:&nbsp;</td><td class='ajaxTd'>"+CONSUMERMAILBOX+"</td></tr>";
                    details = details + "<tr><td class='ajaxTd'>PoolingCode:&nbsp;</td><td class='ajaxTd'>"+POOLINGCODE+"</td></tr>";
                     details = details + "<tr><td class='ajaxTd'>AppId:&nbsp;</td><td class='ajaxTd'>"+APPID+"</td></tr>";
                     details = details + "<tr><td class='ajaxTd'>SenderId:&nbsp;</td><td class='ajaxTd'>"+SENDERID+"</td></tr>";
                     details = details + "<tr><td class='ajaxTd'>ReceiverId:&nbsp;</td><td class='ajaxTd'>"+RECEIVERID+"</td></tr>";
                 
       
            details = details + "</table>";
               // "<h5 >PRE_TRANS_FILE :&nbsp; &nbsp; <a href=\"../download/getAttachment.action?locationAvailable="+PRE_TRANS_FILEPATH+"\">PRE_TRANS_FILE</a></h5>"+
              //  "<h5 >POST_TRANS_FILE :&nbsp; &nbsp; <a href=\"../download/getAttachment.action?locationAvailable="+POST_TRANS_FILEPATH+"\">POST_TRANS_FILE</a></h5>";
               
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
      var details = " <h5 >Sorry ! No Results Found</h5>";
                    
    }
     var detailsDIV=document.getElementById("detailInformation");
	detailsDIV.innerHTML="";
	detailsDIV.innerHTML=details;
    /*  $(function() {
       
           $('#detail_box').show();
           return false;
               
   });*/
 
}

/*Displaying DetailInfo 
 * Author : Santosh Kola
 * Date : 02/09/2015
 */


function getDeliveryChannelDetails(deliveryChannelId){
     // alert("hii");
    $(function() {
       
           $('#detail_box').show();
           return false;
               
   });
  var req = getXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populateDeliveryChannelDetails); 
    var url="../ajax/getDeliveryChannelDetails.action?deliveryChannelId="+deliveryChannelId;
    req.open("POST",url,"true");
   // req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


function populateDeliveryChannelDetails(responseXML)
{
     var details = responseXML.getElementsByTagName("DETAILS")[0];
     
    // alert("datails--->"+details);
     var detail = details.childNodes[0];
    var chk=detail.getElementsByTagName("VALID")[0];  
    
     if(chk.childNodes[0].nodeValue =="true") {
    
            var detail = details.childNodes[0];
            var PARTNER_ID = detail.getElementsByTagName("PARTNER_ID")[0].childNodes[0].nodeValue; 
            var PartnerName = detail.getElementsByTagName("PartnerName")[0].childNodes[0].nodeValue; 
            var ROUTER_NAME = detail.getElementsByTagName("ROUTER_NAME")[0].childNodes[0].nodeValue; 
            var bpName = detail.getElementsByTagName("bpName")[0].childNodes[0].nodeValue;
            var transName = detail.getElementsByTagName("transName")[0].childNodes[0].nodeValue;
            var demName = detail.getElementsByTagName("demName")[0].childNodes[0].nodeValue;
            var pmbName = detail.getElementsByTagName("pmbName")[0].childNodes[0].nodeValue; 
            var encodingName = detail.getElementsByTagName("encodingName")[0].childNodes[0].nodeValue;
            var SEQUENCE = detail.getElementsByTagName("SEQUENCE")[0].childNodes[0].nodeValue;
            var ARCHIVEFLAG = detail.getElementsByTagName("ARCHIVEFLAG")[0].childNodes[0].nodeValue;
            var ARCHIVEDIRCTORY = detail.getElementsByTagName("ARCHIVEDIRCTORY")[0].childNodes[0].nodeValue;
            var OUTPUTFILENAME = detail.getElementsByTagName("OUTPUTFILENAME")[0].childNodes[0].nodeValue;
             var OUTPUTFORMAT = detail.getElementsByTagName("OUTPUTFORMAT")[0].childNodes[0].nodeValue;
              var STATUS = detail.getElementsByTagName("STATUS")[0].childNodes[0].nodeValue;
              
           // var APPID = detail.getElementsByTagName("APPID")[0].childNodes[0].nodeValue;
            
           
             
             //alert("ACKFILEID-->"+ACKFILEID);
           // var routings = detail.getElementsByTagName("ROUTINGS")[0].childNodes[0].nodeValue;  
           
           var details="<table style='margin: 0 0 0 0;padding: 0px 0px;'>"+
                   "<tr><td class='ajaxTd'>Partner&nbsp;Name:</td><td class='ajaxTd'>"+PartnerName+"</td></tr>"+
                   "<tr><td class='ajaxTd'>RoutingName:</td><td class='ajaxTd'>"+ROUTER_NAME+"</td></tr>"+
                    "<tr><td class='ajaxTd'>BPName:</td><td class='ajaxTd'>"+bpName+"</td></tr>"+
                    "<tr><td class='ajaxTd'>TranlationMapName:</td><td class='ajaxTd'>"+transName+"</td></tr>"+
                    "<tr><td class='ajaxTd'>DocumentExtractMapName:</td><td class='ajaxTd'>"+demName+"</td></tr>";
	          
                   // details = details + "<tr><td class='ajaxTd'><b>CreatedDate</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                   details = details + "<tr><td class='ajaxTd'>ProducerMailBox:</td><td class='ajaxTd'>"+pmbName+"</td></tr>";
                   details = details + "<tr><td class='ajaxTd'> Encoding:</td><td class='ajaxTd'>"+encodingName+"</td></tr>";

                  
                   details = details + "<tr><td class='ajaxTd'>Sequence:&nbsp;</td><td class='ajaxTd'>"+SEQUENCE+"</td></tr>";
                    details = details + "<tr><td class='ajaxTd'>ArchiveFlag:&nbsp;</td><td class='ajaxTd'>"+ARCHIVEFLAG+"</td></tr>";
                     details = details + "<tr><td class='ajaxTd'>ArchiveDirectory:&nbsp;</td><td class='ajaxTd'>"+ARCHIVEDIRCTORY+"</td></tr>";
                     details = details + "<tr><td class='ajaxTd'>OutputFileName:&nbsp;</td><td class='ajaxTd'>"+OUTPUTFILENAME+"</td></tr>";
                     details = details + "<tr><td class='ajaxTd'>OutputFormat:&nbsp;</td><td class='ajaxTd'>"+OUTPUTFORMAT+"</td></tr>";
                      details = details + "<tr><td class='ajaxTd'>Status:&nbsp;</td><td class='ajaxTd'>"+STATUS+"</td></tr>";
                 
       
            details = details + "</table>";
               // "<h5 >PRE_TRANS_FILE :&nbsp; &nbsp; <a href=\"../download/getAttachment.action?locationAvailable="+PRE_TRANS_FILEPATH+"\">PRE_TRANS_FILE</a></h5>"+
              //  "<h5 >POST_TRANS_FILE :&nbsp; &nbsp; <a href=\"../download/getAttachment.action?locationAvailable="+POST_TRANS_FILEPATH+"\">POST_TRANS_FILE</a></h5>";
               
    } //if
    if(chk.childNodes[0].nodeValue =="false") {
      var details = " <h5 >Sorry ! No Results Found</h5>";
                    
    }
     var detailsDIV=document.getElementById("detailInformation");
	detailsDIV.innerHTML="";
	detailsDIV.innerHTML=details;
    /*  $(function() {
       
           $('#detail_box').show();
           return false;
               
   });*/
 
}



/*Method for displaying Dashboard details
 * Author : Santosh kola
 * Date : 02/19/2015
 */

function checkDates()
{
    //alert("hi");
     var docdatepickerfrom = document.getElementById('docdatepickerfrom').value;
     var docdatepicker = document.getElementById('docdatepicker').value;
     if(docdatepickerfrom == "" && docdatepicker == ""){
                 alert("Please enter Date From and Date To");
                return false;
      }
      var res = compareDates(docdatepickerfrom,docdatepicker);
     
     return res;
}


function getDashboardDeatls() {
  //  alert("hii");
     document.getElementById("tblCharts").style.display='none';
    var startDate = document.getElementById("docdatepickerfrom").value;
    var endDate = document.getElementById("docdatepicker").value;
  //  var docSenderId = document.getElementById("docSenderId").value;
  //  var direction = document.getElementById("direction").value;
   
     // if(!checkDates())
       if(startDate != "" && startDate != ""){
             if(!checkDates())
                // alert("Please enter Date From and Date To");
                return false;
      }
  
    var docType = document.getElementById("docType").value;
    
   // var ackStatus = document.getElementById("ackStatus").value;
    var status = document.getElementById("status").value;
      var partnerId = document.getElementById("partnerMapId").value;
      document.getElementById("gridDiv").style.display='block';
      
     var req = getXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerLoadText(req,populateDashboardDetails); 
   // var url="../ajax/getDashboardDetails.action?startDate="+startDate+"&endDate="+endDate+"&docType="+docType+"&ackStatus="+ackStatus+"&status="+status+"&partnerId="+partnerId+"&direction="+direction;
    var url="../ajax/getDashboardDetails.action?startDate="+startDate+"&endDate="+endDate+"&docType="+docType+"&status="+status+"&partnerId="+partnerId;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    
}



function populateDashboardDetails(resText){
    
      
          var response =resText.split("*");
          
          
           // var response = "6104917000CH|2^6183932991|3^925485US00|207^KNIG|58^LANDOLAKESDEMO|4^MSSDEMO|4^MSSDEMOSN|9^TPDEMORX|9^UNILEVER_E2U|58^*6104917000CH|4^6183932991|4^925485US00|0^KNIG|31^LANDOLAKESDEMO|6^MSSDEMO|6^MSSDEMOSN|1^TPDEMORX|1^UNILEVER_E2U|31^".split("*");
          document.getElementById("inboundTrans").value = response[0];
              document.getElementById("outboundTrans").value = response[1];
               document.getElementById("tblCharts").style.display='block';
             // google.setOnLoadCallback(drawInboundChart);
	//  google.setOnLoadCallback(drawOutboundChart);
          
          drawInboundChart();
          drawOutboundChart();
               //document.getElementById("tblCharts").style.display='block';
            //   google.setOnLoadCallback(drawInboundChart);
	  //google.setOnLoadCallback(drawOutboundChart);
    //alert(resText);
}

 function drawInboundChart() {
 
  //alert(response[0]);
  //alert(response[1]);
  
 
  var element = document.getElementById("inboundTrans").value;
  
 //  alert(element);
  var inboudResponse =element.split("^") ;
  
  
 var arraydata = [['PartnerName', 'INBOUND', { role: 'annotation' }]];
       /* var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['Work',     11],
          ['Eat',      2],
          ['Commute',  2],
          ['Watch TV', 2],
          ['Sleep',    7]
        ]);
*/
 
    for(var i=0; i<inboudResponse.length-1; i++){
        var res = inboudResponse[i].split("|");
        var dArray = [res[0],parseInt(res[1]), ''];
        arraydata.push(dArray);
    }

	var data = google.visualization.arrayToDataTable(arraydata);

        var options = {
          title: 'Partner Inbound Transcations'
          
        };

        var chart = new google.visualization.PieChart(document.getElementById('inboundPiechart'));
        //var chart1 = new google.visualization.ColumnChart(document.getElementById('inboundPiechart'));
        //chart.draw(data, options);
        chart.draw(data, options);
       // chart1.draw(data, options);
      }
	  
	  //----------------------
	  function drawOutboundChart() {
              var element = document.getElementById("outboundTrans").value;
             // alert(element);
          
 var outboudResponse =element.split("^") ;
  
 var arraydata = [['PartnerName', 'OUTBOUND']];
       /* var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['Work',     11],
          ['Eat',      2],
          ['Commute',  2],
          ['Watch TV', 2],
          ['Sleep',    7]
        ]);
*/
 
    for(var i=0; i<outboudResponse.length-1; i++){
        var res = outboudResponse[i].split("|");
        var dArray = [res[0],parseInt(res[1])];
        arraydata.push(dArray);
    }

	var data = google.visualization.arrayToDataTable(arraydata);

        var options = {
          title: 'Partner Outbound Transcations'
        };

        var chart = new google.visualization.PieChart(document.getElementById('outboundPiechart'));
        //var chart1 = new google.visualization.ColumnChart(document.getElementById('outboundPiechart'));
        chart.draw(data, options);
        //chart1.draw(data, options);
      }
      
      
//  new classe for schdular tasks
  function getDeleteReport(id){
   document.getElementById("load").style.display='block';
   var req = getXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerLoadText(req,populateReportDeleteDetails); 
   
   
   var url="../ajax/getReportDeleteDetails.action?&id="+id;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

  
 function populateReportDeleteDetails(responsetext)
{
    //alert(responsetext);
    document.getElementById("reportsattachForm").submit();
   
}


 function BDMOverlay(id){
    var overlay = document.getElementById('overlay');
    var specialBox1 = document.getElementById('specialBox');
    
    document.getElementById("headerLabel").style.color="white";
    document.getElementById("headerLabel").innerHTML="Download Report";
   
   
     
    overlay.style.opacity = -10;
    if(overlay.style.display == "block"){
        overlay.style.display = "none";
        specialBox1.style.display = "none";
    } else {
        overlay.style.display = "block";
        getOverlay(id);
        specialBox1.style.display = "block";
    }
    
    
    
}

function getOverlay(id){
   var startDate=document.getElementById("schStartdate").value; 
    //document.getElementById("load").style.display='block';
   var req = getXMLHttpRequest();
   document.getElementById("scheduleid").value=id;
   req.onreadystatechange = readyStateHandlerString(req,populateOverlayDetails); 
   
   
   var url="../ajax/getReportOverlayDetails.action?&id="+id+"&startDate="+startDate;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateOverlayDetails(responseText)
{
    //alert("test");
    //alert("response data--->"+responseText);
    
    if(responseText!="Nodata"){
        document.getElementById("downloadLink").style.display='block';
         document.getElementById("downloadMessage").style.display='none';
    }
    else{
        document.getElementById("downloadLink").style.display='none';
        document.getElementById("downloadMessage").style.display='block';
        document.getElementById("downloadMessage").innerHTML="<font color='red'>No reports to Download</font>"
    }
    
    
   
}

function DownloadSchedulerReport(){
    //alert("DownloadSchedulerReport");
    var startDate=document.getElementById("schStartdate").value;
    var scheduleId=document.getElementById("scheduleid").value;
     window.location = "../download/reportDownloads.action?scheduleId="+scheduleId+"&startDate="+startDate;
} 


function CalenderOnChange(){
    //alert(x.value);
    //alert("CalenderOnChange");
   var startDate=document.getElementById("schStartdate").value; 
   //alert("date"+startDate);
   
   var id=document.getElementById("scheduleid").value;
    //document.getElementById("load").style.display='block';
    
   var req = getXMLHttpRequest();
   document.getElementById("scheduleid").value=id;
   req.onreadystatechange = readyStateHandlerString(req,populateOverlayDetails); 
   
   //alert("step1");
   var url="../ajax/getReportOverlayDetails.action?&id="+id+"&startDate="+startDate;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}











