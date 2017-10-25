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

/**
 * For doc Ajax call
 * 
 */
function getlfcPODetails(number,id,type){
    var num=number;
    var id=id;
    var type = type;
    $(function() {
       
           $('#detail_box').show();
           return false;
               
   });
   // alert("in js--->"+num+"-----"+type);
   var req = getXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerText(req,populateLifecycle); 
   var url="../ajax/LifecycleDetails.action?poNumber="+num+"&fileId="+id+"&type="+type;
   req.open("GET",url,"true");
   // req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}



function populateLifecycle(responseXML)
{
  // alert("responseXML--->"+responseXML);
    var details = responseXML.getElementsByTagName("DETAILS")[0];
    var detail = details.childNodes[0];
    var chk=detail.getElementsByTagName("VALID")[0];  
    
     if(chk.childNodes[0].nodeValue =="true") {
    
            var detail = details.childNodes[0];
            var fileid = detail.getElementsByTagName("FILEID")[0].childNodes[0].nodeValue;   
          //  var docType = detail.getElementsByTagName("FILETYPE")[0].childNodes[0].nodeValue; 
            
            var PRE_TRANS_FILEPATH = detail.getElementsByTagName("PRETRANSFILEPATH")[0].childNodes[0].nodeValue;
            var POST_TRANS_FILEPATH = detail.getElementsByTagName("PRETRANSFILEPATH")[0].childNodes[0].nodeValue; 
            //var Status = detail.getElementsByTagName("S")[0].childNodes[0].nodeValue; 
            var ACKFILEID = detail.getElementsByTagName("ACKFILE")[0].childNodes[0].nodeValue; 
            var SENDER_ID = detail.getElementsByTagName("SENDER_ID")[0].childNodes[0].nodeValue;
            var RECEIVER_ID = detail.getElementsByTagName("RECEIVER_ID")[0].childNodes[0].nodeValue;
            
            var SENDER_NAME = detail.getElementsByTagName("SENDER_NAME")[0].childNodes[0].nodeValue;
            var RECEIVER_NAME = detail.getElementsByTagName("RECEIVER_NAME")[0].childNodes[0].nodeValue;
            
            var TRAN_NUMBER = detail.getElementsByTagName("TRAN_NUMBER")[0].childNodes[0].nodeValue; 
            var ORG_FILEPATH = detail.getElementsByTagName("ORGFILEPATH")[0].childNodes[0].nodeValue; 
            
            var dttm = detail.getElementsByTagName("DATETIME")[0].childNodes[0].nodeValue; 
            var STATUS = detail.getElementsByTagName("STATUS")[0].childNodes[0].nodeValue; 
            /*
             * 
             * newly Added
             */
            var PO_NUMBER = detail.getElementsByTagName("PO_NUMBER")[0].childNodes[0].nodeValue; 
            var PO_DATE = detail.getElementsByTagName("PO_DATE")[0].childNodes[0].nodeValue;
            var PO_STATUS = detail.getElementsByTagName("PO_STATUS")[0].childNodes[0].nodeValue;
            var SO_NUMBER = detail.getElementsByTagName("SO_NUMBER")[0].childNodes[0].nodeValue;
            var SAPIDOC_NUMBER = detail.getElementsByTagName("SAPIDOC_NUMBER")[0].childNodes[0].nodeValue;
            var ITEM_QTY = detail.getElementsByTagName("ITEM_QTY")[0].childNodes[0].nodeValue;
            
            var ASN_NUMBER = detail.getElementsByTagName("ASN_NUMBER")[0].childNodes[0].nodeValue;
            var BOL_NUMBER = detail.getElementsByTagName("BOL_NUMBER")[0].childNodes[0].nodeValue;
            var ISA_NUMBER = detail.getElementsByTagName("ISA_NUMBER")[0].childNodes[0].nodeValue;
            var ISA_DATE = detail.getElementsByTagName("ISA_DATE")[0].childNodes[0].nodeValue;
            var ISA_TIME = detail.getElementsByTagName("ISA_TIME")[0].childNodes[0].nodeValue;
            var INV_NUMBER = detail.getElementsByTagName("INV_NUMBER")[0].childNodes[0].nodeValue;
            var INV_AMOUNT = detail.getElementsByTagName("INV_AMOUNT")[0].childNodes[0].nodeValue;
            var CHEQUE_NUMBER = detail.getElementsByTagName("CHEQUE_NUMBER")[0].childNodes[0].nodeValue;
            var TRANS_TYPE = detail.getElementsByTagName("TRANS_TYPE")[0].childNodes[0].nodeValue;
            var DIRECTION = detail.getElementsByTagName("DIRECTION")[0].childNodes[0].nodeValue;
          
          var details = " <table style='margin: 0 0 0 0;padding: 0px 0px;'><tr><td class='ajaxTd'>Instance Id # :&nbsp; &nbsp; </td><td class='ajaxTd'>"+fileid+"</td></tr>";
                        //"<tr><td class='ajaxTd'>Date Time :&nbsp; &nbsp;</td><td class='ajaxTd'>"+dttm+"</td></tr>";
                    if(PO_NUMBER != 0){
               details = details + "<tr><td class='ajaxTd'>PO # :&nbsp; &nbsp;</td><td class='ajaxTd'>"+PO_NUMBER+"</td></tr>";
                }
                if(PO_DATE != 0){
               details = details + "<tr><td class='ajaxTd'>PO Date :&nbsp; &nbsp;</td><td class='ajaxTd'>"+PO_DATE+"</td></tr>";
                }
                if(PO_STATUS != 0){
               details = details + "<tr><td class='ajaxTd'>PO Status :&nbsp; &nbsp;</td><td class='ajaxTd'>"+PO_STATUS+"</td></tr>";
                }
              
                 if(ASN_NUMBER != 0){
               details = details + "<tr><td class='ajaxTd'>ASN # :&nbsp; &nbsp;</td><td class='ajaxTd'>"+ASN_NUMBER+"</td></tr>";
                }
                 if(INV_NUMBER != 0){
               details = details + "<tr><td class='ajaxTd'>Invoice # :&nbsp; &nbsp;</td><td class='ajaxTd'>"+INV_NUMBER+"</td></tr>";
                }
                if(INV_AMOUNT != 0){
               details = details + "<tr><td class='ajaxTd'>Invoice&nbsp;Amount&nbsp;:</td><td class='ajaxTd'>"+INV_AMOUNT+"</td></tr>";
                }
                if(CHEQUE_NUMBER != 0){
               details = details + "<tr><td class='ajaxTd'>Cheque # :&nbsp; &nbsp;</td><td class='ajaxTd'>"+CHEQUE_NUMBER+"</td></tr>";
                }
                if(SO_NUMBER != 0){
               details = details + "<tr><td class='ajaxTd'>SO # :&nbsp; &nbsp;</td><td class='ajaxTd'>"+SO_NUMBER+"</td></tr>";
                }
                if(SAPIDOC_NUMBER != 0){
               details = details + "<tr><td class='ajaxTd'>SAPIDOC # :&nbsp; &nbsp;</td><td class='ajaxTd'>"+SAPIDOC_NUMBER+"</td></tr>";
                }
               
                if(BOL_NUMBER != 0){
               details = details + "<tr><td class='ajaxTd'>BOL # :&nbsp; &nbsp;</td><td class='ajaxTd'>"+BOL_NUMBER+"</td></tr>";
                }
                 
                if(ITEM_QTY != 0){
               details = details + "<tr><td class='ajaxTd'>PO&nbsp;Qty :&nbsp; &nbsp;</td><td class='ajaxTd'>"+ITEM_QTY+"</td></tr>";   
                }
                
                if(TRANS_TYPE != 0){
               details = details + "<tr><td class='ajaxTd'>Transaction&nbsp;Type:</td><td class='ajaxTd'>"+TRANS_TYPE+"</td></tr>";
                }
                
               if(SENDER_ID != 0){
                   
               // details = details + "<tr><td class='ajaxTd'>Sender Info :&nbsp; &nbsp;</td><td class='ajaxTd'>"+SENDER_ID+"_"+SENDER_NAME+"</td></tr>";
                
               // alert("direction--->"+DIRECTION);
                      if(DIRECTION == "inbound"){
                          details = details + "<tr><td class='ajaxTd'><b>Partner Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                      }else{
                          details = details + "<tr><td class='ajaxTd'><b>Sender Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                      }
                
               //details = details + "<tr><td class='ajaxTd'><b>Sender Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
               details = details + "<tr><td class='ajaxTd'> Id&nbsp;: &nbsp;</td><td class='ajaxTd'>"+SENDER_ID+"</td></tr>";
               details = details + "<tr><td class='ajaxTd'> Name&nbsp;: &nbsp;</td><td class='ajaxTd'>"+SENDER_NAME+"</td></tr>";
               }
               if(RECEIVER_ID != 0){
               //details = details + "<tr><td class='ajaxTd'>Receiver Info :&nbsp; &nbsp;</td><td class='ajaxTd'>"+RECEIVER_ID+"_"+RECEIVER_NAME+"</td></tr>";
                if(DIRECTION == "outbound"){
                          details = details + "<tr><td class='ajaxTd'><b>Partner Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                      }else{
                          details = details + "<tr><td class='ajaxTd'><b>Sender Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
                      }
              // details = details + "<tr><td class='ajaxTd'><b>Receiver Info</b> :&nbsp; &nbsp;</td><td class='ajaxTd'></td></tr>";
               details = details + "<tr><td class='ajaxTd'>Id&nbsp;: &nbsp;</td><td class='ajaxTd'>"+RECEIVER_ID+"</td></tr>";
               details = details + "<tr><td class='ajaxTd'> Name&nbsp;: &nbsp;</td><td class='ajaxTd'>"+RECEIVER_NAME+"</td></tr>";
               }
                
        
                if(ISA_NUMBER != 0){
               details = details + "<tr><td class='ajaxTd'>ISA # :&nbsp; &nbsp;</td><td class='ajaxTd'>"+ISA_NUMBER+"</td></tr>";
                }
                if(ISA_DATE != 0){
               details = details + "<tr><td class='ajaxTd'>ISA Date :&nbsp; &nbsp;</td><td class='ajaxTd'>"+ISA_DATE+"</td></tr>";
                }
                if(ISA_TIME != 0){
               details = details + "<tr><td class='ajaxTd'>ISA Time :&nbsp; &nbsp;</td><td class='ajaxTd'>"+ISA_TIME+"</td></tr>";
                }
               
              
               
                //alert("TRAN_NUMBER--->"+TRANS_TYPE);
          /*  if(TRANS_TYPE!="856" && TRANS_TYPE!="810"){
            if(ORG_FILEPATH == "No File"){
                details = details + "<tr><td class='ajaxTd'>Original File&nbsp;:</td><tdclass='ajaxTd'> -- </td></tr>";
            }else{
                details = details + "<tr><td class='ajaxTd'>Original File&nbsp;:</td><td class='ajaxTd'><a href=\"../download/getAttachment.action?locationAvailable="+ORG_FILEPATH+"\">Download</a></td></tr>";
            }
            }*/
        
            if(STATUS.toUpperCase() == "ERROR"){
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp;</td><td class='ajaxTd'><font color='red'>"+STATUS+"</font></td></tr>";
            }else if(STATUS.toUpperCase() == "SUCCESS"){
                details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp; </td><td class='ajaxTd'><font color='green'>"+STATUS+"</font></td></tr>";
            }else {
               details = details + "<tr><td class='ajaxTd'>Status :&nbsp; &nbsp; </td><td class='ajaxTd'><font color='orange'>"+STATUS+"</font></td></tr>"; 
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
            
           
            
            if(TRANS_TYPE!="856" && TRANS_TYPE!="810"){
             if(ACKFILEID == "No File"){
                details = details + "<tr><td class='ajaxTd'>997&nbsp;Ack&nbsp;File&nbsp;:</td><td style='background-color: white;'> -- </td></tr>";
            }else{
                details = details + "<tr><td class='ajaxTd'>997&nbsp;Ack&nbsp;File&nbsp;:</td><td class='ajaxTd'><a href=\"../download/getAttachment.action?locationAvailable="+ACKFILEID+"\">Download</a></td></tr></table>";
            }
            }
           
          
      
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

