
function gridDownload(sheetType,dwdType) {
 
    window.location="../download/gridDownload.action?downloadType="+dwdType+"&sheetType="+sheetType;
}

function gridDashboardDownload(sheetType,dwdType,inbound,outbound) {
    /*alert(inbound);
    alert(outbound);*/
    if(inbound==''&&outbound=='')
    {
        alert("No INBOUND and OUTBOUND data to generate");        
    }
    
    
    else
    {
        window.location="../download/gridDownload.action?downloadType="+dwdType+"&sheetType="+sheetType+"&inbound="+inbound+"&outbound="+outbound;
    }
}

/*function gridDownloadReports(sheetType,dwdType) {
 
    window.location="../download/gridDownloadReports.action?downloadType="+dwdType+"&sheetType="+sheetType;
}*/

function gridDownloadForLtInvoice(sheetType,dwdType) {
    var dateFrom = document.getElementById("datepickerfrom").value; 
    var dateTo = document.getElementById("datepickerTo").value; 
    var senderId = document.getElementById("invSenderId").value; 
    var senderName = document.getElementById("invSenderName").value; 
    //alert("senderName --->"+senderName);
    var receiverId = document.getElementById("invRecId").value; 
    var receiverName = document.getElementById("invRecName").value;
    //alert("receiverName --->"+receiverName);
    var corrattribute = document.getElementById("corrattribute").value; 
    var corrvalue = document.getElementById("corrvalue").value; 
    var corrattribute1 = document.getElementById("corrattribute1").value; 
    var corrvalue1 = document.getElementById("corrvalue1").value; 
    var docType = document.getElementById("docType").value; 
    var status = document.getElementById("status").value; 
    window.location="../download/gridDownload.action?downloadType="+dwdType+"&sheetType="+sheetType+"&dateFrom="+dateFrom+"&dateTo="+dateTo+"&senderId="+senderId+"&senderName="+senderName+"&receiverId="+receiverId+"&receiverName="+receiverName+"&corrattribute="+corrattribute+"&corrvalue="+corrvalue+"&corrattribute1="+corrattribute1+"&corrvalue1="+corrvalue1+"&docType="+docType+"&status="+status;
}

function gridDownloadforLtdocrepo(sheetType,dwdType) {
    var dateFrom = document.getElementById("docdatepickerfrom").value; 
    var dateTo = document.getElementById("docdatepicker").value; 
    var senderId = document.getElementById("docSenderId").value; 
    var senderName = document.getElementById("docSenderName").value; 
   // alert("The doc sender name ----->"+senderName);
    var receiverId = document.getElementById("docBusId").value; 
    // alert("docBusId---------->"+receiverId);
    var receiverName = document.getElementById("docRecName").value; 
    var corrattribute = document.getElementById("corrattribute").value; 
    var corrvalue = document.getElementById("corrvalue").value; 
    var corrattribute1 = document.getElementById("corrattribute1").value; 
    var corrvalue1 = document.getElementById("corrvalue1").value; 
    var corrattribute2 = document.getElementById("corrattribute2").value; 
    var corrvalue2 = document.getElementById("corrvalue2").value; 
    var docType = document.getElementById("docType").value; 
    var status = document.getElementById("status").value; 
    window.location="../download/gridDownload.action?downloadType="+dwdType+"&sheetType="+sheetType+"&dateFrom="+dateFrom+"&dateTo="+dateTo+"&senderId="+senderId+"&senderName="+senderName+"&receiverId="+receiverId+"&receiverName="+receiverName+"&corrattribute="+corrattribute+"&corrvalue="+corrvalue+"&corrattribute1="+corrattribute1+"&corrattribute2="+corrattribute2+"&corrvalue2="+corrvalue2+"&corrvalue1="+corrvalue1+"&docType="+docType+"&status="+status;
}

function gridDownloadforLtReports(sheetType,dwdType) {
    var dateFrom = document.getElementById("docdatepickerfrom").value; 
    var dateTo = document.getElementById("docdatepicker").value; 
    var senderId = document.getElementById("docSenderId").value; 
    var senderName = document.getElementById("docSenderName").value; 
    var receiverId = document.getElementById("docBusId").value; 
    var receiverName = document.getElementById("docRecName").value; 
    //    var corrattribute = document.getElementById("corrattribute").value; 
    //    var corrvalue = document.getElementById("corrvalue").value; 
    //    var corrattribute1 = document.getElementById("corrattribute1").value; 
    //    var corrvalue1 = document.getElementById("corrvalue1").value; 
    //    var corrattribute2 = document.getElementById("corrattribute1").value; 
    //    var corrvalue2 = document.getElementById("corrvalue1").value; 
    var docType = document.getElementById("docType").value; 
    var status = document.getElementById("status").value; 
    window.location="../download/gridDownload.action?downloadType="+dwdType+"&sheetType="+sheetType+"&dateFrom="+dateFrom+"&dateTo="+dateTo+"&senderId="+senderId+"&senderName="+senderName+"&receiverId="+receiverId+"&receiverName="+receiverName+"&docType="+docType+"&status="+status;
}

