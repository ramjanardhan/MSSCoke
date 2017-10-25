/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function newXMLHttpRequest1() {
    var xmlreq = false;
    if(window.XMLHttpRequest) {
        xmlreq = new XMLHttpRequest();
    } else if(window.ActiveXObject) {
        try {
            xmlreq = new ActiveXObject("MSxm12.XMLHTTP");
        } catch(e1) {
            try {
                xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
            } catch(e2) {
                xmlreq = false;
            }
        }
    }
    return xmlreq;
}
function routertoggleOverlay(element){
	var overlay = document.getElementById('routeroverlay');
	var specialBox = document.getElementById('routerspecialBox');
          var routerId = document.getElementById('routerMap').value;
        if(element==2){
          //  var overlay = document.getElementById('profileoverlay1');
	//var specialBox = document.getElementById('profilespecialBox1');
        
         
    document.getElementById('routerId').value = routerId;
        document.getElementById('routingName').value = document.getElementById('routerMap').options[document.getElementById('routerMap').selectedIndex].text;
      //  var routerId=document.getElementById('routingName').value;
       //alert( document.getElementById('partnerMapId').options[document.getElementById('partnerMapId').selectedIndex].text );
	
        var req = newXMLHttpRequest1();
   req.onreadystatechange = readyStateHandlerText1(req,populateRouterDetails);
    //var url=CONTENXT_PATH+"/getRouterInfo.action?routerId="+routerId;
    var url="../ajax/getRouterInfo.action?routerId="+routerId;

    req.open("POST",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
        }
        
        
        
        
        
	overlay.style.opacity = .8;
	if(overlay.style.display == "block"){
		overlay.style.display = "none";
		specialBox.style.display = "none";
	} else {
		overlay.style.display = "block";
		specialBox.style.display = "block";
	}
}

/** profile **/

function readyStateHandlerText(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                (document.getElementById("partnerImage")).style.display = "none";
              //  (document.getElementById("routerImage")).style.display = "none";
                responseTextHandler(req.responseText);
            } else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
            (document.getElementById("partnerImage")).style.display = "block";
           //  (document.getElementById("routerImage")).style.display = "block";
        }
    }
}
function readyStateHandlerText1(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
             //   (document.getElementById("partnerImage")).style.display = "none";
                (document.getElementById("routerImage")).style.display = "none";
                responseTextHandler(req.responseText);
            } else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
            //(document.getElementById("partnerImage")).style.display = "block";
             (document.getElementById("routerImage")).style.display = "block";
        }
    }
}
function partnertoggleOverlay(element){
   // alert(element);
	var overlay = document.getElementById('partneroverlay');
	var specialBox = document.getElementById('partnerspecialBox');
        var partnerId = document.getElementById('partnerMapId').value;
       if(element==2){
        document.getElementById('partnerId').value = partnerId;
        document.getElementById('partnerName').value = document.getElementById('partnerMapId').options[document.getElementById('partnerMapId').selectedIndex].text;
        
       //alert( document.getElementById('partnerMapId').options[document.getElementById('partnerMapId').selectedIndex].text );
	//alert(partnerId);
        var req = newXMLHttpRequest1();
   req.onreadystatechange = readyStateHandlerText(req,populatePartnerDetails);
    var url="../ajax/getPartnerInfo.action?partnerId="+partnerId;
    req.open("POST",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
        
}
        
        
        overlay.style.opacity = .8;
	if(overlay.style.display == "block"){
		overlay.style.display = "none";
		specialBox.style.display = "none";
	} else {
		overlay.style.display = "block";
		specialBox.style.display = "block";
	}
}


function populatePartnerDetails(resText){
    document.getElementById('partnerDetails').innerHTML="SenderIdentfier:"+resText.split("|")[1]+"<br>"+"ReceiverIdentfier:"+resText.split("|")[2]+"<br>ApplicationIdentfier:"+resText.split("|")[0];
    //alert(resText);
}

 function populateRouterDetails(resText){
   document.getElementById('routerDetails').innerHTML="Acceptor&nbsp;Lookup&nbsp;Alias:"+resText.split("|")[0]+"<br>"+"Mailbox:"+resText.split("|")[1];
}


/*Business Process Name Overlay ajax calls
 * Date : 02/04/2015
 * 
 */
function readyStateHandlerText2(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
             //   (document.getElementById("partnerImage")).style.display = "none";
                (document.getElementById("businessImage")).style.display = "none";
                responseTextHandler(req.responseText);
            } else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
            //(document.getElementById("partnerImage")).style.display = "block";
             (document.getElementById("businessImage")).style.display = "block";
        }
    }
}
function businesstoggleOverlay(element){
   // alert(element);
	var overlay = document.getElementById('businessoverlay');
	var specialBox = document.getElementById('businessspecialBox');
        var businessProcessMapId = document.getElementById('businessProcessMapId').value;
       if(element==2){
        document.getElementById('businessProcessId').value = businessProcessMapId;
        document.getElementById('businessProcessName').value = document.getElementById('businessProcessMapId').options[document.getElementById('businessProcessMapId').selectedIndex].text;
        //alert("businessProcessMapId-->"+businessProcessMapId);
       //alert( document.getElementById('partnerMapId').options[document.getElementById('partnerMapId').selectedIndex].text );
	//alert(partnerId);
        var req = newXMLHttpRequest1();
   req.onreadystatechange = readyStateHandlerText2(req,populateBusinessProcessDetails);
    var url="../ajax/getBusinessProcessInfo.action?businessProcessId="+businessProcessMapId;
    req.open("POST",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
        
}
        
        
        overlay.style.opacity = .8;
	if(overlay.style.display == "block"){
		overlay.style.display = "none";
		specialBox.style.display = "none";
	} else {
		overlay.style.display = "block";
		specialBox.style.display = "block";
	}
}
 function populateBusinessProcessDetails(resText){
   document.getElementById('businessDetails').innerHTML="Invoke&nbsp;Method:"+resText.split("|")[0]+"<br>"+"Multiple&nbsp;Messages:"+resText.split("|")[1]+"<br>"+"Adaptor:"+resText.split("|")[2];
  //  document.getElementById('translationMapName').value = resText.split("|")[3];
  //   document.getElementById('docExtractMapName').value = resText.split("|")[4];
     // document.getElementById('producerMailBox').value = resText.split("|")[5];
}


/*Translation map name Overlay ajax calls
 * Date : 02/04/2015
 * 
 */

function translationtoggleOverlay(element){
   // alert(element);
	var overlay = document.getElementById('translationoverlay');
	var specialBox = document.getElementById('translationspecialBox');
        var translationMapId = document.getElementById('translationMapId').value;
       if(element==2){
        document.getElementById('translationId').value = translationMapId;
        document.getElementById('translationMapName').value = document.getElementById('translationMapId').options[document.getElementById('translationMapId').selectedIndex].text;
  
        
}
        
        
        overlay.style.opacity = .8;
	if(overlay.style.display == "block"){
		overlay.style.display = "none";
		specialBox.style.display = "none";
	} else {
		overlay.style.display = "block";
		specialBox.style.display = "block";
	}
}

/*Document Extarct map name Overlay ajax calls
 * Date : 02/04/2015
 * 
 */

function documenttoggleOverlay(element){
   // alert(element);
	var overlay = document.getElementById('documentoverlay');
	var specialBox = document.getElementById('documentspecialBox');
        var documentExtractMapId = document.getElementById('documentExtractMapId').value;
       if(element==2){
        document.getElementById('documentExtarctId').value = documentExtractMapId;
        document.getElementById('docExtractMapName').value = document.getElementById('documentExtractMapId').options[document.getElementById('documentExtractMapId').selectedIndex].text;
  
        
}
        
        
        overlay.style.opacity = .8;
	if(overlay.style.display == "block"){
		overlay.style.display = "none";
		specialBox.style.display = "none";
	} else {
		overlay.style.display = "block";
		specialBox.style.display = "block";
	}
}


/*Producer mail box Overlay ajax calls
 * Date : 02/04/2015
 * 
 */

function producertoggleOverlay(element){
   // alert(element);
	var overlay = document.getElementById('produceroverlay');
	var specialBox = document.getElementById('producerspecialBox');
        var producerMailMapId = document.getElementById('producerMailMapId').value;
       if(element==2){
        document.getElementById('producerMailBoxId').value = producerMailMapId;
        document.getElementById('producerMailBox').value = document.getElementById('producerMailMapId').options[document.getElementById('producerMailMapId').selectedIndex].text;
  
        
}
        
        
        overlay.style.opacity = .8;
	if(overlay.style.display == "block"){
		overlay.style.display = "none";
		specialBox.style.display = "none";
	} else {
		overlay.style.display = "block";
		specialBox.style.display = "block";
	}
}



function encodingtoggleOverlay(element){
   // alert(element);
	var overlay = document.getElementById('encodingoverlay');
	var specialBox = document.getElementById('encodingspecialBox');
        var producerMailMapId = document.getElementById('encodingMailMapId').value;
       if(element==2){
        document.getElementById('encodingMailBoxId').value = producerMailMapId;
        document.getElementById('encodingMailBoxName').value = document.getElementById('encodingMailMapId').options[document.getElementById('encodingMailMapId').selectedIndex].text;
  
        
}
        
        
        overlay.style.opacity = .8;
	if(overlay.style.display == "block"){
		overlay.style.display = "none";
		specialBox.style.display = "none";
	} else {
		overlay.style.display = "block";
		specialBox.style.display = "block";
	}

}


function getErrorMessage(status,errorMsg){
   // alert(element);
	var overlay = document.getElementById('erroroverlay');
	var specialBox = document.getElementById('errorspecialBox');
        document.getElementById("errorMessage").innerHTML = errorMsg;
        
        overlay.style.opacity = .8;
	if(overlay.style.display == "block"){
		overlay.style.display = "none";
		specialBox.style.display = "none";
	} else {
		overlay.style.display = "block";
		specialBox.style.display = "block";
	}
}