function gridDownloadforLtAsnRepo(sheetType,dwdType) {
    var dateFrom = document.getElementById("datepickerfrom").value; 
    var dateTo = document.getElementById("datepickerTo").value; 
    var senderId = document.getElementById("senderId").value; 
    var senderName = document.getElementById("senderName").value; 
    var receiverId = document.getElementById("buId").value; 
    //alert("receiverId --->"+receiverId);
    var receiverName = document.getElementById("recName").value; 
    var corrattribute = document.getElementById("corrattribute").value; 
    var corrvalue = document.getElementById("corrvalue").value; 
    var corrattribute1 = document.getElementById("corrattribute1").value; 
    var corrvalue1 = document.getElementById("corrvalue1").value; 
    //    var corrattribute2 = document.getElementById("corrattribute1").value; 
    //    var corrvalue2 = document.getElementById("corrvalue1").value; 
    var docType = document.getElementById("docType").value; 
    var status = document.getElementById("status").value; 
    window.location="../download/gridDownload.action?downloadType="+dwdType+"&sheetType="+sheetType+"&dateFrom="+dateFrom+"&dateTo="+dateTo+"&senderId="+senderId+"&senderName="+senderName+"&receiverId="+receiverId+"&receiverName="+receiverName+"&corrattribute="+corrattribute+"&corrvalue="+corrvalue+"&corrattribute1="+corrattribute1+"&corrvalue1="+corrvalue1+"&docType="+docType
}

function gridDownloadforLoadTendering(sheetType,dwdType)
{
    //alert("hi");
    var dateFrom = document.getElementById("docdatepickerfrom").value;
    var dateTo = document.getElementById("docdatepicker").value;
    var senderId = document.getElementById("docSenderId").value;
    var senderName = document.getElementById("docSenderName").value;
    //alert("senderName --->"+senderName);
    var receiverId = document.getElementById("docBusId").value;
    var receiverName = document.getElementById("docRecName").value;
    var corrattribute = document.getElementById("corrattribute").value;
    var corrvalue = document.getElementById("corrvalue").value;
    var corrattribute1 = document.getElementById("corrattribute1").value;
    var corrvalue1 = document.getElementById("corrvalue1").value;
    var corrattribute2 = document.getElementById("corrattribute2").value;
    var corrvalue2 = document.getElementById("corrvalue2").value;
    var docType = document.getElementById("docType").value;
    var status = document.getElementById("status").value;
    window.location="../download/gridDownload.action?downloadType="+dwdType+"&sheetType="+sheetType+"&dateFrom="+dateFrom+"&dateTo="+dateTo+"&senderId="+senderId+"&senderName="+senderName+"&receiverId="+receiverId+"&receiverName="+receiverName+"&corrattribute="+corrattribute+"&corrvalue="+corrvalue+"&corrattribute1="+corrattribute1+"&corrattribute2="+corrattribute2+"&corrvalue2="+corrvalue2+"&corrvalue1="+corrvalue1+"&docType="+docType+"&status="+status;

}

function gridDownloadforLtresponse(sheetType,dwdType){
    var dateFrom = document.getElementById("datepickerfrom").value;
    var dateTo = document.getElementById("datepickerTo").value;
    var senderId = document.getElementById("senderId").value;
    var senderName = document.getElementById("senderName").value;
    var receiverId = document.getElementById("receiverId").value;
    
    var receiverName = document.getElementById("receiverName").value;
    var corrattribute = document.getElementById("corrattribute").value;
    var corrvalue = document.getElementById("corrvalue").value;
    var corrattribute1 = document.getElementById("corrattribute1").value;
    var corrvalue1 = document.getElementById("corrvalue1").value;
    var docType = document.getElementById("docType").value;
    var status = document.getElementById("status").value;
    window.location="../download/gridDownload.action?downloadType="+dwdType+"&sheetType="+sheetType+"&dateFrom="+dateFrom+"&dateTo="+dateTo+"&senderId="+senderId+"&senderName="+senderName+"&receiverId="+receiverId+"&receiverName="+receiverName+"&corrattribute="+corrattribute+"&corrvalue="+corrvalue+"&corrattribute1="+corrattribute1+"&corrvalue1="+corrvalue1+"&docType="+docType+"&status="+status;

}